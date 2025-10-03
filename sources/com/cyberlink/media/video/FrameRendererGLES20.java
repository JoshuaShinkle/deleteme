package com.cyberlink.media.video;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.cyberlink.media.opengl.GLMoreUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import p114k2.C5115b;
import p125l2.C5278b;

/* loaded from: classes.dex */
public class FrameRendererGLES20 {

    /* renamed from: q */
    public static final FloatBuffer f5936q;

    /* renamed from: a */
    public final FloatBuffer f5937a;

    /* renamed from: b */
    public int f5938b;

    /* renamed from: c */
    public final int f5939c;

    /* renamed from: d */
    public final int f5940d;

    /* renamed from: e */
    public final int f5941e;

    /* renamed from: f */
    public final int f5942f;

    /* renamed from: g */
    public final int f5943g;

    /* renamed from: h */
    public final int f5944h;

    /* renamed from: i */
    public final boolean f5945i;

    /* renamed from: j */
    public C5115b f5946j;

    /* renamed from: k */
    public final ColorConverter f5947k;

    /* renamed from: l */
    public InterfaceC1225d<ByteBuffer> f5948l;

    /* renamed from: m */
    public InterfaceC1225d<Bitmap> f5949m;

    /* renamed from: n */
    public ByteBuffer f5950n;

    /* renamed from: o */
    public volatile ByteBuffer f5951o;

    /* renamed from: p */
    public State f5952p;

    public enum State {
        INIT,
        GOT_BUFFER,
        UPLOADED
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$b */
    public abstract class AbstractC1223b<Source> implements InterfaceC1225d<Source> {

        /* renamed from: a */
        public int f5957a;

        /* renamed from: b */
        public int f5958b;

        /* renamed from: c */
        public int f5959c;

        public AbstractC1223b() {
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: b */
        public void mo5423b() {
            GLES20.glUseProgram(this.f5957a);
            FrameRendererGLES20.this.f5937a.position(FrameRendererGLES20.this.f5938b);
            GLES20.glVertexAttribPointer(this.f5958b, 3, 5126, false, 20, (Buffer) FrameRendererGLES20.this.f5937a);
            FrameRendererGLES20.this.f5937a.position(FrameRendererGLES20.this.f5938b + 3);
            GLES20.glVertexAttribPointer(this.f5959c, 2, 5126, false, 20, (Buffer) FrameRendererGLES20.this.f5937a);
            GLES20.glEnableVertexAttribArray(this.f5958b);
            GLES20.glEnableVertexAttribArray(this.f5959c);
            mo5425d();
            GLES20.glDrawArrays(5, 0, 4);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: c */
        public void mo5424c(boolean z8) {
            int i9;
            if (!z8 && (i9 = this.f5957a) != 0) {
                GLES20.glDeleteProgram(i9);
            }
            this.f5957a = 0;
        }

        /* renamed from: d */
        public abstract void mo5425d();

        /* renamed from: e */
        public void m5426e(String str, String str2) throws Throwable {
            int iM5384b = GLMoreUtils.m5384b(str, str2);
            this.f5957a = iM5384b;
            if (iM5384b == 0) {
                throw new RuntimeException("Could not compile OpenGL shaders!");
            }
            this.f5958b = GLES20.glGetAttribLocation(iM5384b, "aPosition");
            this.f5959c = GLES20.glGetAttribLocation(this.f5957a, "aTexCoord");
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$c */
    public static class C1224c {

        /* renamed from: a */
        public final int f5961a;

        /* renamed from: b */
        public final int f5962b;

        /* renamed from: c */
        public final int f5963c;

        /* renamed from: d */
        public final int f5964d;

        /* renamed from: e */
        public final int f5965e;

        /* renamed from: f */
        public boolean f5966f;

        /* renamed from: g */
        public boolean f5967g;

        /* renamed from: h */
        public FrameRendererGLES20 m5434h() {
            return new FrameRendererGLES20(this);
        }

        /* renamed from: i */
        public FrameRendererGLES20 m5435i() {
            FrameRendererGLES20 frameRendererGLES20M5434h = m5434h();
            frameRendererGLES20M5434h.m5415n();
            return frameRendererGLES20M5434h;
        }

        /* renamed from: j */
        public FrameRendererGLES20 m5436j() {
            this.f5966f = false;
            return m5435i();
        }

        /* renamed from: k */
        public C1224c m5437k() {
            this.f5967g = true;
            return this;
        }

        public C1224c(int i9, int i10, int i11) {
            this(i9, i10, i11, 0, 0);
        }

        public C1224c(int i9, int i10, int i11, int i12, int i13) {
            this.f5961a = i9;
            this.f5962b = i10;
            this.f5963c = i11;
            this.f5964d = i12;
            this.f5965e = i13;
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$d */
    public interface InterfaceC1225d<Source> {
        /* renamed from: a */
        void mo5438a(Source source);

        /* renamed from: b */
        void mo5423b();

        /* renamed from: c */
        void mo5424c(boolean z8);
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$e */
    public class C1226e extends AbstractC1231j<Bitmap> {

        /* renamed from: i */
        public int f5968i;

        /* renamed from: j */
        public int f5969j;

        public C1226e(int i9) throws Throwable {
            super(i9);
            m5444f("#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}");
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1231j, com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b, com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: c */
        public void mo5424c(boolean z8) {
            this.f5968i = 0;
            this.f5969j = 0;
            super.mo5424c(z8);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo5438a(Bitmap bitmap) {
            GLES20.glActiveTexture(this.f5979e + 33984);
            int i9 = this.f5980f;
            if (i9 == 0) {
                mo5441g();
                GLES20.glBindTexture(3553, this.f5980f);
                GLMoreUtils.m5386d();
            } else {
                GLES20.glBindTexture(3553, i9);
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width == this.f5968i && height == this.f5969j) {
                GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
                return;
            }
            Log.i("RendererAndroidBitmap", "Detected image size changed: " + this.f5968i + "x" + this.f5969j + " => " + width + "x" + height);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            this.f5968i = width;
            this.f5969j = height;
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$f */
    public class C1227f extends AbstractC1231j<ByteBuffer> {

        /* renamed from: i */
        public final int f5971i;

        public C1227f(int i9, int i10, int i11) throws Throwable {
            super(i11);
            this.f5980f = i9;
            this.f5971i = i10;
            m5444f(i10 == 36197 ? "#define OES_INPUT 1\n#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}" : "#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}");
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1231j, com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b, com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: c */
        public void mo5424c(boolean z8) {
            this.f5980f = 0;
            super.mo5424c(z8);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo5438a(ByteBuffer byteBuffer) {
            GLES20.glActiveTexture(this.f5979e + 33984);
            GLES20.glBindTexture(this.f5971i, this.f5980f);
            GLMoreUtils.m5387e(this.f5971i);
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$g */
    public abstract class AbstractC1228g extends AbstractC1223b<ByteBuffer> {

        /* renamed from: e */
        public final int[] f5973e;

        public AbstractC1228g(int i9) {
            super();
            this.f5973e = new int[i9];
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b, com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: c */
        public void mo5424c(boolean z8) {
            if (!z8) {
                int[] iArr = this.f5973e;
                if (iArr[0] != 0) {
                    GLES20.glDeleteTextures(iArr.length, iArr, 0);
                }
            }
            Arrays.fill(this.f5973e, 0);
            super.mo5424c(z8);
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$h */
    public class C1229h extends AbstractC1231j<ByteBuffer> {
        public C1229h() throws Throwable {
            super(FrameRendererGLES20.this);
            m5444f("#define OES_INPUT 1\n#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}");
            mo5441g();
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1231j
        @SuppressLint({"InlinedApi"})
        /* renamed from: g */
        public void mo5441g() {
            super.mo5441g();
            GLES20.glBindTexture(36197, this.f5980f);
            GLMoreUtils.m5387e(36197);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo5438a(ByteBuffer byteBuffer) {
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$i */
    public class C1230i extends AbstractC1231j<ByteBuffer> {

        /* renamed from: i */
        public final int f5976i;

        /* renamed from: j */
        public final int f5977j;

        public C1230i(boolean z8, int i9) throws Throwable {
            super(FrameRendererGLES20.this);
            this.f5976i = z8 ? 6408 : 6407;
            this.f5977j = i9;
            m5444f("#ifdef OES_INPUT\n#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES uTexture;\n#else\nuniform sampler2D uTexture;\n#endif\nprecision mediump float;varying vec2 vTexCoord;void main() {   gl_FragColor = texture2D(uTexture, vTexCoord);}");
            mo5441g();
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1231j
        /* renamed from: g */
        public void mo5441g() {
            super.mo5441g();
            GLES20.glBindTexture(3553, this.f5980f);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, this.f5976i, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, 0, this.f5976i, this.f5977j, null);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void mo5438a(ByteBuffer byteBuffer) {
            int iPosition = byteBuffer.position();
            try {
                GLES20.glPixelStorei(3317, 1);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.f5980f);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, this.f5976i, this.f5977j, byteBuffer);
            } finally {
                byteBuffer.position(iPosition);
            }
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$j */
    public abstract class AbstractC1231j<Source> extends AbstractC1223b<Source> {

        /* renamed from: e */
        public final int f5979e;

        /* renamed from: f */
        public int f5980f;

        /* renamed from: g */
        public int f5981g;

        public AbstractC1231j(FrameRendererGLES20 frameRendererGLES20) {
            this(0);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b, com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: c */
        public void mo5424c(boolean z8) {
            int i9;
            if (!z8 && (i9 = this.f5980f) != 0) {
                GLES20.glDeleteTextures(1, new int[]{i9}, 0);
            }
            this.f5980f = 0;
            super.mo5424c(z8);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b
        /* renamed from: d */
        public void mo5425d() {
            GLES20.glUniform1i(this.f5981g, this.f5979e);
        }

        /* renamed from: f */
        public void m5444f(String str) throws Throwable {
            m5426e("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", str);
            this.f5981g = GLES20.glGetUniformLocation(this.f5957a, "uTexture");
        }

        /* renamed from: g */
        public void mo5441g() {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            this.f5980f = iArr[0];
        }

        public AbstractC1231j(int i9) {
            super();
            this.f5979e = i9;
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$k */
    public class C1232k extends AbstractC1228g {

        /* renamed from: g */
        public int f5983g;

        /* renamed from: h */
        public int f5984h;

        /* renamed from: i */
        public int f5985i;

        public C1232k() throws Throwable {
            super(3);
            m5446g();
            m5447h();
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b
        /* renamed from: d */
        public void mo5425d() {
            GLES20.glUniform1i(this.f5983g, 0);
            GLES20.glUniform1i(this.f5984h, 1);
            GLES20.glUniform1i(this.f5985i, 2);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void mo5438a(ByteBuffer byteBuffer) {
            int iPosition = byteBuffer.position();
            int i9 = FrameRendererGLES20.this.f5941e + iPosition;
            int i10 = i9 + (FrameRendererGLES20.this.f5941e / 4);
            try {
                GLES20.glPixelStorei(3317, 1);
                byteBuffer.position(iPosition);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.f5973e[0]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, 6409, 5121, byteBuffer);
                byteBuffer.position(i9);
                GLES20.glActiveTexture(33985);
                GLES20.glBindTexture(3553, this.f5973e[1]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 6409, 5121, byteBuffer);
                byteBuffer.position(i10);
                GLES20.glActiveTexture(33986);
                GLES20.glBindTexture(3553, this.f5973e[2]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 6409, 5121, byteBuffer);
            } finally {
                byteBuffer.position(iPosition);
            }
        }

        /* renamed from: g */
        public final void m5446g() throws Throwable {
            m5426e("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", "#define U_INDEX 0\n#define V_INDEX 0\nprecision mediump float;uniform sampler2D      uTextureY;uniform sampler2D      uTextureU;\n#ifdef SEMI_PLANAR\n#define uTextureV uTextureU\n#else\nuniform sampler2D      uTextureV;\n#endif\nvarying vec2           vTexCoord;void main() {   float y = texture2D(uTextureY, vTexCoord)[0];   float u = texture2D(uTextureU, vTexCoord)[U_INDEX];   float v = texture2D(uTextureV, vTexCoord)[V_INDEX];   y = 1.1643 * (y - 0.0625);   u = u - 0.5;   v = v - 0.5;   float r = y + 1.5958 * v;   float g = y - 0.39173* u - 0.81290 * v;   float b = y + 2.017 * u;   gl_FragColor = vec4(r, g, b, 1.0);}");
            this.f5983g = GLES20.glGetUniformLocation(this.f5957a, "uTextureY");
            this.f5984h = GLES20.glGetUniformLocation(this.f5957a, "uTextureU");
            this.f5985i = GLES20.glGetUniformLocation(this.f5957a, "uTextureV");
        }

        /* renamed from: h */
        public final void m5447h() {
            int[] iArr = this.f5973e;
            GLES20.glGenTextures(iArr.length, iArr, 0);
            GLES20.glBindTexture(3553, this.f5973e[0]);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, 6409, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f5973e[1]);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, 6409, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f5973e[2]);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, 6409, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 0, 6409, 5121, null);
        }
    }

    /* renamed from: com.cyberlink.media.video.FrameRendererGLES20$l */
    public class C1233l extends AbstractC1228g {

        /* renamed from: g */
        public final int f5987g;

        /* renamed from: h */
        public final int f5988h;

        /* renamed from: i */
        public int f5989i;

        /* renamed from: j */
        public int f5990j;

        public C1233l(boolean z8) throws Throwable {
            super(2);
            this.f5987g = !z8 ? 1 : 0;
            this.f5988h = z8 ? 1 : 0;
            m5449g();
            m5450h();
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.AbstractC1223b
        /* renamed from: d */
        public void mo5425d() {
            GLES20.glUniform1i(this.f5989i, 0);
            GLES20.glUniform1i(this.f5990j, 1);
        }

        @Override // com.cyberlink.media.video.FrameRendererGLES20.InterfaceC1225d
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void mo5438a(ByteBuffer byteBuffer) {
            int iPosition = byteBuffer.position();
            int i9 = FrameRendererGLES20.this.f5941e + iPosition;
            try {
                GLES20.glPixelStorei(3317, 1);
                byteBuffer.position(iPosition);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.f5973e[0]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, 6409, 5121, byteBuffer);
                byteBuffer.position(i9);
                GLES20.glActiveTexture(33985);
                GLES20.glBindTexture(3553, this.f5973e[1]);
                GLES20.glTexSubImage2D(3553, 0, 0, 0, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 6410, 5121, byteBuffer);
            } finally {
                byteBuffer.position(iPosition);
            }
        }

        /* renamed from: g */
        public final void m5449g() throws Throwable {
            m5426e("attribute vec4         aPosition;attribute vec2         aTexCoord;varying vec2           vTexCoord;void main() {   gl_Position = aPosition;   vTexCoord = aTexCoord;}", "#define SEMI_PLANAR\n#define U_INDEX " + this.f5987g + "\n#define V_INDEX " + this.f5988h + "\nprecision mediump float;uniform sampler2D      uTextureY;uniform sampler2D      uTextureU;\n#ifdef SEMI_PLANAR\n#define uTextureV uTextureU\n#else\nuniform sampler2D      uTextureV;\n#endif\nvarying vec2           vTexCoord;void main() {   float y = texture2D(uTextureY, vTexCoord)[0];   float u = texture2D(uTextureU, vTexCoord)[U_INDEX];   float v = texture2D(uTextureV, vTexCoord)[V_INDEX];   y = 1.1643 * (y - 0.0625);   u = u - 0.5;   v = v - 0.5;   float r = y + 1.5958 * v;   float g = y - 0.39173* u - 0.81290 * v;   float b = y + 2.017 * u;   gl_FragColor = vec4(r, g, b, 1.0);}");
            this.f5989i = GLES20.glGetUniformLocation(this.f5957a, "uTextureY");
            this.f5990j = GLES20.glGetUniformLocation(this.f5957a, "uTextureU");
        }

        /* renamed from: h */
        public final void m5450h() {
            int[] iArr = this.f5973e;
            GLES20.glGenTextures(iArr.length, iArr, 0);
            GLES20.glBindTexture(3553, this.f5973e[0]);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, 6409, FrameRendererGLES20.this.f5939c, FrameRendererGLES20.this.f5940d, 0, 6409, 5121, null);
            GLES20.glBindTexture(3553, this.f5973e[1]);
            GLMoreUtils.m5386d();
            GLES20.glTexImage2D(3553, 0, 6410, FrameRendererGLES20.this.f5939c / 2, FrameRendererGLES20.this.f5940d / 2, 0, 6410, 5121, null);
        }
    }

    static {
        FloatBuffer floatBufferPut = ByteBuffer.allocateDirect(160).order(ByteOrder.nativeOrder()).asFloatBuffer().put(new float[]{-1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, -1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, -1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, -1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, -1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f});
        f5936q = floatBufferPut;
        floatBufferPut.flip();
    }

    /* renamed from: h */
    public static C1224c m5407h(int i9, int i10, int i11) {
        if (i9 > 0 && i10 > 0) {
            if (i11 != 2130708361) {
                return new C1224c(i9, i10, i11);
            }
            throw new IllegalArgumentException("Invalid color format.");
        }
        throw new IllegalArgumentException("Invaild video dimension " + i9 + "x" + i10);
    }

    /* renamed from: i */
    public static C1224c m5408i() {
        int i9 = 0;
        return new C1224c(i9, i9, 2130708361);
    }

    /* renamed from: f */
    public void m5409f() throws Throwable {
        if (this.f5952p == State.GOT_BUFFER) {
            m5422u();
        }
        if (this.f5952p != State.UPLOADED) {
            throw new IllegalStateException();
        }
        this.f5952p = State.INIT;
        if (!m5414m()) {
            if (this.f5946j != null) {
                m5418q(false);
            }
            this.f5948l.mo5423b();
            return;
        }
        if (this.f5946j == null) {
            this.f5946j = new C5115b(this.f5939c, this.f5940d);
        }
        this.f5946j.m19977f();
        try {
            GLES20.glClearColor(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f);
            GLES20.glClear(16384);
            this.f5948l.mo5423b();
        } finally {
            this.f5946j.m19973b();
        }
    }

    /* renamed from: g */
    public void m5410g(ByteBuffer byteBuffer) {
        this.f5952p = State.INIT;
        if (this.f5947k == null) {
            this.f5951o = byteBuffer;
            this.f5952p = State.GOT_BUFFER;
            return;
        }
        this.f5950n.rewind();
        this.f5947k.m5401a(byteBuffer, this.f5950n, this.f5939c, this.f5940d);
        this.f5950n.rewind();
        this.f5951o = this.f5950n;
        this.f5952p = State.GOT_BUFFER;
    }

    /* renamed from: j */
    public InterfaceC1225d<Bitmap> m5411j() {
        if (this.f5949m == null) {
            this.f5949m = new C1226e(3);
        }
        return this.f5949m;
    }

    /* renamed from: k */
    public int m5412k() {
        InterfaceC1225d<ByteBuffer> interfaceC1225d = this.f5948l;
        if (interfaceC1225d instanceof C1229h) {
            return ((C1229h) interfaceC1225d).f5980f;
        }
        C5115b c5115b = this.f5946j;
        return (c5115b != null ? Integer.valueOf(c5115b.m19974c()) : null).intValue();
    }

    /* renamed from: l */
    public final boolean m5413l() {
        return this.f5943g != 0;
    }

    /* renamed from: m */
    public boolean m5414m() {
        return this.f5945i;
    }

    /* renamed from: n */
    public void m5415n() {
        if (this.f5948l != null) {
            return;
        }
        if (m5413l()) {
            m5420s(new C1227f(this.f5943g, this.f5944h, 0));
            return;
        }
        int i9 = this.f5942f;
        if (i9 == 6) {
            m5420s(new C1230i(false, 33635));
            return;
        }
        if (i9 == 11) {
            m5420s(new C1230i(false, 5121));
            return;
        }
        if (i9 == 19) {
            m5420s(new C1232k());
            return;
        }
        if (i9 == 21) {
            m5420s(new C1233l(true));
            return;
        }
        if (i9 == 2130708361) {
            m5420s(new C1229h());
            return;
        }
        if (i9 == 2130741384) {
            m5420s(new C1230i(true, 5121));
        } else {
            if (i9 == 2141391875) {
                m5420s(new C1232k());
                return;
            }
            throw new UnsupportedOperationException("Unsupported color format " + this.f5942f);
        }
    }

    /* renamed from: o */
    public void m5416o(ByteBuffer byteBuffer) {
        m5410g(byteBuffer);
        if (this.f5947k == null) {
            m5422u();
        }
    }

    /* renamed from: p */
    public void m5417p(boolean z8) {
        InterfaceC1225d<ByteBuffer> interfaceC1225d = this.f5948l;
        if (interfaceC1225d != null) {
            interfaceC1225d.mo5424c(z8);
            this.f5948l = null;
        }
        InterfaceC1225d<Bitmap> interfaceC1225d2 = this.f5949m;
        if (interfaceC1225d2 != null) {
            interfaceC1225d2.mo5424c(z8);
            this.f5949m = null;
        }
        ColorConverter colorConverter = this.f5947k;
        if (colorConverter != null) {
            colorConverter.release();
        }
        this.f5950n = null;
        this.f5951o = null;
        m5418q(z8);
    }

    /* renamed from: q */
    public final void m5418q(boolean z8) {
        C5115b c5115b = this.f5946j;
        if (c5115b != null) {
            c5115b.m19976e(z8);
            this.f5946j = null;
        }
    }

    /* renamed from: r */
    public void m5419r(ByteBuffer byteBuffer) throws Throwable {
        m5416o(byteBuffer);
        m5409f();
    }

    /* renamed from: s */
    public final void m5420s(InterfaceC1225d<ByteBuffer> interfaceC1225d) {
        InterfaceC1225d<ByteBuffer> interfaceC1225d2 = this.f5948l;
        if (interfaceC1225d2 != null) {
            interfaceC1225d2.mo5424c(false);
        }
        this.f5948l = interfaceC1225d;
    }

    /* renamed from: t */
    public void m5421t(boolean z8) {
        if (m5413l()) {
            z8 = !z8;
        }
        this.f5938b = z8 ? 20 : 0;
    }

    /* renamed from: u */
    public void m5422u() {
        if (this.f5952p != State.GOT_BUFFER) {
            throw new IllegalStateException();
        }
        this.f5952p = State.INIT;
        this.f5948l.mo5438a(this.f5951o);
        this.f5951o = null;
        this.f5952p = State.UPLOADED;
    }

    public FrameRendererGLES20(C1224c c1224c) {
        this.f5937a = f5936q.asReadOnlyBuffer();
        this.f5952p = State.INIT;
        int i9 = c1224c.f5961a;
        this.f5939c = i9;
        int i10 = c1224c.f5962b;
        this.f5940d = i10;
        int i11 = c1224c.f5963c;
        this.f5942f = i11;
        this.f5943g = c1224c.f5964d;
        this.f5944h = c1224c.f5965e;
        this.f5945i = c1224c.f5966f;
        m5421t(c1224c.f5967g);
        this.f5941e = i9 * i10;
        if (m5413l()) {
            this.f5947k = null;
            this.f5950n = null;
            return;
        }
        if (i11 == 6 || i11 == 11 || i11 == 19 || i11 == 21 || i11 == 2130708361 || i11 == 2130741384) {
            this.f5947k = null;
            this.f5950n = null;
        } else if (i11 == 2141391875) {
            this.f5947k = ColorConverter.m5400b(2141391875, 19);
            this.f5950n = ByteBuffer.allocateDirect(C5278b.m20553e(19, i9, i10)).order(ByteOrder.nativeOrder());
        } else {
            throw new UnsupportedOperationException("Unsupported color format " + i11);
        }
    }
}
