package com.google.zxing.client.result;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SMSMMSResultParser extends ResultParser {
    private static void addNumberVia(Collection<String> collection, Collection<String> collection2, String str) {
        int iIndexOf = str.indexOf(59);
        if (iIndexOf < 0) {
            collection.add(str);
            collection2.add(null);
        } else {
            collection.add(str.substring(0, iIndexOf));
            String strSubstring = str.substring(iIndexOf + 1);
            collection2.add(strSubstring.startsWith("via=") ? strSubstring.substring(4) : null);
        }
    }

    @Override // com.google.zxing.client.result.ResultParser
    public SMSParsedResult parse(Result result) {
        boolean z8;
        String str;
        String massagedText = ResultParser.getMassagedText(result);
        String str2 = null;
        if (!massagedText.startsWith("sms:") && !massagedText.startsWith("SMS:") && !massagedText.startsWith("mms:") && !massagedText.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> nameValuePairs = ResultParser.parseNameValuePairs(massagedText);
        if (nameValuePairs == null || nameValuePairs.isEmpty()) {
            z8 = false;
            str = null;
        } else {
            str2 = nameValuePairs.get("subject");
            str = nameValuePairs.get(TtmlNode.TAG_BODY);
            z8 = true;
        }
        int iIndexOf = massagedText.indexOf(63, 4);
        String strSubstring = (iIndexOf < 0 || !z8) ? massagedText.substring(4) : massagedText.substring(4, iIndexOf);
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        int i9 = -1;
        while (true) {
            int i10 = i9 + 1;
            int iIndexOf2 = strSubstring.indexOf(44, i10);
            if (iIndexOf2 <= i9) {
                addNumberVia(arrayList, arrayList2, strSubstring.substring(i10));
                return new SMSParsedResult((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
            addNumberVia(arrayList, arrayList2, strSubstring.substring(i10, iIndexOf2));
            i9 = iIndexOf2;
        }
    }
}
