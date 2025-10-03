package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCamera;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 675;
    private static final int MAX_FRAME_WIDTH = 1200;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    private static final String TAG = "CameraManager";
    private AutoFocusManager autoFocusManager;
    private OpenCamera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private final PreviewCallback previewCallback;
    private boolean previewing;
    private int requestedCameraId = -1;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;

    public CameraManager(Context context) {
        this.context = context;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context);
        this.configManager = cameraConfigurationManager;
        this.previewCallback = new PreviewCallback(cameraConfigurationManager);
    }

    private static int findDesiredDimensionInRange(int i9, int i10, int i11) {
        int i12 = (i9 * 5) / 8;
        return i12 < i10 ? i10 : i12 > i11 ? i11 : i12;
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i9, int i10) {
        Rect framingRectInPreview = getFramingRectInPreview();
        if (framingRectInPreview == null) {
            return null;
        }
        return new PlanarYUVLuminanceSource(bArr, i9, i10, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height(), false);
    }

    public synchronized void closeDriver() {
        OpenCamera openCamera = this.camera;
        if (openCamera != null) {
            openCamera.getCamera().release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public synchronized Rect getFramingRect() {
        if (this.framingRect == null) {
            if (this.camera == null) {
                return null;
            }
            Point screenResolution = this.configManager.getScreenResolution();
            if (screenResolution == null) {
                return null;
            }
            int iFindDesiredDimensionInRange = findDesiredDimensionInRange(screenResolution.x, PsExtractor.VIDEO_STREAM_MASK, MAX_FRAME_WIDTH);
            int iFindDesiredDimensionInRange2 = findDesiredDimensionInRange(screenResolution.y, PsExtractor.VIDEO_STREAM_MASK, MAX_FRAME_HEIGHT);
            int i9 = (screenResolution.x - iFindDesiredDimensionInRange) / 2;
            int i10 = (screenResolution.y - iFindDesiredDimensionInRange2) / 2;
            this.framingRect = new Rect(i9, i10, iFindDesiredDimensionInRange + i9, iFindDesiredDimensionInRange2 + i10);
            Log.d(TAG, "Calculated framing rect: " + this.framingRect);
        }
        return this.framingRect;
    }

    public synchronized Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect framingRect = getFramingRect();
            if (framingRect == null) {
                return null;
            }
            Rect rect = new Rect(framingRect);
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            if (cameraResolution != null && screenResolution != null) {
                int i9 = rect.left;
                int i10 = cameraResolution.x;
                int i11 = screenResolution.x;
                rect.left = (i9 * i10) / i11;
                rect.right = (rect.right * i10) / i11;
                int i12 = rect.top;
                int i13 = cameraResolution.y;
                int i14 = screenResolution.y;
                rect.top = (i12 * i13) / i14;
                rect.bottom = (rect.bottom * i13) / i14;
                this.framingRectInPreview = rect;
            }
            return null;
        }
        return this.framingRectInPreview;
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void openDriver(SurfaceHolder surfaceHolder) {
        int i9;
        OpenCamera openCameraOpen = this.camera;
        if (openCameraOpen == null) {
            openCameraOpen = OpenCameraInterface.open(this.requestedCameraId);
            if (openCameraOpen == null) {
                throw new IOException("Camera.open() failed to return object from driver");
            }
            this.camera = openCameraOpen;
        }
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(openCameraOpen);
            int i10 = this.requestedFramingRectWidth;
            if (i10 > 0 && (i9 = this.requestedFramingRectHeight) > 0) {
                setManualFramingRect(i10, i9);
                this.requestedFramingRectWidth = 0;
                this.requestedFramingRectHeight = 0;
            }
        }
        Camera camera = openCameraOpen.getCamera();
        Camera.Parameters parameters = camera.getParameters();
        String strFlatten = parameters == null ? null : parameters.flatten();
        try {
            this.configManager.setDesiredCameraParameters(openCameraOpen, false);
        } catch (RuntimeException unused) {
            String str = TAG;
            Log.w(str, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(str, "Resetting to saved camera params: " + strFlatten);
            if (strFlatten != null) {
                Camera.Parameters parameters2 = camera.getParameters();
                parameters2.unflatten(strFlatten);
                try {
                    camera.setParameters(parameters2);
                    this.configManager.setDesiredCameraParameters(openCameraOpen, true);
                } catch (RuntimeException unused2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
        camera.setPreviewDisplay(surfaceHolder);
    }

    public synchronized void requestPreviewFrame(Handler handler, int i9) {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && this.previewing) {
            this.previewCallback.setHandler(handler, i9);
            openCamera.getCamera().setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized void setManualCameraId(int i9) {
        this.requestedCameraId = i9;
    }

    public synchronized void setManualFramingRect(int i9, int i10) {
        if (this.initialized) {
            Point screenResolution = this.configManager.getScreenResolution();
            int i11 = screenResolution.x;
            if (i9 > i11) {
                i9 = i11;
            }
            int i12 = screenResolution.y;
            if (i10 > i12) {
                i10 = i12;
            }
            int i13 = (i11 - i9) / 2;
            int i14 = (i12 - i10) / 2;
            this.framingRect = new Rect(i13, i14, i9 + i13, i10 + i14);
            Log.d(TAG, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = i9;
            this.requestedFramingRectHeight = i10;
        }
    }

    public synchronized void setTorch(boolean z8) {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && z8 != this.configManager.getTorchState(openCamera.getCamera())) {
            AutoFocusManager autoFocusManager = this.autoFocusManager;
            boolean z9 = autoFocusManager != null;
            if (z9) {
                autoFocusManager.stop();
                this.autoFocusManager = null;
            }
            this.configManager.setTorch(openCamera.getCamera(), z8);
            if (z9) {
                AutoFocusManager autoFocusManager2 = new AutoFocusManager(this.context, openCamera.getCamera());
                this.autoFocusManager = autoFocusManager2;
                autoFocusManager2.start();
            }
        }
    }

    public synchronized void startPreview() {
        OpenCamera openCamera = this.camera;
        if (openCamera != null && !this.previewing) {
            openCamera.getCamera().startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, openCamera.getCamera());
        }
    }

    public synchronized void stopPreview() {
        AutoFocusManager autoFocusManager = this.autoFocusManager;
        if (autoFocusManager != null) {
            autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        OpenCamera openCamera = this.camera;
        if (openCamera != null && this.previewing) {
            openCamera.getCamera().stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }
}
