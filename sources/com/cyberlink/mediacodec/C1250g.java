package com.cyberlink.mediacodec;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.cyberlink.mediacodec.g */
/* loaded from: classes.dex */
public class C1250g {

    /* renamed from: a */
    public final float[] f6198a;

    /* renamed from: b */
    public FloatBuffer f6199b;

    /* renamed from: c */
    public float[] f6200c;

    /* renamed from: d */
    public float[] f6201d;

    /* renamed from: e */
    public int f6202e;

    /* renamed from: f */
    public int f6203f;

    /* renamed from: g */
    public int f6204g;

    /* renamed from: h */
    public int f6205h;

    /* renamed from: i */
    public int f6206i;

    /* renamed from: j */
    public int f6207j;

    public C1250g() {
        float[] fArr = {-1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, -1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f};
        this.f6198a = fArr;
        this.f6200c = new float[16];
        this.f6201d = new float[16];
        this.f6203f = -12345;
        FloatBuffer floatBufferAsFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f6199b = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(fArr).position(0);
        Matrix.setIdentityM(this.f6201d, 0);
    }

    /* renamed from: a */
    public void m5595a(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError == 0) {
            return;
        }
        Log.e("TextureRender", str + ": glError " + iGlGetError);
        throw new RuntimeException(str + ": glError " + iGlGetError);
    }

    /* renamed from: b */
    public final int m5596b(String str, String str2) {
        int iM5599e;
        int iM5599e2 = m5599e(35633, str);
        if (iM5599e2 == 0 || (iM5599e = m5599e(35632, str2)) == 0) {
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        m5595a("glCreateProgram");
        if (iGlCreateProgram == 0) {
            Log.e("TextureRender", "Could not create program");
        }
        GLES20.glAttachShader(iGlCreateProgram, iM5599e2);
        m5595a("glAttachShader");
        GLES20.glAttachShader(iGlCreateProgram, iM5599e);
        m5595a("glAttachShader");
        GLES20.glLinkProgram(iGlCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return iGlCreateProgram;
        }
        Log.e("TextureRender", "Could not link program: ");
        Log.e("TextureRender", GLES20.glGetProgramInfoLog(iGlCreateProgram));
        GLES20.glDeleteProgram(iGlCreateProgram);
        return 0;
    }

    /* renamed from: c */
    public void m5597c(SurfaceTexture surfaceTexture) {
        m5595a("onDrawFrame start");
        surfaceTexture.getTransformMatrix(this.f6201d);
        GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.f6202e);
        m5595a("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.f6203f);
        this.f6199b.position(0);
        GLES20.glVertexAttribPointer(this.f6206i, 3, 5126, false, 20, (Buffer) this.f6199b);
        m5595a("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.f6206i);
        m5595a("glEnableVertexAttribArray maPositionHandle");
        this.f6199b.position(3);
        GLES20.glVertexAttribPointer(this.f6207j, 2, 5126, false, 20, (Buffer) this.f6199b);
        m5595a("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.f6207j);
        m5595a("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.f6200c, 0);
        GLES20.glUniformMatrix4fv(this.f6204g, 1, false, this.f6200c, 0);
        GLES20.glUniformMatrix4fv(this.f6205h, 1, false, this.f6201d, 0);
        GLES20.glDrawArrays(5, 0, 4);
        m5595a("glDrawArrays");
        GLES20.glFinish();
    }

    /* renamed from: d */
    public int m5598d() {
        return this.f6203f;
    }

    /* renamed from: e */
    public final int m5599e(int i9, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i9);
        m5595a("glCreateShader type=" + i9);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.e("TextureRender", "Could not compile shader " + i9 + ":");
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.SPACE);
        sb.append(GLES20.glGetShaderInfoLog(iGlCreateShader));
        Log.e("TextureRender", sb.toString());
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    /* renamed from: f */
    public void m5600f() {
        int iM5596b = m5596b("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        this.f6202e = iM5596b;
        if (iM5596b == 0) {
            throw new RuntimeException("failed creating program");
        }
        this.f6206i = GLES20.glGetAttribLocation(iM5596b, "aPosition");
        m5595a("glGetAttribLocation aPosition");
        if (this.f6206i == -1) {
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        this.f6207j = GLES20.glGetAttribLocation(this.f6202e, "aTextureCoord");
        m5595a("glGetAttribLocation aTextureCoord");
        if (this.f6207j == -1) {
            throw new RuntimeException("Could not get attrib location for aTextureCoord");
        }
        this.f6204g = GLES20.glGetUniformLocation(this.f6202e, "uMVPMatrix");
        m5595a("glGetUniformLocation uMVPMatrix");
        if (this.f6204g == -1) {
            throw new RuntimeException("Could not get attrib location for uMVPMatrix");
        }
        this.f6205h = GLES20.glGetUniformLocation(this.f6202e, "uSTMatrix");
        m5595a("glGetUniformLocation uSTMatrix");
        if (this.f6205h == -1) {
            throw new RuntimeException("Could not get attrib location for uSTMatrix");
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i9 = iArr[0];
        this.f6203f = i9;
        GLES20.glBindTexture(36197, i9);
        m5595a("glBindTexture mTextureID");
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        m5595a("glTexParameter");
    }
}
