package com.google.android.exoplayer2.upstream;

/* loaded from: classes.dex */
public interface BandwidthMeter {
    public static final long NO_ESTIMATE = -1;

    public interface EventListener {
        void onBandwidthSample(int i9, long j9, long j10);
    }

    long getBitrateEstimate();
}
