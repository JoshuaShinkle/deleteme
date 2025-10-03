package com.google.android.exoplayer2.source;

import android.util.Pair;
import com.google.android.exoplayer2.Timeline;

/* loaded from: classes.dex */
abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int childCount;
    private final boolean isAtomic;
    private final ShuffleOrder shuffleOrder;

    public AbstractConcatenatedTimeline(boolean z8, ShuffleOrder shuffleOrder) {
        this.isAtomic = z8;
        this.shuffleOrder = shuffleOrder;
        this.childCount = shuffleOrder.getLength();
    }

    private int getNextChildIndex(int i9, boolean z8) {
        if (z8) {
            return this.shuffleOrder.getNextIndex(i9);
        }
        if (i9 < this.childCount - 1) {
            return i9 + 1;
        }
        return -1;
    }

    private int getPreviousChildIndex(int i9, boolean z8) {
        if (z8) {
            return this.shuffleOrder.getPreviousIndex(i9);
        }
        if (i9 > 0) {
            return i9 - 1;
        }
        return -1;
    }

    public abstract int getChildIndexByChildUid(Object obj);

    public abstract int getChildIndexByPeriodIndex(int i9);

    public abstract int getChildIndexByWindowIndex(int i9);

    public abstract Object getChildUidByChildIndex(int i9);

    public abstract int getFirstPeriodIndexByChildIndex(int i9);

    @Override // com.google.android.exoplayer2.Timeline
    public int getFirstWindowIndex(boolean z8) {
        if (this.childCount == 0) {
            return -1;
        }
        if (this.isAtomic) {
            z8 = false;
        }
        int firstIndex = z8 ? this.shuffleOrder.getFirstIndex() : 0;
        while (getTimelineByChildIndex(firstIndex).isEmpty()) {
            firstIndex = getNextChildIndex(firstIndex, z8);
            if (firstIndex == -1) {
                return -1;
            }
        }
        return getFirstWindowIndexByChildIndex(firstIndex) + getTimelineByChildIndex(firstIndex).getFirstWindowIndex(z8);
    }

    public abstract int getFirstWindowIndexByChildIndex(int i9);

    @Override // com.google.android.exoplayer2.Timeline
    public final int getIndexOfPeriod(Object obj) {
        int indexOfPeriod;
        if (!(obj instanceof Pair)) {
            return -1;
        }
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        int childIndexByChildUid = getChildIndexByChildUid(obj2);
        if (childIndexByChildUid == -1 || (indexOfPeriod = getTimelineByChildIndex(childIndexByChildUid).getIndexOfPeriod(obj3)) == -1) {
            return -1;
        }
        return getFirstPeriodIndexByChildIndex(childIndexByChildUid) + indexOfPeriod;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getLastWindowIndex(boolean z8) {
        int i9 = this.childCount;
        if (i9 == 0) {
            return -1;
        }
        if (this.isAtomic) {
            z8 = false;
        }
        int lastIndex = z8 ? this.shuffleOrder.getLastIndex() : i9 - 1;
        while (getTimelineByChildIndex(lastIndex).isEmpty()) {
            lastIndex = getPreviousChildIndex(lastIndex, z8);
            if (lastIndex == -1) {
                return -1;
            }
        }
        return getFirstWindowIndexByChildIndex(lastIndex) + getTimelineByChildIndex(lastIndex).getLastWindowIndex(z8);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getNextWindowIndex(int i9, int i10, boolean z8) {
        if (this.isAtomic) {
            if (i10 == 1) {
                i10 = 2;
            }
            z8 = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i9);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        int nextWindowIndex = getTimelineByChildIndex(childIndexByWindowIndex).getNextWindowIndex(i9 - firstWindowIndexByChildIndex, i10 != 2 ? i10 : 0, z8);
        if (nextWindowIndex != -1) {
            return firstWindowIndexByChildIndex + nextWindowIndex;
        }
        int nextChildIndex = getNextChildIndex(childIndexByWindowIndex, z8);
        while (nextChildIndex != -1 && getTimelineByChildIndex(nextChildIndex).isEmpty()) {
            nextChildIndex = getNextChildIndex(nextChildIndex, z8);
        }
        if (nextChildIndex != -1) {
            return getFirstWindowIndexByChildIndex(nextChildIndex) + getTimelineByChildIndex(nextChildIndex).getFirstWindowIndex(z8);
        }
        if (i10 == 2) {
            return getFirstWindowIndex(z8);
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public final Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
        int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i9);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByPeriodIndex);
        getTimelineByChildIndex(childIndexByPeriodIndex).getPeriod(i9 - getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex), period, z8);
        period.windowIndex += firstWindowIndexByChildIndex;
        if (z8) {
            period.uid = Pair.create(getChildUidByChildIndex(childIndexByPeriodIndex), period.uid);
        }
        return period;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPreviousWindowIndex(int i9, int i10, boolean z8) {
        if (this.isAtomic) {
            if (i10 == 1) {
                i10 = 2;
            }
            z8 = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i9);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        int previousWindowIndex = getTimelineByChildIndex(childIndexByWindowIndex).getPreviousWindowIndex(i9 - firstWindowIndexByChildIndex, i10 != 2 ? i10 : 0, z8);
        if (previousWindowIndex != -1) {
            return firstWindowIndexByChildIndex + previousWindowIndex;
        }
        int previousChildIndex = getPreviousChildIndex(childIndexByWindowIndex, z8);
        while (previousChildIndex != -1 && getTimelineByChildIndex(previousChildIndex).isEmpty()) {
            previousChildIndex = getPreviousChildIndex(previousChildIndex, z8);
        }
        if (previousChildIndex != -1) {
            return getFirstWindowIndexByChildIndex(previousChildIndex) + getTimelineByChildIndex(previousChildIndex).getLastWindowIndex(z8);
        }
        if (i10 == 2) {
            return getLastWindowIndex(z8);
        }
        return -1;
    }

    public abstract Timeline getTimelineByChildIndex(int i9);

    @Override // com.google.android.exoplayer2.Timeline
    public final Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i9);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        int firstPeriodIndexByChildIndex = getFirstPeriodIndexByChildIndex(childIndexByWindowIndex);
        getTimelineByChildIndex(childIndexByWindowIndex).getWindow(i9 - firstWindowIndexByChildIndex, window, z8, j9);
        window.firstPeriodIndex += firstPeriodIndexByChildIndex;
        window.lastPeriodIndex += firstPeriodIndexByChildIndex;
        return window;
    }
}
