package com.google.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;

/* loaded from: classes2.dex */
public final class ResultHandlerFactory {

    /* renamed from: com.google.zxing.client.android.result.ResultHandlerFactory$1 */
    public static /* synthetic */ class C44591 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$client$result$ParsedResultType;

        static {
            int[] iArr = new int[ParsedResultType.values().length];
            $SwitchMap$com$google$zxing$client$result$ParsedResultType = iArr;
            try {
                iArr[ParsedResultType.ADDRESSBOOK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.EMAIL_ADDRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.PRODUCT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.URI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.GEO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.TEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.SMS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.CALENDAR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$zxing$client$result$ParsedResultType[ParsedResultType.ISBN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private ResultHandlerFactory() {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result result) {
        ParsedResult result2 = parseResult(result);
        switch (C44591.$SwitchMap$com$google$zxing$client$result$ParsedResultType[result2.getType().ordinal()]) {
            case 1:
                return new AddressBookResultHandler(activity, result2);
            case 2:
                return new EmailAddressResultHandler(activity, result2);
            case 3:
                return new ProductResultHandler(activity, result2, result);
            case 4:
                return new URIResultHandler(activity, result2);
            case 5:
                return new GeoResultHandler(activity, result2);
            case 6:
                return new TelResultHandler(activity, result2);
            case 7:
                return new SMSResultHandler(activity, result2);
            case 8:
                return new CalendarResultHandler(activity, result2);
            case 9:
                return new ISBNResultHandler(activity, result2, result);
            default:
                return new TextResultHandler(activity, result2, result);
        }
    }

    private static ParsedResult parseResult(Result result) {
        return ResultParser.parseResult(result);
    }
}
