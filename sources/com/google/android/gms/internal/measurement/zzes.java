package com.google.android.gms.internal.measurement;

import com.google.common.primitives.UnsignedBytes;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzes {
    public static int zza(int i9, int i10, int i11) {
        return (i9 & (~i11)) | (i10 & i11);
    }

    public static Object zza(int i9) {
        if (i9 >= 2 && i9 <= 1073741824 && Integer.highestOneBit(i9) == i9) {
            return i9 <= 256 ? new byte[i9] : i9 <= 65536 ? new short[i9] : new int[i9];
        }
        StringBuilder sb = new StringBuilder(52);
        sb.append("must be power of 2 between 2^1 and 2^30: ");
        sb.append(i9);
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzb(int i9) {
        return (i9 < 32 ? 4 : 2) * (i9 + 1);
    }

    public static int zza(Object obj, int i9) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i9] & UnsignedBytes.MAX_VALUE;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[i9] & 65535;
        }
        return ((int[]) obj)[i9];
    }

    public static void zza(Object obj, int i9, int i10) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i9] = (byte) i10;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i9] = (short) i10;
        } else {
            ((int[]) obj)[i9] = i10;
        }
    }

    public static int zza(@NullableDecl Object obj, @NullableDecl Object obj2, int i9, Object obj3, int[] iArr, Object[] objArr, @NullableDecl Object[] objArr2) {
        int i10;
        int i11;
        int iZza = zzeu.zza(obj);
        int i12 = iZza & i9;
        int iZza2 = zza(obj3, i12);
        if (iZza2 == 0) {
            return -1;
        }
        int i13 = ~i9;
        int i14 = iZza & i13;
        int i15 = -1;
        while (true) {
            i10 = iZza2 - 1;
            i11 = iArr[i10];
            if ((i11 & i13) == i14 && zzdu.zza(obj, objArr[i10]) && (objArr2 == null || zzdu.zza(obj2, objArr2[i10]))) {
                break;
            }
            int i16 = i11 & i9;
            if (i16 == 0) {
                return -1;
            }
            i15 = i10;
            iZza2 = i16;
        }
        int i17 = i11 & i9;
        if (i15 == -1) {
            zza(obj3, i12, i17);
        } else {
            iArr[i15] = zza(iArr[i15], i17, i9);
        }
        return i10;
    }
}
