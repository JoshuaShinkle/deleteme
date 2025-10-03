package com.google.android.gms.internal.play_billing;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes2.dex */
public abstract class zzf extends zzi implements zzg {
    public zzf() {
        super("com.android.vending.billing.IInAppBillingServiceCallback");
    }

    @Override // com.google.android.gms.internal.play_billing.zzi
    public final boolean zzb(int i9, Parcel parcel, Parcel parcel2, int i10) {
        if (i9 != 1) {
            return false;
        }
        Bundle bundle = (Bundle) zzj.zza(parcel, Bundle.CREATOR);
        int iDataAvail = parcel.dataAvail();
        if (iDataAvail <= 0) {
            zza(bundle);
            parcel2.writeNoException();
            return true;
        }
        throw new BadParcelableException("Parcel data not fully consumed, unread size: " + iDataAvail);
    }
}
