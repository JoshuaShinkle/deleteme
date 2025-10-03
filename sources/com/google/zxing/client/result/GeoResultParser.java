package com.google.zxing.client.result;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class GeoResultParser extends ResultParser {
    private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    @Override // com.google.zxing.client.result.ResultParser
    public GeoParsedResult parse(Result result) throws NumberFormatException {
        Matcher matcher = GEO_URL_PATTERN.matcher(ResultParser.getMassagedText(result));
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(4);
        try {
            double d9 = Double.parseDouble(matcher.group(1));
            if (d9 <= 90.0d && d9 >= -90.0d) {
                double d10 = Double.parseDouble(matcher.group(2));
                if (d10 <= 180.0d && d10 >= -180.0d) {
                    String strGroup2 = matcher.group(3);
                    double d11 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    if (strGroup2 != null) {
                        double d12 = Double.parseDouble(matcher.group(3));
                        if (d12 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            return null;
                        }
                        d11 = d12;
                    }
                    return new GeoParsedResult(d9, d10, d11, strGroup);
                }
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }
}
