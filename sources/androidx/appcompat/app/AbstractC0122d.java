package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import p132m.C5303b;

/* renamed from: androidx.appcompat.app.d */
/* loaded from: classes.dex */
public abstract class AbstractC0122d {

    /* renamed from: b */
    public static int f380b = -100;

    /* renamed from: c */
    public static final C5303b<WeakReference<AbstractC0122d>> f381c = new C5303b<>();

    /* renamed from: d */
    public static final Object f382d = new Object();

    /* renamed from: a */
    public static void m398a(AbstractC0122d abstractC0122d) {
        synchronized (f382d) {
            m403w(abstractC0122d);
            f381c.add(new WeakReference<>(abstractC0122d));
        }
    }

    /* renamed from: e */
    public static AbstractC0122d m399e(Activity activity, InterfaceC0121c interfaceC0121c) {
        return new AppCompatDelegateImpl(activity, interfaceC0121c);
    }

    /* renamed from: f */
    public static AbstractC0122d m400f(Dialog dialog, InterfaceC0121c interfaceC0121c) {
        return new AppCompatDelegateImpl(dialog, interfaceC0121c);
    }

    /* renamed from: h */
    public static int m401h() {
        return f380b;
    }

    /* renamed from: v */
    public static void m402v(AbstractC0122d abstractC0122d) {
        synchronized (f382d) {
            m403w(abstractC0122d);
        }
    }

    /* renamed from: w */
    public static void m403w(AbstractC0122d abstractC0122d) {
        synchronized (f382d) {
            Iterator<WeakReference<AbstractC0122d>> it = f381c.iterator();
            while (it.hasNext()) {
                AbstractC0122d abstractC0122d2 = it.next().get();
                if (abstractC0122d2 == abstractC0122d || abstractC0122d2 == null) {
                    it.remove();
                }
            }
        }
    }

    /* renamed from: A */
    public abstract void mo260A(View view, ViewGroup.LayoutParams layoutParams);

    /* renamed from: B */
    public void mo262B(int i9) {
    }

    /* renamed from: C */
    public abstract void mo264C(CharSequence charSequence);

    /* renamed from: b */
    public abstract void mo297b(View view, ViewGroup.LayoutParams layoutParams);

    @Deprecated
    /* renamed from: c */
    public void m404c(Context context) {
    }

    /* renamed from: d */
    public Context mo300d(Context context) {
        m404c(context);
        return context;
    }

    /* renamed from: g */
    public abstract <T extends View> T mo304g(int i9);

    /* renamed from: i */
    public int mo307i() {
        return -100;
    }

    /* renamed from: j */
    public abstract MenuInflater mo309j();

    /* renamed from: k */
    public abstract AbstractC0119a mo311k();

    /* renamed from: l */
    public abstract void mo313l();

    /* renamed from: m */
    public abstract void mo315m();

    /* renamed from: n */
    public abstract void mo317n(Configuration configuration);

    /* renamed from: o */
    public abstract void mo319o(Bundle bundle);

    /* renamed from: p */
    public abstract void mo321p();

    /* renamed from: q */
    public abstract void mo323q(Bundle bundle);

    /* renamed from: r */
    public abstract void mo325r();

    /* renamed from: s */
    public abstract void mo327s(Bundle bundle);

    /* renamed from: t */
    public abstract void mo329t();

    /* renamed from: u */
    public abstract void mo331u();

    /* renamed from: x */
    public abstract boolean mo335x(int i9);

    /* renamed from: y */
    public abstract void mo337y(int i9);

    /* renamed from: z */
    public abstract void mo339z(View view);
}
