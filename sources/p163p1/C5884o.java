package p163p1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.HashSet;
import java.util.Set;
import p207u0.C6359i;
import p207u0.ComponentCallbacks2C6355e;

/* renamed from: p1.o */
/* loaded from: classes.dex */
public class C5884o extends Fragment {

    /* renamed from: b */
    public final C5870a f20345b;

    /* renamed from: c */
    public final InterfaceC5882m f20346c;

    /* renamed from: d */
    public final Set<C5884o> f20347d;

    /* renamed from: e */
    public C5884o f20348e;

    /* renamed from: f */
    public C6359i f20349f;

    /* renamed from: g */
    public Fragment f20350g;

    /* renamed from: p1.o$a */
    public class a implements InterfaceC5882m {
        public a() {
        }

        public String toString() {
            return super.toString() + "{fragment=" + C5884o.this + "}";
        }
    }

    public C5884o() {
        this(new C5870a());
    }

    /* renamed from: g */
    public final void m23353g(C5884o c5884o) {
        this.f20347d.add(c5884o);
    }

    /* renamed from: h */
    public C5870a m23354h() {
        return this.f20345b;
    }

    /* renamed from: i */
    public final Fragment m23355i() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.f20350g;
    }

    /* renamed from: j */
    public C6359i m23356j() {
        return this.f20349f;
    }

    /* renamed from: k */
    public InterfaceC5882m m23357k() {
        return this.f20346c;
    }

    /* renamed from: l */
    public final void m23358l(FragmentActivity fragmentActivity) {
        m23362p();
        C5884o c5884oM23342i = ComponentCallbacks2C6355e.m24381d(fragmentActivity).m24396l().m23342i(fragmentActivity);
        this.f20348e = c5884oM23342i;
        if (equals(c5884oM23342i)) {
            return;
        }
        this.f20348e.m23353g(this);
    }

    /* renamed from: m */
    public final void m23359m(C5884o c5884o) {
        this.f20347d.remove(c5884o);
    }

    /* renamed from: n */
    public void m23360n(Fragment fragment) {
        this.f20350g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        m23358l(fragment.getActivity());
    }

    /* renamed from: o */
    public void m23361o(C6359i c6359i) {
        this.f20349f = c6359i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            m23358l(getActivity());
        } catch (IllegalStateException e9) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root", e9);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f20345b.m23315c();
        m23362p();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f20350g = null;
        m23362p();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.f20345b.m23316d();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.f20345b.m23317e();
    }

    /* renamed from: p */
    public final void m23362p() {
        C5884o c5884o = this.f20348e;
        if (c5884o != null) {
            c5884o.m23359m(this);
            this.f20348e = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + m23355i() + "}";
    }

    @SuppressLint({"ValidFragment"})
    public C5884o(C5870a c5870a) {
        this.f20346c = new a();
        this.f20347d = new HashSet();
        this.f20345b = c5870a;
    }
}
