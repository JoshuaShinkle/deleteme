package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzmb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zziv extends zzg {
    private final zzjp zza;
    private zzep zzb;
    private volatile Boolean zzc;
    private final zzaj zzd;
    private final zzki zze;
    private final List<Runnable> zzf;
    private final zzaj zzg;

    public zziv(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzf = new ArrayList();
        this.zze = new zzki(zzgbVar.zzl());
        this.zza = new zzjp(this);
        this.zzd = new zziu(this, zzgbVar);
        this.zzg = new zzje(this, zzgbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzaj() {
        zzc();
        this.zze.zza();
        this.zzd.zza(zzat.zzai.zza(null).longValue());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zzak() {
        boolean z8;
        zzc();
        zzv();
        if (this.zzc == null) {
            zzc();
            zzv();
            Boolean boolZzi = zzr().zzi();
            if (boolZzi == null || !boolZzi.booleanValue()) {
                boolean z9 = false;
                if (zzf().zzaf() == 1) {
                    z8 = true;
                    if (z && zzs().zzw()) {
                        zzq().zze().zza("No way to upload. Consider using the full version of Analytics");
                    } else {
                        z9 = z8;
                    }
                    if (z9) {
                        zzr().zza(z);
                    }
                } else {
                    zzq().zzw().zza("Checking service availability");
                    int iZza = zzo().zza(12451000);
                    if (iZza != 0) {
                        if (iZza != 1) {
                            if (iZza != 2) {
                                if (iZza == 3) {
                                    zzq().zzh().zza("Service disabled");
                                } else if (iZza == 9) {
                                    zzq().zzh().zza("Service invalid");
                                } else if (iZza != 18) {
                                    zzq().zzh().zza("Unexpected service status", Integer.valueOf(iZza));
                                } else {
                                    zzq().zzh().zza("Service updating");
                                }
                                z8 = false;
                                z = false;
                            } else {
                                zzq().zzv().zza("Service container out of date");
                                if (zzo().zzi() >= 17443) {
                                    z = boolZzi == null;
                                    z8 = false;
                                }
                            }
                            if (z) {
                                z9 = z8;
                                if (z9) {
                                }
                            }
                        } else {
                            zzq().zzw().zza("Service missing");
                        }
                        z8 = true;
                        z = false;
                        if (z) {
                        }
                    } else {
                        zzq().zzw().zza("Service available");
                    }
                    z8 = true;
                    if (z) {
                    }
                }
            }
            this.zzc = Boolean.valueOf(z);
        }
        return this.zzc.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzal() {
        zzc();
        if (zzaa()) {
            zzq().zzw().zza("Inactivity, disconnecting from the service");
            zzag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzam() {
        zzc();
        zzq().zzw().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        Iterator<Runnable> it = this.zzf.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (Exception e9) {
                zzq().zze().zza("Task exception while flushing queue", e9);
            }
        }
        this.zzf.clear();
        this.zzg.zzc();
    }

    private final zzn zzb(boolean z8) {
        return zzf().zza(z8 ? zzq().zzx() : null);
    }

    public final void zza(boolean z8) {
        if (zzmb.zzb() && zzs().zza(zzat.zzco)) {
            zzc();
            zzv();
            if (z8) {
                zzi().zzaa();
            }
            if (zzai()) {
                zza(new zzjj(this, zzb(false)));
            }
        }
    }

    public final boolean zzaa() {
        zzc();
        zzv();
        return this.zzb != null;
    }

    public final void zzab() {
        zzc();
        zzv();
        zza(new zzjg(this, zzb(true)));
    }

    public final void zzac() {
        zzc();
        zzv();
        zzn zznVarZzb = zzb(false);
        zzi().zzaa();
        zza(new zziy(this, zznVarZzb));
    }

    public final void zzad() {
        zzc();
        zzv();
        zzn zznVarZzb = zzb(true);
        zzi().zzab();
        zza(new zzjd(this, zznVarZzb));
    }

    public final void zzae() {
        zzc();
        zzv();
        if (zzaa()) {
            return;
        }
        if (zzak()) {
            this.zza.zzb();
            return;
        }
        if (zzs().zzw()) {
            return;
        }
        List<ResolveInfo> listQueryIntentServices = zzm().getPackageManager().queryIntentServices(new Intent().setClassName(zzm(), "com.google.android.gms.measurement.AppMeasurementService"), C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        if (!(listQueryIntentServices != null && listQueryIntentServices.size() > 0)) {
            zzq().zze().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        intent.setComponent(new ComponentName(zzm(), "com.google.android.gms.measurement.AppMeasurementService"));
        this.zza.zza(intent);
    }

    public final Boolean zzaf() {
        return this.zzc;
    }

    public final void zzag() {
        zzc();
        zzv();
        this.zza.zza();
        try {
            ConnectionTracker.getInstance().unbindService(zzm(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    public final boolean zzah() {
        zzc();
        zzv();
        return !zzak() || zzo().zzi() >= 200900;
    }

    public final boolean zzai() {
        zzc();
        zzv();
        if (zzs().zza(zzat.zzcp)) {
            return !zzak() || zzo().zzi() >= zzat.zzcq.zza(null).intValue();
        }
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhe zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziv zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzim zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzet zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzkb zzj() {
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

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzy() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @VisibleForTesting
    public final void zza(zzep zzepVar, AbstractSafeParcelable abstractSafeParcelable, zzn zznVar) throws Throwable {
        int size;
        zzc();
        zzv();
        int i9 = 100;
        int i10 = 0;
        while (i10 < 1001 && i9 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> listZza = zzi().zza(100);
            if (listZza != null) {
                arrayList.addAll(listZza);
                size = listZza.size();
            } else {
                size = 0;
            }
            if (abstractSafeParcelable != null && size < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size2 = arrayList.size();
            int i11 = 0;
            while (i11 < size2) {
                Object obj = arrayList.get(i11);
                i11++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzar) {
                    try {
                        zzepVar.zza((zzar) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e9) {
                        zzq().zze().zza("Failed to send event to the service", e9);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkw) {
                    try {
                        zzepVar.zza((zzkw) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e10) {
                        zzq().zze().zza("Failed to send user property to the service", e10);
                    }
                } else if (abstractSafeParcelable2 instanceof zzw) {
                    try {
                        zzepVar.zza((zzw) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e11) {
                        zzq().zze().zza("Failed to send conditional user property to the service", e11);
                    }
                } else {
                    zzq().zze().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i10++;
            i9 = size;
        }
    }

    public final void zza(zzar zzarVar, String str) {
        Preconditions.checkNotNull(zzarVar);
        zzc();
        zzv();
        zza(new zzji(this, true, zzi().zza(zzarVar), zzarVar, zzb(true), str));
    }

    public final void zza(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        zzc();
        zzv();
        zza(new zzjl(this, true, zzi().zza(zzwVar), new zzw(zzwVar), zzb(true), zzwVar));
    }

    public final void zza(AtomicReference<List<zzw>> atomicReference, String str, String str2, String str3) {
        zzc();
        zzv();
        zza(new zzjk(this, atomicReference, str, str2, str3, zzb(false)));
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, String str, String str2) {
        zzc();
        zzv();
        zza(new zzjn(this, str, str2, zzb(false), zzwVar));
    }

    public final void zza(AtomicReference<List<zzkw>> atomicReference, String str, String str2, String str3, boolean z8) {
        zzc();
        zzv();
        zza(new zzjm(this, atomicReference, str, str2, str3, z8, zzb(false)));
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, String str, String str2, boolean z8) {
        zzc();
        zzv();
        zza(new zzix(this, str, str2, z8, zzb(false), zzwVar));
    }

    public final void zza(zzkw zzkwVar) {
        zzc();
        zzv();
        zza(new zziw(this, zzi().zza(zzkwVar), zzkwVar, zzb(true)));
    }

    public final void zza(AtomicReference<List<zzkw>> atomicReference, boolean z8) {
        zzc();
        zzv();
        zza(new zziz(this, atomicReference, zzb(false), z8));
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzc();
        zzv();
        zza(new zzjb(this, atomicReference, zzb(false)));
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar) {
        zzc();
        zzv();
        zza(new zzja(this, zzb(false), zzwVar));
    }

    public final void zza(zzin zzinVar) {
        zzc();
        zzv();
        zza(new zzjc(this, zzinVar));
    }

    public final void zza(Bundle bundle) {
        zzc();
        zzv();
        zza(new zzjf(this, bundle, zzb(false)));
    }

    @VisibleForTesting
    public final void zza(zzep zzepVar) {
        zzc();
        Preconditions.checkNotNull(zzepVar);
        this.zzb = zzepVar;
        zzaj();
        zzam();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(ComponentName componentName) {
        zzc();
        if (this.zzb != null) {
            this.zzb = null;
            zzq().zzw().zza("Disconnected from device MeasurementService", componentName);
            zzc();
            zzae();
        }
    }

    private final void zza(Runnable runnable) {
        zzc();
        if (zzaa()) {
            runnable.run();
        } else {
            if (this.zzf.size() >= 1000) {
                zzq().zze().zza("Discarding data. Max runnable queue size reached");
                return;
            }
            this.zzf.add(runnable);
            this.zzg.zza(60000L);
            zzae();
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, zzar zzarVar, String str) {
        zzc();
        zzv();
        if (zzo().zza(12451000) != 0) {
            zzq().zzh().zza("Not bundling data. Service unavailable or out of date");
            zzo().zza(zzwVar, new byte[0]);
        } else {
            zza(new zzjh(this, zzarVar, str, zzwVar));
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public static /* synthetic */ zzep zza(zziv zzivVar, zzep zzepVar) {
        zzivVar.zzb = null;
        return null;
    }
}
