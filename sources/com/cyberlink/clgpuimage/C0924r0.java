package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.r0 */
/* loaded from: classes.dex */
public class C0924r0 extends C0936w0 {

    /* renamed from: n */
    public int f4745n;

    /* renamed from: o */
    public float f4746o;

    public C0924r0() {
        this(BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4745n = GLES20.glGetUniformLocation(m4403f(), "brightness");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4374z(this.f4746o);
    }

    /* renamed from: z */
    public void m4374z(float f9) {
        this.f4746o = f9;
        m4408q(this.f4745n, f9);
    }

    public C0924r0(float f9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float brightness;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n }");
        this.f4746o = f9;
    }
}
