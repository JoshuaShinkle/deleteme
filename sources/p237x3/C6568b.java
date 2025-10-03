package p237x3;

import android.opengl.GLES20;
import com.cyberlink.clgpuimage.C0936w0;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: x3.b */
/* loaded from: classes.dex */
public class C6568b extends C0936w0 {

    /* renamed from: n */
    public int f22083n;

    /* renamed from: o */
    public float f22084o;

    /* renamed from: p */
    public int f22085p;

    /* renamed from: q */
    public float f22086q;

    public C6568b() {
        this(BitmapDescriptorFactory.HUE_RED, 1.0f);
    }

    /* renamed from: A */
    public void m25169A(float f9) {
        this.f22084o = f9;
        m4408q(this.f22083n, f9);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f22085p = GLES20.glGetUniformLocation(m4403f(), "highlights");
        this.f22083n = GLES20.glGetUniformLocation(m4403f(), "shadows");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m25170z(this.f22086q);
        m25169A(this.f22084o);
    }

    /* renamed from: z */
    public void m25170z(float f9) {
        this.f22086q = f9;
        m4408q(this.f22085p, f9);
    }

    public C6568b(float f9, float f10) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "uniform sampler2D inputImageTexture;\nvarying highp vec2 textureCoordinate;\n\nuniform lowp float shadows;\nuniform lowp float highlights;\n\nconst mediump vec3 luminanceWeighting = vec3(0.3, 0.3, 0.3);\n\nvoid main()\n{\n\tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n\tmediump float luminance = dot(source.rgb, luminanceWeighting);\n\n\tmediump float shadow = clamp((pow(luminance, 1.0/shadows) + (-0.76)*pow(luminance, 2.0/shadows)) - luminance, 0.0, 1.0);\n\tmediump float highlight = clamp((1.0 - (pow(1.0-luminance, 1.0/(2.0-highlights)) + (-0.8)*pow(1.0-luminance, 2.0/(2.0-highlights)))) - luminance, -1.0, 0.0);\n\tlowp vec3 result = vec3(0.0, 0.0, 0.0) + ((luminance + shadow + highlight) - 0.0) * ((source.rgb - vec3(0.0, 0.0, 0.0))/(luminance - 0.0));\n\n\tmediump float contrastedLuminance = ((luminance - 0.5) * 1.5) + 0.5;\n\tmediump float whiteInterp = contrastedLuminance * contrastedLuminance * contrastedLuminance;\n\tmediump float whiteTarget = clamp(highlights, 1.0, 2.0) - 1.0;\n\tresult = mix(result, vec3(1.0), whiteInterp * whiteTarget);\n\n\tmediump float invContrastedLuminance = 1.0 - contrastedLuminance;\n\tmediump float blackInterp = invContrastedLuminance * invContrastedLuminance * invContrastedLuminance;\n\tmediump float blackTarget = 1.0 - clamp(shadows, 0.0, 1.0);\n\tresult = mix(result, vec3(0.0), blackInterp * blackTarget);\n\tgl_FragColor = vec4(result, source.a);\n}");
        this.f22086q = f10;
        this.f22084o = f9;
    }
}
