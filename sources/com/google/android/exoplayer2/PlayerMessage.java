package com.google.android.exoplayer2;

import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;

/* loaded from: classes.dex */
public final class PlayerMessage {
    private Handler handler;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Object payload;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;
    private int windowIndex;
    private long positionMs = C3322C.TIME_UNSET;
    private boolean deleteAfterDelivery = true;

    public interface Sender {
        void sendMessage(PlayerMessage playerMessage);
    }

    public interface Target {
        void handleMessage(int i9, Object obj);
    }

    public PlayerMessage(Sender sender, Target target, Timeline timeline, int i9, Handler handler) {
        this.sender = sender;
        this.target = target;
        this.timeline = timeline;
        this.handler = handler;
        this.windowIndex = i9;
    }

    public synchronized boolean blockUntilDelivered() {
        Assertions.checkState(this.isSent);
        Assertions.checkState(this.handler.getLooper().getThread() != Thread.currentThread());
        while (!this.isProcessed) {
            wait();
        }
        return this.isDelivered;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public int getWindowIndex() {
        return this.windowIndex;
    }

    public synchronized void markAsProcessed(boolean z8) {
        this.isDelivered = z8 | this.isDelivered;
        this.isProcessed = true;
        notifyAll();
    }

    public PlayerMessage send() {
        Assertions.checkState(!this.isSent);
        if (this.positionMs == C3322C.TIME_UNSET) {
            Assertions.checkArgument(this.deleteAfterDelivery);
        }
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setDeleteAfterDelivery(boolean z8) {
        Assertions.checkState(!this.isSent);
        this.deleteAfterDelivery = z8;
        return this;
    }

    public PlayerMessage setHandler(Handler handler) {
        Assertions.checkState(!this.isSent);
        this.handler = handler;
        return this;
    }

    public PlayerMessage setPayload(Object obj) {
        Assertions.checkState(!this.isSent);
        this.payload = obj;
        return this;
    }

    public PlayerMessage setPosition(long j9) {
        Assertions.checkState(!this.isSent);
        this.positionMs = j9;
        return this;
    }

    public PlayerMessage setType(int i9) {
        Assertions.checkState(!this.isSent);
        this.type = i9;
        return this;
    }

    public PlayerMessage setPosition(int i9, long j9) {
        Assertions.checkState(!this.isSent);
        Assertions.checkArgument(j9 != C3322C.TIME_UNSET);
        if (i9 >= 0 && (this.timeline.isEmpty() || i9 < this.timeline.getWindowCount())) {
            this.windowIndex = i9;
            this.positionMs = j9;
            return this;
        }
        throw new IllegalSeekPositionException(this.timeline, i9, j9);
    }
}
