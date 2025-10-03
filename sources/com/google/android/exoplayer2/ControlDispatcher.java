package com.google.android.exoplayer2;

/* loaded from: classes.dex */
public interface ControlDispatcher {
    boolean dispatchSeekTo(Player player, int i9, long j9);

    boolean dispatchSetPlayWhenReady(Player player, boolean z8);

    boolean dispatchSetRepeatMode(Player player, int i9);

    boolean dispatchSetShuffleModeEnabled(Player player, boolean z8);
}
