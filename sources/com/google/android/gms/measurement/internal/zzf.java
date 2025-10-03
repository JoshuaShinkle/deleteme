package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
final class zzf {
    private final zzgb zza;
    private long zzaa;
    private long zzab;
    private long zzac;
    private String zzad;
    private boolean zzae;
    private long zzaf;
    private long zzag;
    private final String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private String zzj;
    private long zzk;
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private long zzp;
    private boolean zzq;
    private boolean zzr;
    private String zzs;
    private Boolean zzt;
    private long zzu;
    private List<String> zzv;
    private String zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    public zzf(zzgb zzgbVar, String str) {
        Preconditions.checkNotNull(zzgbVar);
        Preconditions.checkNotEmpty(str);
        this.zza = zzgbVar;
        this.zzb = str;
        zzgbVar.zzp().zzc();
    }

    public final boolean zza() {
        this.zza.zzp().zzc();
        return this.zzae;
    }

    public final long zzaa() {
        this.zza.zzp().zzc();
        return this.zzac;
    }

    public final long zzab() {
        this.zza.zzp().zzc();
        return this.zzab;
    }

    public final String zzac() {
        this.zza.zzp().zzc();
        return this.zzad;
    }

    public final String zzad() {
        this.zza.zzp().zzc();
        String str = this.zzad;
        zzi((String) null);
        return str;
    }

    public final long zzae() {
        this.zza.zzp().zzc();
        return this.zzp;
    }

    public final boolean zzaf() {
        this.zza.zzp().zzc();
        return this.zzq;
    }

    public final boolean zzag() {
        this.zza.zzp().zzc();
        return this.zzr;
    }

    public final Boolean zzah() {
        this.zza.zzp().zzc();
        return this.zzt;
    }

    public final List<String> zzai() {
        this.zza.zzp().zzc();
        return this.zzv;
    }

    public final void zzb() {
        this.zza.zzp().zzc();
        this.zzae = false;
    }

    public final String zzc() {
        this.zza.zzp().zzc();
        return this.zzb;
    }

    public final String zzd() {
        this.zza.zzp().zzc();
        return this.zzc;
    }

    public final String zze() {
        this.zza.zzp().zzc();
        return this.zzd;
    }

    public final String zzf() {
        this.zza.zzp().zzc();
        return this.zzs;
    }

    public final String zzg() {
        this.zza.zzp().zzc();
        return this.zzw;
    }

    public final String zzh() {
        this.zza.zzp().zzc();
        return this.zze;
    }

    public final String zzi() {
        this.zza.zzp().zzc();
        return this.zzf;
    }

    public final long zzj() {
        this.zza.zzp().zzc();
        return this.zzh;
    }

    public final long zzk() {
        this.zza.zzp().zzc();
        return this.zzi;
    }

    public final String zzl() {
        this.zza.zzp().zzc();
        return this.zzj;
    }

    public final long zzm() {
        this.zza.zzp().zzc();
        return this.zzk;
    }

    public final String zzn() {
        this.zza.zzp().zzc();
        return this.zzl;
    }

    public final long zzo() {
        this.zza.zzp().zzc();
        return this.zzm;
    }

    public final long zzp() {
        this.zza.zzp().zzc();
        return this.zzn;
    }

    public final long zzq() {
        this.zza.zzp().zzc();
        return this.zzu;
    }

    public final boolean zzr() {
        this.zza.zzp().zzc();
        return this.zzo;
    }

    public final long zzs() {
        this.zza.zzp().zzc();
        return this.zzg;
    }

    public final long zzt() {
        this.zza.zzp().zzc();
        return this.zzaf;
    }

    public final long zzu() {
        this.zza.zzp().zzc();
        return this.zzag;
    }

    public final void zzv() {
        this.zza.zzp().zzc();
        long j9 = this.zzg + 1;
        if (j9 > 2147483647L) {
            this.zza.zzq().zzh().zza("Bundle index overflow. appId", zzex.zza(this.zzb));
            j9 = 0;
        }
        this.zzae = true;
        this.zzg = j9;
    }

    public final long zzw() {
        this.zza.zzp().zzc();
        return this.zzx;
    }

    public final long zzx() {
        this.zza.zzp().zzc();
        return this.zzy;
    }

    public final long zzy() {
        this.zza.zzp().zzc();
        return this.zzz;
    }

    public final long zzz() {
        this.zza.zzp().zzc();
        return this.zzaa;
    }

    public final void zza(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zzc, str);
        this.zzc = str;
    }

    public final void zzb(String str) {
        this.zza.zzp().zzc();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkx.zzc(this.zzd, str);
        this.zzd = str;
    }

    public final void zzc(String str) {
        this.zza.zzp().zzc();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkx.zzc(this.zzs, str);
        this.zzs = str;
    }

    public final void zzd(String str) {
        this.zza.zzp().zzc();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkx.zzc(this.zzw, str);
        this.zzw = str;
    }

    public final void zze(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zze, str);
        this.zze = str;
    }

    public final void zzf(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zzf, str);
        this.zzf = str;
    }

    public final void zzg(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zzj, str);
        this.zzj = str;
    }

    public final void zzh(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zzl, str);
        this.zzl = str;
    }

    public final void zzi(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzag != j9;
        this.zzag = j9;
    }

    public final void zzj(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzx != j9;
        this.zzx = j9;
    }

    public final void zzk(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzy != j9;
        this.zzy = j9;
    }

    public final void zzl(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzz != j9;
        this.zzz = j9;
    }

    public final void zzm(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzaa != j9;
        this.zzaa = j9;
    }

    public final void zzn(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzac != j9;
        this.zzac = j9;
    }

    public final void zzo(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzab != j9;
        this.zzab = j9;
    }

    public final void zzp(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzp != j9;
        this.zzp = j9;
    }

    public final void zza(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzh != j9;
        this.zzh = j9;
    }

    public final void zze(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzn != j9;
        this.zzn = j9;
    }

    public final void zzf(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzu != j9;
        this.zzu = j9;
    }

    public final void zzg(long j9) {
        Preconditions.checkArgument(j9 >= 0);
        this.zza.zzp().zzc();
        this.zzae |= this.zzg != j9;
        this.zzg = j9;
    }

    public final void zzh(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzaf != j9;
        this.zzaf = j9;
    }

    public final void zzi(String str) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zzc(this.zzad, str);
        this.zzad = str;
    }

    public final void zzb(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzi != j9;
        this.zzi = j9;
    }

    public final void zzc(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzk != j9;
        this.zzk = j9;
    }

    public final void zzd(long j9) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzm != j9;
        this.zzm = j9;
    }

    public final void zza(boolean z8) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzo != z8;
        this.zzo = z8;
    }

    public final void zzb(boolean z8) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzq != z8;
        this.zzq = z8;
    }

    public final void zzc(boolean z8) {
        this.zza.zzp().zzc();
        this.zzae |= this.zzr != z8;
        this.zzr = z8;
    }

    public final void zza(Boolean bool) {
        this.zza.zzp().zzc();
        this.zzae |= !zzkx.zza(this.zzt, bool);
        this.zzt = bool;
    }

    public final void zza(List<String> list) {
        this.zza.zzp().zzc();
        if (zzkx.zza(this.zzv, list)) {
            return;
        }
        this.zzae = true;
        this.zzv = list != null ? new ArrayList(list) : null;
    }
}
