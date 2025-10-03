package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

/* loaded from: classes2.dex */
public final class zzah extends zza implements zzaf {
    public zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaf
    public final Tile getTile(int i9, int i10, int i11) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        parcelZza.writeInt(i10);
        parcelZza.writeInt(i11);
        Parcel parcelZza2 = zza(1, parcelZza);
        Tile tile = (Tile) zzc.zza(parcelZza2, Tile.CREATOR);
        parcelZza2.recycle();
        return tile;
    }
}
