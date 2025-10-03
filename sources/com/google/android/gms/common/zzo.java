package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class(creator = "GoogleCertificatesLookupQueryCreator")
/* loaded from: classes2.dex */
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();

    @SafeParcelable.Field(getter = "getCallingPackage", m17520id = 1)
    private final String zza;

    @SafeParcelable.Field(getter = "getAllowTestKeys", m17520id = 2)
    private final boolean zzb;

    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", m17520id = 3)
    private final boolean zzc;

    @SafeParcelable.Field(getter = "getCallingContextBinder", m17520id = 4, type = "android.os.IBinder")
    private final Context zzd;

    @SafeParcelable.Field(getter = "getIsChimeraPackage", m17520id = 5)
    private final boolean zze;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) boolean z8, @SafeParcelable.Param(m17521id = 3) boolean z9, @SafeParcelable.Param(m17521id = 4) IBinder iBinder, @SafeParcelable.Param(m17521id = 5) boolean z10) {
        this.zza = str;
        this.zzb = z8;
        this.zzc = z9;
        this.zzd = (Context) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        this.zze = z10;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [android.os.IBinder, com.google.android.gms.dynamic.IObjectWrapper] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzd), false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
