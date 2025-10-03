package com.cyberlink.clgpuimage;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;

/* renamed from: com.cyberlink.clgpuimage.e */
/* loaded from: classes.dex */
public class C0884e {
    /* renamed from: a */
    public static void m4244a(int[] iArr, int[] iArr2, int i9, int i10, int i11, int i12) {
        int[] iArr3 = new int[1];
        int[] iArr4 = new int[1];
        GLES20.glGetIntegerv(32873, iArr3, 0);
        GLES20.glGetIntegerv(36006, iArr4, 0);
        GLES20.glGenFramebuffers(i10, iArr, i9);
        GLES20.glGenTextures(i10, iArr2, i9);
        for (int i13 = i9; i13 < i9 + i10; i13++) {
            GLES20.glBindTexture(3553, iArr2[i13]);
            GLES20.glTexImage2D(3553, 0, 6408, i11, i12, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, iArr[i13]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[i13], 0);
        }
        GLES20.glBindTexture(3553, iArr3[0]);
        GLES20.glBindFramebuffer(36160, iArr4[0]);
    }
}
