package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C3322C;

/* loaded from: classes.dex */
public final class TimestampAdjuster {
    public static final long DO_NOT_OFFSET = Long.MAX_VALUE;
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    private long firstSampleTimestampUs;
    private volatile long lastSampleTimestamp = C3322C.TIME_UNSET;
    private long timestampOffsetUs;

    public TimestampAdjuster(long j9) {
        setFirstSampleTimestampUs(j9);
    }

    public static long ptsToUs(long j9) {
        return (j9 * C3322C.MICROS_PER_SECOND) / 90000;
    }

    public static long usToPts(long j9) {
        return (j9 * 90000) / C3322C.MICROS_PER_SECOND;
    }

    public long adjustSampleTimestamp(long j9) {
        if (j9 == C3322C.TIME_UNSET) {
            return C3322C.TIME_UNSET;
        }
        if (this.lastSampleTimestamp != C3322C.TIME_UNSET) {
            this.lastSampleTimestamp = j9;
        } else {
            long j10 = this.firstSampleTimestampUs;
            if (j10 != Long.MAX_VALUE) {
                this.timestampOffsetUs = j10 - j9;
            }
            synchronized (this) {
                this.lastSampleTimestamp = j9;
                notifyAll();
            }
        }
        return j9 + this.timestampOffsetUs;
    }

    public long adjustTsTimestamp(long j9) {
        if (j9 == C3322C.TIME_UNSET) {
            return C3322C.TIME_UNSET;
        }
        if (this.lastSampleTimestamp != C3322C.TIME_UNSET) {
            long jUsToPts = usToPts(this.lastSampleTimestamp);
            long j10 = (4294967296L + jUsToPts) / MAX_PTS_PLUS_ONE;
            long j11 = ((j10 - 1) * MAX_PTS_PLUS_ONE) + j9;
            j9 += j10 * MAX_PTS_PLUS_ONE;
            if (Math.abs(j11 - jUsToPts) < Math.abs(j9 - jUsToPts)) {
                j9 = j11;
            }
        }
        return adjustSampleTimestamp(ptsToUs(j9));
    }

    public long getFirstSampleTimestampUs() {
        return this.firstSampleTimestampUs;
    }

    public long getLastAdjustedTimestampUs() {
        if (this.lastSampleTimestamp != C3322C.TIME_UNSET) {
            return this.lastSampleTimestamp;
        }
        long j9 = this.firstSampleTimestampUs;
        return j9 != Long.MAX_VALUE ? j9 : C3322C.TIME_UNSET;
    }

    public long getTimestampOffsetUs() {
        if (this.firstSampleTimestampUs == Long.MAX_VALUE) {
            return 0L;
        }
        return this.lastSampleTimestamp == C3322C.TIME_UNSET ? C3322C.TIME_UNSET : this.timestampOffsetUs;
    }

    public void reset() {
        this.lastSampleTimestamp = C3322C.TIME_UNSET;
    }

    public synchronized void setFirstSampleTimestampUs(long j9) {
        Assertions.checkState(this.lastSampleTimestamp == C3322C.TIME_UNSET);
        this.firstSampleTimestampUs = j9;
    }

    public synchronized void waitUntilInitialized() {
        while (this.lastSampleTimestamp == C3322C.TIME_UNSET) {
            wait();
        }
    }
}
