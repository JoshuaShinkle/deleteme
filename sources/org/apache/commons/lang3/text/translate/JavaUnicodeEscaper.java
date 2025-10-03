package org.apache.commons.lang3.text.translate;

/* loaded from: classes.dex */
public class JavaUnicodeEscaper extends UnicodeEscaper {
    public JavaUnicodeEscaper(int i9, int i10, boolean z8) {
        super(i9, i10, z8);
    }

    public static JavaUnicodeEscaper above(int i9) {
        return outsideOf(0, i9);
    }

    public static JavaUnicodeEscaper below(int i9) {
        return outsideOf(i9, Integer.MAX_VALUE);
    }

    public static JavaUnicodeEscaper between(int i9, int i10) {
        return new JavaUnicodeEscaper(i9, i10, true);
    }

    public static JavaUnicodeEscaper outsideOf(int i9, int i10) {
        return new JavaUnicodeEscaper(i9, i10, false);
    }

    @Override // org.apache.commons.lang3.text.translate.UnicodeEscaper
    public String toUtf16Escape(int i9) {
        char[] chars = Character.toChars(i9);
        return "\\u" + CharSequenceTranslator.hex(chars[0]) + "\\u" + CharSequenceTranslator.hex(chars[1]);
    }
}
