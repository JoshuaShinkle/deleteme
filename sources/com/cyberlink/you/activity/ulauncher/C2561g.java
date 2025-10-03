package com.cyberlink.you.activity.ulauncher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.C0476e;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.DialPhoneActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.PhoneCallObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import p116k4.C5170o0;
import p218v2.C6456d;

/* renamed from: com.cyberlink.you.activity.ulauncher.g */
/* loaded from: classes.dex */
public class C2561g extends AbstractC2555a {

    /* renamed from: f */
    public a f11726f;

    /* renamed from: g */
    public TextView f11727g;

    /* renamed from: h */
    public TextView f11728h;

    /* renamed from: d */
    public final String f11724d = "PhoneFragment";

    /* renamed from: e */
    public final int f11725e = 5001;

    /* renamed from: i */
    public View.OnClickListener f11729i = new View.OnClickListener() { // from class: g3.y2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f17049b.m13426o(view);
        }
    };

    /* renamed from: j */
    public XMPPManager.InterfaceC2851b0 f11730j = new XMPPManager.InterfaceC2851b0() { // from class: g3.z2
        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
        /* renamed from: h0 */
        public final void mo13974h0(boolean z8) {
            this.f17055b.m13427p(z8);
        }
    };

    /* renamed from: com.cyberlink.you.activity.ulauncher.g$a */
    public class a extends RecyclerView.AbstractC0446g<C6851a> {

        /* renamed from: a */
        public Context f11731a;

        /* renamed from: b */
        public List<PhoneCallObj> f11732b;

        /* renamed from: com.cyberlink.you.activity.ulauncher.g$a$a, reason: collision with other inner class name */
        public class C6851a extends RecyclerView.AbstractC0442c0 {

            /* renamed from: b */
            public ImageView f11734b;

            /* renamed from: c */
            public TextView f11735c;

            /* renamed from: d */
            public TextView f11736d;

            /* renamed from: e */
            public TextView f11737e;

            public C6851a(View view) {
                super(view);
            }
        }

        public a(Context context, List<PhoneCallObj> list) {
            this.f11731a = context;
            this.f11732b = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m13430d(C6851a c6851a, Friend friend, View view) {
            PhoneCallObj phoneCallObj = this.f11732b.get(c6851a.getLayoutPosition());
            ULogUtility.m16670f("PhoneFragment", "dial from phone recent list, number : " + phoneCallObj.f13032c + " name : " + phoneCallObj.f13033d);
            Group group = new Group();
            group.f13717d = phoneCallObj.f13033d;
            group.f13716c = "Dual";
            if (friend != null) {
                group.f13724k = friend.f13647e;
            }
            MeetingActivity.m6395h9(C2561g.this.getActivity(), group, true, MimeTypes.BASE_TYPE_AUDIO, "Phone Recent List", true, phoneCallObj.f13032c, phoneCallObj.f13033d, phoneCallObj.f13038i, phoneCallObj.f13039j);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(final C6851a c6851a, int i9) {
            final Friend friendM15001A;
            PhoneCallObj phoneCallObj = this.f11732b.get(i9);
            c6851a.f11734b.setImageResource(phoneCallObj.f13034e ? R.drawable.icon_call_in : R.drawable.icon_call_out);
            if (C5170o0.m20170e(phoneCallObj.f13039j)) {
                friendM15001A = null;
            } else {
                friendM15001A = C2950b0.m14899A().m15001A(phoneCallObj.f13039j);
                if (friendM15001A != null) {
                    phoneCallObj.f13033d = friendM15001A.m15620a();
                }
            }
            if (phoneCallObj.f13035f) {
                c6851a.f11735c.setText(phoneCallObj.f13033d + "(#" + phoneCallObj.f13032c + ")");
            } else {
                c6851a.f11735c.setText(phoneCallObj.f13033d);
            }
            if (phoneCallObj.f13036g) {
                c6851a.f11735c.setTextColor(this.f11731a.getResources().getColor(R.color.you_color_red));
            } else {
                c6851a.f11735c.setTextColor(this.f11731a.getResources().getColor(R.color.black));
            }
            c6851a.f11736d.setText(CLUtility.m16442G2(new Date(phoneCallObj.f13037h)));
            c6851a.f11737e.setText(CLUtility.m16454J2(new Date(phoneCallObj.f13037h)));
            c6851a.itemView.setOnClickListener(new View.OnClickListener() { // from class: g3.b3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f16839b.m13430d(c6851a, friendM15001A, view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public C6851a onCreateViewHolder(ViewGroup viewGroup, int i9) {
            View viewInflate = LayoutInflater.from(this.f11731a).inflate(R.layout.view_item_recent_phone_item, viewGroup, false);
            C6851a c6851a = new C6851a(viewInflate);
            c6851a.f11734b = (ImageView) viewInflate.findViewById(R.id.dialInOrOut);
            c6851a.f11735c = (TextView) viewInflate.findViewById(R.id.dialName);
            c6851a.f11736d = (TextView) viewInflate.findViewById(R.id.dialDate);
            c6851a.f11737e = (TextView) viewInflate.findViewById(R.id.dialTime);
            return c6851a;
        }

        /* renamed from: g */
        public void m13433g(List<PhoneCallObj> list) {
            this.f11732b = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public int getItemCount() {
            return this.f11732b.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m13426o(View view) {
        if (view.getId() != R.id.dial_phone) {
            return;
        }
        startActivity(new Intent(getActivity(), (Class<?>) DialPhoneActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public /* synthetic */ void m13427p(boolean z8) {
        if (isAdded()) {
            m12963k(new Runnable() { // from class: g3.a3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16833b.m13428q();
                }
            });
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_phone;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (i9 == 5001 && i10 == -1) {
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = getContext().getContentResolver().query(intent.getData(), new String[]{"data1", "display_name"}, null, null, null);
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        int columnIndex = cursorQuery.getColumnIndex("data1");
                        int columnIndex2 = cursorQuery.getColumnIndex("display_name");
                        String string = cursorQuery.getString(columnIndex);
                        String string2 = cursorQuery.getString(columnIndex2);
                        if (string.length() != 8 || !Locale.getDefault().getCountry().equals(Locale.TAIWAN.getCountry())) {
                            string = PhoneNumberUtils.formatNumber(string, Locale.getDefault().getCountry());
                        }
                        String str = string;
                        ULogUtility.m16670f("PhoneFragment", "dial number from phone contact : " + string2 + " number : " + str);
                        Group group = new Group();
                        group.f13717d = string2;
                        group.f13716c = "Dual";
                        MeetingActivity.m6395h9(getActivity(), group, true, MimeTypes.BASE_TYPE_AUDIO, "Phone Contact", true, str, string2, true, "");
                    }
                    if (cursorQuery == null) {
                        return;
                    }
                } catch (Exception e9) {
                    Log.e("PhoneFragment", "get phone number exception : " + e9);
                    if (cursorQuery == null) {
                        return;
                    }
                }
                cursorQuery.close();
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View viewInflate = layoutInflater.inflate(R.layout.fragment_phone, viewGroup, false);
        viewInflate.findViewById(R.id.dial_phone).setOnClickListener(this.f11729i);
        this.f11727g = (TextView) viewInflate.findViewById(R.id.recentCallEmptyView);
        this.f11728h = (TextView) viewInflate.findViewById(R.id.noConnectionText);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        XMPPManager.m14184g0().m14235a1(this.f11730j);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        m13428q();
        XMPPManager.m14184g0().m14211K(this.f11730j);
        List<PhoneCallObj> listM15210f = C2950b0.m14919r().m15210f();
        this.f11727g.setVisibility(listM15210f.size() > 0 ? 8 : 0);
        this.f11726f.m13433g(listM15210f);
        this.f11726f.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        AbstractC2555a.a aVar = this.f11504b;
        if (aVar != null) {
            aVar.mo12937q0(null, PageType.Phones);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recentList);
        this.f11726f = new a(getContext(), C2950b0.m14919r().m15210f());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(this.f11726f);
        recyclerView.addItemDecoration(new C0476e(recyclerView.getContext(), 1));
    }

    /* renamed from: q */
    public final void m13428q() {
        boolean zM14204A0 = XMPPManager.m14184g0().m14204A0();
        if (!zM14204A0) {
            if (C6456d.m24714D().m24748G()) {
                String string = getString(R.string.connecting);
                if (Globals.m7388i0().m7534d2()) {
                    string = string + " (" + C6456d.m24714D().m24746E() + ")";
                } else {
                    zM14204A0 = true;
                }
                this.f11728h.setText(string);
            } else {
                this.f11728h.setText(getString(R.string.error_no_network));
            }
        }
        this.f11728h.setVisibility(zM14204A0 ? 8 : 0);
    }
}
