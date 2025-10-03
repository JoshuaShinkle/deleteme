package com.cyberlink.you.kernelctrl.gpuimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLES20;
import com.cyberlink.clgpuimage.C0895h1;
import com.cyberlink.clgpuimage.C0925r1;
import com.cyberlink.clgpuimage.C0936w0;
import com.cyberlink.clgpuimage.GPUImageMaskAlphaBlendFilter;
import com.cyberlink.clgpuimage.Rotation;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.kernelctrl.gpuimage.C3096a;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p253z1.C6816a;

/* loaded from: classes.dex */
public class GPUImagePanZoomFilter extends C0936w0 {

    /* renamed from: A */
    public FloatBuffer f14020A;

    /* renamed from: B */
    public FloatBuffer f14021B;

    /* renamed from: C */
    public FloatBuffer f14022C;

    /* renamed from: D */
    public Matrix f14023D;

    /* renamed from: E */
    public float[] f14024E;

    /* renamed from: F */
    public int f14025F;

    /* renamed from: G */
    public Bitmap f14026G;

    /* renamed from: H */
    public boolean f14027H;

    /* renamed from: I */
    public Alignment f14028I;

    /* renamed from: J */
    public float f14029J;

    /* renamed from: K */
    public float f14030K;

    /* renamed from: L */
    public float f14031L;

    /* renamed from: M */
    public float f14032M;

    /* renamed from: N */
    public float f14033N;

    /* renamed from: O */
    public float f14034O;

    /* renamed from: P */
    public FloatBuffer f14035P;

    /* renamed from: Q */
    public FloatBuffer f14036Q;

    /* renamed from: R */
    public int f14037R;

    /* renamed from: n */
    public C3096a f14038n;

    /* renamed from: o */
    public List<C0936w0> f14039o;

    /* renamed from: p */
    public GPUImageMaskAlphaBlendFilter f14040p;

    /* renamed from: q */
    public boolean f14041q;

    /* renamed from: r */
    public boolean f14042r;

    /* renamed from: s */
    public float f14043s;

    /* renamed from: t */
    public float f14044t;

    /* renamed from: u */
    public int[] f14045u;

    /* renamed from: v */
    public int[] f14046v;

    /* renamed from: w */
    public int[] f14047w;

    /* renamed from: x */
    public int[] f14048x;

    /* renamed from: y */
    public C3096a.b f14049y;

    /* renamed from: z */
    public FloatBuffer f14050z;

    public enum Alignment {
        LEFT,
        RIGHT
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomFilter$a */
    public class RunnableC3082a implements Runnable {
        public RunnableC3082a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GPUImagePanZoomFilter.this.f14041q = false;
            GPUImagePanZoomFilter.this.f14042r = false;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomFilter$b */
    public class RunnableC3083b implements Runnable {
        public RunnableC3083b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GPUImagePanZoomFilter.this.f14041q = false;
            GPUImagePanZoomFilter.this.f14042r = false;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.GPUImagePanZoomFilter$c */
    public class RunnableC3084c implements Runnable {
        public RunnableC3084c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GPUImagePanZoomFilter.this.f14042r = false;
        }
    }

    public GPUImagePanZoomFilter(int i9, int i10, Bitmap bitmap) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nuniform mat4 transformMatrix;\n \nvoid main()\n{\n    gl_Position = (transformMatrix * position);\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        this.f14041q = false;
        this.f14042r = false;
        this.f14043s = -1.0f;
        this.f14044t = -1.0f;
        this.f14023D = new Matrix();
        this.f14026G = null;
        this.f14027H = false;
        this.f14028I = Alignment.LEFT;
        this.f14029J = Globals.m7388i0().getResources().getDimensionPixelSize(R.dimen.gpu_bird_view_offset_x);
        this.f14030K = Globals.m7388i0().getResources().getDimensionPixelSize(R.dimen.gpu_bird_view_offset_y);
        this.f14031L = Globals.m7388i0().getResources().getDimensionPixelSize(R.dimen.gpu_bird_view_width) - (this.f14029J * 2.0f);
        float dimensionPixelSize = Globals.m7388i0().getResources().getDimensionPixelSize(R.dimen.gpu_bird_view_height) - (this.f14030K * 2.0f);
        this.f14032M = dimensionPixelSize;
        this.f14033N = this.f14031L;
        this.f14034O = dimensionPixelSize;
        this.f14035P = null;
        this.f14036Q = null;
        this.f14037R = 0;
        C3096a c3096a = new C3096a(null);
        this.f14038n = c3096a;
        c3096a.m16087D(bitmap, false);
        this.f14026G = bitmap;
        C3096a.b bVar = new C3096a.b();
        this.f14049y = bVar;
        bVar.f14155b = i9;
        bVar.f14156c = i10;
        this.f14039o = new ArrayList();
        float[] fArr = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14050z = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArrM25387c = C6816a.m25387c(Rotation.NORMAL, false, true);
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArrM25387c.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14020A = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArrM25387c).position(0);
        float[] fArr2 = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer3 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14021B = floatBufferAsFloatBuffer3;
        floatBufferAsFloatBuffer3.put(fArr2).position(0);
        FloatBuffer floatBufferAsFloatBuffer4 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14022C = floatBufferAsFloatBuffer4;
        floatBufferAsFloatBuffer4.put(fArr2).position(0);
        FloatBuffer floatBufferAsFloatBuffer5 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14035P = floatBufferAsFloatBuffer5;
        floatBufferAsFloatBuffer5.put(fArr2).position(0);
        FloatBuffer floatBufferAsFloatBuffer6 = ByteBuffer.allocateDirect(fArrM25387c.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14036Q = floatBufferAsFloatBuffer6;
        floatBufferAsFloatBuffer6.put(fArrM25387c).position(0);
    }

    /* renamed from: B */
    public void m16008B(List<C0936w0> list, boolean z8) {
        this.f14039o.addAll(list);
        if (z8) {
            this.f14043s = BitmapDescriptorFactory.HUE_RED;
            this.f14040p = new GPUImageMaskAlphaBlendFilter(BitmapDescriptorFactory.HUE_RED);
        }
    }

    /* renamed from: C */
    public final void m16009C(int i9, int i10) {
        float f9 = this.f14028I == Alignment.LEFT ? ((this.f14029J / i9) * 2.0f) - 1.0f : 1.0f - (((this.f14029J + this.f14031L) / i9) * 2.0f);
        float f10 = i10;
        float f11 = 1.0f - ((this.f14030K / f10) * 2.0f);
        float f12 = ((this.f14031L / i9) * 2.0f) + f9;
        float f13 = f11 - ((this.f14032M / f10) * 2.0f);
        this.f14035P.put(new float[]{f9, f11, f12, f11, f9, f13, f12, f13}).position(0);
    }

    /* renamed from: D */
    public final void m16010D(int i9, int i10) {
        C3096a.b bVar = this.f14049y;
        float f9 = bVar.f14155b / i9;
        float f10 = bVar.f14156c / i10;
        float f11 = f9 * (-1.0f);
        float f12 = f10 * 1.0f;
        float f13 = f9 * 1.0f;
        float f14 = f10 * (-1.0f);
        this.f14022C.put(new float[]{f11, f12, f13, f12, f11, f14, f13, f14}).position(0);
    }

    @SuppressLint({"WrongCall"})
    /* renamed from: E */
    public final void m16011E(int i9) {
        List<C0936w0> list = this.f14039o;
        if (list != null && !this.f14041q) {
            int size = list.size();
            int i10 = i9;
            int i11 = 0;
            while (i11 < size) {
                C0936w0 c0936w0 = this.f14039o.get(i11);
                boolean z8 = i11 < size + (-1);
                if (z8) {
                    GLES20.glBindFramebuffer(36160, this.f14045u[i11]);
                } else {
                    GLES20.glBindFramebuffer(36160, this.f14047w[0]);
                }
                GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                GLES20.glClear(16384);
                c0936w0.mo4046j(i10, this.f14021B, this.f14050z);
                GLES20.glBindFramebuffer(36160, 0);
                if (z8) {
                    i10 = this.f14046v[i11];
                }
                i11++;
            }
            this.f14041q = true;
            this.f14042r = false;
        }
        if (this.f14040p == null || this.f14042r) {
            return;
        }
        GLES20.glBindFramebuffer(36160, this.f14047w[1]);
        GPUImageMaskAlphaBlendFilter gPUImageMaskAlphaBlendFilter = this.f14040p;
        gPUImageMaskAlphaBlendFilter.f4703p = i9;
        gPUImageMaskAlphaBlendFilter.mo4046j(this.f14048x[0], this.f14021B, this.f14050z);
        this.f14040p.f4703p = -1;
        GLES20.glBindFramebuffer(36160, 0);
        this.f14042r = true;
    }

    /* renamed from: F */
    public final void m16012F(int i9, int i10) {
        int i11 = this.f14040p == null ? 1 : 2;
        int[] iArr = new int[i11];
        this.f14047w = iArr;
        this.f14048x = new int[i11];
        GLES20.glGenFramebuffers(i11, iArr, 0);
        GLES20.glGenTextures(i11, this.f14048x, 0);
        for (int i12 = 0; i12 < i11; i12++) {
            GLES20.glBindTexture(3553, this.f14048x[i12]);
            GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9728.0f);
            GLES20.glTexParameterf(3553, 10241, 9728.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.f14047w[i12]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f14048x[i12], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
    }

    /* renamed from: G */
    public final void m16013G(int i9, int i10) {
        List<C0936w0> list = this.f14039o;
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = this.f14039o.size() - 1;
        this.f14045u = new int[size];
        this.f14046v = new int[size];
        for (int i11 = 0; i11 < size; i11++) {
            GLES20.glGenFramebuffers(1, this.f14045u, i11);
            GLES20.glGenTextures(1, this.f14046v, i11);
            GLES20.glBindTexture(3553, this.f14046v[i11]);
            GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9728.0f);
            GLES20.glTexParameterf(3553, 10241, 9728.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.f14045u[i11]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f14046v[i11], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
    }

    /* renamed from: H */
    public final void m16014H() {
        int[] iArr = this.f14048x;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f14048x = null;
        }
        int[] iArr2 = this.f14047w;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f14047w = null;
        }
    }

    /* renamed from: I */
    public final void m16015I() {
        int[] iArr = this.f14046v;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f14046v = null;
        }
        int[] iArr2 = this.f14045u;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f14045u = null;
        }
    }

    /* renamed from: J */
    public final void m16016J(int i9) {
        if (this.f14037R == 0) {
            this.f14037R = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\n\tif ( textureCoordinate.x > 1.0 || textureCoordinate.x < 0.0 || textureCoordinate.y > 1.0 || textureCoordinate.y < 0.0 )\n\t\tgl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);\n\telse\n\t\tgl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        }
        GLES20.glUseProgram(this.f14037R);
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f14037R, "position");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(this.f14037R, "inputTextureCoordinate");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.f14037R, "inputImageTexture");
        this.f14035P.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) this.f14035P);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        this.f14036Q.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) this.f14036Q);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i9);
        GLES20.glUniform1i(iGlGetUniformLocation, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
    }

    /* renamed from: K */
    public final void m16017K() {
        m4406o(new RunnableC3084c());
    }

    /* renamed from: L */
    public void m16018L() {
        m4406o(new RunnableC3083b());
    }

    /* renamed from: M */
    public void m16019M(float f9) {
        if (this.f14044t == f9) {
            return;
        }
        this.f14044t = f9;
        m4406o(new RunnableC3082a());
    }

    /* renamed from: N */
    public void m16020N(float f9) {
        if (this.f14043s == f9) {
            return;
        }
        this.f14043s = f9;
        GPUImageMaskAlphaBlendFilter gPUImageMaskAlphaBlendFilter = this.f14040p;
        if (gPUImageMaskAlphaBlendFilter != null) {
            gPUImageMaskAlphaBlendFilter.m4243B(f9);
            m16017K();
        }
    }

    /* renamed from: O */
    public void m16021O(Matrix matrix) {
        this.f14023D = new Matrix(matrix);
    }

    /* renamed from: P */
    public final void m16022P() {
        float[] fArr = new float[9];
        this.f14023D.getValues(fArr);
        float f9 = fArr[0];
        float f10 = fArr[1];
        float fSqrt = (float) Math.sqrt((f9 * f9) + (f10 * f10));
        float[] fArr2 = this.f14024E;
        fArr2[0] = fSqrt;
        fArr2[5] = fSqrt;
        C3096a.b bVar = this.f14049y;
        float f11 = bVar.f14155b * fSqrt;
        float f12 = fArr[2] * fSqrt;
        float f13 = fArr[5] * fSqrt;
        float f14 = 2.0f / this.f4798i;
        fArr2[12] = (f12 + (f11 / 2.0f)) * (2.0f / this.f4797h);
        fArr2[13] = (-(f13 + ((bVar.f14156c * fSqrt) / 2.0f))) * f14;
        m4415x(this.f14025F, fArr2);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        this.f14041q = false;
        this.f14042r = false;
        m16015I();
        m16014H();
        this.f14038n.m4400c();
        Iterator<C0936w0> it = this.f14039o.iterator();
        while (it.hasNext()) {
            it.next().m4400c();
        }
        GPUImageMaskAlphaBlendFilter gPUImageMaskAlphaBlendFilter = this.f14040p;
        if (gPUImageMaskAlphaBlendFilter != null) {
            gPUImageMaskAlphaBlendFilter.m4400c();
        }
        int i9 = this.f14037R;
        if (i9 != 0) {
            GLES20.glDeleteProgram(i9);
            this.f14037R = 0;
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    @SuppressLint({"WrongCall"})
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        m4407p();
        if (!m4405h() || this.f14045u == null || this.f14046v == null || this.f14047w == null || this.f14048x == null) {
            return;
        }
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        this.f14038n.mo4046j(i9, floatBuffer, floatBuffer2);
        C3096a.b bVar = this.f14049y;
        GLES20.glViewport(0, 0, bVar.f14155b, bVar.f14156c);
        m16011E(i9);
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        m16022P();
        int i10 = this.f14040p == null ? this.f14048x[0] : this.f14048x[1];
        GLES20.glBindTexture(3553, i10);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glBindTexture(3553, 0);
        super.mo4046j(i10, this.f14022C, this.f14020A);
        if (this.f14027H) {
            m16016J(i10);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f14041q = false;
        this.f14042r = false;
        this.f14038n.m4404g();
        Bitmap bitmap = this.f14026G;
        if (bitmap != null) {
            this.f14038n.m16087D(bitmap, false);
        }
        Iterator<C0936w0> it = this.f14039o.iterator();
        while (it.hasNext()) {
            it.next().m4404g();
        }
        GPUImageMaskAlphaBlendFilter gPUImageMaskAlphaBlendFilter = this.f14040p;
        if (gPUImageMaskAlphaBlendFilter != null) {
            gPUImageMaskAlphaBlendFilter.m4404g();
        }
        this.f14025F = GLES20.glGetUniformLocation(this.f4793d, "transformMatrix");
        this.f14024E = new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        this.f14041q = false;
        this.f14042r = false;
        if (this.f14045u != null) {
            m16015I();
        }
        if (this.f14047w != null) {
            m16014H();
        }
        this.f14038n.mo4049n(i9, i10);
        C3096a.b bVar = this.f14049y;
        int i11 = bVar.f14155b;
        int i12 = bVar.f14156c;
        Iterator<C0936w0> it = this.f14039o.iterator();
        while (it.hasNext()) {
            it.next().mo4049n(i11, i12);
        }
        GPUImageMaskAlphaBlendFilter gPUImageMaskAlphaBlendFilter = this.f14040p;
        if (gPUImageMaskAlphaBlendFilter != null) {
            gPUImageMaskAlphaBlendFilter.mo4049n(i11, i12);
        }
        m16012F(i11, i12);
        m16013G(i11, i12);
        m16010D(i9, i10);
        m16009C(i9, i10);
    }
}
