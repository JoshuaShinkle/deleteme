package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public class zab implements IInterface {
    private final IBinder zaa;
    private final String zab;

    public zab(IBinder iBinder, String str) {
        this.zaa = iBinder;
        this.zab = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zaa;
    }

    public final Parcel zaa() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zab);
        return parcelObtain;
    }

    public final void zab(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zaa.transact(i9, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    public final void zac(int i9, Parcel parcel) {
        try {
            this.zaa.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }

    public final Parcel zaa(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zaa.transact(2, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e9) {
                parcelObtain.recycle();
                throw e9;
            }
        } finally {
            parcel.recycle();
        }
    }
}
