package p253z1;

import com.cyberlink.clgpuimage.Rotation;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: z1.a */
/* loaded from: classes.dex */
public class C6816a {

    /* renamed from: a */
    public static final float[] f22570a = {BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: b */
    public static final float[] f22571b = {1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: c */
    public static final float[] f22572c = {1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f};

    /* renamed from: d */
    public static final float[] f22573d = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f};

    /* renamed from: e */
    public static /* synthetic */ int[] f22574e;

    /* renamed from: a */
    public static /* synthetic */ int[] m25385a() {
        int[] iArr = f22574e;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Rotation.valuesCustom().length];
        try {
            iArr2[Rotation.NORMAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Rotation.ROTATION_180.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Rotation.ROTATION_270.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[Rotation.ROTATION_90.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        f22574e = iArr2;
        return iArr2;
    }

    /* renamed from: b */
    public static float m25386b(float f9) {
        if (f9 == BitmapDescriptorFactory.HUE_RED) {
            return 1.0f;
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: c */
    public static float[] m25387c(Rotation rotation, boolean z8, boolean z9) {
        int i9 = m25385a()[rotation.ordinal()];
        float[] fArr = i9 != 2 ? i9 != 3 ? i9 != 4 ? f22570a : f22573d : f22572c : f22571b;
        if (z8) {
            fArr = new float[]{m25386b(fArr[0]), fArr[1], m25386b(fArr[2]), fArr[3], m25386b(fArr[4]), fArr[5], m25386b(fArr[6]), fArr[7]};
        }
        return z9 ? new float[]{fArr[0], m25386b(fArr[1]), fArr[2], m25386b(fArr[3]), fArr[4], m25386b(fArr[5]), fArr[6], m25386b(fArr[7])} : fArr;
    }
}
