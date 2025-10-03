package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;

/* loaded from: classes.dex */
public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getFirstWindowIndex(boolean z8) {
        return this.timeline.getFirstWindowIndex(z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getLastWindowIndex(boolean z8) {
        return this.timeline.getLastWindowIndex(z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getNextWindowIndex(int i9, int i10, boolean z8) {
        return this.timeline.getNextWindowIndex(i9, i10, z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
        return this.timeline.getPeriod(i9, period, z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPreviousWindowIndex(int i9, int i10, boolean z8) {
        return this.timeline.getPreviousWindowIndex(i9, i10, z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
        return this.timeline.getWindow(i9, window, z8, j9);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }
}
