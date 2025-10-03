package com.cyberlink.clgpuimage;

import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.util.Log;
import com.cyberlink.clgpuimage.IBeautyFilter2;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.base.Ascii;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.commons.p159io.IOUtils;
import p253z1.C6816a;

/* loaded from: classes.dex */
public class CLBeautyFilterBasicWithTwoPassBlendSource2 extends C0936w0 {

    /* renamed from: W */
    public static String f3960W = "color_effect_table/";

    /* renamed from: A */
    public int f3961A;

    /* renamed from: B */
    public int f3962B;

    /* renamed from: C */
    public int f3963C;

    /* renamed from: D */
    public int f3964D;

    /* renamed from: E */
    public int f3965E;

    /* renamed from: F */
    public int f3966F;

    /* renamed from: G */
    public int f3967G;

    /* renamed from: H */
    public int f3968H;

    /* renamed from: I */
    public int f3969I;

    /* renamed from: J */
    public int f3970J;

    /* renamed from: K */
    public float f3971K;

    /* renamed from: L */
    public TableMode f3972L;

    /* renamed from: M */
    public boolean f3973M;

    /* renamed from: N */
    public byte[] f3974N;

    /* renamed from: O */
    public final int f3975O;

    /* renamed from: P */
    public final int f3976P;

    /* renamed from: Q */
    public final int f3977Q;

    /* renamed from: R */
    public final int f3978R;

    /* renamed from: S */
    public final int f3979S;

    /* renamed from: T */
    public int[] f3980T;

    /* renamed from: U */
    public int f3981U;

    /* renamed from: V */
    public ByteBuffer f3982V;

    /* renamed from: n */
    public final FloatBuffer f3983n;

    /* renamed from: o */
    public final FloatBuffer f3984o;

    /* renamed from: p */
    public IBeautyFilter2.FilterType f3985p;

    /* renamed from: q */
    public String f3986q;

    /* renamed from: r */
    public String f3987r;

    /* renamed from: s */
    public String f3988s;

    /* renamed from: t */
    public String f3989t;

    /* renamed from: u */
    public int f3990u;

    /* renamed from: v */
    public int f3991v;

    /* renamed from: w */
    public int f3992w;

    /* renamed from: x */
    public int[] f3993x;

    /* renamed from: y */
    public int[] f3994y;

    /* renamed from: z */
    public int f3995z;

    public enum PostProcessMode {
        POST_NONE,
        POST_NORMAL,
        POST_VIGNETTE,
        POST_SOURCE,
        POST_FADE;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static PostProcessMode[] valuesCustom() {
            PostProcessMode[] postProcessModeArrValuesCustom = values();
            int length = postProcessModeArrValuesCustom.length;
            PostProcessMode[] postProcessModeArr = new PostProcessMode[length];
            System.arraycopy(postProcessModeArrValuesCustom, 0, postProcessModeArr, 0, length);
            return postProcessModeArr;
        }
    }

    public enum PreProcessMode {
        PRE_NONE,
        PRE_WHITEN;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static PreProcessMode[] valuesCustom() {
            PreProcessMode[] preProcessModeArrValuesCustom = values();
            int length = preProcessModeArrValuesCustom.length;
            PreProcessMode[] preProcessModeArr = new PreProcessMode[length];
            System.arraycopy(preProcessModeArrValuesCustom, 0, preProcessModeArr, 0, length);
            return preProcessModeArr;
        }
    }

    public enum TableMode {
        TABLE_NONE,
        TABLE_3D,
        TABLE_1D;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static TableMode[] valuesCustom() {
            TableMode[] tableModeArrValuesCustom = values();
            int length = tableModeArrValuesCustom.length;
            TableMode[] tableModeArr = new TableMode[length];
            System.arraycopy(tableModeArrValuesCustom, 0, tableModeArr, 0, length);
            return tableModeArr;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLBeautyFilterBasicWithTwoPassBlendSource2$a */
    public class RunnableC0853a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ float f4010c;

        /* renamed from: d */
        public final /* synthetic */ float f4011d;

        public RunnableC0853a(float f9, float f10) {
            this.f4010c = f9;
            this.f4011d = f10;
        }

        @Override // java.lang.Runnable
        public void run() {
            CLBeautyFilterBasicWithTwoPassBlendSource2 cLBeautyFilterBasicWithTwoPassBlendSource2 = CLBeautyFilterBasicWithTwoPassBlendSource2.this;
            cLBeautyFilterBasicWithTwoPassBlendSource2.m4409r(cLBeautyFilterBasicWithTwoPassBlendSource2.f3966F, new float[]{BitmapDescriptorFactory.HUE_RED, this.f4010c / CLBeautyFilterBasicWithTwoPassBlendSource2.this.f3991v});
            CLBeautyFilterBasicWithTwoPassBlendSource2 cLBeautyFilterBasicWithTwoPassBlendSource22 = CLBeautyFilterBasicWithTwoPassBlendSource2.this;
            cLBeautyFilterBasicWithTwoPassBlendSource22.m4409r(cLBeautyFilterBasicWithTwoPassBlendSource22.f3967G, new float[]{BitmapDescriptorFactory.HUE_RED, this.f4011d / CLBeautyFilterBasicWithTwoPassBlendSource2.this.f3991v});
        }
    }

    public CLBeautyFilterBasicWithTwoPassBlendSource2(IBeautyFilter2.FilterType filterType) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}", "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}", filterType);
    }

    /* renamed from: B */
    public final void m4034B(int i9, int i10) {
        int[] iArr = new int[1];
        this.f3993x = iArr;
        this.f3994y = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f3994y, 0);
        GLES20.glBindTexture(3553, this.f3994y[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f3993x[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f3994y[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: C */
    public String m4035C(char[] cArr) {
        char[] cArr2 = (char[]) cArr.clone();
        for (int i9 = 0; i9 < cArr2.length; i9++) {
            char c9 = (char) (cArr2[i9] ^ 200);
            cArr2[i9] = c9;
            if (c9 >= '!' && c9 <= '~') {
                char c10 = (char) (c9 + IOUtils.DIR_SEPARATOR_UNIX);
                cArr2[i9] = c10;
                if (c10 > '~') {
                    cArr2[i9] = (char) (c10 - '^');
                }
            }
        }
        return new String(cArr2);
    }

    /* renamed from: D */
    public final void m4036D(byte[] bArr) {
        for (int i9 = 0; i9 < bArr.length; i9++) {
            bArr[i9] = m4041I(bArr[i9]);
        }
    }

    /* renamed from: E */
    public final void m4037E() {
        int[] iArr = this.f3994y;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f3994y = null;
        }
        int[] iArr2 = this.f3993x;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f3993x = null;
        }
    }

    /* renamed from: F */
    public void m4038F() {
    }

    /* renamed from: G */
    public void m4039G() {
    }

    /* renamed from: H */
    public void m4040H(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        IntBuffer intBufferAllocate = IntBuffer.allocate(UserMetadata.MAX_ATTRIBUTE_SIZE);
        GLES20.glGetIntegerv(36006, intBufferAllocate);
        IntBuffer intBufferAllocate2 = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate2);
        GLES20.glUseProgram(this.f3995z);
        GLES20.glViewport(0, 0, this.f4797h, this.f4798i);
        GLES20.glBindFramebuffer(36160, this.f3993x[0]);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f3961A, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f3961A);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f3963C, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f3963C);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f3962B, 0);
        }
        float fMin = Math.min(this.f3990u, this.f3991v) / 720.0f;
        float fMax = (float) (this.f3985p == IBeautyFilter2.FilterType.LIVE_SMOOTH ? Math.max(1.0d, Math.floor(fMin * this.f3971K)) : Math.max(1.0d, fMin * this.f3971K));
        GLES20.glUniform2f(this.f3964D, (fMax - 0.5f) / this.f3990u, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUniform2f(this.f3965E, fMax / this.f3990u, BitmapDescriptorFactory.HUE_RED);
        m4038F();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f3961A);
        GLES20.glDisableVertexAttribArray(this.f3963C);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, intBufferAllocate.get(0));
        GLES20.glViewport(intBufferAllocate2.get(0), intBufferAllocate2.get(1), intBufferAllocate2.get(2), intBufferAllocate2.get(3));
    }

    /* renamed from: I */
    public final byte m4041I(byte b9) {
        byte b10 = (byte) (((b9 & Ascii.f15389SI) << 4) | ((b9 & 240) >> 4));
        byte b11 = (byte) (((b10 & 51) << 2) | ((b10 & 204) >> 2));
        return (byte) (((b11 & 85) << 1) | ((b11 & 170) >> 1));
    }

    /* renamed from: J */
    public void mo4042J(int i9, int i10) {
        if (i9 <= 0 || i10 <= 0) {
            return;
        }
        this.f3990u = i9;
        this.f3991v = i10;
        float fMin = Math.min(i9, i10) / 720.0f;
        float fMax = (float) (this.f3985p == IBeautyFilter2.FilterType.LIVE_SMOOTH ? Math.max(1.0d, Math.floor(fMin * this.f3971K)) : Math.max(1.0d, fMin * this.f3971K));
        m4406o(new RunnableC0853a(fMax - 0.5f, fMax));
    }

    /* renamed from: K */
    public void m4043K(String str, String str2, String str3, String str4) {
        this.f3986q = str;
        this.f3987r = str2;
        this.f3988s = str3;
        this.f3989t = str4;
        super.m4414w(str3, str4);
    }

    /* renamed from: L */
    public void m4044L(AssetManager assetManager, String str, byte[] bArr, TableMode tableMode, boolean z8) throws IOException {
        int i9;
        this.f3972L = tableMode;
        this.f3973M = z8;
        this.f3974N = bArr;
        if (tableMode != TableMode.TABLE_NONE) {
            int i10 = 256;
            if (tableMode == TableMode.TABLE_3D) {
                i9 = 128;
            } else if (tableMode == TableMode.TABLE_1D) {
                i9 = 1;
            } else {
                Log.v("Aphrodite Log", "Invalid table_lookup_mode");
                i9 = 0;
                i10 = 0;
            }
            int i11 = i10 * i9 * 3;
            byte[] bArr2 = new byte[i11];
            if (bArr == null || tableMode != TableMode.TABLE_1D) {
                try {
                    assetManager.open(str).read(bArr2, 0, i11);
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, Math.min(i11, bArr.length));
            }
            if (this.f3973M) {
                m4036D(bArr2);
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i11);
            this.f3982V = byteBufferAllocate;
            byteBufferAllocate.put(bArr2, 0, i11);
            this.f3982V.position(0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4037E();
        GLES20.glDeleteProgram(this.f3995z);
        if (this.f3972L != TableMode.TABLE_NONE) {
            int[] iArr = this.f3980T;
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (!m4405h() || this.f3993x == null || this.f3994y == null) {
            return;
        }
        m4040H(i9, this.f3983n, this.f3984o);
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (m4405h()) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f4794e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f4796g);
            if (this.f3994y[0] != -1) {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.f3994y[0]);
                GLES20.glUniform1i(this.f4795f, 3);
            }
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(this.f3992w, 0);
            }
            if (this.f3972L != TableMode.TABLE_NONE && this.f3980T[0] != -1) {
                GLES20.glActiveTexture(33988);
                GLES20.glBindTexture(3553, this.f3980T[0]);
                GLES20.glUniform1i(this.f3981U, 4);
            }
            m4039G();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f4794e);
            GLES20.glDisableVertexAttribArray(this.f4796g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        int i9;
        int i10;
        int iM4377c = C0925r1.m4377c(this.f3986q, this.f3987r);
        this.f3995z = iM4377c;
        this.f3961A = GLES20.glGetAttribLocation(iM4377c, "position");
        this.f3962B = GLES20.glGetUniformLocation(this.f3995z, "inputImageTexture");
        this.f3963C = GLES20.glGetAttribLocation(this.f3995z, "inputTextureCoordinate");
        this.f3964D = GLES20.glGetUniformLocation(this.f3995z, "sampling_offset_start");
        this.f3965E = GLES20.glGetUniformLocation(this.f3995z, "sampling_step");
        super.mo4047l();
        this.f3966F = GLES20.glGetUniformLocation(m4403f(), "sampling_offset_start");
        this.f3967G = GLES20.glGetUniformLocation(m4403f(), "sampling_step");
        this.f3968H = GLES20.glGetUniformLocation(m4403f(), "smooth_strength");
        this.f3969I = GLES20.glGetUniformLocation(m4403f(), "color_strength");
        this.f3970J = GLES20.glGetUniformLocation(m4403f(), "fade_strength");
        this.f3992w = GLES20.glGetUniformLocation(m4403f(), "rootImageTexture");
        TableMode tableMode = this.f3972L;
        if (tableMode != TableMode.TABLE_NONE) {
            if (tableMode == TableMode.TABLE_3D) {
                i10 = 128;
                i9 = 256;
            } else if (tableMode == TableMode.TABLE_1D) {
                i9 = 256;
                i10 = 1;
            } else {
                Log.v("Aphrodite Log", "Invalid table_lookup_mode");
                i9 = 0;
                i10 = 0;
            }
            int[] iArr = new int[1];
            this.f3980T = iArr;
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, this.f3980T[0]);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6407, i9, i10, 0, 6407, 5121, this.f3982V);
            this.f3981U = GLES20.glGetUniformLocation(m4403f(), "mapping_table_texture");
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        mo4042J(this.f3990u, this.f3991v);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f3993x != null) {
            m4037E();
        }
        m4034B(i9, i10);
        mo4042J(i9, i10);
    }

    public CLBeautyFilterBasicWithTwoPassBlendSource2(String str, String str2, String str3, String str4, IBeautyFilter2.FilterType filterType) {
        super(str3, str4);
        this.f3990u = 960;
        this.f3991v = 720;
        this.f3975O = 256;
        this.f3976P = 128;
        this.f3977Q = 256;
        this.f3978R = 1;
        this.f3979S = 3;
        this.f3986q = str;
        this.f3987r = str2;
        this.f3988s = str3;
        this.f3989t = str4;
        this.f3985p = filterType;
        if (filterType == IBeautyFilter2.FilterType.LIVE_SMOOTH) {
            this.f3971K = 6.0f;
        } else {
            this.f3971K = 2.0f;
        }
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f3983n = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f3984o = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f3972L = TableMode.TABLE_NONE;
        this.f3973M = false;
        this.f3974N = null;
    }
}
