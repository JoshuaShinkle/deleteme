package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzaa extends zzc implements zzab {
    public zzaa() {
        super("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzc
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 == 1) {
            zza(parcel.readString(), parcel.readString(), (Bundle) zzb.zza(parcel, Bundle.CREATOR), parcel.readLong());
            parcel2.writeNoException();
        } else {
            if (i9 != 2) {
                return false;
            }
            int iZza = zza();
            parcel2.writeNoException();
            parcel2.writeInt(iZza);
        }
        return true;
    }
}
