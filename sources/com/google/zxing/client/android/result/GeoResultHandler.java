package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ParsedResult;

/* loaded from: classes2.dex */
public final class GeoResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_show_map, C4453R.string.button_get_directions};

    public GeoResultHandler(Activity activity, ParsedResult parsedResult) {
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
        return C4453R.string.result_geo;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        GeoParsedResult geoParsedResult = (GeoParsedResult) getResult();
        if (i9 == 0) {
            openMap(geoParsedResult.getGeoURI());
        } else {
            if (i9 != 1) {
                return;
            }
            getDirections(geoParsedResult.getLatitude(), geoParsedResult.getLongitude());
        }
    }
}
