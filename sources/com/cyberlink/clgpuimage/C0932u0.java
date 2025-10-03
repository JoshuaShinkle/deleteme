package com.cyberlink.clgpuimage;

import android.opengl.GLES20;

/* renamed from: com.cyberlink.clgpuimage.u0 */
/* loaded from: classes.dex */
public class C0932u0 extends C0936w0 {

    /* renamed from: n */
    public int f4784n;

    /* renamed from: o */
    public float f4785o;

    public C0932u0() {
        this(1.0f);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4784n = GLES20.glGetUniformLocation(m4403f(), "contrast");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4398z(this.f4785o);
    }

    /* renamed from: z */
    public void m4398z(float f9) {
        this.f4785o = f9;
        m4408q(this.f4784n, f9);
    }

    public C0932u0(float f9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float contrast;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n }");
        this.f4785o = f9;
    }
}
