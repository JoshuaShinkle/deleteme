package com.cyberlink.clgpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import java.nio.ByteBuffer;

/* renamed from: com.cyberlink.clgpuimage.r1 */
/* loaded from: classes.dex */
public class C0925r1 {
    /* renamed from: a */
    public static int m4375a(ByteBuffer byteBuffer, int i9, int i10, int i11) {
        int[] iArr = new int[1];
        if (i11 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6410, i9 / 2, i10 / 2, 0, 6410, 5121, byteBuffer);
        } else {
            GLES20.glBindTexture(3553, i11);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i9 / 2, i10 / 2, 6410, 5121, byteBuffer);
            iArr[0] = i11;
        }
        return iArr[0];
    }

    /* renamed from: b */
    public static int m4376b(ByteBuffer byteBuffer, int i9, int i10, int i11) {
        int[] iArr = new int[1];
        if (i11 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glTexImage2D(3553, 0, 6409, i9, i10, 0, 6409, 5121, byteBuffer);
        } else {
            GLES20.glBindTexture(3553, i11);
            GLES20.glTexSubImage2D(3553, 0, 0, 0, i9, i10, 6409, 5121, byteBuffer);
            iArr[0] = i11;
        }
        return iArr[0];
    }

    /* renamed from: c */
    public static int m4377c(String str, String str2) {
        int[] iArr = new int[1];
        int iM4378d = m4378d(str, 35633);
        if (iM4378d == 0) {
            Log.d("Load Program", "Vertex Shader Failed");
            return 0;
        }
        int iM4378d2 = m4378d(str2, 35632);
        if (iM4378d2 == 0) {
            Log.d("Load Program", "Fragment Shader Failed");
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iGlCreateProgram, iM4378d);
        GLES20.glAttachShader(iGlCreateProgram, iM4378d2);
        GLES20.glLinkProgram(iGlCreateProgram);
        GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            Log.d("Load Program", "Linking Failed");
            return 0;
        }
        GLES20.glDeleteShader(iM4378d);
        GLES20.glDeleteShader(iM4378d2);
        return iGlCreateProgram;
    }

    /* renamed from: d */
    public static int m4378d(String str, int i9) {
        int[] iArr = new int[1];
        int iGlCreateShader = GLES20.glCreateShader(i9);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.d("Load Shader Failed", "Compilation\n" + GLES20.glGetShaderInfoLog(iGlCreateShader));
        return 0;
    }

    /* renamed from: e */
    public static int m4379e(Bitmap bitmap, int i9, boolean z8) {
        int[] iArr = new int[1];
        if (i9 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(3553, i9);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i9;
        }
        if (z8) {
            bitmap.recycle();
        }
        return iArr[0];
    }
}
