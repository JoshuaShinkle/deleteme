package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase14;

@TargetApi(19)
/* loaded from: classes3.dex */
public class MediaCodecVideoEncoder {
    private static final BanColorProperty[] BAN_COLOR_LIST;
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 25000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    private static MediaCodecVideoEncoderErrorCallback errorCallback;
    private static final MediaCodecProperties exynosH264HwProperties;
    private static final MediaCodecProperties exynosVp8HwProperties;
    private static final MediaCodecProperties exynosVp9HwProperties;
    private static final MediaCodecProperties[] h264HwList;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties intelVp8HwProperties;
    private static final MediaCodecProperties omxH264HwProperties;
    private static final MediaCodecProperties qcomH264HwProperties;
    private static final MediaCodecProperties qcomVp8HwProperties;
    private static final MediaCodecProperties qcomVp9HwProperties;
    private static MediaCodecVideoEncoder runningInstance;
    private static final int[] supportedColorList;
    private static final int[] supportedSurfaceColorList;
    private static final BanColorProperty topazH264FormatYUV420Planar;
    private static final MediaCodecProperties[] vp9HwList;
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    private Surface inputSurface;
    private long lastKeyFrameMs;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private ByteBuffer configData = null;

    public static class BanColorProperty {
        public final String codecPrefix;
        public final int colorFormat;
        public final String mime;

        public BanColorProperty(String str, String str2, int i9) {
            this.codecPrefix = str;
            this.mime = str2;
            this.colorFormat = i9;
        }
    }

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i9, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecName = str;
            this.colorFormat = i9;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    public static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        public MediaCodecProperties(String str, int i9, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecPrefix = str;
            this.minSdk = i9;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i9);
    }

    public static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i9, ByteBuffer byteBuffer, boolean z8, long j9) {
            this.index = i9;
            this.buffer = byteBuffer;
            this.isKeyFrame = z8;
            this.presentationTimestampUs = j9;
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    static {
        BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
        qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, bitrateAdjustmentType);
        exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
        intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, bitrateAdjustmentType);
        MediaCodecProperties mediaCodecProperties = new MediaCodecProperties("OMX.qcom.", 23, bitrateAdjustmentType);
        qcomVp9HwProperties = mediaCodecProperties;
        MediaCodecProperties mediaCodecProperties2 = new MediaCodecProperties("OMX.Exynos.", 23, bitrateAdjustmentType);
        exynosVp9HwProperties = mediaCodecProperties2;
        vp9HwList = new MediaCodecProperties[]{mediaCodecProperties, mediaCodecProperties2};
        MediaCodecProperties mediaCodecProperties3 = new MediaCodecProperties("OMX.qcom.", 19, bitrateAdjustmentType);
        qcomH264HwProperties = mediaCodecProperties3;
        MediaCodecProperties mediaCodecProperties4 = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
        exynosH264HwProperties = mediaCodecProperties4;
        MediaCodecProperties mediaCodecProperties5 = new MediaCodecProperties("OMX.", 19, bitrateAdjustmentType);
        omxH264HwProperties = mediaCodecProperties5;
        h264HwList = new MediaCodecProperties[]{mediaCodecProperties3, mediaCodecProperties4, mediaCodecProperties5};
        H264_HW_EXCEPTION_MODELS = new String[0];
        supportedColorList = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
        supportedSurfaceColorList = new int[]{2130708361};
        BanColorProperty banColorProperty = new BanColorProperty("OMX.IMG.TOPAZ.VIDEO.Encoder", "video/avc", 19);
        topazH264FormatYUV420Planar = banColorProperty;
        BAN_COLOR_LIST = new BanColorProperty[]{banColorProperty};
    }

    private void checkOnMediaCodecThread() {
    }

    public static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void disableH264HwCodec() {
        Logging.m23189w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.m23189w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.m23189w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    private static EncoderProperties findHwEncoder(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr) {
        MediaCodecInfo codecInfoAt;
        String name;
        boolean z8;
        if (str.equals("video/avc")) {
            List listAsList = Arrays.asList(H264_HW_EXCEPTION_MODELS);
            String str2 = Build.MODEL;
            if (listAsList.contains(str2)) {
                Logging.m23189w(TAG, "Model: " + str2 + " has black listed H.264 encoder.");
                return null;
            }
        }
        for (int i9 = 0; i9 < MediaCodecList.getCodecCount(); i9++) {
            try {
                codecInfoAt = MediaCodecList.getCodecInfoAt(i9);
            } catch (IllegalArgumentException e9) {
                Logging.m23187e(TAG, "Cannot retrieve encoder codec info", e9);
                codecInfoAt = null;
            }
            if (codecInfoAt != null && codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int length = supportedTypes.length;
                int i10 = 0;
                while (true) {
                    if (i10 >= length) {
                        name = null;
                        break;
                    }
                    if (supportedTypes[i10].equals(str)) {
                        name = codecInfoAt.getName();
                        break;
                    }
                    i10++;
                }
                if (name == null) {
                    continue;
                } else {
                    Logging.m23188v(TAG, "Found candidate encoder " + name);
                    BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
                    int length2 = mediaCodecPropertiesArr.length;
                    int i11 = 0;
                    while (true) {
                        if (i11 >= length2) {
                            z8 = false;
                            break;
                        }
                        MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr[i11];
                        if (name.startsWith(mediaCodecProperties.codecPrefix)) {
                            int i12 = Build.VERSION.SDK_INT;
                            if (i12 < mediaCodecProperties.minSdk) {
                                Logging.m23189w(TAG, "Codec " + name + " is disabled due to SDK version " + i12);
                            } else {
                                BitrateAdjustmentType bitrateAdjustmentType2 = mediaCodecProperties.bitrateAdjustmentType;
                                if (bitrateAdjustmentType2 != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                    Logging.m23189w(TAG, "Codec " + name + " requires bitrate adjustment: " + bitrateAdjustmentType2);
                                    bitrateAdjustmentType = bitrateAdjustmentType2;
                                }
                                z8 = true;
                            }
                        }
                        i11++;
                    }
                    if (z8) {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                            for (int i13 : capabilitiesForType.colorFormats) {
                                Logging.m23188v(TAG, "   Color: 0x" + Integer.toHexString(i13));
                            }
                            for (int i14 : iArr) {
                                for (int i15 : capabilitiesForType.colorFormats) {
                                    if (i15 == i14 && !isColorBanned(name, str, i15)) {
                                        Logging.m23185d(TAG, "Found target encoder for mime " + str + " : " + name + ". Color: 0x" + Integer.toHexString(i15) + ". Bitrate adjustment: " + bitrateAdjustmentType);
                                        return new EncoderProperties(name, i15, bitrateAdjustmentType);
                                    }
                                }
                            }
                        } catch (IllegalArgumentException e10) {
                            Logging.m23187e(TAG, "Cannot retrieve encoder capabilities", e10);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private double getBitrateScale(int i9) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, i9 / 20.0d);
    }

    private static boolean isColorBanned(String str, String str2, int i9) {
        for (BanColorProperty banColorProperty : BAN_COLOR_LIST) {
            if (str.startsWith(banColorProperty.codecPrefix) && str2.equals(banColorProperty.mime) && i9 == banColorProperty.colorFormat) {
                return true;
            }
        }
        return false;
    }

    public static boolean isH264HwSupported() {
        return (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", h264HwList, supportedColorList) == null) ? false : true;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", h264HwList, supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isVp8HwSupported() {
        return (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList) == null) ? false : true;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedSurfaceColorList) == null) ? false : true;
    }

    public static boolean isVp9HwSupported() {
        return (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedColorList) == null) ? false : true;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedSurfaceColorList) == null) ? false : true;
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (mediaCodecVideoEncoder == null || (thread = mediaCodecVideoEncoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length > 0) {
            Logging.m23185d(TAG, "MediaCodecVideoEncoder stacks trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.m23185d(TAG, stackTraceElement.toString());
            }
        }
    }

    private void reportEncodedFrame(int i9) {
        int i10 = this.targetFps;
        if (i10 == 0 || this.bitrateAdjustmentType != BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            return;
        }
        double d9 = this.bitrateAccumulator + (i9 - (this.targetBitrateBps / (i10 * 8.0d)));
        this.bitrateAccumulator = d9;
        this.bitrateObservationTimeMs += 1000.0d / i10;
        double d10 = this.bitrateAccumulatorMax * BITRATE_CORRECTION_SEC;
        double dMin = Math.min(d9, d10);
        this.bitrateAccumulator = dMin;
        this.bitrateAccumulator = Math.max(dMin, -d10);
        if (this.bitrateObservationTimeMs > 3000.0d) {
            Logging.m23185d(TAG, "Acc: " + ((int) this.bitrateAccumulator) + ". Max: " + ((int) this.bitrateAccumulatorMax) + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            double d11 = this.bitrateAccumulator;
            double d12 = this.bitrateAccumulatorMax;
            boolean z8 = true;
            if (d11 > d12) {
                this.bitrateAdjustmentScaleExp -= (int) ((d11 / d12) + 0.5d);
                this.bitrateAccumulator = d12;
            } else if (d11 < (-d12)) {
                this.bitrateAdjustmentScaleExp += (int) (((-d11) / d12) + 0.5d);
                this.bitrateAccumulator = -d12;
            } else {
                z8 = false;
            }
            if (z8) {
                int iMin = Math.min(this.bitrateAdjustmentScaleExp, 20);
                this.bitrateAdjustmentScaleExp = iMin;
                this.bitrateAdjustmentScaleExp = Math.max(iMin, -20);
                Logging.m23185d(TAG, "Adjusting bitrate scale to " + this.bitrateAdjustmentScaleExp + ". Value: " + getBitrateScale(this.bitrateAdjustmentScaleExp));
                setRates(this.targetBitrateBps / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.targetFps);
            }
            this.bitrateObservationTimeMs = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.m23185d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    private boolean setRates(int i9, int i10) {
        checkOnMediaCodecThread();
        int bitrateScale = i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        BitrateAdjustmentType bitrateAdjustmentType = this.bitrateAdjustmentType;
        BitrateAdjustmentType bitrateAdjustmentType2 = BitrateAdjustmentType.DYNAMIC_ADJUSTMENT;
        if (bitrateAdjustmentType == bitrateAdjustmentType2) {
            double d9 = bitrateScale;
            this.bitrateAccumulatorMax = d9 / 8.0d;
            int i11 = this.targetBitrateBps;
            if (i11 > 0 && bitrateScale < i11) {
                this.bitrateAccumulator = (this.bitrateAccumulator * d9) / i11;
            }
        }
        this.targetBitrateBps = bitrateScale;
        this.targetFps = i10;
        if (bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && i10 > 0) {
            bitrateScale = (bitrateScale * 30) / i10;
            Logging.m23185d(TAG, "setRates: " + i9 + " -> " + (bitrateScale / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) + " kbps. Fps: " + this.targetFps);
        } else if (bitrateAdjustmentType == bitrateAdjustmentType2) {
            Logging.m23185d(TAG, "setRates: " + i9 + " kbps. Fps: " + this.targetFps + ". ExpScale: " + this.bitrateAdjustmentScaleExp);
            int i12 = this.bitrateAdjustmentScaleExp;
            if (i12 != 0) {
                bitrateScale = (int) (bitrateScale * getBitrateScale(i12));
            }
        } else {
            Logging.m23185d(TAG, "setRates: " + i9 + " kbps. Fps: " + this.targetFps);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", bitrateScale);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "setRates failed", e9);
            return false;
        }
    }

    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8")) {
            return null;
        }
        return findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList);
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            arrayList.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void checkKeyFrameRequired(boolean z8, long j9) {
        boolean z9;
        long j10 = (j9 + 500) / 1000;
        if (this.lastKeyFrameMs < 0) {
            this.lastKeyFrameMs = j10;
        }
        if (!z8) {
            long j11 = this.forcedKeyFrameMs;
            z9 = j11 > 0 && j10 > this.lastKeyFrameMs + j11;
        }
        if (z8 || z9) {
            if (z8) {
                Logging.m23185d(TAG, "Sync frame request");
            } else {
                Logging.m23185d(TAG, "Sync frame forced");
            }
            Bundle bundle = new Bundle();
            bundle.putInt("request-sync", 0);
            this.mediaCodec.setParameters(bundle);
            this.lastKeyFrameMs = j10;
        }
    }

    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0L);
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "dequeueIntputBuffer failed", e9);
            return -2;
        }
    }

    public OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
            boolean z8 = true;
            if (iDequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Logging.m23185d(TAG, "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[iDequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[iDequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[iDequeueOutputBuffer]);
                    this.mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                    iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
                }
            }
            int i9 = iDequeueOutputBuffer;
            if (i9 < 0) {
                if (i9 == -3) {
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    return dequeueOutputBuffer();
                }
                if (i9 == -2) {
                    return dequeueOutputBuffer();
                }
                if (i9 == -1) {
                    return null;
                }
                throw new RuntimeException("dequeueOutputBuffer: " + i9);
            }
            ByteBuffer byteBufferDuplicate = this.outputBuffers[i9].duplicate();
            byteBufferDuplicate.position(bufferInfo.offset);
            byteBufferDuplicate.limit(bufferInfo.offset + bufferInfo.size);
            reportEncodedFrame(bufferInfo.size);
            if ((bufferInfo.flags & 1) == 0) {
                z8 = false;
            }
            if (z8) {
                Logging.m23185d(TAG, "Sync frame generated");
            }
            if (!z8 || this.type != VideoCodecType.VIDEO_CODEC_H264) {
                return new OutputBufferInfo(i9, byteBufferDuplicate.slice(), z8, bufferInfo.presentationTimeUs);
            }
            Logging.m23185d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
            this.configData.rewind();
            byteBufferAllocateDirect.put(this.configData);
            byteBufferAllocateDirect.put(byteBufferDuplicate);
            byteBufferAllocateDirect.position(0);
            return new OutputBufferInfo(i9, byteBufferAllocateDirect, z8, bufferInfo.presentationTimeUs);
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "dequeueOutputBuffer failed", e9);
            return new OutputBufferInfo(-1, null, false, -1L);
        }
    }

    public boolean encodeBuffer(boolean z8, int i9, int i10, long j9) throws MediaCodec.CryptoException {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z8, j9);
            this.mediaCodec.queueInputBuffer(i9, 0, i10, j9, 0);
            return true;
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "encodeBuffer failed", e9);
            return false;
        }
    }

    public boolean encodeTexture(boolean z8, int i9, float[] fArr, long j9) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z8, j9);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            GlRectDrawer glRectDrawer = this.drawer;
            int i10 = this.width;
            int i11 = this.height;
            glRectDrawer.drawOes(i9, fArr, i10, i11, 0, 0, i10, i11);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j9));
            return true;
        } catch (RuntimeException e9) {
            Logging.m23187e(TAG, "encodeTexture failed", e9);
            return false;
        }
    }

    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.m23185d(TAG, "Input buffers: " + inputBuffers.length);
        return inputBuffers;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean initEncode(VideoCodecType videoCodecType, int i9, int i10, int i11, int i12, EglBase14.Context context) {
        String str;
        int i13;
        EncoderProperties encoderPropertiesFindHwEncoder;
        EncoderProperties encoderPropertiesFindHwEncoder2;
        boolean z8 = context != null;
        Logging.m23185d(TAG, "Java initEncode: " + videoCodecType + " : " + i9 + " x " + i10 + ". @ " + i11 + " kbps. Fps: " + i12 + ". Encode from texture : " + z8);
        this.width = i9;
        this.height = i10;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("Forgot to release()?");
        }
        VideoCodecType videoCodecType2 = VideoCodecType.VIDEO_CODEC_VP8;
        if (videoCodecType == videoCodecType2) {
            str = "video/x-vnd.on2.vp8";
            encoderPropertiesFindHwEncoder2 = findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), z8 ? supportedSurfaceColorList : supportedColorList);
        } else {
            if (videoCodecType != VideoCodecType.VIDEO_CODEC_VP9) {
                if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                    i13 = 20;
                    str = "video/avc";
                    encoderPropertiesFindHwEncoder = findHwEncoder("video/avc", h264HwList, z8 ? supportedSurfaceColorList : supportedColorList);
                } else {
                    str = null;
                    i13 = 0;
                    encoderPropertiesFindHwEncoder = null;
                }
                if (encoderPropertiesFindHwEncoder != null) {
                    throw new RuntimeException("Can not find HW encoder for " + videoCodecType);
                }
                runningInstance = this;
                this.colorFormat = encoderPropertiesFindHwEncoder.colorFormat;
                BitrateAdjustmentType bitrateAdjustmentType = encoderPropertiesFindHwEncoder.bitrateAdjustmentType;
                this.bitrateAdjustmentType = bitrateAdjustmentType;
                int iMin = bitrateAdjustmentType != BitrateAdjustmentType.FRAMERATE_ADJUSTMENT ? Math.min(i12, 30) : 30;
                this.forcedKeyFrameMs = 0L;
                this.lastKeyFrameMs = -1L;
                if (videoCodecType == videoCodecType2 && encoderPropertiesFindHwEncoder.codecName.startsWith(qcomVp8HwProperties.codecPrefix)) {
                    this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS;
                }
                Logging.m23185d(TAG, "Color format: " + this.colorFormat + ". Bitrate adjustment: " + this.bitrateAdjustmentType + ". Key frame interval: " + this.forcedKeyFrameMs + " . Initial fps: " + iMin);
                int i14 = i11 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
                this.targetBitrateBps = i14;
                this.targetFps = iMin;
                this.bitrateAccumulatorMax = ((double) i14) / 8.0d;
                this.bitrateAccumulator = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                this.bitrateObservationTimeMs = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                this.bitrateAdjustmentScaleExp = 0;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i9, i10);
                    mediaFormatCreateVideoFormat.setInteger("bitrate", this.targetBitrateBps);
                    mediaFormatCreateVideoFormat.setInteger("bitrate-mode", 2);
                    mediaFormatCreateVideoFormat.setInteger("color-format", encoderPropertiesFindHwEncoder.colorFormat);
                    mediaFormatCreateVideoFormat.setInteger("frame-rate", this.targetFps);
                    mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i13);
                    Logging.m23185d(TAG, "  Format: " + mediaFormatCreateVideoFormat);
                    MediaCodec mediaCodecCreateByCodecName = createByCodecName(encoderPropertiesFindHwEncoder.codecName);
                    this.mediaCodec = mediaCodecCreateByCodecName;
                    this.type = videoCodecType;
                    if (mediaCodecCreateByCodecName == null) {
                        Logging.m23186e(TAG, "Can not create media encoder");
                        return false;
                    }
                    mediaCodecCreateByCodecName.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                    if (z8) {
                        this.eglBase = new EglBase14(context, EglBase.CONFIG_RECORDABLE);
                        Surface surfaceCreateInputSurface = this.mediaCodec.createInputSurface();
                        this.inputSurface = surfaceCreateInputSurface;
                        this.eglBase.createSurface(surfaceCreateInputSurface);
                        this.drawer = new GlRectDrawer();
                    }
                    this.mediaCodec.start();
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    Logging.m23185d(TAG, "Output buffers: " + this.outputBuffers.length);
                    return true;
                } catch (IllegalStateException e9) {
                    Logging.m23187e(TAG, "initEncode failed", e9);
                    return false;
                }
            }
            encoderPropertiesFindHwEncoder2 = findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, z8 ? supportedSurfaceColorList : supportedColorList);
            str = "video/x-vnd.on2.vp9";
        }
        encoderPropertiesFindHwEncoder = encoderPropertiesFindHwEncoder2;
        i13 = 100;
        if (encoderPropertiesFindHwEncoder != null) {
        }
    }

    public void release() {
        Logging.m23185d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: org.webrtc.MediaCodecVideoEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.m23185d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    MediaCodecVideoEncoder.this.mediaCodec.stop();
                    MediaCodecVideoEncoder.this.mediaCodec.release();
                    Logging.m23185d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                } catch (Exception e9) {
                    Logging.m23187e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e9);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            Logging.m23186e(TAG, "Media encoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.m23186e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        Logging.m23185d(TAG, "Java releaseEncoder done");
    }

    public boolean releaseOutputBuffer(int i9) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i9, false);
            return true;
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "releaseOutputBuffer failed", e9);
            return false;
        }
    }
}
