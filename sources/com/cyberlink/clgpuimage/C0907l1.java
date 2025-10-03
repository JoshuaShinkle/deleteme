package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.ByteBuffer;

/* renamed from: com.cyberlink.clgpuimage.l1 */
/* loaded from: classes.dex */
public class C0907l1 extends C0936w0 {

    /* renamed from: n */
    public int[] f4692n;

    /* renamed from: o */
    public int f4693o;

    /* renamed from: p */
    public float[] f4694p;

    /* renamed from: q */
    public float[] f4695q;

    /* renamed from: r */
    public float[] f4696r;

    /* renamed from: s */
    public float[] f4697s;

    /* renamed from: com.cyberlink.clgpuimage.l1$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, C0907l1.this.f4692n[0]);
            if (C0907l1.this.f4694p.length >= 256) {
                byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                for (int i9 = 0; i9 < 256; i9++) {
                    int i10 = i9 * 4;
                    C0907l1 c0907l1 = C0907l1.this;
                    bArr[i10 + 2] = (byte) (c0907l1.f4697s[i9] * 255.0f);
                    bArr[i10 + 1] = (byte) (c0907l1.f4696r[i9] * 255.0f);
                    bArr[i10] = (byte) (c0907l1.f4695q[i9] * 255.0f);
                    bArr[i10 + 3] = (byte) (c0907l1.f4694p[i9] * 255.0f);
                }
                GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
            }
        }
    }

    public C0907l1() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D toneCurveTexture;\n\nvoid main()\n{\n\tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n\n\tlowp float redCurveValue, greenCurveValue, blueCurveValue;\n\n\tredCurveValue = texture2D(toneCurveTexture, vec2(textureColor.r, 0.0)).a;\n\tredCurveValue = texture2D(toneCurveTexture, vec2(redCurveValue, 0.0)).r;\n\n\tgreenCurveValue = texture2D(toneCurveTexture, vec2(textureColor.g, 0.0)).a;\n\tgreenCurveValue = texture2D(toneCurveTexture, vec2(greenCurveValue, 0.0)).g;\n\n\tblueCurveValue = texture2D(toneCurveTexture, vec2(textureColor.b, 0.0)).a;\n\tblueCurveValue = texture2D(toneCurveTexture, vec2(blueCurveValue, 0.0)).b;\n\n\tgl_FragColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, textureColor.a);\n }");
        int i9 = 0;
        this.f4692n = new int[]{-1};
        this.f4695q = null;
        this.f4696r = null;
        this.f4697s = null;
        this.f4694p = new float[256];
        int i10 = 0;
        while (true) {
            float[] fArr = this.f4694p;
            if (i10 >= fArr.length) {
                break;
            }
            fArr[i10] = i10 / 255.0f;
            i10++;
        }
        this.f4695q = new float[256];
        int i11 = 0;
        while (true) {
            float[] fArr2 = this.f4695q;
            if (i11 >= fArr2.length) {
                break;
            }
            fArr2[i11] = i11 / 255.0f;
            i11++;
        }
        this.f4696r = new float[256];
        int i12 = 0;
        while (true) {
            float[] fArr3 = this.f4696r;
            if (i12 >= fArr3.length) {
                break;
            }
            fArr3[i12] = i12 / 255.0f;
            i12++;
        }
        this.f4697s = new float[256];
        while (true) {
            float[] fArr4 = this.f4697s;
            if (i9 >= fArr4.length) {
                return;
            }
            fArr4[i9] = i9 / 255.0f;
            i9++;
        }
    }

    /* renamed from: A */
    public final void m4356A() {
        m4406o(new a());
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        if (this.f4692n[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f4692n[0]);
            GLES20.glUniform1i(this.f4693o, 3);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4693o = GLES20.glGetUniformLocation(m4403f(), "toneCurveTexture");
        GLES20.glActiveTexture(33987);
        GLES20.glGenTextures(1, this.f4692n, 0);
        GLES20.glBindTexture(3553, this.f4692n[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, Task.EXTRAS_LIMIT_BYTES, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m4356A();
    }

    /* renamed from: z */
    public boolean m4357z(float[] fArr) {
        if (fArr.length < 256) {
            return false;
        }
        this.f4694p = fArr;
        m4356A();
        return true;
    }
}
