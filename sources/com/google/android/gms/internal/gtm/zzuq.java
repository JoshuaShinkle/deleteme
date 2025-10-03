package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzuq;

/* loaded from: classes2.dex */
public abstract class zzuq<M extends zzuq<M>> extends zzuw {
    protected zzus zzbhb;

    @Override // com.google.android.gms.internal.gtm.zzuw
    public /* synthetic */ Object clone() {
        zzuq zzuqVar = (zzuq) super.clone();
        zzuu.zza(this, zzuqVar);
        return zzuqVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public void zza(zzuo zzuoVar) {
        if (this.zzbhb == null) {
            return;
        }
        for (int i9 = 0; i9 < this.zzbhb.size(); i9++) {
            this.zzbhb.zzce(i9).zza(zzuoVar);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    /* renamed from: zzry */
    public final /* synthetic */ zzuw clone() {
        return (zzuq) clone();
    }

    @Override // com.google.android.gms.internal.gtm.zzuw
    public int zzy() {
        if (this.zzbhb == null) {
            return 0;
        }
        int iZzy = 0;
        for (int i9 = 0; i9 < this.zzbhb.size(); i9++) {
            iZzy += this.zzbhb.zzce(i9).zzy();
        }
        return iZzy;
    }

    public final <T> T zza(zzur<M, T> zzurVar) {
        zzut zzutVarZzcd;
        zzus zzusVar = this.zzbhb;
        if (zzusVar == null || (zzutVarZzcd = zzusVar.zzcd(zzurVar.tag >>> 3)) == null) {
            return null;
        }
        return (T) zzutVarZzcd.zzb(zzurVar);
    }

    public final boolean zza(zzun zzunVar, int i9) {
        zzut zzutVarZzcd;
        int position = zzunVar.getPosition();
        if (!zzunVar.zzao(i9)) {
            return false;
        }
        int i10 = i9 >>> 3;
        zzuy zzuyVar = new zzuy(i9, zzunVar.zzt(position, zzunVar.getPosition() - position));
        zzus zzusVar = this.zzbhb;
        if (zzusVar == null) {
            this.zzbhb = new zzus();
            zzutVarZzcd = null;
        } else {
            zzutVarZzcd = zzusVar.zzcd(i10);
        }
        if (zzutVarZzcd == null) {
            zzutVarZzcd = new zzut();
            this.zzbhb.zza(i10, zzutVarZzcd);
        }
        zzutVarZzcd.zza(zzuyVar);
        return true;
    }
}
