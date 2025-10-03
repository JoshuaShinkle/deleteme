package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.p1 */
/* loaded from: classes.dex */
public class C0919p1 extends C0936w0 {

    /* renamed from: n */
    public int f4732n;

    /* renamed from: o */
    public float f4733o;

    /* renamed from: p */
    public int f4734p;

    /* renamed from: q */
    public float f4735q;

    public C0919p1() {
        this(5000.0f, BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: A */
    public void m4370A(float f9) {
        this.f4735q = f9;
        m4408q(this.f4734p, (float) (f9 / 100.0d));
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4732n = GLES20.glGetUniformLocation(m4403f(), "temperature");
        this.f4734p = GLES20.glGetUniformLocation(m4403f(), "tint");
        m4371z(this.f4733o);
        m4370A(this.f4735q);
    }

    /* renamed from: z */
    public void m4371z(float f9) {
        this.f4733o = f9;
        m4408q(this.f4732n, (float) ((f9 - 5000.0d) * (f9 < 5000.0f ? 4.0E-4d : 6.0E-5d)));
    }

    public C0919p1(float f9, float f10) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "uniform sampler2D inputImageTexture;\nvarying highp vec2 textureCoordinate;\n \nuniform lowp float temperature;\nuniform lowp float tint;\n\nconst lowp vec3 warmFilter = vec3(0.93, 0.54, 0.0);\n\nconst mediump mat3 RGBtoYIQ = mat3(0.299, 0.587, 0.114, 0.596, -0.274, -0.322, 0.212, -0.523, 0.311);\nconst mediump mat3 YIQtoRGB = mat3(1.0, 0.956, 0.621, 1.0, -0.272, -0.647, 1.0, -1.105, 1.702);\n\nvoid main()\n{\n\tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n\t\n\tmediump vec3 yiq = RGBtoYIQ * source.rgb; //adjusting tint\n\tyiq.b = clamp(yiq.b + tint*0.5226*0.1, -0.5226, 0.5226);\n\tlowp vec3 rgb = YIQtoRGB * yiq;\n\n\tlowp vec3 processed = vec3(\n\t\t(rgb.r < 0.5 ? (2.0 * rgb.r * warmFilter.r) : (1.0 - 2.0 * (1.0 - rgb.r) * (1.0 - warmFilter.r))), //adjusting temperature\n\t\t(rgb.g < 0.5 ? (2.0 * rgb.g * warmFilter.g) : (1.0 - 2.0 * (1.0 - rgb.g) * (1.0 - warmFilter.g))), \n\t\t(rgb.b < 0.5 ? (2.0 * rgb.b * warmFilter.b) : (1.0 - 2.0 * (1.0 - rgb.b) * (1.0 - warmFilter.b))));\n\n\tgl_FragColor = vec4(mix(rgb, processed, temperature), source.a);\n}");
        this.f4733o = f9;
        this.f4735q = f10;
    }
}
