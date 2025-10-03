package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* loaded from: classes.dex */
final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    private int pendingChildPrepareCount;
    public final MediaPeriod[] periods;
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    private TrackGroupArray trackGroups;

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.periods = mediaPeriodArr;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        return this.compositeSequenceableLoader.continueLoading(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            mediaPeriod.discardBuffer(j9, z8);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        return this.enabledPeriods[0].getAdjustedSeekPositionUs(j9, seekParameters);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.maybeThrowPrepareError();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        int i9 = this.pendingChildPrepareCount - 1;
        this.pendingChildPrepareCount = i9;
        if (i9 > 0) {
            return;
        }
        int i10 = 0;
        for (MediaPeriod mediaPeriod2 : this.periods) {
            i10 += mediaPeriod2.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i10];
        int i11 = 0;
        for (MediaPeriod mediaPeriod3 : this.periods) {
            TrackGroupArray trackGroups = mediaPeriod3.getTrackGroups();
            int i12 = trackGroups.length;
            int i13 = 0;
            while (i13 < i12) {
                trackGroupArr[i11] = trackGroups.get(i13);
                i13++;
                i11++;
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        MediaPeriod[] mediaPeriodArr = this.periods;
        this.pendingChildPrepareCount = mediaPeriodArr.length;
        for (MediaPeriod mediaPeriod : mediaPeriodArr) {
            mediaPeriod.prepare(this, j9);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        long discontinuity = this.periods[0].readDiscontinuity();
        int i9 = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.periods;
            if (i9 >= mediaPeriodArr.length) {
                if (discontinuity != C3322C.TIME_UNSET) {
                    for (MediaPeriod mediaPeriod : this.enabledPeriods) {
                        if (mediaPeriod != this.periods[0] && mediaPeriod.seekToUs(discontinuity) != discontinuity) {
                            throw new IllegalStateException("Children seeked to different positions");
                        }
                    }
                }
                return discontinuity;
            }
            if (mediaPeriodArr[i9].readDiscontinuity() != C3322C.TIME_UNSET) {
                throw new IllegalStateException("Child reported discontinuity");
            }
            i9++;
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        this.compositeSequenceableLoader.reevaluateBuffer(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        long jSeekToUs = this.enabledPeriods[0].seekToUs(j9);
        int i9 = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i9 >= mediaPeriodArr.length) {
                return jSeekToUs;
            }
            if (mediaPeriodArr[i9].seekToUs(jSeekToUs) != jSeekToUs) {
                throw new IllegalStateException("Children seeked to different positions");
            }
            i9++;
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[trackSelectionArr.length];
        int[] iArr2 = new int[trackSelectionArr.length];
        for (int i9 = 0; i9 < trackSelectionArr.length; i9++) {
            SampleStream sampleStream = sampleStreamArr2[i9];
            iArr[i9] = sampleStream == null ? -1 : this.streamPeriodIndices.get(sampleStream).intValue();
            iArr2[i9] = -1;
            TrackSelection trackSelection = trackSelectionArr[i9];
            if (trackSelection != null) {
                TrackGroup trackGroup = trackSelection.getTrackGroup();
                int i10 = 0;
                while (true) {
                    MediaPeriod[] mediaPeriodArr = this.periods;
                    if (i10 >= mediaPeriodArr.length) {
                        break;
                    }
                    if (mediaPeriodArr[i10].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i9] = i10;
                        break;
                    }
                    i10++;
                }
            }
        }
        this.streamPeriodIndices.clear();
        int length = trackSelectionArr.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[trackSelectionArr.length];
        TrackSelection[] trackSelectionArr2 = new TrackSelection[trackSelectionArr.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j10 = j9;
        int i11 = 0;
        while (i11 < this.periods.length) {
            for (int i12 = 0; i12 < trackSelectionArr.length; i12++) {
                TrackSelection trackSelection2 = null;
                sampleStreamArr4[i12] = iArr[i12] == i11 ? sampleStreamArr2[i12] : null;
                if (iArr2[i12] == i11) {
                    trackSelection2 = trackSelectionArr[i12];
                }
                trackSelectionArr2[i12] = trackSelection2;
            }
            int i13 = i11;
            TrackSelection[] trackSelectionArr3 = trackSelectionArr2;
            ArrayList arrayList2 = arrayList;
            long jSelectTracks = this.periods[i11].selectTracks(trackSelectionArr2, zArr, sampleStreamArr4, zArr2, j10);
            if (i13 == 0) {
                j10 = jSelectTracks;
            } else if (jSelectTracks != j10) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean z8 = false;
            for (int i14 = 0; i14 < trackSelectionArr.length; i14++) {
                if (iArr2[i14] == i13) {
                    Assertions.checkState(sampleStreamArr4[i14] != null);
                    sampleStreamArr3[i14] = sampleStreamArr4[i14];
                    this.streamPeriodIndices.put(sampleStreamArr4[i14], Integer.valueOf(i13));
                    z8 = true;
                } else if (iArr[i14] == i13) {
                    Assertions.checkState(sampleStreamArr4[i14] == null);
                }
            }
            if (z8) {
                arrayList2.add(this.periods[i13]);
            }
            i11 = i13 + 1;
            arrayList = arrayList2;
            trackSelectionArr2 = trackSelectionArr3;
            sampleStreamArr2 = sampleStreamArr;
        }
        SampleStream[] sampleStreamArr5 = sampleStreamArr2;
        ArrayList arrayList3 = arrayList;
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr5, 0, length);
        MediaPeriod[] mediaPeriodArr2 = new MediaPeriod[arrayList3.size()];
        this.enabledPeriods = mediaPeriodArr2;
        arrayList3.toArray(mediaPeriodArr2);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.enabledPeriods);
        return j10;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.trackGroups == null) {
            return;
        }
        this.callback.onContinueLoadingRequested(this);
    }
}
