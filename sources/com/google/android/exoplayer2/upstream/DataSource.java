package com.google.android.exoplayer2.upstream;

import android.net.Uri;

/* loaded from: classes.dex */
public interface DataSource {

    public interface Factory {
        DataSource createDataSource();
    }

    void close();

    Uri getUri();

    long open(DataSpec dataSpec);

    int read(byte[] bArr, int i9, int i10);
}
