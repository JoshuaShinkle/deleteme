package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzmb;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class zzjv extends zzkm {
    private String zzb;
    private boolean zzc;
    private long zzd;

    public zzjv(zzkp zzkpVar) {
        super(zzkpVar);
    }

    @Deprecated
    private final Pair<String, Boolean> zzb(String str) {
        zzc();
        long jElapsedRealtime = zzl().elapsedRealtime();
        if (this.zzb != null && jElapsedRealtime < this.zzd) {
            return new Pair<>(this.zzb, Boolean.valueOf(this.zzc));
        }
        this.zzd = jElapsedRealtime + zzs().zze(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzm());
            if (advertisingIdInfo != null) {
                this.zzb = advertisingIdInfo.getId();
                this.zzc = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzb == null) {
                this.zzb = "";
            }
        } catch (Exception e9) {
            zzq().zzv().zza("Unable to get advertising id", e9);
            this.zzb = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzb, Boolean.valueOf(this.zzc));
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    /* renamed from: f_ */
    public final /* bridge */ /* synthetic */ zzkt mo17540f_() {
        return super.mo17540f_();
    }

    public final Pair<String, Boolean> zza(String str, zzad zzadVar) {
        return (zzmb.zzb() && zzs().zza(zzat.zzcp) && !zzadVar.zzc()) ? new Pair<>("", Boolean.FALSE) : zzb(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzkm
    public final boolean zzd() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzjv zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkn
    public final /* bridge */ /* synthetic */ zzfv zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Deprecated
    public final String zza(String str) throws NoSuchAlgorithmException {
        zzc();
        String str2 = (String) zzb(str).first;
        MessageDigest messageDigestZzh = zzkx.zzh();
        if (messageDigestZzh == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzh.digest(str2.getBytes())));
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }
}
