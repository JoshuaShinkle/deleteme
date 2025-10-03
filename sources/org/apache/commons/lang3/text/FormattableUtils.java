package org.apache.commons.lang3.text;

import java.util.Formattable;
import java.util.Formatter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes.dex */
public class FormattableUtils {
    private static final String SIMPLEST_FORMAT = "%s";

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i9, int i10, int i11) {
        return append(charSequence, formatter, i9, i10, i11, ' ', null);
    }

    public static String toString(Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i9, int i10, int i11, char c9) {
        return append(charSequence, formatter, i9, i10, i11, c9, null);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i9, int i10, int i11, CharSequence charSequence2) {
        return append(charSequence, formatter, i9, i10, i11, ' ', charSequence2);
    }

    public static Formatter append(CharSequence charSequence, Formatter formatter, int i9, int i10, int i11, char c9, CharSequence charSequence2) {
        Validate.isTrue(charSequence2 == null || i11 < 0 || charSequence2.length() <= i11, "Specified ellipsis '%1$s' exceeds precision of %2$s", charSequence2, Integer.valueOf(i11));
        StringBuilder sb = new StringBuilder(charSequence);
        if (i11 >= 0 && i11 < charSequence.length()) {
            CharSequence charSequence3 = (CharSequence) ObjectUtils.defaultIfNull(charSequence2, "");
            sb.replace(i11 - charSequence3.length(), charSequence.length(), charSequence3.toString());
        }
        boolean z8 = (i9 & 1) == 1;
        for (int length = sb.length(); length < i10; length++) {
            sb.insert(z8 ? length : 0, c9);
        }
        formatter.format(sb.toString(), new Object[0]);
        return formatter;
    }
}
