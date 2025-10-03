package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ArrayBasedUnicodeEscaper extends UnicodeEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final int safeMax;
    private final char safeMaxChar;
    private final int safeMin;
    private final char safeMinChar;

    public ArrayBasedUnicodeEscaper(Map<Character, String> map, int i9, int i10, String str) {
        this(ArrayBasedEscaperMap.create(map), i9, i10, str);
    }

    @Override // com.google.common.escape.UnicodeEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMaxChar || cCharAt < this.safeMinChar) {
                return escapeSlow(str, i9);
            }
        }
        return str;
    }

    public abstract char[] escapeUnsafe(int i9);

    @Override // com.google.common.escape.UnicodeEscaper
    public final int nextEscapeIndex(CharSequence charSequence, int i9, int i10) {
        while (i9 < i10) {
            char cCharAt = charSequence.charAt(i9);
            if ((cCharAt < this.replacementsLength && this.replacements[cCharAt] != null) || cCharAt > this.safeMaxChar || cCharAt < this.safeMinChar) {
                break;
            }
            i9++;
        }
        return i9;
    }

    public ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, int i9, int i10, String str) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] replacementArray = arrayBasedEscaperMap.getReplacementArray();
        this.replacements = replacementArray;
        this.replacementsLength = replacementArray.length;
        if (i10 < i9) {
            i10 = -1;
            i9 = Integer.MAX_VALUE;
        }
        this.safeMin = i9;
        this.safeMax = i10;
        if (i9 >= 55296) {
            this.safeMinChar = (char) 65535;
            this.safeMaxChar = (char) 0;
        } else {
            this.safeMinChar = (char) i9;
            this.safeMaxChar = (char) Math.min(i10, 55295);
        }
    }

    @Override // com.google.common.escape.UnicodeEscaper
    public final char[] escape(int i9) {
        char[] cArr;
        if (i9 < this.replacementsLength && (cArr = this.replacements[i9]) != null) {
            return cArr;
        }
        if (i9 < this.safeMin || i9 > this.safeMax) {
            return escapeUnsafe(i9);
        }
        return null;
    }
}
