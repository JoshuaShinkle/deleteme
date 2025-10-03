package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AccountChangeEventCreator")
/* loaded from: classes2.dex */
public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zze;

    @SafeParcelable.Field(m17520id = 2)
    private final long zzf;

    @SafeParcelable.Field(m17520id = 3)
    private final String zzg;

    @SafeParcelable.Field(m17520id = 4)
    private final int zzh;

    @SafeParcelable.Field(m17520id = 5)
    private final int zzi;

    @SafeParcelable.Field(m17520id = 6)
    private final String zzj;

    @SafeParcelable.Constructor
    public AccountChangeEvent(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) long j9, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) int i10, @SafeParcelable.Param(m17521id = 5) int i11, @SafeParcelable.Param(m17521id = 6) String str2) {
        this.zze = i9;
        this.zzf = j9;
        this.zzg = (String) Preconditions.checkNotNull(str);
        this.zzh = i10;
        this.zzi = i11;
        this.zzj = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AccountChangeEvent) {
            AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
            if (this.zze == accountChangeEvent.zze && this.zzf == accountChangeEvent.zzf && Objects.equal(this.zzg, accountChangeEvent.zzg) && this.zzh == accountChangeEvent.zzh && this.zzi == accountChangeEvent.zzi && Objects.equal(this.zzj, accountChangeEvent.zzj)) {
                return true;
            }
        }
        return false;
    }

    public String getAccountName() {
        return this.zzg;
    }

    public String getChangeData() {
        return this.zzj;
    }

    public int getChangeType() {
        return this.zzh;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zze), Long.valueOf(this.zzf), this.zzg, Integer.valueOf(this.zzh), Integer.valueOf(this.zzi), this.zzj);
    }

    public String toString() {
        int i9 = this.zzh;
        String str = i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "UNKNOWN" : "RENAMED_TO" : "RENAMED_FROM" : "REMOVED" : "ADDED";
        String str2 = this.zzg;
        String str3 = this.zzj;
        int i10 = this.zzi;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 91 + str.length() + String.valueOf(str3).length());
        sb.append("AccountChangeEvent {accountName = ");
        sb.append(str2);
        sb.append(", changeType = ");
        sb.append(str);
        sb.append(", changeData = ");
        sb.append(str3);
        sb.append(", eventIndex = ");
        sb.append(i10);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeInt(parcel, 5, this.zzi);
        SafeParcelWriter.writeString(parcel, 6, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public AccountChangeEvent(long j9, String str, int i9, int i10, String str2) {
        this.zze = 1;
        this.zzf = j9;
        this.zzg = (String) Preconditions.checkNotNull(str);
        this.zzh = i9;
        this.zzi = i10;
        this.zzj = str2;
    }
}
