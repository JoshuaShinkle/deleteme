package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p021c0.C0702h;
import p188s.C6226a;

/* renamed from: androidx.fragment.app.f */
/* loaded from: classes.dex */
public abstract class AbstractC0367f<E> extends AbstractC0364c {

    /* renamed from: b */
    public final Activity f2009b;

    /* renamed from: c */
    public final Context f2010c;

    /* renamed from: d */
    public final Handler f2011d;

    /* renamed from: e */
    public final int f2012e;

    /* renamed from: f */
    public final LayoutInflaterFactory2C0369h f2013f;

    public AbstractC0367f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // androidx.fragment.app.AbstractC0364c
    /* renamed from: b */
    public View mo1761b(int i9) {
        return null;
    }

    @Override // androidx.fragment.app.AbstractC0364c
    /* renamed from: c */
    public boolean mo1762c() {
        return true;
    }

    /* renamed from: d */
    Activity m1841d() {
        return this.f2009b;
    }

    /* renamed from: e */
    Context m1842e() {
        return this.f2010c;
    }

    /* renamed from: f */
    Handler m1843f() {
        return this.f2011d;
    }

    /* renamed from: g */
    public void mo1765g(Fragment fragment) {
    }

    /* renamed from: h */
    public void mo1766h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    /* renamed from: i */
    public abstract E mo1767i();

    /* renamed from: j */
    public LayoutInflater mo1768j() {
        return LayoutInflater.from(this.f2010c);
    }

    /* renamed from: k */
    public int mo1769k() {
        return this.f2012e;
    }

    /* renamed from: l */
    public boolean mo1770l() {
        return true;
    }

    /* renamed from: m */
    public void mo1771m(Fragment fragment, String[] strArr, int i9) {
    }

    /* renamed from: n */
    public boolean mo1772n(Fragment fragment) {
        return true;
    }

    /* renamed from: o */
    public boolean mo1773o(String str) {
        return false;
    }

    /* renamed from: p */
    public void mo1774p(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i9, Bundle bundle) {
        if (i9 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f2010c.startActivity(intent);
    }

    /* renamed from: q */
    public void mo1775q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) {
        if (i9 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        C6226a.m23793s(this.f2009b, intentSender, i9, intent, i10, i11, i12, bundle);
    }

    /* renamed from: r */
    public void mo1776r() {
    }

    public AbstractC0367f(Activity activity, Context context, Handler handler, int i9) {
        this.f2013f = new LayoutInflaterFactory2C0369h();
        this.f2009b = activity;
        this.f2010c = (Context) C0702h.m3469c(context, "context == null");
        this.f2011d = (Handler) C0702h.m3469c(handler, "handler == null");
        this.f2012e = i9;
    }
}
