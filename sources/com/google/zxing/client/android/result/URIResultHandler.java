package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class URIResultHandler extends ResultHandler {
    private static final String[] SECURE_PROTOCOLS = {"otpauth:"};
    private static final int[] buttons = {C4453R.string.button_open_browser, C4453R.string.button_share_by_email, C4453R.string.button_share_by_sms, C4453R.string.button_search_book_contents};

    public URIResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public boolean areContentsSecure() {
        String lowerCase = ((URIParsedResult) getResult()).getURI().toLowerCase(Locale.ENGLISH);
        for (String str : SECURE_PROTOCOLS) {
            if (lowerCase.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return LocaleManager.isBookSearchUrl(((URIParsedResult) getResult()).getURI()) ? buttons.length : buttons.length - 1;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i9) {
        return buttons[i9];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public Integer getDefaultButtonID() {
        return 0;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_uri;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        String uri = ((URIParsedResult) getResult()).getURI();
        if (i9 == 0) {
            openURL(uri);
            return;
        }
        if (i9 == 1) {
            shareByEmail(uri);
        } else if (i9 == 2) {
            shareBySMS(uri);
        } else {
            if (i9 != 3) {
                return;
            }
            searchBookContents(uri);
        }
    }
}
