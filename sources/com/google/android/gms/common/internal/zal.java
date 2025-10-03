package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zal extends com.google.android.gms.internal.base.zab implements zam {
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    @Override // com.google.android.gms.common.internal.zam
    public final IObjectWrapper zaa(IObjectWrapper iObjectWrapper, zau zauVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, iObjectWrapper);
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, zauVar);
        Parcel parcelZaa2 = zaa(2, parcelZaa);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZaa2.readStrongBinder());
        parcelZaa2.recycle();
        return iObjectWrapperAsInterface;
    }
}
