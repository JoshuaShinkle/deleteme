package com.google.android.exoplayer2.p038ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.p038ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes.dex */
public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_REWIND_MS = 5000;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    private static final long MAX_POSITION_FOR_SEEK_TO_PREVIOUS = 3000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private long[] adGroupTimesMs;
    private final ComponentListener componentListener;
    private ControlDispatcher controlDispatcher;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    private final View fastForwardButton;
    private int fastForwardMs;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private final Runnable hideAction;
    private long hideAtMs;
    private boolean isAttachedToWindow;
    private boolean multiWindowTimeBar;
    private final View nextButton;
    private final View pauseButton;
    private final Timeline.Period period;
    private final View playButton;
    private PlaybackPreparer playbackPreparer;
    private boolean[] playedAdGroups;
    private Player player;
    private final TextView positionView;
    private final View previousButton;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    private final ImageView repeatToggleButton;
    private int repeatToggleModes;
    private final View rewindButton;
    private int rewindMs;
    private boolean scrubbing;
    private boolean showMultiWindowTimeBar;
    private boolean showShuffleButton;
    private int showTimeoutMs;
    private final View shuffleButton;
    private final TimeBar timeBar;
    private final Runnable updateProgressAction;
    private VisibilityListener visibilityListener;
    private final Timeline.Window window;

    public final class ComponentListener extends Player.DefaultEventListener implements TimeBar.OnScrubListener, View.OnClickListener {
        private ComponentListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PlayerControlView.this.player != null) {
                if (PlayerControlView.this.nextButton == view) {
                    PlayerControlView.this.next();
                } else if (PlayerControlView.this.previousButton == view) {
                    PlayerControlView.this.previous();
                } else if (PlayerControlView.this.fastForwardButton == view) {
                    PlayerControlView.this.fastForward();
                } else if (PlayerControlView.this.rewindButton == view) {
                    PlayerControlView.this.rewind();
                } else if (PlayerControlView.this.playButton == view) {
                    if (PlayerControlView.this.player.getPlaybackState() == 1) {
                        if (PlayerControlView.this.playbackPreparer != null) {
                            PlayerControlView.this.playbackPreparer.preparePlayback();
                        }
                    } else if (PlayerControlView.this.player.getPlaybackState() == 4) {
                        PlayerControlView.this.controlDispatcher.dispatchSeekTo(PlayerControlView.this.player, PlayerControlView.this.player.getCurrentWindowIndex(), C3322C.TIME_UNSET);
                    }
                    PlayerControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlayerControlView.this.player, true);
                } else if (PlayerControlView.this.pauseButton == view) {
                    PlayerControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlayerControlView.this.player, false);
                } else if (PlayerControlView.this.repeatToggleButton == view) {
                    PlayerControlView.this.controlDispatcher.dispatchSetRepeatMode(PlayerControlView.this.player, RepeatModeUtil.getNextRepeatMode(PlayerControlView.this.player.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
                } else if (PlayerControlView.this.shuffleButton == view) {
                    PlayerControlView.this.controlDispatcher.dispatchSetShuffleModeEnabled(PlayerControlView.this.player, true ^ PlayerControlView.this.player.getShuffleModeEnabled());
                }
            }
            PlayerControlView.this.hideAfterTimeout();
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z8, int i9) {
            PlayerControlView.this.updatePlayPauseButton();
            PlayerControlView.this.updateProgress();
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i9) {
            PlayerControlView.this.updateNavigation();
            PlayerControlView.this.updateProgress();
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onRepeatModeChanged(int i9) {
            PlayerControlView.this.updateRepeatModeButton();
            PlayerControlView.this.updateNavigation();
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubMove(TimeBar timeBar, long j9) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j9));
            }
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubStart(TimeBar timeBar, long j9) {
            PlayerControlView playerControlView = PlayerControlView.this;
            playerControlView.removeCallbacks(playerControlView.hideAction);
            PlayerControlView.this.scrubbing = true;
        }

        @Override // com.google.android.exoplayer2.ui.TimeBar.OnScrubListener
        public void onScrubStop(TimeBar timeBar, long j9, boolean z8) {
            PlayerControlView.this.scrubbing = false;
            if (!z8 && PlayerControlView.this.player != null) {
                PlayerControlView.this.seekToTimeBarPosition(j9);
            }
            PlayerControlView.this.hideAfterTimeout();
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onShuffleModeEnabledChanged(boolean z8) {
            PlayerControlView.this.updateShuffleButton();
            PlayerControlView.this.updateNavigation();
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj, int i9) {
            PlayerControlView.this.updateNavigation();
            PlayerControlView.this.updateTimeBarMode();
            PlayerControlView.this.updateProgress();
        }
    }

    public interface VisibilityListener {
        void onVisibilityChange(int i9);
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ui");
    }

    public PlayerControlView(Context context) {
        this(context, null);
    }

    private static boolean canShowMultiWindowTimeBar(Timeline timeline, Timeline.Window window) {
        if (timeline.getWindowCount() > 100) {
            return false;
        }
        int windowCount = timeline.getWindowCount();
        for (int i9 = 0; i9 < windowCount; i9++) {
            if (timeline.getWindow(i9, window).durationUs == C3322C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fastForward() {
        if (this.fastForwardMs <= 0) {
            return;
        }
        long duration = this.player.getDuration();
        long currentPosition = this.player.getCurrentPosition() + this.fastForwardMs;
        if (duration != C3322C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekTo(currentPosition);
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i9) {
        return typedArray.getInt(C3429R.styleable.PlayerControlView_repeat_toggle_modes, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAfterTimeout() {
        removeCallbacks(this.hideAction);
        if (this.showTimeoutMs <= 0) {
            this.hideAtMs = C3322C.TIME_UNSET;
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        int i9 = this.showTimeoutMs;
        this.hideAtMs = jUptimeMillis + i9;
        if (this.isAttachedToWindow) {
            postDelayed(this.hideAction, i9);
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean isHandledMediaKey(int i9) {
        return i9 == 90 || i9 == 89 || i9 == 85 || i9 == 126 || i9 == 127 || i9 == 87 || i9 == 88;
    }

    private boolean isPlaying() {
        Player player = this.player;
        return (player == null || player.getPlaybackState() == 4 || this.player.getPlaybackState() == 1 || !this.player.getPlayWhenReady()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void next() {
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return;
        }
        int currentWindowIndex = this.player.getCurrentWindowIndex();
        int nextWindowIndex = this.player.getNextWindowIndex();
        if (nextWindowIndex != -1) {
            seekTo(nextWindowIndex, C3322C.TIME_UNSET);
        } else if (currentTimeline.getWindow(currentWindowIndex, this.window, false).isDynamic) {
            seekTo(currentWindowIndex, C3322C.TIME_UNSET);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
    
        if (r1.isSeekable == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void previous() {
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return;
        }
        currentTimeline.getWindow(this.player.getCurrentWindowIndex(), this.window);
        int previousWindowIndex = this.player.getPreviousWindowIndex();
        if (previousWindowIndex != -1) {
            if (this.player.getCurrentPosition() > MAX_POSITION_FOR_SEEK_TO_PREVIOUS) {
                Timeline.Window window = this.window;
                if (window.isDynamic) {
                }
            }
            seekTo(previousWindowIndex, C3322C.TIME_UNSET);
            return;
        }
        seekTo(0L);
    }

    private void requestPlayPauseFocus() {
        View view;
        View view2;
        boolean zIsPlaying = isPlaying();
        if (!zIsPlaying && (view2 = this.playButton) != null) {
            view2.requestFocus();
        } else {
            if (!zIsPlaying || (view = this.pauseButton) == null) {
                return;
            }
            view.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rewind() {
        if (this.rewindMs <= 0) {
            return;
        }
        seekTo(Math.max(this.player.getCurrentPosition() - this.rewindMs, 0L));
    }

    private void seekTo(long j9) {
        seekTo(this.player.getCurrentWindowIndex(), j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToTimeBarPosition(long j9) {
        int currentWindowIndex;
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (this.multiWindowTimeBar && !currentTimeline.isEmpty()) {
            int windowCount = currentTimeline.getWindowCount();
            currentWindowIndex = 0;
            while (true) {
                long durationMs = currentTimeline.getWindow(currentWindowIndex, this.window).getDurationMs();
                if (j9 < durationMs) {
                    break;
                }
                if (currentWindowIndex == windowCount - 1) {
                    j9 = durationMs;
                    break;
                } else {
                    j9 -= durationMs;
                    currentWindowIndex++;
                }
            }
        } else {
            currentWindowIndex = this.player.getCurrentWindowIndex();
        }
        seekTo(currentWindowIndex, j9);
    }

    private void setButtonEnabled(boolean z8, View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(z8);
        view.setAlpha(z8 ? 1.0f : 0.3f);
        view.setVisibility(0);
    }

    private void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNavigation() {
        boolean z8;
        boolean z9;
        boolean z10;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            Timeline currentTimeline = player != null ? player.getCurrentTimeline() : null;
            if (!((currentTimeline == null || currentTimeline.isEmpty()) ? false : true) || this.player.isPlayingAd()) {
                z8 = false;
                z9 = false;
                z10 = false;
            } else {
                currentTimeline.getWindow(this.player.getCurrentWindowIndex(), this.window);
                Timeline.Window window = this.window;
                z9 = window.isSeekable;
                z8 = (!z9 && window.isDynamic && this.player.getPreviousWindowIndex() == -1) ? false : true;
                z10 = this.window.isDynamic || this.player.getNextWindowIndex() != -1;
            }
            setButtonEnabled(z8, this.previousButton);
            setButtonEnabled(z10, this.nextButton);
            setButtonEnabled(this.fastForwardMs > 0 && z9, this.fastForwardButton);
            setButtonEnabled(this.rewindMs > 0 && z9, this.rewindButton);
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setEnabled(z9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayPauseButton() {
        boolean z8;
        if (isVisible() && this.isAttachedToWindow) {
            boolean zIsPlaying = isPlaying();
            View view = this.playButton;
            if (view != null) {
                z8 = (zIsPlaying && view.isFocused()) | false;
                this.playButton.setVisibility(zIsPlaying ? 8 : 0);
            } else {
                z8 = false;
            }
            View view2 = this.pauseButton;
            if (view2 != null) {
                z8 |= !zIsPlaying && view2.isFocused();
                this.pauseButton.setVisibility(zIsPlaying ? 0 : 8);
            }
            if (z8) {
                requestPlayPauseFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgress() {
        long contentPosition;
        long j9;
        long j10;
        int i9;
        Timeline.Window window;
        int i10;
        if (isVisible() && this.isAttachedToWindow) {
            Player player = this.player;
            long jUsToMs = 0;
            boolean z8 = true;
            if (player != null) {
                Timeline currentTimeline = player.getCurrentTimeline();
                if (currentTimeline.isEmpty()) {
                    j10 = 0;
                    i9 = 0;
                } else {
                    int currentWindowIndex = this.player.getCurrentWindowIndex();
                    boolean z9 = this.multiWindowTimeBar;
                    int i11 = z9 ? 0 : currentWindowIndex;
                    int windowCount = z9 ? currentTimeline.getWindowCount() - 1 : currentWindowIndex;
                    long j11 = 0;
                    j10 = 0;
                    i9 = 0;
                    while (true) {
                        if (i11 > windowCount) {
                            break;
                        }
                        if (i11 == currentWindowIndex) {
                            j10 = j11;
                        }
                        currentTimeline.getWindow(i11, this.window);
                        Timeline.Window window2 = this.window;
                        int i12 = windowCount;
                        if (window2.durationUs == C3322C.TIME_UNSET) {
                            Assertions.checkState(this.multiWindowTimeBar ^ z8);
                            break;
                        }
                        int i13 = window2.firstPeriodIndex;
                        while (true) {
                            window = this.window;
                            if (i13 <= window.lastPeriodIndex) {
                                currentTimeline.getPeriod(i13, this.period);
                                int adGroupCount = this.period.getAdGroupCount();
                                int i14 = 0;
                                while (i14 < adGroupCount) {
                                    long adGroupTimeUs = this.period.getAdGroupTimeUs(i14);
                                    if (adGroupTimeUs == Long.MIN_VALUE) {
                                        i10 = currentWindowIndex;
                                        long j12 = this.period.durationUs;
                                        if (j12 == C3322C.TIME_UNSET) {
                                            i14++;
                                            currentWindowIndex = i10;
                                        } else {
                                            adGroupTimeUs = j12;
                                        }
                                    } else {
                                        i10 = currentWindowIndex;
                                    }
                                    long positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                                    if (positionInWindowUs >= 0 && positionInWindowUs <= this.window.durationUs) {
                                        long[] jArr = this.adGroupTimesMs;
                                        if (i9 == jArr.length) {
                                            int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                            this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                            this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                        }
                                        this.adGroupTimesMs[i9] = C3322C.usToMs(j11 + positionInWindowUs);
                                        this.playedAdGroups[i9] = this.period.hasPlayedAdGroup(i14);
                                        i9++;
                                    }
                                    i14++;
                                    currentWindowIndex = i10;
                                }
                                i13++;
                            }
                        }
                        j11 += window.durationUs;
                        i11++;
                        windowCount = i12;
                        currentWindowIndex = currentWindowIndex;
                        z8 = true;
                    }
                    jUsToMs = j11;
                }
                jUsToMs = C3322C.usToMs(jUsToMs);
                long jUsToMs2 = C3322C.usToMs(j10);
                if (this.player.isPlayingAd()) {
                    contentPosition = jUsToMs2 + this.player.getContentPosition();
                    j9 = contentPosition;
                } else {
                    long currentPosition = this.player.getCurrentPosition() + jUsToMs2;
                    long bufferedPosition = jUsToMs2 + this.player.getBufferedPosition();
                    contentPosition = currentPosition;
                    j9 = bufferedPosition;
                }
                if (this.timeBar != null) {
                    int length2 = this.extraAdGroupTimesMs.length;
                    int i15 = i9 + length2;
                    long[] jArr2 = this.adGroupTimesMs;
                    if (i15 > jArr2.length) {
                        this.adGroupTimesMs = Arrays.copyOf(jArr2, i15);
                        this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i15);
                    }
                    System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i9, length2);
                    System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i9, length2);
                    this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i15);
                }
            } else {
                contentPosition = 0;
                j9 = 0;
            }
            TextView textView = this.durationView;
            if (textView != null) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, jUsToMs));
            }
            TextView textView2 = this.positionView;
            if (textView2 != null && !this.scrubbing) {
                textView2.setText(Util.getStringForTime(this.formatBuilder, this.formatter, contentPosition));
            }
            TimeBar timeBar = this.timeBar;
            if (timeBar != null) {
                timeBar.setPosition(contentPosition);
                this.timeBar.setBufferedPosition(j9);
                this.timeBar.setDuration(jUsToMs);
            }
            removeCallbacks(this.updateProgressAction);
            Player player2 = this.player;
            int playbackState = player2 == null ? 1 : player2.getPlaybackState();
            if (playbackState == 1 || playbackState == 4) {
                return;
            }
            long j13 = 1000;
            if (this.player.getPlayWhenReady() && playbackState == 3) {
                float f9 = this.player.getPlaybackParameters().speed;
                if (f9 > 0.1f) {
                    if (f9 <= 5.0f) {
                        long jMax = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT / Math.max(1, Math.round(1.0f / f9));
                        long j14 = jMax - (contentPosition % jMax);
                        if (j14 < jMax / 5) {
                            j14 += jMax;
                        }
                        if (f9 != 1.0f) {
                            j14 = (long) (j14 / f9);
                        }
                        j13 = j14;
                    } else {
                        j13 = 200;
                    }
                }
            }
            postDelayed(this.updateProgressAction, j13);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                imageView.setVisibility(8);
                return;
            }
            if (this.player == null) {
                setButtonEnabled(false, imageView);
                return;
            }
            setButtonEnabled(true, imageView);
            int repeatMode = this.player.getRepeatMode();
            if (repeatMode == 0) {
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            } else if (repeatMode == 1) {
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
            } else if (repeatMode == 2) {
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
            }
            this.repeatToggleButton.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShuffleButton() {
        View view;
        if (isVisible() && this.isAttachedToWindow && (view = this.shuffleButton) != null) {
            if (!this.showShuffleButton) {
                view.setVisibility(8);
                return;
            }
            Player player = this.player;
            if (player == null) {
                setButtonEnabled(false, view);
                return;
            }
            view.setAlpha(player.getShuffleModeEnabled() ? 1.0f : 0.3f);
            this.shuffleButton.setEnabled(true);
            this.shuffleButton.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTimeBarMode() {
        Player player = this.player;
        if (player == null) {
            return;
        }
        this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player.getCurrentTimeline(), this.window);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            if (keyCode == 90) {
                fastForward();
            } else if (keyCode == 89) {
                rewind();
            } else if (keyEvent.getRepeatCount() == 0) {
                if (keyCode == 85) {
                    this.controlDispatcher.dispatchSetPlayWhenReady(this.player, !r0.getPlayWhenReady());
                } else if (keyCode == 87) {
                    next();
                } else if (keyCode == 88) {
                    previous();
                } else if (keyCode == 126) {
                    this.controlDispatcher.dispatchSetPlayWhenReady(this.player, true);
                } else if (keyCode == 127) {
                    this.controlDispatcher.dispatchSetPlayWhenReady(this.player, false);
                }
            }
        }
        return true;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getShowShuffleButton() {
        return this.showShuffleButton;
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void hide() {
        if (isVisible()) {
            setVisibility(8);
            VisibilityListener visibilityListener = this.visibilityListener;
            if (visibilityListener != null) {
                visibilityListener.onVisibilityChange(getVisibility());
            }
            removeCallbacks(this.updateProgressAction);
            removeCallbacks(this.hideAction);
            this.hideAtMs = C3322C.TIME_UNSET;
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        long j9 = this.hideAtMs;
        if (j9 != C3322C.TIME_UNSET) {
            long jUptimeMillis = j9 - SystemClock.uptimeMillis();
            if (jUptimeMillis <= 0) {
                hide();
            } else {
                postDelayed(this.hideAction, jUptimeMillis);
            }
        } else if (isVisible()) {
            hideAfterTimeout();
        }
        updateAll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        removeCallbacks(this.hideAction);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (controlDispatcher == null) {
            controlDispatcher = new DefaultControlDispatcher();
        }
        this.controlDispatcher = controlDispatcher;
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            Assertions.checkArgument(jArr.length == zArr.length);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr;
        }
        updateProgress();
    }

    public void setFastForwardIncrementMs(int i9) {
        this.fastForwardMs = i9;
        updateNavigation();
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer) {
        this.playbackPreparer = playbackPreparer;
    }

    public void setPlayer(Player player) {
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(this.componentListener);
        }
        updateAll();
    }

    public void setRepeatToggleModes(int i9) {
        this.repeatToggleModes = i9;
        Player player = this.player;
        if (player != null) {
            int repeatMode = player.getRepeatMode();
            if (i9 == 0 && repeatMode != 0) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 0);
                return;
            }
            if (i9 == 1 && repeatMode == 2) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 1);
            } else if (i9 == 2 && repeatMode == 1) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 2);
            }
        }
    }

    public void setRewindIncrementMs(int i9) {
        this.rewindMs = i9;
        updateNavigation();
    }

    public void setShowMultiWindowTimeBar(boolean z8) {
        this.showMultiWindowTimeBar = z8;
        updateTimeBarMode();
    }

    public void setShowShuffleButton(boolean z8) {
        this.showShuffleButton = z8;
        updateShuffleButton();
    }

    public void setShowTimeoutMs(int i9) {
        this.showTimeoutMs = i9;
        if (isVisible()) {
            hideAfterTimeout();
        }
    }

    public void setVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListener = visibilityListener;
    }

    public void show() {
        if (!isVisible()) {
            setVisibility(0);
            VisibilityListener visibilityListener = this.visibilityListener;
            if (visibilityListener != null) {
                visibilityListener.onVisibilityChange(getVisibility());
            }
            updateAll();
            requestPlayPauseFocus();
        }
        hideAfterTimeout();
    }

    public PlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void seekTo(int i9, long j9) {
        if (this.controlDispatcher.dispatchSeekTo(this.player, i9, j9)) {
            return;
        }
        updateProgress();
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i9) {
        this(context, attributeSet, i9, attributeSet);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i9, AttributeSet attributeSet2) {
        super(context, attributeSet, i9);
        this.updateProgressAction = new Runnable() { // from class: com.google.android.exoplayer2.ui.PlayerControlView.1
            @Override // java.lang.Runnable
            public void run() {
                PlayerControlView.this.updateProgress();
            }
        };
        this.hideAction = new Runnable() { // from class: com.google.android.exoplayer2.ui.PlayerControlView.2
            @Override // java.lang.Runnable
            public void run() {
                PlayerControlView.this.hide();
            }
        };
        int resourceId = C3429R.layout.exo_player_control_view;
        this.rewindMs = 5000;
        this.fastForwardMs = 15000;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.hideAtMs = C3322C.TIME_UNSET;
        this.showShuffleButton = false;
        if (attributeSet2 != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, C3429R.styleable.PlayerControlView, 0, 0);
            try {
                this.rewindMs = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerControlView_rewind_increment, this.rewindMs);
                this.fastForwardMs = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerControlView_fastforward_increment, this.fastForwardMs);
                this.showTimeoutMs = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerControlView_show_timeout, this.showTimeoutMs);
                resourceId = typedArrayObtainStyledAttributes.getResourceId(C3429R.styleable.PlayerControlView_controller_layout_id, resourceId);
                this.repeatToggleModes = getRepeatToggleModes(typedArrayObtainStyledAttributes, this.repeatToggleModes);
                this.showShuffleButton = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerControlView_show_shuffle_button, this.showShuffleButton);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        this.period = new Timeline.Period();
        this.window = new Timeline.Window();
        StringBuilder sb = new StringBuilder();
        this.formatBuilder = sb;
        this.formatter = new Formatter(sb, Locale.getDefault());
        this.adGroupTimesMs = new long[0];
        this.playedAdGroups = new boolean[0];
        this.extraAdGroupTimesMs = new long[0];
        this.extraPlayedAdGroups = new boolean[0];
        ComponentListener componentListener = new ComponentListener();
        this.componentListener = componentListener;
        this.controlDispatcher = new DefaultControlDispatcher();
        LayoutInflater.from(context).inflate(resourceId, this);
        setDescendantFocusability(262144);
        this.durationView = (TextView) findViewById(C3429R.id.exo_duration);
        this.positionView = (TextView) findViewById(C3429R.id.exo_position);
        TimeBar timeBar = (TimeBar) findViewById(C3429R.id.exo_progress);
        this.timeBar = timeBar;
        if (timeBar != null) {
            timeBar.addListener(componentListener);
        }
        View viewFindViewById = findViewById(C3429R.id.exo_play);
        this.playButton = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(componentListener);
        }
        View viewFindViewById2 = findViewById(C3429R.id.exo_pause);
        this.pauseButton = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(componentListener);
        }
        View viewFindViewById3 = findViewById(C3429R.id.exo_prev);
        this.previousButton = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(componentListener);
        }
        View viewFindViewById4 = findViewById(C3429R.id.exo_next);
        this.nextButton = viewFindViewById4;
        if (viewFindViewById4 != null) {
            viewFindViewById4.setOnClickListener(componentListener);
        }
        View viewFindViewById5 = findViewById(C3429R.id.exo_rew);
        this.rewindButton = viewFindViewById5;
        if (viewFindViewById5 != null) {
            viewFindViewById5.setOnClickListener(componentListener);
        }
        View viewFindViewById6 = findViewById(C3429R.id.exo_ffwd);
        this.fastForwardButton = viewFindViewById6;
        if (viewFindViewById6 != null) {
            viewFindViewById6.setOnClickListener(componentListener);
        }
        ImageView imageView = (ImageView) findViewById(C3429R.id.exo_repeat_toggle);
        this.repeatToggleButton = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(componentListener);
        }
        View viewFindViewById7 = findViewById(C3429R.id.exo_shuffle);
        this.shuffleButton = viewFindViewById7;
        if (viewFindViewById7 != null) {
            viewFindViewById7.setOnClickListener(componentListener);
        }
        Resources resources = context.getResources();
        this.repeatOffButtonDrawable = resources.getDrawable(C3429R.drawable.exo_controls_repeat_off);
        this.repeatOneButtonDrawable = resources.getDrawable(C3429R.drawable.exo_controls_repeat_one);
        this.repeatAllButtonDrawable = resources.getDrawable(C3429R.drawable.exo_controls_repeat_all);
        this.repeatOffButtonContentDescription = resources.getString(C3429R.string.exo_controls_repeat_off_description);
        this.repeatOneButtonContentDescription = resources.getString(C3429R.string.exo_controls_repeat_one_description);
        this.repeatAllButtonContentDescription = resources.getString(C3429R.string.exo_controls_repeat_all_description);
    }
}
