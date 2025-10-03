package p124l1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import p020c.C0694a;
import p071g.C4798d;
import p197t.C6273a;
import p206u.C6348b;

/* renamed from: l1.a */
/* loaded from: classes.dex */
public final class C5272a {

    /* renamed from: a */
    public static volatile boolean f17875a = true;

    /* renamed from: a */
    public static Drawable m20533a(Context context, int i9, Resources.Theme theme) {
        return m20535c(context, context, i9, theme);
    }

    /* renamed from: b */
    public static Drawable m20534b(Context context, Context context2, int i9) {
        return m20535c(context, context2, i9, null);
    }

    /* renamed from: c */
    public static Drawable m20535c(Context context, Context context2, int i9, Resources.Theme theme) {
        try {
            if (f17875a) {
                return m20537e(context2, i9, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e9) {
            if (context.getPackageName().equals(context2.getPackageName())) {
                throw e9;
            }
            return C6273a.m24025d(context2, i9);
        } catch (NoClassDefFoundError unused2) {
            f17875a = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        return m20536d(context2, i9, theme);
    }

    /* renamed from: d */
    public static Drawable m20536d(Context context, int i9, Resources.Theme theme) {
        return C6348b.m24363a(context.getResources(), i9, theme);
    }

    /* renamed from: e */
    public static Drawable m20537e(Context context, int i9, Resources.Theme theme) {
        if (theme != null) {
            context = new C4798d(context, theme);
        }
        return C0694a.m3458b(context, i9);
    }
}
