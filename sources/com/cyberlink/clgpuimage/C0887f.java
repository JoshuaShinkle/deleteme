package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.exoplayer2.C3322C;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.commons.lang3.StringUtils;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.f */
/* loaded from: classes.dex */
public class C0887f extends C0936w0 {

    /* renamed from: T */
    public static final int f4471T;

    /* renamed from: U */
    public static final String f4472U;

    /* renamed from: A */
    public float[] f4473A;

    /* renamed from: B */
    public float[] f4474B;

    /* renamed from: C */
    public float[] f4475C;

    /* renamed from: D */
    public ByteBuffer f4476D;

    /* renamed from: E */
    public int[] f4477E;

    /* renamed from: F */
    public int[] f4478F;

    /* renamed from: G */
    public int[] f4479G;

    /* renamed from: H */
    public int f4480H;

    /* renamed from: I */
    public int f4481I;

    /* renamed from: J */
    public int f4482J;

    /* renamed from: K */
    public int f4483K;

    /* renamed from: L */
    public int f4484L;

    /* renamed from: M */
    public int f4485M;

    /* renamed from: N */
    public int f4486N;

    /* renamed from: O */
    public int f4487O;

    /* renamed from: P */
    public int f4488P;

    /* renamed from: Q */
    public int f4489Q;

    /* renamed from: R */
    public int f4490R;

    /* renamed from: S */
    public int f4491S;

    /* renamed from: n */
    public final FloatBuffer f4492n;

    /* renamed from: o */
    public final FloatBuffer f4493o;

    /* renamed from: p */
    public String f4494p;

    /* renamed from: q */
    public String f4495q;

    /* renamed from: r */
    public String f4496r;

    /* renamed from: s */
    public String f4497s;

    /* renamed from: t */
    public int f4498t;

    /* renamed from: u */
    public int f4499u;

    /* renamed from: v */
    public float f4500v;

    /* renamed from: w */
    public float f4501w;

    /* renamed from: x */
    public float f4502x;

    /* renamed from: y */
    public float f4503y;

    /* renamed from: z */
    public int f4504z;

    /* renamed from: com.cyberlink.clgpuimage.f$a */
    public class a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4506c;

        /* renamed from: d */
        public final /* synthetic */ int f4507d;

        public a(int i9, int i10) {
            this.f4506c = i9;
            this.f4507d = i10;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0887f.this.f4498t = this.f4506c;
            C0887f.this.f4499u = this.f4507d;
            C0887f.this.m4262F();
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.f$b */
    public class b implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ float f4509c;

        public b(float f9) {
            this.f4509c = f9;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0887f.this.f4500v = this.f4509c;
            C0887f c0887f = C0887f.this;
            c0887f.f4500v = Math.max(c0887f.f4500v, BitmapDescriptorFactory.HUE_RED);
            C0887f c0887f2 = C0887f.this;
            c0887f2.f4500v = Math.min(c0887f2.f4500v, 100.0f);
            C0887f.this.f4503y = (C0887f.this.f4500v / 100.0f) * 0.02f;
            C0887f.this.m4262F();
        }
    }

    static {
        int iM4248A = m4248A();
        f4471T = iM4248A;
        f4472U = "precision mediump float;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D weight_texture;\nuniform lowp int sample_count;\n\n#ifdef GL_FRAGMENT_PRECISION_HIGH\nconst highp float LOOKUP_TEXTURE_SIZE = " + String.format("%d", Integer.valueOf(iM4248A)) + ".0;\nvarying highp vec2 texture_coordinate;\nvarying highp vec2 unit_step;\n#else\nconst float LOOKUP_TEXTURE_SIZE = " + String.format("%d", Integer.valueOf(iM4248A)) + ".0;\nvarying vec2 texture_coordinate;\nvarying vec2 unit_step;\n#endif\n\nvoid main()\n{\n    #ifdef GL_FRAGMENT_PRECISION_HIGH\n    highp vec2 lookup_coordinate;\n    highp vec3 lookup_rgb;\n    highp float location;\n    highp float weight;\n    highp vec2 sample_step;\n    #else\n    vec2 lookup_coordinate;\n    vec3 lookup_rgb;\n    float location;\n    float weight;\n    vec2 sample_step;\n    #endif\n    \n    lowp vec4 fragment_color = texture2D(inputImageTexture, texture_coordinate);\n    \n    vec3 sum = vec3(0.0);\n    \n" + m4250N(4) + "}";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C0887f() {
        String str = f4472U;
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform int direction; // 0: horizontal, 1:vertical\nuniform float sample_step;\n\nvarying vec2 texture_coordinate;\nvarying vec2 unit_step;\n\nvoid main()\n{\n    gl_Position = position;\n    texture_coordinate = inputTextureCoordinate.xy;\n    \n    unit_step = vec2(0.0);\n    if (direction == 0)\n        unit_step.x = sample_step;\n    else\n        unit_step.y = sample_step;\n}\n", str);
        this.f4498t = 640;
        this.f4499u = 640;
        this.f4500v = 30.0f;
        this.f4501w = 1.0f / 640;
        this.f4502x = 1.0f / 640;
        this.f4503y = (30.0f * 0.02f) / 100.0f;
        this.f4504z = 37;
        this.f4473A = new float[73];
        this.f4494p = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform int direction; // 0: horizontal, 1:vertical\nuniform float sample_step;\n\nvarying vec2 texture_coordinate;\nvarying vec2 unit_step;\n\nvoid main()\n{\n    gl_Position = position;\n    texture_coordinate = inputTextureCoordinate.xy;\n    \n    unit_step = vec2(0.0);\n    if (direction == 0)\n        unit_step.x = sample_step;\n    else\n        unit_step.y = sample_step;\n}\n";
        this.f4495q = str;
        this.f4496r = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform int direction; // 0: horizontal, 1:vertical\nuniform float sample_step;\n\nvarying vec2 texture_coordinate;\nvarying vec2 unit_step;\n\nvoid main()\n{\n    gl_Position = position;\n    texture_coordinate = inputTextureCoordinate.xy;\n    \n    unit_step = vec2(0.0);\n    if (direction == 0)\n        unit_step.x = sample_step;\n    else\n        unit_step.y = sample_step;\n}\n";
        this.f4497s = str;
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4492n = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4493o = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f4474B = new float[37];
        this.f4475C = new float[37];
        int i9 = f4471T;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i9 * 3);
        this.f4476D = byteBufferAllocate;
        byteBufferAllocate.position(0);
        this.f4476D.put(new byte[i9 * 3]);
    }

    /* renamed from: A */
    public static int m4248A() {
        int i9 = 2;
        while (i9 < 37) {
            i9 *= 2;
        }
        return i9;
    }

    /* renamed from: L */
    public static String m4249L(int i9, int i10, int i11) {
        String str = "";
        for (int i12 = 0; i12 < i11; i12++) {
            str = String.valueOf(str) + StringUtils.SPACE;
        }
        StringBuilder sb = new StringBuilder(String.valueOf("" + str + String.format("for (int i = %d; i < %d; i++)\n", Integer.valueOf(i9), Integer.valueOf(i10))));
        sb.append(str);
        sb.append("{\n");
        StringBuilder sb2 = new StringBuilder(String.valueOf(String.valueOf(sb.toString()) + str + "    lookup_coordinate.x = (float(i) + 0.5) / LOOKUP_TEXTURE_SIZE;\n"));
        sb2.append(str);
        sb2.append("    location = float(i);\n");
        StringBuilder sb3 = new StringBuilder(String.valueOf(String.valueOf(sb2.toString()) + str + "    lookup_rgb = texture2D(weight_texture, lookup_coordinate).rgb;\n"));
        sb3.append(str);
        sb3.append("    weight = (lookup_rgb.b / 256.0 + lookup_rgb.g) / 256.0 + lookup_rgb.r;\n");
        StringBuilder sb4 = new StringBuilder(String.valueOf(String.valueOf(sb3.toString()) + str + "    \n"));
        sb4.append(str);
        sb4.append("    sample_step = unit_step * location;\n");
        StringBuilder sb5 = new StringBuilder(String.valueOf(String.valueOf(sb4.toString()) + str + "    sum += texture2D(inputImageTexture, texture_coordinate - sample_step).rgb * weight;\n"));
        sb5.append(str);
        sb5.append("    sum += texture2D(inputImageTexture, texture_coordinate + sample_step).rgb * weight;\n");
        return String.valueOf(sb5.toString()) + str + "}\n";
    }

    /* renamed from: N */
    public static String m4250N(int i9) {
        int iMin = Math.min(Math.max(i9, 1), 37);
        int[] iArr = new int[iMin + 1];
        iArr[0] = 0;
        iArr[iMin] = 37;
        for (int i10 = 1; i10 < iMin; i10++) {
            iArr[i10] = (((37 * i10) + (iMin - 1)) / iMin) + 0;
        }
        String string = "";
        int i11 = 0;
        while (i11 < iMin) {
            int i12 = i11 > 0 ? 8 : 4;
            StringBuilder sb = new StringBuilder(String.valueOf(string));
            int i13 = i11 + 1;
            sb.append(m4249L(iArr[i11], iArr[i13], i12));
            String string2 = sb.toString();
            if (i11 > 0) {
                string2 = String.valueOf(string2) + "    }\n";
            }
            if (i11 < iMin - 1) {
                string = String.valueOf(String.valueOf(String.valueOf(string2) + "    \n") + String.format("    if (sample_count > %d)\n", Integer.valueOf(iArr[i13]))) + "    {\n";
            } else {
                StringBuilder sb2 = new StringBuilder(String.valueOf(String.valueOf(string2) + "    \n"));
                sb2.append("    gl_FragColor = vec4(sum, fragment_color.a);\n");
                string = sb2.toString();
            }
            i11 = i13;
        }
        return string;
    }

    /* renamed from: z */
    public static void m4257z(ByteBuffer byteBuffer, int i9) {
        byteBuffer.put((byte) (Math.min(Math.max(i9, 0), 255) & 255));
    }

    /* renamed from: B */
    public final void m4258B(int i9, int i10) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        int[] iArr2 = new int[1];
        this.f4478F = iArr2;
        this.f4479G = new int[1];
        GLES20.glGenFramebuffers(1, iArr2, 0);
        GLES20.glGenTextures(1, this.f4479G, 0);
        GLES20.glBindTexture(3553, this.f4479G[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4478F[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4479G[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
    }

    /* renamed from: C */
    public final void m4259C() {
        int[] iArr = this.f4477E;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4477E = null;
        }
    }

    /* renamed from: D */
    public final void m4260D() {
        int[] iArr = this.f4479G;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4479G = null;
        }
        int[] iArr2 = this.f4478F;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4478F = null;
        }
    }

    /* renamed from: E */
    public float m4261E() {
        return this.f4500v;
    }

    /* renamed from: F */
    public final void m4262F() {
        int i9 = this.f4797h;
        float f9 = i9;
        int i10 = this.f4798i;
        float f10 = i10;
        float f11 = this.f4498t / this.f4499u;
        if (f11 > i9 / i10) {
            f10 = f9 / f11;
        } else {
            f9 = f10 * f11;
        }
        this.f4501w = 1.0f / f9;
        this.f4502x = 1.0f / f10;
        float fMin = this.f4503y * Math.min(f9, f10);
        int i11 = 36;
        float f12 = BitmapDescriptorFactory.HUE_RED;
        if (fMin > 1.0E-4f) {
            float f13 = 0.0f;
            for (int i12 = -36; i12 <= 36; i12++) {
                float f14 = i12 / fMin;
                float fExp = (float) Math.exp((-0.5f) * f14 * f14);
                this.f4473A[i12 + 36] = fExp;
                f13 += fExp;
            }
            if (f13 > BitmapDescriptorFactory.HUE_RED) {
                for (int i13 = 0; i13 < 73; i13++) {
                    float[] fArr = this.f4473A;
                    fArr[i13] = fArr[i13] / f13;
                }
            }
        } else {
            for (int i14 = 0; i14 < 73; i14++) {
                this.f4473A[i14] = 0.0f;
            }
            this.f4473A[36] = 1.0f;
        }
        this.f4504z = 37;
        this.f4474B[0] = 0.0f;
        this.f4475C[0] = this.f4473A[36];
        for (int i15 = 1; i15 <= 36; i15++) {
            this.f4474B[i15] = i15;
            this.f4475C[i15] = this.f4473A[i15 + 36];
        }
        while (i11 >= 7) {
            float[] fArr2 = this.f4475C;
            if (fArr2[i11] / fArr2[0] > 0.03125f) {
                break;
            } else {
                i11--;
            }
        }
        int i16 = i11 + 1;
        this.f4504z = i16;
        if (i16 < 37) {
            float f15 = 0.0f;
            for (int i17 = 1; i17 < 37; i17++) {
                if (i17 < this.f4504z) {
                    f15 += this.f4475C[i17];
                } else {
                    this.f4475C[i17] = 0.0f;
                }
            }
            float f16 = this.f4475C[0] + (f15 * 2.0f);
            for (int i18 = 0; i18 < this.f4504z; i18++) {
                float[] fArr3 = this.f4475C;
                fArr3[i18] = fArr3[i18] / f16;
            }
        }
        for (int i19 = 1; i19 < this.f4504z; i19++) {
            f12 += this.f4475C[i19];
        }
        this.f4475C[0] = (1.0f - (f12 * 2.0f)) * 0.5f;
        m4265I();
    }

    /* renamed from: G */
    public final void m4263G() {
        int[] iArr = new int[1];
        this.f4477E = iArr;
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, this.f4477E[0]);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glTexImage2D(3553, 0, 6407, f4471T, 1, 0, 6407, 5121, null);
        GLES20.glBindTexture(3553, 0);
        this.f4487O = GLES20.glGetUniformLocation(this.f4480H, "weight_texture");
        this.f4491S = GLES20.glGetUniformLocation(m4403f(), "weight_texture");
    }

    /* renamed from: H */
    public void m4264H(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        GLES20.glUseProgram(this.f4480H);
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        GLES20.glBindFramebuffer(36160, this.f4478F[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4481I, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4481I);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4483K, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4483K);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f4482J, 0);
        }
        int[] iArr = this.f4477E;
        if (iArr != null && iArr[0] != -1) {
            GLES20.glActiveTexture(33989);
            GLES20.glBindTexture(3553, this.f4477E[0]);
            GLES20.glUniform1i(this.f4487O, 5);
        }
        GLES20.glUniform1i(this.f4484L, 0);
        GLES20.glUniform1f(this.f4485M, this.f4501w);
        GLES20.glUniform1i(this.f4486N, this.f4504z);
        mo4057k();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4481I);
        GLES20.glDisableVertexAttribArray(this.f4483K);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
    }

    /* renamed from: I */
    public final void m4265I() {
        int i9;
        this.f4476D.position(0);
        int i10 = 0;
        while (true) {
            i9 = this.f4504z;
            if (i10 >= i9) {
                break;
            }
            int iRound = (int) Math.round(this.f4475C[i10] * 1.671168E7d);
            int i11 = iRound / C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
            int i12 = iRound - (C3322C.DEFAULT_BUFFER_SEGMENT_SIZE * i11);
            int i13 = i12 / 256;
            m4257z(this.f4476D, i11);
            m4257z(this.f4476D, i13);
            m4257z(this.f4476D, i12 - (i13 * 256));
            i10++;
        }
        while (i9 < 37) {
            this.f4476D.put((byte) 0);
            this.f4476D.put((byte) 0);
            this.f4476D.put((byte) 0);
            i9++;
        }
    }

    /* renamed from: J */
    public void m4266J(int i9, int i10) {
        m4406o(new a(i9, i10));
    }

    /* renamed from: K */
    public void m4267K(float f9) {
        m4406o(new b(f9));
    }

    /* renamed from: M */
    public final void m4268M() {
        if (this.f4477E == null) {
            return;
        }
        this.f4476D.position(0);
        GLES20.glBindTexture(3553, this.f4477E[0]);
        GLES20.glTexSubImage2D(3553, 0, 0, 0, f4471T, 1, 6407, 5121, this.f4476D);
        GLES20.glBindTexture(3553, 0);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4260D();
        m4259C();
        GLES20.glDeleteProgram(this.f4480H);
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!m4405h() || this.f4478F == null || this.f4479G == null) {
            return;
        }
        m4407p();
        m4268M();
        m4264H(i9, this.f4492n, this.f4493o);
        GLES20.glUseProgram(this.f4793d);
        if (m4405h()) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (this.f4479G[0] != -1) {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4479G[0]);
                GLES20.glUniform1i(this.f4795f, 3);
            }
            int[] iArr = this.f4477E;
            if (iArr != null && iArr[0] != -1) {
                GLES20.glActiveTexture(33989);
                GLES20.glBindTexture(3553, this.f4477E[0]);
                GLES20.glUniform1i(this.f4491S, 5);
            }
            GLES20.glUniform1i(this.f4488P, 1);
            GLES20.glUniform1f(this.f4489Q, this.f4502x);
            GLES20.glUniform1i(this.f4490R, this.f4504z);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        int iM4377c = C0925r1.m4377c(this.f4494p, this.f4495q);
        this.f4480H = iM4377c;
        this.f4481I = GLES20.glGetAttribLocation(iM4377c, "position");
        this.f4482J = GLES20.glGetUniformLocation(this.f4480H, "inputImageTexture");
        this.f4483K = GLES20.glGetAttribLocation(this.f4480H, "inputTextureCoordinate");
        this.f4484L = GLES20.glGetUniformLocation(this.f4480H, "direction");
        this.f4485M = GLES20.glGetUniformLocation(this.f4480H, "sample_step");
        this.f4486N = GLES20.glGetUniformLocation(this.f4480H, "sample_count");
        super.mo4047l();
        this.f4488P = GLES20.glGetUniformLocation(m4403f(), "direction");
        this.f4489Q = GLES20.glGetUniformLocation(m4403f(), "sample_step");
        this.f4490R = GLES20.glGetUniformLocation(m4403f(), "sample_count");
        m4263G();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4478F != null) {
            m4260D();
        }
        m4258B(i9, i10);
        m4262F();
    }
}
