package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.C3123g;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;
import p173q2.C6127a;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class OrganizationJoinActivity extends BaseActivity {

    /* renamed from: f */
    public ImageView f8191f;

    /* renamed from: g */
    public TextView f8192g;

    /* renamed from: h */
    public TextView f8193h;

    /* renamed from: i */
    public TextView f8194i;

    /* renamed from: j */
    public View f8195j;

    /* renamed from: k */
    public View f8196k;

    /* renamed from: l */
    public View f8197l;

    /* renamed from: m */
    public View f8198m;

    /* renamed from: n */
    public AsyncTaskC1577b f8199n;

    /* renamed from: o */
    public boolean f8200o;

    /* renamed from: c */
    public String f8188c = null;

    /* renamed from: d */
    public String f8189d = null;

    /* renamed from: e */
    public String f8190e = null;

    /* renamed from: p */
    public View.OnClickListener f8201p = new ViewOnClickListenerC1576a();

    /* renamed from: com.cyberlink.you.activity.OrganizationJoinActivity$a */
    public class ViewOnClickListenerC1576a implements View.OnClickListener {
        public ViewOnClickListenerC1576a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.back /* 2131297178 */:
                    OrganizationJoinActivity.this.finish();
                    break;
                case R.id.cancel /* 2131297427 */:
                    OrganizationJoinActivity.this.finish();
                    break;
                case R.id.join /* 2131298007 */:
                    if (!OrganizationJoinActivity.this.f8200o) {
                        OrganizationJoinActivity.this.f8200o = true;
                        OrganizationJoinActivity.this.f8199n = new AsyncTaskC1577b(OrganizationJoinActivity.this, null);
                        OrganizationJoinActivity.this.f8199n.executeOnExecutor(C6385v.f21554b, new Void[0]);
                        break;
                    }
                    break;
                case R.id.ok /* 2131298282 */:
                    OrganizationJoinActivity.this.finish();
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.OrganizationJoinActivity$b */
    public class AsyncTaskC1577b extends AsyncTask<Void, Void, JSONObject> {
        public AsyncTaskC1577b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m8827c(DialogInterface dialogInterface, int i9) {
            dialogInterface.dismiss();
            OrganizationJoinActivity.this.finish();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public JSONObject doInBackground(Void... voidArr) {
            if (OrganizationJoinActivity.this.f8189d == null) {
                return null;
            }
            FriendsClient friendsClient = new FriendsClient(true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("organizationId", OrganizationJoinActivity.this.f8189d));
            Pair<String, String> pairM15731j = friendsClient.m15731j("organization", "askJoinOrg", arrayList);
            JSONObject jSONObjectM20195q = "200".equals((String) pairM15731j.first) ? C5172p.m20195q((String) pairM15731j.second) : null;
            friendsClient.m15717U0();
            return jSONObjectM20195q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(OrganizationJoinActivity.this);
                    builderM16382a.setCancelable(false);
                    builderM16382a.setPositiveButton(OrganizationJoinActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.o7
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i9) {
                            this.f11004b.m8827c(dialogInterface, i9);
                        }
                    });
                    String string = jSONObject.has("inviteStatus") ? jSONObject.getString("inviteStatus") : null;
                    String string2 = "";
                    if ("Inviting".equals(string)) {
                        OrganizationJoinActivity organizationJoinActivity = OrganizationJoinActivity.this;
                        string2 = organizationJoinActivity.getString(R.string.organization_request_success, organizationJoinActivity.f8190e);
                    } else if (!"Accepted".equals(string)) {
                        string2 = OrganizationJoinActivity.this.getString(R.string.organization_request_fail);
                    }
                    builderM16382a.setMessage(string2);
                    builderM16382a.show();
                    OrganizationJoinActivity.this.f8200o = false;
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
        }

        public /* synthetic */ AsyncTaskC1577b(OrganizationJoinActivity organizationJoinActivity, ViewOnClickListenerC1576a viewOnClickListenerC1576a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.OrganizationJoinActivity$c */
    public class AsyncTaskC1578c extends AsyncTask<Void, Void, JSONObject> {
        public AsyncTaskC1578c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public JSONObject doInBackground(Void... voidArr) {
            FriendsClient friendsClient = new FriendsClient(true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("inviteURL", OrganizationJoinActivity.this.f8188c));
            Pair<String, String> pairM15731j = friendsClient.m15731j("organization", "queryOrg", arrayList);
            JSONObject jSONObjectM20195q = "200".equals((String) pairM15731j.first) ? C5172p.m20195q((String) pairM15731j.second) : null;
            friendsClient.m15717U0();
            return jSONObjectM20195q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(JSONObject jSONObject) {
            if (jSONObject == null) {
                Log.d("OrganizationJoin", "result=null");
                return;
            }
            Log.d("OrganizationJoin", "result=" + jSONObject.toString());
            try {
                String string = (!jSONObject.has("avatarPath") || jSONObject.isNull("avatarPath")) ? null : jSONObject.getString("avatarPath");
                OrganizationJoinActivity organizationJoinActivity = OrganizationJoinActivity.this;
                C6127a.m23474o(organizationJoinActivity, organizationJoinActivity.f8191f, string, R.drawable.pic_default_official);
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            try {
                OrganizationJoinActivity.this.f8190e = (!jSONObject.has("displayName") || jSONObject.isNull("displayName")) ? null : jSONObject.getString("displayName");
                Log.d("OrganizationJoin", "displayName=" + OrganizationJoinActivity.this.f8190e);
                if (OrganizationJoinActivity.this.f8190e != null) {
                    if (OrganizationJoinActivity.this.f8192g != null) {
                        OrganizationJoinActivity.this.f8192g.setText(OrganizationJoinActivity.this.f8190e);
                    }
                    if (OrganizationJoinActivity.this.f8193h != null) {
                        TextView textView = OrganizationJoinActivity.this.f8193h;
                        OrganizationJoinActivity organizationJoinActivity2 = OrganizationJoinActivity.this;
                        textView.setText(organizationJoinActivity2.getString(R.string.organization_descrption, organizationJoinActivity2.f8190e));
                    }
                }
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
            try {
                OrganizationJoinActivity.this.f8189d = jSONObject.has("organizationId") ? jSONObject.getString("organizationId") : null;
            } catch (JSONException e11) {
                e11.printStackTrace();
            }
            try {
                String string2 = jSONObject.has("inviteStatus") ? jSONObject.getString("inviteStatus") : null;
                if (string2 != null && !string2.equals("None")) {
                    if (string2.equals("Accepted")) {
                        OrganizationJoinActivity.this.f8195j.setVisibility(8);
                        OrganizationJoinActivity.this.f8196k.setVisibility(8);
                        OrganizationJoinActivity.this.f8197l.setVisibility(0);
                        if (OrganizationJoinActivity.this.f8194i == null || OrganizationJoinActivity.this.f8192g == null) {
                            return;
                        }
                        OrganizationJoinActivity.this.f8194i.setVisibility(0);
                        TextView textView2 = OrganizationJoinActivity.this.f8194i;
                        OrganizationJoinActivity organizationJoinActivity3 = OrganizationJoinActivity.this;
                        textView2.setText(organizationJoinActivity3.getString(R.string.organization_already_friend, organizationJoinActivity3.f8190e));
                        return;
                    }
                    if (string2.equals("Declined")) {
                        OrganizationJoinActivity.this.f8195j.setVisibility(8);
                        OrganizationJoinActivity.this.f8196k.setVisibility(8);
                        OrganizationJoinActivity.this.f8197l.setVisibility(0);
                        return;
                    } else if (string2.equals("Inviting")) {
                        OrganizationJoinActivity.this.f8195j.setVisibility(8);
                        OrganizationJoinActivity.this.f8196k.setVisibility(8);
                        OrganizationJoinActivity.this.f8197l.setVisibility(0);
                        return;
                    } else {
                        OrganizationJoinActivity.this.f8195j.setVisibility(8);
                        OrganizationJoinActivity.this.f8196k.setVisibility(8);
                        OrganizationJoinActivity.this.f8197l.setVisibility(0);
                        return;
                    }
                }
                OrganizationJoinActivity.this.f8195j.setVisibility(0);
                OrganizationJoinActivity.this.f8196k.setVisibility(0);
                OrganizationJoinActivity.this.f8197l.setVisibility(8);
            } catch (JSONException e12) {
                e12.printStackTrace();
            }
        }

        public /* synthetic */ AsyncTaskC1578c(OrganizationJoinActivity organizationJoinActivity, ViewOnClickListenerC1576a viewOnClickListenerC1576a) {
            this();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_organization_join);
        Uri data = getIntent().getData();
        if (data != null) {
            Log.d("OrganizationJoin", "Get url=" + data.toString());
            if ("askToJoinOrg".equals(data.getHost())) {
                this.f8188c = data.getQueryParameter("inviteURL");
            }
        }
        if (this.f8188c == null) {
            this.f8188c = getIntent().getStringExtra("inviteURL");
        }
        if (this.f8188c == null) {
            Log.d("OrganizationJoin", "invite url is null");
            return;
        }
        Log.d("OrganizationJoin", "mInviteURL= " + this.f8188c);
        this.f8191f = (ImageView) findViewById(R.id.avatar);
        this.f8192g = (TextView) findViewById(R.id.name);
        this.f8193h = (TextView) findViewById(R.id.description);
        this.f8194i = (TextView) findViewById(R.id.hint);
        View viewFindViewById = findViewById(R.id.join);
        this.f8195j = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8201p);
        View viewFindViewById2 = findViewById(R.id.cancel);
        this.f8196k = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f8201p);
        View viewFindViewById3 = findViewById(R.id.ok);
        this.f8197l = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f8201p);
        View viewFindViewById4 = findViewById(R.id.back);
        this.f8198m = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this.f8201p);
        new AsyncTaskC1578c(this, null).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AsyncTaskC1577b asyncTaskC1577b = this.f8199n;
        if (asyncTaskC1577b != null) {
            asyncTaskC1577b.cancel(false);
        }
    }
}
