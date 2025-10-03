package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LookupTranslator extends CharSequenceTranslator {
    private final int longest;
    private final HashMap<String, CharSequence> lookupMap = new HashMap<>();
    private final int shortest;

    public LookupTranslator(CharSequence[]... charSequenceArr) {
        int i9 = Integer.MAX_VALUE;
        int i10 = 0;
        if (charSequenceArr != null) {
            int i11 = 0;
            for (CharSequence[] charSequenceArr2 : charSequenceArr) {
                this.lookupMap.put(charSequenceArr2[0].toString(), charSequenceArr2[1]);
                int length = charSequenceArr2[0].length();
                i9 = length < i9 ? length : i9;
                if (length > i11) {
                    i11 = length;
                }
            }
            i10 = i11;
        }
        this.shortest = i9;
        this.longest = i10;
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i9, Writer writer) throws IOException {
        int length = this.longest;
        if (i9 + length > charSequence.length()) {
            length = charSequence.length() - i9;
        }
        while (length >= this.shortest) {
            CharSequence charSequence2 = this.lookupMap.get(charSequence.subSequence(i9, i9 + length).toString());
            if (charSequence2 != null) {
                writer.write(charSequence2.toString());
                return length;
            }
            length--;
        }
        return 0;
    }
}
