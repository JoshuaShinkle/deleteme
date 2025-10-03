package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public abstract class ResultParser {
    private static final String BYTE_ORDER_MARK = "\ufeff";
    private static final ResultParser[] PARSERS = {new BookmarkDoCoMoResultParser(), new AddressBookDoCoMoResultParser(), new EmailDoCoMoResultParser(), new AddressBookAUResultParser(), new VCardResultParser(), new BizcardResultParser(), new VEventResultParser(), new EmailAddressResultParser(), new SMTPResultParser(), new TelResultParser(), new SMSMMSResultParser(), new SMSTOMMSTOResultParser(), new GeoResultParser(), new WifiResultParser(), new URLTOResultParser(), new URIResultParser(), new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private static final Pattern AMPERSAND = Pattern.compile("&");
    private static final Pattern EQUALS = Pattern.compile("=");

    private static void appendKeyValue(CharSequence charSequence, Map<String, String> map) {
        String[] strArrSplit = EQUALS.split(charSequence, 2);
        if (strArrSplit.length == 2) {
            try {
                map.put(strArrSplit[0], urlDecode(strArrSplit[1]));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private static int countPrecedingBackslashes(CharSequence charSequence, int i9) {
        int i10 = 0;
        for (int i11 = i9 - 1; i11 >= 0 && charSequence.charAt(i11) == '\\'; i11--) {
            i10++;
        }
        return i10;
    }

    public static String getMassagedText(Result result) {
        String text = result.getText();
        return text.startsWith(BYTE_ORDER_MARK) ? text.substring(1) : text;
    }

    public static boolean isStringOfDigits(CharSequence charSequence, int i9) {
        return charSequence != null && i9 > 0 && i9 == charSequence.length() && DIGITS.matcher(charSequence).matches();
    }

    public static boolean isSubstringOfDigits(CharSequence charSequence, int i9, int i10) {
        int i11;
        return charSequence != null && i10 > 0 && charSequence.length() >= (i11 = i10 + i9) && DIGITS.matcher(charSequence.subSequence(i9, i11)).matches();
    }

    public static String[] matchPrefixedField(String str, String str2, char c9, boolean z8) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i9 = 0;
        while (i9 < length) {
            int iIndexOf = str2.indexOf(str, i9);
            if (iIndexOf < 0) {
                break;
            }
            int length2 = iIndexOf + str.length();
            boolean z9 = true;
            ArrayList arrayList2 = arrayList;
            int length3 = length2;
            while (z9) {
                int iIndexOf2 = str2.indexOf(c9, length3);
                if (iIndexOf2 < 0) {
                    length3 = str2.length();
                } else if (countPrecedingBackslashes(str2, iIndexOf2) % 2 != 0) {
                    length3 = iIndexOf2 + 1;
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(3);
                    }
                    String strUnescapeBackslash = unescapeBackslash(str2.substring(length2, iIndexOf2));
                    if (z8) {
                        strUnescapeBackslash = strUnescapeBackslash.trim();
                    }
                    if (!strUnescapeBackslash.isEmpty()) {
                        arrayList2.add(strUnescapeBackslash);
                    }
                    length3 = iIndexOf2 + 1;
                }
                z9 = false;
            }
            i9 = length3;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String matchSinglePrefixedField(String str, String str2, char c9, boolean z8) {
        String[] strArrMatchPrefixedField = matchPrefixedField(str, str2, c9, z8);
        if (strArrMatchPrefixedField == null) {
            return null;
        }
        return strArrMatchPrefixedField[0];
    }

    public static void maybeAppend(String str, StringBuilder sb) {
        if (str != null) {
            sb.append('\n');
            sb.append(str);
        }
    }

    public static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    public static int parseHexDigit(char c9) {
        if (c9 >= '0' && c9 <= '9') {
            return c9 - '0';
        }
        char c10 = 'a';
        if (c9 < 'a' || c9 > 'f') {
            c10 = 'A';
            if (c9 < 'A' || c9 > 'F') {
                return -1;
            }
        }
        return (c9 - c10) + 10;
    }

    public static Map<String, String> parseNameValuePairs(String str) {
        int iIndexOf = str.indexOf(63);
        if (iIndexOf < 0) {
            return null;
        }
        HashMap map = new HashMap(3);
        for (String str2 : AMPERSAND.split(str.substring(iIndexOf + 1))) {
            appendKeyValue(str2, map);
        }
        return map;
    }

    public static ParsedResult parseResult(Result result) {
        for (ResultParser resultParser : PARSERS) {
            ParsedResult parsedResult = resultParser.parse(result);
            if (parsedResult != null) {
                return parsedResult;
            }
        }
        return new TextParsedResult(result.getText(), null);
    }

    public static String unescapeBackslash(String str) {
        int iIndexOf = str.indexOf(92);
        if (iIndexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length - 1);
        sb.append(str.toCharArray(), 0, iIndexOf);
        boolean z8 = false;
        while (iIndexOf < length) {
            char cCharAt = str.charAt(iIndexOf);
            if (z8 || cCharAt != '\\') {
                sb.append(cCharAt);
                z8 = false;
            } else {
                z8 = true;
            }
            iIndexOf++;
        }
        return sb.toString();
    }

    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public abstract ParsedResult parse(Result result);

    public static void maybeAppend(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                sb.append('\n');
                sb.append(str);
            }
        }
    }
}
