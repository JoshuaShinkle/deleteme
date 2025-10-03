package com.cyberlink.clgpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.o1 */
/* loaded from: classes.dex */
public class C0916o1 extends C0936w0 {

    /* renamed from: n */
    public int f4715n;

    /* renamed from: o */
    public PointF f4716o;

    /* renamed from: p */
    public int f4717p;

    /* renamed from: q */
    public float[] f4718q;

    /* renamed from: r */
    public int f4719r;

    /* renamed from: s */
    public float f4720s;

    /* renamed from: t */
    public int f4721t;

    /* renamed from: u */
    public float f4722u;

    /* renamed from: v */
    public int f4723v;

    /* renamed from: w */
    public float f4724w;

    public C0916o1() {
        this(new PointF(0.5f, 0.5f), new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED}, 1.0f, 0.3f, 0.75f);
    }

    /* renamed from: A */
    public void m4362A(PointF pointF) {
        this.f4716o = pointF;
        m4412u(this.f4715n, pointF);
    }

    /* renamed from: B */
    public void m4363B(float[] fArr) {
        this.f4718q = fArr;
        m4410s(this.f4717p, fArr);
    }

    /* renamed from: C */
    public void m4364C(float f9) {
        this.f4724w = f9;
        m4408q(this.f4723v, f9);
    }

    /* renamed from: D */
    public void m4365D(float f9) {
        this.f4722u = f9;
        m4408q(this.f4721t, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4715n = GLES20.glGetUniformLocation(m4403f(), "vignetteCenter");
        this.f4717p = GLES20.glGetUniformLocation(m4403f(), "vignetteColor");
        this.f4719r = GLES20.glGetUniformLocation(m4403f(), "vignetteAlpha");
        this.f4721t = GLES20.glGetUniformLocation(m4403f(), "vignetteStart");
        this.f4723v = GLES20.glGetUniformLocation(m4403f(), "vignetteEnd");
        m4362A(this.f4716o);
        m4363B(this.f4718q);
        m4366z(this.f4720s);
        m4365D(this.f4722u);
        m4364C(this.f4724w);
    }

    /* renamed from: z */
    public void m4366z(float f9) {
        this.f4720s = f9;
        m4408q(this.f4719r, f9);
    }

    public C0916o1(PointF pointF, float[] fArr, float f9, float f10, float f11) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n \n uniform lowp vec2 vignetteCenter;\n uniform lowp vec3 vignetteColor;\n uniform lowp float vignetteAlpha;\n uniform highp float vignetteStart;\n uniform highp float vignetteEnd;\n \n void main()\n {\n     /*\n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(0.5,0.5));\n     rgb *= (1.0 - smoothstep(vignetteStart, vignetteEnd, d));\n     gl_FragColor = vec4(vec3(rgb),1.0);\n      */\n     \n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(vignetteCenter.x, vignetteCenter.y));\n     lowp float percent = smoothstep(vignetteStart, vignetteEnd, d);\n     gl_FragColor = vec4(mix(rgb, mix(rgb, vignetteColor, vignetteAlpha), percent), 1.0);\n }");
        this.f4716o = pointF;
        this.f4718q = fArr;
        this.f4720s = f9;
        this.f4722u = f10;
        this.f4724w = f11;
    }
}
