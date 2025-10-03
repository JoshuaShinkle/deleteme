package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* loaded from: classes2.dex */
public abstract class UrlTileProvider implements TileProvider {
    private final int height;
    private final int width;

    public UrlTileProvider(int i9, int i10) {
        this.width = i9;
        this.height = i10;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i9, int i10, int i11) throws IOException {
        URL tileUrl = getTileUrl(i9, i10, i11);
        if (tileUrl == null) {
            return TileProvider.NO_TILE;
        }
        try {
            int i12 = this.width;
            int i13 = this.height;
            InputStream inputStreamOpenStream = tileUrl.openStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int i14 = inputStreamOpenStream.read(bArr);
                if (i14 == -1) {
                    return new Tile(i12, i13, byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, i14);
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public abstract URL getTileUrl(int i9, int i10, int i11);
}
