package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.WindowManager;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.messaging.Constants;

@TargetApi(16)
/* loaded from: classes.dex */
public final class VideoFrameReleaseTimeHelper {
    private static final long CHOREOGRAPHER_SAMPLE_DELAY_MILLIS = 500;
    private static final long MAX_ALLOWED_DRIFT_NS = 20000000;
    private static final int MIN_FRAMES_FOR_ADJUSTMENT = 6;
    private static final long VSYNC_OFFSET_PERCENTAGE = 80;
    private long adjustedLastFrameTimeNs;
    private final DefaultDisplayListener displayListener;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private long vsyncDurationNs;
    private long vsyncOffsetNs;
    private final VSyncSampler vsyncSampler;
    private final WindowManager windowManager;

    @TargetApi(17)
    public final class DefaultDisplayListener implements DisplayManager.DisplayListener {
        private final DisplayManager displayManager;

        public DefaultDisplayListener(DisplayManager displayManager) {
            this.displayManager = displayManager;
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i9) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i9) {
            if (i9 == 0) {
                VideoFrameReleaseTimeHelper.this.updateDefaultDisplayRefreshRateParams();
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i9) {
        }

        public void register() {
            this.displayManager.registerDisplayListener(this, null);
        }

        public void unregister() {
            this.displayManager.unregisterDisplayListener(this);
        }
    }

    public static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        private static final int CREATE_CHOREOGRAPHER = 0;
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private static final int MSG_ADD_OBSERVER = 1;
        private static final int MSG_REMOVE_OBSERVER = 2;
        private Choreographer choreographer;
        private final HandlerThread choreographerOwnerThread;
        private final Handler handler;
        private int observerCount;
        public volatile long sampledVsyncTimeNs = C3322C.TIME_UNSET;

        private VSyncSampler() {
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.choreographerOwnerThread = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.handler = handler;
            handler.sendEmptyMessage(0);
        }

        private void addObserverInternal() {
            int i9 = this.observerCount + 1;
            this.observerCount = i9;
            if (i9 == 1) {
                this.choreographer.postFrameCallback(this);
            }
        }

        private void createChoreographerInstanceInternal() {
            this.choreographer = Choreographer.getInstance();
        }

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private void removeObserverInternal() {
            int i9 = this.observerCount - 1;
            this.observerCount = i9;
            if (i9 == 0) {
                this.choreographer.removeFrameCallback(this);
                this.sampledVsyncTimeNs = C3322C.TIME_UNSET;
            }
        }

        public void addObserver() {
            this.handler.sendEmptyMessage(1);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j9) {
            this.sampledVsyncTimeNs = j9;
            this.choreographer.postFrameCallbackDelayed(this, VideoFrameReleaseTimeHelper.CHOREOGRAPHER_SAMPLE_DELAY_MILLIS);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 0) {
                createChoreographerInstanceInternal();
                return true;
            }
            if (i9 == 1) {
                addObserverInternal();
                return true;
            }
            if (i9 != 2) {
                return false;
            }
            removeObserverInternal();
            return true;
        }

        public void removeObserver() {
            this.handler.sendEmptyMessage(2);
        }
    }

    public VideoFrameReleaseTimeHelper() {
        this(null);
    }

    private static long closestVsync(long j9, long j10, long j11) {
        long j12;
        long j13 = j10 + (((j9 - j10) / j11) * j11);
        if (j9 <= j13) {
            j12 = j13 - j11;
        } else {
            j13 = j11 + j13;
            j12 = j13;
        }
        return j13 - j9 < j9 - j12 ? j13 : j12;
    }

    private boolean isDriftTooLarge(long j9, long j10) {
        return Math.abs((j10 - this.syncUnadjustedReleaseTimeNs) - (j9 - this.syncFramePresentationTimeNs)) > MAX_ALLOWED_DRIFT_NS;
    }

    @TargetApi(17)
    private DefaultDisplayListener maybeBuildDefaultDisplayListenerV17(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        if (displayManager == null) {
            return null;
        }
        return new DefaultDisplayListener(displayManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDefaultDisplayRefreshRateParams() {
        if (this.windowManager.getDefaultDisplay() != null) {
            long refreshRate = (long) (1.0E9d / r0.getRefreshRate());
            this.vsyncDurationNs = refreshRate;
            this.vsyncOffsetNs = (refreshRate * VSYNC_OFFSET_PERCENTAGE) / 100;
        }
    }

    public long adjustReleaseTime(long j9, long j10) {
        long j11;
        long j12;
        long j13 = 1000 * j9;
        if (this.haveSync) {
            if (j9 != this.lastFramePresentationTimeUs) {
                this.frameCount++;
                this.adjustedLastFrameTimeNs = this.pendingAdjustedFrameTimeNs;
            }
            long j14 = this.frameCount;
            if (j14 >= 6) {
                j12 = this.adjustedLastFrameTimeNs + ((j13 - this.syncFramePresentationTimeNs) / j14);
                if (isDriftTooLarge(j12, j10)) {
                    this.haveSync = false;
                } else {
                    j11 = (this.syncUnadjustedReleaseTimeNs + j12) - this.syncFramePresentationTimeNs;
                }
            } else if (isDriftTooLarge(j13, j10)) {
                this.haveSync = false;
            }
            j11 = j10;
            j12 = j13;
        } else {
            j11 = j10;
            j12 = j13;
        }
        if (!this.haveSync) {
            this.syncFramePresentationTimeNs = j13;
            this.syncUnadjustedReleaseTimeNs = j10;
            this.frameCount = 0L;
            this.haveSync = true;
        }
        this.lastFramePresentationTimeUs = j9;
        this.pendingAdjustedFrameTimeNs = j12;
        VSyncSampler vSyncSampler = this.vsyncSampler;
        if (vSyncSampler == null || this.vsyncDurationNs == C3322C.TIME_UNSET) {
            return j11;
        }
        long j15 = vSyncSampler.sampledVsyncTimeNs;
        return j15 == C3322C.TIME_UNSET ? j11 : closestVsync(j11, j15, this.vsyncDurationNs) - this.vsyncOffsetNs;
    }

    public void disable() {
        if (this.windowManager != null) {
            DefaultDisplayListener defaultDisplayListener = this.displayListener;
            if (defaultDisplayListener != null) {
                defaultDisplayListener.unregister();
            }
            this.vsyncSampler.removeObserver();
        }
    }

    public void enable() {
        this.haveSync = false;
        if (this.windowManager != null) {
            this.vsyncSampler.addObserver();
            DefaultDisplayListener defaultDisplayListener = this.displayListener;
            if (defaultDisplayListener != null) {
                defaultDisplayListener.register();
            }
            updateDefaultDisplayRefreshRateParams();
        }
    }

    public VideoFrameReleaseTimeHelper(Context context) {
        WindowManager windowManager = context == null ? null : (WindowManager) context.getSystemService("window");
        this.windowManager = windowManager;
        if (windowManager != null) {
            this.displayListener = Util.SDK_INT >= 17 ? maybeBuildDefaultDisplayListenerV17(context) : null;
            this.vsyncSampler = VSyncSampler.getInstance();
        } else {
            this.displayListener = null;
            this.vsyncSampler = null;
        }
        this.vsyncDurationNs = C3322C.TIME_UNSET;
        this.vsyncOffsetNs = C3322C.TIME_UNSET;
    }
}
