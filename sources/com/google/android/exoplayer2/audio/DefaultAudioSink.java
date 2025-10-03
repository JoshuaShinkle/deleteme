package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class DefaultAudioSink implements AudioSink {
    private static final int BUFFER_MULTIPLICATION_FACTOR = 4;
    private static final int ERROR_BAD_VALUE = -2;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final long MAX_BUFFER_DURATION_US = 750000;
    private static final long MAX_LATENCY_US = 5000000;
    private static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    private static final long MIN_BUFFER_DURATION_US = 250000;
    private static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    private static final int MIN_TIMESTAMP_SAMPLE_INTERVAL_US = 500000;
    private static final int MODE_STATIC = 0;
    private static final int MODE_STREAM = 1;
    private static final long PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int PLAYSTATE_PAUSED = 2;
    private static final int PLAYSTATE_PLAYING = 3;
    private static final int PLAYSTATE_STOPPED = 1;
    private static final int START_IN_SYNC = 1;
    private static final int START_NEED_SYNC = 2;
    private static final int START_NOT_SET = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final String TAG = "AudioTrack";

    @SuppressLint({"InlinedApi"})
    private static final int WRITE_NON_BLOCKING = 1;
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private AudioProcessor[] audioProcessors;
    private int audioSessionId;
    private boolean audioTimestampSet;
    private AudioTrack audioTrack;
    private final AudioTrackUtil audioTrackUtil;
    private ByteBuffer avSyncHeader;
    private int bufferSize;
    private long bufferSizeUs;
    private int bytesUntilNextAvSync;
    private boolean canApplyPlaybackParameters;
    private int channelConfig;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private int drainingAudioProcessorIndex;
    private PlaybackParameters drainingPlaybackParameters;
    private final boolean enableConvertHighResIntPcmToFloat;
    private int framesPerEncodedSample;
    private Method getLatencyMethod;
    private boolean handledEndOfStream;
    private boolean hasData;
    private ByteBuffer inputBuffer;
    private int inputSampleRate;
    private boolean isInputPcm;
    private AudioTrack keepSessionIdAudioTrack;
    private long lastFeedElapsedRealtimeMs;
    private long lastPlayheadSampleTimeUs;
    private long lastTimestampSampleTimeUs;
    private long latencyUs;
    private AudioSink.Listener listener;
    private int nextPlayheadOffsetIndex;
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    private int outputEncoding;
    private int outputPcmFrameSize;
    private int pcmFrameSize;
    private PlaybackParameters playbackParameters;
    private final ArrayDeque<PlaybackParametersCheckpoint> playbackParametersCheckpoints;
    private long playbackParametersOffsetUs;
    private long playbackParametersPositionUs;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    private boolean processingEnabled;
    private final ConditionVariable releasingConditionVariable;
    private long resumeSystemTimeUs;
    private int sampleRate;
    private boolean shouldConvertHighResIntPcmToFloat;
    private long smoothedPlayheadOffsetUs;
    private final SonicAudioProcessor sonicAudioProcessor;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    private final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    public static class AudioTrackUtil {
        private static final long FORCE_RESET_WORKAROUND_TIMEOUT_MS = 200;
        protected AudioTrack audioTrack;
        private long endPlaybackHeadPosition;
        private long forceResetWorkaroundTimeMs;
        private long lastRawPlaybackHeadPosition;
        private boolean needsPassthroughWorkaround;
        private long passthroughWorkaroundPauseOffset;
        private long rawPlaybackHeadWrapCount;
        private int sampleRate;
        private long stopPlaybackHeadPosition;
        private long stopTimestampUs;

        private AudioTrackUtil() {
        }

        public long getPlaybackHeadPosition() {
            if (this.stopTimestampUs != C3322C.TIME_UNSET) {
                return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + ((((SystemClock.elapsedRealtime() * 1000) - this.stopTimestampUs) * this.sampleRate) / C3322C.MICROS_PER_SECOND));
            }
            int playState = this.audioTrack.getPlayState();
            if (playState == 1) {
                return 0L;
            }
            long playbackHeadPosition = this.audioTrack.getPlaybackHeadPosition() & 4294967295L;
            if (this.needsPassthroughWorkaround) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                }
                playbackHeadPosition += this.passthroughWorkaroundPauseOffset;
            }
            if (Util.SDK_INT <= 28) {
                if (playbackHeadPosition == 0 && this.lastRawPlaybackHeadPosition > 0 && playState == 3) {
                    if (this.forceResetWorkaroundTimeMs == C3322C.TIME_UNSET) {
                        this.forceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                    }
                    return this.lastRawPlaybackHeadPosition;
                }
                this.forceResetWorkaroundTimeMs = C3322C.TIME_UNSET;
            }
            if (this.lastRawPlaybackHeadPosition > playbackHeadPosition) {
                this.rawPlaybackHeadWrapCount++;
            }
            this.lastRawPlaybackHeadPosition = playbackHeadPosition;
            return playbackHeadPosition + (this.rawPlaybackHeadWrapCount << 32);
        }

        public long getPositionUs() {
            return (getPlaybackHeadPosition() * C3322C.MICROS_PER_SECOND) / this.sampleRate;
        }

        public long getTimestampFramePosition() {
            throw new UnsupportedOperationException();
        }

        public long getTimestampNanoTime() {
            throw new UnsupportedOperationException();
        }

        public void handleEndOfStream(long j9) throws IllegalStateException {
            this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
            this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
            this.endPlaybackHeadPosition = j9;
            this.audioTrack.stop();
        }

        public boolean needsReset(long j9) {
            return this.forceResetWorkaroundTimeMs != C3322C.TIME_UNSET && j9 > 0 && SystemClock.elapsedRealtime() - this.forceResetWorkaroundTimeMs >= FORCE_RESET_WORKAROUND_TIMEOUT_MS;
        }

        public void pause() throws IllegalStateException {
            if (this.stopTimestampUs != C3322C.TIME_UNSET) {
                return;
            }
            this.audioTrack.pause();
        }

        public void reconfigure(AudioTrack audioTrack, boolean z8) {
            this.audioTrack = audioTrack;
            this.needsPassthroughWorkaround = z8;
            this.stopTimestampUs = C3322C.TIME_UNSET;
            this.forceResetWorkaroundTimeMs = C3322C.TIME_UNSET;
            this.lastRawPlaybackHeadPosition = 0L;
            this.rawPlaybackHeadWrapCount = 0L;
            this.passthroughWorkaroundPauseOffset = 0L;
            if (audioTrack != null) {
                this.sampleRate = audioTrack.getSampleRate();
            }
        }

        public boolean updateTimestamp() {
            return false;
        }
    }

    @TargetApi(19)
    public static class AudioTrackUtilV19 extends AudioTrackUtil {
        private final AudioTimestamp audioTimestamp;
        private long lastRawTimestampFramePosition;
        private long lastTimestampFramePosition;
        private long rawTimestampFramePositionWrapCount;

        public AudioTrackUtilV19() {
            super();
            this.audioTimestamp = new AudioTimestamp();
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.AudioTrackUtil
        public long getTimestampFramePosition() {
            return this.lastTimestampFramePosition;
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.AudioTrackUtil
        public long getTimestampNanoTime() {
            return this.audioTimestamp.nanoTime;
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.AudioTrackUtil
        public void reconfigure(AudioTrack audioTrack, boolean z8) {
            super.reconfigure(audioTrack, z8);
            this.rawTimestampFramePositionWrapCount = 0L;
            this.lastRawTimestampFramePosition = 0L;
            this.lastTimestampFramePosition = 0L;
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.AudioTrackUtil
        public boolean updateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j9 = this.audioTimestamp.framePosition;
                if (this.lastRawTimestampFramePosition > j9) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastRawTimestampFramePosition = j9;
                this.lastTimestampFramePosition = j9 + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        public InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static final class PlaybackParametersCheckpoint {
        private final long mediaTimeUs;
        private final PlaybackParameters playbackParameters;
        private final long positionUs;

        private PlaybackParametersCheckpoint(PlaybackParameters playbackParameters, long j9, long j10) {
            this.playbackParameters = playbackParameters;
            this.mediaTimeUs = j9;
            this.positionUs = j10;
        }
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities, AudioProcessor[] audioProcessorArr) {
        this(audioCapabilities, audioProcessorArr, false);
    }

    private long applySpeedup(long j9) {
        long j10;
        long mediaDurationForPlayoutDuration;
        while (!this.playbackParametersCheckpoints.isEmpty() && j9 >= this.playbackParametersCheckpoints.getFirst().positionUs) {
            PlaybackParametersCheckpoint playbackParametersCheckpointRemove = this.playbackParametersCheckpoints.remove();
            this.playbackParameters = playbackParametersCheckpointRemove.playbackParameters;
            this.playbackParametersPositionUs = playbackParametersCheckpointRemove.positionUs;
            this.playbackParametersOffsetUs = playbackParametersCheckpointRemove.mediaTimeUs - this.startMediaTimeUs;
        }
        if (this.playbackParameters.speed == 1.0f) {
            return (j9 + this.playbackParametersOffsetUs) - this.playbackParametersPositionUs;
        }
        if (this.playbackParametersCheckpoints.isEmpty()) {
            j10 = this.playbackParametersOffsetUs;
            mediaDurationForPlayoutDuration = this.sonicAudioProcessor.scaleDurationForSpeedup(j9 - this.playbackParametersPositionUs);
        } else {
            j10 = this.playbackParametersOffsetUs;
            mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(j9 - this.playbackParametersPositionUs, this.playbackParameters.speed);
        }
        return j10 + mediaDurationForPlayoutDuration;
    }

    @TargetApi(21)
    private AudioTrack createAudioTrackV21() {
        android.media.AudioAttributes audioAttributesBuild = this.tunneling ? new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build() : this.audioAttributes.getAudioAttributesV21();
        AudioFormat audioFormatBuild = new AudioFormat.Builder().setChannelMask(this.channelConfig).setEncoding(this.outputEncoding).setSampleRate(this.sampleRate).build();
        int i9 = this.audioSessionId;
        if (i9 == 0) {
            i9 = 0;
        }
        return new AudioTrack(audioAttributesBuild, audioFormatBuild, this.bufferSize, 1, i9);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0032 -> B:9:0x0012). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean drainAudioProcessorsToEndOfStream() throws AudioSink.WriteException {
        boolean z8;
        int i9;
        AudioProcessor[] audioProcessorArr;
        if (this.drainingAudioProcessorIndex == -1) {
            this.drainingAudioProcessorIndex = this.processingEnabled ? 0 : this.audioProcessors.length;
            z8 = true;
            i9 = this.drainingAudioProcessorIndex;
            audioProcessorArr = this.audioProcessors;
            if (i9 < audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i9];
                if (z8) {
                    audioProcessor.queueEndOfStream();
                }
                processBuffers(C3322C.TIME_UNSET);
                if (!audioProcessor.isEnded()) {
                    return false;
                }
                this.drainingAudioProcessorIndex++;
                z8 = true;
                i9 = this.drainingAudioProcessorIndex;
                audioProcessorArr = this.audioProcessors;
                if (i9 < audioProcessorArr.length) {
                    ByteBuffer byteBuffer = this.outputBuffer;
                    if (byteBuffer != null) {
                        writeBuffer(byteBuffer, C3322C.TIME_UNSET);
                        if (this.outputBuffer != null) {
                            return false;
                        }
                    }
                    this.drainingAudioProcessorIndex = -1;
                    return true;
                }
            }
        } else {
            z8 = false;
            i9 = this.drainingAudioProcessorIndex;
            audioProcessorArr = this.audioProcessors;
            if (i9 < audioProcessorArr.length) {
            }
        }
    }

    private long durationUsToFrames(long j9) {
        return (j9 * this.sampleRate) / C3322C.MICROS_PER_SECOND;
    }

    private long framesToDurationUs(long j9) {
        return (j9 * C3322C.MICROS_PER_SECOND) / this.sampleRate;
    }

    private AudioProcessor[] getAvailableAudioProcessors() {
        return this.shouldConvertHighResIntPcmToFloat ? this.toFloatPcmAvailableAudioProcessors : this.toIntPcmAvailableAudioProcessors;
    }

    private static int getFramesPerEncodedSample(int i9, ByteBuffer byteBuffer) {
        if (i9 == 7 || i9 == 8) {
            return DtsUtil.parseDtsAudioSampleCount(byteBuffer);
        }
        if (i9 == 5) {
            return Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        if (i9 == 6) {
            return Ac3Util.parseEAc3SyncframeAudioSampleCount(byteBuffer);
        }
        if (i9 == 14) {
            return Ac3Util.parseTrueHdSyncframeAudioSampleCount(byteBuffer) * 8;
        }
        throw new IllegalStateException("Unexpected audio encoding: " + i9);
    }

    private long getSubmittedFrames() {
        return this.isInputPcm ? this.submittedPcmBytes / this.pcmFrameSize : this.submittedEncodedFrames;
    }

    private long getWrittenFrames() {
        return this.isInputPcm ? this.writtenPcmBytes / this.outputPcmFrameSize : this.writtenEncodedFrames;
    }

    private boolean hasCurrentPositionUs() {
        return isInitialized() && this.startMediaTimeState != 0;
    }

    private void initialize() {
        this.releasingConditionVariable.block();
        this.audioTrack = initializeAudioTrack();
        setPlaybackParameters(this.playbackParameters);
        resetAudioProcessors();
        int audioSessionId = this.audioTrack.getAudioSessionId();
        if (enablePreV21AudioSessionWorkaround && Util.SDK_INT < 21) {
            AudioTrack audioTrack = this.keepSessionIdAudioTrack;
            if (audioTrack != null && audioSessionId != audioTrack.getAudioSessionId()) {
                releaseKeepSessionIdAudioTrack();
            }
            if (this.keepSessionIdAudioTrack == null) {
                this.keepSessionIdAudioTrack = initializeKeepSessionIdAudioTrack(audioSessionId);
            }
        }
        if (this.audioSessionId != audioSessionId) {
            this.audioSessionId = audioSessionId;
            AudioSink.Listener listener = this.listener;
            if (listener != null) {
                listener.onAudioSessionId(audioSessionId);
            }
        }
        this.audioTrackUtil.reconfigure(this.audioTrack, needsPassthroughWorkarounds());
        setVolumeInternal();
        this.hasData = false;
    }

    private AudioTrack initializeAudioTrack() throws AudioSink.InitializationException {
        AudioTrack audioTrack;
        if (Util.SDK_INT >= 21) {
            audioTrack = createAudioTrackV21();
        } else {
            int streamTypeForAudioUsage = Util.getStreamTypeForAudioUsage(this.audioAttributes.usage);
            audioTrack = this.audioSessionId == 0 ? new AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1) : new AudioTrack(streamTypeForAudioUsage, this.sampleRate, this.channelConfig, this.outputEncoding, this.bufferSize, 1, this.audioSessionId);
        }
        int state = audioTrack.getState();
        if (state == 1) {
            return audioTrack;
        }
        try {
            audioTrack.release();
        } catch (Exception unused) {
        }
        throw new AudioSink.InitializationException(state, this.sampleRate, this.channelConfig, this.bufferSize);
    }

    private AudioTrack initializeKeepSessionIdAudioTrack(int i9) {
        return new AudioTrack(3, 4000, 4, 2, 2, 0, i9);
    }

    private long inputFramesToDurationUs(long j9) {
        return (j9 * C3322C.MICROS_PER_SECOND) / this.inputSampleRate;
    }

    private static boolean isEncodingPcm(int i9) {
        return i9 == 3 || i9 == 2 || i9 == Integer.MIN_VALUE || i9 == 1073741824 || i9 == 4;
    }

    private boolean isInitialized() {
        return this.audioTrack != null;
    }

    private void maybeSampleSyncParams() {
        long positionUs = this.audioTrackUtil.getPositionUs();
        if (positionUs == 0) {
            return;
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (jNanoTime - this.lastPlayheadSampleTimeUs >= SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS) {
            long[] jArr = this.playheadOffsets;
            int i9 = this.nextPlayheadOffsetIndex;
            jArr[i9] = positionUs - jNanoTime;
            this.nextPlayheadOffsetIndex = (i9 + 1) % 10;
            int i10 = this.playheadOffsetCount;
            if (i10 < 10) {
                this.playheadOffsetCount = i10 + 1;
            }
            this.lastPlayheadSampleTimeUs = jNanoTime;
            this.smoothedPlayheadOffsetUs = 0L;
            int i11 = 0;
            while (true) {
                int i12 = this.playheadOffsetCount;
                if (i11 >= i12) {
                    break;
                }
                this.smoothedPlayheadOffsetUs += this.playheadOffsets[i11] / i12;
                i11++;
            }
        }
        if (!needsPassthroughWorkarounds() && jNanoTime - this.lastTimestampSampleTimeUs >= 500000) {
            boolean zUpdateTimestamp = this.audioTrackUtil.updateTimestamp();
            this.audioTimestampSet = zUpdateTimestamp;
            if (zUpdateTimestamp) {
                long timestampNanoTime = this.audioTrackUtil.getTimestampNanoTime() / 1000;
                long timestampFramePosition = this.audioTrackUtil.getTimestampFramePosition();
                if (timestampNanoTime < this.resumeSystemTimeUs) {
                    this.audioTimestampSet = false;
                } else if (Math.abs(timestampNanoTime - jNanoTime) > 5000000) {
                    String str = "Spurious audio timestamp (system clock mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + positionUs + ", " + getSubmittedFrames() + ", " + getWrittenFrames();
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str);
                    }
                    Log.w(TAG, str);
                    this.audioTimestampSet = false;
                } else if (Math.abs(framesToDurationUs(timestampFramePosition) - positionUs) > 5000000) {
                    String str2 = "Spurious audio timestamp (frame position mismatch): " + timestampFramePosition + ", " + timestampNanoTime + ", " + jNanoTime + ", " + positionUs + ", " + getSubmittedFrames() + ", " + getWrittenFrames();
                    if (failOnSpuriousAudioTimestamp) {
                        throw new InvalidAudioTrackTimestampException(str2);
                    }
                    Log.w(TAG, str2);
                    this.audioTimestampSet = false;
                }
            }
            if (this.getLatencyMethod != null && this.isInputPcm) {
                try {
                    long jIntValue = (((Integer) r1.invoke(this.audioTrack, null)).intValue() * 1000) - this.bufferSizeUs;
                    this.latencyUs = jIntValue;
                    long jMax = Math.max(jIntValue, 0L);
                    this.latencyUs = jMax;
                    if (jMax > 5000000) {
                        Log.w(TAG, "Ignoring impossibly large audio latency: " + this.latencyUs);
                        this.latencyUs = 0L;
                    }
                } catch (Exception unused) {
                    this.getLatencyMethod = null;
                }
            }
            this.lastTimestampSampleTimeUs = jNanoTime;
        }
    }

    private boolean needsPassthroughWorkarounds() {
        int i9;
        return Util.SDK_INT < 23 && ((i9 = this.outputEncoding) == 5 || i9 == 6);
    }

    private boolean overrideHasPendingData() {
        return needsPassthroughWorkarounds() && this.audioTrack.getPlayState() == 2 && this.audioTrack.getPlaybackHeadPosition() == 0;
    }

    private void processBuffers(long j9) throws AudioSink.WriteException {
        ByteBuffer byteBuffer;
        int length = this.audioProcessors.length;
        int i9 = length;
        while (i9 >= 0) {
            if (i9 > 0) {
                byteBuffer = this.outputBuffers[i9 - 1];
            } else {
                byteBuffer = this.inputBuffer;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.EMPTY_BUFFER;
                }
            }
            if (i9 == length) {
                writeBuffer(byteBuffer, j9);
            } else {
                AudioProcessor audioProcessor = this.audioProcessors[i9];
                audioProcessor.queueInput(byteBuffer);
                ByteBuffer output = audioProcessor.getOutput();
                this.outputBuffers[i9] = output;
                if (output.hasRemaining()) {
                    i9++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            } else {
                i9--;
            }
        }
    }

    private void releaseKeepSessionIdAudioTrack() {
        final AudioTrack audioTrack = this.keepSessionIdAudioTrack;
        if (audioTrack == null) {
            return;
        }
        this.keepSessionIdAudioTrack = null;
        new Thread() { // from class: com.google.android.exoplayer2.audio.DefaultAudioSink.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                audioTrack.release();
            }
        }.start();
    }

    private void resetAudioProcessors() {
        ArrayList arrayList = new ArrayList();
        for (AudioProcessor audioProcessor : getAvailableAudioProcessors()) {
            if (audioProcessor.isActive()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.flush();
            }
        }
        int size = arrayList.size();
        this.audioProcessors = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.outputBuffers = new ByteBuffer[size];
        for (int i9 = 0; i9 < size; i9++) {
            AudioProcessor audioProcessor2 = this.audioProcessors[i9];
            audioProcessor2.flush();
            this.outputBuffers[i9] = audioProcessor2.getOutput();
        }
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0L;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0L;
        this.audioTimestampSet = false;
        this.lastTimestampSampleTimeUs = 0L;
    }

    private void setVolumeInternal() {
        if (isInitialized()) {
            if (Util.SDK_INT >= 21) {
                setVolumeInternalV21(this.audioTrack, this.volume);
            } else {
                setVolumeInternalV3(this.audioTrack, this.volume);
            }
        }
    }

    @TargetApi(21)
    private static void setVolumeInternalV21(AudioTrack audioTrack, float f9) {
        audioTrack.setVolume(f9);
    }

    private static void setVolumeInternalV3(AudioTrack audioTrack, float f9) {
        audioTrack.setStereoVolume(f9, f9);
    }

    private void writeBuffer(ByteBuffer byteBuffer, long j9) throws AudioSink.WriteException {
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.outputBuffer;
            int iWriteNonBlockingV21 = 0;
            if (byteBuffer2 != null) {
                Assertions.checkArgument(byteBuffer2 == byteBuffer);
            } else {
                this.outputBuffer = byteBuffer;
                if (Util.SDK_INT < 21) {
                    int iRemaining = byteBuffer.remaining();
                    byte[] bArr = this.preV21OutputBuffer;
                    if (bArr == null || bArr.length < iRemaining) {
                        this.preV21OutputBuffer = new byte[iRemaining];
                    }
                    int iPosition = byteBuffer.position();
                    byteBuffer.get(this.preV21OutputBuffer, 0, iRemaining);
                    byteBuffer.position(iPosition);
                    this.preV21OutputBufferOffset = 0;
                }
            }
            int iRemaining2 = byteBuffer.remaining();
            if (Util.SDK_INT < 21) {
                int playbackHeadPosition = this.bufferSize - ((int) (this.writtenPcmBytes - (this.audioTrackUtil.getPlaybackHeadPosition() * this.outputPcmFrameSize)));
                if (playbackHeadPosition > 0) {
                    iWriteNonBlockingV21 = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(iRemaining2, playbackHeadPosition));
                    if (iWriteNonBlockingV21 > 0) {
                        this.preV21OutputBufferOffset += iWriteNonBlockingV21;
                        byteBuffer.position(byteBuffer.position() + iWriteNonBlockingV21);
                    }
                }
            } else if (this.tunneling) {
                Assertions.checkState(j9 != C3322C.TIME_UNSET);
                iWriteNonBlockingV21 = writeNonBlockingWithAvSyncV21(this.audioTrack, byteBuffer, iRemaining2, j9);
            } else {
                iWriteNonBlockingV21 = writeNonBlockingV21(this.audioTrack, byteBuffer, iRemaining2);
            }
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if (iWriteNonBlockingV21 < 0) {
                throw new AudioSink.WriteException(iWriteNonBlockingV21);
            }
            boolean z8 = this.isInputPcm;
            if (z8) {
                this.writtenPcmBytes += iWriteNonBlockingV21;
            }
            if (iWriteNonBlockingV21 == iRemaining2) {
                if (!z8) {
                    this.writtenEncodedFrames += this.framesPerEncodedSample;
                }
                this.outputBuffer = null;
            }
        }
    }

    @TargetApi(21)
    private static int writeNonBlockingV21(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9) {
        return audioTrack.write(byteBuffer, i9, 1);
    }

    @TargetApi(21)
    private int writeNonBlockingWithAvSyncV21(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9, long j9) {
        if (this.avSyncHeader == null) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
            this.avSyncHeader = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i9);
            this.avSyncHeader.putLong(8, j9 * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i9;
        }
        int iRemaining = this.avSyncHeader.remaining();
        if (iRemaining > 0) {
            int iWrite = audioTrack.write(this.avSyncHeader, iRemaining, 1);
            if (iWrite < 0) {
                this.bytesUntilNextAvSync = 0;
                return iWrite;
            }
            if (iWrite < iRemaining) {
                return 0;
            }
        }
        int iWriteNonBlockingV21 = writeNonBlockingV21(audioTrack, byteBuffer, i9);
        if (iWriteNonBlockingV21 < 0) {
            this.bytesUntilNextAvSync = 0;
            return iWriteNonBlockingV21;
        }
        this.bytesUntilNextAvSync -= iWriteNonBlockingV21;
        return iWriteNonBlockingV21;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0131  */
    @Override // com.google.android.exoplayer2.audio.AudioSink
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void configure(int i9, int i10, int i11, int i12, int[] iArr, int i13, int i14) throws IllegalStateException, AudioSink.ConfigurationException {
        int outputEncoding;
        boolean z8;
        int outputSampleRateHz;
        boolean zConfigure;
        int outputChannelCount;
        int i15;
        int i16;
        int i17;
        this.inputSampleRate = i11;
        this.isInputPcm = isEncodingPcm(i9);
        this.shouldConvertHighResIntPcmToFloat = this.enableConvertHighResIntPcmToFloat && isEncodingSupported(1073741824) && Util.isEncodingHighResolutionIntegerPcm(i9);
        if (this.isInputPcm) {
            this.pcmFrameSize = Util.getPcmFrameSize(i9, i10);
        }
        int i18 = 4;
        if (this.isInputPcm) {
            outputEncoding = i9;
            z8 = outputEncoding != 4;
            this.canApplyPlaybackParameters = (z8 || this.shouldConvertHighResIntPcmToFloat) ? false : true;
            if (z8) {
                outputSampleRateHz = i11;
                zConfigure = false;
                outputChannelCount = i10;
            } else {
                this.trimmingAudioProcessor.setTrimSampleCount(i13, i14);
                this.channelMappingAudioProcessor.setChannelMap(iArr);
                outputSampleRateHz = i11;
                zConfigure = false;
                outputChannelCount = i10;
                for (AudioProcessor audioProcessor : getAvailableAudioProcessors()) {
                    try {
                        zConfigure |= audioProcessor.configure(outputSampleRateHz, outputChannelCount, outputEncoding);
                        if (audioProcessor.isActive()) {
                            outputChannelCount = audioProcessor.getOutputChannelCount();
                            outputSampleRateHz = audioProcessor.getOutputSampleRateHz();
                            outputEncoding = audioProcessor.getOutputEncoding();
                        }
                    } catch (AudioProcessor.UnhandledFormatException e9) {
                        throw new AudioSink.ConfigurationException(e9);
                    }
                }
            }
            i15 = 252;
            switch (outputChannelCount) {
                case 1:
                    break;
                case 2:
                    i18 = 12;
                    break;
                case 3:
                    i18 = 28;
                    break;
                case 4:
                    i18 = 204;
                    break;
                case 5:
                    i18 = 220;
                    break;
                case 6:
                    i18 = 252;
                    break;
                case 7:
                    i18 = 1276;
                    break;
                case 8:
                    i18 = C3322C.CHANNEL_OUT_7POINT1_SURROUND;
                    break;
                default:
                    throw new AudioSink.ConfigurationException("Unsupported channel count: " + outputChannelCount);
            }
            i16 = Util.SDK_INT;
            if (i16 <= 23 || !"foster".equals(Util.DEVICE) || !"NVIDIA".equals(Util.MANUFACTURER)) {
                i15 = i18;
            } else if (outputChannelCount != 3 && outputChannelCount != 5) {
                if (outputChannelCount == 7) {
                    i15 = C3322C.CHANNEL_OUT_7POINT1_SURROUND;
                }
            }
            i17 = (i16 <= 25 || !"fugu".equals(Util.DEVICE) || this.isInputPcm || outputChannelCount != 1) ? i15 : 12;
            if (zConfigure && isInitialized() && this.outputEncoding == outputEncoding && this.sampleRate == outputSampleRateHz && this.channelConfig == i17) {
                return;
            }
            reset();
            this.processingEnabled = z8;
            this.sampleRate = outputSampleRateHz;
            this.channelConfig = i17;
            this.outputEncoding = outputEncoding;
            if (this.isInputPcm) {
                this.outputPcmFrameSize = Util.getPcmFrameSize(outputEncoding, outputChannelCount);
            }
            if (i12 == 0) {
                this.bufferSize = i12;
            } else if (this.isInputPcm) {
                int minBufferSize = AudioTrack.getMinBufferSize(outputSampleRateHz, i17, this.outputEncoding);
                Assertions.checkState(minBufferSize != -2);
                this.bufferSize = Util.constrainValue(minBufferSize * 4, ((int) durationUsToFrames(250000L)) * this.outputPcmFrameSize, (int) Math.max(minBufferSize, durationUsToFrames(MAX_BUFFER_DURATION_US) * this.outputPcmFrameSize));
            } else {
                int i19 = this.outputEncoding;
                if (i19 == 5 || i19 == 6) {
                    this.bufferSize = CacheDataSink.DEFAULT_BUFFER_SIZE;
                } else if (i19 == 7) {
                    this.bufferSize = 49152;
                } else {
                    this.bufferSize = 294912;
                }
            }
            this.bufferSizeUs = !this.isInputPcm ? framesToDurationUs(this.bufferSize / this.outputPcmFrameSize) : C3322C.TIME_UNSET;
        }
        outputEncoding = i9;
        this.canApplyPlaybackParameters = (z8 || this.shouldConvertHighResIntPcmToFloat) ? false : true;
        if (z8) {
        }
        i15 = 252;
        switch (outputChannelCount) {
        }
        i16 = Util.SDK_INT;
        if (i16 <= 23) {
            i15 = i18;
        }
        if (i16 <= 25) {
        }
        if (zConfigure) {
        }
        reset();
        this.processingEnabled = z8;
        this.sampleRate = outputSampleRateHz;
        this.channelConfig = i17;
        this.outputEncoding = outputEncoding;
        if (this.isInputPcm) {
        }
        if (i12 == 0) {
        }
        this.bufferSizeUs = !this.isInputPcm ? framesToDurationUs(this.bufferSize / this.outputPcmFrameSize) : C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void disableTunneling() throws IllegalStateException {
        if (this.tunneling) {
            this.tunneling = false;
            this.audioSessionId = 0;
            reset();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void enableTunnelingV21(int i9) throws IllegalStateException {
        Assertions.checkState(Util.SDK_INT >= 21);
        if (this.tunneling && this.audioSessionId == i9) {
            return;
        }
        this.tunneling = true;
        this.audioSessionId = i9;
        reset();
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public long getCurrentPositionUs(boolean z8) {
        long positionUs;
        if (!hasCurrentPositionUs()) {
            return Long.MIN_VALUE;
        }
        if (this.audioTrack.getPlayState() == 3) {
            maybeSampleSyncParams();
        }
        long jNanoTime = System.nanoTime() / 1000;
        if (this.audioTimestampSet) {
            positionUs = framesToDurationUs(this.audioTrackUtil.getTimestampFramePosition() + durationUsToFrames(jNanoTime - (this.audioTrackUtil.getTimestampNanoTime() / 1000)));
        } else {
            positionUs = this.playheadOffsetCount == 0 ? this.audioTrackUtil.getPositionUs() : jNanoTime + this.smoothedPlayheadOffsetUs;
            if (!z8) {
                positionUs -= this.latencyUs;
            }
        }
        return this.startMediaTimeUs + applySpeedup(Math.min(positionUs, framesToDurationUs(getWrittenFrames())));
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean handleBuffer(ByteBuffer byteBuffer, long j9) throws IllegalStateException, AudioSink.WriteException {
        String str;
        String str2;
        int i9;
        ByteBuffer byteBuffer2 = this.inputBuffer;
        Assertions.checkArgument(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (!isInitialized()) {
            initialize();
            if (this.playing) {
                play();
            }
        }
        if (needsPassthroughWorkarounds()) {
            if (this.audioTrack.getPlayState() == 2) {
                this.hasData = false;
                return false;
            }
            if (this.audioTrack.getPlayState() == 1 && this.audioTrackUtil.getPlaybackHeadPosition() != 0) {
                return false;
            }
        }
        boolean z8 = this.hasData;
        boolean zHasPendingData = hasPendingData();
        this.hasData = zHasPendingData;
        if (z8 && !zHasPendingData && this.audioTrack.getPlayState() != 1 && this.listener != null) {
            this.listener.onUnderrun(this.bufferSize, C3322C.usToMs(this.bufferSizeUs), SystemClock.elapsedRealtime() - this.lastFeedElapsedRealtimeMs);
        }
        if (this.inputBuffer != null) {
            str = TAG;
        } else {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (!this.isInputPcm && this.framesPerEncodedSample == 0) {
                int framesPerEncodedSample = getFramesPerEncodedSample(this.outputEncoding, byteBuffer);
                this.framesPerEncodedSample = framesPerEncodedSample;
                if (framesPerEncodedSample == 0) {
                    return true;
                }
            }
            if (this.drainingPlaybackParameters == null) {
                str2 = TAG;
            } else {
                if (!drainAudioProcessorsToEndOfStream()) {
                    return false;
                }
                ArrayDeque<PlaybackParametersCheckpoint> arrayDeque = this.playbackParametersCheckpoints;
                PlaybackParameters playbackParameters = this.drainingPlaybackParameters;
                long jMax = Math.max(0L, j9);
                str2 = TAG;
                arrayDeque.add(new PlaybackParametersCheckpoint(playbackParameters, jMax, framesToDurationUs(getWrittenFrames())));
                this.drainingPlaybackParameters = null;
                resetAudioProcessors();
            }
            if (this.startMediaTimeState == 0) {
                this.startMediaTimeUs = Math.max(0L, j9);
                this.startMediaTimeState = 1;
                str = str2;
            } else {
                long jInputFramesToDurationUs = this.startMediaTimeUs + inputFramesToDurationUs(getSubmittedFrames());
                if (this.startMediaTimeState != 1 || Math.abs(jInputFramesToDurationUs - j9) <= 200000) {
                    str = str2;
                    i9 = 2;
                } else {
                    str = str2;
                    Log.e(str, "Discontinuity detected [expected " + jInputFramesToDurationUs + ", got " + j9 + "]");
                    i9 = 2;
                    this.startMediaTimeState = 2;
                }
                if (this.startMediaTimeState == i9) {
                    this.startMediaTimeUs += j9 - jInputFramesToDurationUs;
                    this.startMediaTimeState = 1;
                    AudioSink.Listener listener = this.listener;
                    if (listener != null) {
                        listener.onPositionDiscontinuity();
                    }
                }
            }
            if (this.isInputPcm) {
                this.submittedPcmBytes += byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames += this.framesPerEncodedSample;
            }
            this.inputBuffer = byteBuffer;
        }
        if (this.processingEnabled) {
            processBuffers(j9);
        } else {
            writeBuffer(this.inputBuffer, j9);
        }
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            return true;
        }
        if (!this.audioTrackUtil.needsReset(getWrittenFrames())) {
            return false;
        }
        Log.w(str, "Resetting stalled audio track");
        reset();
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void handleDiscontinuity() {
        if (this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean hasPendingData() {
        return isInitialized() && (getWrittenFrames() > this.audioTrackUtil.getPlaybackHeadPosition() || overrideHasPendingData());
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean isEncodingSupported(int i9) {
        if (isEncodingPcm(i9)) {
            return i9 != 4 || Util.SDK_INT >= 21;
        }
        AudioCapabilities audioCapabilities = this.audioCapabilities;
        return audioCapabilities != null && audioCapabilities.supportsEncoding(i9);
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean isEnded() {
        return !isInitialized() || (this.handledEndOfStream && !hasPendingData());
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void pause() throws IllegalStateException {
        this.playing = false;
        if (isInitialized()) {
            resetSyncParams();
            this.audioTrackUtil.pause();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void play() throws IllegalStateException {
        this.playing = true;
        if (isInitialized()) {
            this.resumeSystemTimeUs = System.nanoTime() / 1000;
            this.audioTrack.play();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void playToEndOfStream() throws IllegalStateException {
        if (!this.handledEndOfStream && isInitialized() && drainAudioProcessorsToEndOfStream()) {
            this.audioTrackUtil.handleEndOfStream(getWrittenFrames());
            this.bytesUntilNextAvSync = 0;
            this.handledEndOfStream = true;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void release() throws IllegalStateException {
        reset();
        releaseKeepSessionIdAudioTrack();
        for (AudioProcessor audioProcessor : this.toIntPcmAvailableAudioProcessors) {
            audioProcessor.reset();
        }
        for (AudioProcessor audioProcessor2 : this.toFloatPcmAvailableAudioProcessors) {
            audioProcessor2.reset();
        }
        this.audioSessionId = 0;
        this.playing = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void reset() throws IllegalStateException {
        if (isInitialized()) {
            this.submittedPcmBytes = 0L;
            this.submittedEncodedFrames = 0L;
            this.writtenPcmBytes = 0L;
            this.writtenEncodedFrames = 0L;
            this.framesPerEncodedSample = 0;
            PlaybackParameters playbackParameters = this.drainingPlaybackParameters;
            if (playbackParameters != null) {
                this.playbackParameters = playbackParameters;
                this.drainingPlaybackParameters = null;
            } else if (!this.playbackParametersCheckpoints.isEmpty()) {
                this.playbackParameters = this.playbackParametersCheckpoints.getLast().playbackParameters;
            }
            this.playbackParametersCheckpoints.clear();
            this.playbackParametersOffsetUs = 0L;
            this.playbackParametersPositionUs = 0L;
            this.inputBuffer = null;
            this.outputBuffer = null;
            int i9 = 0;
            while (true) {
                AudioProcessor[] audioProcessorArr = this.audioProcessors;
                if (i9 >= audioProcessorArr.length) {
                    break;
                }
                AudioProcessor audioProcessor = audioProcessorArr[i9];
                audioProcessor.flush();
                this.outputBuffers[i9] = audioProcessor.getOutput();
                i9++;
            }
            this.handledEndOfStream = false;
            this.drainingAudioProcessorIndex = -1;
            this.avSyncHeader = null;
            this.bytesUntilNextAvSync = 0;
            this.startMediaTimeState = 0;
            this.latencyUs = 0L;
            resetSyncParams();
            if (this.audioTrack.getPlayState() == 3) {
                this.audioTrack.pause();
            }
            final AudioTrack audioTrack = this.audioTrack;
            this.audioTrack = null;
            this.audioTrackUtil.reconfigure(null, false);
            this.releasingConditionVariable.close();
            new Thread() { // from class: com.google.android.exoplayer2.audio.DefaultAudioSink.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        audioTrack.flush();
                        audioTrack.release();
                    } finally {
                        DefaultAudioSink.this.releasingConditionVariable.open();
                    }
                }
            }.start();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes) throws IllegalStateException {
        if (this.audioAttributes.equals(audioAttributes)) {
            return;
        }
        this.audioAttributes = audioAttributes;
        if (this.tunneling) {
            return;
        }
        reset();
        this.audioSessionId = 0;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setAudioSessionId(int i9) throws IllegalStateException {
        if (this.audioSessionId != i9) {
            this.audioSessionId = i9;
            reset();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
        this.listener = listener;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (isInitialized() && !this.canApplyPlaybackParameters) {
            PlaybackParameters playbackParameters2 = PlaybackParameters.DEFAULT;
            this.playbackParameters = playbackParameters2;
            return playbackParameters2;
        }
        PlaybackParameters playbackParameters3 = new PlaybackParameters(this.sonicAudioProcessor.setSpeed(playbackParameters.speed), this.sonicAudioProcessor.setPitch(playbackParameters.pitch));
        PlaybackParameters playbackParameters4 = this.drainingPlaybackParameters;
        if (playbackParameters4 == null) {
            playbackParameters4 = !this.playbackParametersCheckpoints.isEmpty() ? this.playbackParametersCheckpoints.getLast().playbackParameters : this.playbackParameters;
        }
        if (!playbackParameters3.equals(playbackParameters4)) {
            if (isInitialized()) {
                this.drainingPlaybackParameters = playbackParameters3;
            } else {
                this.playbackParameters = playbackParameters3;
            }
        }
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void setVolume(float f9) {
        if (this.volume != f9) {
            this.volume = f9;
            setVolumeInternal();
        }
    }

    public DefaultAudioSink(AudioCapabilities audioCapabilities, AudioProcessor[] audioProcessorArr, boolean z8) {
        this.audioCapabilities = audioCapabilities;
        this.enableConvertHighResIntPcmToFloat = z8;
        this.releasingConditionVariable = new ConditionVariable(true);
        if (Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (Util.SDK_INT >= 19) {
            this.audioTrackUtil = new AudioTrackUtilV19();
        } else {
            this.audioTrackUtil = new AudioTrackUtil();
        }
        ChannelMappingAudioProcessor channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor;
        TrimmingAudioProcessor trimmingAudioProcessor = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor;
        SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();
        this.sonicAudioProcessor = sonicAudioProcessor;
        AudioProcessor[] audioProcessorArr2 = new AudioProcessor[audioProcessorArr.length + 4];
        this.toIntPcmAvailableAudioProcessors = audioProcessorArr2;
        audioProcessorArr2[0] = new ResamplingAudioProcessor();
        audioProcessorArr2[1] = channelMappingAudioProcessor;
        audioProcessorArr2[2] = trimmingAudioProcessor;
        System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 3, audioProcessorArr.length);
        audioProcessorArr2[audioProcessorArr.length + 3] = sonicAudioProcessor;
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.playheadOffsets = new long[10];
        this.volume = 1.0f;
        this.startMediaTimeState = 0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.drainingAudioProcessorIndex = -1;
        this.audioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.playbackParametersCheckpoints = new ArrayDeque<>();
    }
}
