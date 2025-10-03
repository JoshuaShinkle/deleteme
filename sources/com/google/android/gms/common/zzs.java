package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
/* loaded from: classes2.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();

    @SafeParcelable.Field(getter = "getCallingPackage", m17520id = 1)
    private final String zza;

    @SafeParcelable.Field(getter = "getCallingCertificateBinder", m17520id = 2, type = "android.os.IBinder")
    private final zzj zzb;

    @SafeParcelable.Field(getter = "getAllowTestKeys", m17520id = 3)
    private final boolean zzc;

    @SafeParcelable.Field(defaultValue = "false", getter = "getIgnoreTestKeysOverride", m17520id = 4)
    private final boolean zzd;

    @SafeParcelable.Constructor
    public zzs(@SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) IBinder iBinder, @SafeParcelable.Param(m17521id = 3) boolean z8, @SafeParcelable.Param(m17521id = 4) boolean z9) {
        this.zza = str;
        zzk zzkVar = null;
        if (iBinder != null) {
            try {
                IObjectWrapper iObjectWrapperZzd = com.google.android.gms.common.internal.zzy.zzg(iBinder).zzd();
                byte[] bArr = iObjectWrapperZzd == null ? null : (byte[]) ObjectWrapper.unwrap(iObjectWrapperZzd);
                if (bArr != null) {
                    zzkVar = new zzk(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e9) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e9);
            }
        }
        this.zzb = zzkVar;
        this.zzc = z8;
        this.zzd = z9;
    }

    public zzs(String str, zzj zzjVar, boolean z8, boolean z9) {
        this.zza = str;
        this.zzb = zzjVar;
        this.zzc = z8;
        this.zzd = z9;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        zzj zzjVar = this.zzb;
        if (zzjVar == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            zzjVar = null;
        }
        SafeParcelWriter.writeIBinder(parcel, 2, zzjVar, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
