package com.google.android.exoplayer2.util;

import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public final class ParsableByteArray {
    public byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
    }

    public ParsableByteArray(int i9) {
        this.data = new byte[i9];
        this.limit = i9;
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int capacity() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public char peekChar() {
        byte[] bArr = this.data;
        int i9 = this.position;
        return (char) ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) | ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 8));
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & UnsignedBytes.MAX_VALUE;
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i9) {
        readBytes(parsableBitArray.data, 0, i9);
        parsableBitArray.setPosition(0);
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public int readInt() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 24) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 16);
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i13 + 1;
        return (bArr[i13] & UnsignedBytes.MAX_VALUE) | i14;
    }

    public int readInt24() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = (((bArr[i9] & UnsignedBytes.MAX_VALUE) << 24) >> 8) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i11 + 1;
        return (bArr[i11] & UnsignedBytes.MAX_VALUE) | i12;
    }

    public String readLine() {
        if (bytesLeft() == 0) {
            return null;
        }
        int i9 = this.position;
        while (i9 < this.limit && !Util.isLinebreak(this.data[i9])) {
            i9++;
        }
        int i10 = this.position;
        if (i9 - i10 >= 3) {
            byte[] bArr = this.data;
            if (bArr[i10] == -17 && bArr[i10 + 1] == -69 && bArr[i10 + 2] == -65) {
                this.position = i10 + 3;
            }
        }
        byte[] bArr2 = this.data;
        int i11 = this.position;
        String str = new String(bArr2, i11, i9 - i11);
        this.position = i9;
        int i12 = this.limit;
        if (i9 == i12) {
            return str;
        }
        byte[] bArr3 = this.data;
        if (bArr3[i9] == 13) {
            int i13 = i9 + 1;
            this.position = i13;
            if (i13 == i12) {
                return str;
            }
        }
        int i14 = this.position;
        if (bArr3[i14] == 10) {
            this.position = i14 + 1;
        }
        return str;
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8);
        int i13 = i11 + 1;
        int i14 = i12 | ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 16);
        this.position = i13 + 1;
        return ((bArr[i13] & UnsignedBytes.MAX_VALUE) << 24) | i14;
    }

    public int readLittleEndianInt24() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i11 + 1;
        return ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 16) | i12;
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        long j9 = bArr[r1] & 255;
        int i9 = this.position + 1 + 1 + 1;
        long j10 = j9 | ((bArr[r2] & 255) << 8) | ((bArr[r1] & 255) << 16);
        long j11 = j10 | ((bArr[i9] & 255) << 24);
        long j12 = j11 | ((bArr[r3] & 255) << 32);
        long j13 = j12 | ((bArr[r4] & 255) << 40);
        long j14 = j13 | ((bArr[r3] & 255) << 48);
        this.position = i9 + 1 + 1 + 1 + 1 + 1;
        return j14 | ((bArr[r4] & 255) << 56);
    }

    public short readLittleEndianShort() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = bArr[i9] & UnsignedBytes.MAX_VALUE;
        this.position = i10 + 1;
        return (short) (((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8) | i11);
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        long j9 = bArr[r1] & 255;
        int i9 = this.position + 1 + 1 + 1;
        long j10 = j9 | ((bArr[r2] & 255) << 8) | ((bArr[r1] & 255) << 16);
        this.position = i9 + 1;
        return j10 | ((bArr[i9] & 255) << 24);
    }

    public int readLittleEndianUnsignedInt24() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i11 + 1;
        return ((bArr[i11] & UnsignedBytes.MAX_VALUE) << 16) | i12;
    }

    public int readLittleEndianUnsignedIntToInt() {
        int littleEndianInt = readLittleEndianInt();
        if (littleEndianInt >= 0) {
            return littleEndianInt;
        }
        throw new IllegalStateException("Top bit not zero: " + littleEndianInt);
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = bArr[i9] & UnsignedBytes.MAX_VALUE;
        this.position = i10 + 1;
        return ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8) | i11;
    }

    public long readLong() {
        byte[] bArr = this.data;
        long j9 = (bArr[r1] & 255) << 56;
        int i9 = this.position + 1 + 1 + 1;
        long j10 = j9 | ((bArr[r2] & 255) << 48) | ((bArr[r1] & 255) << 40);
        long j11 = j10 | ((bArr[i9] & 255) << 32);
        long j12 = j11 | ((bArr[r3] & 255) << 24);
        long j13 = j12 | ((bArr[r4] & 255) << 16);
        long j14 = j13 | ((bArr[r3] & 255) << 8);
        this.position = i9 + 1 + 1 + 1 + 1 + 1;
        return j14 | (bArr[r4] & 255);
    }

    public String readNullTerminatedString(int i9) {
        if (i9 == 0) {
            return "";
        }
        int i10 = this.position;
        int i11 = (i10 + i9) - 1;
        String str = new String(this.data, i10, (i11 >= this.limit || this.data[i11] != 0) ? i9 : i9 - 1);
        this.position += i9;
        return str;
    }

    public short readShort() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = (bArr[i9] & UnsignedBytes.MAX_VALUE) << 8;
        this.position = i10 + 1;
        return (short) ((bArr[i10] & UnsignedBytes.MAX_VALUE) | i11);
    }

    public String readString(int i9) {
        return readString(i9, Charset.forName("UTF-8"));
    }

    public int readSynchSafeInt() {
        return (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7) | readUnsignedByte();
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int i9 = this.position;
        this.position = i9 + 1;
        return bArr[i9] & UnsignedBytes.MAX_VALUE;
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = (bArr[i10] & UnsignedBytes.MAX_VALUE) | ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i10 + 1 + 2;
        return i11;
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        long j9 = (bArr[r1] & 255) << 24;
        int i9 = this.position + 1 + 1 + 1;
        long j10 = j9 | ((bArr[r2] & 255) << 16) | ((bArr[r1] & 255) << 8);
        this.position = i9 + 1;
        return j10 | (bArr[i9] & 255);
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 16) | ((bArr[i10] & UnsignedBytes.MAX_VALUE) << 8);
        this.position = i11 + 1;
        return (bArr[i11] & UnsignedBytes.MAX_VALUE) | i12;
    }

    public int readUnsignedIntToInt() {
        int i9 = readInt();
        if (i9 >= 0) {
            return i9;
        }
        throw new IllegalStateException("Top bit not zero: " + i9);
    }

    public long readUnsignedLongToLong() {
        long j9 = readLong();
        if (j9 >= 0) {
            return j9;
        }
        throw new IllegalStateException("Top bit not zero: " + j9);
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int i9 = this.position;
        int i10 = i9 + 1;
        int i11 = (bArr[i9] & UnsignedBytes.MAX_VALUE) << 8;
        this.position = i10 + 1;
        return (bArr[i10] & UnsignedBytes.MAX_VALUE) | i11;
    }

    public long readUtf8EncodedLong() {
        int i9;
        int i10;
        long j9 = this.data[this.position];
        int i11 = 7;
        while (true) {
            if (i11 < 0) {
                break;
            }
            if (((1 << i11) & j9) != 0) {
                i11--;
            } else if (i11 < 6) {
                j9 &= r6 - 1;
                i10 = 7 - i11;
            } else if (i11 == 7) {
                i10 = 1;
            }
        }
        i10 = 0;
        if (i10 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j9);
        }
        for (i9 = 1; i9 < i10; i9++) {
            if ((this.data[this.position + i9] & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j9);
            }
            j9 = (j9 << 6) | (r3 & 63);
        }
        this.position += i10;
        return j9;
    }

    public void reset(int i9) {
        reset(capacity() < i9 ? new byte[i9] : this.data, i9);
    }

    public void setLimit(int i9) {
        Assertions.checkArgument(i9 >= 0 && i9 <= this.data.length);
        this.limit = i9;
    }

    public void setPosition(int i9) {
        Assertions.checkArgument(i9 >= 0 && i9 <= this.limit);
        this.position = i9;
    }

    public void skipBytes(int i9) {
        setPosition(this.position + i9);
    }

    public String readString(int i9, Charset charset) {
        String str = new String(this.data, this.position, i9, charset);
        this.position += i9;
        return str;
    }

    public void reset(byte[] bArr, int i9) {
        this.data = bArr;
        this.limit = i9;
        this.position = 0;
    }

    public void readBytes(byte[] bArr, int i9, int i10) {
        System.arraycopy(this.data, this.position, bArr, i9, i10);
        this.position += i10;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public void readBytes(ByteBuffer byteBuffer, int i9) {
        byteBuffer.put(this.data, this.position, i9);
        this.position += i9;
    }

    public String readNullTerminatedString() {
        if (bytesLeft() == 0) {
            return null;
        }
        int i9 = this.position;
        while (i9 < this.limit && this.data[i9] != 0) {
            i9++;
        }
        byte[] bArr = this.data;
        int i10 = this.position;
        String str = new String(bArr, i10, i9 - i10);
        this.position = i9;
        if (i9 < this.limit) {
            this.position = i9 + 1;
        }
        return str;
    }

    public void reset() {
        this.position = 0;
        this.limit = 0;
    }

    public ParsableByteArray(byte[] bArr, int i9) {
        this.data = bArr;
        this.limit = i9;
    }
}
