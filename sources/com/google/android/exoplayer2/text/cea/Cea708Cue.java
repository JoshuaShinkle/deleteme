package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import com.google.android.exoplayer2.text.Cue;

/* loaded from: classes.dex */
final class Cea708Cue extends Cue implements Comparable<Cea708Cue> {
    public static final int PRIORITY_UNSET = -1;
    public final int priority;

    public Cea708Cue(CharSequence charSequence, Layout.Alignment alignment, float f9, int i9, int i10, float f10, int i11, float f11, boolean z8, int i12, int i13) {
        super(charSequence, alignment, f9, i9, i10, f10, i11, f11, z8, i12);
        this.priority = i13;
    }

    @Override // java.lang.Comparable
    public int compareTo(Cea708Cue cea708Cue) {
        int i9 = cea708Cue.priority;
        int i10 = this.priority;
        if (i9 < i10) {
            return -1;
        }
        return i9 > i10 ? 1 : 0;
    }
}
