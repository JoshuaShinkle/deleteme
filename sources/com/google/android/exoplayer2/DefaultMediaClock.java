package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.StandaloneMediaClock;

/* loaded from: classes.dex */
final class DefaultMediaClock implements MediaClock {
    private final PlaybackParameterListener listener;
    private MediaClock rendererClock;
    private Renderer rendererClockSource;
    private final StandaloneMediaClock standaloneMediaClock;

    public interface PlaybackParameterListener {
        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);
    }

    public DefaultMediaClock(PlaybackParameterListener playbackParameterListener, Clock clock) {
        this.listener = playbackParameterListener;
        this.standaloneMediaClock = new StandaloneMediaClock(clock);
    }

    private void ensureSynced() {
        this.standaloneMediaClock.resetPosition(this.rendererClock.getPositionUs());
        PlaybackParameters playbackParameters = this.rendererClock.getPlaybackParameters();
        if (playbackParameters.equals(this.standaloneMediaClock.getPlaybackParameters())) {
            return;
        }
        this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
        this.listener.onPlaybackParametersChanged(playbackParameters);
    }

    private boolean isUsingRendererClock() {
        Renderer renderer = this.rendererClockSource;
        return (renderer == null || renderer.isEnded() || (!this.rendererClockSource.isReady() && this.rendererClockSource.hasReadStreamToEnd())) ? false : true;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        MediaClock mediaClock = this.rendererClock;
        return mediaClock != null ? mediaClock.getPlaybackParameters() : this.standaloneMediaClock.getPlaybackParameters();
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        return isUsingRendererClock() ? this.rendererClock.getPositionUs() : this.standaloneMediaClock.getPositionUs();
    }

    public void onRendererDisabled(Renderer renderer) {
        if (renderer == this.rendererClockSource) {
            this.rendererClock = null;
            this.rendererClockSource = null;
        }
    }

    public void onRendererEnabled(Renderer renderer) throws ExoPlaybackException {
        MediaClock mediaClock;
        MediaClock mediaClock2 = renderer.getMediaClock();
        if (mediaClock2 == null || mediaClock2 == (mediaClock = this.rendererClock)) {
            return;
        }
        if (mediaClock != null) {
            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
        }
        this.rendererClock = mediaClock2;
        this.rendererClockSource = renderer;
        mediaClock2.setPlaybackParameters(this.standaloneMediaClock.getPlaybackParameters());
        ensureSynced();
    }

    public void resetPosition(long j9) {
        this.standaloneMediaClock.resetPosition(j9);
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        MediaClock mediaClock = this.rendererClock;
        if (mediaClock != null) {
            playbackParameters = mediaClock.setPlaybackParameters(playbackParameters);
        }
        this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
        this.listener.onPlaybackParametersChanged(playbackParameters);
        return playbackParameters;
    }

    public void start() {
        this.standaloneMediaClock.start();
    }

    public void stop() {
        this.standaloneMediaClock.stop();
    }

    public long syncAndGetPositionUs() {
        if (!isUsingRendererClock()) {
            return this.standaloneMediaClock.getPositionUs();
        }
        ensureSynced();
        return this.rendererClock.getPositionUs();
    }
}
