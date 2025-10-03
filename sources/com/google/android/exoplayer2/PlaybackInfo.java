package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;

/* loaded from: classes.dex */
final class PlaybackInfo {
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final Object manifest;
    public final MediaSource.MediaPeriodId periodId;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public final TrackSelectorResult trackSelectorResult;

    public PlaybackInfo(Timeline timeline, long j9, TrackSelectorResult trackSelectorResult) {
        this(timeline, null, new MediaSource.MediaPeriodId(0), j9, C3322C.TIME_UNSET, 1, false, trackSelectorResult);
    }

    private static void copyMutablePositions(PlaybackInfo playbackInfo, PlaybackInfo playbackInfo2) {
        playbackInfo2.positionUs = playbackInfo.positionUs;
        playbackInfo2.bufferedPositionUs = playbackInfo.bufferedPositionUs;
    }

    public PlaybackInfo copyWithIsLoading(boolean z8) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, z8, this.trackSelectorResult);
        copyMutablePositions(this, playbackInfo);
        return playbackInfo;
    }

    public PlaybackInfo copyWithPeriodIndex(int i9) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId.copyWithPeriodIndex(i9), this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackSelectorResult);
        copyMutablePositions(this, playbackInfo);
        return playbackInfo;
    }

    public PlaybackInfo copyWithPlaybackState(int i9) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, i9, this.isLoading, this.trackSelectorResult);
        copyMutablePositions(this, playbackInfo);
        return playbackInfo;
    }

    public PlaybackInfo copyWithTimeline(Timeline timeline, Object obj) {
        PlaybackInfo playbackInfo = new PlaybackInfo(timeline, obj, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackSelectorResult);
        copyMutablePositions(this, playbackInfo);
        return playbackInfo;
    }

    public PlaybackInfo copyWithTrackSelectorResult(TrackSelectorResult trackSelectorResult) {
        PlaybackInfo playbackInfo = new PlaybackInfo(this.timeline, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, trackSelectorResult);
        copyMutablePositions(this, playbackInfo);
        return playbackInfo;
    }

    public PlaybackInfo fromNewPosition(MediaSource.MediaPeriodId mediaPeriodId, long j9, long j10) {
        return new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j9, mediaPeriodId.isAd() ? j10 : -9223372036854775807L, this.playbackState, this.isLoading, this.trackSelectorResult);
    }

    public PlaybackInfo(Timeline timeline, Object obj, MediaSource.MediaPeriodId mediaPeriodId, long j9, long j10, int i9, boolean z8, TrackSelectorResult trackSelectorResult) {
        this.timeline = timeline;
        this.manifest = obj;
        this.periodId = mediaPeriodId;
        this.startPositionUs = j9;
        this.contentPositionUs = j10;
        this.positionUs = j9;
        this.bufferedPositionUs = j9;
        this.playbackState = i9;
        this.isLoading = z8;
        this.trackSelectorResult = trackSelectorResult;
    }
}
