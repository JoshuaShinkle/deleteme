package com.google.zxing.client.android.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes2.dex */
final class PreviewCallback implements Camera.PreviewCallback {
    private static final String TAG = "PreviewCallback";
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;

    public PreviewCallback(CameraConfigurationManager cameraConfigurationManager) {
        this.configManager = cameraConfigurationManager;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point cameraResolution = this.configManager.getCameraResolution();
        Handler handler = this.previewHandler;
        if (cameraResolution == null || handler == null) {
            Log.d(TAG, "Got preview callback, but no handler or resolution available");
        } else {
            handler.obtainMessage(this.previewMessage, cameraResolution.x, cameraResolution.y, bArr).sendToTarget();
            this.previewHandler = null;
        }
    }

    public void setHandler(Handler handler, int i9) {
        this.previewHandler = handler;
        this.previewMessage = i9;
    }
}
