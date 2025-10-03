package com.cyberlink.media.video;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.cyberlink.media.video.C1238b;
import com.cyberlink.media.video.CLVideoView;
import com.cyberlink.media.video.FrameRendererGLES20;
import com.cyberlink.media.video.SurfaceHolderC1237a;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import p114k2.C5121h;
import p114k2.InterfaceC5114a;
import p125l2.C5280d;

@TargetApi(16)
/* renamed from: com.cyberlink.media.video.f */
/* loaded from: classes.dex */
public abstract class AbstractC1242f implements InterfaceC1241e, C1238b.b {

    /* renamed from: b */
    public final C5280d f6038b;

    /* renamed from: c */
    public final Object f6039c;

    /* renamed from: d */
    public final InterfaceC5114a f6040d;

    /* renamed from: e */
    public volatile Thread f6041e;

    /* renamed from: f */
    public FrameRendererGLES20 f6042f;

    /* renamed from: g */
    public int f6043g;

    /* renamed from: h */
    public int f6044h;

    /* renamed from: i */
    public int f6045i;

    /* renamed from: j */
    public int f6046j;

    /* renamed from: k */
    public int f6047k;

    /* renamed from: l */
    public boolean f6048l;

    /* renamed from: m */
    public FrameRendererGLES20.InterfaceC1225d<Bitmap> f6049m;

    /* renamed from: n */
    public Bitmap f6050n;

    /* renamed from: o */
    public boolean f6051o;

    /* renamed from: p */
    public volatile boolean f6052p;

    /* renamed from: q */
    public volatile boolean f6053q;

    /* renamed from: r */
    public volatile boolean f6054r;

    /* renamed from: com.cyberlink.media.video.f$a */
    public static class a extends AbstractC1242f {

        /* renamed from: com.cyberlink.media.video.f$a$a, reason: collision with other inner class name */
        public class RunnableC6843a implements Runnable {
            public RunnableC6843a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.m5495j(false);
            }
        }

        public a(InterfaceC5114a interfaceC5114a) {
            super(interfaceC5114a);
        }

        /* renamed from: b */
        private void m5496b() {
            m5498l();
            this.f6040d.mo19971b(new RunnableC6843a());
        }

        @Override // com.cyberlink.media.video.C1238b.b
        /* renamed from: a */
        public void mo5459a() {
        }

        @Override // com.cyberlink.media.video.AbstractC1242f
        /* renamed from: h */
        public void mo5493h() {
            if (this.f6042f == null) {
                return;
            }
            synchronized (this.f6039c) {
            }
        }

        /* renamed from: k */
        public final void m5497k(boolean z8) {
            m5495j(z8);
            try {
                this.f6042f = m5490c(FrameRendererGLES20.m5407h(this.f6045i, this.f6046j, this.f6047k)).m5436j();
                this.f6040d.mo19970a();
            } catch (Throwable th) {
                Log.e("VideoRendererGLES20.Renderer2D", "new FrameRendererGLES20", th);
            }
        }

        /* renamed from: l */
        public final void m5498l() {
            synchronized (this.f6039c) {
            }
        }

        @Override // com.cyberlink.media.video.AbstractC1242f, android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            super.onSurfaceCreated(gl10, eGLConfig);
            if (this.f6042f != null) {
                m5497k(true);
            }
        }

        @Override // com.cyberlink.media.video.AbstractC1242f, com.cyberlink.media.video.InterfaceC1241e
        public void release() {
            m5496b();
            super.release();
        }
    }

    /* renamed from: com.cyberlink.media.video.f$b */
    public static class b extends AbstractC1242f implements SurfaceTexture.OnFrameAvailableListener {

        /* renamed from: s */
        public final SurfaceHolderC1237a f6056s;

        /* renamed from: t */
        public SurfaceTexture f6057t;

        /* renamed from: u */
        public Surface f6058u;

        /* renamed from: v */
        public final Runnable f6059v;

        /* renamed from: w */
        public final Runnable f6060w;

        /* renamed from: com.cyberlink.media.video.f$b$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                if (bVar.f6042f == null || bVar.f6057t == null) {
                    return;
                }
                b.this.f6057t.updateTexImage();
                b.this.m5494i();
            }
        }

        /* renamed from: com.cyberlink.media.video.f$b$b, reason: collision with other inner class name */
        public class RunnableC6844b implements Runnable {
            public RunnableC6844b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f6057t != null) {
                    b.this.f6057t.release();
                    b.this.f6057t = null;
                    b.this.f6056s.m5456d(null);
                }
                b.this.m5495j(false);
            }
        }

        public b(InterfaceC5114a interfaceC5114a) {
            super(interfaceC5114a);
            this.f6059v = new a();
            this.f6060w = new RunnableC6844b();
            this.f6056s = interfaceC5114a instanceof C5121h ? new SurfaceHolderC1237a.a(((C5121h) interfaceC5114a).m19982c()) : new SurfaceHolderC1237a();
        }

        @Override // com.cyberlink.media.video.C1238b.b
        /* renamed from: a */
        public void mo5459a() {
            if (this.f6057t != null) {
                Log.d("VideoRendererGLES20.RendererOES", "SurfaceTexture.detachFromGLContext");
                this.f6057t.detachFromGLContext();
            }
            Surface surface = this.f6058u;
            if (surface != null) {
                surface.release();
                this.f6058u = null;
            }
            m5495j(true);
        }

        @Override // com.cyberlink.media.video.AbstractC1242f
        /* renamed from: f */
        public SurfaceHolder mo5491f() {
            return this.f6056s;
        }

        @Override // com.cyberlink.media.video.AbstractC1242f
        /* renamed from: h */
        public void mo5493h() throws Throwable {
            FrameRendererGLES20 frameRendererGLES20 = this.f6042f;
            if (frameRendererGLES20 == null || this.f6057t == null) {
                return;
            }
            frameRendererGLES20.m5419r(null);
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.f6040d.mo19971b(this.f6059v);
        }

        @Override // com.cyberlink.media.video.AbstractC1242f, android.opengl.GLSurfaceView.Renderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            super.onSurfaceCreated(gl10, eGLConfig);
            if (this.f6042f == null) {
                this.f6042f = m5490c(FrameRendererGLES20.m5408i()).m5436j();
            }
            int iM5412k = this.f6042f.m5412k();
            if (this.f6057t != null) {
                Log.d("VideoRendererGLES20.RendererOES", "SurfaceTexture.attachToGLContext " + iM5412k);
                this.f6057t.attachToGLContext(iM5412k);
                this.f6040d.mo19971b(this.f6059v);
                return;
            }
            Log.d("VideoRendererGLES20.RendererOES", "new SurfaceTexture " + iM5412k);
            SurfaceTexture surfaceTexture = new SurfaceTexture(iM5412k);
            this.f6057t = surfaceTexture;
            surfaceTexture.setOnFrameAvailableListener(this);
            Surface surface = new Surface(this.f6057t);
            this.f6058u = surface;
            this.f6056s.m5456d(surface);
        }

        @Override // com.cyberlink.media.video.AbstractC1242f, com.cyberlink.media.video.InterfaceC1241e
        public void release() {
            this.f6040d.mo19971b(this.f6060w);
            super.release();
        }
    }

    public AbstractC1242f(InterfaceC5114a interfaceC5114a) {
        C5280d c5280d = new C5280d("VideoRendererGLES20");
        this.f6038b = c5280d;
        this.f6039c = c5280d;
        this.f6040d = interfaceC5114a;
    }

    /* renamed from: d */
    public static AbstractC1242f m5487d(GLSurfaceView gLSurfaceView, CLVideoView.RenderMode renderMode) {
        AbstractC1242f abstractC1242fM5488e = m5488e(new C5121h(gLSurfaceView), renderMode);
        gLSurfaceView.setEGLContextClientVersion(2);
        gLSurfaceView.setRenderer(abstractC1242fM5488e);
        gLSurfaceView.setRenderMode(0);
        return abstractC1242fM5488e;
    }

    /* renamed from: e */
    public static AbstractC1242f m5488e(InterfaceC5114a interfaceC5114a, CLVideoView.RenderMode renderMode) {
        return renderMode == CLVideoView.RenderMode.f5923d ? new b(interfaceC5114a) : new a(interfaceC5114a);
    }

    /* renamed from: b */
    public final void m5489b() {
        synchronized (this.f6039c) {
            this.f6047k = 0;
            this.f6046j = 0;
            this.f6045i = 0;
            this.f6049m = null;
            this.f6050n = null;
            this.f6051o = false;
        }
    }

    /* renamed from: c */
    public FrameRendererGLES20.C1224c m5490c(FrameRendererGLES20.C1224c c1224c) {
        if (this.f6048l) {
            c1224c.m5437k();
        }
        return c1224c;
    }

    /* renamed from: f */
    public SurfaceHolder mo5491f() {
        return null;
    }

    /* renamed from: g */
    public final void m5492g() {
        if (this.f6042f == null) {
            return;
        }
        synchronized (this.f6039c) {
            Bitmap bitmap = null;
            if (this.f6051o) {
                this.f6051o = false;
                this.f6039c.notifyAll();
                Bitmap bitmap2 = this.f6050n;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    this.f6049m = this.f6042f.m5411j();
                    bitmap = this.f6050n;
                }
                this.f6050n = null;
                return;
            }
            FrameRendererGLES20.InterfaceC1225d<Bitmap> interfaceC1225d = this.f6049m;
            if (interfaceC1225d != null) {
                if (bitmap != null) {
                    interfaceC1225d.mo5438a(bitmap);
                }
                this.f6049m.mo5423b();
            }
        }
    }

    /* renamed from: h */
    public abstract void mo5493h();

    /* renamed from: i */
    public final void m5494i() {
        if (this.f6052p) {
            this.f6054r = true;
            this.f6040d.mo19970a();
        }
    }

    /* renamed from: j */
    public void m5495j(boolean z8) {
        this.f6049m = null;
        FrameRendererGLES20 frameRendererGLES20 = this.f6042f;
        if (frameRendererGLES20 != null) {
            frameRendererGLES20.m5417p(z8);
            this.f6042f = null;
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16384);
        if (this.f6054r) {
            GLES20.glBlendFunc(1, 0);
            mo5493h();
            GLES20.glBlendFunc(770, 771);
            m5492g();
            if (this.f6053q) {
                synchronized (this.f6039c) {
                    this.f6053q = false;
                    this.f6039c.notifyAll();
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i9, int i10) {
        Log.d("VideoRendererGLES20", "onSurfaceChanged");
        this.f6043g = i9;
        this.f6044h = i10;
        GLES20.glViewport(0, 0, i9, i10);
        GLES20.glEnable(3042);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.d("VideoRendererGLES20", "onSurfaceCreated");
        this.f6041e = Thread.currentThread();
        synchronized (this.f6039c) {
            Bitmap bitmap = this.f6050n;
            if (bitmap != null) {
                if (bitmap.isRecycled()) {
                    this.f6050n = null;
                } else {
                    this.f6051o = true;
                }
            }
        }
    }

    @Override // com.cyberlink.media.video.InterfaceC1241e
    public void release() {
        m5489b();
    }
}
