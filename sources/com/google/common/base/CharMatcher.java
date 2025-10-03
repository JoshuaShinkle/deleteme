package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.p159io.IOUtils;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class CharMatcher implements Predicate<Character> {
    private static final int DISTINCT_CHARS = 65536;

    @Deprecated
    public static final CharMatcher WHITESPACE = whitespace();

    @Deprecated
    public static final CharMatcher BREAKING_WHITESPACE = breakingWhitespace();

    @Deprecated
    public static final CharMatcher ASCII = ascii();

    @Deprecated
    public static final CharMatcher DIGIT = digit();

    @Deprecated
    public static final CharMatcher JAVA_DIGIT = javaDigit();

    @Deprecated
    public static final CharMatcher JAVA_LETTER = javaLetter();

    @Deprecated
    public static final CharMatcher JAVA_LETTER_OR_DIGIT = javaLetterOrDigit();

    @Deprecated
    public static final CharMatcher JAVA_UPPER_CASE = javaUpperCase();

    @Deprecated
    public static final CharMatcher JAVA_LOWER_CASE = javaLowerCase();

    @Deprecated
    public static final CharMatcher JAVA_ISO_CONTROL = javaIsoControl();

    @Deprecated
    public static final CharMatcher INVISIBLE = invisible();

    @Deprecated
    public static final CharMatcher SINGLE_WIDTH = singleWidth();

    @Deprecated
    public static final CharMatcher ANY = any();

    @Deprecated
    public static final CharMatcher NONE = none();

    public static final class And extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        public And(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return this.first.matches(c9) && this.second.matches(c9);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.first.setBits(bitSet2);
            BitSet bitSet3 = new BitSet();
            this.second.setBits(bitSet3);
            bitSet2.and(bitSet3);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.and(" + this.first + ", " + this.second + ")";
        }
    }

    public static final class Any extends NamedFastMatcher {
        static final Any INSTANCE = new Any();

        private Any() {
            super("CharMatcher.any()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c9) {
            return charSequence.length() == 0 ? "" : String.valueOf(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            return charSequence.length() == 0 ? -1 : 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            return charSequence.length() - 1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.none();
        }

        @Override // com.google.common.base.CharMatcher
        /* renamed from: or */
        public CharMatcher mo17548or(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c9) {
            char[] cArr = new char[charSequence.length()];
            Arrays.fill(cArr, c9);
            return new String(cArr);
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return "";
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i9) {
            int length = charSequence.length();
            Preconditions.checkPositionIndex(i9, length);
            if (i9 == length) {
                return -1;
            }
            return i9;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            StringBuilder sb = new StringBuilder(charSequence.length() * charSequence2.length());
            for (int i9 = 0; i9 < charSequence.length(); i9++) {
                sb.append(charSequence2);
            }
            return sb.toString();
        }
    }

    public static final class AnyOf extends CharMatcher {
        private final char[] chars;

        public AnyOf(CharSequence charSequence) {
            char[] charArray = charSequence.toString().toCharArray();
            this.chars = charArray;
            Arrays.sort(charArray);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Arrays.binarySearch(this.chars, c9) >= 0;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            for (char c9 : this.chars) {
                bitSet.set(c9);
            }
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c9 : this.chars) {
                sb.append(CharMatcher.showCharacter(c9));
            }
            sb.append("\")");
            return sb.toString();
        }
    }

    public static final class Ascii extends NamedFastMatcher {
        static final Ascii INSTANCE = new Ascii();

        public Ascii() {
            super("CharMatcher.ascii()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return c9 <= 127;
        }
    }

    @GwtIncompatible
    public static final class BitSetMatcher extends NamedFastMatcher {
        private final BitSet table;

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return this.table.get(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }

        private BitSetMatcher(BitSet bitSet, String str) {
            super(str);
            this.table = bitSet.length() + 64 < bitSet.size() ? (BitSet) bitSet.clone() : bitSet;
        }
    }

    public static final class BreakingWhitespace extends CharMatcher {
        static final CharMatcher INSTANCE = new BreakingWhitespace();

        private BreakingWhitespace() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            if (c9 != ' ' && c9 != 133 && c9 != 5760) {
                if (c9 == 8199) {
                    return false;
                }
                if (c9 != 8287 && c9 != 12288 && c9 != 8232 && c9 != 8233) {
                    switch (c9) {
                        case '\t':
                        case '\n':
                        case 11:
                        case '\f':
                        case '\r':
                            break;
                        default:
                            if (c9 < 8192 || c9 > 8202) {
                                break;
                            }
                            break;
                    }
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    public static final class Digit extends RangesMatcher {
        static final Digit INSTANCE = new Digit();
        private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";

        private Digit() {
            super("CharMatcher.digit()", zeroes(), nines());
        }

        private static char[] nines() {
            char[] cArr = new char[31];
            for (int i9 = 0; i9 < 31; i9++) {
                cArr[i9] = (char) (ZEROES.charAt(i9) + '\t');
            }
            return cArr;
        }

        private static char[] zeroes() {
            return ZEROES.toCharArray();
        }
    }

    public static abstract class FastMatcher extends CharMatcher {
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    public static final class ForPredicate extends CharMatcher {
        private final Predicate<? super Character> predicate;

        public ForPredicate(Predicate<? super Character> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return this.predicate.apply(Character.valueOf(c9));
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.forPredicate(" + this.predicate + ")";
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        public boolean apply(Character ch) {
            return this.predicate.apply(Preconditions.checkNotNull(ch));
        }
    }

    public static final class InRange extends FastMatcher {
        private final char endInclusive;
        private final char startInclusive;

        public InRange(char c9, char c10) {
            Preconditions.checkArgument(c10 >= c9);
            this.startInclusive = c9;
            this.endInclusive = c10;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return this.startInclusive <= c9 && c9 <= this.endInclusive;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.startInclusive, this.endInclusive + 1);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.inRange('" + CharMatcher.showCharacter(this.startInclusive) + "', '" + CharMatcher.showCharacter(this.endInclusive) + "')";
        }
    }

    public static final class Invisible extends RangesMatcher {
        static final Invisible INSTANCE = new Invisible();
        private static final String RANGE_ENDS = "  \u00ad\u0604\u061c\u06dd\u070f\u1680\u180e\u200f \u2064\u2066\u2067\u2068\u2069\u206f\u3000\uf8ff\ufeff\ufff9\ufffb";
        private static final String RANGE_STARTS = "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u1680\u180e\u2000\u2028\u205f\u2066\u2067\u2068\u2069\u206a\u3000\ud800\ufeff\ufff9\ufffa";

        private Invisible() {
            super("CharMatcher.invisible()", RANGE_STARTS.toCharArray(), RANGE_ENDS.toCharArray());
        }
    }

    /* renamed from: com.google.common.base.CharMatcher$Is */
    public static final class C3562Is extends FastMatcher {
        private final char match;

        public C3562Is(char c9) {
            this.match = c9;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? this : CharMatcher.none();
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return c9 == this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.isNot(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        /* renamed from: or */
        public CharMatcher mo17548or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? charMatcher : super.mo17548or(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c9) {
            return charSequence.toString().replace(this.match, c9);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.is('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    public static final class IsEither extends FastMatcher {
        private final char match1;
        private final char match2;

        public IsEither(char c9, char c10) {
            this.match1 = c9;
            this.match2 = c10;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return c9 == this.match1 || c9 == this.match2;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(this.match1);
            bitSet.set(this.match2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.anyOf(\"" + CharMatcher.showCharacter(this.match1) + CharMatcher.showCharacter(this.match2) + "\")";
        }
    }

    public static final class IsNot extends FastMatcher {
        private final char match;

        public IsNot(char c9) {
            this.match = c9;
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? super.and(charMatcher) : charMatcher;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return c9 != this.match;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.m17547is(this.match);
        }

        @Override // com.google.common.base.CharMatcher
        /* renamed from: or */
        public CharMatcher mo17548or(CharMatcher charMatcher) {
            return charMatcher.matches(this.match) ? CharMatcher.any() : this;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            bitSet.set(0, this.match);
            bitSet.set(this.match + 1, 65536);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.isNot('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    public static final class JavaDigit extends CharMatcher {
        static final JavaDigit INSTANCE = new JavaDigit();

        private JavaDigit() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Character.isDigit(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    public static final class JavaIsoControl extends NamedFastMatcher {
        static final JavaIsoControl INSTANCE = new JavaIsoControl();

        private JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return c9 <= 31 || (c9 >= 127 && c9 <= 159);
        }
    }

    public static final class JavaLetter extends CharMatcher {
        static final JavaLetter INSTANCE = new JavaLetter();

        private JavaLetter() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Character.isLetter(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    public static final class JavaLetterOrDigit extends CharMatcher {
        static final JavaLetterOrDigit INSTANCE = new JavaLetterOrDigit();

        private JavaLetterOrDigit() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Character.isLetterOrDigit(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    public static final class JavaLowerCase extends CharMatcher {
        static final JavaLowerCase INSTANCE = new JavaLowerCase();

        private JavaLowerCase() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Character.isLowerCase(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    public static final class JavaUpperCase extends CharMatcher {
        static final JavaUpperCase INSTANCE = new JavaUpperCase();

        private JavaUpperCase() {
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return Character.isUpperCase(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    public static abstract class NamedFastMatcher extends FastMatcher {
        private final String description;

        public NamedFastMatcher(String str) {
            this.description = (String) Preconditions.checkNotNull(str);
        }

        @Override // com.google.common.base.CharMatcher
        public final String toString() {
            return this.description;
        }
    }

    public static class Negated extends CharMatcher {
        final CharMatcher original;

        public Negated(CharMatcher charMatcher) {
            this.original = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            return charSequence.length() - this.original.countIn(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return !this.original.matches(c9);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return this.original.matchesNoneOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            return this.original.matchesAllOf(charSequence);
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return this.original;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            BitSet bitSet2 = new BitSet();
            this.original.setBits(bitSet2);
            bitSet2.flip(0, 65536);
            bitSet.or(bitSet2);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.original + ".negate()";
        }
    }

    public static class NegatedFastMatcher extends Negated {
        public NegatedFastMatcher(CharMatcher charMatcher) {
            super(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public final CharMatcher precomputed() {
            return this;
        }
    }

    public static final class None extends NamedFastMatcher {
        static final None INSTANCE = new None();

        private None() {
            super("CharMatcher.none()");
        }

        @Override // com.google.common.base.CharMatcher
        public CharMatcher and(CharMatcher charMatcher) {
            Preconditions.checkNotNull(charMatcher);
            return this;
        }

        @Override // com.google.common.base.CharMatcher
        public String collapseFrom(CharSequence charSequence, char c9) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int countIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return 0;
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public int lastIndexIn(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return false;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesAllOf(CharSequence charSequence) {
            return charSequence.length() == 0;
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matchesNoneOf(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return true;
        }

        @Override // com.google.common.base.CharMatcher.FastMatcher, com.google.common.base.CharMatcher
        public CharMatcher negate() {
            return CharMatcher.any();
        }

        @Override // com.google.common.base.CharMatcher
        /* renamed from: or */
        public CharMatcher mo17548or(CharMatcher charMatcher) {
            return (CharMatcher) Preconditions.checkNotNull(charMatcher);
        }

        @Override // com.google.common.base.CharMatcher
        public String removeFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, char c9) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimLeadingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public String trimTrailingFrom(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override // com.google.common.base.CharMatcher
        public int indexIn(CharSequence charSequence, int i9) {
            Preconditions.checkPositionIndex(i9, charSequence.length());
            return -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
            Preconditions.checkNotNull(charSequence2);
            return charSequence.toString();
        }
    }

    /* renamed from: com.google.common.base.CharMatcher$Or */
    public static final class C3563Or extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        public C3563Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
            this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return this.first.matches(c9) || this.second.matches(c9);
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            this.first.setBits(bitSet);
            this.second.setBits(bitSet);
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return "CharMatcher.or(" + this.first + ", " + this.second + ")";
        }
    }

    public static class RangesMatcher extends CharMatcher {
        private final String description;
        private final char[] rangeEnds;
        private final char[] rangeStarts;

        public RangesMatcher(String str, char[] cArr, char[] cArr2) {
            this.description = str;
            this.rangeStarts = cArr;
            this.rangeEnds = cArr2;
            Preconditions.checkArgument(cArr.length == cArr2.length);
            int i9 = 0;
            while (i9 < cArr.length) {
                Preconditions.checkArgument(cArr[i9] <= cArr2[i9]);
                int i10 = i9 + 1;
                if (i10 < cArr.length) {
                    Preconditions.checkArgument(cArr2[i9] < cArr[i10]);
                }
                i9 = i10;
            }
        }

        @Override // com.google.common.base.CharMatcher, com.google.common.base.Predicate
        @Deprecated
        public /* bridge */ /* synthetic */ boolean apply(Character ch) {
            return super.apply(ch);
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            int iBinarySearch = Arrays.binarySearch(this.rangeStarts, c9);
            if (iBinarySearch >= 0) {
                return true;
            }
            int i9 = (~iBinarySearch) - 1;
            return i9 >= 0 && c9 <= this.rangeEnds[i9];
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.description;
        }
    }

    public static final class SingleWidth extends RangesMatcher {
        static final SingleWidth INSTANCE = new SingleWidth();

        private SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000־א׳\u0600ݐ\u0e00Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ\u0e7f₯℺﷿\ufeffￜ".toCharArray());
        }
    }

    @VisibleForTesting
    public static final class Whitespace extends NamedFastMatcher {
        static final int MULTIPLIER = 1682554634;
        static final String TABLE = "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001  \f\u2009\u3000\u2004\u3000\u3000\u2028\n \u3000";
        static final int SHIFT = Integer.numberOfLeadingZeros(31);
        static final Whitespace INSTANCE = new Whitespace();

        public Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c9) {
            return TABLE.charAt((MULTIPLIER * c9) >>> SHIFT) == c9;
        }

        @Override // com.google.common.base.CharMatcher
        @GwtIncompatible
        public void setBits(BitSet bitSet) {
            for (int i9 = 0; i9 < 32; i9++) {
                bitSet.set(TABLE.charAt(i9));
            }
        }
    }

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int length = charSequence.length();
        return length != 0 ? length != 1 ? length != 2 ? new AnyOf(charSequence) : isEither(charSequence.charAt(0), charSequence.charAt(1)) : m17547is(charSequence.charAt(0)) : none();
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }

    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    private String finishCollapseFrom(CharSequence charSequence, int i9, int i10, char c9, StringBuilder sb, boolean z8) {
        while (i9 < i10) {
            char cCharAt = charSequence.charAt(i9);
            if (!matches(cCharAt)) {
                sb.append(cCharAt);
                z8 = false;
            } else if (!z8) {
                sb.append(c9);
                z8 = true;
            }
            i9++;
        }
        return sb.toString();
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher) predicate : new ForPredicate(predicate);
    }

    public static CharMatcher inRange(char c9, char c10) {
        return new InRange(c9, c10);
    }

    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }

    /* renamed from: is */
    public static CharMatcher m17547is(char c9) {
        return new C3562Is(c9);
    }

    private static IsEither isEither(char c9, char c10) {
        return new IsEither(c9, c10);
    }

    public static CharMatcher isNot(char c9) {
        return new IsNot(c9);
    }

    @GwtIncompatible
    private static boolean isSmall(int i9, int i10) {
        return i9 <= 1023 && i10 > (i9 * 4) * 16;
    }

    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }

    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }

    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }

    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }

    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }

    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    @GwtIncompatible
    private static CharMatcher precomputedPositive(int i9, BitSet bitSet, String str) {
        if (i9 == 0) {
            return none();
        }
        if (i9 == 1) {
            return m17547is((char) bitSet.nextSetBit(0));
        }
        if (i9 != 2) {
            return isSmall(i9, bitSet.length()) ? SmallCharMatcher.from(bitSet, str) : new BitSetMatcher(bitSet, str);
        }
        char cNextSetBit = (char) bitSet.nextSetBit(0);
        return isEither(cNextSetBit, (char) bitSet.nextSetBit(cNextSetBit + 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String showCharacter(char c9) {
        char[] cArr = {IOUtils.DIR_SEPARATOR_WINDOWS, 'u', 0, 0, 0, 0};
        for (int i9 = 0; i9 < 4; i9++) {
            cArr[5 - i9] = "0123456789ABCDEF".charAt(c9 & 15);
            c9 = (char) (c9 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new And(this, charMatcher);
    }

    public String collapseFrom(CharSequence charSequence, char c9) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length) {
            char cCharAt = charSequence.charAt(i9);
            if (matches(cCharAt)) {
                if (cCharAt != c9 || (i9 != length - 1 && matches(charSequence.charAt(i9 + 1)))) {
                    StringBuilder sb = new StringBuilder(length);
                    sb.append(charSequence, 0, i9);
                    sb.append(c9);
                    return finishCollapseFrom(charSequence, i9 + 1, length, c9, sb, true);
                }
                i9++;
            }
            i9++;
        }
        return charSequence.toString();
    }

    public int countIn(CharSequence charSequence) {
        int i9 = 0;
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            if (matches(charSequence.charAt(i10))) {
                i9++;
            }
        }
        return i9;
    }

    public int indexIn(CharSequence charSequence) {
        return indexIn(charSequence, 0);
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public abstract boolean matches(char c9);

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public CharMatcher negate() {
        return new Negated(this);
    }

    /* renamed from: or */
    public CharMatcher mo17548or(CharMatcher charMatcher) {
        return new C3563Or(this, charMatcher);
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    @GwtIncompatible
    public CharMatcher precomputedInternal() {
        String strSubstring;
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int iCardinality = bitSet.cardinality();
        if (iCardinality * 2 <= 65536) {
            return precomputedPositive(iCardinality, bitSet, toString());
        }
        bitSet.flip(0, 65536);
        int i9 = 65536 - iCardinality;
        final String string = toString();
        if (string.endsWith(".negate()")) {
            strSubstring = string.substring(0, string.length() - 9);
        } else {
            strSubstring = string + ".negate()";
        }
        return new NegatedFastMatcher(precomputedPositive(i9, bitSet, strSubstring)) { // from class: com.google.common.base.CharMatcher.1
            @Override // com.google.common.base.CharMatcher.Negated, com.google.common.base.CharMatcher
            public String toString() {
                return string;
            }
        };
    }

    public String removeFrom(CharSequence charSequence) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        int i9 = 1;
        while (true) {
            iIndexIn++;
            while (iIndexIn != charArray.length) {
                if (matches(charArray[iIndexIn])) {
                    break;
                }
                charArray[iIndexIn - i9] = charArray[iIndexIn];
                iIndexIn++;
            }
            return new String(charArray, 0, iIndexIn - i9);
            i9++;
        }
    }

    public String replaceFrom(CharSequence charSequence, char c9) {
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        char[] charArray = string.toCharArray();
        charArray[iIndexIn] = c9;
        while (true) {
            iIndexIn++;
            if (iIndexIn >= charArray.length) {
                return new String(charArray);
            }
            if (matches(charArray[iIndexIn])) {
                charArray[iIndexIn] = c9;
            }
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    @GwtIncompatible
    public void setBits(BitSet bitSet) {
        for (int i9 = 65535; i9 >= 0; i9--) {
            if (matches((char) i9)) {
                bitSet.set(i9);
            }
        }
    }

    public String toString() {
        return super.toString();
    }

    public String trimAndCollapseFrom(CharSequence charSequence, char c9) {
        int length = charSequence.length();
        int i9 = length - 1;
        int i10 = 0;
        while (i10 < length && matches(charSequence.charAt(i10))) {
            i10++;
        }
        int i11 = i9;
        while (i11 > i10 && matches(charSequence.charAt(i11))) {
            i11--;
        }
        if (i10 == 0 && i11 == i9) {
            return collapseFrom(charSequence, c9);
        }
        int i12 = i11 + 1;
        return finishCollapseFrom(charSequence, i10, i12, c9, new StringBuilder(i12 - i10), false);
    }

    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i9 = 0;
        while (i9 < length && matches(charSequence.charAt(i9))) {
            i9++;
        }
        int i10 = length - 1;
        while (i10 > i9 && matches(charSequence.charAt(i10))) {
            i10--;
        }
        return charSequence.subSequence(i9, i10 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!matches(charSequence.charAt(i9))) {
                return charSequence.subSequence(i9, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public int indexIn(CharSequence charSequence, int i9) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i9, length);
        while (i9 < length) {
            if (matches(charSequence.charAt(i9))) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        int i9 = 0;
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        String string = charSequence.toString();
        int iIndexIn = indexIn(string);
        if (iIndexIn == -1) {
            return string;
        }
        int length2 = string.length();
        StringBuilder sb = new StringBuilder(((length2 * 3) / 2) + 16);
        do {
            sb.append((CharSequence) string, i9, iIndexIn);
            sb.append(charSequence2);
            i9 = iIndexIn + 1;
            iIndexIn = indexIn(string, i9);
        } while (iIndexIn != -1);
        sb.append((CharSequence) string, i9, length2);
        return sb.toString();
    }
}
