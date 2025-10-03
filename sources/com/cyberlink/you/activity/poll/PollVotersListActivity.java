package com.cyberlink.you.activity.poll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.C0476e;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p024c3.C0722d;
import p116k4.C5172p;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class PollVotersListActivity extends BaseActivity {

    /* renamed from: i */
    public static final String f11058i = "PollVotersListActivity";

    /* renamed from: d */
    public long f11060d;

    /* renamed from: f */
    public FriendsClient.InterfaceC3051i f11062f;

    /* renamed from: g */
    public C0722d f11063g;

    /* renamed from: c */
    public FriendsClient f11059c = null;

    /* renamed from: e */
    public final View.OnClickListener f11061e = new View.OnClickListener() { // from class: c3.a
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3400b.m12499q(view);
        }
    };

    /* renamed from: h */
    public final List<PollVoter> f11064h = new ArrayList();

    /* renamed from: com.cyberlink.you.activity.poll.PollVotersListActivity$a */
    public class RunnableC2385a implements Runnable {
        public RunnableC2385a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PollVotersListActivity.this.f11063g.m3553f(PollVotersListActivity.this.f11064h);
            PollVotersListActivity.this.f11063g.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m12499q(View view) {
        m12503s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public /* synthetic */ void m12500r(String str, String str2, String str3, String str4) throws JSONException {
        if (str3 == null) {
            Log.d(f11058i, "Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d(f11058i, "statusCode=" + str3);
            return;
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = C5172p.m20196r(str4).getJSONObject(0);
            JSONArray jSONArray = jSONObject.getJSONArray("voters");
            for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i9);
                PollVoter pollVoter = new PollVoter();
                pollVoter.f11055b = jSONObject2.getLong("userId");
                pollVoter.f11056c = jSONObject2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                pollVoter.f11057d = jSONObject2.getString("avatarPath");
                this.f11064h.add(pollVoter);
            }
            if (this.f11064h.size() <= 0 || this.f11063g == null) {
                return;
            }
            runOnUiThread(new RunnableC2385a());
        } catch (JSONException unused) {
            if (jSONObject != null) {
                Log.d(f11058i, "[parsePollOption] JObj=" + jSONObject.toString());
            }
        }
    }

    /* renamed from: n */
    public final void m12501n() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.PollVotersList);
        C0722d c0722d = new C0722d(this, this.f11059c, this.f11064h);
        this.f11063g = c0722d;
        recyclerView.setAdapter(c0722d);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new C0476e(this, linearLayoutManager.m2303n2()));
    }

    /* renamed from: o */
    public final void m12502o() {
        ((ImageView) findViewById(R.id.SelectPollVotersListBackBtn)).setOnClickListener(this.f11061e);
        Intent intent = getIntent();
        if (intent != null) {
            this.f11060d = intent.getLongExtra("pollOptionId", 0L);
            ((TextView) findViewById(R.id.PollVotersListTitle)).setText(intent.getStringExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m12503s();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_poll_voters_list);
        this.f11059c = new FriendsClient(true);
        m12501n();
        m12502o();
        m12504u();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FriendsClient friendsClient = this.f11059c;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
    }

    /* renamed from: s */
    public final void m12503s() {
        finish();
    }

    /* renamed from: u */
    public final void m12504u() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("optionId", Long.toString(this.f11060d)));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: c3.b
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f3401a.m12500r(str, str2, str3, str4);
            }
        };
        this.f11062f = interfaceC3051i;
        this.f11059c.m15734m("groupbulletin", "queryPollOption", arrayList, interfaceC3051i);
        arrayList.add(new C6301o("pollOptionId", "123"));
    }
}
