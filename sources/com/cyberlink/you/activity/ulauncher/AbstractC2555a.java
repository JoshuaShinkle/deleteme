package com.cyberlink.you.activity.ulauncher;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* renamed from: com.cyberlink.you.activity.ulauncher.a */
/* loaded from: classes.dex */
public abstract class AbstractC2555a extends Fragment {

    /* renamed from: b */
    public a f11504b;

    /* renamed from: c */
    public boolean f11505c = false;

    /* renamed from: com.cyberlink.you.activity.ulauncher.a$a */
    public interface a {
        /* renamed from: n0 */
        void mo12935n0();

        /* renamed from: q0 */
        void mo12937q0(View.OnClickListener onClickListener, PageType pageType);
    }

    /* renamed from: g */
    public abstract int mo12959g();

    /* renamed from: h */
    public boolean m12960h() {
        return getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed();
    }

    /* renamed from: i */
    public boolean m12961i() {
        return this.f11505c;
    }

    /* renamed from: j */
    public boolean mo12962j() {
        return false;
    }

    /* renamed from: k */
    public final void m12963k(Runnable runnable) {
        if (m12960h()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.f11504b = (a) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f11505c = false;
        return layoutInflater.inflate(mo12959g(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.f11505c = true;
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f11504b = null;
    }
}
