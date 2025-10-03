package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
/* loaded from: classes2.dex */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzz();

    @SafeParcelable.Field(m17520id = 2)
    public String zza;

    @SafeParcelable.Field(m17520id = 3)
    public String zzb;

    @SafeParcelable.Field(m17520id = 4)
    public zzkw zzc;

    @SafeParcelable.Field(m17520id = 5)
    public long zzd;

    @SafeParcelable.Field(m17520id = 6)
    public boolean zze;

    @SafeParcelable.Field(m17520id = 7)
    public String zzf;

    @SafeParcelable.Field(m17520id = 8)
    public zzar zzg;

    @SafeParcelable.Field(m17520id = 9)
    public long zzh;

    @SafeParcelable.Field(m17520id = 10)
    public zzar zzi;

    @SafeParcelable.Field(m17520id = 11)
    public long zzj;

    @SafeParcelable.Field(m17520id = 12)
    public zzar zzk;

    public zzw(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        this.zza = zzwVar.zza;
        this.zzb = zzwVar.zzb;
        this.zzc = zzwVar.zzc;
        this.zzd = zzwVar.zzd;
        this.zze = zzwVar.zze;
        this.zzf = zzwVar.zzf;
        this.zzg = zzwVar.zzg;
        this.zzh = zzwVar.zzh;
        this.zzi = zzwVar.zzi;
        this.zzj = zzwVar.zzj;
        this.zzk = zzwVar.zzk;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i9, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i9, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i9, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzw(@SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 3) String str2, @SafeParcelable.Param(m17521id = 4) zzkw zzkwVar, @SafeParcelable.Param(m17521id = 5) long j9, @SafeParcelable.Param(m17521id = 6) boolean z8, @SafeParcelable.Param(m17521id = 7) String str3, @SafeParcelable.Param(m17521id = 8) zzar zzarVar, @SafeParcelable.Param(m17521id = 9) long j10, @SafeParcelable.Param(m17521id = 10) zzar zzarVar2, @SafeParcelable.Param(m17521id = 11) long j11, @SafeParcelable.Param(m17521id = 12) zzar zzarVar3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkwVar;
        this.zzd = j9;
        this.zze = z8;
        this.zzf = str3;
        this.zzg = zzarVar;
        this.zzh = j10;
        this.zzi = zzarVar2;
        this.zzj = j11;
        this.zzk = zzarVar3;
    }
}
