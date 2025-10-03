package androidx.fragment.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* renamed from: androidx.fragment.app.g */
/* loaded from: classes.dex */
public abstract class AbstractC0368g {

    /* renamed from: c */
    public static final C0366e f2014c = new C0366e();

    /* renamed from: b */
    public C0366e f2015b = null;

    /* renamed from: androidx.fragment.app.g$a */
    public interface a {
        void onBackStackChanged();
    }

    /* renamed from: a */
    public abstract AbstractC0372k mo1844a();

    /* renamed from: b */
    public abstract void mo1845b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    /* renamed from: c */
    public abstract boolean mo1846c();

    /* renamed from: d */
    public abstract Fragment mo1847d(int i9);

    /* renamed from: e */
    public abstract Fragment mo1848e(String str);

    /* renamed from: f */
    public abstract int mo1849f();

    /* renamed from: g */
    public abstract Fragment mo1850g(Bundle bundle, String str);

    /* renamed from: h */
    public C0366e mo1851h() {
        if (this.f2015b == null) {
            this.f2015b = f2014c;
        }
        return this.f2015b;
    }

    /* renamed from: i */
    public abstract List<Fragment> mo1852i();

    /* renamed from: j */
    public abstract void mo1853j(int i9, int i10);

    /* renamed from: k */
    public abstract boolean mo1854k();

    /* renamed from: l */
    public abstract void mo1855l(Bundle bundle, String str, Fragment fragment);

    /* renamed from: m */
    public abstract Fragment.SavedState mo1856m(Fragment fragment);

    /* renamed from: n */
    public void m1857n(C0366e c0366e) {
        this.f2015b = c0366e;
    }
}
