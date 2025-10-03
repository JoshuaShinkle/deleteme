package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* renamed from: com.cyberlink.clgpuimage.q0 */
/* loaded from: classes.dex */
public class C0921q0 extends C0936w0 {

    /* renamed from: s */
    public static String f4737s = "attribute vec4 position; \nattribute vec2 inputTextureCoordinate; \n \nvarying vec2 texture_coordinate; \n \nvoid main() \n{ \n    gl_Position = position; \n    texture_coordinate = inputTextureCoordinate; \n} \n";

    /* renamed from: t */
    public static String f4738t = "precision mediump float; \n \nvarying vec2 texture_coordinate; \n \nuniform sampler2D inputImageTexture; \nuniform sampler2D inputImageTexture2; \nuniform int isHD; \n \nconst mat3 yuv2rgb_forHD = mat3( \n        1,        1,         1, \n        0,       -0.343,     1.765, \n        1.400,   -0.711,     0 \n        ); \n \nconst mat3 yuv2rgb_forSD = mat3( \n        1,        1,         1, \n        0,       -0.344,     1.772, \n        1.402,   -0.714,     0 \n        ); \n \nvoid main() \n{ \n    vec3 yuv; \n    yuv.x = texture2D(inputImageTexture, texture_coordinate).r; \n    yuv.yz = texture2D(inputImageTexture2, texture_coordinate).ar; \n    vec3 rgb; \n    if (isHD == 1) \n        rgb = yuv2rgb_forHD * (yuv - vec3(0.0, 0.5, 0.5)); \n    else \n        rgb = yuv2rgb_forSD * (yuv - vec3(0.0, 0.5, 0.5)); \n    gl_FragColor = vec4(rgb, 1.0); \n} \n";

    /* renamed from: n */
    public int f4739n;

    /* renamed from: o */
    public int f4740o;

    /* renamed from: p */
    public int[] f4741p;

    /* renamed from: q */
    public int[] f4742q;

    /* renamed from: r */
    public int f4743r;

    public C0921q0() {
        super(f4737s, f4738t);
        this.f4741p = null;
        this.f4742q = null;
        this.f4743r = 1;
    }

    /* renamed from: A */
    public int m4372A(int i9, int i10, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int[] iArr;
        if (!m4405h() || (iArr = this.f4741p) == null || this.f4742q == null) {
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
            GLES20.glBindTexture(3553, i9);
            GLES20.glUniform1i(this.f4795f, 0);
        }
        if (i10 != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, i10);
            GLES20.glUniform1i(this.f4739n, 3);
        }
        GLES20.glUniform1i(this.f4740o, this.f4743r);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f4794e);
        GLES20.glDisableVertexAttribArray(this.f4796g);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
        return this.f4742q[0];
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        m4373z();
        super.mo4045i();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4739n = GLES20.glGetUniformLocation(m4403f(), "inputImageTexture2");
        this.f4740o = GLES20.glGetUniformLocation(m4403f(), "isHD");
        this.f4741p = new int[1];
        this.f4742q = new int[1];
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        if (this.f4741p != null) {
            m4373z();
        }
        int[] iArr = new int[1];
        this.f4741p = iArr;
        this.f4742q = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        GLES20.glGenTextures(1, this.f4742q, 0);
        GLES20.glBindTexture(3553, this.f4742q[0]);
        GLES20.glTexImage2D(3553, 0, 6408, i9, i10, 0, 6408, 5121, null);
        GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLES20.glBindFramebuffer(36160, this.f4741p[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f4742q[0], 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    /* renamed from: z */
    public final void m4373z() {
        int[] iArr = this.f4742q;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.f4742q = null;
        }
        int[] iArr2 = this.f4741p;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.f4741p = null;
        }
    }
}
