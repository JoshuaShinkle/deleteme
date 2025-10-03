package com.google.android.gms.internal.gtm;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public class zzm implements IInterface {
    private final IBinder zzqx;
    private final String zzqy;

    public zzm(IBinder iBinder, String str) {
        this.zzqx = iBinder;
        this.zzqy = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzqx;
    }

    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzqy);
        return parcelObtain;
    }

    public final Parcel transactAndReadException(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zzqx.transact(i9, parcel, parcelObtain, 0);
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

    public final void zza(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzqx.transact(i9, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    public final void zzb(int i9, Parcel parcel) {
        try {
            this.zzqx.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
