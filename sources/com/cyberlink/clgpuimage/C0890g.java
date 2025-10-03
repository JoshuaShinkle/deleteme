package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.g */
/* loaded from: classes.dex */
public class C0890g extends C0936w0 {

    /* renamed from: A */
    public int f4511A;

    /* renamed from: B */
    public int f4512B;

    /* renamed from: C */
    public int f4513C;

    /* renamed from: D */
    public int f4514D;

    /* renamed from: E */
    public int f4515E;

    /* renamed from: F */
    public int f4516F;

    /* renamed from: G */
    public int[] f4517G;

    /* renamed from: H */
    public int[] f4518H;

    /* renamed from: I */
    public int f4519I;

    /* renamed from: J */
    public int f4520J;

    /* renamed from: K */
    public int f4521K;

    /* renamed from: L */
    public int f4522L;

    /* renamed from: M */
    public int f4523M;

    /* renamed from: N */
    public int f4524N;

    /* renamed from: O */
    public int f4525O;

    /* renamed from: P */
    public int f4526P;

    /* renamed from: Q */
    public int f4527Q;

    /* renamed from: R */
    public int f4528R;

    /* renamed from: S */
    public int f4529S;

    /* renamed from: T */
    public int f4530T;

    /* renamed from: U */
    public int f4531U;

    /* renamed from: V */
    public int f4532V;

    /* renamed from: W */
    public int f4533W;

    /* renamed from: X */
    public int f4534X;

    /* renamed from: Y */
    public float f4535Y;

    /* renamed from: Z */
    public float f4536Z;

    /* renamed from: a0 */
    public float f4537a0;

    /* renamed from: b0 */
    public float f4538b0;

    /* renamed from: c0 */
    public float f4539c0;

    /* renamed from: d0 */
    public Object f4540d0;

    /* renamed from: n */
    public final FloatBuffer f4541n;

    /* renamed from: o */
    public final FloatBuffer f4542o;

    /* renamed from: p */
    public String f4543p;

    /* renamed from: q */
    public String f4544q;

    /* renamed from: r */
    public String f4545r;

    /* renamed from: s */
    public String f4546s;

    /* renamed from: t */
    public String f4547t;

    /* renamed from: u */
    public String f4548u;

    /* renamed from: v */
    public int f4549v;

    /* renamed from: w */
    public int f4550w;

    /* renamed from: x */
    public int f4551x;

    /* renamed from: y */
    public int[] f4552y;

    /* renamed from: z */
    public int[] f4553z;

    /* renamed from: com.cyberlink.clgpuimage.g$a */
    public static class a {

        /* renamed from: a */
        public static String f4554a = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: b */
        public static String f4555b = "precision mediump float;varying vec2 texture_coordinate;uniform sampler2D inputImageTexture;uniform vec2 sampling_offset_start;uniform vec2 sampling_step;void main(){    vec4 center = texture2D(inputImageTexture, texture_coordinate);    center.a = center.g * center.g;    vec4 average_info = center;    const int one_side_sampling_number = 6;    vec2 sampling_offset = sampling_offset_start;    for (int i = 0; i < one_side_sampling_number; i++)    {        vec4 surround_0 = texture2D(inputImageTexture, texture_coordinate + sampling_offset);        surround_0.a = surround_0.g * surround_0.g;        vec4 surround_1 = texture2D(inputImageTexture, texture_coordinate - sampling_offset);        surround_1.a = surround_1.g * surround_1.g;        average_info += surround_0 + surround_1;        sampling_offset += sampling_step;    }    average_info /= float(2 * one_side_sampling_number + 1);    gl_FragColor = average_info;}";

        /* renamed from: c */
        public static String f4556c = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: d */
        public static String f4557d = "precision mediump float;varying vec2 texture_coordinate;uniform sampler2D inputImageTexture;uniform vec2 sampling_offset_start;uniform vec2 sampling_step;void main(){    vec4 average_info = texture2D(inputImageTexture, texture_coordinate);    const int one_side_sampling_number = 6;    vec2 sampling_offset = sampling_offset_start;    for (int i = 0; i < one_side_sampling_number; i++)    {        vec4 surround_0 = texture2D(inputImageTexture, texture_coordinate + sampling_offset);        vec4 surround_1 = texture2D(inputImageTexture, texture_coordinate - sampling_offset);        average_info += surround_0 + surround_1;        sampling_offset += sampling_step;    }    average_info /= float(2 * one_side_sampling_number + 1);    const float RANGE_ADJUST = 100.0;    const float RANGE_ADJUST_SQUARE = RANGE_ADJUST * RANGE_ADJUST;    float mean = RANGE_ADJUST * average_info.g;    float sqaure_mean = RANGE_ADJUST_SQUARE * average_info.a;     float variance = max(0.0001, sqaure_mean - mean * mean);    float epsilon = RANGE_ADJUST * 0.08;    float a_component = variance / (variance + (epsilon * epsilon));    gl_FragColor = vec4((1.0 - a_component) * average_info.rgb, a_component);}";

        /* renamed from: e */
        public static String f4558e = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: f */
        public static String f4559f = "precision mediump float;varying vec2 texture_coordinate;uniform sampler2D inputImageTexture;uniform vec2 sampling_offset_start;uniform vec2 sampling_step;void main(){    vec4 center = texture2D(inputImageTexture, texture_coordinate);    vec4 average_info = center;    const int one_side_sampling_number = 6;    vec2 sampling_offset = sampling_offset_start;    for (int i = 0; i < one_side_sampling_number; i++)    {        vec4 surround_0 = texture2D(inputImageTexture, texture_coordinate + sampling_offset);        vec4 surround_1 = texture2D(inputImageTexture, texture_coordinate - sampling_offset);        average_info += surround_0 + surround_1;        sampling_offset += sampling_step;    }    average_info /= float(2 * one_side_sampling_number + 1);    gl_FragColor = average_info;}";

        /* renamed from: g */
        public static String f4560g = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: h */
        public static String f4561h = "precision mediump float;varying vec2 texture_coordinate;uniform sampler2D rootImageTexture;uniform sampler2D inputImageTexture;uniform vec2 sampling_offset_start;uniform vec2 sampling_step;uniform float strength;uniform float balance;const vec3 convert_y_weight = vec3(0.333, 0.333, 0.333);const float BALANCE_ADJUST_MAX = 1.15;const float BALANCE_ADJUST_MIN = 0.15;void main(){    vec4 average_info = texture2D(inputImageTexture, texture_coordinate);    const int one_side_sampling_number = 6;    vec2 sampling_offset = sampling_offset_start;    for (int i = 0; i < one_side_sampling_number; i++)    {        vec4 surround_0 = texture2D(inputImageTexture, texture_coordinate + sampling_offset);        vec4 surround_1 = texture2D(inputImageTexture, texture_coordinate - sampling_offset);        average_info += surround_0 + surround_1;        sampling_offset += sampling_step;    }    average_info /= float(2 * one_side_sampling_number + 1);    vec4 root_source = texture2D(rootImageTexture, texture_coordinate);    vec3 smooth_rgb = average_info.a * root_source.rgb + average_info.rgb;    float smooth_luma = dot(smooth_rgb, convert_y_weight);    float balance_adjust = (smooth_luma - 0.5) * (balance / 25.0) + 1.0;    balance_adjust = clamp(balance_adjust, BALANCE_ADJUST_MIN, BALANCE_ADJUST_MAX);    float root_luma = dot(root_source.rgb, convert_y_weight);    float result_luma = mix(smooth_luma, root_luma, 1.0 + balance_adjust * strength - balance_adjust);    vec3 result = root_source.rgb + vec3(result_luma - root_luma);    result = mix(vec3(dot(result, convert_y_weight)), result, clamp(result_luma / max(0.001, root_luma), 1.0, 1.5));    gl_FragColor = vec4(result, 1.0);}";
    }

    public C0890g() {
        super(a.f4560g, a.f4561h);
        this.f4549v = 256;
        this.f4550w = 256;
        this.f4535Y = 5.0f;
        this.f4536Z = 1.0f;
        this.f4537a0 = 1.0f / 12.0f;
        this.f4538b0 = BitmapDescriptorFactory.HUE_RED;
        this.f4539c0 = 2.0f;
        this.f4540d0 = new Object();
        this.f4543p = a.f4554a;
        this.f4544q = a.f4555b;
        this.f4545r = a.f4556c;
        this.f4546s = a.f4557d;
        this.f4547t = a.f4558e;
        this.f4548u = a.f4559f;
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4541n = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4542o = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
    }

    /* renamed from: A */
    public final void m4269A(int i9, int i10) {
        int[] iArr = new int[1];
        this.f4517G = iArr;
        this.f4518H = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4518H, 0);
        GLES20.glBindTexture(3553, this.f4518H[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4517G[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4518H[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: B */
    public final void m4270B() {
        int[] iArr = this.f4553z;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4553z = null;
        }
        int[] iArr2 = this.f4552y;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4552y = null;
        }
    }

    /* renamed from: C */
    public final void m4271C() {
        int[] iArr = this.f4518H;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4518H = null;
        }
        int[] iArr2 = this.f4517G;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4517G = null;
        }
    }

    /* renamed from: D */
    public void m4272D(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f4511A);
        GLES20.glViewport(0, 0, this.f4549v, this.f4550w);
        GLES20.glBindFramebuffer(36160, this.f4552y[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4512B, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4512B);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4514D, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4514D);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f4513C, 0);
        }
        float fMax = Math.max(this.f4537a0 * (Math.min(this.f4549v, this.f4550w) / 2400.0f) * this.f4539c0, 0.083333336f);
        GLES20.glUniform2f(this.f4515E, fMax / this.f4549v, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUniform2f(this.f4516F, fMax / this.f4549v, BitmapDescriptorFactory.HUE_RED);
        mo4057k();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4512B);
        GLES20.glDisableVertexAttribArray(this.f4514D);
        GLES20.glBindTexture(3553, 0);
    }

    /* renamed from: E */
    public void m4273E(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f4519I);
        GLES20.glViewport(0, 0, this.f4549v, this.f4550w);
        GLES20.glBindFramebuffer(36160, this.f4517G[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4520J, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4520J);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4522L, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4522L);
        if (this.f4553z[0] != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f4553z[0]);
            GLES20.glUniform1i(this.f4521K, 0);
        }
        float fMax = Math.max(this.f4537a0 * (Math.min(this.f4549v, this.f4550w) / 2400.0f) * this.f4539c0, 0.083333336f);
        GLES20.glUniform2f(this.f4523M, BitmapDescriptorFactory.HUE_RED, fMax / this.f4550w);
        GLES20.glUniform2f(this.f4524N, BitmapDescriptorFactory.HUE_RED, fMax / this.f4550w);
        mo4057k();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4520J);
        GLES20.glDisableVertexAttribArray(this.f4522L);
        GLES20.glBindTexture(3553, 0);
    }

    /* renamed from: F */
    public void m4274F(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f4525O);
        GLES20.glViewport(0, 0, this.f4549v, this.f4550w);
        GLES20.glBindFramebuffer(36160, this.f4552y[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4526P, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4526P);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4528R, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4528R);
        if (this.f4518H[0] != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f4518H[0]);
            GLES20.glUniform1i(this.f4527Q, 0);
        }
        float fMax = Math.max(this.f4537a0 * (Math.min(this.f4549v, this.f4550w) / 2400.0f) * this.f4539c0, 0.083333336f);
        GLES20.glUniform2f(this.f4529S, fMax / this.f4549v, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUniform2f(this.f4530T, fMax / this.f4549v, BitmapDescriptorFactory.HUE_RED);
        mo4057k();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4526P);
        GLES20.glDisableVertexAttribArray(this.f4528R);
        GLES20.glBindTexture(3553, 0);
    }

    /* renamed from: G */
    public void m4275G(float f9) {
        this.f4538b0 = f9;
        m4408q(this.f4534X, f9);
    }

    /* renamed from: H */
    public void m4276H(float f9) {
        synchronized (this.f4540d0) {
            this.f4536Z = f9;
        }
    }

    /* renamed from: I */
    public void m4277I(float f9) {
        this.f4535Y = f9;
        m4408q(this.f4533W, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4270B();
        m4271C();
        GLES20.glDeleteProgram(this.f4511A);
        GLES20.glDeleteProgram(this.f4519I);
        GLES20.glDeleteProgram(this.f4525O);
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!m4405h() || this.f4552y == null || this.f4553z == null) {
            return;
        }
        synchronized (this.f4540d0) {
            this.f4537a0 = this.f4536Z / 12.0f;
        }
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        m4272D(i9, this.f4541n, this.f4542o);
        m4273E(i9, this.f4541n, this.f4542o);
        m4274F(i9, this.f4541n, this.f4542o);
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (m4405h()) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (this.f4553z[0] != -1) {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4553z[0]);
                GLES20.glUniform1i(this.f4795f, 3);
            }
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(this.f4551x, 0);
            }
            float fMax = Math.max(this.f4537a0 * (Math.min(this.f4549v, this.f4550w) / 2400.0f) * this.f4539c0, 0.083333336f);
            GLES20.glUniform2f(this.f4531U, BitmapDescriptorFactory.HUE_RED, fMax / this.f4550w);
            GLES20.glUniform2f(this.f4532V, BitmapDescriptorFactory.HUE_RED, fMax / this.f4550w);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        int iM4377c = C0925r1.m4377c(this.f4543p, this.f4544q);
        this.f4511A = iM4377c;
        this.f4512B = GLES20.glGetAttribLocation(iM4377c, "position");
        this.f4513C = GLES20.glGetUniformLocation(this.f4511A, "inputImageTexture");
        this.f4514D = GLES20.glGetAttribLocation(this.f4511A, "inputTextureCoordinate");
        this.f4515E = GLES20.glGetUniformLocation(this.f4511A, "sampling_offset_start");
        this.f4516F = GLES20.glGetUniformLocation(this.f4511A, "sampling_step");
        int iM4377c2 = C0925r1.m4377c(this.f4545r, this.f4546s);
        this.f4519I = iM4377c2;
        this.f4520J = GLES20.glGetAttribLocation(iM4377c2, "position");
        this.f4521K = GLES20.glGetUniformLocation(this.f4519I, "inputImageTexture");
        this.f4522L = GLES20.glGetAttribLocation(this.f4519I, "inputTextureCoordinate");
        this.f4523M = GLES20.glGetUniformLocation(this.f4519I, "sampling_offset_start");
        this.f4524N = GLES20.glGetUniformLocation(this.f4519I, "sampling_step");
        int iM4377c3 = C0925r1.m4377c(this.f4547t, this.f4548u);
        this.f4525O = iM4377c3;
        this.f4526P = GLES20.glGetAttribLocation(iM4377c3, "position");
        this.f4527Q = GLES20.glGetUniformLocation(this.f4525O, "inputImageTexture");
        this.f4528R = GLES20.glGetAttribLocation(this.f4525O, "inputTextureCoordinate");
        this.f4529S = GLES20.glGetUniformLocation(this.f4525O, "sampling_offset_start");
        this.f4530T = GLES20.glGetUniformLocation(this.f4525O, "sampling_step");
        super.mo4047l();
        this.f4531U = GLES20.glGetUniformLocation(m4403f(), "sampling_offset_start");
        this.f4532V = GLES20.glGetUniformLocation(m4403f(), "sampling_step");
        this.f4533W = GLES20.glGetUniformLocation(m4403f(), "strength");
        this.f4534X = GLES20.glGetUniformLocation(m4403f(), "balance");
        this.f4551x = GLES20.glGetUniformLocation(m4403f(), "rootImageTexture");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4277I(this.f4535Y);
        m4276H(this.f4536Z);
        m4275G(this.f4538b0);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4552y != null) {
            m4270B();
        }
        if (this.f4517G != null) {
            m4271C();
        }
        int iMax = Math.max(0, ((int) ((Math.log(i9) / Math.log(2.0d)) + 0.5d)) - 1);
        int iMax2 = Math.max(0, ((int) ((Math.log(i10) / Math.log(2.0d)) + 0.5d)) - 1);
        int i11 = 1 << iMax;
        this.f4549v = i11;
        int i12 = 1 << iMax2;
        this.f4550w = i12;
        m4278z(i11, i12);
        m4269A(this.f4549v, this.f4550w);
    }

    /* renamed from: z */
    public final void m4278z(int i9, int i10) {
        int[] iArr = new int[1];
        this.f4552y = iArr;
        this.f4553z = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4553z, 0);
        GLES20.glBindTexture(3553, this.f4553z[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4552y[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4553z[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }
}
