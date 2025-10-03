package org.apache.commons.lang3.mutable;

/* loaded from: classes.dex */
public class MutableInt extends Number implements Comparable<MutableInt>, Mutable<Number> {
    private static final long serialVersionUID = 512176391864L;
    private int value;

    public MutableInt() {
    }

    public void add(int i9) {
        this.value += i9;
    }

    public void decrement() {
        this.value--;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableInt) && this.value == ((MutableInt) obj).intValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value++;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(int i9) {
        this.value -= i9;
    }

    public Integer toInteger() {
        return Integer.valueOf(intValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public MutableInt(int i9) {
        this.value = i9;
    }

    public void add(Number number) {
        this.value += number.intValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableInt mutableInt) {
        int i9 = mutableInt.value;
        int i10 = this.value;
        if (i10 < i9) {
            return -1;
        }
        return i10 == i9 ? 0 : 1;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Number getValue2() {
        return Integer.valueOf(this.value);
    }

    public void setValue(int i9) {
        this.value = i9;
    }

    public void subtract(Number number) {
        this.value -= number.intValue();
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(String str) {
        this.value = Integer.parseInt(str);
    }
}
