package com.google.android.exoplayer2.p038ui;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.exoplayer2.p038ui.PlayerControlView;

@Deprecated
/* loaded from: classes.dex */
public class PlaybackControlView extends PlayerControlView {

    @Deprecated
    public static final ControlDispatcher DEFAULT_CONTROL_DISPATCHER = new DefaultControlDispatcher();
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_REWIND_MS = 5000;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;

    @Deprecated
    public interface ControlDispatcher extends com.google.android.exoplayer2.ControlDispatcher {
    }

    public static final class DefaultControlDispatcher extends com.google.android.exoplayer2.DefaultControlDispatcher implements ControlDispatcher {
        private DefaultControlDispatcher() {
        }
    }

    @Deprecated
    public interface VisibilityListener extends PlayerControlView.VisibilityListener {
    }

    public PlaybackControlView(Context context) {
        super(context);
    }

    public PlaybackControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlaybackControlView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }

    public PlaybackControlView(Context context, AttributeSet attributeSet, int i9, AttributeSet attributeSet2) {
        super(context, attributeSet, i9, attributeSet2);
    }
}
