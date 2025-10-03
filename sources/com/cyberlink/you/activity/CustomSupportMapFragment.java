package com.cyberlink.you.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cyberlink.you.activity.C1886aj;
import com.google.android.gms.maps.SupportMapFragment;

/* loaded from: classes.dex */
public class CustomSupportMapFragment extends SupportMapFragment {

    /* renamed from: b */
    public View f7590b;

    /* renamed from: c */
    public C1886aj f7591c;

    /* renamed from: g */
    public void m7996g(C1886aj.a aVar) {
        this.f7591c.setTouchListener(aVar);
    }

    @Override // androidx.fragment.app.Fragment
    public View getView() {
        return this.f7590b;
    }

    @Override // com.google.android.gms.maps.SupportMapFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f7590b = super.onCreateView(layoutInflater, viewGroup, bundle);
        C1886aj c1886aj = new C1886aj(getActivity());
        this.f7591c = c1886aj;
        c1886aj.addView(this.f7590b);
        return this.f7591c;
    }
}
