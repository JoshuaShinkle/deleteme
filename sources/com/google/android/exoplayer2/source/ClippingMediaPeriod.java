package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;

/* loaded from: classes.dex */
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];
    long startUs;

    public final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.childStream = sampleStream;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return !ClippingMediaPeriod.this.isPendingInitialDiscontinuity() && this.childStream.isReady();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() {
            this.childStream.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int data = this.childStream.readData(formatHolder, decoderInputBuffer, z8);
            if (data == -5) {
                Format format = formatHolder.format;
                int i9 = format.encoderDelay;
                if (i9 != -1 || format.encoderPadding != -1) {
                    ClippingMediaPeriod clippingMediaPeriod = ClippingMediaPeriod.this;
                    if (clippingMediaPeriod.startUs != 0) {
                        i9 = 0;
                    }
                    formatHolder.format = format.copyWithGaplessInfo(i9, clippingMediaPeriod.endUs == Long.MIN_VALUE ? format.encoderPadding : 0);
                }
                return -5;
            }
            ClippingMediaPeriod clippingMediaPeriod2 = ClippingMediaPeriod.this;
            long j9 = clippingMediaPeriod2.endUs;
            if (j9 == Long.MIN_VALUE || ((data != -4 || decoderInputBuffer.timeUs < j9) && !(data == -3 && clippingMediaPeriod2.getBufferedPositionUs() == Long.MIN_VALUE))) {
                if (data == -4 && !decoderInputBuffer.isEndOfStream()) {
                    decoderInputBuffer.timeUs -= ClippingMediaPeriod.this.startUs;
                }
                return data;
            }
            decoderInputBuffer.clear();
            decoderInputBuffer.setFlags(4);
            this.sentEos = true;
            return -4;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int skipData(long j9) {
            if (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }
            return this.childStream.skipData(ClippingMediaPeriod.this.startUs + j9);
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod, boolean z8) {
        this.mediaPeriod = mediaPeriod;
        this.pendingInitialDiscontinuityPositionUs = z8 ? 0L : -9223372036854775807L;
        this.startUs = C3322C.TIME_UNSET;
        this.endUs = C3322C.TIME_UNSET;
    }

    private SeekParameters clipSeekParameters(long j9, SeekParameters seekParameters) {
        long jMin = Math.min(j9 - this.startUs, seekParameters.toleranceBeforeUs);
        long j10 = this.endUs;
        long jMin2 = j10 == Long.MIN_VALUE ? seekParameters.toleranceAfterUs : Math.min(j10 - j9, seekParameters.toleranceAfterUs);
        return (jMin == seekParameters.toleranceBeforeUs && jMin2 == seekParameters.toleranceAfterUs) ? seekParameters : new SeekParameters(jMin, jMin2);
    }

    private static boolean shouldKeepInitialDiscontinuity(long j9, TrackSelection[] trackSelectionArr) {
        if (j9 != 0) {
            for (TrackSelection trackSelection : trackSelectionArr) {
                if (trackSelection != null && !MimeTypes.isAudio(trackSelection.getSelectedFormat().sampleMimeType)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        return this.mediaPeriod.continueLoading(j9 + this.startUs);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        this.mediaPeriod.discardBuffer(j9 + this.startUs, z8);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        long j10 = this.startUs;
        if (j9 == j10) {
            return 0L;
        }
        long j11 = j9 + j10;
        return this.mediaPeriod.getAdjustedSeekPositionUs(j11, clipSeekParameters(j11, seekParameters)) - this.startUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j9 = this.endUs;
            if (j9 == Long.MIN_VALUE || bufferedPositionUs < j9) {
                return Math.max(0L, bufferedPositionUs - this.startUs);
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j9 = this.endUs;
            if (j9 == Long.MIN_VALUE || nextLoadPositionUs < j9) {
                return nextLoadPositionUs - this.startUs;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public boolean isPendingInitialDiscontinuity() {
        return this.pendingInitialDiscontinuityPositionUs != C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        Assertions.checkState((this.startUs == C3322C.TIME_UNSET || this.endUs == C3322C.TIME_UNSET) ? false : true);
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, this.startUs + j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        if (isPendingInitialDiscontinuity()) {
            long j9 = this.pendingInitialDiscontinuityPositionUs;
            this.pendingInitialDiscontinuityPositionUs = C3322C.TIME_UNSET;
            long discontinuity = readDiscontinuity();
            return discontinuity != C3322C.TIME_UNSET ? discontinuity : j9;
        }
        long discontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (discontinuity2 == C3322C.TIME_UNSET) {
            return C3322C.TIME_UNSET;
        }
        boolean z8 = true;
        Assertions.checkState(discontinuity2 >= this.startUs);
        long j10 = this.endUs;
        if (j10 != Long.MIN_VALUE && discontinuity2 > j10) {
            z8 = false;
        }
        Assertions.checkState(z8);
        return discontinuity2 - this.startUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        this.mediaPeriod.reevaluateBuffer(j9 + this.startUs);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long seekToUs(long j9) {
        this.pendingInitialDiscontinuityPositionUs = C3322C.TIME_UNSET;
        boolean z8 = false;
        for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
            if (clippingSampleStream != null) {
                clippingSampleStream.clearSentEos();
            }
        }
        long j10 = j9 + this.startUs;
        long jSeekToUs = this.mediaPeriod.seekToUs(j10);
        if (jSeekToUs == j10) {
            z8 = true;
        } else if (jSeekToUs >= this.startUs) {
            long j11 = this.endUs;
            if (j11 == Long.MIN_VALUE || jSeekToUs <= j11) {
            }
        }
        Assertions.checkState(z8);
        return jSeekToUs - this.startUs;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        boolean z8;
        this.sampleStreams = new ClippingSampleStream[sampleStreamArr.length];
        SampleStream[] sampleStreamArr2 = new SampleStream[sampleStreamArr.length];
        int i9 = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i9 >= sampleStreamArr.length) {
                break;
            }
            ClippingSampleStream[] clippingSampleStreamArr = this.sampleStreams;
            ClippingSampleStream clippingSampleStream = (ClippingSampleStream) sampleStreamArr[i9];
            clippingSampleStreamArr[i9] = clippingSampleStream;
            if (clippingSampleStream != null) {
                sampleStream = clippingSampleStream.childStream;
            }
            sampleStreamArr2[i9] = sampleStream;
            i9++;
        }
        long jSelectTracks = this.mediaPeriod.selectTracks(trackSelectionArr, zArr, sampleStreamArr2, zArr2, j9 + this.startUs) - this.startUs;
        this.pendingInitialDiscontinuityPositionUs = (isPendingInitialDiscontinuity() && j9 == 0 && shouldKeepInitialDiscontinuity(this.startUs, trackSelectionArr)) ? jSelectTracks : C3322C.TIME_UNSET;
        if (jSelectTracks == j9) {
            z8 = true;
        } else {
            if (jSelectTracks >= 0) {
                long j10 = this.endUs;
                if (j10 == Long.MIN_VALUE || this.startUs + jSelectTracks <= j10) {
                }
            }
            z8 = false;
        }
        Assertions.checkState(z8);
        for (int i10 = 0; i10 < sampleStreamArr.length; i10++) {
            SampleStream sampleStream2 = sampleStreamArr2[i10];
            if (sampleStream2 == null) {
                this.sampleStreams[i10] = null;
            } else if (sampleStreamArr[i10] == null || this.sampleStreams[i10].childStream != sampleStream2) {
                this.sampleStreams[i10] = new ClippingSampleStream(sampleStream2);
            }
            sampleStreamArr[i10] = this.sampleStreams[i10];
        }
        return jSelectTracks;
    }

    public void setClipping(long j9, long j10) {
        this.startUs = j9;
        this.endUs = j10;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.callback.onContinueLoadingRequested(this);
    }
}
