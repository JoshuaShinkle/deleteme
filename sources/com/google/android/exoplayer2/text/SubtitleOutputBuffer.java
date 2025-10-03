package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.OutputBuffer;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SubtitleOutputBuffer extends OutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    private Subtitle subtitle;

    @Override // com.google.android.exoplayer2.decoder.Buffer
    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j9) {
        return this.subtitle.getCues(j9 - this.subsampleOffsetUs);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int i9) {
        return this.subtitle.getEventTime(i9) + this.subsampleOffsetUs;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.subtitle.getEventTimeCount();
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j9) {
        return this.subtitle.getNextEventTimeIndex(j9 - this.subsampleOffsetUs);
    }

    @Override // com.google.android.exoplayer2.decoder.OutputBuffer
    public abstract void release();

    public void setContent(long j9, Subtitle subtitle, long j10) {
        this.timeUs = j9;
        this.subtitle = subtitle;
        if (j10 != Long.MAX_VALUE) {
            j9 = j10;
        }
        this.subsampleOffsetUs = j9;
    }
}
