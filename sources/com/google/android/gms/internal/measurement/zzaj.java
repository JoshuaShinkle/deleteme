package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzag;
import java.util.ArrayList;

/* loaded from: classes2.dex */
final class zzaj extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(zzag zzagVar, String str, String str2, Context context, Bundle bundle) {
        super(zzagVar);
        this.zzg = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    public final void zza() {
        String str;
        String str2;
        String str3;
        boolean z8;
        int iMax;
        try {
            this.zzg.zzf = new ArrayList();
            zzag zzagVar = this.zzg;
            if (zzag.zzc(this.zzc, this.zzd)) {
                str3 = this.zzd;
                str2 = this.zzc;
                str = this.zzg.zzc;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            zzag.zzh(this.zze);
            boolean z9 = zzag.zzh.booleanValue() || str2 != null;
            zzag zzagVar2 = this.zzg;
            zzagVar2.zzm = zzagVar2.zza(this.zze, z9);
            if (this.zzg.zzm == null) {
                Log.w(this.zzg.zzc, "Failed to connect to measurement client.");
                return;
            }
            int iZzg = zzag.zzg(this.zze);
            int iZzf = zzag.zzf(this.zze);
            if (z9) {
                iMax = Math.max(iZzg, iZzf);
                z8 = iZzf < iZzg;
            } else {
                if (iZzg > 0) {
                    iZzf = iZzg;
                }
                z8 = iZzg > 0;
                iMax = iZzf;
            }
            this.zzg.zzm.initialize(ObjectWrapper.wrap(this.zze), new zzae(31049L, iMax, z8, str, str2, str3, this.zzf), this.zza);
        } catch (Exception e9) {
            this.zzg.zza(e9, true, false);
        }
    }
}
