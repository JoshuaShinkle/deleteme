package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class UnicodeUnescaper extends CharSequenceTranslator {
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i9, Writer writer) throws IOException {
        int i10;
        int i11;
        if (charSequence.charAt(i9) != '\\' || (i10 = i9 + 1) >= charSequence.length() || charSequence.charAt(i10) != 'u') {
            return 0;
        }
        int i12 = 2;
        while (true) {
            i11 = i9 + i12;
            if (i11 >= charSequence.length() || charSequence.charAt(i11) != 'u') {
                break;
            }
            i12++;
        }
        if (i11 < charSequence.length() && charSequence.charAt(i11) == '+') {
            i12++;
        }
        int i13 = i9 + i12;
        int i14 = i13 + 4;
        if (i14 > charSequence.length()) {
            throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + ((Object) charSequence.subSequence(i9, charSequence.length())) + "' due to end of CharSequence");
        }
        CharSequence charSequenceSubSequence = charSequence.subSequence(i13, i14);
        try {
            writer.write((char) Integer.parseInt(charSequenceSubSequence.toString(), 16));
            return i12 + 4;
        } catch (NumberFormatException e9) {
            throw new IllegalArgumentException("Unable to parse unicode value: " + ((Object) charSequenceSubSequence), e9);
        }
    }
}
