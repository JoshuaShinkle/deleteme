package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    public static int codePointAt(CharSequence charSequence, int i9, int i10) {
        Preconditions.checkNotNull(charSequence);
        if (i9 >= i10) {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
        int i11 = i9 + 1;
        char cCharAt = charSequence.charAt(i9);
        if (cCharAt < 55296 || cCharAt > 57343) {
            return cCharAt;
        }
        if (cCharAt > 56319) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected low surrogate character '");
            sb.append(cCharAt);
            sb.append("' with value ");
            sb.append((int) cCharAt);
            sb.append(" at index ");
            sb.append(i11 - 1);
            sb.append(" in '");
            sb.append((Object) charSequence);
            sb.append("'");
            throw new IllegalArgumentException(sb.toString());
        }
        if (i11 == i10) {
            return -cCharAt;
        }
        char cCharAt2 = charSequence.charAt(i11);
        if (Character.isLowSurrogate(cCharAt2)) {
            return Character.toCodePoint(cCharAt, cCharAt2);
        }
        throw new IllegalArgumentException("Expected low surrogate but got char '" + cCharAt2 + "' with value " + ((int) cCharAt2) + " at index " + i11 + " in '" + ((Object) charSequence) + "'");
    }

    private static char[] growBuffer(char[] cArr, int i9, int i10) {
        if (i10 < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr2 = new char[i10];
        if (i9 > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i9);
        }
        return cArr2;
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int iNextEscapeIndex = nextEscapeIndex(str, 0, length);
        return iNextEscapeIndex == length ? str : escapeSlow(str, iNextEscapeIndex);
    }

    public abstract char[] escape(int i9);

    public final String escapeSlow(String str, int i9) {
        int length = str.length();
        char[] cArrCharBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i10 = 0;
        int length2 = 0;
        while (i9 < length) {
            int iCodePointAt = codePointAt(str, i9, length);
            if (iCodePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] cArrEscape = escape(iCodePointAt);
            int i11 = (Character.isSupplementaryCodePoint(iCodePointAt) ? 2 : 1) + i9;
            if (cArrEscape != null) {
                int i12 = i9 - i10;
                int i13 = length2 + i12;
                int length3 = cArrEscape.length + i13;
                if (cArrCharBufferFromThreadLocal.length < length3) {
                    cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, length2, length3 + (length - i9) + 32);
                }
                if (i12 > 0) {
                    str.getChars(i10, i9, cArrCharBufferFromThreadLocal, length2);
                    length2 = i13;
                }
                if (cArrEscape.length > 0) {
                    System.arraycopy(cArrEscape, 0, cArrCharBufferFromThreadLocal, length2, cArrEscape.length);
                    length2 += cArrEscape.length;
                }
                i10 = i11;
            }
            i9 = nextEscapeIndex(str, i11, length);
        }
        int i14 = length - i10;
        if (i14 > 0) {
            int i15 = i14 + length2;
            if (cArrCharBufferFromThreadLocal.length < i15) {
                cArrCharBufferFromThreadLocal = growBuffer(cArrCharBufferFromThreadLocal, length2, i15);
            }
            str.getChars(i10, length, cArrCharBufferFromThreadLocal, length2);
            length2 = i15;
        }
        return new String(cArrCharBufferFromThreadLocal, 0, length2);
    }

    public int nextEscapeIndex(CharSequence charSequence, int i9, int i10) {
        while (i9 < i10) {
            int iCodePointAt = codePointAt(charSequence, i9, i10);
            if (iCodePointAt < 0 || escape(iCodePointAt) != null) {
                break;
            }
            i9 += Character.isSupplementaryCodePoint(iCodePointAt) ? 2 : 1;
        }
        return i9;
    }
}
