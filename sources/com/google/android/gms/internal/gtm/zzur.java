package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuq;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzur<M extends zzuq<M>, T> {
    public final int tag;
    private final int type;
    private final zzrc<?, ?> zzban;
    protected final Class<T> zzbhc;
    protected final boolean zzbhd;

    private zzur(int i9, Class<T> cls, int i10, boolean z8) {
        this(11, cls, null, 810, false);
    }

    public static <M extends zzuq<M>, T extends zzuw> zzur<M, T> zza(int i9, Class<T> cls, long j9) {
        return new zzur<>(11, cls, 810, false);
    }

    private final Object zzc(zzun zzunVar) {
        Class componentType = this.zzbhd ? this.zzbhc.getComponentType() : this.zzbhc;
        try {
            int i9 = this.type;
            if (i9 == 10) {
                zzuw zzuwVar = (zzuw) componentType.newInstance();
                zzunVar.zza(zzuwVar, this.tag >>> 3);
                return zzuwVar;
            }
            if (i9 == 11) {
                zzuw zzuwVar2 = (zzuw) componentType.newInstance();
                zzunVar.zza(zzuwVar2);
                return zzuwVar2;
            }
            int i10 = this.type;
            StringBuilder sb = new StringBuilder(24);
            sb.append("Unknown type ");
            sb.append(i10);
            throw new IllegalArgumentException(sb.toString());
        } catch (IOException e9) {
            throw new IllegalArgumentException("Error reading extension field", e9);
        } catch (IllegalAccessException e10) {
            String strValueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 33);
            sb2.append("Error creating instance of class ");
            sb2.append(strValueOf);
            throw new IllegalArgumentException(sb2.toString(), e10);
        } catch (InstantiationException e11) {
            String strValueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 33);
            sb3.append("Error creating instance of class ");
            sb3.append(strValueOf2);
            throw new IllegalArgumentException(sb3.toString(), e11);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzur)) {
            return false;
        }
        zzur zzurVar = (zzur) obj;
        return this.type == zzurVar.type && this.zzbhc == zzurVar.zzbhc && this.tag == zzurVar.tag && this.zzbhd == zzurVar.zzbhd;
    }

    public final int hashCode() {
        return ((((((this.type + 1147) * 31) + this.zzbhc.hashCode()) * 31) + this.tag) * 31) + (this.zzbhd ? 1 : 0);
    }

    public final T zzag(List<zzuy> list) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (list == null) {
            return null;
        }
        if (!this.zzbhd) {
            if (list.isEmpty()) {
                return null;
            }
            return this.zzbhc.cast(zzc(zzun.zzk(list.get(list.size() - 1).zzawe)));
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < list.size(); i9++) {
            byte[] bArr = list.get(i9).zzawe;
            if (bArr.length != 0) {
                arrayList.add(zzc(zzun.zzk(bArr)));
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        Class<T> cls = this.zzbhc;
        T tCast = cls.cast(Array.newInstance(cls.getComponentType(), size));
        for (int i10 = 0; i10 < size; i10++) {
            Array.set(tCast, i10, arrayList.get(i10));
        }
        return tCast;
    }

    public final int zzaj(Object obj) {
        int i9 = this.tag >>> 3;
        int i10 = this.type;
        if (i10 == 10) {
            return (zzuo.zzbb(i9) << 1) + ((zzuw) obj).zzpe();
        }
        if (i10 == 11) {
            return zzuo.zzb(i9, (zzuw) obj);
        }
        int i11 = this.type;
        StringBuilder sb = new StringBuilder(24);
        sb.append("Unknown type ");
        sb.append(i11);
        throw new IllegalArgumentException(sb.toString());
    }

    private zzur(int i9, Class<T> cls, zzrc<?, ?> zzrcVar, int i10, boolean z8) {
        this.type = i9;
        this.zzbhc = cls;
        this.tag = i10;
        this.zzbhd = false;
        this.zzban = null;
    }

    public final void zza(Object obj, zzuo zzuoVar) {
        try {
            zzuoVar.zzcb(this.tag);
            int i9 = this.type;
            if (i9 == 10) {
                int i10 = this.tag >>> 3;
                ((zzuw) obj).zza(zzuoVar);
                zzuoVar.zzd(i10, 4);
            } else {
                if (i9 == 11) {
                    zzuoVar.zzb((zzuw) obj);
                    return;
                }
                int i11 = this.type;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i11);
                throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }
}
