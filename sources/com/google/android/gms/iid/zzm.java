package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzm extends com.google.android.gms.internal.gcm.zzd implements zzl {
    public zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    @Override // com.google.android.gms.iid.zzl
    public final void send(Message message) {
        Parcel parcelZzd = zzd();
        com.google.android.gms.internal.gcm.zze.zzd(parcelZzd, message);
        zze(1, parcelZzd);
    }
}
