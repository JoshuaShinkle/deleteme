package com.google.android.exoplayer2.p038ui;

import android.annotation.SuppressLint;
import android.widget.TextView;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import java.util.Locale;

/* loaded from: classes.dex */
public class DebugTextViewHelper extends Player.DefaultEventListener implements Runnable {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final SimpleExoPlayer player;
    private boolean started;
    private final TextView textView;

    public DebugTextViewHelper(SimpleExoPlayer simpleExoPlayer, TextView textView) {
        this.player = simpleExoPlayer;
        this.textView = textView;
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters decoderCounters) {
        if (decoderCounters == null) {
            return "";
        }
        decoderCounters.ensureUpdated();
        return " sib:" + decoderCounters.skippedInputBufferCount + " sb:" + decoderCounters.skippedOutputBufferCount + " rb:" + decoderCounters.renderedOutputBufferCount + " db:" + decoderCounters.droppedBufferCount + " mcdb:" + decoderCounters.maxConsecutiveDroppedBufferCount + " dk:" + decoderCounters.droppedToKeyframeCount;
    }

    private static String getPixelAspectRatioString(float f9) {
        if (f9 == -1.0f || f9 == 1.0f) {
            return "";
        }
        return " par:" + String.format(Locale.US, "%.02f", Float.valueOf(f9));
    }

    public String getAudioString() {
        Format audioFormat = this.player.getAudioFormat();
        if (audioFormat == null) {
            return "";
        }
        return "\n" + audioFormat.sampleMimeType + "(id:" + audioFormat.f15298id + " hz:" + audioFormat.sampleRate + " ch:" + audioFormat.channelCount + getDecoderCountersBufferCountString(this.player.getAudioDecoderCounters()) + ")";
    }

    public String getDebugString() {
        return getPlayerStateString() + getVideoString() + getAudioString();
    }

    public String getPlayerStateString() {
        int playbackState = this.player.getPlaybackState();
        return String.format("playWhenReady:%s playbackState:%s window:%s", Boolean.valueOf(this.player.getPlayWhenReady()), playbackState != 1 ? playbackState != 2 ? playbackState != 3 ? playbackState != 4 ? "unknown" : "ended" : "ready" : "buffering" : "idle", Integer.valueOf(this.player.getCurrentWindowIndex()));
    }

    public String getVideoString() {
        Format videoFormat = this.player.getVideoFormat();
        if (videoFormat == null) {
            return "";
        }
        return "\n" + videoFormat.sampleMimeType + "(id:" + videoFormat.f15298id + " r:" + videoFormat.width + "x" + videoFormat.height + getPixelAspectRatioString(videoFormat.pixelWidthHeightRatio) + getDecoderCountersBufferCountString(this.player.getVideoDecoderCounters()) + ")";
    }

    @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
    public final void onPlayerStateChanged(boolean z8, int i9) {
        updateAndPost();
    }

    @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
    public final void onPositionDiscontinuity(int i9) {
        updateAndPost();
    }

    @Override // java.lang.Runnable
    public final void run() {
        updateAndPost();
    }

    public final void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        this.player.addListener(this);
        updateAndPost();
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            this.player.removeListener(this);
            this.textView.removeCallbacks(this);
        }
    }

    @SuppressLint({"SetTextI18n"})
    public final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this);
        this.textView.postDelayed(this, 1000L);
    }
}
