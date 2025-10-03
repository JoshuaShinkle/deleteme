package com.google.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.TelParsedResult;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class TelResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_dial, C4453R.string.button_add_contact};

    public TelResultHandler(Activity activity, ParsedResult parsedResult) {
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
        return PhoneNumberUtils.formatNumber(getResult().getDisplayResult().replace(StringUtils.f19107CR, ""));
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public int getDisplayTitle() {
        return C4453R.string.result_tel;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        TelParsedResult telParsedResult = (TelParsedResult) getResult();
        if (i9 == 0) {
            dialPhoneFromUri(telParsedResult.getTelURI());
            getActivity().finish();
        } else {
            if (i9 != 1) {
                return;
            }
            addPhoneOnlyContact(new String[]{telParsedResult.getNumber()}, null);
        }
    }
}
