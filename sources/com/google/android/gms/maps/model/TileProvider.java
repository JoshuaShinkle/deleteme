package com.google.android.gms.maps.model;

/* loaded from: classes2.dex */
public interface TileProvider {
    public static final Tile NO_TILE = new Tile(-1, -1, null);

    Tile getTile(int i9, int i10, int i11);
}
