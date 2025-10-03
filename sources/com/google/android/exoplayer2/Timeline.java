package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public abstract class Timeline {
    public static final Timeline EMPTY = new Timeline() { // from class: com.google.android.exoplayer2.Timeline.1
        @Override // com.google.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Period getPeriod(int i9, Period period, boolean z8) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.Timeline
        public Window getWindow(int i9, Window window, boolean z8, long j9) {
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 0;
        }
    };

    public static final class Period {
        private AdPlaybackState adPlaybackState;
        public long durationUs;

        /* renamed from: id */
        public Object f15300id;
        private long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        public int getAdCountInAdGroup(int i9) {
            return this.adPlaybackState.adGroups[i9].count;
        }

        public long getAdDurationUs(int i9, int i10) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.adGroups[i9];
            return adGroup.count != -1 ? adGroup.durationsUs[i10] : C3322C.TIME_UNSET;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long j9) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j9);
        }

        public int getAdGroupIndexForPositionUs(long j9) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j9);
        }

        public long getAdGroupTimeUs(int i9) {
            return this.adPlaybackState.adGroupTimesUs[i9];
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public long getDurationMs() {
            return C3322C.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int i9) {
            return this.adPlaybackState.adGroups[i9].getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int i9, int i10) {
            return this.adPlaybackState.adGroups[i9].getNextAdIndexToPlay(i10);
        }

        public long getPositionInWindowMs() {
            return C3322C.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public boolean hasPlayedAdGroup(int i9) {
            return !this.adPlaybackState.adGroups[i9].hasUnplayedAds();
        }

        public boolean isAdAvailable(int i9, int i10) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.adGroups[i9];
            return (adGroup.count == -1 || adGroup.states[i10] == 0) ? false : true;
        }

        public Period set(Object obj, Object obj2, int i9, long j9, long j10) {
            return set(obj, obj2, i9, j9, j10, AdPlaybackState.NONE);
        }

        public Period set(Object obj, Object obj2, int i9, long j9, long j10, AdPlaybackState adPlaybackState) {
            this.f15300id = obj;
            this.uid = obj2;
            this.windowIndex = i9;
            this.durationUs = j9;
            this.positionInWindowUs = j10;
            this.adPlaybackState = adPlaybackState;
            return this;
        }
    }

    public static final class Window {
        public long defaultPositionUs;
        public long durationUs;
        public int firstPeriodIndex;

        /* renamed from: id */
        public Object f15301id;
        public boolean isDynamic;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        public long windowStartTimeMs;

        public long getDefaultPositionMs() {
            return C3322C.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return C3322C.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return C3322C.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public Window set(Object obj, long j9, long j10, boolean z8, boolean z9, long j11, long j12, int i9, int i10, long j13) {
            this.f15301id = obj;
            this.presentationStartTimeMs = j9;
            this.windowStartTimeMs = j10;
            this.isSeekable = z8;
            this.isDynamic = z9;
            this.defaultPositionUs = j11;
            this.durationUs = j12;
            this.firstPeriodIndex = i9;
            this.lastPeriodIndex = i10;
            this.positionInFirstPeriodUs = j13;
            return this;
        }
    }

    public int getFirstWindowIndex(boolean z8) {
        return isEmpty() ? -1 : 0;
    }

    public abstract int getIndexOfPeriod(Object obj);

    public int getLastWindowIndex(boolean z8) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public final int getNextPeriodIndex(int i9, Period period, Window window, int i10, boolean z8) {
        int i11 = getPeriod(i9, period).windowIndex;
        if (getWindow(i11, window).lastPeriodIndex != i9) {
            return i9 + 1;
        }
        int nextWindowIndex = getNextWindowIndex(i11, i10, z8);
        if (nextWindowIndex == -1) {
            return -1;
        }
        return getWindow(nextWindowIndex, window).firstPeriodIndex;
    }

    public int getNextWindowIndex(int i9, int i10, boolean z8) {
        if (i10 == 0) {
            if (i9 == getLastWindowIndex(z8)) {
                return -1;
            }
            return i9 + 1;
        }
        if (i10 == 1) {
            return i9;
        }
        if (i10 == 2) {
            return i9 == getLastWindowIndex(z8) ? getFirstWindowIndex(z8) : i9 + 1;
        }
        throw new IllegalStateException();
    }

    public final Period getPeriod(int i9, Period period) {
        return getPeriod(i9, period, false);
    }

    public abstract Period getPeriod(int i9, Period period, boolean z8);

    public abstract int getPeriodCount();

    public final Pair<Integer, Long> getPeriodPosition(Window window, Period period, int i9, long j9) {
        return getPeriodPosition(window, period, i9, j9, 0L);
    }

    public int getPreviousWindowIndex(int i9, int i10, boolean z8) {
        if (i10 == 0) {
            if (i9 == getFirstWindowIndex(z8)) {
                return -1;
            }
            return i9 - 1;
        }
        if (i10 == 1) {
            return i9;
        }
        if (i10 == 2) {
            return i9 == getFirstWindowIndex(z8) ? getLastWindowIndex(z8) : i9 - 1;
        }
        throw new IllegalStateException();
    }

    public final Window getWindow(int i9, Window window) {
        return getWindow(i9, window, false);
    }

    public abstract Window getWindow(int i9, Window window, boolean z8, long j9);

    public abstract int getWindowCount();

    public final boolean isEmpty() {
        return getWindowCount() == 0;
    }

    public final boolean isLastPeriod(int i9, Period period, Window window, int i10, boolean z8) {
        return getNextPeriodIndex(i9, period, window, i10, z8) == -1;
    }

    public final Pair<Integer, Long> getPeriodPosition(Window window, Period period, int i9, long j9, long j10) {
        Assertions.checkIndex(i9, 0, getWindowCount());
        getWindow(i9, window, false, j10);
        if (j9 == C3322C.TIME_UNSET) {
            j9 = window.getDefaultPositionUs();
            if (j9 == C3322C.TIME_UNSET) {
                return null;
            }
        }
        int i10 = window.firstPeriodIndex;
        long positionInFirstPeriodUs = window.getPositionInFirstPeriodUs() + j9;
        long durationUs = getPeriod(i10, period).getDurationUs();
        while (durationUs != C3322C.TIME_UNSET && positionInFirstPeriodUs >= durationUs && i10 < window.lastPeriodIndex) {
            positionInFirstPeriodUs -= durationUs;
            i10++;
            durationUs = getPeriod(i10, period).getDurationUs();
        }
        return Pair.create(Integer.valueOf(i10), Long.valueOf(positionInFirstPeriodUs));
    }

    public final Window getWindow(int i9, Window window, boolean z8) {
        return getWindow(i9, window, z8, 0L);
    }
}
