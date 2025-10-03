package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class ConcatenatingMediaSource extends CompositeMediaSource<Integer> {
    private final boolean isAtomic;
    private MediaSource.Listener listener;
    private final Object[] manifests;
    private final MediaSource[] mediaSources;
    private final ShuffleOrder shuffleOrder;
    private final Map<MediaPeriod, Integer> sourceIndexByMediaPeriod;
    private ConcatenatedTimeline timeline;
    private final Timeline[] timelines;

    public static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final int[] sourcePeriodOffsets;
        private final int[] sourceWindowOffsets;
        private final Timeline[] timelines;

        public ConcatenatedTimeline(Timeline[] timelineArr, boolean z8, ShuffleOrder shuffleOrder) {
            super(z8, shuffleOrder);
            int[] iArr = new int[timelineArr.length];
            int[] iArr2 = new int[timelineArr.length];
            long periodCount = 0;
            int windowCount = 0;
            for (int i9 = 0; i9 < timelineArr.length; i9++) {
                Timeline timeline = timelineArr[i9];
                periodCount += timeline.getPeriodCount();
                Assertions.checkState(periodCount <= 2147483647L, "ConcatenatingMediaSource children contain too many periods");
                iArr[i9] = (int) periodCount;
                windowCount += timeline.getWindowCount();
                iArr2[i9] = windowCount;
            }
            this.timelines = timelineArr;
            this.sourcePeriodOffsets = iArr;
            this.sourceWindowOffsets = iArr2;
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
            return Util.binarySearchFloor(this.sourcePeriodOffsets, i9 + 1, false, false) + 1;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int i9) {
            return Util.binarySearchFloor(this.sourceWindowOffsets, i9 + 1, false, false) + 1;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int i9) {
            return Integer.valueOf(i9);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int i9) {
            if (i9 == 0) {
                return 0;
            }
            return this.sourcePeriodOffsets[i9 - 1];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int i9) {
            if (i9 == 0) {
                return 0;
            }
            return this.sourceWindowOffsets[i9 - 1];
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.sourcePeriodOffsets[r0.length - 1];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int i9) {
            return this.timelines[i9];
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.sourceWindowOffsets[r0.length - 1];
        }
    }

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    private static boolean[] buildDuplicateFlags(MediaSource[] mediaSourceArr) {
        boolean[] zArr = new boolean[mediaSourceArr.length];
        IdentityHashMap identityHashMap = new IdentityHashMap(mediaSourceArr.length);
        for (int i9 = 0; i9 < mediaSourceArr.length; i9++) {
            MediaSource mediaSource = mediaSourceArr[i9];
            if (identityHashMap.containsKey(mediaSource)) {
                zArr[i9] = true;
            } else {
                identityHashMap.put(mediaSource, null);
            }
        }
        return zArr;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        int childIndexByPeriodIndex = this.timeline.getChildIndexByPeriodIndex(mediaPeriodId.periodIndex);
        MediaPeriod mediaPeriodCreatePeriod = this.mediaSources[childIndexByPeriodIndex].createPeriod(mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex - this.timeline.getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex)), allocator);
        this.sourceIndexByMediaPeriod.put(mediaPeriodCreatePeriod, Integer.valueOf(childIndexByPeriodIndex));
        return mediaPeriodCreatePeriod;
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        super.prepareSource(exoPlayer, z8, listener);
        this.listener = listener;
        boolean[] zArrBuildDuplicateFlags = buildDuplicateFlags(this.mediaSources);
        if (this.mediaSources.length == 0) {
            listener.onSourceInfoRefreshed(this, Timeline.EMPTY, null);
            return;
        }
        for (int i9 = 0; i9 < this.mediaSources.length; i9++) {
            if (!zArrBuildDuplicateFlags[i9]) {
                prepareChildSource(Integer.valueOf(i9), this.mediaSources[i9]);
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        int iIntValue = this.sourceIndexByMediaPeriod.get(mediaPeriod).intValue();
        this.sourceIndexByMediaPeriod.remove(mediaPeriod);
        this.mediaSources[iIntValue].releasePeriod(mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        super.releaseSource();
        this.listener = null;
        this.timeline = null;
    }

    public ConcatenatingMediaSource(boolean z8, MediaSource... mediaSourceArr) {
        this(z8, new ShuffleOrder.DefaultShuffleOrder(mediaSourceArr.length), mediaSourceArr);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline, Object obj) {
        this.timelines[num.intValue()] = timeline;
        this.manifests[num.intValue()] = obj;
        int iIntValue = num.intValue();
        while (true) {
            iIntValue++;
            MediaSource[] mediaSourceArr = this.mediaSources;
            if (iIntValue >= mediaSourceArr.length) {
                break;
            } else if (mediaSourceArr[iIntValue] == mediaSource) {
                this.timelines[iIntValue] = timeline;
                this.manifests[iIntValue] = obj;
            }
        }
        for (Timeline timeline2 : this.timelines) {
            if (timeline2 == null) {
                return;
            }
        }
        ConcatenatedTimeline concatenatedTimeline = new ConcatenatedTimeline((Timeline[]) this.timelines.clone(), this.isAtomic, this.shuffleOrder);
        this.timeline = concatenatedTimeline;
        this.listener.onSourceInfoRefreshed(this, concatenatedTimeline, this.manifests.clone());
    }

    public ConcatenatingMediaSource(boolean z8, ShuffleOrder shuffleOrder, MediaSource... mediaSourceArr) {
        for (MediaSource mediaSource : mediaSourceArr) {
            Assertions.checkNotNull(mediaSource);
        }
        Assertions.checkArgument(shuffleOrder.getLength() == mediaSourceArr.length);
        this.mediaSources = mediaSourceArr;
        this.isAtomic = z8;
        this.shuffleOrder = shuffleOrder;
        this.timelines = new Timeline[mediaSourceArr.length];
        this.manifests = new Object[mediaSourceArr.length];
        this.sourceIndexByMediaPeriod = new HashMap();
    }
}
