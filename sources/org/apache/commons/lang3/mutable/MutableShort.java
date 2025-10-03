package org.apache.commons.lang3.mutable;

/* loaded from: classes.dex */
public class MutableShort extends Number implements Comparable<MutableShort>, Mutable<Number> {
    private static final long serialVersionUID = -2135791679;
    private short value;

    public MutableShort() {
    }

    public void add(short s8) {
        this.value = (short) (this.value + s8);
    }

    public void decrement() {
        this.value = (short) (this.value - 1);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableShort) && this.value == ((MutableShort) obj).shortValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value = (short) (this.value + 1);
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public short shortValue() {
        return this.value;
    }

    public void subtract(short s8) {
        this.value = (short) (this.value - s8);
    }

    public Short toShort() {
        return Short.valueOf(shortValue());
    }

    public String toString() {
        return String.valueOf((int) this.value);
    }

    public MutableShort(short s8) {
        this.value = s8;
    }

    public void add(Number number) {
        this.value = (short) (this.value + number.shortValue());
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableShort mutableShort) {
        short s8 = mutableShort.value;
        short s9 = this.value;
        if (s9 < s8) {
            return -1;
        }
        return s9 == s8 ? 0 : 1;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public Number getValue2() {
        return Short.valueOf(this.value);
    }

    public void setValue(short s8) {
        this.value = s8;
    }

    public void subtract(Number number) {
        this.value = (short) (this.value - number.shortValue());
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.shortValue();
    }

    public MutableShort(Number number) {
        this.value = number.shortValue();
    }

    public MutableShort(String str) {
        this.value = Short.parseShort(str);
    }
}
