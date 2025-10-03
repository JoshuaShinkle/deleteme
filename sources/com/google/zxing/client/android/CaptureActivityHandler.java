package com.google.zxing.client.android;

import android.os.Handler;
import android.os.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.client.android.camera.CameraManager;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public final class CaptureActivityHandler extends Handler {
    private static final String TAG = "CaptureActivityHandler";
    private final CaptureActivity activity;
    private final CameraManager cameraManager;
    private final DecodeThread decodeThread;
    private State state;

    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(CaptureActivity captureActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, CameraManager cameraManager) {
        this.activity = captureActivity;
        DecodeThread decodeThread = new DecodeThread(captureActivity, collection, map, str, new ViewfinderResultPointCallback(captureActivity.getViewfinderView()));
        this.decodeThread = decodeThread;
        decodeThread.start();
        this.state = State.SUCCESS;
        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    private void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            this.cameraManager.requestPreviewFrame(this.decodeThread.getHandler(), C4453R.id.decode);
            this.activity.drawViewfinder();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
    }

    public void quitSynchronously() throws InterruptedException {
        this.state = State.DONE;
        this.cameraManager.stopPreview();
        Message.obtain(this.decodeThread.getHandler(), C4453R.id.quit).sendToTarget();
        try {
            this.decodeThread.join(500L);
        } catch (InterruptedException unused) {
        }
        removeMessages(C4453R.id.decode_succeeded);
        removeMessages(C4453R.id.decode_failed);
    }
}
