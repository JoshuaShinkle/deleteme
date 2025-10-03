package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class OctalUnescaper extends CharSequenceTranslator {
    private boolean isOctalDigit(char c9) {
        return c9 >= '0' && c9 <= '7';
    }

    private boolean isZeroToThree(char c9) {
        return c9 >= '0' && c9 <= '3';
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i9, Writer writer) throws IOException {
        int length = (charSequence.length() - i9) - 1;
        StringBuilder sb = new StringBuilder();
        if (charSequence.charAt(i9) != '\\' || length <= 0) {
            return 0;
        }
        int i10 = i9 + 1;
        if (!isOctalDigit(charSequence.charAt(i10))) {
            return 0;
        }
        int i11 = i9 + 2;
        int i12 = i9 + 3;
        sb.append(charSequence.charAt(i10));
        if (length > 1 && isOctalDigit(charSequence.charAt(i11))) {
            sb.append(charSequence.charAt(i11));
            if (length > 2 && isZeroToThree(charSequence.charAt(i10)) && isOctalDigit(charSequence.charAt(i12))) {
                sb.append(charSequence.charAt(i12));
            }
        }
        writer.write(Integer.parseInt(sb.toString(), 8));
        return sb.length() + 1;
    }
}
