package com.google.android.exoplayer2.upstream;

/* loaded from: classes.dex */
public interface TransferListener<S> {
    void onBytesTransferred(S s8, int i9);

    void onTransferEnd(S s8);

    void onTransferStart(S s8, DataSpec dataSpec);
}
