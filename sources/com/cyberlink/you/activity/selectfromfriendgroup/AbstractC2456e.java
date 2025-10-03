package com.cyberlink.you.activity.selectfromfriendgroup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.selectfromfriendgroup.C2455d;
import com.cyberlink.you.friends.Friend;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.cyberlink.you.activity.selectfromfriendgroup.e */
/* loaded from: classes.dex */
public abstract class AbstractC2456e extends Fragment {

    /* renamed from: b */
    public C2455d f11230b;

    /* renamed from: c */
    public ListView f11231c;

    /* renamed from: d */
    public View f11232d;

    /* renamed from: g */
    public abstract Filter mo12570g();

    /* renamed from: h */
    public abstract C2455d.a mo12571h();

    /* renamed from: i */
    public List<Friend> m12618i() {
        return getActivity() instanceof SelectFromFriendGroupActivity ? ((SelectFromFriendGroupActivity) getActivity()).m12550d1() : new ArrayList();
    }

    /* renamed from: j */
    public List<Friend> m12619j() {
        return getActivity() instanceof SelectFromFriendGroupActivity ? ((SelectFromFriendGroupActivity) getActivity()).m12551e1() : new ArrayList();
    }

    /* renamed from: k */
    public void m12620k() {
        this.f11232d.setVisibility(this.f11231c.getCount() > 0 ? 8 : 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        C2455d c2455dM12549c1 = ((SelectFromFriendGroupActivity) context).m12549c1();
        this.f11230b = c2455dM12549c1;
        c2455dM12549c1.m12613c(mo12571h());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_sticker_shop_list, viewGroup, false);
        this.f11231c = (ListView) viewInflate.findViewById(R.id.stickerShopList);
        this.f11232d = viewInflate.findViewById(R.id.ListSearchEmptyView);
        return viewInflate;
    }
}
