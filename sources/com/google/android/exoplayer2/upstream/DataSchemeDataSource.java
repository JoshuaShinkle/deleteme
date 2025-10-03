package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.ParserException;
import java.net.URLDecoder;

/* loaded from: classes.dex */
public final class DataSchemeDataSource implements DataSource {
    public static final String SCHEME_DATA = "data";
    private int bytesRead;
    private byte[] data;
    private DataSpec dataSpec;

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        this.dataSpec = null;
        this.data = null;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        DataSpec dataSpec = this.dataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws ParserException {
        this.dataSpec = dataSpec;
        Uri uri = dataSpec.uri;
        String scheme = uri.getScheme();
        if (!"data".equals(scheme)) {
            throw new ParserException("Unsupported scheme: " + scheme);
        }
        String[] strArrSplit = uri.getSchemeSpecificPart().split(",");
        if (strArrSplit.length > 2) {
            throw new ParserException("Unexpected URI format: " + uri);
        }
        String str = strArrSplit[1];
        if (strArrSplit[0].contains(";base64")) {
            try {
                this.data = Base64.decode(str, 0);
            } catch (IllegalArgumentException e9) {
                throw new ParserException("Error while parsing Base64 encoded string: " + str, e9);
            }
        } else {
            this.data = URLDecoder.decode(str, "US-ASCII").getBytes();
        }
        return this.data.length;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) {
        if (i10 == 0) {
            return 0;
        }
        int length = this.data.length - this.bytesRead;
        if (length == 0) {
            return -1;
        }
        int iMin = Math.min(i10, length);
        System.arraycopy(this.data, this.bytesRead, bArr, i9, iMin);
        this.bytesRead += iMin;
        return iMin;
    }
}
