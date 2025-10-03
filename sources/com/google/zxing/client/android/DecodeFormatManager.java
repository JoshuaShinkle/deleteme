package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.android.Intents;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
final class DecodeFormatManager {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE;
    static final Set<BarcodeFormat> QR_CODE_FORMATS;

    static {
        EnumSet enumSetOf = EnumSet.of(BarcodeFormat.QR_CODE);
        QR_CODE_FORMATS = enumSetOf;
        HashMap map = new HashMap();
        FORMATS_FOR_MODE = map;
        map.put(Intents.Scan.QR_CODE_MODE, enumSetOf);
    }

    private DecodeFormatManager() {
    }

    public static Set<BarcodeFormat> parseDecodeFormats(Intent intent) {
        String stringExtra = intent.getStringExtra(Intents.Scan.FORMATS);
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra(Intents.Scan.MODE));
    }

    public static Set<BarcodeFormat> parseDecodeFormats(Uri uri) {
        List<String> queryParameters = uri.getQueryParameters(Intents.Scan.FORMATS);
        if (queryParameters != null && queryParameters.size() == 1 && queryParameters.get(0) != null) {
            queryParameters = Arrays.asList(COMMA_PATTERN.split(queryParameters.get(0)));
        }
        return parseDecodeFormats(queryParameters, uri.getQueryParameter(Intents.Scan.MODE));
    }

    private static Set<BarcodeFormat> parseDecodeFormats(Iterable<String> iterable, String str) {
        if (iterable != null) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    enumSetNoneOf.add(BarcodeFormat.valueOf(it.next()));
                }
                return enumSetNoneOf;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str != null) {
            return FORMATS_FOR_MODE.get(str);
        }
        return null;
    }
}
