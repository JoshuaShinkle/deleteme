package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMax;
    private final char safeMin;

    public ArrayBasedCharEscaper(Map<Character, String> map, char c9, char c10) {
        this(ArrayBasedEscaperMap.create(map), c9, c10);
    }

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMax || cCharAt < this.safeMin) {
                return escapeSlow(str, i9);
            }
        }
        return str;
    }

    public abstract char[] escapeUnsafe(char c9);

    public ArrayBasedCharEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, char c9, char c10) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] replacementArray = arrayBasedEscaperMap.getReplacementArray();
        this.replacements = replacementArray;
        this.replacementsLength = replacementArray.length;
        if (c10 < c9) {
            c10 = 0;
            c9 = 65535;
        }
        this.safeMin = c9;
        this.safeMax = c10;
    }

    @Override // com.google.common.escape.CharEscaper
    public final char[] escape(char c9) {
        char[] cArr;
        if (c9 < this.replacementsLength && (cArr = this.replacements[c9]) != null) {
            return cArr;
        }
        if (c9 < this.safeMin || c9 > this.safeMax) {
            return escapeUnsafe(c9);
        }
        return null;
    }
}
