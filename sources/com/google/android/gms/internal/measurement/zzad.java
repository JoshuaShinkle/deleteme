package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzad extends zza implements zzab {
    public zzad(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzab
    public final void zza(String str, String str2, Bundle bundle, long j9) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb.zza(parcelM17533a_, bundle);
        parcelM17533a_.writeLong(j9);
        zzb(1, parcelM17533a_);
    }

    @Override // com.google.android.gms.internal.measurement.zzab
    public final int zza() {
        Parcel parcelZza = zza(2, m17533a_());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }
}
