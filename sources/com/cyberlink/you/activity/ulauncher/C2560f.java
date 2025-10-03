package com.cyberlink.you.activity.ulauncher;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupAlbumActivity;
import com.cyberlink.you.activity.NoticesActivity;
import com.cyberlink.you.activity.SettingActivity;
import com.cyberlink.you.activity.StickerShopActivity;
import com.cyberlink.you.activity.UpgradeToProUserActivity;
import com.cyberlink.you.activity.UserProfileActivity;
import com.cyberlink.you.activity.friend.C2143a;
import com.cyberlink.you.activity.friend.FriendAddActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.feedback.EditFeedbackActivity;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import p116k4.C5164m0;
import p116k4.C5167n0;
import p116k4.C5173p0;
import p116k4.C5192y;
import p116k4.C5194z;
import p136m3.C5321e;
import p173q2.C6127a;
import p209u2.C6385v;

/* renamed from: com.cyberlink.you.activity.ulauncher.f */
/* loaded from: classes.dex */
public class C2560f extends AbstractC2555a {

    /* renamed from: d */
    public View f11699d;

    /* renamed from: e */
    public View f11700e;

    /* renamed from: f */
    public View f11701f;

    /* renamed from: g */
    public View f11702g;

    /* renamed from: h */
    public ImageView f11703h;

    /* renamed from: i */
    public View f11704i;

    /* renamed from: v */
    public Future<?> f11717v;

    /* renamed from: x */
    public Future<?> f11719x;

    /* renamed from: j */
    public boolean f11705j = false;

    /* renamed from: k */
    public View.OnClickListener f11706k = new View.OnClickListener() { // from class: g3.j2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16940b.m13385D(view);
        }
    };

    /* renamed from: l */
    public View.OnClickListener f11707l = new View.OnClickListener() { // from class: g3.o2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16978b.m13386E(view);
        }
    };

    /* renamed from: m */
    public View.OnClickListener f11708m = new View.OnClickListener() { // from class: g3.p2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16986b.m13388G(view);
        }
    };

    /* renamed from: n */
    public View.OnClickListener f11709n = new View.OnClickListener() { // from class: g3.q2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16992b.m13389H(view);
        }
    };

    /* renamed from: o */
    public View.OnClickListener f11710o = new View.OnClickListener() { // from class: g3.r2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16998b.m13390I(view);
        }
    };

    /* renamed from: p */
    public View.OnClickListener f11711p = new View.OnClickListener() { // from class: g3.s2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17006b.m13391J(view);
        }
    };

    /* renamed from: q */
    public View.OnClickListener f11712q = new View.OnClickListener() { // from class: g3.t2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17014b.m13392K(view);
        }
    };

    /* renamed from: r */
    public View.OnClickListener f11713r = new View.OnClickListener() { // from class: g3.u2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17022b.m13393L(view);
        }
    };

    /* renamed from: s */
    public View.OnClickListener f11714s = new View.OnClickListener() { // from class: g3.v2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17030b.m13394M(view);
        }
    };

    /* renamed from: t */
    public View.OnClickListener f11715t = new View.OnClickListener() { // from class: g3.k2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16948b.m13395N(view);
        }
    };

    /* renamed from: u */
    public C5321e.m f11716u = new C5321e.m() { // from class: g3.n2
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f16971b.m13387F(c2904l, map);
        }
    };

    /* renamed from: w */
    public Runnable f11718w = new a();

    /* renamed from: y */
    public Runnable f11720y = new b();

    /* renamed from: com.cyberlink.you.activity.ulauncher.f$a */
    public class a implements Runnable {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m13419b(C5194z.a aVar) {
            if (aVar.m20305a() > Globals.m7388i0().m7597q0()) {
                Globals.m7388i0().m7416D3(true);
                C2560f.this.f11699d.setVisibility(0);
            }
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            final C5194z.a aVarM20296f = C5194z.m20296f();
            if (aVarM20296f == null) {
                return;
            }
            C2560f.this.m12963k(new Runnable() { // from class: g3.w2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17035b.m13419b(aVarM20296f);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.f$b */
    public class b implements Runnable {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m13421b(long j9) {
            if (j9 > Globals.m7388i0().m7639x0()) {
                Globals.m7388i0().m7531c4(false);
                Globals.m7388i0().m7426F3(true);
                Globals.m7388i0().m7406B3(true);
                Globals.m7388i0().m7651z3(true);
                C2560f.this.f11700e.setVisibility(0);
            }
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            final long jM20143a = C5167n0.m20143a();
            C2560f.this.m12963k(new Runnable() { // from class: g3.x2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17041b.m13421b(jM20143a);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.f$c */
    public class c extends PromisedTask.AbstractC3021b<SubscriptionInfo> {
        public c() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(SubscriptionInfo subscriptionInfo) {
            Globals.m7388i0().m7476Q3("pro".equalsIgnoreCase(subscriptionInfo.plan));
            Globals.m7388i0().m7642x3(Integer.parseInt(subscriptionInfo.attendeeCapacity));
            Globals.m7388i0().m7647y3(Integer.parseInt(subscriptionInfo.maximumLength));
            Globals.m7388i0().m7646y2(subscriptionInfo.androidOnHoldProductIds);
            if (C2560f.this.getActivity() != null && C2925v.m14620s0()) {
                C2925v.m14569N0(C2560f.this.getActivity(), null, false, null, "more page");
            }
            C2560f.this.m13417T();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C */
    public /* synthetic */ void m13384C() {
        C1260a.m5672i(Globals.m7388i0().m7506X()).m15439e(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D */
    public /* synthetic */ void m13385D(View view) {
        Globals.m7388i0().m7411C3(false);
        this.f11701f.setVisibility(8);
        startActivity(new Intent(getContext(), (Class<?>) FriendAddActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E */
    public /* synthetic */ void m13386E(View view) {
        C2143a.m12413m(this, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F */
    public /* synthetic */ boolean m13387F(C2904l c2904l, Map map) {
        if (!((String) map.get("eventType")).equals("suggestion.suggestion.created")) {
            return true;
        }
        m12963k(new Runnable() { // from class: g3.m2
            @Override // java.lang.Runnable
            public final void run() {
                this.f16963b.m13416S();
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G */
    public /* synthetic */ void m13388G(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) UpgradeToProUserActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m13389H(View view) {
        startActivity(new Intent(getActivity(), (Class<?>) GroupAlbumActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m13390I(View view) {
        startActivity(new Intent(getContext(), (Class<?>) UserProfileActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m13391J(View view) {
        Globals.m7388i0().m7421E3(false);
        this.f11702g.setVisibility(8);
        startActivity(new Intent(getContext(), (Class<?>) SettingActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K */
    public /* synthetic */ void m13392K(View view) {
        Globals.m7388i0().m7426F3(false);
        this.f11700e.setVisibility(8);
        startActivity(new Intent(getContext(), (Class<?>) StickerShopActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m13393L(View view) {
        this.f11699d.setVisibility(8);
        startActivityForResult(new Intent(getContext(), (Class<?>) NoticesActivity.class), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m13394M(View view) {
        String strM16548j0 = CLUtility.m16548j0();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(strM16548j0));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e9) {
            e9.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m13395N(View view) {
        Globals.m7388i0().m7421E3(false);
        this.f11702g.setVisibility(8);
        startActivity(new Intent(getContext(), (Class<?>) EditFeedbackActivity.class));
    }

    /* renamed from: B */
    public void m13411B() {
        C6385v.m24526d(new Runnable() { // from class: g3.l2
            @Override // java.lang.Runnable
            public final void run() {
                this.f16954b.m13384C();
            }
        });
    }

    /* renamed from: O */
    public final void m13412O() {
        Future<?> future = this.f11717v;
        if (future != null) {
            future.cancel(true);
        }
        this.f11717v = C6385v.f21553a.submit(this.f11718w);
    }

    /* renamed from: P */
    public final void m13413P() {
        if (Globals.m7388i0().m7523b0()) {
            return;
        }
        Globals.m7388i0().m7421E3(true);
        this.f11702g.setVisibility(0);
    }

    /* renamed from: Q */
    public final void m13414Q() {
        Future<?> future = this.f11719x;
        if (future != null) {
            future.cancel(true);
        }
        this.f11719x = C6385v.f21553a.submit(this.f11720y);
    }

    /* renamed from: R */
    public final void m13415R() {
        C6127a.m23473n(getActivity(), this.f11703h, CLUtility.m16497V0(getActivity()));
    }

    /* renamed from: S */
    public final void m13416S() {
        boolean zM7608s = Globals.m7388i0().m7608s();
        boolean zM7620u = Globals.m7388i0().m7620u();
        boolean zM7602r = Globals.m7388i0().m7602r();
        boolean zM7614t = Globals.m7388i0().m7614t();
        View view = this.f11699d;
        if (view != null && zM7608s) {
            view.setVisibility(0);
        }
        if (this.f11699d != null && !zM7608s && !this.f11705j && C5192y.m20281f().m20284e()) {
            m13412O();
        }
        View view2 = this.f11700e;
        if (view2 != null && zM7620u) {
            view2.setVisibility(0);
        }
        if (this.f11700e != null && !zM7620u && !this.f11705j && C5164m0.m20108m().m20114g()) {
            m13414Q();
        }
        View view3 = this.f11702g;
        if (view3 != null && zM7614t) {
            view3.setVisibility(0);
        }
        if (this.f11702g != null && !zM7614t && !this.f11705j) {
            m13413P();
        }
        View view4 = this.f11701f;
        if (view4 == null || !zM7602r) {
            return;
        }
        view4.setVisibility(0);
    }

    /* renamed from: T */
    public final void m13417T() {
        if (Globals.m7388i0().m7591p()) {
            this.f11704i.setVisibility(0);
        } else {
            this.f11704i.setVisibility(8);
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_more_list;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        Bundle extras;
        SuggestionFriend suggestionFriend;
        super.onActivityResult(i9, i10, intent);
        if (i10 == 0) {
            return;
        }
        if (i9 == 1) {
            C2143a.m12398C(this, i10, intent, 2);
            return;
        }
        if (i9 != 2 || i10 != -1 || intent == null || (extras = intent.getExtras()) == null || (suggestionFriend = (SuggestionFriend) extras.getParcelable("suggestionFriend")) == null) {
            return;
        }
        ArrayList<SuggestionFriend> arrayListM20211g = C5173p0.m20207e().m20211g();
        arrayListM20211g.remove(suggestionFriend);
        C5173p0.m20207e().m20209d(arrayListM20211g);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.f11705j = true;
        C5321e.m20824o().m20832B0(this.f11716u);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f11705j = false;
        m13417T();
        m13416S();
        C5321e.m20824o().m20875k(this.f11716u);
        m13415R();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        view.findViewById(R.id.moreAddContactsArea).setOnClickListener(this.f11706k);
        view.findViewById(R.id.moreScanQRCodeArea).setOnClickListener(this.f11707l);
        view.findViewById(R.id.moreUpgradeToProArea).setOnClickListener(this.f11708m);
        view.findViewById(R.id.moreContentArea).setOnClickListener(this.f11709n);
        view.findViewById(R.id.moreProfileArea).setOnClickListener(this.f11710o);
        view.findViewById(R.id.moreSettingArea).setOnClickListener(this.f11711p);
        view.findViewById(R.id.moreStickerShopArea).setOnClickListener(this.f11712q);
        view.findViewById(R.id.moreNoticeArea).setOnClickListener(this.f11713r);
        view.findViewById(R.id.moreFAQArea).setOnClickListener(this.f11714s);
        view.findViewById(R.id.moreFeedbackArea).setOnClickListener(this.f11715t);
        this.f11704i = view.findViewById(R.id.moreUpgradeToProArea);
        this.f11699d = view.findViewById(R.id.moreNoticeNewIcon);
        this.f11700e = view.findViewById(R.id.moreStickerShopNewIcon);
        this.f11701f = view.findViewById(R.id.moreAddContactsNewIcon);
        this.f11702g = view.findViewById(R.id.moreSettingNewIcon);
        this.f11703h = (ImageView) view.findViewById(R.id.moreAvatarIcon);
        this.f11504b.mo12937q0(null, PageType.More);
        m13411B();
    }
}
