package com.google.android.exoplayer2.p038ui;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.p038ui.PlayerControlView;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;

/* loaded from: classes.dex */
public class PlayerView extends FrameLayout {
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final ImageView artworkView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    private boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private Bitmap defaultArtwork;
    private final FrameLayout overlayFrameLayout;
    private Player player;
    private final View shutterView;
    private final SubtitleView subtitleView;
    private final View surfaceView;
    private int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    public final class ComponentListener extends Player.DefaultEventListener implements TextOutput, VideoListener, View.OnLayoutChangeListener {
        private ComponentListener() {
        }

        @Override // com.google.android.exoplayer2.text.TextOutput
        public void onCues(List<Cue> list) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.onCues(list);
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z8, int i9) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            } else {
                PlayerView.this.maybeShowController(false);
            }
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i9) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
            }
        }

        @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            PlayerView.this.updateForCurrentTrackSelections();
        }

        @Override // com.google.android.exoplayer2.video.VideoListener
        public void onVideoSizeChanged(int i9, int i10, int i11, float f9) {
            if (PlayerView.this.contentFrame == null) {
                return;
            }
            float f10 = (i10 == 0 || i9 == 0) ? 1.0f : (i9 * f9) / i10;
            if (PlayerView.this.surfaceView instanceof TextureView) {
                if (i11 == 90 || i11 == 270) {
                    f10 = 1.0f / f10;
                }
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.removeOnLayoutChangeListener(this);
                }
                PlayerView.this.textureViewRotation = i11;
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.addOnLayoutChangeListener(this);
                }
                PlayerView.applyTextureViewRotation((TextureView) PlayerView.this.surfaceView, PlayerView.this.textureViewRotation);
            }
            PlayerView.this.contentFrame.setAspectRatio(f10);
        }
    }

    public PlayerView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int i9) {
        float width = textureView.getWidth();
        float height = textureView.getHeight();
        if (width == BitmapDescriptorFactory.HUE_RED || height == BitmapDescriptorFactory.HUE_RED || i9 == 0) {
            textureView.setTransform(null);
            return;
        }
        Matrix matrix = new Matrix();
        float f9 = width / 2.0f;
        float f10 = height / 2.0f;
        matrix.postRotate(i9, f9, f10);
        RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, width, height);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postScale(width / rectF2.width(), height / rectF2.height(), f9, f10);
        textureView.setTransform(matrix);
    }

    private static void configureEditModeLogo(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(C3429R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(C3429R.color.exo_edit_mode_background_color));
    }

    @TargetApi(23)
    private static void configureEditModeLogoV23(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(C3429R.drawable.exo_edit_mode_logo, null));
        imageView.setBackgroundColor(resources.getColor(C3429R.color.exo_edit_mode_background_color, null));
    }

    private void hideArtwork() {
        ImageView imageView = this.artworkView;
        if (imageView != null) {
            imageView.setImageResource(R.color.transparent);
            this.artworkView.setVisibility(4);
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean isDpadKey(int i9) {
        return i9 == 19 || i9 == 270 || i9 == 22 || i9 == 271 || i9 == 20 || i9 == 269 || i9 == 21 || i9 == 268 || i9 == 23;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingAd() {
        Player player = this.player;
        return player != null && player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeShowController(boolean z8) {
        if (!(isPlayingAd() && this.controllerHideDuringAds) && this.useController) {
            boolean z9 = this.controller.isVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean zShouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z8 || z9 || zShouldShowControllerIndefinitely) {
                showController(zShouldShowControllerIndefinitely);
            }
        }
    }

    private boolean setArtworkFromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                AspectRatioFrameLayout aspectRatioFrameLayout = this.contentFrame;
                if (aspectRatioFrameLayout != null) {
                    aspectRatioFrameLayout.setAspectRatio(width / height);
                }
                this.artworkView.setImageBitmap(bitmap);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        for (int i9 = 0; i9 < metadata.length(); i9++) {
            Metadata.Entry entry = metadata.get(i9);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return setArtworkFromBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        }
        return false;
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i9) {
        aspectRatioFrameLayout.setResizeMode(i9);
    }

    private boolean shouldShowControllerIndefinitely() {
        Player player = this.player;
        if (player == null) {
            return true;
        }
        int playbackState = player.getPlaybackState();
        return this.controllerAutoShow && (playbackState == 1 || playbackState == 4 || !this.player.getPlayWhenReady());
    }

    public static void switchTargetView(Player player, PlayerView playerView, PlayerView playerView2) {
        if (playerView == playerView2) {
            return;
        }
        if (playerView2 != null) {
            playerView2.setPlayer(player);
        }
        if (playerView != null) {
            playerView.setPlayer(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForCurrentTrackSelections() {
        Player player = this.player;
        if (player == null) {
            return;
        }
        TrackSelectionArray currentTrackSelections = player.getCurrentTrackSelections();
        for (int i9 = 0; i9 < currentTrackSelections.length; i9++) {
            if (this.player.getRendererType(i9) == 2 && currentTrackSelections.get(i9) != null) {
                hideArtwork();
                return;
            }
        }
        View view = this.shutterView;
        if (view != null) {
            view.setVisibility(0);
        }
        if (this.useArtwork) {
            for (int i10 = 0; i10 < currentTrackSelections.length; i10++) {
                TrackSelection trackSelection = currentTrackSelections.get(i10);
                if (trackSelection != null) {
                    for (int i11 = 0; i11 < trackSelection.length(); i11++) {
                        Metadata metadata = trackSelection.getFormat(i11).metadata;
                        if (metadata != null && setArtworkFromMetadata(metadata)) {
                            return;
                        }
                    }
                }
            }
            if (setArtworkFromBitmap(this.defaultArtwork)) {
                return;
            }
        }
        hideArtwork();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Player player = this.player;
        if (player != null && player.isPlayingAd()) {
            this.overlayFrameLayout.requestFocus();
            return super.dispatchKeyEvent(keyEvent);
        }
        boolean z8 = isDpadKey(keyEvent.getKeyCode()) && this.useController && !this.controller.isVisible();
        maybeShowController(true);
        return z8 || dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return this.useController && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public Bitmap getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public Player getPlayer() {
        return this.player;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public boolean getUseController() {
        return this.useController;
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public void hideController() {
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            playerControlView.hide();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null || motionEvent.getActionMasked() != 0) {
            return false;
        }
        if (!this.controller.isVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.controller != null);
        this.controller.setControlDispatcher(controlDispatcher);
    }

    public void setControllerAutoShow(boolean z8) {
        this.controllerAutoShow = z8;
    }

    public void setControllerHideDuringAds(boolean z8) {
        this.controllerHideDuringAds = z8;
    }

    public void setControllerHideOnTouch(boolean z8) {
        Assertions.checkState(this.controller != null);
        this.controllerHideOnTouch = z8;
    }

    public void setControllerShowTimeoutMs(int i9) {
        Assertions.checkState(this.controller != null);
        this.controllerShowTimeoutMs = i9;
        if (this.controller.isVisible()) {
            showController();
        }
    }

    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener visibilityListener) {
        Assertions.checkState(this.controller != null);
        this.controller.setVisibilityListener(visibilityListener);
    }

    public void setDefaultArtwork(Bitmap bitmap) {
        if (this.defaultArtwork != bitmap) {
            this.defaultArtwork = bitmap;
            updateForCurrentTrackSelections();
        }
    }

    public void setFastForwardIncrementMs(int i9) {
        Assertions.checkState(this.controller != null);
        this.controller.setFastForwardIncrementMs(i9);
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer) {
        Assertions.checkState(this.controller != null);
        this.controller.setPlaybackPreparer(playbackPreparer);
    }

    public void setPlayer(Player player) {
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.componentListener);
            Player.VideoComponent videoComponent = this.player.getVideoComponent();
            if (videoComponent != null) {
                videoComponent.removeVideoListener(this.componentListener);
                View view = this.surfaceView;
                if (view instanceof TextureView) {
                    videoComponent.clearVideoTextureView((TextureView) view);
                } else if (view instanceof SurfaceView) {
                    videoComponent.clearVideoSurfaceView((SurfaceView) view);
                }
            }
            Player.TextComponent textComponent = this.player.getTextComponent();
            if (textComponent != null) {
                textComponent.removeTextOutput(this.componentListener);
            }
        }
        this.player = player;
        if (this.useController) {
            this.controller.setPlayer(player);
        }
        View view2 = this.shutterView;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        SubtitleView subtitleView = this.subtitleView;
        if (subtitleView != null) {
            subtitleView.setCues(null);
        }
        if (player == null) {
            hideController();
            hideArtwork();
            return;
        }
        Player.VideoComponent videoComponent2 = player.getVideoComponent();
        if (videoComponent2 != null) {
            View view3 = this.surfaceView;
            if (view3 instanceof TextureView) {
                videoComponent2.setVideoTextureView((TextureView) view3);
            } else if (view3 instanceof SurfaceView) {
                videoComponent2.setVideoSurfaceView((SurfaceView) view3);
            }
            videoComponent2.addVideoListener(this.componentListener);
        }
        Player.TextComponent textComponent2 = player.getTextComponent();
        if (textComponent2 != null) {
            textComponent2.addTextOutput(this.componentListener);
        }
        player.addListener(this.componentListener);
        maybeShowController(false);
        updateForCurrentTrackSelections();
    }

    public void setRepeatToggleModes(int i9) {
        Assertions.checkState(this.controller != null);
        this.controller.setRepeatToggleModes(i9);
    }

    public void setResizeMode(int i9) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setResizeMode(i9);
    }

    public void setRewindIncrementMs(int i9) {
        Assertions.checkState(this.controller != null);
        this.controller.setRewindIncrementMs(i9);
    }

    public void setShowMultiWindowTimeBar(boolean z8) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowMultiWindowTimeBar(z8);
    }

    public void setShowShuffleButton(boolean z8) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowShuffleButton(z8);
    }

    public void setShutterBackgroundColor(int i9) {
        View view = this.shutterView;
        if (view != null) {
            view.setBackgroundColor(i9);
        }
    }

    public void setUseArtwork(boolean z8) {
        Assertions.checkState((z8 && this.artworkView == null) ? false : true);
        if (this.useArtwork != z8) {
            this.useArtwork = z8;
            updateForCurrentTrackSelections();
        }
    }

    public void setUseController(boolean z8) {
        Assertions.checkState((z8 && this.controller == null) ? false : true);
        if (this.useController == z8) {
            return;
        }
        this.useController = z8;
        if (z8) {
            this.controller.setPlayer(this.player);
            return;
        }
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            playerControlView.hide();
            this.controller.setPlayer(null);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        super.setVisibility(i9);
        View view = this.surfaceView;
        if (view instanceof SurfaceView) {
            view.setVisibility(i9);
        }
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void showController(boolean z8) {
        if (this.useController) {
            this.controller.setShowTimeoutMs(z8 ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlayerView(Context context, AttributeSet attributeSet, int i9) {
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        int i10;
        int i11;
        int color;
        boolean zHasValue;
        int resourceId;
        int i12;
        boolean z12;
        int i13;
        View surfaceView;
        super(context, attributeSet, i9);
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.artworkView = null;
            this.subtitleView = null;
            this.controller = null;
            this.componentListener = null;
            this.overlayFrameLayout = null;
            ImageView imageView = new ImageView(context);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(getResources(), imageView);
            } else {
                configureEditModeLogo(getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int i14 = C3429R.layout.exo_player_view;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C3429R.styleable.PlayerView, 0, 0);
            try {
                int i15 = C3429R.styleable.PlayerView_shutter_background_color;
                zHasValue = typedArrayObtainStyledAttributes.hasValue(i15);
                color = typedArrayObtainStyledAttributes.getColor(i15, 0);
                int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(C3429R.styleable.PlayerView_player_layout_id, i14);
                z10 = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerView_use_artwork, true);
                resourceId = typedArrayObtainStyledAttributes.getResourceId(C3429R.styleable.PlayerView_default_artwork, 0);
                z11 = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerView_use_controller, true);
                i10 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerView_surface_type, 1);
                i12 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerView_resize_mode, 0);
                int i16 = typedArrayObtainStyledAttributes.getInt(C3429R.styleable.PlayerView_show_timeout, 5000);
                boolean z13 = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerView_hide_on_touch, true);
                boolean z14 = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerView_auto_show, true);
                boolean z15 = typedArrayObtainStyledAttributes.getBoolean(C3429R.styleable.PlayerView_hide_during_ads, true);
                typedArrayObtainStyledAttributes.recycle();
                i11 = i16;
                z12 = z13;
                z9 = z14;
                z8 = z15;
                i14 = resourceId2;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z8 = true;
            z9 = true;
            z10 = true;
            z11 = true;
            i10 = 1;
            i11 = 5000;
            color = 0;
            zHasValue = false;
            resourceId = 0;
            i12 = 0;
            z12 = true;
        }
        LayoutInflater.from(context).inflate(i14, this);
        this.componentListener = new ComponentListener();
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(C3429R.id.exo_content_frame);
        this.contentFrame = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            setResizeModeRaw(aspectRatioFrameLayout, i12);
        }
        View viewFindViewById = findViewById(C3429R.id.exo_shutter);
        this.shutterView = viewFindViewById;
        if (viewFindViewById != null && zHasValue) {
            viewFindViewById.setBackgroundColor(color);
        }
        if (aspectRatioFrameLayout != null && i10 != 0) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (i10 == 2) {
                surfaceView = new TextureView(context);
            } else {
                surfaceView = new SurfaceView(context);
            }
            this.surfaceView = surfaceView;
            surfaceView.setLayoutParams(layoutParams);
            aspectRatioFrameLayout.addView(surfaceView, 0);
        } else {
            this.surfaceView = null;
        }
        this.overlayFrameLayout = (FrameLayout) findViewById(C3429R.id.exo_overlay);
        ImageView imageView2 = (ImageView) findViewById(C3429R.id.exo_artwork);
        this.artworkView = imageView2;
        this.useArtwork = z10 && imageView2 != null;
        if (resourceId != 0) {
            this.defaultArtwork = BitmapFactory.decodeResource(context.getResources(), resourceId);
        }
        SubtitleView subtitleView = (SubtitleView) findViewById(C3429R.id.exo_subtitles);
        this.subtitleView = subtitleView;
        if (subtitleView != null) {
            subtitleView.setUserDefaultStyle();
            subtitleView.setUserDefaultTextSize();
        }
        PlayerControlView playerControlView = (PlayerControlView) findViewById(C3429R.id.exo_controller);
        View viewFindViewById2 = findViewById(C3429R.id.exo_controller_placeholder);
        if (playerControlView != null) {
            this.controller = playerControlView;
            i13 = 0;
        } else if (viewFindViewById2 != null) {
            i13 = 0;
            PlayerControlView playerControlView2 = new PlayerControlView(context, null, 0, attributeSet);
            this.controller = playerControlView2;
            playerControlView2.setLayoutParams(viewFindViewById2.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) viewFindViewById2.getParent();
            int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById2);
            viewGroup.removeView(viewFindViewById2);
            viewGroup.addView(playerControlView2, iIndexOfChild);
        } else {
            i13 = 0;
            this.controller = null;
        }
        PlayerControlView playerControlView3 = this.controller;
        this.controllerShowTimeoutMs = playerControlView3 == null ? i13 : i11;
        this.controllerHideOnTouch = z12;
        this.controllerAutoShow = z9;
        this.controllerHideDuringAds = z8;
        this.useController = (!z11 || playerControlView3 == null) ? i13 : 1;
        hideController();
    }
}
