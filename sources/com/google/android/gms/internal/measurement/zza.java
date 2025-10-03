package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    public zza(IBinder iBinder, String str) {
        this.zza = iBinder;
        this.zzb = str;
    }

    /* renamed from: a_ */
    public final Parcel m17533a_() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzb);
        return parcelObtain;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zza;
    }

    public final Parcel zza(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zza.transact(i9, parcel, parcelObtain, 0);
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

    public final void zzb(int i9, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zza.transact(i9, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }
}
