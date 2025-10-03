package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DeviceMetaDataCreator")
/* loaded from: classes2.dex */
public class DeviceMetaData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzv();

    @SafeParcelable.Field(getter = "isLockScreenSolved", m17520id = 2)
    private boolean zzbs;

    @SafeParcelable.Field(getter = "getMinAgeOfLockScreen", m17520id = 3)
    private long zzbt;

    @SafeParcelable.Field(getter = "isChallengeAllowed", m17520id = 4)
    private final boolean zzbu;

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zzv;

    @SafeParcelable.Constructor
    public DeviceMetaData(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) boolean z8, @SafeParcelable.Param(m17521id = 3) long j9, @SafeParcelable.Param(m17521id = 4) boolean z9) {
        this.zzv = i9;
        this.zzbs = z8;
        this.zzbt = j9;
        this.zzbu = z9;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzbt;
    }

    public boolean isChallengeAllowed() {
        return this.zzbu;
    }

    public boolean isLockScreenSolved() {
        return this.zzbs;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeBoolean(parcel, 2, isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel, 3, getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel, 4, isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
