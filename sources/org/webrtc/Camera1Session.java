package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;
import org.webrtc.SurfaceTextureHelper;

/* loaded from: classes3.dex */
class Camera1Session implements CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private final Context applicationContext;
    private final Camera camera;
    private final int cameraId;
    private final Handler cameraThreadHandler;
    private final CameraEnumerationAndroid.CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private boolean firstFrameReported = false;
    private final Camera.CameraInfo info;
    private SessionState state;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private static final Histogram camera1StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StartTimeMs", 1, 10000, 50);
    private static final Histogram camera1StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StopTimeMs", 1, 10000, 50);
    private static final Histogram camera1ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera1.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());

    public enum SessionState {
        RUNNING,
        STOPPED
    }

    private Camera1Session(CameraSession.Events events, boolean z8, Context context, SurfaceTextureHelper surfaceTextureHelper, int i9, Camera camera, Camera.CameraInfo cameraInfo, CameraEnumerationAndroid.CaptureFormat captureFormat, long j9) {
        Logging.m23185d(TAG, "Create new camera1 session on camera " + i9);
        this.cameraThreadHandler = new Handler();
        this.events = events;
        this.captureToTexture = z8;
        this.applicationContext = context;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.cameraId = i9;
        this.camera = camera;
        this.info = cameraInfo;
        this.captureFormat = captureFormat;
        this.constructionTimeNs = j9;
        startCapturing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, boolean z8, Context context, SurfaceTextureHelper surfaceTextureHelper, int i9, int i10, int i11, int i12) throws IOException {
        long jNanoTime = System.nanoTime();
        Logging.m23185d(TAG, "Open camera " + i9);
        events.onCameraOpening();
        try {
            Camera cameraOpen = Camera.open(i9);
            try {
                cameraOpen.setPreviewTexture(surfaceTextureHelper.getSurfaceTexture());
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i9, cameraInfo);
                Camera.Parameters parameters = cameraOpen.getParameters();
                CameraEnumerationAndroid.CaptureFormat captureFormatFindClosestCaptureFormat = findClosestCaptureFormat(parameters, i10, i11, i12);
                updateCameraParameters(cameraOpen, parameters, captureFormatFindClosestCaptureFormat, findClosestPictureSize(parameters, i10, i11), z8);
                if (!z8) {
                    int iFrameSize = captureFormatFindClosestCaptureFormat.frameSize();
                    for (int i13 = 0; i13 < 3; i13++) {
                        cameraOpen.addCallbackBuffer(ByteBuffer.allocateDirect(iFrameSize).array());
                    }
                }
                cameraOpen.setDisplayOrientation(0);
                createSessionCallback.onDone(new Camera1Session(events, z8, context, surfaceTextureHelper, i9, cameraOpen, cameraInfo, captureFormatFindClosestCaptureFormat, jNanoTime));
            } catch (IOException e9) {
                cameraOpen.release();
                createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e9.getMessage());
            }
        } catch (RuntimeException e10) {
            createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e10.getMessage());
        }
    }

    private static CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat(Camera.Parameters parameters, int i9, int i10, int i11) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> listConvertFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.m23185d(TAG, "Available fps ranges: " + listConvertFramerates);
        CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(listConvertFramerates, i11);
        Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes()), i9, i10);
        CameraEnumerationAndroid.reportCameraResolution(camera1ResolutionHistogram, closestSupportedSize);
        return new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
    }

    private static Size findClosestPictureSize(Camera.Parameters parameters, int i9, int i10) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), i9, i10);
    }

    private int getDeviceOrientation() {
        int rotation = ((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFrameOrientation() {
        int deviceOrientation = getDeviceOrientation();
        Camera.CameraInfo cameraInfo = this.info;
        if (cameraInfo.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (cameraInfo.orientation + deviceOrientation) % 360;
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() { // from class: org.webrtc.Camera1Session.3
            @Override // android.hardware.Camera.PreviewCallback
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                Camera1Session.this.checkIsOnCameraThread();
                if (camera != Camera1Session.this.camera) {
                    Logging.m23186e(Camera1Session.TAG, "Callback from a different camera. This should never happen.");
                    return;
                }
                if (Camera1Session.this.state != SessionState.RUNNING) {
                    Logging.m23185d(Camera1Session.TAG, "Bytebuffer frame captured but camera is no longer running.");
                    return;
                }
                long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                if (!Camera1Session.this.firstFrameReported) {
                    Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                    Camera1Session.this.firstFrameReported = true;
                }
                CameraSession.Events events = Camera1Session.this.events;
                Camera1Session camera1Session = Camera1Session.this;
                events.onByteBufferFrameCaptured(camera1Session, bArr, camera1Session.captureFormat.width, Camera1Session.this.captureFormat.height, Camera1Session.this.getFrameOrientation(), nanos);
                Camera1Session.this.camera.addCallbackBuffer(bArr);
            }
        });
    }

    private void listenForTextureFrames() {
        this.surfaceTextureHelper.startListening(new SurfaceTextureHelper.OnTextureFrameAvailableListener() { // from class: org.webrtc.Camera1Session.2
            @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
            public void onTextureFrameAvailable(int i9, float[] fArr, long j9) {
                Camera1Session.this.checkIsOnCameraThread();
                if (Camera1Session.this.state != SessionState.RUNNING) {
                    Logging.m23185d(Camera1Session.TAG, "Texture frame captured but camera is no longer running.");
                    Camera1Session.this.surfaceTextureHelper.returnTextureFrame();
                    return;
                }
                if (!Camera1Session.this.firstFrameReported) {
                    Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                    Camera1Session.this.firstFrameReported = true;
                }
                int frameOrientation = Camera1Session.this.getFrameOrientation();
                if (Camera1Session.this.info.facing == 1) {
                    fArr = RendererCommon.multiplyMatrices(fArr, RendererCommon.horizontalFlipMatrix());
                }
                CameraSession.Events events = Camera1Session.this.events;
                Camera1Session camera1Session = Camera1Session.this;
                events.onTextureFrameCaptured(camera1Session, camera1Session.captureFormat.width, Camera1Session.this.captureFormat.height, i9, fArr, frameOrientation, j9);
            }
        });
    }

    private void startCapturing() {
        Logging.m23185d(TAG, "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new Camera.ErrorCallback() { // from class: org.webrtc.Camera1Session.1
            @Override // android.hardware.Camera.ErrorCallback
            public void onError(int i9, Camera camera) {
                String str;
                if (i9 == 100) {
                    str = "Camera server died!";
                } else {
                    str = "Camera error: " + i9;
                }
                Logging.m23186e(Camera1Session.TAG, str);
                Camera1Session.this.stopInternal();
                if (i9 == 2) {
                    Camera1Session.this.events.onCameraDisconnected(Camera1Session.this);
                } else {
                    Camera1Session.this.events.onCameraError(Camera1Session.this, str);
                }
            }
        });
        if (this.captureToTexture) {
            listenForTextureFrames();
        } else {
            listenForBytebufferFrames();
        }
        try {
            this.camera.startPreview();
        } catch (RuntimeException e9) {
            stopInternal();
            this.events.onCameraError(this, e9.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternal() {
        Logging.m23185d(TAG, "Stop internal");
        checkIsOnCameraThread();
        SessionState sessionState = this.state;
        SessionState sessionState2 = SessionState.STOPPED;
        if (sessionState == sessionState2) {
            Logging.m23185d(TAG, "Camera is already stopped");
            return;
        }
        this.state = sessionState2;
        this.surfaceTextureHelper.stopListening();
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        Logging.m23185d(TAG, "Stop done");
    }

    private static void updateCameraParameters(Camera camera, Camera.Parameters parameters, CameraEnumerationAndroid.CaptureFormat captureFormat, Size size, boolean z8) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        CameraEnumerationAndroid.CaptureFormat.FramerateRange framerateRange = captureFormat.framerate;
        parameters.setPreviewFpsRange(framerateRange.min, framerateRange.max);
        parameters.setPreviewSize(captureFormat.width, captureFormat.height);
        parameters.setPictureSize(size.width, size.height);
        if (!z8) {
            parameters.setPreviewFormat(17);
        }
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        camera.setParameters(parameters);
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        Logging.m23185d(TAG, "Stop camera1 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long jNanoTime = System.nanoTime();
            stopInternal();
            camera1StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime));
        }
    }
}
