package com.cyberlink.meeting.page;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.tokenautocomplete.PeopleCompleteView;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p042d0.C4619d;
import p116k4.C5170o0;
import p116k4.C5178r;
import p116k4.C5187v0;
import p132m.C5305d;
import p173q2.C6127a;
import p209u2.AbstractC6381r;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class InviteMeetingActivity extends BaseActivity {

    /* renamed from: c */
    public List<Friend> f6363c;

    /* renamed from: d */
    public List<Friend> f6364d;

    /* renamed from: e */
    public List<Friend> f6365e;

    /* renamed from: f */
    public List<Friend> f6366f;

    /* renamed from: g */
    public FriendsClient f6367g;

    /* renamed from: h */
    public String f6368h;

    /* renamed from: i */
    public String f6369i;

    /* renamed from: j */
    public String f6370j;

    /* renamed from: k */
    public String f6371k;

    /* renamed from: l */
    public String f6372l;

    /* renamed from: m */
    public ListView f6373m;

    /* renamed from: n */
    public View f6374n;

    /* renamed from: o */
    public PeopleCompleteView f6375o;

    /* renamed from: p */
    public Button f6376p;

    /* renamed from: q */
    public Dialog f6377q;

    /* renamed from: s */
    public String f6379s;

    /* renamed from: t */
    public C1276j f6380t;

    /* renamed from: w */
    public C1260a f6383w;

    /* renamed from: x */
    public boolean f6384x;

    /* renamed from: y */
    public String f6385y;

    /* renamed from: z */
    public boolean f6386z;

    /* renamed from: r */
    public int f6378r = 0;

    /* renamed from: u */
    public C4619d f6381u = null;

    /* renamed from: v */
    public C5305d<Boolean> f6382v = new C5305d<>();

    /* renamed from: A */
    public View.OnClickListener f6359A = new View.OnClickListener() { // from class: o2.g
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f18327b.m5777k0(view);
        }
    };

    /* renamed from: B */
    public AdapterView.OnItemClickListener f6360B = new C1270d();

    /* renamed from: C */
    public View.OnClickListener f6361C = new ViewOnClickListenerC1271e();

    /* renamed from: D */
    public View.OnClickListener f6362D = new View.OnClickListener() { // from class: o2.h
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f18332b.m5779l0(view);
        }
    };

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$a */
    public class C1267a extends GestureDetector.SimpleOnGestureListener {
        public C1267a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(InviteMeetingActivity.this);
            return false;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$b */
    public class C1268b implements TokenCompleteTextView.InterfaceC3249e<Friend> {
        public C1268b() {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: a */
        public void mo5801a(String str) {
            if (InviteMeetingActivity.this.f6380t != null) {
                InviteMeetingActivity.this.f6380t.getFilter().filter(str);
            }
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void mo5802b(Friend friend) {
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void mo5803c(Friend friend) {
            InviteMeetingActivity.this.f6382v.m20726j(friend.f13645c, Boolean.FALSE);
            InviteMeetingActivity.this.f6366f.remove(friend);
            InviteMeetingActivity.this.f6380t.notifyDataSetChanged();
            InviteMeetingActivity.m5766O(InviteMeetingActivity.this);
            InviteMeetingActivity.this.m5800p0();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$c */
    public class AsyncTaskC1269c extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ List f6389a;

        public AsyncTaskC1269c(List list) {
            this.f6389a = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ boolean m5807c(View view, MotionEvent motionEvent) {
            if (InviteMeetingActivity.this.f6381u == null) {
                return false;
            }
            return InviteMeetingActivity.this.f6381u.m18406a(motionEvent);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            List<Friend> listM15026r = C2950b0.m14899A().m15026r();
            Collections.sort(listM15026r, new Friend.C3041b());
            InviteMeetingActivity.this.f6365e = listM15026r;
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r62) {
            InviteMeetingActivity inviteMeetingActivity = InviteMeetingActivity.this;
            InviteMeetingActivity inviteMeetingActivity2 = InviteMeetingActivity.this;
            inviteMeetingActivity.f6380t = inviteMeetingActivity2.new C1276j(inviteMeetingActivity2, inviteMeetingActivity2.f6365e);
            InviteMeetingActivity.this.f6373m.setAdapter((ListAdapter) InviteMeetingActivity.this.f6380t);
            InviteMeetingActivity.this.f6373m.setOnItemClickListener(InviteMeetingActivity.this.f6360B);
            InviteMeetingActivity.this.f6373m.setOnTouchListener(new View.OnTouchListener() { // from class: o2.i
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f18338b.m5807c(view, motionEvent);
                }
            });
            List list = this.f6389a;
            if (list != null && list.size() > 0) {
                for (Friend friend : this.f6389a) {
                    if (!InviteMeetingActivity.this.f6366f.contains(friend)) {
                        InviteMeetingActivity.m5765N(InviteMeetingActivity.this);
                        InviteMeetingActivity.this.f6382v.m20726j(friend.f13645c, Boolean.TRUE);
                        InviteMeetingActivity.this.f6375o.m17434p(friend);
                        InviteMeetingActivity.this.f6366f.add(friend);
                    }
                }
            }
            InviteMeetingActivity.this.m5800p0();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$d */
    public class C1270d implements AdapterView.OnItemClickListener {
        public C1270d() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C1277k c1277k = (C1277k) view.getTag();
            if (c1277k.f6408c.isEnabled()) {
                Long lValueOf = Long.valueOf(InviteMeetingActivity.this.f6380t.getItem(i9).f13645c);
                boolean zM5797j0 = InviteMeetingActivity.this.m5797j0(lValueOf.longValue());
                InviteMeetingActivity.this.f6382v.m20726j(lValueOf.longValue(), Boolean.valueOf(!zM5797j0));
                c1277k.f6408c.setChecked(!zM5797j0);
                Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(lValueOf));
                if (zM5797j0) {
                    InviteMeetingActivity.this.f6366f.remove(friendM15003C);
                    InviteMeetingActivity.this.f6375o.m17426S(friendM15003C);
                } else if (InviteMeetingActivity.this.f6363c == null || !InviteMeetingActivity.this.f6363c.contains(friendM15003C)) {
                    InviteMeetingActivity.m5765N(InviteMeetingActivity.this);
                    InviteMeetingActivity.this.f6366f.add(friendM15003C);
                    InviteMeetingActivity.this.f6375o.m17434p(friendM15003C);
                }
                InviteMeetingActivity.this.m5800p0();
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$e */
    public class ViewOnClickListenerC1271e implements View.OnClickListener {
        public ViewOnClickListenerC1271e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m5811b() {
            CLUtility.m16589t1(InviteMeetingActivity.this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InviteMeetingActivity.this.runOnUiThread(new Runnable() { // from class: o2.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f18344b.m5811b();
                }
            });
            Intent intent = new Intent();
            ArrayList arrayList = new ArrayList(InviteMeetingActivity.this.f6382v.m20730n());
            for (int i9 = 0; i9 < InviteMeetingActivity.this.f6382v.m20730n(); i9++) {
                long jM20725i = InviteMeetingActivity.this.f6382v.m20725i(i9);
                if (InviteMeetingActivity.this.m5797j0(jM20725i)) {
                    Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(jM20725i));
                    boolean z8 = InviteMeetingActivity.this.f6363c != null && InviteMeetingActivity.this.f6363c.contains(friendM15003C);
                    if (friendM15003C != null && !z8) {
                        arrayList.add(friendM15003C);
                    }
                }
            }
            InviteMeetingActivity.this.m5799o0(arrayList);
            InviteMeetingActivity.this.setResult(-1, intent);
            InviteMeetingActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$f */
    public class C1272f extends AbstractC6381r<Group, Void> {

        /* renamed from: c */
        public final /* synthetic */ Friend f6393c;

        /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$f$a */
        public class a implements XMPPManager.InterfaceC2873x {

            /* renamed from: a */
            public final /* synthetic */ Group f6395a;

            public a(Group group) {
                this.f6395a = group;
            }

            @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
            /* renamed from: a */
            public void mo5816a() {
                Log.e("InviteParticipants", "send invite message fail : " + this.f6395a.f13717d);
            }

            @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
            public void onSuccess() {
                Log.v("InviteParticipants", "send invite message success : " + this.f6395a.f13717d);
            }
        }

        public C1272f(Friend friend) {
            this.f6393c = friend;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            Log.v("InviteParticipants", "getOrCreateGroup success :  " + this.f6393c.m15620a());
            InviteMeetingActivity.this.f6383w.m5690D(group, InviteMeetingActivity.this.f6368h, InviteMeetingActivity.this.f6369i, InviteMeetingActivity.this.f6371k, InviteMeetingActivity.this.f6372l, new a(group));
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r22) {
            Log.e("InviteParticipants", "getOrCreateGroup fail :  " + this.f6393c.m15620a());
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$g */
    public class ViewOnClickListenerC1273g implements View.OnClickListener {
        public ViewOnClickListenerC1273g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InviteMeetingActivity.this.f6377q.dismiss();
            InviteMeetingActivity.this.m5796i0();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$h */
    public class ViewOnClickListenerC1274h implements View.OnClickListener {
        public ViewOnClickListenerC1274h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            InviteMeetingActivity.this.f6377q.dismiss();
            ClipboardManager clipboardManager = (ClipboardManager) InviteMeetingActivity.this.getSystemService("clipboard");
            if (InviteMeetingActivity.this.m5793d0() && InviteMeetingActivity.this.f6384x) {
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(InviteMeetingActivity.this);
                String str2 = String.format(InviteMeetingActivity.this.getString(R.string.clm_invite_email_body_phone_line_prefix), userInfoM16497V0 != null ? userInfoM16497V0.f13778c : "");
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                InviteMeetingActivity inviteMeetingActivity = InviteMeetingActivity.this;
                sb.append(inviteMeetingActivity.getString(R.string.clm_invite_email_body_phone_line_via_u, inviteMeetingActivity.f6370j));
                String string = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                InviteMeetingActivity inviteMeetingActivity2 = InviteMeetingActivity.this;
                sb2.append(inviteMeetingActivity2.getString(R.string.clm_invite_email_body_phone_line_via_phone, CLUtility.m16456K0(inviteMeetingActivity2), C5170o0.m20172g(InviteMeetingActivity.this.f6368h, 3, '-')));
                str = sb2.toString() + InviteMeetingActivity.this.getString(R.string.clm_invite_email_body_phone_line_suffix, CLUtility.m16565n1());
            } else {
                str = !C5170o0.m20170e(InviteMeetingActivity.this.f6385y) ? String.format(InviteMeetingActivity.this.getString(R.string.clm_invite_email_body_new_with_faq_password), InviteMeetingActivity.this.f6370j, InviteMeetingActivity.this.f6385y, CLUtility.m16565n1()) : String.format(InviteMeetingActivity.this.getString(R.string.clm_invite_email_body_new_with_faq), InviteMeetingActivity.this.f6370j, CLUtility.m16565n1());
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, Html.fromHtml(str, 0)));
            C5187v0.m20267c(R.string.clm_meeting_invite_copy_invitation_done);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$i */
    public class ViewOnClickListenerC1275i implements View.OnClickListener {
        public ViewOnClickListenerC1275i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InviteMeetingActivity.this.f6377q.dismiss();
            ((ClipboardManager) InviteMeetingActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, InviteMeetingActivity.this.f6370j));
            C5187v0.m20267c(R.string.clm_meeting_invite_copy_url_done);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$j */
    public class C1276j extends BaseAdapter implements Filterable {

        /* renamed from: b */
        public Context f6400b;

        /* renamed from: c */
        public List<Friend> f6401c;

        /* renamed from: d */
        public List<Friend> f6402d;

        /* renamed from: e */
        public a f6403e;

        /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$j$a */
        public class a extends Filter {
            public a() {
            }

            @Override // android.widget.Filter
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                for (Friend friend : C1276j.this.f6401c) {
                    if (friend.m15621b().toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                        arrayList.add(friend);
                    }
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
                return filterResults;
            }

            @Override // android.widget.Filter
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                C1276j.this.f6402d = (ArrayList) filterResults.values;
                C1276j.this.notifyDataSetChanged();
                if (C1276j.this.f6402d.size() > 0) {
                    InviteMeetingActivity.this.f6374n.setVisibility(8);
                } else {
                    InviteMeetingActivity.this.f6374n.setVisibility(0);
                }
            }

            public /* synthetic */ a(C1276j c1276j, C1267a c1267a) {
                this();
            }
        }

        public C1276j(Context context, List<Friend> list) {
            this.f6400b = context;
            this.f6401c = list;
            this.f6402d = list;
        }

        @Override // android.widget.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Friend getItem(int i9) {
            return this.f6402d.get(i9);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f6402d.size();
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            if (this.f6403e == null) {
                this.f6403e = new a(this, null);
            }
            return this.f6403e;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1277k c1277k;
            boolean z8 = false;
            if (view == null) {
                view = ((LayoutInflater) this.f6400b.getSystemService("layout_inflater")).inflate(R.layout.view_item_firend_group_create, viewGroup, false);
                c1277k = new C1277k(InviteMeetingActivity.this, null);
                c1277k.f6407b = (TextView) view.findViewById(R.id.GroupCreateMemberName);
                c1277k.f6406a = (ImageView) view.findViewById(R.id.GroupCreateImageView);
                c1277k.f6408c = (CheckBox) view.findViewById(R.id.GroupCreateMemberCheckBox);
                view.setTag(c1277k);
            } else {
                c1277k = (C1277k) view.getTag();
            }
            Friend friend = this.f6402d.get(i9);
            if (friend.m15621b() != null) {
                c1277k.f6407b.setText(friend.m15621b());
            }
            C6127a.m23469j(InviteMeetingActivity.this, c1277k.f6406a, friend);
            c1277k.f6408c.setChecked(InviteMeetingActivity.this.m5797j0(friend.f13645c));
            if (InviteMeetingActivity.this.f6363c != null && InviteMeetingActivity.this.f6363c.contains(friend)) {
                z8 = true;
            }
            c1277k.f6408c.setEnabled(!z8);
            return view;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.InviteMeetingActivity$k */
    public class C1277k {

        /* renamed from: a */
        public ImageView f6406a;

        /* renamed from: b */
        public TextView f6407b;

        /* renamed from: c */
        public CheckBox f6408c;

        public C1277k() {
        }

        public /* synthetic */ C1277k(InviteMeetingActivity inviteMeetingActivity, C1267a c1267a) {
            this();
        }
    }

    /* renamed from: N */
    public static /* synthetic */ int m5765N(InviteMeetingActivity inviteMeetingActivity) {
        int i9 = inviteMeetingActivity.f6378r;
        inviteMeetingActivity.f6378r = i9 + 1;
        return i9;
    }

    /* renamed from: O */
    public static /* synthetic */ int m5766O(InviteMeetingActivity inviteMeetingActivity) {
        int i9 = inviteMeetingActivity.f6378r;
        inviteMeetingActivity.f6378r = i9 - 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m5777k0(View view) {
        m5798n0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m5779l0(View view) {
        if (this.f6377q == null) {
            Dialog dialogM16384c = C3123g.m16384c(this);
            this.f6377q = dialogM16384c;
            dialogM16384c.setContentView(R.layout.dialog_meeting_invite_more);
            this.f6377q.findViewById(R.id.item_invite_by_email).setOnClickListener(new ViewOnClickListenerC1273g());
            this.f6377q.findViewById(R.id.item_copy_invitation).setOnClickListener(new ViewOnClickListenerC1274h());
            this.f6377q.findViewById(R.id.item_copy_url).setOnClickListener(new ViewOnClickListenerC1275i());
        }
        CLUtility.m16578q2(this, this.f6377q);
        this.f6377q.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m5780m0() {
        CLUtility.m16589t1(this);
    }

    /* renamed from: b0 */
    public final boolean m5791b0() {
        return this.f6366f.size() > 0;
    }

    /* renamed from: c0 */
    public final void m5792c0() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f6379s = extras.getString("INTENT_TITLE", getString(R.string.clm_invite_page_title));
            this.f6364d = extras.getParcelableArrayList("INTENT_PREV_SELECTED_USERS_LIST");
            this.f6363c = extras.getParcelableArrayList("INTENT_CANNOT_MODIFIED_USERS_LIST");
            this.f6368h = extras.getString("meetingId");
            this.f6369i = extras.getString("type");
            this.f6370j = extras.getString("meetingShareUrl");
            this.f6371k = extras.getString("meetingMServerAddress");
            this.f6372l = extras.getString("meetingMServerToken");
            this.f6386z = extras.getBoolean("isLtiMeeting");
            this.f6384x = extras.getBoolean("canSendPSTNInvitation", true);
            this.f6385y = extras.getString("password", "");
            if (this.f6363c == null) {
                this.f6363c = new ArrayList();
            }
        }
    }

    /* renamed from: d0 */
    public final boolean m5793d0() {
        for (String str : Globals.m7388i0().m7455M0().split(",")) {
            if ("PSTN".equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e0 */
    public final void m5794e0(List<Friend> list) {
        new AsyncTaskC1269c(list).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: g0 */
    public final void m5795g0() {
        ((TextView) findViewById(R.id.textViewTitle)).setText(this.f6379s);
        PeopleCompleteView peopleCompleteView = (PeopleCompleteView) findViewById(R.id.token_complete);
        this.f6375o = peopleCompleteView;
        peopleCompleteView.m17442x(null);
        this.f6375o.setTokenListener(new C1268b());
        this.f6375o.setPrefix(getString(R.string.group_add_member_search_hint));
        findViewById(R.id.barLayout).setVisibility(0);
        this.f6373m = (ListView) findViewById(R.id.MeetingInviteListView);
        findViewById(R.id.MeetingInviteBackBtn).setOnClickListener(this.f6359A);
        Button button = (Button) findViewById(R.id.buttonInvite);
        this.f6376p = button;
        button.setOnClickListener(this.f6361C);
        this.f6374n = findViewById(R.id.MeetingInviteSearchEmptyView);
        ImageView imageView = (ImageView) findViewById(R.id.MeetingInviteMoreBtn);
        if (this.f6386z) {
            imageView.setVisibility(8);
        } else {
            imageView.setOnClickListener(this.f6362D);
        }
    }

    /* renamed from: i0 */
    public final void m5796i0() {
        String str;
        String str2;
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
        if (userInfoM16497V0 == null || (str = userInfoM16497V0.f13778c) == null) {
            str = "";
        }
        String str3 = String.format(getString(R.string.clm_invite_email_title_new), str);
        if (m5793d0() && this.f6384x) {
            UserInfo userInfoM16497V02 = CLUtility.m16497V0(this);
            str2 = ((String.format(getString(R.string.clm_invite_email_body_phone_line_prefix), userInfoM16497V02 != null ? userInfoM16497V02.f13778c : "") + getString(R.string.clm_invite_email_body_phone_line_via_u, this.f6370j)) + getString(R.string.clm_invite_email_body_phone_line_via_phone, CLUtility.m16456K0(this), C5170o0.m20172g(this.f6368h, 3, '-'))) + getString(R.string.clm_invite_email_body_phone_line_suffix, CLUtility.m16565n1());
        } else {
            str2 = !C5170o0.m20170e(this.f6385y) ? String.format(getString(R.string.clm_invite_email_body_new_with_faq_password), this.f6370j, this.f6385y, CLUtility.m16565n1()) : String.format(getString(R.string.clm_invite_email_body_new_with_faq), this.f6370j, CLUtility.m16565n1());
        }
        try {
            startActivity(C5178r.m20236e(str3, str2));
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.invite_email_no_client);
        }
    }

    /* renamed from: j0 */
    public final boolean m5797j0(long j9) {
        Boolean boolM20722e = this.f6382v.m20722e(j9);
        return boolM20722e != null && boolM20722e.booleanValue();
    }

    /* renamed from: n0 */
    public final void m5798n0() {
        runOnUiThread(new Runnable() { // from class: o2.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f18322b.m5780m0();
            }
        });
        setResult(0, new Intent());
        finish();
    }

    /* renamed from: o0 */
    public final void m5799o0(ArrayList<Friend> arrayList) {
        Iterator<Friend> it = arrayList.iterator();
        while (it.hasNext()) {
            Friend next = it.next();
            this.f6367g.m15719Z(next, new C1272f(next));
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m5798n0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_chat_add_member_in_meeting);
        this.f6383w = new C1260a(this);
        this.f6367g = new FriendsClient(true);
        this.f6381u = new C4619d(this, new C1267a());
        this.f6366f = new ArrayList(this.f6382v.m20730n());
        m5792c0();
        m5795g0();
        m5794e0((bundle == null || !bundle.containsKey("SELECT_USER")) ? null : bundle.getParcelableArrayList("SELECT_USER"));
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f6375o.setTokenListener(null);
        this.f6373m.setOnTouchListener(null);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        List<Friend> list = this.f6366f;
        if (list != null && list.size() > 0) {
            bundle.putParcelableArrayList("SELECT_USER", new ArrayList<>(this.f6366f));
        }
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: p0 */
    public final void m5800p0() {
        if (this.f6378r > 0) {
            this.f6376p.setText(getString(R.string.invitation_sms_invite_btn) + " (" + this.f6378r + ")");
            this.f6376p.setEnabled(true);
        } else {
            this.f6376p.setText(R.string.invitation_sms_invite_btn);
            this.f6376p.setEnabled(false);
        }
        if (this.f6364d != null && this.f6366f != null) {
            if (!m5791b0()) {
                this.f6376p.setText(R.string.invitation_sms_invite_btn);
            }
            this.f6376p.setEnabled(m5791b0());
        }
        Log.d("InviteParticipants", "mInviteButton enable = " + this.f6376p.isEnabled());
    }
}
