package org.webrtc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
import org.webrtc.stats.RenderStatCallback;

/* loaded from: classes3.dex */
public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback, VideoRenderer.Callbacks {
    private static final String TAG = "SurfaceViewRenderer";
    private final EglRenderer eglRenderer;
    private boolean enableFixedSize;
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private final Object layoutLock;
    private Point measureSize;
    private RendererCommon.RendererEvents rendererEvents;
    private final String resourceName;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure;

    public SurfaceViewRenderer(Context context) {
        super(context);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        this.layoutLock = new Object();
        this.measureSize = new Point(0, 0);
        String resourceName = getResourceName();
        this.resourceName = resourceName;
        this.eglRenderer = new EglRenderer(resourceName);
        getHolder().addCallback(this);
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private void logD(String str) {
        Logging.m23185d(TAG, this.resourceName + str);
    }

    private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame i420Frame) {
        synchronized (this.layoutLock) {
            boolean z8 = true;
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                logD("Reporting first rendered frame.");
                RendererCommon.RendererEvents rendererEvents = this.rendererEvents;
                if (rendererEvents != null) {
                    rendererEvents.onFirstFrameRendered();
                }
            }
            if (this.rotatedFrameWidth == i420Frame.rotatedWidth() && this.rotatedFrameHeight == i420Frame.rotatedHeight()) {
                z8 = false;
            }
            if (z8 || this.frameRotation != i420Frame.rotationDegree) {
                logD("Reporting frame resolution changed to " + i420Frame.width + "x" + i420Frame.height + " with rotation " + i420Frame.rotationDegree);
                RendererCommon.RendererEvents rendererEvents2 = this.rendererEvents;
                if (rendererEvents2 != null) {
                    rendererEvents2.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.rotatedFrameWidth = i420Frame.rotatedWidth();
                this.rotatedFrameHeight = i420Frame.rotatedHeight();
                this.frameRotation = i420Frame.rotationDegree;
                if (z8) {
                    post(new Runnable() { // from class: org.webrtc.SurfaceViewRenderer.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SurfaceViewRenderer.this.requestLayout();
                        }
                    });
                }
            }
        }
    }

    private void updateSurfaceSize() {
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            if (!this.enableFixedSize || this.rotatedFrameWidth == 0 || this.rotatedFrameHeight == 0 || getWidth() == 0 || getHeight() == 0) {
                this.surfaceHeight = 0;
                this.surfaceWidth = 0;
                getHolder().setSizeFromLayout();
            } else {
                float width = getWidth() / getHeight();
                int i9 = this.rotatedFrameWidth;
                int i10 = this.rotatedFrameHeight;
                if (i9 / i10 > width) {
                    i9 = (int) (i10 * width);
                } else {
                    i10 = (int) (i9 / width);
                }
                int iMin = Math.min(getWidth(), i9);
                int iMin2 = Math.min(getHeight(), i10);
                if (iMin != this.surfaceWidth || iMin2 != this.surfaceHeight) {
                    logD("updateSurfaceSize. Layout size: " + getWidth() + "x" + getHeight() + ", frame size: " + this.rotatedFrameWidth + "x" + this.rotatedFrameHeight + ", requested surface size: " + iMin + "x" + iMin2 + ", old surface size: " + this.surfaceWidth + "x" + this.surfaceHeight);
                    this.surfaceWidth = iMin;
                    this.surfaceHeight = iMin2;
                    getHolder().setFixedSize(iMin, iMin2);
                }
            }
        }
    }

    public void addFrameListener(EglRenderer.FrameListener frameListener, float f9, RendererCommon.GlDrawer glDrawer) {
        this.eglRenderer.addFrameListener(frameListener, f9, glDrawer);
    }

    public void clearImage() {
        this.eglRenderer.clearImage();
    }

    public void disableFpsReduction() {
        this.eglRenderer.disableFpsReduction();
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents) {
        init(context, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    @Override // android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.setLayoutAspectRatio((i11 - i9) / (i12 - i10));
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int i9, int i10) {
        Point pointMeasure;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            pointMeasure = this.videoLayoutMeasure.measure(i9, i10, this.rotatedFrameWidth, this.rotatedFrameHeight);
        }
        setMeasuredDimension(pointMeasure.x, pointMeasure.y);
        if (pointMeasure.equals(this.measureSize)) {
            return;
        }
        logD("onMeasure(). New size: " + pointMeasure.x + "x" + pointMeasure.y);
        this.measureSize = pointMeasure;
    }

    public void pauseVideo() {
        this.eglRenderer.pauseVideo();
    }

    public void release() {
        this.eglRenderer.release();
    }

    public void removeFrameListener(EglRenderer.FrameListener frameListener) {
        this.eglRenderer.removeFrameListener(frameListener);
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        updateFrameDimensionsAndReportEvents(i420Frame);
        this.eglRenderer.renderFrame(i420Frame);
    }

    public void setEnableHardwareScaler(boolean z8) {
        ThreadUtils.checkIsOnMainThread();
        this.enableFixedSize = z8;
        updateSurfaceSize();
    }

    public void setFpsReduction(float f9) {
        this.eglRenderer.setFpsReduction(f9);
    }

    public void setMirror(boolean z8) {
        this.eglRenderer.setMirror(z8);
    }

    public void setRenderStatCallback(RenderStatCallback renderStatCallback) {
        this.eglRenderer.setRenderStatCallback(renderStatCallback);
    }

    public void setScalingType(RendererCommon.ScalingType scalingType) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingType);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i9, int i10, int i11) {
        ThreadUtils.checkIsOnMainThread();
        logD("surfaceChanged: format: " + i9 + " size: " + i10 + "x" + i11);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.createEglSurface(surfaceHolder.getSurface());
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        ThreadUtils.checkIsOnMainThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.eglRenderer.releaseEglSurface(new Runnable() { // from class: org.webrtc.SurfaceViewRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                countDownLatch.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
    }

    public void addFrameListener(EglRenderer.FrameListener frameListener, float f9) {
        this.eglRenderer.addFrameListener(frameListener, f9);
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = rendererEvents;
        synchronized (this.layoutLock) {
            this.rotatedFrameWidth = 0;
            this.rotatedFrameHeight = 0;
            this.frameRotation = 0;
        }
        this.eglRenderer.init(context, iArr, glDrawer);
    }

    public void setScalingType(RendererCommon.ScalingType scalingType, RendererCommon.ScalingType scalingType2) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingType, scalingType2);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        this.layoutLock = new Object();
        this.measureSize = new Point(0, 0);
        String resourceName = getResourceName();
        this.resourceName = resourceName;
        this.eglRenderer = new EglRenderer(resourceName);
        getHolder().addCallback(this);
    }
}
