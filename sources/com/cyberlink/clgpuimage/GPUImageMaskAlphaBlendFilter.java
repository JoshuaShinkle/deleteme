package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import p253z1.C6816a;

/* loaded from: classes.dex */
public class GPUImageMaskAlphaBlendFilter extends C0883d1 {

    /* renamed from: P */
    public static /* synthetic */ int[] f4235P;

    /* renamed from: Q */
    public static /* synthetic */ int[] f4236Q;

    /* renamed from: A */
    public int f4237A;

    /* renamed from: B */
    public int f4238B;

    /* renamed from: C */
    public final FloatBuffer f4239C;

    /* renamed from: D */
    public final FloatBuffer f4240D;

    /* renamed from: E */
    public float f4241E;

    /* renamed from: F */
    public float f4242F;

    /* renamed from: G */
    public float f4243G;

    /* renamed from: H */
    public float f4244H;

    /* renamed from: I */
    public MaskOperation f4245I;

    /* renamed from: J */
    public MaskType f4246J;

    /* renamed from: K */
    public boolean f4247K;

    /* renamed from: L */
    public boolean f4248L;

    /* renamed from: M */
    public boolean f4249M;

    /* renamed from: N */
    public int f4250N;

    /* renamed from: O */
    public int f4251O;

    /* renamed from: u */
    public int[] f4252u;

    /* renamed from: v */
    public int[] f4253v;

    /* renamed from: w */
    public int f4254w;

    /* renamed from: x */
    public int f4255x;

    /* renamed from: y */
    public int f4256y;

    /* renamed from: z */
    public int f4257z;

    public enum MaskOperation {
        MaskPoint,
        Invert,
        Undo,
        Apply,
        HideLast;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static MaskOperation[] valuesCustom() {
            MaskOperation[] maskOperationArrValuesCustom = values();
            int length = maskOperationArrValuesCustom.length;
            MaskOperation[] maskOperationArr = new MaskOperation[length];
            System.arraycopy(maskOperationArrValuesCustom, 0, maskOperationArr, 0, length);
            return maskOperationArr;
        }
    }

    public enum MaskType {
        PointAdd,
        PointErase,
        PointColorAdd,
        PointColorErase;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static MaskType[] valuesCustom() {
            MaskType[] maskTypeArrValuesCustom = values();
            int length = maskTypeArrValuesCustom.length;
            MaskType[] maskTypeArr = new MaskType[length];
            System.arraycopy(maskTypeArrValuesCustom, 0, maskTypeArr, 0, length);
            return maskTypeArr;
        }
    }

    public GPUImageMaskAlphaBlendFilter(float f9) {
        this(f9, false);
    }

    /* renamed from: C */
    public static /* synthetic */ int[] m4170C() {
        int[] iArr = f4236Q;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[MaskOperation.valuesCustom().length];
        try {
            iArr2[MaskOperation.Apply.ordinal()] = 4;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[MaskOperation.HideLast.ordinal()] = 5;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[MaskOperation.Invert.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[MaskOperation.MaskPoint.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[MaskOperation.Undo.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        f4236Q = iArr2;
        return iArr2;
    }

    /* renamed from: D */
    public static /* synthetic */ int[] m4171D() {
        int[] iArr = f4235P;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[MaskType.valuesCustom().length];
        try {
            iArr2[MaskType.PointAdd.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[MaskType.PointColorAdd.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[MaskType.PointColorErase.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[MaskType.PointErase.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        f4235P = iArr2;
        return iArr2;
    }

    /* renamed from: E */
    public void m4172E(boolean z8, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        if (this.f4238B == 0) {
            this.f4238B = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        }
        GLES20.glUseProgram(this.f4238B);
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f4238B, "position");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(this.f4238B, "inputTextureCoordinate");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(this.f4238B, "inputImageTexture");
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glViewport(0, 0, m4176I(), m4175H());
        if (z8) {
            GLES20.glBindFramebuffer(36160, this.f4252u[1]);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f4253v[0]);
            GLES20.glUniform1i(iGlGetUniformLocation, 0);
        } else {
            GLES20.glBindFramebuffer(36160, this.f4252u[0]);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f4253v[1]);
            GLES20.glUniform1i(iGlGetUniformLocation, 0);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glUseProgram(m4403f());
    }

    /* renamed from: F */
    public final void m4173F(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int i10;
        float f9 = this.f4243G;
        if (f9 < BitmapDescriptorFactory.HUE_RED || f9 > 1.0f) {
            return;
        }
        float f10 = this.f4244H;
        if (f10 < BitmapDescriptorFactory.HUE_RED || f10 > 1.0f) {
            return;
        }
        int i11 = m4171D()[this.f4246J.ordinal()];
        if (i11 == 1) {
            if (this.f4255x == 0) {
                this.f4255x = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nvarying vec2 textureCoordinate;\nvarying vec2 referenceCoordinate;\nuniform highp vec2 uv_reference;\n\nvoid main()\n{\n\tgl_Position = position;\n\ttextureCoordinate = inputTextureCoordinate.xy;\n\treferenceCoordinate = uv_reference;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 referenceCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float radius;\nuniform highp float threshold;const highp vec3 luminanceWeighting = vec3(0.2126, 0.7152, 0.0722);\n \nvoid main()\n{\n\t  highp vec4 color = texture2D(inputImageTexture, textureCoordinate);\n\t  highp vec4 crReference = texture2D(inputImageTexture, referenceCoordinate);\n\t  highp float distance = length(referenceCoordinate-textureCoordinate);\n\t  if (distance >= radius)\n\t\t\tdiscard;\n\t  highp float varianceThreshold = mix(threshold, 0.0, distance);\n\t  highp float variance = abs(dot(color.rgb, luminanceWeighting) - dot(crReference.rgb, luminanceWeighting));\n\t  if (variance >= varianceThreshold)\n\t  \t\tdiscard;\n\t  gl_FragColor = vec4(distance/radius);\n}");
            }
            i10 = this.f4255x;
        } else {
            if (i11 != 2) {
                return;
            }
            if (this.f4256y == 0) {
                this.f4256y = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nvarying vec2 textureCoordinate;\nvarying vec2 referenceCoordinate;\nuniform highp vec2 uv_reference;\n\nvoid main()\n{\n\tgl_Position = position;\n\ttextureCoordinate = inputTextureCoordinate.xy;\n\treferenceCoordinate = uv_reference;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 referenceCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float radius;\nuniform highp float threshold;const highp vec3 luminanceWeighting = vec3(0.2126, 0.7152, 0.0722);\n \nvoid main()\n{\n\t  highp vec4 color = texture2D(inputImageTexture, textureCoordinate);\n\t  highp vec4 crReference = texture2D(inputImageTexture, referenceCoordinate);\n\t  highp float distance = length(referenceCoordinate-textureCoordinate);\n\t  if (distance >= radius)\n\t\t\tdiscard;\n\t  highp float varianceThreshold = mix(threshold, 0.0, distance);\n\t  highp float variance = abs(dot(color.rgb, luminanceWeighting) - dot(crReference.rgb, luminanceWeighting));\n\t  if (variance >= varianceThreshold)\n\t  \t\tdiscard;\n\t  gl_FragColor = vec4(1.0 - distance/radius);\n}");
            }
            i10 = this.f4256y;
        }
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(i10, "position");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(i10, "inputTextureCoordinate");
        int iGlGetUniformLocation = GLES20.glGetUniformLocation(i10, "inputImageTexture");
        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(i10, "uv_reference");
        int iGlGetUniformLocation3 = GLES20.glGetUniformLocation(i10, "radius");
        int iGlGetUniformLocation4 = GLES20.glGetUniformLocation(i10, "threshold");
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glBindFramebuffer(36160, this.f4252u[0]);
        GLES20.glViewport(0, 0, m4176I(), m4175H());
        GLES20.glUseProgram(i10);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(iGlGetUniformLocation, 0);
        }
        GLES20.glUniform2f(iGlGetUniformLocation2, this.f4243G, this.f4244H);
        GLES20.glUniform1f(iGlGetUniformLocation3, this.f4241E);
        GLES20.glUniform1f(iGlGetUniformLocation4, this.f4247K ? 0.1f : 1.0f);
        boolean zGlIsEnabled = GLES20.glIsEnabled(3042);
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        GLES20.glGetIntegerv(32971, iArr2, 0);
        GLES20.glGetIntegerv(32970, iArr3, 0);
        GLES20.glEnable(3042);
        int i12 = m4171D()[this.f4246J.ordinal()];
        if (i12 == 1) {
            GLES20.glBlendFunc(774, 0);
        } else if (i12 != 2 && i12 != 4) {
            return;
        } else {
            GLES20.glBlendFunc(1, 769);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBlendFunc(iArr2[0], iArr3[0]);
        if (!zGlIsEnabled) {
            GLES20.glDisable(3042);
        }
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glUseProgram(m4403f());
    }

    /* renamed from: G */
    public final void m4174G(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int i10;
        int iGlGetUniformLocation;
        float f9 = this.f4243G;
        if (f9 < BitmapDescriptorFactory.HUE_RED || f9 > 1.0f) {
            return;
        }
        float f10 = this.f4244H;
        if (f10 < BitmapDescriptorFactory.HUE_RED || f10 > 1.0f) {
            return;
        }
        int i11 = m4171D()[this.f4246J.ordinal()];
        if (i11 == 1) {
            if (this.f4255x == 0) {
                this.f4255x = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nvarying vec2 textureCoordinate;\nvarying vec2 referenceCoordinate;\nuniform highp vec2 uv_reference;\n\nvoid main()\n{\n\tgl_Position = position;\n\ttextureCoordinate = inputTextureCoordinate.xy;\n\treferenceCoordinate = uv_reference;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 referenceCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float radius;\nuniform highp float threshold;const highp vec3 luminanceWeighting = vec3(0.2126, 0.7152, 0.0722);\n \nvoid main()\n{\n\t  highp vec4 color = texture2D(inputImageTexture, textureCoordinate);\n\t  highp vec4 crReference = texture2D(inputImageTexture, referenceCoordinate);\n\t  highp float distance = length(referenceCoordinate-textureCoordinate);\n\t  if (distance >= radius)\n\t\t\tdiscard;\n\t  highp float varianceThreshold = mix(threshold, 0.0, distance);\n\t  highp float variance = abs(dot(color.rgb, luminanceWeighting) - dot(crReference.rgb, luminanceWeighting));\n\t  if (variance >= varianceThreshold)\n\t  \t\tdiscard;\n\t  gl_FragColor = vec4(0.0);\n}");
            }
            i10 = this.f4255x;
        } else if (i11 == 2) {
            if (this.f4256y == 0) {
                this.f4256y = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nvarying vec2 textureCoordinate;\nvarying vec2 referenceCoordinate;\nuniform highp vec2 uv_reference;\n\nvoid main()\n{\n\tgl_Position = position;\n\ttextureCoordinate = inputTextureCoordinate.xy;\n\treferenceCoordinate = uv_reference;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 referenceCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float radius;\nuniform highp float threshold;const highp vec3 luminanceWeighting = vec3(0.2126, 0.7152, 0.0722);\n \nvoid main()\n{\n\t  highp vec4 color = texture2D(inputImageTexture, textureCoordinate);\n\t  highp vec4 crReference = texture2D(inputImageTexture, referenceCoordinate);\n\t  highp float distance = length(referenceCoordinate-textureCoordinate);\n\t  if (distance >= radius)\n\t\t\tdiscard;\n\t  highp float varianceThreshold = mix(threshold, 0.0, distance);\n\t  highp float variance = abs(dot(color.rgb, luminanceWeighting) - dot(crReference.rgb, luminanceWeighting));\n\t  if (variance >= varianceThreshold)\n\t  \t\tdiscard;\n\t  gl_FragColor = vec4(1.0);\n}");
            }
            i10 = this.f4256y;
        } else {
            if (i11 != 4) {
                return;
            }
            if (this.f4257z == 0) {
                this.f4257z = C0925r1.m4377c("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nvarying vec2 textureCoordinate;\nvarying vec2 referenceCoordinate;\nuniform highp vec2 uv_reference;\n\nvoid main()\n{\n\tgl_Position = position;\n\ttextureCoordinate = inputTextureCoordinate.xy;\n\treferenceCoordinate = uv_reference;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 referenceCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float threshold;\nconst highp vec3 hsv_scale = vec3(2.0*3.1415926, 0.5, sqrt(0.0));\nhighp vec3 RGB2HSV(highp vec3 c)\n{\n    highp vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n    highp vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);\n    highp vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);\n\n    highp float d = q.x - min(q.w, q.y);\n    highp float e = 1.0e-10;    return clamp(vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x), vec3(0.0), vec3(1.0));\n}\n\nvoid main()\n{\n\t  highp vec4 color = texture2D(inputImageTexture, textureCoordinate);\n\t  highp vec4 crReference = texture2D(inputImageTexture, referenceCoordinate);\n\t  highp vec3 hsv = RGB2HSV(color.rgb) * hsv_scale;\n\t  highp vec3 hsvReference = RGB2HSV(crReference.rgb) * hsv_scale;\n\t  highp float dhue = length(vec2(cos(hsv.x) , sin(hsv.x)) - vec2(cos(hsvReference.x) , sin(hsvReference.x)));\t  highp float dsat = abs(hsv.y - hsvReference.y);\t  gl_FragColor = (dhue <= threshold * 2.0 && dsat <= threshold * 2.0) ? vec4(0.0) : vec4(1.0); \n}");
            }
            i10 = this.f4257z;
        }
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(i10, "position");
        int iGlGetAttribLocation2 = GLES20.glGetAttribLocation(i10, "inputTextureCoordinate");
        int iGlGetUniformLocation2 = GLES20.glGetUniformLocation(i10, "inputImageTexture");
        int iGlGetUniformLocation3 = GLES20.glGetUniformLocation(i10, "uv_reference");
        int iGlGetUniformLocation4 = GLES20.glGetUniformLocation(i10, "threshold");
        int i12 = m4171D()[this.f4246J.ordinal()];
        if (i12 == 1 || i12 == 2) {
            iGlGetUniformLocation = GLES20.glGetUniformLocation(i10, "radius");
        } else if (i12 != 4) {
            return;
        } else {
            iGlGetUniformLocation = 0;
        }
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glBindFramebuffer(36160, this.f4252u[0]);
        GLES20.glViewport(0, 0, m4176I(), m4175H());
        GLES20.glUseProgram(i10);
        floatBuffer.position(0);
        int i13 = iGlGetUniformLocation;
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation2, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glUniform2f(iGlGetUniformLocation3, this.f4243G, this.f4244H);
        int i14 = m4171D()[this.f4246J.ordinal()];
        if (i14 == 1 || i14 == 2) {
            if (i9 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i9);
                GLES20.glUniform1i(iGlGetUniformLocation2, 0);
            }
            GLES20.glUniform1f(i13, this.f4241E);
            GLES20.glUniform1f(iGlGetUniformLocation4, this.f4247K ? 0.1f : 1.0f);
        } else {
            if (i14 != 4) {
                return;
            }
            if (this.f4703p != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.f4703p);
                GLES20.glUniform1i(iGlGetUniformLocation2, 0);
            }
            GLES20.glUniform1f(iGlGetUniformLocation4, this.f4242F);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation2);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glUseProgram(m4403f());
    }

    /* renamed from: H */
    public final int m4175H() {
        return this.f4798i / 2;
    }

    /* renamed from: I */
    public final int m4176I() {
        return this.f4797h / 2;
    }

    /* renamed from: J */
    public final void m4177J(FloatBuffer floatBuffer) {
        if (this.f4237A == 0) {
            this.f4237A = C0925r1.m4377c("attribute vec4 position;\nvoid main()\n{\n    gl_Position = position;\n}", "void main()\n{\n     gl_FragColor = vec4(1.0);\n}");
        }
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        GLES20.glBindFramebuffer(36160, this.f4252u[0]);
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.f4237A, "position");
        GLES20.glViewport(0, 0, m4176I(), m4175H());
        GLES20.glUseProgram(this.f4237A);
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(iGlGetAttribLocation, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(iGlGetAttribLocation);
        boolean zGlIsEnabled = GLES20.glIsEnabled(3042);
        int[] iArr2 = new int[1];
        int[] iArr3 = new int[1];
        GLES20.glGetIntegerv(32971, iArr2, 0);
        GLES20.glGetIntegerv(32970, iArr3, 0);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(775, 0);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBlendFunc(iArr2[0], iArr3[0]);
        if (!zGlIsEnabled) {
            GLES20.glDisable(3042);
        }
        GLES20.glDisableVertexAttribArray(iGlGetAttribLocation);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glUseProgram(m4403f());
    }

    @Override // com.cyberlink.clgpuimage.C0910m1, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        int i9 = this.f4255x;
        if (i9 != 0) {
            GLES20.glDeleteProgram(i9);
            this.f4255x = 0;
        }
        int i10 = this.f4256y;
        if (i10 != 0) {
            GLES20.glDeleteProgram(i10);
            this.f4256y = 0;
        }
        int i11 = this.f4257z;
        if (i11 != 0) {
            GLES20.glDeleteProgram(i11);
            this.f4257z = 0;
        }
        int i12 = this.f4237A;
        if (i12 != 0) {
            GLES20.glDeleteProgram(i12);
            this.f4237A = 0;
        }
        int i13 = this.f4238B;
        if (i13 != 0) {
            GLES20.glDeleteProgram(i13);
            this.f4238B = 0;
        }
        int[] iArr = this.f4253v;
        if (iArr[0] != -1) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4253v[0] = -1;
        }
        int[] iArr2 = this.f4252u;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4252u = null;
        }
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: j */
    public void mo4046j(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(m4403f());
        m4407p();
        int i10 = m4170C()[this.f4245I.ordinal()];
        if (i10 == 2) {
            m4177J(floatBuffer);
            this.f4245I = MaskOperation.MaskPoint;
        } else if (i10 == 3) {
            m4172E(false, floatBuffer, this.f4240D);
            this.f4245I = MaskOperation.MaskPoint;
        } else if (i10 == 4) {
            m4172E(true, floatBuffer, this.f4240D);
            this.f4245I = MaskOperation.MaskPoint;
        } else if (i10 != 5) {
            if (this.f4248L) {
                m4173F(i9, floatBuffer, this.f4240D);
            } else {
                m4174G(i9, floatBuffer, this.f4240D);
            }
        }
        GLES20.glViewport(0, 0, m4402e(), m4401d());
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.f4245I == MaskOperation.HideLast ? this.f4253v[1] : this.f4253v[0]);
        GLES20.glUniform1i(this.f4250N, 1);
        GLES20.glUniform2f(this.f4251O, m4176I(), m4175H());
        super.mo4046j(i9, floatBuffer, floatBuffer2);
    }

    @Override // com.cyberlink.clgpuimage.C0883d1, com.cyberlink.clgpuimage.C0910m1, com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4250N = GLES20.glGetUniformLocation(m4403f(), "inputMaskTexture");
        this.f4251O = GLES20.glGetUniformLocation(m4403f(), "inputMaskSize");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        int i11 = this.f4797h;
        int i12 = this.f4798i;
        super.mo4049n(i9, i10);
        if (i11 != i9 || i12 != i10 || this.f4252u == null || this.f4253v == null) {
            int i13 = this.f4254w;
            this.f4252u = new int[i13];
            this.f4253v = new int[i13];
            int[] iArr = new int[1];
            GLES20.glGetIntegerv(36006, iArr, 0);
            for (int i14 = 0; i14 < this.f4254w; i14++) {
                GLES20.glGenFramebuffers(1, this.f4252u, i14);
                GLES20.glGenTextures(1, this.f4253v, i14);
                GLES20.glBindTexture(3553, this.f4253v[i14]);
                GLES20.glTexImage2D(3553, 0, 6408, m4176I(), m4175H(), 0, 6408, 5121, null);
                GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLES20.glBindFramebuffer(36160, this.f4252u[i14]);
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4253v[i14], 0);
                GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                GLES20.glClear(16384);
                GLES20.glBindTexture(3553, 0);
            }
            GLES20.glBindFramebuffer(36160, iArr[0]);
        }
    }

    public GPUImageMaskAlphaBlendFilter(float f9, boolean z8) {
        super("varying highp vec2 textureCoordinate;\nvarying highp vec2 textureCoordinate2;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2;\nuniform sampler2D inputMaskTexture;\nuniform highp vec2 inputMaskSize;\nuniform highp float mixturePercent;\n\n\nvoid main()\n{\n\tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n\tlowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n\thighp float mask = texture2D(inputMaskTexture, textureCoordinate).a;\n\n\thighp vec4 textureColor3 = vec4(mix(textureColor.rgb, textureColor2.rgb, textureColor2.a * mixturePercent), textureColor.a);\n\tgl_FragColor = vec4(mix(textureColor2.rgb, textureColor3.rgb, mask), textureColor3.a);\n}", f9);
        this.f4254w = 2;
        this.f4241E = BitmapDescriptorFactory.HUE_RED;
        this.f4242F = 0.1f;
        this.f4243G = -1.0f;
        this.f4244H = -1.0f;
        this.f4245I = MaskOperation.MaskPoint;
        this.f4246J = MaskType.PointAdd;
        this.f4247K = true;
        this.f4248L = false;
        this.f4249M = false;
        float[] fArr = C0895h1.f4601V;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4239C = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        float[] fArr2 = C6816a.f22570a;
        FloatBuffer floatBufferAsFloatBuffer2 = ByteBuffer.allocateDirect(fArr2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4240D = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(fArr2).position(0);
        this.f4249M = z8;
        if (z8) {
            m4414w("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}", "varying highp vec2 textureCoordinate;\nvarying highp vec2 textureCoordinate2;\n\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2;\nuniform sampler2D inputMaskTexture;\nuniform highp vec2 inputMaskSize;\nuniform highp float mixturePercent;\n\n\nvoid main()\n{\n\tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n\tlowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n\thighp float mask = texture2D(inputMaskTexture, textureCoordinate).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2( 1.0,  0.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2(-1.0,  0.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2( 1.0,  1.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2( 0.0,  1.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2(-1.0,  1.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2( 1.0, -1.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2( 0.0, -1.0) / inputMaskSize).a;\n\tmask += texture2D(inputMaskTexture, textureCoordinate + vec2(-1.0, -1.0) / inputMaskSize).a;\n\tmask /= 9.0;\n\thighp vec4 textureColor3 = vec4(mix(textureColor.rgb, textureColor2.rgb, textureColor2.a * mixturePercent), textureColor.a);\n\tgl_FragColor = vec4(mix(textureColor2.rgb, textureColor3.rgb, mask), textureColor3.a);\n}");
        }
    }
}
