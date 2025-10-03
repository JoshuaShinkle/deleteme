package p173q2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import java.io.File;
import p103j1.C5085r;
import p116k4.C5154j;
import p190s1.C6250g;
import p199t1.AbstractC6281f;
import p208u1.InterfaceC6362b;
import p209u2.AbstractC6381r;
import p209u2.C6383t;

/* renamed from: q2.j */
/* loaded from: classes.dex */
public final class C6136j {

    /* renamed from: q2.j$a */
    public class a extends AbstractC6281f<Bitmap> {

        /* renamed from: e */
        public final /* synthetic */ AbstractC6381r f20762e;

        public a(AbstractC6381r abstractC6381r) {
            this.f20762e = abstractC6381r;
        }

        @Override // p199t1.InterfaceC6283h
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo7183f(Bitmap bitmap, InterfaceC6362b<? super Bitmap> interfaceC6362b) {
            this.f20762e.m24506d(bitmap);
        }

        @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
        /* renamed from: e */
        public void mo7182e(Drawable drawable) {
            this.f20762e.m24507e();
        }
    }

    /* renamed from: a */
    public static Bitmap m23581a(Context context, int i9) {
        return m23582b(context, i9, -1);
    }

    /* renamed from: b */
    public static Bitmap m23582b(Context context, int i9, int i10) {
        return m23583c(context, i9, i10, i10);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: c */
    public static Bitmap m23583c(Context context, int i9, int i10, int i11) {
        if (C6143q.m23608a(context)) {
            return null;
        }
        C6134h<Bitmap> c6134hM23546E = C6132f.m23492b(context).mo23575d().mo23568r(Integer.valueOf(i9)).m23544C().m23546E(DownsampleStrategy.f3882e);
        if (i10 != -1 && i11 != -1) {
            c6134hM23546E.m23560S(i10, i11);
        }
        try {
            return c6134hM23546E.m24428w().get();
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: d */
    public static Bitmap m23584d(Context context, Uri uri, int i9) {
        return m23585e(context, uri, i9, i9);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: e */
    public static Bitmap m23585e(Context context, Uri uri, int i9, int i10) {
        if (C6143q.m23608a(context)) {
            return null;
        }
        C6134h<Bitmap> c6134hM23546E = C6132f.m23492b(context).mo23575d().m23553L(uri).m23544C().m23546E(DownsampleStrategy.f3882e);
        if (i9 != -1 && i10 != -1) {
            c6134hM23546E.m23560S(i9, i10);
        }
        try {
            return c6134hM23546E.m24428w().get();
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    /* renamed from: f */
    public static Bitmap m23586f(Context context, String str) {
        return m23587g(context, str, -1);
    }

    /* renamed from: g */
    public static Bitmap m23587g(Context context, String str, int i9) {
        return m23588h(context, str, i9, i9);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: h */
    public static Bitmap m23588h(Context context, String str, int i9, int i10) {
        if (C6143q.m23608a(context)) {
            return null;
        }
        C6134h<Bitmap> c6134hM23546E = C6132f.m23492b(context).mo23575d().mo23570t(str).m23544C().m23546E(DownsampleStrategy.f3882e);
        if (i9 != -1 && i10 != -1) {
            c6134hM23546E.m23560S(i9, i10);
        }
        try {
            return c6134hM23546E.m24428w().get();
        } catch (Exception e9) {
            C5154j.m20076j(e9);
            return null;
        }
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: i */
    public static void m23589i(Context context, String str, int i9, int i10, AbstractC6381r<Bitmap, Void> abstractC6381r) {
        if (context == null || str == null || abstractC6381r == null || C6143q.m23608a(context)) {
            return;
        }
        C6134h<Bitmap> c6134hM23546E = C6132f.m23492b(context).mo23575d().mo23570t(str).m23544C().m23546E(DownsampleStrategy.f3882e);
        if (i9 != -1 && i10 != -1) {
            c6134hM23546E.m23560S(i9, i10);
        }
        c6134hM23546E.m24419k(new a(abstractC6381r));
    }

    /* renamed from: j */
    public static void m23590j(Context context, String str, int i9, AbstractC6381r<Bitmap, Void> abstractC6381r) {
        m23589i(context, str, i9, i9, abstractC6381r);
    }

    /* renamed from: k */
    public static void m23591k(Context context, ImageView... imageViewArr) {
        if (C6143q.m23608a(context) || imageViewArr == null || imageViewArr.length <= 0) {
            return;
        }
        for (ImageView imageView : imageViewArr) {
            C6132f.m23492b(context).m24431l(imageView);
        }
    }

    /* renamed from: l */
    public static void m23592l(Context context, ImageView imageView, int i9) {
        m23593m(context, imageView, i9, false);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: m */
    public static void m23593m(Context context, ImageView imageView, int i9, boolean z8) {
        if (C6143q.m23608a(context)) {
            return;
        }
        C6134h<Drawable> c6134hM23572A = C6132f.m23492b(context).m23572A(Integer.valueOf(i9));
        if (z8) {
            c6134hM23572A.m23564W(new C5085r(C6128b.f20758a));
        }
        c6134hM23572A.m24422n(imageView);
    }

    /* renamed from: n */
    public static void m23594n(Context context, ImageView imageView, Uri uri) {
        m23595o(context, imageView, uri, 0);
    }

    /* renamed from: o */
    public static void m23595o(Context context, ImageView imageView, Uri uri, int i9) {
        m23596p(context, imageView, uri, i9, -1);
    }

    /* renamed from: p */
    public static void m23596p(Context context, ImageView imageView, Uri uri, int i9, int i10) {
        m23598r(context, imageView, uri, i9, i10, i10, false);
    }

    @SuppressLint({"CheckResult", "ResourceType"})
    /* renamed from: q */
    public static void m23597q(Context context, ImageView imageView, Uri uri, int i9, int i10, int i11, int i12, boolean z8) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        if (uri == null) {
            m23592l(context, imageView, i9);
            return;
        }
        C6134h<Bitmap> c6134hM23553L = C6132f.m23492b(context).mo23575d().m23553L(uri);
        if (i11 != -1 && i12 != -1) {
            c6134hM23553L.m23560S(i11, i12);
        }
        c6134hM23553L.m23561T(i9).m23546E(DownsampleStrategy.f3882e);
        if (i10 > 0) {
            c6134hM23553L.m23547F(i10);
        }
        if (z8) {
            c6134hM23553L.m23564W(new C5085r(C6128b.f20758a));
        }
        c6134hM23553L.m24422n(imageView);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: r */
    public static void m23598r(Context context, ImageView imageView, Uri uri, int i9, int i10, int i11, boolean z8) {
        m23597q(context, imageView, uri, i9, 0, i10, i11, z8);
    }

    /* renamed from: s */
    public static void m23599s(Context context, ImageView imageView, String str) {
        m23600t(context, imageView, str, 0);
    }

    /* renamed from: t */
    public static void m23600t(Context context, ImageView imageView, String str, int i9) {
        m23601u(context, imageView, str, i9, -1);
    }

    /* renamed from: u */
    public static void m23601u(Context context, ImageView imageView, String str, int i9, int i10) {
        m23603w(context, imageView, str, i9, i10, i10, false);
    }

    @SuppressLint({"CheckResult", "ResourceType"})
    /* renamed from: v */
    public static void m23602v(Context context, ImageView imageView, String str, int i9, int i10, int i11, int i12, boolean z8) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        if (C6383t.m24517f(str)) {
            m23592l(context, imageView, i9);
            return;
        }
        C6134h<Bitmap> c6134hM23556O = C6132f.m23492b(context).mo23575d().mo23570t(str);
        try {
            if (new File(str).isFile()) {
                c6134hM23556O.m23549H(str);
            }
        } catch (Exception unused) {
        }
        if (i11 != -1 && i12 != -1) {
            c6134hM23556O.m23560S(i11, i12);
        }
        c6134hM23556O.m23561T(i9).m23546E(DownsampleStrategy.f3882e);
        if (i10 > 0) {
            c6134hM23556O.m23547F(i10);
        }
        if (z8) {
            c6134hM23556O.m23564W(new C5085r(C6128b.f20758a));
        }
        c6134hM23556O.m24422n(imageView);
    }

    @SuppressLint({"CheckResult"})
    /* renamed from: w */
    public static void m23603w(Context context, ImageView imageView, String str, int i9, int i10, int i11, boolean z8) {
        m23602v(context, imageView, str, i9, 0, i10, i11, z8);
    }

    /* renamed from: x */
    public static void m23604x(Context context, ImageView imageView, String str, int i9, int i10, boolean z8) {
        m23603w(context, imageView, str, i9, i10, i10, z8);
    }

    /* renamed from: y */
    public static void m23605y(Context context, ImageView imageView, String str, int i9, C6250g c6250g) {
        if (context == null || imageView == null || C6143q.m23608a(context)) {
            return;
        }
        if (C6383t.m24517f(str)) {
            C6132f.m23492b(context).m23572A(Integer.valueOf(i9)).mo23566b(c6250g).m24422n(imageView);
        } else {
            C6132f.m23492b(context).mo23575d().mo23570t(str).mo23566b(c6250g).m23547F(i9).m24422n(imageView);
        }
    }

    /* renamed from: z */
    public static void m23606z(Context context, ImageView imageView, String str, boolean z8) {
        m23604x(context, imageView, str, 0, -1, z8);
    }
}
