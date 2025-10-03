package com.google.android.gms.internal.base;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public class zaa extends Binder implements IInterface {
    private static zac zaa;

    public zaa(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) throws RemoteException {
        boolean zOnTransact;
        if (i9 > 16777215) {
            zOnTransact = super.onTransact(i9, parcel, parcel2, i10);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            zOnTransact = false;
        }
        if (zOnTransact) {
            return true;
        }
        return zaa(i9, parcel, parcel2, i10);
    }

    public boolean zaa(int i9, Parcel parcel, Parcel parcel2, int i10) {
        return false;
    }
}
