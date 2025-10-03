package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;

/* loaded from: classes2.dex */
public final class EmailAddressResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_email, C4453R.string.button_add_contact};

    public EmailAddressResultHandler(Activity activity, ParsedResult parsedResult) {
        super(activity, parsedResult);
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonCount() {
        return buttons.length;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getButtonText(int i9) {
        return buttons[i9];
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_email_address;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        EmailAddressParsedResult emailAddressParsedResult = (EmailAddressParsedResult) getResult();
        if (i9 == 0) {
            sendEmail(emailAddressParsedResult.getTos(), emailAddressParsedResult.getCCs(), emailAddressParsedResult.getBCCs(), emailAddressParsedResult.getSubject(), emailAddressParsedResult.getBody());
        } else {
            if (i9 != 1) {
                return;
            }
            addEmailOnlyContact(emailAddressParsedResult.getTos(), null);
        }
    }
}
