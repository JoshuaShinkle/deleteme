package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public final class SinglePeriodTimeline extends Timeline {

    /* renamed from: ID */
    private static final Object f15315ID = new Object();
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    public SinglePeriodTimeline(long j9, boolean z8, boolean z9) {
        this(j9, j9, 0L, 0L, z8, z9);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getIndexOfPeriod(Object obj) {
        return f15315ID.equals(obj) ? 0 : -1;
    }

    @Override // com.google.android.exoplayer2.Timeline
    public Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
        Assertions.checkIndex(i9, 0, 1);
        Object obj = z8 ? f15315ID : null;
        return period.set(obj, obj, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getPeriodCount() {
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030 A[PHI: r1
      0x0030: PHI (r1v4 long) = (r1v3 long), (r1v3 long), (r1v7 long) binds: [B:7:0x0014, B:9:0x001a, B:14:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.exoplayer2.Timeline
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
        long j10;
        Assertions.checkIndex(i9, 0, 1);
        Object obj = z8 ? f15315ID : null;
        long j11 = this.windowDefaultStartPositionUs;
        boolean z9 = this.isDynamic;
        if (!z9 || j9 == 0) {
            j10 = j11;
        } else {
            long j12 = this.windowDurationUs;
            if (j12 != C3322C.TIME_UNSET) {
                j11 += j9;
                if (j11 > j12) {
                }
            }
            j10 = -9223372036854775807L;
        }
        return window.set(obj, this.presentationStartTimeMs, this.windowStartTimeMs, this.isSeekable, z9, j10, this.windowDurationUs, 0, 0, this.windowPositionInPeriodUs);
    }

    @Override // com.google.android.exoplayer2.Timeline
    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j9, long j10, long j11, long j12, boolean z8, boolean z9) {
        this(C3322C.TIME_UNSET, C3322C.TIME_UNSET, j9, j10, j11, j12, z8, z9);
    }

    public SinglePeriodTimeline(long j9, long j10, long j11, long j12, long j13, long j14, boolean z8, boolean z9) {
        this.presentationStartTimeMs = j9;
        this.windowStartTimeMs = j10;
        this.periodDurationUs = j11;
        this.windowDurationUs = j12;
        this.windowPositionInPeriodUs = j13;
        this.windowDefaultStartPositionUs = j14;
        this.isSeekable = z8;
        this.isDynamic = z9;
    }
}
