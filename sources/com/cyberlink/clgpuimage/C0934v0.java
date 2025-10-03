package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.v0 */
/* loaded from: classes.dex */
public class C0934v0 extends C0936w0 {

    /* renamed from: n */
    public int f4787n;

    /* renamed from: o */
    public float f4788o;

    public C0934v0() {
        this(BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4787n = GLES20.glGetUniformLocation(m4403f(), "exposure");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4399z(this.f4788o);
    }

    /* renamed from: z */
    public void m4399z(float f9) {
        this.f4788o = f9;
        m4408q(this.f4787n, f9);
    }

    public C0934v0(float f9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform highp float exposure;\n \n void main()\n {\n     highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(textureColor.rgb * pow(2.0, exposure), textureColor.w);\n } ");
        this.f4788o = f9;
    }
}
