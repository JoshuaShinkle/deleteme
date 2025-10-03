package com.cyberlink.mediacodec;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: com.cyberlink.mediacodec.f */
/* loaded from: classes.dex */
public class C1249f {

    /* renamed from: n */
    public static final String f6161n = "f";

    /* renamed from: o */
    public static final FloatBuffer f6162o;

    /* renamed from: a */
    public EGLDisplay f6163a;

    /* renamed from: b */
    public EGLContext f6164b;

    /* renamed from: c */
    public EGLSurface f6165c;

    /* renamed from: d */
    public final FloatBuffer f6166d;

    /* renamed from: e */
    public int f6167e;

    /* renamed from: f */
    public final int f6168f;

    /* renamed from: g */
    public final int f6169g;

    /* renamed from: h */
    public final int f6170h;

    /* renamed from: i */
    public final int f6171i;

    /* renamed from: j */
    public final int f6172j;

    /* renamed from: k */
    public final boolean f6173k;

    /* renamed from: l */
    public c<ByteBuffer> f6174l;

    /* renamed from: m */
    public c<Bitmap> f6175m;

    /* renamed from: com.cyberlink.mediacodec.f$b */
    public abstract class b<Source> implements c<Source> {

        /* renamed from: a */
        public int f6176a;

        /* renamed from: b */
        public int f6177b;

        /* renamed from: c */
        public int f6178c;

        public b() {
        }

        @Override // com.cyberlink.mediacodec.C1249f.c
        /* renamed from: b */
        public void mo5573b() {
            m5582k("render", new Object[0]);
            GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f);
            GLES20.glClear(16640);
            GLES20.glUseProgram(this.f6176a);
            C1249f.this.f6166d.position(C1249f.this.f6167e);
            GLES20.glVertexAttribPointer(this.f6177b, 3, 5126, false, 20, (Buffer) C1249f.this.f6166d);
            C1249f.this.f6166d.position(C1249f.this.f6167e + 3);
            GLES20.glVertexAttribPointer(this.f6178c, 2, 5126, false, 20, (Buffer) C1249f.this.f6166d);
            GLES20.glEnableVertexAttribArray(this.f6177b);
            GLES20.glEnableVertexAttribArray(this.f6178c);
            mo5578g();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glFinish();
            m5582k("render, done", new Object[0]);
        }

        /* renamed from: c */
        public final void m5574c(String str) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                return;
            }
            String str2 = str + ": glError " + iGlGetError;
            C1249f.this.m5567g(str2, new Object[0]);
            throw new RuntimeException(str2);
        }

        /* renamed from: d */
        public final int m5575d(String str, String str2) {
            int iM5577f;
            m5582k("createProgram", new Object[0]);
            int iM5577f2 = m5577f(35633, str);
            if (iM5577f2 == 0 || (iM5577f = m5577f(35632, str2)) == 0) {
                return 0;
            }
            int iGlCreateProgram = GLES20.glCreateProgram();
            m5574c("glCreateProgram");
            if (iGlCreateProgram == 0) {
                m5580i("createProgram, could not create program", new Object[0]);
            }
            GLES20.glAttachShader(iGlCreateProgram, iM5577f2);
            m5574c("glAttachShader");
            GLES20.glAttachShader(iGlCreateProgram, iM5577f);
            m5574c("glAttachShader");
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] == 1) {
                return iGlCreateProgram;
            }
            m5580i("createProgram, could not link program: " + GLES20.glGetProgramInfoLog(iGlCreateProgram), new Object[0]);
            GLES20.glDeleteProgram(iGlCreateProgram);
            return 0;
        }

        /* renamed from: e */
        public abstract String mo5576e();

        /* renamed from: f */
        public final int m5577f(int i9, String str) {
            m5582k("loadShader", new Object[0]);
            int iGlCreateShader = GLES20.glCreateShader(i9);
            m5574c("glCreateShader type=" + i9);
            GLES20.glShaderSource(iGlCreateShader, str);
            GLES20.glCompileShader(iGlCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
            if (iArr[0] != 0) {
                return iGlCreateShader;
            }
            m5580i("loadShader, could not compile shader " + i9 + ": " + GLES20.glGetShaderInfoLog(iGlCreateShader), new Object[0]);
            GLES20.glDeleteShader(iGlCreateShader);
            return 0;
        }

        /* renamed from: g */
        public abstract void mo5578g();

        /* renamed from: h */
        public void m5579h(String str, String str2) {
            m5581j("prepareShaders", new Object[0]);
            int iM5575d = m5575d(str, str2);
            this.f6176a = iM5575d;
            if (iM5575d == 0) {
                throw new RuntimeException("Could not compile OpenGL shaders!");
            }
            this.f6177b = GLES20.glGetAttribLocation(iM5575d, "aPosition");
            this.f6178c = GLES20.glGetAttribLocation(this.f6176a, "aTexCoord");
        }

        /* renamed from: i */
        public void m5580i(String str, Object... objArr) {
            String str2 = String.format(str, objArr);
            Log.e(mo5576e(), "[" + hashCode() + "] " + str2);
        }

        /* renamed from: j */
        public void m5581j(String str, Object... objArr) {
        }

        /* renamed from: k */
        public void m5582k(String str, Object... objArr) {
        }

        /* renamed from: l */
        public void m5583l() {
            m5584m(3553);
        }

        /* renamed from: m */
        public void m5584m(int i9) {
            m5582k("setTextureParamenters, target " + i9, new Object[0]);
            GLES20.glTexParameteri(i9, 10241, 9729);
            GLES20.glTexParameteri(i9, Task.EXTRAS_LIMIT_BYTES, 9729);
            GLES20.glTexParameteri(i9, 10242, 33071);
            GLES20.glTexParameteri(i9, 10243, 33071);
        }
    }

    /* renamed from: com.cyberlink.mediacodec.f$c */
    public interface c<Source> {
        /* renamed from: a */
        void mo5585a(Source source);

        /* renamed from: b */
        void mo5573b();
    }

    /* renamed from: com.cyberlink.mediacodec.f$d */
    public class d extends f<Bitmap> {

        /* renamed from: i */
        public int f6180i;

        /* renamed from: j */
        public int f6181j;

        public d(int i9) {
            super(i9);
            m5582k("RendererAndroidBitmap", new Object[0]);
            m5587n("#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}");
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: e */
        public String mo5576e() {
            return d.class.getSimpleName();
        }

        @Override // com.cyberlink.mediacodec.C1249f.c
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public void mo5585a(Bitmap bitmap) {
            m5582k("prepareFrame", new Object[0]);
            GLES20.glActiveTexture(this.f6185e + 33984);
            int i9 = this.f6186f;
            if (i9 == 0) {
                m5588o();
                GLES20.glBindTexture(3553, this.f6186f);
                m5583l();
            } else {
                GLES20.glBindTexture(3553, i9);
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width == this.f6180i && height == this.f6181j) {
                GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
                return;
            }
            m5582k("prepareFrame, detected image size changed: " + this.f6180i + "x" + this.f6181j + " => " + width + "x" + height, new Object[0]);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            this.f6180i = width;
            this.f6181j = height;
        }
    }

    /* renamed from: com.cyberlink.mediacodec.f$e */
    public abstract class e extends b<ByteBuffer> {

        /* renamed from: e */
        public final int[] f6183e;

        public e(int i9) {
            super();
            m5582k("RendererMultiTexture, numberOfTextures " + i9, new Object[0]);
            this.f6183e = new int[i9];
        }
    }

    /* renamed from: com.cyberlink.mediacodec.f$f */
    public abstract class f<Source> extends b<Source> {

        /* renamed from: e */
        public final int f6185e;

        /* renamed from: f */
        public int f6186f;

        /* renamed from: g */
        public int f6187g;

        public f(int i9) {
            super();
            m5582k("RendererSingleTexture, textureUnit " + i9, new Object[0]);
            this.f6185e = i9;
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: g */
        public void mo5578g() {
            m5582k("onRender", new Object[0]);
            GLES20.glUniform1i(this.f6187g, this.f6185e);
        }

        /* renamed from: n */
        public void m5587n(String str) {
            m5579h("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", str);
            this.f6187g = GLES20.glGetUniformLocation(this.f6176a, "uTexture");
        }

        /* renamed from: o */
        public void m5588o() {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            this.f6186f = iArr[0];
        }
    }

    /* renamed from: com.cyberlink.mediacodec.f$g */
    public class g extends e {

        /* renamed from: g */
        public int f6189g;

        /* renamed from: h */
        public int f6190h;

        /* renamed from: i */
        public int f6191i;

        public g() {
            super(3);
            m5582k("RendererYUVPlanar", new Object[0]);
            m5590o();
            m5591p();
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: e */
        public String mo5576e() {
            return g.class.getSimpleName();
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: g */
        public void mo5578g() {
            m5582k("onRender", new Object[0]);
            GLES20.glUniform1i(this.f6189g, 0);
            GLES20.glUniform1i(this.f6190h, 1);
            GLES20.glUniform1i(this.f6191i, 2);
        }

        @Override // com.cyberlink.mediacodec.C1249f.c
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public void mo5585a(ByteBuffer byteBuffer) {
            m5582k("prepareFrame", new Object[0]);
            int i9 = C1249f.this.f6170h * C1249f.this.f6171i;
            int iPosition = byteBuffer.position();
            try {
                GLES20.glPixelStorei(3317, 1);
                if (C1249f.this.f6168f == C1249f.this.f6170h) {
                    int i10 = iPosition + i9;
                    byteBuffer.position(iPosition);
                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(3553, this.f6183e[0]);
                    GLES20.glTexSubImage2D(3553, 0, 0, 0, C1249f.this.f6168f, C1249f.this.f6169g, 6409, 5121, byteBuffer);
                    byteBuffer.position(i10);
                    GLES20.glActiveTexture(33985);
                    GLES20.glBindTexture(3553, this.f6183e[1]);
                    GLES20.glTexSubImage2D(3553, 0, 0, 0, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 6409, 5121, byteBuffer);
                    byteBuffer.position(i10 + (i9 / 4));
                    GLES20.glActiveTexture(33986);
                    GLES20.glBindTexture(3553, this.f6183e[2]);
                    GLES20.glTexSubImage2D(3553, 0, 0, 0, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 6409, 5121, byteBuffer);
                } else {
                    int i11 = iPosition + i9;
                    int i12 = i11 + (i9 / 4);
                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(3553, this.f6183e[0]);
                    int i13 = iPosition;
                    for (int i14 = 0; i14 < C1249f.this.f6169g; i14++) {
                        byteBuffer.position(i13);
                        GLES20.glTexSubImage2D(3553, 0, 0, i14, C1249f.this.f6168f, 1, 6409, 5121, byteBuffer);
                        i13 += C1249f.this.f6170h;
                    }
                    GLES20.glActiveTexture(33985);
                    GLES20.glBindTexture(3553, this.f6183e[1]);
                    int i15 = i11;
                    for (int i16 = 0; i16 < C1249f.this.f6169g / 2; i16++) {
                        byteBuffer.position(i15);
                        GLES20.glTexSubImage2D(3553, 0, 0, i16, C1249f.this.f6168f / 2, 1, 6409, 5121, byteBuffer);
                        i15 += C1249f.this.f6170h / 2;
                    }
                    GLES20.glActiveTexture(33986);
                    GLES20.glBindTexture(3553, this.f6183e[2]);
                    int i17 = i12;
                    for (int i18 = 0; i18 < C1249f.this.f6169g / 2; i18++) {
                        byteBuffer.position(i17);
                        GLES20.glTexSubImage2D(3553, 0, 0, i18, C1249f.this.f6168f / 2, 1, 6409, 5121, byteBuffer);
                        i17 += C1249f.this.f6170h / 2;
                    }
                }
            } finally {
                byteBuffer.position(iPosition);
            }
        }

        /* renamed from: o */
        public final void m5590o() {
            m5579h("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", "#define U_INDEX 0\n#define V_INDEX 0\nprecision mediump float;uniform sampler2D      uTextureY;uniform sampler2D      uTextureU;\n#ifdef SEMI_PLANAR\n#define uTextureV uTextureU\n#else\nuniform sampler2D      uTextureV;\n#endif\nvarying vec2           vTexCoord;void main() {   float y = texture2D(uTextureY, vTexCoord)[0];   float u = texture2D(uTextureU, vTexCoord)[U_INDEX];   float v = texture2D(uTextureV, vTexCoord)[V_INDEX];   y = 1.1643 * (y - 0.0625);   u = u - 0.5;   v = v - 0.5;   float r = y + 1.5958 * v;   float g = y - 0.39173* u - 0.81290 * v;   float b = y + 2.017 * u;   gl_FragColor = vec4(r, g, b, 1.0);}");
            this.f6189g = GLES20.glGetUniformLocation(this.f6176a, "uTextureY");
            this.f6190h = GLES20.glGetUniformLocation(this.f6176a, "uTextureU");
            this.f6191i = GLES20.glGetUniformLocation(this.f6176a, "uTextureV");
        }

        /* renamed from: p */
        public final void m5591p() {
            int[] iArr = this.f6183e;
            GLES20.glGenTextures(iArr.length, iArr, 0);
            GLES20.glBindTexture(3553, this.f6183e[0]);
            m5583l();
            GLES20.glTexImage2D(3553, 0, 6409, C1249f.this.f6168f, C1249f.this.f6169g, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f6183e[1]);
            m5583l();
            GLES20.glTexImage2D(3553, 0, 6409, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f6183e[2]);
            m5583l();
            GLES20.glTexImage2D(3553, 0, 6409, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 0, 6409, 5121, null);
        }
    }

    /* renamed from: com.cyberlink.mediacodec.f$h */
    public class h extends e {

        /* renamed from: g */
        public final int f6193g;

        /* renamed from: h */
        public final int f6194h;

        /* renamed from: i */
        public int f6195i;

        /* renamed from: j */
        public int f6196j;

        public h(boolean z8) {
            super(2);
            m5582k("RendererYUVSemiPlanar", new Object[0]);
            this.f6193g = z8 ? 0 : 3;
            this.f6194h = z8 ? 3 : 0;
            m5593o();
            m5594p();
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: e */
        public String mo5576e() {
            return h.class.getSimpleName();
        }

        @Override // com.cyberlink.mediacodec.C1249f.b
        /* renamed from: g */
        public void mo5578g() {
            m5582k("onRender", new Object[0]);
            GLES20.glUniform1i(this.f6195i, 0);
            GLES20.glUniform1i(this.f6196j, 1);
        }

        @Override // com.cyberlink.mediacodec.C1249f.c
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public void mo5585a(ByteBuffer byteBuffer) {
            m5582k("prepareFrame", new Object[0]);
            int i9 = C1249f.this.f6170h * C1249f.this.f6171i;
            int iPosition = byteBuffer.position();
            try {
                GLES20.glPixelStorei(3317, 1);
                int i10 = 3553;
                if (C1249f.this.f6168f == C1249f.this.f6170h) {
                    byteBuffer.position(iPosition);
                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(3553, this.f6183e[0]);
                    GLES20.glTexSubImage2D(3553, 0, 0, 0, C1249f.this.f6168f, C1249f.this.f6169g, 6409, 5121, byteBuffer);
                    byteBuffer.position(i9 + iPosition);
                    GLES20.glActiveTexture(33985);
                    GLES20.glBindTexture(3553, this.f6183e[1]);
                    GLES20.glTexSubImage2D(3553, 0, 0, 0, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 6410, 5121, byteBuffer);
                } else {
                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(3553, this.f6183e[0]);
                    int i11 = 0;
                    int i12 = iPosition;
                    while (i11 < C1249f.this.f6169g) {
                        byteBuffer.position(i12);
                        GLES20.glTexSubImage2D(3553, 0, 0, i11, C1249f.this.f6168f, 1, 6409, 5121, byteBuffer);
                        i12 += C1249f.this.f6170h;
                        i11++;
                        i10 = i10;
                    }
                    GLES20.glActiveTexture(33985);
                    GLES20.glBindTexture(i10, this.f6183e[1]);
                    int i13 = i9 + iPosition;
                    for (int i14 = 0; i14 < C1249f.this.f6169g / 2; i14++) {
                        byteBuffer.position(i13);
                        GLES20.glTexSubImage2D(3553, 0, 0, i14, C1249f.this.f6168f / 2, 1, 6410, 5121, byteBuffer);
                        i13 += C1249f.this.f6170h;
                    }
                }
            } finally {
                byteBuffer.position(iPosition);
            }
        }

        /* renamed from: o */
        public final void m5593o() {
            m5579h("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", "#define SEMI_PLANAR\n#define U_INDEX " + this.f6193g + "\n#define V_INDEX " + this.f6194h + "\nprecision mediump float;uniform sampler2D      uTextureY;uniform sampler2D      uTextureU;\n#ifdef SEMI_PLANAR\n#define uTextureV uTextureU\n#else\nuniform sampler2D      uTextureV;\n#endif\nvarying vec2           vTexCoord;void main() {   float y = texture2D(uTextureY, vTexCoord)[0];   float u = texture2D(uTextureU, vTexCoord)[U_INDEX];   float v = texture2D(uTextureV, vTexCoord)[V_INDEX];   y = 1.1643 * (y - 0.0625);   u = u - 0.5;   v = v - 0.5;   float r = y + 1.5958 * v;   float g = y - 0.39173* u - 0.81290 * v;   float b = y + 2.017 * u;   gl_FragColor = vec4(r, g, b, 1.0);}");
            this.f6195i = GLES20.glGetUniformLocation(this.f6176a, "uTextureY");
            this.f6196j = GLES20.glGetUniformLocation(this.f6176a, "uTextureU");
        }

        /* renamed from: p */
        public final void m5594p() {
            int[] iArr = this.f6183e;
            GLES20.glGenTextures(iArr.length, iArr, 0);
            GLES20.glBindTexture(3553, this.f6183e[0]);
            m5583l();
            GLES20.glTexImage2D(3553, 0, 6409, C1249f.this.f6168f, C1249f.this.f6169g, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f6183e[1]);
            m5583l();
            GLES20.glTexImage2D(3553, 0, 6410, C1249f.this.f6168f / 2, C1249f.this.f6169g / 2, 0, 6410, 5121, null);
        }
    }

    static {
        FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(160).order(ByteOrder.nativeOrder()).asFloatBuffer().put(new float[]{-1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, -1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, -1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, -1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f});
        f6162o = floatBufferPut;
        floatBufferPut.flip();
    }

    public C1249f(int i9, int i10, int i11) {
        this(i9, i10, i11, i10, i11);
    }

    /* renamed from: g */
    public void m5567g(String str, Object... objArr) {
        String str2 = String.format(str, objArr);
        Log.e(f6161n, "[" + hashCode() + "] " + str2);
    }

    /* renamed from: h */
    public void m5568h(String str, Object... objArr) {
    }

    /* renamed from: i */
    public void m5569i(String str, Object... objArr) {
    }

    /* renamed from: j */
    public void m5570j(ByteBuffer byteBuffer) {
        m5569i("drawImage(ByteBuffer)", new Object[0]);
        this.f6174l.mo5585a(byteBuffer);
        this.f6174l.mo5573b();
    }

    /* renamed from: k */
    public void m5571k() {
        m5568h("release", new Object[0]);
        EGLDisplay eGLDisplay = this.f6163a;
        if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglDestroySurface(eGLDisplay, this.f6165c);
            EGL14.eglDestroyContext(this.f6163a, this.f6164b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f6163a);
        }
        this.f6163a = EGL14.EGL_NO_DISPLAY;
        this.f6164b = EGL14.EGL_NO_CONTEXT;
        this.f6165c = EGL14.EGL_NO_SURFACE;
        this.f6174l = null;
        this.f6175m = null;
    }

    /* renamed from: l */
    public final void m5572l() {
        int i9 = this.f6172j;
        if (i9 == 0) {
            m5568h("setup, ColorFormat 0, using RendererAndroidBitmap", new Object[0]);
            this.f6175m = new d(0);
            return;
        }
        if (i9 == 19) {
            m5568h("setup, ColorFormat COLOR_FormatYUV420Planar, using RendererYUVPlanar", new Object[0]);
            this.f6174l = new g();
            return;
        }
        if (i9 == 21) {
            m5568h("setup, ColorFormat COLOR_FormatYUV420SemiPlanar, using RendererYUVSemiPlanar", new Object[0]);
            this.f6174l = new h(true);
            return;
        }
        m5567g("setup, unsupported format " + this.f6172j, new Object[0]);
        throw new UnsupportedOperationException("Unsupported color format " + this.f6172j);
    }

    public C1249f(int i9, int i10, int i11, int i12, int i13) {
        this.f6163a = EGL14.EGL_NO_DISPLAY;
        this.f6164b = EGL14.EGL_NO_CONTEXT;
        this.f6165c = EGL14.EGL_NO_SURFACE;
        this.f6166d = f6162o.asReadOnlyBuffer();
        this.f6167e = 0;
        this.f6173k = false;
        if (i10 <= 0 || i11 <= 0) {
            throw new IllegalArgumentException();
        }
        m5568h("OutputSurfaceBuffer, format " + i9 + ", size " + i10 + "x" + i11, new Object[0]);
        this.f6172j = i9;
        this.f6168f = i10;
        this.f6169g = i11;
        this.f6170h = i12;
        this.f6171i = i13;
        m5572l();
    }
}
