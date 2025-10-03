package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultMediaClock;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes.dex */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSource.Listener, DefaultMediaClock.PlaybackParameterListener, PlayerMessage.Sender {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 2;
    private static final int MSG_PERIOD_PREPARED = 9;
    public static final int MSG_PLAYBACK_INFO_CHANGED = 0;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 1;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 8;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 12;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 13;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 10;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 11;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERER_TIMESTAMP_OFFSET_US = 60000000;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final ExoPlayer player;
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private SeekParameters seekParameters = SeekParameters.DEFAULT;
    private final PlaybackInfoUpdate playbackInfoUpdate = new PlaybackInfoUpdate();

    public static final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline, Object obj) {
            this.source = mediaSource;
            this.timeline = timeline;
            this.manifest = obj;
        }
    }

    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i9, long j9, Object obj) {
            this.resolvedPeriodIndex = i9;
            this.resolvedPeriodTimeUs = j9;
            this.resolvedPeriodUid = obj;
        }

        @Override // java.lang.Comparable
        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            Object obj = this.resolvedPeriodUid;
            if ((obj == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return obj != null ? -1 : 1;
            }
            if (obj == null) {
                return 0;
            }
            int i9 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            return i9 != 0 ? i9 : Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    public static final class PlaybackInfoUpdate {
        private int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        private int operationAcks;
        private boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void incrementPendingOperationAcks(int i9) {
            this.operationAcks += i9;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void setPositionDiscontinuity(int i9) {
            if (this.positionDiscontinuity && this.discontinuityReason != 4) {
                Assertions.checkArgument(i9 == 4);
            } else {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i9;
            }
        }
    }

    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int i9, long j9) {
            this.timeline = timeline;
            this.windowIndex = i9;
            this.windowPositionUs = j9;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, TrackSelectorResult trackSelectorResult, LoadControl loadControl, boolean z8, int i9, boolean z9, Handler handler, ExoPlayer exoPlayer, Clock clock) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl;
        this.playWhenReady = z8;
        this.repeatMode = i9;
        this.shuffleModeEnabled = z9;
        this.eventHandler = handler;
        this.player = exoPlayer;
        this.clock = clock;
        this.backBufferDurationUs = loadControl.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl.retainBackBufferFromKeyframe();
        this.playbackInfo = new PlaybackInfo(Timeline.EMPTY, C3322C.TIME_UNSET, trackSelectorResult);
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i10 = 0; i10 < rendererArr.length; i10++) {
            rendererArr[i10].setIndex(i10);
            this.rendererCapabilities[i10] = rendererArr[i10].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock);
        this.pendingMessages = new ArrayList<>();
        this.enabledRenderers = new Renderer[0];
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector.init(this);
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread = handlerThread;
        handlerThread.start();
        this.handler = clock.createHandler(handlerThread.getLooper(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deliverMessage(PlayerMessage playerMessage) {
        try {
            playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
        } finally {
            playerMessage.markAsProcessed(true);
        }
    }

    private void disableRenderer(Renderer renderer) {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void doSomeWork() throws ExoPlaybackException {
        int i9;
        long jUptimeMillis = this.clock.uptimeMillis();
        updatePeriods();
        if (!this.queue.hasPlayingPeriod()) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(jUptimeMillis, 10L);
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        long jElapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        boolean z8 = true;
        boolean z9 = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, jElapsedRealtime);
            z9 = z9 && renderer.isEnded();
            boolean z10 = renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer);
            if (!z10) {
                renderer.maybeThrowStreamError();
            }
            z8 = z8 && z10;
        }
        if (!z8) {
            maybeThrowPeriodPrepareError();
        }
        long j9 = playingPeriod.info.durationUs;
        if (z9 && ((j9 == C3322C.TIME_UNSET || j9 <= this.playbackInfo.positionUs) && playingPeriod.info.isFinal)) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState == 2 && shouldTransitionToReadyState(z8)) {
            setState(3);
            if (this.playWhenReady) {
                startRenderers();
            }
        } else if (this.playbackInfo.playbackState == 3 && (this.enabledRenderers.length != 0 ? !z8 : !isTimelineReady())) {
            this.rebuffering = this.playWhenReady;
            setState(2);
            stopRenderers();
        }
        if (this.playbackInfo.playbackState == 2) {
            for (Renderer renderer2 : this.enabledRenderers) {
                renderer2.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.playbackInfo.playbackState == 3) || (i9 = this.playbackInfo.playbackState) == 2) {
            scheduleNextWork(jUptimeMillis, 10L);
        } else if (this.enabledRenderers.length == 0 || i9 == 4) {
            this.handler.removeMessages(2);
        } else {
            scheduleNextWork(jUptimeMillis, 1000L);
        }
        TraceUtil.endSection();
    }

    private void enableRenderer(int i9, boolean z8, int i10) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i9];
        this.enabledRenderers[i10] = renderer;
        if (renderer.getState() == 0) {
            TrackSelectorResult trackSelectorResult = playingPeriod.trackSelectorResult;
            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i9];
            Format[] formats = getFormats(trackSelectorResult.selections.get(i9));
            boolean z9 = this.playWhenReady && this.playbackInfo.playbackState == 3;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i9], this.rendererPositionUs, !z8 && z9, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (z9) {
                renderer.start();
            }
        }
    }

    private void enableRenderers(boolean[] zArr, int i9) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i9];
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        int i10 = 0;
        for (int i11 = 0; i11 < this.renderers.length; i11++) {
            if (playingPeriod.trackSelectorResult.renderersEnabled[i11]) {
                enableRenderer(i11, zArr[i11], i10);
                i10++;
            }
        }
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private int getFirstPeriodIndex() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return 0;
        }
        return timeline.getWindow(timeline.getFirstWindowIndex(this.shuffleModeEnabled), this.window).firstPeriodIndex;
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i9 = 0; i9 < length; i9++) {
            formatArr[i9] = trackSelection.getFormat(i9);
        }
        return formatArr;
    }

    private Pair<Integer, Long> getPeriodPosition(Timeline timeline, int i9, long j9) {
        return timeline.getPeriodPosition(this.window, this.period, i9, j9);
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            updateLoadControlTrackSelection(this.queue.handleLoadingPeriodPrepared(this.mediaClock.getPlaybackParameters().speed));
            if (!this.queue.hasPlayingPeriod()) {
                resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
                updatePlayingPeriodRenderers(null);
            }
            maybeContinueLoading();
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        setState(4);
        resetInternal(false, true, false);
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo mediaSourceRefreshInfo) throws ExoPlaybackException {
        if (mediaSourceRefreshInfo.source != this.mediaSource) {
            return;
        }
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = mediaSourceRefreshInfo.timeline;
        Object obj = mediaSourceRefreshInfo.manifest;
        this.queue.setTimeline(timeline2);
        this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2, obj);
        resolvePendingMessagePositions();
        int i9 = this.pendingPrepareCount;
        if (i9 > 0) {
            this.playbackInfoUpdate.incrementPendingOperationAcks(i9);
            this.pendingPrepareCount = 0;
            SeekPosition seekPosition = this.pendingInitialSeekPosition;
            if (seekPosition != null) {
                Pair<Integer, Long> pairResolveSeekPosition = resolveSeekPosition(seekPosition, true);
                this.pendingInitialSeekPosition = null;
                if (pairResolveSeekPosition == null) {
                    handleSourceInfoRefreshEndedPlayback();
                    return;
                }
                int iIntValue = ((Integer) pairResolveSeekPosition.first).intValue();
                long jLongValue = ((Long) pairResolveSeekPosition.second).longValue();
                MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(iIntValue, jLongValue);
                this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds, mediaPeriodIdResolveMediaPeriodIdForAds.isAd() ? 0L : jLongValue, jLongValue);
                return;
            }
            if (this.playbackInfo.startPositionUs == C3322C.TIME_UNSET) {
                if (timeline2.isEmpty()) {
                    handleSourceInfoRefreshEndedPlayback();
                    return;
                }
                Pair<Integer, Long> periodPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), C3322C.TIME_UNSET);
                int iIntValue2 = ((Integer) periodPosition.first).intValue();
                long jLongValue2 = ((Long) periodPosition.second).longValue();
                MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds2 = this.queue.resolveMediaPeriodIdForAds(iIntValue2, jLongValue2);
                this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds2, mediaPeriodIdResolveMediaPeriodIdForAds2.isAd() ? 0L : jLongValue2, jLongValue2);
                return;
            }
            return;
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        int i10 = playbackInfo.periodId.periodIndex;
        long j9 = playbackInfo.contentPositionUs;
        if (timeline.isEmpty()) {
            if (timeline2.isEmpty()) {
                return;
            }
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds3 = this.queue.resolveMediaPeriodIdForAds(i10, j9);
            this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds3, mediaPeriodIdResolveMediaPeriodIdForAds3.isAd() ? 0L : j9, j9);
            return;
        }
        MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod();
        int indexOfPeriod = timeline2.getIndexOfPeriod(frontPeriod == null ? timeline.getPeriod(i10, this.period, true).uid : frontPeriod.uid);
        if (indexOfPeriod != -1) {
            if (indexOfPeriod != i10) {
                this.playbackInfo = this.playbackInfo.copyWithPeriodIndex(indexOfPeriod);
            }
            MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
            if (mediaPeriodId.isAd()) {
                MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds4 = this.queue.resolveMediaPeriodIdForAds(indexOfPeriod, j9);
                if (!mediaPeriodIdResolveMediaPeriodIdForAds4.equals(mediaPeriodId)) {
                    this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds4, seekToPeriodPosition(mediaPeriodIdResolveMediaPeriodIdForAds4, mediaPeriodIdResolveMediaPeriodIdForAds4.isAd() ? 0L : j9), j9);
                    return;
                }
            }
            if (this.queue.updateQueuedPeriods(mediaPeriodId, this.rendererPositionUs)) {
                return;
            }
            seekToCurrentPosition(false);
            return;
        }
        int iResolveSubsequentPeriod = resolveSubsequentPeriod(i10, timeline, timeline2);
        if (iResolveSubsequentPeriod == -1) {
            handleSourceInfoRefreshEndedPlayback();
            return;
        }
        Pair<Integer, Long> periodPosition2 = getPeriodPosition(timeline2, timeline2.getPeriod(iResolveSubsequentPeriod, this.period).windowIndex, C3322C.TIME_UNSET);
        int iIntValue3 = ((Integer) periodPosition2.first).intValue();
        long jLongValue3 = ((Long) periodPosition2.second).longValue();
        MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds5 = this.queue.resolveMediaPeriodIdForAds(iIntValue3, jLongValue3);
        timeline2.getPeriod(iIntValue3, this.period, true);
        if (frontPeriod != null) {
            Object obj2 = this.period.uid;
            frontPeriod.info = frontPeriod.info.copyWithPeriodIndex(-1);
            while (true) {
                frontPeriod = frontPeriod.next;
                if (frontPeriod == null) {
                    break;
                } else if (frontPeriod.uid.equals(obj2)) {
                    frontPeriod.info = this.queue.getUpdatedMediaPeriodInfo(frontPeriod.info, iIntValue3);
                } else {
                    frontPeriod.info = frontPeriod.info.copyWithPeriodIndex(-1);
                }
            }
        }
        this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodIdResolveMediaPeriodIdForAds5, seekToPeriodPosition(mediaPeriodIdResolveMediaPeriodIdForAds5, mediaPeriodIdResolveMediaPeriodIdForAds5.isAd() ? 0L : jLongValue3), jLongValue3);
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j9 = playingPeriod.info.durationUs;
        return j9 == C3322C.TIME_UNSET || this.playbackInfo.positionUs < j9 || ((mediaPeriodHolder = playingPeriod.next) != null && (mediaPeriodHolder.prepared || mediaPeriodHolder.info.f15299id.isAd()));
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long nextLoadPositionUs = loadingPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        boolean zShouldContinueLoading = this.loadControl.shouldContinueLoading(nextLoadPositionUs - loadingPeriod.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed);
        setIsLoading(zShouldContinueLoading);
        if (zShouldContinueLoading) {
            loadingPeriod.continueLoading(this.rendererPositionUs);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void maybeThrowPeriodPrepareError() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (loadingPeriod == null || loadingPeriod.prepared) {
            return;
        }
        if (readingPeriod == null || readingPeriod.next == loadingPeriod) {
            for (Renderer renderer : this.enabledRenderers) {
                if (!renderer.hasReadStreamToEnd()) {
                    return;
                }
            }
            loadingPeriod.mediaPeriod.maybeThrowPrepareError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0034, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x006b, code lost:
    
        r1 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void maybeTriggerPendingMessages(long j9, long j10) {
        PendingMessageInfo pendingMessageInfo;
        PendingMessageInfo pendingMessageInfo2;
        if (this.pendingMessages.isEmpty() || this.playbackInfo.periodId.isAd()) {
            return;
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.startPositionUs == j9) {
            j9--;
        }
        int i9 = playbackInfo.periodId.periodIndex;
        int i10 = this.nextPendingMessageIndex;
        if (i10 > 0) {
            pendingMessageInfo = this.pendingMessages.get(i10 - 1);
            while (pendingMessageInfo != null) {
                int i11 = pendingMessageInfo.resolvedPeriodIndex;
                if (i11 <= i9 && (i11 != i9 || pendingMessageInfo.resolvedPeriodTimeUs <= j9)) {
                    break;
                }
                int i12 = this.nextPendingMessageIndex - 1;
                this.nextPendingMessageIndex = i12;
                if (i12 > 0) {
                    pendingMessageInfo = this.pendingMessages.get(i12 - 1);
                }
            }
            if (this.nextPendingMessageIndex < this.pendingMessages.size()) {
                pendingMessageInfo2 = this.pendingMessages.get(this.nextPendingMessageIndex);
                while (pendingMessageInfo2 != null && pendingMessageInfo2.resolvedPeriodUid != null) {
                    int i13 = pendingMessageInfo2.resolvedPeriodIndex;
                    if (i13 >= i9 && (i13 != i9 || pendingMessageInfo2.resolvedPeriodTimeUs > j9)) {
                        break;
                    }
                    int i14 = this.nextPendingMessageIndex + 1;
                    this.nextPendingMessageIndex = i14;
                    if (i14 < this.pendingMessages.size()) {
                        pendingMessageInfo2 = this.pendingMessages.get(this.nextPendingMessageIndex);
                    }
                }
                while (pendingMessageInfo2 != null && pendingMessageInfo2.resolvedPeriodUid != null && pendingMessageInfo2.resolvedPeriodIndex == i9) {
                    long j11 = pendingMessageInfo2.resolvedPeriodTimeUs;
                    if (j11 <= j9 || j11 > j10) {
                        return;
                    }
                    sendMessageToTarget(pendingMessageInfo2.message);
                    if (pendingMessageInfo2.message.getDeleteAfterDelivery()) {
                        this.pendingMessages.remove(this.nextPendingMessageIndex);
                    } else {
                        this.nextPendingMessageIndex++;
                    }
                    pendingMessageInfo2 = this.nextPendingMessageIndex < this.pendingMessages.size() ? this.pendingMessages.get(this.nextPendingMessageIndex) : null;
                }
                return;
            }
            pendingMessageInfo2 = null;
        }
        pendingMessageInfo = null;
    }

    private void maybeUpdateLoadingPeriod() {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
                return;
            }
            this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, 60000000L, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, this.playbackInfo.timeline.getPeriod(nextMediaPeriodInfo.f15299id.periodIndex, this.period, true).uid, nextMediaPeriodInfo).prepare(this, nextMediaPeriodInfo.startPositionUs);
            setIsLoading(true);
        }
    }

    private void prepareInternal(MediaSource mediaSource, boolean z8, boolean z9) {
        this.pendingPrepareCount++;
        resetInternal(true, z8, z9);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource;
        setState(2);
        mediaSource.prepareSource(this.player, true, this);
        this.handler.sendEmptyMessage(2);
    }

    private void releaseInternal() {
        resetInternal(true, true, true);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder mediaPeriodHolder = this.queue.getReadingPeriod().next;
        return mediaPeriodHolder != null && mediaPeriodHolder.prepared && renderer.hasReadStreamToEnd();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            float f9 = this.mediaClock.getPlaybackParameters().speed;
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z8 = true;
            for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null && playingPeriod.prepared; playingPeriod = playingPeriod.next) {
                if (playingPeriod.selectTracks(f9)) {
                    if (z8) {
                        MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                        boolean zRemoveAfter = this.queue.removeAfter(playingPeriod2);
                        boolean[] zArr = new boolean[this.renderers.length];
                        long jApplyTrackSelection = playingPeriod2.applyTrackSelection(this.playbackInfo.positionUs, zRemoveAfter, zArr);
                        updateLoadControlTrackSelection(playingPeriod2.trackSelectorResult);
                        PlaybackInfo playbackInfo = this.playbackInfo;
                        if (playbackInfo.playbackState != 4 && jApplyTrackSelection != playbackInfo.positionUs) {
                            PlaybackInfo playbackInfo2 = this.playbackInfo;
                            this.playbackInfo = playbackInfo2.fromNewPosition(playbackInfo2.periodId, jApplyTrackSelection, playbackInfo2.contentPositionUs);
                            this.playbackInfoUpdate.setPositionDiscontinuity(4);
                            resetRendererPosition(jApplyTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i9 = 0;
                        int i10 = 0;
                        while (true) {
                            Renderer[] rendererArr = this.renderers;
                            if (i9 >= rendererArr.length) {
                                break;
                            }
                            Renderer renderer = rendererArr[i9];
                            boolean z9 = renderer.getState() != 0;
                            zArr2[i9] = z9;
                            SampleStream sampleStream = playingPeriod2.sampleStreams[i9];
                            if (sampleStream != null) {
                                i10++;
                            }
                            if (z9) {
                                if (sampleStream != renderer.getStream()) {
                                    disableRenderer(renderer);
                                } else if (zArr[i9]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                            i9++;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithTrackSelectorResult(playingPeriod2.trackSelectorResult);
                        enableRenderers(zArr2, i10);
                    } else {
                        this.queue.removeAfter(playingPeriod);
                        if (playingPeriod.prepared) {
                            playingPeriod.applyTrackSelection(Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                            updateLoadControlTrackSelection(playingPeriod.trackSelectorResult);
                        }
                    }
                    if (this.playbackInfo.playbackState != 4) {
                        maybeContinueLoading();
                        updatePlaybackPositions();
                        this.handler.sendEmptyMessage(2);
                        return;
                    }
                    return;
                }
                if (playingPeriod == readingPeriod) {
                    z8 = false;
                }
            }
        }
    }

    private void resetInternal(boolean z8, boolean z9, boolean z10) {
        MediaSource mediaSource;
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = 60000000L;
        for (Renderer renderer : this.enabledRenderers) {
            try {
                disableRenderer(renderer);
            } catch (ExoPlaybackException | RuntimeException e9) {
                Log.e(TAG, "Stop failed.", e9);
            }
        }
        this.enabledRenderers = new Renderer[0];
        this.queue.clear();
        setIsLoading(false);
        if (z9) {
            this.pendingInitialSeekPosition = null;
        }
        if (z10) {
            this.queue.setTimeline(Timeline.EMPTY);
            Iterator<PendingMessageInfo> it = this.pendingMessages.iterator();
            while (it.hasNext()) {
                it.next().message.markAsProcessed(false);
            }
            this.pendingMessages.clear();
            this.nextPendingMessageIndex = 0;
        }
        Timeline timeline = z10 ? Timeline.EMPTY : this.playbackInfo.timeline;
        Object obj = z10 ? null : this.playbackInfo.manifest;
        MediaSource.MediaPeriodId mediaPeriodId = z9 ? new MediaSource.MediaPeriodId(getFirstPeriodIndex()) : this.playbackInfo.periodId;
        long j9 = C3322C.TIME_UNSET;
        long j10 = z9 ? -9223372036854775807L : this.playbackInfo.positionUs;
        if (!z9) {
            j9 = this.playbackInfo.contentPositionUs;
        }
        long j11 = j9;
        PlaybackInfo playbackInfo = this.playbackInfo;
        this.playbackInfo = new PlaybackInfo(timeline, obj, mediaPeriodId, j10, j11, playbackInfo.playbackState, false, z10 ? this.emptyTrackSelectorResult : playbackInfo.trackSelectorResult);
        if (!z8 || (mediaSource = this.mediaSource) == null) {
            return;
        }
        mediaSource.releaseSource();
        this.mediaSource = null;
    }

    private void resetRendererPosition(long j9) {
        long rendererTime = !this.queue.hasPlayingPeriod() ? j9 + 60000000 : this.queue.getPlayingPeriod().toRendererTime(j9);
        this.rendererPositionUs = rendererTime;
        this.mediaClock.resetPosition(rendererTime);
        for (Renderer renderer : this.enabledRenderers) {
            renderer.resetPosition(this.rendererPositionUs);
        }
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        Object obj = pendingMessageInfo.resolvedPeriodUid;
        if (obj == null) {
            Pair<Integer, Long> pairResolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C3322C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (pairResolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(((Integer) pairResolveSeekPosition.first).intValue(), ((Long) pairResolveSeekPosition.second).longValue(), this.playbackInfo.timeline.getPeriod(((Integer) pairResolveSeekPosition.first).intValue(), this.period, true).uid);
        } else {
            int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(obj);
            if (indexOfPeriod == -1) {
                return false;
            }
            pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        }
        return true;
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition(this.pendingMessages.get(size))) {
                this.pendingMessages.get(size).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private Pair<Integer, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z8) {
        int iResolveSubsequentPeriod;
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair<Integer, Long> periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2) {
                return periodPosition;
            }
            int indexOfPeriod = timeline.getIndexOfPeriod(timeline2.getPeriod(((Integer) periodPosition.first).intValue(), this.period, true).uid);
            if (indexOfPeriod != -1) {
                return Pair.create(Integer.valueOf(indexOfPeriod), periodPosition.second);
            }
            if (!z8 || (iResolveSubsequentPeriod = resolveSubsequentPeriod(((Integer) periodPosition.first).intValue(), timeline2, timeline)) == -1) {
                return null;
            }
            return getPeriodPosition(timeline, timeline.getPeriod(iResolveSubsequentPeriod, this.period).windowIndex, C3322C.TIME_UNSET);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private int resolveSubsequentPeriod(int i9, Timeline timeline, Timeline timeline2) {
        int periodCount = timeline.getPeriodCount();
        int nextPeriodIndex = i9;
        int indexOfPeriod = -1;
        for (int i10 = 0; i10 < periodCount && indexOfPeriod == -1; i10++) {
            nextPeriodIndex = timeline.getNextPeriodIndex(nextPeriodIndex, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (nextPeriodIndex == -1) {
                break;
            }
            indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getPeriod(nextPeriodIndex, this.period, true).uid);
        }
        return indexOfPeriod;
    }

    private void scheduleNextWork(long j9, long j10) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j9 + j10);
    }

    private void seekToCurrentPosition(boolean z8) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.f15299id;
        long jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (jSeekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo = this.playbackInfo;
            this.playbackInfo = playbackInfo.fromNewPosition(mediaPeriodId, jSeekToPeriodPosition, playbackInfo.contentPositionUs);
            if (z8) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void seekToInternal(SeekPosition seekPosition) {
        long jLongValue;
        boolean z8;
        MediaSource.MediaPeriodId mediaPeriodId;
        long j9;
        long j10;
        long adjustedSeekPositionUs;
        boolean z9 = true;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Pair<Integer, Long> pairResolveSeekPosition = resolveSeekPosition(seekPosition, true);
        if (pairResolveSeekPosition == null) {
            mediaPeriodId = new MediaSource.MediaPeriodId(getFirstPeriodIndex());
            z8 = true;
            jLongValue = -9223372036854775807L;
            j9 = -9223372036854775807L;
        } else {
            int iIntValue = ((Integer) pairResolveSeekPosition.first).intValue();
            long jLongValue2 = ((Long) pairResolveSeekPosition.second).longValue();
            MediaSource.MediaPeriodId mediaPeriodIdResolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(iIntValue, jLongValue2);
            if (mediaPeriodIdResolveMediaPeriodIdForAds.isAd()) {
                z8 = true;
                jLongValue = 0;
            } else {
                jLongValue = ((Long) pairResolveSeekPosition.second).longValue();
                z8 = seekPosition.windowPositionUs == C3322C.TIME_UNSET;
            }
            mediaPeriodId = mediaPeriodIdResolveMediaPeriodIdForAds;
            j9 = jLongValue2;
        }
        try {
            if (this.mediaSource == null || this.pendingPrepareCount > 0) {
                this.pendingInitialSeekPosition = seekPosition;
            } else {
                if (jLongValue != C3322C.TIME_UNSET) {
                    if (mediaPeriodId.equals(this.playbackInfo.periodId)) {
                        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                        adjustedSeekPositionUs = (playingPeriod == null || jLongValue == 0) ? jLongValue : playingPeriod.mediaPeriod.getAdjustedSeekPositionUs(jLongValue, this.seekParameters);
                        if (C3322C.usToMs(adjustedSeekPositionUs) == C3322C.usToMs(this.playbackInfo.positionUs)) {
                            this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodId, this.playbackInfo.positionUs, j9);
                            if (z8) {
                                this.playbackInfoUpdate.setPositionDiscontinuity(2);
                                return;
                            }
                            return;
                        }
                    } else {
                        adjustedSeekPositionUs = jLongValue;
                    }
                    long jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, adjustedSeekPositionUs);
                    if (jLongValue == jSeekToPeriodPosition) {
                        z9 = false;
                    }
                    z8 |= z9;
                    j10 = jSeekToPeriodPosition;
                    this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodId, j10, j9);
                    if (z8) {
                        return;
                    }
                    this.playbackInfoUpdate.setPositionDiscontinuity(2);
                    return;
                }
                setState(4);
                resetInternal(false, true, false);
            }
            j10 = jLongValue;
            this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodId, j10, j9);
            if (z8) {
            }
        } catch (Throwable th) {
            this.playbackInfo = this.playbackInfo.fromNewPosition(mediaPeriodId, jLongValue, j9);
            if (z8) {
                this.playbackInfoUpdate.setPositionDiscontinuity(2);
            }
            throw th;
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j9) {
        return seekToPeriodPosition(mediaPeriodId, j9, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private void sendMessageInternal(PlayerMessage playerMessage) {
        if (playerMessage.getPositionMs() == C3322C.TIME_UNSET) {
            sendMessageToTarget(playerMessage);
            return;
        }
        if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
            return;
        }
        PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
        if (!resolvePendingMessagePosition(pendingMessageInfo)) {
            playerMessage.markAsProcessed(false);
        } else {
            this.pendingMessages.add(pendingMessageInfo);
            Collections.sort(this.pendingMessages);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) {
        if (playerMessage.getHandler().getLooper() != this.handler.getLooper()) {
            this.handler.obtainMessage(15, playerMessage).sendToTarget();
            return;
        }
        deliverMessage(playerMessage);
        int i9 = this.playbackInfo.playbackState;
        if (i9 == 3 || i9 == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void sendMessageToTargetThread(final PlayerMessage playerMessage) {
        playerMessage.getHandler().post(new Runnable() { // from class: com.google.android.exoplayer2.ExoPlayerImplInternal.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ExoPlayerImplInternal.this.deliverMessage(playerMessage);
                } catch (ExoPlaybackException e9) {
                    Log.e(ExoPlayerImplInternal.TAG, "Unexpected error delivering message on external thread.", e9);
                    throw new RuntimeException(e9);
                }
            }
        });
    }

    private void setIsLoading(boolean z8) {
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.isLoading != z8) {
            this.playbackInfo = playbackInfo.copyWithIsLoading(z8);
        }
    }

    private void setPlayWhenReadyInternal(boolean z8) {
        this.rebuffering = false;
        this.playWhenReady = z8;
        if (!z8) {
            stopRenderers();
            updatePlaybackPositions();
            return;
        }
        int i9 = this.playbackInfo.playbackState;
        if (i9 == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (i9 == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setRepeatModeInternal(int i9) throws ExoPlaybackException {
        this.repeatMode = i9;
        if (this.queue.updateRepeatMode(i9)) {
            return;
        }
        seekToCurrentPosition(true);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters) {
        this.seekParameters = seekParameters;
    }

    private void setShuffleModeEnabledInternal(boolean z8) throws ExoPlaybackException {
        this.shuffleModeEnabled = z8;
        if (this.queue.updateShuffleModeEnabled(z8)) {
            return;
        }
        seekToCurrentPosition(true);
    }

    private void setState(int i9) {
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.playbackState != i9) {
            this.playbackInfo = playbackInfo.copyWithPlaybackState(i9);
        }
    }

    private boolean shouldKeepPeriodHolder(MediaSource.MediaPeriodId mediaPeriodId, long j9, MediaPeriodHolder mediaPeriodHolder) {
        if (!mediaPeriodId.equals(mediaPeriodHolder.info.f15299id) || !mediaPeriodHolder.prepared) {
            return false;
        }
        this.playbackInfo.timeline.getPeriod(mediaPeriodHolder.info.f15299id.periodIndex, this.period);
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j9);
        return adGroupIndexAfterPositionUs == -1 || this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs) == mediaPeriodHolder.info.endPositionUs;
    }

    private boolean shouldTransitionToReadyState(boolean z8) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        if (!z8) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long bufferedPositionUs = loadingPeriod.getBufferedPositionUs(!loadingPeriod.info.isFinal);
        return bufferedPositionUs == Long.MIN_VALUE || this.loadControl.shouldStartPlayback(bufferedPositionUs - loadingPeriod.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed, this.rebuffering);
    }

    private void startRenderers() {
        this.rebuffering = false;
        this.mediaClock.start();
        for (Renderer renderer : this.enabledRenderers) {
            renderer.start();
        }
    }

    private void stopInternal(boolean z8, boolean z9) {
        resetInternal(true, z8, z8);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (z9 ? 1 : 0));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void stopRenderers() {
        this.mediaClock.stop();
        for (Renderer renderer : this.enabledRenderers) {
            ensureStopped(renderer);
        }
    }

    private void updateLoadControlTrackSelection(TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackSelectorResult.groups, trackSelectorResult.selections);
    }

    private void updatePeriods() throws ExoPlaybackException {
        MediaSource mediaSource = this.mediaSource;
        if (mediaSource == null) {
            return;
        }
        if (this.pendingPrepareCount > 0) {
            mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        maybeUpdateLoadingPeriod();
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        int i9 = 0;
        if (loadingPeriod == null || loadingPeriod.isFullyBuffered()) {
            setIsLoading(false);
        } else if (!this.playbackInfo.isLoading) {
            maybeContinueLoading();
        }
        if (!this.queue.hasPlayingPeriod()) {
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        boolean z8 = false;
        while (this.playWhenReady && playingPeriod != readingPeriod && this.rendererPositionUs >= playingPeriod.next.rendererPositionOffsetUs) {
            if (z8) {
                maybeNotifyPlaybackInfoChanged();
            }
            int i10 = playingPeriod.info.isLastInTimelinePeriod ? 0 : 3;
            MediaPeriodHolder mediaPeriodHolderAdvancePlayingPeriod = this.queue.advancePlayingPeriod();
            updatePlayingPeriodRenderers(playingPeriod);
            PlaybackInfo playbackInfo = this.playbackInfo;
            MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolderAdvancePlayingPeriod.info;
            this.playbackInfo = playbackInfo.fromNewPosition(mediaPeriodInfo.f15299id, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.contentPositionUs);
            this.playbackInfoUpdate.setPositionDiscontinuity(i10);
            updatePlaybackPositions();
            playingPeriod = mediaPeriodHolderAdvancePlayingPeriod;
            z8 = true;
        }
        if (readingPeriod.info.isFinal) {
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i9 >= rendererArr.length) {
                    return;
                }
                Renderer renderer = rendererArr[i9];
                SampleStream sampleStream = readingPeriod.sampleStreams[i9];
                if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                    renderer.setCurrentStreamFinal();
                }
                i9++;
            }
        } else {
            MediaPeriodHolder mediaPeriodHolder = readingPeriod.next;
            if (mediaPeriodHolder == null || !mediaPeriodHolder.prepared) {
                return;
            }
            int i11 = 0;
            while (true) {
                Renderer[] rendererArr2 = this.renderers;
                if (i11 < rendererArr2.length) {
                    Renderer renderer2 = rendererArr2[i11];
                    SampleStream sampleStream2 = readingPeriod.sampleStreams[i11];
                    if (renderer2.getStream() != sampleStream2) {
                        return;
                    }
                    if (sampleStream2 != null && !renderer2.hasReadStreamToEnd()) {
                        return;
                    } else {
                        i11++;
                    }
                } else {
                    TrackSelectorResult trackSelectorResult = readingPeriod.trackSelectorResult;
                    MediaPeriodHolder mediaPeriodHolderAdvanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = mediaPeriodHolderAdvanceReadingPeriod.trackSelectorResult;
                    boolean z9 = mediaPeriodHolderAdvanceReadingPeriod.mediaPeriod.readDiscontinuity() != C3322C.TIME_UNSET;
                    int i12 = 0;
                    while (true) {
                        Renderer[] rendererArr3 = this.renderers;
                        if (i12 >= rendererArr3.length) {
                            return;
                        }
                        Renderer renderer3 = rendererArr3[i12];
                        if (trackSelectorResult.renderersEnabled[i12]) {
                            if (z9) {
                                renderer3.setCurrentStreamFinal();
                            } else if (!renderer3.isCurrentStreamFinal()) {
                                TrackSelection trackSelection = trackSelectorResult2.selections.get(i12);
                                boolean z10 = trackSelectorResult2.renderersEnabled[i12];
                                boolean z11 = this.rendererCapabilities[i12].getTrackType() == 5;
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i12];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i12];
                                if (z10 && rendererConfiguration2.equals(rendererConfiguration) && !z11) {
                                    renderer3.replaceStream(getFormats(trackSelection), mediaPeriodHolderAdvanceReadingPeriod.sampleStreams[i12], mediaPeriodHolderAdvanceReadingPeriod.getRendererOffset());
                                } else {
                                    renderer3.setCurrentStreamFinal();
                                }
                            }
                        }
                        i12++;
                    }
                }
            }
        }
    }

    private void updatePlaybackPositions() {
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            long discontinuity = playingPeriod.mediaPeriod.readDiscontinuity();
            if (discontinuity != C3322C.TIME_UNSET) {
                resetRendererPosition(discontinuity);
                if (discontinuity != this.playbackInfo.positionUs) {
                    PlaybackInfo playbackInfo = this.playbackInfo;
                    this.playbackInfo = playbackInfo.fromNewPosition(playbackInfo.periodId, discontinuity, playbackInfo.contentPositionUs);
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                long jSyncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs();
                this.rendererPositionUs = jSyncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(jSyncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            this.playbackInfo.bufferedPositionUs = this.enabledRenderers.length == 0 ? playingPeriod.info.durationUs : playingPeriod.getBufferedPositionUs(true);
        }
    }

    private void updatePlayingPeriodRenderers(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod == null || mediaPeriodHolder == playingPeriod) {
            return;
        }
        boolean[] zArr = new boolean[this.renderers.length];
        int i9 = 0;
        int i10 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i9 >= rendererArr.length) {
                this.playbackInfo = this.playbackInfo.copyWithTrackSelectorResult(playingPeriod.trackSelectorResult);
                enableRenderers(zArr, i10);
                return;
            }
            Renderer renderer = rendererArr[i9];
            boolean z8 = renderer.getState() != 0;
            zArr[i9] = z8;
            boolean z9 = playingPeriod.trackSelectorResult.renderersEnabled[i9];
            if (z9) {
                i10++;
            }
            if (z8 && (!z9 || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i9]))) {
                disableRenderer(renderer);
            }
            i9++;
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f9) {
        for (MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            TrackSelectorResult trackSelectorResult = frontPeriod.trackSelectorResult;
            if (trackSelectorResult != null) {
                for (TrackSelection trackSelection : trackSelectorResult.selections.getAll()) {
                    if (trackSelection != null) {
                        trackSelection.onPlaybackSpeed(f9);
                    }
                }
            }
        }
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e9) {
            Log.e(TAG, "Playback error.", e9);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, e9).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e10) {
            Log.e(TAG, "Source error.", e10);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForSource(e10)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (RuntimeException e11) {
            Log.e(TAG, "Internal runtime error.", e11);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForUnexpected(e11)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    @Override // com.google.android.exoplayer2.DefaultMediaClock.PlaybackParameterListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.eventHandler.obtainMessage(1, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Listener
    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource, timeline, obj)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(11);
    }

    public void prepare(MediaSource mediaSource, boolean z8, boolean z9) {
        this.handler.obtainMessage(0, z8 ? 1 : 0, z9 ? 1 : 0, mediaSource).sendToTarget();
    }

    public synchronized void release() {
        if (this.released) {
            return;
        }
        this.handler.sendEmptyMessage(7);
        boolean z8 = false;
        while (!this.released) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z8 = true;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    public void seekTo(Timeline timeline, int i9, long j9) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i9, j9)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.PlayerMessage.Sender
    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (!this.released) {
            this.handler.obtainMessage(14, playerMessage).sendToTarget();
        } else {
            Log.w(TAG, "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
        }
    }

    public void setPlayWhenReady(boolean z8) {
        this.handler.obtainMessage(1, z8 ? 1 : 0, 0).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setRepeatMode(int i9) {
        this.handler.obtainMessage(12, i9, 0).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters) {
        this.handler.obtainMessage(5, seekParameters).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z8) {
        this.handler.obtainMessage(13, z8 ? 1 : 0, 0).sendToTarget();
    }

    public void stop(boolean z8) {
        this.handler.obtainMessage(6, z8 ? 1 : 0, 0).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j9, boolean z8) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolderAdvancePlayingPeriod = playingPeriod;
        while (true) {
            if (mediaPeriodHolderAdvancePlayingPeriod == null) {
                break;
            }
            if (shouldKeepPeriodHolder(mediaPeriodId, j9, mediaPeriodHolderAdvancePlayingPeriod)) {
                this.queue.removeAfter(mediaPeriodHolderAdvancePlayingPeriod);
                break;
            }
            mediaPeriodHolderAdvancePlayingPeriod = this.queue.advancePlayingPeriod();
        }
        if (playingPeriod != mediaPeriodHolderAdvancePlayingPeriod || z8) {
            for (Renderer renderer : this.enabledRenderers) {
                disableRenderer(renderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
        }
        if (mediaPeriodHolderAdvancePlayingPeriod != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolderAdvancePlayingPeriod.hasEnabledTracks) {
                long jSeekToUs = mediaPeriodHolderAdvancePlayingPeriod.mediaPeriod.seekToUs(j9);
                mediaPeriodHolderAdvancePlayingPeriod.mediaPeriod.discardBuffer(jSeekToUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
                j9 = jSeekToUs;
            }
            resetRendererPosition(j9);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j9);
        }
        this.handler.sendEmptyMessage(2);
        return j9;
    }
}
