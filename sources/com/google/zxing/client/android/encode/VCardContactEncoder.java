package com.google.zxing.client.android.encode;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.zxing.client.android.Intents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class VCardContactEncoder extends ContactEncoder {
    private static final char TERMINATOR = '\n';

    public static List<Map<String, Set<String>>> buildPhoneMetadata(Collection<String> collection, List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < collection.size(); i9++) {
            if (list.size() <= i9) {
                arrayList.add(null);
            } else {
                HashMap map = new HashMap();
                arrayList.add(map);
                HashSet hashSet = new HashSet();
                map.put(Intents.WifiConnect.TYPE, hashSet);
                String str = list.get(i9);
                Integer numMaybeIntValue = maybeIntValue(str);
                if (numMaybeIntValue == null) {
                    hashSet.add(str);
                } else {
                    String strVCardPurposeLabelForAndroidType = vCardPurposeLabelForAndroidType(numMaybeIntValue.intValue());
                    String strVCardContextLabelForAndroidType = vCardContextLabelForAndroidType(numMaybeIntValue.intValue());
                    if (strVCardPurposeLabelForAndroidType != null) {
                        hashSet.add(strVCardPurposeLabelForAndroidType);
                    }
                    if (strVCardContextLabelForAndroidType != null) {
                        hashSet.add(strVCardContextLabelForAndroidType);
                    }
                }
            }
        }
        return arrayList;
    }

    private static Integer maybeIntValue(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static String vCardContextLabelForAndroidType(int i9) {
        if (i9 == 10 || i9 == 17 || i9 == 18) {
            return "work";
        }
        switch (i9) {
            case 1:
            case 2:
            case 5:
            case 6:
                return "home";
            case 3:
            case 4:
                return "work";
            default:
                return null;
        }
    }

    private static String vCardPurposeLabelForAndroidType(int i9) {
        if (i9 == 4 || i9 == 5) {
            return "fax";
        }
        if (i9 == 6) {
            return "pager";
        }
        if (i9 == 13) {
            return "fax";
        }
        if (i9 == 16) {
            return "textphone";
        }
        if (i9 == 18) {
            return "pager";
        }
        if (i9 != 20) {
            return null;
        }
        return MimeTypes.BASE_TYPE_TEXT;
    }

    @Override // com.google.zxing.client.android.encode.ContactEncoder
    public String[] encode(List<String> list, String str, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, String str2) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("BEGIN:VCARD");
        sb.append('\n');
        sb.append("VERSION:3.0");
        sb.append('\n');
        StringBuilder sb2 = new StringBuilder(100);
        VCardFieldFormatter vCardFieldFormatter = new VCardFieldFormatter();
        ContactEncoder.appendUpToUnique(sb, sb2, "N", list, 1, null, vCardFieldFormatter, '\n');
        ContactEncoder.append(sb, sb2, "ORG", str, vCardFieldFormatter, '\n');
        ContactEncoder.appendUpToUnique(sb, sb2, "ADR", list2, 1, null, vCardFieldFormatter, '\n');
        List<Map<String, Set<String>>> listBuildPhoneMetadata = buildPhoneMetadata(list3, list4);
        ContactEncoder.appendUpToUnique(sb, sb2, "TEL", list3, Integer.MAX_VALUE, new VCardTelDisplayFormatter(listBuildPhoneMetadata), new VCardFieldFormatter(listBuildPhoneMetadata), '\n');
        ContactEncoder.appendUpToUnique(sb, sb2, "EMAIL", list5, Integer.MAX_VALUE, null, vCardFieldFormatter, '\n');
        ContactEncoder.appendUpToUnique(sb, sb2, "URL", list6, Integer.MAX_VALUE, null, vCardFieldFormatter, '\n');
        ContactEncoder.append(sb, sb2, "NOTE", str2, vCardFieldFormatter, '\n');
        sb.append("END:VCARD");
        sb.append('\n');
        return new String[]{sb.toString(), sb2.toString()};
    }
}
