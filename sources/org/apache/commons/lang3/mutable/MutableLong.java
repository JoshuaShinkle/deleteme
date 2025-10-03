package org.apache.commons.lang3.mutable;

/* loaded from: classes.dex */
public class MutableLong extends Number implements Comparable<MutableLong>, Mutable<Number> {
    private static final long serialVersionUID = 62986528375L;
    private long value;

    public MutableLong() {
    }

    public void add(long j9) {
        this.value += j9;
    }

    public void decrement() {
        this.value--;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableLong) && this.value == ((MutableLong) obj).longValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        long j9 = this.value;
        return (int) (j9 ^ (j9 >>> 32));
    }

    public void increment() {
        this.value++;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(long j9) {
        this.value -= j9;
    }

    public Long toLong() {
        return Long.valueOf(longValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public MutableLong(long j9) {
        this.value = j9;
    }

    public void add(Number number) {
        this.value += number.longValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableLong mutableLong) {
        long j9 = mutableLong.value;
        long j10 = this.value;
        if (j10 < j9) {
            return -1;
        }
        return j10 == j9 ? 0 : 1;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Number getValue2() {
        return Long.valueOf(this.value);
    }

    public void setValue(long j9) {
        this.value = j9;
    }

    public void subtract(Number number) {
        this.value -= number.longValue();
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.longValue();
    }

    public MutableLong(Number number) {
        this.value = number.longValue();
    }

    public MutableLong(String str) {
        this.value = Long.parseLong(str);
    }
}
