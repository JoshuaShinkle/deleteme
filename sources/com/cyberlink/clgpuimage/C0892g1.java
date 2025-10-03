package com.cyberlink.clgpuimage;

import android.opengl.GLES20;

/* renamed from: com.cyberlink.clgpuimage.g1 */
/* loaded from: classes.dex */
public class C0892g1 extends C0936w0 {

    /* renamed from: n */
    public int f4563n;

    /* renamed from: o */
    public float f4564o;

    /* renamed from: p */
    public int f4565p;

    /* renamed from: q */
    public float f4566q;

    /* renamed from: r */
    public int f4567r;

    /* renamed from: s */
    public float f4568s;

    /* renamed from: t */
    public boolean f4569t;

    public C0892g1() {
        this(1.0f, 1.0f, 1.0f);
    }

    /* renamed from: A */
    public void m4287A(float f9) {
        this.f4566q = f9;
        if (this.f4569t) {
            m4408q(this.f4565p, f9);
        }
    }

    /* renamed from: B */
    public void m4288B(float f9) {
        this.f4564o = f9;
        if (this.f4569t) {
            m4408q(this.f4563n, f9);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4563n = GLES20.glGetUniformLocation(m4403f(), "red");
        this.f4565p = GLES20.glGetUniformLocation(m4403f(), "green");
        this.f4567r = GLES20.glGetUniformLocation(m4403f(), "blue");
        this.f4569t = true;
        m4288B(this.f4564o);
        m4287A(this.f4566q);
        m4289z(this.f4568s);
    }

    /* renamed from: z */
    public void m4289z(float f9) {
        this.f4568s = f9;
        if (this.f4569t) {
            m4408q(this.f4567r, f9);
        }
    }

    public C0892g1(float f9, float f10, float f11) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform highp float red;\n  uniform highp float green;\n  uniform highp float blue;\n  \n  void main()\n  {\n      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n  }\n");
        this.f4569t = false;
        this.f4564o = f9;
        this.f4566q = f10;
        this.f4568s = f11;
    }
}
