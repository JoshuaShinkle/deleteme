package com.google.android.gms.internal.gtm;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes2.dex */
public final class zzuu {
    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName(CharEncoding.ISO_8859_1);
    public static final Object zzbhk = new Object();

    public static boolean equals(int[] iArr, int[] iArr2) {
        return (iArr == null || iArr.length == 0) ? iArr2 == null || iArr2.length == 0 : Arrays.equals(iArr, iArr2);
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(iArr);
    }

    public static void zza(zzuq zzuqVar, zzuq zzuqVar2) {
        zzus zzusVar = zzuqVar.zzbhb;
        if (zzusVar != null) {
            zzuqVar2.zzbhb = (zzus) zzusVar.clone();
        }
    }

    public static int hashCode(Object[] objArr) {
        int length = objArr == null ? 0 : objArr.length;
        int iHashCode = 0;
        for (int i9 = 0; i9 < length; i9++) {
            Object obj = objArr[i9];
            if (obj != null) {
                iHashCode = (iHashCode * 31) + obj.hashCode();
            }
        }
        return iHashCode;
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length = objArr == null ? 0 : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (i9 >= length || objArr[i9] != null) {
                while (i10 < length2 && objArr2[i10] == null) {
                    i10++;
                }
                boolean z8 = i9 >= length;
                boolean z9 = i10 >= length2;
                if (z8 && z9) {
                    return true;
                }
                if (z8 != z9 || !objArr[i9].equals(objArr2[i10])) {
                    return false;
                }
                i9++;
                i10++;
            } else {
                i9++;
            }
        }
    }
}
