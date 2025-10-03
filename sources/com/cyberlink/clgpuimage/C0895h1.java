package com.cyberlink.clgpuimage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.cyberlink.clgpuimage.GPUImage;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;
import p253z1.C6816a;

@SuppressLint({"WrongCall"})
@TargetApi(11)
/* renamed from: com.cyberlink.clgpuimage.h1 */
/* loaded from: classes.dex */
public class C0895h1 implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: V */
    public static final float[] f4601V = {-1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f};

    /* renamed from: W */
    public static int[] f4602W = new int[4];

    /* renamed from: X */
    public static int[] f4603X = new int[4];

    /* renamed from: Y */
    public static int f4604Y = 36197;

    /* renamed from: A */
    public boolean f4605A;

    /* renamed from: M */
    public int[] f4617M;

    /* renamed from: N */
    public int[] f4618N;

    /* renamed from: O */
    public C0936w0 f4619O;

    /* renamed from: P */
    public int f4620P;

    /* renamed from: b */
    public GLSurfaceView f4626b;

    /* renamed from: c */
    public C0936w0 f4627c;

    /* renamed from: d */
    public C0918p0 f4628d;

    /* renamed from: e */
    public C0921q0 f4629e;

    /* renamed from: k */
    public int f4635k;

    /* renamed from: l */
    public final FloatBuffer f4636l;

    /* renamed from: m */
    public final FloatBuffer f4637m;

    /* renamed from: n */
    public final FloatBuffer f4638n;

    /* renamed from: o */
    public final FloatBuffer f4639o;

    /* renamed from: q */
    public int f4641q;

    /* renamed from: r */
    public int f4642r;

    /* renamed from: s */
    public int f4643s;

    /* renamed from: t */
    public int f4644t;

    /* renamed from: w */
    public final Queue<Runnable> f4647w;

    /* renamed from: x */
    public final Queue<Runnable> f4648x;

    /* renamed from: y */
    public Rotation f4649y;

    /* renamed from: z */
    public boolean f4650z;

    /* renamed from: f */
    public final Object f4630f = new Object();

    /* renamed from: g */
    public Object f4631g = new Object();

    /* renamed from: h */
    public Object f4632h = null;

    /* renamed from: i */
    public int f4633i = -1;

    /* renamed from: j */
    public SurfaceTexture f4634j = null;

    /* renamed from: p */
    public float[] f4640p = new float[16];

    /* renamed from: u */
    public int f4645u = 0;

    /* renamed from: v */
    public int f4646v = 0;

    /* renamed from: B */
    public GPUImage.ScaleType f4606B = GPUImage.ScaleType.CENTER_CROP;

    /* renamed from: C */
    public boolean f4607C = false;

    /* renamed from: D */
    public f f4608D = null;

    /* renamed from: E */
    public EGLContext f4609E = EGL10.EGL_NO_CONTEXT;

    /* renamed from: F */
    public EGLDisplay f4610F = null;

    /* renamed from: G */
    public EGLConfig f4611G = null;

    /* renamed from: H */
    public int f4612H = -1;

    /* renamed from: I */
    public int f4613I = -1;

    /* renamed from: J */
    public ByteBuffer f4614J = null;

    /* renamed from: K */
    public ByteBuffer f4615K = null;

    /* renamed from: L */
    public boolean f4616L = false;

    /* renamed from: Q */
    public int f4621Q = 0;

    /* renamed from: R */
    public long f4622R = System.nanoTime();

    /* renamed from: S */
    public boolean f4623S = false;

    /* renamed from: T */
    public final int f4624T = 3;

    /* renamed from: U */
    public int f4625U = 3;

    /* renamed from: com.cyberlink.clgpuimage.h1$a */
    public class a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ C0936w0 f4652c;

        public a(C0936w0 c0936w0) {
            this.f4652c = c0936w0;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0936w0 c0936w0 = C0895h1.this.f4627c;
            C0895h1.this.f4627c = this.f4652c;
            if (c0936w0 != null) {
                c0936w0.m4400c();
            }
            C0895h1.this.f4627c.m4404g();
            C0895h1.this.f4627c.m4413v(C0895h1.this.f4606B);
            GLES20.glUseProgram(C0895h1.this.f4627c.m4403f());
            if (C0895h1.this.f4606B == GPUImage.ScaleType.CENTER_INSIDE_CAMERA || C0895h1.this.f4606B == GPUImage.ScaleType.CENTER_INSIDE_CAMERA_YUV_BUFFER) {
                if (C0895h1.this.f4649y == Rotation.ROTATION_270 || C0895h1.this.f4649y == Rotation.ROTATION_90) {
                    C0895h1.this.f4627c.mo4049n(C0895h1.this.f4644t, C0895h1.this.f4643s);
                    return;
                } else {
                    C0895h1.this.f4627c.mo4049n(C0895h1.this.f4643s, C0895h1.this.f4644t);
                    return;
                }
            }
            if (C0895h1.this.f4606B == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
                int iMin = Math.min(C0895h1.this.f4643s, C0895h1.this.f4644t);
                C0895h1.this.f4627c.mo4049n(iMin, iMin);
            } else {
                C0895h1.this.f4627c.mo4049n(C0895h1.this.f4641q, C0895h1.this.f4642r);
                C0895h1.this.f4627c.m4416y(C0895h1.this.f4645u, C0895h1.this.f4646v);
            }
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.h1$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glDeleteTextures(1, new int[]{C0895h1.this.f4633i}, 0);
            C0895h1.this.f4633i = -1;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.h1$c */
    public class c implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ Bitmap f4655c;

        /* renamed from: d */
        public final /* synthetic */ boolean f4656d;

        public c(Bitmap bitmap, boolean z8) {
            this.f4655c = bitmap;
            this.f4656d = z8;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmapCreateScaledBitmap;
            if (this.f4655c.getWidth() % 2 == 1) {
                Bitmap bitmap = this.f4655c;
                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() + 1, this.f4655c.getHeight(), false);
            } else {
                bitmapCreateScaledBitmap = null;
            }
            C0895h1 c0895h1 = C0895h1.this;
            c0895h1.f4633i = C0925r1.m4379e(bitmapCreateScaledBitmap != null ? bitmapCreateScaledBitmap : this.f4655c, c0895h1.f4633i, this.f4656d);
            if (bitmapCreateScaledBitmap != null) {
                bitmapCreateScaledBitmap.recycle();
            }
            C0895h1.this.f4643s = this.f4655c.getWidth();
            C0895h1.this.f4644t = this.f4655c.getHeight();
            C0895h1.this.m4332s();
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.h1$d */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C0895h1.this.f4606B == GPUImage.ScaleType.CENTER_INSIDE_CAMERA || C0895h1.this.f4606B == GPUImage.ScaleType.CENTER_INSIDE_CAMERA_YUV_BUFFER) {
                if (C0895h1.this.f4649y == Rotation.ROTATION_270 || C0895h1.this.f4649y == Rotation.ROTATION_90) {
                    if (C0895h1.this.f4627c != null) {
                        C0895h1.this.f4627c.mo4049n(C0895h1.this.f4644t, C0895h1.this.f4643s);
                    }
                } else if (C0895h1.this.f4627c != null) {
                    C0895h1.this.f4627c.mo4049n(C0895h1.this.f4643s, C0895h1.this.f4644t);
                }
                C0895h1.this.m4326J();
                return;
            }
            if (C0895h1.this.f4606B == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
                int iMin = Math.min(C0895h1.this.f4643s, C0895h1.this.f4644t);
                if (C0895h1.this.f4627c != null) {
                    C0895h1.this.f4627c.mo4049n(iMin, iMin);
                }
                C0895h1.this.m4326J();
                return;
            }
            if (C0895h1.this.f4627c != null) {
                C0895h1.this.f4627c.mo4049n(C0895h1.this.f4641q, C0895h1.this.f4642r);
                C0895h1.this.f4627c.m4416y(C0895h1.this.f4645u, C0895h1.this.f4646v);
            }
            GLES20.glViewport(0, 0, C0895h1.this.f4641q, C0895h1.this.f4642r);
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.h1$e */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0895h1.this.m4332s();
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.h1$f */
    public interface f {
        /* renamed from: a */
        void mo4340a();

        /* renamed from: b */
        void mo4341b();
    }

    public C0895h1(C0936w0 c0936w0) {
        this.f4617M = null;
        this.f4618N = null;
        this.f4619O = null;
        this.f4620P = 0;
        this.f4627c = c0936w0;
        if (c0936w0 != null) {
            c0936w0.m4413v(this.f4606B);
            this.f4627c.m4416y(this.f4645u, this.f4646v);
        }
        this.f4647w = new ConcurrentLinkedQueue();
        this.f4648x = new ConcurrentLinkedQueue();
        float[] fArr = f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4636l = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        this.f4637m = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        m4328L(Rotation.NORMAL, false, false);
        this.f4628d = new C0918p0();
        this.f4629e = new C0921q0();
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4638n = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr).position(0);
        FloatBuffer floatBufferAsFloatBuffer3 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4639o = floatBufferAsFloatBuffer3;
        floatBufferAsFloatBuffer3.put(fArr2).position(0);
        this.f4617M = null;
        this.f4618N = null;
        this.f4619O = null;
        this.f4620P = 0;
    }

    /* renamed from: A */
    public int m4317A() {
        return this.f4641q;
    }

    /* renamed from: B */
    public boolean m4318B() {
        return this.f4650z;
    }

    /* renamed from: C */
    public boolean m4319C() {
        return this.f4605A;
    }

    /* renamed from: D */
    public final void m4320D(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                queue.peek().run();
                queue.poll();
            }
        }
    }

    /* renamed from: E */
    public void m4321E(Runnable runnable) {
        this.f4647w.add(runnable);
    }

    /* renamed from: F */
    public void m4322F(C0936w0 c0936w0) {
        m4321E(new a(c0936w0));
    }

    /* renamed from: G */
    public void m4323G(GLSurfaceView gLSurfaceView) {
        this.f4626b = gLSurfaceView;
    }

    /* renamed from: H */
    public void m4324H(Bitmap bitmap, boolean z8) {
        if (bitmap == null) {
            return;
        }
        m4321E(new c(bitmap, z8));
    }

    /* renamed from: I */
    public void m4325I(f fVar) {
        this.f4608D = fVar;
    }

    /* renamed from: J */
    public final void m4326J() {
        int iMin;
        int i9;
        Rotation rotation = this.f4649y;
        if (rotation == Rotation.ROTATION_270 || rotation == Rotation.ROTATION_90) {
            iMin = this.f4644t;
            i9 = this.f4643s;
        } else {
            iMin = this.f4643s;
            i9 = this.f4644t;
        }
        if (this.f4606B == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
            iMin = Math.min(this.f4643s, this.f4644t);
            i9 = iMin;
        }
        GLES20.glViewport(0, 0, iMin, i9);
        C0936w0 c0936w0 = this.f4627c;
        if (c0936w0 != null) {
            c0936w0.mo4049n(iMin, i9);
        }
        C0918p0 c0918p0 = this.f4628d;
        if (c0918p0 != null) {
            c0918p0.mo4049n(iMin, i9);
        }
        C0921q0 c0921q0 = this.f4629e;
        if (c0921q0 != null) {
            c0921q0.mo4049n(iMin, i9);
        }
        int i10 = this.f4641q;
        int i11 = this.f4642r;
        float f9 = iMin;
        float f10 = i9;
        float f11 = f9 / f10;
        if (i10 / i11 > f11) {
            int[] iArr = f4602W;
            iArr[1] = 0;
            iArr[3] = i11;
            iArr[0] = (i10 / 2) - ((int) ((i11 / 2) * f11));
            iArr[2] = (int) (i11 * f11);
        } else {
            int[] iArr2 = f4602W;
            iArr2[0] = 0;
            iArr2[2] = i10;
            float f12 = f10 / f9;
            iArr2[1] = (i11 / 2) - ((int) ((i10 / 2) * f12));
            iArr2[3] = (int) (i10 * f12);
        }
        int[] iArr3 = f4603X;
        iArr3[0] = 0;
        iArr3[1] = 0;
        iArr3[2] = iMin;
        iArr3[3] = i9;
    }

    /* renamed from: K */
    public void m4327K(Rotation rotation) {
        this.f4649y = rotation;
        m4321E(new e());
    }

    /* renamed from: L */
    public void m4328L(Rotation rotation, boolean z8, boolean z9) {
        this.f4650z = z8;
        this.f4605A = z9;
        m4327K(rotation);
    }

    /* renamed from: M */
    public void m4329M(GPUImage.ScaleType scaleType) {
        this.f4606B = scaleType;
        C0936w0 c0936w0 = this.f4627c;
        if (c0936w0 != null) {
            c0936w0.m4413v(scaleType);
        }
        m4330N();
    }

    /* renamed from: N */
    public final void m4330N() {
        m4321E(new d());
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    @TargetApi(14)
    public void onDrawFrame(GL10 gl10) {
        int iM4375a;
        int iM4375a2;
        Object obj = this.f4632h;
        if (obj != null) {
            synchronized (obj) {
                GLES20.glClear(16640);
                f fVar = this.f4608D;
                if (fVar != null) {
                    fVar.mo4341b();
                }
                m4320D(this.f4647w);
                GPUImage.ScaleType scaleType = this.f4606B;
                if (scaleType == GPUImage.ScaleType.CENTER_INSIDE_CAMERA || scaleType == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
                    SurfaceTexture surfaceTexture = this.f4634j;
                    if (surfaceTexture != null) {
                        surfaceTexture.updateTexImage();
                        this.f4634j.getTransformMatrix(this.f4640p);
                        C0918p0 c0918p0 = this.f4628d;
                        c0918p0.m4415x(c0918p0.m4367A(), this.f4640p);
                        int[] iArr = f4603X;
                        GLES20.glViewport(iArr[0], iArr[1], iArr[2], iArr[3]);
                        this.f4633i = this.f4628d.m4368B(this.f4635k, this.f4636l, this.f4637m);
                        int[] iArr2 = f4602W;
                        GLES20.glViewport(iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
                        this.f4627c.mo4046j(this.f4633i, this.f4638n, this.f4639o);
                    }
                } else if (scaleType == GPUImage.ScaleType.CENTER_INSIDE_CAMERA_YUV_BUFFER) {
                    synchronized (this.f4631g) {
                        this.f4612H = C0925r1.m4376b(this.f4614J, this.f4643s, this.f4644t, this.f4612H);
                        iM4375a2 = C0925r1.m4375a(this.f4615K, this.f4643s, this.f4644t, this.f4613I);
                        this.f4613I = iM4375a2;
                    }
                    if (this.f4612H != -1 && iM4375a2 != -1) {
                        int[] iArr3 = f4603X;
                        GLES20.glViewport(iArr3[0], iArr3[1], iArr3[2], iArr3[3]);
                        this.f4633i = this.f4629e.m4372A(this.f4612H, this.f4613I, this.f4636l, this.f4637m);
                        int[] iArr4 = f4602W;
                        GLES20.glViewport(iArr4[0], iArr4[1], iArr4[2], iArr4[3]);
                        this.f4627c.mo4046j(this.f4633i, this.f4638n, this.f4639o);
                    }
                } else {
                    int i9 = this.f4633i;
                    if (i9 != -1) {
                        if (this.f4616L) {
                            int i10 = this.f4620P;
                            if (i10 == 0) {
                                m4336w(i9, this.f4618N[0], this.f4617M[0], this.f4639o, this.f4636l, this.f4637m);
                                this.f4620P++;
                            } else {
                                if (i10 % 2 != 0) {
                                    int[] iArr5 = this.f4618N;
                                    int i11 = iArr5[0];
                                    int i12 = iArr5[1];
                                    int i13 = this.f4617M[1];
                                    FloatBuffer floatBuffer = this.f4639o;
                                    m4336w(i11, i12, i13, floatBuffer, this.f4636l, floatBuffer);
                                } else {
                                    int[] iArr6 = this.f4618N;
                                    int i14 = iArr6[1];
                                    int i15 = iArr6[0];
                                    int i16 = this.f4617M[0];
                                    FloatBuffer floatBuffer2 = this.f4639o;
                                    m4336w(i14, i15, i16, floatBuffer2, this.f4636l, floatBuffer2);
                                }
                                this.f4620P++;
                            }
                        } else {
                            this.f4627c.mo4046j(i9, this.f4636l, this.f4637m);
                        }
                    }
                }
                m4320D(this.f4648x);
                if (this.f4608D != null) {
                    GLES20.glFinish();
                    this.f4608D.mo4340a();
                }
            }
        } else {
            GLES20.glClear(16640);
            f fVar2 = this.f4608D;
            if (fVar2 != null) {
                fVar2.mo4341b();
            }
            m4320D(this.f4647w);
            GPUImage.ScaleType scaleType2 = this.f4606B;
            if (scaleType2 == GPUImage.ScaleType.CENTER_INSIDE_CAMERA || scaleType2 == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
                SurfaceTexture surfaceTexture2 = this.f4634j;
                if (surfaceTexture2 != null) {
                    surfaceTexture2.updateTexImage();
                    this.f4634j.getTransformMatrix(this.f4640p);
                    C0918p0 c0918p02 = this.f4628d;
                    c0918p02.m4415x(c0918p02.m4367A(), this.f4640p);
                    int[] iArr7 = f4603X;
                    GLES20.glViewport(iArr7[0], iArr7[1], iArr7[2], iArr7[3]);
                    this.f4633i = this.f4628d.m4368B(this.f4635k, this.f4636l, this.f4637m);
                    int[] iArr8 = f4602W;
                    GLES20.glViewport(iArr8[0], iArr8[1], iArr8[2], iArr8[3]);
                    this.f4627c.mo4046j(this.f4633i, this.f4638n, this.f4639o);
                }
            } else if (scaleType2 == GPUImage.ScaleType.CENTER_INSIDE_CAMERA_YUV_BUFFER) {
                synchronized (this.f4631g) {
                    this.f4612H = C0925r1.m4376b(this.f4614J, this.f4643s, this.f4644t, this.f4612H);
                    iM4375a = C0925r1.m4375a(this.f4615K, this.f4643s, this.f4644t, this.f4613I);
                    this.f4613I = iM4375a;
                }
                if (this.f4612H != -1 && iM4375a != -1) {
                    int[] iArr9 = f4603X;
                    GLES20.glViewport(iArr9[0], iArr9[1], iArr9[2], iArr9[3]);
                    this.f4633i = this.f4629e.m4372A(this.f4612H, this.f4613I, this.f4636l, this.f4637m);
                    int[] iArr10 = f4602W;
                    GLES20.glViewport(iArr10[0], iArr10[1], iArr10[2], iArr10[3]);
                    this.f4627c.mo4046j(this.f4633i, this.f4638n, this.f4639o);
                }
            } else {
                int i17 = this.f4633i;
                if (i17 != -1) {
                    if (this.f4616L) {
                        int i18 = this.f4620P;
                        if (i18 == 0) {
                            m4336w(i17, this.f4618N[0], this.f4617M[0], this.f4639o, this.f4636l, this.f4637m);
                            this.f4620P++;
                        } else {
                            if (i18 % 2 != 0) {
                                int[] iArr11 = this.f4618N;
                                int i19 = iArr11[0];
                                int i20 = iArr11[1];
                                int i21 = this.f4617M[1];
                                FloatBuffer floatBuffer3 = this.f4639o;
                                m4336w(i19, i20, i21, floatBuffer3, this.f4636l, floatBuffer3);
                            } else {
                                int[] iArr12 = this.f4618N;
                                int i22 = iArr12[1];
                                int i23 = iArr12[0];
                                int i24 = this.f4617M[0];
                                FloatBuffer floatBuffer4 = this.f4639o;
                                m4336w(i22, i23, i24, floatBuffer4, this.f4636l, floatBuffer4);
                            }
                            this.f4620P++;
                        }
                    } else {
                        this.f4627c.mo4046j(i17, this.f4636l, this.f4637m);
                    }
                }
            }
            m4320D(this.f4648x);
            if (this.f4608D != null) {
                GLES20.glFinish();
                this.f4608D.mo4340a();
            }
        }
        if (this.f4623S) {
            int i25 = this.f4625U;
            if (i25 > 0) {
                int i26 = i25 - 1;
                this.f4625U = i26;
                if (i26 == 0) {
                    this.f4622R = System.nanoTime();
                    this.f4621Q = 0;
                    return;
                }
                return;
            }
            int i27 = this.f4621Q + 1;
            this.f4621Q = i27;
            if (i27 % 10 == 0) {
                long jNanoTime = System.nanoTime();
                this.f4621Q = 0;
                this.f4622R = jNanoTime;
            }
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.f4626b.requestRender();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i9, int i10) {
        this.f4641q = i9;
        this.f4642r = i10;
        GLES20.glViewport(0, 0, i9, i10);
        GLES20.glUseProgram(this.f4627c.m4403f());
        this.f4627c.mo4049n(this.f4641q, this.f4642r);
        this.f4627c.m4416y(this.f4645u, this.f4646v);
        if (this.f4616L) {
            if (this.f4617M != null) {
                m4335v();
            }
            m4333t(i9, i10);
            this.f4619O.mo4049n(this.f4641q, this.f4642r);
            this.f4619O.m4416y(this.f4645u, this.f4646v);
        }
        m4332s();
        synchronized (this.f4630f) {
            this.f4630f.notifyAll();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.f4609E = egl10.eglGetCurrentContext();
        this.f4610F = egl10.eglGetCurrentDisplay();
        this.f4611G = eGLConfig;
        GLES20.glDisable(2929);
        this.f4627c.m4404g();
        this.f4628d.m4404g();
        this.f4629e.m4404g();
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.f4635k = iArr[0];
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.f4635k);
        this.f4634j = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        GLES20.glBindTexture(f4604Y, this.f4635k);
        GLES20.glTexParameterf(f4604Y, 10241, 9728.0f);
        GLES20.glTexParameterf(f4604Y, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameteri(f4604Y, 10242, 33071);
        GLES20.glTexParameteri(f4604Y, 10243, 33071);
        GLES20.glBindTexture(f4604Y, 0);
        if (this.f4616L) {
            C0936w0 c0936w0 = new C0936w0();
            this.f4619O = c0936w0;
            c0936w0.m4404g();
        }
    }

    /* renamed from: r */
    public final float m4331r(float f9, float f10) {
        return f9 == BitmapDescriptorFactory.HUE_RED ? f10 : 1.0f - f10;
    }

    /* renamed from: s */
    public final void m4332s() {
        float f9 = this.f4641q;
        float f10 = this.f4642r;
        int i9 = this.f4643s;
        float f11 = i9;
        int i10 = this.f4644t;
        float f12 = i10;
        Rotation rotation = this.f4649y;
        if (rotation == Rotation.ROTATION_270 || rotation == Rotation.ROTATION_90) {
            f11 = i10;
            f12 = i9;
        }
        float fMin = Math.min(f9 / f11, f10 / f12);
        float fRound = Math.round(f11 * fMin) / f9;
        float fRound2 = Math.round(f12 * fMin) / f10;
        float[] fArrM4337x = f4601V;
        GPUImage.ScaleType scaleType = this.f4606B;
        GPUImage.ScaleType scaleType2 = GPUImage.ScaleType.CENTER_CROP;
        float[] fArrM25387c = (scaleType == scaleType2 || scaleType == GPUImage.ScaleType.CENTER_INSIDE || scaleType == GPUImage.ScaleType.AS_DISAPLY || scaleType == GPUImage.ScaleType.MANUALLY || scaleType == GPUImage.ScaleType.CENTER_INSIDE_CAMERA_YUV_BUFFER) ? C6816a.m25387c(this.f4649y, this.f4650z, !this.f4605A) : C6816a.m25387c(Rotation.NORMAL, this.f4650z, this.f4605A);
        GPUImage.ScaleType scaleType3 = this.f4606B;
        if (scaleType3 == scaleType2) {
            float f13 = (1.0f - (1.0f / fRound)) / 2.0f;
            float f14 = (1.0f - (1.0f / fRound2)) / 2.0f;
            fArrM25387c = new float[]{m4331r(fArrM25387c[0], f13), m4331r(fArrM25387c[1], f14), m4331r(fArrM25387c[2], f13), m4331r(fArrM25387c[3], f14), m4331r(fArrM25387c[4], f13), m4331r(fArrM25387c[5], f14), m4331r(fArrM25387c[6], f13), m4331r(fArrM25387c[7], f14)};
        } else if (scaleType3 == GPUImage.ScaleType.CENTER_INSIDE) {
            fArrM4337x = new float[]{fArrM4337x[0] * fRound, fArrM4337x[1] * fRound2, fArrM4337x[2] * fRound, fArrM4337x[3] * fRound2, fArrM4337x[4] * fRound, fArrM4337x[5] * fRound2, fArrM4337x[6] * fRound, fArrM4337x[7] * fRound2};
        } else if (scaleType3 != GPUImage.ScaleType.AS_DISAPLY && scaleType3 != GPUImage.ScaleType.MANUALLY) {
            m4326J();
            if (this.f4606B == GPUImage.ScaleType.SQUARE_CROP_INSIDE_CAMERA) {
                fArrM4337x = m4337x();
            }
        }
        this.f4636l.clear();
        this.f4636l.put(fArrM4337x).position(0);
        this.f4637m.clear();
        this.f4637m.put(fArrM25387c).position(0);
    }

    /* renamed from: t */
    public final void m4333t(int i9, int i10) {
        int[] iArr = new int[2];
        this.f4617M = iArr;
        this.f4618N = new int[2];
        GLES20.glGenFramebuffers(2, iArr, 0);
        GLES20.glGenTextures(2, this.f4618N, 0);
        for (int i11 = 0; i11 < 2; i11++) {
            GLES20.glBindTexture(3553, this.f4618N[i11]);
            GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.f4617M[i11]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4618N[i11], 0);
        }
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: u */
    public void m4334u() {
        m4321E(new b());
    }

    /* renamed from: v */
    public final void m4335v() {
        int[] iArr = this.f4618N;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4618N = null;
        }
        int[] iArr2 = this.f4617M;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4617M = null;
        }
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: w */
    public final void m4336w(int i9, int i10, int i11, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, FloatBuffer floatBuffer3) {
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        GLES20.glBindFramebuffer(36160, i11);
        GLES20.glClear(16384);
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        this.f4627c.mo4046j(i9, floatBuffer2, floatBuffer3);
        GLES20.glFlush();
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glClear(16384);
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
        this.f4619O.mo4046j(i10, this.f4636l, floatBuffer);
        GLES20.glFlush();
    }

    /* renamed from: x */
    public final float[] m4337x() {
        int i9;
        int i10;
        float f9;
        float f10;
        Rotation rotation = this.f4649y;
        if (rotation == Rotation.ROTATION_90 || rotation == Rotation.ROTATION_270) {
            i9 = this.f4644t;
            i10 = this.f4643s;
        } else {
            i9 = this.f4643s;
            i10 = this.f4644t;
        }
        float fMin = Math.min(this.f4643s, this.f4644t);
        float f11 = fMin / i9;
        float f12 = fMin / i10;
        if (f11 < f12) {
            f10 = f12 / f11;
            f9 = 1.0f;
        } else if (f11 > f12) {
            f9 = f11 / f12;
            f10 = 1.0f;
        } else {
            f9 = 1.0f;
            f10 = 1.0f;
        }
        float f13 = f10 * (-1.0f);
        float f14 = f9 * 1.0f;
        float f15 = f10 * 1.0f;
        float f16 = f9 * (-1.0f);
        return new float[]{f13, f14, f15, f14, f13, f16, f15, f16};
    }

    /* renamed from: y */
    public EGLContext m4338y() {
        return this.f4609E;
    }

    /* renamed from: z */
    public int m4339z() {
        return this.f4642r;
    }
}
