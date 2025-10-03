package com.google.android.gms.internal.p260authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public class zzd implements IInterface {
    private final IBinder zzd;
    private final String zze;

    public zzd(IBinder iBinder, String str) {
        this.zzd = iBinder;
        this.zze = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzd;
    }

    public final Parcel zzc() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zze);
        return parcelObtain;
    }

    public final void zzc(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzd.transact(i9, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
