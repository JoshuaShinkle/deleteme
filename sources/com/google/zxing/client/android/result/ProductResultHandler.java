package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.android.C4453R;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;

/* loaded from: classes2.dex */
public final class ProductResultHandler extends ResultHandler {
    private static final int[] buttons = {C4453R.string.button_product_search, C4453R.string.button_web_search, C4453R.string.button_custom_product_search};

    public ProductResultHandler(Activity activity, ParsedResult parsedResult, Result result) {
        super(activity, parsedResult, result);
    }

    private static String getProductIDFromResult(ParsedResult parsedResult) {
        if (parsedResult instanceof ProductParsedResult) {
            return ((ProductParsedResult) parsedResult).getNormalizedProductID();
        }
        if (parsedResult instanceof ExpandedProductParsedResult) {
            return ((ExpandedProductParsedResult) parsedResult).getRawText();
        }
        throw new IllegalArgumentException(parsedResult.getClass().toString());
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
        return C4453R.string.result_product;
    }

    @Override // com.google.zxing.client.android.result.ResultHandler
    public void handleButtonPress(int i9) {
        String productIDFromResult = getProductIDFromResult(getResult());
        if (i9 == 0) {
            openProductSearch(productIDFromResult);
        } else if (i9 == 1) {
            webSearch(productIDFromResult);
        } else {
            if (i9 != 2) {
                return;
            }
            openURL(fillInCustomSearchURL(productIDFromResult));
        }
    }
}
