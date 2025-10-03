package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzf extends zza implements zzd {
    public zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override // com.google.android.gms.internal.measurement.zzd
    public final Bundle zza(Bundle bundle) {
        Parcel parcelM17533a_ = m17533a_();
        zzb.zza(parcelM17533a_, bundle);
        Parcel parcelZza = zza(1, parcelM17533a_);
        Bundle bundle2 = (Bundle) zzb.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle2;
    }
}
