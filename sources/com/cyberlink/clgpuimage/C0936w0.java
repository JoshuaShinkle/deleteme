package com.cyberlink.clgpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import com.cyberlink.clgpuimage.GPUImage;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.cyberlink.clgpuimage.w0 */
/* loaded from: classes.dex */
public class C0936w0 {

    /* renamed from: a */
    public final Queue<Runnable> f4790a;

    /* renamed from: b */
    public String f4791b;

    /* renamed from: c */
    public String f4792c;

    /* renamed from: d */
    public int f4793d;

    /* renamed from: e */
    public int f4794e;

    /* renamed from: f */
    public int f4795f;

    /* renamed from: g */
    public int f4796g;

    /* renamed from: h */
    public int f4797h;

    /* renamed from: i */
    public int f4798i;

    /* renamed from: j */
    public boolean f4799j;

    /* renamed from: k */
    public GPUImage.ScaleType f4800k;

    /* renamed from: l */
    public int f4801l;

    /* renamed from: m */
    public int f4802m;

    /* renamed from: com.cyberlink.clgpuimage.w0$a */
    public class a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4804c;

        /* renamed from: d */
        public final /* synthetic */ int f4805d;

        public a(int i9, int i10) {
            this.f4804c = i9;
            this.f4805d = i10;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform1i(this.f4804c, this.f4805d);
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.w0$b */
    public class b implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4807c;

        /* renamed from: d */
        public final /* synthetic */ float f4808d;

        public b(int i9, float f9) {
            this.f4807c = i9;
            this.f4808d = f9;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform1f(this.f4807c, this.f4808d);
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.w0$c */
    public class c implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4810c;

        /* renamed from: d */
        public final /* synthetic */ float[] f4811d;

        public c(int i9, float[] fArr) {
            this.f4810c = i9;
            this.f4811d = fArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform2fv(this.f4810c, 1, FloatBuffer.wrap(this.f4811d));
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.w0$d */
    public class d implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4813c;

        /* renamed from: d */
        public final /* synthetic */ float[] f4814d;

        public d(int i9, float[] fArr) {
            this.f4813c = i9;
            this.f4814d = fArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform3fv(this.f4813c, 1, FloatBuffer.wrap(this.f4814d));
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.w0$e */
    public class e implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ PointF f4816c;

        /* renamed from: d */
        public final /* synthetic */ int f4817d;

        public e(PointF pointF, int i9) {
            this.f4816c = pointF;
            this.f4817d = i9;
        }

        @Override // java.lang.Runnable
        public void run() {
            PointF pointF = this.f4816c;
            GLES20.glUniform2fv(this.f4817d, 1, new float[]{pointF.x, pointF.y}, 0);
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.w0$f */
    public class f implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ int f4819c;

        /* renamed from: d */
        public final /* synthetic */ float[] f4820d;

        public f(int i9, float[] fArr) {
            this.f4819c = i9;
            this.f4820d = fArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniformMatrix4fv(this.f4819c, 1, false, this.f4820d, 0);
        }
    }

    public C0936w0() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
    }

    /* renamed from: c */
    public final void m4400c() {
        this.f4799j = false;
        GLES20.glDeleteProgram(this.f4793d);
        mo4045i();
    }

    /* renamed from: d */
    public int m4401d() {
        return this.f4798i;
    }

    /* renamed from: e */
    public int m4402e() {
        return this.f4797h;
    }

    /* renamed from: f */
    public int m4403f() {
        return this.f4793d;
    }

    /* renamed from: g */
    public final void m4404g() {
        mo4047l();
        this.f4799j = true;
        mo4048m();
    }

    /* renamed from: h */
    public boolean m4405h() {
        return this.f4799j;
    }

    /* renamed from: i */
    public void mo4045i() {
    }

    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (this.f4799j) {
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
            mo4057k();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    /* renamed from: k */
    public void mo4057k() {
    }

    /* renamed from: l */
    public void mo4047l() {
        int iM4377c = C0925r1.m4377c(this.f4791b, this.f4792c);
        this.f4793d = iM4377c;
        this.f4794e = GLES20.glGetAttribLocation(iM4377c, "position");
        this.f4795f = GLES20.glGetUniformLocation(this.f4793d, "inputImageTexture");
        this.f4796g = GLES20.glGetAttribLocation(this.f4793d, "inputTextureCoordinate");
        this.f4799j = true;
    }

    /* renamed from: m */
    public void mo4048m() {
    }

    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        this.f4797h = i9;
        this.f4798i = i10;
    }

    /* renamed from: o */
    public void m4406o(Runnable runnable) {
        this.f4790a.add(runnable);
    }

    /* renamed from: p */
    public void m4407p() {
        synchronized (this.f4790a) {
            while (!this.f4790a.isEmpty()) {
                this.f4790a.poll().run();
            }
        }
    }

    /* renamed from: q */
    public void m4408q(int i9, float f9) {
        m4406o(new b(i9, f9));
    }

    /* renamed from: r */
    public void m4409r(int i9, float[] fArr) {
        m4406o(new c(i9, fArr));
    }

    /* renamed from: s */
    public void m4410s(int i9, float[] fArr) {
        m4406o(new d(i9, fArr));
    }

    /* renamed from: t */
    public void m4411t(int i9, int i10) {
        m4406o(new a(i9, i10));
    }

    /* renamed from: u */
    public void m4412u(int i9, PointF pointF) {
        m4406o(new e(pointF, i9));
    }

    /* renamed from: v */
    public void m4413v(GPUImage.ScaleType scaleType) {
        this.f4800k = scaleType;
    }

    /* renamed from: w */
    public void m4414w(String str, String str2) {
        this.f4791b = str;
        this.f4792c = str2;
    }

    /* renamed from: x */
    public void m4415x(int i9, float[] fArr) {
        m4406o(new f(i9, fArr));
    }

    /* renamed from: y */
    public void m4416y(int i9, int i10) {
        this.f4801l = i9;
        this.f4802m = i10;
    }

    public C0936w0(String str, String str2) {
        this.f4800k = GPUImage.ScaleType.CENTER_CROP;
        this.f4801l = 0;
        this.f4802m = 0;
        this.f4790a = new ConcurrentLinkedQueue();
        this.f4791b = str;
        this.f4792c = str2;
    }
}
