package org.apache.commons.lang3.text;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public abstract class StrMatcher {
    private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
    private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
    private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
    private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
    private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
    private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
    private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('\"');
    private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
    private static final StrMatcher NONE_MATCHER = new NoMatcher();

    public static final class CharMatcher extends StrMatcher {

        /* renamed from: ch */
        private final char f19111ch;

        public CharMatcher(char c9) {
            this.f19111ch = c9;
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int i9, int i10, int i11) {
            return this.f19111ch == cArr[i9] ? 1 : 0;
        }
    }

    public static final class CharSetMatcher extends StrMatcher {
        private final char[] chars;

        public CharSetMatcher(char[] cArr) {
            char[] cArr2 = (char[]) cArr.clone();
            this.chars = cArr2;
            Arrays.sort(cArr2);
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int i9, int i10, int i11) {
            return Arrays.binarySearch(this.chars, cArr[i9]) >= 0 ? 1 : 0;
        }
    }

    public static final class NoMatcher extends StrMatcher {
        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int i9, int i10, int i11) {
            return 0;
        }
    }

    public static final class StringMatcher extends StrMatcher {
        private final char[] chars;

        public StringMatcher(String str) {
            this.chars = str.toCharArray();
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int i9, int i10, int i11) {
            int length = this.chars.length;
            if (i9 + length > i11) {
                return 0;
            }
            int i12 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i12 >= cArr2.length) {
                    return length;
                }
                if (cArr2[i12] != cArr[i9]) {
                    return 0;
                }
                i12++;
                i9++;
            }
        }
    }

    public static final class TrimMatcher extends StrMatcher {
        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int i9, int i10, int i11) {
            return cArr[i9] <= ' ' ? 1 : 0;
        }
    }

    public static StrMatcher charMatcher(char c9) {
        return new CharMatcher(c9);
    }

    public static StrMatcher charSetMatcher(char... cArr) {
        return (cArr == null || cArr.length == 0) ? NONE_MATCHER : cArr.length == 1 ? new CharMatcher(cArr[0]) : new CharSetMatcher(cArr);
    }

    public static StrMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    public static StrMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    public static StrMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    public static StrMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    public static StrMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    public static StrMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    public static StrMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    public static StrMatcher stringMatcher(String str) {
        return StringUtils.isEmpty(str) ? NONE_MATCHER : new StringMatcher(str);
    }

    public static StrMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    public static StrMatcher trimMatcher() {
        return TRIM_MATCHER;
    }

    public int isMatch(char[] cArr, int i9) {
        return isMatch(cArr, i9, 0, cArr.length);
    }

    public abstract int isMatch(char[] cArr, int i9, int i10, int i11);

    public static StrMatcher charSetMatcher(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE_MATCHER;
        }
        if (str.length() == 1) {
            return new CharMatcher(str.charAt(0));
        }
        return new CharSetMatcher(str.toCharArray());
    }
}
