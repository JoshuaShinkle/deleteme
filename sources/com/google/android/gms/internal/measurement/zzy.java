package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzy extends zza implements zzw {
    public zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override // com.google.android.gms.internal.measurement.zzw
    public final void zza(Bundle bundle) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        zzb(1, parcelM17533a_);
    }
}
