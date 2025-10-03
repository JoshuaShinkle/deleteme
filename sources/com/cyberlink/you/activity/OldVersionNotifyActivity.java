package com.cyberlink.you.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;

/* loaded from: classes.dex */
public class OldVersionNotifyActivity extends BaseActivity {

    /* renamed from: d */
    public View f8186d;

    /* renamed from: c */
    public final String f8185c = "OldVersionNotifyACT";

    /* renamed from: e */
    public View.OnClickListener f8187e = new View.OnClickListener() { // from class: com.cyberlink.you.activity.n7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10920b.m8808k(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m8808k(View view) {
        try {
            CLUtility.m16477P1(m8809j());
        } catch (Exception e9) {
            Log.d("OldVersionNotifyACT", "[UpdateClickListener] e = " + e9.getMessage());
        }
    }

    /* renamed from: j */
    public final Activity m8809j() {
        return this;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_old_version_notify);
        View viewFindViewById = findViewById(R.id.OldVersionNotifyUpdateBtn);
        this.f8186d = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8187e);
        WebView webView = (WebView) findViewById(R.id.webView);
        String strM15672k0 = FriendsClient.m15672k0();
        if (strM15672k0 != null) {
            try {
                JSONObject jSONObject = new JSONObject(strM15672k0);
                if (jSONObject.has("errorResults")) {
                    webView.loadDataWithBaseURL("", C5172p.m20175B(jSONObject.getJSONObject("errorResults")), "text/html", "UTF-8", "");
                }
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f8186d.setOnClickListener(null);
        this.f8187e = null;
        super.onDestroy();
    }
}
