package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DynamicLinkDataCreator")
/* loaded from: classes2.dex */
public class DynamicLinkData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DynamicLinkData> CREATOR = new zza();

    @SafeParcelable.Field(getter = "getDynamicLink", m17520id = 1)
    private String zzj;

    @SafeParcelable.Field(getter = "getDeepLink", m17520id = 2)
    private String zzk;

    @SafeParcelable.Field(getter = "getMinVersion", m17520id = 3)
    private int zzl;

    @SafeParcelable.Field(getter = "getClickTimestamp", m17520id = 4)
    private long zzm;

    @SafeParcelable.Field(getter = "getExtensionBundle", m17520id = 5)
    private Bundle zzn;

    @SafeParcelable.Field(getter = "getRedirectUrl", m17520id = 6)
    private Uri zzo;

    @SafeParcelable.Constructor
    public DynamicLinkData(@SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) String str2, @SafeParcelable.Param(m17521id = 3) int i9, @SafeParcelable.Param(m17521id = 4) long j9, @SafeParcelable.Param(m17521id = 5) Bundle bundle, @SafeParcelable.Param(m17521id = 6) Uri uri) {
        this.zzj = str;
        this.zzk = str2;
        this.zzl = i9;
        this.zzm = j9;
        this.zzn = bundle;
        this.zzo = uri;
    }

    public final long getClickTimestamp() {
        return this.zzm;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzl);
        SafeParcelWriter.writeLong(parcel, 4, this.zzm);
        SafeParcelWriter.writeBundle(parcel, 5, zzf(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzo, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final void zza(long j9) {
        this.zzm = j9;
    }

    public final Uri zzc() {
        return this.zzo;
    }

    public final String zzd() {
        return this.zzk;
    }

    public final int zze() {
        return this.zzl;
    }

    public final Bundle zzf() {
        Bundle bundle = this.zzn;
        return bundle == null ? new Bundle() : bundle;
    }
}
