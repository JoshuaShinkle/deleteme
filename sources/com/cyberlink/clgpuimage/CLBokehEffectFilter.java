package com.cyberlink.clgpuimage;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import com.cyberlink.clgpuimage.CLBokehFilter;
import com.cyberlink.clgpuimage.CLFocusEffectFilter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import p253z1.C6816a;

/* loaded from: classes.dex */
public class CLBokehEffectFilter extends C0936w0 {

    /* renamed from: A */
    public int[] f4035A;

    /* renamed from: B */
    public int[] f4036B;

    /* renamed from: C */
    public int[] f4037C;

    /* renamed from: D */
    public int[] f4038D;

    /* renamed from: K */
    public int f4045K;

    /* renamed from: L */
    public int f4046L;

    /* renamed from: M */
    public int f4047M;

    /* renamed from: N */
    public int f4048N;

    /* renamed from: O */
    public int f4049O;

    /* renamed from: P */
    public int f4050P;

    /* renamed from: Q */
    public int f4051Q;

    /* renamed from: R */
    public int f4052R;

    /* renamed from: S */
    public final FloatBuffer f4053S;

    /* renamed from: T */
    public final FloatBuffer f4054T;

    /* renamed from: Y */
    public boolean f4059Y;

    /* renamed from: Z */
    public QualityLevel f4060Z;

    /* renamed from: s */
    public CLBokehFilter f4067s;

    /* renamed from: u */
    public int[] f4069u;

    /* renamed from: v */
    public int[] f4070v;

    /* renamed from: w */
    public int[] f4071w;

    /* renamed from: x */
    public int[] f4072x;

    /* renamed from: y */
    public int[] f4073y;

    /* renamed from: z */
    public int[] f4074z;

    /* renamed from: E */
    public int f4039E = 640;

    /* renamed from: F */
    public int f4040F = 640;

    /* renamed from: G */
    public int f4041G = 640;

    /* renamed from: H */
    public int f4042H = 640;

    /* renamed from: I */
    public int f4043I = 8;

    /* renamed from: J */
    public CLBokehFilter.KernelMode f4044J = CLBokehFilter.KernelMode.NORMAL;

    /* renamed from: U */
    public boolean f4055U = true;

    /* renamed from: V */
    public float f4056V = 50.0f;

    /* renamed from: W */
    public boolean f4057W = false;

    /* renamed from: X */
    public boolean f4058X = false;

    /* renamed from: a0 */
    public ProcessMode f4061a0 = ProcessMode.PREVIEW;

    /* renamed from: n */
    public C0936w0 f4062n = new C0936w0();

    /* renamed from: o */
    public C0881d f4063o = new C0881d();

    /* renamed from: p */
    public C0881d f4064p = new C0881d();

    /* renamed from: q */
    public C0881d f4065q = new C0881d();

    /* renamed from: r */
    public C0936w0 f4066r = new C0936w0();

    /* renamed from: t */
    public CLFocusEffectFilter f4068t = new CLFocusEffectFilter();

    public enum BokehMode {
        HEART,
        CIRCLE,
        CROSS,
        STAR;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static BokehMode[] valuesCustom() {
            BokehMode[] bokehModeArrValuesCustom = values();
            int length = bokehModeArrValuesCustom.length;
            BokehMode[] bokehModeArr = new BokehMode[length];
            System.arraycopy(bokehModeArrValuesCustom, 0, bokehModeArr, 0, length);
            return bokehModeArr;
        }
    }

    public enum ProcessMode {
        PREVIEW,
        FAST_PREVIEW,
        PRODUCTION;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static ProcessMode[] valuesCustom() {
            ProcessMode[] processModeArrValuesCustom = values();
            int length = processModeArrValuesCustom.length;
            ProcessMode[] processModeArr = new ProcessMode[length];
            System.arraycopy(processModeArrValuesCustom, 0, processModeArr, 0, length);
            return processModeArr;
        }
    }

    public enum QualityLevel {
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4,
        DETECTION,
        INVALID;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static QualityLevel[] valuesCustom() {
            QualityLevel[] qualityLevelArrValuesCustom = values();
            int length = qualityLevelArrValuesCustom.length;
            QualityLevel[] qualityLevelArr = new QualityLevel[length];
            System.arraycopy(qualityLevelArrValuesCustom, 0, qualityLevelArr, 0, length);
            return qualityLevelArr;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLBokehEffectFilter$a */
    public class RunnableC0855a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4092c;

        /* renamed from: d */
        public final /* synthetic */ int f4093d;

        public RunnableC0855a(int i9, int i10) {
            this.f4092c = i9;
            this.f4093d = i10;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLBokehEffectFilter.this.m4077C(this.f4092c, this.f4093d);
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLBokehEffectFilter$b */
    public class RunnableC0856b implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ float f4095c;

        public RunnableC0856b(float f9) {
            this.f4095c = f9;
        }

        @Override // java.lang.Runnable
        public void run() {
            float fMin = Math.min(Math.max(this.f4095c, BitmapDescriptorFactory.HUE_RED), 100.0f);
            if (CLBokehEffectFilter.this.f4056V != fMin) {
                CLBokehEffectFilter.this.f4057W = false;
            }
            CLBokehEffectFilter.this.f4056V = fMin;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLBokehEffectFilter$c */
    public class RunnableC0857c implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ ProcessMode f4097c;

        public RunnableC0857c(ProcessMode processMode) {
            this.f4097c = processMode;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLBokehEffectFilter.this.f4061a0 = this.f4097c;
            int i9 = CLBokehEffectFilter.this.f4043I;
            CLBokehEffectFilter.this.m4076B();
            if (!(CLBokehEffectFilter.this.f4069u != null) || CLBokehEffectFilter.this.f4043I == i9) {
                return;
            }
            CLBokehEffectFilter.this.m4088N();
            if (CLBokehEffectFilter.this.f4038D != null) {
                GLES20.glDeleteTextures(CLBokehEffectFilter.this.f4038D.length, CLBokehEffectFilter.this.f4038D, 0);
                CLBokehEffectFilter.this.f4038D = null;
            }
            if (CLBokehEffectFilter.this.f4037C != null) {
                GLES20.glDeleteFramebuffers(CLBokehEffectFilter.this.f4037C.length, CLBokehEffectFilter.this.f4037C, 0);
                CLBokehEffectFilter.this.f4037C = null;
            }
            CLBokehEffectFilter.this.f4037C = new int[1];
            CLBokehEffectFilter.this.f4038D = new int[1];
            C0884e.m4244a(CLBokehEffectFilter.this.f4037C, CLBokehEffectFilter.this.f4038D, 0, 1, CLBokehEffectFilter.this.f4045K, CLBokehEffectFilter.this.f4046L);
            CLBokehEffectFilter.this.f4067s.mo4049n(CLBokehEffectFilter.this.f4045K, CLBokehEffectFilter.this.f4046L);
            CLBokehEffectFilter.this.f4057W = false;
            CLBokehEffectFilter.this.f4058X = false;
        }
    }

    public CLBokehEffectFilter(QualityLevel qualityLevel) {
        this.f4059Y = false;
        this.f4067s = new CLBokehFilter(qualityLevel);
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4053S = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4054T = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f4060Z = qualityLevel;
        this.f4059Y = qualityLevel == QualityLevel.DETECTION;
    }

    /* renamed from: A */
    public final void m4075A() {
        int[] iArr = this.f4070v;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4070v = null;
        }
        int[] iArr2 = this.f4069u;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4069u = null;
        }
        int[] iArr3 = this.f4072x;
        if (iArr3 != null) {
            GLES20.glDeleteTextures(iArr3.length, iArr3, 0);
            this.f4072x = null;
        }
        int[] iArr4 = this.f4071w;
        if (iArr4 != null) {
            GLES20.glDeleteFramebuffers(iArr4.length, iArr4, 0);
            this.f4071w = null;
        }
        int[] iArr5 = this.f4074z;
        if (iArr5 != null) {
            GLES20.glDeleteTextures(iArr5.length, iArr5, 0);
            this.f4074z = null;
        }
        int[] iArr6 = this.f4073y;
        if (iArr6 != null) {
            GLES20.glDeleteFramebuffers(iArr6.length, iArr6, 0);
            this.f4073y = null;
        }
        int[] iArr7 = this.f4036B;
        if (iArr7 != null) {
            GLES20.glDeleteTextures(iArr7.length, iArr7, 0);
            this.f4036B = null;
        }
        int[] iArr8 = this.f4035A;
        if (iArr8 != null) {
            GLES20.glDeleteFramebuffers(iArr8.length, iArr8, 0);
            this.f4035A = null;
        }
        int[] iArr9 = this.f4038D;
        if (iArr9 != null) {
            GLES20.glDeleteTextures(iArr9.length, iArr9, 0);
            this.f4038D = null;
        }
        int[] iArr10 = this.f4037C;
        if (iArr10 != null) {
            GLES20.glDeleteFramebuffers(iArr10.length, iArr10, 0);
            this.f4037C = null;
        }
    }

    /* renamed from: B */
    public final void m4076B() {
        int iMax = Math.max(this.f4039E, this.f4040F);
        int i9 = iMax * 2;
        int i10 = (iMax + 1) / 2;
        int i11 = (i10 + 1) / 2;
        ProcessMode processMode = this.f4061a0;
        boolean z8 = processMode == ProcessMode.FAST_PREVIEW;
        boolean z9 = processMode == ProcessMode.PRODUCTION;
        QualityLevel qualityLevel = this.f4060Z;
        if (qualityLevel == QualityLevel.LEVEL_1) {
            if (i10 <= 200) {
                this.f4043I = 2;
                return;
            } else if (i11 <= 200) {
                this.f4043I = 4;
                return;
            } else {
                this.f4043I = 8;
                return;
            }
        }
        if (qualityLevel == QualityLevel.LEVEL_2) {
            if (iMax <= 400) {
                this.f4043I = 2;
                return;
            }
            if (i10 <= 400) {
                if (z8) {
                    this.f4043I = 4;
                    return;
                } else if (z9) {
                    this.f4043I = 2;
                    return;
                } else {
                    this.f4043I = 4;
                    return;
                }
            }
            if (i11 > 400) {
                this.f4043I = 8;
                return;
            }
            if (z8) {
                this.f4043I = 8;
                return;
            } else if (z9) {
                this.f4043I = 4;
                return;
            } else {
                this.f4043I = 4;
                return;
            }
        }
        if (qualityLevel == QualityLevel.LEVEL_3) {
            if (i9 <= 800) {
                this.f4043I = 2;
                return;
            }
            if (iMax <= 800) {
                if (z8) {
                    this.f4043I = 4;
                    return;
                } else if (z9) {
                    this.f4043I = 2;
                    return;
                } else {
                    this.f4043I = 4;
                    return;
                }
            }
            if (i10 > 800) {
                this.f4043I = 8;
                return;
            }
            if (z8) {
                this.f4043I = 8;
                return;
            } else if (z9) {
                this.f4043I = 4;
                return;
            } else {
                this.f4043I = 4;
                return;
            }
        }
        if (qualityLevel != QualityLevel.LEVEL_4) {
            if (qualityLevel == QualityLevel.DETECTION) {
                this.f4043I = 4;
                return;
            }
            return;
        }
        if (iMax <= 800) {
            this.f4043I = 2;
            return;
        }
        if (i10 <= 800) {
            if (z8) {
                this.f4043I = 4;
                return;
            } else if (z9) {
                this.f4043I = 2;
                return;
            } else {
                this.f4043I = 4;
                return;
            }
        }
        if (i11 > 800) {
            this.f4043I = 8;
            return;
        }
        if (z8) {
            this.f4043I = 8;
        } else if (z9) {
            this.f4043I = 4;
        } else {
            this.f4043I = 4;
        }
    }

    /* renamed from: C */
    public final void m4077C(int i9, int i10) {
        this.f4039E = i9;
        this.f4040F = i10;
        this.f4055U = true;
        this.f4057W = false;
        this.f4058X = false;
        m4076B();
        m4075A();
        m4091z();
        this.f4067s.mo4049n(this.f4045K, this.f4046L);
        m4090P();
        this.f4066r.mo4049n(this.f4041G, this.f4042H);
        m4079E();
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: D */
    public final void m4078D(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2, C0936w0 c0936w0, IntBuffer intBuffer, int i10, boolean z8) {
        GLES20.glBindFramebuffer(36160, i10);
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        GLES20.glClear(16384);
        GLES20.glViewport(intBuffer.get(0), intBuffer.get(1), intBuffer.get(2), intBuffer.get(3));
        if (z8) {
            c0936w0.mo4046j(i9, floatBuffer, floatBuffer2);
        } else {
            c0936w0.mo4046j(i9, this.f4053S, this.f4054T);
        }
    }

    /* renamed from: E */
    public final void m4079E() {
        int[] iArr = new int[1];
        this.f4069u = iArr;
        int[] iArr2 = new int[1];
        this.f4070v = iArr2;
        C0884e.m4244a(iArr, iArr2, 0, 1, this.f4041G, this.f4042H);
        int[] iArr3 = new int[1];
        this.f4071w = iArr3;
        int[] iArr4 = new int[1];
        this.f4072x = iArr4;
        C0884e.m4244a(iArr3, iArr4, 0, 1, this.f4047M, this.f4048N);
        int[] iArr5 = new int[1];
        this.f4073y = iArr5;
        int[] iArr6 = new int[1];
        this.f4074z = iArr6;
        C0884e.m4244a(iArr5, iArr6, 0, 1, this.f4049O, this.f4050P);
        int[] iArr7 = new int[1];
        this.f4035A = iArr7;
        int[] iArr8 = new int[1];
        this.f4036B = iArr8;
        C0884e.m4244a(iArr7, iArr8, 0, 1, this.f4051Q, this.f4052R);
        int[] iArr9 = new int[1];
        this.f4037C = iArr9;
        int[] iArr10 = new int[1];
        this.f4038D = iArr10;
        C0884e.m4244a(iArr9, iArr10, 0, 1, this.f4045K, this.f4046L);
        this.f4057W = false;
        this.f4058X = false;
    }

    /* renamed from: F */
    public void m4080F(int i9, int i10) {
        m4406o(new RunnableC0855a(i9, i10));
        this.f4068t.m4130C(i9, i10);
    }

    /* renamed from: G */
    public final int m4081G(int i9, int i10) {
        return ((i9 + i10) - 1) / i10;
    }

    /* renamed from: H */
    public void m4082H(BokehMode bokehMode) {
        if (bokehMode != this.f4067s.m4108I()) {
            this.f4057W = false;
        }
        this.f4067s.m4117U(bokehMode);
    }

    /* renamed from: I */
    public void m4083I(CLFocusEffectFilter.C0865f c0865f) {
        this.f4068t.m4134z(c0865f);
    }

    /* renamed from: J */
    public void m4084J(CLFocusEffectFilter.C0866g c0866g) {
        this.f4068t.m4128A(c0866g);
    }

    /* renamed from: K */
    public void m4085K(CLFocusEffectFilter.FocusMode focusMode) {
        this.f4068t.m4129B(focusMode);
    }

    /* renamed from: L */
    public void m4086L(CLFocusEffectFilter.C0867h c0867h) {
        this.f4068t.m4131D(c0867h);
    }

    /* renamed from: M */
    public void m4087M(ProcessMode processMode) {
        m4406o(new RunnableC0857c(processMode));
    }

    /* renamed from: N */
    public final void m4088N() {
        int i9 = this.f4043I;
        if (i9 == 2) {
            this.f4045K = this.f4047M;
            this.f4046L = this.f4048N;
        } else if (i9 == 4) {
            this.f4045K = this.f4049O;
            this.f4046L = this.f4050P;
        } else {
            this.f4045K = this.f4051Q;
            this.f4046L = this.f4052R;
        }
    }

    /* renamed from: O */
    public void m4089O(float f9) {
        m4406o(new RunnableC0856b(f9));
    }

    /* renamed from: P */
    public final void m4090P() {
        this.f4063o.mo4049n(this.f4047M, this.f4048N);
        this.f4063o.m4241A(this.f4041G, this.f4042H);
        this.f4064p.mo4049n(this.f4049O, this.f4050P);
        this.f4064p.m4241A(this.f4047M, this.f4048N);
        this.f4065q.mo4049n(this.f4051Q, this.f4052R);
        this.f4065q.m4241A(this.f4049O, this.f4050P);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4075A();
        this.f4062n.m4400c();
        this.f4063o.m4400c();
        this.f4064p.m4400c();
        this.f4065q.m4400c();
        this.f4066r.m4400c();
        this.f4067s.m4400c();
        this.f4068t.m4400c();
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int i10;
        IntBuffer intBuffer;
        m4407p();
        if (!m4405h() || this.f4069u == null || this.f4070v == null || this.f4044J == CLBokehFilter.KernelMode.INVALID || this.f4060Z == QualityLevel.INVALID) {
            return;
        }
        IntBuffer intBufferAllocate = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate);
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        float fM4110K = this.f4067s.m4110K();
        float f9 = this.f4056V;
        if (f9 > BitmapDescriptorFactory.HUE_RED && f9 != fM4110K) {
            this.f4067s.m4118V(f9);
        }
        int[] iArr2 = {0, 0, this.f4041G, this.f4042H};
        int[] iArr3 = {0, 0, this.f4047M, this.f4048N};
        int[] iArr4 = {0, 0, this.f4049O, this.f4050P};
        int[] iArr5 = {0, 0, this.f4051Q, this.f4052R};
        IntBuffer intBufferWrap = IntBuffer.wrap(iArr2);
        IntBuffer intBufferWrap2 = IntBuffer.wrap(iArr3);
        IntBuffer intBufferWrap3 = IntBuffer.wrap(iArr4);
        IntBuffer intBufferWrap4 = IntBuffer.wrap(iArr5);
        if (!(this.f4055U && this.f4056V > BitmapDescriptorFactory.HUE_RED)) {
            m4078D(i9, floatBuffer, floatBuffer2, this.f4062n, intBufferAllocate, iArr[0], true);
            return;
        }
        if (!this.f4057W || this.f4059Y) {
            int i11 = this.f4072x[0];
            int i12 = this.f4043I;
            if (i12 == 4) {
                i10 = this.f4074z[0];
                intBuffer = intBufferWrap3;
            } else if (i12 == 8) {
                i10 = this.f4036B[0];
                intBuffer = intBufferWrap4;
            } else {
                i10 = i11;
                intBuffer = intBufferWrap2;
            }
            if (!this.f4058X) {
                m4078D(i9, floatBuffer, floatBuffer2, this.f4063o, intBufferWrap2, this.f4071w[0], false);
                int i13 = this.f4043I;
                if (i13 == 4 || i13 == 8) {
                    m4078D(this.f4072x[0], floatBuffer, floatBuffer2, this.f4064p, intBufferWrap3, this.f4073y[0], false);
                }
                if (this.f4043I == 8) {
                    m4078D(this.f4074z[0], floatBuffer, floatBuffer2, this.f4065q, intBufferWrap4, this.f4035A[0], false);
                }
                this.f4058X = true;
            }
            m4078D(i10, floatBuffer, floatBuffer2, this.f4067s, intBuffer, this.f4037C[0], false);
            m4078D(this.f4038D[0], floatBuffer, floatBuffer2, this.f4066r, intBufferWrap, this.f4069u[0], false);
            this.f4057W = true;
        }
        this.f4068t.m4132E(i9);
        m4078D(this.f4070v[0], floatBuffer, floatBuffer2, this.f4068t, intBufferAllocate, iArr[0], true);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4062n.m4404g();
        this.f4063o.m4404g();
        this.f4064p.m4404g();
        this.f4065q.m4404g();
        this.f4066r.m4404g();
        this.f4067s.m4404g();
        this.f4068t.m4404g();
        this.f4044J = this.f4067s.m4109J();
        m4077C(this.f4039E, this.f4040F);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        this.f4062n.mo4049n(i9, i10);
        this.f4068t.mo4049n(i9, i10);
    }

    /* renamed from: z */
    public final void m4091z() {
        int i9 = this.f4039E;
        this.f4041G = i9;
        this.f4042H = this.f4040F;
        this.f4047M = m4081G(i9, 2);
        this.f4048N = m4081G(this.f4042H, 2);
        this.f4049O = m4081G(this.f4047M, 2);
        this.f4050P = m4081G(this.f4048N, 2);
        this.f4051Q = m4081G(this.f4049O, 2);
        this.f4052R = m4081G(this.f4050P, 2);
        m4088N();
    }
}
