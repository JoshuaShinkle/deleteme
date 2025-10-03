package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5161l0;
import p116k4.C5131b0;
import p116k4.C5155j0;
import p116k4.C5187v0;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class InvitationEmailActivity extends BaseActivity {

    /* renamed from: w */
    public static final String f7913w = "InvitationEmailActivity";

    /* renamed from: e */
    public ListView f7916e;

    /* renamed from: f */
    public EditText f7917f;

    /* renamed from: g */
    public View f7918g;

    /* renamed from: h */
    public View f7919h;

    /* renamed from: i */
    public TextView f7920i;

    /* renamed from: j */
    public Button f7921j;

    /* renamed from: k */
    public ArrayList<HashMap<String, String>> f7922k;

    /* renamed from: l */
    public ArrayList<HashMap<String, String>> f7923l;

    /* renamed from: m */
    public BaseAdapter f7924m;

    /* renamed from: n */
    public Group f7925n;

    /* renamed from: o */
    public C1528j f7926o;

    /* renamed from: p */
    public FriendsClient f7927p;

    /* renamed from: c */
    public final String f7914c = "EMAIL";

    /* renamed from: d */
    public final String f7915d = "NAME";

    /* renamed from: q */
    public boolean f7928q = false;

    /* renamed from: r */
    public C1527i f7929r = new C1527i();

    /* renamed from: s */
    public int f7930s = 0;

    /* renamed from: t */
    public View.OnClickListener f7931t = new ViewOnClickListenerC1522d();

    /* renamed from: u */
    public TextWatcher f7932u = new C1525g();

    /* renamed from: v */
    public AbsListView.OnScrollListener f7933v = new C1526h();

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$a */
    public class ViewOnClickListenerC1519a implements View.OnClickListener {
        public ViewOnClickListenerC1519a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CLUtility.m16589t1(InvitationEmailActivity.this);
            InvitationEmailActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$b */
    public class C1520b extends BaseAdapter {
        public C1520b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return InvitationEmailActivity.this.f7923l.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            return InvitationEmailActivity.this.f7923l.get(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C1529k c1529k;
            if (view == null) {
                view = ((LayoutInflater) InvitationEmailActivity.this.m8589L().getSystemService("layout_inflater")).inflate(R.layout.view_item_invite_via_email, viewGroup, false);
                c1529k = new C1529k(InvitationEmailActivity.this, null);
                c1529k.f7949a = (ImageView) view.findViewById(R.id.InvitationViaSomeWayImage);
                c1529k.f7950b = (TextView) view.findViewById(R.id.InvitationViaSomeWayDisplayName);
                c1529k.f7951c = (TextView) view.findViewById(R.id.InvitationViaSomeWayData);
                c1529k.f7952d = (CheckBox) view.findViewById(R.id.checkBox);
                view.setTag(c1529k);
            } else {
                c1529k = (C1529k) view.getTag();
            }
            HashMap map = (HashMap) getItem(i9);
            ImageView imageView = c1529k.f7949a;
            if (c1529k.f7950b != null) {
                c1529k.f7950b.setText((String) map.get("NAME"));
            }
            if (c1529k.f7951c != null) {
                c1529k.f7951c.setText((String) map.get("EMAIL"));
            }
            Boolean boolM8602b = InvitationEmailActivity.this.f7929r.m8602b((String) map.get("NAME"), (String) map.get("EMAIL"));
            if (boolM8602b != null) {
                c1529k.f7952d.setChecked(boolM8602b.booleanValue());
            } else {
                c1529k.f7952d.setChecked(false);
            }
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$c */
    public class C1521c implements AdapterView.OnItemClickListener {
        public C1521c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C1529k c1529k = (C1529k) view.getTag();
            HashMap map = (HashMap) InvitationEmailActivity.this.f7924m.getItem(i9);
            Boolean boolM8602b = InvitationEmailActivity.this.f7929r.m8602b((String) map.get("NAME"), (String) map.get("EMAIL"));
            if (boolM8602b == null || !boolM8602b.booleanValue()) {
                InvitationEmailActivity.this.f7929r.m8605e((String) map.get("NAME"), (String) map.get("EMAIL"), Boolean.TRUE);
                InvitationEmailActivity.m8570D(InvitationEmailActivity.this);
                c1529k.f7952d.setChecked(true);
            } else {
                InvitationEmailActivity.this.f7929r.m8605e((String) map.get("NAME"), (String) map.get("EMAIL"), Boolean.FALSE);
                InvitationEmailActivity.m8571E(InvitationEmailActivity.this);
                c1529k.f7952d.setChecked(false);
            }
            InvitationEmailActivity.this.m8595V();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$d */
    public class ViewOnClickListenerC1522d implements View.OnClickListener {
        public ViewOnClickListenerC1522d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string;
            SharedPreferences sharedPreferences = InvitationEmailActivity.this.getApplicationContext().getSharedPreferences("U", 0);
            if (InvitationEmailActivity.this.f7928q) {
                string = null;
                try {
                    string = C2950b0.m14912k().m15077n(Long.toString(InvitationEmailActivity.this.f7925n.f13727n)).f13707F;
                    Log.d(InvitationEmailActivity.f7913w, "[getFriendLink] inviteGroupLink = " + string);
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            } else {
                string = sharedPreferences.getString("inviteFriendLink", "");
            }
            String[] strArrM8591O = InvitationEmailActivity.this.m8591O();
            if (string == null || string.isEmpty()) {
                InvitationEmailActivity.this.m8590N(strArrM8591O);
            } else {
                InvitationEmailActivity.this.m8593Q(strArrM8591O, string);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$e */
    public class C1523e implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final /* synthetic */ String[] f7938a;

        public C1523e(String[] strArr) {
            this.f7938a = strArr;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws JSONException {
            if (str3 == null) {
                Log.d(InvitationEmailActivity.f7913w, "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d(InvitationEmailActivity.f7913w, "statusCode=" + str3);
                return;
            }
            String string = null;
            try {
                string = new JSONObject(str4).getString("inviteURL");
                if (InvitationEmailActivity.this.f7928q) {
                    InvitationEmailActivity.this.f7925n.f13707F = string;
                    Log.d(InvitationEmailActivity.f7913w, "[getFriendLink] Renew inviteGroupLink = " + InvitationEmailActivity.this.f7925n.f13707F + ", update to database.");
                    C2950b0.m14912k().m15062A(String.valueOf(InvitationEmailActivity.this.f7925n.f13727n), InvitationEmailActivity.this.f7925n, "inviteGroupLink");
                } else {
                    InvitationEmailActivity.this.getApplicationContext().getSharedPreferences("U", 0).edit().putString("inviteFriendLink", string).apply();
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            InvitationEmailActivity.this.m8593Q(this.f7938a, string);
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$f */
    public class RunnableC1524f implements Runnable {
        public RunnableC1524f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            InvitationEmailActivity.this.f7924m.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$g */
    public class C1525g implements TextWatcher {
        public C1525g() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            InvitationEmailActivity.this.f7926o.filter(charSequence.toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$h */
    public class C1526h implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f7942a = false;

        /* renamed from: b */
        public int f7943b = -1;

        /* renamed from: c */
        public String f7944c = null;

        public C1526h() {
        }

        /* renamed from: a */
        public final String m8596a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m8597b() {
            if (InvitationEmailActivity.this.f7919h != null && InvitationEmailActivity.this.f7919h.isShown()) {
                InvitationEmailActivity.this.f7919h.setVisibility(4);
            }
            m8598c();
        }

        /* renamed from: c */
        public final void m8598c() {
            this.f7943b = -1;
            this.f7944c = null;
        }

        /* renamed from: d */
        public final void m8599d() {
            HashMap map = (HashMap) InvitationEmailActivity.this.f7916e.getItemAtPosition(InvitationEmailActivity.this.f7916e.getFirstVisiblePosition());
            if (map == null || !map.containsKey("NAME")) {
                return;
            }
            String str = (String) map.get("NAME");
            if (InvitationEmailActivity.this.f7920i != null) {
                String strM8596a = m8596a(str);
                String str2 = this.f7944c;
                if (str2 == null || !strM8596a.equals(str2)) {
                    InvitationEmailActivity.this.f7920i.setText(strM8596a);
                    m8600e();
                    this.f7944c = strM8596a;
                }
            }
        }

        /* renamed from: e */
        public final void m8600e() {
            if (InvitationEmailActivity.this.f7919h != null && !InvitationEmailActivity.this.f7919h.isShown()) {
                InvitationEmailActivity.this.f7919h.setVisibility(0);
            }
            m8598c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f7942a && this.f7943b != (firstVisiblePosition = InvitationEmailActivity.this.f7916e.getFirstVisiblePosition())) {
                this.f7943b = firstVisiblePosition;
                m8599d();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f7942a = false;
                m8597b();
            } else if (i9 == 1) {
                this.f7942a = true;
                m8598c();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$i */
    public class C1527i {

        /* renamed from: a */
        public HashMap<String, Boolean> f7946a = new HashMap<>();

        public C1527i() {
        }

        /* renamed from: a */
        public Boolean m8601a(String str) {
            return this.f7946a.get(str);
        }

        /* renamed from: b */
        public Boolean m8602b(String str, String str2) {
            return this.f7946a.get(str + "######" + str2);
        }

        /* renamed from: c */
        public String m8603c(String str) {
            return str.split("######")[1];
        }

        /* renamed from: d */
        public Set<String> m8604d() {
            return this.f7946a.keySet();
        }

        /* renamed from: e */
        public Boolean m8605e(String str, String str2, Boolean bool) {
            return this.f7946a.put(str + "######" + str2, bool);
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$j */
    public class C1528j extends AbstractC5161l0<HashMap<String, String>> {
        public C1528j() {
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: a */
        public List<HashMap<String, String>> mo3351a() {
            return InvitationEmailActivity.this.f7922k;
        }

        @Override // p116k4.AbstractC5161l0
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public boolean mo3352c(HashMap<String, String> map, String str, Object obj) {
            return m20104d(map.get("EMAIL")).contains(str) || m20104d(map.get("NAME")).contains(str);
        }

        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            InvitationEmailActivity.this.f7923l = (ArrayList) filterResults.values;
            if (InvitationEmailActivity.this.f7923l != null) {
                InvitationEmailActivity.this.m8594T();
                if (InvitationEmailActivity.this.f7923l.size() > 0) {
                    InvitationEmailActivity.this.f7918g.setVisibility(8);
                } else {
                    InvitationEmailActivity.this.f7918g.setVisibility(0);
                }
            }
        }

        public /* synthetic */ C1528j(InvitationEmailActivity invitationEmailActivity, ViewOnClickListenerC1519a viewOnClickListenerC1519a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.InvitationEmailActivity$k */
    public class C1529k {

        /* renamed from: a */
        public ImageView f7949a;

        /* renamed from: b */
        public TextView f7950b;

        /* renamed from: c */
        public TextView f7951c;

        /* renamed from: d */
        public CheckBox f7952d;

        public C1529k() {
        }

        public /* synthetic */ C1529k(InvitationEmailActivity invitationEmailActivity, ViewOnClickListenerC1519a viewOnClickListenerC1519a) {
            this();
        }
    }

    /* renamed from: D */
    public static /* synthetic */ int m8570D(InvitationEmailActivity invitationEmailActivity) {
        int i9 = invitationEmailActivity.f7930s;
        invitationEmailActivity.f7930s = i9 + 1;
        return i9;
    }

    /* renamed from: E */
    public static /* synthetic */ int m8571E(InvitationEmailActivity invitationEmailActivity) {
        int i9 = invitationEmailActivity.f7930s;
        invitationEmailActivity.f7930s = i9 - 1;
        return i9;
    }

    /* renamed from: L */
    public final Activity m8589L() {
        return this;
    }

    /* renamed from: N */
    public final void m8590N(String[] strArr) {
        String str;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        if (this.f7928q) {
            arrayList.add(new C6301o("groupId", Long.toString(this.f7925n.f13727n)));
            str = "group";
        } else {
            str = "invite";
        }
        this.f7927p.m15734m(str, "genInviteURL", arrayList, new C1523e(strArr));
    }

    /* renamed from: O */
    public final String[] m8591O() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.f7929r.m8604d()) {
            if (this.f7929r.m8601a(str).booleanValue()) {
                arrayList.add(this.f7929r.m8603c(str));
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: P */
    public final void m8592P() {
        this.f7916e = (ListView) findViewById(R.id.EmailListView);
        ArrayList<HashMap<String, String>> arrayListM20004b = C5131b0.m20004b("NAME", "EMAIL", this);
        this.f7922k = arrayListM20004b;
        this.f7923l = arrayListM20004b;
        C1520b c1520b = new C1520b();
        this.f7924m = c1520b;
        this.f7916e.setAdapter((ListAdapter) c1520b);
        this.f7916e.setOnScrollListener(this.f7933v);
        this.f7926o.filter(this.f7917f.getText().toString());
        this.f7916e.setOnItemClickListener(new C1521c());
    }

    /* renamed from: Q */
    public final void m8593Q(String[] strArr, String str) {
        String str2;
        String str3;
        CLUtility.m16589t1(this);
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(getApplicationContext());
        if (userInfoM16497V0 == null) {
            return;
        }
        if (this.f7928q) {
            str3 = String.format(getString(R.string.group_invite_email_title_new), userInfoM16497V0.f13778c, this.f7925n.f13717d);
            str2 = String.format(getString(R.string.group_invite_email_new), this.f7925n.f13717d, str);
        } else {
            String str4 = String.format(getString(R.string.invite_email_title_new), userInfoM16497V0.f13778c);
            str2 = String.format(getString(R.string.invite_email_new), userInfoM16497V0.f13778c, "<a href=\"" + str + "\">" + str + "</a>");
            str3 = str4;
        }
        try {
            startActivity(C5155j0.m20083a(str3, str2, str, strArr));
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.invite_email_no_client);
        }
    }

    /* renamed from: T */
    public final void m8594T() {
        runOnUiThread(new RunnableC1524f());
    }

    /* renamed from: V */
    public final void m8595V() {
        if (this.f7930s <= 0) {
            this.f7921j.setText(getString(R.string.invitation_sms_invite_btn));
            this.f7921j.setEnabled(false);
            return;
        }
        this.f7921j.setText(getString(R.string.invitation_sms_invite_btn) + "(" + this.f7930s + ")");
        this.f7921j.setEnabled(true);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_email);
        findViewById(R.id.InvitationEmailBackBtn).setOnClickListener(new ViewOnClickListenerC1519a());
        this.f7918g = findViewById(R.id.InvitationEmailSearchEmptyView);
        EditText editText = (EditText) findViewById(R.id.SearchEditText);
        this.f7917f = editText;
        editText.setInputType(32);
        this.f7917f.setHint(getResources().getString(R.string.search));
        this.f7917f.addTextChangedListener(this.f7932u);
        Button button = (Button) findViewById(R.id.btn_invite);
        this.f7921j = button;
        button.setOnClickListener(this.f7931t);
        this.f7919h = findViewById(R.id.AlphabeticScrollTextLayout);
        this.f7920i = (TextView) findViewById(R.id.AlphabeticScrollTextView);
        this.f7927p = new FriendsClient();
        this.f7926o = new C1528j(this, null);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f7928q = extras.getBoolean("isGroupInviteURL");
            this.f7925n = (Group) extras.getParcelable("Group");
        }
        m8592P();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        findViewById(R.id.InvitationEmailBackBtn).setOnClickListener(null);
        FriendsClient friendsClient = this.f7927p;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        super.onDestroy();
    }
}
