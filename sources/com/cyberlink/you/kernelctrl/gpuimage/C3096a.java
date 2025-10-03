package com.cyberlink.you.kernelctrl.gpuimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.cyberlink.clgpuimage.C0895h1;
import com.cyberlink.clgpuimage.C0925r1;
import com.cyberlink.clgpuimage.C0936w0;
import com.cyberlink.clgpuimage.Rotation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import p126l3.C5283b;
import p253z1.C6816a;

/* renamed from: com.cyberlink.you.kernelctrl.gpuimage.a */
/* loaded from: classes.dex */
public class C3096a extends C0936w0 {

    /* renamed from: n */
    public C0936w0 f14139n;

    /* renamed from: p */
    public FloatBuffer f14141p;

    /* renamed from: q */
    public FloatBuffer f14142q;

    /* renamed from: r */
    public Rotation f14143r = Rotation.NORMAL;

    /* renamed from: s */
    public boolean f14144s = false;

    /* renamed from: t */
    public boolean f14145t = true;

    /* renamed from: u */
    public int f14146u = -1;

    /* renamed from: v */
    public int f14147v = -1;

    /* renamed from: w */
    public IntBuffer f14148w = IntBuffer.allocate(4);

    /* renamed from: x */
    public boolean f14149x = false;

    /* renamed from: y */
    public boolean f14150y = true;

    /* renamed from: o */
    public b f14140o = new b();

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.a$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Bitmap f14151b;

        /* renamed from: c */
        public final /* synthetic */ boolean f14152c;

        public a(Bitmap bitmap, boolean z8) {
            this.f14151b = bitmap;
            this.f14152c = z8;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C3096a.this.f14140o.f14154a != -1) {
                GLES20.glDeleteTextures(1, new int[]{C3096a.this.f14140o.f14154a}, 0);
            }
            C3096a.this.f14140o.f14154a = C0925r1.m4379e(this.f14151b, -1, this.f14152c);
            C3096a.this.f14140o.f14155b = this.f14151b.getWidth();
            C3096a.this.f14140o.f14156c = this.f14151b.getHeight();
            C3096a.this.f14149x = true;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.a$b */
    public static class b {

        /* renamed from: a */
        public int f14154a = -1;

        /* renamed from: b */
        public int f14155b = -1;

        /* renamed from: c */
        public int f14156c = -1;
    }

    public C3096a(C0936w0 c0936w0) {
        m16088E(c0936w0);
    }

    /* renamed from: B */
    public final void m16085B() {
        int i9;
        int i10;
        float f9;
        float f10;
        GLES20.glGetIntegerv(2978, this.f14148w);
        int i11 = this.f14148w.get(2);
        int i12 = this.f14148w.get(3);
        if (i11 == 0 || i12 == 0) {
            return;
        }
        int iM20562a = C5283b.m20562a(i11, i12);
        int i13 = i11 / iM20562a;
        int i14 = i12 / iM20562a;
        if (i13 == this.f14146u && i14 == this.f14147v) {
            return;
        }
        this.f14146u = i13;
        this.f14147v = i14;
        Rotation rotation = this.f14143r;
        if (rotation == Rotation.ROTATION_90 || rotation == Rotation.ROTATION_270) {
            b bVar = this.f14140o;
            i9 = bVar.f14156c;
            i10 = bVar.f14155b;
        } else {
            b bVar2 = this.f14140o;
            i9 = bVar2.f14155b;
            i10 = bVar2.f14156c;
        }
        float f11 = i13 / i9;
        float f12 = i14 / i10;
        if (f11 < f12) {
            f10 = f12 / f11;
            f9 = 1.0f;
        } else if (f11 > f12) {
            f9 = f11 / f12;
            f10 = 1.0f;
        } else {
            f9 = 1.0f;
            f10 = 1.0f;
        }
        float f13 = f10 * (-1.0f);
        float f14 = f9 * 1.0f;
        float f15 = f10 * 1.0f;
        float f16 = f9 * (-1.0f);
        this.f14142q.put(new float[]{f13, f14, f15, f14, f13, f16, f15, f16}).position(0);
    }

    /* renamed from: C */
    public final void m16086C() {
        this.f14141p.put(C6816a.m25387c(this.f14143r, this.f14144s, this.f14145t)).position(0);
    }

    /* renamed from: D */
    public void m16087D(Bitmap bitmap, boolean z8) {
        m4406o(new a(bitmap, z8));
    }

    /* renamed from: E */
    public final void m16088E(C0936w0 c0936w0) {
        this.f14139n = c0936w0;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        C0936w0 c0936w0 = this.f14139n;
        if (c0936w0 != null) {
            c0936w0.m4400c();
        }
        this.f14146u = -1;
        this.f14147v = -1;
        int i9 = this.f14140o.f14154a;
        if (i9 != -1 && this.f14150y) {
            GLES20.glDeleteTextures(1, new int[]{i9}, 0);
            b bVar = this.f14140o;
            bVar.f14154a = -1;
            bVar.f14155b = -1;
            bVar.f14156c = -1;
            this.f14149x = false;
        }
        this.f14150y = true;
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    @SuppressLint({"WrongCall"})
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        C0936w0 c0936w0 = this.f14139n;
        if (c0936w0 != null) {
            c0936w0.mo4046j(i9, floatBuffer, floatBuffer2);
        }
        m4407p();
        if (!m4405h() || this.f14140o.f14154a == -1) {
            return;
        }
        m16085B();
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        super.mo4046j(this.f14140o.f14154a, this.f14142q, this.f14141p);
        GLES20.glDisable(3042);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        C0936w0 c0936w0 = this.f14139n;
        if (c0936w0 != null) {
            c0936w0.m4404g();
        }
        this.f14141p = ByteBuffer.allocateDirect(C6816a.f22570a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        m16086C();
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f14142q = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        C0936w0 c0936w0 = this.f14139n;
        if (c0936w0 != null) {
            c0936w0.mo4049n(i9, i10);
        }
    }
}
