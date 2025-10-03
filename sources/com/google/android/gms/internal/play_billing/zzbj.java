package com.google.android.gms.internal.play_billing;

import java.util.List;

/* loaded from: classes2.dex */
final class zzbj implements zzey {
    private final zzbi zza;

    private zzbj(zzbi zzbiVar) {
        byte[] bArr = zzcg.zzd;
        this.zza = zzbiVar;
        zzbiVar.zza = this;
    }

    public static zzbj zza(zzbi zzbiVar) {
        zzbj zzbjVar = zzbiVar.zza;
        return zzbjVar != null ? zzbjVar : new zzbj(zzbiVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzA(int i9, int i10) {
        this.zza.zzp(i9, (i10 >> 31) ^ (i10 + i10));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzB(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                zzbi zzbiVar = this.zza;
                int iIntValue = ((Integer) list.get(i10)).intValue();
                zzbiVar.zzp(i9, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzx = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            int iIntValue2 = ((Integer) list.get(i11)).intValue();
            iZzx += zzbi.zzx((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
        }
        this.zza.zzq(iZzx);
        while (i10 < list.size()) {
            zzbi zzbiVar2 = this.zza;
            int iIntValue3 = ((Integer) list.get(i10)).intValue();
            zzbiVar2.zzq((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzC(int i9, long j9) {
        this.zza.zzr(i9, (j9 >> 63) ^ (j9 + j9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzD(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                zzbi zzbiVar = this.zza;
                long jLongValue = ((Long) list.get(i10)).longValue();
                zzbiVar.zzr(i9, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzy = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            long jLongValue2 = ((Long) list.get(i11)).longValue();
            iZzy += zzbi.zzy((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
        }
        this.zza.zzq(iZzy);
        while (i10 < list.size()) {
            zzbi zzbiVar2 = this.zza;
            long jLongValue3 = ((Long) list.get(i10)).longValue();
            zzbiVar2.zzs((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    @Deprecated
    public final void zzE(int i9) {
        this.zza.zzo(i9, 3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzF(int i9, String str) {
        this.zza.zzm(i9, str);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzG(int i9, List list) {
        int i10 = 0;
        if (!(list instanceof zzcn)) {
            while (i10 < list.size()) {
                this.zza.zzm(i9, (String) list.get(i10));
                i10++;
            }
            return;
        }
        zzcn zzcnVar = (zzcn) list;
        while (i10 < list.size()) {
            Object objZzf = zzcnVar.zzf(i10);
            if (objZzf instanceof String) {
                this.zza.zzm(i9, (String) objZzf);
            } else {
                this.zza.zze(i9, (zzba) objZzf);
            }
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzH(int i9, int i10) {
        this.zza.zzp(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzI(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzp(i9, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzx = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzx += zzbi.zzx(((Integer) list.get(i11)).intValue());
        }
        this.zza.zzq(iZzx);
        while (i10 < list.size()) {
            this.zza.zzq(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzJ(int i9, long j9) {
        this.zza.zzr(i9, j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzK(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzr(i9, ((Long) list.get(i10)).longValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzy = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzy += zzbi.zzy(((Long) list.get(i11)).longValue());
        }
        this.zza.zzq(iZzy);
        while (i10 < list.size()) {
            this.zza.zzs(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzb(int i9, boolean z8) {
        this.zza.zzd(i9, z8);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzc(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzd(i9, ((Boolean) list.get(i10)).booleanValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Boolean) list.get(i12)).booleanValue();
            i11++;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzb(((Boolean) list.get(i10)).booleanValue() ? (byte) 1 : (byte) 0);
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzd(int i9, zzba zzbaVar) {
        this.zza.zze(i9, zzbaVar);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zze(int i9, List list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            this.zza.zze(i9, (zzba) list.get(i10));
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzf(int i9, double d9) {
        this.zza.zzh(i9, Double.doubleToRawLongBits(d9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzg(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzh(i9, Double.doubleToRawLongBits(((Double) list.get(i10)).doubleValue()));
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Double) list.get(i12)).doubleValue();
            i11 += 8;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i10)).doubleValue()));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    @Deprecated
    public final void zzh(int i9) {
        this.zza.zzo(i9, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzi(int i9, int i10) {
        this.zza.zzj(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzj(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzj(i9, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzu = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzu += zzbi.zzu(((Integer) list.get(i11)).intValue());
        }
        this.zza.zzq(iZzu);
        while (i10 < list.size()) {
            this.zza.zzk(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzk(int i9, int i10) {
        this.zza.zzf(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzl(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzf(i9, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Integer) list.get(i12)).intValue();
            i11 += 4;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzg(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzm(int i9, long j9) {
        this.zza.zzh(i9, j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzn(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzh(i9, ((Long) list.get(i10)).longValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Long) list.get(i12)).longValue();
            i11 += 8;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzi(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzo(int i9, float f9) {
        this.zza.zzf(i9, Float.floatToRawIntBits(f9));
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzp(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzf(i9, Float.floatToRawIntBits(((Float) list.get(i10)).floatValue()));
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Float) list.get(i12)).floatValue();
            i11 += 4;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i10)).floatValue()));
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzq(int i9, Object obj, zzdp zzdpVar) {
        zzbi zzbiVar = this.zza;
        zzbiVar.zzo(i9, 3);
        zzdpVar.zzi((zzdf) obj, zzbiVar.zza);
        zzbiVar.zzo(i9, 4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzr(int i9, int i10) {
        this.zza.zzj(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzs(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzj(i9, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzu = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzu += zzbi.zzu(((Integer) list.get(i11)).intValue());
        }
        this.zza.zzq(iZzu);
        while (i10 < list.size()) {
            this.zza.zzk(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzt(int i9, long j9) {
        this.zza.zzr(i9, j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzu(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzr(i9, ((Long) list.get(i10)).longValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int iZzy = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iZzy += zzbi.zzy(((Long) list.get(i11)).longValue());
        }
        this.zza.zzq(iZzy);
        while (i10 < list.size()) {
            this.zza.zzs(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzv(int i9, Object obj, zzdp zzdpVar) {
        zzdf zzdfVar = (zzdf) obj;
        zzbf zzbfVar = (zzbf) this.zza;
        zzbfVar.zzq((i9 << 3) | 2);
        zzbfVar.zzq(((zzak) zzdfVar).zza(zzdpVar));
        zzdpVar.zzi(zzdfVar, zzbfVar.zza);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzw(int i9, int i10) {
        this.zza.zzf(i9, i10);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzx(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzf(i9, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Integer) list.get(i12)).intValue();
            i11 += 4;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzg(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzy(int i9, long j9) {
        this.zza.zzh(i9, j9);
    }

    @Override // com.google.android.gms.internal.play_billing.zzey
    public final void zzz(int i9, List list, boolean z8) {
        int i10 = 0;
        if (!z8) {
            while (i10 < list.size()) {
                this.zza.zzh(i9, ((Long) list.get(i10)).longValue());
                i10++;
            }
            return;
        }
        this.zza.zzo(i9, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Long) list.get(i12)).longValue();
            i11 += 8;
        }
        this.zza.zzq(i11);
        while (i10 < list.size()) {
            this.zza.zzi(((Long) list.get(i10)).longValue());
            i10++;
        }
    }
}
