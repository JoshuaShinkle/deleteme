package com.cyberlink.clgpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes.dex */
public class CLBlendModesFilter extends C0936w0 {

    /* renamed from: A */
    public static final float[] f4012A = {BitmapDescriptorFactory.HUE_RED, 1.0f, 1.0f, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED};

    /* renamed from: B */
    public static /* synthetic */ int[] f4013B;

    /* renamed from: n */
    public int f4014n;

    /* renamed from: o */
    public FloatBuffer f4015o;

    /* renamed from: p */
    public float[] f4016p;

    /* renamed from: q */
    public int f4017q;

    /* renamed from: r */
    public int f4018r;

    /* renamed from: s */
    public Bitmap f4019s;

    /* renamed from: t */
    public int f4020t;

    /* renamed from: u */
    public int f4021u;

    /* renamed from: v */
    public Rotation f4022v;

    /* renamed from: w */
    public boolean f4023w;

    /* renamed from: x */
    public boolean f4024x;

    /* renamed from: y */
    public int f4025y;

    /* renamed from: z */
    public float f4026z;

    public enum BlendMode {
        NORMAL,
        SCREEN,
        MULTIPLY,
        HARDLIGHT,
        OVERLAY,
        SOFTLIGHT;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static BlendMode[] valuesCustom() {
            BlendMode[] blendModeArrValuesCustom = values();
            int length = blendModeArrValuesCustom.length;
            BlendMode[] blendModeArr = new BlendMode[length];
            System.arraycopy(blendModeArrValuesCustom, 0, blendModeArr, 0, length);
            return blendModeArr;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.CLBlendModesFilter$a */
    public class RunnableC0854a implements Runnable {
        public RunnableC0854a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CLBlendModesFilter.this.m4056F();
        }
    }

    public CLBlendModesFilter(Bitmap bitmap, BlendMode blendMode) {
        this(bitmap, blendMode, Rotation.NORMAL, false, false);
    }

    /* renamed from: z */
    public static /* synthetic */ int[] m4051z() {
        int[] iArr = f4013B;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Rotation.valuesCustom().length];
        try {
            iArr2[Rotation.NORMAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Rotation.ROTATION_180.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Rotation.ROTATION_270.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[Rotation.ROTATION_90.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        f4013B = iArr2;
        return iArr2;
    }

    /* renamed from: A */
    public final float[] m4052A(float[] fArr, Rotation rotation, boolean z8, boolean z9) {
        int i9 = m4051z()[rotation.ordinal()];
        float[] fArr2 = i9 != 2 ? i9 != 3 ? i9 != 4 ? new float[]{fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7]} : new float[]{fArr[4], fArr[5], fArr[0], fArr[1], fArr[6], fArr[7], fArr[2], fArr[3]} : new float[]{fArr[6], fArr[7], fArr[4], fArr[5], fArr[2], fArr[3], fArr[0], fArr[1]} : new float[]{fArr[2], fArr[3], fArr[6], fArr[7], fArr[0], fArr[1], fArr[4], fArr[5]};
        if (z8) {
            float f9 = (((fArr2[0] + fArr2[2]) + fArr2[4]) + fArr2[6]) / 2.0f;
            float f10 = (((fArr2[1] + fArr2[3]) + fArr2[5]) + fArr2[7]) / 2.0f;
            int i10 = m4051z()[rotation.ordinal()];
            fArr2 = (i10 == 2 || i10 == 4) ? new float[]{fArr2[0], f10 - fArr2[1], fArr2[2], f10 - fArr2[3], fArr2[4], f10 - fArr2[5], fArr2[6], f10 - fArr2[7]} : new float[]{f9 - fArr2[0], fArr2[1], f9 - fArr2[2], fArr2[3], f9 - fArr2[4], fArr2[5], f9 - fArr2[6], fArr2[7]};
        }
        if (!z9) {
            return fArr2;
        }
        float f11 = (((fArr2[0] + fArr2[2]) + fArr2[4]) + fArr2[6]) / 2.0f;
        float f12 = (((fArr2[1] + fArr2[3]) + fArr2[5]) + fArr2[7]) / 2.0f;
        int i11 = m4051z()[rotation.ordinal()];
        return (i11 == 2 || i11 == 4) ? new float[]{f11 - fArr2[0], fArr2[1], f11 - fArr2[2], fArr2[3], f11 - fArr2[4], fArr2[5], f11 - fArr2[6], fArr2[7]} : new float[]{fArr2[0], f12 - fArr2[1], fArr2[2], f12 - fArr2[3], fArr2[4], f12 - fArr2[5], fArr2[6], f12 - fArr2[7]};
    }

    /* renamed from: B */
    public void m4053B(Bitmap bitmap) {
        this.f4019s = bitmap;
        this.f4020t = bitmap.getWidth();
        this.f4021u = bitmap.getHeight();
        m4406o(new RunnableC0854a());
    }

    /* renamed from: C */
    public void m4054C(float f9) {
        if (f9 < BitmapDescriptorFactory.HUE_RED || f9 > 100.0f) {
            return;
        }
        this.f4026z = f9;
        m4408q(this.f4025y, f9 / 100.0f);
    }

    /* renamed from: D */
    public void m4055D(Rotation rotation, boolean z8, boolean z9) {
        this.f4022v = rotation;
        this.f4023w = z8;
        this.f4024x = z9;
    }

    /* renamed from: F */
    public final void m4056F() {
        int i9 = this.f4018r;
        if (i9 != -1) {
            GLES20.glDeleteTextures(1, new int[]{i9}, 0);
            this.f4018r = -1;
        }
        Bitmap bitmap = this.f4019s;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f4018r = C0925r1.m4379e(this.f4019s, -1, false);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        super.mo4045i();
        GLES20.glDeleteTextures(1, new int[]{this.f4018r}, 0);
        this.f4018r = -1;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        this.f4015o.clear();
        this.f4015o.put(m4052A(this.f4016p, this.f4022v, this.f4023w, this.f4024x));
        this.f4015o.position(0);
        GLES20.glVertexAttribPointer(this.f4014n, 2, 5126, false, 0, (Buffer) this.f4015o);
        GLES20.glEnableVertexAttribArray(this.f4014n);
        if (this.f4018r != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f4018r);
            GLES20.glUniform1i(this.f4017q, 3);
        }
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4014n = GLES20.glGetAttribLocation(m4403f(), "inputBlendTextureCoordinate");
        this.f4017q = GLES20.glGetUniformLocation(m4403f(), "blending_texture");
        this.f4025y = GLES20.glGetUniformLocation(m4403f(), "strength");
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
        Bitmap bitmap = this.f4019s;
        if (bitmap != null && !bitmap.isRecycled()) {
            m4053B(this.f4019s);
        }
        m4054C(this.f4026z);
    }

    public CLBlendModesFilter(Bitmap bitmap, BlendMode blendMode, Rotation rotation, boolean z8, boolean z9) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
        this.f4018r = -1;
        this.f4019s = null;
        this.f4026z = 100.0f;
        if (blendMode == BlendMode.NORMAL) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, light, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else if (blendMode == BlendMode.SCREEN) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     vec3 blend = vec3(1.0) - (vec3(1.0) - source) * (vec3(1.0) - light);     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, blend, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else if (blendMode == BlendMode.MULTIPLY) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     vec3 blend = source * light;     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, blend, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else if (blendMode == BlendMode.HARDLIGHT) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     vec3 multiply = source * (vec3(2.0) * light);     vec3 screen = vec3(1.0) - vec3(2.0) * (vec3(1.0) - source) * (vec3(1.0) - light);     vec3 step_result = step(vec3(0.5), light);     vec3 blend = (vec3(1.0) - step_result) * multiply + step_result * screen;     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, blend, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else if (blendMode == BlendMode.OVERLAY) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     vec3 multiply = source * (vec3(2.0) * light);     vec3 screen = vec3(1.0) - vec3(2.0) * (vec3(1.0) - source) * (vec3(1.0) - light);     vec3 step_result = step(vec3(0.5), source);     vec3 blend = (vec3(1.0) - step_result) * multiply + step_result * screen;     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, blend, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else if (blendMode == BlendMode.SOFTLIGHT) {
            super.m4414w("attribute vec4 position;attribute vec4 inputTextureCoordinate;attribute vec4 inputBlendTextureCoordinate;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;void main(){    gl_Position = position;    textureCoordinate = inputTextureCoordinate.xy;    blendTextureCoordinate = inputBlendTextureCoordinate.xy;}", "precision mediump float;varying vec2 textureCoordinate;varying vec2 blendTextureCoordinate;uniform sampler2D inputImageTexture;uniform sampler2D blending_texture;uniform float strength;void main(){     vec3 source = texture2D(inputImageTexture, textureCoordinate).rgb;     vec3 light = texture2D(blending_texture, blendTextureCoordinate).rgb;     vec3 multiply = source * (vec3(2.0) * light) + (source * source) * (vec3(1.0) - vec3(2.0) * light);     vec3 screen = (vec3(2.0) * source) * (vec3(1.0) - light) + sqrt(source) * (vec3(2.0) * light - vec3(1.0));     vec3 step_result = step(vec3(0.5), light);     vec3 blend = (vec3(1.0) - step_result) * multiply + step_result * screen;     float weight = texture2D(blending_texture, blendTextureCoordinate).a * strength;     vec3 color = mix(source, blend, weight);     gl_FragColor = vec4(color, 1.0);}");
        } else {
            Log.v("CLOverlayBlend Log", "Invalid blend_mode" + blendMode);
        }
        this.f4019s = bitmap;
        this.f4020t = bitmap.getWidth();
        this.f4021u = bitmap.getHeight();
        float[] fArr = f4012A;
        this.f4015o = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f4016p = fArr;
        m4055D(rotation, z8, z9);
    }
}
