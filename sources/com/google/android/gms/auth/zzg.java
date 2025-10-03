package com.google.android.gms.auth;

import android.os.IBinder;
import java.util.List;

/* loaded from: classes2.dex */
final class zzg implements zzj<List<AccountChangeEvent>> {
    private final /* synthetic */ String zzr;
    private final /* synthetic */ int zzs;

    public zzg(String str, int i9) {
        this.zzr = str;
        this.zzs = i9;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ List<AccountChangeEvent> zzb(IBinder iBinder) {
        return ((AccountChangeEventsResponse) zzd.zza(com.google.android.gms.internal.auth.zzf.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzr).setEventIndex(this.zzs)))).getEvents();
    }
}
