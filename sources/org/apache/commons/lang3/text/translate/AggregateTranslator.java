package org.apache.commons.lang3.text.translate;

import java.io.Writer;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes.dex */
public class AggregateTranslator extends CharSequenceTranslator {
    private final CharSequenceTranslator[] translators;

    public AggregateTranslator(CharSequenceTranslator... charSequenceTranslatorArr) {
        this.translators = (CharSequenceTranslator[]) ArrayUtils.clone(charSequenceTranslatorArr);
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i9, Writer writer) {
        for (CharSequenceTranslator charSequenceTranslator : this.translators) {
            int iTranslate = charSequenceTranslator.translate(charSequence, i9, writer);
            if (iTranslate != 0) {
                return iTranslate;
            }
        }
        return 0;
    }
}
