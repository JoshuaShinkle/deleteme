package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class CodedInputStream {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private boolean enableAliasing;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private RefillCallback refillCallback;
    private int sizeLimit;
    private int totalBytesRetired;

    public interface RefillCallback {
        void onRefill();
    }

    public class SkippedDataSink implements RefillCallback {
        private ByteArrayOutputStream byteArrayStream;
        private int lastPos;

        private SkippedDataSink() {
            this.lastPos = CodedInputStream.this.bufferPos;
        }

        public ByteBuffer getSkippedData() {
            ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
            if (byteArrayOutputStream == null) {
                return ByteBuffer.wrap(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos - this.lastPos);
            }
            byteArrayOutputStream.write(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos);
            return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.CodedInputStream.RefillCallback
        public void onRefill() {
            if (this.byteArrayStream == null) {
                this.byteArrayStream = new ByteArrayOutputStream();
            }
            this.byteArrayStream.write(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos - this.lastPos);
            this.lastPos = 0;
        }
    }

    private CodedInputStream(byte[] bArr, int i9, int i10) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = bArr;
        this.bufferSize = i10 + i9;
        this.bufferPos = i9;
        this.totalBytesRetired = -i9;
        this.input = null;
        this.bufferIsImmutable = false;
    }

    public static int decodeZigZag32(int i9) {
        return (-(i9 & 1)) ^ (i9 >>> 1);
    }

    public static long decodeZigZag64(long j9) {
        return (-(j9 & 1)) ^ (j9 >>> 1);
    }

    private void ensureAvailable(int i9) throws InvalidProtocolBufferException {
        if (this.bufferSize - this.bufferPos < i9) {
            refillBuffer(i9);
        }
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream);
    }

    private byte[] readRawBytesSlowPath(int i9) throws InvalidProtocolBufferException {
        if (i9 <= 0) {
            if (i9 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }
        int i10 = this.totalBytesRetired;
        int i11 = this.bufferPos;
        int i12 = i10 + i11 + i9;
        int i13 = this.currentLimit;
        if (i12 > i13) {
            skipRawBytes((i13 - i10) - i11);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if (i9 < 4096) {
            byte[] bArr = new byte[i9];
            int i14 = this.bufferSize - i11;
            System.arraycopy(this.buffer, i11, bArr, 0, i14);
            this.bufferPos = this.bufferSize;
            int i15 = i9 - i14;
            ensureAvailable(i15);
            System.arraycopy(this.buffer, 0, bArr, i14, i15);
            this.bufferPos = i15;
            return bArr;
        }
        int i16 = this.bufferSize;
        this.totalBytesRetired = i10 + i16;
        this.bufferPos = 0;
        this.bufferSize = 0;
        int length = i16 - i11;
        int i17 = i9 - length;
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i17 > 0) {
            int iMin = Math.min(i17, 4096);
            byte[] bArr2 = new byte[iMin];
            int i18 = 0;
            while (i18 < iMin) {
                InputStream inputStream = this.input;
                int i19 = inputStream == null ? -1 : inputStream.read(bArr2, i18, iMin - i18);
                if (i19 == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                this.totalBytesRetired += i19;
                i18 += i19;
            }
            i17 -= iMin;
            arrayList.add(bArr2);
        }
        byte[] bArr3 = new byte[i9];
        System.arraycopy(this.buffer, i11, bArr3, 0, length);
        for (byte[] bArr4 : arrayList) {
            System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
            length += bArr4.length;
        }
        return bArr3;
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

    private void refillBuffer(int i9) throws InvalidProtocolBufferException {
        if (!tryRefillBuffer(i9)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private void skipRawBytesSlowPath(int i9) throws InvalidProtocolBufferException {
        if (i9 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int i10 = this.totalBytesRetired;
        int i11 = this.bufferPos;
        int i12 = i10 + i11 + i9;
        int i13 = this.currentLimit;
        if (i12 > i13) {
            skipRawBytes((i13 - i10) - i11);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i14 = this.bufferSize;
        int i15 = i14 - i11;
        this.bufferPos = i14;
        refillBuffer(1);
        while (true) {
            int i16 = i9 - i15;
            int i17 = this.bufferSize;
            if (i16 <= i17) {
                this.bufferPos = i16;
                return;
            } else {
                i15 += i17;
                this.bufferPos = i17;
                refillBuffer(1);
            }
        }
    }

    private void skipRawVarint() throws InvalidProtocolBufferException {
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (i9 - i10 >= 10) {
            byte[] bArr = this.buffer;
            int i11 = 0;
            while (i11 < 10) {
                int i12 = i10 + 1;
                if (bArr[i10] >= 0) {
                    this.bufferPos = i12;
                    return;
                } else {
                    i11++;
                    i10 = i12;
                }
            }
        }
        skipRawVarintSlowPath();
    }

    private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
        for (int i9 = 0; i9 < 10; i9++) {
            if (readRawByte() >= 0) {
                return;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    private boolean tryRefillBuffer(int i9) throws IOException {
        int i10 = this.bufferPos;
        if (i10 + i9 <= this.bufferSize) {
            StringBuilder sb = new StringBuilder(77);
            sb.append("refillBuffer() called when ");
            sb.append(i9);
            sb.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb.toString());
        }
        if (this.totalBytesRetired + i10 + i9 > this.currentLimit) {
            return false;
        }
        RefillCallback refillCallback = this.refillCallback;
        if (refillCallback != null) {
            refillCallback.onRefill();
        }
        if (this.input != null) {
            int i11 = this.bufferPos;
            if (i11 > 0) {
                int i12 = this.bufferSize;
                if (i12 > i11) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, i11, bArr, 0, i12 - i11);
                }
                this.totalBytesRetired += i11;
                this.bufferSize -= i11;
                this.bufferPos = 0;
            }
            InputStream inputStream = this.input;
            byte[] bArr2 = this.buffer;
            int i13 = this.bufferSize;
            int i14 = inputStream.read(bArr2, i13, bArr2.length - i13);
            if (i14 == 0 || i14 < -1 || i14 > this.buffer.length) {
                StringBuilder sb2 = new StringBuilder(102);
                sb2.append("InputStream#read(byte[]) returned invalid result: ");
                sb2.append(i14);
                sb2.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb2.toString());
            }
            if (i14 > 0) {
                this.bufferSize += i14;
                if ((this.totalBytesRetired + i9) - this.sizeLimit > 0) {
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
                recomputeBufferSizeAfterLimit();
                if (this.bufferSize >= i9) {
                    return true;
                }
                return tryRefillBuffer(i9);
            }
        }
        return false;
    }

    public void checkLastTagWas(int i9) throws InvalidProtocolBufferException {
        if (this.lastTag != i9) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public void enableAliasing(boolean z8) {
        this.enableAliasing = z8;
    }

    public int getBytesUntilLimit() {
        int i9 = this.currentLimit;
        if (i9 == Integer.MAX_VALUE) {
            return -1;
        }
        return i9 - (this.totalBytesRetired + this.bufferPos);
    }

    public int getLastTag() {
        return this.lastTag;
    }

    public int getTotalBytesRead() {
        return this.totalBytesRetired + this.bufferPos;
    }

    public boolean isAtEnd() {
        return this.bufferPos == this.bufferSize && !tryRefillBuffer(1);
    }

    public void popLimit(int i9) {
        this.currentLimit = i9;
        recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int i9) throws InvalidProtocolBufferException {
        if (i9 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        int i10 = i9 + this.totalBytesRetired + this.bufferPos;
        int i11 = this.currentLimit;
        if (i10 > i11) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        this.currentLimit = i10;
        recomputeBufferSizeAfterLimit();
        return i11;
    }

    public boolean readBool() {
        return readRawVarint64() != 0;
    }

    public byte[] readByteArray() {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return readRawBytesSlowPath(rawVarint32);
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i10, i10 + rawVarint32);
        this.bufferPos += rawVarint32;
        return bArrCopyOfRange;
    }

    public ByteBuffer readByteBuffer() {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return rawVarint32 == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(rawVarint32));
        }
        ByteBuffer byteBufferSlice = (this.input == null && !this.bufferIsImmutable && this.enableAliasing) ? ByteBuffer.wrap(this.buffer, i10, rawVarint32).slice() : ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i10, i10 + rawVarint32));
        this.bufferPos += rawVarint32;
        return byteBufferSlice;
    }

    public ByteString readBytes() {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return rawVarint32 == 0 ? ByteString.EMPTY : new LiteralByteString(readRawBytesSlowPath(rawVarint32));
        }
        ByteString boundedByteString = (this.bufferIsImmutable && this.enableAliasing) ? new BoundedByteString(this.buffer, this.bufferPos, rawVarint32) : ByteString.copyFrom(this.buffer, i10, rawVarint32);
        this.bufferPos += rawVarint32;
        return boundedByteString;
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

    public void readGroup(int i9, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        int i10 = this.recursionDepth;
        if (i10 >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        this.recursionDepth = i10 + 1;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(WireFormat.makeTag(i9, 4));
        this.recursionDepth--;
    }

    public int readInt32() {
        return readRawVarint32();
    }

    public long readInt64() {
        return readRawVarint64();
    }

    public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        int rawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int iPushLimit = pushLimit(rawVarint32);
        this.recursionDepth++;
        builder.mergeFrom(this, extensionRegistryLite);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(iPushLimit);
    }

    public byte readRawByte() throws InvalidProtocolBufferException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(1);
        }
        byte[] bArr = this.buffer;
        int i9 = this.bufferPos;
        this.bufferPos = i9 + 1;
        return bArr[i9];
    }

    public byte[] readRawBytes(int i9) {
        int i10 = this.bufferPos;
        if (i9 > this.bufferSize - i10 || i9 <= 0) {
            return readRawBytesSlowPath(i9);
        }
        int i11 = i9 + i10;
        this.bufferPos = i11;
        return Arrays.copyOfRange(this.buffer, i10, i11);
    }

    public int readRawLittleEndian32() throws InvalidProtocolBufferException {
        int i9 = this.bufferPos;
        if (this.bufferSize - i9 < 4) {
            refillBuffer(4);
            i9 = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i9 + 4;
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    public long readRawLittleEndian64() throws InvalidProtocolBufferException {
        int i9 = this.bufferPos;
        if (this.bufferSize - i9 < 8) {
            refillBuffer(8);
            i9 = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i9 + 8;
        return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
    
        if (r2[r3] < 0) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int readRawVarint32() {
        int i9;
        long j9;
        int i10 = this.bufferPos;
        int i11 = this.bufferSize;
        if (i11 != i10) {
            byte[] bArr = this.buffer;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.bufferPos = i12;
                return b9;
            }
            if (i11 - i12 >= 9) {
                int i13 = i12 + 1;
                int i14 = b9 ^ (bArr[i12] << 7);
                long j10 = i14;
                if (j10 >= 0) {
                    int i15 = i13 + 1;
                    int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                    long j11 = i16;
                    if (j11 >= 0) {
                        i9 = (int) (16256 ^ j11);
                    } else {
                        i13 = i15 + 1;
                        j10 = i16 ^ (bArr[i15] << Ascii.NAK);
                        if (j10 >= 0) {
                            i15 = i13 + 1;
                            i9 = (int) ((r0 ^ (r1 << Ascii.f15383FS)) ^ 266354560);
                            if (bArr[i13] < 0) {
                                i13 = i15 + 1;
                                if (bArr[i15] < 0) {
                                    i15 = i13 + 1;
                                    if (bArr[i13] < 0) {
                                        i13 = i15 + 1;
                                        if (bArr[i15] < 0) {
                                            i15 = i13 + 1;
                                            if (bArr[i13] < 0) {
                                                i13 = i15 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                            this.bufferPos = i13;
                            return i9;
                        }
                        j9 = -2080896;
                    }
                    i13 = i15;
                    this.bufferPos = i13;
                    return i9;
                }
                j9 = -128;
                i9 = (int) (j10 ^ j9);
                this.bufferPos = i13;
                return i9;
            }
        }
        return (int) readRawVarint64SlowPath();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        if (r2[r0] < 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readRawVarint64() {
        long j9;
        long j10;
        long j11;
        int i9 = this.bufferPos;
        int i10 = this.bufferSize;
        if (i10 != i9) {
            byte[] bArr = this.buffer;
            int i11 = i9 + 1;
            byte b9 = bArr[i9];
            if (b9 >= 0) {
                this.bufferPos = i11;
                return b9;
            }
            if (i10 - i11 >= 9) {
                int i12 = i11 + 1;
                long j12 = b9 ^ (bArr[i11] << 7);
                if (j12 >= 0) {
                    int i13 = i12 + 1;
                    long j13 = j12 ^ (bArr[i12] << Ascii.f15390SO);
                    if (j13 >= 0) {
                        j11 = 16256;
                    } else {
                        i12 = i13 + 1;
                        j12 = j13 ^ (bArr[i13] << Ascii.NAK);
                        if (j12 < 0) {
                            j10 = -2080896;
                        } else {
                            i13 = i12 + 1;
                            j13 = j12 ^ (bArr[i12] << 28);
                            if (j13 >= 0) {
                                j11 = 266354560;
                            } else {
                                i12 = i13 + 1;
                                j12 = j13 ^ (bArr[i13] << 35);
                                if (j12 < 0) {
                                    j10 = -34093383808L;
                                } else {
                                    i13 = i12 + 1;
                                    j13 = j12 ^ (bArr[i12] << 42);
                                    if (j13 >= 0) {
                                        j11 = 4363953127296L;
                                    } else {
                                        i12 = i13 + 1;
                                        j12 = j13 ^ (bArr[i13] << 49);
                                        if (j12 >= 0) {
                                            int i14 = i12 + 1;
                                            long j14 = (j12 ^ (bArr[i12] << 56)) ^ 71499008037633920L;
                                            i12 = j14 < 0 ? i14 + 1 : i14;
                                            j9 = j14;
                                            this.bufferPos = i12;
                                            return j9;
                                        }
                                        j10 = -558586000294016L;
                                    }
                                }
                            }
                        }
                    }
                    j9 = j13 ^ j11;
                    i12 = i13;
                    this.bufferPos = i12;
                    return j9;
                }
                j10 = -128;
                j9 = j12 ^ j10;
                this.bufferPos = i12;
                return j9;
            }
        }
        return readRawVarint64SlowPath();
    }

    public long readRawVarint64SlowPath() throws InvalidProtocolBufferException {
        long j9 = 0;
        for (int i9 = 0; i9 < 64; i9 += 7) {
            j9 |= (r3 & Ascii.DEL) << i9;
            if ((readRawByte() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j9;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
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

    public String readString() {
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferSize;
        int i10 = this.bufferPos;
        if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
            return rawVarint32 == 0 ? "" : new String(readRawBytesSlowPath(rawVarint32), "UTF-8");
        }
        String str = new String(this.buffer, i10, rawVarint32, "UTF-8");
        this.bufferPos += rawVarint32;
        return str;
    }

    public String readStringRequireUtf8() throws InvalidProtocolBufferException {
        byte[] rawBytesSlowPath;
        int rawVarint32 = readRawVarint32();
        int i9 = this.bufferPos;
        if (rawVarint32 <= this.bufferSize - i9 && rawVarint32 > 0) {
            rawBytesSlowPath = this.buffer;
            this.bufferPos = i9 + rawVarint32;
        } else {
            if (rawVarint32 == 0) {
                return "";
            }
            rawBytesSlowPath = readRawBytesSlowPath(rawVarint32);
            i9 = 0;
        }
        if (Utf8.isValidUtf8(rawBytesSlowPath, i9, i9 + rawVarint32)) {
            return new String(rawBytesSlowPath, i9, rawVarint32, "UTF-8");
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    public int readTag() throws InvalidProtocolBufferException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        int rawVarint32 = readRawVarint32();
        this.lastTag = rawVarint32;
        if (WireFormat.getTagFieldNumber(rawVarint32) != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public int readUInt32() {
        return readRawVarint32();
    }

    public long readUInt64() {
        return readRawVarint64();
    }

    @Deprecated
    public void readUnknownGroup(int i9, MessageLite.Builder builder) throws InvalidProtocolBufferException {
        readGroup(i9, builder, (ExtensionRegistryLite) null);
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = -this.bufferPos;
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

    public boolean skipField(int i9) throws InvalidProtocolBufferException {
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            skipRawVarint();
            return true;
        }
        if (tagWireType == 1) {
            skipRawBytes(8);
            return true;
        }
        if (tagWireType == 2) {
            skipRawBytes(readRawVarint32());
            return true;
        }
        if (tagWireType == 3) {
            skipMessage();
            checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i9), 4));
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        skipRawBytes(4);
        return true;
    }

    public void skipMessage() throws InvalidProtocolBufferException {
        int tag;
        do {
            tag = readTag();
            if (tag == 0) {
                return;
            }
        } while (skipField(tag));
    }

    public void skipRawBytes(int i9) throws InvalidProtocolBufferException {
        int i10 = this.bufferSize;
        int i11 = this.bufferPos;
        if (i9 > i10 - i11 || i9 < 0) {
            skipRawBytesSlowPath(i9);
        } else {
            this.bufferPos = i11 + i9;
        }
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i9, int i10) {
        CodedInputStream codedInputStream = new CodedInputStream(bArr, i9, i10);
        try {
            codedInputStream.pushLimit(i10);
            return codedInputStream;
        } catch (InvalidProtocolBufferException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
        int tag;
        do {
            tag = readTag();
            if (tag == 0) {
                return;
            }
        } while (skipField(tag, codedOutputStream));
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        }
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byte[] bArr = new byte[byteBufferDuplicate.remaining()];
        byteBufferDuplicate.get(bArr);
        return newInstance(bArr);
    }

    public <T extends MessageLite> T readGroup(int i9, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        int i10 = this.recursionDepth;
        if (i10 < this.recursionLimit) {
            this.recursionDepth = i10 + 1;
            T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i9, 4));
            this.recursionDepth--;
            return partialFrom;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        int rawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            int iPushLimit = pushLimit(rawVarint32);
            this.recursionDepth++;
            T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(iPushLimit);
            return partialFrom;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public boolean skipField(int i9, CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            long int64 = readInt64();
            codedOutputStream.writeRawVarint32(i9);
            codedOutputStream.writeUInt64NoTag(int64);
            return true;
        }
        if (tagWireType == 1) {
            long rawLittleEndian64 = readRawLittleEndian64();
            codedOutputStream.writeRawVarint32(i9);
            codedOutputStream.writeFixed64NoTag(rawLittleEndian64);
            return true;
        }
        if (tagWireType == 2) {
            ByteString bytes = readBytes();
            codedOutputStream.writeRawVarint32(i9);
            codedOutputStream.writeBytesNoTag(bytes);
            return true;
        }
        if (tagWireType == 3) {
            codedOutputStream.writeRawVarint32(i9);
            skipMessage(codedOutputStream);
            int iMakeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i9), 4);
            checkLastTagWas(iMakeTag);
            codedOutputStream.writeRawVarint32(iMakeTag);
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType == 5) {
            int rawLittleEndian32 = readRawLittleEndian32();
            codedOutputStream.writeRawVarint32(i9);
            codedOutputStream.writeFixed32NoTag(rawLittleEndian32);
            return true;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    private CodedInputStream(InputStream inputStream) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
        this.bufferIsImmutable = false;
    }

    public static int readRawVarint32(InputStream inputStream) throws IOException {
        int i9 = inputStream.read();
        if (i9 != -1) {
            return readRawVarint32(i9, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static CodedInputStream newInstance(LiteralByteString literalByteString) {
        CodedInputStream codedInputStream = new CodedInputStream(literalByteString);
        try {
            codedInputStream.pushLimit(literalByteString.size());
            return codedInputStream;
        } catch (InvalidProtocolBufferException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public static int readRawVarint32(int i9, InputStream inputStream) throws IOException {
        if ((i9 & 128) == 0) {
            return i9;
        }
        int i10 = i9 & 127;
        int i11 = 7;
        while (i11 < 32) {
            int i12 = inputStream.read();
            if (i12 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            i10 |= (i12 & 127) << i11;
            if ((i12 & 128) == 0) {
                return i10;
            }
            i11 += 7;
        }
        while (i11 < 64) {
            int i13 = inputStream.read();
            if (i13 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if ((i13 & 128) == 0) {
                return i10;
            }
            i11 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    private CodedInputStream(LiteralByteString literalByteString) {
        this.enableAliasing = false;
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = DEFAULT_SIZE_LIMIT;
        this.refillCallback = null;
        this.buffer = literalByteString.bytes;
        int offsetIntoBytes = literalByteString.getOffsetIntoBytes();
        this.bufferPos = offsetIntoBytes;
        this.bufferSize = offsetIntoBytes + literalByteString.size();
        this.totalBytesRetired = -this.bufferPos;
        this.input = null;
        this.bufferIsImmutable = true;
    }
}
