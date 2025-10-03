package org.apache.commons.lang3.text.translate;

import java.io.Writer;

/* loaded from: classes.dex */
public class UnicodeUnpairedSurrogateRemover extends CodePointTranslator {
    @Override // org.apache.commons.lang3.text.translate.CodePointTranslator
    public boolean translate(int i9, Writer writer) {
        return i9 >= 55296 && i9 <= 57343;
    }
}
