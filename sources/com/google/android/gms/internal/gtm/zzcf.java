package com.google.android.gms.internal.gtm;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class zzcf extends zzm implements zzce {
    public zzcf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.analytics.internal.IAnalyticsService");
    }

    @Override // com.google.android.gms.internal.gtm.zzce
    public final void zza(Map map, long j9, String str, List<zzbk> list) {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        parcelObtainAndWriteInterfaceToken.writeMap(map);
        parcelObtainAndWriteInterfaceToken.writeLong(j9);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeTypedList(list);
        zza(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.gtm.zzce
    public final void zzch() {
        zza(2, obtainAndWriteInterfaceToken());
    }
}
