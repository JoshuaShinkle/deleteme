package com.google.zxing.client.android.encode;

import android.telephony.PhoneNumberUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class VCardTelDisplayFormatter implements Formatter {
    private final List<Map<String, Set<String>>> metadataForIndex;

    public VCardTelDisplayFormatter() {
        this(null);
    }

    private static CharSequence formatMetadata(CharSequence charSequence, Map<String, Set<String>> map) {
        if (map == null || map.isEmpty()) {
            return charSequence;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Set<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Set<String> value = it.next().getValue();
            if (value != null && !value.isEmpty()) {
                Iterator<String> it2 = value.iterator();
                sb.append(it2.next());
                while (it2.hasNext()) {
                    sb.append(',');
                    sb.append(it2.next());
                }
            }
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(charSequence);
        return sb;
    }

    @Override // com.google.zxing.client.android.encode.Formatter
    public CharSequence format(CharSequence charSequence, int i9) {
        String number = PhoneNumberUtils.formatNumber(charSequence.toString());
        List<Map<String, Set<String>>> list = this.metadataForIndex;
        return formatMetadata(number, (list == null || list.size() <= i9) ? null : this.metadataForIndex.get(i9));
    }

    public VCardTelDisplayFormatter(List<Map<String, Set<String>>> list) {
        this.metadataForIndex = list;
    }
}
