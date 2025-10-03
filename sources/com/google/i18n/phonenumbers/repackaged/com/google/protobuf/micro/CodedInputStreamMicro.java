package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.micro;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/* loaded from: classes2.dex */
public final class CodedInputStreamMicro {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private int sizeLimit;
    private int totalBytesRetired;

    private CodedInputStreamMicro(byte[] bArr, int i9, int i10) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.buffer = bArr;
        this.bufferSize = i10 + i9;
        this.bufferPos = i9;
        this.input = null;
    }

    public static int decodeZigZag32(int i9) {
        return (-(i9 & 1)) ^ (i9 >>> 1);
    }

    public static long decodeZigZag64(long j9) {
        return (-(j9 & 1)) ^ (j9 >>> 1);
    }

    public static CodedInputStreamMicro newInstance(InputStream inputStream) {
        return new CodedInputStreamMicro(inputStream);
    }

    private void recomputeBufferSizeAfterLimit() {
        int i9 = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i9;
        int i10 = this.totalBytesRetired + i9;
        int i11 = this.currentLimit;
        if (i10 <= i11) {
            this.bufferSizeAfterLimit = 0;
            return;
        }
        int i12 = i10 - i11;
        this.bufferSizeAfterLimit = i12;
        this.bufferSize = i9 - i12;
    }

    private boolean refillBuffer(boolean z8) throws InvalidProtocolBufferMicroException {
        int i9 = this.bufferPos;
        int i10 = this.bufferSize;
        if (i9 < i10) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        int i11 = this.totalBytesRetired;
        if (i11 + i10 == this.currentLimit) {
            if (z8) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        this.totalBytesRetired = i11 + i10;
        this.bufferPos = 0;
        InputStream inputStream = this.input;
        int i12 = inputStream == null ? -1 : inputStream.read(this.buffer);
        this.bufferSize = i12;
        if (i12 == 0 || i12 < -1) {
            int i13 = this.bufferSize;
            StringBuilder sb = new StringBuilder(102);
            sb.append("InputStream#read(byte[]) returned invalid result: ");
            sb.append(i13);
            sb.append("\nThe InputStream implementation is buggy.");
            throw new IllegalStateException(sb.toString());
        }
        if (i12 == -1) {
            this.bufferSize = 0;
            if (z8) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            return false;
        }
        recomputeBufferSizeAfterLimit();
        int i14 = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
        if (i14 > this.sizeLimit || i14 < 0) {
            throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
        }
        return true;
    }

    public void checkLastTagWas(int i9) throws InvalidProtocolBufferMicroException {
        if (this.lastTag != i9) {
            throw InvalidProtocolBufferMicroException.invalidEndTag();
        }
    }

    public int getBytesUntilLimit() {
        int i9 = this.currentLimit;
        if (i9 == Integer.MAX_VALUE) {
            return -1;
        }
        return i9 - (this.totalBytesRetired + this.bufferPos);
    }

    public boolean isAtEnd() {
        return this.bufferPos == this.bufferSize && !refillBuffer(false);
    }

    public void popLimit(int i9) {
        this.currentLimit = i9;
        recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int i9) throws InvalidProtocolBufferMicroException {
        if (i9 < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int i10 = i9 + this.totalBytesRetired + this.bufferPos;
        int i11 = this.currentLimit;
        if (i10 > i11) {
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        this.currentLimit = i10;
        recomputeBufferSizeAfterLimit();
        return i11;
    }

    public boolean readBool() {
        return readRawVarint32() != 0;
    }

    public ByteStringMicro readBytes() throws InvalidProtocolBufferMicroException {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return rawVarint32 == 0 ? ByteStringMicro.EMPTY : ByteStringMicro.copyFrom(readRawBytes(rawVarint32));
        }
        ByteStringMicro byteStringMicroCopyFrom = ByteStringMicro.copyFrom(this.buffer, i10, rawVarint32);
        this.bufferPos += rawVarint32;
        return byteStringMicroCopyFrom;
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

    public void readGroup(MessageMicro messageMicro, int i9) throws InvalidProtocolBufferMicroException {
        int i10 = this.recursionDepth;
        if (i10 >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        this.recursionDepth = i10 + 1;
        messageMicro.mergeFrom(this);
        checkLastTagWas(WireFormatMicro.makeTag(i9, 4));
        this.recursionDepth--;
    }

    public int readInt32() {
        return readRawVarint32();
    }

    public long readInt64() {
        return readRawVarint64();
    }

    public void readMessage(MessageMicro messageMicro) throws InvalidProtocolBufferMicroException {
        int rawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        int iPushLimit = pushLimit(rawVarint32);
        this.recursionDepth++;
        messageMicro.mergeFrom(this);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(iPushLimit);
    }

    public byte readRawByte() throws InvalidProtocolBufferMicroException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(true);
        }
        byte[] bArr = this.buffer;
        int i9 = this.bufferPos;
        this.bufferPos = i9 + 1;
        return bArr[i9];
    }

    public byte[] readRawBytes(int i9) throws InvalidProtocolBufferMicroException {
        if (i9 < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int i10 = this.totalBytesRetired;
        int i11 = this.bufferPos;
        int i12 = i10 + i11 + i9;
        int i13 = this.currentLimit;
        if (i12 > i13) {
            skipRawBytes((i13 - i10) - i11);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        int i14 = this.bufferSize;
        if (i9 <= i14 - i11) {
            byte[] bArr = new byte[i9];
            System.arraycopy(this.buffer, i11, bArr, 0, i9);
            this.bufferPos += i9;
            return bArr;
        }
        if (i9 >= 4096) {
            this.totalBytesRetired = i10 + i14;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int length = i14 - i11;
            int i15 = i9 - length;
            Vector vector = new Vector();
            while (i15 > 0) {
                int iMin = Math.min(i15, 4096);
                byte[] bArr2 = new byte[iMin];
                int i16 = 0;
                while (i16 < iMin) {
                    InputStream inputStream = this.input;
                    int i17 = inputStream == null ? -1 : inputStream.read(bArr2, i16, iMin - i16);
                    if (i17 == -1) {
                        throw InvalidProtocolBufferMicroException.truncatedMessage();
                    }
                    this.totalBytesRetired += i17;
                    i16 += i17;
                }
                i15 -= iMin;
                vector.addElement(bArr2);
            }
            byte[] bArr3 = new byte[i9];
            System.arraycopy(this.buffer, i11, bArr3, 0, length);
            for (int i18 = 0; i18 < vector.size(); i18++) {
                byte[] bArr4 = (byte[]) vector.elementAt(i18);
                System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
                length += bArr4.length;
            }
            return bArr3;
        }
        byte[] bArr5 = new byte[i9];
        int i19 = i14 - i11;
        System.arraycopy(this.buffer, i11, bArr5, 0, i19);
        this.bufferPos = this.bufferSize;
        refillBuffer(true);
        while (true) {
            int i20 = i9 - i19;
            int i21 = this.bufferSize;
            if (i20 <= i21) {
                System.arraycopy(this.buffer, 0, bArr5, i19, i20);
                this.bufferPos = i20;
                return bArr5;
            }
            System.arraycopy(this.buffer, 0, bArr5, i19, i21);
            int i22 = this.bufferSize;
            i19 += i22;
            this.bufferPos = i22;
            refillBuffer(true);
        }
    }

    public int readRawLittleEndian32() throws InvalidProtocolBufferMicroException {
        return (readRawByte() & UnsignedBytes.MAX_VALUE) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 8) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 16) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 24);
    }

    public long readRawLittleEndian64() throws InvalidProtocolBufferMicroException {
        return ((readRawByte() & 255) << 8) | (readRawByte() & 255) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48) | ((readRawByte() & 255) << 56);
    }

    public int readRawVarint32() throws InvalidProtocolBufferMicroException {
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
                    throw InvalidProtocolBufferMicroException.malformedVarint();
                }
                i9 = rawByte4 << Ascii.NAK;
            }
        }
        return i10 | i9;
    }

    public long readRawVarint64() throws InvalidProtocolBufferMicroException {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((readRawByte() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
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

    public String readString() throws InvalidProtocolBufferMicroException {
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

    public int readTag() throws InvalidProtocolBufferMicroException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int rawVarint32 = readRawVarint32();
        this.lastTag = rawVarint32;
        if (rawVarint32 != 0) {
            return rawVarint32;
        }
        throw InvalidProtocolBufferMicroException.invalidTag();
    }

    public int readUInt32() {
        return readRawVarint32();
    }

    public long readUInt64() {
        return readRawVarint64();
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = 0;
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

    public boolean skipField(int i9) throws InvalidProtocolBufferMicroException {
        int tagWireType = WireFormatMicro.getTagWireType(i9);
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
            checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(i9), 4));
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType != 5) {
            throw InvalidProtocolBufferMicroException.invalidWireType();
        }
        readRawLittleEndian32();
        return true;
    }

    public void skipMessage() throws InvalidProtocolBufferMicroException {
        int tag;
        do {
            tag = readTag();
            if (tag == 0) {
                return;
            }
        } while (skipField(tag));
    }

    public void skipRawBytes(int i9) throws InvalidProtocolBufferMicroException {
        if (i9 < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int i10 = this.totalBytesRetired;
        int i11 = this.bufferPos;
        int i12 = i10 + i11 + i9;
        int i13 = this.currentLimit;
        if (i12 > i13) {
            skipRawBytes((i13 - i10) - i11);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        int i14 = this.bufferSize;
        if (i9 <= i14 - i11) {
            this.bufferPos = i11 + i9;
            return;
        }
        int i15 = i14 - i11;
        this.totalBytesRetired = i10 + i14;
        this.bufferPos = 0;
        this.bufferSize = 0;
        while (i15 < i9) {
            InputStream inputStream = this.input;
            int iSkip = inputStream == null ? -1 : (int) inputStream.skip(i9 - i15);
            if (iSkip <= 0) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            i15 += iSkip;
            this.totalBytesRetired += iSkip;
        }
    }

    public static CodedInputStreamMicro newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStreamMicro newInstance(byte[] bArr, int i9, int i10) {
        return new CodedInputStreamMicro(bArr, i9, i10);
    }

    public static int readRawVarint32(InputStream inputStream) throws IOException {
        int i9 = 0;
        int i10 = 0;
        while (i9 < 32) {
            int i11 = inputStream.read();
            if (i11 == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            i10 |= (i11 & 127) << i9;
            if ((i11 & 128) == 0) {
                return i10;
            }
            i9 += 7;
        }
        while (i9 < 64) {
            int i12 = inputStream.read();
            if (i12 == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            if ((i12 & 128) == 0) {
                return i10;
            }
            i9 += 7;
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    private CodedInputStreamMicro(InputStream inputStream) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.input = inputStream;
    }
}
