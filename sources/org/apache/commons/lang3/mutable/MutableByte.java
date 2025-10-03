package org.apache.commons.lang3.mutable;

/* loaded from: classes.dex */
public class MutableByte extends Number implements Comparable<MutableByte>, Mutable<Number> {
    private static final long serialVersionUID = -1585823265;
    private byte value;

    public MutableByte() {
    }

    public void add(byte b9) {
        this.value = (byte) (this.value + b9);
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return this.value;
    }

    public void decrement() {
        this.value = (byte) (this.value - 1);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableByte) && this.value == ((MutableByte) obj).byteValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value = (byte) (this.value + 1);
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void subtract(byte b9) {
        this.value = (byte) (this.value - b9);
    }

    public Byte toByte() {
        return Byte.valueOf(byteValue());
    }

    public String toString() {
        return String.valueOf((int) this.value);
    }

    public MutableByte(byte b9) {
        this.value = b9;
    }

    public void add(Number number) {
        this.value = (byte) (this.value + number.byteValue());
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableByte mutableByte) {
        byte b9 = mutableByte.value;
        byte b10 = this.value;
        if (b10 < b9) {
            return -1;
        }
        return b10 == b9 ? 0 : 1;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue, reason: merged with bridge method [inline-methods] */
    public Number getValue2() {
        return Byte.valueOf(this.value);
    }

    public void setValue(byte b9) {
        this.value = b9;
    }

    public void subtract(Number number) {
        this.value = (byte) (this.value - number.byteValue());
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.byteValue();
    }

    public MutableByte(Number number) {
        this.value = number.byteValue();
    }

    public MutableByte(String str) {
        this.value = Byte.parseByte(str);
    }
}
