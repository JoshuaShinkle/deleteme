package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.messaging.Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(16)
/* loaded from: classes.dex */
public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "MediaCodecRenderer";
    private final DecoderInputBuffer buffer;
    private MediaCodec codec;
    private int codecAdaptationWorkaroundMode;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private DrmSession<FrameworkMediaCrypto> drmSession;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final MediaCodecSelector mediaCodecSelector;
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private DrmSession<FrameworkMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean shouldSkipOutputBuffer;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    public MediaCodecRenderer(int i9, MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z8) {
        super(i9);
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector);
        this.drmSessionManager = drmSessionManager;
        this.playClearSamplesWithoutKeys = z8;
        this.buffer = new DecoderInputBuffer(0);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
    }

    private int codecAdaptationWorkaroundMode(String str) {
        int i9 = Util.SDK_INT;
        if (i9 <= 25 && "OMX.Exynos.avc.dec.secure".equals(str)) {
            String str2 = Util.MODEL;
            if (str2.startsWith("SM-T585") || str2.startsWith("SM-A510") || str2.startsWith("SM-A520") || str2.startsWith("SM-J700")) {
                return 2;
            }
        }
        if (i9 >= 24) {
            return 0;
        }
        if (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) {
            return 0;
        }
        String str3 = Util.DEVICE;
        return ("flounder".equals(str3) || "flounder_lte".equals(str3) || "grouper".equals(str3) || "tilapia".equals(str3)) ? 1 : 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format) {
        return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        int i9 = Util.SDK_INT;
        return (i9 <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (i9 <= 19 && "hb2000".equals(Util.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(String str) {
        return Util.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        int i9 = Util.SDK_INT;
        return i9 < 18 || (i9 == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (i9 == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format) {
        return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
    }

    @TargetApi(23)
    private static void configureMediaFormatForPlaybackV23(MediaFormat mediaFormat) {
        mediaFormat.setInteger(Constants.FirelogAnalytics.PARAM_PRIORITY, 0);
    }

    private boolean drainOutputBuffer(long j9, long j10) throws ExoPlaybackException {
        boolean zProcessOutputBuffer;
        int iDequeueOutputBuffer;
        if (!hasOutputBuffer()) {
            if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
                try {
                    iDequeueOutputBuffer = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
                } catch (IllegalStateException unused) {
                    processEndOfStream();
                    if (this.outputStreamEnded) {
                        releaseCodec();
                    }
                    return false;
                }
            } else {
                iDequeueOutputBuffer = this.codec.dequeueOutputBuffer(this.outputBufferInfo, getDequeueOutputBufferTimeoutUs());
            }
            if (iDequeueOutputBuffer < 0) {
                if (iDequeueOutputBuffer == -2) {
                    processOutputFormat();
                    return true;
                }
                if (iDequeueOutputBuffer == -3) {
                    processOutputBuffersChanged();
                    return true;
                }
                if (this.codecNeedsEosPropagationWorkaround && (this.inputStreamEnded || this.codecReinitializationState == 2)) {
                    processEndOfStream();
                }
                return false;
            }
            if (this.shouldSkipAdaptationWorkaroundOutputBuffer) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                this.codec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                return true;
            }
            if ((this.outputBufferInfo.flags & 4) != 0) {
                processEndOfStream();
                return false;
            }
            this.outputIndex = iDequeueOutputBuffer;
            ByteBuffer outputBuffer = getOutputBuffer(iDequeueOutputBuffer);
            this.outputBuffer = outputBuffer;
            if (outputBuffer != null) {
                outputBuffer.position(this.outputBufferInfo.offset);
                ByteBuffer byteBuffer = this.outputBuffer;
                MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
                byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            }
            this.shouldSkipOutputBuffer = shouldSkipOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        }
        if (this.codecNeedsEosOutputExceptionWorkaround && this.codecReceivedEos) {
            try {
                MediaCodec mediaCodec = this.codec;
                ByteBuffer byteBuffer2 = this.outputBuffer;
                int i9 = this.outputIndex;
                MediaCodec.BufferInfo bufferInfo2 = this.outputBufferInfo;
                zProcessOutputBuffer = processOutputBuffer(j9, j10, mediaCodec, byteBuffer2, i9, bufferInfo2.flags, bufferInfo2.presentationTimeUs, this.shouldSkipOutputBuffer);
            } catch (IllegalStateException unused2) {
                processEndOfStream();
                if (this.outputStreamEnded) {
                    releaseCodec();
                }
                return false;
            }
        } else {
            MediaCodec mediaCodec2 = this.codec;
            ByteBuffer byteBuffer3 = this.outputBuffer;
            int i10 = this.outputIndex;
            MediaCodec.BufferInfo bufferInfo3 = this.outputBufferInfo;
            zProcessOutputBuffer = processOutputBuffer(j9, j10, mediaCodec2, byteBuffer3, i10, bufferInfo3.flags, bufferInfo3.presentationTimeUs, this.shouldSkipOutputBuffer);
        }
        if (!zProcessOutputBuffer) {
            return false;
        }
        onProcessedOutputBuffer(this.outputBufferInfo.presentationTimeUs);
        resetOutputBuffer();
        return true;
    }

    private boolean feedInputBuffer() throws ExoPlaybackException, MediaCodec.CryptoException {
        int iPosition;
        int source;
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec == null || this.codecReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputIndex < 0) {
            int iDequeueInputBuffer = mediaCodec.dequeueInputBuffer(0L);
            this.inputIndex = iDequeueInputBuffer;
            if (iDequeueInputBuffer < 0) {
                return false;
            }
            this.buffer.data = getInputBuffer(iDequeueInputBuffer);
            this.buffer.clear();
        }
        if (this.codecReinitializationState == 1) {
            if (!this.codecNeedsEosPropagationWorkaround) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                resetInputBuffer();
            }
            this.codecReinitializationState = 2;
            return false;
        }
        if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            ByteBuffer byteBuffer = this.buffer.data;
            byte[] bArr = ADAPTATION_WORKAROUND_BUFFER;
            byteBuffer.put(bArr);
            this.codec.queueInputBuffer(this.inputIndex, 0, bArr.length, 0L, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        }
        if (this.waitingForKeys) {
            source = -4;
            iPosition = 0;
        } else {
            if (this.codecReconfigurationState == 1) {
                for (int i9 = 0; i9 < this.format.initializationData.size(); i9++) {
                    this.buffer.data.put(this.format.initializationData.get(i9));
                }
                this.codecReconfigurationState = 2;
            }
            iPosition = this.buffer.data.position();
            source = readSource(this.formatHolder, this.buffer, false);
        }
        if (source == -3) {
            return false;
        }
        if (source == -5) {
            if (this.codecReconfigurationState == 2) {
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            onInputFormatChanged(this.formatHolder.format);
            return true;
        }
        if (this.buffer.isEndOfStream()) {
            if (this.codecReconfigurationState == 2) {
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            this.inputStreamEnded = true;
            if (!this.codecReceivedBuffers) {
                processEndOfStream();
                return false;
            }
            try {
                if (!this.codecNeedsEosPropagationWorkaround) {
                    this.codecReceivedEos = true;
                    this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0L, 4);
                    resetInputBuffer();
                }
                return false;
            } catch (MediaCodec.CryptoException e9) {
                throw ExoPlaybackException.createForRenderer(e9, getIndex());
            }
        }
        if (this.waitingForFirstSyncFrame && !this.buffer.isKeyFrame()) {
            this.buffer.clear();
            if (this.codecReconfigurationState == 2) {
                this.codecReconfigurationState = 1;
            }
            return true;
        }
        this.waitingForFirstSyncFrame = false;
        boolean zIsEncrypted = this.buffer.isEncrypted();
        boolean zShouldWaitForKeys = shouldWaitForKeys(zIsEncrypted);
        this.waitingForKeys = zShouldWaitForKeys;
        if (zShouldWaitForKeys) {
            return false;
        }
        if (this.codecNeedsDiscardToSpsWorkaround && !zIsEncrypted) {
            NalUnitUtil.discardToSps(this.buffer.data);
            if (this.buffer.data.position() == 0) {
                return true;
            }
            this.codecNeedsDiscardToSpsWorkaround = false;
        }
        try {
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            long j9 = decoderInputBuffer.timeUs;
            if (decoderInputBuffer.isDecodeOnly()) {
                this.decodeOnlyPresentationTimestamps.add(Long.valueOf(j9));
            }
            this.buffer.flip();
            onQueueInputBuffer(this.buffer);
            if (zIsEncrypted) {
                this.codec.queueSecureInputBuffer(this.inputIndex, 0, getFrameworkCryptoInfo(this.buffer, iPosition), j9, 0);
            } else {
                this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), j9, 0);
            }
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            this.codecReconfigurationState = 0;
            this.decoderCounters.inputBufferCount++;
            return true;
        } catch (MediaCodec.CryptoException e10) {
            throw ExoPlaybackException.createForRenderer(e10, getIndex());
        }
    }

    private void getCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = this.codec.getInputBuffers();
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private static MediaCodec.CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer decoderInputBuffer, int i9) {
        MediaCodec.CryptoInfo frameworkCryptoInfoV16 = decoderInputBuffer.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i9 == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i9;
        return frameworkCryptoInfoV16;
    }

    private ByteBuffer getInputBuffer(int i9) {
        return Util.SDK_INT >= 21 ? this.codec.getInputBuffer(i9) : this.inputBuffers[i9];
    }

    private ByteBuffer getOutputBuffer(int i9) {
        return Util.SDK_INT >= 21 ? this.codec.getOutputBuffer(i9) : this.outputBuffers[i9];
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.outputStreamEnded = true;
            renderToEndOfStream();
        }
    }

    private void processOutputBuffersChanged() {
        if (Util.SDK_INT < 21) {
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void processOutputFormat() {
        MediaFormat outputFormat = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat.setInteger("channel-count", 1);
        }
        onOutputFormatChanged(this.codec, outputFormat);
    }

    private void resetCodecBuffers() {
        if (Util.SDK_INT < 21) {
            this.inputBuffers = null;
            this.outputBuffers = null;
        }
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private boolean shouldSkipOutputBuffer(long j9) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.decodeOnlyPresentationTimestamps.get(i9).longValue() == j9) {
                this.decodeOnlyPresentationTimestamps.remove(i9);
                return true;
            }
        }
        return false;
    }

    private boolean shouldWaitForKeys(boolean z8) throws ExoPlaybackException {
        DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
        if (drmSession == null || (!z8 && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = drmSession.getState();
        if (state != 1) {
            return state != 4;
        }
        throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
    }

    private void throwDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        throw ExoPlaybackException.createForRenderer(decoderInitializationException, getIndex());
    }

    public boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z8, Format format, Format format2) {
        return false;
    }

    public abstract void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto);

    public void flushCodec() throws ExoPlaybackException {
        this.codecHotswapDeadlineMs = C3322C.TIME_UNSET;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if (this.codecNeedsFlushWorkaround || ((this.codecNeedsEosFlushWorkaround && this.codecReceivedEos) || this.codecReinitializationState != 0)) {
            releaseCodec();
            maybeInitCodec();
        } else {
            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        if (!this.codecReconfigured || this.format == null) {
            return;
        }
        this.codecReconfigurationState = 1;
    }

    public final MediaCodec getCodec() {
        return this.codec;
    }

    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, Format format, boolean z8) {
        return mediaCodecSelector.getDecoderInfo(format.sampleMimeType, z8);
    }

    public long getDequeueOutputBufferTimeoutUs() {
        return 0L;
    }

    public final MediaFormat getMediaFormatForPlayback(Format format) {
        MediaFormat frameworkMediaFormatV16 = format.getFrameworkMediaFormatV16();
        if (Util.SDK_INT >= 23) {
            configureMediaFormatForPlaybackV23(frameworkMediaFormatV16);
        }
        return frameworkMediaFormatV16;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return (this.format == null || this.waitingForKeys || (!isSourceReady() && !hasOutputBuffer() && (this.codecHotswapDeadlineMs == C3322C.TIME_UNSET || SystemClock.elapsedRealtime() >= this.codecHotswapDeadlineMs))) ? false : true;
    }

    public final void maybeInitCodec() throws ExoPlaybackException {
        Format format;
        boolean zRequiresSecureDecoderComponent;
        MediaCrypto wrappedMediaCrypto;
        if (this.codec != null || (format = this.format) == null) {
            return;
        }
        DrmSession<FrameworkMediaCrypto> drmSession = this.pendingDrmSession;
        this.drmSession = drmSession;
        String str = format.sampleMimeType;
        if (drmSession == null) {
            zRequiresSecureDecoderComponent = false;
            wrappedMediaCrypto = null;
        } else {
            FrameworkMediaCrypto frameworkMediaCrypto = (FrameworkMediaCrypto) drmSession.getMediaCrypto();
            if (frameworkMediaCrypto == null) {
                if (this.drmSession.getError() == null) {
                    return;
                }
                zRequiresSecureDecoderComponent = false;
                wrappedMediaCrypto = null;
            } else {
                wrappedMediaCrypto = frameworkMediaCrypto.getWrappedMediaCrypto();
                zRequiresSecureDecoderComponent = frameworkMediaCrypto.requiresSecureDecoderComponent(str);
            }
        }
        if (this.codecInfo == null) {
            try {
                MediaCodecInfo decoderInfo = getDecoderInfo(this.mediaCodecSelector, this.format, zRequiresSecureDecoderComponent);
                this.codecInfo = decoderInfo;
                if (decoderInfo == null && zRequiresSecureDecoderComponent) {
                    MediaCodecInfo decoderInfo2 = getDecoderInfo(this.mediaCodecSelector, this.format, false);
                    this.codecInfo = decoderInfo2;
                    if (decoderInfo2 != null) {
                        Log.w(TAG, "Drm session requires secure decoder for " + str + ", but no secure decoder available. Trying to proceed with " + this.codecInfo.name + ".");
                    }
                }
            } catch (MediaCodecUtil.DecoderQueryException e9) {
                throwDecoderInitError(new DecoderInitializationException(this.format, e9, zRequiresSecureDecoderComponent, -49998));
            }
            if (this.codecInfo == null) {
                throwDecoderInitError(new DecoderInitializationException(this.format, (Throwable) null, zRequiresSecureDecoderComponent, -49999));
            }
        }
        if (shouldInitCodec(this.codecInfo)) {
            String str2 = this.codecInfo.name;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(str2);
            this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(str2, this.format);
            this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(str2);
            this.codecNeedsEosPropagationWorkaround = codecNeedsEosPropagationWorkaround(str2);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(str2);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(str2);
            this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(str2, this.format);
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                TraceUtil.beginSection("createCodec:" + str2);
                this.codec = MediaCodec.createByCodecName(str2);
                TraceUtil.endSection();
                TraceUtil.beginSection("configureCodec");
                configureCodec(this.codecInfo, this.codec, this.format, wrappedMediaCrypto);
                TraceUtil.endSection();
                TraceUtil.beginSection("startCodec");
                this.codec.start();
                TraceUtil.endSection();
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                onCodecInitialized(str2, jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
                getCodecBuffers();
            } catch (Exception e10) {
                throwDecoderInitError(new DecoderInitializationException(this.format, e10, zRequiresSecureDecoderComponent, str2));
            }
            this.codecHotswapDeadlineMs = getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : C3322C.TIME_UNSET;
            resetInputBuffer();
            resetOutputBuffer();
            this.waitingForFirstSyncFrame = true;
            this.decoderCounters.decoderInitCount++;
        }
    }

    public void onCodecInitialized(String str, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.format = null;
        try {
            releaseCodec();
            try {
                DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
                if (drmSession != null) {
                    this.drmSessionManager.releaseSession(drmSession);
                }
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.pendingDrmSession;
                    if (drmSession2 != null && drmSession2 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession2);
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.pendingDrmSession;
                    if (drmSession3 != null && drmSession3 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession3);
                    }
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th2) {
            try {
                if (this.drmSession != null) {
                    this.drmSessionManager.releaseSession(this.drmSession);
                }
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession4 = this.pendingDrmSession;
                    if (drmSession4 != null && drmSession4 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession4);
                    }
                    throw th2;
                } finally {
                }
            } catch (Throwable th3) {
                try {
                    DrmSession<FrameworkMediaCrypto> drmSession5 = this.pendingDrmSession;
                    if (drmSession5 != null && drmSession5 != this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession5);
                    }
                    throw th3;
                } finally {
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z8) {
        this.decoderCounters = new DecoderCounters();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        MediaCodec mediaCodec;
        Format format2 = this.format;
        this.format = format;
        boolean z8 = true;
        if (!Util.areEqual(format.drmInitData, format2 == null ? null : format2.drmInitData)) {
            if (this.format.drmInitData != null) {
                DrmSessionManager<FrameworkMediaCrypto> drmSessionManager = this.drmSessionManager;
                if (drmSessionManager == null) {
                    throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
                DrmSession drmSessionAcquireSession = drmSessionManager.acquireSession(Looper.myLooper(), this.format.drmInitData);
                this.pendingDrmSession = drmSessionAcquireSession;
                if (drmSessionAcquireSession == this.drmSession) {
                    this.drmSessionManager.releaseSession(drmSessionAcquireSession);
                }
            } else {
                this.pendingDrmSession = null;
            }
        }
        if (this.pendingDrmSession != this.drmSession || (mediaCodec = this.codec) == null || !canReconfigureCodec(mediaCodec, this.codecInfo.adaptive, format2, this.format)) {
            if (this.codecReceivedBuffers) {
                this.codecReinitializationState = 1;
                return;
            } else {
                releaseCodec();
                maybeInitCodec();
                return;
            }
        }
        this.codecReconfigured = true;
        this.codecReconfigurationState = 1;
        int i9 = this.codecAdaptationWorkaroundMode;
        if (i9 != 2) {
            if (i9 == 1) {
                Format format3 = this.format;
                if (format3.width != format2.width || format3.height != format2.height) {
                    z8 = false;
                }
            }
        }
        this.codecNeedsAdaptationWorkaroundBuffer = z8;
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j9, boolean z8) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.codec != null) {
            flushCodec();
        }
    }

    public void onProcessedOutputBuffer(long j9) {
    }

    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStarted() {
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStopped() {
    }

    public abstract boolean processOutputBuffer(long j9, long j10, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i9, int i10, long j11, boolean z8);

    public void releaseCodec() {
        this.codecHotswapDeadlineMs = C3322C.TIME_UNSET;
        resetInputBuffer();
        resetOutputBuffer();
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        resetCodecBuffers();
        this.codecInfo = null;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec != null) {
            this.decoderCounters.decoderReleaseCount++;
            try {
                mediaCodec.stop();
                try {
                    this.codec.release();
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession = this.drmSession;
                    if (drmSession == null || this.pendingDrmSession == drmSession) {
                        return;
                    }
                    try {
                        this.drmSessionManager.releaseSession(drmSession);
                    } finally {
                    }
                } catch (Throwable th) {
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
                    if (drmSession2 != null && this.pendingDrmSession != drmSession2) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession2);
                        } finally {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    this.codec.release();
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession3 = this.drmSession;
                    if (drmSession3 != null && this.pendingDrmSession != drmSession3) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession3);
                        } finally {
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    this.codec = null;
                    DrmSession<FrameworkMediaCrypto> drmSession4 = this.drmSession;
                    if (drmSession4 != null && this.pendingDrmSession != drmSession4) {
                        try {
                            this.drmSessionManager.releaseSession(drmSession4);
                        } finally {
                        }
                    }
                    throw th3;
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j9, long j10) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            renderToEndOfStream();
            return;
        }
        if (this.format == null) {
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
        maybeInitCodec();
        if (this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            while (drainOutputBuffer(j9, j10)) {
            }
            while (feedInputBuffer()) {
            }
            TraceUtil.endSection();
        } else {
            this.decoderCounters.skippedInputBufferCount += skipSource(j9);
            this.flagsOnlyBuffer.clear();
            int source2 = readSource(this.formatHolder, this.flagsOnlyBuffer, false);
            if (source2 == -5) {
                onInputFormatChanged(this.formatHolder.format);
            } else if (source2 == -4) {
                Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                this.inputStreamEnded = true;
                processEndOfStream();
            }
        }
        this.decoderCounters.ensureUpdated();
    }

    public void renderToEndOfStream() {
    }

    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, this.drmSessionManager, format);
        } catch (MediaCodecUtil.DecoderQueryException e9) {
            throw ExoPlaybackException.createForRenderer(e9, getIndex());
        }
    }

    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format);

    @Override // com.google.android.exoplayer2.BaseRenderer, com.google.android.exoplayer2.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z8, int i9) {
            super("Decoder init failed: [" + i9 + "], " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z8;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i9);
        }

        private static String buildCustomDiagnosticInfo(int i9) {
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + (i9 < 0 ? "neg_" : "") + Math.abs(i9);
        }

        @TargetApi(21)
        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        public DecoderInitializationException(Format format, Throwable th, boolean z8, String str) {
            super("Decoder init failed: " + str + ", " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z8;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }
    }
}
