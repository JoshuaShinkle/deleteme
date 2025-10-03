package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.VideoRenderer;

/* loaded from: classes3.dex */
public class VideoFileRenderer implements VideoRenderer.Callbacks {
    private static final String TAG = "VideoFileRenderer";
    private EglBase eglBase;
    private final int outputFileHeight;
    private final String outputFileName;
    private final int outputFileWidth;
    private final ByteBuffer outputFrameBuffer;
    private final int outputFrameSize;
    private final HandlerThread renderThread;
    private final Handler renderThreadHandler;
    private final FileOutputStream videoOutFile;
    private YuvConverter yuvConverter;
    private final Object handlerLock = new Object();
    private ArrayList<ByteBuffer> rawFrames = new ArrayList<>();

    public VideoFileRenderer(String str, int i9, int i10, final EglBase.Context context) throws IOException {
        if (i9 % 2 == 1 || i10 % 2 == 1) {
            throw new IllegalArgumentException("Does not support uneven width or height");
        }
        this.outputFileName = str;
        this.outputFileWidth = i9;
        this.outputFileHeight = i10;
        int i11 = ((i9 * i10) * 3) / 2;
        this.outputFrameSize = i11;
        this.outputFrameBuffer = ByteBuffer.allocateDirect(i11);
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        this.videoOutFile = fileOutputStream;
        fileOutputStream.write(("YUV4MPEG2 C420 W" + i9 + " H" + i10 + " Ip F30:1 A1:1\n").getBytes());
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.renderThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.renderThreadHandler = handler;
        ThreadUtils.invokeAtFrontUninterruptibly(handler, new Runnable() { // from class: org.webrtc.VideoFileRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.eglBase = EglBase.create(context, EglBase.CONFIG_PIXEL_BUFFER);
                VideoFileRenderer.this.eglBase.createDummyPbufferSurface();
                VideoFileRenderer.this.eglBase.makeCurrent();
                VideoFileRenderer.this.yuvConverter = new YuvConverter();
            }
        });
    }

    public static native ByteBuffer nativeCreateNativeByteBuffer(int i9);

    public static native void nativeFreeNativeByteBuffer(ByteBuffer byteBuffer);

    public static native void nativeI420Scale(ByteBuffer byteBuffer, int i9, ByteBuffer byteBuffer2, int i10, ByteBuffer byteBuffer3, int i11, int i12, int i13, ByteBuffer byteBuffer4, int i14, int i15);

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread(VideoRenderer.I420Frame i420Frame) {
        int i9;
        float[] fArrMultiplyMatrices = RendererCommon.multiplyMatrices(RendererCommon.rotateTextureMatrix(i420Frame.samplingMatrix, i420Frame.rotationDegree), RendererCommon.getLayoutMatrix(false, i420Frame.rotatedWidth() / i420Frame.rotatedHeight(), this.outputFileWidth / this.outputFileHeight));
        try {
            ByteBuffer byteBufferNativeCreateNativeByteBuffer = nativeCreateNativeByteBuffer(this.outputFrameSize);
            if (i420Frame.yuvFrame) {
                ByteBuffer[] byteBufferArr = i420Frame.yuvPlanes;
                ByteBuffer byteBuffer = byteBufferArr[0];
                int[] iArr = i420Frame.yuvStrides;
                nativeI420Scale(byteBuffer, iArr[0], byteBufferArr[1], iArr[1], byteBufferArr[2], iArr[2], i420Frame.width, i420Frame.height, this.outputFrameBuffer, this.outputFileWidth, this.outputFileHeight);
                byteBufferNativeCreateNativeByteBuffer.put(this.outputFrameBuffer.array(), this.outputFrameBuffer.arrayOffset(), this.outputFrameSize);
            } else {
                YuvConverter yuvConverter = this.yuvConverter;
                ByteBuffer byteBuffer2 = this.outputFrameBuffer;
                int i10 = this.outputFileWidth;
                yuvConverter.convert(byteBuffer2, i10, this.outputFileHeight, i10, i420Frame.textureId, fArrMultiplyMatrices);
                int i11 = this.outputFileWidth;
                byte[] bArrArray = this.outputFrameBuffer.array();
                int iArrayOffset = this.outputFrameBuffer.arrayOffset();
                byteBufferNativeCreateNativeByteBuffer.put(bArrArray, iArrayOffset, this.outputFileWidth * this.outputFileHeight);
                int i12 = this.outputFileHeight;
                while (true) {
                    i9 = this.outputFileHeight;
                    if (i12 >= (i9 * 3) / 2) {
                        break;
                    }
                    byteBufferNativeCreateNativeByteBuffer.put(bArrArray, (i12 * i11) + iArrayOffset, i11 / 2);
                    i12++;
                }
                while (i9 < (this.outputFileHeight * 3) / 2) {
                    byteBufferNativeCreateNativeByteBuffer.put(bArrArray, (i9 * i11) + iArrayOffset + (i11 / 2), i11 / 2);
                    i9++;
                }
            }
            byteBufferNativeCreateNativeByteBuffer.rewind();
            this.rawFrames.add(byteBufferNativeCreateNativeByteBuffer);
        } finally {
            VideoRenderer.renderFrameDone(i420Frame);
        }
    }

    public void release() throws IOException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.yuvConverter.release();
                VideoFileRenderer.this.eglBase.release();
                VideoFileRenderer.this.renderThread.quit();
                countDownLatch.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
        try {
            Iterator<ByteBuffer> it = this.rawFrames.iterator();
            while (it.hasNext()) {
                ByteBuffer next = it.next();
                this.videoOutFile.write("FRAME\n".getBytes());
                byte[] bArr = new byte[this.outputFrameSize];
                next.get(bArr);
                this.videoOutFile.write(bArr);
                nativeFreeNativeByteBuffer(next);
            }
            this.videoOutFile.close();
            Logging.m23185d(TAG, "Video written to disk as " + this.outputFileName + ". Number frames are " + this.rawFrames.size() + " and the dimension of the frames are " + this.outputFileWidth + "x" + this.outputFileHeight + ".");
        } catch (IOException e9) {
            Logging.m23187e(TAG, "Error writing video to disk", e9);
        }
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(final VideoRenderer.I420Frame i420Frame) {
        this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.VideoFileRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                VideoFileRenderer.this.renderFrameOnRenderThread(i420Frame);
            }
        });
    }
}
