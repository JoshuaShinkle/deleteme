package com.google.android.exoplayer2.offline;

/* loaded from: classes.dex */
public interface Downloader {

    public interface ProgressListener {
        void onDownloadProgress(Downloader downloader, float f9, long j9);
    }

    void download(ProgressListener progressListener);

    float getDownloadPercentage();

    long getDownloadedBytes();

    void init();

    void remove();
}
