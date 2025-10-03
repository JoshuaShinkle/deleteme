package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class UnicodeEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    public UnicodeEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    public static UnicodeEscaper above(int i9) {
        return outsideOf(0, i9);
    }

    public static UnicodeEscaper below(int i9) {
        return outsideOf(i9, Integer.MAX_VALUE);
    }

    public static UnicodeEscaper between(int i9, int i10) {
        return new UnicodeEscaper(i9, i10, true);
    }

    public static UnicodeEscaper outsideOf(int i9, int i10) {
        return new UnicodeEscaper(i9, i10, false);
    }

    public String toUtf16Escape(int i9) {
        return "\\u" + CharSequenceTranslator.hex(i9);
    }

    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int i9, Writer writer) throws IOException {
        if (this.between) {
            if (i9 < this.below || i9 > this.above) {
                return false;
            }
        } else if (i9 >= this.below && i9 <= this.above) {
            return false;
        }
        if (i9 > 65535) {
            writer.write(toUtf16Escape(i9));
            return true;
        }
        if (i9 > 4095) {
            writer.write("\\u" + CharSequenceTranslator.hex(i9));
            return true;
        }
        if (i9 > 255) {
            writer.write("\\u0" + CharSequenceTranslator.hex(i9));
            return true;
        }
        if (i9 > 15) {
            writer.write("\\u00" + CharSequenceTranslator.hex(i9));
            return true;
        }
        writer.write("\\u000" + CharSequenceTranslator.hex(i9));
        return true;
    }

    public UnicodeEscaper(int i9, int i10, boolean z8) {
        this.below = i9;
        this.above = i10;
        this.between = z8;
    }
}
