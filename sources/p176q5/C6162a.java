package p176q5;

import android.annotation.SuppressLint;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.util.Log;
import java.nio.Buffer;

@SuppressLint({"NewApi"})
/* renamed from: q5.a */
/* loaded from: classes2.dex */
public class C6162a extends GLES20 {
    /* renamed from: a */
    public static void m23614a(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError == 0) {
            return;
        }
        Log.e("GLES20DebugWrapper", str + ": glError " + iGlGetError);
        throw new GLException(iGlGetError);
    }

    public static int glCheckFramebufferStatus(int i9) {
        int iGlCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(i9);
        m23614a("glCheckFramebufferStatus");
        return iGlCheckFramebufferStatus;
    }

    public static void glDeleteFramebuffers(int i9, int[] iArr, int i10) {
        GLES20.glDeleteFramebuffers(i9, iArr, i10);
        m23614a("glDeleteFramebuffers");
    }

    public static void glDeleteTextures(int i9, int[] iArr, int i10) {
        GLES20.glDeleteTextures(i9, iArr, i10);
        m23614a("glDeleteTextures");
    }

    public static void glFramebufferTexture2D(int i9, int i10, int i11, int i12, int i13) {
        GLES20.glFramebufferTexture2D(i9, i10, i11, i12, i13);
        m23614a("glFramebufferTexture2D");
    }

    public static void glGenFramebuffers(int i9, int[] iArr, int i10) {
        GLES20.glGenFramebuffers(i9, iArr, i10);
        m23614a("glGenFramebuffers");
    }

    public static void glGenTextures(int i9, int[] iArr, int i10) {
        GLES20.glGenTextures(i9, iArr, i10);
        m23614a("glGenTextures");
    }

    public static void glTexImage2D(int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, Buffer buffer) {
        GLES20.glTexImage2D(i9, i10, i11, i12, i13, i14, i15, i16, buffer);
        m23614a("glTexImage2D");
    }
}
