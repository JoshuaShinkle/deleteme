package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
final class DecodeHintManager {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String TAG = "DecodeHintManager";

    private DecodeHintManager() {
    }

    public static Map<DecodeHintType, ?> parseDecodeHints(Uri uri) {
        String strSubstring;
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.isEmpty()) {
            return null;
        }
        Map<String, String> mapSplitQuery = splitQuery(encodedQuery);
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (decodeHintType != DecodeHintType.CHARACTER_SET && decodeHintType != DecodeHintType.NEED_RESULT_POINT_CALLBACK && decodeHintType != DecodeHintType.POSSIBLE_FORMATS && (strSubstring = mapSplitQuery.get(decodeHintType.name())) != null) {
                if (decodeHintType.getValueType().equals(Object.class)) {
                    enumMap.put((EnumMap) decodeHintType, (DecodeHintType) strSubstring);
                } else if (decodeHintType.getValueType().equals(Void.class)) {
                    enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.TRUE);
                } else if (decodeHintType.getValueType().equals(String.class)) {
                    enumMap.put((EnumMap) decodeHintType, (DecodeHintType) strSubstring);
                } else if (decodeHintType.getValueType().equals(Boolean.class)) {
                    if (strSubstring.isEmpty()) {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.TRUE);
                    } else if ("0".equals(strSubstring) || "false".equalsIgnoreCase(strSubstring) || "no".equalsIgnoreCase(strSubstring)) {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.FALSE);
                    } else {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.TRUE);
                    }
                } else if (decodeHintType.getValueType().equals(int[].class)) {
                    if (!strSubstring.isEmpty() && strSubstring.charAt(strSubstring.length() - 1) == ',') {
                        strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                    }
                    String[] strArrSplit = COMMA.split(strSubstring);
                    int[] iArr = new int[strArrSplit.length];
                    for (int i9 = 0; i9 < strArrSplit.length; i9++) {
                        try {
                            iArr[i9] = Integer.parseInt(strArrSplit[i9]);
                        } catch (NumberFormatException unused) {
                            Log.w(TAG, "Skipping array of integers hint " + decodeHintType + " due to invalid numeric value: '" + strArrSplit[i9] + '\'');
                            iArr = null;
                        }
                    }
                    if (iArr != null) {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) iArr);
                    }
                } else {
                    Log.w(TAG, "Unsupported hint type '" + decodeHintType + "' of type " + decodeHintType.getValueType());
                }
            }
        }
        Log.i(TAG, "Hints from the URI: " + enumMap);
        return enumMap;
    }

    private static Map<String, String> splitQuery(String str) {
        String strDecode;
        HashMap map = new HashMap();
        int i9 = 0;
        while (true) {
            if (i9 >= str.length()) {
                break;
            }
            if (str.charAt(i9) == '&') {
                i9++;
            } else {
                int iIndexOf = str.indexOf(38, i9);
                int iIndexOf2 = str.indexOf(61, i9);
                String strDecode2 = "";
                if (iIndexOf < 0) {
                    if (iIndexOf2 < 0) {
                        strDecode = Uri.decode(str.substring(i9).replace('+', ' '));
                    } else {
                        String strDecode3 = Uri.decode(str.substring(i9, iIndexOf2).replace('+', ' '));
                        strDecode2 = Uri.decode(str.substring(iIndexOf2 + 1).replace('+', ' '));
                        strDecode = strDecode3;
                    }
                    if (!map.containsKey(strDecode)) {
                        map.put(strDecode, strDecode2);
                    }
                } else {
                    if (iIndexOf2 < 0 || iIndexOf2 > iIndexOf) {
                        String strDecode4 = Uri.decode(str.substring(i9, iIndexOf).replace('+', ' '));
                        if (!map.containsKey(strDecode4)) {
                            map.put(strDecode4, "");
                        }
                    } else {
                        String strDecode5 = Uri.decode(str.substring(i9, iIndexOf2).replace('+', ' '));
                        String strDecode6 = Uri.decode(str.substring(iIndexOf2 + 1, iIndexOf).replace('+', ' '));
                        if (!map.containsKey(strDecode5)) {
                            map.put(strDecode5, strDecode6);
                        }
                    }
                    i9 = iIndexOf + 1;
                }
            }
        }
        return map;
    }

    public static Map<DecodeHintType, Object> parseDecodeHints(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (decodeHintType != DecodeHintType.CHARACTER_SET && decodeHintType != DecodeHintType.NEED_RESULT_POINT_CALLBACK && decodeHintType != DecodeHintType.POSSIBLE_FORMATS) {
                String strName = decodeHintType.name();
                if (extras.containsKey(strName)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put((EnumMap) decodeHintType, (DecodeHintType) Boolean.TRUE);
                    } else {
                        Object obj = extras.get(strName);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put((EnumMap) decodeHintType, (DecodeHintType) obj);
                        } else {
                            Log.w(TAG, "Ignoring hint " + decodeHintType + " because it is not assignable from " + obj);
                        }
                    }
                }
            }
        }
        Log.i(TAG, "Hints from the Intent: " + enumMap);
        return enumMap;
    }
}
