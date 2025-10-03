package com.cyberlink.you.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.widgetpool.common.CLFragmentTabHost;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5164m0;
import p116k4.C5172p;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class StickerPackSettingActivity extends BaseFragmentActivity {

    /* renamed from: e */
    public static int f9116e;

    /* renamed from: c */
    public CLFragmentTabHost f9117c;

    /* renamed from: d */
    public ArrayList<String> f9118d = new ArrayList<>();

    /* renamed from: com.cyberlink.you.activity.StickerPackSettingActivity$a */
    public class AsyncTaskC1747a extends AsyncTask<Void, Void, ArrayList<String>> {
        public AsyncTaskC1747a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10124c() {
            if (StickerPackSettingActivity.this.f9118d.size() != 0) {
                Log.d("StickerPackSettingACT", "mPurchaseList = " + StickerPackSettingActivity.this.f9118d);
                StickerPackSettingActivity.this.m10122W0();
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ArrayList<String> doInBackground(Void... voidArr) throws JSONException {
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            String str = "token";
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(200)));
            FriendsClient friendsClient = new FriendsClient();
            Pair<String, String> pairM15731j = friendsClient.m15731j("sticker", "user.pack.list", arrayList);
            String str2 = (String) pairM15731j.first;
            String str3 = (String) pairM15731j.second;
            ArrayList<String> arrayList2 = new ArrayList<>();
            if (str2 == null || !str2.equals("200")) {
                Log.d("StickerPackSettingACT", "statusCode = " + str2);
            } else {
                int iM16553k1 = CLUtility.m16553k1(str3);
                int iM16494U0 = CLUtility.m16494U0(str3);
                int iM16559m = CLUtility.m16559m(iM16553k1, 200);
                ArrayList<String> arrayListM10120U0 = StickerPackSettingActivity.this.m10120U0(str3);
                if (iM16553k1 == -1 || iM16494U0 == -1) {
                    Log.d("StickerPackSettingACT", "totalSize = " + iM16553k1 + " resultsSize " + iM16494U0);
                } else if (iM16553k1 != iM16494U0) {
                    int i9 = 2;
                    while (i9 <= iM16559m) {
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(new C6301o(str, strM7449L));
                        String str4 = strM7449L;
                        arrayList3.add(new C6301o("pageIndex", String.valueOf(i9)));
                        String str5 = str;
                        arrayList3.add(new C6301o("pageSize", String.valueOf(200)));
                        Pair<String, String> pairM15731j2 = friendsClient.m15731j("sticker", "user.pack.list", arrayList3);
                        String str6 = (String) pairM15731j2.first;
                        String str7 = (String) pairM15731j2.second;
                        if (!"200".equals(str6)) {
                            Log.d("StickerPackSettingACT", "statusCode = " + str6);
                        } else if (arrayListM10120U0 != null) {
                            arrayListM10120U0.addAll(StickerPackSettingActivity.this.m10120U0(str7));
                        }
                        i9++;
                        str = str5;
                        strM7449L = str4;
                    }
                }
                arrayList2 = arrayListM10120U0;
            }
            friendsClient.m15717U0();
            return arrayList2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<String> arrayList) {
            if (arrayList != null) {
                HashSet hashSet = new HashSet(arrayList);
                StickerPackSettingActivity.this.f9118d = new ArrayList(hashSet);
                StickerPackSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.pg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11045b.m10124c();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S0 */
    public /* synthetic */ void m10116S0(View view) {
        onBackPressed();
    }

    /* renamed from: T0 */
    public static /* synthetic */ void m10117T0(String str, String str2, String str3, String str4) {
        if (str3 == null || str4 == null) {
            return;
        }
        Log.d("StickerPackSettingACT", "[updateSticker] statusCode = " + str3);
        Log.d("StickerPackSettingACT", "[updateSticker] jsonStr = " + str4);
    }

    /* renamed from: Q0 */
    public final void m10118Q0() {
        new AsyncTaskC1747a().execute(new Void[0]);
    }

    /* renamed from: R0 */
    public final View m10119R0(String str) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i9 = displayMetrics.widthPixels;
        View viewInflate = getLayoutInflater().inflate(R.layout.chat_album_fragment_selection, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.chat_album_fragment_item_text)).setText(str);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams((int) (i9 / f9116e), (int) (((int) (i9 / 2)) * 0.19444445f)));
        return viewInflate;
    }

    /* renamed from: U0 */
    public final ArrayList<String> m10120U0(String str) throws JSONException {
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        ArrayList<String> arrayList = new ArrayList<>();
        if (jSONArrayM20196r == null) {
            return null;
        }
        for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
            try {
                JSONObject jSONObject = jSONArrayM20196r.getJSONObject(i9);
                try {
                    long j9 = jSONObject.getLong("packId");
                    if (jSONObject.getString("purchaseType").equals("Purchase")) {
                        arrayList.add(Long.toString(j9));
                    }
                } catch (JSONException unused) {
                    Log.e("StickerPackSettingACT", "[sticker.pack.info] Parse item error. JSONstr=" + jSONObject.toString());
                }
            } catch (JSONException unused2) {
                Log.e("StickerPackSettingACT", "[sticker.pack.info] groupinfo parse error. JSONstr=" + jSONArrayM20196r.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: V0 */
    public final void m10121V0() {
        boolean zM12437k;
        C2305mg c2305mg = (C2305mg) getSupportFragmentManager().mo1848e("Current");
        if (c2305mg != null) {
            zM12437k = c2305mg.m12437k();
        } else {
            Log.d("StickerPackSettingACT", "Can not find the StickerCurrentListFragment");
            zM12437k = false;
        }
        if (zM12437k) {
            String strM12436j = c2305mg.m12436j();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("list", strM12436j));
            new FriendsClient().m15734m("sticker", "user.pack.update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ng
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    StickerPackSettingActivity.m10117T0(str, str2, str3, str4);
                }
            });
        }
        Intent intent = new Intent();
        intent.putExtra("isChanged", zM12437k);
        setResult(-1, intent);
    }

    /* renamed from: W0 */
    public final void m10122W0() {
        if (this.f9118d != null) {
            this.f9117c.setCurrentTab(0);
            this.f9117c.clearAllTabs();
            f9116e = 2;
            Bundle bundle = new Bundle();
            bundle.putString("type", "Current");
            CLFragmentTabHost cLFragmentTabHost = this.f9117c;
            cLFragmentTabHost.m17287a(cLFragmentTabHost.newTabSpec("Current").setIndicator(m10119R0(getString(R.string.current))), C2305mg.class, bundle);
            Bundle bundle2 = new Bundle();
            bundle2.putString("type", "Purchased");
            bundle2.putStringArrayList("purchasedList", this.f9118d);
            CLFragmentTabHost cLFragmentTabHost2 = this.f9117c;
            cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec("Purchased").setIndicator(m10119R0(getString(R.string.purchased))), C2403qg.class, bundle2);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        m10121V0();
        super.onBackPressed();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sticker_pack_setting);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.og
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11016b.m10116S0(view);
            }
        });
        ((TextView) findViewById(R.id.title)).setText(R.string.my_stickers);
        CLFragmentTabHost cLFragmentTabHost = (CLFragmentTabHost) findViewById(R.id.tabhost);
        this.f9117c = cLFragmentTabHost;
        cLFragmentTabHost.m17293g(getBaseContext(), getSupportFragmentManager(), R.id.realtabcontent);
        f9116e = 1;
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", "Current");
        CLFragmentTabHost cLFragmentTabHost2 = this.f9117c;
        cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec("Current").setIndicator(m10119R0(getString(R.string.current))), C2305mg.class, bundle2);
        this.f9117c.getTabWidget().getChildAt(0).setVisibility(8);
        m10118Q0();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferencesM20119n = C5164m0.m20108m().m20119n();
        String language = Locale.getDefault().getLanguage();
        String string = sharedPreferencesM20119n.getString("last_sticker_language", null);
        if (string == null || language.equals(string)) {
            return;
        }
        finish();
    }
}
