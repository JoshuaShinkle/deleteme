package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes.dex */
public final class Range<T> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Comparator<T> comparator;
    private transient int hashCode;
    private final T maximum;
    private final T minimum;
    private transient String toString;

    public enum ComparableComparator implements Comparator {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    private Range(T t8, T t9, Comparator<T> comparator) {
        if (t8 == null || t9 == null) {
            throw new IllegalArgumentException("Elements in a range must not be null: element1=" + t8 + ", element2=" + t9);
        }
        if (comparator == null) {
            this.comparator = ComparableComparator.INSTANCE;
        } else {
            this.comparator = comparator;
        }
        if (this.comparator.compare(t8, t9) < 1) {
            this.minimum = t8;
            this.maximum = t9;
        } else {
            this.minimum = t9;
            this.maximum = t8;
        }
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range between(Comparable comparable, Comparable comparable2) {
        return between(comparable, comparable2, null);
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    /* renamed from: is */
    public static Range m21914is(Comparable comparable) {
        return between(comparable, comparable, null);
    }

    public boolean contains(T t8) {
        return t8 != null && this.comparator.compare(t8, this.minimum) > -1 && this.comparator.compare(t8, this.maximum) < 1;
    }

    public boolean containsRange(Range<T> range) {
        return range != null && contains(range.minimum) && contains(range.maximum);
    }

    public int elementCompareTo(T t8) {
        if (t8 == null) {
            throw new NullPointerException("Element is null");
        }
        if (isAfter(t8)) {
            return -1;
        }
        return isBefore(t8) ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != Range.class) {
            return false;
        }
        Range range = (Range) obj;
        return this.minimum.equals(range.minimum) && this.maximum.equals(range.maximum);
    }

    public Comparator<T> getComparator() {
        return this.comparator;
    }

    public T getMaximum() {
        return this.maximum;
    }

    public T getMinimum() {
        return this.minimum;
    }

    public int hashCode() {
        int i9 = this.hashCode;
        if (i9 != 0) {
            return i9;
        }
        int iHashCode = this.maximum.hashCode() + ((((629 + Range.class.hashCode()) * 37) + this.minimum.hashCode()) * 37);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public Range<T> intersectionWith(Range<T> range) {
        if (!isOverlappedBy(range)) {
            throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", range));
        }
        if (equals(range)) {
            return this;
        }
        return between(getComparator().compare(this.minimum, range.minimum) < 0 ? range.minimum : this.minimum, getComparator().compare(this.maximum, range.maximum) < 0 ? this.maximum : range.maximum, getComparator());
    }

    public boolean isAfter(T t8) {
        return t8 != null && this.comparator.compare(t8, this.minimum) < 0;
    }

    public boolean isAfterRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isAfter(range.maximum);
    }

    public boolean isBefore(T t8) {
        return t8 != null && this.comparator.compare(t8, this.maximum) > 0;
    }

    public boolean isBeforeRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isBefore(range.minimum);
    }

    public boolean isEndedBy(T t8) {
        return t8 != null && this.comparator.compare(t8, this.maximum) == 0;
    }

    public boolean isNaturalOrdering() {
        return this.comparator == ComparableComparator.INSTANCE;
    }

    public boolean isOverlappedBy(Range<T> range) {
        if (range == null) {
            return false;
        }
        return range.contains(this.minimum) || range.contains(this.maximum) || contains(range.minimum);
    }

    public boolean isStartedBy(T t8) {
        return t8 != null && this.comparator.compare(t8, this.minimum) == 0;
    }

    public String toString() {
        String str = this.toString;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        sb.append(this.minimum);
        sb.append("..");
        sb.append(this.maximum);
        sb.append(']');
        String string = sb.toString();
        this.toString = string;
        return string;
    }

    public static <T> Range<T> between(T t8, T t9, Comparator<T> comparator) {
        return new Range<>(t8, t9, comparator);
    }

    /* renamed from: is */
    public static <T> Range<T> m21915is(T t8, Comparator<T> comparator) {
        return between(t8, t8, comparator);
    }

    public String toString(String str) {
        return String.format(str, this.minimum, this.maximum, this.comparator);
    }
}
