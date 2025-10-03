package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.List;

/* loaded from: classes.dex */
public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_INITIAL_BITRATE = 800000;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    public static final long DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 2000;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    private long lastBufferEvaluationMs;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxInitialBitrate;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private final long minTimeBetweenBufferReevaluationMs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    public static final class Factory implements TrackSelection.Factory {
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxInitialBitrate;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;
        private final long minTimeBetweenBufferReevaluationMs;

        public Factory(BandwidthMeter bandwidthMeter) {
            this(bandwidthMeter, AdaptiveTrackSelection.DEFAULT_MAX_INITIAL_BITRATE, 10000, 25000, 25000, 0.75f, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
        }

        public Factory(BandwidthMeter bandwidthMeter, int i9, int i10, int i11, int i12, float f9) {
            this(bandwidthMeter, i9, i10, i11, i12, f9, 0.75f, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection.Factory
        public AdaptiveTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            return new AdaptiveTrackSelection(trackGroup, iArr, this.bandwidthMeter, this.maxInitialBitrate, this.minDurationForQualityIncreaseMs, this.maxDurationForQualityDecreaseMs, this.minDurationToRetainAfterDiscardMs, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, this.minTimeBetweenBufferReevaluationMs, this.clock);
        }

        public Factory(BandwidthMeter bandwidthMeter, int i9, int i10, int i11, int i12, float f9, float f10, long j9, Clock clock) {
            this.bandwidthMeter = bandwidthMeter;
            this.maxInitialBitrate = i9;
            this.minDurationForQualityIncreaseMs = i10;
            this.maxDurationForQualityDecreaseMs = i11;
            this.minDurationToRetainAfterDiscardMs = i12;
            this.bandwidthFraction = f9;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f10;
            this.minTimeBetweenBufferReevaluationMs = j9;
            this.clock = clock;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter) {
        this(trackGroup, iArr, bandwidthMeter, DEFAULT_MAX_INITIAL_BITRATE, 10000L, 25000L, 25000L, 0.75f, 0.75f, DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS, Clock.DEFAULT);
    }

    private int determineIdealSelectedIndex(long j9) {
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        long j10 = bitrateEstimate == -1 ? this.maxInitialBitrate : (long) (bitrateEstimate * this.bandwidthFraction);
        int i9 = 0;
        for (int i10 = 0; i10 < this.length; i10++) {
            if (j9 == Long.MIN_VALUE || !isBlacklisted(i10, j9)) {
                if (Math.round(getFormat(i10).bitrate * this.playbackSpeed) <= j10) {
                    return i10;
                }
                i9 = i10;
            }
        }
        return i9;
    }

    private long minDurationForQualityIncreaseUs(long j9) {
        return (j9 > C3322C.TIME_UNSET ? 1 : (j9 == C3322C.TIME_UNSET ? 0 : -1)) != 0 && (j9 > this.minDurationForQualityIncreaseUs ? 1 : (j9 == this.minDurationForQualityIncreaseUs ? 0 : -1)) <= 0 ? (long) (j9 * this.bufferedFractionToLiveEdgeForQualityIncrease) : this.minDurationForQualityIncreaseUs;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.TrackSelection
    public void enable() {
        this.lastBufferEvaluationMs = C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.TrackSelection
    public int evaluateQueueSize(long j9, List<? extends MediaChunk> list) {
        int i9;
        int i10;
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long j10 = this.lastBufferEvaluationMs;
        if (j10 != C3322C.TIME_UNSET && jElapsedRealtime - j10 < this.minTimeBetweenBufferReevaluationMs) {
            return list.size();
        }
        this.lastBufferEvaluationMs = jElapsedRealtime;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        if (Util.getPlayoutDurationForMediaDuration(list.get(size - 1).startTimeUs - j9, this.playbackSpeed) < this.minDurationToRetainAfterDiscardUs) {
            return size;
        }
        Format format = getFormat(determineIdealSelectedIndex(jElapsedRealtime));
        for (int i11 = 0; i11 < size; i11++) {
            MediaChunk mediaChunk = list.get(i11);
            Format format2 = mediaChunk.trackFormat;
            if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j9, this.playbackSpeed) >= this.minDurationToRetainAfterDiscardUs && format2.bitrate < format.bitrate && (i9 = format2.height) != -1 && i9 < 720 && (i10 = format2.width) != -1 && i10 < 1280 && i9 < format.height) {
                return i11;
            }
        }
        return size;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public Object getSelectionData() {
        return null;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // com.google.android.exoplayer2.trackselection.BaseTrackSelection, com.google.android.exoplayer2.trackselection.TrackSelection
    public void onPlaybackSpeed(float f9) {
        this.playbackSpeed = f9;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j9, long j10, long j11) {
        long jElapsedRealtime = this.clock.elapsedRealtime();
        int i9 = this.selectedIndex;
        int iDetermineIdealSelectedIndex = determineIdealSelectedIndex(jElapsedRealtime);
        this.selectedIndex = iDetermineIdealSelectedIndex;
        if (iDetermineIdealSelectedIndex == i9) {
            return;
        }
        if (!isBlacklisted(i9, jElapsedRealtime)) {
            Format format = getFormat(i9);
            Format format2 = getFormat(this.selectedIndex);
            if (format2.bitrate > format.bitrate && j10 < minDurationForQualityIncreaseUs(j11)) {
                this.selectedIndex = i9;
            } else if (format2.bitrate < format.bitrate && j10 >= this.maxDurationForQualityDecreaseUs) {
                this.selectedIndex = i9;
            }
        }
        if (this.selectedIndex != i9) {
            this.reason = 3;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter, int i9, long j9, long j10, long j11, float f9, float f10, long j12, Clock clock) {
        super(trackGroup, iArr);
        this.bandwidthMeter = bandwidthMeter;
        this.maxInitialBitrate = i9;
        this.minDurationForQualityIncreaseUs = j9 * 1000;
        this.maxDurationForQualityDecreaseUs = j10 * 1000;
        this.minDurationToRetainAfterDiscardUs = j11 * 1000;
        this.bandwidthFraction = f9;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f10;
        this.minTimeBetweenBufferReevaluationMs = j12;
        this.clock = clock;
        this.playbackSpeed = 1.0f;
        this.selectedIndex = determineIdealSelectedIndex(Long.MIN_VALUE);
        this.reason = 1;
        this.lastBufferEvaluationMs = C3322C.TIME_UNSET;
    }
}
