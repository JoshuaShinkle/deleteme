package com.google.zxing.client.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class HelpActivity extends Activity {
    private static final String BASE_URL = "file:///android_asset/html-" + LocaleManager.getTranslatedAssetLanguage() + IOUtils.DIR_SEPARATOR_UNIX;
    private WebView webView;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4453R.layout.help);
        WebView webView = (WebView) findViewById(C4453R.id.help_contents);
        this.webView = webView;
        if (bundle != null) {
            webView.restoreState(bundle);
            return;
        }
        webView.loadUrl(BASE_URL + "index.html");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (i9 != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(i9, keyEvent);
        }
        this.webView.goBack();
        return true;
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.webView.saveState(bundle);
    }
}
