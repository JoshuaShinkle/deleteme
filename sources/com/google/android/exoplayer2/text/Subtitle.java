package com.google.android.exoplayer2.text;

import java.util.List;

/* loaded from: classes.dex */
public interface Subtitle {
    List<Cue> getCues(long j9);

    long getEventTime(int i9);

    int getEventTimeCount();

    int getNextEventTimeIndex(long j9);
}
