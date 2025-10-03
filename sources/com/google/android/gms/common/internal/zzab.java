package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzab extends com.google.android.gms.internal.common.zzb implements IGmsCallbacks {
    public zzab() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.internal.common.zzb
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            int i11 = parcel.readInt();
            IBinder strongBinder = parcel.readStrongBinder();
            Bundle bundle = (Bundle) com.google.android.gms.internal.common.zzc.zza(parcel, Bundle.CREATOR);
            com.google.android.gms.internal.common.zzc.zzb(parcel);
            onPostInitComplete(i11, strongBinder, bundle);
        } else if (i9 == 2) {
            int i12 = parcel.readInt();
            Bundle bundle2 = (Bundle) com.google.android.gms.internal.common.zzc.zza(parcel, Bundle.CREATOR);
            com.google.android.gms.internal.common.zzc.zzb(parcel);
            zzb(i12, bundle2);
        } else {
            if (i9 != 3) {
                return false;
            }
            int i13 = parcel.readInt();
            IBinder strongBinder2 = parcel.readStrongBinder();
            zzj zzjVar = (zzj) com.google.android.gms.internal.common.zzc.zza(parcel, zzj.CREATOR);
            com.google.android.gms.internal.common.zzc.zzb(parcel);
            zzc(i13, strongBinder2, zzjVar);
        }
        parcel2.writeNoException();
        return true;
    }
}
