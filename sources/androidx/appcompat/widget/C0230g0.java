package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p051e.C4691a;
import p132m.C5305d;
import p132m.C5306e;
import p132m.C5308g;
import p132m.C5309h;
import p171q0.C6120b;
import p197t.C6273a;
import p224w.C6494a;

/* renamed from: androidx.appcompat.widget.g0 */
/* loaded from: classes.dex */
public final class C0230g0 {

    /* renamed from: i */
    public static C0230g0 f1070i;

    /* renamed from: a */
    public WeakHashMap<Context, C5309h<ColorStateList>> f1072a;

    /* renamed from: b */
    public C5308g<String, b> f1073b;

    /* renamed from: c */
    public C5309h<String> f1074c;

    /* renamed from: d */
    public final WeakHashMap<Context, C5305d<WeakReference<Drawable.ConstantState>>> f1075d = new WeakHashMap<>(0);

    /* renamed from: e */
    public TypedValue f1076e;

    /* renamed from: f */
    public boolean f1077f;

    /* renamed from: g */
    public c f1078g;

    /* renamed from: h */
    public static final PorterDuff.Mode f1069h = PorterDuff.Mode.SRC_IN;

    /* renamed from: j */
    public static final a f1071j = new a(6);

    /* renamed from: androidx.appcompat.widget.g0$a */
    public static class a extends C5306e<Integer, PorterDuffColorFilter> {
        public a(int i9) {
            super(i9);
        }

        /* renamed from: a */
        public static int m869a(int i9, PorterDuff.Mode mode) {
            return ((i9 + 31) * 31) + mode.hashCode();
        }

        /* renamed from: b */
        public PorterDuffColorFilter m870b(int i9, PorterDuff.Mode mode) {
            return get(Integer.valueOf(m869a(i9, mode)));
        }

        /* renamed from: c */
        public PorterDuffColorFilter m871c(int i9, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return put(Integer.valueOf(m869a(i9, mode)), porterDuffColorFilter);
        }
    }

    /* renamed from: androidx.appcompat.widget.g0$b */
    public interface b {
        /* renamed from: a */
        Drawable m872a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* renamed from: androidx.appcompat.widget.g0$c */
    public interface c {
        /* renamed from: a */
        boolean mo827a(Context context, int i9, Drawable drawable);

        /* renamed from: b */
        PorterDuff.Mode mo828b(int i9);

        /* renamed from: c */
        Drawable mo829c(C0230g0 c0230g0, Context context, int i9);

        /* renamed from: d */
        ColorStateList mo830d(Context context, int i9);

        /* renamed from: e */
        boolean mo831e(Context context, int i9, Drawable drawable);
    }

    /* renamed from: d */
    public static long m846d(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    /* renamed from: f */
    public static PorterDuffColorFilter m847f(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return m849k(colorStateList.getColorForState(iArr, 0), mode);
    }

    /* renamed from: g */
    public static synchronized C0230g0 m848g() {
        if (f1070i == null) {
            C0230g0 c0230g0 = new C0230g0();
            f1070i = c0230g0;
            m850o(c0230g0);
        }
        return f1070i;
    }

    /* renamed from: k */
    public static synchronized PorterDuffColorFilter m849k(int i9, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterM870b;
        a aVar = f1071j;
        porterDuffColorFilterM870b = aVar.m870b(i9, mode);
        if (porterDuffColorFilterM870b == null) {
            porterDuffColorFilterM870b = new PorterDuffColorFilter(i9, mode);
            aVar.m871c(i9, mode, porterDuffColorFilterM870b);
        }
        return porterDuffColorFilterM870b;
    }

    /* renamed from: o */
    public static void m850o(C0230g0 c0230g0) {
    }

    /* renamed from: p */
    public static boolean m851p(Drawable drawable) {
        return (drawable instanceof C6120b) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    /* renamed from: v */
    public static void m852v(Drawable drawable, C0246o0 c0246o0, int[] iArr) {
        if (C0262x.m1073a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z8 = c0246o0.f1190d;
        if (z8 || c0246o0.f1189c) {
            drawable.setColorFilter(m847f(z8 ? c0246o0.f1187a : null, c0246o0.f1189c ? c0246o0.f1188b : f1069h, iArr));
        } else {
            drawable.clearColorFilter();
        }
    }

    /* renamed from: a */
    public final synchronized boolean m853a(Context context, long j9, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        C5305d<WeakReference<Drawable.ConstantState>> c5305d = this.f1075d.get(context);
        if (c5305d == null) {
            c5305d = new C5305d<>();
            this.f1075d.put(context, c5305d);
        }
        c5305d.m20726j(j9, new WeakReference<>(constantState));
        return true;
    }

    /* renamed from: b */
    public final void m854b(Context context, int i9, ColorStateList colorStateList) {
        if (this.f1072a == null) {
            this.f1072a = new WeakHashMap<>();
        }
        C5309h<ColorStateList> c5309h = this.f1072a.get(context);
        if (c5309h == null) {
            c5309h = new C5309h<>();
            this.f1072a.put(context, c5309h);
        }
        c5309h.m20756a(i9, colorStateList);
    }

    /* renamed from: c */
    public final void m855c(Context context) {
        if (this.f1077f) {
            return;
        }
        this.f1077f = true;
        Drawable drawableM858i = m858i(context, C4691a.abc_vector_test);
        if (drawableM858i == null || !m851p(drawableM858i)) {
            this.f1077f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    /* renamed from: e */
    public final Drawable m856e(Context context, int i9) throws Resources.NotFoundException {
        if (this.f1076e == null) {
            this.f1076e = new TypedValue();
        }
        TypedValue typedValue = this.f1076e;
        context.getResources().getValue(i9, typedValue, true);
        long jM846d = m846d(typedValue);
        Drawable drawableM857h = m857h(context, jM846d);
        if (drawableM857h != null) {
            return drawableM857h;
        }
        c cVar = this.f1078g;
        Drawable drawableMo829c = cVar == null ? null : cVar.mo829c(this, context, i9);
        if (drawableMo829c != null) {
            drawableMo829c.setChangingConfigurations(typedValue.changingConfigurations);
            m853a(context, jM846d, drawableMo829c);
        }
        return drawableMo829c;
    }

    /* renamed from: h */
    public final synchronized Drawable m857h(Context context, long j9) {
        C5305d<WeakReference<Drawable.ConstantState>> c5305d = this.f1075d.get(context);
        if (c5305d == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceM20722e = c5305d.m20722e(j9);
        if (weakReferenceM20722e != null) {
            Drawable.ConstantState constantState = weakReferenceM20722e.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            c5305d.m20727k(j9);
        }
        return null;
    }

    /* renamed from: i */
    public synchronized Drawable m858i(Context context, int i9) {
        return m859j(context, i9, false);
    }

    /* renamed from: j */
    public synchronized Drawable m859j(Context context, int i9, boolean z8) {
        Drawable drawableM863q;
        m855c(context);
        drawableM863q = m863q(context, i9);
        if (drawableM863q == null) {
            drawableM863q = m856e(context, i9);
        }
        if (drawableM863q == null) {
            drawableM863q = C6273a.m24025d(context, i9);
        }
        if (drawableM863q != null) {
            drawableM863q = m867u(context, i9, z8, drawableM863q);
        }
        if (drawableM863q != null) {
            C0262x.m1074b(drawableM863q);
        }
        return drawableM863q;
    }

    /* renamed from: l */
    public synchronized ColorStateList m860l(Context context, int i9) {
        ColorStateList colorStateListM861m;
        colorStateListM861m = m861m(context, i9);
        if (colorStateListM861m == null) {
            c cVar = this.f1078g;
            colorStateListM861m = cVar == null ? null : cVar.mo830d(context, i9);
            if (colorStateListM861m != null) {
                m854b(context, i9, colorStateListM861m);
            }
        }
        return colorStateListM861m;
    }

    /* renamed from: m */
    public final ColorStateList m861m(Context context, int i9) {
        C5309h<ColorStateList> c5309h;
        WeakHashMap<Context, C5309h<ColorStateList>> weakHashMap = this.f1072a;
        if (weakHashMap == null || (c5309h = weakHashMap.get(context)) == null) {
            return null;
        }
        return c5309h.m20760e(i9);
    }

    /* renamed from: n */
    public PorterDuff.Mode m862n(int i9) {
        c cVar = this.f1078g;
        if (cVar == null) {
            return null;
        }
        return cVar.mo828b(i9);
    }

    /* renamed from: q */
    public final Drawable m863q(Context context, int i9) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        C5308g<String, b> c5308g = this.f1073b;
        if (c5308g == null || c5308g.isEmpty()) {
            return null;
        }
        C5309h<String> c5309h = this.f1074c;
        if (c5309h != null) {
            String strM20760e = c5309h.m20760e(i9);
            if ("appcompat_skip_skip".equals(strM20760e) || (strM20760e != null && this.f1073b.get(strM20760e) == null)) {
                return null;
            }
        } else {
            this.f1074c = new C5309h<>();
        }
        if (this.f1076e == null) {
            this.f1076e = new TypedValue();
        }
        TypedValue typedValue = this.f1076e;
        Resources resources = context.getResources();
        resources.getValue(i9, typedValue, true);
        long jM846d = m846d(typedValue);
        Drawable drawableM857h = m857h(context, jM846d);
        if (drawableM857h != null) {
            return drawableM857h;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i9);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f1074c.m20756a(i9, name);
                b bVar = this.f1073b.get(name);
                if (bVar != null) {
                    drawableM857h = bVar.m872a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableM857h != null) {
                    drawableM857h.setChangingConfigurations(typedValue.changingConfigurations);
                    m853a(context, jM846d, drawableM857h);
                }
            } catch (Exception e9) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e9);
            }
        }
        if (drawableM857h == null) {
            this.f1074c.m20756a(i9, "appcompat_skip_skip");
        }
        return drawableM857h;
    }

    /* renamed from: r */
    public synchronized void m864r(Context context) {
        C5305d<WeakReference<Drawable.ConstantState>> c5305d = this.f1075d.get(context);
        if (c5305d != null) {
            c5305d.m20718a();
        }
    }

    /* renamed from: s */
    public synchronized Drawable m865s(Context context, C0256t0 c0256t0, int i9) {
        Drawable drawableM863q = m863q(context, i9);
        if (drawableM863q == null) {
            drawableM863q = c0256t0.m1065c(i9);
        }
        if (drawableM863q == null) {
            return null;
        }
        return m867u(context, i9, false, drawableM863q);
    }

    /* renamed from: t */
    public synchronized void m866t(c cVar) {
        this.f1078g = cVar;
    }

    /* renamed from: u */
    public final Drawable m867u(Context context, int i9, boolean z8, Drawable drawable) {
        ColorStateList colorStateListM860l = m860l(context, i9);
        if (colorStateListM860l == null) {
            c cVar = this.f1078g;
            if ((cVar == null || !cVar.mo831e(context, i9, drawable)) && !m868w(context, i9, drawable) && z8) {
                return null;
            }
            return drawable;
        }
        if (C0262x.m1073a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableM24849l = C6494a.m24849l(drawable);
        C6494a.m24846i(drawableM24849l, colorStateListM860l);
        PorterDuff.Mode modeM862n = m862n(i9);
        if (modeM862n == null) {
            return drawableM24849l;
        }
        C6494a.m24847j(drawableM24849l, modeM862n);
        return drawableM24849l;
    }

    /* renamed from: w */
    public boolean m868w(Context context, int i9, Drawable drawable) {
        c cVar = this.f1078g;
        return cVar != null && cVar.mo827a(context, i9, drawable);
    }
}
