package com.google.android.exoplayer2.text.ssa;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class SsaSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final Cue[] cues;

    public SsaSubtitle(Cue[] cueArr, long[] jArr) {
        this.cues = cueArr;
        this.cueTimesUs = jArr;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j9) {
        Cue cue;
        int iBinarySearchFloor = Util.binarySearchFloor(this.cueTimesUs, j9, true, false);
        return (iBinarySearchFloor == -1 || (cue = this.cues[iBinarySearchFloor]) == null) ? Collections.emptyList() : Collections.singletonList(cue);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int i9) {
        Assertions.checkArgument(i9 >= 0);
        Assertions.checkArgument(i9 < this.cueTimesUs.length);
        return this.cueTimesUs[i9];
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.cueTimesUs.length;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j9) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.cueTimesUs, j9, false, false);
        if (iBinarySearchCeil < this.cueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }
}
