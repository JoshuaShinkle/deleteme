package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ForwardingTimeline;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline timeline, AdPlaybackState adPlaybackState) {
        super(timeline);
        Assertions.checkState(timeline.getPeriodCount() == 1);
        Assertions.checkState(timeline.getWindowCount() == 1);
        this.adPlaybackState = adPlaybackState;
    }

    @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
        this.timeline.getPeriod(i9, period, z8);
        period.set(period.f15300id, period.uid, period.windowIndex, period.durationUs, period.getPositionInWindowUs(), this.adPlaybackState);
        return period;
    }

    @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
    public Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
        Timeline.Window window2 = super.getWindow(i9, window, z8, j9);
        if (window2.durationUs == C3322C.TIME_UNSET) {
            window2.durationUs = this.adPlaybackState.contentDurationUs;
        }
        return window2;
    }
}
