package com.cyberlink.you.kernelctrl.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.util.Log;
import com.cyberlink.clgpuimage.C0925r1;
import com.cyberlink.clgpuimage.C0936w0;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes.dex */
public class CLLensFlareFilter extends C0936w0 {

    /* renamed from: I */
    public static final float[] f13990I = {BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: A */
    public int f13991A;

    /* renamed from: B */
    public int f13992B;

    /* renamed from: C */
    public int f13993C;

    /* renamed from: D */
    public int f13994D;

    /* renamed from: E */
    public int f13995E;

    /* renamed from: F */
    public int f13996F;

    /* renamed from: G */
    public int f13997G;

    /* renamed from: H */
    public int f13998H;

    /* renamed from: n */
    public float f13999n;

    /* renamed from: o */
    public float f14000o;

    /* renamed from: p */
    public float f14001p;

    /* renamed from: q */
    public float f14002q;

    /* renamed from: r */
    public float f14003r;

    /* renamed from: s */
    public int f14004s;

    /* renamed from: t */
    public int f14005t;

    /* renamed from: u */
    public Bitmap f14006u;

    /* renamed from: v */
    public int f14007v;

    /* renamed from: w */
    public FloatBuffer f14008w;

    /* renamed from: x */
    public int f14009x;

    /* renamed from: y */
    public int f14010y;

    /* renamed from: z */
    public int f14011z;

    public enum BlendMode {
        NORMAL,
        SCREEN,
        MULTIPLY,
        HARDLIGHT,
        OVERLAY,
        SOFTLIGHT
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.CLLensFlareFilter$a */
    public class RunnableC3081a implements Runnable {
        public RunnableC3081a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CLLensFlareFilter.this.f14004s != -1) {
                GLES20.glDeleteTextures(1, new int[]{CLLensFlareFilter.this.f14004s}, 0);
                CLLensFlareFilter.this.f14004s = -1;
            }
            CLLensFlareFilter cLLensFlareFilter = CLLensFlareFilter.this;
            cLLensFlareFilter.f14004s = C0925r1.m4379e(cLLensFlareFilter.f14006u, -1, false);
        }
    }

    public CLLensFlareFilter(BlendMode blendMode) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        this.f13999n = 0.1f;
        this.f14000o = 0.1f;
        this.f14001p = BitmapDescriptorFactory.HUE_RED;
        this.f14002q = 1.0f;
        this.f14003r = 100.0f;
        this.f14004s = -1;
        this.f14006u = null;
        this.f13997G = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        this.f13998H = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
        if (blendMode == BlendMode.NORMAL) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 color = mix(source, light, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else if (blendMode == BlendMode.SCREEN) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 blend = vec3(1.0) - (vec3(1.0) - source) * (vec3(1.0) - light);         vec3 color = mix(source, blend, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else if (blendMode == BlendMode.MULTIPLY) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 blend = source * light;         vec3 color = mix(source, blend, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else if (blendMode == BlendMode.HARDLIGHT) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 multiply = vec3(2.0) * source * light;         vec3 screen = vec3(1.0) - vec3(2.0) * (vec3(1.0) - source) * (vec3(1.0) - light);         vec3 step_result = step(vec3(0.5), light);         vec3 blend = (vec3(1.0) - step_result) * multiply + step_result * screen;         vec3 color = mix(source, blend, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else if (blendMode == BlendMode.OVERLAY) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 multiply = vec3(2.0) * source * light;         vec3 screen = vec3(1.0) - vec3(2.0) * (vec3(1.0) - source) * (vec3(1.0) - light);         vec3 step_result = step(vec3(0.5), source);         vec3 blend = (vec3(1.0) - step_result) * multiply + step_result * screen;         vec3 color = mix(source, blend, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else if (blendMode == BlendMode.SOFTLIGHT) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 input_lens_flare_texture_coordinate;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    lens_flare_texture_coordinate = input_lens_flare_texture_coordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 lens_flare_texture_coordinate;uniform sampler2D inputImageTexture;uniform sampler2D lens_flare_texture;uniform float center_x;uniform float center_y;uniform float background_image_width;uniform float background_image_height;uniform float flare_width;uniform float flare_height;uniform float cos_rotate;uniform float sin_rotate;uniform float strength;void main(){     float coord_x = (lens_flare_texture_coordinate.x - center_x) * background_image_width;     float coord_y = (lens_flare_texture_coordinate.y - center_y) * background_image_height;     vec2 rotate_coordinate;     rotate_coordinate.x = (coord_x * cos_rotate + coord_y * sin_rotate) / flare_width + 0.5;     rotate_coordinate.y = (coord_y * cos_rotate - coord_x * sin_rotate) / flare_height + 0.5;     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec4 flare = texture2D(lens_flare_texture, rotate_coordinate);     vec3 light = flare.rgb;     float weight = flare.a * strength;     if (rotate_coordinate.x < 0.0 || rotate_coordinate.x > 1.0 || rotate_coordinate.y < 0.0 || rotate_coordinate.y > 1.0)     {         gl_FragColor = vec4(source, 1.0);     }     else     {         vec3 result_1 = vec3(2.0) * source * light + source * source * (vec3(1.0) - vec3(2.0) * light);         vec3 result_2 = vec3(2.0) * source * (vec3(1.0) - light) + sqrt(source) * (vec3(2.0) * light - vec3(1.0));         vec3 step_result = step(vec3(0.5), light);         vec3 blend = (vec3(1.0) - step_result) * result_1 + step_result * result_2;         vec3 color = mix(source, blend, weight);         gl_FragColor = vec4(color, 1.0);     }}");
        } else {
            Log.v("CLOverlayBlend Log", "Invalid blend_mode" + blendMode);
        }
        this.f14008w = ByteBuffer.allocateDirect(f13990I.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    /* renamed from: A */
    public void m16001A(float f9) {
        if (f9 < BitmapDescriptorFactory.HUE_RED || f9 > 100.0f) {
            return;
        }
        this.f14003r = f9;
        m4408q(this.f13996F, f9 / 100.0f);
    }

    /* renamed from: B */
    public void m16002B(float f9, float f10) {
        this.f13999n = f9;
        this.f14000o = f10;
        m4408q(this.f14009x, f9);
        m4408q(this.f14010y, this.f14000o);
    }

    /* renamed from: C */
    public void m16003C(float f9) {
        this.f14001p = f9;
        float fCos = (float) Math.cos(f9);
        float fSin = (float) Math.sin(this.f14001p);
        m4408q(this.f13994D, fCos);
        m4408q(this.f13995E, fSin);
    }

    /* renamed from: D */
    public void m16004D(float f9) {
        this.f14002q = f9;
        float f10 = this.f13997G;
        float f11 = this.f13998H;
        float width = this.f14006u.getWidth() / this.f14006u.getHeight();
        if (this.f13997G / this.f13998H > width) {
            f10 = f11 * width;
        } else {
            f11 = f10 / width;
        }
        m4408q(this.f14011z, f10 * this.f14002q);
        m4408q(this.f13991A, f11 * this.f14002q);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        super.mo4045i();
        GLES20.glDeleteTextures(1, new int[]{this.f14004s}, 0);
        this.f14004s = -1;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        this.f14008w.clear();
        this.f14008w.put(f13990I);
        this.f14008w.position(0);
        GLES20.glVertexAttribPointer(this.f14007v, 2, 5126, false, 0, (Buffer) this.f14008w);
        GLES20.glEnableVertexAttribArray(this.f14007v);
        if (this.f14004s != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f14004s);
            GLES20.glUniform1i(this.f14005t, 3);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f14007v = GLES20.glGetAttribLocation(m4403f(), "input_lens_flare_texture_coordinate");
        this.f14005t = GLES20.glGetUniformLocation(m4403f(), "lens_flare_texture");
        this.f14009x = GLES20.glGetUniformLocation(m4403f(), "center_x");
        this.f14010y = GLES20.glGetUniformLocation(m4403f(), "center_y");
        this.f14011z = GLES20.glGetUniformLocation(m4403f(), "flare_width");
        this.f13991A = GLES20.glGetUniformLocation(m4403f(), "flare_height");
        this.f13992B = GLES20.glGetUniformLocation(m4403f(), "background_image_width");
        this.f13993C = GLES20.glGetUniformLocation(m4403f(), "background_image_height");
        this.f13994D = GLES20.glGetUniformLocation(m4403f(), "cos_rotate");
        this.f13995E = GLES20.glGetUniformLocation(m4403f(), "sin_rotate");
        this.f13996F = GLES20.glGetUniformLocation(m4403f(), "strength");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        m16005z(this.f14006u);
        m16001A(this.f14003r);
        m16002B(this.f13999n, this.f14000o);
        m16003C(this.f14001p);
        m16004D(this.f14002q);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
        this.f13997G = i9;
        this.f13998H = i10;
        m4408q(this.f13992B, i9);
        m4408q(this.f13993C, this.f13998H);
        m16004D(this.f14002q);
    }

    /* renamed from: z */
    public void m16005z(Bitmap bitmap) {
        this.f14006u = bitmap;
        if (bitmap != null) {
            m4406o(new RunnableC3081a());
        }
    }
}
