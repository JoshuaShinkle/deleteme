package p209u2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: u2.k */
/* loaded from: classes.dex */
public class C6374k {

    /* renamed from: e */
    public static C6374k f21525e;

    /* renamed from: a */
    public final Context f21526a;

    /* renamed from: b */
    public final ArrayList<WeakReference<c>> f21527b = new ArrayList<>();

    /* renamed from: c */
    public final BroadcastReceiver f21528c = new a();

    /* renamed from: d */
    public b f21529d = new b(false, -1, -1);

    /* renamed from: u2.k$a */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            C6374k.this.m24488d(intent);
        }
    }

    /* renamed from: u2.k$b */
    public static class b {

        /* renamed from: a */
        public final boolean f21531a;

        /* renamed from: b */
        public final int f21532b;

        /* renamed from: c */
        public final int f21533c;

        public b(boolean z8, int i9, int i10) {
            this.f21531a = z8;
            this.f21532b = z8 ? i9 : -1;
            this.f21533c = z8 ? i10 : -1;
        }
    }

    /* renamed from: u2.k$c */
    public interface c {
        /* renamed from: a */
        void mo6925a();

        /* renamed from: b */
        void mo6926b();

        /* renamed from: c */
        void mo6927c();
    }

    public C6374k(Context context) {
        this.f21526a = context.getApplicationContext();
    }

    /* renamed from: c */
    public static C6374k m24485c() {
        C6374k c6374k = f21525e;
        if (c6374k != null) {
            return c6374k;
        }
        throw new IllegalStateException("Instance is not initialized");
    }

    /* renamed from: e */
    public static void m24486e(Context context) {
        if (f21525e == null) {
            f21525e = new C6374k(context);
        }
    }

    /* renamed from: b */
    public void m24487b(c cVar) {
        synchronized (this.f21527b) {
            ArrayList<WeakReference<c>> arrayList = this.f21527b;
            boolean z8 = false;
            for (WeakReference weakReference : (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()])) {
                if (weakReference.get() == null) {
                    this.f21527b.remove(weakReference);
                } else if (weakReference.get() == cVar) {
                    z8 = true;
                }
            }
            if (!z8) {
                this.f21527b.add(new WeakReference<>(cVar));
                if (this.f21527b.size() == 1) {
                    m24492i();
                }
            }
        }
    }

    /* renamed from: d */
    public final void m24488d(Intent intent) {
        NetworkInfo activeNetworkInfo;
        if (intent == null) {
            return;
        }
        boolean z8 = !intent.getBooleanExtra("noConnectivity", false);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f21526a.getSystemService("connectivity");
        b bVar = (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) ? null : new b(z8, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype());
        if (bVar == null) {
            bVar = new b(z8, -1, -1);
        }
        b bVar2 = this.f21529d;
        this.f21529d = bVar;
        boolean z9 = bVar.f21531a;
        if (z9 != bVar2.f21531a) {
            if (z9) {
                m24490g();
            } else {
                m24491h();
            }
        }
        if (bVar.f21531a) {
            if (bVar.f21532b == bVar2.f21532b && bVar.f21533c == bVar2.f21533c) {
                return;
            }
            m24489f();
        }
    }

    /* renamed from: f */
    public final void m24489f() {
        synchronized (this.f21527b) {
            ArrayList<WeakReference<c>> arrayList = this.f21527b;
            boolean z8 = false;
            for (WeakReference weakReference : (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()])) {
                if (weakReference.get() == null) {
                    this.f21527b.remove(weakReference);
                    z8 = true;
                } else {
                    ((c) weakReference.get()).mo6925a();
                }
            }
            if (z8 && this.f21527b.isEmpty()) {
                m24494k();
            }
        }
    }

    /* renamed from: g */
    public final void m24490g() {
        synchronized (this.f21527b) {
            ArrayList<WeakReference<c>> arrayList = this.f21527b;
            boolean z8 = false;
            for (WeakReference weakReference : (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()])) {
                if (weakReference.get() == null) {
                    this.f21527b.remove(weakReference);
                    z8 = true;
                } else {
                    ((c) weakReference.get()).mo6926b();
                }
            }
            if (z8 && this.f21527b.isEmpty()) {
                m24494k();
            }
        }
    }

    /* renamed from: h */
    public final void m24491h() {
        synchronized (this.f21527b) {
            ArrayList<WeakReference<c>> arrayList = this.f21527b;
            boolean z8 = false;
            for (WeakReference weakReference : (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()])) {
                if (weakReference.get() == null) {
                    this.f21527b.remove(weakReference);
                    z8 = true;
                } else {
                    ((c) weakReference.get()).mo6927c();
                }
            }
            if (z8 && this.f21527b.isEmpty()) {
                m24494k();
            }
        }
    }

    /* renamed from: i */
    public final void m24492i() {
        if (this.f21527b.size() != 1) {
            throw new IllegalStateException("It's too early, or late to register");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f21526a.registerReceiver(this.f21528c, intentFilter);
    }

    /* renamed from: j */
    public void m24493j(c cVar) {
        synchronized (this.f21527b) {
            ArrayList<WeakReference<c>> arrayList = this.f21527b;
            boolean z8 = false;
            for (WeakReference weakReference : (WeakReference[]) arrayList.toArray(new WeakReference[arrayList.size()])) {
                if (weakReference.get() == null || weakReference.get() == cVar) {
                    this.f21527b.remove(weakReference);
                    z8 = true;
                }
            }
            if (z8 && this.f21527b.isEmpty()) {
                m24494k();
            }
        }
    }

    /* renamed from: k */
    public final void m24494k() {
        if (!this.f21527b.isEmpty()) {
            throw new IllegalStateException("There're observers before unregister");
        }
        this.f21526a.unregisterReceiver(this.f21528c);
    }
}
