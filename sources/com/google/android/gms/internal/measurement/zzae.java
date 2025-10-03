package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "InitializationParamsCreator")
/* loaded from: classes2.dex */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzah();

    @SafeParcelable.Field(m17520id = 1)
    public final long zza;

    @SafeParcelable.Field(m17520id = 2)
    public final long zzb;

    @SafeParcelable.Field(m17520id = 3)
    public final boolean zzc;

    @SafeParcelable.Field(m17520id = 4)
    public final String zzd;

    @SafeParcelable.Field(m17520id = 5)
    public final String zze;

    @SafeParcelable.Field(m17520id = 6)
    public final String zzf;

    @SafeParcelable.Field(m17520id = 7)
    public final Bundle zzg;

    @SafeParcelable.Constructor
    public zzae(@SafeParcelable.Param(m17521id = 1) long j9, @SafeParcelable.Param(m17521id = 2) long j10, @SafeParcelable.Param(m17521id = 3) boolean z8, @SafeParcelable.Param(m17521id = 4) String str, @SafeParcelable.Param(m17521id = 5) String str2, @SafeParcelable.Param(m17521id = 6) String str3, @SafeParcelable.Param(m17521id = 7) Bundle bundle) {
        this.zza = j9;
        this.zzb = j10;
        this.zzc = z8;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = bundle;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
