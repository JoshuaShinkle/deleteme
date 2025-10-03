package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import java.io.IOException;

/* loaded from: classes.dex */
public final class DeferredMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;

    /* renamed from: id */
    private final MediaSource.MediaPeriodId f15314id;
    private PrepareErrorListener listener;
    private MediaPeriod mediaPeriod;
    public final MediaSource mediaSource;
    private boolean notifiedPrepareError;
    private long preparePositionUs;

    public interface PrepareErrorListener {
        void onPrepareError(IOException iOException);
    }

    public DeferredMediaPeriod(MediaSource mediaSource, MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        this.f15314id = mediaPeriodId;
        this.allocator = allocator;
        this.mediaSource = mediaSource;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        return mediaPeriod != null && mediaPeriod.continueLoading(j9);
    }

    public void createPeriod() {
        MediaPeriod mediaPeriodCreatePeriod = this.mediaSource.createPeriod(this.f15314id, this.allocator);
        this.mediaPeriod = mediaPeriodCreatePeriod;
        if (this.callback != null) {
            mediaPeriodCreatePeriod.prepare(this, this.preparePositionUs);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        this.mediaPeriod.discardBuffer(j9, z8);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j9, seekParameters);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.mediaPeriod.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        try {
            MediaPeriod mediaPeriod = this.mediaPeriod;
            if (mediaPeriod != null) {
                mediaPeriod.maybeThrowPrepareError();
            } else {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
            }
        } catch (IOException e9) {
            PrepareErrorListener prepareErrorListener = this.listener;
            if (prepareErrorListener == null) {
                throw e9;
            }
            if (this.notifiedPrepareError) {
                return;
            }
            this.notifiedPrepareError = true;
            prepareErrorListener.onPrepareError(e9);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        this.preparePositionUs = j9;
        MediaPeriod mediaPeriod = this.mediaPeriod;
        if (mediaPeriod != null) {
            mediaPeriod.prepare(this, j9);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return this.mediaPeriod.readDiscontinuity();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        this.mediaPeriod.reevaluateBuffer(j9);
    }

    public void releasePeriod() {
        MediaPeriod mediaPeriod = this.mediaPeriod;
        if (mediaPeriod != null) {
            this.mediaSource.releasePeriod(mediaPeriod);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        return this.mediaPeriod.seekToUs(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        return this.mediaPeriod.selectTracks(trackSelectionArr, zArr, sampleStreamArr, zArr2, j9);
    }

    public void setPrepareErrorListener(PrepareErrorListener prepareErrorListener) {
        this.listener = prepareErrorListener;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.callback.onContinueLoadingRequested(this);
    }
}
