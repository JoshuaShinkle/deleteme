package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class AddressBookAUResultParser extends ResultParser {
    private static String[] matchMultipleValuePrefix(String str, int i9, String str2, boolean z8) {
        ArrayList arrayList = null;
        for (int i10 = 1; i10 <= i9; i10++) {
            String strMatchSinglePrefixedField = ResultParser.matchSinglePrefixedField(str + i10 + ':', str2, CharUtils.f19105CR, z8);
            if (strMatchSinglePrefixedField == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(i9);
            }
            arrayList.add(strMatchSinglePrefixedField);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.contains("MEMORY") || !massagedText.contains(IOUtils.LINE_SEPARATOR_WINDOWS)) {
            return null;
        }
        String strMatchSinglePrefixedField = ResultParser.matchSinglePrefixedField("NAME1:", massagedText, CharUtils.f19105CR, true);
        String strMatchSinglePrefixedField2 = ResultParser.matchSinglePrefixedField("NAME2:", massagedText, CharUtils.f19105CR, true);
        String[] strArrMatchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, massagedText, true);
        String[] strArrMatchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, massagedText, true);
        String strMatchSinglePrefixedField3 = ResultParser.matchSinglePrefixedField("MEMORY:", massagedText, CharUtils.f19105CR, false);
        String strMatchSinglePrefixedField4 = ResultParser.matchSinglePrefixedField("ADD:", massagedText, CharUtils.f19105CR, true);
        return new AddressBookParsedResult(ResultParser.maybeWrap(strMatchSinglePrefixedField), null, strMatchSinglePrefixedField2, strArrMatchMultipleValuePrefix, null, strArrMatchMultipleValuePrefix2, null, null, strMatchSinglePrefixedField3, strMatchSinglePrefixedField4 != null ? new String[]{strMatchSinglePrefixedField4} : null, null, null, null, null, null, null);
    }
}
