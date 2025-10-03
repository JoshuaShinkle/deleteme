package com.cyberlink.clgpuimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.x0 */
/* loaded from: classes.dex */
public class C0938x0 extends GPUImageMaskAlphaBlendFilter {

    /* renamed from: R */
    public String f4822R;

    /* renamed from: S */
    public List<C0936w0> f4823S;

    /* renamed from: T */
    public List<C0936w0> f4824T;

    /* renamed from: U */
    public int[] f4825U;

    /* renamed from: V */
    public int[] f4826V;

    /* renamed from: W */
    public int[] f4827W;

    /* renamed from: X */
    public int[] f4828X;

    /* renamed from: Y */
    public final FloatBuffer f4829Y;

    /* renamed from: Z */
    public final FloatBuffer f4830Z;

    /* renamed from: a0 */
    public boolean f4831a0;

    /* renamed from: b0 */
    public boolean f4832b0;

    /* renamed from: c0 */
    public boolean f4833c0;

    public C0938x0() {
        this(null, false, false);
    }

    /* renamed from: K */
    public void m4417K(C0936w0 c0936w0) {
        if (c0936w0 == null) {
            return;
        }
        this.f4831a0 = false;
        this.f4823S.add(c0936w0);
        m4425S();
    }

    /* renamed from: L */
    public final void m4418L(int i9, int i10) {
        int[] iArr = new int[1];
        this.f4827W = iArr;
        this.f4828X = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4828X, 0);
        GLES20.glBindTexture(3553, this.f4828X[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4827W[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4828X[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: M */
    public final void m4419M(int i9, int i10) {
        int size = this.f4823S.size();
        for (int i11 = 0; i11 < size; i11++) {
            this.f4823S.get(i11).mo4049n(i9, i10);
        }
        List<C0936w0> list = this.f4824T;
        if (list == null) {
            return;
        }
        boolean z8 = this.f4832b0;
        int size2 = list.size() - 1;
        if (z8) {
            size2 = Math.min(size2, 2);
        }
        int i12 = size2;
        this.f4825U = new int[i12];
        this.f4826V = new int[i12];
        for (int i13 = 0; i13 < i12; i13++) {
            GLES20.glGenFramebuffers(1, this.f4825U, i13);
            GLES20.glGenTextures(1, this.f4826V, i13);
            GLES20.glBindTexture(3553, this.f4826V[i13]);
            GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
            int i14 = 9728;
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, this.f4833c0 ? 9728 : 9729);
            if (!this.f4833c0) {
                i14 = 9729;
            }
            GLES20.glTexParameterf(3553, 10241, i14);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.f4825U[i13]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4826V[i13], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
    }

    /* renamed from: N */
    public final void m4420N() {
        int[] iArr = this.f4828X;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4828X = null;
        }
        int[] iArr2 = this.f4827W;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4827W = null;
        }
    }

    /* renamed from: O */
    public final void m4421O() {
        int[] iArr = this.f4826V;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4826V = null;
        }
        int[] iArr2 = this.f4825U;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4825U = null;
        }
    }

    /* renamed from: P */
    public C0936w0 m4422P(Class<? extends C0936w0> cls) {
        synchronized (this.f4824T) {
            for (C0936w0 c0936w0 : this.f4824T) {
                if (cls.isInstance(c0936w0)) {
                    return c0936w0;
                }
            }
            return null;
        }
    }

    /* renamed from: Q */
    public List<C0936w0> m4423Q() {
        return this.f4824T;
    }

    /* renamed from: R */
    public void m4424R() {
        Log.e(this.f4822R, "Invalidate cache");
        this.f4831a0 = false;
    }

    /* renamed from: S */
    public void m4425S() {
        if (this.f4823S == null) {
            return;
        }
        List<C0936w0> list = this.f4824T;
        if (list == null) {
            this.f4824T = new ArrayList();
        } else {
            list.clear();
        }
        for (C0936w0 c0936w0 : this.f4823S) {
            if (c0936w0 instanceof C0938x0) {
                C0938x0 c0938x0 = (C0938x0) c0936w0;
                c0938x0.m4425S();
                List<C0936w0> listM4423Q = c0938x0.m4423Q();
                if (listM4423Q != null && !listM4423Q.isEmpty()) {
                    this.f4824T.addAll(listM4423Q);
                }
            } else {
                this.f4824T.add(c0936w0);
            }
        }
    }

    @Override // com.cyberlink.clgpuimage.GPUImageMaskAlphaBlendFilter, com.cyberlink.clgpuimage.C0910m1, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        this.f4831a0 = false;
        m4421O();
        m4420N();
        Iterator<C0936w0> it = this.f4823S.iterator();
        while (it.hasNext()) {
            it.next().m4400c();
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.GPUImageMaskAlphaBlendFilter, com.cyberlink.clgpuimage.C0936w0
    @SuppressLint({"WrongCall"})
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        m4407p();
        if (!m4405h() || this.f4825U == null || this.f4826V == null || this.f4827W == null || this.f4828X == null) {
            return;
        }
        if (this.f4703p == -1) {
            this.f4703p = i9;
        }
        List<C0936w0> list = this.f4824T;
        if (list != null && !this.f4831a0) {
            int size = list.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    break;
                }
                C0936w0 c0936w0 = this.f4824T.get(i10);
                boolean z8 = i10 < size + (-1);
                if (z8) {
                    GLES20.glBindFramebuffer(36160, this.f4825U[this.f4832b0 ? i10 % 2 : i10]);
                } else {
                    GLES20.glBindFramebuffer(36160, this.f4827W[0]);
                }
                GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                GLES20.glClear(16384);
                c0936w0.mo4046j(i9, this.f4829Y, this.f4830Z);
                if (z8) {
                    GLES20.glBindFramebuffer(36160, 0);
                    i9 = this.f4826V[this.f4832b0 ? i10 % 2 : i10];
                } else {
                    GLES20.glBindFramebuffer(36160, 0);
                }
                i10++;
            }
            this.f4831a0 = true;
        }
        super.mo4046j(this.f4828X[0], floatBuffer, floatBuffer2);
        this.f4703p = -1;
    }

    @Override // com.cyberlink.clgpuimage.GPUImageMaskAlphaBlendFilter, com.cyberlink.clgpuimage.C0883d1, com.cyberlink.clgpuimage.C0910m1, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        this.f4831a0 = false;
        super.mo4047l();
        Iterator<C0936w0> it = this.f4823S.iterator();
        while (it.hasNext()) {
            it.next().m4404g();
        }
    }

    @Override // com.cyberlink.clgpuimage.GPUImageMaskAlphaBlendFilter, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        this.f4831a0 = false;
        if (this.f4825U != null) {
            m4421O();
        }
        if (this.f4827W != null) {
            m4420N();
        }
        m4418L(i9, i10);
        m4419M(i9, i10);
    }

    @Override // com.cyberlink.clgpuimage.C0910m1
    /* renamed from: z */
    public void mo4359z(Bitmap bitmap) {
        throw new RuntimeException("GPUImageFilterGroupEx doesn't support setBitmap().");
    }

    public C0938x0(boolean z8, boolean z9) {
        this(null, z8, z9);
    }

    public C0938x0(List<C0936w0> list, boolean z8, boolean z9) {
        super(BitmapDescriptorFactory.HUE_RED);
        this.f4822R = "GPUImageFilterGroupEx";
        this.f4831a0 = false;
        this.f4832b0 = z8;
        this.f4833c0 = z9;
        this.f4823S = list;
        if (list == null) {
            this.f4823S = new ArrayList();
        } else {
            m4425S();
        }
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4829Y = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4830Z = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        m4358A(Rotation.NORMAL, false, true);
    }
}
