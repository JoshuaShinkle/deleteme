package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.ParsedResult;

/* loaded from: classes2.dex */
public final class TextResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_web_search, C4453R.string.button_share_by_email, C4453R.string.button_share_by_sms, C4453R.string.button_custom_product_search};

    public TextResultHandler(Activity activity, ParsedResult parsedResult, Result result) {
        super(activity, parsedResult, result);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return hasCustomProductSearch() ? buttons.length : buttons.length - 1;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i9) {
        return buttons[i9];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_text;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        String displayResult = getResult().getDisplayResult();
        if (i9 == 0) {
            webSearch(displayResult);
            return;
        }
        if (i9 == 1) {
            shareByEmail(displayResult);
        } else if (i9 == 2) {
            shareBySMS(displayResult);
        } else {
            if (i9 != 3) {
                return;
            }
            openURL(fillInCustomSearchURL(displayResult));
        }
    }
}
