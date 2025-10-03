package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import java.util.Random;

/* loaded from: classes.dex */
public final class RandomTrackSelection extends BaseTrackSelection {
    private final Random random;
    private int selectedIndex;

    public static final class Factory implements TrackSelection.Factory {
        private final Random random;

        public Factory() {
            this.random = new Random();
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection.Factory
        public RandomTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            return new RandomTrackSelection(trackGroup, iArr, this.random);
        }

        public Factory(int i9) {
            this.random = new Random(i9);
        }
    }

    public RandomTrackSelection(TrackGroup trackGroup, int... iArr) {
        super(trackGroup, iArr);
        Random random = new Random();
        this.random = random;
        this.selectedIndex = random.nextInt(this.length);
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
        return 3;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j9, long j10, long j11) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i9 = 0;
        for (int i10 = 0; i10 < this.length; i10++) {
            if (!isBlacklisted(i10, jElapsedRealtime)) {
                i9++;
            }
        }
        this.selectedIndex = this.random.nextInt(i9);
        if (i9 != this.length) {
            int i11 = 0;
            for (int i12 = 0; i12 < this.length; i12++) {
                if (!isBlacklisted(i12, jElapsedRealtime)) {
                    int i13 = i11 + 1;
                    if (this.selectedIndex == i11) {
                        this.selectedIndex = i12;
                        return;
                    }
                    i11 = i13;
                }
            }
        }
    }

    public RandomTrackSelection(TrackGroup trackGroup, int[] iArr, long j9) {
        this(trackGroup, iArr, new Random(j9));
    }

    public RandomTrackSelection(TrackGroup trackGroup, int[] iArr, Random random) {
        super(trackGroup, iArr);
        this.random = random;
        this.selectedIndex = random.nextInt(this.length);
    }
}
