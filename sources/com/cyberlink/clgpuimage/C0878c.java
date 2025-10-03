package com.cyberlink.clgpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import com.cyberlink.clgpuimage.CLFocusEffectFilter;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.c */
/* loaded from: classes.dex */
public class C0878c extends C0936w0 {

    /* renamed from: A */
    public int[] f4420A;

    /* renamed from: B */
    public final FloatBuffer f4421B;

    /* renamed from: C */
    public final FloatBuffer f4422C;

    /* renamed from: H */
    public boolean f4427H;

    /* renamed from: J */
    public int f4429J;

    /* renamed from: K */
    public int f4430K;

    /* renamed from: L */
    public int f4431L;

    /* renamed from: M */
    public int f4432M;

    /* renamed from: N */
    public int f4433N;

    /* renamed from: O */
    public int f4434O;

    /* renamed from: P */
    public int f4435P;

    /* renamed from: Q */
    public int f4436Q;

    /* renamed from: t */
    public int[] f4443t;

    /* renamed from: u */
    public int[] f4444u;

    /* renamed from: v */
    public int[] f4445v;

    /* renamed from: w */
    public int[] f4446w;

    /* renamed from: x */
    public int[] f4447x;

    /* renamed from: y */
    public int[] f4448y;

    /* renamed from: z */
    public int[] f4449z;

    /* renamed from: D */
    public boolean f4423D = true;

    /* renamed from: E */
    public float f4424E = 30.0f;

    /* renamed from: F */
    public boolean f4425F = false;

    /* renamed from: G */
    public boolean f4426G = false;

    /* renamed from: I */
    public int f4428I = 4;

    /* renamed from: n */
    public C0936w0 f4437n = new C0936w0();

    /* renamed from: o */
    public C0936w0 f4438o = new C0936w0();

    /* renamed from: p */
    public C0936w0 f4439p = new C0936w0();

    /* renamed from: q */
    public C0936w0 f4440q = new C0936w0();

    /* renamed from: r */
    public C0887f f4441r = new C0887f();

    /* renamed from: s */
    public CLFocusEffectFilter f4442s = new CLFocusEffectFilter();

    /* renamed from: com.cyberlink.clgpuimage.c$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0878c.this.f4423D = true;
            C0878c.this.f4425F = false;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.c$b */
    public class b implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ float f4452c;

        public b(float f9) {
            this.f4452c = f9;
        }

        @Override // java.lang.Runnable
        public void run() {
            float fMin = Math.min(Math.max(this.f4452c, BitmapDescriptorFactory.HUE_RED), 100.0f);
            if (C0878c.this.f4424E != fMin) {
                C0878c.this.f4425F = false;
            }
            C0878c.this.f4424E = fMin;
            C0878c c0878c = C0878c.this;
            c0878c.f4427H = c0878c.f4424E > -1.0f;
        }
    }

    public C0878c() {
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4421B = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4422C = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f4427H = this.f4424E > -1.0f;
    }

    /* renamed from: A */
    public final void m4227A() {
        int[] iArr = this.f4444u;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4444u = null;
        }
        int[] iArr2 = this.f4443t;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4443t = null;
        }
        int[] iArr3 = this.f4446w;
        if (iArr3 != null) {
            GLES20.glDeleteTextures(iArr3.length, iArr3, 0);
            this.f4446w = null;
        }
        int[] iArr4 = this.f4445v;
        if (iArr4 != null) {
            GLES20.glDeleteFramebuffers(iArr4.length, iArr4, 0);
            this.f4445v = null;
        }
        int[] iArr5 = this.f4448y;
        if (iArr5 != null) {
            GLES20.glDeleteTextures(iArr5.length, iArr5, 0);
            this.f4448y = null;
        }
        int[] iArr6 = this.f4447x;
        if (iArr6 != null) {
            GLES20.glDeleteFramebuffers(iArr6.length, iArr6, 0);
            this.f4447x = null;
        }
        int[] iArr7 = this.f4420A;
        if (iArr7 != null) {
            GLES20.glDeleteTextures(iArr7.length, iArr7, 0);
            this.f4420A = null;
        }
        int[] iArr8 = this.f4449z;
        if (iArr8 != null) {
            GLES20.glDeleteFramebuffers(iArr8.length, iArr8, 0);
            this.f4449z = null;
        }
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: B */
    public final void m4228B(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, C0936w0 c0936w0, IntBuffer intBuffer, int i10, boolean z8) {
        GLES20.glBindFramebuffer(36160, i10);
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        GLES20.glClear(16384);
        GLES20.glViewport(intBuffer.get(0), intBuffer.get(1), intBuffer.get(2), intBuffer.get(3));
        if (z8) {
            c0936w0.mo4046j(i9, floatBuffer, floatBuffer2);
        } else {
            c0936w0.mo4046j(i9, this.f4421B, this.f4422C);
        }
    }

    /* renamed from: C */
    public final void m4229C(int[] iArr, int[] iArr2, int i9, int i10, int i11, int i12) {
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        GLES20.glGetIntegerv(32873, iArr3, 0);
        GLES20.glGetIntegerv(36006, iArr4, 0);
        GLES20.glGenFramebuffers(i10, iArr, i9);
        GLES20.glGenTextures(i10, iArr2, i9);
        for (int i13 = i9; i13 < i9 + i10; i13++) {
            GLES20.glBindTexture(3553, iArr2[i13]);
            GLES20.glTexImage2D(3553, 0, 6408, i11, i12, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, iArr[i13]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[i13], 0);
        }
        GLES20.glBindTexture(3553, iArr3[0]);
        GLES20.glBindFramebuffer(36160, iArr4[0]);
    }

    /* renamed from: D */
    public void m4230D(int i9, int i10) {
        m4406o(new a());
        this.f4441r.m4266J(i9, i10);
        this.f4442s.m4130C(i9, i10);
    }

    /* renamed from: E */
    public void m4231E(CLFocusEffectFilter.C0865f c0865f) {
        this.f4442s.m4134z(c0865f);
    }

    /* renamed from: F */
    public void m4232F(CLFocusEffectFilter.C0866g c0866g) {
        this.f4442s.m4128A(c0866g);
    }

    /* renamed from: G */
    public void m4233G(CLFocusEffectFilter.FocusMode focusMode) {
        this.f4442s.m4129B(focusMode);
    }

    /* renamed from: H */
    public void m4234H(CLFocusEffectFilter.C0867h c0867h) {
        this.f4442s.m4131D(c0867h);
    }

    /* renamed from: I */
    public void m4235I(float f9) {
        m4406o(new b(f9));
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4227A();
        this.f4437n.m4400c();
        this.f4438o.m4400c();
        this.f4439p.m4400c();
        this.f4440q.m4400c();
        this.f4441r.m4400c();
        this.f4442s.m4400c();
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        m4407p();
        if (!m4405h() || this.f4443t == null || this.f4444u == null) {
            return;
        }
        boolean z8 = this.f4427H;
        int i10 = z8 ? this.f4429J : this.f4797h;
        int i11 = z8 ? this.f4430K : this.f4798i;
        if (i10 != this.f4435P || i11 != this.f4436Q) {
            m4236z(i10, i11);
        }
        IntBuffer intBufferAllocate = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        IntBuffer intBufferAllocate3 = IntBuffer.allocate(4);
        if (this.f4427H) {
            float f9 = this.f4431L / this.f4797h;
            float f10 = this.f4432M / this.f4798i;
            intBufferAllocate2.clear();
            intBufferAllocate2.put(Math.round(intBufferAllocate.get(0) * f9));
            intBufferAllocate2.put(Math.round(intBufferAllocate.get(1) * f10));
            intBufferAllocate2.put(Math.round(f9 * intBufferAllocate.get(2)));
            intBufferAllocate2.put(Math.round(f10 * intBufferAllocate.get(3)));
            float f11 = this.f4433N / this.f4797h;
            float f12 = this.f4434O / this.f4798i;
            intBufferAllocate3.clear();
            intBufferAllocate3.put(Math.round(intBufferAllocate.get(0) * f11));
            intBufferAllocate3.put(Math.round(intBufferAllocate.get(1) * f12));
            intBufferAllocate3.put(Math.round(f11 * intBufferAllocate.get(2)));
            intBufferAllocate3.put(Math.round(f12 * intBufferAllocate.get(3)));
        }
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        float fM4261E = this.f4441r.m4261E();
        float f13 = this.f4424E;
        if (f13 > BitmapDescriptorFactory.HUE_RED && f13 != fM4261E) {
            this.f4441r.m4267K(f13);
        }
        if (!(this.f4423D && this.f4424E > BitmapDescriptorFactory.HUE_RED)) {
            m4228B(i9, floatBuffer, floatBuffer2, this.f4437n, intBufferAllocate, iArr[0], true);
            return;
        }
        if (!this.f4425F || this.f4426G) {
            if (this.f4427H) {
                m4228B(i9, floatBuffer, floatBuffer2, this.f4438o, intBufferAllocate2, this.f4445v[0], false);
                if (this.f4428I == 2) {
                    m4228B(this.f4446w[0], floatBuffer, floatBuffer2, this.f4441r, intBufferAllocate2, this.f4449z[0], false);
                } else {
                    m4228B(this.f4446w[0], floatBuffer, floatBuffer2, this.f4439p, intBufferAllocate3, this.f4447x[0], false);
                    m4228B(this.f4448y[0], floatBuffer, floatBuffer2, this.f4441r, intBufferAllocate3, this.f4449z[0], false);
                }
                m4228B(this.f4420A[0], floatBuffer, floatBuffer2, this.f4440q, intBufferAllocate, this.f4443t[0], false);
            } else {
                m4228B(i9, floatBuffer, floatBuffer2, this.f4441r, intBufferAllocate, this.f4443t[0], false);
            }
            this.f4425F = true;
        }
        this.f4442s.m4132E(i9);
        m4228B(this.f4444u[0], floatBuffer, floatBuffer2, this.f4442s, intBufferAllocate, iArr[0], true);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4437n.m4404g();
        this.f4438o.m4404g();
        this.f4439p.m4404g();
        this.f4440q.m4404g();
        this.f4441r.m4404g();
        this.f4442s.m4404g();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4443t != null) {
            m4227A();
        }
        int i11 = (i9 + 1) / 2;
        this.f4431L = i11;
        int i12 = (i10 + 1) / 2;
        this.f4432M = i12;
        int i13 = (i11 + 1) / 2;
        this.f4433N = i13;
        int i14 = (i12 + 1) / 2;
        this.f4434O = i14;
        if (this.f4428I == 2) {
            this.f4429J = i11;
            this.f4430K = i12;
        } else {
            this.f4429J = i13;
            this.f4430K = i14;
        }
        if (this.f4427H) {
            m4236z(this.f4429J, this.f4430K);
        } else {
            m4236z(i9, i10);
        }
        this.f4437n.mo4049n(i9, i10);
        this.f4438o.mo4049n(this.f4431L, this.f4432M);
        this.f4439p.mo4049n(this.f4433N, this.f4434O);
        this.f4440q.mo4049n(i9, i10);
        this.f4442s.mo4049n(i9, i10);
        int[] iArr = new int[1];
        this.f4443t = iArr;
        int[] iArr2 = new int[1];
        this.f4444u = iArr2;
        m4229C(iArr, iArr2, 0, 1, i9, i10);
        int[] iArr3 = new int[1];
        this.f4445v = iArr3;
        int[] iArr4 = new int[1];
        this.f4446w = iArr4;
        m4229C(iArr3, iArr4, 0, 1, this.f4431L, this.f4432M);
        if (this.f4428I == 4) {
            int[] iArr5 = new int[1];
            this.f4447x = iArr5;
            int[] iArr6 = new int[1];
            this.f4448y = iArr6;
            m4229C(iArr5, iArr6, 0, 1, this.f4433N, this.f4434O);
        }
        int[] iArr7 = new int[1];
        this.f4449z = iArr7;
        int[] iArr8 = new int[1];
        this.f4420A = iArr8;
        m4229C(iArr7, iArr8, 0, 1, this.f4429J, this.f4430K);
        this.f4425F = false;
    }

    /* renamed from: z */
    public final void m4236z(int i9, int i10) {
        this.f4441r.mo4049n(i9, i10);
        this.f4435P = i9;
        this.f4436Q = i10;
    }
}
