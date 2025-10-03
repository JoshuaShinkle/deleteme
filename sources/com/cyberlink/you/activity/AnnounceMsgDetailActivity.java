package com.cyberlink.you.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p173q2.C6127a;
import p173q2.C6136j;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class AnnounceMsgDetailActivity extends BaseActivity {

    /* renamed from: v */
    public static String f7358v = "AnnounceMsgDetailActivity";

    /* renamed from: c */
    public MessageObj f7359c;

    /* renamed from: d */
    public Friend f7360d;

    /* renamed from: e */
    public List<C1416c> f7361e;

    /* renamed from: f */
    public FriendsClient f7362f;

    /* renamed from: i */
    public ImageView f7365i;

    /* renamed from: j */
    public TextView f7366j;

    /* renamed from: k */
    public TextView f7367k;

    /* renamed from: l */
    public TextView f7368l;

    /* renamed from: m */
    public ImageView f7369m;

    /* renamed from: n */
    public TextView f7370n;

    /* renamed from: o */
    public TextView f7371o;

    /* renamed from: p */
    public LinearLayout f7372p;

    /* renamed from: q */
    public View f7373q;

    /* renamed from: r */
    public String f7374r;

    /* renamed from: s */
    public String f7375s;

    /* renamed from: t */
    public String f7376t;

    /* renamed from: g */
    public final int f7363g = Math.round(TypedValue.applyDimension(1, 150.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: h */
    public int f7364h = Math.round(TypedValue.applyDimension(1, 100.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: u */
    public View.OnClickListener f7377u = new ViewOnClickListenerC1415b();

    /* renamed from: com.cyberlink.you.activity.AnnounceMsgDetailActivity$a */
    public class AsyncTaskC1414a extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f7378a;

        public AsyncTaskC1414a(String str) {
            this.f7378a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            Thread.currentThread().setName("AnnounceMsgDetailActivity AsyncTask");
            if (this.f7378a == null) {
                return null;
            }
            AnnounceMsgDetailActivity.this.f7359c = C2950b0.m14916o().m15179r(this.f7378a);
            AnnounceMsgDetailActivity.this.f7360d = C2950b0.m14899A().m15001A(AnnounceMsgDetailActivity.this.f7359c.m14745G());
            if (AnnounceMsgDetailActivity.this.f7359c == null) {
                return null;
            }
            String strM14747I = AnnounceMsgDetailActivity.this.f7359c.m14747I("announcementId");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("announcementId", strM14747I));
            Pair<String, String> pairM15731j = AnnounceMsgDetailActivity.this.f7362f.m15731j("chat", "queryAnnouncementDetail", arrayList);
            if (pairM15731j == null) {
                return null;
            }
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            if (str == null || !str.equals("200")) {
                return null;
            }
            try {
                JSONArray jSONArray = new JSONObject(str2).getJSONArray("data");
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    C1416c c1416c = new C1416c();
                    c1416c.f7381a = (String) jSONArray.getJSONObject(i9).get(MimeTypes.BASE_TYPE_TEXT);
                    c1416c.f7382b = (String) jSONArray.getJSONObject(i9).get("imageUrl");
                    AnnounceMsgDetailActivity.this.f7361e.add(c1416c);
                }
                return null;
            } catch (Exception e9) {
                Log.d(AnnounceMsgDetailActivity.f7358v, "[AnnounceMsgDetailActivity AsyncTask] Parse AnnouncementDetail Fail: e = " + e9.getMessage());
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r11) {
            if (AnnounceMsgDetailActivity.this.f7359c == null) {
                AnnounceMsgDetailActivity.this.finish();
                return;
            }
            if (AnnounceMsgDetailActivity.this.f7360d != null) {
                AnnounceMsgDetailActivity.this.f7366j.setText(AnnounceMsgDetailActivity.this.f7360d.m15621b());
                AnnounceMsgDetailActivity.this.f7367k.setText(AnnounceMsgDetailActivity.this.f7360d.m15621b());
            } else if (AnnounceMsgDetailActivity.this.f7374r != null) {
                AnnounceMsgDetailActivity.this.f7366j.setText(AnnounceMsgDetailActivity.this.f7374r);
                AnnounceMsgDetailActivity.this.f7367k.setText(AnnounceMsgDetailActivity.this.f7374r);
            }
            AnnounceMsgDetailActivity.this.f7370n.setText(AnnounceMsgDetailActivity.this.f7359c.m14748J(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FirebaseAnalytics.Param.CONTENT));
            if (AnnounceMsgDetailActivity.this.f7360d != null) {
                AnnounceMsgDetailActivity announceMsgDetailActivity = AnnounceMsgDetailActivity.this;
                C6127a.m23469j(announceMsgDetailActivity, announceMsgDetailActivity.f7365i, AnnounceMsgDetailActivity.this.f7360d);
            } else if (AnnounceMsgDetailActivity.this.f7375s != null) {
                AnnounceMsgDetailActivity announceMsgDetailActivity2 = AnnounceMsgDetailActivity.this;
                C6127a.m23474o(announceMsgDetailActivity2, announceMsgDetailActivity2.f7365i, AnnounceMsgDetailActivity.this.f7375s, R.drawable.pic_default);
            }
            AnnounceMsgDetailActivity.this.f7368l.setText(AnnounceMsgDetailActivity.this.f7359c.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
            AnnounceMsgDetailActivity.this.f7371o.setText(CLUtility.m16458K2(AnnounceMsgDetailActivity.this.f7359c.m14788z()));
            LayoutInflater layoutInflater = AnnounceMsgDetailActivity.this.getLayoutInflater();
            float f9 = Globals.m7388i0().getResources().getDisplayMetrics().widthPixels * 0.95f;
            float fIntValue = f9 / Integer.valueOf(AnnounceMsgDetailActivity.this.f7359c.m14748J("image", "width")).intValue();
            int i9 = (int) f9;
            int iIntValue = (int) (fIntValue * Integer.valueOf(AnnounceMsgDetailActivity.this.f7359c.m14748J("image", "height")).intValue());
            String strM14748J = AnnounceMsgDetailActivity.this.f7359c.m14748J("image", "src");
            if (strM14748J == null || strM14748J.isEmpty()) {
                AnnounceMsgDetailActivity.this.f7369m.setVisibility(8);
            } else {
                if (i9 == 0 || iIntValue == 0) {
                    AnnounceMsgDetailActivity.this.f7369m.getLayoutParams().height = AnnounceMsgDetailActivity.this.f7364h;
                    AnnounceMsgDetailActivity.this.f7369m.getLayoutParams().width = AnnounceMsgDetailActivity.this.f7363g;
                } else {
                    AnnounceMsgDetailActivity.this.f7369m.getLayoutParams().height = iIntValue;
                    AnnounceMsgDetailActivity.this.f7369m.getLayoutParams().width = i9;
                }
                AnnounceMsgDetailActivity announceMsgDetailActivity3 = AnnounceMsgDetailActivity.this;
                C6136j.m23599s(announceMsgDetailActivity3, announceMsgDetailActivity3.f7369m, strM14748J);
            }
            for (int i10 = 0; i10 < AnnounceMsgDetailActivity.this.f7361e.size(); i10++) {
                View viewInflate = layoutInflater.inflate(R.layout.view_item_msg_img_text, (ViewGroup) AnnounceMsgDetailActivity.this.f7372p, false);
                ImageView imageView = (ImageView) viewInflate.findViewById(R.id.MsgImg);
                TextView textView = (TextView) viewInflate.findViewById(R.id.MsgText);
                imageView.getLayoutParams().width = i9;
                imageView.getLayoutParams().height = iIntValue;
                C1416c c1416c = (C1416c) AnnounceMsgDetailActivity.this.f7361e.get(i10);
                C6136j.m23599s(AnnounceMsgDetailActivity.this, imageView, c1416c.f7382b);
                boolean zContains = c1416c.f7381a.contains("<a href='");
                textView.setText(Html.fromHtml(c1416c.f7381a));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setAutoLinkMask(15);
                textView.setLinkTextColor(-16776961);
                if (!zContains) {
                    CLUtility.m16543i(textView);
                }
                AnnounceMsgDetailActivity.this.f7372p.addView(viewInflate);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.AnnounceMsgDetailActivity$b */
    public class ViewOnClickListenerC1415b implements View.OnClickListener {
        public ViewOnClickListenerC1415b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnnounceMsgDetailActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.AnnounceMsgDetailActivity$c */
    public static class C1416c {

        /* renamed from: a */
        public String f7381a;

        /* renamed from: b */
        public String f7382b;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_announce_msg_detail);
        String stringExtra = getIntent().getStringExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
        this.f7374r = getIntent().getStringExtra("displayName");
        this.f7375s = getIntent().getStringExtra("avatar");
        this.f7376t = getIntent().getStringExtra("groupSubType");
        this.f7362f = new FriendsClient(true);
        this.f7361e = new ArrayList();
        View viewFindViewById = findViewById(R.id.AnnounceMsgDetailBackBtn);
        this.f7373q = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f7377u);
        this.f7365i = (ImageView) findViewById(R.id.MessageAvatar);
        this.f7366j = (TextView) findViewById(R.id.AnnounceMsgDetailTitle);
        this.f7367k = (TextView) findViewById(R.id.MessageDisplayName);
        this.f7368l = (TextView) findViewById(R.id.MessageTitle);
        this.f7371o = (TextView) findViewById(R.id.MessageTime);
        this.f7372p = (LinearLayout) findViewById(R.id.MessageDetailListArea);
        this.f7369m = (ImageView) findViewById(R.id.ChatMessageAdImage);
        this.f7370n = (TextView) findViewById(R.id.CoverContentTextView);
        new AsyncTaskC1414a(stringExtra).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f7373q.setOnClickListener(null);
        super.onDestroy();
    }
}
