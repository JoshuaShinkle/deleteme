package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.k1 */
/* loaded from: classes.dex */
public class C0904k1 extends C0936w0 {

    /* renamed from: n */
    public float f4680n;

    /* renamed from: o */
    public boolean f4681o;

    /* renamed from: p */
    public float[] f4682p;

    /* renamed from: q */
    public float[] f4683q;

    /* renamed from: r */
    public float[] f4684r;

    /* renamed from: s */
    public float[] f4685s;

    /* renamed from: t */
    public int f4686t;

    /* renamed from: u */
    public int f4687u;

    /* renamed from: v */
    public int f4688v;

    /* renamed from: w */
    public int f4689w;

    public C0904k1() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " precision lowp float;\n  \n  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform int blackwhite;\n  uniform float balance;\n  uniform vec3 highlight;\n  uniform vec3 shadow;  \n  const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n  \n  void main()\n  {\n \tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n \tfloat luminance = dot(textureColor.rgb, luminanceWeighting);\n \t\n \tlowp vec4 overlay = (blackwhite == 0) ? textureColor :vec4(vec3(luminance), 1.0);\n \t\n \t//overlay\n \tlowp vec4 highlightColor = vec4(\n                                  (overlay.r < 0.5 ? (2.0 * overlay.r * highlight.r) : (1.0 - 2.0 * (1.0 - overlay.r) * (1.0 - highlight.r))),\n                                  (overlay.g < 0.5 ? (2.0 * overlay.g * highlight.g) : (1.0 - 2.0 * (1.0 - overlay.g) * (1.0 - highlight.g))),\n                                  (overlay.b < 0.5 ? (2.0 * overlay.b * highlight.b) : (1.0 - 2.0 * (1.0 - overlay.b) * (1.0 - highlight.b))),\n                                  1.0\n                                  );\n \t\n \tlowp vec4 shadowlightColor = vec4(\n                                  (overlay.r < 0.5 ? (2.0 * overlay.r * shadow.r) : (1.0 - 2.0 * (1.0 - overlay.r) * (1.0 - shadow.r))),\n                                  (overlay.g < 0.5 ? (2.0 * overlay.g * shadow.g) : (1.0 - 2.0 * (1.0 - overlay.g) * (1.0 - shadow.g))),\n                                  (overlay.b < 0.5 ? (2.0 * overlay.b * shadow.b) : (1.0 - 2.0 * (1.0 - overlay.b) * (1.0 - shadow.b))),\n                                  1.0\n                                  );\n \t\n \tgl_FragColor = vec4( mix(shadowlightColor.rgb, highlightColor.rgb, balance*luminance), textureColor.a);\n  }");
        this.f4682p = new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};
        this.f4683q = new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f};
        this.f4684r = new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f, 0.5f};
        this.f4685s = new float[]{BitmapDescriptorFactory.HUE_RED, 1.0f, 0.5f};
        this.f4680n = 0.5f;
        this.f4681o = false;
    }

    /* renamed from: A */
    public void m4352A(boolean z8) {
        this.f4681o = z8;
        if (z8) {
            m4411t(this.f4687u, 1);
        } else {
            m4411t(this.f4687u, 0);
        }
    }

    /* renamed from: B */
    public void m4353B(float[] fArr) {
        this.f4682p = fArr;
        m4410s(this.f4688v, fArr);
    }

    /* renamed from: C */
    public void m4354C(float[] fArr) {
        this.f4683q = fArr;
        m4410s(this.f4689w, fArr);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4686t = GLES20.glGetUniformLocation(m4403f(), "balance");
        this.f4687u = GLES20.glGetUniformLocation(m4403f(), "blackwhite");
        this.f4688v = GLES20.glGetUniformLocation(m4403f(), "highlight");
        this.f4689w = GLES20.glGetUniformLocation(m4403f(), "shadow");
        m4355z(this.f4680n);
        m4353B(this.f4682p);
        m4354C(this.f4683q);
        m4352A(this.f4681o);
    }

    /* renamed from: z */
    public void m4355z(float f9) {
        this.f4680n = f9;
        m4408q(this.f4686t, f9);
    }
}
