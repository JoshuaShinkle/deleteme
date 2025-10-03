package com.google.android.exoplayer2.text.webvtt;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class WebvttSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final List<WebvttCue> cues;
    private final int numCues;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCue> list) {
        this.cues = list;
        int size = list.size();
        this.numCues = size;
        this.cueTimesUs = new long[size * 2];
        for (int i9 = 0; i9 < this.numCues; i9++) {
            WebvttCue webvttCue = list.get(i9);
            int i10 = i9 * 2;
            long[] jArr = this.cueTimesUs;
            jArr[i10] = webvttCue.startTime;
            jArr[i10 + 1] = webvttCue.endTime;
        }
        long[] jArr2 = this.cueTimesUs;
        long[] jArrCopyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j9) {
        SpannableStringBuilder spannableStringBuilder = null;
        ArrayList arrayList = null;
        WebvttCue webvttCue = null;
        for (int i9 = 0; i9 < this.numCues; i9++) {
            long[] jArr = this.cueTimesUs;
            int i10 = i9 * 2;
            if (jArr[i10] <= j9 && j9 < jArr[i10 + 1]) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                WebvttCue webvttCue2 = this.cues.get(i9);
                if (!webvttCue2.isNormalCue()) {
                    arrayList.add(webvttCue2);
                } else if (webvttCue == null) {
                    webvttCue = webvttCue2;
                } else if (spannableStringBuilder == null) {
                    spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(webvttCue.text).append((CharSequence) "\n").append(webvttCue2.text);
                } else {
                    spannableStringBuilder.append((CharSequence) "\n").append(webvttCue2.text);
                }
            }
        }
        if (spannableStringBuilder != null) {
            arrayList.add(new WebvttCue(spannableStringBuilder));
        } else if (webvttCue != null) {
            arrayList.add(webvttCue);
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int i9) {
        Assertions.checkArgument(i9 >= 0);
        Assertions.checkArgument(i9 < this.sortedCueTimesUs.length);
        return this.sortedCueTimesUs[i9];
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j9) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j9, false, false);
        if (iBinarySearchCeil < this.sortedCueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }
}
