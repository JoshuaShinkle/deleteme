package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.nio.ByteBuffer;

@TargetApi(16)
/* loaded from: classes.dex */
public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsAutoFrcWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long joiningDeadlineMs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Format[] streamFormats;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i9, int i10, int i11) {
            this.width = i9;
            this.height = i10;
            this.inputSize = i11;
        }
    }

    @TargetApi(23)
    public final class OnFrameRenderedListenerV23 implements MediaCodec.OnFrameRenderedListener {
        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(MediaCodec mediaCodec, long j9, long j10) {
            MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
            if (this != mediaCodecVideoRenderer.tunnelingOnFrameRenderedListener) {
                return;
            }
            mediaCodecVideoRenderer.maybeNotifyRenderedFirstFrame();
        }

        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector) {
        this(context, mediaCodecSelector, 0L);
    }

    private static boolean areAdaptationCompatible(boolean z8, Format format, Format format2) {
        return format.sampleMimeType.equals(format2.sampleMimeType) && getRotationDegrees(format) == getRotationDegrees(format2) && (z8 || (format.width == format2.width && format.height == format2.height));
    }

    private void clearRenderedFirstFrame() {
        MediaCodec codec;
        this.renderedFirstFrame = false;
        if (Util.SDK_INT < 23 || !this.tunneling || (codec = getCodec()) == null) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private static boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        String str2 = Util.DEVICE;
        if (((!"deb".equals(str2) && !"flo".equals(str2) && !"mido".equals(str2) && !"santoni".equals(str2)) || !"OMX.qcom.video.decoder.avc".equals(str)) && ((!"tcl_eu".equals(str2) && !"SVP-DTV15".equals(str2) && !"BRAVIA_ATV2".equals(str2) && !str2.startsWith("panell_") && !"F3311".equals(str2) && !"M5c".equals(str2) && !"A7010a48".equals(str2)) || !"OMX.MTK.VIDEO.DECODER.AVC".equals(str))) {
            String str3 = Util.MODEL;
            if ((!"ALE-L21".equals(str3) && !"CAM-L21".equals(str3)) || !"OMX.k3.video.decoder.avc".equals(str)) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i9) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i9);
    }

    private static boolean deviceNeedsAutoFrcWorkaround() {
        return Util.SDK_INT <= 22 && "foster".equals(Util.DEVICE) && "NVIDIA".equals(Util.MANUFACTURER);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int i9 = format.height;
        int i10 = format.width;
        boolean z8 = i9 > i10;
        int i11 = z8 ? i9 : i10;
        if (z8) {
            i9 = i10;
        }
        float f9 = i9 / i11;
        for (int i12 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i13 = (int) (i12 * f9);
            if (i12 <= i11 || i13 <= i9) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i14 = z8 ? i13 : i12;
                if (!z8) {
                    i12 = i13;
                }
                Point pointAlignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i14, i12);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(pointAlignVideoSizeV21.x, pointAlignVideoSizeV21.y, format.frameRate)) {
                    return pointAlignVideoSizeV21;
                }
            } else {
                int iCeilDivide = Util.ceilDivide(i12, 16) * 16;
                int iCeilDivide2 = Util.ceilDivide(i13, 16) * 16;
                if (iCeilDivide * iCeilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i15 = z8 ? iCeilDivide2 : iCeilDivide;
                    if (!z8) {
                        iCeilDivide = iCeilDivide2;
                    }
                    return new Point(i15, iCeilDivide);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(Format format) {
        if (format.maxInputSize == -1) {
            return getMaxInputSize(format.sampleMimeType, format.width, format.height);
        }
        int size = format.initializationData.size();
        int length = 0;
        for (int i9 = 0; i9 < size; i9++) {
            length += format.initializationData.get(i9).length;
        }
        return format.maxInputSize + length;
    }

    private static float getPixelWidthHeightRatio(Format format) {
        float f9 = format.pixelWidthHeightRatio;
        if (f9 == -1.0f) {
            return 1.0f;
        }
        return f9;
    }

    private static int getRotationDegrees(Format format) {
        int i9 = format.rotationDegrees;
        if (i9 == -1) {
            return 0;
        }
        return i9;
    }

    private static boolean isBufferLate(long j9) {
        return j9 < -30000;
    }

    private static boolean isBufferVeryLate(long j9) {
        return j9 < -500000;
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
        }
    }

    private void maybeNotifyVideoSizeChanged() {
        int i9 = this.currentWidth;
        if (i9 == -1 && this.currentHeight == -1) {
            return;
        }
        if (this.reportedWidth == i9 && this.reportedHeight == this.currentHeight && this.reportedUnappliedRotationDegrees == this.currentUnappliedRotationDegrees && this.reportedPixelWidthHeightRatio == this.currentPixelWidthHeightRatio) {
            return;
        }
        this.eventDispatcher.videoSizeChanged(i9, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
        this.reportedWidth = this.currentWidth;
        this.reportedHeight = this.currentHeight;
        this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
        this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        int i9 = this.reportedWidth;
        if (i9 == -1 && this.reportedHeight == -1) {
            return;
        }
        this.eventDispatcher.videoSizeChanged(i9, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C3322C.TIME_UNSET;
    }

    @TargetApi(23)
    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface) {
        mediaCodec.setOutputSurface(surface);
    }

    private void setSurface(Surface surface) throws ExoPlaybackException {
        if (surface == null) {
            Surface surface2 = this.dummySurface;
            if (surface2 != null) {
                surface = surface2;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    surface = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    this.dummySurface = surface;
                }
            }
        }
        if (this.surface == surface) {
            if (surface == null || surface == this.dummySurface) {
                return;
            }
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
            return;
        }
        this.surface = surface;
        int state = getState();
        if (state == 1 || state == 2) {
            MediaCodec codec = getCodec();
            if (Util.SDK_INT < 23 || codec == null || surface == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                releaseCodec();
                maybeInitCodec();
            } else {
                setOutputSurfaceV23(codec, surface);
            }
        }
        if (surface == null || surface == this.dummySurface) {
            clearReportedVideoSize();
            clearRenderedFirstFrame();
            return;
        }
        maybeRenotifyVideoSizeChanged();
        clearRenderedFirstFrame();
        if (state == 2) {
            setJoiningDeadlineMs();
        }
    }

    private static void setVideoScalingMode(MediaCodec mediaCodec, int i9) {
        mediaCodec.setVideoScalingMode(i9);
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z8, Format format, Format format2) {
        if (areAdaptationCompatible(z8, format, format2)) {
            int i9 = format2.width;
            CodecMaxValues codecMaxValues = this.codecMaxValues;
            if (i9 <= codecMaxValues.width && format2.height <= codecMaxValues.height && getMaxInputSize(format2) <= this.codecMaxValues.inputSize) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) {
        CodecMaxValues codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, this.streamFormats);
        this.codecMaxValues = codecMaxValues;
        MediaFormat mediaFormat = getMediaFormat(format, codecMaxValues, this.deviceNeedsAutoFrcWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT < 23 || !this.tunneling) {
            return;
        }
        this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
    }

    public void dropOutputBuffer(MediaCodec mediaCodec, int i9, long j9) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i9, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void flushCodec() throws ExoPlaybackException {
        super.flushCodec();
        this.buffersInCodecCount = 0;
    }

    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int iMax = format.width;
        int iMax2 = format.height;
        int maxInputSize = getMaxInputSize(format);
        if (formatArr.length == 1) {
            return new CodecMaxValues(iMax, iMax2, maxInputSize);
        }
        boolean z8 = false;
        for (Format format2 : formatArr) {
            if (areAdaptationCompatible(mediaCodecInfo.adaptive, format, format2)) {
                int i9 = format2.width;
                z8 |= i9 == -1 || format2.height == -1;
                iMax = Math.max(iMax, i9);
                iMax2 = Math.max(iMax2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(format2));
            }
        }
        if (z8) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + iMax + "x" + iMax2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                iMax = Math.max(iMax, codecMaxSize.x);
                iMax2 = Math.max(iMax2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(format.sampleMimeType, iMax, iMax2));
                Log.w(TAG, "Codec max resolution adjusted to: " + iMax + "x" + iMax2);
            }
        }
        return new CodecMaxValues(iMax, iMax2, maxInputSize);
    }

    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues, boolean z8, int i9) {
        MediaFormat mediaFormatForPlayback = getMediaFormatForPlayback(format);
        mediaFormatForPlayback.setInteger("max-width", codecMaxValues.width);
        mediaFormatForPlayback.setInteger("max-height", codecMaxValues.height);
        int i10 = codecMaxValues.inputSize;
        if (i10 != -1) {
            mediaFormatForPlayback.setInteger("max-input-size", i10);
        }
        if (z8) {
            mediaFormatForPlayback.setInteger("auto-frc", 0);
        }
        if (i9 != 0) {
            configureTunnelingV21(mediaFormatForPlayback, i9);
        }
        return mediaFormatForPlayback;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i9, Object obj) throws ExoPlaybackException {
        if (i9 == 1) {
            setSurface((Surface) obj);
            return;
        }
        if (i9 != 4) {
            super.handleMessage(i9, obj);
            return;
        }
        this.scalingMode = ((Integer) obj).intValue();
        MediaCodec codec = getCodec();
        if (codec != null) {
            setVideoScalingMode(codec, this.scalingMode);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        Surface surface;
        if (super.isReady() && (this.renderedFirstFrame || (((surface = this.dummySurface) != null && this.surface == surface) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = C3322C.TIME_UNSET;
            return true;
        }
        if (this.joiningDeadlineMs == C3322C.TIME_UNSET) {
            return false;
        }
        if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
            return true;
        }
        this.joiningDeadlineMs = C3322C.TIME_UNSET;
        return false;
    }

    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i9, long j9, long j10) throws ExoPlaybackException {
        int iSkipSource = skipSource(j10);
        if (iSkipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.buffersInCodecCount + iSkipSource);
        flushCodec();
        return true;
    }

    public void maybeNotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            return;
        }
        this.renderedFirstFrame = true;
        this.eventDispatcher.renderedFirstFrame(this.surface);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, long j9, long j10) {
        this.eventDispatcher.decoderInitialized(str, j9, j10);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.outputStreamOffsetUs = C3322C.TIME_UNSET;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
        try {
            super.onDisabled();
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z8) {
        super.onEnabled(z8);
        int i9 = getConfiguration().tunnelingAudioSessionId;
        this.tunnelingAudioSessionId = i9;
        this.tunneling = i9 != 0;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = getPixelWidthHeightRatio(format);
        this.pendingRotationDegrees = getRotationDegrees(format);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        boolean z8 = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
        this.currentWidth = z8 ? (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1 : mediaFormat.getInteger("width");
        int integer = z8 ? (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1 : mediaFormat.getInteger("height");
        this.currentHeight = integer;
        float f9 = this.pendingPixelWidthHeightRatio;
        this.currentPixelWidthHeightRatio = f9;
        if (Util.SDK_INT >= 21) {
            int i9 = this.pendingRotationDegrees;
            if (i9 == 90 || i9 == 270) {
                int i10 = this.currentWidth;
                this.currentWidth = integer;
                this.currentHeight = i10;
                this.currentPixelWidthHeightRatio = 1.0f / f9;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        setVideoScalingMode(mediaCodec, this.scalingMode);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j9, boolean z8) throws ExoPlaybackException {
        super.onPositionReset(j9, z8);
        clearRenderedFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
        int i9 = this.pendingOutputStreamOffsetCount;
        if (i9 != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i9 - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z8) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C3322C.TIME_UNSET;
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onProcessedOutputBuffer(long j9) {
        this.buffersInCodecCount--;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        if (Util.SDK_INT >= 23 || !this.tunneling) {
            return;
        }
        maybeNotifyRenderedFirstFrame();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.joiningDeadlineMs = C3322C.TIME_UNSET;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j9) {
        this.streamFormats = formatArr;
        if (this.outputStreamOffsetUs == C3322C.TIME_UNSET) {
            this.outputStreamOffsetUs = j9;
        } else {
            int i9 = this.pendingOutputStreamOffsetCount;
            if (i9 == this.pendingOutputStreamOffsetsUs.length) {
                Log.w(TAG, "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
            } else {
                this.pendingOutputStreamOffsetCount = i9 + 1;
            }
            this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = j9;
        }
        super.onStreamChanged(formatArr, j9);
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j9, long j10, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i9, int i10, long j11, boolean z8) throws InterruptedException {
        long j12;
        long j13;
        while (true) {
            int i11 = this.pendingOutputStreamOffsetCount;
            if (i11 == 0) {
                break;
            }
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            long j14 = jArr[0];
            if (j11 < j14) {
                break;
            }
            this.outputStreamOffsetUs = j14;
            int i12 = i11 - 1;
            this.pendingOutputStreamOffsetCount = i12;
            System.arraycopy(jArr, 1, jArr, 0, i12);
        }
        long j15 = j11 - this.outputStreamOffsetUs;
        if (z8) {
            skipOutputBuffer(mediaCodec, i9, j15);
            return true;
        }
        long j16 = j11 - j9;
        if (this.surface == this.dummySurface) {
            if (!isBufferLate(j16)) {
                return false;
            }
            skipOutputBuffer(mediaCodec, i9, j15);
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        boolean z9 = getState() == 2;
        if (!this.renderedFirstFrame || (z9 && shouldForceRenderOutputBuffer(j16, jElapsedRealtime - this.lastRenderTimeUs))) {
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec, i9, j15, System.nanoTime());
                return true;
            }
            renderOutputBuffer(mediaCodec, i9, j15);
            return true;
        }
        if (!z9) {
            return false;
        }
        long j17 = j16 - (jElapsedRealtime - j10);
        long jNanoTime = System.nanoTime();
        long jAdjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j11, (j17 * 1000) + jNanoTime);
        long j18 = (jAdjustReleaseTime - jNanoTime) / 1000;
        if (shouldDropBuffersToKeyframe(j18, j10)) {
            j12 = jAdjustReleaseTime;
            j13 = j18;
            if (maybeDropBuffersToKeyframe(mediaCodec, i9, j15, j9)) {
                return false;
            }
        } else {
            j12 = jAdjustReleaseTime;
            j13 = j18;
        }
        if (shouldDropOutputBuffer(j13, j10)) {
            dropOutputBuffer(mediaCodec, i9, j15);
            return true;
        }
        if (Util.SDK_INT >= 21) {
            if (j13 >= 50000) {
                return false;
            }
            renderOutputBufferV21(mediaCodec, i9, j15, j12);
            return true;
        }
        if (j13 >= SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS) {
            return false;
        }
        if (j13 > 11000) {
            try {
                Thread.sleep((j13 - 10000) / 1000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        renderOutputBuffer(mediaCodec, i9, j15);
        return true;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void releaseCodec() {
        try {
            super.releaseCodec();
            this.buffersInCodecCount = 0;
            Surface surface = this.dummySurface;
            if (surface != null) {
                if (this.surface == surface) {
                    this.surface = null;
                }
                surface.release();
                this.dummySurface = null;
            }
        } catch (Throwable th) {
            this.buffersInCodecCount = 0;
            if (this.dummySurface != null) {
                Surface surface2 = this.surface;
                Surface surface3 = this.dummySurface;
                if (surface2 == surface3) {
                    this.surface = null;
                }
                surface3.release();
                this.dummySurface = null;
            }
            throw th;
        }
    }

    public void renderOutputBuffer(MediaCodec mediaCodec, int i9, long j9) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i9, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    @TargetApi(21)
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i9, long j9, long j10) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i9, j10);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    public boolean shouldDropBuffersToKeyframe(long j9, long j10) {
        return isBufferVeryLate(j9);
    }

    public boolean shouldDropOutputBuffer(long j9, long j10) {
        return isBufferLate(j9);
    }

    public boolean shouldForceRenderOutputBuffer(long j9, long j10) {
        return isBufferLate(j9) && j10 > 100000;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    public void skipOutputBuffer(MediaCodec mediaCodec, int i9, long j9) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i9, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) {
        boolean z8;
        int i9;
        int i10;
        String str = format.sampleMimeType;
        if (!MimeTypes.isVideo(str)) {
            return 0;
        }
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            z8 = false;
            for (int i11 = 0; i11 < drmInitData.schemeDataCount; i11++) {
                z8 |= drmInitData.get(i11).requiresSecureDecryption;
            }
        } else {
            z8 = false;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector.getDecoderInfo(str, z8);
        if (decoderInfo == null) {
            return (!z8 || mediaCodecSelector.getDecoderInfo(str, false) == null) ? 1 : 2;
        }
        if (!BaseRenderer.supportsFormatDrm(drmSessionManager, drmInitData)) {
            return 2;
        }
        boolean zIsCodecSupported = decoderInfo.isCodecSupported(format.codecs);
        if (zIsCodecSupported && (i9 = format.width) > 0 && (i10 = format.height) > 0) {
            if (Util.SDK_INT >= 21) {
                zIsCodecSupported = decoderInfo.isVideoSizeAndRateSupportedV21(i9, i10, format.frameRate);
            } else {
                boolean z9 = i9 * i10 <= MediaCodecUtil.maxH264DecodableFrameSize();
                if (!z9) {
                    Log.d(TAG, "FalseCheck [legacyFrameSize, " + format.width + "x" + format.height + "] [" + Util.DEVICE_DEBUG_INFO + "]");
                }
                zIsCodecSupported = z9;
            }
        }
        return (zIsCodecSupported ? 4 : 3) | (decoderInfo.adaptive ? 16 : 8) | (decoderInfo.tunneling ? 32 : 0);
    }

    public void updateDroppedBufferCounters(int i9) {
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedBufferCount += i9;
        this.droppedFrames += i9;
        int i10 = this.consecutiveDroppedFrameCount + i9;
        this.consecutiveDroppedFrameCount = i10;
        decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(i10, decoderCounters.maxConsecutiveDroppedBufferCount);
        if (this.droppedFrames >= this.maxDroppedFramesToNotify) {
            maybeNotifyDroppedFrames();
        }
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j9) {
        this(context, mediaCodecSelector, j9, null, null, -1);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j9, Handler handler, VideoRendererEventListener videoRendererEventListener, int i9) {
        this(context, mediaCodecSelector, j9, null, false, handler, videoRendererEventListener, i9);
    }

    public MediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j9, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8, Handler handler, VideoRendererEventListener videoRendererEventListener, int i9) {
        super(2, mediaCodecSelector, drmSessionManager, z8);
        this.allowedJoiningTimeMs = j9;
        this.maxDroppedFramesToNotify = i9;
        this.context = context.getApplicationContext();
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(context);
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsAutoFrcWorkaround = deviceNeedsAutoFrcWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.outputStreamOffsetUs = C3322C.TIME_UNSET;
        this.joiningDeadlineMs = C3322C.TIME_UNSET;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int getMaxInputSize(String str, int i9, int i10) {
        int i11;
        int iCeilDivide;
        if (i9 == -1 || i10 == -1) {
            return -1;
        }
        str.hashCode();
        i11 = 4;
        switch (str) {
            case "video/3gpp":
            case "video/mp4v-es":
            case "video/x-vnd.on2.vp8":
                iCeilDivide = i9 * i10;
                i11 = 2;
                break;
            case "video/hevc":
            case "video/x-vnd.on2.vp9":
                iCeilDivide = i9 * i10;
                break;
            case "video/avc":
                if (!"BRAVIA 4K 2015".equals(Util.MODEL)) {
                    iCeilDivide = Util.ceilDivide(i9, 16) * Util.ceilDivide(i10, 16) * 16 * 16;
                    i11 = 2;
                    break;
                }
                break;
        }
        return -1;
    }
}
