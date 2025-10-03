package org.apache.commons.lang3;

/* loaded from: classes.dex */
public class BitField {
    private final int _mask;
    private final int _shift_count;

    public BitField(int i9) {
        this._mask = i9;
        int i10 = 0;
        if (i9 != 0) {
            while ((i9 & 1) == 0) {
                i10++;
                i9 >>= 1;
            }
        }
        this._shift_count = i10;
    }

    public int clear(int i9) {
        return i9 & (~this._mask);
    }

    public byte clearByte(byte b9) {
        return (byte) clear(b9);
    }

    public short clearShort(short s8) {
        return (short) clear(s8);
    }

    public int getRawValue(int i9) {
        return i9 & this._mask;
    }

    public short getShortRawValue(short s8) {
        return (short) getRawValue(s8);
    }

    public short getShortValue(short s8) {
        return (short) getValue(s8);
    }

    public int getValue(int i9) {
        return getRawValue(i9) >> this._shift_count;
    }

    public boolean isAllSet(int i9) {
        int i10 = this._mask;
        return (i9 & i10) == i10;
    }

    public boolean isSet(int i9) {
        return (i9 & this._mask) != 0;
    }

    public int set(int i9) {
        return i9 | this._mask;
    }

    public int setBoolean(int i9, boolean z8) {
        return z8 ? set(i9) : clear(i9);
    }

    public byte setByte(byte b9) {
        return (byte) set(b9);
    }

    public byte setByteBoolean(byte b9, boolean z8) {
        return z8 ? setByte(b9) : clearByte(b9);
    }

    public short setShort(short s8) {
        return (short) set(s8);
    }

    public short setShortBoolean(short s8, boolean z8) {
        return z8 ? setShort(s8) : clearShort(s8);
    }

    public short setShortValue(short s8, short s9) {
        return (short) setValue(s8, s9);
    }

    public int setValue(int i9, int i10) {
        int i11 = this._mask;
        return (i9 & (~i11)) | ((i10 << this._shift_count) & i11);
    }
}
