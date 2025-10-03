package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;

@TargetApi(16)
/* loaded from: classes.dex */
public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    private boolean allowFirstBufferPositionDiscontinuity;
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private int channelCount;
    private boolean codecNeedsDiscardChannelsWorkaround;
    private long currentPositionUs;
    private int encoderDelay;
    private int encoderPadding;
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private boolean passthroughEnabled;
    private MediaFormat passthroughMediaFormat;
    private int pcmEncoding;

    public final class AudioSinkListener implements AudioSink.Listener {
        private AudioSinkListener() {
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onAudioSessionId(int i9) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioSessionId(i9);
            MediaCodecAudioRenderer.this.onAudioSessionId(i9);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            MediaCodecAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.Listener
        public void onUnderrun(int i9, long j9, long j10) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackUnderrun(i9, j9, j10);
            MediaCodecAudioRenderer.this.onAudioTrackUnderrun(i9, j9, j10);
        }
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector) {
        this(mediaCodecSelector, (DrmSessionManager<FrameworkMediaCrypto>) null, true);
    }

    private static boolean codecNeedsDiscardChannelsWorkaround(String str) {
        if (Util.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(Util.MANUFACTURER)) {
            String str2 = Util.DEVICE;
            if (str2.startsWith("zeroflte") || str2.startsWith("herolte") || str2.startsWith("heroqlte")) {
                return true;
            }
        }
        return false;
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

    public boolean allowPassthrough(String str) {
        int encoding = MimeTypes.getEncoding(str);
        return encoding != 0 && this.audioSink.isEncodingSupported(encoding);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) {
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(mediaCodecInfo.name);
        MediaFormat mediaFormatForPlayback = getMediaFormatForPlayback(format);
        if (!this.passthroughEnabled) {
            mediaCodec.configure(mediaFormatForPlayback, (Surface) null, mediaCrypto, 0);
            this.passthroughMediaFormat = null;
        } else {
            this.passthroughMediaFormat = mediaFormatForPlayback;
            mediaFormatForPlayback.setString("mime", MimeTypes.AUDIO_RAW);
            mediaCodec.configure(this.passthroughMediaFormat, (Surface) null, mediaCrypto, 0);
            this.passthroughMediaFormat.setString("mime", format.sampleMimeType);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, Format format, boolean z8) {
        MediaCodecInfo passthroughDecoderInfo;
        if (!allowPassthrough(format.sampleMimeType) || (passthroughDecoderInfo = mediaCodecSelector.getPassthroughDecoderInfo()) == null) {
            this.passthroughEnabled = false;
            return super.getDecoderInfo(mediaCodecSelector, format, z8);
        }
        this.passthroughEnabled = true;
        return passthroughDecoderInfo;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.Renderer
    public MediaClock getMediaClock() {
        return this;
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

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return super.isEnded() && this.audioSink.isEnded();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.audioSink.hasPendingData() || super.isReady();
    }

    public void onAudioSessionId(int i9) {
    }

    public void onAudioTrackPositionDiscontinuity() {
    }

    public void onAudioTrackUnderrun(int i9, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, long j9, long j10) {
        this.eventDispatcher.decoderInitialized(str, j9, j10);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        try {
            this.audioSink.release();
            try {
                super.onDisabled();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.onDisabled();
                throw th;
            } finally {
            }
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z8) {
        super.onEnabled(z8);
        this.eventDispatcher.enabled(this.decoderCounters);
        int i9 = getConfiguration().tunnelingAudioSessionId;
        if (i9 != 0) {
            this.audioSink.enableTunnelingV21(i9);
        } else {
            this.audioSink.disableTunneling();
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pcmEncoding = MimeTypes.AUDIO_RAW.equals(format.sampleMimeType) ? format.pcmEncoding : 2;
        this.channelCount = format.channelCount;
        int i9 = format.encoderDelay;
        if (i9 == -1) {
            i9 = 0;
        }
        this.encoderDelay = i9;
        int i10 = format.encoderPadding;
        this.encoderPadding = i10 != -1 ? i10 : 0;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
        int encoding;
        int[] iArr;
        int i9;
        MediaFormat mediaFormat2 = this.passthroughMediaFormat;
        if (mediaFormat2 != null) {
            encoding = MimeTypes.getEncoding(mediaFormat2.getString("mime"));
            mediaFormat = this.passthroughMediaFormat;
        } else {
            encoding = this.pcmEncoding;
        }
        int i10 = encoding;
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.codecNeedsDiscardChannelsWorkaround && integer == 6 && (i9 = this.channelCount) < 6) {
            iArr = new int[i9];
            for (int i11 = 0; i11 < this.channelCount; i11++) {
                iArr[i11] = i11;
            }
        } else {
            iArr = null;
        }
        try {
            this.audioSink.configure(i10, integer, integer2, 0, iArr, this.encoderDelay, this.encoderPadding);
        } catch (AudioSink.ConfigurationException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j9, boolean z8) throws ExoPlaybackException {
        super.onPositionReset(j9, z8);
        this.audioSink.reset();
        this.currentPositionUs = j9;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (!this.allowFirstBufferPositionDiscontinuity || decoderInputBuffer.isDecodeOnly()) {
            return;
        }
        if (Math.abs(decoderInputBuffer.timeUs - this.currentPositionUs) > 500000) {
            this.currentPositionUs = decoderInputBuffer.timeUs;
        }
        this.allowFirstBufferPositionDiscontinuity = false;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.audioSink.play();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.audioSink.pause();
        updateCurrentPosition();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j9, long j10, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i9, int i10, long j11, boolean z8) throws ExoPlaybackException {
        if (this.passthroughEnabled && (i10 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i9, false);
            return true;
        }
        if (z8) {
            mediaCodec.releaseOutputBuffer(i9, false);
            this.decoderCounters.skippedOutputBufferCount++;
            this.audioSink.handleDiscontinuity();
            return true;
        }
        try {
            if (!this.audioSink.handleBuffer(byteBuffer, j11)) {
                return false;
            }
            mediaCodec.releaseOutputBuffer(i9, false);
            this.decoderCounters.renderedOutputBufferCount++;
            return true;
        } catch (AudioSink.InitializationException | AudioSink.WriteException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void renderToEndOfStream() throws ExoPlaybackException {
        try {
            this.audioSink.playToEndOfStream();
        } catch (AudioSink.WriteException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        return this.audioSink.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) {
        boolean z8;
        int i9;
        int i10;
        String str = format.sampleMimeType;
        boolean z9 = false;
        if (!MimeTypes.isAudio(str)) {
            return 0;
        }
        int i11 = Util.SDK_INT >= 21 ? 32 : 0;
        boolean zSupportsFormatDrm = BaseRenderer.supportsFormatDrm(drmSessionManager, format.drmInitData);
        if (zSupportsFormatDrm && allowPassthrough(str) && mediaCodecSelector.getPassthroughDecoderInfo() != null) {
            return i11 | 8 | 4;
        }
        if ((MimeTypes.AUDIO_RAW.equals(str) && !this.audioSink.isEncodingSupported(format.pcmEncoding)) || !this.audioSink.isEncodingSupported(2)) {
            return 1;
        }
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            z8 = false;
            for (int i12 = 0; i12 < drmInitData.schemeDataCount; i12++) {
                z8 |= drmInitData.get(i12).requiresSecureDecryption;
            }
        } else {
            z8 = false;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector.getDecoderInfo(str, z8);
        if (decoderInfo == null) {
            return (!z8 || mediaCodecSelector.getDecoderInfo(str, false) == null) ? 1 : 2;
        }
        if (!zSupportsFormatDrm) {
            return 2;
        }
        if (Util.SDK_INT < 21 || (((i9 = format.sampleRate) == -1 || decoderInfo.isAudioSampleRateSupportedV21(i9)) && ((i10 = format.channelCount) == -1 || decoderInfo.isAudioChannelCountSupportedV21(i10)))) {
            z9 = true;
        }
        return i11 | 8 | (z9 ? 4 : 3);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8) {
        this(mediaCodecSelector, drmSessionManager, z8, null, null);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener) {
        this(mediaCodecSelector, null, true, handler, audioRendererEventListener);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8, Handler handler, AudioRendererEventListener audioRendererEventListener) {
        this(mediaCodecSelector, drmSessionManager, z8, handler, audioRendererEventListener, null, new AudioProcessor[0]);
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        this(mediaCodecSelector, drmSessionManager, z8, handler, audioRendererEventListener, new DefaultAudioSink(audioCapabilities, audioProcessorArr));
    }

    public MediaCodecAudioRenderer(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink) {
        super(1, mediaCodecSelector, drmSessionManager, z8);
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        this.audioSink = audioSink;
        audioSink.setListener(new AudioSinkListener());
    }
}
