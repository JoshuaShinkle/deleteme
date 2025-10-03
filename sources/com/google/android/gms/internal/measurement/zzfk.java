package com.google.android.gms.internal.measurement;

import com.google.common.primitives.UnsignedBytes;
import java.util.Arrays;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
final class zzfk<K, V> extends zzfb<K, V> {
    static final zzfb<Object, Object> zza = new zzfk(null, new Object[0], 0);
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    private zzfk(Object obj, Object[] objArr, int i9) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
    
        r2[r6] = (byte) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
    
        r2[r6] = (short) r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cc, code lost:
    
        r2[r7] = r3;
        r1 = r1 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int[]] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <K, V> zzfk<K, V> zza(int i9, Object[] objArr) {
        byte[] bArr;
        if (i9 == 0) {
            return (zzfk) zza;
        }
        Object obj = null;
        int i10 = 0;
        if (i9 == 1) {
            zzei.zza(objArr[0], objArr[1]);
            return new zzfk<>(null, objArr, 1);
        }
        zzdw.zzb(i9, objArr.length >> 1);
        int iZza = zzff.zza(i9);
        if (i9 == 1) {
            zzei.zza(objArr[0], objArr[1]);
        } else {
            int i11 = iZza - 1;
            if (iZza <= 128) {
                bArr = new byte[iZza];
                Arrays.fill(bArr, (byte) -1);
                while (i10 < i9) {
                    int i12 = i10 * 2;
                    Object obj2 = objArr[i12];
                    Object obj3 = objArr[i12 ^ 1];
                    zzei.zza(obj2, obj3);
                    int iZza2 = zzeu.zza(obj2.hashCode());
                    while (true) {
                        int i13 = iZza2 & i11;
                        int i14 = bArr[i13] & 255;
                        if (i14 == 255) {
                            break;
                        }
                        if (objArr[i14].equals(obj2)) {
                            throw zza(obj2, obj3, objArr, i14);
                        }
                        iZza2 = i13 + 1;
                    }
                }
            } else if (iZza <= 32768) {
                bArr = new short[iZza];
                Arrays.fill(bArr, (short) -1);
                while (i10 < i9) {
                    int i15 = i10 * 2;
                    Object obj4 = objArr[i15];
                    Object obj5 = objArr[i15 ^ 1];
                    zzei.zza(obj4, obj5);
                    int iZza3 = zzeu.zza(obj4.hashCode());
                    while (true) {
                        int i16 = iZza3 & i11;
                        int i17 = bArr[i16] & 65535;
                        if (i17 == 65535) {
                            break;
                        }
                        if (objArr[i17].equals(obj4)) {
                            throw zza(obj4, obj5, objArr, i17);
                        }
                        iZza3 = i16 + 1;
                    }
                }
            } else {
                bArr = new int[iZza];
                Arrays.fill((int[]) bArr, -1);
                while (i10 < i9) {
                    int i18 = i10 * 2;
                    Object obj6 = objArr[i18];
                    Object obj7 = objArr[i18 ^ 1];
                    zzei.zza(obj6, obj7);
                    int iZza4 = zzeu.zza(obj6.hashCode());
                    while (true) {
                        int i19 = iZza4 & i11;
                        ?? r8 = bArr[i19];
                        if (r8 == -1) {
                            break;
                        }
                        if (objArr[r8].equals(obj6)) {
                            throw zza(obj6, obj7, objArr, r8);
                        }
                        iZza4 = i19 + 1;
                    }
                }
            }
            obj = bArr;
        }
        return new zzfk<>(obj, objArr, i9);
    }

    @Override // com.google.android.gms.internal.measurement.zzfb, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i9 = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i9 == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int iZza = zzeu.zza(obj.hashCode());
            while (true) {
                int i10 = iZza & length;
                int i11 = bArr[i10] & UnsignedBytes.MAX_VALUE;
                if (i11 == 255) {
                    return null;
                }
                if (objArr[i11].equals(obj)) {
                    return (V) objArr[i11 ^ 1];
                }
                iZza = i10 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iZza2 = zzeu.zza(obj.hashCode());
            while (true) {
                int i12 = iZza2 & length2;
                int i13 = sArr[i12] & 65535;
                if (i13 == 65535) {
                    return null;
                }
                if (objArr[i13].equals(obj)) {
                    return (V) objArr[i13 ^ 1];
                }
                iZza2 = i12 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iZza3 = zzeu.zza(obj.hashCode());
            while (true) {
                int i14 = iZza3 & length3;
                int i15 = iArr[i14];
                if (i15 == -1) {
                    return null;
                }
                if (objArr[i15].equals(obj)) {
                    return (V) objArr[i15 ^ 1];
                }
                iZza3 = i14 + 1;
            }
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzfb
    public final zzff<K> zzb() {
        return new zzfp(this, new zzfo(this.zzc, 0, this.zzd));
    }

    @Override // com.google.android.gms.internal.measurement.zzfb
    public final zzex<V> zzc() {
        return new zzfo(this.zzc, 1, this.zzd);
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i9) {
        String strValueOf = String.valueOf(obj);
        String strValueOf2 = String.valueOf(obj2);
        String strValueOf3 = String.valueOf(objArr[i9]);
        String strValueOf4 = String.valueOf(objArr[i9 ^ 1]);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 39 + strValueOf2.length() + strValueOf3.length() + strValueOf4.length());
        sb.append("Multiple entries with same key: ");
        sb.append(strValueOf);
        sb.append("=");
        sb.append(strValueOf2);
        sb.append(" and ");
        sb.append(strValueOf3);
        sb.append("=");
        sb.append(strValueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzfb
    public final zzff<Map.Entry<K, V>> zza() {
        return new zzfn(this, this.zzc, 0, this.zzd);
    }
}
