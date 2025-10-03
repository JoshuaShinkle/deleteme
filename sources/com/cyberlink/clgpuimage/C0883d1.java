package com.cyberlink.clgpuimage;

import android.opengl.GLES20;

/* renamed from: com.cyberlink.clgpuimage.d1 */
/* loaded from: classes.dex */
public class C0883d1 extends C0910m1 {

    /* renamed from: s */
    public int f4464s;

    /* renamed from: t */
    public float f4465t;

    public C0883d1(String str, float f9) {
        super(str);
        this.f4465t = f9;
    }

    /* renamed from: B */
    public void m4243B(float f9) {
        this.f4465t = f9;
        m4408q(this.f4464s, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0910m1, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4464s = GLES20.glGetUniformLocation(m4403f(), "mixturePercent");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4243B(this.f4465t);
    }
}
