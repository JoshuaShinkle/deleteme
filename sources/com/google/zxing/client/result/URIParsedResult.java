package com.google.zxing.client.result;

import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class URIParsedResult extends ParsedResult {
    private static final Pattern USER_IN_HOST = Pattern.compile(":/*([^/@]+)@[^/]+");
    private final String title;
    private final String uri;

    public URIParsedResult(String str, String str2) {
        super(ParsedResultType.URI);
        this.uri = massageURI(str);
        this.title = str2;
    }

    private static boolean isColonFollowedByPortNumber(String str, int i9) {
        int i10 = i9 + 1;
        int iIndexOf = str.indexOf(47, i10);
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        return ResultParser.isSubstringOfDigits(str, i10, iIndexOf - i10);
    }

    private static String massageURI(String str) {
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(58);
        if (iIndexOf >= 0 && !isColonFollowedByPortNumber(strTrim, iIndexOf)) {
            return strTrim;
        }
        return "http://" + strTrim;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(30);
        ParsedResult.maybeAppend(this.title, sb);
        ParsedResult.maybeAppend(this.uri, sb);
        return sb.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public String getURI() {
        return this.uri;
    }

    public boolean isPossiblyMaliciousURI() {
        return USER_IN_HOST.matcher(this.uri).find();
    }
}
