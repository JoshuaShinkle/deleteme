package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzz extends zzc implements zzw {
    public zzz() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzc
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        zza((Bundle) zzb.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
