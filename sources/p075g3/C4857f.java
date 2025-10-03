package p075g3;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.activity.ulauncher.C2556b;
import com.cyberlink.you.activity.ulauncher.PageType;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.common.CLFragmentTabHost;

/* renamed from: g3.f */
/* loaded from: classes.dex */
public class C4857f extends AbstractC2555a {

    /* renamed from: e */
    public EditText f16872e;

    /* renamed from: f */
    public TextView f16873f;

    /* renamed from: g */
    public ImageView f16874g;

    /* renamed from: h */
    public View f16875h;

    /* renamed from: i */
    public boolean f16876i;

    /* renamed from: d */
    public CLFragmentTabHost f16871d = null;

    /* renamed from: j */
    public final int f16877j = 2;

    /* renamed from: k */
    public View.OnClickListener f16878k = new View.OnClickListener() { // from class: g3.c
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16842b.m19190s(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public /* synthetic */ void m19190s(View view) {
        m19197v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public /* synthetic */ void m19191t(View view) {
        m19198w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public /* synthetic */ void m19192u(View view) {
        this.f16874g.setVisibility(0);
        ((ULauncherActivity) getActivity()).m12923c2(false);
        this.f16876i = true;
        this.f16875h.setVisibility(8);
        m19193o(true);
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_chat;
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: j */
    public boolean mo12962j() {
        AbstractC2555a abstractC2555aM19195q = m19195q();
        if (!m19196r()) {
            if (abstractC2555aM19195q instanceof C2556b) {
                return ((C2556b) abstractC2555aM19195q).mo12962j();
            }
            return false;
        }
        if (abstractC2555aM19195q instanceof C4875i2) {
            ((C4875i2) abstractC2555aM19195q).mo12962j();
            return true;
        }
        m19197v();
        return true;
    }

    /* renamed from: o */
    public final void m19193o(boolean z8) {
        AbstractC2555a abstractC2555aM19195q = m19195q();
        if (abstractC2555aM19195q instanceof C2556b) {
            ((C2556b) abstractC2555aM19195q).m13070H0(z8);
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View viewInflate = layoutInflater.inflate(R.layout.fragment_chat, viewGroup, false);
        CLFragmentTabHost cLFragmentTabHost = (CLFragmentTabHost) viewInflate.findViewById(R.id.tabhost);
        this.f16871d = cLFragmentTabHost;
        cLFragmentTabHost.m17293g(getContext(), getChildFragmentManager(), R.id.realtabcontent);
        CLFragmentTabHost cLFragmentTabHost2 = this.f16871d;
        cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec(PageType.ChatsGroup.name()).setIndicator(m19194p(getString(R.string.search_chat_tab_contact_group))), C2556b.class, null);
        CLFragmentTabHost cLFragmentTabHost3 = this.f16871d;
        cLFragmentTabHost3.m17287a(cLFragmentTabHost3.newTabSpec(PageType.Messages.name()).setIndicator(m19194p(getString(R.string.search_chat_tab_message))), C4875i2.class, null);
        this.f16871d.getTabWidget().setDividerDrawable((Drawable) null);
        this.f16871d.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener() { // from class: g3.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f16847b.m19191t(view);
            }
        });
        m19199x(false);
        this.f16871d.setCurrentTab(0);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.searchChatBackBtn);
        this.f16874g = imageView;
        imageView.setOnClickListener(this.f16878k);
        this.f16876i = false;
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f16872e = (EditText) view.findViewById(R.id.SearchEditText);
        this.f16873f = (TextView) view.findViewById(R.id.hintTextView);
        View viewFindViewById = view.findViewById(R.id.fakeClickView);
        this.f16875h = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: g3.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f16854b.m19192u(view2);
            }
        });
    }

    /* renamed from: p */
    public final View m19194p(String str) {
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.fragment_chat_tab_selection, (ViewGroup) this.f16871d, false);
        ((TextView) viewInflate.findViewById(R.id.fragment_chat_tab_item_text)).setText(str);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams((int) (r0.widthPixels / 2.0f), (int) ((r0 * 0.2777778f) / 1.0d)));
        return viewInflate;
    }

    /* renamed from: q */
    public final AbstractC2555a m19195q() {
        PageType pageType = PageType.None;
        int currentTab = this.f16871d.getCurrentTab();
        PageType pageType2 = currentTab != 0 ? currentTab != 1 ? pageType : PageType.Messages : PageType.ChatsGroup;
        if (pageType2 != pageType) {
            return (AbstractC2555a) getChildFragmentManager().mo1848e(pageType2.name());
        }
        Log.e("ChatFragment", "Cannot find page id = " + currentTab);
        return null;
    }

    /* renamed from: r */
    public boolean m19196r() {
        return this.f16876i;
    }

    /* renamed from: v */
    public void m19197v() {
        this.f16874g.setVisibility(8);
        this.f16872e.setText("");
        this.f16873f.setVisibility(8);
        this.f16871d.setCurrentTab(0);
        m19199x(false);
        CLUtility.m16589t1(getActivity());
        ((ULauncherActivity) getActivity()).m12923c2(true);
        this.f16876i = false;
        this.f16875h.setVisibility(0);
        m19193o(false);
    }

    /* renamed from: w */
    public void m19198w() {
        AbstractC2555a abstractC2555aM19195q = m19195q();
        if (abstractC2555aM19195q instanceof C2556b) {
            ((C2556b) abstractC2555aM19195q).m13087P1();
        } else {
            this.f16873f.setVisibility(8);
            this.f16871d.setCurrentTab(0);
        }
    }

    /* renamed from: x */
    public void m19199x(boolean z8) {
        CLFragmentTabHost cLFragmentTabHost = this.f16871d;
        if (cLFragmentTabHost == null || cLFragmentTabHost.getTabWidget() == null) {
            return;
        }
        this.f16871d.getTabWidget().setVisibility(z8 ? 0 : 8);
    }

    /* renamed from: y */
    public void m19200y() {
        AbstractC2555a abstractC2555aM19195q = m19195q();
        if (abstractC2555aM19195q instanceof C2556b) {
            ((C2556b) abstractC2555aM19195q).m13109a2();
        }
    }
}
