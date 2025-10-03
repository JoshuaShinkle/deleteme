package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* loaded from: classes.dex */
public class CLFocusEffectFilter extends C0936w0 {

    /* renamed from: A */
    public int f4148A;

    /* renamed from: B */
    public int f4149B;

    /* renamed from: C */
    public int f4150C;

    /* renamed from: D */
    public int f4151D;

    /* renamed from: E */
    public FocusMode f4152E;

    /* renamed from: F */
    public C0865f f4153F;

    /* renamed from: G */
    public C0867h f4154G;

    /* renamed from: H */
    public C0866g f4155H;

    /* renamed from: n */
    public int f4156n;

    /* renamed from: o */
    public int f4157o;

    /* renamed from: p */
    public int f4158p;

    /* renamed from: q */
    public int f4159q;

    /* renamed from: r */
    public int f4160r;

    /* renamed from: s */
    public int f4161s;

    /* renamed from: t */
    public int f4162t;

    /* renamed from: u */
    public int f4163u;

    /* renamed from: v */
    public int f4164v;

    /* renamed from: w */
    public int f4165w;

    /* renamed from: x */
    public int f4166x;

    /* renamed from: y */
    public int f4167y;

    /* renamed from: z */
    public int f4168z;

    public enum FocusMode {
        NONE,
        CIRCLE,
        LINEAR,
        ELLIPSE;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static FocusMode[] valuesCustom() {
            FocusMode[] focusModeArrValuesCustom = values();
            int length = focusModeArrValuesCustom.length;
            FocusMode[] focusModeArr = new FocusMode[length];
            System.arraycopy(focusModeArrValuesCustom, 0, focusModeArr, 0, length);
            return focusModeArr;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$a */
    public class RunnableC0860a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4175c;

        /* renamed from: d */
        public final /* synthetic */ int f4176d;

        public RunnableC0860a(int i9, int i10) {
            this.f4175c = i9;
            this.f4176d = i10;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLFocusEffectFilter.this.f4156n = this.f4175c;
            CLFocusEffectFilter.this.f4157o = this.f4176d;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$b */
    public class RunnableC0861b implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ FocusMode f4178c;

        public RunnableC0861b(FocusMode focusMode) {
            this.f4178c = focusMode;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLFocusEffectFilter.this.f4152E = this.f4178c;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$c */
    public class RunnableC0862c implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ C0865f f4180c;

        public RunnableC0862c(C0865f c0865f) {
            this.f4180c = c0865f;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLFocusEffectFilter.this.f4153F.f4185a = this.f4180c.f4185a;
            CLFocusEffectFilter.this.f4153F.f4186b = this.f4180c.f4186b;
            CLFocusEffectFilter.this.f4153F.f4187c = this.f4180c.f4187c;
            CLFocusEffectFilter.this.f4153F.f4188d = this.f4180c.f4188d;
            CLFocusEffectFilter.this.f4153F.f4189e = this.f4180c.f4189e;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$d */
    public class RunnableC0863d implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ C0867h f4182c;

        public RunnableC0863d(C0867h c0867h) {
            this.f4182c = c0867h;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLFocusEffectFilter.this.f4154G.f4196a = this.f4182c.f4196a;
            CLFocusEffectFilter.this.f4154G.f4197b = this.f4182c.f4197b;
            CLFocusEffectFilter.this.f4154G.f4198c = this.f4182c.f4198c;
            CLFocusEffectFilter.this.f4154G.f4199d = this.f4182c.f4199d;
            CLFocusEffectFilter.this.f4154G.f4200e = this.f4182c.f4200e;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$e */
    public class RunnableC0864e implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ C0866g f4184c;

        public RunnableC0864e(C0866g c0866g) {
            this.f4184c = c0866g;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLFocusEffectFilter.this.f4155H.f4190a = this.f4184c.f4190a;
            CLFocusEffectFilter.this.f4155H.f4191b = this.f4184c.f4191b;
            CLFocusEffectFilter.this.f4155H.f4192c = this.f4184c.f4192c;
            CLFocusEffectFilter.this.f4155H.f4193d = this.f4184c.f4193d;
            CLFocusEffectFilter.this.f4155H.f4194e = this.f4184c.f4194e;
            CLFocusEffectFilter.this.f4155H.f4195f = this.f4184c.f4195f;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$f */
    public static class C0865f {

        /* renamed from: a */
        public float f4185a;

        /* renamed from: b */
        public float f4186b;

        /* renamed from: c */
        public float f4187c;

        /* renamed from: d */
        public float f4188d;

        /* renamed from: e */
        public float f4189e;
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$g */
    public static class C0866g {

        /* renamed from: a */
        public float f4190a;

        /* renamed from: b */
        public float f4191b;

        /* renamed from: c */
        public float f4192c;

        /* renamed from: d */
        public float f4193d;

        /* renamed from: e */
        public float f4194e;

        /* renamed from: f */
        public float f4195f;
    }

    /* renamed from: com.cyberlink.clgpuimage.CLFocusEffectFilter$h */
    public static class C0867h {

        /* renamed from: a */
        public float f4196a;

        /* renamed from: b */
        public float f4197b;

        /* renamed from: c */
        public float f4198c;

        /* renamed from: d */
        public float f4199d;

        /* renamed from: e */
        public float f4200e;
    }

    public CLFocusEffectFilter() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 rootTextureCoordinate;\n\nvarying vec2 texture_coordinate;\nvarying vec2 root_texture_coordinate;\n\nvoid main()\n{\n    gl_Position = position;\n    texture_coordinate = inputTextureCoordinate.xy;\n    root_texture_coordinate = rootTextureCoordinate.xy;\n    \n}\n", "precision mediump float;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D root_image_texture;\nuniform int focus_mode;\n// shared parameters (may only use partial of them)\nuniform float center_x;\nuniform float center_y;\nuniform float inner_distance;\nuniform float outer_distance;\nuniform float poly_a;\nuniform float poly_b;\nuniform float poly_c;\nuniform float poly_d;\nuniform float poly_e;\nuniform float poly_f;\n// parameters for circle focus\nuniform float aspect_ratio;\n// parameters for linear focus\n// (none)\n// parameters for ellipse focus\n// (none)\n\n#ifdef GL_FRAGMENT_PRECISION_HIGH\nvarying highp vec2 texture_coordinate;\nvarying highp vec2 root_texture_coordinate;\n#else\nvarying vec2 texture_coordinate;\nvarying vec2 root_texture_coordinate;\n#endif\n\nvoid main()\n{\n    vec4 fragment_color = texture2D(inputImageTexture, texture_coordinate);\n    vec4 root_fragment_color = texture2D(root_image_texture, root_texture_coordinate);\n    float weight = 1.0;\n    float distance = outer_distance;\n    \n    if (focus_mode == 1) // circle shape\n    {\n        float center_x_distance = texture_coordinate.x - center_x;\n        float center_y_distance = (texture_coordinate.y - center_y) / aspect_ratio;\n        distance = sqrt(center_x_distance * center_x_distance + center_y_distance * center_y_distance);\n    }\n    else if (focus_mode == 2) // linear shape\n    {\n        distance = poly_a * texture_coordinate.x + poly_b * texture_coordinate.y + poly_c;\n        if (distance < 0.0)            distance = -distance;\n        distance *= poly_d;\n    }\n    else if (focus_mode == 3) // ellipse shape\n    {\n        float poly_x = poly_a * texture_coordinate.x + poly_b * texture_coordinate.y + poly_c;\n        float poly_y = poly_d * texture_coordinate.x + poly_e * texture_coordinate.y + poly_f;\n        distance = sqrt(poly_x * poly_x + poly_y * poly_y);\n    }\n    \n    if (distance < inner_distance)\n        weight = 0.0;\n    else if (distance < outer_distance)\n        weight = (distance - inner_distance) / (outer_distance - inner_distance);\n    \n    fragment_color.rgb *= weight;\n    fragment_color.rgb += root_fragment_color.rgb * (1.0 - weight);\n    gl_FragColor = fragment_color;\n}");
        this.f4156n = 640;
        this.f4157o = 640;
        this.f4160r = -1;
        this.f4152E = FocusMode.NONE;
        this.f4153F = new C0865f();
        this.f4154G = new C0867h();
        this.f4155H = new C0866g();
    }

    /* renamed from: A */
    public void m4128A(C0866g c0866g) {
        m4406o(new RunnableC0864e(c0866g));
    }

    /* renamed from: B */
    public void m4129B(FocusMode focusMode) {
        m4406o(new RunnableC0861b(focusMode));
    }

    /* renamed from: C */
    public void m4130C(int i9, int i10) {
        m4406o(new RunnableC0860a(i9, i10));
    }

    /* renamed from: D */
    public void m4131D(C0867h c0867h) {
        m4406o(new RunnableC0863d(c0867h));
    }

    /* renamed from: E */
    public void m4132E(int i9) {
        this.f4160r = i9;
    }

    /* renamed from: F */
    public final void m4133F() {
        if (this.f4160r == -1) {
            GLES20.glUniform1i(this.f4161s, 0);
            return;
        }
        FocusMode focusMode = this.f4152E;
        if (focusMode == FocusMode.NONE) {
            GLES20.glUniform1i(this.f4161s, 0);
            return;
        }
        if (focusMode == FocusMode.CIRCLE) {
            GLES20.glUniform1i(this.f4161s, 1);
            C0865f c0865f = this.f4153F;
            float f9 = c0865f.f4185a;
            int i9 = this.f4156n;
            float f10 = c0865f.f4186b;
            int i10 = this.f4157o;
            float f11 = c0865f.f4187c / i9;
            float f12 = c0865f.f4188d / i9;
            float f13 = (c0865f.f4189e * i9) / i10;
            GLES20.glUniform1f(this.f4162t, f9 / i9);
            GLES20.glUniform1f(this.f4163u, f10 / i10);
            GLES20.glUniform1f(this.f4164v, f11);
            GLES20.glUniform1f(this.f4165w, f12);
            GLES20.glUniform1f(this.f4151D, f13);
            return;
        }
        if (focusMode == FocusMode.LINEAR) {
            GLES20.glUniform1i(this.f4161s, 2);
            C0867h c0867h = this.f4154G;
            float f14 = c0867h.f4196a;
            int i11 = this.f4156n;
            float f15 = f14 / i11;
            float f16 = c0867h.f4197b;
            int i12 = this.f4157o;
            float f17 = f16 / i12;
            float f18 = c0867h.f4199d / i11;
            float f19 = c0867h.f4200e / i11;
            float f20 = i11 / i12;
            double d9 = -c0867h.f4198c;
            float fSin = (float) Math.sin(d9);
            float fCos = (float) Math.cos(d9);
            float f21 = fSin * f20;
            float fSqrt = (float) Math.sqrt((fCos * fCos) + (f21 * f21));
            float f22 = -fCos;
            float f23 = f20 / fSqrt;
            GLES20.glUniform1f(this.f4166x, f21);
            GLES20.glUniform1f(this.f4167y, f22);
            GLES20.glUniform1f(this.f4168z, ((-f21) * f15) - (f17 * f22));
            GLES20.glUniform1f(this.f4148A, 1.0f / fSqrt);
            GLES20.glUniform1f(this.f4164v, f18 * f23);
            GLES20.glUniform1f(this.f4165w, f19 * f23);
            return;
        }
        if (focusMode != FocusMode.ELLIPSE) {
            GLES20.glUniform1i(this.f4161s, 0);
            return;
        }
        GLES20.glUniform1i(this.f4161s, 3);
        C0866g c0866g = this.f4155H;
        float f24 = c0866g.f4190a;
        float f25 = c0866g.f4191b;
        float f26 = c0866g.f4192c;
        float f27 = c0866g.f4195f;
        float f28 = this.f4156n;
        float f29 = this.f4157o;
        double d10 = f26;
        float fSin2 = (float) Math.sin(d10);
        float fCos2 = (float) Math.cos(d10);
        float f30 = (-f29) * fSin2;
        float f31 = ((-f24) * fCos2) + (f25 * fSin2);
        float f32 = f28 * fSin2 * f27;
        float f33 = f29 * fCos2 * f27;
        float f34 = (-((f24 * fSin2) + (f25 * fCos2))) * f27;
        float fSqrt2 = (float) Math.sqrt(f28 * f29);
        C0866g c0866g2 = this.f4155H;
        float f35 = c0866g2.f4193d / fSqrt2;
        float f36 = c0866g2.f4194e / fSqrt2;
        GLES20.glUniform1f(this.f4166x, (f28 * fCos2) / fSqrt2);
        GLES20.glUniform1f(this.f4167y, f30 / fSqrt2);
        GLES20.glUniform1f(this.f4168z, f31 / fSqrt2);
        GLES20.glUniform1f(this.f4148A, f32 / fSqrt2);
        GLES20.glUniform1f(this.f4149B, f33 / fSqrt2);
        GLES20.glUniform1f(this.f4150C, f34 / fSqrt2);
        GLES20.glUniform1f(this.f4164v, f35);
        GLES20.glUniform1f(this.f4165w, f36);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (m4405h()) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(this.f4795f, 0);
            }
            if (this.f4160r != -1) {
                floatBuffer2.position(0);
                GLES20.glVertexAttribPointer(this.f4158p, 2, 5126, false, 0, (Buffer) floatBuffer2);
                GLES20.glEnableVertexAttribArray(this.f4158p);
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4160r);
                GLES20.glUniform1i(this.f4159q, 3);
            }
            m4133F();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4158p = GLES20.glGetAttribLocation(m4403f(), "rootTextureCoordinate");
        this.f4159q = GLES20.glGetUniformLocation(m4403f(), "root_image_texture");
        GLES20.glEnableVertexAttribArray(this.f4158p);
        this.f4161s = GLES20.glGetUniformLocation(m4403f(), "focus_mode");
        this.f4162t = GLES20.glGetUniformLocation(m4403f(), "center_x");
        this.f4163u = GLES20.glGetUniformLocation(m4403f(), "center_y");
        this.f4164v = GLES20.glGetUniformLocation(m4403f(), "inner_distance");
        this.f4165w = GLES20.glGetUniformLocation(m4403f(), "outer_distance");
        this.f4166x = GLES20.glGetUniformLocation(m4403f(), "poly_a");
        this.f4167y = GLES20.glGetUniformLocation(m4403f(), "poly_b");
        this.f4168z = GLES20.glGetUniformLocation(m4403f(), "poly_c");
        this.f4148A = GLES20.glGetUniformLocation(m4403f(), "poly_d");
        this.f4149B = GLES20.glGetUniformLocation(m4403f(), "poly_e");
        this.f4150C = GLES20.glGetUniformLocation(m4403f(), "poly_f");
        this.f4151D = GLES20.glGetUniformLocation(m4403f(), "aspect_ratio");
    }

    /* renamed from: z */
    public void m4134z(C0865f c0865f) {
        m4406o(new RunnableC0862c(c0865f));
    }
}
