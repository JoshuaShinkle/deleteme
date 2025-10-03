package com.google.android.exoplayer2;

import android.util.Log;
import com.google.android.exoplayer2.source.ClippingMediaPeriod;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
final class MediaPeriodHolder {
    private static final String TAG = "MediaPeriodHolder";
    public boolean hasEnabledTracks;
    public MediaPeriodInfo info;
    public final boolean[] mayRetainStreamFlags;
    public final MediaPeriod mediaPeriod;
    private final MediaSource mediaSource;
    public MediaPeriodHolder next;
    private TrackSelectorResult periodTrackSelectorResult;
    public boolean prepared;
    private final RendererCapabilities[] rendererCapabilities;
    public long rendererPositionOffsetUs;
    public final SampleStream[] sampleStreams;
    private final TrackSelector trackSelector;
    public TrackSelectorResult trackSelectorResult;
    public final Object uid;

    public MediaPeriodHolder(RendererCapabilities[] rendererCapabilitiesArr, long j9, TrackSelector trackSelector, Allocator allocator, MediaSource mediaSource, Object obj, MediaPeriodInfo mediaPeriodInfo) {
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.rendererPositionOffsetUs = j9 - mediaPeriodInfo.startPositionUs;
        this.trackSelector = trackSelector;
        this.mediaSource = mediaSource;
        this.uid = Assertions.checkNotNull(obj);
        this.info = mediaPeriodInfo;
        this.sampleStreams = new SampleStream[rendererCapabilitiesArr.length];
        this.mayRetainStreamFlags = new boolean[rendererCapabilitiesArr.length];
        MediaPeriod mediaPeriodCreatePeriod = mediaSource.createPeriod(mediaPeriodInfo.f15299id, allocator);
        if (mediaPeriodInfo.endPositionUs != Long.MIN_VALUE) {
            ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(mediaPeriodCreatePeriod, true);
            clippingMediaPeriod.setClipping(0L, mediaPeriodInfo.endPositionUs);
            mediaPeriodCreatePeriod = clippingMediaPeriod;
        }
        this.mediaPeriod = mediaPeriodCreatePeriod;
    }

    private void associateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i9 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i9 >= rendererCapabilitiesArr.length) {
                return;
            }
            if (rendererCapabilitiesArr[i9].getTrackType() == 5 && this.trackSelectorResult.renderersEnabled[i9]) {
                sampleStreamArr[i9] = new EmptySampleStream();
            }
            i9++;
        }
    }

    private void disableTrackSelectionsInResult(TrackSelectorResult trackSelectorResult) {
        int i9 = 0;
        while (true) {
            boolean[] zArr = trackSelectorResult.renderersEnabled;
            if (i9 >= zArr.length) {
                return;
            }
            boolean z8 = zArr[i9];
            TrackSelection trackSelection = trackSelectorResult.selections.get(i9);
            if (z8 && trackSelection != null) {
                trackSelection.disable();
            }
            i9++;
        }
    }

    private void disassociateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i9 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i9 >= rendererCapabilitiesArr.length) {
                return;
            }
            if (rendererCapabilitiesArr[i9].getTrackType() == 5) {
                sampleStreamArr[i9] = null;
            }
            i9++;
        }
    }

    private void enableTrackSelectionsInResult(TrackSelectorResult trackSelectorResult) {
        int i9 = 0;
        while (true) {
            boolean[] zArr = trackSelectorResult.renderersEnabled;
            if (i9 >= zArr.length) {
                return;
            }
            boolean z8 = zArr[i9];
            TrackSelection trackSelection = trackSelectorResult.selections.get(i9);
            if (z8 && trackSelection != null) {
                trackSelection.enable();
            }
            i9++;
        }
    }

    private void updatePeriodTrackSelectorResult(TrackSelectorResult trackSelectorResult) {
        TrackSelectorResult trackSelectorResult2 = this.periodTrackSelectorResult;
        if (trackSelectorResult2 != null) {
            disableTrackSelectionsInResult(trackSelectorResult2);
        }
        this.periodTrackSelectorResult = trackSelectorResult;
        if (trackSelectorResult != null) {
            enableTrackSelectionsInResult(trackSelectorResult);
        }
    }

    public long applyTrackSelection(long j9, boolean z8) {
        return applyTrackSelection(j9, z8, new boolean[this.rendererCapabilities.length]);
    }

    public void continueLoading(long j9) {
        this.mediaPeriod.continueLoading(toPeriodTime(j9));
    }

    public long getBufferedPositionUs(boolean z8) {
        if (!this.prepared) {
            return this.info.startPositionUs;
        }
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        return (bufferedPositionUs == Long.MIN_VALUE && z8) ? this.info.durationUs : bufferedPositionUs;
    }

    public long getDurationUs() {
        return this.info.durationUs;
    }

    public long getNextLoadPositionUs() {
        if (this.prepared) {
            return this.mediaPeriod.getNextLoadPositionUs();
        }
        return 0L;
    }

    public long getRendererOffset() {
        return this.rendererPositionOffsetUs;
    }

    public TrackSelectorResult handlePrepared(float f9) {
        this.prepared = true;
        selectTracks(f9);
        long jApplyTrackSelection = applyTrackSelection(this.info.startPositionUs, false);
        long j9 = this.rendererPositionOffsetUs;
        MediaPeriodInfo mediaPeriodInfo = this.info;
        this.rendererPositionOffsetUs = j9 + (mediaPeriodInfo.startPositionUs - jApplyTrackSelection);
        this.info = mediaPeriodInfo.copyWithStartPositionUs(jApplyTrackSelection);
        return this.trackSelectorResult;
    }

    public boolean isFullyBuffered() {
        return this.prepared && (!this.hasEnabledTracks || this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE);
    }

    public void reevaluateBuffer(long j9) {
        if (this.prepared) {
            this.mediaPeriod.reevaluateBuffer(toPeriodTime(j9));
        }
    }

    public void release() {
        updatePeriodTrackSelectorResult(null);
        try {
            if (this.info.endPositionUs != Long.MIN_VALUE) {
                this.mediaSource.releasePeriod(((ClippingMediaPeriod) this.mediaPeriod).mediaPeriod);
            } else {
                this.mediaSource.releasePeriod(this.mediaPeriod);
            }
        } catch (RuntimeException e9) {
            Log.e(TAG, "Period release failed.", e9);
        }
    }

    public boolean selectTracks(float f9) {
        TrackSelectorResult trackSelectorResultSelectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, this.mediaPeriod.getTrackGroups());
        if (trackSelectorResultSelectTracks.isEquivalent(this.periodTrackSelectorResult)) {
            return false;
        }
        this.trackSelectorResult = trackSelectorResultSelectTracks;
        for (TrackSelection trackSelection : trackSelectorResultSelectTracks.selections.getAll()) {
            if (trackSelection != null) {
                trackSelection.onPlaybackSpeed(f9);
            }
        }
        return true;
    }

    public long toPeriodTime(long j9) {
        return j9 - getRendererOffset();
    }

    public long toRendererTime(long j9) {
        return j9 + getRendererOffset();
    }

    public long applyTrackSelection(long j9, boolean z8, boolean[] zArr) {
        TrackSelectionArray trackSelectionArray = this.trackSelectorResult.selections;
        int i9 = 0;
        while (true) {
            boolean z9 = true;
            if (i9 >= trackSelectionArray.length) {
                break;
            }
            boolean[] zArr2 = this.mayRetainStreamFlags;
            if (z8 || !this.trackSelectorResult.isEquivalent(this.periodTrackSelectorResult, i9)) {
                z9 = false;
            }
            zArr2[i9] = z9;
            i9++;
        }
        disassociateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        updatePeriodTrackSelectorResult(this.trackSelectorResult);
        long jSelectTracks = this.mediaPeriod.selectTracks(trackSelectionArray.getAll(), this.mayRetainStreamFlags, this.sampleStreams, zArr, j9);
        associateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.hasEnabledTracks = false;
        int i10 = 0;
        while (true) {
            SampleStream[] sampleStreamArr = this.sampleStreams;
            if (i10 >= sampleStreamArr.length) {
                return jSelectTracks;
            }
            if (sampleStreamArr[i10] != null) {
                Assertions.checkState(this.trackSelectorResult.renderersEnabled[i10]);
                if (this.rendererCapabilities[i10].getTrackType() != 5) {
                    this.hasEnabledTracks = true;
                }
            } else {
                Assertions.checkState(trackSelectionArray.get(i10) == null);
            }
            i10++;
        }
    }
}
