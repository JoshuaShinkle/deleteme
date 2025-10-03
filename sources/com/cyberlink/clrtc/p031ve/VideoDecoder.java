package com.cyberlink.clrtc.p031ve;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.view.Surface;
import androidx.annotation.Keep;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.Logging;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;

@Keep
/* loaded from: classes.dex */
public class VideoDecoder {
    private static final int DEQUEUE_INPUT_TIMEOUT = 200000;
    private static final String FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String FORMAT_KEY_STRIDE = "stride";
    private static final String H264_MIME_TYPE = "video/avc";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "VideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    private static InterfaceC1148c errorCallback;
    private static VideoDecoder runningInstance;
    private int colorFormat;
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private C1149d textureListener;
    private boolean useSurface;
    private int width;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Nvidia.", "OMX.Exynos.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    private static final String[] supportedH264HwCodecPrefixes = {"OMX.", "OMX.qcom.", "OMX.Intel.", "OMX.Exynos.", "c2.exynos."};
    private static final String[] supportedH264HighProfileHwCodecPrefixes = {"OMX.qcom."};
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final List<Integer> supportedColorList = Arrays.asList(2135033992, 19, 21, 2141391872, Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf(COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m));
    private boolean waitForFormatChange = false;
    private final Queue<C1150e> decodeStartTimeMs = new LinkedList();
    private Surface surface = null;
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();

    @Keep
    public static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int height;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int rotation;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int i9, int i10, int i11, long j9, long j10, long j11, long j12, long j13, int i12, int i13) {
            this.index = i9;
            this.offset = i10;
            this.size = i11;
            this.presentationTimeStampMs = j9;
            this.timeStampMs = j10;
            this.ntpTimeStampMs = j11;
            this.decodeTimeMs = j12;
            this.endDecodeTimeMs = j13;
            this.height = i12;
            this.rotation = i13;
        }
    }

    @Keep
    public static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final int rotation;
        private final int textureID;
        private final long timeStampMs;
        private final float[] transformMatrix;

        public DecodedTextureBuffer(int i9, float[] fArr, long j9, long j10, long j11, long j12, long j13, int i10) {
            this.textureID = i9;
            this.transformMatrix = fArr;
            this.presentationTimeStampMs = j9;
            this.timeStampMs = j10;
            this.ntpTimeStampMs = j11;
            this.decodeTimeMs = j12;
            this.frameDelayMs = j13;
            this.rotation = i10;
        }
    }

    @Keep
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoDecoder$a */
    public class RunnableC1146a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ CountDownLatch f5653b;

        public RunnableC1146a(CountDownLatch countDownLatch) {
            this.f5653b = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Logging.m23185d(VideoDecoder.TAG, "Java releaseDecoder on release thread");
                VideoDecoder.this.mediaCodec.stop();
                VideoDecoder.this.mediaCodec.release();
                Logging.m23185d(VideoDecoder.TAG, "Java releaseDecoder on release thread done");
            } catch (Exception e9) {
                Logging.m23187e(VideoDecoder.TAG, "Media decoder release failed", e9);
            }
            this.f5653b.countDown();
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoDecoder$b */
    public static class C1147b {

        /* renamed from: a */
        public final String f5655a;

        /* renamed from: b */
        public final int f5656b;

        public C1147b(String str, int i9) {
            this.f5655a = str;
            this.f5656b = i9;
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoDecoder$c */
    public interface InterfaceC1148c {
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoDecoder$d */
    public static class C1149d implements SurfaceTextureHelper.OnTextureFrameAvailableListener {

        /* renamed from: a */
        public final SurfaceTextureHelper f5657a;

        /* renamed from: c */
        public DecodedOutputBuffer f5659c;

        /* renamed from: b */
        public final Object f5658b = new Object();

        /* renamed from: d */
        public final Queue<DecodedTextureBuffer> f5660d = new LinkedList();

        public C1149d(SurfaceTextureHelper surfaceTextureHelper) {
            this.f5657a = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        /* renamed from: a */
        public void m5178a(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.f5659c == null) {
                this.f5659c = decodedOutputBuffer;
            } else {
                Logging.m23186e(VideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            }
        }

        /* renamed from: b */
        public final float[] m5179b(float[] fArr) {
            if (this.f5659c.height > 360 && this.f5659c.height <= 368) {
                float f9 = 358.0f / this.f5659c.height;
                Matrix.scaleM(fArr, 0, 1.0f, f9, 1.0f);
                Matrix.translateM(fArr, 0, BitmapDescriptorFactory.HUE_RED, 1.0f - f9, BitmapDescriptorFactory.HUE_RED);
            } else if (this.f5659c.height > 180 && this.f5659c.height <= 192) {
                float f10 = 178.0f / this.f5659c.height;
                Matrix.scaleM(fArr, 0, 1.0f, f10, 1.0f);
                Matrix.translateM(fArr, 0, BitmapDescriptorFactory.HUE_RED, 1.0f - f10, BitmapDescriptorFactory.HUE_RED);
            }
            return fArr;
        }

        /* renamed from: c */
        public DecodedTextureBuffer m5180c(int i9) {
            synchronized (this.f5658b) {
                if (this.f5660d.isEmpty() && i9 > 0 && m5181d()) {
                    try {
                        this.f5658b.wait(i9);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (this.f5660d.isEmpty()) {
                    return null;
                }
                return this.f5660d.remove();
            }
        }

        /* renamed from: d */
        public boolean m5181d() {
            boolean z8;
            synchronized (this.f5658b) {
                z8 = this.f5659c != null;
            }
            return z8;
        }

        /* renamed from: e */
        public void m5182e() {
            this.f5657a.stopListening();
            synchronized (this.f5658b) {
                if (!this.f5660d.isEmpty()) {
                    this.f5657a.returnTextureFrame();
                    this.f5660d.clear();
                }
            }
        }

        @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
        public void onTextureFrameAvailable(int i9, float[] fArr, long j9) {
            synchronized (this.f5658b) {
                int size = this.f5660d.size();
                if (size > 0) {
                    Logging.m23189w(VideoDecoder.TAG, "onTextureFrameAvailable called but queued buffer size: " + size);
                }
                this.f5660d.add(new DecodedTextureBuffer(i9, m5179b(fArr), this.f5659c.presentationTimeStampMs, this.f5659c.timeStampMs, this.f5659c.ntpTimeStampMs, this.f5659c.decodeTimeMs, SystemClock.elapsedRealtime() - this.f5659c.endDecodeTimeMs, this.f5659c.rotation));
                this.f5659c = null;
                this.f5658b.notifyAll();
            }
        }
    }

    /* renamed from: com.cyberlink.clrtc.ve.VideoDecoder$e */
    public static class C1150e {

        /* renamed from: a */
        public final long f5661a;

        /* renamed from: b */
        public final long f5662b;

        /* renamed from: c */
        public final long f5663c;

        /* renamed from: d */
        public final int f5664d;

        public C1150e(long j9, long j10, long j11, int i9) {
            this.f5661a = j9;
            this.f5662b = j10;
            this.f5663c = j11;
            this.f5664d = i9;
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (this.dequeuedSurfaceOutputBuffers.isEmpty() || this.textureListener.m5181d()) {
            return;
        }
        DecodedOutputBuffer decodedOutputBufferRemove = this.dequeuedSurfaceOutputBuffers.remove();
        this.textureListener.m5178a(decodedOutputBufferRemove);
        this.mediaCodec.releaseOutputBuffer(decodedOutputBufferRemove.index, true);
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() == Thread.currentThread().getId()) {
            return;
        }
        throw new IllegalStateException("MediaCodecVideoDecoder previously operated on " + this.mediaCodecThread + " but is now called on " + Thread.currentThread());
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        int i9 = 0;
        int iDequeueInputBuffer = -1;
        while (true) {
            int i10 = i9 + 1;
            if (i9 > 5) {
                Logging.m23189w(TAG, "dequeueInputBuffer retry but unavailable");
                return iDequeueInputBuffer;
            }
            try {
                iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(200000L);
                if (iDequeueInputBuffer >= 0) {
                    return iDequeueInputBuffer;
                }
                i9 = i10;
            } catch (IllegalStateException e9) {
                Logging.m23187e(TAG, "dequeueInputBuffer failed", e9);
                return -2;
            }
            Logging.m23187e(TAG, "dequeueInputBuffer failed", e9);
            return -2;
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int i9) {
        long j9;
        int integer;
        int integer2;
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, TimeUnit.MILLISECONDS.toMicros(i9));
            if (iDequeueOutputBuffer == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                Logging.m23185d(TAG, "Decoder output buffers changed: " + this.outputBuffers.length);
            } else {
                if (iDequeueOutputBuffer != -2) {
                    if (iDequeueOutputBuffer == -1) {
                        return null;
                    }
                    this.hasDecodedFirstFrame = true;
                    C1150e c1150eRemove = this.decodeStartTimeMs.remove();
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - c1150eRemove.f5661a;
                    if (jElapsedRealtime > MAX_DECODE_TIME_MS) {
                        Logging.m23186e(TAG, "Very high decode time: " + jElapsedRealtime + "ms. Q size: " + this.decodeStartTimeMs.size() + ". Might be caused by resuming H264 decoding after a pause.");
                        j9 = 200L;
                    } else {
                        j9 = jElapsedRealtime;
                    }
                    int i10 = this.height;
                    if (this.waitForFormatChange && i10 == 180) {
                        i10 = PsExtractor.AUDIO_STREAM;
                    }
                    return new DecodedOutputBuffer(iDequeueOutputBuffer, bufferInfo.offset, bufferInfo.size, TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs), c1150eRemove.f5662b, c1150eRemove.f5663c, j9, SystemClock.elapsedRealtime(), i10, c1150eRemove.f5664d);
                }
                MediaFormat outputFormat = this.mediaCodec.getOutputFormat();
                Logging.m23185d(TAG, "Decoder format changed: " + outputFormat.toString());
                if (outputFormat.containsKey(FORMAT_KEY_CROP_LEFT) && outputFormat.containsKey(FORMAT_KEY_CROP_RIGHT) && outputFormat.containsKey(FORMAT_KEY_CROP_BOTTOM) && outputFormat.containsKey(FORMAT_KEY_CROP_TOP)) {
                    integer = (outputFormat.getInteger(FORMAT_KEY_CROP_RIGHT) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_LEFT);
                    integer2 = (outputFormat.getInteger(FORMAT_KEY_CROP_BOTTOM) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_TOP);
                } else {
                    integer = outputFormat.getInteger("width");
                    integer2 = outputFormat.getInteger("height");
                }
                if (this.hasDecodedFirstFrame && (integer != this.width || integer2 != this.height)) {
                    Logging.m23189w(TAG, " > Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + integer + "*" + integer2);
                }
                this.width = integer;
                this.height = integer2;
                this.waitForFormatChange = false;
                if (!this.useSurface && outputFormat.containsKey("color-format")) {
                    this.colorFormat = outputFormat.getInteger("color-format");
                    Logging.m23185d(TAG, "Color: 0x" + Integer.toHexString(this.colorFormat));
                    if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                        throw new IllegalStateException("Non supported color format: " + this.colorFormat);
                    }
                }
                if (outputFormat.containsKey(FORMAT_KEY_STRIDE)) {
                    this.stride = outputFormat.getInteger(FORMAT_KEY_STRIDE);
                }
                if (outputFormat.containsKey(FORMAT_KEY_SLICE_HEIGHT)) {
                    this.sliceHeight = outputFormat.getInteger(FORMAT_KEY_SLICE_HEIGHT);
                }
                Logging.m23185d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                this.stride = Math.max(this.width, this.stride);
                this.sliceHeight = Math.max(this.height, this.sliceHeight);
            }
        }
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int i9) {
        checkOnMediaCodecThread();
        if (!this.useSurface) {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
        DecodedOutputBuffer decodedOutputBufferDequeueOutputBuffer = dequeueOutputBuffer(i9);
        if (decodedOutputBufferDequeueOutputBuffer != null) {
            this.dequeuedSurfaceOutputBuffers.add(decodedOutputBufferDequeueOutputBuffer);
        }
        MaybeRenderDecodedTextureBuffer();
        DecodedTextureBuffer decodedTextureBufferM5180c = this.textureListener.m5180c(i9);
        if (decodedTextureBufferM5180c != null) {
            MaybeRenderDecodedTextureBuffer();
            return decodedTextureBufferM5180c;
        }
        if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (i9 <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
            return null;
        }
        this.droppedFrames++;
        DecodedOutputBuffer decodedOutputBufferRemove = this.dequeuedSurfaceOutputBuffers.remove();
        if (i9 > 0) {
            Logging.m23189w(TAG, "Draining decoder. Dropping frame with TS: " + decodedOutputBufferRemove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
        } else {
            Logging.m23189w(TAG, "Too many output buffers " + this.dequeuedSurfaceOutputBuffers.size() + ". Dropping frame with TS: " + decodedOutputBufferRemove.presentationTimeStampMs + ". Total number of dropped frames: " + this.droppedFrames);
        }
        this.mediaCodec.releaseOutputBuffer(decodedOutputBufferRemove.index, false);
        return new DecodedTextureBuffer(0, null, decodedOutputBufferRemove.presentationTimeStampMs, decodedOutputBufferRemove.timeStampMs, decodedOutputBufferRemove.ntpTimeStampMs, decodedOutputBufferRemove.decodeTimeMs, SystemClock.elapsedRealtime() - decodedOutputBufferRemove.endDecodeTimeMs, decodedOutputBufferRemove.rotation);
    }

    public static void disableH264HwCodec() {
        Logging.m23189w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.m23189w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.m23189w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    private static C1147b findDecoder(String str, String[] strArr) {
        MediaCodecInfo codecInfoAt;
        boolean z8;
        Logging.m23185d(TAG, "Trying to find HW decoder for mime " + str);
        int i9 = 0;
        while (true) {
            String name = null;
            if (i9 >= MediaCodecList.getCodecCount()) {
                Logging.m23185d(TAG, "No HW decoder found for mime " + str);
                return null;
            }
            try {
                codecInfoAt = MediaCodecList.getCodecInfoAt(i9);
            } catch (IllegalArgumentException e9) {
                Logging.m23187e(TAG, "Cannot retrieve decoder codec info", e9);
                codecInfoAt = null;
            }
            if (codecInfoAt != null && !codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int length = supportedTypes.length;
                int i10 = 0;
                while (true) {
                    if (i10 >= length) {
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
                    Logging.m23185d(TAG, "Found candidate decoder " + name);
                    int length2 = strArr.length;
                    int i11 = 0;
                    while (true) {
                        if (i11 >= length2) {
                            z8 = false;
                            break;
                        }
                        if (name.startsWith(strArr[i11])) {
                            z8 = true;
                            break;
                        }
                        i11++;
                    }
                    if (z8) {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                            for (int i12 : capabilitiesForType.colorFormats) {
                                Logging.m23188v(TAG, "   Color: 0x" + Integer.toHexString(i12));
                            }
                            Iterator<Integer> it = supportedColorList.iterator();
                            while (it.hasNext()) {
                                int iIntValue = it.next().intValue();
                                for (int i13 : capabilitiesForType.colorFormats) {
                                    if (i13 == iIntValue) {
                                        Logging.m23185d(TAG, "Found target decoder " + name + ". Color: 0x" + Integer.toHexString(i13));
                                        return new C1147b(name, i13);
                                    }
                                }
                            }
                        } catch (IllegalArgumentException e10) {
                            Logging.m23187e(TAG, "Cannot retrieve decoder capabilities", e10);
                        }
                    } else {
                        continue;
                    }
                }
            }
            i9++;
        }
    }

    private boolean initDecode(VideoCodecType videoCodecType, int i9, int i10, SurfaceTextureHelper surfaceTextureHelper) {
        String[] strArr;
        String str;
        if (this.mediaCodecThread != null) {
            throw new RuntimeException("initDecode: Forgot to release()?");
        }
        this.useSurface = surfaceTextureHelper != null;
        if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
            strArr = supportedVp8HwCodecPrefixes;
            str = "video/x-vnd.on2.vp8";
        } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
            strArr = supportedVp9HwCodecPrefixes;
            str = "video/x-vnd.on2.vp9";
        } else {
            if (videoCodecType != VideoCodecType.VIDEO_CODEC_H264) {
                throw new RuntimeException("initDecode: Non-supported codec " + videoCodecType);
            }
            strArr = supportedH264HwCodecPrefixes;
            str = "video/avc";
        }
        C1147b c1147bFindDecoder = findDecoder(str, strArr);
        if (c1147bFindDecoder == null) {
            throw new RuntimeException("Cannot find HW decoder for " + videoCodecType);
        }
        Logging.m23185d(TAG, "Java initDecode: " + videoCodecType + " : " + i9 + " x " + i10 + ". Color: 0x" + Integer.toHexString(c1147bFindDecoder.f5656b) + ". Use Surface: " + this.useSurface);
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        try {
            this.width = i9;
            this.height = i10;
            this.stride = i9;
            this.sliceHeight = i10;
            if (this.useSurface) {
                this.textureListener = new C1149d(surfaceTextureHelper);
                this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i9, i10);
            if (C1156a.m5198l(this.useSurface)) {
                mediaFormatCreateVideoFormat.setInteger("color-format", c1147bFindDecoder.f5656b);
            }
            Logging.m23185d(TAG, "  Format: " + mediaFormatCreateVideoFormat);
            MediaCodec mediaCodecCreateByCodecName = VideoEncoder.createByCodecName(c1147bFindDecoder.f5655a);
            this.mediaCodec = mediaCodecCreateByCodecName;
            if (mediaCodecCreateByCodecName == null) {
                Logging.m23186e(TAG, "Can not create media decoder");
                return false;
            }
            mediaCodecCreateByCodecName.configure(mediaFormatCreateVideoFormat, this.surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
            this.colorFormat = c1147bFindDecoder.f5656b;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
            this.waitForFormatChange = true;
            this.dequeuedSurfaceOutputBuffers.clear();
            this.droppedFrames = 0;
            Logging.m23185d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
            return true;
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "initDecode failed", e9);
            return false;
        }
    }

    public static boolean isH264HighProfileHwSupported() {
        if (C1156a.m5194h()) {
            return true;
        }
        return (hwDecoderDisabledTypes.contains("video/avc") || findDecoder("video/avc", supportedH264HighProfileHwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isH264HwSupported() {
        return (hwDecoderDisabledTypes.contains("video/avc") || findDecoder("video/avc", supportedH264HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isVp8HwSupported() {
        return (hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findDecoder("video/x-vnd.on2.vp8", supportedVp8HwCodecPrefixes) == null) ? false : true;
    }

    public static boolean isVp9HwSupported() {
        return (hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findDecoder("video/x-vnd.on2.vp9", supportedVp9HwCodecPrefixes) == null) ? false : true;
    }

    public static void printStackTrace() {
        Thread thread;
        VideoDecoder videoDecoder = runningInstance;
        if (videoDecoder == null || (thread = videoDecoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length > 0) {
            Logging.m23185d(TAG, "MediaCodecVideoDecoder stacks trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.m23185d(TAG, stackTraceElement.toString());
            }
        }
    }

    private boolean queueInputBuffer(int i9, int i10, long j9, long j10, long j11, int i11) throws MediaCodec.CryptoException {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[i9].position(0);
            this.inputBuffers[i9].limit(i10);
            this.decodeStartTimeMs.add(new C1150e(SystemClock.elapsedRealtime(), j10, j11, i11));
            this.mediaCodec.queueInputBuffer(i9, 0, i10, j9, 0);
            return true;
        } catch (IllegalStateException e9) {
            Logging.m23187e(TAG, "decode failed", e9);
            return false;
        }
    }

    private void release() {
        Logging.m23185d(TAG, "Java releaseDecoder. Total number of dropped frames: " + this.droppedFrames);
        checkOnMediaCodecThread();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new RunnableC1146a(countDownLatch)).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            Logging.m23186e(TAG, "Media decoder release timeout");
            codecErrors++;
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.m5182e();
        }
        Logging.m23185d(TAG, "Java releaseDecoder done");
    }

    private boolean reset(int i9, int i10) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.m23185d(TAG, "Java reset: " + i9 + " x " + i10);
        this.mediaCodec.flush();
        boolean z8 = this.hasDecodedFirstFrame && C1156a.m5196j() && !(this.width == i9 && this.height == i10);
        this.width = i9;
        this.height = i10;
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.waitForFormatChange = true;
        this.droppedFrames = 0;
        return z8;
    }

    private void returnDecodedOutputBuffer(int i9) {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(i9, false);
    }

    public static void setErrorCallback(InterfaceC1148c interfaceC1148c) {
        Logging.m23185d(TAG, "Set error callback");
    }
}
