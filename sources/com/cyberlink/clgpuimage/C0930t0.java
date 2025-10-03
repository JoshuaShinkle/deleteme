package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.t0 */
/* loaded from: classes.dex */
public class C0930t0 extends C0936w0 {

    /* renamed from: n */
    public float f4779n;

    /* renamed from: o */
    public float[] f4780o;

    /* renamed from: p */
    public int f4781p;

    /* renamed from: q */
    public int f4782q;

    public C0930t0() {
        this(1.0f, new float[]{1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f});
    }

    /* renamed from: A */
    public void m4396A(float f9) {
        this.f4779n = f9;
        m4408q(this.f4782q, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4781p = GLES20.glGetUniformLocation(m4403f(), "colorMatrix");
        this.f4782q = GLES20.glGetUniformLocation(m4403f(), "intensity");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4396A(this.f4779n);
        m4397z(this.f4780o);
    }

    /* renamed from: z */
    public void m4397z(float[] fArr) {
        this.f4780o = fArr;
        m4415x(this.f4781p, fArr);
    }

    public C0930t0(float f9, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform lowp mat4 colorMatrix;\nuniform lowp float intensity;\n\nvoid main()\n{\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp vec4 outputColor = textureColor * colorMatrix;\n    \n    gl_FragColor = (intensity * outputColor) + ((1.0 - intensity) * textureColor);\n}");
        this.f4779n = f9;
        this.f4780o = fArr;
    }
}
