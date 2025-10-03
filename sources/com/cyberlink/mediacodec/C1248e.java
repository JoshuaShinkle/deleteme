package com.cyberlink.mediacodec;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.view.Surface;

/* renamed from: com.cyberlink.mediacodec.e */
/* loaded from: classes.dex */
public class C1248e implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: e */
    public SurfaceTexture f6156e;

    /* renamed from: f */
    public Surface f6157f;

    /* renamed from: h */
    public boolean f6159h;

    /* renamed from: i */
    public C1250g f6160i;

    /* renamed from: b */
    public EGLDisplay f6153b = EGL14.EGL_NO_DISPLAY;

    /* renamed from: c */
    public EGLContext f6154c = EGL14.EGL_NO_CONTEXT;

    /* renamed from: d */
    public EGLSurface f6155d = EGL14.EGL_NO_SURFACE;

    /* renamed from: g */
    public Object f6158g = new Object();

    public C1248e() {
        m5555b("OutputSurface", new Object[0]);
        m5560f();
    }

    /* renamed from: b */
    public static void m5555b(String str, Object... objArr) {
    }

    /* renamed from: a */
    public void m5556a() {
        m5555b("awaitNewImage", new Object[0]);
        synchronized (this.f6158g) {
            while (!this.f6159h) {
                try {
                    this.f6158g.wait(10000L);
                    if (!this.f6159h) {
                        throw new RuntimeException("Surface frame wait timed out");
                    }
                } catch (InterruptedException e9) {
                    throw new RuntimeException(e9);
                }
            }
            this.f6159h = false;
        }
        this.f6160i.m5595a("before updateTexImage");
        this.f6156e.updateTexImage();
        m5555b("awaitNewImage, done", new Object[0]);
    }

    /* renamed from: c */
    public void m5557c() {
        m5555b("drawImage", new Object[0]);
        this.f6160i.m5597c(this.f6156e);
        m5555b("drawImage, done", new Object[0]);
    }

    /* renamed from: d */
    public Surface m5558d() {
        return this.f6157f;
    }

    /* renamed from: e */
    public void m5559e() {
        m5555b("release", new Object[0]);
        EGLDisplay eGLDisplay = this.f6153b;
        if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(eGLDisplay, this.f6155d);
            EGL14.eglDestroyContext(this.f6153b, this.f6154c);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f6153b);
        }
        this.f6157f.release();
        this.f6153b = EGL14.EGL_NO_DISPLAY;
        this.f6154c = EGL14.EGL_NO_CONTEXT;
        this.f6155d = EGL14.EGL_NO_SURFACE;
        this.f6160i = null;
        this.f6157f = null;
        this.f6156e = null;
    }

    /* renamed from: f */
    public final void m5560f() {
        C1250g c1250g = new C1250g();
        this.f6160i = c1250g;
        c1250g.m5600f();
        m5555b("setup, textureID=" + this.f6160i.m5598d(), new Object[0]);
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.f6160i.m5598d());
        this.f6156e = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.f6157f = new Surface(this.f6156e);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        m5555b("onFrameAvailable", new Object[0]);
        synchronized (this.f6158g) {
            if (this.f6159h) {
                throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
            }
            this.f6159h = true;
            this.f6158g.notifyAll();
        }
    }
}
