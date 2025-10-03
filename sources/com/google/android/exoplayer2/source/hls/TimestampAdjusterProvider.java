package com.google.android.exoplayer2.source.hls;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

/* loaded from: classes.dex */
public final class TimestampAdjusterProvider {
    private final SparseArray<TimestampAdjuster> timestampAdjusters = new SparseArray<>();

    public TimestampAdjuster getAdjuster(int i9) {
        TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i9);
        if (timestampAdjuster != null) {
            return timestampAdjuster;
        }
        TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(Long.MAX_VALUE);
        this.timestampAdjusters.put(i9, timestampAdjuster2);
        return timestampAdjuster2;
    }

    public void reset() {
        this.timestampAdjusters.clear();
    }
}
