package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    public static final class Factory implements TrackSelection.Factory {
        private final Object data;
        private final int reason;

        public Factory() {
            this.reason = 0;
            this.data = null;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection.Factory
        public FixedTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            Assertions.checkArgument(iArr.length == 1);
            return new FixedTrackSelection(trackGroup, iArr[0], this.reason, this.data);
        }

        public Factory(int i9, Object obj) {
            this.reason = i9;
            this.data = obj;
        }
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i9) {
        this(trackGroup, i9, 0, null);
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public int getSelectedIndex() {
        return 0;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public Object getSelectionData() {
        return this.data;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j9, long j10, long j11) {
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i9, int i10, Object obj) {
        super(trackGroup, i9);
        this.reason = i10;
        this.data = obj;
    }
}
