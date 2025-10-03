package org.webrtc;

import android.opengl.GLES20;
import com.google.android.gms.gcm.Task;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes3.dex */
public class GlUtil {
    private GlUtil() {
    }

    public static void checkNoGLES2Error(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError == 0) {
            return;
        }
        throw new RuntimeException(str + ": GLES20 error: " + iGlGetError);
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        floatBufferAsFloatBuffer.put(fArr);
        floatBufferAsFloatBuffer.position(0);
        return floatBufferAsFloatBuffer;
    }

    public static int generateTexture(int i9) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i10 = iArr[0];
        GLES20.glBindTexture(i9, i10);
        GLES20.glTexParameterf(i9, 10241, 9729.0f);
        GLES20.glTexParameterf(i9, Task.EXTRAS_LIMIT_BYTES, 9729.0f);
        GLES20.glTexParameterf(i9, 10242, 33071.0f);
        GLES20.glTexParameterf(i9, 10243, 33071.0f);
        checkNoGLES2Error("generateTexture");
        return i10;
    }
}
