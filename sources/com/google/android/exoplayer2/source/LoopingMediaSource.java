package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public final class LoopingMediaSource extends CompositeMediaSource<Void> {
    private int childPeriodCount;
    private final MediaSource childSource;
    private MediaSource.Listener listener;
    private final int loopCount;

    public static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline timeline) {
            super(timeline);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public int getNextWindowIndex(int i9, int i10, boolean z8) {
            int nextWindowIndex = this.timeline.getNextWindowIndex(i9, i10, z8);
            return nextWindowIndex == -1 ? getFirstWindowIndex(z8) : nextWindowIndex;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public int getPreviousWindowIndex(int i9, int i10, boolean z8) {
            int previousWindowIndex = this.timeline.getPreviousWindowIndex(i9, i10, z8);
            return previousWindowIndex == -1 ? getLastWindowIndex(z8) : previousWindowIndex;
        }
    }

    public static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        public LoopingTimeline(Timeline timeline, int i9) {
            super(false, new ShuffleOrder.UnshuffledShuffleOrder(i9));
            this.childTimeline = timeline;
            int periodCount = timeline.getPeriodCount();
            this.childPeriodCount = periodCount;
            this.childWindowCount = timeline.getWindowCount();
            this.loopCount = i9;
            if (periodCount > 0) {
                Assertions.checkState(i9 <= Integer.MAX_VALUE / periodCount, "LoopingMediaSource contains too many periods");
            }
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByChildUid(Object obj) {
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByPeriodIndex(int i9) {
            return i9 / this.childPeriodCount;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int i9) {
            return i9 / this.childWindowCount;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int i9) {
            return Integer.valueOf(i9);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int i9) {
            return i9 * this.childPeriodCount;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int i9) {
            return i9 * this.childWindowCount;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int i9) {
            return this.childTimeline;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }
    }

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        return this.loopCount != Integer.MAX_VALUE ? this.childSource.createPeriod(mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex % this.childPeriodCount), allocator) : this.childSource.createPeriod(mediaPeriodId, allocator);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        super.prepareSource(exoPlayer, z8, listener);
        this.listener = listener;
        prepareChildSource(null, this.childSource);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.childSource.releasePeriod(mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        super.releaseSource();
        this.listener = null;
        this.childPeriodCount = 0;
    }

    public LoopingMediaSource(MediaSource mediaSource, int i9) {
        Assertions.checkArgument(i9 > 0);
        this.childSource = mediaSource;
        this.loopCount = i9;
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Void r12, MediaSource mediaSource, Timeline timeline, Object obj) {
        this.childPeriodCount = timeline.getPeriodCount();
        this.listener.onSourceInfoRefreshed(this, this.loopCount != Integer.MAX_VALUE ? new LoopingTimeline(timeline, this.loopCount) : new InfinitelyLoopingTimeline(timeline), obj);
    }
}
