package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.trackselection.TrackSelection;

/* loaded from: classes.dex */
public interface MediaPeriod extends SequenceableLoader {

    public interface Callback extends SequenceableLoader.Callback<MediaPeriod> {
        void onPrepared(MediaPeriod mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    boolean continueLoading(long j9);

    void discardBuffer(long j9, boolean z8);

    long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters);

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    long getBufferedPositionUs();

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    long getNextLoadPositionUs();

    TrackGroupArray getTrackGroups();

    void maybeThrowPrepareError();

    void prepare(Callback callback, long j9);

    long readDiscontinuity();

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    void reevaluateBuffer(long j9);

    long seekToUs(long j9);

    long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9);
}
