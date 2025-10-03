package com.google.android.gms.internal.gcm;

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

    public final Parcel zzd() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zze);
        return parcelObtain;
    }

    public final void zze(int i9, Parcel parcel) {
        try {
            this.zzd.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }

    public final void zzd(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzd.transact(2, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
