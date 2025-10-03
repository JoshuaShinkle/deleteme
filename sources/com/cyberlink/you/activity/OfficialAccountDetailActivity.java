package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.CorpAccount;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p116k4.C5172p;
import p173q2.C6127a;
import p173q2.C6129c;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class OfficialAccountDetailActivity extends BaseActivity {

    /* renamed from: d */
    public C1575h f8144d;

    /* renamed from: e */
    public CorpAccount f8145e;

    /* renamed from: f */
    public FriendsClient f8146f;

    /* renamed from: g */
    public ListView f8147g;

    /* renamed from: h */
    public View f8148h;

    /* renamed from: i */
    public View f8149i;

    /* renamed from: j */
    public ImageView f8150j;

    /* renamed from: k */
    public ImageView f8151k;

    /* renamed from: l */
    public TextView f8152l;

    /* renamed from: m */
    public TextView f8153m;

    /* renamed from: n */
    public View f8154n;

    /* renamed from: o */
    public View f8155o;

    /* renamed from: p */
    public TextView f8156p;

    /* renamed from: q */
    public TextView f8157q;

    /* renamed from: r */
    public TextView f8158r;

    /* renamed from: s */
    public View f8159s;

    /* renamed from: t */
    public View f8160t;

    /* renamed from: u */
    public TextView f8161u;

    /* renamed from: c */
    public final String f8143c = "OfficAccountDetailACT";

    /* renamed from: v */
    public View.OnClickListener f8162v = new ViewOnClickListenerC1568a();

    /* renamed from: w */
    public View.OnClickListener f8163w = new ViewOnClickListenerC1569b();

    /* renamed from: x */
    public View.OnClickListener f8164x = new ViewOnClickListenerC1570c();

    /* renamed from: y */
    public View.OnClickListener f8165y = new ViewOnClickListenerC1571d();

    /* renamed from: z */
    public AdapterView.OnItemClickListener f8166z = new C1572e();

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$a */
    public class ViewOnClickListenerC1568a implements View.OnClickListener {
        public ViewOnClickListenerC1568a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OfficialAccountDetailActivity.this.f8154n.setVisibility(8);
            OfficialAccountDetailActivity.this.f8153m.setMaxLines(Integer.MAX_VALUE);
            OfficialAccountDetailActivity.this.f8155o.setVisibility(0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$b */
    public class ViewOnClickListenerC1569b implements View.OnClickListener {
        public ViewOnClickListenerC1569b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OfficialAccountDetailActivity.this.m8799C();
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$c */
    public class ViewOnClickListenerC1570c implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$c$a */
        public class a implements FriendsClient.InterfaceC3051i {

            /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$c$a$a, reason: collision with other inner class name */
            public class RunnableC6846a implements Runnable {
                public RunnableC6846a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    OfficialAccountDetailActivity.this.f8159s.setVisibility(8);
                    OfficialAccountDetailActivity.this.f8160t.setVisibility(0);
                    OfficialAccountDetailActivity.this.m8799C();
                }
            }

            public a() {
            }

            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public void mo134a(String str, String str2, String str3, String str4) {
                if ("200".equals(str3)) {
                    Log.d("OfficAccountDetailACT", "[onClick] Follow Success");
                    OfficialAccountDetailActivity.this.m8802z().runOnUiThread(new RunnableC6846a());
                } else {
                    Log.d("OfficAccountDetailACT", "[onClick] Follow Fail, statusCode = " + str3);
                }
            }
        }

        public ViewOnClickListenerC1570c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("inviteeId", Long.toString(OfficialAccountDetailActivity.this.f8145e.f13614c)));
            OfficialAccountDetailActivity.this.f8146f.m15734m("invite", "inviteFriend", arrayList, new a());
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$d */
    public class ViewOnClickListenerC1571d implements View.OnClickListener {
        public ViewOnClickListenerC1571d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OfficialAccountDetailActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$e */
    public class C1572e implements AdapterView.OnItemClickListener {
        public C1572e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            int headerViewsCount = OfficialAccountDetailActivity.this.f8147g.getHeaderViewsCount();
            if (i9 < headerViewsCount) {
                return;
            }
            StickerPackObj stickerPackObj = (StickerPackObj) OfficialAccountDetailActivity.this.f8144d.getItem(i9 - headerViewsCount);
            Intent intent = new Intent(OfficialAccountDetailActivity.this.m8802z(), (Class<?>) StickerShopDetailActivity.class);
            intent.putExtra("stickerPckObj", stickerPackObj);
            intent.putExtra("isPurchased", false);
            OfficialAccountDetailActivity.this.m8802z().startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$f */
    public class AsyncTaskC1573f extends AsyncTask<Void, Void, Boolean> {
        public AsyncTaskC1573f() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            Thread.currentThread().setName("checkAlreadyFollowed AsyncTask");
            return C2950b0.m14899A().m15001A(String.valueOf(OfficialAccountDetailActivity.this.f8145e.f13614c)) != null ? Boolean.TRUE : Boolean.FALSE;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool == null || !bool.booleanValue()) {
                OfficialAccountDetailActivity.this.f8159s.setVisibility(0);
                OfficialAccountDetailActivity.this.f8160t.setVisibility(4);
            } else {
                OfficialAccountDetailActivity.this.f8159s.setVisibility(4);
                OfficialAccountDetailActivity.this.f8160t.setVisibility(0);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$g */
    public class AsyncTaskC1574g extends AsyncTask<Void, Void, List<StickerPackObj>> {

        /* renamed from: a */
        public final /* synthetic */ List f8175a;

        public AsyncTaskC1574g(List list) {
            this.f8175a = list;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<StickerPackObj> doInBackground(Void... voidArr) {
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Long l9 : this.f8175a) {
                StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(l9.longValue());
                if (stickerPackObjM15293k != null) {
                    arrayList.add(stickerPackObjM15293k);
                } else {
                    arrayList2.add(l9);
                }
            }
            if (arrayList2.size() > 0) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(new C6301o("token", strM7449L));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(new C6301o("packId", Long.toString(((Long) it.next()).longValue())));
                }
                Pair<String, String> pairM15731j = OfficialAccountDetailActivity.this.f8146f.m15731j("sticker", "pack.info", arrayList3);
                String str = (String) pairM15731j.first;
                String str2 = (String) pairM15731j.second;
                if (!"200".equals(str)) {
                    Log.d("OfficAccountDetailACT", "statusCode=" + str);
                    return null;
                }
                List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r(str2), false, false);
                if (listM20177D != null) {
                    C2950b0.m14925x().m15289g(listM20177D);
                    arrayList.addAll(listM20177D);
                }
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<StickerPackObj> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            OfficialAccountDetailActivity.this.m8800D(list);
        }
    }

    /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$h */
    public class C1575h extends ArrayAdapter<StickerPackObj> {

        /* renamed from: com.cyberlink.you.activity.OfficialAccountDetailActivity$h$a */
        public class a {

            /* renamed from: a */
            public ImageView f8178a;

            /* renamed from: b */
            public View f8179b;

            /* renamed from: c */
            public TextView f8180c;

            /* renamed from: d */
            public TextView f8181d;

            /* renamed from: e */
            public TextView f8182e;

            /* renamed from: f */
            public View f8183f;

            public a() {
            }

            public /* synthetic */ a(C1575h c1575h, ViewOnClickListenerC1568a viewOnClickListenerC1568a) {
                this();
            }
        }

        public C1575h(Context context, int i9, List<StickerPackObj> list) {
            super(context, i9, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.sticker_shop_item, viewGroup, false);
                aVar = new a(this, null);
                aVar.f8180c = (TextView) view.findViewById(R.id.auther);
                aVar.f8181d = (TextView) view.findViewById(R.id.name);
                aVar.f8182e = (TextView) view.findViewById(R.id.text);
                aVar.f8178a = (ImageView) view.findViewById(R.id.cover);
                aVar.f8179b = view.findViewById(R.id.toDetail);
                aVar.f8183f = view.findViewById(R.id.newIcon);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            StickerPackObj stickerPackObj = (StickerPackObj) getItem(i9);
            if (stickerPackObj.m14812p().f13074b != null) {
                LoadImageUtils.m16641z(getContext(), stickerPackObj, aVar.f8178a, true, true);
            }
            aVar.f8180c.setText(stickerPackObj.m14808l());
            if (stickerPackObj.m14804h() != null) {
                aVar.f8181d.setText(stickerPackObj.m14804h());
            }
            if (stickerPackObj.m14806j().equals("Free")) {
                aVar.f8182e.setText(OfficialAccountDetailActivity.this.getResources().getString(R.string.free_tab));
            } else if (stickerPackObj.m14806j().equals("Purchase")) {
                String strM14800d = stickerPackObj.m14800d();
                if (strM14800d.equals("sticker_1_buck")) {
                    aVar.f8182e.setText(Globals.m7388i0().m7538e1());
                } else if (strM14800d.equals("sticker_2_buck")) {
                    aVar.f8182e.setText(Globals.m7388i0().m7544f1());
                } else {
                    aVar.f8182e.setText(OfficialAccountDetailActivity.this.getResources().getString(R.string.purchase));
                }
            } else if (stickerPackObj.m14806j().equals("Share")) {
                aVar.f8182e.setText(OfficialAccountDetailActivity.this.getResources().getString(R.string.menu_share));
            }
            aVar.f8179b.setTag(stickerPackObj);
            return view;
        }
    }

    /* renamed from: B */
    public final void m8798B(List<Long> list) {
        new AsyncTaskC1574g(list).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: C */
    public final void m8799C() {
        if (this.f8145e != null) {
            Group groupM15081r = null;
            try {
                Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(this.f8145e.f13614c));
                if (friendM15003C != null) {
                    groupM15081r = C2950b0.m14912k().m15081r(friendM15003C.f13648f);
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            if (groupM15081r != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Group", groupM15081r);
                Intent intent = new Intent(this, (Class<?>) ChatDialogActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    /* renamed from: D */
    public final void m8800D(List<StickerPackObj> list) {
        if (list == null || list.size() == 0) {
            this.f8161u.setVisibility(8);
            return;
        }
        this.f8161u.setVisibility(0);
        this.f8161u.setText(String.format(getResources().getString(R.string.stickers_from), this.f8145e.f13616e));
        this.f8144d.clear();
        this.f8144d.addAll(list);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_official_account_detail);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        CorpAccount corpAccount = (CorpAccount) intent.getParcelableExtra("data");
        this.f8145e = corpAccount;
        if (corpAccount == null) {
            finish();
            return;
        }
        this.f8146f = new FriendsClient(true);
        this.f8148h = findViewById(R.id.OfficialAccountDetailBackBtn);
        this.f8147g = (ListView) findViewById(R.id.OfficialAccountDetailListView);
        View viewInflate = getLayoutInflater().inflate(R.layout.official_account_detail_info, (ViewGroup) this.f8147g, false);
        this.f8149i = viewInflate;
        if (viewInflate != null) {
            this.f8147g.addHeaderView(viewInflate, null, false);
            this.f8150j = (ImageView) this.f8149i.findViewById(R.id.official_account_detail_info_cover);
            this.f8151k = (ImageView) this.f8149i.findViewById(R.id.official_account_detail_info_avatar);
            this.f8152l = (TextView) this.f8149i.findViewById(R.id.official_account_detail_info_title);
            this.f8153m = (TextView) this.f8149i.findViewById(R.id.official_account_detail_info_description);
            this.f8154n = this.f8149i.findViewById(R.id.official_account_detail_info_btn_detail_description);
            this.f8155o = this.f8149i.findViewById(R.id.official_account_detail_info_contact_area);
            this.f8156p = (TextView) this.f8149i.findViewById(R.id.official_account_detail_info_phone_text);
            this.f8157q = (TextView) this.f8149i.findViewById(R.id.official_account_detail_info_address_text);
            this.f8158r = (TextView) this.f8149i.findViewById(R.id.official_account_detail_info_website_text);
            this.f8159s = this.f8149i.findViewById(R.id.official_account_detail_info_follow_btn);
            this.f8160t = this.f8149i.findViewById(R.id.official_account_detail_info_chat_btn);
            this.f8161u = (TextView) this.f8149i.findViewById(R.id.official_account_detail_sticker_title);
            this.f8154n.setOnClickListener(this.f8162v);
            this.f8159s.setOnClickListener(this.f8164x);
            this.f8160t.setOnClickListener(this.f8163w);
            m8801y();
            DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
            this.f8150j.getLayoutParams().width = displayMetrics.widthPixels;
            this.f8150j.getLayoutParams().height = (int) m8802z().getResources().getDimension(R.dimen.corporate_account_detail_cover_height);
            C6129c.m23480a(this, this.f8150j, this.f8145e);
            C6127a.m23467h(this, this.f8151k, this.f8145e);
            this.f8152l.setText(this.f8145e.f13616e);
            this.f8153m.setText(this.f8145e.f13615d);
            String str = this.f8145e.f13622k;
            if (str == null || str.isEmpty()) {
                findViewById(R.id.official_account_detail_info_phone_title).setVisibility(8);
                this.f8156p.setVisibility(8);
            } else {
                this.f8156p.setText(this.f8145e.f13622k);
            }
            String str2 = this.f8145e.f13621j;
            if (str2 == null || str2.isEmpty()) {
                findViewById(R.id.official_account_detail_info_address_title).setVisibility(8);
                this.f8157q.setVisibility(8);
            } else {
                this.f8157q.setText(this.f8145e.f13621j);
            }
            String str3 = this.f8145e.f13623l;
            if (str3 == null || str3.isEmpty()) {
                findViewById(R.id.official_account_detail_info_website_title).setVisibility(8);
                this.f8158r.setVisibility(8);
            } else {
                this.f8158r.setText(this.f8145e.f13623l);
                CLUtility.m16543i(this.f8158r);
            }
        }
        C1575h c1575h = new C1575h(m8802z(), R.layout.sticker_shop_item, new ArrayList());
        this.f8144d = c1575h;
        this.f8147g.setAdapter((ListAdapter) c1575h);
        this.f8147g.setOnItemClickListener(this.f8166z);
        this.f8148h.setOnClickListener(this.f8165y);
        m8798B(this.f8145e.m15615a());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8148h.setOnClickListener(null);
        this.f8154n.setOnClickListener(null);
        this.f8159s.setOnClickListener(null);
        this.f8147g.setOnItemClickListener(null);
        this.f8147g.removeHeaderView(this.f8149i);
        FriendsClient friendsClient = this.f8146f;
        if (friendsClient != null) {
            friendsClient.m15717U0();
            this.f8146f = null;
        }
        super.onDestroy();
    }

    /* renamed from: y */
    public final void m8801y() {
        new AsyncTaskC1573f().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: z */
    public final Activity m8802z() {
        return this;
    }
}
