package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzaa extends com.google.android.gms.internal.common.zza implements IGmsCallbacks {
    public zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int i9, IBinder iBinder, Bundle bundle) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        parcelZza.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzc.zzd(parcelZza, bundle);
        zzC(1, parcelZza);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzb(int i9, Bundle bundle) {
        throw null;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzc(int i9, IBinder iBinder, zzj zzjVar) {
        throw null;
    }
}
