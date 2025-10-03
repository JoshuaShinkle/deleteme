package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class CharSequenceTranslator {
    public static String hex(int i9) {
        return Integer.toHexString(i9).toUpperCase(Locale.ENGLISH);
    }

    public abstract int translate(CharSequence charSequence, int i9, Writer writer);

    public final String translate(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(charSequence.length() * 2);
            translate(charSequence, stringWriter);
            return stringWriter.toString();
        } catch (IOException e9) {
            throw new RuntimeException(e9);
        }
    }

    public final CharSequenceTranslator with(CharSequenceTranslator... charSequenceTranslatorArr) {
        CharSequenceTranslator[] charSequenceTranslatorArr2 = new CharSequenceTranslator[charSequenceTranslatorArr.length + 1];
        charSequenceTranslatorArr2[0] = this;
        System.arraycopy(charSequenceTranslatorArr, 0, charSequenceTranslatorArr2, 1, charSequenceTranslatorArr.length);
        return new AggregateTranslator(charSequenceTranslatorArr2);
    }

    public final void translate(CharSequence charSequence, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (charSequence == null) {
            return;
        }
        int length = charSequence.length();
        int length2 = 0;
        while (length2 < length) {
            int iTranslate = translate(charSequence, length2, writer);
            if (iTranslate == 0) {
                char[] chars = Character.toChars(Character.codePointAt(charSequence, length2));
                writer.write(chars);
                length2 += chars.length;
            } else {
                for (int i9 = 0; i9 < iTranslate; i9++) {
                    length2 += Character.charCount(Character.codePointAt(charSequence, length2));
                }
            }
        }
    }
}
