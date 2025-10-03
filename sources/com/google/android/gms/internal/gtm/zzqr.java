package com.google.android.gms.internal.gtm;

import com.google.android.gms.internal.gtm.zzrc;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzqr extends zzqq<Object> {
    @Override // com.google.android.gms.internal.gtm.zzqq
    public final <UT, UB> UB zza(zzsy zzsyVar, Object obj, zzqp zzqpVar, zzqt<Object> zzqtVar, UB ub, zztr<UT, UB> zztrVar) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final int zzb(Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final boolean zze(zzsk zzskVar) {
        return zzskVar instanceof zzrc.zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final zzqt<Object> zzr(Object obj) {
        return ((zzrc.zzc) obj).zzbaq;
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final zzqt<Object> zzs(Object obj) {
        zzrc.zzc zzcVar = (zzrc.zzc) obj;
        if (zzcVar.zzbaq.isImmutable()) {
            zzcVar.zzbaq = (zzqt) zzcVar.zzbaq.clone();
        }
        return zzcVar.zzbaq;
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final void zzt(Object obj) {
        zzr(obj).zzmi();
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final void zza(zzum zzumVar, Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final Object zza(zzqp zzqpVar, zzsk zzskVar, int i9) {
        return zzqpVar.zza(zzskVar, i9);
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final void zza(zzsy zzsyVar, Object obj, zzqp zzqpVar, zzqt<Object> zzqtVar) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.gtm.zzqq
    public final void zza(zzps zzpsVar, Object obj, zzqp zzqpVar, zzqt<Object> zzqtVar) {
        throw new NoSuchMethodError();
    }
}
