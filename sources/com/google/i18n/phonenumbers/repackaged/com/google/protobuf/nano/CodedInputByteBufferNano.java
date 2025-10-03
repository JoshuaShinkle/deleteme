package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

/* loaded from: classes2.dex */
public final class CodedInputByteBufferNano {
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int bufferStart;
    private int lastTag;
    private int recursionDepth;
    private int currentLimit = Integer.MAX_VALUE;
    private int recursionLimit = 64;
    private int sizeLimit = DEFAULT_SIZE_LIMIT;

    private CodedInputByteBufferNano(byte[] bArr, int i9, int i10) {
        this.buffer = bArr;
        this.bufferStart = i9;
        this.bufferSize = i10 + i9;
        this.bufferPos = i9;
    }

    public static int decodeZigZag32(int i9) {
        return (-(i9 & 1)) ^ (i9 >>> 1);
    }

    public static long decodeZigZag64(long j9) {
        return (-(j9 & 1)) ^ (j9 >>> 1);
    }

    public static CodedInputByteBufferNano newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    private void recomputeBufferSizeAfterLimit() {
        int i9 = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i9;
        int i10 = this.currentLimit;
        if (i9 <= i10) {
            this.bufferSizeAfterLimit = 0;
            return;
        }
        int i11 = i9 - i10;
        this.bufferSizeAfterLimit = i11;
        this.bufferSize = i9 - i11;
    }

    public void checkLastTagWas(int i9) {
        if (this.lastTag != i9) {
            throw InvalidProtocolBufferNanoException.invalidEndTag();
        }
    }

    public int getAbsolutePosition() {
        return this.bufferPos;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public int getBytesUntilLimit() {
        int i9 = this.currentLimit;
        if (i9 == Integer.MAX_VALUE) {
            return -1;
        }
        return i9 - this.bufferPos;
    }

    public byte[] getData(int i9, int i10) {
        if (i10 == 0) {
            return WireFormatNano.EMPTY_BYTES;
        }
        byte[] bArr = new byte[i10];
        System.arraycopy(this.buffer, this.bufferStart + i9, bArr, 0, i10);
        return bArr;
    }

    public int getPosition() {
        return this.bufferPos - this.bufferStart;
    }

    public boolean isAtEnd() {
        return this.bufferPos == this.bufferSize;
    }

    public void popLimit(int i9) {
        this.currentLimit = i9;
        recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int i9) throws InvalidProtocolBufferNanoException {
        if (i9 < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i10 = i9 + this.bufferPos;
        int i11 = this.currentLimit;
        if (i10 > i11) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.currentLimit = i10;
        recomputeBufferSizeAfterLimit();
        return i11;
    }

    public boolean readBool() {
        return readRawVarint32() != 0;
    }

    public byte[] readBytes() throws InvalidProtocolBufferNanoException {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return rawVarint32 == 0 ? WireFormatNano.EMPTY_BYTES : readRawBytes(rawVarint32);
        }
        byte[] bArr = new byte[rawVarint32];
        System.arraycopy(this.buffer, i10, bArr, 0, rawVarint32);
        this.bufferPos += rawVarint32;
        return bArr;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public int readEnum() {
        return readRawVarint32();
    }

    public int readFixed32() {
        return readRawLittleEndian32();
    }

    public long readFixed64() {
        return readRawLittleEndian64();
    }

    public float readFloat() {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public void readGroup(MessageNano messageNano, int i9) throws InvalidProtocolBufferNanoException {
        int i10 = this.recursionDepth;
        if (i10 >= this.recursionLimit) {
            throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
        }
        this.recursionDepth = i10 + 1;
        messageNano.mergeFrom(this);
        checkLastTagWas(WireFormatNano.makeTag(i9, 4));
        this.recursionDepth--;
    }

    public int readInt32() {
        return readRawVarint32();
    }

    public long readInt64() {
        return readRawVarint64();
    }

    public void readMessage(MessageNano messageNano) throws InvalidProtocolBufferNanoException {
        int rawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
        }
        int iPushLimit = pushLimit(rawVarint32);
        this.recursionDepth++;
        messageNano.mergeFrom(this);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(iPushLimit);
    }

    public byte readRawByte() throws InvalidProtocolBufferNanoException {
        int i9 = this.bufferPos;
        if (i9 == this.bufferSize) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i9 + 1;
        return bArr[i9];
    }

    public byte[] readRawBytes(int i9) throws InvalidProtocolBufferNanoException {
        if (i9 < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i10 = this.bufferPos;
        int i11 = i10 + i9;
        int i12 = this.currentLimit;
        if (i11 > i12) {
            skipRawBytes(i12 - i10);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (i9 > this.bufferSize - i10) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        byte[] bArr = new byte[i9];
        System.arraycopy(this.buffer, i10, bArr, 0, i9);
        this.bufferPos += i9;
        return bArr;
    }

    public int readRawLittleEndian32() throws InvalidProtocolBufferNanoException {
        return (readRawByte() & UnsignedBytes.MAX_VALUE) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 8) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 16) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 24);
    }

    public long readRawLittleEndian64() throws InvalidProtocolBufferNanoException {
        return ((readRawByte() & 255) << 8) | (readRawByte() & 255) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48) | ((readRawByte() & 255) << 56);
    }

    public int readRawVarint32() throws InvalidProtocolBufferNanoException {
        int i9;
        byte rawByte = readRawByte();
        if (rawByte >= 0) {
            return rawByte;
        }
        int i10 = rawByte & Ascii.DEL;
        byte rawByte2 = readRawByte();
        if (rawByte2 >= 0) {
            i9 = rawByte2 << 7;
        } else {
            i10 |= (rawByte2 & Ascii.DEL) << 7;
            byte rawByte3 = readRawByte();
            if (rawByte3 >= 0) {
                i9 = rawByte3 << Ascii.f15390SO;
            } else {
                i10 |= (rawByte3 & Ascii.DEL) << 14;
                byte rawByte4 = readRawByte();
                if (rawByte4 < 0) {
                    int i11 = i10 | ((rawByte4 & Ascii.DEL) << 21);
                    byte rawByte5 = readRawByte();
                    int i12 = i11 | (rawByte5 << Ascii.f15383FS);
                    if (rawByte5 >= 0) {
                        return i12;
                    }
                    for (int i13 = 0; i13 < 5; i13++) {
                        if (readRawByte() >= 0) {
                            return i12;
                        }
                    }
                    throw InvalidProtocolBufferNanoException.malformedVarint();
                }
                i9 = rawByte4 << Ascii.NAK;
            }
        }
        return i10 | i9;
    }

    public long readRawVarint64() throws InvalidProtocolBufferNanoException {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((readRawByte() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw InvalidProtocolBufferNanoException.malformedVarint();
    }

    public int readSFixed32() {
        return readRawLittleEndian32();
    }

    public long readSFixed64() {
        return readRawLittleEndian64();
    }

    public int readSInt32() {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() {
        return decodeZigZag64(readRawVarint64());
    }

    public String readString() throws InvalidProtocolBufferNanoException {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return new String(readRawBytes(rawVarint32), "UTF-8");
        }
        String str = new String(this.buffer, i10, rawVarint32, "UTF-8");
        this.bufferPos += rawVarint32;
        return str;
    }

    public int readTag() throws InvalidProtocolBufferNanoException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int rawVarint32 = readRawVarint32();
        this.lastTag = rawVarint32;
        if (rawVarint32 != 0) {
            return rawVarint32;
        }
        throw InvalidProtocolBufferNanoException.invalidTag();
    }

    public int readUInt32() {
        return readRawVarint32();
    }

    public long readUInt64() {
        return readRawVarint64();
    }

    public void resetSizeCounter() {
    }

    public void rewindToPosition(int i9) {
        int i10 = this.bufferPos;
        int i11 = this.bufferStart;
        if (i9 <= i10 - i11) {
            if (i9 >= 0) {
                this.bufferPos = i11 + i9;
                return;
            }
            StringBuilder sb = new StringBuilder(24);
            sb.append("Bad position ");
            sb.append(i9);
            throw new IllegalArgumentException(sb.toString());
        }
        int i12 = this.bufferPos - this.bufferStart;
        StringBuilder sb2 = new StringBuilder(50);
        sb2.append("Position ");
        sb2.append(i9);
        sb2.append(" is beyond current ");
        sb2.append(i12);
        throw new IllegalArgumentException(sb2.toString());
    }

    public int setRecursionLimit(int i9) {
        if (i9 >= 0) {
            int i10 = this.recursionLimit;
            this.recursionLimit = i9;
            return i10;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append("Recursion limit cannot be negative: ");
        sb.append(i9);
        throw new IllegalArgumentException(sb.toString());
    }

    public int setSizeLimit(int i9) {
        if (i9 >= 0) {
            int i10 = this.sizeLimit;
            this.sizeLimit = i9;
            return i10;
        }
        StringBuilder sb = new StringBuilder(42);
        sb.append("Size limit cannot be negative: ");
        sb.append(i9);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean skipField(int i9) {
        int tagWireType = WireFormatNano.getTagWireType(i9);
        if (tagWireType == 0) {
            readInt32();
            return true;
        }
        if (tagWireType == 1) {
            readRawLittleEndian64();
            return true;
        }
        if (tagWireType == 2) {
            skipRawBytes(readRawVarint32());
            return true;
        }
        if (tagWireType == 3) {
            skipMessage();
            checkLastTagWas(WireFormatNano.makeTag(WireFormatNano.getTagFieldNumber(i9), 4));
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType != 5) {
            throw InvalidProtocolBufferNanoException.invalidWireType();
        }
        readRawLittleEndian32();
        return true;
    }

    public void skipMessage() throws InvalidProtocolBufferNanoException {
        int tag;
        do {
            tag = readTag();
            if (tag == 0) {
                return;
            }
        } while (skipField(tag));
    }

    public void skipRawBytes(int i9) throws InvalidProtocolBufferNanoException {
        if (i9 < 0) {
            throw InvalidProtocolBufferNanoException.negativeSize();
        }
        int i10 = this.bufferPos;
        int i11 = i10 + i9;
        int i12 = this.currentLimit;
        if (i11 > i12) {
            skipRawBytes(i12 - i10);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (i9 > this.bufferSize - i10) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.bufferPos = i10 + i9;
    }

    public static CodedInputByteBufferNano newInstance(byte[] bArr, int i9, int i10) {
        return new CodedInputByteBufferNano(bArr, i9, i10);
    }
}
