package com.cyberlink.you.feedback;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TopBarFragment extends Fragment {

    /* renamed from: r */
    public static int f13421r = 5;

    /* renamed from: s */
    public static long f13422s = 2000;

    /* renamed from: b */
    public ArrayList<InterfaceC3023a> f13423b = new ArrayList<>();

    /* renamed from: c */
    public LayoutInflater f13424c = null;

    /* renamed from: d */
    public RelativeLayout f13425d = null;

    /* renamed from: e */
    public ArrayList<View> f13426e = new ArrayList<>();

    /* renamed from: f */
    public View f13427f = null;

    /* renamed from: g */
    public View f13428g = null;

    /* renamed from: h */
    public ImageView f13429h = null;

    /* renamed from: i */
    public ImageView f13430i = null;

    /* renamed from: j */
    public TextView f13431j = null;

    /* renamed from: k */
    public TextView f13432k = null;

    /* renamed from: l */
    public ImageView f13433l = null;

    /* renamed from: m */
    public int f13434m = 0;

    /* renamed from: n */
    public long f13435n = 0;

    /* renamed from: o */
    public int f13436o = 0;

    /* renamed from: p */
    public View.OnClickListener f13437p = new View.OnClickListener() { // from class: p3.k
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20696b.m15452d(view);
        }
    };

    /* renamed from: q */
    public View.OnClickListener f13438q = new View.OnClickListener() { // from class: p3.l
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20697b.m15453e(view);
        }
    };

    /* renamed from: com.cyberlink.you.feedback.TopBarFragment$a */
    public interface InterfaceC3023a {
        /* renamed from: b */
        void mo15390b();

        void onRightBtnClick(View view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ void m15452d(View view) {
        Iterator<InterfaceC3023a> it = this.f13423b.iterator();
        while (it.hasNext()) {
            it.next().mo15390b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m15453e(View view) {
        Iterator<InterfaceC3023a> it = this.f13423b.iterator();
        while (it.hasNext()) {
            it.next().onRightBtnClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m15454f(View view) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f13435n > f13422s) {
            this.f13434m = 1;
        } else {
            this.f13434m++;
        }
        this.f13435n = jCurrentTimeMillis;
        if (this.f13434m >= f13421r) {
            this.f13434m = 0;
        }
    }

    /* renamed from: g */
    public synchronized void m15455g(InterfaceC3023a interfaceC3023a) {
        if (!this.f13423b.contains(interfaceC3023a)) {
            this.f13423b.add(interfaceC3023a);
        }
    }

    /* renamed from: h */
    public final void m15456h(int i9, int i10) {
        ImageView imageView = this.f13429h;
        if (imageView == null || i9 == 0) {
            return;
        }
        imageView.setVisibility(0);
        if (i10 != 0) {
            this.f13429h.setImageResource(i10);
        }
    }

    /* renamed from: i */
    public final void m15457i(int i9, int i10, boolean z8) {
        ImageView imageView = this.f13430i;
        if (imageView == null || i9 == 0) {
            return;
        }
        imageView.setVisibility(0);
        if (i10 != 0) {
            this.f13430i.setImageResource(i10);
            if (z8) {
                this.f13430i.setRotation(180.0f);
            }
        }
    }

    /* renamed from: j */
    public void m15458j(String str) {
        if (str == null) {
            return;
        }
        if (this.f13426e.isEmpty()) {
            m15459k(new String[]{str});
            return;
        }
        View view = this.f13426e.get(this.f13436o);
        if (view instanceof TextView) {
            ((TextView) view).setText(str);
        }
    }

    /* renamed from: k */
    public void m15459k(String[] strArr) {
        this.f13425d.removeAllViews();
        this.f13426e.clear();
        for (String str : strArr) {
            TextView textView = (TextView) this.f13424c.inflate(R.layout.bc_view_item_topbar_title, (ViewGroup) this.f13425d, false);
            textView.setText(str);
            this.f13425d.addView(textView);
            this.f13426e.add(textView);
        }
    }

    /* renamed from: l */
    public final void m15460l(View view, int i9) {
        if (view != null) {
            view.setVisibility(i9 != 0 ? 0 : 4);
        }
    }

    /* renamed from: m */
    public void m15461m() {
        m15462n(Integer.MIN_VALUE, R.drawable.icon_back, 0);
    }

    /* renamed from: n */
    public void m15462n(int i9, int i10, int i11) {
        int i12 = Integer.MIN_VALUE & i9;
        m15460l(this.f13427f, i12);
        m15460l(this.f13428g, 1073741824 & i9);
        int i13 = 67108864 & i9;
        m15460l(this.f13430i, i13);
        m15460l(this.f13431j, 33554432 & i9);
        m15460l(this.f13432k, 16777216 & i9);
        m15460l(this.f13433l, 134217728 & i9);
        this.f13429h.setVisibility(4);
        m15456h(i12, i10);
        this.f13430i.setVisibility(4);
        m15457i(i13, i11, (i9 & 1) != 0);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f13424c = layoutInflater;
        View viewInflate = layoutInflater.inflate(R.layout.bc_fragment_topbar, viewGroup, false);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.top_bar_title_layout);
        this.f13425d = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: p3.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20698b.m15454f(view);
            }
        });
        this.f13427f = viewInflate.findViewById(R.id.top_bar_left_panel);
        this.f13428g = viewInflate.findViewById(R.id.top_bar_right_panel);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.top_bar_btn_back);
        this.f13429h = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(this.f13437p);
        }
        ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.top_bar_right_btn);
        this.f13430i = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this.f13438q);
        }
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
