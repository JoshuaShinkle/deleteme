package com.cyberlink.clrtc.p031ve;

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
import androidx.annotation.Keep;
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
import org.webrtc.EglBase;
import org.webrtc.EglBase14;
import org.webrtc.GlRectDrawer;
import org.webrtc.Logging;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.ThreadUtils;

@Keep
@TargetApi(19)
/* loaded from: classes.dex */
public class VideoEncoder {
    private static final C1152b[] BAN_COLOR_LIST;
    private static final int BITRATE_ADJUSTMENT_FPS = 24;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String[] H264_HW_EXCEPTION_MODELS;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 24;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 25000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "VideoEncoder";
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static final C1154d c2exynosH264HwProperties;
    private static int codecErrors;
    private static InterfaceC1155e errorCallback;
    private static final C1154d exynosH264HwProperties;
    private static final C1154d exynosVp8HwProperties;
    private static final C1154d exynosVp9HwProperties;
    private static final C1154d[] h264HwList;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final C1154d intelVp8HwProperties;
    private static final C1154d omxH264HwProperties;
    private static final C1154d qcomH264HwProperties;
    private static final C1154d qcomVp8HwProperties;
    private static final C1154d qcomVp9HwProperties;
    private static VideoEncoder runningInstance;
    private static final int[] supportedColorList;
    private static final int[] supportedSurfaceColorList;
    private static final C1152b topazH264FormatYUV420Planar;
    private static final C1154d[] vp9HwList;
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

    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    @Keep
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

    @Keep
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoEncoder$a */
    public class RunnableC1151a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ CountDownLatch f5669b;

        public RunnableC1151a(CountDownLatch countDownLatch) {
            this.f5669b = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Logging.m23185d(VideoEncoder.TAG, "Java releaseEncoder on release thread");
                VideoEncoder.this.mediaCodec.stop();
                VideoEncoder.this.mediaCodec.release();
                Logging.m23185d(VideoEncoder.TAG, "Java releaseEncoder on release thread done");
            } catch (Exception e9) {
                Logging.m23187e(VideoEncoder.TAG, "Media encoder release failed", e9);
            }
            this.f5669b.countDown();
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoEncoder$b */
    public static class C1152b {

        /* renamed from: a */
        public final String f5671a;

        /* renamed from: b */
        public final String f5672b;

        /* renamed from: c */
        public final int f5673c;

        public C1152b(String str, String str2, int i9) {
            this.f5671a = str;
            this.f5672b = str2;
            this.f5673c = i9;
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoEncoder$c */
    public static class C1153c {

        /* renamed from: a */
        public final String f5674a;

        /* renamed from: b */
        public final int f5675b;

        /* renamed from: c */
        public final BitrateAdjustmentType f5676c;

        public C1153c(String str, int i9, BitrateAdjustmentType bitrateAdjustmentType) {
            this.f5674a = str;
            this.f5675b = i9;
            this.f5676c = bitrateAdjustmentType;
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoEncoder$d */
    public static class C1154d {

        /* renamed from: a */
        public final String f5677a;

        /* renamed from: b */
        public final int f5678b;

        /* renamed from: c */
        public final BitrateAdjustmentType f5679c;

        public C1154d(String str, int i9, BitrateAdjustmentType bitrateAdjustmentType) {
            this.f5677a = str;
            this.f5678b = i9;
            this.f5679c = bitrateAdjustmentType;
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoEncoder$e */
    public interface InterfaceC1155e {
    }

    static {
        BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
        qcomVp8HwProperties = new C1154d("OMX.qcom.", 19, bitrateAdjustmentType);
        exynosVp8HwProperties = new C1154d("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
        intelVp8HwProperties = new C1154d("OMX.Intel.", 21, bitrateAdjustmentType);
        C1154d c1154d = new C1154d("OMX.qcom.", 23, bitrateAdjustmentType);
        qcomVp9HwProperties = c1154d;
        C1154d c1154d2 = new C1154d("OMX.Exynos.", 23, bitrateAdjustmentType);
        exynosVp9HwProperties = c1154d2;
        vp9HwList = new C1154d[]{c1154d, c1154d2};
        C1154d c1154d3 = new C1154d("OMX.qcom.", 19, bitrateAdjustmentType);
        qcomH264HwProperties = c1154d3;
        C1154d c1154d4 = new C1154d("OMX.Exynos.", 21, bitrateAdjustmentType);
        exynosH264HwProperties = c1154d4;
        C1154d c1154d5 = new C1154d("c2.exynos.", 21, bitrateAdjustmentType);
        c2exynosH264HwProperties = c1154d5;
        C1154d c1154d6 = new C1154d("OMX.", 19, bitrateAdjustmentType);
        omxH264HwProperties = c1154d6;
        h264HwList = new C1154d[]{c1154d3, c1154d4, c1154d5, c1154d6};
        H264_HW_EXCEPTION_MODELS = new String[0];
        supportedColorList = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
        supportedSurfaceColorList = new int[]{2130708361};
        C1152b c1152b = new C1152b("OMX.IMG.TOPAZ.VIDEO.Encoder", "video/avc", 19);
        topazH264FormatYUV420Planar = c1152b;
        BAN_COLOR_LIST = new C1152b[]{c1152b};
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

    private static C1153c findHwEncoder(String str, C1154d[] c1154dArr, int[] iArr) {
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
                    int length2 = c1154dArr.length;
                    int i11 = 0;
                    while (true) {
                        if (i11 >= length2) {
                            z8 = false;
                            break;
                        }
                        C1154d c1154d = c1154dArr[i11];
                        if (name.startsWith(c1154d.f5677a)) {
                            int i12 = Build.VERSION.SDK_INT;
                            if (i12 < c1154d.f5678b) {
                                Logging.m23189w(TAG, "Codec " + name + " is disabled due to SDK version " + i12);
                            } else {
                                BitrateAdjustmentType bitrateAdjustmentType2 = c1154d.f5679c;
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
                                        return new C1153c(name, i15, bitrateAdjustmentType);
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
        for (C1152b c1152b : BAN_COLOR_LIST) {
            if (str.startsWith(c1152b.f5671a) && str2.equals(c1152b.f5672b) && i9 == c1152b.f5673c) {
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
        VideoEncoder videoEncoder = runningInstance;
        if (videoEncoder == null || (thread = videoEncoder.mediaCodecThread) == null) {
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

    public static void setErrorCallback(InterfaceC1155e interfaceC1155e) {
        Logging.m23185d(TAG, "Set error callback");
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
            bitrateScale = (bitrateScale * 24) / i10;
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

    public static C1153c vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8")) {
            return null;
        }
        return findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList);
    }

    private static C1154d[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            arrayList.add(intelVp8HwProperties);
        }
        return (C1154d[]) arrayList.toArray(new C1154d[arrayList.size()]);
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

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean initEncode(VideoCodecType videoCodecType, int i9, int i10, int i11, int i12, EglBase14.Context context) {
        String str;
        C1153c c1153cFindHwEncoder;
        int i13;
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
            c1153cFindHwEncoder = findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), z8 ? supportedSurfaceColorList : supportedColorList);
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
            c1153cFindHwEncoder = findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, z8 ? supportedSurfaceColorList : supportedColorList);
            str = "video/x-vnd.on2.vp9";
        } else {
            if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                c1153cFindHwEncoder = findHwEncoder("video/avc", h264HwList, z8 ? supportedSurfaceColorList : supportedColorList);
                str = "video/avc";
                i13 = 2;
            } else {
                str = null;
                c1153cFindHwEncoder = null;
                i13 = 0;
            }
            if (c1153cFindHwEncoder != null) {
                throw new RuntimeException("Can not find HW encoder for " + videoCodecType);
            }
            runningInstance = this;
            this.colorFormat = c1153cFindHwEncoder.f5675b;
            BitrateAdjustmentType bitrateAdjustmentType = c1153cFindHwEncoder.f5676c;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
            int iMin = bitrateAdjustmentType != BitrateAdjustmentType.FRAMERATE_ADJUSTMENT ? Math.min(i12, 24) : 24;
            this.forcedKeyFrameMs = 0L;
            this.lastKeyFrameMs = -1L;
            if (videoCodecType == videoCodecType2 && c1153cFindHwEncoder.f5674a.startsWith(qcomVp8HwProperties.f5677a)) {
                this.forcedKeyFrameMs = QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Color format: ");
            sb.append(this.colorFormat);
            sb.append(". Bitrate adjustment: ");
            sb.append(this.bitrateAdjustmentType);
            sb.append(". Key frame interval: ");
            boolean z9 = z8;
            sb.append(this.forcedKeyFrameMs);
            sb.append(" . Initial fps: ");
            sb.append(iMin);
            Logging.m23185d(TAG, sb.toString());
            int i14 = i11 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
            this.targetBitrateBps = i14;
            this.targetFps = iMin;
            this.bitrateAccumulatorMax = i14 / 8.0d;
            this.bitrateAccumulator = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.bitrateObservationTimeMs = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.bitrateAdjustmentScaleExp = 0;
            this.mediaCodecThread = Thread.currentThread();
            try {
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i9, i10);
                mediaFormatCreateVideoFormat.setInteger("bitrate", this.targetBitrateBps);
                mediaFormatCreateVideoFormat.setInteger("bitrate-mode", 2);
                mediaFormatCreateVideoFormat.setInteger("color-format", c1153cFindHwEncoder.f5675b);
                mediaFormatCreateVideoFormat.setInteger("frame-rate", this.targetFps);
                mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i13);
                MediaFormat mediaFormatM5199m = C1156a.m5199m(mediaFormatCreateVideoFormat);
                Logging.m23185d(TAG, "  Format: " + mediaFormatM5199m);
                MediaCodec mediaCodecCreateByCodecName = createByCodecName(c1153cFindHwEncoder.f5674a);
                this.mediaCodec = mediaCodecCreateByCodecName;
                this.type = videoCodecType;
                if (mediaCodecCreateByCodecName == null) {
                    Logging.m23186e(TAG, "Can not create media encoder");
                    return false;
                }
                mediaCodecCreateByCodecName.configure(mediaFormatM5199m, (Surface) null, (MediaCrypto) null, 1);
                if (z9) {
                    try {
                        this.eglBase = new EglBase14(context, EglBase.CONFIG_RECORDABLE);
                        Surface surfaceCreateInputSurface = this.mediaCodec.createInputSurface();
                        this.inputSurface = surfaceCreateInputSurface;
                        this.eglBase.createSurface(surfaceCreateInputSurface);
                        this.drawer = new GlRectDrawer();
                    } catch (Throwable th) {
                        th = th;
                        Logging.m23187e(TAG, "initEncode failed", th);
                        if (C1156a.m5192f() <= 0 && C1156a.m5191e() <= 0) {
                            return false;
                        }
                        C1156a.m5201o(0);
                        C1156a.m5200n(0);
                        MediaCodec mediaCodec = this.mediaCodec;
                        if (mediaCodec != null) {
                            mediaCodec.stop();
                            this.mediaCodec.release();
                            this.mediaCodec = null;
                            this.mediaCodecThread = null;
                        }
                        return initEncode(videoCodecType, i9, i10, i11, i12, context);
                    }
                }
                this.mediaCodec.start();
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                Logging.m23185d(TAG, "Output buffers: " + this.outputBuffers.length);
                return true;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        i13 = 100;
        if (c1153cFindHwEncoder != null) {
        }
    }

    public void release() {
        Logging.m23185d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new RunnableC1151a(countDownLatch)).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            Logging.m23186e(TAG, "Media encoder release timeout");
            codecErrors++;
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
