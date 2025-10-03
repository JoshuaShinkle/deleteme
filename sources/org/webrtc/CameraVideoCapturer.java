package org.webrtc;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* loaded from: classes3.dex */
public interface CameraVideoCapturer extends VideoCapturer {

    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraDisconnected();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(String str);

        void onFirstFrameAvailable();
    }

    public static class CameraStatistics {
        private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
        private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
        private static final String TAG = "CameraStatistics";
        private final Runnable cameraObserver;
        private final CameraEventsHandler eventsHandler;
        private int frameCount;
        private int freezePeriodCount;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public CameraStatistics(SurfaceTextureHelper surfaceTextureHelper, CameraEventsHandler cameraEventsHandler) {
            Runnable runnable = new Runnable() { // from class: org.webrtc.CameraVideoCapturer.CameraStatistics.1
                @Override // java.lang.Runnable
                public void run() {
                    Logging.m23185d(CameraStatistics.TAG, "Camera fps: " + Math.round((CameraStatistics.this.frameCount * 1000.0f) / 2000.0f) + ".");
                    if (CameraStatistics.this.frameCount == 0) {
                        CameraStatistics.access$104(CameraStatistics.this);
                        if (CameraStatistics.this.freezePeriodCount * 2000 >= CameraStatistics.CAMERA_FREEZE_REPORT_TIMOUT_MS && CameraStatistics.this.eventsHandler != null) {
                            Logging.m23186e(CameraStatistics.TAG, "Camera freezed.");
                            if (CameraStatistics.this.surfaceTextureHelper.isTextureInUse()) {
                                CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure. Client must return video buffers.");
                                return;
                            } else {
                                CameraStatistics.this.eventsHandler.onCameraFreezed("Camera failure.");
                                return;
                            }
                        }
                    } else {
                        CameraStatistics.this.freezePeriodCount = 0;
                    }
                    CameraStatistics.this.frameCount = 0;
                    CameraStatistics.this.surfaceTextureHelper.getHandler().postDelayed(this, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
                }
            };
            this.cameraObserver = runnable;
            if (surfaceTextureHelper == null) {
                throw new IllegalArgumentException("SurfaceTextureHelper is null");
            }
            this.surfaceTextureHelper = surfaceTextureHelper;
            this.eventsHandler = cameraEventsHandler;
            this.frameCount = 0;
            this.freezePeriodCount = 0;
            surfaceTextureHelper.getHandler().postDelayed(runnable, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        public static /* synthetic */ int access$104(CameraStatistics cameraStatistics) {
            int i9 = cameraStatistics.freezePeriodCount + 1;
            cameraStatistics.freezePeriodCount = i9;
            return i9;
        }

        private void checkThread() {
            if (Thread.currentThread() != this.surfaceTextureHelper.getHandler().getLooper().getThread()) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void addFrame() {
            checkThread();
            this.frameCount++;
        }

        public void release() {
            this.surfaceTextureHelper.getHandler().removeCallbacks(this.cameraObserver);
        }
    }

    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean z8);

        void onCameraSwitchError(String str);
    }

    void switchCamera(CameraSwitchHandler cameraSwitchHandler);
}
