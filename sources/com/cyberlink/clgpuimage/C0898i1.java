package com.cyberlink.clgpuimage;

import android.opengl.GLES20;

/* renamed from: com.cyberlink.clgpuimage.i1 */
/* loaded from: classes.dex */
public class C0898i1 extends C0936w0 {

    /* renamed from: n */
    public int f4674n;

    /* renamed from: o */
    public float f4675o;

    public C0898i1() {
        this(1.0f);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4674n = GLES20.glGetUniformLocation(m4403f(), "saturation");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4351z(this.f4675o);
    }

    /* renamed from: z */
    public void m4351z(float f9) {
        this.f4675o = f9;
        m4408q(this.f4674n, f9);
    }

    public C0898i1(float f9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }");
        this.f4675o = f9;
    }
}
