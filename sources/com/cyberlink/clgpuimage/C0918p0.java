package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* renamed from: com.cyberlink.clgpuimage.p0 */
/* loaded from: classes.dex */
public class C0918p0 extends C0936w0 {

    /* renamed from: q */
    public static String f4726q = "attribute vec4 position; \nattribute vec2 inputTextureCoordinate; \n \nvarying vec2 texture_coordinate; \n \nuniform mat4 transformMatrix; \n \nvoid main() \n{ \n    gl_Position = position; \n    texture_coordinate = (transformMatrix * vec4(inputTextureCoordinate.xy, 0.0, 1.0)).xy; \n} \n";

    /* renamed from: r */
    public static String f4727r = "#extension GL_OES_EGL_image_external : require\nprecision mediump float; \nvarying vec2 texture_coordinate;\n \nuniform samplerExternalOES inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, texture_coordinate);\n}";

    /* renamed from: s */
    public static int f4728s = 36197;

    /* renamed from: n */
    public int[] f4729n;

    /* renamed from: o */
    public int[] f4730o;

    /* renamed from: p */
    public int f4731p;

    public C0918p0() {
        super(f4726q, f4727r);
        this.f4729n = null;
        this.f4730o = null;
    }

    /* renamed from: A */
    public int m4367A() {
        return this.f4731p;
    }

    /* renamed from: B */
    public int m4368B(int i9, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int[] iArr;
        if (!m4405h() || (iArr = this.f4729n) == null || this.f4730o == null) {
            return -1;
        }
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        GLES20.glUseProgram(this.f4793d);
        m4407p();
        if (!m4405h()) {
            return -1;
        }
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.f4794e, 2, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f4794e);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.f4796g, 2, 5126, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.f4796g);
        if (i9 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(f4728s, i9);
            GLES20.glUniform1i(this.f4795f, 0);
        }
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4794e);
        GLES20.glDisableVertexAttribArray(this.f4796g);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindTexture(f4728s, 0);
        GLES20.glBindFramebuffer(36160, 0);
        return this.f4730o[0];
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4369z();
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4729n = new int[1];
        this.f4730o = new int[1];
        this.f4731p = GLES20.glGetUniformLocation(this.f4793d, "transformMatrix");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4729n != null) {
            m4369z();
        }
        int[] iArr = new int[1];
        this.f4729n = iArr;
        this.f4730o = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4730o, 0);
        GLES20.glBindTexture(3553, this.f4730o[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4729n[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4730o[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: z */
    public final void m4369z() {
        int[] iArr = this.f4730o;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4730o = null;
        }
        int[] iArr2 = this.f4729n;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4729n = null;
        }
    }
}
