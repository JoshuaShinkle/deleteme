package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;

/* loaded from: classes2.dex */
public final class SMSResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_sms, C4453R.string.button_mms};

    public SMSResultHandler(Activity activity, ParsedResult parsedResult) {
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
    public CharSequence getDisplayContents() {
        SMSParsedResult sMSParsedResult = (SMSParsedResult) getResult();
        String[] numbers = sMSParsedResult.getNumbers();
        String[] strArr = new String[numbers.length];
        for (int i9 = 0; i9 < numbers.length; i9++) {
            strArr[i9] = PhoneNumberUtils.formatNumber(numbers[i9]);
        }
        StringBuilder sb = new StringBuilder(50);
        ParsedResult.maybeAppend(strArr, sb);
        ParsedResult.maybeAppend(sMSParsedResult.getSubject(), sb);
        ParsedResult.maybeAppend(sMSParsedResult.getBody(), sb);
        return sb.toString();
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_sms;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        SMSParsedResult sMSParsedResult = (SMSParsedResult) getResult();
        String str = sMSParsedResult.getNumbers()[0];
        if (i9 == 0) {
            sendSMS(str, sMSParsedResult.getBody());
        } else {
            if (i9 != 1) {
                return;
            }
            sendMMS(str, sMSParsedResult.getSubject(), sMSParsedResult.getBody());
        }
    }
}
