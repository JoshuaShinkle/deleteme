package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.SlidingPercentile;

/* loaded from: classes.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener<Object> {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    public static final int DEFAULT_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private long bitrateEstimate;
    private final Clock clock;
    private final Handler eventHandler;
    private final BandwidthMeter.EventListener eventListener;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    public DefaultBandwidthMeter() {
        this(null, null);
    }

    private void notifyBandwidthSample(final int i9, final long j9, final long j10) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer2.upstream.DefaultBandwidthMeter.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultBandwidthMeter.this.eventListener.onBandwidthSample(i9, j9, j10);
            }
        });
    }

    @Override // com.google.android.exoplayer2.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onBytesTransferred(Object obj, int i9) {
        this.sampleBytesTransferred += i9;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferEnd(Object obj) {
        Assertions.checkState(this.streamCount > 0);
        long jElapsedRealtime = this.clock.elapsedRealtime();
        int i9 = (int) (jElapsedRealtime - this.sampleStartTimeMs);
        this.totalElapsedTimeMs += i9;
        long j9 = this.totalBytesTransferred;
        long j10 = this.sampleBytesTransferred;
        this.totalBytesTransferred = j9 + j10;
        if (i9 > 0) {
            this.slidingPercentile.addSample((int) Math.sqrt(j10), (8000 * j10) / r7);
            if (this.totalElapsedTimeMs >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS || this.totalBytesTransferred >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                float percentile = this.slidingPercentile.getPercentile(0.5f);
                this.bitrateEstimate = Float.isNaN(percentile) ? -1L : (long) percentile;
            }
        }
        notifyBandwidthSample(i9, this.sampleBytesTransferred, this.bitrateEstimate);
        int i10 = this.streamCount - 1;
        this.streamCount = i10;
        if (i10 > 0) {
            this.sampleStartTimeMs = jElapsedRealtime;
        }
        this.sampleBytesTransferred = 0L;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferStart(Object obj, DataSpec dataSpec) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener) {
        this(handler, eventListener, 2000);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener, int i9) {
        this(handler, eventListener, i9, Clock.DEFAULT);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener, int i9, Clock clock) {
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.slidingPercentile = new SlidingPercentile(i9);
        this.clock = clock;
        this.bitrateEstimate = -1L;
    }
}
