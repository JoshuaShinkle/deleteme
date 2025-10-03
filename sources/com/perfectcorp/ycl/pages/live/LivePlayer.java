package com.perfectcorp.ycl.pages.live;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.p038ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.p038ui.DebugTextViewHelper;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.lang.ref.WeakReference;
import p047d5.C4680d;

/* loaded from: classes2.dex */
public class LivePlayer extends Player.DefaultEventListener implements TextureView.SurfaceTextureListener, InterfaceC4605y, VideoListener, MediaSourceEventListener, ExtractorMediaSource.EventListener {

    /* renamed from: A */
    public static final InterfaceC4580c f16039A = new C4578a();

    /* renamed from: B */
    public static final DefaultBandwidthMeter f16040B = new DefaultBandwidthMeter();

    /* renamed from: b */
    public final WeakReference<Context> f16041b;

    /* renamed from: c */
    public C4584d f16042c;

    /* renamed from: d */
    public final TextureView f16043d;

    /* renamed from: e */
    public Surface f16044e;

    /* renamed from: f */
    public final AspectRatioFrameLayout f16045f;

    /* renamed from: g */
    public int f16046g;

    /* renamed from: h */
    public InterfaceC4580c f16047h;

    /* renamed from: i */
    public int f16048i;

    /* renamed from: j */
    public int f16049j;

    /* renamed from: k */
    public View f16050k;

    /* renamed from: l */
    public TextView f16051l;

    /* renamed from: m */
    public TextView f16052m;

    /* renamed from: n */
    public TextView f16053n;

    /* renamed from: o */
    public DebugTextViewHelper f16054o;

    /* renamed from: p */
    public final Handler f16055p;

    /* renamed from: q */
    public final DataSource.Factory f16056q;

    /* renamed from: r */
    public Uri f16057r;

    /* renamed from: s */
    public SimpleExoPlayer f16058s;

    /* renamed from: t */
    public C4599s f16059t;

    /* renamed from: u */
    public Timeline.Window f16060u;

    /* renamed from: v */
    public MappingTrackSelector f16061v;

    /* renamed from: w */
    public boolean f16062w;

    /* renamed from: x */
    public boolean f16063x;

    /* renamed from: y */
    public int f16064y;

    /* renamed from: z */
    public long f16065z;

    public enum Rotation {
        ROTATE_0(BitmapDescriptorFactory.HUE_RED),
        ROTATE_90(90.0f),
        ROTATE_180(180.0f),
        ROTATE_270(270.0f);

        final float degree;

        Rotation(float f9) {
            this.degree = f9;
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.LivePlayer$a */
    public class C4578a implements InterfaceC4580c {
        @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
        /* renamed from: D0 */
        public void mo13663D0(IOException iOException, int i9, LivePlayer livePlayer) {
        }

        @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
        /* renamed from: c0 */
        public void mo13694c0(LivePlayer livePlayer) {
        }

        @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
        /* renamed from: n */
        public void mo13717n(int i9, LivePlayer livePlayer) {
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.LivePlayer$b */
    public static /* synthetic */ class C4579b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f16071a;

        static {
            int[] iArr = new int[Rotation.values().length];
            f16071a = iArr;
            try {
                iArr[Rotation.ROTATE_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16071a[Rotation.ROTATE_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16071a[Rotation.ROTATE_270.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.pages.live.LivePlayer$c */
    public interface InterfaceC4580c {
        /* renamed from: D0 */
        void mo13663D0(IOException iOException, int i9, LivePlayer livePlayer);

        /* renamed from: c0 */
        void mo13694c0(LivePlayer livePlayer);

        /* renamed from: n */
        void mo13717n(int i9, LivePlayer livePlayer);
    }

    public LivePlayer(Context context, AspectRatioFrameLayout aspectRatioFrameLayout, TextureView textureView, InterfaceC4580c interfaceC4580c) {
        this.f16041b = new WeakReference<>(context);
        this.f16045f = aspectRatioFrameLayout;
        this.f16043d = textureView;
        textureView.setSurfaceTextureListener(this);
        this.f16047h = interfaceC4580c;
        this.f16060u = new Timeline.Window();
        this.f16055p = new Handler(Looper.getMainLooper());
        this.f16056q = new DefaultDataSourceFactory(context, "LivePlayer");
    }

    /* renamed from: g */
    public static LivePlayer m18167g(Context context, AspectRatioFrameLayout aspectRatioFrameLayout, TextureView textureView, InterfaceC4580c interfaceC4580c) {
        if (interfaceC4580c == null) {
            interfaceC4580c = f16039A;
        }
        return new LivePlayer(context, aspectRatioFrameLayout, textureView, interfaceC4580c);
    }

    /* renamed from: z */
    public static String m18168z(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "unknown" : "ended" : "ready" : "buffering" : "idle";
    }

    /* renamed from: A */
    public void m18169A() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVolume(1.0f);
        }
    }

    @Override // com.perfectcorp.ycl.pages.live.InterfaceC4605y
    /* renamed from: a */
    public Format mo18170a(int i9, int i10) {
        return null;
    }

    @Override // com.perfectcorp.ycl.pages.live.InterfaceC4605y
    /* renamed from: b */
    public int mo18171b(int i9) {
        return this.f16061v.getCurrentMappedTrackInfo().length;
    }

    @Override // com.perfectcorp.ycl.pages.live.InterfaceC4605y
    /* renamed from: c */
    public int mo18172c(int i9) {
        return -1;
    }

    /* renamed from: d */
    public void m18173d(boolean z8) {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(z8);
        }
    }

    /* renamed from: e */
    public void m18174e(long j9) {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.seekTo(j9);
        }
    }

    /* renamed from: f */
    public final MediaSource m18175f(Uri uri, String str) {
        String lastPathSegment;
        if (TextUtils.isEmpty(str)) {
            lastPathSegment = uri.getLastPathSegment();
        } else {
            lastPathSegment = "." + str;
        }
        int iInferContentType = Util.inferContentType(lastPathSegment);
        if (iInferContentType == 2) {
            return new HlsMediaSource.Factory(this.f16056q).setAllowChunklessPreparation(true).createMediaSource(uri, this.f16055p, (MediaSourceEventListener) this);
        }
        if (iInferContentType == 3) {
            return new ExtractorMediaSource.Factory(this.f16056q).createMediaSource(uri, this.f16055p, (MediaSourceEventListener) this);
        }
        throw new IllegalStateException("Unsupported type: " + iInferContentType);
    }

    /* renamed from: h */
    public long m18176h() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getBufferedPosition();
        }
        return 0L;
    }

    /* renamed from: i */
    public long m18177i() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getCurrentPosition();
        }
        return 0L;
    }

    /* renamed from: j */
    public long m18178j() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getDuration();
        }
        return 0L;
    }

    /* renamed from: k */
    public final Rotation m18179k() {
        return Rotation.ROTATE_0;
    }

    /* renamed from: l */
    public boolean m18180l() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getPlayWhenReady();
        }
        return false;
    }

    @Deprecated
    /* renamed from: m */
    public boolean m18181m(int i9) {
        return false;
    }

    /* renamed from: n */
    public void m18182n() {
        this.f16045f.setVisibility(4);
        m18184p();
    }

    /* renamed from: o */
    public boolean m18183o() {
        return this.f16058s != null;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int i9, Format format, int i10, Object obj, long j9) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onDownstreamFormatChanged(i9, format, i10, obj, j9);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCanceled(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onLoadCanceled(dataSpec, i9, i10, format, i11, obj, j9, j10, j11, j12, j13);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCompleted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onLoadCompleted(dataSpec, i9, i10, format, i11, obj, j9, j10, j11, j12, j13);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadError(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13, IOException iOException, boolean z8) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onLoadError(dataSpec, i9, i10, format, i11, obj, j9, j10, j11, j12, j13, iOException, z8);
        }
        int i12 = this.f16046g + 1;
        this.f16046g = i12;
        InterfaceC4580c interfaceC4580c = this.f16047h;
        if (interfaceC4580c != null) {
            interfaceC4580c.mo13663D0(iOException, i12, this);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onLoadStarted(dataSpec, i9, i10, format, i11, obj, j9, j10, j11);
        }
    }

    @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        this.f16062w = true;
    }

    @Override // com.google.android.exoplayer2.Player.DefaultEventListener, com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z8, int i9) {
        C4599s c4599s;
        InterfaceC4580c interfaceC4580c;
        String str = ("playWhenReady=" + z8 + ", playbackState=") + m18168z(i9);
        if (i9 == 3) {
            this.f16046g = 0;
        }
        this.f16053n.setText(str);
        InterfaceC4580c interfaceC4580c2 = this.f16047h;
        if (interfaceC4580c2 != null) {
            interfaceC4580c2.mo13717n(i9, this);
        }
        if (i9 != 2 || (c4599s = this.f16059t) == null || !c4599s.m18337b(this.f16058s.getPlayWhenReady()) || (interfaceC4580c = this.f16047h) == null) {
            return;
        }
        interfaceC4580c.mo13694c0(this);
    }

    @Override // com.google.android.exoplayer2.video.VideoListener
    public void onRenderedFirstFrame() {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i9, int i10) {
        Surface surface = new Surface(surfaceTexture);
        this.f16044e = surface;
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVideoSurface(surface);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.clearVideoSurface();
        }
        this.f16044e = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i9, int i10) {
        Rotation rotationM18179k = m18179k();
        if (rotationM18179k != Rotation.ROTATE_0) {
            m18189u(rotationM18179k, i9, i10);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0019  */
    @Override // com.google.android.exoplayer2.Player.DefaultEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onTimelineChanged(Timeline timeline, Object obj) {
        boolean z8;
        if (timeline == null || timeline.getWindowCount() <= 0) {
            z8 = false;
        } else {
            z8 = true;
            if (timeline.getWindow(timeline.getWindowCount() - 1, this.f16060u).isDynamic) {
            }
        }
        this.f16063x = z8;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int i9, long j9, long j10) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onUpstreamDiscarded(i9, j9, j10);
        }
    }

    @Override // com.google.android.exoplayer2.video.VideoListener
    public void onVideoSizeChanged(int i9, int i10, int i11, float f9) {
        this.f16048i = i9;
        this.f16049j = i10;
        Rotation rotationM18179k = m18179k();
        if (rotationM18179k != Rotation.ROTATE_0) {
            m18189u(rotationM18179k, this.f16045f.getMeasuredWidth(), this.f16045f.getMeasuredHeight());
        } else {
            this.f16045f.setAspectRatio(i10 == 0 ? 1.0f : (i9 * f9) / i10);
        }
    }

    /* renamed from: p */
    public void m18184p() {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVolume(BitmapDescriptorFactory.HUE_RED);
        }
    }

    /* renamed from: q */
    public void m18185q(Uri uri, long j9) {
        if (this.f16058s == null) {
            this.f16061v = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(f16040B));
            this.f16042c = new C4584d(this.f16061v);
            this.f16059t = new C4599s(j9);
            this.f16058s = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this.f16041b.get()), this.f16061v, this.f16059t);
            if (this.f16063x && uri.equals(this.f16057r)) {
                long j10 = this.f16065z;
                if (j10 == C3322C.TIME_UNSET) {
                    this.f16058s.seekToDefaultPosition(this.f16064y);
                } else {
                    this.f16058s.seekTo(this.f16064y, j10);
                }
            }
            this.f16057r = uri;
            this.f16058s.addListener(this);
            this.f16058s.addListener(this.f16042c);
            this.f16058s.addAudioDebugListener(this.f16042c);
            this.f16058s.addVideoDebugListener(this.f16042c);
            this.f16058s.addVideoListener(this);
            this.f16058s.setPlayWhenReady(true);
            DebugTextViewHelper debugTextViewHelper = new DebugTextViewHelper(this.f16058s, this.f16052m);
            this.f16054o = debugTextViewHelper;
            debugTextViewHelper.start();
            this.f16062w = true;
        }
        if (this.f16062w) {
            MediaSource mediaSourceM18175f = m18175f(uri, "");
            SimpleExoPlayer simpleExoPlayer = this.f16058s;
            boolean z8 = this.f16063x;
            simpleExoPlayer.prepare(mediaSourceM18175f, !z8, !z8);
            this.f16062w = false;
        }
        this.f16058s.setVideoSurface(this.f16044e);
    }

    /* renamed from: r */
    public void m18186r(boolean z8) {
        if (this.f16058s != null) {
            this.f16054o.stop();
            this.f16054o = null;
            this.f16064y = this.f16058s.getCurrentWindowIndex();
            this.f16065z = C3322C.TIME_UNSET;
            if (z8) {
                Timeline currentTimeline = this.f16058s.getCurrentTimeline();
                if (currentTimeline != null && currentTimeline != Timeline.EMPTY && currentTimeline.getWindow(this.f16064y, this.f16060u).isSeekable) {
                    this.f16065z = this.f16058s.getCurrentPosition();
                }
            } else {
                this.f16063x = false;
            }
            this.f16058s.release();
            this.f16058s = null;
            this.f16059t = null;
            this.f16061v = null;
            this.f16042c = null;
            this.f16046g = 0;
        }
    }

    /* renamed from: s */
    public void m18187s(boolean z8) {
        if (z8) {
            this.f16061v.setRendererDisabled(2, true);
        } else {
            this.f16061v.setRendererDisabled(2, false);
        }
    }

    /* renamed from: t */
    public void m18188t(float f9) {
        SimpleExoPlayer simpleExoPlayer = this.f16058s;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlaybackParameters(new PlaybackParameters(f9, 1.0f));
        }
    }

    /* renamed from: u */
    public final void m18189u(Rotation rotation, int i9, int i10) {
        Matrix matrix = new Matrix();
        float f9 = i9;
        float f10 = f9 / 2.0f;
        float f11 = i10;
        float f12 = f11 / 2.0f;
        int i11 = C4579b.f16071a[rotation.ordinal()];
        if (i11 == 2 || i11 == 3) {
            matrix.postRotate(rotation.degree, f10, f12);
            matrix.postScale(1.0f / (f11 / f9), this.f16048i / this.f16049j, f10, f12);
            this.f16043d.setTransform(matrix);
        }
    }

    @Deprecated
    /* renamed from: v */
    public void m18190v(int i9, int i10) {
    }

    /* renamed from: w */
    public void m18191w(View view) {
        this.f16050k = view;
        this.f16051l = (TextView) view.findViewById(C4680d.uri_text_view);
        this.f16052m = (TextView) view.findViewById(C4680d.debug_text_view);
        this.f16053n = (TextView) view.findViewById(C4680d.player_state_view);
    }

    /* renamed from: x */
    public void m18192x(boolean z8) {
        if (z8) {
            this.f16050k.setVisibility(0);
            this.f16052m.setVisibility(0);
            this.f16053n.setVisibility(0);
        } else {
            this.f16050k.setVisibility(8);
            this.f16052m.setVisibility(8);
            this.f16053n.setVisibility(8);
        }
    }

    /* renamed from: y */
    public void m18193y() {
        this.f16045f.bringToFront();
        this.f16045f.setVisibility(0);
        m18169A();
    }

    @Override // com.google.android.exoplayer2.source.ExtractorMediaSource.EventListener
    public void onLoadError(IOException iOException) {
        C4584d c4584d = this.f16042c;
        if (c4584d != null) {
            c4584d.onLoadError(iOException);
        }
        int i9 = this.f16046g + 1;
        this.f16046g = i9;
        this.f16047h.mo13663D0(iOException, i9, this);
    }
}
