package org.webrtc;

import android.hardware.Camera;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraVideoCapturer;

/* loaded from: classes3.dex */
public class Camera1Enumerator implements CameraEnumerator {
    private static final String TAG = "Camera1Enumerator";
    private static List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;
    private final boolean captureToTexture;

    public Camera1Enumerator() {
        this(true);
    }

    public static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(List<int[]> list) {
        ArrayList arrayList = new ArrayList();
        for (int[] iArr : list) {
            arrayList.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(iArr[0], iArr[1]));
        }
        return arrayList;
    }

    public static List<Size> convertSizes(List<Camera.Size> list) {
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : list) {
            arrayList.add(new Size(size.width, size.height));
        }
        return arrayList;
    }

    private static List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int i9) {
        int i10;
        Logging.m23185d(TAG, "Get supported formats for camera index " + i9 + ".");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Camera cameraOpen = null;
        try {
            try {
                Logging.m23185d(TAG, "Opening camera with index " + i9);
                cameraOpen = Camera.open(i9);
                Camera.Parameters parameters = cameraOpen.getParameters();
                cameraOpen.release();
                ArrayList arrayList = new ArrayList();
                try {
                    List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
                    int i11 = 0;
                    if (supportedPreviewFpsRange != null) {
                        int[] iArr = supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1);
                        i11 = iArr[0];
                        i10 = iArr[1];
                    } else {
                        i10 = 0;
                    }
                    for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                        arrayList.add(new CameraEnumerationAndroid.CaptureFormat(size.width, size.height, i11, i10));
                    }
                } catch (Exception e9) {
                    Logging.m23187e(TAG, "getSupportedFormats() failed on camera index " + i9, e9);
                }
                Logging.m23185d(TAG, "Get supported formats for camera index " + i9 + " done. Time spent: " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms.");
                return arrayList;
            } catch (RuntimeException e10) {
                Logging.m23187e(TAG, "Open camera failed on camera index " + i9, e10);
                ArrayList arrayList2 = new ArrayList();
                if (cameraOpen != null) {
                    cameraOpen.release();
                }
                return arrayList2;
            }
        } catch (Throwable th) {
            if (cameraOpen != null) {
                cameraOpen.release();
            }
            throw th;
        }
    }

    public static int getCameraIndex(String str) {
        Logging.m23185d(TAG, "getCameraIndex: " + str);
        for (int i9 = 0; i9 < Camera.getNumberOfCameras(); i9++) {
            if (str.equals(getDeviceName(i9))) {
                return i9;
            }
        }
        throw new IllegalArgumentException("No such camera: " + str);
    }

    private static Camera.CameraInfo getCameraInfo(int i9) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i9, cameraInfo);
            return cameraInfo;
        } catch (Exception e9) {
            Logging.m23187e(TAG, "getCameraInfo failed on index " + i9, e9);
            return null;
        }
    }

    public static String getDeviceName(int i9) {
        Camera.CameraInfo cameraInfo = getCameraInfo(i9);
        if (cameraInfo == null) {
            return null;
        }
        return "Camera " + i9 + ", Facing " + (cameraInfo.facing == 1 ? "front" : "back") + ", Orientation " + cameraInfo.orientation;
    }

    @Override // org.webrtc.CameraEnumerator
    public CameraVideoCapturer createCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        return new Camera1Capturer(str, cameraEventsHandler, this.captureToTexture);
    }

    @Override // org.webrtc.CameraEnumerator
    public String getBack() {
        for (String str : getDeviceNames()) {
            if (isBackFacing(str)) {
                return str;
            }
        }
        return "";
    }

    @Override // org.webrtc.CameraEnumerator
    public String[] getDeviceNames() {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < Camera.getNumberOfCameras(); i9++) {
            String deviceName = getDeviceName(i9);
            if (deviceName != null) {
                arrayList.add(deviceName);
                Logging.m23185d(TAG, "Index: " + i9 + ". " + deviceName);
            } else {
                Logging.m23186e(TAG, "Index: " + i9 + ". Failed to query camera name.");
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // org.webrtc.CameraEnumerator
    public String getFront() {
        for (String str : getDeviceNames()) {
            if (isFrontFacing(str)) {
                return str;
            }
        }
        return "";
    }

    @Override // org.webrtc.CameraEnumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String str) {
        return getSupportedFormats(getCameraIndex(str));
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isBackFacing(String str) {
        Camera.CameraInfo cameraInfo = getCameraInfo(getCameraIndex(str));
        return cameraInfo != null && cameraInfo.facing == 0;
    }

    @Override // org.webrtc.CameraEnumerator
    public boolean isFrontFacing(String str) {
        Camera.CameraInfo cameraInfo = getCameraInfo(getCameraIndex(str));
        return cameraInfo != null && cameraInfo.facing == 1;
    }

    public Camera1Enumerator(boolean z8) {
        this.captureToTexture = z8;
    }

    public static synchronized List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int i9) {
        if (cachedSupportedFormats == null) {
            cachedSupportedFormats = new ArrayList();
            for (int i10 = 0; i10 < Camera.getNumberOfCameras(); i10++) {
                cachedSupportedFormats.add(enumerateFormats(i10));
            }
        }
        return cachedSupportedFormats.get(i9);
    }
}
