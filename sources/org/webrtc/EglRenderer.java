package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
import org.webrtc.stats.RenderStatCallback;

/* loaded from: classes3.dex */
public class EglRenderer implements VideoRenderer.Callbacks {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final int MAX_SURFACE_CLEAR_COUNT = 3;
    private static final String TAG = "EglRenderer";
    private GlTextureFrameBuffer bitmapTextureFramebuffer;
    private RendererCommon.GlDrawer drawer;
    private EglBase eglBase;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private float layoutAspectRatio;
    private long minRenderPeriodNs;
    private boolean mirror;
    private final String name;
    private long nextFrameTimeNs;
    private VideoRenderer.I420Frame pendingFrame;
    private RenderStatCallback renderStatCallback;
    private long renderSwapBufferTimeNs;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private long statisticsStartTimeNs;
    private final Object handlerLock = new Object();
    private final ArrayList<FrameListenerAndParams> frameListeners = new ArrayList<>();
    private final Object fpsReductionLock = new Object();
    private final RendererCommon.YuvUploader yuvUploader = new RendererCommon.YuvUploader();
    private int[] yuvTextures = null;
    private final Object frameLock = new Object();
    private final Object layoutLock = new Object();
    private final Object statisticsLock = new Object();
    private final Runnable renderFrameRunnable = new Runnable() { // from class: org.webrtc.EglRenderer.1
        @Override // java.lang.Runnable
        public void run() {
            EglRenderer.this.renderFrameOnRenderThread();
        }
    };
    private final Runnable logStatisticsRunnable = new Runnable() { // from class: org.webrtc.EglRenderer.2
        @Override // java.lang.Runnable
        public void run() {
            EglRenderer.this.logStatistics();
            synchronized (EglRenderer.this.handlerLock) {
                if (EglRenderer.this.renderThreadHandler != null) {
                    EglRenderer.this.renderThreadHandler.removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                    EglRenderer.this.renderThreadHandler.postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4L));
                }
            }
        }
    };
    private final EglSurfaceCreation eglSurfaceCreationRunnable = new EglSurfaceCreation();

    public class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (this.surface != null && EglRenderer.this.eglBase != null && !EglRenderer.this.eglBase.hasSurface()) {
                Object obj = this.surface;
                if (obj instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else {
                    if (!(obj instanceof SurfaceTexture)) {
                        throw new IllegalStateException("Invalid surface: " + this.surface);
                    }
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }

        public synchronized void setSurface(Object obj) {
            this.surface = obj;
        }
    }

    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    public static class FrameListenerAndParams {
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener frameListener, float f9, RendererCommon.GlDrawer glDrawer) {
            this.listener = frameListener;
            this.scale = f9;
            this.drawer = glDrawer;
        }
    }

    public EglRenderer(String str) {
        this.name = str;
    }

    private String averageTimeAsString(long j9, int i9) {
        if (i9 <= 0) {
            return "NA";
        }
        return TimeUnit.NANOSECONDS.toMicros(j9 / i9) + " μs";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSurfaceOnRenderThread() {
        EglBase eglBase = this.eglBase;
        if (eglBase == null || !eglBase.hasSurface()) {
            return;
        }
        logD("clearSurface");
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        GLES20.glClear(16384);
        this.eglBase.swapBuffers();
    }

    private void createEglSurfaceInternal(Object obj) {
        this.eglSurfaceCreationRunnable.setSurface(obj);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logD(String str) {
        Logging.m23185d(TAG, this.name + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logStatistics() {
        long jNanoTime = System.nanoTime();
        synchronized (this.statisticsLock) {
            long j9 = jNanoTime - this.statisticsStartTimeNs;
            if (j9 <= 0) {
                return;
            }
            float nanos = (this.framesRendered * TimeUnit.SECONDS.toNanos(1L)) / j9;
            RenderStatCallback renderStatCallback = this.renderStatCallback;
            if (renderStatCallback != null) {
                renderStatCallback.onStat(4L, this.framesReceived, this.framesDropped, this.framesRendered, nanos);
            }
            resetStatistics(jNanoTime);
        }
    }

    private void notifyCallbacks(VideoRenderer.I420Frame i420Frame, float[] fArr) {
        int i9;
        EglRenderer eglRenderer = this;
        if (eglRenderer.frameListeners.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(eglRenderer.frameListeners);
        eglRenderer.frameListeners.clear();
        float[] fArrMultiplyMatrices = RendererCommon.multiplyMatrices(RendererCommon.multiplyMatrices(fArr, eglRenderer.mirror ? RendererCommon.horizontalFlipMatrix() : RendererCommon.identityMatrix()), RendererCommon.verticalFlipMatrix());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FrameListenerAndParams frameListenerAndParams = (FrameListenerAndParams) it.next();
            int iRotatedWidth = (int) (frameListenerAndParams.scale * i420Frame.rotatedWidth());
            int iRotatedHeight = (int) (frameListenerAndParams.scale * i420Frame.rotatedHeight());
            if (iRotatedWidth == 0 || iRotatedHeight == 0) {
                frameListenerAndParams.listener.onFrame(null);
            } else {
                if (eglRenderer.bitmapTextureFramebuffer == null) {
                    eglRenderer.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
                }
                eglRenderer.bitmapTextureFramebuffer.setSize(iRotatedWidth, iRotatedHeight);
                GLES20.glBindFramebuffer(36160, eglRenderer.bitmapTextureFramebuffer.getFrameBufferId());
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, eglRenderer.bitmapTextureFramebuffer.getTextureId(), 0);
                GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                GLES20.glClear(16384);
                if (i420Frame.yuvFrame) {
                    i9 = 36160;
                    frameListenerAndParams.drawer.drawYuv(eglRenderer.yuvTextures, fArrMultiplyMatrices, i420Frame.rotatedWidth(), i420Frame.rotatedHeight(), 0, 0, iRotatedWidth, iRotatedHeight);
                } else {
                    i9 = 36160;
                    frameListenerAndParams.drawer.drawOes(i420Frame.textureId, fArrMultiplyMatrices, i420Frame.rotatedWidth(), i420Frame.rotatedHeight(), 0, 0, iRotatedWidth, iRotatedHeight);
                }
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iRotatedWidth * iRotatedHeight * 4);
                GLES20.glViewport(0, 0, iRotatedWidth, iRotatedHeight);
                GLES20.glReadPixels(0, 0, iRotatedWidth, iRotatedHeight, 6408, 5121, byteBufferAllocateDirect);
                GLES20.glBindFramebuffer(i9, 0);
                GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRotatedWidth, iRotatedHeight, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferAllocateDirect);
                frameListenerAndParams.listener.onFrame(bitmapCreateBitmap);
            }
            eglRenderer = this;
        }
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            if (handler != null) {
                handler.post(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderFrameOnRenderThread() {
        float[] fArrHorizontalFlipMatrix;
        int iRotatedWidth;
        int iRotatedHeight;
        synchronized (this.frameLock) {
            VideoRenderer.I420Frame i420Frame = this.pendingFrame;
            if (i420Frame == null) {
                return;
            }
            this.pendingFrame = null;
            EglBase eglBase = this.eglBase;
            if (eglBase == null || !eglBase.hasSurface()) {
                VideoRenderer.renderFrameDone(i420Frame);
                return;
            }
            long jNanoTime = System.nanoTime();
            float[] fArrRotateTextureMatrix = RendererCommon.rotateTextureMatrix(i420Frame.samplingMatrix, i420Frame.rotationDegree);
            synchronized (this.layoutLock) {
                if (this.layoutAspectRatio > BitmapDescriptorFactory.HUE_RED) {
                    float fRotatedWidth = i420Frame.rotatedWidth() / i420Frame.rotatedHeight();
                    if (Math.abs(fRotatedWidth - this.layoutAspectRatio) > 1.0f) {
                        logD("Frame dropped cause of unexpected layout aspect ratio: " + this.layoutAspectRatio + ", expected: " + fRotatedWidth);
                        VideoRenderer.renderFrameDone(i420Frame);
                        return;
                    }
                    fArrHorizontalFlipMatrix = RendererCommon.getLayoutMatrix(this.mirror, fRotatedWidth, this.layoutAspectRatio);
                    if (fRotatedWidth > this.layoutAspectRatio) {
                        iRotatedWidth = (int) (i420Frame.rotatedHeight() * this.layoutAspectRatio);
                        iRotatedHeight = i420Frame.rotatedHeight();
                    } else {
                        iRotatedWidth = i420Frame.rotatedWidth();
                        iRotatedHeight = (int) (i420Frame.rotatedWidth() / this.layoutAspectRatio);
                    }
                } else {
                    fArrHorizontalFlipMatrix = this.mirror ? RendererCommon.horizontalFlipMatrix() : RendererCommon.identityMatrix();
                    iRotatedWidth = i420Frame.rotatedWidth();
                    iRotatedHeight = i420Frame.rotatedHeight();
                }
                int i9 = iRotatedWidth;
                int i10 = iRotatedHeight;
                float[] fArrMultiplyMatrices = RendererCommon.multiplyMatrices(fArrRotateTextureMatrix, fArrHorizontalFlipMatrix);
                GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                GLES20.glClear(16384);
                if (i420Frame.yuvFrame) {
                    if (this.yuvTextures == null) {
                        this.yuvTextures = new int[3];
                        for (int i11 = 0; i11 < 3; i11++) {
                            this.yuvTextures[i11] = GlUtil.generateTexture(3553);
                        }
                    }
                    this.yuvUploader.uploadYuvData(this.yuvTextures, i420Frame.width, i420Frame.height, i420Frame.yuvStrides, i420Frame.yuvPlanes);
                    this.drawer.drawYuv(this.yuvTextures, fArrMultiplyMatrices, i9, i10, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight());
                } else {
                    this.drawer.drawOes(i420Frame.textureId, fArrMultiplyMatrices, i9, i10, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight());
                }
                long jNanoTime2 = System.nanoTime();
                this.eglBase.swapBuffers();
                long jNanoTime3 = System.nanoTime();
                synchronized (this.statisticsLock) {
                    this.framesRendered++;
                    this.renderTimeNs += jNanoTime3 - jNanoTime;
                    this.renderSwapBufferTimeNs += jNanoTime3 - jNanoTime2;
                }
                notifyCallbacks(i420Frame, fArrRotateTextureMatrix);
                VideoRenderer.renderFrameDone(i420Frame);
            }
        }
    }

    private void resetStatistics(long j9) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = j9;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0L;
            this.renderSwapBufferTimeNs = 0L;
        }
    }

    public void addFrameListener(final FrameListener frameListener, final float f9) {
        postToRenderThread(new Runnable() { // from class: org.webrtc.EglRenderer.6
            @Override // java.lang.Runnable
            public void run() {
                EglRenderer.this.frameListeners.add(new FrameListenerAndParams(frameListener, f9, EglRenderer.this.drawer));
            }
        });
    }

    public void clearImage() {
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            if (handler == null) {
                return;
            }
            handler.postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer.10
                @Override // java.lang.Runnable
                public void run() {
                    EglRenderer.this.clearSurfaceOnRenderThread();
                }
            });
        }
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void init(final EglBase.Context context, final int[] iArr, RendererCommon.GlDrawer glDrawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                throw new IllegalStateException(this.name + "Already initialized");
            }
            logD("Initializing EglRenderer");
            this.drawer = glDrawer;
            HandlerThread handlerThread = new HandlerThread(this.name + TAG);
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            this.renderThreadHandler = handler;
            ThreadUtils.invokeAtFrontUninterruptibly(handler, new Runnable() { // from class: org.webrtc.EglRenderer.3
                @Override // java.lang.Runnable
                public void run() {
                    if (context == null) {
                        EglRenderer.this.logD("EglBase10.create context");
                        EglRenderer.this.eglBase = new EglBase10(null, iArr);
                    } else {
                        EglRenderer.this.logD("EglBase.create shared context");
                        EglRenderer.this.eglBase = EglBase.create(context, iArr);
                    }
                }
            });
            this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);
            resetStatistics(System.nanoTime());
            this.renderThreadHandler.postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4L));
        }
    }

    public void pauseVideo() {
        setFpsReduction(BitmapDescriptorFactory.HUE_RED);
    }

    public void printStackTrace() {
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            Thread thread = handler == null ? null : handler.getLooper().getThread();
            if (thread != null) {
                StackTraceElement[] stackTrace = thread.getStackTrace();
                if (stackTrace.length > 0) {
                    logD("EglRenderer stack trace:");
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        logD(stackTraceElement.toString());
                    }
                }
            }
        }
    }

    public void release() {
        logD("Releasing.");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            if (handler == null) {
                logD("Already released");
                return;
            }
            handler.removeCallbacks(this.logStatisticsRunnable);
            this.renderThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer.4
                @Override // java.lang.Runnable
                public void run() {
                    if (EglRenderer.this.drawer != null) {
                        EglRenderer.this.drawer.release();
                        EglRenderer.this.drawer = null;
                    }
                    if (EglRenderer.this.yuvTextures != null) {
                        GLES20.glDeleteTextures(3, EglRenderer.this.yuvTextures, 0);
                        EglRenderer.this.yuvTextures = null;
                    }
                    if (EglRenderer.this.bitmapTextureFramebuffer != null) {
                        EglRenderer.this.bitmapTextureFramebuffer.release();
                        EglRenderer.this.bitmapTextureFramebuffer = null;
                    }
                    if (EglRenderer.this.eglBase != null) {
                        EglRenderer.this.logD("eglBase detach and release.");
                        EglRenderer.this.eglBase.detachCurrent();
                        EglRenderer.this.eglBase.release();
                        EglRenderer.this.eglBase = null;
                    }
                    countDownLatch.countDown();
                    EglRenderer.this.renderStatCallback = null;
                }
            });
            final Looper looper = this.renderThreadHandler.getLooper();
            this.renderThreadHandler.post(new Runnable() { // from class: org.webrtc.EglRenderer.5
                @Override // java.lang.Runnable
                public void run() {
                    EglRenderer.this.logD("Quitting render thread.");
                    looper.quit();
                }
            });
            this.renderThreadHandler = null;
            ThreadUtils.awaitUninterruptibly(countDownLatch);
            synchronized (this.frameLock) {
                VideoRenderer.I420Frame i420Frame = this.pendingFrame;
                if (i420Frame != null) {
                    VideoRenderer.renderFrameDone(i420Frame);
                    this.pendingFrame = null;
                }
            }
            logD("Releasing done.");
        }
    }

    public void releaseEglSurface(final Runnable runnable) {
        this.eglSurfaceCreationRunnable.setSurface(null);
        synchronized (this.handlerLock) {
            Handler handler = this.renderThreadHandler;
            if (handler == null) {
                runnable.run();
            } else {
                handler.removeCallbacks(this.eglSurfaceCreationRunnable);
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: org.webrtc.EglRenderer.9
                    @Override // java.lang.Runnable
                    public void run() {
                        if (EglRenderer.this.eglBase != null) {
                            EglRenderer.this.eglBase.detachCurrent();
                            EglRenderer.this.eglBase.releaseSurface();
                        }
                        runnable.run();
                    }
                });
            }
        }
    }

    public void removeFrameListener(final FrameListener frameListener) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        postToRenderThread(new Runnable() { // from class: org.webrtc.EglRenderer.8
            @Override // java.lang.Runnable
            public void run() {
                countDownLatch.countDown();
                Iterator it = EglRenderer.this.frameListeners.iterator();
                while (it.hasNext()) {
                    if (((FrameListenerAndParams) it.next()).listener == frameListener) {
                        it.remove();
                    }
                }
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        boolean z8;
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                logD("Dropping frame - Not initialized or already released.");
                VideoRenderer.renderFrameDone(i420Frame);
                return;
            }
            synchronized (this.fpsReductionLock) {
                if (this.minRenderPeriodNs > 0) {
                    long jNanoTime = System.nanoTime();
                    long j9 = this.nextFrameTimeNs;
                    if (jNanoTime < j9) {
                        logD("Dropping frame - fps reduction is active.");
                        VideoRenderer.renderFrameDone(i420Frame);
                        return;
                    } else {
                        long j10 = j9 + this.minRenderPeriodNs;
                        this.nextFrameTimeNs = j10;
                        this.nextFrameTimeNs = Math.max(j10, jNanoTime);
                    }
                }
                synchronized (this.frameLock) {
                    VideoRenderer.I420Frame i420Frame2 = this.pendingFrame;
                    z8 = i420Frame2 != null;
                    if (z8) {
                        VideoRenderer.renderFrameDone(i420Frame2);
                    }
                    this.pendingFrame = i420Frame;
                    this.renderThreadHandler.post(this.renderFrameRunnable);
                }
                if (z8) {
                    synchronized (this.statisticsLock) {
                        this.framesDropped++;
                    }
                }
            }
        }
    }

    public void setFpsReduction(float f9) {
        logD("setFpsReduction: " + f9);
        synchronized (this.fpsReductionLock) {
            long j9 = this.minRenderPeriodNs;
            if (f9 <= BitmapDescriptorFactory.HUE_RED) {
                this.minRenderPeriodNs = Long.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = (long) (TimeUnit.SECONDS.toNanos(1L) / f9);
            }
            if (this.minRenderPeriodNs != j9) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void setLayoutAspectRatio(float f9) {
        if (this.layoutAspectRatio == f9) {
            return;
        }
        logD("setLayoutAspectRatio: " + f9);
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = f9;
        }
    }

    public void setMirror(boolean z8) {
        logD("setMirror: " + z8);
        synchronized (this.layoutLock) {
            this.mirror = z8;
        }
    }

    public void setRenderStatCallback(RenderStatCallback renderStatCallback) {
        this.renderStatCallback = renderStatCallback;
    }

    public void addFrameListener(final FrameListener frameListener, final float f9, final RendererCommon.GlDrawer glDrawer) {
        postToRenderThread(new Runnable() { // from class: org.webrtc.EglRenderer.7
            @Override // java.lang.Runnable
            public void run() {
                EglRenderer.this.frameListeners.add(new FrameListenerAndParams(frameListener, f9, glDrawer));
            }
        });
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }
}
