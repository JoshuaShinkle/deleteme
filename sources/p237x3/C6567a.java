package p237x3;

import android.opengl.GLES20;
import com.cyberlink.clgpuimage.C0936w0;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.ByteBuffer;
import p116k4.C5188w;

/* renamed from: x3.a */
/* loaded from: classes.dex */
public class C6567a extends C0936w0 {

    /* renamed from: n */
    public int f22077n;

    /* renamed from: o */
    public float f22078o;

    /* renamed from: p */
    public int[] f22079p;

    /* renamed from: q */
    public int f22080q;

    /* renamed from: x3.a$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ float f22081b;

        public a(float f9) {
            this.f22081b = f9;
        }

        @Override // java.lang.Runnable
        public void run() {
            float f9 = this.f22081b / 100.0f;
            float f10 = 255;
            float f11 = 0.21363f * f10;
            float f12 = ((0.15258998f * f9) + 0.61036f) * f10;
            float[] fArr = {BitmapDescriptorFactory.HUE_RED, f10 * 0.10681f, f11, f10 * 0.61036f, f10};
            float[] fArr2 = {BitmapDescriptorFactory.HUE_RED, (0.10681f - (0.030519992f * f9)) * f10, f11, f12, f10};
            float f13 = 1.0f - (f9 * 0.8f);
            float[] fArr3 = {f13, 1.0f, (f12 - f11) / (fArr[3] - fArr[2]), 1.0f, f13};
            int[] iArr = new int[256];
            for (int i9 = 0; i9 <= 255; i9++) {
                iArr[i9] = 0;
            }
            for (int i10 = 0; i10 <= 255; i10++) {
                iArr[i10] = Math.max(0, Math.min((int) C5188w.m20274a(fArr, fArr2, fArr3, 5, i10), 255));
            }
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, C6567a.this.f22079p[0]);
            byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
            for (int i11 = 0; i11 <= 255; i11++) {
                int i12 = i11 * 4;
                int i13 = iArr[i11];
                bArr[i12 + 2] = (byte) i13;
                bArr[i12 + 1] = (byte) i13;
                bArr[i12] = (byte) i13;
                bArr[i12 + 3] = (byte) i13;
            }
            GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
        }
    }

    public C6567a() {
        this(BitmapDescriptorFactory.HUE_RED);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        super.mo4045i();
        int[] iArr = this.f22079p;
        if (iArr[0] != -1) {
            GLES20.glDeleteTextures(1, iArr, 0);
            this.f22079p[0] = -1;
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        if (this.f22079p[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f22079p[0]);
            GLES20.glUniform1i(this.f22080q, 3);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f22077n = GLES20.glGetUniformLocation(m4403f(), "contrast");
        this.f22080q = GLES20.glGetUniformLocation(m4403f(), "contrastTexture");
        GLES20.glGenTextures(1, this.f22079p, 0);
        GLES20.glBindTexture(3553, this.f22079p[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, Task.EXTRAS_LIMIT_BYTES, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m25168z(this.f22078o);
    }

    /* renamed from: z */
    public void m25168z(float f9) {
        this.f22078o = f9;
        m4408q(this.f22077n, f9);
        m4406o(new a(f9));
    }

    public C6567a(float f9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D contrastTexture;\nuniform mediump float contrast;\n\nmediump vec3 RGB2HSV(mediump vec3 c)\n{\n    mediump vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n    mediump vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);\n    mediump vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);\n\n    mediump float d = q.x - min(q.w, q.y);\n    mediump float e = 1.0e-10;    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);\n}\n\nmediump vec3 HSV2RGB(mediump vec3 c)\n{\n    mediump vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n    mediump vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);\n    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);\n}\n\nvoid main()\n{\n\tmediump vec4 RGBA = texture2D(inputImageTexture, textureCoordinate);\n\tmediump vec3 oldHSV = RGB2HSV(RGBA.rgb);\n\tmediump float R = texture2D(contrastTexture, vec2(RGBA.r, 0.0)).r;\n\tmediump float G = texture2D(contrastTexture, vec2(RGBA.g, 0.0)).g;\n\tmediump float B = texture2D(contrastTexture, vec2(RGBA.b, 0.0)).b;\n\tmediump vec3 newHSV = RGB2HSV(vec3(R, G, B));\n\n\tif (contrast < 0.0)\n\t{\n\t\tgl_FragColor = vec4(HSV2RGB(vec3(oldHSV.x, newHSV.y, newHSV.z)), RGBA.a);\t}\n\telse\n\t{\n\t\tgl_FragColor = vec4(R, G, B, RGBA.a);\n\t}\n}");
        this.f22079p = new int[]{-1};
        this.f22078o = f9;
    }
}
