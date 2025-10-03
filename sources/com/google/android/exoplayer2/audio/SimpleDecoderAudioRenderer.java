package com.google.android.exoplayer2.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public abstract class SimpleDecoderAudioRenderer extends BaseRenderer implements MediaClock {
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private boolean allowFirstBufferPositionDiscontinuity;
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioTrackNeedsConfigure;
    private long currentPositionUs;
    private SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> decoder;
    private DecoderCounters decoderCounters;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private DrmSession<ExoMediaCrypto> drmSession;
    private final DrmSessionManager<ExoMediaCrypto> drmSessionManager;
    private int encoderDelay;
    private int encoderPadding;
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private final FormatHolder formatHolder;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private SimpleOutputBuffer outputBuffer;
    private boolean outputStreamEnded;
    private DrmSession<ExoMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean waitingForKeys;

    public final class AudioSinkListener implements AudioSink.Listener {
        private AudioSinkListener() {
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onAudioSessionId(int i9) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioSessionId(i9);
            SimpleDecoderAudioRenderer.this.onAudioSessionId(i9);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            SimpleDecoderAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            SimpleDecoderAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onUnderrun(int i9, long j9, long j10) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioTrackUnderrun(i9, j9, j10);
            SimpleDecoderAudioRenderer.this.onAudioTrackUnderrun(i9, j9, j10);
        }
    }

    public SimpleDecoderAudioRenderer() {
        this((Handler) null, (AudioRendererEventListener) null, new AudioProcessor[0]);
    }

    private boolean drainOutputBuffer() throws ExoPlaybackException, InterruptedException {
        if (this.outputBuffer == null) {
            SimpleOutputBuffer simpleOutputBuffer = (SimpleOutputBuffer) this.decoder.dequeueOutputBuffer();
            this.outputBuffer = simpleOutputBuffer;
            if (simpleOutputBuffer == null) {
                return false;
            }
            this.decoderCounters.skippedOutputBufferCount += simpleOutputBuffer.skippedOutputBufferCount;
        }
        if (this.outputBuffer.isEndOfStream()) {
            if (this.decoderReinitializationState == 2) {
                releaseDecoder();
                maybeInitDecoder();
                this.audioTrackNeedsConfigure = true;
            } else {
                this.outputBuffer.release();
                this.outputBuffer = null;
                processEndOfStream();
            }
            return false;
        }
        if (this.audioTrackNeedsConfigure) {
            Format outputFormat = getOutputFormat();
            this.audioSink.configure(outputFormat.pcmEncoding, outputFormat.channelCount, outputFormat.sampleRate, 0, null, this.encoderDelay, this.encoderPadding);
            this.audioTrackNeedsConfigure = false;
        }
        AudioSink audioSink = this.audioSink;
        SimpleOutputBuffer simpleOutputBuffer2 = this.outputBuffer;
        if (!audioSink.handleBuffer(simpleOutputBuffer2.data, simpleOutputBuffer2.timeUs)) {
            return false;
        }
        this.decoderCounters.renderedOutputBufferCount++;
        this.outputBuffer.release();
        this.outputBuffer = null;
        return true;
    }

    private boolean feedInputBuffer() throws ExoPlaybackException, InterruptedException {
        SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> simpleDecoder = this.decoder;
        if (simpleDecoder == null || this.decoderReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputBuffer == null) {
            DecoderInputBuffer decoderInputBufferDequeueInputBuffer = simpleDecoder.dequeueInputBuffer();
            this.inputBuffer = decoderInputBufferDequeueInputBuffer;
            if (decoderInputBufferDequeueInputBuffer == null) {
                return false;
            }
        }
        if (this.decoderReinitializationState == 1) {
            this.inputBuffer.setFlags(4);
            this.decoder.queueInputBuffer((SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException>) this.inputBuffer);
            this.inputBuffer = null;
            this.decoderReinitializationState = 2;
            return false;
        }
        int source = this.waitingForKeys ? -4 : readSource(this.formatHolder, this.inputBuffer, false);
        if (source == -3) {
            return false;
        }
        if (source == -5) {
            onInputFormatChanged(this.formatHolder.format);
            return true;
        }
        if (this.inputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            this.decoder.queueInputBuffer((SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException>) this.inputBuffer);
            this.inputBuffer = null;
            return false;
        }
        boolean zShouldWaitForKeys = shouldWaitForKeys(this.inputBuffer.isEncrypted());
        this.waitingForKeys = zShouldWaitForKeys;
        if (zShouldWaitForKeys) {
            return false;
        }
        this.inputBuffer.flip();
        onQueueInputBuffer(this.inputBuffer);
        this.decoder.queueInputBuffer((SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException>) this.inputBuffer);
        this.decoderReceivedBuffers = true;
        this.decoderCounters.inputBufferCount++;
        this.inputBuffer = null;
        return true;
    }

    private void flushDecoder() throws ExoPlaybackException, InterruptedException {
        this.waitingForKeys = false;
        if (this.decoderReinitializationState != 0) {
            releaseDecoder();
            maybeInitDecoder();
            return;
        }
        this.inputBuffer = null;
        SimpleOutputBuffer simpleOutputBuffer = this.outputBuffer;
        if (simpleOutputBuffer != null) {
            simpleOutputBuffer.release();
            this.outputBuffer = null;
        }
        this.decoder.flush();
        this.decoderReceivedBuffers = false;
    }

    private void maybeInitDecoder() throws ExoPlaybackException {
        ExoMediaCrypto mediaCrypto;
        if (this.decoder != null) {
            return;
        }
        DrmSession<ExoMediaCrypto> drmSession = this.pendingDrmSession;
        this.drmSession = drmSession;
        if (drmSession != null) {
            mediaCrypto = drmSession.getMediaCrypto();
            if (mediaCrypto == null && this.drmSession.getError() == null) {
                return;
            }
        } else {
            mediaCrypto = null;
        }
        try {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            TraceUtil.beginSection("createAudioDecoder");
            this.decoder = createDecoder(this.inputFormat, mediaCrypto);
            TraceUtil.endSection();
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            this.eventDispatcher.decoderInitialized(this.decoder.getName(), jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
            this.decoderCounters.decoderInitCount++;
        } catch (AudioDecoderException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    private void onInputFormatChanged(Format format) throws ExoPlaybackException, InterruptedException {
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        if (!Util.areEqual(format.drmInitData, format2 == null ? null : format2.drmInitData)) {
            if (this.inputFormat.drmInitData != null) {
                DrmSessionManager<ExoMediaCrypto> drmSessionManager = this.drmSessionManager;
                if (drmSessionManager == null) {
                    throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
                DrmSession<ExoMediaCrypto> drmSessionAcquireSession = drmSessionManager.acquireSession(Looper.myLooper(), this.inputFormat.drmInitData);
                this.pendingDrmSession = drmSessionAcquireSession;
                if (drmSessionAcquireSession == this.drmSession) {
                    this.drmSessionManager.releaseSession(drmSessionAcquireSession);
                }
            } else {
                this.pendingDrmSession = null;
            }
        }
        if (this.decoderReceivedBuffers) {
            this.decoderReinitializationState = 1;
        } else {
            releaseDecoder();
            maybeInitDecoder();
            this.audioTrackNeedsConfigure = true;
        }
        int i9 = format.encoderDelay;
        if (i9 == -1) {
            i9 = 0;
        }
        this.encoderDelay = i9;
        int i10 = format.encoderPadding;
        this.encoderPadding = i10 != -1 ? i10 : 0;
        this.eventDispatcher.inputFormatChanged(format);
    }

    private void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (!this.allowFirstBufferPositionDiscontinuity || decoderInputBuffer.isDecodeOnly()) {
            return;
        }
        if (Math.abs(decoderInputBuffer.timeUs - this.currentPositionUs) > 500000) {
            this.currentPositionUs = decoderInputBuffer.timeUs;
        }
        this.allowFirstBufferPositionDiscontinuity = false;
    }

    private void processEndOfStream() throws ExoPlaybackException {
        this.outputStreamEnded = true;
        try {
            this.audioSink.playToEndOfStream();
        } catch (AudioSink.WriteException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    private void releaseDecoder() throws InterruptedException {
        SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> simpleDecoder = this.decoder;
        if (simpleDecoder == null) {
            return;
        }
        this.inputBuffer = null;
        this.outputBuffer = null;
        simpleDecoder.release();
        this.decoder = null;
        this.decoderCounters.decoderReleaseCount++;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
    }

    private boolean shouldWaitForKeys(boolean z8) throws ExoPlaybackException {
        DrmSession<ExoMediaCrypto> drmSession = this.drmSession;
        if (drmSession == null || (!z8 && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = drmSession.getState();
        if (state != 1) {
            return state != 4;
        }
        throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
    }

    private void updateCurrentPosition() {
        long currentPositionUs = this.audioSink.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
    }

    public abstract SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> createDecoder(Format format, ExoMediaCrypto exoMediaCrypto);

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.Renderer
    public MediaClock getMediaClock() {
        return this;
    }

    public Format getOutputFormat() {
        Format format = this.inputFormat;
        return Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, -1, -1, format.channelCount, format.sampleRate, 2, null, null, 0, null);
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        if (getState() == 2) {
            updateCurrentPosition();
        }
        return this.currentPositionUs;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i9, Object obj) {
        if (i9 == 2) {
            this.audioSink.setVolume(((Float) obj).floatValue());
        } else if (i9 != 3) {
            super.handleMessage(i9, obj);
        } else {
            this.audioSink.setAudioAttributes((AudioAttributes) obj);
        }
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded && this.audioSink.isEnded();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.audioSink.hasPendingData() || !(this.inputFormat == null || this.waitingForKeys || (!isSourceReady() && this.outputBuffer == null));
    }

    public void onAudioSessionId(int i9) {
    }

    public void onAudioTrackPositionDiscontinuity() {
    }

    public void onAudioTrackUnderrun(int i9, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.audioTrackNeedsConfigure = true;
        this.waitingForKeys = false;
        try {
            releaseDecoder();
            this.audioSink.release();
            try {
                DrmSession<ExoMediaCrypto> drmSession = this.drmSession;
                if (drmSession != null) {
                    this.drmSessionManager.releaseSession(drmSession);
                }
                try {
                    DrmSession<ExoMediaCrypto> drmSession2 = this.pendingDrmSession;
                    if (drmSession2 != null && drmSession2 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession2);
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    DrmSession<ExoMediaCrypto> drmSession3 = this.pendingDrmSession;
                    if (drmSession3 != null && drmSession3 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession3);
                    }
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th2) {
            try {
                DrmSession<ExoMediaCrypto> drmSession4 = this.drmSession;
                if (drmSession4 != null) {
                    this.drmSessionManager.releaseSession(drmSession4);
                }
                try {
                    DrmSession<ExoMediaCrypto> drmSession5 = this.pendingDrmSession;
                    if (drmSession5 != null && drmSession5 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession5);
                    }
                    throw th2;
                } finally {
                }
            } catch (Throwable th3) {
                try {
                    DrmSession<ExoMediaCrypto> drmSession6 = this.pendingDrmSession;
                    if (drmSession6 != null && drmSession6 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession6);
                    }
                    throw th3;
                } finally {
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z8) {
        DecoderCounters decoderCounters = new DecoderCounters();
        this.decoderCounters = decoderCounters;
        this.eventDispatcher.enabled(decoderCounters);
        int i9 = getConfiguration().tunnelingAudioSessionId;
        if (i9 != 0) {
            this.audioSink.enableTunnelingV21(i9);
        } else {
            this.audioSink.disableTunneling();
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j9, boolean z8) throws ExoPlaybackException, InterruptedException {
        this.audioSink.reset();
        this.currentPositionUs = j9;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.decoder != null) {
            flushDecoder();
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        this.audioSink.play();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.audioSink.pause();
        updateCurrentPosition();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j9, long j10) throws ExoPlaybackException, InterruptedException {
        if (this.outputStreamEnded) {
            try {
                this.audioSink.playToEndOfStream();
                return;
            } catch (AudioSink.WriteException e9) {
                throw ExoPlaybackException.createForRenderer(e9, getIndex());
            }
        }
        if (this.inputFormat == null) {
            this.flagsOnlyBuffer.clear();
            int source = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if (source != -5) {
                if (source == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    processEndOfStream();
                    return;
                }
                return;
            }
            onInputFormatChanged(this.formatHolder.format);
        }
        maybeInitDecoder();
        if (this.decoder != null) {
            try {
                TraceUtil.beginSection("drainAndFeed");
                while (drainOutputBuffer()) {
                }
                while (feedInputBuffer()) {
                }
                TraceUtil.endSection();
                this.decoderCounters.ensureUpdated();
            } catch (AudioDecoderException | AudioSink.ConfigurationException | AudioSink.InitializationException | AudioSink.WriteException e10) {
                throw ExoPlaybackException.createForRenderer(e10, getIndex());
            }
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        return this.audioSink.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public final int supportsFormat(Format format) {
        int iSupportsFormatInternal = supportsFormatInternal(this.drmSessionManager, format);
        if (iSupportsFormatInternal <= 2) {
            return iSupportsFormatInternal;
        }
        return iSupportsFormatInternal | (Util.SDK_INT >= 21 ? 32 : 0) | 8;
    }

    public abstract int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager, Format format);

    public final boolean supportsOutputEncoding(int i9) {
        return this.audioSink.isEncodingSupported(i9);
    }

    public SimpleDecoderAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, null, null, false, audioProcessorArr);
    }

    public SimpleDecoderAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities) {
        this(handler, audioRendererEventListener, audioCapabilities, null, false, new AudioProcessor[0]);
    }

    public SimpleDecoderAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, DrmSessionManager<ExoMediaCrypto> drmSessionManager, boolean z8, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, drmSessionManager, z8, new DefaultAudioSink(audioCapabilities, audioProcessorArr));
    }

    public SimpleDecoderAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, DrmSessionManager<ExoMediaCrypto> drmSessionManager, boolean z8, AudioSink audioSink) {
        super(1);
        this.drmSessionManager = drmSessionManager;
        this.playClearSamplesWithoutKeys = z8;
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        this.audioSink = audioSink;
        audioSink.setListener(new AudioSinkListener());
        this.formatHolder = new FormatHolder();
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.decoderReinitializationState = 0;
        this.audioTrackNeedsConfigure = true;
    }
}
