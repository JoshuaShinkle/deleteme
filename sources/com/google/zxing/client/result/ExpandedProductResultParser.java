package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public final class ExpandedProductResultParser extends ResultParser {
    private static String findAIvalue(int i9, String str) {
        char cCharAt;
        if (str.charAt(i9) != '(') {
            return null;
        }
        String strSubstring = str.substring(i9 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i10 = 0; i10 < strSubstring.length() && (cCharAt = strSubstring.charAt(i10)) != ')'; i10++) {
            if (cCharAt < '0' || cCharAt > '9') {
                return null;
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static String findValue(int i9, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(i9);
        for (int i10 = 0; i10 < strSubstring.length(); i10++) {
            char cCharAt = strSubstring.charAt(i10);
            if (cCharAt != '(') {
                sb.append(cCharAt);
            } else {
                if (findAIvalue(i10, strSubstring) != null) {
                    break;
                }
                sb.append('(');
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    @Override // com.google.zxing.client.result.ResultParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ExpandedProductParsedResult parse(Result result) {
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String str10 = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        int i9 = 0;
        while (i9 < massagedText.length()) {
            String strFindAIvalue = findAIvalue(i9, massagedText);
            if (strFindAIvalue == null) {
                return expandedProductParsedResult;
            }
            char c9 = 2;
            int length = i9 + strFindAIvalue.length() + 2;
            String strFindValue = findValue(length, massagedText);
            int length2 = length + strFindValue.length();
            switch (strFindAIvalue.hashCode()) {
                case 1536:
                    if (!strFindAIvalue.equals("00")) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 0;
                        break;
                    }
                case 1537:
                    if (strFindAIvalue.equals("01")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 1567:
                    if (!strFindAIvalue.equals("10")) {
                    }
                    break;
                case 1568:
                    if (strFindAIvalue.equals("11")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case 1570:
                    if (strFindAIvalue.equals("13")) {
                        c9 = 4;
                        break;
                    }
                    break;
                case 1572:
                    if (strFindAIvalue.equals("15")) {
                        c9 = 5;
                        break;
                    }
                    break;
                case 1574:
                    if (strFindAIvalue.equals("17")) {
                        c9 = 6;
                        break;
                    }
                    break;
                case 1567966:
                    if (strFindAIvalue.equals("3100")) {
                        c9 = 7;
                        break;
                    }
                    break;
                case 1567967:
                    if (strFindAIvalue.equals("3101")) {
                        c9 = '\b';
                        break;
                    }
                    break;
                case 1567968:
                    if (strFindAIvalue.equals("3102")) {
                        c9 = '\t';
                        break;
                    }
                    break;
                case 1567969:
                    if (strFindAIvalue.equals("3103")) {
                        c9 = '\n';
                        break;
                    }
                    break;
                case 1567970:
                    if (strFindAIvalue.equals("3104")) {
                        c9 = 11;
                        break;
                    }
                    break;
                case 1567971:
                    if (strFindAIvalue.equals("3105")) {
                        c9 = '\f';
                        break;
                    }
                    break;
                case 1567972:
                    if (strFindAIvalue.equals("3106")) {
                        c9 = CharUtils.f19105CR;
                        break;
                    }
                    break;
                case 1567973:
                    if (strFindAIvalue.equals("3107")) {
                        c9 = 14;
                        break;
                    }
                    break;
                case 1567974:
                    if (strFindAIvalue.equals("3108")) {
                        c9 = 15;
                        break;
                    }
                    break;
                case 1567975:
                    if (strFindAIvalue.equals("3109")) {
                        c9 = 16;
                        break;
                    }
                    break;
                case 1568927:
                    if (strFindAIvalue.equals("3200")) {
                        c9 = 17;
                        break;
                    }
                    break;
                case 1568928:
                    if (strFindAIvalue.equals("3201")) {
                        c9 = 18;
                        break;
                    }
                    break;
                case 1568929:
                    if (strFindAIvalue.equals("3202")) {
                        c9 = 19;
                        break;
                    }
                    break;
                case 1568930:
                    if (strFindAIvalue.equals("3203")) {
                        c9 = 20;
                        break;
                    }
                    break;
                case 1568931:
                    if (strFindAIvalue.equals("3204")) {
                        c9 = 21;
                        break;
                    }
                    break;
                case 1568932:
                    if (strFindAIvalue.equals("3205")) {
                        c9 = 22;
                        break;
                    }
                    break;
                case 1568933:
                    if (strFindAIvalue.equals("3206")) {
                        c9 = 23;
                        break;
                    }
                    break;
                case 1568934:
                    if (strFindAIvalue.equals("3207")) {
                        c9 = 24;
                        break;
                    }
                    break;
                case 1568935:
                    if (strFindAIvalue.equals("3208")) {
                        c9 = 25;
                        break;
                    }
                    break;
                case 1568936:
                    if (strFindAIvalue.equals("3209")) {
                        c9 = 26;
                        break;
                    }
                    break;
                case 1575716:
                    if (strFindAIvalue.equals("3920")) {
                        c9 = 27;
                        break;
                    }
                    break;
                case 1575717:
                    if (strFindAIvalue.equals("3921")) {
                        c9 = 28;
                        break;
                    }
                    break;
                case 1575718:
                    if (strFindAIvalue.equals("3922")) {
                        c9 = 29;
                        break;
                    }
                    break;
                case 1575719:
                    if (strFindAIvalue.equals("3923")) {
                        c9 = 30;
                        break;
                    }
                    break;
                case 1575747:
                    if (strFindAIvalue.equals("3930")) {
                        c9 = 31;
                        break;
                    }
                    break;
                case 1575748:
                    if (strFindAIvalue.equals("3931")) {
                        c9 = ' ';
                        break;
                    }
                    break;
                case 1575749:
                    if (strFindAIvalue.equals("3932")) {
                        c9 = '!';
                        break;
                    }
                    break;
                case 1575750:
                    if (strFindAIvalue.equals("3933")) {
                        c9 = '\"';
                        break;
                    }
                    break;
            }
            switch (c9) {
                case 0:
                    str2 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 1:
                    str = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 2:
                    str3 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 3:
                    str4 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 4:
                    str5 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 5:
                    str6 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 6:
                    str7 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    strSubstring2 = strFindAIvalue.substring(3);
                    str10 = strFindValue;
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                case 31:
                case ' ':
                case '!':
                case '\"':
                    if (strFindValue.length() < 4) {
                        return null;
                    }
                    String strSubstring4 = strFindValue.substring(3);
                    strSubstring3 = strFindValue.substring(0, 3);
                    str10 = strSubstring4;
                    strSubstring2 = strFindAIvalue.substring(3);
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
                default:
                    map.put(strFindAIvalue, strFindValue);
                    continue;
                    i9 = length2;
                    expandedProductParsedResult = null;
            }
            str8 = strFindValue;
            i9 = length2;
            expandedProductParsedResult = null;
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, str10, strSubstring2, strSubstring3, map);
    }
}
