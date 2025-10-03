package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Locale;

/* renamed from: com.cyberlink.clgpuimage.s0 */
/* loaded from: classes.dex */
public class C0927s0 extends C0936w0 {

    /* renamed from: A */
    public boolean f4748A;

    /* renamed from: B */
    public boolean f4749B;

    /* renamed from: C */
    public final int f4750C;

    /* renamed from: D */
    public float f4751D;

    /* renamed from: E */
    public boolean f4752E;

    /* renamed from: n */
    public int f4753n;

    /* renamed from: o */
    public float f4754o;

    /* renamed from: p */
    public int f4755p;

    /* renamed from: q */
    public int f4756q;

    /* renamed from: r */
    public final FloatBuffer f4757r;

    /* renamed from: s */
    public int[] f4758s;

    /* renamed from: t */
    public int[] f4759t;

    /* renamed from: u */
    public int f4760u;

    /* renamed from: v */
    public int[] f4761v;

    /* renamed from: w */
    public int[] f4762w;

    /* renamed from: x */
    public int f4763x;

    /* renamed from: y */
    public int f4764y;

    /* renamed from: z */
    public boolean f4765z;

    public C0927s0() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "precision highp float;\n\nuniform lowp float clarity;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D lowpassImageTexture;\nvarying vec2 textureCoordinate;\nvoid main()\n{\n\t mediump vec4 color = texture2D(inputImageTexture, textureCoordinate);\t mediump vec4 lowpass = texture2D(lowpassImageTexture, textureCoordinate);\n    gl_FragColor = color + (color-lowpass) * clarity;\n}");
        this.f4765z = false;
        this.f4748A = false;
        this.f4749B = false;
        this.f4750C = 4;
        this.f4751D = 1.0f;
        this.f4752E = true;
        this.f4760u = 0;
        this.f4763x = 0;
        this.f4754o = BitmapDescriptorFactory.HUE_RED;
        this.f4755p = 32;
        this.f4764y = 1;
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4757r = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
    }

    /* renamed from: A */
    public final void m4380A(int i9, int i10) {
        this.f4761v = new int[1];
        this.f4762w = new int[1];
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glGenFramebuffers(1, this.f4761v, 0);
        GLES20.glGenTextures(1, this.f4762w, 0);
        GLES20.glBindTexture(3553, this.f4762w[0]);
        GLES20.glTexImage2D(3553, 0, 6408, m4386G(), m4385F(), 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4761v[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4762w[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
    }

    /* renamed from: B */
    public final void m4381B() {
        int[] iArr = this.f4759t;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4759t = null;
        }
        int[] iArr2 = this.f4758s;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4758s = null;
        }
        int[] iArr3 = this.f4762w;
        if (iArr3 != null) {
            GLES20.glDeleteTextures(iArr3.length, iArr3, 0);
            this.f4762w = null;
        }
        int[] iArr4 = this.f4761v;
        if (iArr4 != null) {
            GLES20.glDeleteFramebuffers(iArr4.length, iArr4, 0);
            this.f4761v = null;
        }
    }

    /* renamed from: C */
    public final void m4382C(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (this.f4765z) {
            GLES20.glDeleteProgram(this.f4760u);
            this.f4760u = 0;
        }
        if (this.f4760u == 0) {
            this.f4760u = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", m4384E(true));
        }
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f4760u, "position");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.f4760u, "inputImageTexture");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(this.f4760u, "inputTextureCoordinate");
        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(this.f4760u, "scalefactor");
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glBindFramebuffer(36160, this.f4758s[0]);
        GLES20.glViewport(0, 0, m4386G(), m4385F());
        GLES20.glUseProgram(this.f4760u);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(iGlGetUniformLocation, 0);
        }
        GLES20.glUniform1f(iGlGetUniformLocation2, m4386G());
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
    }

    /* renamed from: D */
    public final void m4383D(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (this.f4748A) {
            GLES20.glDeleteProgram(this.f4763x);
            this.f4763x = 0;
        }
        if (this.f4763x == 0) {
            this.f4763x = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", m4384E(false));
        }
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f4763x, "position");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.f4763x, "inputImageTexture");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(this.f4763x, "inputTextureCoordinate");
        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(this.f4763x, "scalefactor");
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glBindFramebuffer(36160, this.f4761v[0]);
        GLES20.glViewport(0, 0, m4386G(), m4385F());
        GLES20.glUseProgram(this.f4763x);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(iGlGetUniformLocation, 0);
        }
        GLES20.glUniform1f(iGlGetUniformLocation2, m4385F());
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
    }

    /* renamed from: E */
    public final String m4384E(boolean z8) {
        int i9 = this.f4755p;
        float f9 = i9 * 2.0f;
        int i10 = (i9 * 2) + 1;
        float[] fArr = new float[i10];
        float[] fArr2 = new float[i10];
        float f10 = BitmapDescriptorFactory.HUE_RED;
        int i11 = 0;
        while (i11 < i10) {
            float f11 = i11 - this.f4755p;
            if (z8) {
                fArr[i11] = f11;
            } else {
                fArr[i11] = f11;
            }
            float fExp = (float) Math.exp(((-f11) * f11) / f9);
            fArr2[i11] = fExp;
            f10 += fExp;
            i11 += this.f4764y;
        }
        int i12 = 0;
        while (i12 < i10) {
            fArr2[i12] = fArr2[i12] / f10;
            i12 += this.f4764y;
        }
        StringBuilder sb = new StringBuilder("varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nuniform highp float scalefactor;void main()\n{\nhighp vec4 color = vec4(0.0);");
        int i13 = 0;
        while (i13 < i10) {
            float f12 = fArr[i13];
            float f13 = fArr2[i13];
            if (z8) {
                sb.append(String.format(Locale.US, "color += texture2D(inputImageTexture, textureCoordinate + vec2(%f, 0.0)/scalefactor) * %f;\n", Float.valueOf(f12), Float.valueOf(f13)));
            } else {
                sb.append(String.format(Locale.US, "color += texture2D(inputImageTexture, textureCoordinate + vec2(0.0, %f)/scalefactor) * %f;\n", Float.valueOf(f12), Float.valueOf(f13)));
            }
            i13 += this.f4764y;
        }
        sb.append("gl_FragColor = color;\n}\n");
        return sb.toString();
    }

    /* renamed from: F */
    public int m4385F() {
        return (int) (this.f4797h / this.f4751D);
    }

    /* renamed from: G */
    public int m4386G() {
        return (int) (this.f4797h / this.f4751D);
    }

    /* renamed from: H */
    public void m4387H(float f9) {
        this.f4754o = f9;
        m4408q(this.f4753n, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4381B();
        int i9 = this.f4760u;
        if (i9 != 0) {
            GLES20.glDeleteProgram(i9);
            this.f4760u = 0;
        }
        if (this.f4763x != 0) {
            GLES20.glDeleteProgram(this.f4760u);
            this.f4763x = 0;
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        synchronized (this) {
            if (this.f4749B) {
                if (this.f4758s != null || this.f4761v != null) {
                    m4381B();
                }
                m4388z(m4386G(), m4385F());
                m4380A(m4386G(), m4385F());
                this.f4749B = false;
            }
            if (m4405h() && this.f4758s != null && this.f4762w != null) {
                m4382C(i9, this.f4757r, floatBuffer2);
                m4383D(this.f4759t[0], this.f4757r, floatBuffer2);
                GLES20.glUseProgram(m4403f());
                GLES20.glViewport(0, 0, m4402e(), m4401d());
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f4762w[0]);
                GLES20.glUniform1i(this.f4756q, 3);
                super.mo4046j(i9, floatBuffer, floatBuffer2);
            }
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4753n = GLES20.glGetUniformLocation(m4403f(), "clarity");
        this.f4756q = GLES20.glGetUniformLocation(m4403f(), "lowpassImageTexture");
        m4387H(this.f4754o);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4752E) {
            this.f4755p = (int) Math.min(Math.min(i9, i10) / 64.0f, 4.0f);
            this.f4751D = 1.0f;
        }
        if (this.f4758s != null || this.f4761v != null) {
            m4381B();
        }
        m4388z(m4386G(), m4385F());
        m4380A(m4386G(), m4385F());
    }

    /* renamed from: z */
    public final void m4388z(int i9, int i10) {
        this.f4758s = new int[1];
        this.f4759t = new int[1];
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glGenFramebuffers(1, this.f4758s, 0);
        GLES20.glGenTextures(1, this.f4759t, 0);
        GLES20.glBindTexture(3553, this.f4759t[0]);
        GLES20.glTexImage2D(3553, 0, 6408, m4386G(), m4385F(), 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4758s[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4759t[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
    }
}
