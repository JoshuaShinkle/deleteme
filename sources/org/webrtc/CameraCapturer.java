package org.webrtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Arrays;
import org.webrtc.CameraSession;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;

/* loaded from: classes3.dex */
abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    private Context applicationContext;
    private final CameraEnumerator cameraEnumerator;
    private String cameraName;
    private CameraVideoCapturer.CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    private VideoCapturer.CapturerObserver capturerObserver;
    private CameraSession currentSession;
    private final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    private boolean firstFrameObserved;
    private int framerate;
    private int height;
    private int openAttemptsRemaining;
    private boolean sessionOpening;
    private SurfaceTextureHelper surfaceHelper;
    private CameraVideoCapturer.CameraSwitchHandler switchEventsHandler;
    private final Handler uiThreadHandler;
    private int width;
    private final CameraSession.CreateSessionCallback createSessionCallback = new CameraSession.CreateSessionCallback() { // from class: org.webrtc.CameraCapturer.1
        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onDone(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.m23185d(CameraCapturer.TAG, "Create session done");
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                CameraCapturer.this.sessionOpening = false;
                CameraCapturer.this.currentSession = cameraSession;
                CameraCapturer cameraCapturer = CameraCapturer.this;
                cameraCapturer.cameraStatistics = new CameraVideoCapturer.CameraStatistics(cameraCapturer.surfaceHelper, CameraCapturer.this.eventsHandler);
                CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState == SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchEventsHandler != null) {
                        CameraCapturer.this.switchEventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                        CameraCapturer.this.switchEventsHandler = null;
                    }
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                } else if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer cameraCapturer2 = CameraCapturer.this;
                    cameraCapturer2.switchCameraInternal(cameraCapturer2.switchEventsHandler);
                }
            }
        }

        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onFailure(CameraSession.FailureType failureType, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.access$1610(CameraCapturer.this);
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.m23189w(CameraCapturer.TAG, "Opening camera failed, passing: " + str);
                    CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    SwitchState switchState = CameraCapturer.this.switchState;
                    SwitchState switchState2 = SwitchState.IDLE;
                    if (switchState != switchState2) {
                        if (CameraCapturer.this.switchEventsHandler != null) {
                            CameraCapturer.this.switchEventsHandler.onCameraSwitchError(str);
                            CameraCapturer.this.switchEventsHandler = null;
                        }
                        CameraCapturer.this.switchState = switchState2;
                    }
                    if (failureType == CameraSession.FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(str);
                    }
                } else {
                    Logging.m23189w(CameraCapturer.TAG, "Opening camera failed, retry: " + str);
                    CameraCapturer.this.createSessionInternal(500);
                }
            }
        }
    };
    private final CameraSession.Events cameraSessionEventsHandler = new CameraSession.Events() { // from class: org.webrtc.CameraCapturer.2
        @Override // org.webrtc.CameraSession.Events
        public void onByteBufferFrameCaptured(CameraSession cameraSession, byte[] bArr, int i9, int i10, int i11, long j9) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.m23189w(CameraCapturer.TAG, "onByteBufferFrameCaptured from another session.");
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onByteBufferFrameCaptured(bArr, i9, i10, i11, j9);
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraClosed(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                } else {
                    Logging.m23185d(CameraCapturer.TAG, "onCameraClosed from another session.");
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraDisconnected(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.m23189w(CameraCapturer.TAG, "onCameraDisconnected from another session.");
                } else {
                    CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    CameraCapturer.this.stopCapture();
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraError(CameraSession cameraSession, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    CameraCapturer.this.eventsHandler.onCameraError(str);
                    CameraCapturer.this.stopCapture();
                } else {
                    Logging.m23189w(CameraCapturer.TAG, "onCameraError from another session: " + str);
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession != null) {
                    Logging.m23189w(CameraCapturer.TAG, "onCameraOpening while session was open.");
                } else {
                    CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName);
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onTextureFrameCaptured(CameraSession cameraSession, int i9, int i10, int i11, float[] fArr, int i12, long j9) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession != CameraCapturer.this.currentSession) {
                    Logging.m23189w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
                    CameraCapturer.this.surfaceHelper.returnTextureFrame();
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onTextureFrameCaptured(i9, i10, i11, fArr, i12, j9);
            }
        }
    };
    private final Runnable openCameraTimeoutRunnable = new Runnable() { // from class: org.webrtc.CameraCapturer.3
        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    };
    private final Object stateLock = new Object();
    private SwitchState switchState = SwitchState.IDLE;

    public enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    public CameraCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, CameraEnumerator cameraEnumerator) {
        this.eventsHandler = cameraEventsHandler == null ? new CameraVideoCapturer.CameraEventsHandler() { // from class: org.webrtc.CameraCapturer.4
            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
            }
        } : cameraEventsHandler;
        this.cameraEnumerator = cameraEnumerator;
        this.cameraName = str;
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        if (deviceNames.length == 0) {
            throw new RuntimeException("No cameras attached.");
        }
        if (Arrays.asList(deviceNames).contains(this.cameraName)) {
            return;
        }
        throw new IllegalArgumentException("Camera name " + this.cameraName + " does not match any known camera device.");
    }

    public static /* synthetic */ int access$1610(CameraCapturer cameraCapturer) {
        int i9 = cameraCapturer.openAttemptsRemaining;
        cameraCapturer.openAttemptsRemaining = i9 - 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            return;
        }
        Logging.m23186e(TAG, "Check is on camera thread failed.");
        throw new RuntimeException("Not on camera thread.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSessionInternal(int i9) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, i9 + 10000);
        this.cameraThreadHandler.postDelayed(new Runnable() { // from class: org.webrtc.CameraCapturer.5
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer cameraCapturer = CameraCapturer.this;
                cameraCapturer.createCameraSession(cameraCapturer.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceHelper, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
            }
        }, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraInternal(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.m23185d(TAG, "switchCamera internal");
        if (this.cameraEnumerator.getDeviceNames().length < 2) {
            if (cameraSwitchHandler != null) {
                cameraSwitchHandler.onCameraSwitchError("No camera to switch to.");
                return;
            }
            return;
        }
        synchronized (this.stateLock) {
            if (this.switchState != SwitchState.IDLE) {
                Logging.m23185d(TAG, "switchCamera switchInProgress");
                if (cameraSwitchHandler != null) {
                    cameraSwitchHandler.onCameraSwitchError("Camera switch already in progress.");
                }
                return;
            }
            boolean z8 = this.sessionOpening;
            if (!z8 && this.currentSession == null) {
                Logging.m23185d(TAG, "switchCamera: No session open");
                if (cameraSwitchHandler != null) {
                    cameraSwitchHandler.onCameraSwitchError("Camera is not running.");
                }
                return;
            }
            this.switchEventsHandler = cameraSwitchHandler;
            if (z8) {
                this.switchState = SwitchState.PENDING;
                return;
            }
            this.switchState = SwitchState.IN_PROGRESS;
            Logging.m23185d(TAG, "switchCamera: Stopping session");
            this.cameraStatistics.release();
            this.cameraStatistics = null;
            final CameraSession cameraSession = this.currentSession;
            this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.8
                @Override // java.lang.Runnable
                public void run() {
                    cameraSession.stop();
                }
            });
            this.currentSession = null;
            boolean zIsFrontFacing = this.cameraEnumerator.isFrontFacing(this.cameraName);
            String back = zIsFrontFacing ? this.cameraEnumerator.getBack() : this.cameraEnumerator.getFront();
            if (!TextUtils.isEmpty(back)) {
                this.cameraName = back;
            } else if (zIsFrontFacing) {
                cameraSwitchHandler.onCameraSwitchError("No back camera can switch to.");
            } else {
                cameraSwitchHandler.onCameraSwitchError("No front camera can switch to.");
            }
            this.sessionOpening = true;
            this.openAttemptsRemaining = 1;
            createSessionInternal(0);
            Logging.m23185d(TAG, "switchCamera done");
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i9, int i10, int i11) {
        Logging.m23185d(TAG, "changeCaptureFormat: " + i9 + "x" + i10 + "@" + i11);
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(i9, i10, i11);
        }
    }

    public abstract void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, String str, int i9, int i10, int i11);

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        Logging.m23185d(TAG, "dispose");
        stopCapture();
        SurfaceTextureHelper surfaceTextureHelper = this.surfaceHelper;
        if (surfaceTextureHelper != null) {
            surfaceTextureHelper.dispose();
            this.surfaceHelper = null;
        }
    }

    public String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.applicationContext = context;
        this.capturerObserver = capturerObserver;
        this.surfaceHelper = surfaceTextureHelper;
        this.cameraThreadHandler = surfaceTextureHelper == null ? null : surfaceTextureHelper.getHandler();
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    public void printStackTrace() {
        Handler handler = this.cameraThreadHandler;
        Thread thread = handler != null ? handler.getLooper().getThread() : null;
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.m23185d(TAG, "CameraCapturer stack trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.m23185d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void resetFormat(int i9, int i10, int i11) {
        if (this.sessionOpening) {
            return;
        }
        this.width = i9;
        this.height = i10;
        this.framerate = i11;
    }

    @Override // org.webrtc.VideoCapturer
    public void resumeCapture() {
        synchronized (this.stateLock) {
            if (!this.sessionOpening && this.currentSession == null) {
                this.sessionOpening = true;
                this.openAttemptsRemaining = 3;
                createSessionInternal(0);
                return;
            }
            Logging.m23189w(TAG, "Session already open");
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i9, int i10, int i11) {
        Logging.m23185d(TAG, "startCapture: " + i9 + "x" + i10 + "@" + i11);
        if (this.applicationContext == null) {
            throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
        }
        synchronized (this.stateLock) {
            if (!this.sessionOpening && this.currentSession == null) {
                resetFormat(i9, i10, i11);
                this.sessionOpening = true;
                this.openAttemptsRemaining = 3;
                createSessionInternal(0);
                return;
            }
            Logging.m23189w(TAG, "Session already open");
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        Logging.m23185d(TAG, "Stop capture");
        synchronized (this.stateLock) {
            while (this.sessionOpening) {
                Logging.m23185d(TAG, "Stop capture: Waiting for session to open");
                try {
                    this.stateLock.wait();
                } catch (InterruptedException unused) {
                    Logging.m23189w(TAG, "Stop capture interrupted while waiting for the session to open.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (this.currentSession != null) {
                Logging.m23185d(TAG, "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.6
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.m23185d(TAG, "Stop capture: No session open");
            }
        }
        Logging.m23185d(TAG, "Stop capture done");
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void switchCamera(final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.m23185d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.7
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.switchCameraInternal(cameraSwitchHandler);
            }
        });
    }
}
