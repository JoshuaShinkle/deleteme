package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzh extends com.google.android.gms.internal.gcm.zzd implements zzg {
    public zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }

    @Override // com.google.android.gms.gcm.zzg
    public final void zzf(int i9) {
        Parcel parcelZzd = zzd();
        parcelZzd.writeInt(i9);
        zzd(2, parcelZzd);
    }
}
