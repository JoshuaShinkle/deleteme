package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "AppMetadataCreator")
@SafeParcelable.Reserved({1, 20})
/* loaded from: classes2.dex */
public final class zzn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzn> CREATOR = new zzm();

    @SafeParcelable.Field(m17520id = 2)
    public final String zza;

    @SafeParcelable.Field(m17520id = 3)
    public final String zzb;

    @SafeParcelable.Field(m17520id = 4)
    public final String zzc;

    @SafeParcelable.Field(m17520id = 5)
    public final String zzd;

    @SafeParcelable.Field(m17520id = 6)
    public final long zze;

    @SafeParcelable.Field(m17520id = 7)
    public final long zzf;

    @SafeParcelable.Field(m17520id = 8)
    public final String zzg;

    @SafeParcelable.Field(defaultValue = "true", m17520id = 9)
    public final boolean zzh;

    @SafeParcelable.Field(m17520id = 10)
    public final boolean zzi;

    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MIN_VALUE", m17520id = 11)
    public final long zzj;

    @SafeParcelable.Field(m17520id = 12)
    public final String zzk;

    @SafeParcelable.Field(m17520id = 13)
    public final long zzl;

    @SafeParcelable.Field(m17520id = 14)
    public final long zzm;

    @SafeParcelable.Field(m17520id = 15)
    public final int zzn;

    @SafeParcelable.Field(defaultValue = "true", m17520id = 16)
    public final boolean zzo;

    @SafeParcelable.Field(defaultValue = "true", m17520id = 17)
    public final boolean zzp;

    @SafeParcelable.Field(m17520id = 18)
    public final boolean zzq;

    @SafeParcelable.Field(m17520id = 19)
    public final String zzr;

    @SafeParcelable.Field(m17520id = 21)
    public final Boolean zzs;

    @SafeParcelable.Field(m17520id = 22)
    public final long zzt;

    @SafeParcelable.Field(m17520id = 23)
    public final List<String> zzu;

    @SafeParcelable.Field(m17520id = 24)
    public final String zzv;

    @SafeParcelable.Field(defaultValue = "", m17520id = 25)
    public final String zzw;

    public zzn(String str, String str2, String str3, long j9, String str4, long j10, long j11, String str5, boolean z8, boolean z9, String str6, long j12, long j13, int i9, boolean z10, boolean z11, boolean z12, String str7, Boolean bool, long j14, List<String> list, String str8, String str9) {
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = TextUtils.isEmpty(str2) ? null : str2;
        this.zzc = str3;
        this.zzj = j9;
        this.zzd = str4;
        this.zze = j10;
        this.zzf = j11;
        this.zzg = str5;
        this.zzh = z8;
        this.zzi = z9;
        this.zzk = str6;
        this.zzl = j12;
        this.zzm = j13;
        this.zzn = i9;
        this.zzo = z10;
        this.zzp = z11;
        this.zzq = z12;
        this.zzr = str7;
        this.zzs = bool;
        this.zzt = j14;
        this.zzu = list;
        this.zzv = str8;
        this.zzw = str9;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zze);
        SafeParcelWriter.writeLong(parcel, 7, this.zzf);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzl);
        SafeParcelWriter.writeLong(parcel, 14, this.zzm);
        SafeParcelWriter.writeInt(parcel, 15, this.zzn);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzp);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzq);
        SafeParcelWriter.writeString(parcel, 19, this.zzr, false);
        SafeParcelWriter.writeBooleanObject(parcel, 21, this.zzs, false);
        SafeParcelWriter.writeLong(parcel, 22, this.zzt);
        SafeParcelWriter.writeStringList(parcel, 23, this.zzu, false);
        SafeParcelWriter.writeString(parcel, 24, this.zzv, false);
        SafeParcelWriter.writeString(parcel, 25, this.zzw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzn(@SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) String str2, @SafeParcelable.Param(m17521id = 4) String str3, @SafeParcelable.Param(m17521id = 5) String str4, @SafeParcelable.Param(m17521id = 6) long j9, @SafeParcelable.Param(m17521id = 7) long j10, @SafeParcelable.Param(m17521id = 8) String str5, @SafeParcelable.Param(m17521id = 9) boolean z8, @SafeParcelable.Param(m17521id = 10) boolean z9, @SafeParcelable.Param(m17521id = 11) long j11, @SafeParcelable.Param(m17521id = 12) String str6, @SafeParcelable.Param(m17521id = 13) long j12, @SafeParcelable.Param(m17521id = 14) long j13, @SafeParcelable.Param(m17521id = 15) int i9, @SafeParcelable.Param(m17521id = 16) boolean z10, @SafeParcelable.Param(m17521id = 17) boolean z11, @SafeParcelable.Param(m17521id = 18) boolean z12, @SafeParcelable.Param(m17521id = 19) String str7, @SafeParcelable.Param(m17521id = 21) Boolean bool, @SafeParcelable.Param(m17521id = 22) long j14, @SafeParcelable.Param(m17521id = 23) List<String> list, @SafeParcelable.Param(m17521id = 24) String str8, @SafeParcelable.Param(m17521id = 25) String str9) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzj = j11;
        this.zzd = str4;
        this.zze = j9;
        this.zzf = j10;
        this.zzg = str5;
        this.zzh = z8;
        this.zzi = z9;
        this.zzk = str6;
        this.zzl = j12;
        this.zzm = j13;
        this.zzn = i9;
        this.zzo = z10;
        this.zzp = z11;
        this.zzq = z12;
        this.zzr = str7;
        this.zzs = bool;
        this.zzt = j14;
        this.zzu = list;
        this.zzv = str8;
        this.zzw = str9;
    }
}
