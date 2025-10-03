package org.webrtc;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.webrtc.ThreadUtils;

/* loaded from: classes3.dex */
class YuvConverter {
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oesTex;\nuniform vec2 xUnit;\nuniform vec4 coeffs;\n\nvoid main() {\n  gl_FragColor.r = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 1.5 * xUnit).rgb);\n  gl_FragColor.g = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc - 0.5 * xUnit).rgb);\n  gl_FragColor.b = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 0.5 * xUnit).rgb);\n  gl_FragColor.a = coeffs.a + dot(coeffs.rgb,\n      texture2D(oesTex, interp_tc + 1.5 * xUnit).rgb);\n}\n";
    private static final String VERTEX_SHADER = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private final int coeffsLoc;
    private boolean released;
    private final GlShader shader;
    private final int texMatrixLoc;
    private final GlTextureFrameBuffer textureFrameBuffer;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final int xUnitLoc;
    private static final FloatBuffer DEVICE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer TEXTURE_RECTANGLE = GlUtil.createFloatBuffer(new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f});

    public YuvConverter() {
        ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();
        this.threadChecker = threadChecker;
        this.released = false;
        threadChecker.checkIsOnValidThread();
        this.textureFrameBuffer = new GlTextureFrameBuffer(6408);
        GlShader glShader = new GlShader(VERTEX_SHADER, FRAGMENT_SHADER);
        this.shader = glShader;
        glShader.useProgram();
        this.texMatrixLoc = glShader.getUniformLocation("texMatrix");
        this.xUnitLoc = glShader.getUniformLocation("xUnit");
        this.coeffsLoc = glShader.getUniformLocation("coeffs");
        GLES20.glUniform1i(glShader.getUniformLocation("oesTex"), 0);
        GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
        glShader.setVertexAttribArray("in_pos", 2, DEVICE_RECTANGLE);
        glShader.setVertexAttribArray("in_tc", 2, TEXTURE_RECTANGLE);
    }

    public void convert(ByteBuffer byteBuffer, int i9, int i10, int i11, int i12, float[] fArr) {
        this.threadChecker.checkIsOnValidThread();
        if (this.released) {
            throw new IllegalStateException("YuvConverter.convert called on released object");
        }
        if (i11 % 8 != 0) {
            throw new IllegalArgumentException("Invalid stride, must be a multiple of 8");
        }
        if (i11 < i9) {
            throw new IllegalArgumentException("Invalid stride, must >= width");
        }
        int i13 = (i9 + 3) / 4;
        int i14 = (i9 + 7) / 8;
        int i15 = (i10 + 1) / 2;
        int i16 = i10 + i15;
        if (byteBuffer.capacity() < i11 * i16) {
            throw new IllegalArgumentException("YuvConverter.convert called with too small buffer");
        }
        float[] fArrMultiplyMatrices = RendererCommon.multiplyMatrices(fArr, RendererCommon.verticalFlipMatrix());
        int i17 = i11 / 4;
        this.textureFrameBuffer.setSize(i17, i16);
        GLES20.glBindFramebuffer(36160, this.textureFrameBuffer.getFrameBufferId());
        GlUtil.checkNoGLES2Error("glBindFramebuffer");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i12);
        GLES20.glUniformMatrix4fv(this.texMatrixLoc, 1, false, fArrMultiplyMatrices, 0);
        GLES20.glViewport(0, 0, i13, i10);
        float f9 = i9;
        GLES20.glUniform2f(this.xUnitLoc, fArrMultiplyMatrices[0] / f9, fArrMultiplyMatrices[1] / f9);
        GLES20.glUniform4f(this.coeffsLoc, 0.299f, 0.587f, 0.114f, BitmapDescriptorFactory.HUE_RED);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glViewport(0, i10, i14, i15);
        GLES20.glUniform2f(this.xUnitLoc, (fArrMultiplyMatrices[0] * 2.0f) / f9, (fArrMultiplyMatrices[1] * 2.0f) / f9);
        GLES20.glUniform4f(this.coeffsLoc, -0.169f, -0.331f, 0.499f, 0.5f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glViewport(i11 / 8, i10, i14, i15);
        GLES20.glUniform4f(this.coeffsLoc, 0.499f, -0.418f, -0.0813f, 0.5f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glReadPixels(0, 0, i17, i16, 6408, 5121, byteBuffer);
        GlUtil.checkNoGLES2Error("YuvConverter.convert");
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindTexture(36197, 0);
    }

    public void release() {
        this.threadChecker.checkIsOnValidThread();
        this.released = true;
        this.shader.release();
        this.textureFrameBuffer.release();
    }
}
