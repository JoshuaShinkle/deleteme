package com.google.android.exoplayer2.p038ui;

/* loaded from: classes.dex */
public interface TimeBar {

    public interface OnScrubListener {
        void onScrubMove(TimeBar timeBar, long j9);

        void onScrubStart(TimeBar timeBar, long j9);

        void onScrubStop(TimeBar timeBar, long j9, boolean z8);
    }

    void addListener(OnScrubListener onScrubListener);

    void removeListener(OnScrubListener onScrubListener);

    void setAdGroupTimesMs(long[] jArr, boolean[] zArr, int i9);

    void setBufferedPosition(long j9);

    void setDuration(long j9);

    void setEnabled(boolean z8);

    void setKeyCountIncrement(int i9);

    void setKeyTimeIncrement(long j9);

    void setPosition(long j9);
}
