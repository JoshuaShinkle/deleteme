package com.cyberlink.mediacodec;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.util.Log;
import android.view.Surface;

/* renamed from: com.cyberlink.mediacodec.c */
/* loaded from: classes.dex */
public class C1246c {

    /* renamed from: e */
    public static final String f6143e = "c";

    /* renamed from: a */
    public EGLDisplay f6144a;

    /* renamed from: b */
    public EGLContext f6145b;

    /* renamed from: c */
    public EGLSurface f6146c;

    /* renamed from: d */
    public Surface f6147d;

    public C1246c(Surface surface) {
        m5540c("InputSurface", new Object[0]);
        surface.getClass();
        this.f6147d = surface;
        m5542d();
    }

    /* renamed from: b */
    public static void m5539b(String str, Object... objArr) {
        Log.e(f6143e, String.format(str, objArr));
    }

    /* renamed from: c */
    public static void m5540c(String str, Object... objArr) {
    }

    /* renamed from: a */
    public final void m5541a(String str) {
        boolean z8 = false;
        while (true) {
            int iEglGetError = EGL14.eglGetError();
            if (iEglGetError == 12288) {
                break;
            }
            m5539b(str + ": EGL error: 0x" + Integer.toHexString(iEglGetError), new Object[0]);
            z8 = true;
        }
        if (z8) {
            throw new RuntimeException("EGL error encountered (see log)");
        }
    }

    /* renamed from: d */
    public final void m5542d() {
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
        this.f6144a = eGLDisplayEglGetDisplay;
        if (eGLDisplayEglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1)) {
            this.f6144a = null;
            throw new RuntimeException("unable to initialize EGL14");
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.f6144a, new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
        }
        this.f6145b = EGL14.eglCreateContext(this.f6144a, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        m5541a("eglCreateContext");
        if (this.f6145b == null) {
            throw new RuntimeException("null context");
        }
        this.f6146c = EGL14.eglCreateWindowSurface(this.f6144a, eGLConfigArr[0], this.f6147d, new int[]{12344}, 0);
        m5541a("eglCreateWindowSurface");
        if (this.f6146c == null) {
            throw new RuntimeException("surface was null");
        }
    }

    /* renamed from: e */
    public void m5543e() {
        m5540c("makeCurrent", new Object[0]);
        EGLDisplay eGLDisplay = this.f6144a;
        EGLSurface eGLSurface = this.f6146c;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f6145b)) {
            throw new RuntimeException("eglMakeCurrent failed");
        }
    }

    /* renamed from: f */
    public void m5544f() {
        m5540c("release", new Object[0]);
        if (EGL14.eglGetCurrentContext().equals(this.f6145b)) {
            EGLDisplay eGLDisplay = this.f6144a;
            EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
        }
        EGL14.eglDestroySurface(this.f6144a, this.f6146c);
        EGL14.eglDestroyContext(this.f6144a, this.f6145b);
        this.f6147d.release();
        this.f6144a = null;
        this.f6145b = null;
        this.f6146c = null;
        this.f6147d = null;
    }

    /* renamed from: g */
    public void m5545g(long j9) {
        EGLExt.eglPresentationTimeANDROID(this.f6144a, this.f6146c, j9);
    }

    /* renamed from: h */
    public boolean m5546h() {
        m5540c("swapBuffers", new Object[0]);
        return EGL14.eglSwapBuffers(this.f6144a, this.f6146c);
    }
}
