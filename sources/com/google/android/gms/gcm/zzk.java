package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
final class zzk implements Parcelable.Creator<PeriodicTask> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PeriodicTask createFromParcel(Parcel parcel) {
        return new PeriodicTask(parcel, (zzk) null);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PeriodicTask[] newArray(int i9) {
        return new PeriodicTask[i9];
    }
}
