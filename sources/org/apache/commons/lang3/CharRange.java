package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class CharRange implements Iterable<Character>, Serializable {
    private static final long serialVersionUID = 8270183163158333422L;
    private final char end;
    private transient String iToString;
    private final boolean negated;
    private final char start;

    public static class CharacterIterator implements Iterator<Character> {
        private char current;
        private boolean hasNext;
        private final CharRange range;

        private void prepareNext() {
            if (!this.range.negated) {
                if (this.current < this.range.end) {
                    this.current = (char) (this.current + 1);
                    return;
                } else {
                    this.hasNext = false;
                    return;
                }
            }
            char c9 = this.current;
            if (c9 == 65535) {
                this.hasNext = false;
                return;
            }
            if (c9 + 1 != this.range.start) {
                this.current = (char) (this.current + 1);
            } else if (this.range.end == 65535) {
                this.hasNext = false;
            } else {
                this.current = (char) (this.range.end + 1);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private CharacterIterator(CharRange charRange) {
            this.range = charRange;
            this.hasNext = true;
            if (!charRange.negated) {
                this.current = charRange.start;
                return;
            }
            if (charRange.start != 0) {
                this.current = (char) 0;
            } else if (charRange.end == 65535) {
                this.hasNext = false;
            } else {
                this.current = (char) (charRange.end + 1);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Character next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            char c9 = this.current;
            prepareNext();
            return Character.valueOf(c9);
        }
    }

    private CharRange(char c9, char c10, boolean z8) {
        if (c9 > c10) {
            c10 = c9;
            c9 = c10;
        }
        this.start = c9;
        this.end = c10;
        this.negated = z8;
    }

    /* renamed from: is */
    public static CharRange m21913is(char c9) {
        return new CharRange(c9, c9, false);
    }

    public static CharRange isIn(char c9, char c10) {
        return new CharRange(c9, c10, false);
    }

    public static CharRange isNot(char c9) {
        return new CharRange(c9, c9, true);
    }

    public static CharRange isNotIn(char c9, char c10) {
        return new CharRange(c9, c10, true);
    }

    public boolean contains(char c9) {
        return (c9 >= this.start && c9 <= this.end) != this.negated;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange charRange = (CharRange) obj;
        return this.start == charRange.start && this.end == charRange.end && this.negated == charRange.negated;
    }

    public char getEnd() {
        return this.end;
    }

    public char getStart() {
        return this.start;
    }

    public int hashCode() {
        return this.start + 'S' + (this.end * 7) + (this.negated ? 1 : 0);
    }

    public boolean isNegated() {
        return this.negated;
    }

    @Override // java.lang.Iterable
    public Iterator<Character> iterator() {
        return new CharacterIterator();
    }

    public String toString() {
        if (this.iToString == null) {
            StringBuilder sb = new StringBuilder(4);
            if (isNegated()) {
                sb.append('^');
            }
            sb.append(this.start);
            if (this.start != this.end) {
                sb.append('-');
                sb.append(this.end);
            }
            this.iToString = sb.toString();
        }
        return this.iToString;
    }

    public boolean contains(CharRange charRange) {
        if (charRange != null) {
            return this.negated ? charRange.negated ? this.start >= charRange.start && this.end <= charRange.end : charRange.end < this.start || charRange.start > this.end : charRange.negated ? this.start == 0 && this.end == 65535 : this.start <= charRange.start && this.end >= charRange.end;
        }
        throw new IllegalArgumentException("The Range must not be null");
    }
}
