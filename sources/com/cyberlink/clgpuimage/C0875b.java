package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.cyberlink.clgpuimage.IBeautyFilter2;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.commons.p159io.IOUtils;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.b */
/* loaded from: classes.dex */
public class C0875b extends C0936w0 {

    /* renamed from: A */
    public int f4388A;

    /* renamed from: B */
    public int f4389B;

    /* renamed from: C */
    public int f4390C;

    /* renamed from: D */
    public int f4391D;

    /* renamed from: E */
    public int f4392E;

    /* renamed from: F */
    public int f4393F;

    /* renamed from: G */
    public int f4394G;

    /* renamed from: H */
    public int f4395H;

    /* renamed from: I */
    public int f4396I;

    /* renamed from: J */
    public float f4397J;

    /* renamed from: K */
    public boolean f4398K;

    /* renamed from: L */
    public int[] f4399L;

    /* renamed from: M */
    public int f4400M;

    /* renamed from: N */
    public ByteBuffer f4401N;

    /* renamed from: O */
    public final int f4402O;

    /* renamed from: n */
    public final FloatBuffer f4403n;

    /* renamed from: o */
    public final FloatBuffer f4404o;

    /* renamed from: p */
    public IBeautyFilter2.FilterType f4405p;

    /* renamed from: q */
    public String f4406q;

    /* renamed from: r */
    public String f4407r;

    /* renamed from: s */
    public String f4408s;

    /* renamed from: t */
    public String f4409t;

    /* renamed from: u */
    public int f4410u;

    /* renamed from: v */
    public int f4411v;

    /* renamed from: w */
    public int f4412w;

    /* renamed from: x */
    public int[] f4413x;

    /* renamed from: y */
    public int[] f4414y;

    /* renamed from: z */
    public int f4415z;

    /* renamed from: com.cyberlink.clgpuimage.b$a */
    public class a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ float f4417c;

        /* renamed from: d */
        public final /* synthetic */ float f4418d;

        public a(float f9, float f10) {
            this.f4417c = f9;
            this.f4418d = f10;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0875b c0875b = C0875b.this;
            c0875b.m4409r(c0875b.f4393F, new float[]{BitmapDescriptorFactory.HUE_RED, this.f4417c / C0875b.this.f4411v});
            C0875b c0875b2 = C0875b.this;
            c0875b2.m4409r(c0875b2.f4394G, new float[]{BitmapDescriptorFactory.HUE_RED, this.f4418d / C0875b.this.f4411v});
        }
    }

    public C0875b(IBeautyFilter2.FilterType filterType) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}", "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}", filterType);
    }

    /* renamed from: B */
    public final void m4214B(int i9, int i10) {
        int[] iArr = new int[1];
        this.f4413x = iArr;
        this.f4414y = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4414y, 0);
        GLES20.glBindTexture(3553, this.f4414y[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4413x[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4414y[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: C */
    public final String m4215C(char[] cArr) {
        char[] cArr2 = (char[]) cArr.clone();
        for (int i9 = 0; i9 < cArr2.length; i9++) {
            char c9 = (char) (cArr2[i9] ^ 200);
            cArr2[i9] = c9;
            if (c9 >= '!' && c9 <= '~') {
                char c10 = (char) (c9 + IOUtils.DIR_SEPARATOR_UNIX);
                cArr2[i9] = c10;
                if (c10 > '~') {
                    cArr2[i9] = (char) (c10 - '^');
                }
            }
        }
        return new String(cArr2);
    }

    /* renamed from: D */
    public final void m4216D() {
        int[] iArr = this.f4414y;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4414y = null;
        }
        int[] iArr2 = this.f4413x;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4413x = null;
        }
    }

    /* renamed from: E */
    public void m4217E() {
    }

    /* renamed from: F */
    public void m4218F() {
    }

    /* renamed from: G */
    public void m4219G(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        GLES20.glUseProgram(this.f4415z);
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        GLES20.glBindFramebuffer(36160, this.f4413x[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4388A, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4388A);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4390C, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4390C);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f4389B, 0);
        }
        float fMin = Math.min(this.f4410u, this.f4411v) / 720.0f;
        float fMax = (float) (this.f4405p == IBeautyFilter2.FilterType.LIVE_SMOOTH ? Math.max(1.0d, Math.floor(fMin * this.f4397J)) : Math.max(1.0d, fMin * this.f4397J));
        GLES20.glUniform2f(this.f4391D, (fMax - 0.5f) / this.f4410u, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUniform2f(this.f4392E, fMax / this.f4410u, BitmapDescriptorFactory.HUE_RED);
        m4217E();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4388A);
        GLES20.glDisableVertexAttribArray(this.f4390C);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
    }

    /* renamed from: H */
    public void mo4220H(int i9, int i10) {
        if (i9 <= 0 || i10 <= 0) {
            return;
        }
        this.f4410u = i9;
        this.f4411v = i10;
        float fMin = Math.min(i9, i10) / 720.0f;
        float fMax = (float) (this.f4405p == IBeautyFilter2.FilterType.LIVE_SMOOTH ? Math.max(1.0d, Math.floor(fMin * this.f4397J)) : Math.max(1.0d, fMin * this.f4397J));
        m4406o(new a(fMax - 0.5f, fMax));
    }

    /* renamed from: I */
    public void m4221I(char[] cArr, char[] cArr2, char[] cArr3, char[] cArr4) {
        this.f4406q = m4215C(cArr);
        this.f4407r = m4215C(cArr2);
        this.f4408s = m4215C(cArr3);
        String strM4215C = m4215C(cArr4);
        this.f4409t = strM4215C;
        super.m4414w(this.f4408s, strM4215C);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4216D();
        GLES20.glDeleteProgram(this.f4415z);
        if (this.f4398K) {
            int[] iArr = this.f4399L;
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!m4405h() || this.f4413x == null || this.f4414y == null) {
            return;
        }
        m4219G(i9, this.f4403n, this.f4404o);
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (m4405h()) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (this.f4414y[0] != -1) {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4414y[0]);
                GLES20.glUniform1i(this.f4795f, 3);
            }
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(this.f4412w, 0);
            }
            if (this.f4398K && this.f4399L[0] != -1) {
                GLES20.glActiveTexture(33988);
                GLES20.glBindTexture(3553, this.f4399L[0]);
                GLES20.glUniform1i(this.f4400M, 4);
            }
            m4218F();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        int iM4377c = C0925r1.m4377c(this.f4406q, this.f4407r);
        this.f4415z = iM4377c;
        this.f4388A = GLES20.glGetAttribLocation(iM4377c, "position");
        this.f4389B = GLES20.glGetUniformLocation(this.f4415z, "inputImageTexture");
        this.f4390C = GLES20.glGetAttribLocation(this.f4415z, "inputTextureCoordinate");
        this.f4391D = GLES20.glGetUniformLocation(this.f4415z, "sampling_offset_start");
        this.f4392E = GLES20.glGetUniformLocation(this.f4415z, "sampling_step");
        super.mo4047l();
        this.f4393F = GLES20.glGetUniformLocation(m4403f(), "sampling_offset_start");
        this.f4394G = GLES20.glGetUniformLocation(m4403f(), "sampling_step");
        this.f4395H = GLES20.glGetUniformLocation(m4403f(), "smooth_strength");
        this.f4396I = GLES20.glGetUniformLocation(m4403f(), "color_strength");
        this.f4412w = GLES20.glGetUniformLocation(m4403f(), "rootImageTexture");
        if (this.f4398K) {
            int[] iArr = new int[1];
            this.f4399L = iArr;
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, this.f4399L[0]);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6407, 256, 128, 0, 6407, 5121, this.f4401N);
            this.f4400M = GLES20.glGetUniformLocation(m4403f(), "mapping_table_texture");
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        mo4220H(this.f4410u, this.f4411v);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4413x != null) {
            m4216D();
        }
        m4214B(i9, i10);
        mo4220H(i9, i10);
    }

    public C0875b(String str, String str2, String str3, String str4, IBeautyFilter2.FilterType filterType) {
        super(str3, str4);
        this.f4410u = 960;
        this.f4411v = 720;
        this.f4402O = 98304;
        this.f4406q = str;
        this.f4407r = str2;
        this.f4408s = str3;
        this.f4409t = str4;
        this.f4405p = filterType;
        if (filterType == IBeautyFilter2.FilterType.LIVE_SMOOTH) {
            this.f4397J = 6.0f;
        } else {
            this.f4397J = 2.0f;
        }
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4403n = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4404o = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f4398K = false;
    }
}
