package com.google.android.exoplayer2.p038ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.exoplayer2.SimpleExoPlayer;

@TargetApi(16)
@Deprecated
/* loaded from: classes.dex */
public final class SimpleExoPlayerView extends PlayerView {
    public SimpleExoPlayerView(Context context) {
        super(context);
    }

    public static void switchTargetView(SimpleExoPlayer simpleExoPlayer, SimpleExoPlayerView simpleExoPlayerView, SimpleExoPlayerView simpleExoPlayerView2) {
        PlayerView.switchTargetView(simpleExoPlayer, simpleExoPlayerView, simpleExoPlayerView2);
    }

    public SimpleExoPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleExoPlayerView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
