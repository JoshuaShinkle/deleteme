package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zzx extends com.google.android.gms.internal.common.zza implements zzz {
    public zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    @Override // com.google.android.gms.common.internal.zzz
    public final int zzc() {
        Parcel parcelZzB = zzB(2, zza());
        int i9 = parcelZzB.readInt();
        parcelZzB.recycle();
        return i9;
    }

    @Override // com.google.android.gms.common.internal.zzz
    public final IObjectWrapper zzd() {
        Parcel parcelZzB = zzB(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzB.readStrongBinder());
        parcelZzB.recycle();
        return iObjectWrapperAsInterface;
    }
}
