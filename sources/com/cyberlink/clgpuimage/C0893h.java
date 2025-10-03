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

/* renamed from: com.cyberlink.clgpuimage.h */
/* loaded from: classes.dex */
public class C0893h extends C0936w0 {

    /* renamed from: A */
    public int[] f4570A;

    /* renamed from: B */
    public int f4571B;

    /* renamed from: C */
    public int f4572C;

    /* renamed from: D */
    public int f4573D;

    /* renamed from: E */
    public int f4574E;

    /* renamed from: F */
    public int f4575F;

    /* renamed from: G */
    public int f4576G;

    /* renamed from: H */
    public int f4577H;

    /* renamed from: I */
    public int f4578I;

    /* renamed from: J */
    public int f4579J;

    /* renamed from: K */
    public int f4580K;

    /* renamed from: L */
    public float f4581L;

    /* renamed from: n */
    public final FloatBuffer f4582n;

    /* renamed from: o */
    public final FloatBuffer f4583o;

    /* renamed from: p */
    public String f4584p;

    /* renamed from: q */
    public String f4585q;

    /* renamed from: r */
    public String f4586r;

    /* renamed from: s */
    public final int f4587s;

    /* renamed from: t */
    public final int f4588t;

    /* renamed from: u */
    public int f4589u;

    /* renamed from: v */
    public int f4590v;

    /* renamed from: w */
    public int f4591w;

    /* renamed from: x */
    public float f4592x;

    /* renamed from: y */
    public float f4593y;

    /* renamed from: z */
    public int[] f4594z;

    /* renamed from: com.cyberlink.clgpuimage.h$a */
    public static class a {

        /* renamed from: a */
        public static String f4595a = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: b */
        public static String f4596b = " precision mediump float; varying vec2 texture_coordinate; uniform sampler2D inputImageTexture; uniform float block; uniform float skip; uniform float strength; const int BIN_SIZE_INT = 5; const float BIN_SIZE_FLOAT = float(BIN_SIZE_INT); const vec3 convert_y_weight = vec3(0.333, 0.333, 0.333); void main() {     vec2 position_in_block = fract(texture_coordinate * block);     bvec2 not_right_and_bottom = lessThan(texture_coordinate, vec2(1.0 - skip / block));     bvec2 skip_in_block = greaterThan(position_in_block, vec2(skip));     if (all(not_right_and_bottom) && any(skip_in_block))         discard;     vec4 histogram_index_from_0 = vec4(0.0);     vec2 histogram_index_from_4 = vec2(0.0);     vec2 block_start = floor(texture_coordinate * block) / block;     float sampling_step = 0.166 / block;     float sampling_start = 0.083 / block;     for (int i = 0; i < 6; i++)     {         for (int j = 0; j < 6; j++)         {             vec4 textureColor = texture2D(inputImageTexture, block_start + vec2(float(i), float(j)) * sampling_step + sampling_start);             float luma = dot(textureColor.rgb, convert_y_weight);             float weight = fract(luma * 5.0);             vec2 vec2_weight = vec2(1.0 - weight, weight);             float threshold_0 = step(luma, 0.2);             float threshold_1 = step(luma, 0.4);             float threshold_2 = step(luma, 0.6);             float threshold_3 = step(luma, 0.8);             float response_0 = threshold_0;             float response_1 = threshold_1 * (1.0 - threshold_0);             float response_2 = threshold_2 * (1.0 - threshold_1);             float response_3 = threshold_3 * (1.0 - threshold_2);             float response_4 = (1.0 - threshold_3);             histogram_index_from_0.rg += vec2_weight * response_0;             histogram_index_from_0.gb += vec2_weight * response_1;             histogram_index_from_0.ba += vec2_weight * response_2;             histogram_index_from_4.r += weight * response_3;             histogram_index_from_0.a += (1.0 - weight) * response_3;             histogram_index_from_4.rg += vec2_weight * response_4;         }     }     float local_histogram[BIN_SIZE_INT + 1];     local_histogram[0] = histogram_index_from_0.r;     local_histogram[1] = histogram_index_from_0.g;     local_histogram[2] = histogram_index_from_0.b;     local_histogram[3] = histogram_index_from_0.a;     local_histogram[4] = histogram_index_from_4.r;     local_histogram[5] = histogram_index_from_4.g;     float total = 0.0;     total += local_histogram[0];     total += local_histogram[1];     total += local_histogram[2];     total += local_histogram[3];     total += local_histogram[4];     total += local_histogram[5];     local_histogram[0] /= total;     local_histogram[1] /= total;     local_histogram[2] /= total;     local_histogram[3] /= total;     local_histogram[4] /= total;     local_histogram[5] /= total;     float remain = 0.0;     float limit = (strength / 100.0 * 2.0) / BIN_SIZE_FLOAT;     remain += max(0.0, local_histogram[0] - limit * 0.5);     local_histogram[0] = min(local_histogram[0], limit * 0.5);     remain += max(0.0, local_histogram[1] - limit);     local_histogram[1] = min(local_histogram[1], limit);     remain += max(0.0, local_histogram[2] - limit);     local_histogram[2] = min(local_histogram[2], limit);     remain += max(0.0, local_histogram[3] - limit);     local_histogram[3] = min(local_histogram[3], limit);     remain += max(0.0, local_histogram[4] - limit);     local_histogram[4] = min(local_histogram[4], limit);     remain += max(0.0, local_histogram[5] - limit * 0.5);     local_histogram[5] = min(local_histogram[5], limit * 0.5);     float share = remain / BIN_SIZE_FLOAT;     local_histogram[0] += share * 0.5;     local_histogram[1] += share + local_histogram[0];     local_histogram[2] += share + local_histogram[1];     local_histogram[3] += share + local_histogram[2];     local_histogram[4] += share + local_histogram[3];     local_histogram[5] += share + local_histogram[4];     vec4 mapping;     mapping.r = local_histogram[0] + local_histogram[1];     mapping.g = local_histogram[1] + local_histogram[2];     mapping.b = local_histogram[2] + local_histogram[3];     mapping.a = local_histogram[3] + local_histogram[4];     mapping *= 0.5;     gl_FragColor = mapping; }";

        /* renamed from: c */
        public static String f4597c = " precision mediump float; varying vec2 texture_coordinate; uniform sampler2D inputImageTexture; uniform float block; uniform float skip; uniform float strength; const int BIN_SIZE_INT = 5; const float BIN_SIZE_FLOAT = float(BIN_SIZE_INT); const vec3 convert_y_weight = vec3(0.333, 0.333, 0.333); void main() {     vec2 position_in_block = fract(texture_coordinate * block);     bvec2 not_right_and_bottom = lessThan(texture_coordinate, vec2(1.0 - skip / block));     bvec2 skip_in_block = greaterThan(position_in_block, vec2(skip));     if (all(not_right_and_bottom) && any(skip_in_block))         discard;     vec4 histogram_index_from_0 = vec4(0.0);     vec2 histogram_index_from_4 = vec2(0.0);     vec2 block_start = floor(texture_coordinate * block) / block;     float sampling_step = 0.5 / block;     float sampling_start = 0.25 / block;     for (int i = 0; i < 2; i++)     {         for (int j = 0; j < 2; j++)         {             vec4 textureColor = texture2D(inputImageTexture, block_start + vec2(float(i), float(j)) * sampling_step + sampling_start);             float luma = dot(textureColor.rgb, convert_y_weight);             float weight = fract(luma * 5.0);             vec2 vec2_weight = vec2(1.0 - weight, weight);             float threshold_0 = step(luma, 0.2);             float threshold_1 = step(luma, 0.4);             float threshold_2 = step(luma, 0.6);             float threshold_3 = step(luma, 0.8);             float response_0 = threshold_0;             float response_1 = threshold_1 * (1.0 - threshold_0);             float response_2 = threshold_2 * (1.0 - threshold_1);             float response_3 = threshold_3 * (1.0 - threshold_2);             float response_4 = (1.0 - threshold_3);             histogram_index_from_0.rg += vec2_weight * response_0;             histogram_index_from_0.gb += vec2_weight * response_1;             histogram_index_from_0.ba += vec2_weight * response_2;             histogram_index_from_4.r += weight * response_3;             histogram_index_from_0.a += (1.0 - weight) * response_3;             histogram_index_from_4.rg += vec2_weight * response_4;         }     }     float local_histogram[BIN_SIZE_INT + 1];     local_histogram[0] = histogram_index_from_0.r;     local_histogram[1] = histogram_index_from_0.g;     local_histogram[2] = histogram_index_from_0.b;     local_histogram[3] = histogram_index_from_0.a;     local_histogram[4] = histogram_index_from_4.r;     local_histogram[5] = histogram_index_from_4.g;     float total = 0.0;     total += local_histogram[0];     total += local_histogram[1];     total += local_histogram[2];     total += local_histogram[3];     total += local_histogram[4];     total += local_histogram[5];     local_histogram[0] /= total;     local_histogram[1] /= total;     local_histogram[2] /= total;     local_histogram[3] /= total;     local_histogram[4] /= total;     local_histogram[5] /= total;     float remain = 0.0;     float limit = (strength / 100.0 * 2.0) / BIN_SIZE_FLOAT;     remain += max(0.0, local_histogram[0] - limit * 0.5);     local_histogram[0] = min(local_histogram[0], limit * 0.5);     remain += max(0.0, local_histogram[1] - limit);     local_histogram[1] = min(local_histogram[1], limit);     remain += max(0.0, local_histogram[2] - limit);     local_histogram[2] = min(local_histogram[2], limit);     remain += max(0.0, local_histogram[3] - limit);     local_histogram[3] = min(local_histogram[3], limit);     remain += max(0.0, local_histogram[4] - limit);     local_histogram[4] = min(local_histogram[4], limit);     remain += max(0.0, local_histogram[5] - limit * 0.5);     local_histogram[5] = min(local_histogram[5], limit * 0.5);     float share = remain / BIN_SIZE_FLOAT;     local_histogram[0] += share * 0.5;     local_histogram[1] += share + local_histogram[0];     local_histogram[2] += share + local_histogram[1];     local_histogram[3] += share + local_histogram[2];     local_histogram[4] += share + local_histogram[3];     local_histogram[5] += share + local_histogram[4];     vec4 mapping;     mapping.r = local_histogram[0] + local_histogram[1];     mapping.g = local_histogram[1] + local_histogram[2];     mapping.b = local_histogram[2] + local_histogram[3];     mapping.a = local_histogram[3] + local_histogram[4];     mapping *= 0.5;     gl_FragColor = mapping; }";

        /* renamed from: d */
        public static String f4598d = "attribute vec4 position;attribute vec2 inputTextureCoordinate;varying vec2 texture_coordinate;void main(){    gl_Position = position;    texture_coordinate = inputTextureCoordinate;}";

        /* renamed from: e */
        public static String f4599e = "precision mediump float;varying vec2 texture_coordinate;uniform sampler2D rootImageTexture;uniform sampler2D inputImageTexture;uniform float block;uniform float skip;uniform float balance;const vec3 convert_y_weight = vec3(0.333, 0.333, 0.333);const float BALANCE_ADJUST_MAX = 1.15;const float BALANCE_ADJUST_MIN = 0.15;void main(){    vec2 location = fract(texture_coordinate * block);    vec2 mapping_location = floor(texture_coordinate * block) / block + (skip / 2.0) / block;    float total_weight = 0.0;    vec4 average_mapping = vec4(0.0);    for (int i = 0; i < 3; i++)    {        for (int j = 0; j < 3; j++)        {            float weight = max(0.0, 1.5 - distance(location, vec2(-0.5, -0.5) + vec2(float(i), float(j)) ));            vec4 mapping = texture2D(inputImageTexture, mapping_location + vec2((-1.0 + float(i)) / block, (-1.0 + float(j)) / block));            average_mapping += mapping * weight;            total_weight += weight;        }    }    average_mapping /= total_weight;     float local_histogram[6];     local_histogram[0] = 0.0;     local_histogram[1] = average_mapping.r;     local_histogram[2] = average_mapping.g;     local_histogram[3] = average_mapping.b;     local_histogram[4] = average_mapping.a;     local_histogram[5] = 1.0;    vec4 this_pixel = texture2D(rootImageTexture, texture_coordinate);    float luma = dot(this_pixel.rgb, convert_y_weight);    luma = clamp(luma, 0.005, 0.995);    int index = int(floor(luma / 0.2));    float new_luma = luma;    if (index == 0)        new_luma = mix(local_histogram[0], local_histogram[1], fract(luma / 0.2));    else if (index == 1)        new_luma = mix(local_histogram[1], local_histogram[2], fract(luma / 0.2));    else if (index == 2)        new_luma = mix(local_histogram[2], local_histogram[3], fract(luma / 0.2));    else if (index == 3)        new_luma = mix(local_histogram[3], local_histogram[4], fract(luma / 0.2));    else if (index == 4)        new_luma = mix(local_histogram[4], local_histogram[5], fract(luma / 0.2));    float balance_adjust = (luma - 0.5) * (balance / 25.0) + 1.0;    balance_adjust = clamp(balance_adjust, BALANCE_ADJUST_MIN, BALANCE_ADJUST_MAX);    new_luma = mix(luma, new_luma, balance_adjust);    this_pixel.rgb += (new_luma - luma);    this_pixel.rgb = mix(vec3(dot(this_pixel.rgb, convert_y_weight)), this_pixel.rgb, clamp(new_luma / max(0.001, luma), 1.0, 1.5));    gl_FragColor = vec4(this_pixel.rgb, 1.0);}";
    }

    public C0893h() {
        super(a.f4598d, a.f4599e);
        this.f4587s = 128;
        this.f4588t = 128;
        this.f4590v = 1;
        this.f4591w = 1;
        this.f4592x = BitmapDescriptorFactory.HUE_RED;
        this.f4593y = 0.125f;
        this.f4581L = BitmapDescriptorFactory.HUE_RED;
        this.f4584p = a.f4595a;
        this.f4585q = a.f4596b;
        this.f4586r = a.f4597c;
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4582n = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4583o = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
    }

    /* renamed from: A */
    public final void m4290A() {
        int[] iArr = this.f4570A;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4570A = null;
        }
        int[] iArr2 = this.f4594z;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4594z = null;
        }
    }

    /* renamed from: B */
    public void m4291B(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        GLES20.glUseProgram(this.f4571B);
        GLES20.glViewport(0, 0, 128, 128);
        GLES20.glBindFramebuffer(36160, this.f4594z[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4572C, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4572C);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4574E, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4574E);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f4573D, 0);
        }
        GLES20.glUniform1f(this.f4575F, Math.max(this.f4591w, 1));
        GLES20.glUniform1f(this.f4576G, this.f4593y);
        GLES20.glUniform1f(this.f4577H, this.f4592x);
        mo4057k();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4572C);
        GLES20.glDisableVertexAttribArray(this.f4574E);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
    }

    /* renamed from: C */
    public void m4292C(float f9) {
        this.f4581L = f9;
        m4408q(this.f4580K, f9);
    }

    /* renamed from: D */
    public void m4293D(float f9) {
        this.f4590v = Math.max(1, (int) f9);
    }

    /* renamed from: E */
    public void m4294E(float f9) {
        this.f4592x = f9;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4290A();
        GLES20.glDeleteProgram(this.f4571B);
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        this.f4591w = this.f4590v;
        this.f4593y = 3.0f / (Math.min(128, 128) / this.f4591w);
        if (!m4405h() || this.f4594z == null || this.f4570A == null) {
            return;
        }
        m4291B(i9, this.f4582n, this.f4583o);
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (m4405h()) {
            GLES20.glUniform1f(this.f4578I, Math.max(this.f4591w, 1));
            GLES20.glUniform1f(this.f4579J, this.f4593y);
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (this.f4570A[0] != -1) {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4570A[0]);
                GLES20.glUniform1i(this.f4795f, 3);
            }
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(this.f4589u, 0);
            }
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        int iM4377c = C0925r1.m4377c(this.f4584p, this.f4585q);
        this.f4571B = iM4377c;
        if (iM4377c == 0) {
            this.f4571B = C0925r1.m4377c(this.f4584p, this.f4586r);
        }
        this.f4572C = GLES20.glGetAttribLocation(this.f4571B, "position");
        this.f4573D = GLES20.glGetUniformLocation(this.f4571B, "inputImageTexture");
        this.f4574E = GLES20.glGetAttribLocation(this.f4571B, "inputTextureCoordinate");
        this.f4575F = GLES20.glGetUniformLocation(this.f4571B, "block");
        this.f4576G = GLES20.glGetUniformLocation(this.f4571B, "skip");
        this.f4577H = GLES20.glGetUniformLocation(this.f4571B, "strength");
        super.mo4047l();
        this.f4589u = GLES20.glGetUniformLocation(m4403f(), "rootImageTexture");
        this.f4578I = GLES20.glGetUniformLocation(m4403f(), "block");
        this.f4579J = GLES20.glGetUniformLocation(m4403f(), "skip");
        this.f4580K = GLES20.glGetUniformLocation(m4403f(), "balance");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4294E(this.f4592x);
        m4293D(this.f4590v);
        m4292C(this.f4581L);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4594z != null) {
            m4290A();
        }
        m4295z(128, 128);
    }

    /* renamed from: z */
    public final void m4295z(int i9, int i10) {
        int[] iArr = new int[1];
        this.f4594z = iArr;
        this.f4570A = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4570A, 0);
        GLES20.glBindTexture(3553, this.f4570A[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4594z[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4570A[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }
}
