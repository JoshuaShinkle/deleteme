package com.google.android.gms.appinvite;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import java.util.ArrayList;

@KeepForSdkWithMembers
/* loaded from: classes2.dex */
public class PreviewActivity extends Activity {
    public static final String ACTION_PREVIEW = "com.google.android.gms.appinvite.ACTION_PREVIEW";
    public static final String EXTRA_LAYOUT_RES_ID = "com.google.android.gms.appinvite.LAYOUT_RES_ID";
    public static final String EXTRA_TABS = "com.google.android.gms.appinvite.TABS";
    public static final String EXTRA_VIEWS = "com.google.android.gms.appinvite.VIEWS";
    public static final String KEY_TAB_CONTENT_ID = "tabContentId";
    public static final String KEY_TAB_TAG = "tabTag";
    public static final String KEY_TEXT_VIEW_IS_TITLE = "TextView_isTitle";
    public static final String KEY_TEXT_VIEW_TEXT = "TextView_text";
    public static final String KEY_TEXT_VIEW_TEXT_COLOR = "TextView_textColor";
    public static final String KEY_VIEW_BACKGROUND_COLOR = "View_backgroundColor";
    public static final String KEY_VIEW_ID = "View_id";
    public static final String KEY_VIEW_MIN_HEIGHT = "View_minHeight";
    public static final String KEY_VIEW_ON_CLICK_LISTENER = "View_onClickListener";
    public static final String KEY_WEB_VIEW_DATA = "WebView_data";
    public static final String ON_CLICK_LISTENER_CLOSE = "close";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final View zza(Context context, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = LayoutInflater.from(context).inflate(bundle.getInt(EXTRA_LAYOUT_RES_ID), viewGroup, false);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(EXTRA_VIEWS);
        if (parcelableArrayList != null) {
            int size = parcelableArrayList.size();
            int i9 = 0;
            while (i9 < size) {
                Object obj = parcelableArrayList.get(i9);
                i9++;
                Bundle bundle2 = (Bundle) obj;
                View viewFindViewById = viewInflate.findViewById(bundle2.getInt(KEY_VIEW_ID));
                for (String str : bundle2.keySet()) {
                    str.hashCode();
                    switch (str) {
                        case "View_minHeight":
                            viewFindViewById.setMinimumHeight(bundle2.getInt(str));
                            break;
                        case "TextView_text":
                            if (viewFindViewById instanceof TextView) {
                                ((TextView) viewFindViewById).setText(bundle2.getCharSequence(str));
                                break;
                            } else {
                                break;
                            }
                        case "WebView_data":
                            if (viewFindViewById instanceof ViewGroup) {
                                WebView webView = new WebView(this);
                                webView.loadData(bundle2.getString(str), "text/html; charset=utf-8", "UTF-8");
                                ((ViewGroup) viewFindViewById).addView(webView, new ViewGroup.LayoutParams(-1, -1));
                                break;
                            } else {
                                break;
                            }
                        case "TextView_textColor":
                            if (viewFindViewById instanceof TextView) {
                                ((TextView) viewFindViewById).setTextColor(bundle2.getInt(str));
                                break;
                            } else {
                                break;
                            }
                        case "View_backgroundColor":
                            viewFindViewById.setBackgroundColor(bundle2.getInt(str));
                            break;
                        case "TextView_isTitle":
                            if (!(viewFindViewById instanceof TextView) || !bundle2.getBoolean(str)) {
                                break;
                            } else {
                                setTitle(((TextView) viewFindViewById).getText());
                                break;
                            }
                        case "View_onClickListener":
                            String string = bundle2.getString(str);
                            string.hashCode();
                            if (string.equals(ON_CLICK_LISTENER_CLOSE)) {
                                viewFindViewById.setOnClickListener(new zzb(this));
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
        }
        return viewInflate;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) throws PackageManager.NameNotFoundException {
        super.onCreate(bundle);
        if (getCallingActivity() == null || !"com.google.android.gms".equals(getCallingActivity().getPackageName())) {
            finish();
            return;
        }
        int i9 = 0;
        try {
            Context contextCreatePackageContext = createPackageContext("com.google.android.gms", 0);
            Bundle extras = getIntent().getExtras();
            View viewZza = zza(contextCreatePackageContext, null, extras);
            if (viewZza == null) {
                finish();
                return;
            }
            TabHost tabHost = (TabHost) viewZza.findViewById(android.R.id.tabhost);
            TabWidget tabWidget = (TabWidget) viewZza.findViewById(android.R.id.tabs);
            ArrayList parcelableArrayList = extras.getParcelableArrayList(EXTRA_TABS);
            if (tabHost != null && tabWidget != null && parcelableArrayList != null) {
                tabHost.setup();
                int size = parcelableArrayList.size();
                while (i9 < size) {
                    Object obj = parcelableArrayList.get(i9);
                    i9++;
                    Bundle bundle2 = (Bundle) obj;
                    TabHost.TabSpec tabSpecNewTabSpec = tabHost.newTabSpec(bundle2.getString(KEY_TAB_TAG));
                    tabSpecNewTabSpec.setContent(bundle2.getInt(KEY_TAB_CONTENT_ID));
                    tabSpecNewTabSpec.setIndicator(zza(contextCreatePackageContext, tabWidget, bundle2));
                    tabHost.addTab(tabSpecNewTabSpec);
                }
            }
            setContentView(viewZza);
        } catch (PackageManager.NameNotFoundException unused) {
            finish();
        }
    }
}
