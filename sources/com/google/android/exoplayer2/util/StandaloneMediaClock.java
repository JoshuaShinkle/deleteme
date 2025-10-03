package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.PlaybackParameters;

/* loaded from: classes.dex */
public final class StandaloneMediaClock implements MediaClock {
    private long baseElapsedMs;
    private long baseUs;
    private final Clock clock;
    private PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
    private boolean started;

    public StandaloneMediaClock(Clock clock) {
        this.clock = clock;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        long j9 = this.baseUs;
        if (!this.started) {
            return j9;
        }
        long jElapsedRealtime = this.clock.elapsedRealtime() - this.baseElapsedMs;
        PlaybackParameters playbackParameters = this.playbackParameters;
        return j9 + (playbackParameters.speed == 1.0f ? C3322C.msToUs(jElapsedRealtime) : playbackParameters.getMediaTimeUsForPlayoutTimeMs(jElapsedRealtime));
    }

    public void resetPosition(long j9) {
        this.baseUs = j9;
        if (this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
        }
    }

    @Override // com.google.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.started) {
            resetPosition(getPositionUs());
        }
        this.playbackParameters = playbackParameters;
        return playbackParameters;
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.baseElapsedMs = this.clock.elapsedRealtime();
        this.started = true;
    }

    public void stop() {
        if (this.started) {
            resetPosition(getPositionUs());
            this.started = false;
        }
    }
}
