package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private IllegalClippingException clippingError;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private MediaSource.Listener sourceListener;
    private final long startUs;

    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long endUs;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j9, long j10) throws IllegalClippingException {
            super(timeline);
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            }
            if (timeline.getPeriod(0, new Timeline.Period()).getPositionInWindowUs() != 0) {
                throw new IllegalClippingException(1);
            }
            Timeline.Window window = timeline.getWindow(0, new Timeline.Window(), false);
            j10 = j10 == Long.MIN_VALUE ? window.durationUs : j10;
            long j11 = window.durationUs;
            if (j11 != C3322C.TIME_UNSET) {
                j10 = j10 > j11 ? j11 : j10;
                if (j9 != 0 && !window.isSeekable) {
                    throw new IllegalClippingException(2);
                }
                if (j9 > j10) {
                    throw new IllegalClippingException(3);
                }
            }
            this.startUs = j9;
            this.endUs = j10;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i9, Timeline.Period period, boolean z8) {
            Timeline.Period period2 = this.timeline.getPeriod(0, period, z8);
            long j9 = this.endUs;
            long j10 = C3322C.TIME_UNSET;
            if (j9 != C3322C.TIME_UNSET) {
                j10 = j9 - this.startUs;
            }
            period2.durationUs = j10;
            return period2;
        }

        @Override // com.google.android.exoplayer2.source.ForwardingTimeline, com.google.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i9, Timeline.Window window, boolean z8, long j9) {
            Timeline.Window window2 = this.timeline.getWindow(0, window, z8, j9);
            long j10 = this.endUs;
            window2.durationUs = j10 != C3322C.TIME_UNSET ? j10 - this.startUs : -9223372036854775807L;
            long j11 = window2.defaultPositionUs;
            if (j11 != C3322C.TIME_UNSET) {
                long jMax = Math.max(j11, this.startUs);
                window2.defaultPositionUs = jMax;
                long j12 = this.endUs;
                if (j12 != C3322C.TIME_UNSET) {
                    jMax = Math.min(jMax, j12);
                }
                window2.defaultPositionUs = jMax - this.startUs;
            }
            long jUsToMs = C3322C.usToMs(this.startUs);
            long j13 = window2.presentationStartTimeMs;
            if (j13 != C3322C.TIME_UNSET) {
                window2.presentationStartTimeMs = j13 + jUsToMs;
            }
            long j14 = window2.windowStartTimeMs;
            if (j14 != C3322C.TIME_UNSET) {
                window2.windowStartTimeMs = j14 + jUsToMs;
            }
            return window2;
        }
    }

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 2;
        public static final int REASON_PERIOD_OFFSET_IN_WINDOW = 1;
        public static final int REASON_START_EXCEEDS_END = 3;
        public final int reason;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalClippingException(int i9) {
            this.reason = i9;
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long j9, long j10) {
        this(mediaSource, j9, j10, true);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator), this.enableInitialDiscontinuity);
        this.mediaPeriods.add(clippingMediaPeriod);
        clippingMediaPeriod.setClipping(this.startUs, this.endUs);
        return clippingMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IllegalClippingException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException != null) {
            throw illegalClippingException;
        }
        super.maybeThrowSourceInfoRefreshError();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        super.prepareSource(exoPlayer, z8, listener);
        this.sourceListener = listener;
        prepareChildSource(null, this.mediaSource);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource, com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        super.releaseSource();
        this.clippingError = null;
        this.sourceListener = null;
    }

    public ClippingMediaSource(MediaSource mediaSource, long j9, long j10, boolean z8) {
        Assertions.checkArgument(j9 >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource);
        this.startUs = j9;
        this.endUs = j10;
        this.enableInitialDiscontinuity = z8;
        this.mediaPeriods = new ArrayList<>();
    }

    @Override // com.google.android.exoplayer2.source.CompositeMediaSource
    public void onChildSourceInfoRefreshed(Void r72, MediaSource mediaSource, Timeline timeline, Object obj) {
        if (this.clippingError != null) {
            return;
        }
        try {
            this.sourceListener.onSourceInfoRefreshed(this, new ClippingTimeline(timeline, this.startUs, this.endUs), obj);
            int size = this.mediaPeriods.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.mediaPeriods.get(i9).setClipping(this.startUs, this.endUs);
            }
        } catch (IllegalClippingException e9) {
            this.clippingError = e9;
        }
    }
}
