package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
final class ExoPlayerImpl implements ExoPlayer {
    private static final String TAG = "ExoPlayerImpl";
    private final TrackSelectorResult emptyTrackSelectorResult;
    private final Handler eventHandler;
    private boolean hasPendingPrepare;
    private boolean hasPendingSeek;
    private final ExoPlayerImplInternal internalPlayer;
    private final Handler internalPlayerHandler;
    private final CopyOnWriteArraySet<Player.EventListener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private int pendingOperationAcks;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private final Renderer[] renderers;
    private int repeatMode;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, Clock clock) {
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [" + ExoPlayerLibraryInfo.VERSION_SLASHY + "] [" + Util.DEVICE_DEBUG_INFO + "]");
        Assertions.checkState(rendererArr.length > 0);
        this.renderers = (Renderer[]) Assertions.checkNotNull(rendererArr);
        this.trackSelector = (TrackSelector) Assertions.checkNotNull(trackSelector);
        this.playWhenReady = false;
        this.repeatMode = 0;
        this.shuffleModeEnabled = false;
        this.listeners = new CopyOnWriteArraySet<>();
        TrackSelectorResult trackSelectorResult = new TrackSelectorResult(TrackGroupArray.EMPTY, new boolean[rendererArr.length], new TrackSelectionArray(new TrackSelection[rendererArr.length]), null, new RendererConfiguration[rendererArr.length]);
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.playbackParameters = PlaybackParameters.DEFAULT;
        Handler handler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()) { // from class: com.google.android.exoplayer2.ExoPlayerImpl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                ExoPlayerImpl.this.handleEvent(message);
            }
        };
        this.eventHandler = handler;
        this.playbackInfo = new PlaybackInfo(Timeline.EMPTY, 0L, trackSelectorResult);
        ExoPlayerImplInternal exoPlayerImplInternal = new ExoPlayerImplInternal(rendererArr, trackSelector, trackSelectorResult, loadControl, this.playWhenReady, this.repeatMode, this.shuffleModeEnabled, handler, this, clock);
        this.internalPlayer = exoPlayerImplInternal;
        this.internalPlayerHandler = new Handler(exoPlayerImplInternal.getPlaybackLooper());
    }

    private PlaybackInfo getResetPlaybackInfo(boolean z8, boolean z9, int i9) {
        if (z8) {
            this.maskingWindowIndex = 0;
            this.maskingPeriodIndex = 0;
            this.maskingWindowPositionMs = 0L;
        } else {
            this.maskingWindowIndex = getCurrentWindowIndex();
            this.maskingPeriodIndex = getCurrentPeriodIndex();
            this.maskingWindowPositionMs = getCurrentPosition();
        }
        Timeline timeline = z9 ? Timeline.EMPTY : this.playbackInfo.timeline;
        Object obj = z9 ? null : this.playbackInfo.manifest;
        PlaybackInfo playbackInfo = this.playbackInfo;
        return new PlaybackInfo(timeline, obj, playbackInfo.periodId, playbackInfo.startPositionUs, playbackInfo.contentPositionUs, i9, false, z9 ? this.emptyTrackSelectorResult : playbackInfo.trackSelectorResult);
    }

    private void handlePlaybackInfo(PlaybackInfo playbackInfo, int i9, boolean z8, int i10) {
        int i11 = this.pendingOperationAcks - i9;
        this.pendingOperationAcks = i11;
        if (i11 == 0) {
            if (playbackInfo.startPositionUs == C3322C.TIME_UNSET) {
                playbackInfo = playbackInfo.fromNewPosition(playbackInfo.periodId, 0L, playbackInfo.contentPositionUs);
            }
            PlaybackInfo playbackInfo2 = playbackInfo;
            if ((!this.playbackInfo.timeline.isEmpty() || this.hasPendingPrepare) && playbackInfo2.timeline.isEmpty()) {
                this.maskingPeriodIndex = 0;
                this.maskingWindowIndex = 0;
                this.maskingWindowPositionMs = 0L;
            }
            int i12 = this.hasPendingPrepare ? 0 : 2;
            boolean z9 = this.hasPendingSeek;
            this.hasPendingPrepare = false;
            this.hasPendingSeek = false;
            updatePlaybackInfo(playbackInfo2, z8, i10, i12, z9);
        }
    }

    private long playbackInfoPositionUsToWindowPositionMs(long j9) {
        long jUsToMs = C3322C.usToMs(j9);
        if (this.playbackInfo.periodId.isAd()) {
            return jUsToMs;
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        playbackInfo.timeline.getPeriod(playbackInfo.periodId.periodIndex, this.period);
        return jUsToMs + this.period.getPositionInWindowMs();
    }

    private boolean shouldMaskPosition() {
        return this.playbackInfo.timeline.isEmpty() || this.pendingOperationAcks > 0;
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo, boolean z8, int i9, int i10, boolean z9) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        boolean z10 = (playbackInfo2.timeline == playbackInfo.timeline && playbackInfo2.manifest == playbackInfo.manifest) ? false : true;
        boolean z11 = playbackInfo2.playbackState != playbackInfo.playbackState;
        boolean z12 = playbackInfo2.isLoading != playbackInfo.isLoading;
        boolean z13 = playbackInfo2.trackSelectorResult != playbackInfo.trackSelectorResult;
        this.playbackInfo = playbackInfo;
        if (z10 || i10 == 0) {
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                Player.EventListener next = it.next();
                PlaybackInfo playbackInfo3 = this.playbackInfo;
                next.onTimelineChanged(playbackInfo3.timeline, playbackInfo3.manifest, i10);
            }
        }
        if (z8) {
            Iterator<Player.EventListener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().onPositionDiscontinuity(i9);
            }
        }
        if (z13) {
            this.trackSelector.onSelectionActivated(this.playbackInfo.trackSelectorResult.info);
            Iterator<Player.EventListener> it3 = this.listeners.iterator();
            while (it3.hasNext()) {
                Player.EventListener next2 = it3.next();
                TrackSelectorResult trackSelectorResult = this.playbackInfo.trackSelectorResult;
                next2.onTracksChanged(trackSelectorResult.groups, trackSelectorResult.selections);
            }
        }
        if (z12) {
            Iterator<Player.EventListener> it4 = this.listeners.iterator();
            while (it4.hasNext()) {
                it4.next().onLoadingChanged(this.playbackInfo.isLoading);
            }
        }
        if (z11) {
            Iterator<Player.EventListener> it5 = this.listeners.iterator();
            while (it5.hasNext()) {
                it5.next().onPlayerStateChanged(this.playWhenReady, this.playbackInfo.playbackState);
            }
        }
        if (z9) {
            Iterator<Player.EventListener> it6 = this.listeners.iterator();
            while (it6.hasNext()) {
                it6.next().onSeekProcessed();
            }
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void addListener(Player.EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void blockingSendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        ArrayList<PlayerMessage> arrayList = new ArrayList();
        for (ExoPlayer.ExoPlayerMessage exoPlayerMessage : exoPlayerMessageArr) {
            arrayList.add(createMessage(exoPlayerMessage.target).setType(exoPlayerMessage.messageType).setPayload(exoPlayerMessage.message).send());
        }
        boolean z8 = false;
        for (PlayerMessage playerMessage : arrayList) {
            boolean z9 = true;
            while (z9) {
                try {
                    playerMessage.blockUntilDelivered();
                    z9 = false;
                } catch (InterruptedException unused) {
                    z8 = true;
                }
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public PlayerMessage createMessage(PlayerMessage.Target target) {
        return new PlayerMessage(this.internalPlayer, target, this.playbackInfo.timeline, getCurrentWindowIndex(), this.internalPlayerHandler);
    }

    @Override // com.google.android.exoplayer2.Player
    public int getBufferedPercentage() {
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == C3322C.TIME_UNSET || duration == C3322C.TIME_UNSET) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((bufferedPosition * 100) / duration), 0, 100);
    }

    @Override // com.google.android.exoplayer2.Player
    public long getBufferedPosition() {
        return shouldMaskPosition() ? this.maskingWindowPositionMs : playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.bufferedPositionUs);
    }

    @Override // com.google.android.exoplayer2.Player
    public long getContentPosition() {
        if (!isPlayingAd()) {
            return getCurrentPosition();
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        playbackInfo.timeline.getPeriod(playbackInfo.periodId.periodIndex, this.period);
        return this.period.getPositionInWindowMs() + C3322C.usToMs(this.playbackInfo.contentPositionUs);
    }

    @Override // com.google.android.exoplayer2.Player
    public int getCurrentAdGroupIndex() {
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getCurrentAdIndexInAdGroup() {
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.Player
    public Object getCurrentManifest() {
        return this.playbackInfo.manifest;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getCurrentPeriodIndex() {
        return shouldMaskPosition() ? this.maskingPeriodIndex : this.playbackInfo.periodId.periodIndex;
    }

    @Override // com.google.android.exoplayer2.Player
    public long getCurrentPosition() {
        return shouldMaskPosition() ? this.maskingWindowPositionMs : playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.positionUs);
    }

    @Override // com.google.android.exoplayer2.Player
    public Timeline getCurrentTimeline() {
        return this.playbackInfo.timeline;
    }

    @Override // com.google.android.exoplayer2.Player
    public TrackGroupArray getCurrentTrackGroups() {
        return this.playbackInfo.trackSelectorResult.groups;
    }

    @Override // com.google.android.exoplayer2.Player
    public TrackSelectionArray getCurrentTrackSelections() {
        return this.playbackInfo.trackSelectorResult.selections;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getCurrentWindowIndex() {
        if (shouldMaskPosition()) {
            return this.maskingWindowIndex;
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        return playbackInfo.timeline.getPeriod(playbackInfo.periodId.periodIndex, this.period).windowIndex;
    }

    @Override // com.google.android.exoplayer2.Player
    public long getDuration() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return C3322C.TIME_UNSET;
        }
        if (!isPlayingAd()) {
            return timeline.getWindow(getCurrentWindowIndex(), this.window).getDurationMs();
        }
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
        return C3322C.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    @Override // com.google.android.exoplayer2.Player
    public int getNextWindowIndex() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return -1;
        }
        return timeline.getNextWindowIndex(getCurrentWindowIndex(), this.repeatMode, this.shuffleModeEnabled);
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    @Override // com.google.android.exoplayer2.Player
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getPlaybackState() {
        return this.playbackInfo.playbackState;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getPreviousWindowIndex() {
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty()) {
            return -1;
        }
        return timeline.getPreviousWindowIndex(getCurrentWindowIndex(), this.repeatMode, this.shuffleModeEnabled);
    }

    @Override // com.google.android.exoplayer2.Player
    public int getRendererCount() {
        return this.renderers.length;
    }

    @Override // com.google.android.exoplayer2.Player
    public int getRendererType(int i9) {
        return this.renderers[i9].getTrackType();
    }

    @Override // com.google.android.exoplayer2.Player
    public int getRepeatMode() {
        return this.repeatMode;
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean getShuffleModeEnabled() {
        return this.shuffleModeEnabled;
    }

    @Override // com.google.android.exoplayer2.Player
    public Player.TextComponent getTextComponent() {
        return null;
    }

    @Override // com.google.android.exoplayer2.Player
    public Player.VideoComponent getVideoComponent() {
        return null;
    }

    public void handleEvent(Message message) {
        int i9 = message.what;
        if (i9 == 0) {
            PlaybackInfo playbackInfo = (PlaybackInfo) message.obj;
            int i10 = message.arg1;
            int i11 = message.arg2;
            handlePlaybackInfo(playbackInfo, i10, i11 != -1, i11);
            return;
        }
        if (i9 != 1) {
            if (i9 != 2) {
                throw new IllegalStateException();
            }
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) message.obj;
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayerError(exoPlaybackException);
            }
            return;
        }
        PlaybackParameters playbackParameters = (PlaybackParameters) message.obj;
        if (this.playbackParameters.equals(playbackParameters)) {
            return;
        }
        this.playbackParameters = playbackParameters;
        Iterator<Player.EventListener> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().onPlaybackParametersChanged(playbackParameters);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean isCurrentWindowDynamic() {
        Timeline timeline = this.playbackInfo.timeline;
        return !timeline.isEmpty() && timeline.getWindow(getCurrentWindowIndex(), this.window).isDynamic;
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean isCurrentWindowSeekable() {
        Timeline timeline = this.playbackInfo.timeline;
        return !timeline.isEmpty() && timeline.getWindow(getCurrentWindowIndex(), this.window).isSeekable;
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean isLoading() {
        return this.playbackInfo.isLoading;
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean isPlayingAd() {
        return !shouldMaskPosition() && this.playbackInfo.periodId.isAd();
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void prepare(MediaSource mediaSource) {
        prepare(mediaSource, true, true);
    }

    @Override // com.google.android.exoplayer2.Player
    public void release() {
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [" + ExoPlayerLibraryInfo.VERSION_SLASHY + "] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.exoplayer2.Player
    public void removeListener(Player.EventListener eventListener) {
        this.listeners.remove(eventListener);
    }

    @Override // com.google.android.exoplayer2.Player
    public void seekTo(long j9) {
        seekTo(getCurrentWindowIndex(), j9);
    }

    @Override // com.google.android.exoplayer2.Player
    public void seekToDefaultPosition() {
        seekToDefaultPosition(getCurrentWindowIndex());
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void sendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        for (ExoPlayer.ExoPlayerMessage exoPlayerMessage : exoPlayerMessageArr) {
            createMessage(exoPlayerMessage.target).setType(exoPlayerMessage.messageType).setPayload(exoPlayerMessage.message).send();
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void setPlayWhenReady(boolean z8) {
        if (this.playWhenReady != z8) {
            this.playWhenReady = z8;
            this.internalPlayer.setPlayWhenReady(z8);
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayerStateChanged(z8, this.playbackInfo.playbackState);
            }
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        this.internalPlayer.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.Player
    public void setRepeatMode(int i9) {
        if (this.repeatMode != i9) {
            this.repeatMode = i9;
            this.internalPlayer.setRepeatMode(i9);
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onRepeatModeChanged(i9);
            }
        }
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void setSeekParameters(SeekParameters seekParameters) {
        if (seekParameters == null) {
            seekParameters = SeekParameters.DEFAULT;
        }
        this.internalPlayer.setSeekParameters(seekParameters);
    }

    @Override // com.google.android.exoplayer2.Player
    public void setShuffleModeEnabled(boolean z8) {
        if (this.shuffleModeEnabled != z8) {
            this.shuffleModeEnabled = z8;
            this.internalPlayer.setShuffleModeEnabled(z8);
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onShuffleModeEnabledChanged(z8);
            }
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void stop() {
        stop(false);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void prepare(MediaSource mediaSource, boolean z8, boolean z9) {
        PlaybackInfo resetPlaybackInfo = getResetPlaybackInfo(z8, z9, 2);
        this.hasPendingPrepare = true;
        this.pendingOperationAcks++;
        this.internalPlayer.prepare(mediaSource, z8, z9);
        updatePlaybackInfo(resetPlaybackInfo, false, 4, 1, false);
    }

    @Override // com.google.android.exoplayer2.Player
    public void seekTo(int i9, long j9) {
        Timeline timeline = this.playbackInfo.timeline;
        if (i9 < 0 || (!timeline.isEmpty() && i9 >= timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(timeline, i9, j9);
        }
        this.hasPendingSeek = true;
        this.pendingOperationAcks++;
        if (isPlayingAd()) {
            Log.w(TAG, "seekTo ignored because an ad is playing");
            this.eventHandler.obtainMessage(0, 1, -1, this.playbackInfo).sendToTarget();
            return;
        }
        this.maskingWindowIndex = i9;
        if (timeline.isEmpty()) {
            this.maskingWindowPositionMs = j9 == C3322C.TIME_UNSET ? 0L : j9;
            this.maskingPeriodIndex = 0;
        } else {
            long defaultPositionUs = j9 == C3322C.TIME_UNSET ? timeline.getWindow(i9, this.window).getDefaultPositionUs() : C3322C.msToUs(j9);
            Pair<Integer, Long> periodPosition = timeline.getPeriodPosition(this.window, this.period, i9, defaultPositionUs);
            this.maskingWindowPositionMs = C3322C.usToMs(defaultPositionUs);
            this.maskingPeriodIndex = ((Integer) periodPosition.first).intValue();
        }
        this.internalPlayer.seekTo(timeline, i9, C3322C.msToUs(j9));
        Iterator<Player.EventListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPositionDiscontinuity(1);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void seekToDefaultPosition(int i9) {
        seekTo(i9, C3322C.TIME_UNSET);
    }

    @Override // com.google.android.exoplayer2.Player
    public void stop(boolean z8) {
        PlaybackInfo resetPlaybackInfo = getResetPlaybackInfo(z8, z8, 1);
        this.pendingOperationAcks++;
        this.internalPlayer.stop(z8);
        updatePlaybackInfo(resetPlaybackInfo, false, 4, 1, false);
    }
}
