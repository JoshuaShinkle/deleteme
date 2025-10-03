package com.google.zxing.client.android.camera;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@TargetApi(15)
/* loaded from: classes2.dex */
public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final double MAX_ASPECT_DISTORTION = 0.15d;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final int MIN_PREVIEW_PIXELS = 153600;
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    private static List<Camera.Area> buildMiddleArea(int i9) {
        int i10 = -i9;
        return Collections.singletonList(new Camera.Area(new Rect(i10, i10, i9, i9), 1));
    }

    public static String collectStats(Camera.Parameters parameters) {
        return collectStats(parameters.flatten());
    }

    public static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point point) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() { // from class: com.google.zxing.client.android.camera.CameraConfigurationUtils.1
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                int i9 = size.height * size.width;
                int i10 = size2.height * size2.width;
                if (i10 < i9) {
                    return -1;
                }
                return i10 > i9 ? 1 : 0;
            }
        });
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i(TAG, "Supported preview sizes: " + ((Object) sb));
        }
        int i9 = point.x;
        int i10 = point.y;
        double d9 = i9 / i10;
        boolean z8 = i9 < i10;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Camera.Size size2 = (Camera.Size) it.next();
            int i11 = size2.width;
            int i12 = size2.height;
            if (i11 * i12 < MIN_PREVIEW_PIXELS) {
                it.remove();
            } else {
                int i13 = z8 ? i12 : i11;
                int i14 = z8 ? i11 : i12;
                if (Math.abs((i13 / i14) - d9) > MAX_ASPECT_DISTORTION) {
                    it.remove();
                } else if (i13 == point.x && i14 == point.y) {
                    Point point2 = new Point(i11, i12);
                    Log.i(TAG, "Found preview size exactly matching screen size: " + point2);
                    return point2;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            Camera.Size size3 = (Camera.Size) arrayList.get(0);
            Point point3 = new Point(size3.width, size3.height);
            Log.i(TAG, "Using largest suitable preview size: " + point3);
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 == null) {
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        Point point4 = new Point(previewSize2.width, previewSize2.height);
        Log.i(TAG, "No suitable preview sizes, using default: " + point4);
        return point4;
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        Log.i(TAG, "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i(TAG, "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i(TAG, "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i(TAG, "No supported values match");
        return null;
    }

    private static Integer indexOfClosestZoom(Camera.Parameters parameters, double d9) {
        List<Integer> zoomRatios = parameters.getZoomRatios();
        Log.i(TAG, "Zoom ratios: " + zoomRatios);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w(TAG, "Invalid zoom ratios!");
            return null;
        }
        double d10 = d9 * 100.0d;
        double d11 = Double.POSITIVE_INFINITY;
        int i9 = 0;
        for (int i10 = 0; i10 < zoomRatios.size(); i10++) {
            double dAbs = Math.abs(zoomRatios.get(i10).intValue() - d10);
            if (dAbs < d11) {
                i9 = i10;
                d11 = dAbs;
            }
        }
        Log.i(TAG, "Chose zoom ratio of " + (zoomRatios.get(i9).intValue() / 100.0d));
        return Integer.valueOf(i9);
    }

    public static void setBarcodeSceneMode(Camera.Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i(TAG, "Barcode scene mode already set");
            return;
        }
        String strFindSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (strFindSettableValue != null) {
            parameters.setSceneMode(strFindSettableValue);
        }
    }

    public static void setBestExposure(Camera.Parameters parameters, boolean z8) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int iRound = Math.round((z8 ? 0.0f : MAX_EXPOSURE_COMPENSATION) / exposureCompensationStep);
                float f9 = exposureCompensationStep * iRound;
                int iMax = Math.max(Math.min(iRound, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == iMax) {
                    Log.i(TAG, "Exposure compensation already set to " + iMax + " / " + f9);
                    return;
                }
                Log.i(TAG, "Setting exposure compensation to " + iMax + " / " + f9);
                parameters.setExposureCompensation(iMax);
                return;
            }
        }
        Log.i(TAG, "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setFocus(Camera.Parameters parameters, boolean z8, boolean z9, boolean z10) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        String strFindSettableValue = z8 ? (z10 || z9) ? findSettableValue("focus mode", supportedFocusModes, "auto") : findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto") : null;
        if (!z10 && strFindSettableValue == null) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (strFindSettableValue != null) {
            if (!strFindSettableValue.equals(parameters.getFocusMode())) {
                parameters.setFocusMode(strFindSettableValue);
                return;
            }
            Log.i(TAG, "Focus mode already set to " + strFindSettableValue);
        }
    }

    public static void setFocusArea(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() <= 0) {
            Log.i(TAG, "Device does not support focus areas");
            return;
        }
        Log.i(TAG, "Old focus areas: " + toString((Iterable<Camera.Area>) parameters.getFocusAreas()));
        List<Camera.Area> listBuildMiddleArea = buildMiddleArea(AREA_PER_1000);
        Log.i(TAG, "Setting focus area to : " + toString((Iterable<Camera.Area>) listBuildMiddleArea));
        parameters.setFocusAreas(listBuildMiddleArea);
    }

    public static void setInvertColor(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i(TAG, "Negative effect already set");
            return;
        }
        String strFindSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (strFindSettableValue != null) {
            parameters.setColorEffect(strFindSettableValue);
        }
    }

    public static void setMetering(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() <= 0) {
            Log.i(TAG, "Device does not support metering areas");
            return;
        }
        Log.i(TAG, "Old metering areas: " + parameters.getMeteringAreas());
        List<Camera.Area> listBuildMiddleArea = buildMiddleArea(AREA_PER_1000);
        Log.i(TAG, "Setting metering area to : " + toString((Iterable<Camera.Area>) listBuildMiddleArea));
        parameters.setMeteringAreas(listBuildMiddleArea);
    }

    public static void setTorch(Camera.Parameters parameters, boolean z8) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String strFindSettableValue = z8 ? findSettableValue("flash mode", supportedFlashModes, "torch", "on") : findSettableValue("flash mode", supportedFlashModes, "off");
        if (strFindSettableValue != null) {
            if (strFindSettableValue.equals(parameters.getFlashMode())) {
                Log.i(TAG, "Flash mode already set to " + strFindSettableValue);
                return;
            }
            Log.i(TAG, "Setting flash mode to " + strFindSettableValue);
            parameters.setFlashMode(strFindSettableValue);
        }
    }

    public static void setVideoStabilization(Camera.Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported()) {
            Log.i(TAG, "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i(TAG, "Video stabilization already enabled");
        } else {
            Log.i(TAG, "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    public static void setZoom(Camera.Parameters parameters, double d9) {
        if (!parameters.isZoomSupported()) {
            Log.i(TAG, "Zoom is not supported");
            return;
        }
        Integer numIndexOfClosestZoom = indexOfClosestZoom(parameters, d9);
        if (numIndexOfClosestZoom == null) {
            return;
        }
        if (parameters.getZoom() == numIndexOfClosestZoom.intValue()) {
            Log.i(TAG, "Zoom is already set to " + numIndexOfClosestZoom);
            return;
        }
        Log.i(TAG, "Setting zoom to " + numIndexOfClosestZoom);
        parameters.setZoom(numIndexOfClosestZoom.intValue());
    }

    private static String toString(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString(it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        sb.append("BOARD=");
        sb.append(Build.BOARD);
        sb.append('\n');
        sb.append("BRAND=");
        sb.append(Build.BRAND);
        sb.append('\n');
        sb.append("CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append('\n');
        sb.append("DEVICE=");
        sb.append(Build.DEVICE);
        sb.append('\n');
        sb.append("DISPLAY=");
        sb.append(Build.DISPLAY);
        sb.append('\n');
        sb.append("FINGERPRINT=");
        sb.append(Build.FINGERPRINT);
        sb.append('\n');
        sb.append("HOST=");
        sb.append(Build.HOST);
        sb.append('\n');
        sb.append("ID=");
        sb.append(Build.ID);
        sb.append('\n');
        sb.append("MANUFACTURER=");
        sb.append(Build.MANUFACTURER);
        sb.append('\n');
        sb.append("MODEL=");
        sb.append(Build.MODEL);
        sb.append('\n');
        sb.append("PRODUCT=");
        sb.append(Build.PRODUCT);
        sb.append('\n');
        sb.append("TAGS=");
        sb.append(Build.TAGS);
        sb.append('\n');
        sb.append("TIME=");
        sb.append(Build.TIME);
        sb.append('\n');
        sb.append("TYPE=");
        sb.append(Build.TYPE);
        sb.append('\n');
        sb.append("USER=");
        sb.append(Build.USER);
        sb.append('\n');
        sb.append("VERSION.CODENAME=");
        sb.append(Build.VERSION.CODENAME);
        sb.append('\n');
        sb.append("VERSION.INCREMENTAL=");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append('\n');
        sb.append("VERSION.RELEASE=");
        sb.append(Build.VERSION.RELEASE);
        sb.append('\n');
        sb.append("VERSION.SDK_INT=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append('\n');
        if (charSequence != null) {
            String[] strArrSplit = SEMICOLON.split(charSequence);
            Arrays.sort(strArrSplit);
            for (String str : strArrSplit) {
                sb.append(str);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters, int i9, int i10) {
        int[] next;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i(TAG, "Supported FPS ranges: " + toString((Collection<int[]>) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            return;
        }
        Iterator<int[]> it = supportedPreviewFpsRange.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int i11 = next[0];
            int i12 = next[1];
            if (i11 >= i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT && i12 <= i10 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) {
                break;
            }
        }
        if (next == null) {
            Log.i(TAG, "No suitable FPS range?");
            return;
        }
        int[] iArr = new int[2];
        parameters.getPreviewFpsRange(iArr);
        if (Arrays.equals(iArr, next)) {
            Log.i(TAG, "FPS range already set to " + Arrays.toString(next));
            return;
        }
        Log.i(TAG, "Setting FPS range to " + Arrays.toString(next));
        parameters.setPreviewFpsRange(next[0], next[1]);
    }

    private static String toString(Iterable<Camera.Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Camera.Area area : iterable) {
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }
}
