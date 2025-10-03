package com.google.zxing.client.android;

import android.app.Activity;
import android.net.Uri;
import com.google.zxing.Result;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.result.ResultHandler;

/* loaded from: classes2.dex */
public class CaptureActivityFactory {

    public static class AmbientLightManagerProxy {
        private AmbientLightManager ambientLightManager;

        public AmbientLightManagerProxy(Activity activity) {
            this.ambientLightManager = new AmbientLightManager(activity);
        }

        public void start(CameraManager cameraManager) {
            this.ambientLightManager.start(cameraManager);
        }

        public void stop() {
            this.ambientLightManager.stop();
        }
    }

    public static class BeepManagerProxy {
        private BeepManager beepManager;

        public BeepManagerProxy(Activity activity) {
            this.beepManager = new BeepManager(activity);
        }

        public void close() {
            this.beepManager.close();
        }

        public void playBeepSoundAndVibrate() {
            this.beepManager.playBeepSoundAndVibrate();
        }

        public void updatePrefs() {
            this.beepManager.updatePrefs();
        }
    }

    public static class InactivityTimerProxy {
        private InactivityTimer inactivityTimer;

        public InactivityTimerProxy(Activity activity) {
            this.inactivityTimer = new InactivityTimer(activity);
        }

        public void onActivity() {
            this.inactivityTimer.onActivity();
        }

        public void onPause() {
            this.inactivityTimer.onPause();
        }

        public void onResume() {
            this.inactivityTimer.onResume();
        }

        public void shutdown() {
            this.inactivityTimer.shutdown();
        }
    }

    public static class ScanFromWebPageManagerProxy {
        private ScanFromWebPageManager scanFromWebPageManager;

        public ScanFromWebPageManagerProxy(Uri uri) {
            this.scanFromWebPageManager = new ScanFromWebPageManager(uri);
        }

        public String buildReplyURL(Result result, ResultHandler resultHandler) {
            return this.scanFromWebPageManager.buildReplyURL(result, resultHandler);
        }

        public boolean isScanFromWebPage() {
            return this.scanFromWebPageManager.isScanFromWebPage();
        }
    }
}
