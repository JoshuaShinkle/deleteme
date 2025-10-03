package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
final class MediaPeriodQueue {
    private static final int MAXIMUM_BUFFER_AHEAD_PERIODS = 100;
    private int length;
    private MediaPeriodHolder loading;
    private long nextWindowSequenceNumber;
    private MediaPeriodHolder playing;
    private MediaPeriodHolder reading;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private Timeline timeline;
    private final Timeline.Period period = new Timeline.Period();
    private final Timeline.Window window = new Timeline.Window();

    private boolean canKeepMediaPeriodHolder(MediaPeriodHolder mediaPeriodHolder, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
        return mediaPeriodInfo2.startPositionUs == mediaPeriodInfo.startPositionUs && mediaPeriodInfo2.endPositionUs == mediaPeriodInfo.endPositionUs && mediaPeriodInfo2.f15299id.equals(mediaPeriodInfo.f15299id);
    }

    private MediaPeriodInfo getFirstMediaPeriodInfo(PlaybackInfo playbackInfo) {
        return getMediaPeriodInfo(playbackInfo.periodId, playbackInfo.contentPositionUs, playbackInfo.startPositionUs);
    }

    private MediaPeriodInfo getFollowingMediaPeriodInfo(MediaPeriodHolder mediaPeriodHolder, long j9) {
        int i9;
        long j10;
        long j11;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
        if (mediaPeriodInfo.isLastInTimelinePeriod) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(mediaPeriodInfo.f15299id.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex == -1) {
                return null;
            }
            int i10 = this.timeline.getPeriod(nextPeriodIndex, this.period, true).windowIndex;
            Object obj = this.period.uid;
            long j12 = mediaPeriodInfo.f15299id.windowSequenceNumber;
            long j13 = 0;
            if (this.timeline.getWindow(i10, this.window).firstPeriodIndex == nextPeriodIndex) {
                Pair<Integer, Long> periodPosition = this.timeline.getPeriodPosition(this.window, this.period, i10, C3322C.TIME_UNSET, Math.max(0L, (mediaPeriodHolder.getRendererOffset() + mediaPeriodInfo.durationUs) - j9));
                if (periodPosition == null) {
                    return null;
                }
                int iIntValue = ((Integer) periodPosition.first).intValue();
                long jLongValue = ((Long) periodPosition.second).longValue();
                MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.next;
                if (mediaPeriodHolder2 == null || !mediaPeriodHolder2.uid.equals(obj)) {
                    j11 = this.nextWindowSequenceNumber;
                    this.nextWindowSequenceNumber = 1 + j11;
                } else {
                    j11 = mediaPeriodHolder.next.info.f15299id.windowSequenceNumber;
                }
                j13 = jLongValue;
                j10 = j11;
                i9 = iIntValue;
            } else {
                i9 = nextPeriodIndex;
                j10 = j12;
            }
            long j14 = j13;
            return getMediaPeriodInfo(resolveMediaPeriodIdForAds(i9, j14, j10), j14, j13);
        }
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.f15299id;
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (mediaPeriodId.isAd()) {
            int i11 = mediaPeriodId.adGroupIndex;
            int adCountInAdGroup = this.period.getAdCountInAdGroup(i11);
            if (adCountInAdGroup == -1) {
                return null;
            }
            int nextAdIndexToPlay = this.period.getNextAdIndexToPlay(i11, mediaPeriodId.adIndexInAdGroup);
            if (nextAdIndexToPlay >= adCountInAdGroup) {
                return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, mediaPeriodInfo.contentPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            if (this.period.isAdAvailable(i11, nextAdIndexToPlay)) {
                return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, i11, nextAdIndexToPlay, mediaPeriodInfo.contentPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            return null;
        }
        long j15 = mediaPeriodInfo.endPositionUs;
        if (j15 != Long.MIN_VALUE) {
            int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(j15);
            if (adGroupIndexForPositionUs == -1) {
                return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, mediaPeriodInfo.endPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            int firstAdIndexToPlay = this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs);
            if (this.period.isAdAvailable(adGroupIndexForPositionUs, firstAdIndexToPlay)) {
                return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, adGroupIndexForPositionUs, firstAdIndexToPlay, mediaPeriodInfo.endPositionUs, mediaPeriodId.windowSequenceNumber);
            }
            return null;
        }
        int adGroupCount = this.period.getAdGroupCount();
        if (adGroupCount == 0) {
            return null;
        }
        int i12 = adGroupCount - 1;
        if (this.period.getAdGroupTimeUs(i12) != Long.MIN_VALUE || this.period.hasPlayedAdGroup(i12)) {
            return null;
        }
        int firstAdIndexToPlay2 = this.period.getFirstAdIndexToPlay(i12);
        if (!this.period.isAdAvailable(i12, firstAdIndexToPlay2)) {
            return null;
        }
        return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, i12, firstAdIndexToPlay2, this.period.getDurationUs(), mediaPeriodId.windowSequenceNumber);
    }

    private MediaPeriodInfo getMediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j9, long j10) {
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (!mediaPeriodId.isAd()) {
            return getMediaPeriodInfoForContent(mediaPeriodId.periodIndex, j10, mediaPeriodId.windowSequenceNumber);
        }
        if (this.period.isAdAvailable(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup)) {
            return getMediaPeriodInfoForAd(mediaPeriodId.periodIndex, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, j9, mediaPeriodId.windowSequenceNumber);
        }
        return null;
    }

    private MediaPeriodInfo getMediaPeriodInfoForAd(int i9, int i10, int i11, long j9, long j10) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(i9, i10, i11, j10);
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodId, Long.MIN_VALUE);
        boolean zIsLastInTimeline = isLastInTimeline(mediaPeriodId, zIsLastInPeriod);
        return new MediaPeriodInfo(mediaPeriodId, i11 == this.period.getFirstAdIndexToPlay(i10) ? this.period.getAdResumePositionUs() : 0L, Long.MIN_VALUE, j9, this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup), zIsLastInPeriod, zIsLastInTimeline);
    }

    private MediaPeriodInfo getMediaPeriodInfoForContent(int i9, long j9, long j10) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(i9, j10);
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j9);
        long adGroupTimeUs = adGroupIndexAfterPositionUs == -1 ? Long.MIN_VALUE : this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs);
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodId, adGroupTimeUs);
        return new MediaPeriodInfo(mediaPeriodId, j9, adGroupTimeUs, C3322C.TIME_UNSET, adGroupTimeUs == Long.MIN_VALUE ? this.period.getDurationUs() : adGroupTimeUs, zIsLastInPeriod, isLastInTimeline(mediaPeriodId, zIsLastInPeriod));
    }

    private boolean isLastInPeriod(MediaSource.MediaPeriodId mediaPeriodId, long j9) {
        int adGroupCount = this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).getAdGroupCount();
        if (adGroupCount == 0) {
            return true;
        }
        int i9 = adGroupCount - 1;
        boolean zIsAd = mediaPeriodId.isAd();
        if (this.period.getAdGroupTimeUs(i9) != Long.MIN_VALUE) {
            return !zIsAd && j9 == Long.MIN_VALUE;
        }
        int adCountInAdGroup = this.period.getAdCountInAdGroup(i9);
        if (adCountInAdGroup == -1) {
            return false;
        }
        if (zIsAd && mediaPeriodId.adGroupIndex == i9 && mediaPeriodId.adIndexInAdGroup == adCountInAdGroup + (-1)) {
            return true;
        }
        return !zIsAd && this.period.getFirstAdIndexToPlay(i9) == adCountInAdGroup;
    }

    private boolean isLastInTimeline(MediaSource.MediaPeriodId mediaPeriodId, boolean z8) {
        return !this.timeline.getWindow(this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period).windowIndex, this.window).isDynamic && this.timeline.isLastPeriod(mediaPeriodId.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled) && z8;
    }

    private long resolvePeriodIndexToWindowSequenceNumber(int i9) {
        Object obj = this.timeline.getPeriod(i9, this.period, true).uid;
        for (MediaPeriodHolder frontPeriod = getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.uid.equals(obj)) {
                return frontPeriod.info.f15299id.windowSequenceNumber;
            }
        }
        int i10 = this.period.windowIndex;
        for (MediaPeriodHolder frontPeriod2 = getFrontPeriod(); frontPeriod2 != null; frontPeriod2 = frontPeriod2.next) {
            int indexOfPeriod = this.timeline.getIndexOfPeriod(frontPeriod2.uid);
            if (indexOfPeriod != -1 && this.timeline.getPeriod(indexOfPeriod, this.period).windowIndex == i10) {
                return frontPeriod2.info.f15299id.windowSequenceNumber;
            }
        }
        long j9 = this.nextWindowSequenceNumber;
        this.nextWindowSequenceNumber = 1 + j9;
        return j9;
    }

    private boolean updateForPlaybackModeChange() {
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        if (frontPeriod == null) {
            return true;
        }
        while (true) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(frontPeriod.info.f15299id.periodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            while (true) {
                mediaPeriodHolder = frontPeriod.next;
                if (mediaPeriodHolder == null || frontPeriod.info.isLastInTimelinePeriod) {
                    break;
                }
                frontPeriod = mediaPeriodHolder;
            }
            if (nextPeriodIndex == -1 || mediaPeriodHolder == null || mediaPeriodHolder.info.f15299id.periodIndex != nextPeriodIndex) {
                break;
            }
            frontPeriod = mediaPeriodHolder;
        }
        boolean zRemoveAfter = removeAfter(frontPeriod);
        MediaPeriodInfo mediaPeriodInfo = frontPeriod.info;
        frontPeriod.info = getUpdatedMediaPeriodInfo(mediaPeriodInfo, mediaPeriodInfo.f15299id);
        return (zRemoveAfter && hasPlayingPeriod()) ? false : true;
    }

    public MediaPeriodHolder advancePlayingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.playing;
        if (mediaPeriodHolder != null) {
            if (mediaPeriodHolder == this.reading) {
                this.reading = mediaPeriodHolder.next;
            }
            mediaPeriodHolder.release();
            this.playing = this.playing.next;
            int i9 = this.length - 1;
            this.length = i9;
            if (i9 == 0) {
                this.loading = null;
            }
        } else {
            MediaPeriodHolder mediaPeriodHolder2 = this.loading;
            this.playing = mediaPeriodHolder2;
            this.reading = mediaPeriodHolder2;
        }
        return this.playing;
    }

    public MediaPeriodHolder advanceReadingPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.reading;
        Assertions.checkState((mediaPeriodHolder == null || mediaPeriodHolder.next == null) ? false : true);
        MediaPeriodHolder mediaPeriodHolder2 = this.reading.next;
        this.reading = mediaPeriodHolder2;
        return mediaPeriodHolder2;
    }

    public void clear() {
        MediaPeriodHolder frontPeriod = getFrontPeriod();
        if (frontPeriod != null) {
            frontPeriod.release();
            removeAfter(frontPeriod);
        }
        this.playing = null;
        this.loading = null;
        this.reading = null;
        this.length = 0;
    }

    public MediaPeriod enqueueNextMediaPeriod(RendererCapabilities[] rendererCapabilitiesArr, long j9, TrackSelector trackSelector, Allocator allocator, MediaSource mediaSource, Object obj, MediaPeriodInfo mediaPeriodInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        MediaPeriodHolder mediaPeriodHolder2 = new MediaPeriodHolder(rendererCapabilitiesArr, mediaPeriodHolder == null ? mediaPeriodInfo.startPositionUs + j9 : mediaPeriodHolder.getRendererOffset() + this.loading.info.durationUs, trackSelector, allocator, mediaSource, obj, mediaPeriodInfo);
        if (this.loading != null) {
            Assertions.checkState(hasPlayingPeriod());
            this.loading.next = mediaPeriodHolder2;
        }
        this.loading = mediaPeriodHolder2;
        this.length++;
        return mediaPeriodHolder2.mediaPeriod;
    }

    public MediaPeriodHolder getFrontPeriod() {
        return hasPlayingPeriod() ? this.playing : this.loading;
    }

    public MediaPeriodHolder getLoadingPeriod() {
        return this.loading;
    }

    public MediaPeriodInfo getNextMediaPeriodInfo(long j9, PlaybackInfo playbackInfo) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null ? getFirstMediaPeriodInfo(playbackInfo) : getFollowingMediaPeriodInfo(mediaPeriodHolder, j9);
    }

    public MediaPeriodHolder getPlayingPeriod() {
        return this.playing;
    }

    public MediaPeriodHolder getReadingPeriod() {
        return this.reading;
    }

    public MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo, int i9) {
        return getUpdatedMediaPeriodInfo(mediaPeriodInfo, mediaPeriodInfo.f15299id.copyWithPeriodIndex(i9));
    }

    public TrackSelectorResult handleLoadingPeriodPrepared(float f9) {
        return this.loading.handlePrepared(f9);
    }

    public boolean hasPlayingPeriod() {
        return this.playing != null;
    }

    public boolean isLoading(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder != null && mediaPeriodHolder.mediaPeriod == mediaPeriod;
    }

    public void reevaluateBuffer(long j9) {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.reevaluateBuffer(j9);
        }
    }

    public boolean removeAfter(MediaPeriodHolder mediaPeriodHolder) {
        boolean z8 = false;
        Assertions.checkState(mediaPeriodHolder != null);
        this.loading = mediaPeriodHolder;
        while (true) {
            mediaPeriodHolder = mediaPeriodHolder.next;
            if (mediaPeriodHolder == null) {
                this.loading.next = null;
                return z8;
            }
            if (mediaPeriodHolder == this.reading) {
                this.reading = this.playing;
                z8 = true;
            }
            mediaPeriodHolder.release();
            this.length--;
        }
    }

    public MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(int i9, long j9) {
        return resolveMediaPeriodIdForAds(i9, j9, resolvePeriodIndexToWindowSequenceNumber(i9));
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public boolean shouldLoadNextMediaPeriod() {
        MediaPeriodHolder mediaPeriodHolder = this.loading;
        return mediaPeriodHolder == null || (!mediaPeriodHolder.info.isFinal && mediaPeriodHolder.isFullyBuffered() && this.loading.info.durationUs != C3322C.TIME_UNSET && this.length < 100);
    }

    public boolean updateQueuedPeriods(MediaSource.MediaPeriodId mediaPeriodId, long j9) {
        int i9 = mediaPeriodId.periodIndex;
        MediaPeriodHolder mediaPeriodHolder = null;
        int nextPeriodIndex = i9;
        for (MediaPeriodHolder frontPeriod = getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (mediaPeriodHolder == null) {
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, nextPeriodIndex);
            } else {
                if (nextPeriodIndex == -1 || !frontPeriod.uid.equals(this.timeline.getPeriod(nextPeriodIndex, this.period, true).uid)) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
                MediaPeriodInfo followingMediaPeriodInfo = getFollowingMediaPeriodInfo(mediaPeriodHolder, j9);
                if (followingMediaPeriodInfo == null) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
                frontPeriod.info = getUpdatedMediaPeriodInfo(frontPeriod.info, nextPeriodIndex);
                if (!canKeepMediaPeriodHolder(frontPeriod, followingMediaPeriodInfo)) {
                    return true ^ removeAfter(mediaPeriodHolder);
                }
            }
            if (frontPeriod.info.isLastInTimelinePeriod) {
                nextPeriodIndex = this.timeline.getNextPeriodIndex(nextPeriodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            }
            mediaPeriodHolder = frontPeriod;
        }
        return true;
    }

    public boolean updateRepeatMode(int i9) {
        this.repeatMode = i9;
        return updateForPlaybackModeChange();
    }

    public boolean updateShuffleModeEnabled(boolean z8) {
        this.shuffleModeEnabled = z8;
        return updateForPlaybackModeChange();
    }

    private MediaSource.MediaPeriodId resolveMediaPeriodIdForAds(int i9, long j9, long j10) {
        this.timeline.getPeriod(i9, this.period);
        int adGroupIndexForPositionUs = this.period.getAdGroupIndexForPositionUs(j9);
        if (adGroupIndexForPositionUs == -1) {
            return new MediaSource.MediaPeriodId(i9, j10);
        }
        return new MediaSource.MediaPeriodId(i9, adGroupIndexForPositionUs, this.period.getFirstAdIndexToPlay(adGroupIndexForPositionUs), j10);
    }

    private MediaPeriodInfo getUpdatedMediaPeriodInfo(MediaPeriodInfo mediaPeriodInfo, MediaSource.MediaPeriodId mediaPeriodId) {
        long j9;
        long durationUs;
        long j10 = mediaPeriodInfo.startPositionUs;
        long j11 = mediaPeriodInfo.endPositionUs;
        boolean zIsLastInPeriod = isLastInPeriod(mediaPeriodId, j11);
        boolean zIsLastInTimeline = isLastInTimeline(mediaPeriodId, zIsLastInPeriod);
        this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        if (mediaPeriodId.isAd()) {
            durationUs = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
        } else if (j11 == Long.MIN_VALUE) {
            durationUs = this.period.getDurationUs();
        } else {
            j9 = j11;
            return new MediaPeriodInfo(mediaPeriodId, j10, j11, mediaPeriodInfo.contentPositionUs, j9, zIsLastInPeriod, zIsLastInTimeline);
        }
        j9 = durationUs;
        return new MediaPeriodInfo(mediaPeriodId, j10, j11, mediaPeriodInfo.contentPositionUs, j9, zIsLastInPeriod, zIsLastInTimeline);
    }
}
