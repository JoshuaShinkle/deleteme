package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource;

/* loaded from: classes.dex */
final class MediaPeriodInfo {
    public final long contentPositionUs;
    public final long durationUs;
    public final long endPositionUs;

    /* renamed from: id */
    public final MediaSource.MediaPeriodId f15299id;
    public final boolean isFinal;
    public final boolean isLastInTimelinePeriod;
    public final long startPositionUs;

    public MediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j9, long j10, long j11, long j12, boolean z8, boolean z9) {
        this.f15299id = mediaPeriodId;
        this.startPositionUs = j9;
        this.endPositionUs = j10;
        this.contentPositionUs = j11;
        this.durationUs = j12;
        this.isLastInTimelinePeriod = z8;
        this.isFinal = z9;
    }

    public MediaPeriodInfo copyWithPeriodIndex(int i9) {
        return new MediaPeriodInfo(this.f15299id.copyWithPeriodIndex(i9), this.startPositionUs, this.endPositionUs, this.contentPositionUs, this.durationUs, this.isLastInTimelinePeriod, this.isFinal);
    }

    public MediaPeriodInfo copyWithStartPositionUs(long j9) {
        return new MediaPeriodInfo(this.f15299id, j9, this.endPositionUs, this.contentPositionUs, this.durationUs, this.isLastInTimelinePeriod, this.isFinal);
    }
}
