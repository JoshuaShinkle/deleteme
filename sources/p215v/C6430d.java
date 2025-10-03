package p215v;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import p001a0.C0004b;
import p132m.C5306e;
import p206u.C6347a;
import p206u.C6348b;

@SuppressLint({"NewApi"})
/* renamed from: v.d */
/* loaded from: classes.dex */
public class C6430d {

    /* renamed from: a */
    public static final C6444r f21660a;

    /* renamed from: b */
    public static final C5306e<String, Typeface> f21661b;

    static {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 29) {
            f21660a = new C6443q();
        } else if (i9 >= 28) {
            f21660a = new C6433g();
        } else {
            f21660a = new C6432f();
        }
        f21661b = new C5306e<>(16);
    }

    /* renamed from: a */
    public static Typeface m24594a(Context context, Typeface typeface, int i9) {
        if (context != null) {
            return Typeface.create(typeface, i9);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    /* renamed from: b */
    public static Typeface m24595b(Context context, CancellationSignal cancellationSignal, C0004b.f[] fVarArr, int i9) {
        return f21660a.mo24606b(context, cancellationSignal, fVarArr, i9);
    }

    /* renamed from: c */
    public static Typeface m24596c(Context context, C6347a.a aVar, Resources resources, int i9, int i10, C6348b.a aVar2, Handler handler, boolean z8) {
        Typeface typefaceMo24604a;
        if (aVar instanceof C6347a.d) {
            C6347a.d dVar = (C6347a.d) aVar;
            boolean z9 = false;
            if (!z8 ? aVar2 == null : dVar.m24360a() == 0) {
                z9 = true;
            }
            typefaceMo24604a = C0004b.m25g(context, dVar.m24361b(), aVar2, handler, z9, z8 ? dVar.m24362c() : -1, i10);
        } else {
            typefaceMo24604a = f21660a.mo24604a(context, (C6347a.b) aVar, resources, i10);
            if (aVar2 != null) {
                if (typefaceMo24604a != null) {
                    aVar2.callbackSuccessAsync(typefaceMo24604a, handler);
                } else {
                    aVar2.callbackFailAsync(-3, handler);
                }
            }
        }
        if (typefaceMo24604a != null) {
            f21661b.put(m24598e(resources, i9, i10), typefaceMo24604a);
        }
        return typefaceMo24604a;
    }

    /* renamed from: d */
    public static Typeface m24597d(Context context, Resources resources, int i9, String str, int i10) {
        Typeface typefaceMo24607c = f21660a.mo24607c(context, resources, i9, str, i10);
        if (typefaceMo24607c != null) {
            f21661b.put(m24598e(resources, i9, i10), typefaceMo24607c);
        }
        return typefaceMo24607c;
    }

    /* renamed from: e */
    public static String m24598e(Resources resources, int i9, int i10) {
        return resources.getResourcePackageName(i9) + "-" + i9 + "-" + i10;
    }

    /* renamed from: f */
    public static Typeface m24599f(Resources resources, int i9, int i10) {
        return f21661b.get(m24598e(resources, i9, i10));
    }
}
