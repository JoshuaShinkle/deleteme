package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EventParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzaq();

    @SafeParcelable.Field(m17520id = 2)
    public final String zza;

    @SafeParcelable.Field(m17520id = 3)
    public final zzam zzb;

    @SafeParcelable.Field(m17520id = 4)
    public final String zzc;

    @SafeParcelable.Field(m17520id = 5)
    public final long zzd;

    @SafeParcelable.Constructor
    public zzar(@SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) zzam zzamVar, @SafeParcelable.Param(m17521id = 4) String str2, @SafeParcelable.Param(m17521id = 5) long j9) {
        this.zza = str;
        this.zzb = zzamVar;
        this.zzc = str2;
        this.zzd = j9;
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String strValueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + strValueOf.length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(strValueOf);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i9, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzar(zzar zzarVar, long j9) {
        Preconditions.checkNotNull(zzarVar);
        this.zza = zzarVar.zza;
        this.zzb = zzarVar.zzb;
        this.zzc = zzarVar.zzc;
        this.zzd = j9;
    }
}
