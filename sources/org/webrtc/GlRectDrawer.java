package org.webrtc;

import android.opengl.GLES20;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import org.webrtc.RendererCommon;

/* loaded from: classes3.dex */
public class GlRectDrawer implements RendererCommon.GlDrawer {
    private static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(new float[]{BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f});
    private static final String OES_FRAGMENT_SHADER_STRING = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n";
    private static final String RGB_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(rgb_tex, interp_tc);\n}\n";
    private static final String VERTEX_SHADER_STRING = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    private static final String YUV_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\n\nvoid main() {\n  float y = texture2D(y_tex, interp_tc).r;\n  float u = texture2D(u_tex, interp_tc).r - 0.5;\n  float v = texture2D(v_tex, interp_tc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1);\n}\n";
    private final Map<String, Shader> shaders = new IdentityHashMap();

    public static class Shader {
        public final GlShader glShader;
        public final int texMatrixLocation;

        public Shader(String str) {
            GlShader glShader = new GlShader(GlRectDrawer.VERTEX_SHADER_STRING, str);
            this.glShader = glShader;
            this.texMatrixLocation = glShader.getUniformLocation("texMatrix");
        }
    }

    private void drawRectangle(int i9, int i10, int i11, int i12) {
        GLES20.glViewport(i9, i10, i11, i12);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private void prepareShader(String str, float[] fArr) {
        Shader shader;
        if (this.shaders.containsKey(str)) {
            shader = this.shaders.get(str);
        } else {
            Shader shader2 = new Shader(str);
            this.shaders.put(str, shader2);
            shader2.glShader.useProgram();
            if (str == YUV_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("y_tex"), 0);
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("u_tex"), 1);
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("v_tex"), 2);
            } else if (str == RGB_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("rgb_tex"), 0);
            } else {
                if (str != OES_FRAGMENT_SHADER_STRING) {
                    throw new IllegalStateException("Unknown fragment shader: " + str);
                }
                GLES20.glUniform1i(shader2.glShader.getUniformLocation("oes_tex"), 0);
            }
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            shader2.glShader.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
            shader2.glShader.setVertexAttribArray("in_tc", 2, FULL_RECTANGLE_TEX_BUF);
            shader = shader2;
        }
        shader.glShader.useProgram();
        GLES20.glUniformMatrix4fv(shader.texMatrixLocation, 1, false, fArr, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawOes(int i9, float[] fArr, int i10, int i11, int i12, int i13, int i14, int i15) {
        prepareShader(OES_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i9);
        drawRectangle(i12, i13, i14, i15);
        GLES20.glBindTexture(36197, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawRgb(int i9, float[] fArr, int i10, int i11, int i12, int i13, int i14, int i15) {
        prepareShader(RGB_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i9);
        drawRectangle(i12, i13, i14, i15);
        GLES20.glBindTexture(3553, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawYuv(int[] iArr, float[] fArr, int i9, int i10, int i11, int i12, int i13, int i14) {
        prepareShader(YUV_FRAGMENT_SHADER_STRING, fArr);
        for (int i15 = 0; i15 < 3; i15++) {
            GLES20.glActiveTexture(33984 + i15);
            GLES20.glBindTexture(3553, iArr[i15]);
        }
        drawRectangle(i11, i12, i13, i14);
        for (int i16 = 0; i16 < 3; i16++) {
            GLES20.glActiveTexture(i16 + 33984);
            GLES20.glBindTexture(3553, 0);
        }
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void release() {
        Iterator<Shader> it = this.shaders.values().iterator();
        while (it.hasNext()) {
            it.next().glShader.release();
        }
        this.shaders.clear();
    }
}
