package com.cyberlink.you.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.p036ui.DialogC3133q;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5187v0;
import p209u2.C6383t;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class WebViewActivity extends BaseActivity {

    /* renamed from: c */
    public WebView f9686c;

    /* renamed from: d */
    public TextView f9687d;

    /* renamed from: e */
    public String f9688e;

    /* renamed from: f */
    public JSONObject f9689f;

    /* renamed from: g */
    public boolean f9690g;

    /* renamed from: h */
    public DialogC3133q f9691h;

    /* renamed from: i */
    public WebViewClient f9692i = new C1862a();

    /* renamed from: j */
    public WebChromeClient f9693j = new C1863b();

    /* renamed from: com.cyberlink.you.activity.WebViewActivity$a */
    public class C1862a extends WebViewClient {
        public C1862a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewActivity.this.f9691h.dismiss();
            if (WebViewActivity.this.f9690g) {
                WebViewActivity webViewActivity = WebViewActivity.this;
                webViewActivity.m11037s(webViewActivity.f9688e, WebViewActivity.this.f9689f.toString());
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (WebViewActivity.this.f9691h.isShowing()) {
                return;
            }
            WebViewActivity.this.f9691h.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.WebViewActivity$b */
    public class C1863b extends WebChromeClient {
        public C1863b() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            WebViewActivity.this.f9687d.setText(webView.getTitle());
        }
    }

    /* renamed from: com.cyberlink.you.activity.WebViewActivity$c */
    public class C1864c {
        public C1864c() {
        }

        @JavascriptInterface
        public void onError(String str) {
            throw new Error(str);
        }

        public /* synthetic */ C1864c(WebViewActivity webViewActivity, C1862a c1862a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.WebViewActivity$d */
    public static class AsyncTaskC1865d extends AsyncTask<Void, Void, String> {

        /* renamed from: a */
        public WeakReference<Context> f9697a;

        /* renamed from: b */
        public Intent f9698b;

        public AsyncTaskC1865d(Context context, Intent intent) {
            this.f9697a = new WeakReference<>(context);
            this.f9698b = intent;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            return FriendsClient.m15648M();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            if (C6383t.m24517f(str)) {
                C5187v0.m20267c(R.string.error_server_response);
            } else {
                this.f9698b.putExtra("userAttribute", str);
                this.f9697a.get().startActivity(this.f9698b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m11033v(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m11034w(DialogC3133q dialogC3133q) {
        finish();
    }

    /* renamed from: y */
    public static void m11035y(Context context, String str) {
        m11036z(context, str, false);
    }

    /* renamed from: z */
    public static void m11036z(Context context, String str, boolean z8) {
        Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
        intent.putExtra("URL", str);
        if (!z8) {
            context.startActivity(intent);
        } else {
            intent.putExtra("isStickerContest", true);
            new AsyncTaskC1865d(context, intent).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f9686c.canGoBack()) {
            this.f9686c.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void onCreate(Bundle bundle) throws JSONException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web_view);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.en
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10417b.m11033v(view);
            }
        });
        this.f9687d = (TextView) findViewById(R.id.title);
        this.f9686c = (WebView) findViewById(R.id.webView);
        this.f9691h = new DialogC3133q.b(this).m16412c(true).m16414e(new DialogC3133q.c() { // from class: com.cyberlink.you.activity.fn
            @Override // com.cyberlink.you.p036ui.DialogC3133q.c
            /* renamed from: a */
            public final void mo10190a(DialogC3133q dialogC3133q) {
                this.f10504a.m11034w(dialogC3133q);
            }
        }).m16410a();
        Intent intent = getIntent();
        this.f9690g = intent.getBooleanExtra("isStickerContest", false);
        String stringExtra = intent.getStringExtra("URL");
        this.f9686c.setWebViewClient(this.f9692i);
        this.f9686c.setWebChromeClient(this.f9693j);
        WebSettings settings = this.f9686c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setTextZoom(100);
        settings.setDomStorageEnabled(true);
        if (this.f9690g) {
            this.f9686c.addJavascriptInterface(new C1864c(this, null), "AndroidErrorReporter");
            this.f9688e = Uri.parse(stringExtra).getQueryParameter("callback");
            m11038u(intent.getStringExtra("userAttribute"));
        }
        this.f9686c.loadUrl(stringExtra);
    }

    /* renamed from: s */
    public final void m11037s(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:try{");
        sb.append(str);
        sb.append("(");
        for (int i9 = 0; i9 < objArr.length; i9++) {
            Object obj = objArr[i9];
            if (obj instanceof String) {
                sb.append("'");
                sb.append(obj);
                sb.append("'");
            }
            if (i9 < objArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(")}catch(error){Android.onError(error.message);}");
        this.f9686c.loadUrl(sb.toString());
    }

    /* renamed from: u */
    public final void m11038u(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        this.f9689f = jSONObject;
        try {
            jSONObject.put("token", str);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }
}
