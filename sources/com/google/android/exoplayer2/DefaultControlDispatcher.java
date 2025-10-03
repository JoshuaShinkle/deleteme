package com.google.android.exoplayer2;

/* loaded from: classes.dex */
public class DefaultControlDispatcher implements ControlDispatcher {
    @Override // com.google.android.exoplayer2.ControlDispatcher
    public boolean dispatchSeekTo(Player player, int i9, long j9) {
        player.seekTo(i9, j9);
        return true;
    }

    @Override // com.google.android.exoplayer2.ControlDispatcher
    public boolean dispatchSetPlayWhenReady(Player player, boolean z8) {
        player.setPlayWhenReady(z8);
        return true;
    }

    @Override // com.google.android.exoplayer2.ControlDispatcher
    public boolean dispatchSetRepeatMode(Player player, int i9) {
        player.setRepeatMode(i9);
        return true;
    }

    @Override // com.google.android.exoplayer2.ControlDispatcher
    public boolean dispatchSetShuffleModeEnabled(Player player, boolean z8) {
        player.setShuffleModeEnabled(z8);
        return true;
    }
}
