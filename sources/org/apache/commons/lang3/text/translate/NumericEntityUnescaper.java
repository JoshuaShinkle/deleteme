package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

/* loaded from: classes.dex */
public class NumericEntityUnescaper extends CharSequenceTranslator {
    private final EnumSet<OPTION> options;

    public enum OPTION {
        semiColonRequired,
        semiColonOptional,
        errorIfNoSemiColon
    }

    public NumericEntityUnescaper(OPTION... optionArr) {
        if (optionArr.length > 0) {
            this.options = EnumSet.copyOf((Collection) Arrays.asList(optionArr));
        } else {
            this.options = EnumSet.copyOf((Collection) Arrays.asList(OPTION.semiColonRequired));
        }
    }

    public boolean isSet(OPTION option) {
        EnumSet<OPTION> enumSet = this.options;
        if (enumSet == null) {
            return false;
        }
        return enumSet.contains(option);
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i9, Writer writer) throws IOException {
        int i10;
        int length = charSequence.length();
        if (charSequence.charAt(i9) == '&' && i9 < length - 2 && charSequence.charAt(i9 + 1) == '#') {
            int i11 = i9 + 2;
            char cCharAt = charSequence.charAt(i11);
            if (cCharAt == 'x' || cCharAt == 'X') {
                i11++;
                if (i11 == length) {
                    return 0;
                }
                i10 = 1;
            } else {
                i10 = 0;
            }
            int i12 = i11;
            while (i12 < length && ((charSequence.charAt(i12) >= '0' && charSequence.charAt(i12) <= '9') || ((charSequence.charAt(i12) >= 'a' && charSequence.charAt(i12) <= 'f') || (charSequence.charAt(i12) >= 'A' && charSequence.charAt(i12) <= 'F')))) {
                i12++;
            }
            int i13 = (i12 == length || charSequence.charAt(i12) != ';') ? 0 : 1;
            if (i13 == 0) {
                if (isSet(OPTION.semiColonRequired)) {
                    return 0;
                }
                if (isSet(OPTION.errorIfNoSemiColon)) {
                    throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
                }
            }
            try {
                int i14 = i10 != 0 ? Integer.parseInt(charSequence.subSequence(i11, i12).toString(), 16) : Integer.parseInt(charSequence.subSequence(i11, i12).toString(), 10);
                if (i14 > 65535) {
                    char[] chars = Character.toChars(i14);
                    writer.write(chars[0]);
                    writer.write(chars[1]);
                } else {
                    writer.write(i14);
                }
                return ((i12 + 2) - i11) + i10 + i13;
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }
}
