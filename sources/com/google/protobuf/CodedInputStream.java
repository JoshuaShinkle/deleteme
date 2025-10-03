package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 100;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    public static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private void recomputeBufferSizeAfterLimit() {
            int i9 = this.limit + this.bufferSizeAfterLimit;
            this.limit = i9;
            int i10 = i9 - this.startPos;
            int i11 = this.currentLimit;
            if (i10 <= i11) {
                this.bufferSizeAfterLimit = 0;
                return;
            }
            int i12 = i10 - i11;
            this.bufferSizeAfterLimit = i12;
            this.limit = i9 - i12;
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                byte[] bArr = this.buffer;
                int i10 = this.pos;
                this.pos = i10 + 1;
                if (bArr[i10] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i9) throws InvalidProtocolBufferException {
            if (this.lastTag != i9) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z8) {
            this.enableAliasing = z8;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i9 = this.currentLimit;
            if (i9 == Integer.MAX_VALUE) {
                return -1;
            }
            return i9 - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i9) {
            this.currentLimit = i9;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = i9 + getTotalBytesRead();
            int i10 = this.currentLimit;
            if (totalBytesRead > i10) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i10;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i9 = this.limit;
                int i10 = this.pos;
                if (rawVarint32 <= i9 - i10) {
                    ByteBuffer byteBufferWrap = (this.immutable || !this.enableAliasing) ? ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i10, i10 + rawVarint32)) : ByteBuffer.wrap(this.buffer, i10, rawVarint32).slice();
                    this.pos += rawVarint32;
                    return byteBufferWrap;
                }
            }
            if (rawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i9 = this.limit;
                int i10 = this.pos;
                if (rawVarint32 <= i9 - i10) {
                    ByteString byteStringWrap = (this.immutable && this.enableAliasing) ? ByteString.wrap(this.buffer, i10, rawVarint32) : ByteString.copyFrom(this.buffer, i10, rawVarint32);
                    this.pos += rawVarint32;
                    return byteStringWrap;
                }
            }
            return rawVarint32 == 0 ? ByteString.EMPTY : ByteString.wrap(readRawBytes(rawVarint32));
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (i9 == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 1;
            return bArr[i9];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 > 0) {
                int i10 = this.limit;
                int i11 = this.pos;
                if (i9 <= i10 - i11) {
                    int i12 = i9 + i11;
                    this.pos = i12;
                    return Arrays.copyOfRange(this.buffer, i11, i12);
                }
            }
            if (i9 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i9 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (this.limit - i9 < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 4;
            return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (this.limit - i9 < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 8;
            return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int readRawVarint32() {
            int i9;
            int i10 = this.pos;
            int i11 = this.limit;
            if (i11 != i10) {
                byte[] bArr = this.buffer;
                int i12 = i10 + 1;
                byte b9 = bArr[i10];
                if (b9 >= 0) {
                    this.pos = i12;
                    return b9;
                }
                if (i11 - i12 >= 9) {
                    int i13 = i12 + 1;
                    int i14 = b9 ^ (bArr[i12] << 7);
                    if (i14 < 0) {
                        i9 = i14 ^ (-128);
                    } else {
                        int i15 = i13 + 1;
                        int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                        if (i16 >= 0) {
                            i9 = i16 ^ 16256;
                        } else {
                            i13 = i15 + 1;
                            int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                            if (i17 < 0) {
                                i9 = i17 ^ (-2080896);
                            } else {
                                i15 = i13 + 1;
                                byte b10 = bArr[i13];
                                i9 = (i17 ^ (b10 << Ascii.f15383FS)) ^ 266354560;
                                if (b10 < 0) {
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
                            }
                        }
                        i13 = i15;
                    }
                    this.pos = i13;
                    return i9;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public long readRawVarint64() {
            long j9;
            long j10;
            long j11;
            int i9;
            int i10 = this.pos;
            int i11 = this.limit;
            if (i11 != i10) {
                byte[] bArr = this.buffer;
                int i12 = i10 + 1;
                byte b9 = bArr[i10];
                if (b9 >= 0) {
                    this.pos = i12;
                    return b9;
                }
                if (i11 - i12 >= 9) {
                    int i13 = i12 + 1;
                    int i14 = b9 ^ (bArr[i12] << 7);
                    if (i14 >= 0) {
                        int i15 = i13 + 1;
                        int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                        if (i16 >= 0) {
                            i13 = i15;
                            j9 = i16 ^ 16256;
                        } else {
                            i13 = i15 + 1;
                            int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                            if (i17 < 0) {
                                i9 = i17 ^ (-2080896);
                            } else {
                                long j12 = i17;
                                int i18 = i13 + 1;
                                long j13 = j12 ^ (bArr[i13] << 28);
                                if (j13 >= 0) {
                                    j11 = 266354560;
                                } else {
                                    i13 = i18 + 1;
                                    long j14 = j13 ^ (bArr[i18] << 35);
                                    if (j14 < 0) {
                                        j10 = -34093383808L;
                                    } else {
                                        i18 = i13 + 1;
                                        j13 = j14 ^ (bArr[i13] << 42);
                                        if (j13 >= 0) {
                                            j11 = 4363953127296L;
                                        } else {
                                            i13 = i18 + 1;
                                            j14 = j13 ^ (bArr[i18] << 49);
                                            if (j14 < 0) {
                                                j10 = -558586000294016L;
                                            } else {
                                                int i19 = i13 + 1;
                                                long j15 = (j14 ^ (bArr[i13] << 56)) ^ 71499008037633920L;
                                                i13 = j15 < 0 ? i19 + 1 : i19;
                                                j9 = j15;
                                            }
                                        }
                                    }
                                    j9 = j14 ^ j10;
                                }
                                j9 = j13 ^ j11;
                                i13 = i18;
                            }
                        }
                        this.pos = i13;
                        return j9;
                    }
                    i9 = i14 ^ (-128);
                    j9 = i9;
                    this.pos = i13;
                    return j9;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i9 = this.limit;
                int i10 = this.pos;
                if (rawVarint32 <= i9 - i10) {
                    String str = new String(this.buffer, i10, rawVarint32, Internal.UTF_8);
                    this.pos += rawVarint32;
                    return str;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i9 = this.limit;
                int i10 = this.pos;
                if (rawVarint32 <= i9 - i10) {
                    String strDecodeUtf8 = Utf8.decodeUtf8(this.buffer, i10, rawVarint32);
                    this.pos += rawVarint32;
                    return strDecodeUtf8;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i9, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i9, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 >= 0) {
                int i10 = this.limit;
                int i11 = this.pos;
                if (i9 <= i10 - i11) {
                    this.pos = i11 + i9;
                    return;
                }
            }
            if (i9 >= 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private ArrayDecoder(byte[] bArr, int i9, int i10, boolean z8) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i10 + i9;
            this.pos = i9;
            this.startPos = i9;
            this.immutable = z8;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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
    }

    public static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private int currentLimit;
        private boolean enableAliasing;
        private boolean immutable;
        private Iterable<ByteBuffer> input;
        private Iterator<ByteBuffer> iterator;
        private int lastTag;
        private int startOffset;
        private int totalBufferSize;
        private int totalBytesRead;

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if (!this.iterator.hasNext()) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            tryGetNextByteBuffer();
        }

        private void readRawBytesTo(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferException {
            if (i10 < 0 || i10 > remaining()) {
                if (i10 > 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                if (i10 != 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                return;
            }
            int i11 = i10;
            while (i11 > 0) {
                if (currentRemaining() == 0) {
                    getNextByteBuffer();
                }
                int iMin = Math.min(i11, (int) currentRemaining());
                long j9 = iMin;
                UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, (i10 - i11) + i9, j9);
                i11 -= iMin;
                this.currentByteBufferPos += j9;
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            int i9 = this.totalBufferSize + this.bufferSizeAfterCurrentLimit;
            this.totalBufferSize = i9;
            int i10 = i9 - this.startOffset;
            int i11 = this.currentLimit;
            if (i10 <= i11) {
                this.bufferSizeAfterCurrentLimit = 0;
                return;
            }
            int i12 = i10 - i11;
            this.bufferSizeAfterCurrentLimit = i12;
            this.totalBufferSize = i9 - i12;
        }

        private int remaining() {
            return (int) (((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(int i9, int i10) {
            int iPosition = this.currentByteBuffer.position();
            int iLimit = this.currentByteBuffer.limit();
            try {
                try {
                    this.currentByteBuffer.position(i9);
                    this.currentByteBuffer.limit(i10);
                    return this.currentByteBuffer.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.currentByteBuffer.position(iPosition);
                this.currentByteBuffer.limit(iLimit);
            }
        }

        private void tryGetNextByteBuffer() {
            ByteBuffer next = this.iterator.next();
            this.currentByteBuffer = next;
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            long jPosition = next.position();
            this.currentByteBufferPos = jPosition;
            this.currentByteBufferStartPos = jPosition;
            this.currentByteBufferLimit = this.currentByteBuffer.limit();
            long jAddressOffset = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentAddress = jAddressOffset;
            this.currentByteBufferPos += jAddressOffset;
            this.currentByteBufferStartPos += jAddressOffset;
            this.currentByteBufferLimit += jAddressOffset;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i9) throws InvalidProtocolBufferException {
            if (this.lastTag != i9) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z8) {
            this.enableAliasing = z8;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i9 = this.currentLimit;
            if (i9 == Integer.MAX_VALUE) {
                return -1;
            }
            return i9 - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (((this.totalBytesRead - this.startOffset) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return (((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos == ((long) this.totalBufferSize);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i9) {
            this.currentLimit = i9;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = i9 + getTotalBytesRead();
            int i10 = this.currentLimit;
            if (totalBytesRead > i10) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i10;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j9 = rawVarint32;
                if (j9 <= currentRemaining()) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[rawVarint32];
                        UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j9);
                        this.currentByteBufferPos += j9;
                        return ByteBuffer.wrap(bArr);
                    }
                    long j10 = this.currentByteBufferPos + j9;
                    this.currentByteBufferPos = j10;
                    long j11 = this.currentAddress;
                    return slice((int) ((j10 - j11) - j9), (int) (j10 - j11));
                }
            }
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return ByteBuffer.wrap(bArr2);
            }
            if (rawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j9 = rawVarint32;
                long j10 = this.currentByteBufferLimit;
                long j11 = this.currentByteBufferPos;
                if (j9 <= j10 - j11) {
                    if (this.immutable && this.enableAliasing) {
                        int i9 = (int) (j11 - this.currentAddress);
                        ByteString byteStringWrap = ByteString.wrap(slice(i9, rawVarint32 + i9));
                        this.currentByteBufferPos += j9;
                        return byteStringWrap;
                    }
                    byte[] bArr = new byte[rawVarint32];
                    UnsafeUtil.copyMemory(j11, bArr, 0L, j9);
                    this.currentByteBufferPos += j9;
                    return ByteString.wrap(bArr);
                }
            }
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return ByteString.wrap(bArr2);
            }
            if (rawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j9 = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j9;
            return UnsafeUtil.getByte(j9);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 >= 0) {
                long j9 = i9;
                if (j9 <= currentRemaining()) {
                    byte[] bArr = new byte[i9];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j9);
                    this.currentByteBufferPos += j9;
                    return bArr;
                }
            }
            if (i9 >= 0 && i9 <= remaining()) {
                byte[] bArr2 = new byte[i9];
                readRawBytesTo(bArr2, 0, i9);
                return bArr2;
            }
            if (i9 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i9 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() {
            if (currentRemaining() < 4) {
                return (readRawByte() & UnsignedBytes.MAX_VALUE) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 8) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 16) | ((readRawByte() & UnsignedBytes.MAX_VALUE) << 24);
            }
            long j9 = this.currentByteBufferPos;
            this.currentByteBufferPos = 4 + j9;
            return ((UnsafeUtil.getByte(j9 + 3) & UnsignedBytes.MAX_VALUE) << 24) | (UnsafeUtil.getByte(j9) & UnsignedBytes.MAX_VALUE) | ((UnsafeUtil.getByte(1 + j9) & UnsignedBytes.MAX_VALUE) << 8) | ((UnsafeUtil.getByte(2 + j9) & UnsignedBytes.MAX_VALUE) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            long rawByte;
            byte rawByte2;
            if (currentRemaining() >= 8) {
                long j9 = this.currentByteBufferPos;
                this.currentByteBufferPos = 8 + j9;
                rawByte = (UnsafeUtil.getByte(j9) & 255) | ((UnsafeUtil.getByte(1 + j9) & 255) << 8) | ((UnsafeUtil.getByte(2 + j9) & 255) << 16) | ((UnsafeUtil.getByte(3 + j9) & 255) << 24) | ((UnsafeUtil.getByte(4 + j9) & 255) << 32) | ((UnsafeUtil.getByte(5 + j9) & 255) << 40) | ((UnsafeUtil.getByte(6 + j9) & 255) << 48);
                rawByte2 = UnsafeUtil.getByte(j9 + 7);
            } else {
                rawByte = (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48);
                rawByte2 = readRawByte();
            }
            return ((rawByte2 & 255) << 56) | rawByte;
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0088, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int readRawVarint32() {
            int i9;
            long j9 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j9) {
                long j10 = j9 + 1;
                byte b9 = UnsafeUtil.getByte(j9);
                if (b9 >= 0) {
                    this.currentByteBufferPos++;
                    return b9;
                }
                if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j11 = j10 + 1;
                    int i10 = b9 ^ (UnsafeUtil.getByte(j10) << 7);
                    if (i10 < 0) {
                        i9 = i10 ^ (-128);
                    } else {
                        long j12 = j11 + 1;
                        int i11 = i10 ^ (UnsafeUtil.getByte(j11) << Ascii.f15390SO);
                        if (i11 >= 0) {
                            i9 = i11 ^ 16256;
                        } else {
                            j11 = j12 + 1;
                            int i12 = i11 ^ (UnsafeUtil.getByte(j12) << Ascii.NAK);
                            if (i12 < 0) {
                                i9 = i12 ^ (-2080896);
                            } else {
                                j12 = j11 + 1;
                                byte b10 = UnsafeUtil.getByte(j11);
                                i9 = (i12 ^ (b10 << Ascii.f15383FS)) ^ 266354560;
                                if (b10 < 0) {
                                    j11 = j12 + 1;
                                    if (UnsafeUtil.getByte(j12) < 0) {
                                        j12 = j11 + 1;
                                        if (UnsafeUtil.getByte(j11) < 0) {
                                            j11 = j12 + 1;
                                            if (UnsafeUtil.getByte(j12) < 0) {
                                                j12 = j11 + 1;
                                                if (UnsafeUtil.getByte(j11) < 0) {
                                                    j11 = j12 + 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        j11 = j12;
                    }
                    this.currentByteBufferPos = j11;
                    return i9;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() {
            long j9;
            long j10;
            long j11;
            int i9;
            long j12 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j12) {
                long j13 = j12 + 1;
                byte b9 = UnsafeUtil.getByte(j12);
                if (b9 >= 0) {
                    this.currentByteBufferPos++;
                    return b9;
                }
                if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j14 = j13 + 1;
                    int i10 = b9 ^ (UnsafeUtil.getByte(j13) << 7);
                    if (i10 >= 0) {
                        long j15 = j14 + 1;
                        int i11 = i10 ^ (UnsafeUtil.getByte(j14) << Ascii.f15390SO);
                        if (i11 >= 0) {
                            j9 = i11 ^ 16256;
                        } else {
                            j14 = j15 + 1;
                            int i12 = i11 ^ (UnsafeUtil.getByte(j15) << Ascii.NAK);
                            if (i12 < 0) {
                                i9 = i12 ^ (-2080896);
                            } else {
                                j15 = j14 + 1;
                                long j16 = i12 ^ (UnsafeUtil.getByte(j14) << 28);
                                if (j16 < 0) {
                                    long j17 = j15 + 1;
                                    long j18 = j16 ^ (UnsafeUtil.getByte(j15) << 35);
                                    if (j18 < 0) {
                                        j10 = -34093383808L;
                                    } else {
                                        j15 = j17 + 1;
                                        j16 = j18 ^ (UnsafeUtil.getByte(j17) << 42);
                                        if (j16 >= 0) {
                                            j11 = 4363953127296L;
                                        } else {
                                            j17 = j15 + 1;
                                            j18 = j16 ^ (UnsafeUtil.getByte(j15) << 49);
                                            if (j18 >= 0) {
                                                j15 = j17 + 1;
                                                j9 = (j18 ^ (UnsafeUtil.getByte(j17) << 56)) ^ 71499008037633920L;
                                                if (j9 < 0) {
                                                    long j19 = 1 + j15;
                                                    if (UnsafeUtil.getByte(j15) >= 0) {
                                                        j14 = j19;
                                                    }
                                                }
                                                this.currentByteBufferPos = j14;
                                                return j9;
                                            }
                                            j10 = -558586000294016L;
                                        }
                                    }
                                    j9 = j18 ^ j10;
                                    j14 = j17;
                                    this.currentByteBufferPos = j14;
                                    return j9;
                                }
                                j11 = 266354560;
                                j9 = j16 ^ j11;
                            }
                        }
                        j14 = j15;
                        this.currentByteBufferPos = j14;
                        return j9;
                    }
                    i9 = i10 ^ (-128);
                    j9 = i9;
                    this.currentByteBufferPos = j14;
                    return j9;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j9 = rawVarint32;
                long j10 = this.currentByteBufferLimit;
                long j11 = this.currentByteBufferPos;
                if (j9 <= j10 - j11) {
                    byte[] bArr = new byte[rawVarint32];
                    UnsafeUtil.copyMemory(j11, bArr, 0L, j9);
                    String str = new String(bArr, Internal.UTF_8);
                    this.currentByteBufferPos += j9;
                    return str;
                }
            }
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return new String(bArr2, Internal.UTF_8);
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j9 = rawVarint32;
                long j10 = this.currentByteBufferLimit;
                long j11 = this.currentByteBufferPos;
                if (j9 <= j10 - j11) {
                    String strDecodeUtf8 = Utf8.decodeUtf8(this.currentByteBuffer, (int) (j11 - this.currentByteBufferStartPos), rawVarint32);
                    this.currentByteBufferPos += j9;
                    return strDecodeUtf8;
                }
            }
            if (rawVarint32 >= 0 && rawVarint32 <= remaining()) {
                byte[] bArr = new byte[rawVarint32];
                readRawBytesTo(bArr, 0, rawVarint32);
                return Utf8.decodeUtf8(bArr, 0, rawVarint32);
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i9, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i9, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startOffset = (int) ((this.totalBytesRead + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0 || i9 > ((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                if (i9 >= 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            while (i9 > 0) {
                if (currentRemaining() == 0) {
                    getNextByteBuffer();
                }
                int iMin = Math.min(i9, (int) currentRemaining());
                i9 -= iMin;
                this.currentByteBufferPos += iMin;
            }
        }

        private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> iterable, int i9, boolean z8) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.totalBufferSize = i9;
            this.input = iterable;
            this.iterator = iterable.iterator();
            this.immutable = z8;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if (i9 != 0) {
                tryGetNextByteBuffer();
                return;
            }
            this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
            this.currentByteBufferPos = 0L;
            this.currentByteBufferStartPos = 0L;
            this.currentByteBufferLimit = 0L;
            this.currentAddress = 0L;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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
    }

    public static final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        public interface RefillCallback {
            void onRefill();
        }

        public class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos;

            private SkippedDataSink() {
                this.lastPos = StreamDecoder.this.pos;
            }

            public ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }

            @Override // com.google.protobuf.CodedInputStream.StreamDecoder.RefillCallback
            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }
        }

        private ByteString readBytesSlowPath(int i9) throws IOException {
            byte[] rawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i9);
            if (rawBytesSlowPathOneChunk != null) {
                return ByteString.copyFrom(rawBytesSlowPathOneChunk);
            }
            int i10 = this.pos;
            int i11 = this.bufferSize;
            int length = i11 - i10;
            this.totalBytesRetired += i11;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> rawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i9 - length);
            byte[] bArr = new byte[i9];
            System.arraycopy(this.buffer, i10, bArr, 0, length);
            for (byte[] bArr2 : rawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return ByteString.wrap(bArr);
        }

        private byte[] readRawBytesSlowPath(int i9, boolean z8) throws IOException {
            byte[] rawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i9);
            if (rawBytesSlowPathOneChunk != null) {
                return z8 ? (byte[]) rawBytesSlowPathOneChunk.clone() : rawBytesSlowPathOneChunk;
            }
            int i10 = this.pos;
            int i11 = this.bufferSize;
            int length = i11 - i10;
            this.totalBytesRetired += i11;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> rawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i9 - length);
            byte[] bArr = new byte[i9];
            System.arraycopy(this.buffer, i10, bArr, 0, length);
            for (byte[] bArr2 : rawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return bArr;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i9) throws IOException {
            if (i9 == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i10 = this.totalBytesRetired;
            int i11 = this.pos;
            int i12 = i10 + i11 + i9;
            if (i12 - this.sizeLimit > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            int i13 = this.currentLimit;
            if (i12 > i13) {
                skipRawBytes((i13 - i10) - i11);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i14 = this.bufferSize - i11;
            int i15 = i9 - i14;
            if (i15 >= 4096 && i15 > this.input.available()) {
                return null;
            }
            byte[] bArr = new byte[i9];
            System.arraycopy(this.buffer, this.pos, bArr, 0, i14);
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            while (i14 < i9) {
                int i16 = this.input.read(bArr, i14, i9 - i14);
                if (i16 == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                this.totalBytesRetired += i16;
                i14 += i16;
            }
            return bArr;
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int i9) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i9 > 0) {
                int iMin = Math.min(i9, 4096);
                byte[] bArr = new byte[iMin];
                int i10 = 0;
                while (i10 < iMin) {
                    int i11 = this.input.read(bArr, i10, iMin - i10);
                    if (i11 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += i11;
                    i10 += i11;
                }
                i9 -= iMin;
                arrayList.add(bArr);
            }
            return arrayList;
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
            if (tryRefillBuffer(i9)) {
                return;
            }
            if (i9 <= (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.sizeLimitExceeded();
        }

        private void skipRawBytesSlowPath(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i10 = this.totalBytesRetired;
            int i11 = this.pos;
            int i12 = i10 + i11 + i9;
            int i13 = this.currentLimit;
            if (i12 > i13) {
                skipRawBytes((i13 - i10) - i11);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i14 = 0;
            if (this.refillCallback == null) {
                this.totalBytesRetired = i10 + i11;
                int i15 = this.bufferSize - i11;
                this.bufferSize = 0;
                this.pos = 0;
                i14 = i15;
                while (i14 < i9) {
                    try {
                        long j9 = i9 - i14;
                        long jSkip = this.input.skip(j9);
                        if (jSkip < 0 || jSkip > j9) {
                            throw new IllegalStateException(this.input.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                        }
                        if (jSkip == 0) {
                            break;
                        } else {
                            i14 += (int) jSkip;
                        }
                    } finally {
                        this.totalBytesRetired += i14;
                        recomputeBufferSizeAfterLimit();
                    }
                }
            }
            if (i14 >= i9) {
                return;
            }
            int i16 = this.bufferSize;
            int i17 = i16 - this.pos;
            this.pos = i16;
            refillBuffer(1);
            while (true) {
                int i18 = i9 - i17;
                int i19 = this.bufferSize;
                if (i18 <= i19) {
                    this.pos = i18;
                    return;
                } else {
                    i17 += i19;
                    this.pos = i19;
                    refillBuffer(1);
                }
            }
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                byte[] bArr = this.buffer;
                int i10 = this.pos;
                this.pos = i10 + 1;
                if (bArr[i10] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
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
            int i10 = this.pos;
            if (i10 + i9 <= this.bufferSize) {
                throw new IllegalStateException("refillBuffer() called when " + i9 + " bytes were already available in buffer");
            }
            int i11 = this.sizeLimit;
            int i12 = this.totalBytesRetired;
            if (i9 > (i11 - i12) - i10 || i12 + i10 + i9 > this.currentLimit) {
                return false;
            }
            RefillCallback refillCallback = this.refillCallback;
            if (refillCallback != null) {
                refillCallback.onRefill();
            }
            int i13 = this.pos;
            if (i13 > 0) {
                int i14 = this.bufferSize;
                if (i14 > i13) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, i13, bArr, 0, i14 - i13);
                }
                this.totalBytesRetired += i13;
                this.bufferSize -= i13;
                this.pos = 0;
            }
            InputStream inputStream = this.input;
            byte[] bArr2 = this.buffer;
            int i15 = this.bufferSize;
            int i16 = inputStream.read(bArr2, i15, Math.min(bArr2.length - i15, (this.sizeLimit - this.totalBytesRetired) - i15));
            if (i16 == 0 || i16 < -1 || i16 > this.buffer.length) {
                throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + i16 + "\nThe InputStream implementation is buggy.");
            }
            if (i16 <= 0) {
                return false;
            }
            this.bufferSize += i16;
            recomputeBufferSizeAfterLimit();
            if (this.bufferSize >= i9) {
                return true;
            }
            return tryRefillBuffer(i9);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i9) throws InvalidProtocolBufferException {
            if (this.lastTag != i9) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z8) {
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i9 = this.currentLimit;
            if (i9 == Integer.MAX_VALUE) {
                return -1;
            }
            return i9 - (this.totalBytesRetired + this.pos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i9) {
            this.currentLimit = i9;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i10 = i9 + this.totalBytesRetired + this.pos;
            int i11 = this.currentLimit;
            if (i10 > i11) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = i10;
            recomputeBufferSizeAfterLimit();
            return i11;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            int rawVarint32 = readRawVarint32();
            int i9 = this.bufferSize;
            int i10 = this.pos;
            if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
                return readRawBytesSlowPath(rawVarint32, false);
            }
            byte[] bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i10, i10 + rawVarint32);
            this.pos += rawVarint32;
            return bArrCopyOfRange;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() {
            int rawVarint32 = readRawVarint32();
            int i9 = this.bufferSize;
            int i10 = this.pos;
            if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
                return rawVarint32 == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(rawVarint32, true));
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i10, i10 + rawVarint32));
            this.pos += rawVarint32;
            return byteBufferWrap;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() {
            int rawVarint32 = readRawVarint32();
            int i9 = this.bufferSize;
            int i10 = this.pos;
            if (rawVarint32 > i9 - i10 || rawVarint32 <= 0) {
                return rawVarint32 == 0 ? ByteString.EMPTY : readBytesSlowPath(rawVarint32);
            }
            ByteString byteStringCopyFrom = ByteString.copyFrom(this.buffer, i10, rawVarint32);
            this.pos += rawVarint32;
            return byteStringCopyFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            this.pos = i9 + 1;
            return bArr[i9];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i9) {
            int i10 = this.pos;
            if (i9 > this.bufferSize - i10 || i9 <= 0) {
                return readRawBytesSlowPath(i9, false);
            }
            int i11 = i9 + i10;
            this.pos = i11;
            return Arrays.copyOfRange(this.buffer, i10, i11);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (this.bufferSize - i9 < 4) {
                refillBuffer(4);
                i9 = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 4;
            return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (this.bufferSize - i9 < 8) {
                refillBuffer(8);
                i9 = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 8;
            return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int readRawVarint32() {
            int i9;
            int i10 = this.pos;
            int i11 = this.bufferSize;
            if (i11 != i10) {
                byte[] bArr = this.buffer;
                int i12 = i10 + 1;
                byte b9 = bArr[i10];
                if (b9 >= 0) {
                    this.pos = i12;
                    return b9;
                }
                if (i11 - i12 >= 9) {
                    int i13 = i12 + 1;
                    int i14 = b9 ^ (bArr[i12] << 7);
                    if (i14 < 0) {
                        i9 = i14 ^ (-128);
                    } else {
                        int i15 = i13 + 1;
                        int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                        if (i16 >= 0) {
                            i9 = i16 ^ 16256;
                        } else {
                            i13 = i15 + 1;
                            int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                            if (i17 < 0) {
                                i9 = i17 ^ (-2080896);
                            } else {
                                i15 = i13 + 1;
                                byte b10 = bArr[i13];
                                i9 = (i17 ^ (b10 << Ascii.f15383FS)) ^ 266354560;
                                if (b10 < 0) {
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
                            }
                        }
                        i13 = i15;
                    }
                    this.pos = i13;
                    return i9;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public long readRawVarint64() {
            long j9;
            long j10;
            long j11;
            int i9;
            int i10 = this.pos;
            int i11 = this.bufferSize;
            if (i11 != i10) {
                byte[] bArr = this.buffer;
                int i12 = i10 + 1;
                byte b9 = bArr[i10];
                if (b9 >= 0) {
                    this.pos = i12;
                    return b9;
                }
                if (i11 - i12 >= 9) {
                    int i13 = i12 + 1;
                    int i14 = b9 ^ (bArr[i12] << 7);
                    if (i14 >= 0) {
                        int i15 = i13 + 1;
                        int i16 = i14 ^ (bArr[i13] << Ascii.f15390SO);
                        if (i16 >= 0) {
                            i13 = i15;
                            j9 = i16 ^ 16256;
                        } else {
                            i13 = i15 + 1;
                            int i17 = i16 ^ (bArr[i15] << Ascii.NAK);
                            if (i17 < 0) {
                                i9 = i17 ^ (-2080896);
                            } else {
                                long j12 = i17;
                                int i18 = i13 + 1;
                                long j13 = j12 ^ (bArr[i13] << 28);
                                if (j13 >= 0) {
                                    j11 = 266354560;
                                } else {
                                    i13 = i18 + 1;
                                    long j14 = j13 ^ (bArr[i18] << 35);
                                    if (j14 < 0) {
                                        j10 = -34093383808L;
                                    } else {
                                        i18 = i13 + 1;
                                        j13 = j14 ^ (bArr[i13] << 42);
                                        if (j13 >= 0) {
                                            j11 = 4363953127296L;
                                        } else {
                                            i13 = i18 + 1;
                                            j14 = j13 ^ (bArr[i18] << 49);
                                            if (j14 < 0) {
                                                j10 = -558586000294016L;
                                            } else {
                                                int i19 = i13 + 1;
                                                long j15 = (j14 ^ (bArr[i13] << 56)) ^ 71499008037633920L;
                                                i13 = j15 < 0 ? i19 + 1 : i19;
                                                j9 = j15;
                                            }
                                        }
                                    }
                                    j9 = j14 ^ j10;
                                }
                                j9 = j13 ^ j11;
                                i13 = i18;
                            }
                        }
                        this.pos = i13;
                        return j9;
                    }
                    i9 = i14 ^ (-128);
                    j9 = i9;
                    this.pos = i13;
                    return j9;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i9 = this.bufferSize;
                int i10 = this.pos;
                if (rawVarint32 <= i9 - i10) {
                    String str = new String(this.buffer, i10, rawVarint32, Internal.UTF_8);
                    this.pos += rawVarint32;
                    return str;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(rawVarint32, false), Internal.UTF_8);
            }
            refillBuffer(rawVarint32);
            String str2 = new String(this.buffer, this.pos, rawVarint32, Internal.UTF_8);
            this.pos += rawVarint32;
            return str2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            byte[] rawBytesSlowPath;
            int rawVarint32 = readRawVarint32();
            int i9 = this.pos;
            int i10 = this.bufferSize;
            if (rawVarint32 <= i10 - i9 && rawVarint32 > 0) {
                rawBytesSlowPath = this.buffer;
                this.pos = i9 + rawVarint32;
            } else {
                if (rawVarint32 == 0) {
                    return "";
                }
                i9 = 0;
                if (rawVarint32 <= i10) {
                    refillBuffer(rawVarint32);
                    rawBytesSlowPath = this.buffer;
                    this.pos = rawVarint32 + 0;
                } else {
                    rawBytesSlowPath = readRawBytesSlowPath(rawVarint32, false);
                }
            }
            return Utf8.decodeUtf8(rawBytesSlowPath, i9, rawVarint32);
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i9, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i9, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i9) throws InvalidProtocolBufferException {
            int i10 = this.bufferSize;
            int i11 = this.pos;
            if (i9 > i10 - i11 || i9 < 0) {
                skipRawBytesSlowPath(i9);
            } else {
                this.pos = i11 + i9;
            }
        }

        private StreamDecoder(InputStream inputStream, int i9) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i9];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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
    }

    public static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        private int bufferPos(long j9) {
            return (int) (j9 - this.address);
        }

        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private void recomputeBufferSizeAfterLimit() {
            long j9 = this.limit + this.bufferSizeAfterLimit;
            this.limit = j9;
            int i9 = (int) (j9 - this.startPos);
            int i10 = this.currentLimit;
            if (i9 <= i10) {
                this.bufferSizeAfterLimit = 0;
                return;
            }
            int i11 = i9 - i10;
            this.bufferSizeAfterLimit = i11;
            this.limit = j9 - i11;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                long j9 = this.pos;
                this.pos = 1 + j9;
                if (UnsafeUtil.getByte(j9) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(long j9, long j10) {
            int iPosition = this.buffer.position();
            int iLimit = this.buffer.limit();
            try {
                try {
                    this.buffer.position(bufferPos(j9));
                    this.buffer.limit(bufferPos(j10));
                    return this.buffer.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.buffer.position(iPosition);
                this.buffer.limit(iLimit);
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i9) throws InvalidProtocolBufferException {
            if (this.lastTag != i9) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z8) {
            this.enableAliasing = z8;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i9 = this.currentLimit;
            if (i9 == Integer.MAX_VALUE) {
                return -1;
            }
            return i9 - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i9) {
            this.currentLimit = i9;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = i9 + getTotalBytesRead();
            int i10 = this.currentLimit;
            if (totalBytesRead > i10) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i10;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[rawVarint32];
                long j9 = rawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0L, j9);
                this.pos += j9;
                return ByteBuffer.wrap(bArr);
            }
            long j10 = this.pos;
            long j11 = rawVarint32;
            ByteBuffer byteBufferSlice = slice(j10, j10 + j11);
            this.pos += j11;
            return byteBufferSlice;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.immutable && this.enableAliasing) {
                long j9 = this.pos;
                long j10 = rawVarint32;
                ByteBuffer byteBufferSlice = slice(j9, j9 + j10);
                this.pos += j10;
                return ByteString.wrap(byteBufferSlice);
            }
            byte[] bArr = new byte[rawVarint32];
            long j11 = rawVarint32;
            UnsafeUtil.copyMemory(this.pos, bArr, 0L, j11);
            this.pos += j11;
            return ByteString.wrap(bArr);
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            long j9 = this.pos;
            if (j9 == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 1 + j9;
            return UnsafeUtil.getByte(j9);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0 || i9 > remaining()) {
                if (i9 > 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                if (i9 == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            byte[] bArr = new byte[i9];
            long j9 = this.pos;
            long j10 = i9;
            slice(j9, j9 + j10).get(bArr);
            this.pos += j10;
            return bArr;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            long j9 = this.pos;
            if (this.limit - j9 < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 4 + j9;
            return ((UnsafeUtil.getByte(j9 + 3) & UnsignedBytes.MAX_VALUE) << 24) | (UnsafeUtil.getByte(j9) & UnsignedBytes.MAX_VALUE) | ((UnsafeUtil.getByte(1 + j9) & UnsignedBytes.MAX_VALUE) << 8) | ((UnsafeUtil.getByte(2 + j9) & UnsignedBytes.MAX_VALUE) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            long j9 = this.pos;
            if (this.limit - j9 < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 8 + j9;
            return ((UnsafeUtil.getByte(j9 + 7) & 255) << 56) | (UnsafeUtil.getByte(j9) & 255) | ((UnsafeUtil.getByte(1 + j9) & 255) << 8) | ((UnsafeUtil.getByte(2 + j9) & 255) << 16) | ((UnsafeUtil.getByte(3 + j9) & 255) << 24) | ((UnsafeUtil.getByte(4 + j9) & 255) << 32) | ((UnsafeUtil.getByte(5 + j9) & 255) << 40) | ((UnsafeUtil.getByte(6 + j9) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int readRawVarint32() {
            int i9;
            long j9 = this.pos;
            if (this.limit != j9) {
                long j10 = j9 + 1;
                byte b9 = UnsafeUtil.getByte(j9);
                if (b9 >= 0) {
                    this.pos = j10;
                    return b9;
                }
                if (this.limit - j10 >= 9) {
                    long j11 = j10 + 1;
                    int i10 = b9 ^ (UnsafeUtil.getByte(j10) << 7);
                    if (i10 < 0) {
                        i9 = i10 ^ (-128);
                    } else {
                        long j12 = j11 + 1;
                        int i11 = i10 ^ (UnsafeUtil.getByte(j11) << Ascii.f15390SO);
                        if (i11 >= 0) {
                            i9 = i11 ^ 16256;
                        } else {
                            j11 = j12 + 1;
                            int i12 = i11 ^ (UnsafeUtil.getByte(j12) << Ascii.NAK);
                            if (i12 < 0) {
                                i9 = i12 ^ (-2080896);
                            } else {
                                j12 = j11 + 1;
                                byte b10 = UnsafeUtil.getByte(j11);
                                i9 = (i12 ^ (b10 << Ascii.f15383FS)) ^ 266354560;
                                if (b10 < 0) {
                                    j11 = j12 + 1;
                                    if (UnsafeUtil.getByte(j12) < 0) {
                                        j12 = j11 + 1;
                                        if (UnsafeUtil.getByte(j11) < 0) {
                                            j11 = j12 + 1;
                                            if (UnsafeUtil.getByte(j12) < 0) {
                                                j12 = j11 + 1;
                                                if (UnsafeUtil.getByte(j11) < 0) {
                                                    j11 = j12 + 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        j11 = j12;
                    }
                    this.pos = j11;
                    return i9;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() {
            long j9;
            long j10;
            long j11;
            int i9;
            long j12 = this.pos;
            if (this.limit != j12) {
                long j13 = j12 + 1;
                byte b9 = UnsafeUtil.getByte(j12);
                if (b9 >= 0) {
                    this.pos = j13;
                    return b9;
                }
                if (this.limit - j13 >= 9) {
                    long j14 = j13 + 1;
                    int i10 = b9 ^ (UnsafeUtil.getByte(j13) << 7);
                    if (i10 >= 0) {
                        long j15 = j14 + 1;
                        int i11 = i10 ^ (UnsafeUtil.getByte(j14) << Ascii.f15390SO);
                        if (i11 >= 0) {
                            j9 = i11 ^ 16256;
                        } else {
                            j14 = j15 + 1;
                            int i12 = i11 ^ (UnsafeUtil.getByte(j15) << Ascii.NAK);
                            if (i12 < 0) {
                                i9 = i12 ^ (-2080896);
                            } else {
                                j15 = j14 + 1;
                                long j16 = i12 ^ (UnsafeUtil.getByte(j14) << 28);
                                if (j16 < 0) {
                                    long j17 = j15 + 1;
                                    long j18 = j16 ^ (UnsafeUtil.getByte(j15) << 35);
                                    if (j18 < 0) {
                                        j10 = -34093383808L;
                                    } else {
                                        j15 = j17 + 1;
                                        j16 = j18 ^ (UnsafeUtil.getByte(j17) << 42);
                                        if (j16 >= 0) {
                                            j11 = 4363953127296L;
                                        } else {
                                            j17 = j15 + 1;
                                            j18 = j16 ^ (UnsafeUtil.getByte(j15) << 49);
                                            if (j18 >= 0) {
                                                j15 = j17 + 1;
                                                j9 = (j18 ^ (UnsafeUtil.getByte(j17) << 56)) ^ 71499008037633920L;
                                                if (j9 < 0) {
                                                    long j19 = 1 + j15;
                                                    if (UnsafeUtil.getByte(j15) >= 0) {
                                                        j14 = j19;
                                                    }
                                                }
                                                this.pos = j14;
                                                return j9;
                                            }
                                            j10 = -558586000294016L;
                                        }
                                    }
                                    j9 = j18 ^ j10;
                                    j14 = j17;
                                    this.pos = j14;
                                    return j9;
                                }
                                j11 = 266354560;
                                j9 = j16 ^ j11;
                            }
                        }
                        j14 = j15;
                        this.pos = j14;
                        return j9;
                    }
                    i9 = i10 ^ (-128);
                    j9 = i9;
                    this.pos = j14;
                    return j9;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return "";
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[rawVarint32];
            long j9 = rawVarint32;
            UnsafeUtil.copyMemory(this.pos, bArr, 0L, j9);
            String str = new String(bArr, Internal.UTF_8);
            this.pos += j9;
            return str;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                String strDecodeUtf8 = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), rawVarint32);
                this.pos += rawVarint32;
                return strDecodeUtf8;
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i9, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i9, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 >= 0 && i9 <= remaining()) {
                this.pos += i9;
            } else {
                if (i9 >= 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z8) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer);
            this.address = jAddressOffset;
            this.limit = byteBuffer.limit() + jAddressOffset;
            long jPosition = jAddressOffset + byteBuffer.position();
            this.pos = jPosition;
            this.startPos = jPosition;
            this.immutable = z8;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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

        @Override // com.google.protobuf.CodedInputStream
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
    }

    public static int decodeZigZag32(int i9) {
        return (-(i9 & 1)) ^ (i9 >>> 1);
    }

    public static long decodeZigZag64(long j9) {
        return (-(j9 & 1)) ^ (j9 >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
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

    public abstract void checkLastTagWas(int i9);

    public final void discardUnknownFields() {
        this.shouldDiscardUnknownFields = true;
    }

    public abstract void enableAliasing(boolean z8);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd();

    public abstract void popLimit(int i9);

    public abstract int pushLimit(int i9);

    public abstract boolean readBool();

    public abstract byte[] readByteArray();

    public abstract ByteBuffer readByteBuffer();

    public abstract ByteString readBytes();

    public abstract double readDouble();

    public abstract int readEnum();

    public abstract int readFixed32();

    public abstract long readFixed64();

    public abstract float readFloat();

    public abstract <T extends MessageLite> T readGroup(int i9, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite);

    public abstract void readGroup(int i9, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite);

    public abstract int readInt32();

    public abstract long readInt64();

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite);

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite);

    public abstract byte readRawByte();

    public abstract byte[] readRawBytes(int i9);

    public abstract int readRawLittleEndian32();

    public abstract long readRawLittleEndian64();

    public abstract int readRawVarint32();

    public abstract long readRawVarint64();

    public abstract long readRawVarint64SlowPath();

    public abstract int readSFixed32();

    public abstract long readSFixed64();

    public abstract int readSInt32();

    public abstract long readSInt64();

    public abstract String readString();

    public abstract String readStringRequireUtf8();

    public abstract int readTag();

    public abstract int readUInt32();

    public abstract long readUInt64();

    @Deprecated
    public abstract void readUnknownGroup(int i9, MessageLite.Builder builder);

    public abstract void resetSizeCounter();

    public final int setRecursionLimit(int i9) {
        if (i9 >= 0) {
            int i10 = this.recursionLimit;
            this.recursionLimit = i9;
            return i10;
        }
        throw new IllegalArgumentException("Recursion limit cannot be negative: " + i9);
    }

    public final int setSizeLimit(int i9) {
        if (i9 >= 0) {
            int i10 = this.sizeLimit;
            this.sizeLimit = i9;
            return i10;
        }
        throw new IllegalArgumentException("Size limit cannot be negative: " + i9);
    }

    public final boolean shouldDiscardUnknownFields() {
        return this.shouldDiscardUnknownFields;
    }

    public abstract boolean skipField(int i9);

    @Deprecated
    public abstract boolean skipField(int i9, CodedOutputStream codedOutputStream);

    public abstract void skipMessage();

    public abstract void skipMessage(CodedOutputStream codedOutputStream);

    public abstract void skipRawBytes(int i9);

    public final void unsetDiscardUnknownFields() {
        this.shouldDiscardUnknownFields = false;
    }

    private CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public static CodedInputStream newInstance(InputStream inputStream, int i9) {
        if (i9 > 0) {
            return inputStream == null ? newInstance(Internal.EMPTY_BYTE_ARRAY) : new StreamDecoder(inputStream, i9);
        }
        throw new IllegalArgumentException("bufferSize must be > 0");
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable) {
        if (!UnsafeDirectNioDecoder.isSupported()) {
            return newInstance(new IterableByteBufferInputStream(iterable));
        }
        return newInstance(iterable, false);
    }

    public static int readRawVarint32(InputStream inputStream) throws IOException {
        int i9 = inputStream.read();
        if (i9 != -1) {
            return readRawVarint32(i9, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable, boolean z8) {
        int i9 = 0;
        int iRemaining = 0;
        for (ByteBuffer byteBuffer : iterable) {
            iRemaining += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                i9 |= 1;
            } else {
                i9 = byteBuffer.isDirect() ? i9 | 2 : i9 | 4;
            }
        }
        if (i9 == 2) {
            return new IterableDirectByteBufferDecoder(iterable, iRemaining, z8);
        }
        return newInstance(new IterableByteBufferInputStream(iterable));
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i9, int i10) {
        return newInstance(bArr, i9, i10, false);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i9, int i10, boolean z8) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i9, i10, z8);
        try {
            arrayDecoder.pushLimit(i10);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z8) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z8);
        }
        if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z8);
        }
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, iRemaining, true);
    }
}
