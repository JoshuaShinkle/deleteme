package p163p1;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import p207u0.C6359i;
import p207u0.ComponentCallbacks2C6355e;

@Deprecated
/* renamed from: p1.k */
/* loaded from: classes.dex */
public class FragmentC5880k extends Fragment {

    /* renamed from: b */
    public final C5870a f20326b;

    /* renamed from: c */
    public final InterfaceC5882m f20327c;

    /* renamed from: d */
    public final Set<FragmentC5880k> f20328d;

    /* renamed from: e */
    public C6359i f20329e;

    /* renamed from: f */
    public FragmentC5880k f20330f;

    /* renamed from: g */
    public Fragment f20331g;

    /* renamed from: p1.k$a */
    public class a implements InterfaceC5882m {
        public a() {
        }

        public String toString() {
            return super.toString() + "{fragment=" + FragmentC5880k.this + "}";
        }
    }

    public FragmentC5880k() {
        this(new C5870a());
    }

    /* renamed from: a */
    public final void m23323a(FragmentC5880k fragmentC5880k) {
        this.f20328d.add(fragmentC5880k);
    }

    /* renamed from: b */
    public C5870a m23324b() {
        return this.f20326b;
    }

    @TargetApi(17)
    /* renamed from: c */
    public final Fragment m23325c() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.f20331g;
    }

    /* renamed from: d */
    public C6359i m23326d() {
        return this.f20329e;
    }

    /* renamed from: e */
    public InterfaceC5882m m23327e() {
        return this.f20327c;
    }

    /* renamed from: f */
    public final void m23328f(Activity activity) {
        m23332j();
        FragmentC5880k fragmentC5880kM23340g = ComponentCallbacks2C6355e.m24381d(activity).m24396l().m23340g(activity);
        this.f20330f = fragmentC5880kM23340g;
        if (equals(fragmentC5880kM23340g)) {
            return;
        }
        this.f20330f.m23323a(this);
    }

    /* renamed from: g */
    public final void m23329g(FragmentC5880k fragmentC5880k) {
        this.f20328d.remove(fragmentC5880k);
    }

    /* renamed from: h */
    public void m23330h(Fragment fragment) {
        this.f20331g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        m23328f(fragment.getActivity());
    }

    /* renamed from: i */
    public void m23331i(C6359i c6359i) {
        this.f20329e = c6359i;
    }

    /* renamed from: j */
    public final void m23332j() {
        FragmentC5880k fragmentC5880k = this.f20330f;
        if (fragmentC5880k != null) {
            fragmentC5880k.m23329g(this);
            this.f20330f = null;
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            m23328f(activity);
        } catch (IllegalStateException e9) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e9);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f20326b.m23315c();
        m23332j();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        m23332j();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.f20326b.m23316d();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f20326b.m23317e();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + m23325c() + "}";
    }

    @SuppressLint({"ValidFragment"})
    public FragmentC5880k(C5870a c5870a) {
        this.f20327c = new a();
        this.f20328d = new HashSet();
        this.f20326b = c5870a;
    }
}
