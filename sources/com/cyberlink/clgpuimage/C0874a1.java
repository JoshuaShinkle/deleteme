package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.cyberlink.clgpuimage.a1 */
/* loaded from: classes.dex */
public class C0874a1 extends C0936w0 {

    /* renamed from: n */
    public int[] f4377n;

    /* renamed from: o */
    public int f4378o;

    /* renamed from: p */
    public final int f4379p;

    /* renamed from: q */
    public final int f4380q;

    /* renamed from: r */
    public final int f4381r;

    /* renamed from: s */
    public final int[] f4382s;

    /* renamed from: t */
    public byte[] f4383t;

    /* renamed from: u */
    public int[] f4384u;

    /* renamed from: v */
    public float[] f4385v;

    /* renamed from: w */
    public float[] f4386w;

    /* renamed from: x */
    public AtomicBoolean f4387x;

    public C0874a1() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputMappingTexture;\n\nmediump vec3 RGB2HSV(mediump vec3 c)\n{\n    mediump vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n    mediump vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);\n    mediump vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);\n\n    mediump float d = q.x - min(q.w, q.y);\n    mediump float e = 1.0e-10;    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);\n}\n\nmediump vec3 HSV2RGB(mediump vec3 c)\n{\n    mediump vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n    mediump vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);\n    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);\n}\n\nmediump float hueWeight(mediump float H)\n{\n    if      (H < (30.0/360.0))\n        return mix(0.0,   0.125, (H - (0.0/360.0)  ) / (30.0/360.0));\n    else if (H < (60.0/360.0))\n        return mix(0.125, 0.25,  (H - (30.0/360.0) ) / (30.0/360.0));\n    else if (H < (120.0/360.0))\n        return mix(0.25,  0.375, (H - (60.0/360.0) ) / (60.0/360.0));\n    else if (H < (180.0/360.0))\n        return mix(0.375, 0.5,   (H - (120.0/360.0) ) / (60.0/360.0));\n    else if (H < (240.0/360.0))\n        return mix(0.5,   0.625, (H - (180.0/360.0)) / (60.0/360.0));\n    else if (H < (285.0/360.0))\n        return mix(0.625, 0.75,  (H - (240.0/360.0)) / (45.0/360.0));\n    else if (H < (300.0/360.0))\n        return mix(0.75,  0.875, (H - (285.0/360.0)) / (15.0/360.0));\n    else\n        return mix(0.875, 1.0,   (H - (300.0/360.0)) / (60.0/360.0));\n}\n\nvoid main()\n{\n    mediump vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n\n    mediump vec3 HSV = RGB2HSV(textureColor.rgb);\n\n    mediump vec4 HMapping, SMapping, VMapping;\n\n    HMapping = texture2D(inputMappingTexture, vec2(HSV.x, 0.0));\n\n    mediump float weight = hueWeight(HSV.x);\n\n    SMapping = texture2D(inputMappingTexture, vec2(HSV.y, weight));\n\n    VMapping = texture2D(inputMappingTexture, vec2(HSV.z, weight));\n\n    if (HSV.y > 0.0)\n    {\n        mediump float H, S, V;\n\n        H = mod((HSV.x + HMapping.z) - 0.5, 1.0);\n\n        S = SMapping.y;\n\n        V = VMapping.x;\n        mediump float DeltaV = V - HSV.z;\n        if (HSV.y < 0.025)\n            V = clamp(HSV.z + (DeltaV * HSV.y), 0.0, 1.0);\n        else\n        {\n           if (DeltaV < 0.0)\n               V = clamp(HSV.z - (pow(abs(DeltaV), 1.0/HSV.y)), 0.0, 1.0);\n           else\n               V = clamp(HSV.z + (pow(abs(DeltaV), 1.0/HSV.y)), 0.0, 1.0);\n        }\n\n        gl_FragColor = vec4(HSV2RGB(vec3(H, S, V)), textureColor.a);\n    }\n    else\n        gl_FragColor = vec4(textureColor.rgb, textureColor.a);\n}");
        this.f4377n = new int[]{-1};
        this.f4379p = 8;
        this.f4380q = 256;
        this.f4381r = 4;
        this.f4382s = new int[]{0, 30, 60, 120, 180, PsExtractor.VIDEO_STREAM_MASK, 285, 300, 360};
        this.f4383t = new byte[9216];
        this.f4384u = new int[8];
        this.f4385v = new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
        this.f4386w = new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
        this.f4387x = new AtomicBoolean(true);
    }

    /* renamed from: A */
    public final int m4206A(int i9, int i10) {
        int iPow;
        synchronized (this.f4383t) {
            iPow = (int) (Math.pow(i10 / 255.0f, (float) Math.exp((-this.f4386w[i9 % 8]) * 2.0f)) * 255.0d);
        }
        return iPow;
    }

    /* renamed from: B */
    public final int m4207B(int i9, int i10) {
        int iMin;
        synchronized (this.f4383t) {
            iMin = (int) (Math.min(1.0d, (i10 / 255.0f) * (this.f4385v[i9 % 8] + 1.0f)) * 255.0d);
        }
        return iMin;
    }

    /* renamed from: C */
    public void m4208C(int i9, float f9) {
        synchronized (this.f4383t) {
            try {
                if (f9 == BitmapDescriptorFactory.HUE_RED) {
                    this.f4384u[i9] = 0;
                } else {
                    double d9 = f9;
                    if (d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        int[] iArr = this.f4382s;
                        this.f4384u[i9] = (int) (f9 * Math.abs(((iArr[i9] - iArr[((i9 - 1) + 8) % 8]) + 360) % 360));
                    } else if (d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        int i10 = this.f4382s[i9];
                        this.f4384u[i9] = (int) (f9 * Math.abs(((r1[(i9 + 1) % 8] - i10) + 360) % 360));
                    }
                }
                this.f4387x.set(true);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: D */
    public void m4209D(int i9, float f9) {
        synchronized (this.f4383t) {
            this.f4386w[i9] = f9;
            this.f4387x.set(true);
        }
    }

    /* renamed from: E */
    public void m4210E(int i9, float f9) {
        synchronized (this.f4383t) {
            this.f4385v[i9] = f9;
            this.f4387x.set(true);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        super.mo4045i();
        GLES20.glDeleteTextures(1, this.f4377n, 0);
        this.f4377n[0] = -1;
        this.f4387x.set(true);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        if (this.f4377n[0] == -1) {
            GLES20.glActiveTexture(33985);
            GLES20.glGenTextures(1, this.f4377n, 0);
            GLES20.glBindTexture(3553, this.f4377n[0]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, Task.EXTRAS_LIMIT_BYTES, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
        }
        if (!this.f4387x.compareAndSet(true, false)) {
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(3553, this.f4377n[0]);
            GLES20.glUniform1i(this.f4378o, 1);
            return;
        }
        synchronized (this.f4383t) {
            for (int i9 = 0; i9 < 9; i9++) {
                for (int i10 = 0; i10 < 256; i10++) {
                    int i11 = ((i9 * 256) + i10) * 4;
                    this.f4383t[i11 + 2] = (byte) m4211z(i10);
                    this.f4383t[i11 + 1] = (byte) m4207B(i9, i10);
                    this.f4383t[i11] = (byte) m4206A(i9, i10);
                    this.f4383t[i11 + 3] = (byte) i10;
                }
                try {
                } finally {
                }
            }
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(3553, this.f4377n[0]);
            GLES20.glTexImage2D(3553, 0, 6408, 256, 9, 0, 6408, 5121, ByteBuffer.wrap(this.f4383t));
            GLES20.glUniform1i(this.f4378o, 1);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4378o = GLES20.glGetUniformLocation(m4403f(), "inputMappingTexture");
    }

    /* renamed from: z */
    public final int m4211z(int i9) {
        synchronized (this.f4383t) {
            int i10 = (int) ((i9 / 255.0f) * 360.0f);
            for (int i11 = 7; i11 >= 0; i11--) {
                int[] iArr = this.f4382s;
                int i12 = iArr[i11];
                if (i10 >= i12) {
                    int[] iArr2 = this.f4384u;
                    int i13 = iArr2[i11];
                    int i14 = i11 + 1;
                    return (((i13 + (((iArr2[i14 % 8] - i13) * (i10 - i12)) / (iArr[i14] - i12))) + 180) * 255) / 360;
                }
            }
            return 127;
        }
    }
}
