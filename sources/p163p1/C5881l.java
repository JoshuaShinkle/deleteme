package p163p1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.HashMap;
import java.util.Map;
import p132m.C5302a;
import p207u0.C6359i;
import p207u0.ComponentCallbacks2C6355e;
import p226w1.C6517j;

/* renamed from: p1.l */
/* loaded from: classes.dex */
public class C5881l implements Handler.Callback {

    /* renamed from: j */
    public static final b f20333j = new a();

    /* renamed from: b */
    public volatile C6359i f20334b;

    /* renamed from: e */
    public final Handler f20337e;

    /* renamed from: f */
    public final b f20338f;

    /* renamed from: c */
    public final Map<FragmentManager, FragmentC5880k> f20335c = new HashMap();

    /* renamed from: d */
    public final Map<AbstractC0368g, C5884o> f20336d = new HashMap();

    /* renamed from: g */
    public final C5302a<View, Fragment> f20339g = new C5302a<>();

    /* renamed from: h */
    public final C5302a<View, android.app.Fragment> f20340h = new C5302a<>();

    /* renamed from: i */
    public final Bundle f20341i = new Bundle();

    /* renamed from: p1.l$a */
    public class a implements b {
        @Override // p163p1.C5881l.b
        /* renamed from: a */
        public C6359i mo23345a(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC5877h interfaceC5877h, InterfaceC5882m interfaceC5882m, Context context) {
            return new C6359i(componentCallbacks2C6355e, interfaceC5877h, interfaceC5882m, context);
        }
    }

    /* renamed from: p1.l$b */
    public interface b {
        /* renamed from: a */
        C6359i mo23345a(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC5877h interfaceC5877h, InterfaceC5882m interfaceC5882m, Context context);
    }

    public C5881l(b bVar) {
        this.f20338f = bVar == null ? f20333j : bVar;
        this.f20337e = new Handler(Looper.getMainLooper(), this);
    }

    @TargetApi(17)
    /* renamed from: a */
    public static void m23333a(Activity activity) {
        if (activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    /* renamed from: k */
    public static boolean m23334k(Activity activity) {
        return !activity.isFinishing();
    }

    @Deprecated
    /* renamed from: b */
    public final C6359i m23335b(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z8) {
        FragmentC5880k fragmentC5880kM23341h = m23341h(fragmentManager, fragment, z8);
        C6359i c6359iM23326d = fragmentC5880kM23341h.m23326d();
        if (c6359iM23326d != null) {
            return c6359iM23326d;
        }
        C6359i c6359iMo23345a = this.f20338f.mo23345a(ComponentCallbacks2C6355e.m24381d(context), fragmentC5880kM23341h.m23324b(), fragmentC5880kM23341h.m23327e(), context);
        fragmentC5880kM23341h.m23331i(c6359iMo23345a);
        return c6359iMo23345a;
    }

    /* renamed from: c */
    public C6359i m23336c(Activity activity) {
        if (C6517j.m24956q()) {
            return m23337d(activity.getApplicationContext());
        }
        m23333a(activity);
        return m23335b(activity, activity.getFragmentManager(), null, m23334k(activity));
    }

    /* renamed from: d */
    public C6359i m23337d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (C6517j.m24957r() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return m23338e((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return m23336c((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return m23337d(((ContextWrapper) context).getBaseContext());
            }
        }
        return m23339f(context);
    }

    /* renamed from: e */
    public C6359i m23338e(FragmentActivity fragmentActivity) {
        if (C6517j.m24956q()) {
            return m23337d(fragmentActivity.getApplicationContext());
        }
        m23333a(fragmentActivity);
        return m23344l(fragmentActivity, fragmentActivity.getSupportFragmentManager(), null, m23334k(fragmentActivity));
    }

    /* renamed from: f */
    public final C6359i m23339f(Context context) {
        if (this.f20334b == null) {
            synchronized (this) {
                if (this.f20334b == null) {
                    this.f20334b = this.f20338f.mo23345a(ComponentCallbacks2C6355e.m24381d(context.getApplicationContext()), new C5871b(), new C5876g(), context.getApplicationContext());
                }
            }
        }
        return this.f20334b;
    }

    @Deprecated
    /* renamed from: g */
    public FragmentC5880k m23340g(Activity activity) {
        return m23341h(activity.getFragmentManager(), null, m23334k(activity));
    }

    /* renamed from: h */
    public final FragmentC5880k m23341h(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z8) {
        FragmentC5880k fragmentC5880k = (FragmentC5880k) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (fragmentC5880k == null && (fragmentC5880k = this.f20335c.get(fragmentManager)) == null) {
            fragmentC5880k = new FragmentC5880k();
            fragmentC5880k.m23330h(fragment);
            if (z8) {
                fragmentC5880k.m23324b().m23316d();
            }
            this.f20335c.put(fragmentManager, fragmentC5880k);
            fragmentManager.beginTransaction().add(fragmentC5880k, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f20337e.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return fragmentC5880k;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        ComponentCallbacks componentCallbacksRemove;
        Object obj2;
        ComponentCallbacks componentCallbacks;
        int i9 = message.what;
        boolean z8 = true;
        if (i9 == 1) {
            obj = (FragmentManager) message.obj;
            componentCallbacksRemove = this.f20335c.remove(obj);
        } else {
            if (i9 != 2) {
                componentCallbacks = null;
                z8 = false;
                obj2 = null;
                if (z8 && componentCallbacks == null && Log.isLoggable("RMRetriever", 5)) {
                    Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
                }
                return z8;
            }
            obj = (AbstractC0368g) message.obj;
            componentCallbacksRemove = this.f20336d.remove(obj);
        }
        ComponentCallbacks componentCallbacks2 = componentCallbacksRemove;
        obj2 = obj;
        componentCallbacks = componentCallbacks2;
        if (z8) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
        }
        return z8;
    }

    /* renamed from: i */
    public C5884o m23342i(FragmentActivity fragmentActivity) {
        return m23343j(fragmentActivity.getSupportFragmentManager(), null, m23334k(fragmentActivity));
    }

    /* renamed from: j */
    public final C5884o m23343j(AbstractC0368g abstractC0368g, Fragment fragment, boolean z8) {
        C5884o c5884o = (C5884o) abstractC0368g.mo1848e("com.bumptech.glide.manager");
        if (c5884o == null && (c5884o = this.f20336d.get(abstractC0368g)) == null) {
            c5884o = new C5884o();
            c5884o.m23360n(fragment);
            if (z8) {
                c5884o.m23354h().m23316d();
            }
            this.f20336d.put(abstractC0368g, c5884o);
            abstractC0368g.mo1844a().m1982d(c5884o, "com.bumptech.glide.manager").mo1794h();
            this.f20337e.obtainMessage(2, abstractC0368g).sendToTarget();
        }
        return c5884o;
    }

    /* renamed from: l */
    public final C6359i m23344l(Context context, AbstractC0368g abstractC0368g, Fragment fragment, boolean z8) {
        C5884o c5884oM23343j = m23343j(abstractC0368g, fragment, z8);
        C6359i c6359iM23356j = c5884oM23343j.m23356j();
        if (c6359iM23356j != null) {
            return c6359iM23356j;
        }
        C6359i c6359iMo23345a = this.f20338f.mo23345a(ComponentCallbacks2C6355e.m24381d(context), c5884oM23343j.m23354h(), c5884oM23343j.m23357k(), context);
        c5884oM23343j.m23361o(c6359iMo23345a);
        return c6359iMo23345a;
    }
}
