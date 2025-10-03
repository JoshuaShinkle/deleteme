package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5131b0;
import p116k4.C5187v0;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class InvitationSMSActivity extends BaseActivity {

    /* renamed from: e */
    public ListView f7956e;

    /* renamed from: f */
    public EditText f7957f;

    /* renamed from: g */
    public View f7958g;

    /* renamed from: h */
    public View f7959h;

    /* renamed from: i */
    public TextView f7960i;

    /* renamed from: j */
    public ArrayList<HashMap<String, String>> f7961j;

    /* renamed from: k */
    public ArrayList<HashMap<String, String>> f7962k;

    /* renamed from: l */
    public BaseAdapter f7963l;

    /* renamed from: m */
    public Group f7964m;

    /* renamed from: n */
    public C1536g f7965n;

    /* renamed from: o */
    public FriendsClient f7966o;

    /* renamed from: c */
    public final String f7954c = "PHONE";

    /* renamed from: d */
    public final String f7955d = "NAME";

    /* renamed from: p */
    public boolean f7967p = false;

    /* renamed from: q */
    public TextWatcher f7968q = new C1534e();

    /* renamed from: r */
    public AbsListView.OnScrollListener f7969r = new C1535f();

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$a */
    public class ViewOnClickListenerC1530a implements View.OnClickListener {
        public ViewOnClickListenerC1530a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16602w2(InvitationSMSActivity.this.m8622D(), false);
            InvitationSMSActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$b */
    public class C1531b extends BaseAdapter {

        /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$b$a */
        public class a implements View.OnClickListener {

            /* renamed from: b */
            public final /* synthetic */ int f7972b;

            public a(int i9) {
                this.f7972b = i9;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string;
                SharedPreferences sharedPreferences = InvitationSMSActivity.this.getApplicationContext().getSharedPreferences("U", 0);
                if (InvitationSMSActivity.this.f7967p) {
                    string = null;
                    try {
                        string = C2950b0.m14912k().m15077n(Long.toString(InvitationSMSActivity.this.f7964m.f13727n)).f13707F;
                        Log.d("InvitationSMSActivity", "[getFriendLink] inviteGroupLink = " + string);
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                } else {
                    string = sharedPreferences.getString("inviteFriendLink", "");
                }
                String string2 = ((String) ((HashMap) InvitationSMSActivity.this.f7962k.get(this.f7972b)).get("PHONE")).toString();
                if (string == null || string.isEmpty()) {
                    InvitationSMSActivity.this.m8623E(string2);
                } else {
                    InvitationSMSActivity.this.m8625I(string2, string);
                }
            }
        }

        public C1531b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (InvitationSMSActivity.this.f7962k == null) {
                return 0;
            }
            return InvitationSMSActivity.this.f7962k.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            return InvitationSMSActivity.this.f7962k.get(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1537h c1537h;
            if (view == null) {
                view = ((LayoutInflater) InvitationSMSActivity.this.m8622D().getSystemService("layout_inflater")).inflate(R.layout.view_item_invite_via_someway, viewGroup, false);
                c1537h = new C1537h(InvitationSMSActivity.this, null);
                c1537h.f7986d = (Button) view.findViewById(R.id.InvitationViaSomeWayBtn);
                c1537h.f7983a = (ImageView) view.findViewById(R.id.InvitationViaSomeWayImage);
                c1537h.f7984b = (TextView) view.findViewById(R.id.InvitationViaSomeWayDisplayName);
                c1537h.f7985c = (TextView) view.findViewById(R.id.InvitationViaSomeWayData);
                view.setTag(c1537h);
            } else {
                c1537h = (C1537h) view.getTag();
            }
            HashMap map = (HashMap) getItem(i9);
            ImageView imageView = c1537h.f7983a;
            if (c1537h.f7984b != null) {
                c1537h.f7984b.setText((String) map.get("NAME"));
            }
            if (c1537h.f7985c != null) {
                c1537h.f7985c.setText((String) map.get("PHONE"));
            }
            Button button = c1537h.f7986d;
            if (button != null) {
                button.setOnClickListener(new a(i9));
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$c */
    public class C1532c implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final /* synthetic */ String f7974a;

        public C1532c(String str) {
            this.f7974a = str;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws JSONException {
            if (str3 == null) {
                Log.d("InvitationSMSActivity", "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("InvitationSMSActivity", "statusCode=" + str3);
                return;
            }
            String string = null;
            try {
                string = new JSONObject(str4).getString("inviteURL");
                if (InvitationSMSActivity.this.f7967p) {
                    InvitationSMSActivity.this.f7964m.f13707F = string;
                    Log.d("InvitationSMSActivity", "[getFriendLink] Renew inviteGroupLink = " + InvitationSMSActivity.this.f7964m.f13707F + ", update to database.");
                    C2950b0.m14912k().m15062A(String.valueOf(InvitationSMSActivity.this.f7964m.f13727n), InvitationSMSActivity.this.f7964m, "inviteGroupLink");
                } else {
                    InvitationSMSActivity.this.getApplicationContext().getSharedPreferences("U", 0).edit().putString("inviteFriendLink", string).apply();
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            InvitationSMSActivity.this.m8625I(this.f7974a, string);
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$d */
    public class RunnableC1533d implements Runnable {
        public RunnableC1533d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            InvitationSMSActivity.this.f7963l.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$e */
    public class C1534e implements TextWatcher {
        public C1534e() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            InvitationSMSActivity.this.f7965n.filter(charSequence.toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$f */
    public class C1535f implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f7978a = false;

        /* renamed from: b */
        public int f7979b = -1;

        /* renamed from: c */
        public String f7980c = null;

        public C1535f() {
        }

        /* renamed from: a */
        public final String m8627a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m8628b() {
            if (InvitationSMSActivity.this.f7959h != null && InvitationSMSActivity.this.f7959h.isShown()) {
                InvitationSMSActivity.this.f7959h.setVisibility(4);
            }
            m8629c();
        }

        /* renamed from: c */
        public final void m8629c() {
            this.f7979b = -1;
            this.f7980c = null;
        }

        /* renamed from: d */
        public final void m8630d() {
            HashMap map = (HashMap) InvitationSMSActivity.this.f7956e.getItemAtPosition(InvitationSMSActivity.this.f7956e.getFirstVisiblePosition());
            if (map == null || !map.containsKey("NAME")) {
                return;
            }
            String str = (String) map.get("NAME");
            if (InvitationSMSActivity.this.f7960i != null) {
                String strM8627a = m8627a(str);
                String str2 = this.f7980c;
                if (str2 == null || !strM8627a.equals(str2)) {
                    InvitationSMSActivity.this.f7960i.setText(strM8627a);
                    m8631e();
                    this.f7980c = strM8627a;
                }
            }
        }

        /* renamed from: e */
        public final void m8631e() {
            if (InvitationSMSActivity.this.f7959h != null && !InvitationSMSActivity.this.f7959h.isShown()) {
                InvitationSMSActivity.this.f7959h.setVisibility(0);
            }
            m8629c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f7978a && this.f7979b != (firstVisiblePosition = InvitationSMSActivity.this.f7956e.getFirstVisiblePosition())) {
                this.f7979b = firstVisiblePosition;
                m8630d();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f7978a = false;
                m8628b();
            } else if (i9 == 1) {
                this.f7978a = true;
                m8629c();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$g */
    public class C1536g extends Filter {
        public C1536g() {
        }

        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            String lowerCase = charSequence.toString().toLowerCase(Locale.getDefault());
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList arrayList = new ArrayList();
            Iterator it = InvitationSMSActivity.this.f7961j.iterator();
            while (it.hasNext()) {
                HashMap map = (HashMap) it.next();
                if (((String) map.get("PHONE")).toLowerCase(Locale.getDefault()).contains(lowerCase) || ((String) map.get("NAME")).toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    arrayList.add(map);
                }
            }
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            InvitationSMSActivity.this.f7962k = (ArrayList) filterResults.values;
            if (InvitationSMSActivity.this.f7962k != null) {
                InvitationSMSActivity.this.m8626i();
                if (InvitationSMSActivity.this.f7962k.size() > 0) {
                    InvitationSMSActivity.this.f7958g.setVisibility(8);
                } else {
                    InvitationSMSActivity.this.f7958g.setVisibility(0);
                }
            }
        }

        public /* synthetic */ C1536g(InvitationSMSActivity invitationSMSActivity, ViewOnClickListenerC1530a viewOnClickListenerC1530a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationSMSActivity$h */
    public class C1537h {

        /* renamed from: a */
        public ImageView f7983a;

        /* renamed from: b */
        public TextView f7984b;

        /* renamed from: c */
        public TextView f7985c;

        /* renamed from: d */
        public Button f7986d;

        public C1537h() {
        }

        public /* synthetic */ C1537h(InvitationSMSActivity invitationSMSActivity, ViewOnClickListenerC1530a viewOnClickListenerC1530a) {
            this();
        }
    }

    /* renamed from: D */
    public final Activity m8622D() {
        return this;
    }

    /* renamed from: E */
    public final void m8623E(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (this.f7967p) {
            arrayList.add(new C6301o("groupId", Long.toString(this.f7964m.f13727n)));
            str2 = "group";
        } else {
            str2 = "invite";
        }
        this.f7966o.m15734m(str2, "genInviteURL", arrayList, new C1532c(str));
    }

    /* renamed from: H */
    public final void m8624H() {
        this.f7956e = (ListView) findViewById(R.id.PhoneListView);
        ArrayList<HashMap<String, String>> arrayListM20005c = C5131b0.m20005c("NAME", "PHONE", this);
        this.f7961j = arrayListM20005c;
        this.f7962k = arrayListM20005c;
        C1531b c1531b = new C1531b();
        this.f7963l = c1531b;
        this.f7956e.setAdapter((ListAdapter) c1531b);
        this.f7956e.setOnScrollListener(this.f7969r);
        this.f7965n.filter(this.f7957f.getText().toString());
    }

    /* renamed from: I */
    public final void m8625I(String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", this.f7967p ? String.format(getString(R.string.group_invite_sms_new), this.f7964m.f13717d, str2) : String.format(getString(R.string.invite_sms_new), CLUtility.m16497V0(m8622D()).f13778c, str2));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.error_no_sms_client);
        }
    }

    /* renamed from: i */
    public final void m8626i() {
        m8622D().runOnUiThread(new RunnableC1533d());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_sms);
        findViewById(R.id.InvitationSmsBackBtn).setOnClickListener(new ViewOnClickListenerC1530a());
        this.f7958g = findViewById(R.id.InvitationSmsSearchEmptyView);
        this.f7965n = new C1536g(this, null);
        this.f7966o = new FriendsClient();
        EditText editText = (EditText) findViewById(R.id.SearchEditText);
        this.f7957f = editText;
        editText.setHint(getResources().getString(R.string.search));
        this.f7957f.addTextChangedListener(this.f7968q);
        this.f7959h = findViewById(R.id.AlphabeticScrollTextLayout);
        this.f7960i = (TextView) findViewById(R.id.AlphabeticScrollTextView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7967p = extras.getBoolean("isGroupInviteURL");
            this.f7964m = (Group) extras.getParcelable("Group");
        }
        m8624H();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        findViewById(R.id.InvitationSmsBackBtn).setOnClickListener(null);
        FriendsClient friendsClient = this.f7966o;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        super.onDestroy();
    }
}
