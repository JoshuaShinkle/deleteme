package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class zzut implements Cloneable {
    private Object value;
    private zzur<?, ?> zzbhi;
    private List<zzuy> zzbhj = new ArrayList();

    private final byte[] toByteArray() throws zzup, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        byte[] bArr = new byte[zzy()];
        zza(zzuo.zzl(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzrz, reason: merged with bridge method [inline-methods] */
    public final zzut clone() {
        zzut zzutVar = new zzut();
        try {
            zzutVar.zzbhi = this.zzbhi;
            List<zzuy> list = this.zzbhj;
            if (list == null) {
                zzutVar.zzbhj = null;
            } else {
                zzutVar.zzbhj.addAll(list);
            }
            Object obj = this.value;
            if (obj != null) {
                if (obj instanceof zzuw) {
                    zzutVar.value = (zzuw) ((zzuw) obj).clone();
                } else if (obj instanceof byte[]) {
                    zzutVar.value = ((byte[]) obj).clone();
                } else {
                    int i9 = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzutVar.value = bArr2;
                        while (i9 < bArr.length) {
                            bArr2[i9] = (byte[]) bArr[i9].clone();
                            i9++;
                        }
                    } else if (obj instanceof boolean[]) {
                        zzutVar.value = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        zzutVar.value = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        zzutVar.value = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        zzutVar.value = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        zzutVar.value = ((double[]) obj).clone();
                    } else if (obj instanceof zzuw[]) {
                        zzuw[] zzuwVarArr = (zzuw[]) obj;
                        zzuw[] zzuwVarArr2 = new zzuw[zzuwVarArr.length];
                        zzutVar.value = zzuwVarArr2;
                        while (i9 < zzuwVarArr.length) {
                            zzuwVarArr2[i9] = (zzuw) zzuwVarArr[i9].clone();
                            i9++;
                        }
                    }
                }
            }
            return zzutVar;
        } catch (CloneNotSupportedException e9) {
            throw new AssertionError(e9);
        }
    }

    public final boolean equals(Object obj) {
        List<zzuy> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzut)) {
            return false;
        }
        zzut zzutVar = (zzut) obj;
        if (this.value == null || zzutVar.value == null) {
            List<zzuy> list2 = this.zzbhj;
            if (list2 != null && (list = zzutVar.zzbhj) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zzutVar.toByteArray());
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }
        zzur<?, ?> zzurVar = this.zzbhi;
        if (zzurVar != zzutVar.zzbhi) {
            return false;
        }
        if (!zzurVar.zzbhc.isArray()) {
            return this.value.equals(zzutVar.value);
        }
        Object obj2 = this.value;
        return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzutVar.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzutVar.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzutVar.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzutVar.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzutVar.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzutVar.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzutVar.value);
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public final void zza(zzuy zzuyVar) throws zzuv, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        Object objZzag;
        Object obj;
        List<zzuy> list = this.zzbhj;
        if (list != null) {
            list.add(zzuyVar);
            return;
        }
        Object obj2 = this.value;
        if (obj2 instanceof zzuw) {
            byte[] bArr = zzuyVar.zzawe;
            zzun zzunVarZzj = zzun.zzj(bArr, 0, bArr.length);
            int iZzoa = zzunVarZzj.zzoa();
            if (iZzoa != bArr.length - zzuo.zzbc(iZzoa)) {
                throw zzuv.zzsa();
            }
            objZzag = ((zzuw) this.value).zza(zzunVarZzj);
        } else {
            if (obj2 instanceof zzuw[]) {
                zzuw[] zzuwVarArr = (zzuw[]) this.zzbhi.zzag(Collections.singletonList(zzuyVar));
                zzuw[] zzuwVarArr2 = (zzuw[]) this.value;
                obj = (zzuw[]) Arrays.copyOf(zzuwVarArr2, zzuwVarArr2.length + zzuwVarArr.length);
                System.arraycopy(zzuwVarArr, 0, obj, zzuwVarArr2.length, zzuwVarArr.length);
            } else if (obj2 instanceof zzsk) {
                objZzag = ((zzsk) this.value).zzpg().zza((zzsk) this.zzbhi.zzag(Collections.singletonList(zzuyVar))).zzpm();
            } else if (obj2 instanceof zzsk[]) {
                zzsk[] zzskVarArr = (zzsk[]) this.zzbhi.zzag(Collections.singletonList(zzuyVar));
                zzsk[] zzskVarArr2 = (zzsk[]) this.value;
                obj = (zzsk[]) Arrays.copyOf(zzskVarArr2, zzskVarArr2.length + zzskVarArr.length);
                System.arraycopy(zzskVarArr, 0, obj, zzskVarArr2.length, zzskVarArr.length);
            } else {
                objZzag = this.zzbhi.zzag(Collections.singletonList(zzuyVar));
            }
            objZzag = obj;
        }
        this.zzbhi = this.zzbhi;
        this.value = objZzag;
        this.zzbhj = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T zzb(zzur<?, T> zzurVar) {
        if (this.value == null) {
            this.zzbhi = zzurVar;
            this.value = zzurVar.zzag(this.zzbhj);
            this.zzbhj = null;
        } else if (!this.zzbhi.equals(zzurVar)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return (T) this.value;
    }

    public final int zzy() throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        Object obj = this.value;
        if (obj == null) {
            int iZzbj = 0;
            for (zzuy zzuyVar : this.zzbhj) {
                iZzbj += zzuo.zzbj(zzuyVar.tag) + 0 + zzuyVar.zzawe.length;
            }
            return iZzbj;
        }
        zzur<?, ?> zzurVar = this.zzbhi;
        if (!zzurVar.zzbhd) {
            return zzurVar.zzaj(obj);
        }
        int length = Array.getLength(obj);
        int iZzaj = 0;
        for (int i9 = 0; i9 < length; i9++) {
            Object obj2 = Array.get(obj, i9);
            if (obj2 != null) {
                iZzaj += zzurVar.zzaj(obj2);
            }
        }
        return iZzaj;
    }

    public final void zza(zzuo zzuoVar) throws zzup, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        Object obj = this.value;
        if (obj != null) {
            zzur<?, ?> zzurVar = this.zzbhi;
            if (zzurVar.zzbhd) {
                int length = Array.getLength(obj);
                for (int i9 = 0; i9 < length; i9++) {
                    Object obj2 = Array.get(obj, i9);
                    if (obj2 != null) {
                        zzurVar.zza(obj2, zzuoVar);
                    }
                }
                return;
            }
            zzurVar.zza(obj, zzuoVar);
            return;
        }
        for (zzuy zzuyVar : this.zzbhj) {
            zzuoVar.zzcb(zzuyVar.tag);
            zzuoVar.zzm(zzuyVar.zzawe);
        }
    }
}
