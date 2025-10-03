package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class DynamicConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> implements PlayerMessage.Target {
    private static final int MSG_ADD = 0;
    private static final int MSG_ADD_MULTIPLE = 1;
    private static final int MSG_MOVE = 3;
    private static final int MSG_ON_COMPLETION = 4;
    private static final int MSG_REMOVE = 2;
    private final List<DeferredMediaPeriod> deferredMediaPeriods;
    private final boolean isAtomic;
    private MediaSource.Listener listener;
    private final Map<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final List<MediaSource> mediaSourcesPublic;
    private int periodCount;
    private ExoPlayer player;
    private boolean preventListenerNotification;
    private final MediaSourceHolder query;
    private ShuffleOrder shuffleOrder;
    private int windowCount;

    public static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final SparseIntArray childIndexByUid;
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final int[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, int i9, int i10, ShuffleOrder shuffleOrder, boolean z8) {
            super(z8, shuffleOrder);
            this.windowCount = i9;
            this.periodCount = i10;
            int size = collection.size();
            this.firstPeriodInChildIndices = new int[size];
            this.firstWindowInChildIndices = new int[size];
            this.timelines = new Timeline[size];
            this.uids = new int[size];
            this.childIndexByUid = new SparseIntArray();
            int i11 = 0;
            for (MediaSourceHolder mediaSourceHolder : collection) {
                this.timelines[i11] = mediaSourceHolder.timeline;
                this.firstPeriodInChildIndices[i11] = mediaSourceHolder.firstPeriodIndexInChild;
                this.firstWindowInChildIndices[i11] = mediaSourceHolder.firstWindowIndexInChild;
                int[] iArr = this.uids;
                int i12 = mediaSourceHolder.uid;
                iArr[i11] = i12;
                this.childIndexByUid.put(i12, i11);
                i11++;
            }
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByChildUid(Object obj) {
            int i9;
            if ((obj instanceof Integer) && (i9 = this.childIndexByUid.get(((Integer) obj).intValue(), -1)) != -1) {
                return i9;
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByPeriodIndex(int i9) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i9 + 1, false, false);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int i9) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i9 + 1, false, false);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int i9) {
            return Integer.valueOf(this.uids[i9]);
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int i9) {
            return this.firstPeriodInChildIndices[i9];
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int i9) {
            return this.firstWindowInChildIndices[i9];
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.periodCount;
        }

        @Override // com.google.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int i9) {
            return this.timelines[i9];
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.windowCount;
        }
    }

    public static final class DeferredTimeline extends ForwardingTimeline {
        private final Object replacedId;
        private static final Object DUMMY_ID = new Object();
        private static final Timeline.Period period = new Timeline.Period();
        private static final DummyTimeline dummyTimeline = new DummyTimeline();

        public DeferredTimeline() {
            this(dummyTimeline, null);
        }

        public DeferredTimeline cloneWithNewTimeline(Timeline timeline) {
            return new DeferredTimeline(timeline, (this.replacedId != null || timeline.getPeriodCount() <= 0) ? this.replacedId : timeline.getPeriod(0, period, true).uid);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_ID.equals(obj)) {
                obj = this.replacedId;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i9, Timeline.Period period2, boolean z8) {
            this.timeline.getPeriod(i9, period2, z8);
            if (Util.areEqual(period2.uid, this.replacedId)) {
                period2.uid = DUMMY_ID;
            }
            return period2;
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        private DeferredTimeline(Timeline timeline, Object obj) {
            super(timeline);
            this.replacedId = obj;
        }
    }

    public static final class DummyTimeline extends Timeline {
        private DummyTimeline() {
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return obj == null ? 0 : -1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
            return period.set(null, null, 0, C3322C.TIME_UNSET, C3322C.TIME_UNSET);
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
            return window.set(null, C3322C.TIME_UNSET, C3322C.TIME_UNSET, false, true, 0L, C3322C.TIME_UNSET, 0, 0, 0L);
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public static final class EventDispatcher {
        public final Handler eventHandler;
        public final Runnable runnable;

        public EventDispatcher(Runnable runnable) {
            this.runnable = runnable;
            this.eventHandler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        }

        public void dispatchEvent() {
            this.eventHandler.post(this.runnable);
        }
    }

    public static final class MediaSourceHolder implements Comparable<MediaSourceHolder> {
        public int activeMediaPeriods;
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public final int uid = System.identityHashCode(this);

        public MediaSourceHolder(MediaSource mediaSource, DeferredTimeline deferredTimeline, int i9, int i10, int i11) {
            this.mediaSource = mediaSource;
            this.timeline = deferredTimeline;
            this.childIndex = i9;
            this.firstWindowIndexInChild = i10;
            this.firstPeriodIndexInChild = i11;
        }

        @Override // java.lang.Comparable
        public int compareTo(MediaSourceHolder mediaSourceHolder) {
            return this.firstPeriodIndexInChild - mediaSourceHolder.firstPeriodIndexInChild;
        }
    }

    public static final class MessageData<T> {
        public final EventDispatcher actionOnCompletion;
        public final T customData;
        public final int index;

        public MessageData(int i9, T t8, Runnable runnable) {
            this.index = i9;
            this.actionOnCompletion = runnable != null ? new EventDispatcher(runnable) : null;
            this.customData = t8;
        }
    }

    public DynamicConcatenatingMediaSource() {
        this(false, new ShuffleOrder.DefaultShuffleOrder(0));
    }

    private void addMediaSourceInternal(int i9, MediaSource mediaSource) {
        MediaSourceHolder mediaSourceHolder;
        DeferredTimeline deferredTimeline = new DeferredTimeline();
        if (i9 > 0) {
            MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i9 - 1);
            mediaSourceHolder = new MediaSourceHolder(mediaSource, deferredTimeline, i9, mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.timeline.getWindowCount(), mediaSourceHolder2.firstPeriodIndexInChild + mediaSourceHolder2.timeline.getPeriodCount());
        } else {
            mediaSourceHolder = new MediaSourceHolder(mediaSource, deferredTimeline, 0, 0, 0);
        }
        correctOffsets(i9, 1, deferredTimeline.getWindowCount(), deferredTimeline.getPeriodCount());
        this.mediaSourceHolders.add(i9, mediaSourceHolder);
        prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
    }

    private void addMediaSourcesInternal(int i9, Collection<MediaSource> collection) {
        Iterator<MediaSource> it = collection.iterator();
        while (it.hasNext()) {
            addMediaSourceInternal(i9, it.next());
            i9++;
        }
    }

    private void correctOffsets(int i9, int i10, int i11, int i12) {
        this.windowCount += i11;
        this.periodCount += i12;
        while (i9 < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i9).childIndex += i10;
            this.mediaSourceHolders.get(i9).firstWindowIndexInChild += i11;
            this.mediaSourceHolders.get(i9).firstPeriodIndexInChild += i12;
            i9++;
        }
    }

    private int findMediaSourceHolderByPeriodIndex(int i9) {
        MediaSourceHolder mediaSourceHolder = this.query;
        mediaSourceHolder.firstPeriodIndexInChild = i9;
        int iBinarySearch = Collections.binarySearch(this.mediaSourceHolders, mediaSourceHolder);
        if (iBinarySearch < 0) {
            return (-iBinarySearch) - 2;
        }
        while (iBinarySearch < this.mediaSourceHolders.size() - 1) {
            int i10 = iBinarySearch + 1;
            if (this.mediaSourceHolders.get(i10).firstPeriodIndexInChild != i9) {
                break;
            }
            iBinarySearch = i10;
        }
        return iBinarySearch;
    }

    private void maybeNotifyListener(EventDispatcher eventDispatcher) {
        if (this.preventListenerNotification) {
            return;
        }
        this.listener.onSourceInfoRefreshed(this, new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), null);
        if (eventDispatcher != null) {
            this.player.createMessage(this).setType(4).setPayload(eventDispatcher).send();
        }
    }

    private void moveMediaSourceInternal(int i9, int i10) {
        int iMin = Math.min(i9, i10);
        int iMax = Math.max(i9, i10);
        int windowCount = this.mediaSourceHolders.get(iMin).firstWindowIndexInChild;
        int periodCount = this.mediaSourceHolders.get(iMin).firstPeriodIndexInChild;
        List<MediaSourceHolder> list = this.mediaSourceHolders;
        list.add(i10, list.remove(i9));
        while (iMin <= iMax) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(iMin);
            mediaSourceHolder.firstWindowIndexInChild = windowCount;
            mediaSourceHolder.firstPeriodIndexInChild = periodCount;
            windowCount += mediaSourceHolder.timeline.getWindowCount();
            periodCount += mediaSourceHolder.timeline.getPeriodCount();
            iMin++;
        }
    }

    private void removeMediaSourceInternal(int i9) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i9);
        this.mediaSourceHolders.remove(i9);
        DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
        correctOffsets(i9, -1, -deferredTimeline.getWindowCount(), -deferredTimeline.getPeriodCount());
        mediaSourceHolder.isRemoved = true;
        if (mediaSourceHolder.activeMediaPeriods == 0) {
            releaseChildSource(mediaSourceHolder);
        }
    }

    private void updateMediaSourceInternal(MediaSourceHolder mediaSourceHolder, Timeline timeline) {
        if (mediaSourceHolder == null) {
            throw new IllegalArgumentException();
        }
        DeferredTimeline deferredTimeline = mediaSourceHolder.timeline;
        if (deferredTimeline.getTimeline() == timeline) {
            return;
        }
        int windowCount = timeline.getWindowCount() - deferredTimeline.getWindowCount();
        int periodCount = timeline.getPeriodCount() - deferredTimeline.getPeriodCount();
        if (windowCount != 0 || periodCount != 0) {
            correctOffsets(mediaSourceHolder.childIndex + 1, 0, windowCount, periodCount);
        }
        mediaSourceHolder.timeline = deferredTimeline.cloneWithNewTimeline(timeline);
        if (!mediaSourceHolder.isPrepared) {
            for (int size = this.deferredMediaPeriods.size() - 1; size >= 0; size--) {
                if (this.deferredMediaPeriods.get(size).mediaSource == mediaSourceHolder.mediaSource) {
                    this.deferredMediaPeriods.get(size).createPeriod();
                    this.deferredMediaPeriods.remove(size);
                }
            }
        }
        mediaSourceHolder.isPrepared = true;
        maybeNotifyListener(null);
    }

    public synchronized void addMediaSource(MediaSource mediaSource) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, null);
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        MediaPeriod mediaPeriodCreatePeriod;
        MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(findMediaSourceHolderByPeriodIndex(mediaPeriodId.periodIndex));
        MediaSource.MediaPeriodId mediaPeriodIdCopyWithPeriodIndex = mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex - mediaSourceHolder.firstPeriodIndexInChild);
        if (mediaSourceHolder.isPrepared) {
            mediaPeriodCreatePeriod = mediaSourceHolder.mediaSource.createPeriod(mediaPeriodIdCopyWithPeriodIndex, allocator);
        } else {
            mediaPeriodCreatePeriod = new DeferredMediaPeriod(mediaSourceHolder.mediaSource, mediaPeriodIdCopyWithPeriodIndex, allocator);
            this.deferredMediaPeriods.add(mediaPeriodCreatePeriod);
        }
        this.mediaSourceByMediaPeriod.put(mediaPeriodCreatePeriod, mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriods++;
        return mediaPeriodCreatePeriod;
    }

    public synchronized MediaSource getMediaSource(int i9) {
        return this.mediaSourcesPublic.get(i9);
    }

    public synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i9, Object obj) {
        EventDispatcher eventDispatcher;
        if (i9 == 4) {
            ((EventDispatcher) obj).dispatchEvent();
            return;
        }
        this.preventListenerNotification = true;
        if (i9 == 0) {
            MessageData messageData = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, 1);
            addMediaSourceInternal(messageData.index, (MediaSource) messageData.customData);
            eventDispatcher = messageData.actionOnCompletion;
        } else if (i9 == 1) {
            MessageData messageData2 = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData2.index, ((Collection) messageData2.customData).size());
            addMediaSourcesInternal(messageData2.index, (Collection) messageData2.customData);
            eventDispatcher = messageData2.actionOnCompletion;
        } else if (i9 == 2) {
            MessageData messageData3 = (MessageData) obj;
            this.shuffleOrder = this.shuffleOrder.cloneAndRemove(messageData3.index);
            removeMediaSourceInternal(messageData3.index);
            eventDispatcher = messageData3.actionOnCompletion;
        } else {
            if (i9 != 3) {
                throw new IllegalStateException();
            }
            MessageData messageData4 = (MessageData) obj;
            ShuffleOrder shuffleOrderCloneAndRemove = this.shuffleOrder.cloneAndRemove(messageData4.index);
            this.shuffleOrder = shuffleOrderCloneAndRemove;
            this.shuffleOrder = shuffleOrderCloneAndRemove.cloneAndInsert(((Integer) messageData4.customData).intValue(), 1);
            moveMediaSourceInternal(messageData4.index, ((Integer) messageData4.customData).intValue());
            eventDispatcher = messageData4.actionOnCompletion;
        }
        this.preventListenerNotification = false;
        maybeNotifyListener(eventDispatcher);
    }

    public synchronized void moveMediaSource(int i9, int i10) {
        moveMediaSource(i9, i10, null);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public synchronized void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        super.prepareSource(exoPlayer, z8, listener);
        this.player = exoPlayer;
        this.listener = listener;
        this.preventListenerNotification = true;
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
        addMediaSourcesInternal(0, this.mediaSourcesPublic);
        this.preventListenerNotification = false;
        maybeNotifyListener(null);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolderRemove = this.mediaSourceByMediaPeriod.remove(mediaPeriod);
        if (mediaPeriod instanceof DeferredMediaPeriod) {
            this.deferredMediaPeriods.remove(mediaPeriod);
            ((DeferredMediaPeriod) mediaPeriod).releasePeriod();
        } else {
            mediaSourceHolderRemove.mediaSource.releasePeriod(mediaPeriod);
        }
        int i9 = mediaSourceHolderRemove.activeMediaPeriods - 1;
        mediaSourceHolderRemove.activeMediaPeriods = i9;
        if (i9 == 0 && mediaSourceHolderRemove.isRemoved) {
            releaseChildSource(mediaSourceHolderRemove);
        }
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        super.releaseSource();
        this.mediaSourceHolders.clear();
        this.player = null;
        this.listener = null;
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
    }

    public synchronized void removeMediaSource(int i9) {
        removeMediaSource(i9, null);
    }

    public DynamicConcatenatingMediaSource(boolean z8) {
        this(z8, new ShuffleOrder.DefaultShuffleOrder(0));
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline, Object obj) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    public DynamicConcatenatingMediaSource(boolean z8, ShuffleOrder shuffleOrder) {
        this.shuffleOrder = shuffleOrder;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.deferredMediaPeriods = new ArrayList(1);
        this.query = new MediaSourceHolder(null, null, -1, -1, -1);
        this.isAtomic = z8;
    }

    public synchronized void addMediaSource(MediaSource mediaSource, Runnable runnable) {
        addMediaSource(this.mediaSourcesPublic.size(), mediaSource, runnable);
    }

    public synchronized void addMediaSources(Collection<MediaSource> collection, Runnable runnable) {
        addMediaSources(this.mediaSourcesPublic.size(), collection, runnable);
    }

    public synchronized void moveMediaSource(int i9, int i10, Runnable runnable) {
        if (i9 == i10) {
            return;
        }
        List<MediaSource> list = this.mediaSourcesPublic;
        list.add(i10, list.remove(i9));
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.createMessage(this).setType(3).setPayload(new MessageData(i9, Integer.valueOf(i10), runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void removeMediaSource(int i9, Runnable runnable) {
        this.mediaSourcesPublic.remove(i9);
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.createMessage(this).setType(2).setPayload(new MessageData(i9, null, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void addMediaSource(int i9, MediaSource mediaSource) {
        addMediaSource(i9, mediaSource, null);
    }

    public synchronized void addMediaSources(int i9, Collection<MediaSource> collection) {
        addMediaSources(i9, collection, null);
    }

    public synchronized void addMediaSource(int i9, MediaSource mediaSource, Runnable runnable) {
        Assertions.checkNotNull(mediaSource);
        Assertions.checkArgument(!this.mediaSourcesPublic.contains(mediaSource));
        this.mediaSourcesPublic.add(i9, mediaSource);
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.createMessage(this).setType(0).setPayload(new MessageData(i9, mediaSource, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void addMediaSources(int i9, Collection<MediaSource> collection, Runnable runnable) {
        Iterator<MediaSource> it = collection.iterator();
        while (true) {
            boolean z8 = true;
            if (!it.hasNext()) {
                break;
            }
            MediaSource next = it.next();
            Assertions.checkNotNull(next);
            if (this.mediaSourcesPublic.contains(next)) {
                z8 = false;
            }
            Assertions.checkArgument(z8);
        }
        this.mediaSourcesPublic.addAll(i9, collection);
        if (this.player != null && !collection.isEmpty()) {
            this.player.createMessage(this).setType(1).setPayload(new MessageData(i9, collection, runnable)).send();
        } else if (runnable != null) {
            runnable.run();
        }
    }
}
