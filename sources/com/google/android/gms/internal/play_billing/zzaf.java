package com.google.android.gms.internal.play_billing;

import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class zzaf extends zzx {
    static final zzx zza = new zzaf(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    private zzaf(Object obj, Object[] objArr, int i9) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i9;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
    public static zzaf zzf(int i9, Object[] objArr, zzw zzwVar) {
        int iHighestOneBit;
        short[] sArr;
        Object[] objArr2;
        int i10 = i9;
        Object[] objArrCopyOf = objArr;
        if (i10 == 0) {
            return (zzaf) zza;
        }
        Object obj = null;
        if (i10 == 1) {
            Object obj2 = objArrCopyOf[0];
            obj2.getClass();
            Object obj3 = objArrCopyOf[1];
            obj3.getClass();
            zzp.zza(obj2, obj3);
            return new zzaf(null, objArrCopyOf, 1);
        }
        zzm.zzb(i10, objArrCopyOf.length >> 1, FirebaseAnalytics.Param.INDEX);
        char c9 = 2;
        int iMax = Math.max(i10, 2);
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1);
            do {
                iHighestOneBit += iHighestOneBit;
            } while (iHighestOneBit * 0.7d < iMax);
        } else {
            iHighestOneBit = 1073741824;
            if (iMax >= 1073741824) {
                throw new IllegalArgumentException("collection too large");
            }
        }
        if (i10 == 1) {
            Object obj4 = objArrCopyOf[0];
            obj4.getClass();
            Object obj5 = objArrCopyOf[1];
            obj5.getClass();
            zzp.zza(obj4, obj5);
        } else {
            int i11 = iHighestOneBit - 1;
            char c10 = 65535;
            if (iHighestOneBit <= 128) {
                byte[] bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                int i12 = 0;
                for (int i13 = 0; i13 < i10; i13++) {
                    int i14 = i12 + i12;
                    int i15 = i13 + i13;
                    Object obj6 = objArrCopyOf[i15];
                    obj6.getClass();
                    Object obj7 = objArrCopyOf[i15 ^ 1];
                    obj7.getClass();
                    zzp.zza(obj6, obj7);
                    int iZza = zzq.zza(obj6.hashCode());
                    while (true) {
                        int i16 = iZza & i11;
                        int i17 = bArr[i16] & UnsignedBytes.MAX_VALUE;
                        if (i17 == 255) {
                            bArr[i16] = (byte) i14;
                            if (i12 < i13) {
                                objArrCopyOf[i14] = obj6;
                                objArrCopyOf[i14 ^ 1] = obj7;
                            }
                            i12++;
                        } else {
                            if (obj6.equals(objArrCopyOf[i17])) {
                                int i18 = i17 ^ 1;
                                Object obj8 = objArrCopyOf[i18];
                                obj8.getClass();
                                zzv zzvVar = new zzv(obj6, obj7, obj8);
                                objArrCopyOf[i18] = obj7;
                                obj = zzvVar;
                                break;
                            }
                            iZza = i16 + 1;
                        }
                    }
                }
                if (i12 == i10) {
                    obj = bArr;
                    c9 = 2;
                } else {
                    obj = new Object[]{bArr, Integer.valueOf(i12), obj};
                    c9 = 2;
                }
            } else if (iHighestOneBit <= 32768) {
                sArr = new short[iHighestOneBit];
                Arrays.fill(sArr, (short) -1);
                int i19 = 0;
                for (int i20 = 0; i20 < i10; i20++) {
                    int i21 = i19 + i19;
                    int i22 = i20 + i20;
                    Object obj9 = objArrCopyOf[i22];
                    obj9.getClass();
                    Object obj10 = objArrCopyOf[i22 ^ 1];
                    obj10.getClass();
                    zzp.zza(obj9, obj10);
                    int iZza2 = zzq.zza(obj9.hashCode());
                    while (true) {
                        int i23 = iZza2 & i11;
                        char c11 = (char) sArr[i23];
                        if (c11 == 65535) {
                            sArr[i23] = (short) i21;
                            if (i19 < i20) {
                                objArrCopyOf[i21] = obj9;
                                objArrCopyOf[i21 ^ 1] = obj10;
                            }
                            i19++;
                        } else {
                            if (obj9.equals(objArrCopyOf[c11])) {
                                int i24 = c11 ^ 1;
                                Object obj11 = objArrCopyOf[i24];
                                obj11.getClass();
                                zzv zzvVar2 = new zzv(obj9, obj10, obj11);
                                objArrCopyOf[i24] = obj10;
                                obj = zzvVar2;
                                break;
                            }
                            iZza2 = i23 + 1;
                        }
                    }
                }
                if (i19 != i10) {
                    c9 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i19), obj};
                    obj = objArr2;
                }
                obj = sArr;
                c9 = 2;
            } else {
                sArr = new int[iHighestOneBit];
                Arrays.fill((int[]) sArr, -1);
                int i25 = 0;
                int i26 = 0;
                while (i25 < i10) {
                    int i27 = i26 + i26;
                    int i28 = i25 + i25;
                    Object obj12 = objArrCopyOf[i28];
                    obj12.getClass();
                    Object obj13 = objArrCopyOf[i28 ^ 1];
                    obj13.getClass();
                    zzp.zza(obj12, obj13);
                    int iZza3 = zzq.zza(obj12.hashCode());
                    while (true) {
                        int i29 = iZza3 & i11;
                        ?? r15 = sArr[i29];
                        if (r15 == c10) {
                            sArr[i29] = i27;
                            if (i26 < i25) {
                                objArrCopyOf[i27] = obj12;
                                objArrCopyOf[i27 ^ 1] = obj13;
                            }
                            i26++;
                        } else {
                            if (obj12.equals(objArrCopyOf[r15])) {
                                int i30 = r15 ^ 1;
                                Object obj14 = objArrCopyOf[i30];
                                obj14.getClass();
                                zzv zzvVar3 = new zzv(obj12, obj13, obj14);
                                objArrCopyOf[i30] = obj13;
                                obj = zzvVar3;
                                break;
                            }
                            iZza3 = i29 + 1;
                            c10 = 65535;
                        }
                    }
                    i25++;
                    c10 = 65535;
                }
                if (i26 != i10) {
                    c9 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i26), obj};
                    obj = objArr2;
                }
                obj = sArr;
                c9 = 2;
            }
        }
        boolean z8 = obj instanceof Object[];
        Object obj15 = obj;
        if (z8) {
            Object[] objArr3 = (Object[]) obj;
            zzwVar.zzc = (zzv) objArr3[c9];
            Object obj16 = objArr3[0];
            int iIntValue = ((Integer) objArr3[1]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue + iIntValue);
            obj15 = obj16;
            i10 = iIntValue;
        }
        return new zzaf(obj15, objArrCopyOf, i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0009 A[EDGE_INSN: B:43:0x0009->B:4:0x0009 BREAK  A[LOOP:0: B:15:0x0038->B:21:0x004e], EDGE_INSN: B:45:0x0009->B:4:0x0009 BREAK  A[LOOP:1: B:25:0x0063->B:31:0x007a], EDGE_INSN: B:47:0x0009->B:4:0x0009 BREAK  A[LOOP:2: B:33:0x0089->B:42:0x00a0]] */
    @Override // com.google.android.gms.internal.play_billing.zzx, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(Object obj) {
        Object obj2;
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i9 = this.zzd;
        if (obj != null) {
            if (i9 == 1) {
                Object obj4 = objArr[0];
                obj4.getClass();
                if (obj4.equals(obj)) {
                    obj2 = objArr[1];
                    obj2.getClass();
                } else {
                    obj2 = null;
                }
            } else if (obj3 != null) {
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    int length = bArr.length - 1;
                    int iZza = zzq.zza(obj.hashCode());
                    while (true) {
                        int i10 = iZza & length;
                        int i11 = bArr[i10] & UnsignedBytes.MAX_VALUE;
                        if (i11 == 255) {
                            break;
                        }
                        if (obj.equals(objArr[i11])) {
                            obj2 = objArr[i11 ^ 1];
                            break;
                        }
                        iZza = i10 + 1;
                    }
                } else if (obj3 instanceof short[]) {
                    short[] sArr = (short[]) obj3;
                    int length2 = sArr.length - 1;
                    int iZza2 = zzq.zza(obj.hashCode());
                    while (true) {
                        int i12 = iZza2 & length2;
                        char c9 = (char) sArr[i12];
                        if (c9 == 65535) {
                            break;
                        }
                        if (obj.equals(objArr[c9])) {
                            obj2 = objArr[c9 ^ 1];
                            break;
                        }
                        iZza2 = i12 + 1;
                    }
                } else {
                    int[] iArr = (int[]) obj3;
                    int length3 = iArr.length - 1;
                    int iZza3 = zzq.zza(obj.hashCode());
                    while (true) {
                        int i13 = iZza3 & length3;
                        int i14 = iArr[i13];
                        if (i14 == -1) {
                            break;
                        }
                        if (obj.equals(objArr[i14])) {
                            obj2 = objArr[i14 ^ 1];
                            break;
                        }
                        iZza3 = i13 + 1;
                    }
                }
            }
        }
        if (obj2 == null) {
            return null;
        }
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    public final zzr zza() {
        return new zzae(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    public final zzy zzc() {
        return new zzac(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    public final zzy zzd() {
        return new zzad(this, new zzae(this.zzb, 0, this.zzd));
    }
}
