package com.cyberlink.you.kernelctrl.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.cyberlink.clgpuimage.C0895h1;
import com.cyberlink.clgpuimage.C0913n1;
import com.cyberlink.clgpuimage.GPUImage;
import com.cyberlink.clgpuimage.Rotation;
import com.cyberlink.you.kernelctrl.dataeditcenter.DevelopSetting;
import com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine;
import com.cyberlink.you.kernelctrl.gpuimage.C3097b;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p056e4.C4754a;

/* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b */
/* loaded from: classes.dex */
public class C3097b extends FrameLayout {

    /* renamed from: b */
    public Context f14157b;

    /* renamed from: c */
    public int f14158c;

    /* renamed from: d */
    public int f14159d;

    /* renamed from: e */
    public final ExecutorService f14160e;

    /* renamed from: f */
    public d f14161f;

    /* renamed from: g */
    public c f14162g;

    /* renamed from: h */
    public ImageView f14163h;

    /* renamed from: i */
    public String f14164i;

    /* renamed from: j */
    public AsyncTask<Void, Void, Void> f14165j;

    /* renamed from: k */
    public boolean f14166k;

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b$a */
    public class a implements GLViewEngine.InterfaceC3075c<Void> {
        public a() {
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: b */
        public void mo15552b(Object obj, String str) {
            Log.d("GPUImageViewer", "updateGPUImageView onCancel");
        }

        @Override // com.cyberlink.you.kernelctrl.glviewengine.GLViewEngine.InterfaceC3075c
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo15551a(Object obj, Void r22) {
            Log.d("GPUImageViewer", "updateGPUImageView onComplete");
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b$b */
    public class b extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ int f14168a;

        /* renamed from: b */
        public final /* synthetic */ String f14169b;

        /* renamed from: c */
        public final /* synthetic */ Uri f14170c;

        /* renamed from: d */
        public final /* synthetic */ float f14171d;

        /* renamed from: e */
        public final /* synthetic */ DevelopSetting f14172e;

        /* renamed from: f */
        public final /* synthetic */ DevelopSetting f14173f;

        /* renamed from: g */
        public final /* synthetic */ DevelopSetting f14174g;

        /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b$b$a */
        public class a implements C0895h1.f {

            /* renamed from: a */
            public final /* synthetic */ Handler f14176a;

            /* renamed from: b */
            public final /* synthetic */ c f14177b;

            /* renamed from: c */
            public final /* synthetic */ Animation f14178c;

            /* renamed from: d */
            public final /* synthetic */ ImageView f14179d;

            public a(Handler handler, c cVar, Animation animation, ImageView imageView) {
                this.f14176a = handler;
                this.f14177b = cVar;
                this.f14178c = animation;
                this.f14179d = imageView;
            }

            /* renamed from: e */
            public static /* synthetic */ void m16110e() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: f */
            public /* synthetic */ void m16111f(c cVar, Animation animation, ImageView imageView) {
                if (cVar != null) {
                    cVar.setAlpha(1.0f);
                    if (!animation.hasEnded()) {
                        animation.cancel();
                    }
                    C3097b.this.removeView(imageView);
                    C3097b.this.setEnabled(true);
                }
            }

            @Override // com.cyberlink.clgpuimage.C0895h1.f
            /* renamed from: a */
            public void mo4340a() {
                Handler handler = this.f14176a;
                final c cVar = this.f14177b;
                final Animation animation = this.f14178c;
                final ImageView imageView = this.f14179d;
                handler.post(new Runnable() { // from class: x3.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22094b.m16111f(cVar, animation, imageView);
                    }
                });
                this.f14177b.getRender().m4325I(null);
            }

            @Override // com.cyberlink.clgpuimage.C0895h1.f
            /* renamed from: b */
            public void mo4341b() {
                this.f14176a.post(new Runnable() { // from class: x3.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        C3097b.b.a.m16110e();
                    }
                });
            }
        }

        public b(int i9, String str, Uri uri, float f9, DevelopSetting developSetting, DevelopSetting developSetting2, DevelopSetting developSetting3) {
            this.f14168a = i9;
            this.f14169b = str;
            this.f14170c = uri;
            this.f14171d = f9;
            this.f14172e = developSetting;
            this.f14173f = developSetting2;
            this.f14174g = developSetting3;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws IOException {
            Bitmap bitmapM16493U;
            Log.d("GPUImageViewer", "doInBackground");
            if (isCancelled()) {
                return null;
            }
            if (C3097b.this.f14161f.f14199r == null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inSampleSize = this.f14168a;
                try {
                    bitmapM16493U = CLUtility.m16493U(this.f14169b, this.f14170c, options);
                } catch (OutOfMemoryError e9) {
                    if (e9.getMessage() != null) {
                        Log.e("GPUImageViewer", e9.getMessage());
                    }
                    bitmapM16493U = null;
                }
                if (bitmapM16493U == null) {
                    return null;
                }
                float fM16436F0 = CLUtility.m16436F0(options.outWidth, options.outHeight, 1280) * this.f14171d;
                Matrix matrix = new Matrix();
                matrix.postScale(fM16436F0, fM16436F0);
                try {
                    matrix.postRotate(C4754a.m18873c(this.f14169b, this.f14170c));
                } catch (IOException e10) {
                    Log.d("GPUImageViewer", Log.getStackTraceString(e10));
                }
                try {
                    C3097b.this.f14161f.f14199r = Bitmap.createBitmap(bitmapM16493U, 0, 0, bitmapM16493U.getWidth(), bitmapM16493U.getHeight(), matrix, true);
                } catch (OutOfMemoryError e11) {
                    if (e11.getMessage() != null) {
                        Log.e("GPUImageViewer", e11.getMessage());
                    }
                }
            }
            isCancelled();
            return null;
        }

        /* renamed from: b */
        public final void m16106b() {
            C3097b c3097b = C3097b.this;
            if (c3097b.f14162g != null || c3097b.f14161f.f14199r == null) {
                return;
            }
            Log.d("GPUImageViewer", "mGPUImageView == null, init once.");
            C3097b c3097b2 = C3097b.this;
            C3097b c3097b3 = C3097b.this;
            c3097b2.f14162g = c3097b3.new c(c3097b3.f14157b);
            C3097b.this.f14162g.setScaleType(GPUImage.ScaleType.CENTER_INSIDE);
            AlphaAnimation alphaAnimation = new AlphaAnimation(BitmapDescriptorFactory.HUE_RED, 1.0f);
            alphaAnimation.setDuration(500L);
            ImageView imageView = new ImageView(C3097b.this.f14157b);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAnimation(alphaAnimation);
            imageView.setImageBitmap(C3097b.this.f14161f.f14199r);
            C3097b c3097b4 = C3097b.this;
            if (c3097b4.f14163h == null) {
                c3097b4.f14163h = new ImageView(C3097b.this.f14157b);
                C3097b.this.f14163h.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            a aVar = new a(new Handler(), C3097b.this.f14162g, alphaAnimation, imageView);
            C3097b.this.f14162g.setAlpha(BitmapDescriptorFactory.HUE_RED);
            C3097b c3097b5 = C3097b.this;
            c cVar = c3097b5.f14162g;
            d dVar = c3097b5.f14161f;
            cVar.m16112c(dVar.f14200s, dVar.f14201t, dVar.f14202u);
            C3097b.this.f14162g.getRender().m4325I(aVar);
            C3097b c3097b6 = C3097b.this;
            c3097b6.f14162g.setImage(c3097b6.f14161f.f14199r);
            C3097b.this.removeAllViews();
            FrameLayout.LayoutParams layoutParamsMo16061g = C3097b.this.mo16061g(false);
            if (layoutParamsMo16061g != null) {
                C3097b c3097b7 = C3097b.this;
                c3097b7.addView(c3097b7.f14162g, layoutParamsMo16061g);
            } else {
                C3097b c3097b8 = C3097b.this;
                c3097b8.addView(c3097b8.f14162g);
            }
            C3097b c3097b9 = C3097b.this;
            c3097b9.addView(imageView, c3097b9.mo16063h(false));
            C3097b c3097b10 = C3097b.this;
            c3097b10.addView(c3097b10.f14163h, c3097b10.mo16063h(false));
            C3097b.this.setEnabled(false);
            alphaAnimation.startNow();
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            if (isCancelled()) {
                return;
            }
            m16106b();
            C3097b c3097b = C3097b.this;
            DevelopSetting developSetting = this.f14172e;
            DevelopSetting developSetting2 = this.f14173f;
            DevelopSetting developSetting3 = this.f14174g;
            d dVar = c3097b.f14161f;
            c3097b.mo16074q(developSetting, developSetting2, developSetting3, dVar.f14196o, dVar.f14197p, dVar.f14198q, dVar.f14203v);
            C3097b.this.getClass();
            C3097b.this.f14166k = true;
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b$c */
    public class c extends C0913n1 {
        public c(Context context) {
            super(context);
        }

        /* renamed from: c */
        public void m16112c(Rotation rotation, boolean z8, boolean z9) {
            Log.d("GPUImageViewer", "setRotation(), Rotation = " + rotation.m4181b() + ", flipHorizontal = " + z8 + ", flipVertical = " + z9);
            this.f4710b.m4158t(rotation, z8, z9);
            requestRender();
        }
    }

    /* renamed from: com.cyberlink.you.kernelctrl.gpuimage.b$d */
    public class d {

        /* renamed from: a */
        public String f14182a = null;

        /* renamed from: b */
        public String f14183b = null;

        /* renamed from: c */
        public int f14184c = -1;

        /* renamed from: d */
        public int f14185d = -1;

        /* renamed from: e */
        public int f14186e = 0;

        /* renamed from: f */
        public int f14187f = -1;

        /* renamed from: g */
        public int f14188g = -1;

        /* renamed from: h */
        public DevelopSetting f14189h = null;

        /* renamed from: i */
        public DevelopSetting f14190i = null;

        /* renamed from: j */
        public DevelopSetting f14191j = null;

        /* renamed from: k */
        public boolean f14192k = false;

        /* renamed from: l */
        public float f14193l = -1.0f;

        /* renamed from: m */
        public float f14194m = -1.0f;

        /* renamed from: n */
        public float f14195n = -1.0f;

        /* renamed from: o */
        public double f14196o = 1.0d;

        /* renamed from: p */
        public double f14197p = 1.0d;

        /* renamed from: q */
        public double f14198q = 1.0d;

        /* renamed from: r */
        public Bitmap f14199r = null;

        /* renamed from: s */
        public Rotation f14200s = Rotation.NORMAL;

        /* renamed from: t */
        public boolean f14201t = false;

        /* renamed from: u */
        public boolean f14202u = false;

        /* renamed from: v */
        public GLViewEngine.EffectParam.ExtraFunc f14203v = GLViewEngine.EffectParam.ExtraFunc.None;

        public d() {
        }
    }

    public C3097b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14157b = null;
        this.f14158c = -1;
        this.f14159d = -1;
        this.f14160e = Executors.newSingleThreadExecutor();
        this.f14161f = new d();
        this.f14162g = null;
        this.f14163h = null;
        this.f14164i = null;
        this.f14165j = null;
        this.f14166k = false;
        this.f14157b = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m16092n(int i9, int i10, int i11, int i12) throws IOException {
        m16096f(i9, i10, i11, i12);
        m16098j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m16093o(String str) {
        ImageView imageView = this.f14163h;
        if (imageView != null) {
            if (str == null) {
                imageView.setImageResource(0);
            } else {
                imageView.setImageURI(Uri.fromFile(new File(str)));
            }
        }
    }

    /* renamed from: c */
    public void m16094c(String str) {
        Log.d("GPUImageViewer", str);
    }

    /* renamed from: e */
    public float m16095e(d dVar, boolean z8) {
        m16094c("[calculateMinScale]");
        return Math.min(this.f14158c / dVar.f14187f, this.f14159d / (z8 ? GLViewEngine.m15970g().m15974h(dVar.f14188g) : dVar.f14188g));
    }

    /* renamed from: f */
    public final void m16096f(int i9, int i10, int i11, int i12) {
        this.f14162g = null;
        this.f14158c = i9;
        this.f14159d = i10;
    }

    /* renamed from: g */
    public FrameLayout.LayoutParams mo16061g(boolean z8) {
        return new FrameLayout.LayoutParams(this.f14161f.f14199r.getWidth(), this.f14161f.f14199r.getHeight(), z8 ? 81 : 17);
    }

    public String getDoodleImagePath() {
        return this.f14164i;
    }

    public int getImageHeight() {
        d dVar = this.f14161f;
        if (dVar != null) {
            return dVar.f14188g;
        }
        return 0;
    }

    public int getImageWidth() {
        d dVar = this.f14161f;
        if (dVar != null) {
            return dVar.f14187f;
        }
        return 0;
    }

    public float getScale() {
        d dVar = this.f14161f;
        if (dVar != null) {
            return dVar.f14195n;
        }
        return 1.0f;
    }

    /* renamed from: h */
    public FrameLayout.LayoutParams mo16063h(boolean z8) {
        return new FrameLayout.LayoutParams(this.f14161f.f14199r.getWidth(), this.f14161f.f14199r.getHeight(), z8 ? 81 : 17);
    }

    /* renamed from: i */
    public void m16097i(String str, String str2, CLUtility.C3138e c3138e) throws IOException {
        int i9;
        CLUtility.C3138e c3138eM16420B0 = CLUtility.m16420B0(str, TextUtils.isEmpty(str2) ? null : Uri.parse(str2));
        int i10 = c3138eM16420B0.f14440b;
        if (i10 == -1 || (i9 = c3138eM16420B0.f14441c) == -1) {
            return;
        }
        Point pointM16579r = CLUtility.m16579r(i10, i9);
        int i11 = c3138eM16420B0.f14442d;
        if (i11 == 6 || i11 == 8) {
            c3138e.f14440b = pointM16579r.y;
            c3138e.f14441c = pointM16579r.x;
        } else {
            c3138e.f14440b = pointM16579r.x;
            c3138e.f14441c = pointM16579r.y;
        }
        c3138e.f14442d = 0;
        c3138e.f14439a = c3138eM16420B0.f14439a;
    }

    /* renamed from: j */
    public final void m16098j() throws IOException {
        float f9;
        Log.d("GPUImageViewer", String.format("initImage(), this.mBoundaryWidth = %d, this.mBoundaryHeight = %d", Integer.valueOf(this.f14158c), Integer.valueOf(this.f14159d)));
        if (this.f14161f.f14182a == null || this.f14158c <= 0 || this.f14159d <= 0) {
            return;
        }
        CLUtility.C3138e c3138e = new CLUtility.C3138e();
        d dVar = this.f14161f;
        m16097i(dVar.f14182a, dVar.f14183b, c3138e);
        int i9 = c3138e.f14440b;
        if (i9 > 1280 || c3138e.f14441c > 1280) {
            float f10 = 1280.0f / i9;
            int i10 = c3138e.f14441c;
            float f11 = 1280.0f / i10;
            if (f10 >= f11) {
                f10 = f11;
            }
            c3138e.f14440b = (int) (i9 * f10);
            c3138e.f14441c = (int) (i10 * f10);
            f9 = f10;
        } else {
            f9 = 1.0f;
        }
        mo16067k(this.f14161f, c3138e, false);
        d dVar2 = this.f14161f;
        DevelopSetting developSetting = dVar2.f14189h;
        DevelopSetting developSetting2 = dVar2.f14190i;
        DevelopSetting developSetting3 = dVar2.f14191j;
        String str = dVar2.f14182a;
        Uri uri = TextUtils.isEmpty(dVar2.f14183b) ? null : Uri.parse(this.f14161f.f14183b);
        int i11 = c3138e.f14439a;
        int i12 = c3138e.f14442d;
        if (i12 == 2) {
            this.f14161f.f14201t = true;
        } else if (i12 == 3) {
            this.f14161f.f14200s = Rotation.ROTATION_180;
        } else if (i12 == 4) {
            this.f14161f.f14202u = true;
        } else if (i12 == 6) {
            this.f14161f.f14200s = Rotation.ROTATION_90;
        } else if (i12 == 8) {
            this.f14161f.f14200s = Rotation.ROTATION_270;
        }
        AsyncTask<Void, Void, Void> asyncTask = this.f14165j;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        this.f14165j = new b(i11, str, uri, f9, developSetting, developSetting2, developSetting3).executeOnExecutor(this.f14160e, new Void[0]);
    }

    /* renamed from: k */
    public void mo16067k(d dVar, CLUtility.C3138e c3138e, boolean z8) {
        m16094c("[initImageInfo]");
        int i9 = c3138e.f14440b;
        int i10 = c3138e.f14441c;
        int i11 = c3138e.f14442d;
        dVar.f14184c = i9;
        dVar.f14185d = i10;
        dVar.f14186e = i11;
        if (i11 == 6 || i11 == 8) {
            dVar.f14187f = i10;
            dVar.f14188g = i9;
        } else {
            dVar.f14187f = i9;
            dVar.f14188g = i10;
        }
        m16094c("info imageWidth: " + dVar.f14184c + " imageHeight: " + dVar.f14185d);
        m16094c("info rotatedImageWidth: " + dVar.f14187f + " rotatedImageHeight: " + dVar.f14188g);
        float fM16095e = m16095e(dVar, z8);
        StringBuilder sb = new StringBuilder();
        sb.append("minScale: ");
        sb.append(fM16095e);
        m16094c(sb.toString());
        float fMax = Math.max(fM16095e, 4.0f);
        m16094c("maxScale: " + fMax);
        dVar.f14193l = fM16095e;
        dVar.f14194m = fMax;
        dVar.f14195n = fM16095e;
    }

    /* renamed from: l */
    public final boolean m16099l(String str, String str2) {
        String str3;
        String str4 = this.f14161f.f14182a;
        return (str4 != null && str4.equals(str)) || ((str3 = this.f14161f.f14183b) != null && str3.equals(str2));
    }

    /* renamed from: m */
    public boolean m16100m() {
        return this.f14166k;
    }

    @Override // android.view.View
    public void onSizeChanged(final int i9, final int i10, final int i11, final int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        if (i9 == i11 || i10 == i12) {
            return;
        }
        m16094c(String.format("onSizeChanged() w = %d, h = %d", Integer.valueOf(i9), Integer.valueOf(i10)));
        post(new Runnable() { // from class: x3.c
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                this.f22087b.m16092n(i9, i10, i11, i12);
            }
        });
    }

    /* renamed from: p */
    public void m16101p(String str, String str2, DevelopSetting developSetting, DevelopSetting developSetting2, DevelopSetting developSetting3, double d9, double d10, double d11, GLViewEngine.EffectParam.ExtraFunc extraFunc) throws IOException {
        String str3;
        Bitmap bitmap;
        String str4 = this.f14161f.f14182a;
        if (((str4 != null && !str4.equals(str)) || ((str3 = this.f14161f.f14183b) != null && !str3.equals(str2))) && (bitmap = this.f14161f.f14199r) != null) {
            bitmap.recycle();
            this.f14161f.f14199r = null;
        }
        d dVar = this.f14161f;
        dVar.f14189h = developSetting;
        dVar.f14190i = developSetting2;
        dVar.f14191j = developSetting3;
        dVar.f14196o = d9;
        dVar.f14197p = d10;
        dVar.f14198q = d11;
        dVar.f14203v = extraFunc;
        if (m16099l(str, str2)) {
            mo16074q(developSetting, developSetting2, developSetting3, d9, d10, d11, extraFunc);
            return;
        }
        d dVar2 = this.f14161f;
        dVar2.f14182a = str;
        dVar2.f14183b = str2;
        m16098j();
    }

    /* renamed from: q */
    public void mo16074q(DevelopSetting developSetting, DevelopSetting developSetting2, DevelopSetting developSetting3, double d9, double d10, double d11, GLViewEngine.EffectParam.ExtraFunc extraFunc) {
        d dVar = this.f14161f;
        Bitmap bitmap = dVar.f14199r;
        if (bitmap == null || ((dVar.f14189h == null && dVar.f14190i == null && dVar.f14191j == null) || bitmap.isRecycled())) {
            Log.e("GPUImageViewer", "updateGPUImageView failed");
            return;
        }
        d dVar2 = this.f14161f;
        GLViewEngine.EffectParam effectParam = new GLViewEngine.EffectParam(developSetting, d9, dVar2.f14199r, dVar2.f14200s, dVar2.f14201t, dVar2.f14202u, extraFunc);
        d dVar3 = this.f14161f;
        GLViewEngine.EffectParam effectParam2 = new GLViewEngine.EffectParam(developSetting2, d10, dVar3.f14199r, dVar3.f14200s, dVar3.f14201t, dVar3.f14202u, extraFunc);
        d dVar4 = this.f14161f;
        GLViewEngine.m15970g().m15972e(this.f14162g, this.f14161f.f14199r, effectParam, effectParam2, new GLViewEngine.EffectParam(developSetting3, d11, dVar4.f14199r, dVar4.f14200s, dVar4.f14201t, dVar4.f14202u, extraFunc), new a(), null);
    }

    /* renamed from: r */
    public void m16102r(DevelopSetting.EffectMode effectMode, double d9, double d10, double d11) {
        m16094c("updateStrength, vAll = " + d9 + ", vFore = " + d10 + ", vBack = " + d11);
        if (d9 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d9 > 1.0d) {
            throw new IllegalArgumentException();
        }
        if (d10 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d10 > 1.0d) {
            throw new IllegalArgumentException();
        }
        if (d11 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d11 > 1.0d) {
            throw new IllegalArgumentException();
        }
        d dVar = this.f14161f;
        dVar.f14196o = d9;
        dVar.f14197p = d10;
        dVar.f14198q = d11;
        DevelopSetting developSetting = dVar.f14189h;
        developSetting.f13894a = effectMode;
        DevelopSetting developSetting2 = dVar.f14190i;
        developSetting2.f13894a = effectMode;
        DevelopSetting developSetting3 = dVar.f14191j;
        developSetting3.f13894a = effectMode;
        mo16074q(developSetting, developSetting2, developSetting3, d9, d10, d11, dVar.f14203v);
    }

    /* renamed from: s */
    public void m16103s(DevelopSetting.EffectMode effectMode, double d9, double d10, double d11) {
        mo16074q(DevelopSetting.m15961a(), DevelopSetting.m15961a(), DevelopSetting.m15961a(), d9, d10, d11, GLViewEngine.EffectParam.ExtraFunc.None);
    }

    public void setDoodleImage(final String str) {
        this.f14164i = str;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: x3.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f22092b.m16093o(str);
            }
        });
    }
}
