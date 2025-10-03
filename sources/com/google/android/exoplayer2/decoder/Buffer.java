package com.google.android.exoplayer2.decoder;

/* loaded from: classes.dex */
public abstract class Buffer {
    private int flags;

    public final void addFlag(int i9) {
        this.flags = i9 | this.flags;
    }

    public void clear() {
        this.flags = 0;
    }

    public final void clearFlag(int i9) {
        this.flags = (~i9) & this.flags;
    }

    public final boolean getFlag(int i9) {
        return (this.flags & i9) == i9;
    }

    public final boolean isDecodeOnly() {
        return getFlag(Integer.MIN_VALUE);
    }

    public final boolean isEndOfStream() {
        return getFlag(4);
    }

    public final boolean isKeyFrame() {
        return getFlag(1);
    }

    public final void setFlags(int i9) {
        this.flags = i9;
    }
}
