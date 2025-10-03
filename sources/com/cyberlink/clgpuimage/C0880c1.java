package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.c1 */
/* loaded from: classes.dex */
public class C0880c1 extends C0936w0 {

    /* renamed from: n */
    public int f4454n;

    /* renamed from: o */
    public float f4455o;

    /* renamed from: p */
    public int f4456p;

    /* renamed from: q */
    public float f4457q;

    public C0880c1() {
        this(BitmapDescriptorFactory.HUE_RED, 1.0f);
    }

    /* renamed from: A */
    public void m4237A(float f9) {
        this.f4455o = f9;
        m4408q(this.f4454n, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4456p = GLES20.glGetUniformLocation(m4403f(), "highlights");
        this.f4454n = GLES20.glGetUniformLocation(m4403f(), "shadows");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4238z(this.f4457q);
        m4237A(this.f4455o);
    }

    /* renamed from: z */
    public void m4238z(float f9) {
        this.f4457q = f9;
        m4408q(this.f4456p, f9);
    }

    public C0880c1(float f9, float f10) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n  \n uniform lowp float shadows;\n uniform lowp float highlights;\n \n const mediump vec3 luminanceWeighting = vec3(0.3, 0.3, 0.3);\n \n void main()\n {\n \tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n \tmediump float luminance = dot(source.rgb, luminanceWeighting);\n \n \tmediump float shadow = clamp((pow(luminance, 1.0/(shadows+1.0)) + (-0.76)*pow(luminance, 2.0/(shadows+1.0))) - luminance, 0.0, 1.0);\n \tmediump float highlight = clamp((1.0 - (pow(1.0-luminance, 1.0/(2.0-highlights)) + (-0.8)*pow(1.0-luminance, 2.0/(2.0-highlights)))) - luminance, -1.0, 0.0);\n \tlowp vec3 result = vec3(0.0, 0.0, 0.0) + ((luminance + shadow + highlight) - 0.0) * ((source.rgb - vec3(0.0, 0.0, 0.0))/(luminance - 0.0));\n \n \tgl_FragColor = vec4(result.rgb, source.a);\n }");
        this.f4457q = f10;
        this.f4455o = f9;
    }
}
