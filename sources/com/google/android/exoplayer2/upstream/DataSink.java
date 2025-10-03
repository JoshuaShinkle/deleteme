package com.google.android.exoplayer2.upstream;

/* loaded from: classes.dex */
public interface DataSink {

    public interface Factory {
        DataSink createDataSink();
    }

    void close();

    void open(DataSpec dataSpec);

    void write(byte[] bArr, int i9, int i10);
}
