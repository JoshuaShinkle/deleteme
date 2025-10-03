package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class CharEscaperBuilder {
    private int max = -1;
    private final Map<Character, String> map = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscape(char c9, String str) {
        this.map.put(Character.valueOf(c9), Preconditions.checkNotNull(str));
        if (c9 > this.max) {
            this.max = c9;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscapes(char[] cArr, String str) {
        Preconditions.checkNotNull(str);
        for (char c9 : cArr) {
            addEscape(c9, str);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] cArr = new char[this.max + 1][];
        for (Map.Entry<Character, String> entry : this.map.entrySet()) {
            cArr[entry.getKey().charValue()] = entry.getValue().toCharArray();
        }
        return cArr;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(toArray());
    }

    public static class CharArrayDecorator extends CharEscaper {
        private final int replaceLength;
        private final char[][] replacements;

        public CharArrayDecorator(char[][] cArr) {
            this.replacements = cArr;
            this.replaceLength = cArr.length;
        }

        @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
        public String escape(String str) {
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = str.charAt(i9);
                char[][] cArr = this.replacements;
                if (cCharAt < cArr.length && cArr[cCharAt] != null) {
                    return escapeSlow(str, i9);
                }
            }
            return str;
        }

        @Override // com.google.common.escape.CharEscaper
        public char[] escape(char c9) {
            if (c9 < this.replaceLength) {
                return this.replacements[c9];
            }
            return null;
        }
    }
}
