package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import p116k4.C5152i0;
import p116k4.C5164m0;
import p173q2.C6136j;
import p201t3.C6301o;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class StickerShopDetailActivity extends BaseActivity {

    /* renamed from: r */
    public static String f9159r = "";

    /* renamed from: s */
    public static boolean f9160s = false;

    /* renamed from: c */
    public StickerPackObj f9161c;

    /* renamed from: d */
    public Button f9162d;

    /* renamed from: e */
    public View f9163e;

    /* renamed from: g */
    public FriendsClient f9165g;

    /* renamed from: i */
    public FrameLayout f9167i;

    /* renamed from: j */
    public ProgressBar f9168j;

    /* renamed from: k */
    public TextView f9169k;

    /* renamed from: n */
    public AsyncTask<Void, Void, Boolean> f9172n;

    /* renamed from: o */
    public boolean f9173o;

    /* renamed from: q */
    public FriendsClient.InterfaceC3051i f9175q;

    /* renamed from: f */
    public String f9164f = null;

    /* renamed from: h */
    public ProgressDialog f9166h = null;

    /* renamed from: l */
    public View.OnClickListener f9170l = new ViewOnClickListenerC1753a();

    /* renamed from: m */
    public View.OnClickListener f9171m = new ViewOnClickListenerC1754b();

    /* renamed from: p */
    public View.OnClickListener f9174p = new ViewOnClickListenerC1759g();

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$a */
    public class ViewOnClickListenerC1753a implements View.OnClickListener {
        public ViewOnClickListenerC1753a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            StickerShopDetailActivity.this.m10172L();
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$b */
    public class ViewOnClickListenerC1754b implements View.OnClickListener {
        public ViewOnClickListenerC1754b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.d("StickerShopDetailACT", "Launching purchase flow.");
            if (StickerShopDetailActivity.f9160s || StickerShopDetailActivity.this.f9161c.m14806j().equals("Free")) {
                StickerShopDetailActivity.this.m10170I();
            } else if (StickerShopDetailActivity.this.f9161c.m14806j().equals("Purchase")) {
                try {
                    StickerShopDetailActivity.this.f9162d.setEnabled(false);
                } catch (Exception unused) {
                    Log.e("StickerShopDetailACT", " Do twice purchase flow");
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$c */
    public class RunnableC1755c implements Runnable {
        public RunnableC1755c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StickerShopDetailActivity stickerShopDetailActivity = StickerShopDetailActivity.this;
            stickerShopDetailActivity.f9166h = ProgressDialog.show(stickerShopDetailActivity.m10171J(), "", StickerShopDetailActivity.this.m10171J().getResources().getString(R.string.processing), true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$d */
    public class AsyncTaskC1756d extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ String f9179a;

        /* renamed from: b */
        public final /* synthetic */ String f9180b;

        /* renamed from: c */
        public final /* synthetic */ String f9181c;

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$d$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (StickerShopDetailActivity.this.f9166h != null) {
                    StickerShopDetailActivity.this.f9166h.dismiss();
                    StickerShopDetailActivity.this.f9166h = null;
                }
            }
        }

        public AsyncTaskC1756d(String str, String str2, String str3) {
            this.f9179a = str;
            this.f9180b = str2;
            this.f9181c = str3;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws IOException {
            boolean zM16463M = CLUtility.m16463M(this.f9179a, this.f9180b);
            if (zM16463M && !CLUtility.m16474O2(this.f9180b, this.f9181c)) {
                zM16463M = false;
            }
            if (!CLUtility.m16447I(new File(this.f9180b))) {
                Log.d("StickerShopDetailACT", "[doDownloadSticker]Delete Zip file fail");
            }
            return Boolean.valueOf(zM16463M);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            StickerShopDetailActivity.this.f9173o = false;
            if (bool.booleanValue()) {
                StickerShopDetailActivity.this.m10174O();
                return;
            }
            StickerShopDetailActivity.this.f9162d.setEnabled(true);
            StickerShopDetailActivity.this.f9163e.setVisibility(8);
            if (StickerShopDetailActivity.this.m10171J() != null) {
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new a());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e */
    public class C1757e implements FriendsClient.InterfaceC3051i {

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity.this.f9162d.setEnabled(true);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing()) {
                    return;
                }
                StickerShopDetailActivity.this.m10169H();
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e$b */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity.this.f9162d.setEnabled(true);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing()) {
                    return;
                }
                StickerShopDetailActivity.this.m10169H();
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e$c */
        public class c implements C3197a.b {

            /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e$c$a */
            public class a implements Runnable {
                public a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (StickerShopDetailActivity.this.f9166h != null) {
                        StickerShopDetailActivity.this.f9166h.dismiss();
                        StickerShopDetailActivity.this.f9166h = null;
                    }
                }
            }

            public c() {
            }

            @Override // com.cyberlink.you.utility.C3197a.b
            /* renamed from: a */
            public void mo9122a() {
                StickerShopDetailActivity.this.f9167i.setVisibility(8);
                StickerShopDetailActivity.this.f9162d.setEnabled(true);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.m10171J() != null) {
                    StickerShopDetailActivity.this.m10171J().runOnUiThread(new a());
                }
            }

            @Override // com.cyberlink.you.utility.C3197a.b
            /* renamed from: b */
            public void mo9123b(String str) {
                StickerShopDetailActivity.this.m10174O();
            }

            @Override // com.cyberlink.you.utility.C3197a.b
            /* renamed from: c */
            public void mo9124c(int i9, int i10, int i11) {
                StickerShopDetailActivity.this.f9167i.setVisibility(0);
                StickerShopDetailActivity.this.f9168j.setProgress(i9);
                StickerShopDetailActivity.this.f9169k.setText(i9 + "%");
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$e$d */
        public class d implements Runnable {
            public d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity stickerShopDetailActivity = StickerShopDetailActivity.this;
                stickerShopDetailActivity.m10173N(stickerShopDetailActivity.f9161c.m14811o());
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing()) {
                    return;
                }
                StickerShopDetailActivity.this.m10169H();
            }
        }

        public C1757e() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("StickerShopDetailACT", "Response is null");
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new a());
                return;
            }
            if (!str3.equals("200")) {
                Log.d("StickerShopDetailACT", "statusCode=" + str3);
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new b());
                return;
            }
            if (StickerShopDetailActivity.this.f9161c.m14806j().equals("Purchase")) {
                Globals.m7388i0().m7555i(String.valueOf(StickerShopDetailActivity.this.f9161c.m14803g()));
            }
            if (StickerShopDetailActivity.this.f9161c.m14814r()) {
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new d());
                return;
            }
            String str5 = CLUtility.m16472O0(StickerShopDetailActivity.this) + File.separator + StickerShopDetailActivity.this.f9161c.m14803g() + ".zip";
            C3197a c3197a = new C3197a();
            c3197a.m16992o(StickerShopDetailActivity.this.f9161c.m14812p().f13073a);
            c3197a.m16990m(str5);
            c3197a.m16989l(true);
            c3197a.m16987j(new c());
            c3197a.m16993p();
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$f */
    public class RunnableC1758f implements Runnable {
        public RunnableC1758f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StickerShopDetailActivity stickerShopDetailActivity = StickerShopDetailActivity.this;
            stickerShopDetailActivity.m10173N(stickerShopDetailActivity.f9161c.m14811o());
            StickerShopDetailActivity.this.f9163e.setVisibility(8);
            if (StickerShopDetailActivity.this.f9166h == null || !StickerShopDetailActivity.this.f9166h.isShowing()) {
                return;
            }
            StickerShopDetailActivity.this.f9166h.dismiss();
            StickerShopDetailActivity.this.f9166h = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$g */
    public class ViewOnClickListenerC1759g implements View.OnClickListener {
        public ViewOnClickListenerC1759g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (StickerShopDetailActivity.this.f9161c == null) {
                return;
            }
            try {
                String strM14810n = StickerShopDetailActivity.this.f9161c.m14810n();
                if (strM14810n == null || strM14810n.isEmpty()) {
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(strM14810n));
                StickerShopDetailActivity.this.startActivity(intent);
            } catch (Exception e9) {
                Log.d("StickerShopDetailACT", Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$h */
    public class C1760h implements FriendsClient.InterfaceC3051i {

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$h$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity.this.f9167i.setVisibility(8);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing() || StickerShopDetailActivity.this.f9166h == null || !StickerShopDetailActivity.this.f9166h.isShowing()) {
                    return;
                }
                StickerShopDetailActivity.this.f9166h.dismiss();
                StickerShopDetailActivity.this.f9166h = null;
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$h$b */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity.this.f9167i.setVisibility(8);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing() || StickerShopDetailActivity.this.f9166h == null || !StickerShopDetailActivity.this.f9166h.isShowing()) {
                    return;
                }
                StickerShopDetailActivity.this.f9166h.dismiss();
                StickerShopDetailActivity.this.f9166h = null;
            }
        }

        /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$h$c */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                StickerShopDetailActivity.this.f9167i.setVisibility(8);
                StickerShopDetailActivity.this.f9163e.setVisibility(8);
                if (StickerShopDetailActivity.this.isFinishing() || StickerShopDetailActivity.this.f9166h == null || !StickerShopDetailActivity.this.f9166h.isShowing()) {
                    return;
                }
                StickerShopDetailActivity.this.f9166h.dismiss();
                StickerShopDetailActivity.this.f9166h = null;
            }
        }

        public C1760h() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws Throwable {
            if (str3 == null) {
                Log.d("StickerShopDetailACT", "Response is null");
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new a());
                return;
            }
            if (str3.equals("200")) {
                List<StickerObj> listM20109q = C5164m0.m20109q(str4, StickerShopDetailActivity.this.f9161c);
                if (listM20109q != null) {
                    C2950b0.m14924w().m15277e(listM20109q);
                    StickerShopDetailActivity.this.m10173N(StickerPackObj.Status.DOWNLOADED);
                }
                StickerShopDetailActivity.this.m10171J().runOnUiThread(new c());
                return;
            }
            Log.d("StickerShopDetailACT", "statusCode=" + str3);
            StickerShopDetailActivity.this.m10171J().runOnUiThread(new b());
        }
    }

    /* renamed from: com.cyberlink.you.activity.StickerShopDetailActivity$i */
    public class RunnableC1761i implements Runnable {
        public RunnableC1761i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StickerShopDetailActivity.this.m10168E();
        }
    }

    /* renamed from: D */
    public final void m10167D(String str, FriendsClient.InterfaceC3051i interfaceC3051i) {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        if (this.f9161c.m14806j().equals("Purchase")) {
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("packId", String.valueOf(this.f9161c.m14803g())));
            arrayList.add(new C6301o("receipt", this.f9164f));
            this.f9165g.m15734m("sticker", "user.pack.purchase", arrayList, interfaceC3051i);
            return;
        }
        if (this.f9161c.m14806j().equals("Free")) {
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("packId", str));
            this.f9165g.m15734m("sticker", "user.pack.create", arrayList, interfaceC3051i);
        }
    }

    /* renamed from: E */
    public final void m10168E() {
        this.f9162d.setEnabled(false);
        this.f9162d.setBackgroundResource(R.drawable.bg_edittext_normal_gray_border);
        this.f9162d.setTextColor(getResources().getColor(R.color.you_color_normal_gray_text));
        this.f9162d.setText(getString(R.string.downloaded));
    }

    /* renamed from: H */
    public final void m10169H() {
        C5152i0.m20065b(this.f9166h);
    }

    /* renamed from: I */
    public final void m10170I() {
        this.f9162d.setEnabled(false);
        this.f9163e.setVisibility(0);
        if (m10171J() != null) {
            m10171J().runOnUiThread(new RunnableC1755c());
        }
        String str = this.f9161c.m14812p().f13073a;
        StringBuilder sb = new StringBuilder();
        sb.append(m10171J().getCacheDir());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(this.f9161c.m14803g());
        sb.append(".zip");
        this.f9172n = new AsyncTaskC1756d(str, sb.toString(), CLUtility.m16537g1() + str2);
        if (!f9160s) {
            m10167D(String.valueOf(this.f9161c.m14803g()), new C1757e());
            return;
        }
        if (this.f9161c.m14814r()) {
            m10171J().runOnUiThread(new RunnableC1758f());
        } else {
            if (this.f9173o) {
                return;
            }
            this.f9173o = true;
            this.f9172n.executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: J */
    public final Activity m10171J() {
        return this;
    }

    /* renamed from: L */
    public final void m10172L() {
        if (m10171J().getCallingActivity() != null) {
            setResult(-1, null);
        }
        finish();
    }

    /* renamed from: N */
    public final void m10173N(StickerPackObj.Status status) {
        this.f9161c.m14819w(status);
        this.f9161c.m14818v(true);
        if (C2950b0.m14925x().m15293k(this.f9161c.m14803g()) == null) {
            this.f9161c.m14812p().f13077e = CLUtility.m16541h1(this.f9161c.m14803g()) + File.separator + "thumbnail";
        }
        C2950b0.m14925x().m15287e(this.f9161c, true);
        C2950b0.m14925x().m15298p(this.f9161c.m14803g());
        m10171J().runOnUiThread(new RunnableC1761i());
    }

    /* renamed from: O */
    public final void m10174O() {
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("packId", String.valueOf(this.f9161c.m14803g())));
        arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
        arrayList.add(new C6301o("pageSize", String.valueOf(200)));
        C1760h c1760h = new C1760h();
        this.f9175q = c1760h;
        this.f9165g.m15734m("sticker", "sticker.list", arrayList, c1760h);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sticker_shop_detail);
        findViewById(R.id.back).setOnClickListener(this.f9170l);
        findViewById(R.id.publisherText).setOnClickListener(this.f9174p);
        StickerPackObj stickerPackObj = (StickerPackObj) getIntent().getParcelableExtra("stickerPckObj");
        f9160s = getIntent().getBooleanExtra("isPurchased", false);
        Log.d("StickerShopDetailACT", "stickerPackObj.getPackId() = " + stickerPackObj.m14803g());
        Log.d("StickerShopDetailACT", "isPurchased = " + f9160s);
        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerPackObj.m14803g());
        if (stickerPackObjM15293k != null) {
            this.f9161c = stickerPackObjM15293k;
        } else {
            this.f9161c = stickerPackObj;
        }
        ((TextView) findViewById(R.id.auther)).setText(this.f9161c.m14808l());
        if (this.f9161c.m14812p().f13074b != null) {
            C6136j.m23599s(this, (ImageView) findViewById(R.id.cover), this.f9161c.m14812p().f13074b);
        }
        if (this.f9161c.m14804h() != null) {
            ((TextView) findViewById(R.id.name)).setText(this.f9161c.m14804h());
        }
        ((TextView) findViewById(R.id.expiration)).setText(getString(R.string.no_expiry_date));
        if (this.f9161c.m14797a() == null || this.f9161c.m14797a().equals("null")) {
            findViewById(R.id.description).setVisibility(8);
        } else {
            findViewById(R.id.description).setVisibility(0);
            ((TextView) findViewById(R.id.description)).setText(this.f9161c.m14797a());
        }
        if (this.f9161c.m14812p().f13078f != null) {
            C6136j.m23599s(this, (ImageView) findViewById(R.id.preview), this.f9161c.m14812p().f13078f);
        }
        this.f9162d = (Button) findViewById(R.id.download);
        if (this.f9161c.m14815s() && this.f9161c.m14814r()) {
            m10168E();
        } else {
            if (!f9160s && this.f9161c.m14806j().equals("Purchase")) {
                this.f9162d.setText(getResources().getString(R.string.purchase));
            }
            this.f9162d.setOnClickListener(this.f9171m);
        }
        if (this.f9161c.m14810n() == null || this.f9161c.m14810n().isEmpty()) {
            findViewById(R.id.publisherArea).setVisibility(8);
        }
        if (this.f9161c.m14806j().equals("Purchase")) {
            f9159r = this.f9161c.m14800d();
        } else {
            ((TextView) findViewById(R.id.text)).setText(getString(R.string.now_get_it_free));
        }
        this.f9163e = findViewById(R.id.loading);
        this.f9165g = new FriendsClient(true);
        this.f9167i = (FrameLayout) findViewById(R.id.downloadLayout);
        this.f9168j = (ProgressBar) findViewById(R.id.progressBar);
        this.f9169k = (TextView) findViewById(R.id.progressText);
        Log.d("StickerShopDetailACT", "pack id = " + this.f9161c.m14803g());
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.d("StickerShopDetailACT", "Destroying helper.");
        AsyncTask<Void, Void, Boolean> asyncTask = this.f9172n;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        m10169H();
        FriendsClient friendsClient = this.f9165g;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        m10172L();
        return true;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
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
