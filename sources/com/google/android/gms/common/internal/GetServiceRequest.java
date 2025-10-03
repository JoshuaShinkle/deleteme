package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
/* loaded from: classes2.dex */
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzm();
    static final Scope[] zza = new Scope[0];
    static final Feature[] zzb = new Feature[0];

    @SafeParcelable.VersionField(m17523id = 1)
    final int zzc;

    @SafeParcelable.Field(m17520id = 2)
    final int zzd;

    @SafeParcelable.Field(m17520id = 3)
    int zze;

    @SafeParcelable.Field(m17520id = 4)
    String zzf;

    @SafeParcelable.Field(m17520id = 5)
    IBinder zzg;

    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_SCOPES", m17520id = 6)
    Scope[] zzh;

    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.Bundle()", m17520id = 7)
    Bundle zzi;

    @SafeParcelable.Field(m17520id = 8)
    Account zzj;

    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_FEATURES", m17520id = 10)
    Feature[] zzk;

    @SafeParcelable.Field(defaultValueUnchecked = "GetServiceRequest.EMPTY_FEATURES", m17520id = 11)
    Feature[] zzl;

    @SafeParcelable.Field(m17520id = 12)
    boolean zzm;

    @SafeParcelable.Field(defaultValue = "0", m17520id = 13)
    int zzn;

    @SafeParcelable.Field(getter = "isRequestingTelemetryConfiguration", m17520id = 14)
    boolean zzo;

    @SafeParcelable.Field(getter = "getAttributionTag", m17520id = 15)
    private String zzp;

    @SafeParcelable.Constructor
    public GetServiceRequest(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) int i10, @SafeParcelable.Param(m17521id = 3) int i11, @SafeParcelable.Param(m17521id = 4) String str, @SafeParcelable.Param(m17521id = 5) IBinder iBinder, @SafeParcelable.Param(m17521id = 6) Scope[] scopeArr, @SafeParcelable.Param(m17521id = 7) Bundle bundle, @SafeParcelable.Param(m17521id = 8) Account account, @SafeParcelable.Param(m17521id = 10) Feature[] featureArr, @SafeParcelable.Param(m17521id = 11) Feature[] featureArr2, @SafeParcelable.Param(m17521id = 12) boolean z8, @SafeParcelable.Param(m17521id = 13) int i12, @SafeParcelable.Param(m17521id = 14) boolean z9, @SafeParcelable.Param(m17521id = 15) String str2) {
        scopeArr = scopeArr == null ? zza : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        featureArr = featureArr == null ? zzb : featureArr;
        featureArr2 = featureArr2 == null ? zzb : featureArr2;
        this.zzc = i9;
        this.zzd = i10;
        this.zze = i11;
        if ("com.google.android.gms".equals(str)) {
            this.zzf = "com.google.android.gms";
        } else {
            this.zzf = str;
        }
        if (i9 < 2) {
            this.zzj = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.zzg = iBinder;
            this.zzj = account;
        }
        this.zzh = scopeArr;
        this.zzi = bundle;
        this.zzk = featureArr;
        this.zzl = featureArr2;
        this.zzm = z8;
        this.zzn = i12;
        this.zzo = z9;
        this.zzp = str2;
    }

    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.zzi;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        zzm.zza(this, parcel, i9);
    }

    public final String zza() {
        return this.zzp;
    }
}
