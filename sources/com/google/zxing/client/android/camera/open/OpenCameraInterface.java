package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import android.util.Log;

/* loaded from: classes2.dex */
public final class OpenCameraInterface {
    public static final int NO_REQUESTED_CAMERA = -1;
    private static final String TAG = "com.google.zxing.client.android.camera.open.OpenCameraInterface";

    private OpenCameraInterface() {
    }

    public static OpenCamera open(int i9) {
        int i10;
        Camera.CameraInfo cameraInfo;
        Camera cameraOpen;
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(TAG, "No cameras!");
            return null;
        }
        boolean z8 = i9 >= 0;
        if (!z8) {
            i10 = 0;
            while (true) {
                if (i10 >= numberOfCameras) {
                    cameraInfo = null;
                    break;
                }
                cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i10, cameraInfo);
                if (CameraFacing.values()[cameraInfo.facing] == CameraFacing.BACK) {
                    break;
                }
                i10++;
            }
        } else {
            Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
            Camera.getCameraInfo(i9, cameraInfo2);
            cameraInfo = cameraInfo2;
            i10 = i9;
        }
        if (i10 < numberOfCameras) {
            Log.i(TAG, "Opening camera #" + i10);
            cameraOpen = Camera.open(i10);
        } else if (z8) {
            Log.w(TAG, "Requested camera does not exist: " + i9);
            cameraOpen = null;
        } else {
            Log.i(TAG, "No camera facing " + CameraFacing.BACK + "; returning camera #0");
            cameraOpen = Camera.open(0);
            cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(0, cameraInfo);
        }
        if (cameraOpen == null) {
            return null;
        }
        return new OpenCamera(i10, cameraOpen, CameraFacing.values()[cameraInfo.facing], cameraInfo.orientation);
    }
}
