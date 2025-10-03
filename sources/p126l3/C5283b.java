package p126l3;

import android.hardware.Camera;
import java.util.List;

/* renamed from: l3.b */
/* loaded from: classes.dex */
public class C5283b {
    /* renamed from: a */
    public static int m20562a(int i9, int i10) {
        while (i9 != 0 && i10 != 0) {
            int i11 = i10;
            i10 = i9 % i10;
            i9 = i11;
        }
        return i9 + i10;
    }

    /* renamed from: b */
    public static Camera.Size m20563b(List<Camera.Size> list, int i9, int i10) {
        int iAbs;
        Camera.Size size = null;
        if (list == null) {
            return null;
        }
        int iAbs2 = 0;
        for (Camera.Size size2 : list) {
            if (size == null) {
                iAbs2 = Math.abs(size2.width - i9) + Math.abs(size2.height - i10);
                size = size2;
            } else {
                int i11 = size2.width;
                if (i11 >= i9 && size2.height >= i10 && (iAbs = Math.abs(i11 - i9) + Math.abs(size2.height - i10)) < iAbs2) {
                    size = size2;
                    iAbs2 = iAbs;
                }
            }
        }
        return size;
    }

    /* renamed from: c */
    public static Camera.Size m20564c(Camera camera, int i9, int i10) {
        return m20565d(camera.getParameters().getSupportedPreviewSizes(), i9, i10);
    }

    /* renamed from: d */
    public static Camera.Size m20565d(List<Camera.Size> list, int i9, int i10) {
        double d9 = i9 / i10;
        Camera.Size size = null;
        if (list == null) {
            return null;
        }
        for (Camera.Size size2 : list) {
            if (Math.abs((size2.width / size2.height) - d9) <= 0.009999999776482582d && size2.width > 480 && size2.height > 360) {
                size = size2;
            }
        }
        return size;
    }

    /* renamed from: e */
    public static Camera.Size m20566e(Camera camera, int i9, int i10) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedVideoSizes = parameters.getSupportedVideoSizes();
        if (supportedVideoSizes == null) {
            supportedVideoSizes = parameters.getSupportedPreviewSizes();
        }
        return m20563b(supportedVideoSizes, i9, i10);
    }
}
