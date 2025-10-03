package com.cyberlink.clgpuimage;

import android.opengl.GLES20;

/* renamed from: com.cyberlink.clgpuimage.d */
/* loaded from: classes.dex */
public class C0881d extends C0936w0 {

    /* renamed from: n */
    public int f4458n;

    /* renamed from: o */
    public int f4459o;

    /* renamed from: p */
    public int f4460p;

    /* renamed from: q */
    public int f4461q;

    /* renamed from: com.cyberlink.clgpuimage.d$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLES20.glUniform1f(C0881d.this.f4460p, r0.f4458n);
            GLES20.glUniform1f(C0881d.this.f4461q, r0.f4459o);
        }
    }

    public C0881d() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform highp float input_width;\nuniform highp float input_height;\n\nvarying highp vec2 input_coordinate_1;\nvarying highp vec2 input_coordinate_2;\nvarying highp vec2 input_coordinate_3;\nvarying highp vec2 input_coordinate_4;\nvarying highp vec4 input_weights;\n\nvoid main()\n{\n    gl_Position = position;\n    \n    // Compute the 4 closest pixel coordinates for the input texture.\n    highp float input_x = inputTextureCoordinate.x * input_width - 0.5;\n    highp float input_y = inputTextureCoordinate.y * input_height - 0.5;\n    highp float floor_x = floor(input_x);\n    highp float floor_y = floor(input_y);\n    highp float unit_x = 1.0 / input_width;\n    highp float unit_y = 1.0 / input_height;\n    \n    input_coordinate_1.x = (floor_x + 0.5) * unit_x;\n    input_coordinate_1.y = (floor_y + 0.5) * unit_y;\n    input_coordinate_2.x = input_coordinate_1.x + unit_x;\n    input_coordinate_2.y = input_coordinate_1.y;\n    input_coordinate_3.x = input_coordinate_1.x;\n    input_coordinate_3.y = input_coordinate_1.y + unit_y;\n    input_coordinate_4.x = input_coordinate_2.x;\n    input_coordinate_4.y = input_coordinate_3.y;\n    \n    highp float frac_x = input_x - floor_x;\n    highp float frac_y = input_y - floor_y;\n    input_weights.x = (1.0 - frac_x) * (1.0 - frac_y);\n    input_weights.y =        frac_x  * (1.0 - frac_y);\n    input_weights.z = (1.0 - frac_x) *        frac_y ;\n    input_weights.w =        frac_x  *        frac_y ;\n    \n}\n", "precision mediump float;\n\nuniform sampler2D inputImageTexture;\nconst float low_threshold = 0.60;\nconst float high_threshold = 0.90;\nconst float max_weight_threshold = 0.85;\n#ifdef GL_FRAGMENT_PRECISION_HIGH\nvarying highp vec2 input_coordinate_1;\nvarying highp vec2 input_coordinate_2;\nvarying highp vec2 input_coordinate_3;\nvarying highp vec2 input_coordinate_4;\nvarying highp vec4 input_weights;\n#else\nvarying vec2 input_coordinate_1;\nvarying vec2 input_coordinate_2;\nvarying vec2 input_coordinate_3;\nvarying vec2 input_coordinate_4;\nvarying vec4 input_weights;\n#endif\n\nvoid main()\n{\n#ifdef GL_FRAGMENT_PRECISION_HIGH\n    highp vec4 sample_color;\n    highp vec4 sum_color = vec4(0.0);\n    highp vec3 max_color = vec3(0.0);\n#else\n    vec4 sample_color;\n    vec4 sum_color = vec4(0.0);\n    vec3 max_color = vec3(0.0);\n#endif\n    sample_color = texture2D(inputImageTexture, input_coordinate_1);\n    sum_color += sample_color * input_weights.x;\n    max_color = max(max_color, sample_color.rgb);\n    sample_color = texture2D(inputImageTexture, input_coordinate_2);\n    sum_color += sample_color * input_weights.y;\n    max_color = max(max_color, sample_color.rgb);\n    sample_color = texture2D(inputImageTexture, input_coordinate_3);\n    sum_color += sample_color * input_weights.z;\n    max_color = max(max_color, sample_color.rgb);\n    sample_color = texture2D(inputImageTexture, input_coordinate_4);\n    sum_color += sample_color * input_weights.w;\n    max_color = max(max_color, sample_color.rgb);\n    \n    vec3 weight = (max_color - vec3(low_threshold)) / (high_threshold - low_threshold);\n    weight = max(weight, vec3(0.0));\n    weight = min(weight, vec3(1.0));\n    weight *= max_weight_threshold;\n    \n    gl_FragColor.rgb = max_color * weight + sum_color.rgb * (1.0 - weight);\n    gl_FragColor.a = sum_color.a;\n}");
        this.f4458n = 640;
        this.f4459o = 640;
    }

    /* renamed from: A */
    public void m4241A(int i9, int i10) {
        this.f4458n = i9;
        this.f4459o = i10;
        m4242z();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4460p = GLES20.glGetUniformLocation(m4403f(), "input_width");
        this.f4461q = GLES20.glGetUniformLocation(m4403f(), "input_height");
        m4242z();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: m */
    public void mo4048m() {
        super.mo4048m();
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: n */
    public void mo4049n(int i9, int i10) {
        super.mo4049n(i9, i10);
    }

    /* renamed from: z */
    public final void m4242z() {
        m4406o(new a());
    }
}
