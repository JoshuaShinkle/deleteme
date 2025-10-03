package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
abstract class BinaryReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;

    /* renamed from: com.google.protobuf.BinaryReader$1 */
    public static /* synthetic */ class C43811 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private BinaryReader() {
    }

    public /* synthetic */ BinaryReader(C43811 c43811) {
        this();
    }

    public static BinaryReader newInstance(ByteBuffer byteBuffer, boolean z8) {
        if (byteBuffer.hasArray()) {
            return new SafeHeapReader(byteBuffer, z8);
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    public abstract int getTotalBytesRead();

    @Override // com.google.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return false;
    }

    public static final class SafeHeapReader extends BinaryReader {
        private final byte[] buffer;
        private final boolean bufferIsImmutable;
        private int endGroupTag;
        private final int initialPos;
        private int limit;
        private int pos;
        private int tag;

        public SafeHeapReader(ByteBuffer byteBuffer, boolean z8) {
            super(null);
            this.bufferIsImmutable = z8;
            this.buffer = byteBuffer.array();
            int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            this.pos = iArrayOffset;
            this.initialPos = iArrayOffset;
            this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
        }

        private boolean isAtEnd() {
            return this.pos == this.limit;
        }

        private byte readByte() throws InvalidProtocolBufferException {
            int i9 = this.pos;
            if (i9 == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i9 + 1;
            return bArr[i9];
        }

        private Object readField(WireFormat.FieldType fieldType, Class<?> cls, ExtensionRegistryLite extensionRegistryLite) {
            switch (C43811.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
                case 1:
                    return Boolean.valueOf(readBool());
                case 2:
                    return readBytes();
                case 3:
                    return Double.valueOf(readDouble());
                case 4:
                    return Integer.valueOf(readEnum());
                case 5:
                    return Integer.valueOf(readFixed32());
                case 6:
                    return Long.valueOf(readFixed64());
                case 7:
                    return Float.valueOf(readFloat());
                case 8:
                    return Integer.valueOf(readInt32());
                case 9:
                    return Long.valueOf(readInt64());
                case 10:
                    return readMessage(cls, extensionRegistryLite);
                case 11:
                    return Integer.valueOf(readSFixed32());
                case 12:
                    return Long.valueOf(readSFixed64());
                case 13:
                    return Integer.valueOf(readSInt32());
                case 14:
                    return Long.valueOf(readSInt64());
                case 15:
                    return readStringRequireUtf8();
                case 16:
                    return Integer.valueOf(readUInt32());
                case 17:
                    return Long.valueOf(readUInt64());
                default:
                    throw new RuntimeException("unsupported field type.");
            }
        }

        private int readLittleEndian32() throws InvalidProtocolBufferException {
            requireBytes(4);
            return readLittleEndian32_NoCheck();
        }

        private int readLittleEndian32_NoCheck() {
            int i9 = this.pos;
            byte[] bArr = this.buffer;
            this.pos = i9 + 4;
            return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
        }

        private long readLittleEndian64() throws InvalidProtocolBufferException {
            requireBytes(8);
            return readLittleEndian64_NoCheck();
        }

        private long readLittleEndian64_NoCheck() {
            int i9 = this.pos;
            byte[] bArr = this.buffer;
            this.pos = i9 + 8;
            return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
        }

        private int readVarint32() throws InvalidProtocolBufferException {
            int i9;
            int i10 = this.pos;
            int i11 = this.limit;
            if (i11 == i10) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.pos = i12;
                return b9;
            }
            if (i11 - i12 < 9) {
                return (int) readVarint64SlowPath();
            }
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
                                            if (bArr[i15] < 0) {
                                                throw InvalidProtocolBufferException.malformedVarint();
                                            }
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

        private long readVarint64SlowPath() throws InvalidProtocolBufferException {
            long j9 = 0;
            for (int i9 = 0; i9 < 64; i9 += 7) {
                j9 |= (r3 & Ascii.DEL) << i9;
                if ((readByte() & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return j9;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void requireBytes(int i9) throws InvalidProtocolBufferException {
            if (i9 < 0 || i9 > this.limit - this.pos) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void requirePosition(int i9) throws InvalidProtocolBufferException {
            if (this.pos != i9) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void requireWireType(int i9) throws InvalidProtocolBufferException.InvalidWireTypeException {
            if (WireFormat.getTagWireType(this.tag) != i9) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        private void skipBytes(int i9) throws InvalidProtocolBufferException {
            requireBytes(i9);
            this.pos += i9;
        }

        private void skipGroup() throws InvalidProtocolBufferException {
            int i9 = this.endGroupTag;
            this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
            while (getFieldNumber() != Integer.MAX_VALUE && skipField()) {
            }
            if (this.tag != this.endGroupTag) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            this.endGroupTag = i9;
        }

        private void skipVarint() throws InvalidProtocolBufferException {
            int i9 = this.limit;
            int i10 = this.pos;
            if (i9 - i10 >= 10) {
                byte[] bArr = this.buffer;
                int i11 = 0;
                while (i11 < 10) {
                    int i12 = i10 + 1;
                    if (bArr[i10] >= 0) {
                        this.pos = i12;
                        return;
                    } else {
                        i11++;
                        i10 = i12;
                    }
                }
            }
            skipVarintSlowPath();
        }

        private void skipVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i9 = 0; i9 < 10; i9++) {
                if (readByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void verifyPackedFixed32Length(int i9) throws InvalidProtocolBufferException {
            requireBytes(i9);
            if ((i9 & 3) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        private void verifyPackedFixed64Length(int i9) throws InvalidProtocolBufferException {
            requireBytes(i9);
            if ((i9 & 7) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        @Override // com.google.protobuf.Reader
        public int getFieldNumber() throws InvalidProtocolBufferException {
            if (isAtEnd()) {
                return Integer.MAX_VALUE;
            }
            int varint32 = readVarint32();
            this.tag = varint32;
            if (varint32 == this.endGroupTag) {
                return Integer.MAX_VALUE;
            }
            return WireFormat.getTagFieldNumber(varint32);
        }

        @Override // com.google.protobuf.Reader
        public int getTag() {
            return this.tag;
        }

        @Override // com.google.protobuf.BinaryReader
        public int getTotalBytesRead() {
            return this.pos - this.initialPos;
        }

        @Override // com.google.protobuf.Reader
        public boolean readBool() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint32() != 0;
        }

        @Override // com.google.protobuf.Reader
        public void readBoolList(List<Boolean> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof BooleanArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                    int varint32 = this.pos + readVarint32();
                    while (this.pos < varint32) {
                        list.add(Boolean.valueOf(readVarint32() != 0));
                    }
                    requirePosition(varint32);
                    return;
                }
                do {
                    list.add(Boolean.valueOf(readBool()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            BooleanArrayList booleanArrayList = (BooleanArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint322 = this.pos + readVarint32();
                while (this.pos < varint322) {
                    booleanArrayList.addBoolean(readVarint32() != 0);
                }
                requirePosition(varint322);
                return;
            }
            do {
                booleanArrayList.addBoolean(readBool());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public ByteString readBytes() throws InvalidProtocolBufferException {
            requireWireType(2);
            int varint32 = readVarint32();
            if (varint32 == 0) {
                return ByteString.EMPTY;
            }
            requireBytes(varint32);
            ByteString byteStringWrap = this.bufferIsImmutable ? ByteString.wrap(this.buffer, this.pos, varint32) : ByteString.copyFrom(this.buffer, this.pos, varint32);
            this.pos += varint32;
            return byteStringWrap;
        }

        @Override // com.google.protobuf.Reader
        public void readBytesList(List<ByteString> list) throws InvalidProtocolBufferException.InvalidWireTypeException {
            int i9;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(readBytes());
                if (isAtEnd()) {
                    return;
                } else {
                    i9 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i9;
        }

        @Override // com.google.protobuf.Reader
        public double readDouble() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(1);
            return Double.longBitsToDouble(readLittleEndian64());
        }

        @Override // com.google.protobuf.Reader
        public void readDoubleList(List<Double> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof DoubleArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Double.valueOf(readDouble()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = readVarint32();
                verifyPackedFixed64Length(varint32);
                int i11 = this.pos + varint32;
                while (this.pos < i11) {
                    list.add(Double.valueOf(Double.longBitsToDouble(readLittleEndian64_NoCheck())));
                }
                return;
            }
            DoubleArrayList doubleArrayList = (DoubleArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    doubleArrayList.addDouble(readDouble());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = readVarint32();
            verifyPackedFixed64Length(varint322);
            int i12 = this.pos + varint322;
            while (this.pos < i12) {
                doubleArrayList.addDouble(Double.longBitsToDouble(readLittleEndian64_NoCheck()));
            }
        }

        @Override // com.google.protobuf.Reader
        public int readEnum() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.protobuf.Reader
        public void readEnumList(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                    int varint32 = this.pos + readVarint32();
                    while (this.pos < varint32) {
                        list.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readEnum()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint322 = this.pos + readVarint32();
                while (this.pos < varint322) {
                    intArrayList.addInt(readVarint32());
                }
                return;
            }
            do {
                intArrayList.addInt(readEnum());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public int readFixed32() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(5);
            return readLittleEndian32();
        }

        @Override // com.google.protobuf.Reader
        public void readFixed32List(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int varint32 = readVarint32();
                    verifyPackedFixed32Length(varint32);
                    int i11 = this.pos + varint32;
                    while (this.pos < i11) {
                        list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                }
                if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    list.add(Integer.valueOf(readFixed32()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int varint322 = readVarint32();
                verifyPackedFixed32Length(varint322);
                int i12 = this.pos + varint322;
                while (this.pos < i12) {
                    intArrayList.addInt(readLittleEndian32_NoCheck());
                }
                return;
            }
            if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(readFixed32());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public long readFixed64() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(1);
            return readLittleEndian64();
        }

        @Override // com.google.protobuf.Reader
        public void readFixed64List(List<Long> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Long.valueOf(readFixed64()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = readVarint32();
                verifyPackedFixed64Length(varint32);
                int i11 = this.pos + varint32;
                while (this.pos < i11) {
                    list.add(Long.valueOf(readLittleEndian64_NoCheck()));
                }
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    longArrayList.addLong(readFixed64());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = readVarint32();
            verifyPackedFixed64Length(varint322);
            int i12 = this.pos + varint322;
            while (this.pos < i12) {
                longArrayList.addLong(readLittleEndian64_NoCheck());
            }
        }

        @Override // com.google.protobuf.Reader
        public float readFloat() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(5);
            return Float.intBitsToFloat(readLittleEndian32());
        }

        @Override // com.google.protobuf.Reader
        public void readFloatList(List<Float> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof FloatArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int varint32 = readVarint32();
                    verifyPackedFixed32Length(varint32);
                    int i11 = this.pos + varint32;
                    while (this.pos < i11) {
                        list.add(Float.valueOf(Float.intBitsToFloat(readLittleEndian32_NoCheck())));
                    }
                    return;
                }
                if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            FloatArrayList floatArrayList = (FloatArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int varint322 = readVarint32();
                verifyPackedFixed32Length(varint322);
                int i12 = this.pos + varint322;
                while (this.pos < i12) {
                    floatArrayList.addFloat(Float.intBitsToFloat(readLittleEndian32_NoCheck()));
                }
                return;
            }
            if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                floatArrayList.addFloat(readFloat());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public <T> T readGroup(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(3);
            return (T) readGroup(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(3);
            return (T) readGroup(schema, extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public <T> void readGroupList(List<T> list, Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            readGroupList(list, Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public int readInt32() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.protobuf.Reader
        public void readInt32List(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        list.add(Integer.valueOf(readInt32()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = this.pos + readVarint32();
                while (this.pos < varint32) {
                    list.add(Integer.valueOf(readVarint32()));
                }
                requirePosition(varint32);
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    intArrayList.addInt(readInt32());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = this.pos + readVarint32();
            while (this.pos < varint322) {
                intArrayList.addInt(readVarint32());
            }
            requirePosition(varint322);
        }

        @Override // com.google.protobuf.Reader
        public long readInt64() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint64();
        }

        @Override // com.google.protobuf.Reader
        public void readInt64List(List<Long> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        list.add(Long.valueOf(readInt64()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = this.pos + readVarint32();
                while (this.pos < varint32) {
                    list.add(Long.valueOf(readVarint64()));
                }
                requirePosition(varint32);
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    longArrayList.addLong(readInt64());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = this.pos + readVarint32();
            while (this.pos < varint322) {
                longArrayList.addLong(readVarint64());
            }
            requirePosition(varint322);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.Reader
        public <K, V> void readMap(Map<K, V> map, MapEntryLite.Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            requireWireType(2);
            int varint32 = readVarint32();
            requireBytes(varint32);
            int i9 = this.limit;
            this.limit = this.pos + varint32;
            try {
                Object field = metadata.defaultKey;
                Object field2 = metadata.defaultValue;
                while (true) {
                    int fieldNumber = getFieldNumber();
                    if (fieldNumber == Integer.MAX_VALUE) {
                        map.put(field, field2);
                        return;
                    }
                    if (fieldNumber == 1) {
                        field = readField(metadata.keyType, null, null);
                    } else if (fieldNumber != 2) {
                        try {
                            if (!skipField()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                            if (!skipField()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        }
                    } else {
                        field2 = readField(metadata.valueType, metadata.defaultValue.getClass(), extensionRegistryLite);
                    }
                }
            } finally {
                this.limit = i9;
            }
        }

        @Override // com.google.protobuf.Reader
        public <T> T readMessage(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(2);
            return (T) readMessage(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(2);
            return (T) readMessage(schema, extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public <T> void readMessageList(List<T> list, Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            readMessageList(list, Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.protobuf.Reader
        public int readSFixed32() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(5);
            return readLittleEndian32();
        }

        @Override // com.google.protobuf.Reader
        public void readSFixed32List(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int varint32 = readVarint32();
                    verifyPackedFixed32Length(varint32);
                    int i11 = this.pos + varint32;
                    while (this.pos < i11) {
                        list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                }
                if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                do {
                    list.add(Integer.valueOf(readSFixed32()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int varint322 = readVarint32();
                verifyPackedFixed32Length(varint322);
                int i12 = this.pos + varint322;
                while (this.pos < i12) {
                    intArrayList.addInt(readLittleEndian32_NoCheck());
                }
                return;
            }
            if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(readSFixed32());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public long readSFixed64() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(1);
            return readLittleEndian64();
        }

        @Override // com.google.protobuf.Reader
        public void readSFixed64List(List<Long> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Long.valueOf(readSFixed64()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = readVarint32();
                verifyPackedFixed64Length(varint32);
                int i11 = this.pos + varint32;
                while (this.pos < i11) {
                    list.add(Long.valueOf(readLittleEndian64_NoCheck()));
                }
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    longArrayList.addLong(readSFixed64());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = readVarint32();
            verifyPackedFixed64Length(varint322);
            int i12 = this.pos + varint322;
            while (this.pos < i12) {
                longArrayList.addLong(readLittleEndian64_NoCheck());
            }
        }

        @Override // com.google.protobuf.Reader
        public int readSInt32() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return CodedInputStream.decodeZigZag32(readVarint32());
        }

        @Override // com.google.protobuf.Reader
        public void readSInt32List(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                    int varint32 = this.pos + readVarint32();
                    while (this.pos < varint32) {
                        list.add(Integer.valueOf(CodedInputStream.decodeZigZag32(readVarint32())));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readSInt32()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint322 = this.pos + readVarint32();
                while (this.pos < varint322) {
                    intArrayList.addInt(CodedInputStream.decodeZigZag32(readVarint32()));
                }
                return;
            }
            do {
                intArrayList.addInt(readSInt32());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public long readSInt64() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return CodedInputStream.decodeZigZag64(readVarint64());
        }

        @Override // com.google.protobuf.Reader
        public void readSInt64List(List<Long> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                    int varint32 = this.pos + readVarint32();
                    while (this.pos < varint32) {
                        list.add(Long.valueOf(CodedInputStream.decodeZigZag64(readVarint64())));
                    }
                    return;
                }
                do {
                    list.add(Long.valueOf(readSInt64()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint322 = this.pos + readVarint32();
                while (this.pos < varint322) {
                    longArrayList.addLong(CodedInputStream.decodeZigZag64(readVarint64()));
                }
                return;
            }
            do {
                longArrayList.addLong(readSInt64());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public String readString() {
            return readStringInternal(false);
        }

        public String readStringInternal(boolean z8) throws InvalidProtocolBufferException {
            requireWireType(2);
            int varint32 = readVarint32();
            if (varint32 == 0) {
                return "";
            }
            requireBytes(varint32);
            if (z8) {
                byte[] bArr = this.buffer;
                int i9 = this.pos;
                if (!Utf8.isValidUtf8(bArr, i9, i9 + varint32)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            String str = new String(this.buffer, this.pos, varint32, Internal.UTF_8);
            this.pos += varint32;
            return str;
        }

        @Override // com.google.protobuf.Reader
        public void readStringList(List<String> list) throws InvalidProtocolBufferException.InvalidWireTypeException {
            readStringListInternal(list, false);
        }

        public void readStringListInternal(List<String> list, boolean z8) throws InvalidProtocolBufferException.InvalidWireTypeException {
            int i9;
            int i10;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            if (!(list instanceof LazyStringList) || z8) {
                do {
                    list.add(readStringInternal(z8));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            LazyStringList lazyStringList = (LazyStringList) list;
            do {
                lazyStringList.add(readBytes());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public void readStringListRequireUtf8(List<String> list) throws InvalidProtocolBufferException.InvalidWireTypeException {
            readStringListInternal(list, true);
        }

        @Override // com.google.protobuf.Reader
        public String readStringRequireUtf8() {
            return readStringInternal(true);
        }

        @Override // com.google.protobuf.Reader
        public int readUInt32() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.protobuf.Reader
        public void readUInt32List(List<Integer> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                    int varint32 = this.pos + readVarint32();
                    while (this.pos < varint32) {
                        list.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readUInt32()));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i9;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint322 = this.pos + readVarint32();
                while (this.pos < varint322) {
                    intArrayList.addInt(readVarint32());
                }
                return;
            }
            do {
                intArrayList.addInt(readUInt32());
                if (isAtEnd()) {
                    return;
                } else {
                    i10 = this.pos;
                }
            } while (readVarint32() == this.tag);
            this.pos = i10;
        }

        @Override // com.google.protobuf.Reader
        public long readUInt64() throws InvalidProtocolBufferException.InvalidWireTypeException {
            requireWireType(0);
            return readVarint64();
        }

        @Override // com.google.protobuf.Reader
        public void readUInt64List(List<Long> list) throws InvalidProtocolBufferException {
            int i9;
            int i10;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        list.add(Long.valueOf(readUInt64()));
                        if (isAtEnd()) {
                            return;
                        } else {
                            i9 = this.pos;
                        }
                    } while (readVarint32() == this.tag);
                    this.pos = i9;
                    return;
                }
                if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                int varint32 = this.pos + readVarint32();
                while (this.pos < varint32) {
                    list.add(Long.valueOf(readVarint64()));
                }
                requirePosition(varint32);
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    longArrayList.addLong(readUInt64());
                    if (isAtEnd()) {
                        return;
                    } else {
                        i10 = this.pos;
                    }
                } while (readVarint32() == this.tag);
                this.pos = i10;
                return;
            }
            if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int varint322 = this.pos + readVarint32();
            while (this.pos < varint322) {
                longArrayList.addLong(readVarint64());
            }
            requirePosition(varint322);
        }

        public long readVarint64() throws InvalidProtocolBufferException {
            long j9;
            long j10;
            long j11;
            int i9;
            int i10 = this.pos;
            int i11 = this.limit;
            if (i11 == i10) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            int i12 = i10 + 1;
            byte b9 = bArr[i10];
            if (b9 >= 0) {
                this.pos = i12;
                return b9;
            }
            if (i11 - i12 < 9) {
                return readVarint64SlowPath();
            }
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
                                        if (j15 < 0) {
                                            i13 = i19 + 1;
                                            if (bArr[i19] < 0) {
                                                throw InvalidProtocolBufferException.malformedVarint();
                                            }
                                        } else {
                                            i13 = i19;
                                        }
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

        @Override // com.google.protobuf.Reader
        public boolean skipField() throws InvalidProtocolBufferException {
            int i9;
            if (isAtEnd() || (i9 = this.tag) == this.endGroupTag) {
                return false;
            }
            int tagWireType = WireFormat.getTagWireType(i9);
            if (tagWireType == 0) {
                skipVarint();
                return true;
            }
            if (tagWireType == 1) {
                skipBytes(8);
                return true;
            }
            if (tagWireType == 2) {
                skipBytes(readVarint32());
                return true;
            }
            if (tagWireType == 3) {
                skipGroup();
                return true;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            skipBytes(4);
            return true;
        }

        private <T> T readGroup(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            int i9 = this.endGroupTag;
            this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
            try {
                T tNewInstance = schema.newInstance();
                schema.mergeFrom(tNewInstance, this, extensionRegistryLite);
                schema.makeImmutable(tNewInstance);
                if (this.tag == this.endGroupTag) {
                    return tNewInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.endGroupTag = i9;
            }
        }

        private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int varint32 = readVarint32();
            requireBytes(varint32);
            int i9 = this.limit;
            int i10 = this.pos + varint32;
            this.limit = i10;
            try {
                T tNewInstance = schema.newInstance();
                schema.mergeFrom(tNewInstance, this, extensionRegistryLite);
                schema.makeImmutable(tNewInstance);
                if (this.pos == i10) {
                    return tNewInstance;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } finally {
                this.limit = i9;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.Reader
        public <T> void readGroupList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            int i9;
            if (WireFormat.getTagWireType(this.tag) == 3) {
                int i10 = this.tag;
                do {
                    list.add(readGroup(schema, extensionRegistryLite));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == i10);
                this.pos = i9;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.Reader
        public <T> void readMessageList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException.InvalidWireTypeException {
            int i9;
            if (WireFormat.getTagWireType(this.tag) == 2) {
                int i10 = this.tag;
                do {
                    list.add(readMessage(schema, extensionRegistryLite));
                    if (isAtEnd()) {
                        return;
                    } else {
                        i9 = this.pos;
                    }
                } while (readVarint32() == i10);
                this.pos = i9;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }
}
