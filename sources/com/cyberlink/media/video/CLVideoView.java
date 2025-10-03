package com.cyberlink.media.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.cyberlink.media.video.SurfaceHolderC1237a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import p104j2.AbstractC5091b;

/* loaded from: classes.dex */
public class CLVideoView extends ViewGroup {

    /* renamed from: u */
    public static final ViewGroup.LayoutParams f5901u = new ViewGroup.LayoutParams(-1, -1);

    /* renamed from: b */
    public final ViewGroup.MarginLayoutParams f5902b;

    /* renamed from: c */
    public final ViewGroup.MarginLayoutParams f5903c;

    /* renamed from: d */
    public RenderMode f5904d;

    /* renamed from: e */
    public boolean f5905e;

    /* renamed from: f */
    public View f5906f;

    /* renamed from: g */
    public SurfaceHolder f5907g;

    /* renamed from: h */
    public InterfaceC1241e f5908h;

    /* renamed from: i */
    public VideoOverlayView f5909i;

    /* renamed from: j */
    public int f5910j;

    /* renamed from: k */
    public int f5911k;

    /* renamed from: l */
    public double f5912l;

    /* renamed from: m */
    public int f5913m;

    /* renamed from: n */
    public final Rect f5914n;

    /* renamed from: o */
    public final Rect f5915o;

    /* renamed from: p */
    public Rect f5916p;

    /* renamed from: q */
    public Rect f5917q;

    /* renamed from: r */
    public boolean f5918r;

    /* renamed from: s */
    public final C1221c f5919s;

    /* renamed from: t */
    public final SurfaceHolderCallbackC1220b f5920t;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'b' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class RenderMode {

        /* renamed from: b */
        public static final RenderMode f5921b;

        /* renamed from: c */
        public static final RenderMode f5922c;

        /* renamed from: d */
        public static final RenderMode f5923d;

        /* renamed from: e */
        public static final RenderMode f5924e;

        /* renamed from: f */
        public static final RenderMode f5925f;

        /* renamed from: g */
        public static final /* synthetic */ RenderMode[] f5926g;
        private final Set<Flag> mFlags;

        public enum Flag {
            OPENGL,
            NEED_SURFACE,
            NEED_TOP_MOST
        }

        static {
            Flag flag = Flag.NEED_SURFACE;
            RenderMode renderMode = new RenderMode("NATIVE", 0, EnumSet.of(flag));
            f5921b = renderMode;
            Flag flag2 = Flag.OPENGL;
            RenderMode renderMode2 = new RenderMode("GLES20", 1, EnumSet.of(flag2));
            f5922c = renderMode2;
            RenderMode renderMode3 = new RenderMode("EGL_IMAGE_EXTERNAL", 2, EnumSet.of(flag2, flag));
            f5923d = renderMode3;
            Flag flag3 = Flag.NEED_TOP_MOST;
            RenderMode renderMode4 = new RenderMode("TEXTURE_VIEW", 3, EnumSet.of(flag, flag3));
            f5924e = renderMode4;
            RenderMode renderMode5 = new RenderMode("RAW_TEXTURE_VIEW", 4, EnumSet.of(flag, flag3));
            f5925f = renderMode5;
            f5926g = new RenderMode[]{renderMode, renderMode2, renderMode3, renderMode4, renderMode5};
        }

        public RenderMode(String str, int i9, Set set) {
            this.mFlags = Collections.unmodifiableSet(set);
        }

        public static RenderMode valueOf(String str) {
            return (RenderMode) Enum.valueOf(RenderMode.class, str);
        }

        public static RenderMode[] values() {
            return (RenderMode[]) f5926g.clone();
        }

        /* renamed from: a */
        public boolean m5399a() {
            return this.mFlags.contains(Flag.NEED_TOP_MOST);
        }
    }

    /* renamed from: com.cyberlink.media.video.CLVideoView$a */
    public static /* synthetic */ class C1219a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f5931a;

        static {
            int[] iArr = new int[RenderMode.values().length];
            f5931a = iArr;
            try {
                iArr[RenderMode.f5921b.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5931a[RenderMode.f5922c.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5931a[RenderMode.f5923d.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5931a[RenderMode.f5924e.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5931a[RenderMode.f5925f.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: com.cyberlink.media.video.CLVideoView$b */
    public class SurfaceHolderCallbackC1220b implements SurfaceHolder.Callback {
        public SurfaceHolderCallbackC1220b() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i9, int i10, int i11) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            CLVideoView.this.f5918r = true;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CLVideoView.this.f5918r = false;
        }

        public /* synthetic */ SurfaceHolderCallbackC1220b(CLVideoView cLVideoView, C1219a c1219a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.media.video.CLVideoView$c */
    public class C1221c extends AbstractC5091b {
        public C1221c() {
        }

        public /* synthetic */ C1221c(CLVideoView cLVideoView, C1219a c1219a) {
            this();
        }
    }

    public CLVideoView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f5902b = new ViewGroup.MarginLayoutParams(-1, -1);
        this.f5903c = new ViewGroup.MarginLayoutParams(-1, -1);
        this.f5912l = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f5913m = 17;
        this.f5914n = new Rect();
        this.f5915o = new Rect();
        C1219a c1219a = null;
        this.f5919s = new C1221c(this, c1219a);
        this.f5920t = new SurfaceHolderCallbackC1220b(this, c1219a);
        m5393d();
    }

    /* renamed from: h */
    public static void m5389h(View view, Rect rect) {
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* renamed from: j */
    public static void m5390j(ViewGroup.MarginLayoutParams marginLayoutParams, Rect rect, Rect rect2) {
        marginLayoutParams.leftMargin = rect2.left - rect.left;
        marginLayoutParams.topMargin = rect2.top - rect.top;
        marginLayoutParams.rightMargin = rect.right - rect2.right;
        marginLayoutParams.bottomMargin = rect.bottom - rect2.bottom;
    }

    /* renamed from: b */
    public final void m5391b() {
        InterfaceC1241e interfaceC1241e = this.f5908h;
        if (interfaceC1241e != null) {
            interfaceC1241e.release();
            this.f5908h = null;
        }
        View view = this.f5906f;
        if (view != null) {
            removeViewInLayout(view);
            this.f5906f = null;
        }
        this.f5907g = null;
    }

    /* renamed from: c */
    public final void m5392c() {
        this.f5909i = null;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            try {
                this.f5909i = (VideoOverlayView) getChildAt(i9);
                Log.d("CLVideoView", "Found VideoOverlayView");
                return;
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: d */
    public final void m5393d() {
        setRenderMode(RenderMode.f5921b);
    }

    /* renamed from: e */
    public final void m5394e() {
        this.f5907g.setType(3);
    }

    @TargetApi(14)
    /* renamed from: f */
    public final void m5395f(boolean z8) {
        TextureView textureView = new TextureView(getContext());
        this.f5907g = z8 ? new SurfaceHolderC1237a.b(textureView) : null;
        this.f5906f = textureView;
    }

    /* renamed from: g */
    public boolean m5396g() {
        VideoOverlayView videoOverlayView;
        return this.f5905e && ((videoOverlayView = this.f5909i) == null || !videoOverlayView.willNotDraw());
    }

    public View getContentView() {
        return this.f5906f;
    }

    public int getGravity() {
        return this.f5913m;
    }

    public C1240d getOverlayStyle() {
        return getOverlayView().getStyle();
    }

    public VideoOverlayView getOverlayView() {
        return this.f5909i;
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.f5907g;
    }

    public InterfaceC1241e getVideoRenderer() {
        return this.f5908h;
    }

    /* renamed from: i */
    public final void m5397i(View view, int i9, int i10, ViewGroup.MarginLayoutParams marginLayoutParams, Rect rect, Rect rect2) {
        m5390j(marginLayoutParams, rect, rect2);
        view.setLayoutParams(marginLayoutParams);
        measureChildWithMargins(view, i9, 0, i10, 0);
    }

    /* renamed from: k */
    public final void m5398k(int i9, int i10) {
        if (this.f5906f == null) {
            return;
        }
        this.f5914n.left = getPaddingLeft();
        this.f5914n.right = i9 - getPaddingRight();
        int iWidth = this.f5914n.width();
        this.f5914n.top = getPaddingTop();
        this.f5914n.bottom = i10 - getPaddingBottom();
        int iHeight = this.f5914n.height();
        if (iWidth <= 0 || iHeight <= 0) {
            return;
        }
        double d9 = this.f5912l;
        if (d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || (d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && (this.f5910j <= 0 || this.f5911k <= 0))) {
            this.f5916p = this.f5914n;
        } else {
            if (d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d9 = this.f5910j / this.f5911k;
            }
            double d10 = iWidth;
            double d11 = iHeight;
            if (d9 > d10 / d11) {
                iHeight = (int) (d10 / d9);
            } else {
                iWidth = (int) (d11 * d9);
            }
            Gravity.apply(this.f5913m, iWidth, iHeight, this.f5914n, this.f5915o);
            this.f5916p = this.f5915o;
        }
        m5390j(this.f5902b, this.f5914n, this.f5916p);
        Rect rect = m5396g() ? this.f5914n : this.f5916p;
        this.f5917q = rect;
        m5390j(this.f5903c, this.f5914n, rect);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        m5392c();
        if (this.f5909i == null) {
            setOverlayView(new VideoOverlayView(getContext()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        View view = this.f5906f;
        if (view == null) {
            return;
        }
        m5389h(view, this.f5916p);
        VideoOverlayView videoOverlayView = this.f5909i;
        if (videoOverlayView != null) {
            m5389h(videoOverlayView, this.f5917q);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        m5398k(getMeasuredWidth(), getMeasuredHeight());
        View view = this.f5906f;
        if (view != null) {
            m5397i(view, i9, i10, this.f5902b, this.f5914n, this.f5916p);
        }
        VideoOverlayView videoOverlayView = this.f5909i;
        if (videoOverlayView != null) {
            m5397i(videoOverlayView, i9, i10, this.f5903c, this.f5914n, this.f5917q);
        }
    }

    public void setDisplayAspectRatio(double d9) {
        this.f5912l = d9;
        requestLayout();
    }

    public void setGravity(int i9) {
        this.f5913m = i9;
        requestLayout();
    }

    public void setOverlayStretched(boolean z8) {
        this.f5905e = z8;
        requestLayout();
    }

    public void setOverlayStyle(C1240d c1240d) {
        getOverlayView().setStyle(c1240d);
    }

    public void setOverlayView(VideoOverlayView videoOverlayView) {
        VideoOverlayView videoOverlayView2 = this.f5909i;
        if (videoOverlayView == videoOverlayView2) {
            return;
        }
        if (videoOverlayView2 != null) {
            removeViewInLayout(videoOverlayView2);
        }
        this.f5909i = videoOverlayView;
        if (videoOverlayView != null) {
            addViewInLayout(videoOverlayView, -1, this.f5903c);
        }
        requestLayout();
    }

    public void setRenderMode(RenderMode renderMode) {
        if (renderMode == null) {
            throw new NullPointerException("RenderMode must not be null");
        }
        if (renderMode == this.f5904d) {
            return;
        }
        this.f5904d = renderMode;
        m5391b();
        int i9 = C1219a.f5931a[this.f5904d.ordinal()];
        if (i9 == 1) {
            SurfaceView surfaceView = new SurfaceView(getContext());
            this.f5907g = surfaceView.getHolder();
            m5394e();
            this.f5907g.addCallback(this.f5920t);
            this.f5906f = surfaceView;
        } else if (i9 == 2 || i9 == 3) {
            C1238b c1238b = new C1238b(getContext());
            AbstractC1242f abstractC1242fM5487d = AbstractC1242f.m5487d(c1238b, this.f5904d);
            this.f5908h = abstractC1242fM5487d;
            this.f5907g = abstractC1242fM5487d.mo5491f();
            this.f5906f = c1238b;
        } else if (i9 == 4) {
            m5395f(true);
        } else if (i9 == 5) {
            m5395f(false);
        }
        VideoOverlayView videoOverlayView = this.f5909i;
        if (videoOverlayView != null) {
            videoOverlayView.setVisibility(this.f5904d.m5399a() ? 8 : 0);
        }
        addViewInLayout(this.f5906f, 0, f5901u);
        requestLayout();
    }
}
