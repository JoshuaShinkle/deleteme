package com.cyberlink.clgpuimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.util.Log;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.cyberlink.clgpuimage.s1 */
/* loaded from: classes.dex */
public class C0928s1 {

    /* renamed from: a */
    public GLSurfaceView.Renderer f4766a;

    /* renamed from: b */
    public int f4767b;

    /* renamed from: c */
    public int f4768c;

    /* renamed from: d */
    public Bitmap f4769d;

    /* renamed from: e */
    public EGL10 f4770e;

    /* renamed from: f */
    public EGLDisplay f4771f;

    /* renamed from: g */
    public EGLConfig[] f4772g;

    /* renamed from: h */
    public EGLConfig f4773h;

    /* renamed from: i */
    public EGLContext f4774i;

    /* renamed from: j */
    public EGLSurface f4775j;

    /* renamed from: k */
    public GL10 f4776k;

    /* renamed from: l */
    public String f4777l;

    public C0928s1(int i9, int i10, EGLContext eGLContext) {
        this.f4767b = i9;
        this.f4768c = i10;
        int[] iArr = {12375, i9, 12374, i10, 12344};
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.f4770e = egl10;
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.f4771f = eGLDisplayEglGetDisplay;
        this.f4770e.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        EGLConfig eGLConfigM4389a = m4389a();
        this.f4773h = eGLConfigM4389a;
        this.f4774i = this.f4770e.eglCreateContext(this.f4771f, eGLConfigM4389a, eGLContext, new int[]{12440, 2, 12344});
        EGLSurface eGLSurfaceEglCreatePbufferSurface = this.f4770e.eglCreatePbufferSurface(this.f4771f, this.f4773h, iArr);
        this.f4775j = eGLSurfaceEglCreatePbufferSurface;
        this.f4770e.eglMakeCurrent(this.f4771f, eGLSurfaceEglCreatePbufferSurface, eGLSurfaceEglCreatePbufferSurface, this.f4774i);
        this.f4776k = (GL10) this.f4774i.getGL();
        this.f4777l = Thread.currentThread().getName();
    }

    /* renamed from: a */
    public final EGLConfig m4389a() {
        int[] iArr = {12325, 0, 12326, 0, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344};
        int[] iArr2 = new int[1];
        this.f4770e.eglChooseConfig(this.f4771f, iArr, null, 0, iArr2);
        int i9 = iArr2[0];
        EGLConfig[] eGLConfigArr = new EGLConfig[i9];
        this.f4772g = eGLConfigArr;
        this.f4770e.eglChooseConfig(this.f4771f, iArr, eGLConfigArr, i9, iArr2);
        return this.f4772g[0];
    }

    /* renamed from: b */
    public final void m4390b() {
        IntBuffer intBufferAllocate = IntBuffer.allocate(this.f4767b * this.f4768c);
        this.f4776k.glReadPixels(0, 0, this.f4767b, this.f4768c, 6408, 5121, intBufferAllocate);
        int[] iArrArray = intBufferAllocate.array();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.f4767b, this.f4768c, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(IntBuffer.wrap(iArrArray));
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        this.f4769d = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix, false);
    }

    /* renamed from: c */
    public void m4391c(Rect rect, Rect rect2, Bitmap bitmap) {
        EGL10 egl10 = this.f4770e;
        EGLDisplay eGLDisplay = this.f4771f;
        EGLSurface eGLSurface = this.f4775j;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f4774i);
        IntBuffer intBufferAllocate = IntBuffer.allocate(rect.width() * rect.height());
        this.f4776k.glReadPixels(rect.left, this.f4768c - (rect.top + rect.height()), rect.width(), rect.height(), 6408, 5121, intBufferAllocate);
        int[] iArrArray = intBufferAllocate.array();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(IntBuffer.wrap(iArrArray));
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        new Canvas(bitmap).drawBitmap(Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix, false), rect2.left, rect2.top, (Paint) null);
    }

    /* renamed from: d */
    public void m4392d() {
        EGL10 egl10 = this.f4770e;
        EGLDisplay eGLDisplay = this.f4771f;
        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
        this.f4770e.eglDestroySurface(this.f4771f, this.f4775j);
        this.f4770e.eglDestroyContext(this.f4771f, this.f4774i);
        this.f4770e.eglTerminate(this.f4771f);
    }

    /* renamed from: e */
    public Bitmap m4393e() {
        if (!m4395g()) {
            return null;
        }
        m4390b();
        return this.f4769d;
    }

    /* renamed from: f */
    public void m4394f(GLSurfaceView.Renderer renderer) {
        this.f4766a = renderer;
        if (!Thread.currentThread().getName().equals(this.f4777l)) {
            Log.e("PixelBuffer", "setRenderer: This thread does not own the OpenGL context.");
        } else {
            this.f4766a.onSurfaceCreated(this.f4776k, this.f4773h);
            this.f4766a.onSurfaceChanged(this.f4776k, this.f4767b, this.f4768c);
        }
    }

    /* renamed from: g */
    public boolean m4395g() {
        if (this.f4766a == null) {
            Log.e("PixelBuffer", "getBitmap: Renderer was not set.");
            return false;
        }
        if (!Thread.currentThread().getName().equals(this.f4777l)) {
            Log.e("PixelBuffer", "getBitmap: This thread does not own the OpenGL context.");
            return false;
        }
        EGL10 egl10 = this.f4770e;
        EGLDisplay eGLDisplay = this.f4771f;
        EGLSurface eGLSurface = this.f4775j;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f4774i);
        this.f4766a.onDrawFrame(this.f4776k);
        return true;
    }
}
