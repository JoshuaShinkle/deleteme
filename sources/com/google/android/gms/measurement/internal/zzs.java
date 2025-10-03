package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzms;
import java.util.HashSet;
import java.util.Iterator;
import p132m.C5302a;

/* loaded from: classes2.dex */
final class zzs extends zzv {
    private zzbv.zzb zzg;
    private final /* synthetic */ zzo zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzo zzoVar, String str, int i9, zzbv.zzb zzbVar) {
        super(str, i9);
        this.zzh = zzoVar;
        this.zzg = zzbVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final int zza() {
        return this.zzg.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final boolean zzb() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzv
    public final boolean zzc() {
        return this.zzg.zzf();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zza(Long l9, Long l10, zzcd.zzc zzcVar, long j9, zzan zzanVar, boolean z8) {
        Boolean boolZza;
        Object[] objArr = zzms.zzb() && this.zzh.zzs().zzd(this.zza, zzat.zzbc);
        long j10 = this.zzg.zzk() ? zzanVar.zze : j9;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        Boolean bool = null;
        if (this.zzh.zzq().zza(2)) {
            this.zzh.zzq().zzw().zza("Evaluating filter. audience, filter, event", Integer.valueOf(this.zzb), this.zzg.zza() ? Integer.valueOf(this.zzg.zzb()) : null, this.zzh.zzn().zza(this.zzg.zzc()));
            this.zzh.zzq().zzw().zza("Filter definition", this.zzh.mo17540f_().zza(this.zzg));
        }
        if (!this.zzg.zza() || this.zzg.zzb() > 256) {
            this.zzh.zzq().zzh().zza("Invalid event filter ID. appId, id", zzex.zza(this.zza), String.valueOf(this.zzg.zza() ? Integer.valueOf(this.zzg.zzb()) : null));
            return false;
        }
        Object[] objArr2 = this.zzg.zzh() || this.zzg.zzi() || this.zzg.zzk();
        if (z8 && objArr2 != true) {
            this.zzh.zzq().zzw().zza("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzb), this.zzg.zza() ? Integer.valueOf(this.zzg.zzb()) : null);
            return true;
        }
        zzbv.zzb zzbVar = this.zzg;
        String strZzc = zzcVar.zzc();
        if (!zzbVar.zzf()) {
            HashSet hashSet = new HashSet();
            Iterator<zzbv.zzc> it = zzbVar.zzd().iterator();
            while (true) {
                if (!it.hasNext()) {
                    C5302a c5302a = new C5302a();
                    Iterator<zzcd.zze> it2 = zzcVar.zza().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            Iterator<zzbv.zzc> it3 = zzbVar.zzd().iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    bool = Boolean.TRUE;
                                    break;
                                }
                                zzbv.zzc next = it3.next();
                                boolean z9 = next.zze() && next.zzf();
                                String strZzh = next.zzh();
                                if (strZzh.isEmpty()) {
                                    this.zzh.zzq().zzh().zza("Event has empty param name. event", this.zzh.zzn().zza(strZzc));
                                    break;
                                }
                                Object obj = c5302a.get(strZzh);
                                if (obj instanceof Long) {
                                    if (!next.zzc()) {
                                        this.zzh.zzq().zzh().zza("No number filter for long param. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                        break;
                                    }
                                    Boolean boolZza2 = zzv.zza(((Long) obj).longValue(), next.zzd());
                                    if (boolZza2 == null) {
                                        break;
                                    }
                                    if (boolZza2.booleanValue() == z9) {
                                        bool = Boolean.FALSE;
                                        break;
                                    }
                                } else if (obj instanceof Double) {
                                    if (!next.zzc()) {
                                        this.zzh.zzq().zzh().zza("No number filter for double param. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                        break;
                                    }
                                    Boolean boolZza3 = zzv.zza(((Double) obj).doubleValue(), next.zzd());
                                    if (boolZza3 == null) {
                                        break;
                                    }
                                    if (boolZza3.booleanValue() == z9) {
                                        bool = Boolean.FALSE;
                                        break;
                                    }
                                } else if (obj instanceof String) {
                                    if (!next.zza()) {
                                        if (!next.zzc()) {
                                            this.zzh.zzq().zzh().zza("No filter for String param. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                            break;
                                        }
                                        String str = (String) obj;
                                        if (!zzkt.zza(str)) {
                                            this.zzh.zzq().zzh().zza("Invalid param value for number filter. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                            break;
                                        }
                                        boolZza = zzv.zza(str, next.zzd());
                                    } else {
                                        boolZza = zzv.zza((String) obj, next.zzb(), this.zzh.zzq());
                                    }
                                    if (boolZza == null) {
                                        break;
                                    }
                                    if (boolZza.booleanValue() == z9) {
                                        bool = Boolean.FALSE;
                                        break;
                                    }
                                } else if (obj == null) {
                                    this.zzh.zzq().zzw().zza("Missing param for filter. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                    bool = Boolean.FALSE;
                                } else {
                                    this.zzh.zzq().zzh().zza("Unknown param type. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(strZzh));
                                }
                            }
                        } else {
                            zzcd.zze next2 = it2.next();
                            if (hashSet.contains(next2.zzb())) {
                                if (!next2.zze()) {
                                    if (!next2.zzi()) {
                                        if (!next2.zzc()) {
                                            this.zzh.zzq().zzh().zza("Unknown value for param. event, param", this.zzh.zzn().zza(strZzc), this.zzh.zzn().zzb(next2.zzb()));
                                            break;
                                        }
                                        c5302a.put(next2.zzb(), next2.zzd());
                                    } else {
                                        c5302a.put(next2.zzb(), next2.zzi() ? Double.valueOf(next2.zzj()) : null);
                                    }
                                } else {
                                    c5302a.put(next2.zzb(), next2.zze() ? Long.valueOf(next2.zzf()) : null);
                                }
                            }
                        }
                    }
                } else {
                    zzbv.zzc next3 = it.next();
                    if (next3.zzh().isEmpty()) {
                        this.zzh.zzq().zzh().zza("null or empty param name in filter. event", this.zzh.zzn().zza(strZzc));
                        break;
                    }
                    hashSet.add(next3.zzh());
                }
            }
        } else {
            Boolean boolZza4 = zzv.zza(j10, zzbVar.zzg());
            if (boolZza4 != null) {
                if (!boolZza4.booleanValue()) {
                    bool = Boolean.FALSE;
                }
            }
        }
        this.zzh.zzq().zzw().zza("Event filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        Boolean bool2 = Boolean.TRUE;
        this.zzc = bool2;
        if (!bool.booleanValue()) {
            return true;
        }
        this.zzd = bool2;
        if (objArr2 != false && zzcVar.zzd()) {
            Long lValueOf = Long.valueOf(zzcVar.zze());
            if (this.zzg.zzi()) {
                if (objArr != false && this.zzg.zzf()) {
                    lValueOf = l9;
                }
                this.zzf = lValueOf;
            } else {
                if (objArr != false && this.zzg.zzf()) {
                    lValueOf = l10;
                }
                this.zze = lValueOf;
            }
        }
        return true;
    }
}
