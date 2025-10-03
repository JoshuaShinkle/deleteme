package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConnectionInfoCreator")
/* loaded from: classes2.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();

    @SafeParcelable.Field(m17520id = 1)
    Bundle zza;

    @SafeParcelable.Field(m17520id = 2)
    Feature[] zzb;

    @SafeParcelable.Field(defaultValue = "0", m17520id = 3)
    int zzc;

    @SafeParcelable.Field(m17520id = 4)
    ConnectionTelemetryConfiguration zzd;

    public zzj() {
    }

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(m17521id = 1) Bundle bundle, @SafeParcelable.Param(m17521id = 2) Feature[] featureArr, @SafeParcelable.Param(m17521id = 3) int i9, @SafeParcelable.Param(m17521id = 4) ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.zza = bundle;
        this.zzb = featureArr;
        this.zzc = i9;
        this.zzd = connectionTelemetryConfiguration;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzb, i9, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
