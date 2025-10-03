package com.cyberlink.clgpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.view.Display;
import android.view.WindowManager;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGL10;

/* loaded from: classes.dex */
public class GPUImage {

    /* renamed from: a */
    public final Context f4208a;

    /* renamed from: b */
    public C0895h1 f4209b;

    /* renamed from: c */
    public GLSurfaceView f4210c;

    /* renamed from: d */
    public C0936w0 f4211d;

    /* renamed from: e */
    public Bitmap f4212e;

    /* renamed from: f */
    public ScaleType f4213f = ScaleType.CENTER_CROP;

    /* renamed from: g */
    public int f4214g = 0;

    /* renamed from: h */
    public int f4215h = 0;

    /* renamed from: i */
    public int f4216i;

    public enum ScaleType {
        CENTER_INSIDE,
        CENTER_CROP,
        CENTER_INSIDE_CAMERA,
        SQUARE_CROP_INSIDE_CAMERA,
        CENTER_INSIDE_CAMERA_YUV_BUFFER,
        AS_DISAPLY,
        MANUALLY;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static ScaleType[] valuesCustom() {
            ScaleType[] scaleTypeArrValuesCustom = values();
            int length = scaleTypeArrValuesCustom.length;
            ScaleType[] scaleTypeArr = new ScaleType[length];
            System.arraycopy(scaleTypeArrValuesCustom, 0, scaleTypeArr, 0, length);
            return scaleTypeArr;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.GPUImage$a */
    public abstract class AbstractAsyncTaskC0869a extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a */
        public final GPUImage f4225a;

        /* renamed from: b */
        public int f4226b;

        /* renamed from: c */
        public int f4227c;

        public AbstractAsyncTaskC0869a(GPUImage gPUImage) {
            this.f4225a = gPUImage;
        }

        /* renamed from: a */
        public final boolean m4161a(boolean z8, boolean z9) {
            return GPUImage.this.f4213f == ScaleType.CENTER_CROP ? z8 && z9 : z8 || z9;
        }

        /* renamed from: b */
        public abstract Bitmap mo4162b(BitmapFactory.Options options);

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            if (GPUImage.this.f4209b != null && GPUImage.this.f4209b.m4317A() == 0) {
                try {
                    synchronized (GPUImage.this.f4209b.f4630f) {
                        GPUImage.this.f4209b.f4630f.wait(3000L);
                    }
                } catch (InterruptedException e9) {
                    e9.printStackTrace();
                }
            }
            this.f4226b = GPUImage.this.m4151m();
            this.f4227c = GPUImage.this.m4150l();
            return m4166f();
        }

        /* renamed from: d */
        public abstract int mo4164d();

        /* renamed from: e */
        public final int[] m4165e(int i9, int i10) {
            float f9;
            float f10;
            float f11 = i9;
            float f12 = f11 / this.f4226b;
            float f13 = i10;
            float f14 = f13 / this.f4227c;
            if (GPUImage.this.f4213f != ScaleType.CENTER_CROP ? f12 < f14 : f12 > f14) {
                f10 = this.f4227c;
                f9 = (f10 / f13) * f11;
            } else {
                float f15 = this.f4226b;
                float f16 = (f15 / f11) * f13;
                f9 = f15;
                f10 = f16;
            }
            return new int[]{Math.round(f9), Math.round(f10)};
        }

        /* renamed from: f */
        public final Bitmap m4166f() {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            mo4162b(options);
            int i9 = 1;
            while (true) {
                if (!m4161a(options.outWidth / i9 > this.f4226b, options.outHeight / i9 > this.f4227c)) {
                    break;
                }
                i9++;
            }
            int i10 = i9 - 1;
            if (i10 < 1) {
                i10 = 1;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i10;
            options2.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options2.inPurgeable = true;
            options2.inTempStorage = new byte[32768];
            Bitmap bitmapMo4162b = mo4162b(options2);
            if (bitmapMo4162b == null) {
                return null;
            }
            return m4169i(m4168h(bitmapMo4162b));
        }

        @Override // android.os.AsyncTask
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.f4225a.m4145f();
            this.f4225a.m4156r(bitmap);
        }

        /* renamed from: h */
        public final Bitmap m4168h(Bitmap bitmap) {
            Bitmap bitmapCreateBitmap;
            IOException e9;
            int iMo4164d;
            if (bitmap == null) {
                return null;
            }
            try {
                iMo4164d = mo4164d();
            } catch (IOException e10) {
                bitmapCreateBitmap = bitmap;
                e9 = e10;
            }
            if (iMo4164d == 0) {
                return bitmap;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(iMo4164d);
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            try {
                bitmap.recycle();
            } catch (IOException e11) {
                e9 = e11;
                e9.printStackTrace();
                return bitmapCreateBitmap;
            }
            return bitmapCreateBitmap;
        }

        /* renamed from: i */
        public final Bitmap m4169i(Bitmap bitmap) {
            int[] iArrM4165e = m4165e(bitmap.getWidth(), bitmap.getHeight());
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, iArrM4165e[0], iArrM4165e[1], true);
            if (bitmapCreateScaledBitmap != bitmap) {
                bitmap.recycle();
                System.gc();
                bitmap = bitmapCreateScaledBitmap;
            }
            if (GPUImage.this.f4213f != ScaleType.CENTER_CROP) {
                return bitmap;
            }
            int i9 = iArrM4165e[0];
            int i10 = i9 - this.f4226b;
            int i11 = iArrM4165e[1];
            int i12 = i11 - this.f4227c;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i10 / 2, i12 / 2, i9 - i10, i11 - i12);
            if (bitmapCreateBitmap == bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return bitmapCreateBitmap;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.GPUImage$b */
    public class AsyncTaskC0870b extends AbstractAsyncTaskC0869a {

        /* renamed from: e */
        public final Uri f4229e;

        public AsyncTaskC0870b(GPUImage gPUImage, Uri uri) {
            super(gPUImage);
            this.f4229e = uri;
        }

        @Override // com.cyberlink.clgpuimage.GPUImage.AbstractAsyncTaskC0869a
        /* renamed from: b */
        public Bitmap mo4162b(BitmapFactory.Options options) {
            try {
                return BitmapFactory.decodeStream((this.f4229e.getScheme().startsWith("http") || this.f4229e.getScheme().startsWith("https")) ? new URL(this.f4229e.toString()).openStream() : GPUImage.this.f4208a.getContentResolver().openInputStream(this.f4229e), null, options);
            } catch (Exception e9) {
                e9.printStackTrace();
                return null;
            }
        }

        @Override // com.cyberlink.clgpuimage.GPUImage.AbstractAsyncTaskC0869a
        /* renamed from: d */
        public int mo4164d() {
            Cursor cursorQuery = GPUImage.this.f4208a.getContentResolver().query(this.f4229e, new String[]{"orientation"}, null, null, null);
            if (cursorQuery == null || cursorQuery.getCount() != 1) {
                return 0;
            }
            cursorQuery.moveToFirst();
            int i9 = cursorQuery.getInt(0);
            cursorQuery.close();
            return i9;
        }
    }

    /* renamed from: com.cyberlink.clgpuimage.GPUImage$c */
    public class C0871c {

        /* renamed from: a */
        public final C0928s1 f4231a;

        /* renamed from: b */
        public final Rect f4232b;

        /* renamed from: c */
        public final Rect f4233c;

        public C0871c(C0928s1 c0928s1, Rect rect, Rect rect2) {
            this.f4231a = c0928s1;
            this.f4232b = rect;
            this.f4233c = rect2;
        }
    }

    public GPUImage(Context context) {
        this.f4216i = 0;
        if (!m4160v(context)) {
            throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
        }
        this.f4208a = context;
        this.f4211d = new C0936w0();
        this.f4209b = new C0895h1(this.f4211d);
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        this.f4216i = iArr[0];
    }

    /* renamed from: h */
    public static Bitmap m4144h(Bitmap bitmap, C0936w0 c0936w0) {
        if (c0936w0 == null) {
            return null;
        }
        C0895h1 c0895h1 = new C0895h1(c0936w0);
        c0895h1.m4324H(bitmap, false);
        C0928s1 c0928s1 = new C0928s1(bitmap.getWidth(), bitmap.getHeight(), EGL10.EGL_NO_CONTEXT);
        c0928s1.m4394f(c0895h1);
        c0895h1.m4322F(c0936w0);
        Bitmap bitmapM4393e = c0928s1.m4393e();
        c0936w0.m4400c();
        c0895h1.m4334u();
        c0928s1.m4392d();
        return bitmapM4393e;
    }

    /* renamed from: f */
    public void m4145f() {
        this.f4209b.m4334u();
        this.f4212e = null;
        m4153o();
    }

    /* renamed from: g */
    public void m4146g(boolean z8) {
        this.f4209b.m4334u();
        this.f4212e = null;
        if (z8) {
            return;
        }
        m4153o();
    }

    /* renamed from: i */
    public Bitmap m4147i() {
        Bitmap bitmapM4148j = m4148j(this.f4212e);
        if (this.f4212e.isRecycled()) {
            this.f4212e = null;
        }
        return bitmapM4148j;
    }

    /* renamed from: j */
    public Bitmap m4148j(Bitmap bitmap) {
        Bitmap bitmapM4393e;
        int i9;
        int i10;
        if (this.f4216i == 0 || (bitmap.getWidth() <= this.f4216i && bitmap.getHeight() <= this.f4216i)) {
            C0895h1 c0895h1 = new C0895h1(this.f4211d);
            c0895h1.m4328L(Rotation.NORMAL, this.f4209b.m4318B(), this.f4209b.m4319C());
            c0895h1.m4329M(this.f4213f);
            C0928s1 c0928s1 = new C0928s1(bitmap.getWidth(), bitmap.getHeight(), this.f4209b.m4338y());
            c0928s1.m4394f(c0895h1);
            c0895h1.m4324H(bitmap, false);
            bitmapM4393e = c0928s1.m4393e();
            this.f4211d.m4400c();
            c0895h1.m4334u();
            c0928s1.m4392d();
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            ArrayList arrayList = new ArrayList();
            int i11 = this.f4216i;
            do {
                i11 /= 2;
                i9 = i11 + 512;
                i10 = this.f4216i;
            } while (i9 > i10);
            do {
                i10 /= 2;
            } while (i10 + 512 > this.f4216i);
            for (int i12 = 0; i12 < height; i12 += i10) {
                int i13 = 0;
                while (i13 < width) {
                    int i14 = i13 - 256;
                    int i15 = i12 - 256;
                    int i16 = i14 + i11 + 512;
                    int i17 = i15 + i10 + 512;
                    if (i14 < 0) {
                        i16 -= i14;
                        i14 = 0;
                    }
                    if (i15 < 0) {
                        i17 -= i15;
                        i15 = 0;
                    }
                    if (i16 > width) {
                        i14 -= i16 - width;
                        i16 = width;
                    }
                    if (i17 > height) {
                        i15 -= i17 - height;
                        i17 = height;
                    }
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i14, i15, i16 - i14, i17 - i15);
                    C0895h1 c0895h12 = new C0895h1(this.f4211d);
                    c0895h12.m4328L(Rotation.NORMAL, this.f4209b.m4318B(), this.f4209b.m4319C());
                    c0895h12.m4329M(this.f4213f);
                    ArrayList arrayList2 = arrayList;
                    C0928s1 c0928s12 = new C0928s1(bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), this.f4209b.m4338y());
                    c0928s12.m4394f(c0895h12);
                    c0895h12.m4324H(bitmapCreateBitmap, false);
                    c0928s12.m4395g();
                    c0895h12.m4334u();
                    if (bitmapCreateBitmap != bitmap) {
                        bitmapCreateBitmap.recycle();
                    }
                    Rect rect = new Rect(i13 - i14, i12 - i15, c0928s12.f4767b, c0928s12.f4768c);
                    rect.right = rect.left + Math.min(i11, c0928s12.f4767b);
                    rect.bottom = rect.top + Math.min(i10, c0928s12.f4768c);
                    Rect rect2 = new Rect(i13, i12, 0, 0);
                    i13 += i11;
                    if (i13 < width) {
                        rect2.right = i13;
                    } else {
                        rect2.right = width;
                    }
                    int i18 = i12 + i10;
                    if (i18 < height) {
                        rect2.bottom = i18;
                    } else {
                        rect2.bottom = height;
                    }
                    arrayList2.add(new C0871c(c0928s12, rect, rect2));
                    arrayList = arrayList2;
                }
            }
            bitmap.recycle();
            bitmapM4393e = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C0871c c0871c = (C0871c) it.next();
                c0871c.f4231a.m4391c(c0871c.f4232b, c0871c.f4233c, bitmapM4393e);
                c0871c.f4231a.m4392d();
            }
            this.f4211d.m4400c();
        }
        this.f4209b.m4322F(this.f4211d);
        Bitmap bitmap2 = this.f4212e;
        if (bitmap2 != null) {
            this.f4209b.m4324H(bitmap2, false);
        }
        m4153o();
        return bitmapM4393e;
    }

    /* renamed from: k */
    public Bitmap m4149k() {
        return this.f4212e;
    }

    /* renamed from: l */
    public final int m4150l() {
        C0895h1 c0895h1 = this.f4209b;
        if (c0895h1 != null && c0895h1.m4339z() != 0) {
            return this.f4209b.m4339z();
        }
        Bitmap bitmap = this.f4212e;
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        Display defaultDisplay = ((WindowManager) this.f4208a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.y;
    }

    /* renamed from: m */
    public final int m4151m() {
        C0895h1 c0895h1 = this.f4209b;
        if (c0895h1 != null && c0895h1.m4317A() != 0) {
            return this.f4209b.m4317A();
        }
        Bitmap bitmap = this.f4212e;
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        Display defaultDisplay = ((WindowManager) this.f4208a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }

    /* renamed from: n */
    public C0895h1 m4152n() {
        return this.f4209b;
    }

    /* renamed from: o */
    public void m4153o() {
        GLSurfaceView gLSurfaceView = this.f4210c;
        if (gLSurfaceView != null) {
            gLSurfaceView.requestRender();
        }
    }

    /* renamed from: p */
    public void m4154p(C0936w0 c0936w0) {
        this.f4211d = c0936w0;
        this.f4209b.m4322F(c0936w0);
        m4153o();
    }

    /* renamed from: q */
    public void m4155q(GLSurfaceView gLSurfaceView) {
        this.f4210c = gLSurfaceView;
        gLSurfaceView.setEGLContextClientVersion(2);
        this.f4210c.setRenderer(this.f4209b);
        this.f4210c.setRenderMode(0);
        this.f4210c.requestRender();
        this.f4209b.m4323G(this.f4210c);
    }

    /* renamed from: r */
    public void m4156r(Bitmap bitmap) {
        this.f4212e = bitmap;
        this.f4209b.m4324H(bitmap, false);
        m4153o();
    }

    /* renamed from: s */
    public void m4157s(Uri uri) {
        new AsyncTaskC0870b(this, uri).execute(new Void[0]);
    }

    /* renamed from: t */
    public void m4158t(Rotation rotation, boolean z8, boolean z9) {
        this.f4209b.m4328L(rotation, z9, z8);
    }

    /* renamed from: u */
    public void m4159u(ScaleType scaleType) {
        this.f4213f = scaleType;
        this.f4209b.m4329M(scaleType);
        this.f4209b.m4334u();
        this.f4212e = null;
        m4153o();
    }

    /* renamed from: v */
    public final boolean m4160v(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }
}
