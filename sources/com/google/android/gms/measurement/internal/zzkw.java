package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserAttributeParcelCreator")
/* loaded from: classes2.dex */
public final class zzkw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzkw> CREATOR = new zzkv();

    @SafeParcelable.Field(m17520id = 2)
    public final String zza;

    @SafeParcelable.Field(m17520id = 3)
    public final long zzb;

    @SafeParcelable.Field(m17520id = 4)
    public final Long zzc;

    @SafeParcelable.Field(m17520id = 6)
    public final String zzd;

    @SafeParcelable.Field(m17520id = 7)
    public final String zze;

    @SafeParcelable.Field(m17520id = 8)
    public final Double zzf;

    @SafeParcelable.Field(m17520id = 1)
    private final int zzg;

    @SafeParcelable.Field(m17520id = 5)
    private final Float zzh;

    public zzkw(zzky zzkyVar) {
        this(zzkyVar.zzc, zzkyVar.zzd, zzkyVar.zze, zzkyVar.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 7, this.zze, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final Object zza() {
        Long l9 = this.zzc;
        if (l9 != null) {
            return l9;
        }
        Double d9 = this.zzf;
        if (d9 != null) {
            return d9;
        }
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        return null;
    }

    public zzkw(String str, long j9, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.zzg = 2;
        this.zza = str;
        this.zzb = j9;
        this.zze = str2;
        if (obj == null) {
            this.zzc = null;
            this.zzh = null;
            this.zzf = null;
            this.zzd = null;
            return;
        }
        if (obj instanceof Long) {
            this.zzc = (Long) obj;
            this.zzh = null;
            this.zzf = null;
            this.zzd = null;
            return;
        }
        if (obj instanceof String) {
            this.zzc = null;
            this.zzh = null;
            this.zzf = null;
            this.zzd = (String) obj;
            return;
        }
        if (!(obj instanceof Double)) {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
        this.zzc = null;
        this.zzh = null;
        this.zzf = (Double) obj;
        this.zzd = null;
    }

    @SafeParcelable.Constructor
    public zzkw(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) long j9, @SafeParcelable.Param(m17521id = 4) Long l9, @SafeParcelable.Param(m17521id = 5) Float f9, @SafeParcelable.Param(m17521id = 6) String str2, @SafeParcelable.Param(m17521id = 7) String str3, @SafeParcelable.Param(m17521id = 8) Double d9) {
        this.zzg = i9;
        this.zza = str;
        this.zzb = j9;
        this.zzc = l9;
        this.zzh = null;
        if (i9 == 1) {
            this.zzf = f9 != null ? Double.valueOf(f9.doubleValue()) : null;
        } else {
            this.zzf = d9;
        }
        this.zzd = str2;
        this.zze = str3;
    }
}
