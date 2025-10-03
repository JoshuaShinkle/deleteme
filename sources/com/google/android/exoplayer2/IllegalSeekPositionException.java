package com.google.android.exoplayer2;

/* loaded from: classes.dex */
public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline timeline, int i9, long j9) {
        this.timeline = timeline;
        this.windowIndex = i9;
        this.positionMs = j9;
    }
}
