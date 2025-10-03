package com.google.android.gms.common.internal.service;

import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zal extends com.google.android.gms.internal.base.zaa implements zai {
    public zal() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zaa
    public final boolean zaa(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zaa(parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
