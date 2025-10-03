package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class NumericEntityEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    private NumericEntityEscaper(int i9, int i10, boolean z8) {
        this.below = i9;
        this.above = i10;
        this.between = z8;
    }

    public static NumericEntityEscaper above(int i9) {
        return outsideOf(0, i9);
    }

    public static NumericEntityEscaper below(int i9) {
        return outsideOf(i9, Integer.MAX_VALUE);
    }

    public static NumericEntityEscaper between(int i9, int i10) {
        return new NumericEntityEscaper(i9, i10, true);
    }

    public static NumericEntityEscaper outsideOf(int i9, int i10) {
        return new NumericEntityEscaper(i9, i10, false);
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
        writer.write("&#");
        writer.write(Integer.toString(i9, 10));
        writer.write(59);
        return true;
    }

    public NumericEntityEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }
}
