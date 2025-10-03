package org.apache.commons.lang3.text.translate;

import java.io.Writer;

/* loaded from: classes.dex */
public abstract class CodePointTranslator extends CharSequenceTranslator {
    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public final int translate(CharSequence charSequence, int i9, Writer writer) {
        return translate(Character.codePointAt(charSequence, i9), writer) ? 1 : 0;
    }

    public abstract boolean translate(int i9, Writer writer);
}
