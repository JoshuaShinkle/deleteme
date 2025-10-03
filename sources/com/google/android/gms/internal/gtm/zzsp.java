package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzsp<T> implements zzsz<T> {
    private final zzsk zzbdc;
    private final boolean zzbdd;
    private final zztr<?, ?> zzbdm;
    private final zzqq<?> zzbdn;

    private zzsp(zztr<?, ?> zztrVar, zzqq<?> zzqqVar, zzsk zzskVar) {
        this.zzbdm = zztrVar;
        this.zzbdd = zzqqVar.zze(zzskVar);
        this.zzbdn = zzqqVar;
        this.zzbdc = zzskVar;
    }

    public static <T> zzsp<T> zza(zztr<?, ?> zztrVar, zzqq<?> zzqqVar, zzsk zzskVar) {
        return new zzsp<>(zztrVar, zzqqVar, zzskVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final boolean equals(T t8, T t9) {
        if (!this.zzbdm.zzag(t8).equals(this.zzbdm.zzag(t9))) {
            return false;
        }
        if (this.zzbdd) {
            return this.zzbdn.zzr(t8).equals(this.zzbdn.zzr(t9));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final int hashCode(T t8) {
        int iHashCode = this.zzbdm.zzag(t8).hashCode();
        return this.zzbdd ? (iHashCode * 53) + this.zzbdn.zzr(t8).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final T newInstance() {
        return (T) this.zzbdc.zzph().zzpl();
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final int zzad(T t8) {
        zztr<?, ?> zztrVar = this.zzbdm;
        int iZzai = zztrVar.zzai(zztrVar.zzag(t8)) + 0;
        return this.zzbdd ? iZzai + this.zzbdn.zzr(t8).zzow() : iZzai;
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final boolean zzae(T t8) {
        return this.zzbdn.zzr(t8).isInitialized();
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final void zzd(T t8, T t9) {
        zztb.zza(this.zzbdm, t8, t9);
        if (this.zzbdd) {
            zztb.zza(this.zzbdn, t8, t9);
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final void zzt(T t8) {
        this.zzbdm.zzt(t8);
        this.zzbdn.zzt(t8);
    }

    @Override // com.google.android.gms.internal.gtm.zzsz
    public final void zza(T t8, zzum zzumVar) {
        Iterator it = this.zzbdn.zzr(t8).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzqv zzqvVar = (zzqv) entry.getKey();
            if (zzqvVar.zzoy() != zzul.MESSAGE || zzqvVar.zzoz() || zzqvVar.zzpa()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzrp) {
                zzumVar.zza(zzqvVar.zzc(), (Object) ((zzrp) entry).zzpz().zzmv());
            } else {
                zzumVar.zza(zzqvVar.zzc(), entry.getValue());
            }
        }
        zztr<?, ?> zztrVar = this.zzbdm;
        zztrVar.zzc(zztrVar.zzag(t8), zzumVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:0: B:45:0x000c->B:53:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.gtm.zzsz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(T t8, zzsy zzsyVar, zzqp zzqpVar) {
        boolean zZzoh;
        zztr<?, ?> zztrVar = this.zzbdm;
        zzqq<?> zzqqVar = this.zzbdn;
        Object objZzah = zztrVar.zzah(t8);
        zzqt<T> zzqtVarZzs = zzqqVar.zzs(t8);
        while (zzsyVar.zzog() != Integer.MAX_VALUE) {
            try {
                int tag = zzsyVar.getTag();
                if (tag != 11) {
                    if ((tag & 7) == 2) {
                        Object objZza = zzqqVar.zza(zzqpVar, this.zzbdc, tag >>> 3);
                        if (objZza != null) {
                            zzqqVar.zza(zzsyVar, objZza, zzqpVar, zzqtVarZzs);
                        } else {
                            zZzoh = zztrVar.zza((zztr<?, ?>) objZzah, zzsyVar);
                        }
                    } else {
                        zZzoh = zzsyVar.zzoh();
                    }
                    if (zZzoh) {
                        return;
                    }
                } else {
                    Object objZza2 = null;
                    int iZznr = 0;
                    zzps zzpsVarZznq = null;
                    while (zzsyVar.zzog() != Integer.MAX_VALUE) {
                        int tag2 = zzsyVar.getTag();
                        if (tag2 == 16) {
                            iZznr = zzsyVar.zznr();
                            objZza2 = zzqqVar.zza(zzqpVar, this.zzbdc, iZznr);
                        } else if (tag2 == 26) {
                            if (objZza2 != null) {
                                zzqqVar.zza(zzsyVar, objZza2, zzqpVar, zzqtVarZzs);
                            } else {
                                zzpsVarZznq = zzsyVar.zznq();
                            }
                        } else if (!zzsyVar.zzoh()) {
                            break;
                        }
                    }
                    if (zzsyVar.getTag() != 12) {
                        throw zzrk.zzps();
                    }
                    if (zzpsVarZznq != null) {
                        if (objZza2 != null) {
                            zzqqVar.zza(zzpsVarZznq, objZza2, zzqpVar, zzqtVarZzs);
                        } else {
                            zztrVar.zza((zztr<?, ?>) objZzah, iZznr, zzpsVarZznq);
                        }
                    }
                }
                zZzoh = true;
                if (zZzoh) {
                }
            } finally {
                zztrVar.zzg(t8, objZzah);
            }
        }
    }
}
