package com.cyberlink.media.opengl;

import android.opengl.GLES20;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import p104j2.C5092c;

/* loaded from: classes.dex */
public final class GLMoreUtils {
    static {
        C5092c.m19924a();
    }

    /* renamed from: a */
    public static int m5383a(int i9, String str) {
        int[] iArr = new int[1];
        int iGlCreateShader = GLES20.glCreateShader(i9);
        if (iGlCreateShader == 0) {
            return 0;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Log.e("GLMoreUtils", GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    /* renamed from: b */
    public static int m5384b(String str, String str2) throws Throwable {
        int iM5383a;
        int i9 = 0;
        try {
            int iM5383a2 = m5383a(35633, str);
            if (iM5383a2 == 0) {
                if (iM5383a2 != 0) {
                    GLES20.glDeleteShader(iM5383a2);
                }
                return 0;
            }
            try {
                iM5383a = m5383a(35632, str2);
                if (iM5383a == 0) {
                    if (iM5383a2 != 0) {
                        GLES20.glDeleteShader(iM5383a2);
                    }
                    if (iM5383a != 0) {
                        GLES20.glDeleteShader(iM5383a);
                    }
                    return 0;
                }
                try {
                    int iGlCreateProgram = GLES20.glCreateProgram();
                    if (iGlCreateProgram == 0) {
                        if (iM5383a2 != 0) {
                            GLES20.glDeleteShader(iM5383a2);
                        }
                        if (iM5383a != 0) {
                            GLES20.glDeleteShader(iM5383a);
                        }
                        return 0;
                    }
                    GLES20.glAttachShader(iGlCreateProgram, iM5383a2);
                    GLES20.glAttachShader(iGlCreateProgram, iM5383a);
                    GLES20.glLinkProgram(iGlCreateProgram);
                    int[] iArr = new int[1];
                    GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
                    if (iArr[0] != 0) {
                        if (iM5383a2 != 0) {
                            GLES20.glDeleteShader(iM5383a2);
                        }
                        if (iM5383a != 0) {
                            GLES20.glDeleteShader(iM5383a);
                        }
                        return iGlCreateProgram;
                    }
                    Log.e("GLMoreUtils", GLES20.glGetProgramInfoLog(iGlCreateProgram));
                    GLES20.glDeleteProgram(iGlCreateProgram);
                    if (iM5383a2 != 0) {
                        GLES20.glDeleteShader(iM5383a2);
                    }
                    if (iM5383a != 0) {
                        GLES20.glDeleteShader(iM5383a);
                    }
                    return 0;
                } catch (Throwable th) {
                    th = th;
                    i9 = iM5383a2;
                    if (i9 != 0) {
                        GLES20.glDeleteShader(i9);
                    }
                    if (iM5383a != 0) {
                        GLES20.glDeleteShader(iM5383a);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                iM5383a = 0;
            }
        } catch (Throwable th3) {
            th = th3;
            iM5383a = 0;
        }
    }

    /* renamed from: c */
    public static String m5385c(int i9) {
        if (i9 == 36057) {
            return "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS";
        }
        if (i9 == 36061) {
            return "GL_FRAMEBUFFER_UNSUPPORTED";
        }
        switch (i9) {
            case 36053:
                return "GL_FRAMEBUFFER_COMPLETE";
            case 36054:
                return "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT";
            case 36055:
                return "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT";
            default:
                return "Unknown framebuffer status " + Integer.toHexString(i9);
        }
    }

    /* renamed from: d */
    public static void m5386d() {
        m5387e(3553);
    }

    /* renamed from: e */
    public static void m5387e(int i9) {
        GLES20.glTexParameteri(i9, 10241, 9729);
        GLES20.glTexParameteri(i9, Task.EXTRAS_LIMIT_BYTES, 9729);
        GLES20.glTexParameteri(i9, 10242, 33071);
        GLES20.glTexParameteri(i9, 10243, 33071);
    }
}
