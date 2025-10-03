package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.google.zxing.client.android.Intents;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char cCharAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i9 = 0;
        while (i9 < length) {
            char cCharAt2 = charSequence.charAt(i9);
            if (cCharAt2 != '\n' && cCharAt2 != '\r') {
                if (cCharAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(cCharAt2);
                } else if (i9 < length - 2 && (cCharAt = charSequence.charAt(i9 + 1)) != '\r' && cCharAt != '\n') {
                    i9 += 2;
                    char cCharAt3 = charSequence.charAt(i9);
                    int hexDigit = ResultParser.parseHexDigit(cCharAt);
                    int hexDigit2 = ResultParser.parseHexDigit(cCharAt3);
                    if (hexDigit >= 0 && hexDigit2 >= 0) {
                        byteArrayOutputStream.write((hexDigit << 4) + hexDigit2);
                    }
                }
            }
            i9++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        int iIndexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i9 = 0;
                int i10 = 0;
                while (i9 < 4 && (iIndexOf = str.indexOf(59, i10)) >= 0) {
                    strArr[i9] = str.substring(i10, iIndexOf);
                    i9++;
                    i10 = iIndexOf + 1;
                }
                strArr[i9] = str.substring(i10);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    public static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z8, boolean z9) {
        List<List<String>> listMatchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z8, z9);
        if (listMatchVCardPrefixedField == null || listMatchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return listMatchVCardPrefixedField.get(0);
    }

    public static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z8, boolean z9) {
        ArrayList arrayList;
        int i9;
        String str2;
        int iIndexOf;
        int i10;
        int i11;
        String strReplaceAll;
        int length = str.length();
        int i12 = 0;
        int i13 = 0;
        ArrayList arrayList2 = null;
        while (i13 < length) {
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i13 > 0) {
                i13--;
            }
            if (!matcher.find(i13)) {
                break;
            }
            int iEnd = matcher.end(i12);
            String strGroup = matcher.group(1);
            if (strGroup != null) {
                String[] strArrSplit = SEMICOLON.split(strGroup);
                int length2 = strArrSplit.length;
                int i14 = i12;
                i9 = i14;
                arrayList = null;
                str2 = null;
                while (i14 < length2) {
                    String str3 = strArrSplit[i14];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str3);
                    String[] strArrSplit2 = EQUALS.split(str3, 2);
                    if (strArrSplit2.length > 1) {
                        String str4 = strArrSplit2[i12];
                        String str5 = strArrSplit2[1];
                        if ("ENCODING".equalsIgnoreCase(str4) && "QUOTED-PRINTABLE".equalsIgnoreCase(str5)) {
                            i9 = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str4)) {
                            str2 = str5;
                        }
                    }
                    i14++;
                    i12 = 0;
                }
            } else {
                arrayList = null;
                i9 = 0;
                str2 = null;
            }
            int i15 = iEnd;
            while (true) {
                iIndexOf = str.indexOf(10, i15);
                if (iIndexOf < 0) {
                    break;
                }
                if (iIndexOf < str.length() - 1) {
                    int i16 = iIndexOf + 1;
                    if (str.charAt(i16) == ' ' || str.charAt(i16) == '\t') {
                        i15 = iIndexOf + 2;
                    }
                }
                if (i9 == 0 || ((iIndexOf < 1 || str.charAt(iIndexOf - 1) != '=') && (iIndexOf < 2 || str.charAt(iIndexOf - 2) != '='))) {
                    break;
                }
                i15 = iIndexOf + 1;
            }
            if (iIndexOf < 0) {
                i11 = length;
                i10 = 0;
            } else if (iIndexOf <= iEnd) {
                i10 = 0;
                i11 = iIndexOf + 1;
            } else {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                }
                if (iIndexOf >= 1 && str.charAt(iIndexOf - 1) == '\r') {
                    iIndexOf--;
                }
                String strSubstring = str.substring(iEnd, iIndexOf);
                if (z8) {
                    strSubstring = strSubstring.trim();
                }
                if (i9 != 0) {
                    strReplaceAll = decodeQuotedPrintable(strSubstring, str2);
                    if (z9) {
                        strReplaceAll = UNESCAPED_SEMICOLONS.matcher(strReplaceAll).replaceAll("\n").trim();
                    }
                } else {
                    if (z9) {
                        strSubstring = UNESCAPED_SEMICOLONS.matcher(strSubstring).replaceAll("\n").trim();
                    }
                    strReplaceAll = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(strSubstring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if (arrayList == null) {
                    ArrayList arrayList3 = new ArrayList(1);
                    arrayList3.add(strReplaceAll);
                    arrayList2.add(arrayList3);
                    i10 = 0;
                    i11 = iIndexOf + 1;
                } else {
                    i10 = 0;
                    arrayList.add(0, strReplaceAll);
                    arrayList2.add(arrayList);
                    i11 = iIndexOf + 1;
                }
            }
            i12 = i10;
            i13 = i11;
        }
        return arrayList2;
    }

    private static void maybeAppendComponent(String[] strArr, int i9, StringBuilder sb) {
        String str = strArr[i9];
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i9]);
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<List<String>> it = collection.iterator();
        while (it.hasNext()) {
            String str = it.next().get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String strSubstring;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            int i9 = 1;
            while (true) {
                if (i9 >= list.size()) {
                    strSubstring = null;
                    break;
                }
                strSubstring = list.get(i9);
                int iIndexOf = strSubstring.indexOf(61);
                if (iIndexOf >= 0) {
                    if (Intents.WifiConnect.TYPE.equalsIgnoreCase(strSubstring.substring(0, iIndexOf))) {
                        strSubstring = strSubstring.substring(iIndexOf + 1);
                        break;
                    }
                    i9++;
                }
            }
            arrayList.add(strSubstring);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> listMatchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
        if (listMatchVCardPrefixedField == null) {
            listMatchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
            formatNames(listMatchVCardPrefixedField);
        }
        List<String> listMatchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
        String[] strArrSplit = listMatchSingleVCardPrefixedField == null ? null : COMMA.split(listMatchSingleVCardPrefixedField.get(0));
        List<List<String>> listMatchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
        List<List<String>> listMatchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
        List<List<String>> listMatchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
        List<String> listMatchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
        List<String> listMatchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
        List<String> list = (listMatchSingleVCardPrefixedField4 == null || isLikeVCardDate(listMatchSingleVCardPrefixedField4.get(0))) ? listMatchSingleVCardPrefixedField4 : null;
        List<String> listMatchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField("TITLE", massagedText, true, false);
        List<List<String>> listMatchVCardPrefixedField5 = matchVCardPrefixedField("URL", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
        List<String> listMatchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
        String[] strArrSplit2 = listMatchSingleVCardPrefixedField7 == null ? null : SEMICOLON_OR_COMMA.split(listMatchSingleVCardPrefixedField7.get(0));
        return new AddressBookParsedResult(toPrimaryValues(listMatchVCardPrefixedField), strArrSplit, null, toPrimaryValues(listMatchVCardPrefixedField2), toTypes(listMatchVCardPrefixedField2), toPrimaryValues(listMatchVCardPrefixedField3), toTypes(listMatchVCardPrefixedField3), toPrimaryValue(listMatchSingleVCardPrefixedField6), toPrimaryValue(listMatchSingleVCardPrefixedField2), toPrimaryValues(listMatchVCardPrefixedField4), toTypes(listMatchVCardPrefixedField4), toPrimaryValue(listMatchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(listMatchSingleVCardPrefixedField5), toPrimaryValues(listMatchVCardPrefixedField5), (strArrSplit2 == null || strArrSplit2.length == 2) ? strArrSplit2 : null);
    }
}
