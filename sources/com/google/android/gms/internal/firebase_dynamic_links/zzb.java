package com.google.android.gms.internal.firebase_dynamic_links;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public class zzb implements IInterface {
    private final IBinder zzb;
    private final String zzc;

    public zzb(IBinder iBinder, String str) {
        this.zzb = iBinder;
        this.zzc = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzb;
    }

    public final Parcel zza() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzc);
        return parcelObtain;
    }

    public final void zza(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzb.transact(i9, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
