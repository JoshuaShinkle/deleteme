package com.cyberlink.clgpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import p253z1.C6816a;

/* renamed from: com.cyberlink.clgpuimage.m1 */
/* loaded from: classes.dex */
public class C0910m1 extends C0936w0 {

    /* renamed from: n */
    public int f4701n;

    /* renamed from: o */
    public int f4702o;

    /* renamed from: p */
    public int f4703p;

    /* renamed from: q */
    public ByteBuffer f4704q;

    /* renamed from: r */
    public Bitmap f4705r;

    /* renamed from: com.cyberlink.clgpuimage.m1$a */
    public class a implements Runnable {

        /* renamed from: c */
        public final /* synthetic */ Bitmap f4707c;

        public a(Bitmap bitmap) {
            this.f4707c = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmap;
            if (C0910m1.this.f4703p != -1 || (bitmap = this.f4707c) == null || bitmap.isRecycled()) {
                return;
            }
            GLES20.glActiveTexture(33987);
            C0910m1.this.f4703p = C0925r1.m4379e(this.f4707c, -1, false);
        }
    }

    public C0910m1(String str) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}", str);
    }

    /* renamed from: A */
    public void m4358A(Rotation rotation, boolean z8, boolean z9) {
        float[] fArrM25387c = C6816a.m25387c(rotation, z8, z9);
        ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferOrder.asFloatBuffer();
        floatBufferAsFloatBuffer.put(fArrM25387c);
        floatBufferAsFloatBuffer.flip();
        this.f4704q = byteBufferOrder;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: i */
    public void mo4045i() {
        super.mo4045i();
        GLES20.glDeleteTextures(1, new int[]{this.f4703p}, 0);
        this.f4703p = -1;
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: k */
    public void mo4057k() {
        GLES20.glEnableVertexAttribArray(this.f4701n);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, this.f4703p);
        GLES20.glUniform1i(this.f4702o, 3);
        this.f4704q.position(0);
        GLES20.glVertexAttribPointer(this.f4701n, 2, 5126, false, 0, (Buffer) this.f4704q);
    }

    @Override // com.cyberlink.clgpuimage.C0936w0
    /* renamed from: l */
    public void mo4047l() {
        super.mo4047l();
        this.f4701n = GLES20.glGetAttribLocation(m4403f(), "inputTextureCoordinate2");
        this.f4702o = GLES20.glGetUniformLocation(m4403f(), "inputImageTexture2");
        GLES20.glEnableVertexAttribArray(this.f4701n);
        Bitmap bitmap = this.f4705r;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        mo4359z(this.f4705r);
    }

    /* renamed from: z */
    public void mo4359z(Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.f4705r = bitmap;
            if (bitmap == null) {
                return;
            }
            m4406o(new a(bitmap));
        }
    }

    public C0910m1(String str, String str2) {
        super(str, str2);
        this.f4703p = -1;
        m4358A(Rotation.NORMAL, false, false);
    }
}
