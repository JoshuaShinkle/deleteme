package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.e1 */
/* loaded from: classes.dex */
public class C0886e1 extends C0936w0 {

    /* renamed from: n */
    public int f4467n;

    /* renamed from: o */
    public float f4468o;

    /* renamed from: p */
    public int f4469p;

    /* renamed from: q */
    public float[] f4470q;

    public C0886e1() {
        this(BitmapDescriptorFactory.HUE_RED, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
    }

    /* renamed from: A */
    public void m4245A(float[] fArr) {
        this.f4470q = fArr;
        m4247z(fArr[0], fArr[1], fArr[2]);
    }

    /* renamed from: B */
    public void m4246B(float f9) {
        this.f4468o = f9;
        m4408q(this.f4467n, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4467n = GLES20.glGetUniformLocation(m4403f(), "intensity");
        this.f4469p = GLES20.glGetUniformLocation(m4403f(), "filterColor");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4246B(this.f4468o);
        m4245A(this.f4470q);
    }

    /* renamed from: z */
    public void m4247z(float f9, float f10, float f11) {
        m4410s(this.f4469p, new float[]{f9, f10, f11});
    }

    public C0886e1(float f9, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " precision lowp float;\n  \n  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform float intensity;\n  uniform vec3 filterColor;\n  \n  const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n  \n  void main()\n  {\n \t//desat, then apply overlay blend\n \tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n \tfloat luminance = dot(textureColor.rgb, luminanceWeighting);\n \t\n \tlowp vec4 desat = vec4(vec3(luminance), 1.0);\n \t\n \t//overlay\n \tlowp vec4 outputColor = vec4(\n                                  (desat.r < 0.5 ? (2.0 * desat.r * filterColor.r) : (1.0 - 2.0 * (1.0 - desat.r) * (1.0 - filterColor.r))),\n                                  (desat.g < 0.5 ? (2.0 * desat.g * filterColor.g) : (1.0 - 2.0 * (1.0 - desat.g) * (1.0 - filterColor.g))),\n                                  (desat.b < 0.5 ? (2.0 * desat.b * filterColor.b) : (1.0 - 2.0 * (1.0 - desat.b) * (1.0 - filterColor.b))),\n                                  1.0\n                                  );\n \t\n \t//which is better, or are they equal?\n \tgl_FragColor = vec4( mix(textureColor.rgb, outputColor.rgb, intensity), textureColor.a);\n  }");
        this.f4468o = f9;
        this.f4470q = fArr;
    }
}
