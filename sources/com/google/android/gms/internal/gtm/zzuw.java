package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class zzuw {
    protected volatile int zzbhl = -1;

    public static final void zza(zzuw zzuwVar, byte[] bArr, int i9, int i10) {
        try {
            zzuo zzuoVarZzk = zzuo.zzk(bArr, 0, i10);
            zzuwVar.zza(zzuoVarZzk);
            zzuoVarZzk.zzrx();
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e9);
        }
    }

    private static final <T extends zzuw> T zzb(T t8, byte[] bArr, int i9, int i10) throws zzuv {
        try {
            zzun zzunVarZzj = zzun.zzj(bArr, 0, i10);
            t8.zza(zzunVarZzj);
            zzunVarZzj.zzan(0);
            return t8;
        } catch (zzuv e9) {
            throw e9;
        } catch (IOException e10) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e10);
        }
    }

    public String toString() {
        return zzux.zzc(this);
    }

    public abstract zzuw zza(zzun zzunVar);

    public void zza(zzuo zzuoVar) {
    }

    public final int zzpe() {
        int iZzy = zzy();
        this.zzbhl = iZzy;
        return iZzy;
    }

    @Override // 
    /* renamed from: zzry, reason: merged with bridge method [inline-methods] */
    public zzuw clone() {
        return (zzuw) super.clone();
    }

    public final int zzse() {
        if (this.zzbhl < 0) {
            zzpe();
        }
        return this.zzbhl;
    }

    public int zzy() {
        return 0;
    }

    public static final <T extends zzuw> T zza(T t8, byte[] bArr) {
        return (T) zzb(t8, bArr, 0, bArr.length);
    }
}
