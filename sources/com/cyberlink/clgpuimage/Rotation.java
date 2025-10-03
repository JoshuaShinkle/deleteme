package com.cyberlink.clgpuimage;

/* loaded from: classes.dex */
public enum Rotation {
    NORMAL,
    ROTATION_90,
    ROTATION_180,
    ROTATION_270;


    /* renamed from: f */
    public static /* synthetic */ int[] f4342f;

    /* renamed from: a */
    public static /* synthetic */ int[] m4180a() {
        int[] iArr = f4342f;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[valuesCustom().length];
        try {
            iArr2[NORMAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[ROTATION_180.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[ROTATION_270.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[ROTATION_90.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        f4342f = iArr2;
        return iArr2;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static Rotation[] valuesCustom() {
        Rotation[] rotationArrValuesCustom = values();
        int length = rotationArrValuesCustom.length;
        Rotation[] rotationArr = new Rotation[length];
        System.arraycopy(rotationArrValuesCustom, 0, rotationArr, 0, length);
        return rotationArr;
    }

    /* renamed from: b */
    public int m4181b() {
        int i9 = m4180a()[ordinal()];
        if (i9 == 1) {
            return 0;
        }
        if (i9 == 2) {
            return 90;
        }
        if (i9 == 3) {
            return 180;
        }
        if (i9 == 4) {
            return 270;
        }
        throw new IllegalStateException("Unknown Rotation!");
    }
}
