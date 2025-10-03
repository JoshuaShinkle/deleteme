package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleCertificatesLookupResponseCreator")
/* loaded from: classes2.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();

    @SafeParcelable.Field(getter = "getResult", m17520id = 1)
    private final boolean zza;

    @SafeParcelable.Field(getter = "getErrorMessage", m17520id = 2)
    private final String zzb;

    @SafeParcelable.Field(getter = "getStatusValue", m17520id = 3)
    private final int zzc;

    @SafeParcelable.Field(getter = "getFirstPartyStatusValue", m17520id = 4)
    private final int zzd;

    @SafeParcelable.Constructor
    public zzq(@SafeParcelable.Param(m17521id = 1) boolean z8, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) int i9, @SafeParcelable.Param(m17521id = 4) int i10) {
        this.zza = z8;
        this.zzb = str;
        this.zzc = zzy.zza(i9) - 1;
        this.zzd = zzd.zza(i10) - 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zza;
    }

    public final int zzc() {
        return zzd.zza(this.zzd);
    }

    public final int zzd() {
        return zzy.zza(this.zzc);
    }
}
