package com.google.android.exoplayer2.source;

/* loaded from: classes.dex */
public interface SequenceableLoader {

    public interface Callback<T extends SequenceableLoader> {
        void onContinueLoadingRequested(T t8);
    }

    boolean continueLoading(long j9);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    void reevaluateBuffer(long j9);
}
