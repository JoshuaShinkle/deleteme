package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzag;

/* loaded from: classes2.dex */
final class zzbr extends zzag.zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzag.zzc zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbr(zzag.zzc zzcVar, Activity activity) {
        super(zzag.this);
        this.zzd = zzcVar;
        this.zzc = activity;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        zzag.this.zzm.onActivityResumed(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
