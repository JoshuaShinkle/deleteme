package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
final class zzi implements Parcelable.Creator<OneoffTask> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OneoffTask createFromParcel(Parcel parcel) {
        return new OneoffTask(parcel, (zzi) null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OneoffTask[] newArray(int i9) {
        return new OneoffTask[i9];
    }
}
