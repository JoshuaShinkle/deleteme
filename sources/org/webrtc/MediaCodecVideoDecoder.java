package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.exoplayer2.DefaultRenderersFactory;
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
import org.webrtc.SurfaceTextureHelper;

/* loaded from: classes3.dex */
public class MediaCodecVideoDecoder {
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
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors;
    private static MediaCodecVideoDecoderErrorCallback errorCallback;
    private static MediaCodecVideoDecoder runningInstance;
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
    private TextureListener textureListener;
    private boolean useSurface;
    private int width;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Nvidia.", "OMX.Exynos.", "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    private static final String[] supportedH264HwCodecPrefixes = {"OMX.", "OMX.qcom.", "OMX.Intel.", "OMX.Exynos."};
    private static final String[] supportedH264HighProfileHwCodecPrefixes = {"OMX.qcom."};
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final List<Integer> supportedColorList = Arrays.asList(2135033992, 19, 21, 2141391872, Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf(COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf(COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m));
    private final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    private Surface surface = null;
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();

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

    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String str, int i9) {
            this.codecName = str;
            this.colorFormat = i9;
        }
    }

    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i9);
    }

    public static class TextureListener implements SurfaceTextureHelper.OnTextureFrameAvailableListener {
        private DecodedOutputBuffer bufferToRender;
        private final SurfaceTextureHelper surfaceTextureHelper;
        private final Object newFrameLock = new Object();
        private final Queue<DecodedTextureBuffer> decodedTextureBufferQueue = new LinkedList();

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper) {
            this.surfaceTextureHelper = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        private float[] cropMatrix(float[] fArr) {
            fArr[13] = fArr[13] * ((this.bufferToRender.height <= 360 || this.bufferToRender.height > 368) ? 1.0f : 360.0f / this.bufferToRender.height);
            return fArr;
        }

        public void addBufferToRender(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.bufferToRender == null) {
                this.bufferToRender = decodedOutputBuffer;
            } else {
                Logging.m23186e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int i9) {
            synchronized (this.newFrameLock) {
                if (this.decodedTextureBufferQueue.isEmpty() && i9 > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait(i9);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (this.decodedTextureBufferQueue.isEmpty()) {
                    return null;
                }
                return this.decodedTextureBufferQueue.remove();
            }
        }

        public boolean isWaitingForTexture() {
            boolean z8;
            synchronized (this.newFrameLock) {
                z8 = this.bufferToRender != null;
            }
            return z8;
        }

        @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
        public void onTextureFrameAvailable(int i9, float[] fArr, long j9) {
            synchronized (this.newFrameLock) {
                int size = this.decodedTextureBufferQueue.size();
                if (size > 0) {
                    Logging.m23189w(MediaCodecVideoDecoder.TAG, "onTextureFrameAvailable called but queued buffer size: " + size);
                }
                this.decodedTextureBufferQueue.add(new DecodedTextureBuffer(i9, cropMatrix(fArr), this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs, this.bufferToRender.rotation));
                this.bufferToRender = null;
                this.newFrameLock.notifyAll();
            }
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (!this.decodedTextureBufferQueue.isEmpty()) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.decodedTextureBufferQueue.clear();
                }
            }
        }
    }

    public static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final int rotation;
        private final long timeStampMs;

        public TimeStamps(long j9, long j10, long j11, int i9) {
            this.decodeStartTimeMs = j9;
            this.timeStampMs = j10;
            this.ntpTimeStampMs = j11;
            this.rotation = i9;
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (this.dequeuedSurfaceOutputBuffers.isEmpty() || this.textureListener.isWaitingForTexture()) {
            return;
        }
        DecodedOutputBuffer decodedOutputBufferRemove = this.dequeuedSurfaceOutputBuffers.remove();
        this.textureListener.addBufferToRender(decodedOutputBufferRemove);
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
                    TimeStamps timeStampsRemove = this.decodeStartTimeMs.remove();
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - timeStampsRemove.decodeStartTimeMs;
                    if (jElapsedRealtime > MAX_DECODE_TIME_MS) {
                        Logging.m23186e(TAG, "Very high decode time: " + jElapsedRealtime + "ms. Q size: " + this.decodeStartTimeMs.size() + ". Might be caused by resuming H264 decoding after a pause.");
                        j9 = 200L;
                    } else {
                        j9 = jElapsedRealtime;
                    }
                    return new DecodedOutputBuffer(iDequeueOutputBuffer, bufferInfo.offset, bufferInfo.size, TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs), timeStampsRemove.timeStampMs, timeStampsRemove.ntpTimeStampMs, j9, SystemClock.elapsedRealtime(), this.height, timeStampsRemove.rotation);
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
        DecodedTextureBuffer decodedTextureBufferDequeueTextureBuffer = this.textureListener.dequeueTextureBuffer(i9);
        if (decodedTextureBufferDequeueTextureBuffer != null) {
            MaybeRenderDecodedTextureBuffer();
            return decodedTextureBufferDequeueTextureBuffer;
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

    private static DecoderProperties findDecoder(String str, String[] strArr) {
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
                                        return new DecoderProperties(name, i13);
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
        DecoderProperties decoderPropertiesFindDecoder = findDecoder(str, strArr);
        if (decoderPropertiesFindDecoder == null) {
            throw new RuntimeException("Cannot find HW decoder for " + videoCodecType);
        }
        Logging.m23185d(TAG, "Java initDecode: " + videoCodecType + " : " + i9 + " x " + i10 + ". Color: 0x" + Integer.toHexString(decoderPropertiesFindDecoder.colorFormat) + ". Use Surface: " + this.useSurface);
        runningInstance = this;
        this.mediaCodecThread = Thread.currentThread();
        try {
            this.width = i9;
            this.height = i10;
            this.stride = i9;
            this.sliceHeight = i10;
            if (this.useSurface) {
                this.textureListener = new TextureListener(surfaceTextureHelper);
                this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(str, i9, i10);
            Logging.m23185d(TAG, "  Format: " + mediaFormatCreateVideoFormat);
            MediaCodec mediaCodecCreateByCodecName = MediaCodecVideoEncoder.createByCodecName(decoderPropertiesFindDecoder.codecName);
            this.mediaCodec = mediaCodecCreateByCodecName;
            if (mediaCodecCreateByCodecName == null) {
                Logging.m23186e(TAG, "Can not create media decoder");
                return false;
            }
            mediaCodecCreateByCodecName.configure(mediaFormatCreateVideoFormat, this.surface, (MediaCrypto) null, 0);
            this.mediaCodec.start();
            this.colorFormat = decoderPropertiesFindDecoder.colorFormat;
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
            this.inputBuffers = this.mediaCodec.getInputBuffers();
            this.decodeStartTimeMs.clear();
            this.hasDecodedFirstFrame = false;
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
        MediaCodecVideoDecoder mediaCodecVideoDecoder = runningInstance;
        if (mediaCodecVideoDecoder == null || (thread = mediaCodecVideoDecoder.mediaCodecThread) == null) {
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
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), j10, j11, i11));
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
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: org.webrtc.MediaCodecVideoDecoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.m23185d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.m23185d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e9) {
                    Logging.m23187e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e9);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            Logging.m23186e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                Logging.m23186e(TAG, "Invoke codec error callback. Errors: " + codecErrors);
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.m23185d(TAG, "Java releaseDecoder done");
    }

    private void reset(int i9, int i10) {
        if (this.mediaCodecThread == null || this.mediaCodec == null) {
            throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
        }
        Logging.m23185d(TAG, "Java reset: " + i9 + " x " + i10);
        this.mediaCodec.flush();
        this.width = i9;
        this.height = i10;
        this.decodeStartTimeMs.clear();
        this.dequeuedSurfaceOutputBuffers.clear();
        this.hasDecodedFirstFrame = false;
        this.droppedFrames = 0;
    }

    private void returnDecodedOutputBuffer(int i9) {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
        }
        this.mediaCodec.releaseOutputBuffer(i9, false);
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback mediaCodecVideoDecoderErrorCallback) {
        Logging.m23185d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoDecoderErrorCallback;
    }
}
