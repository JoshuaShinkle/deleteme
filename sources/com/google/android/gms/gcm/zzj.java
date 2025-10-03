package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
final class zzj implements Parcelable.Creator<PendingCallback> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PendingCallback createFromParcel(Parcel parcel) {
        return new PendingCallback(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PendingCallback[] newArray(int i9) {
        return new PendingCallback[i9];
    }
}
