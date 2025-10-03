package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import net.sqlcipher.database.SQLiteDatabase;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p116k4.C5182t;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class GDPRActivity extends BaseActivity {

    /* renamed from: c */
    public static SharedPreferences f7713c;

    /* renamed from: d */
    public static boolean f7714d;

    /* renamed from: j */
    public static SimpleDateFormat m8210j() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }

    /* renamed from: k */
    public static boolean m8211k() throws ParseException {
        String string = f7713c.getString("lastAgreedTime", "");
        if (string.equals("")) {
            f7714d = true;
            return true;
        }
        String strM15642G = FriendsClient.m15642G("privacy", "lastModified");
        if (C5170o0.m20170e(strM15642G)) {
            return false;
        }
        try {
            if (f7713c.getBoolean("isNewDateFormat", false)) {
                return m8210j().parse(string).before(m8210j().parse(strM15642G));
            }
            f7713c.edit().putString("lastAgreedTime", m8210j().format(m8210j().parse(strM15642G))).apply();
            f7713c.edit().putBoolean("isNewDateFormat", true).apply();
            return false;
        } catch (ParseException e9) {
            Log.d("GDPRActivity", "ParseException: " + e9);
            return false;
        }
    }

    /* renamed from: n */
    public static boolean m8212n() {
        String country = LocaleList.getDefault().get(0).getCountry();
        String strM15642G = FriendsClient.m15642G("privacy", "EU.countries");
        if (strM15642G == null) {
            strM15642G = "[AI,AT,AX,AW,BE,BG,BL,BM,BQ,CW,CY,CZ,DE,DK,EA,EE,ES,FI,FK,FR,GB,GF,GL,GI,GP,GR,HR,HU,IC,IE,IM,IS,IT,KY,LT,LU,LI,LV,MF,MQ,MS,MT,NC,NL,NO,PF,PL,PM,PN,PT,RE,RO,SE,SH,SI,SK,SX,TC,TF,VG,WF,YT,US]";
        }
        return strM15642G.contains(country);
    }

    /* renamed from: o */
    public static boolean m8213o(Context context) {
        return m8212n() && C5170o0.m20170e(context.getSharedPreferences("GDPRActivity", 0).getString("lastAgreedTime", ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public /* synthetic */ void m8214q(View view) {
        if (f7714d) {
            C6566a.m25148g(this);
            C6566a.m25166y(this);
            C5154j.m20069c(this);
        }
        String strM15642G = FriendsClient.m15642G("privacy", "lastModified");
        if (!C5170o0.m20170e(strM15642G)) {
            f7713c.edit().putString("lastAgreedTime", strM15642G).apply();
        }
        f7713c.edit().putBoolean("isNewDateFormat", true).apply();
        finish();
    }

    /* renamed from: r */
    public static void m8215r(Activity activity) {
        if (FriendsClient.m15682q0()) {
            Globals.m7388i0().m7412D();
            return;
        }
        f7713c = activity.getSharedPreferences("GDPRActivity", 0);
        if (m8212n() && m8211k()) {
            activity.startActivity(new Intent(activity, (Class<?>) GDPRActivity.class));
        }
    }

    /* renamed from: u */
    public static void m8216u(Context context, TextView textView, boolean z8) {
        C5182t.m20260d(context, textView, z8 ? context.getString(R.string.registration_sign_up_terms_privacy, C5182t.m20258b(), C5182t.m20257a()) : context.getString(R.string.registration_login_terms_privacy, C5182t.m20258b(), C5182t.m20257a()));
        textView.setVisibility(m8212n() ? 8 : 0);
    }

    /* renamed from: l */
    public final void m8217l() {
        C5179r0.m20247b((TextView) findViewById(R.id.WelcomeTitleTextView), 2);
        findViewById(R.id.GDPRAgreeButton).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.t3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11389b.m8214q(view);
            }
        });
        String strM20258b = C5182t.m20258b();
        String strM20257a = C5182t.m20257a();
        C5182t.m20260d(this, (TextView) findViewById(R.id.GDPRTextDescription), getString(R.string.GDPR_description, strM20258b, strM20257a));
        C5182t.m20260d(this, (TextView) findViewById(R.id.UpdateDescription), getString(R.string.GDPR_update_description, strM20258b, strM20257a));
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        startActivity(intent);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gdpr);
        m8217l();
        m8218s();
    }

    /* renamed from: s */
    public final void m8218s() {
        if (!f7714d || Globals.m7388i0().m7464O1()) {
            findViewById(R.id.UpdateNoticePage).setVisibility(0);
        } else {
            findViewById(R.id.WelcomePage).setVisibility(0);
        }
    }
}
