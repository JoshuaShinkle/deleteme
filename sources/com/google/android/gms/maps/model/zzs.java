package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaf;

/* loaded from: classes2.dex */
final class zzs implements TileProvider {
    private final zzaf zzel;
    private final /* synthetic */ TileOverlayOptions zzem;

    public zzs(TileOverlayOptions tileOverlayOptions) {
        this.zzem = tileOverlayOptions;
        this.zzel = tileOverlayOptions.zzei;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i9, int i10, int i11) {
        try {
            return this.zzel.getTile(i9, i10, i11);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
