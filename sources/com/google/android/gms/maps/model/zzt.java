package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

/* loaded from: classes2.dex */
final class zzt extends zzag {
    private final /* synthetic */ TileProvider zzen;

    public zzt(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zzen = tileProvider;
    }

    @Override // com.google.android.gms.internal.maps.zzaf
    public final Tile getTile(int i9, int i10, int i11) {
        return this.zzen.getTile(i9, i10, i11);
    }
}
