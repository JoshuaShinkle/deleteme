package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzp extends zza implements zzn {
    public zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getActiveLevelIndex() {
        Parcel parcelZza = zza(1, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getDefaultLevelIndex() {
        Parcel parcelZza = zza(2, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final List<IBinder> getLevels() {
        Parcel parcelZza = zza(3, zza());
        ArrayList<IBinder> arrayListCreateBinderArrayList = parcelZza.createBinderArrayList();
        parcelZza.recycle();
        return arrayListCreateBinderArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean isUnderground() {
        Parcel parcelZza = zza(4, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean zzb(zzn zznVar) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zznVar);
        Parcel parcelZza2 = zza(5, parcelZza);
        boolean zZza = zzc.zza(parcelZza2);
        parcelZza2.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int zzj() {
        Parcel parcelZza = zza(6, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }
}
