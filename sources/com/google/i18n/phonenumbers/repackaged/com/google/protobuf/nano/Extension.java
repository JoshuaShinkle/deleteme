package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.ExtendableMessageNano;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Extension<M extends ExtendableMessageNano<M>, T> {
    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BYTES = 12;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_ENUM = 14;
    public static final int TYPE_FIXED32 = 7;
    public static final int TYPE_FIXED64 = 6;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_GROUP = 10;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_MESSAGE = 11;
    public static final int TYPE_SFIXED32 = 15;
    public static final int TYPE_SFIXED64 = 16;
    public static final int TYPE_SINT32 = 17;
    public static final int TYPE_SINT64 = 18;
    public static final int TYPE_STRING = 9;
    public static final int TYPE_UINT32 = 13;
    public static final int TYPE_UINT64 = 4;
    protected final Class<T> clazz;
    protected final boolean repeated;
    public final int tag;
    protected final int type;

    public static class PrimitiveExtension<M extends ExtendableMessageNano<M>, T> extends Extension<M, T> {
        private final int nonPackedTag;
        private final int packedTag;

        public PrimitiveExtension(int i9, Class<T> cls, int i10, boolean z8, int i11, int i12) {
            super(i9, cls, i10, z8);
            this.nonPackedTag = i11;
            this.packedTag = i12;
        }

        private int computePackedDataSize(Object obj) {
            int iComputeInt64SizeNoTag;
            int length = Array.getLength(obj);
            int i9 = 0;
            switch (this.type) {
                case 1:
                case 6:
                case 16:
                    return length * 8;
                case 2:
                case 7:
                case 15:
                    return length * 4;
                case 3:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeInt64SizeNoTag(Array.getLong(obj, i9));
                        i9++;
                    }
                    break;
                case 4:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeUInt64SizeNoTag(Array.getLong(obj, i9));
                        i9++;
                    }
                    break;
                case 5:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(Array.getInt(obj, i9));
                        i9++;
                    }
                    break;
                case 8:
                    return length;
                case 9:
                case 10:
                case 11:
                case 12:
                default:
                    int i10 = this.type;
                    StringBuilder sb = new StringBuilder(40);
                    sb.append("Unexpected non-packable type ");
                    sb.append(i10);
                    throw new IllegalArgumentException(sb.toString());
                case 13:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeUInt32SizeNoTag(Array.getInt(obj, i9));
                        i9++;
                    }
                    break;
                case 14:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeEnumSizeNoTag(Array.getInt(obj, i9));
                        i9++;
                    }
                    break;
                case 17:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeSInt32SizeNoTag(Array.getInt(obj, i9));
                        i9++;
                    }
                    break;
                case 18:
                    iComputeInt64SizeNoTag = 0;
                    while (i9 < length) {
                        iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeSInt64SizeNoTag(Array.getLong(obj, i9));
                        i9++;
                    }
                    break;
            }
            return iComputeInt64SizeNoTag;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public int computeRepeatedSerializedSize(Object obj) {
            int i9 = this.tag;
            if (i9 == this.nonPackedTag) {
                return super.computeRepeatedSerializedSize(obj);
            }
            if (i9 == this.packedTag) {
                int iComputePackedDataSize = computePackedDataSize(obj);
                return iComputePackedDataSize + CodedOutputByteBufferNano.computeRawVarint32Size(iComputePackedDataSize) + CodedOutputByteBufferNano.computeRawVarint32Size(this.tag);
            }
            int i10 = this.tag;
            int i11 = this.nonPackedTag;
            int i12 = this.packedTag;
            StringBuilder sb = new StringBuilder(124);
            sb.append("Unexpected repeated extension tag ");
            sb.append(i10);
            sb.append(", unequal to both non-packed variant ");
            sb.append(i11);
            sb.append(" and packed variant ");
            sb.append(i12);
            throw new IllegalArgumentException(sb.toString());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public final int computeSingularSerializedSize(Object obj) {
            int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
            switch (this.type) {
                case 1:
                    return CodedOutputByteBufferNano.computeDoubleSize(tagFieldNumber, ((Double) obj).doubleValue());
                case 2:
                    return CodedOutputByteBufferNano.computeFloatSize(tagFieldNumber, ((Float) obj).floatValue());
                case 3:
                    return CodedOutputByteBufferNano.computeInt64Size(tagFieldNumber, ((Long) obj).longValue());
                case 4:
                    return CodedOutputByteBufferNano.computeUInt64Size(tagFieldNumber, ((Long) obj).longValue());
                case 5:
                    return CodedOutputByteBufferNano.computeInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 6:
                    return CodedOutputByteBufferNano.computeFixed64Size(tagFieldNumber, ((Long) obj).longValue());
                case 7:
                    return CodedOutputByteBufferNano.computeFixed32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 8:
                    return CodedOutputByteBufferNano.computeBoolSize(tagFieldNumber, ((Boolean) obj).booleanValue());
                case 9:
                    return CodedOutputByteBufferNano.computeStringSize(tagFieldNumber, (String) obj);
                case 10:
                case 11:
                default:
                    int i9 = this.type;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i9);
                    throw new IllegalArgumentException(sb.toString());
                case 12:
                    return CodedOutputByteBufferNano.computeBytesSize(tagFieldNumber, (byte[]) obj);
                case 13:
                    return CodedOutputByteBufferNano.computeUInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 14:
                    return CodedOutputByteBufferNano.computeEnumSize(tagFieldNumber, ((Integer) obj).intValue());
                case 15:
                    return CodedOutputByteBufferNano.computeSFixed32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 16:
                    return CodedOutputByteBufferNano.computeSFixed64Size(tagFieldNumber, ((Long) obj).longValue());
                case 17:
                    return CodedOutputByteBufferNano.computeSInt32Size(tagFieldNumber, ((Integer) obj).intValue());
                case 18:
                    return CodedOutputByteBufferNano.computeSInt64Size(tagFieldNumber, ((Long) obj).longValue());
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public Object readData(CodedInputByteBufferNano codedInputByteBufferNano) {
            try {
                switch (this.type) {
                    case 1:
                        return Double.valueOf(codedInputByteBufferNano.readDouble());
                    case 2:
                        return Float.valueOf(codedInputByteBufferNano.readFloat());
                    case 3:
                        return Long.valueOf(codedInputByteBufferNano.readInt64());
                    case 4:
                        return Long.valueOf(codedInputByteBufferNano.readUInt64());
                    case 5:
                        return Integer.valueOf(codedInputByteBufferNano.readInt32());
                    case 6:
                        return Long.valueOf(codedInputByteBufferNano.readFixed64());
                    case 7:
                        return Integer.valueOf(codedInputByteBufferNano.readFixed32());
                    case 8:
                        return Boolean.valueOf(codedInputByteBufferNano.readBool());
                    case 9:
                        return codedInputByteBufferNano.readString();
                    case 10:
                    case 11:
                    default:
                        int i9 = this.type;
                        StringBuilder sb = new StringBuilder(24);
                        sb.append("Unknown type ");
                        sb.append(i9);
                        throw new IllegalArgumentException(sb.toString());
                    case 12:
                        return codedInputByteBufferNano.readBytes();
                    case 13:
                        return Integer.valueOf(codedInputByteBufferNano.readUInt32());
                    case 14:
                        return Integer.valueOf(codedInputByteBufferNano.readEnum());
                    case 15:
                        return Integer.valueOf(codedInputByteBufferNano.readSFixed32());
                    case 16:
                        return Long.valueOf(codedInputByteBufferNano.readSFixed64());
                    case 17:
                        return Integer.valueOf(codedInputByteBufferNano.readSInt32());
                    case 18:
                        return Long.valueOf(codedInputByteBufferNano.readSInt64());
                }
            } catch (IOException e9) {
                throw new IllegalArgumentException("Error reading extension field", e9);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public void readDataInto(UnknownFieldData unknownFieldData, List<Object> list) {
            if (unknownFieldData.tag == this.nonPackedTag) {
                list.add(readData(CodedInputByteBufferNano.newInstance(unknownFieldData.bytes)));
                return;
            }
            CodedInputByteBufferNano codedInputByteBufferNanoNewInstance = CodedInputByteBufferNano.newInstance(unknownFieldData.bytes);
            try {
                codedInputByteBufferNanoNewInstance.pushLimit(codedInputByteBufferNanoNewInstance.readRawVarint32());
                while (!codedInputByteBufferNanoNewInstance.isAtEnd()) {
                    list.add(readData(codedInputByteBufferNanoNewInstance));
                }
            } catch (IOException e9) {
                throw new IllegalArgumentException("Error reading extension field", e9);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public void writeRepeatedData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
            int i9 = this.tag;
            if (i9 == this.nonPackedTag) {
                super.writeRepeatedData(obj, codedOutputByteBufferNano);
                return;
            }
            if (i9 != this.packedTag) {
                int i10 = this.tag;
                int i11 = this.nonPackedTag;
                int i12 = this.packedTag;
                StringBuilder sb = new StringBuilder(124);
                sb.append("Unexpected repeated extension tag ");
                sb.append(i10);
                sb.append(", unequal to both non-packed variant ");
                sb.append(i11);
                sb.append(" and packed variant ");
                sb.append(i12);
                throw new IllegalArgumentException(sb.toString());
            }
            int length = Array.getLength(obj);
            int iComputePackedDataSize = computePackedDataSize(obj);
            try {
                codedOutputByteBufferNano.writeRawVarint32(this.tag);
                codedOutputByteBufferNano.writeRawVarint32(iComputePackedDataSize);
                int i13 = 0;
                switch (this.type) {
                    case 1:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeDoubleNoTag(Array.getDouble(obj, i13));
                            i13++;
                        }
                        return;
                    case 2:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeFloatNoTag(Array.getFloat(obj, i13));
                            i13++;
                        }
                        return;
                    case 3:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeInt64NoTag(Array.getLong(obj, i13));
                            i13++;
                        }
                        return;
                    case 4:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeUInt64NoTag(Array.getLong(obj, i13));
                            i13++;
                        }
                        return;
                    case 5:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeInt32NoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 6:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeFixed64NoTag(Array.getLong(obj, i13));
                            i13++;
                        }
                        return;
                    case 7:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeFixed32NoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 8:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeBoolNoTag(Array.getBoolean(obj, i13));
                            i13++;
                        }
                        return;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    default:
                        int i14 = this.type;
                        StringBuilder sb2 = new StringBuilder(27);
                        sb2.append("Unpackable type ");
                        sb2.append(i14);
                        throw new IllegalArgumentException(sb2.toString());
                    case 13:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeUInt32NoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 14:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeEnumNoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 15:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeSFixed32NoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 16:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeSFixed64NoTag(Array.getLong(obj, i13));
                            i13++;
                        }
                        return;
                    case 17:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeSInt32NoTag(Array.getInt(obj, i13));
                            i13++;
                        }
                        return;
                    case 18:
                        while (i13 < length) {
                            codedOutputByteBufferNano.writeSInt64NoTag(Array.getLong(obj, i13));
                            i13++;
                        }
                        return;
                }
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.Extension
        public final void writeSingularData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) {
            try {
                codedOutputByteBufferNano.writeRawVarint32(this.tag);
                switch (this.type) {
                    case 1:
                        codedOutputByteBufferNano.writeDoubleNoTag(((Double) obj).doubleValue());
                        return;
                    case 2:
                        codedOutputByteBufferNano.writeFloatNoTag(((Float) obj).floatValue());
                        return;
                    case 3:
                        codedOutputByteBufferNano.writeInt64NoTag(((Long) obj).longValue());
                        return;
                    case 4:
                        codedOutputByteBufferNano.writeUInt64NoTag(((Long) obj).longValue());
                        return;
                    case 5:
                        codedOutputByteBufferNano.writeInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 6:
                        codedOutputByteBufferNano.writeFixed64NoTag(((Long) obj).longValue());
                        return;
                    case 7:
                        codedOutputByteBufferNano.writeFixed32NoTag(((Integer) obj).intValue());
                        return;
                    case 8:
                        codedOutputByteBufferNano.writeBoolNoTag(((Boolean) obj).booleanValue());
                        return;
                    case 9:
                        codedOutputByteBufferNano.writeStringNoTag((String) obj);
                        return;
                    case 10:
                    case 11:
                    default:
                        int i9 = this.type;
                        StringBuilder sb = new StringBuilder(24);
                        sb.append("Unknown type ");
                        sb.append(i9);
                        throw new IllegalArgumentException(sb.toString());
                    case 12:
                        codedOutputByteBufferNano.writeBytesNoTag((byte[]) obj);
                        return;
                    case 13:
                        codedOutputByteBufferNano.writeUInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 14:
                        codedOutputByteBufferNano.writeEnumNoTag(((Integer) obj).intValue());
                        return;
                    case 15:
                        codedOutputByteBufferNano.writeSFixed32NoTag(((Integer) obj).intValue());
                        return;
                    case 16:
                        codedOutputByteBufferNano.writeSFixed64NoTag(((Long) obj).longValue());
                        return;
                    case 17:
                        codedOutputByteBufferNano.writeSInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 18:
                        codedOutputByteBufferNano.writeSInt64NoTag(((Long) obj).longValue());
                        return;
                }
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    @Deprecated
    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int i9, Class<T> cls, int i10) {
        return new Extension<>(i9, cls, i10, false);
    }

    public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createPrimitiveTyped(int i9, Class<T> cls, long j9) {
        return new PrimitiveExtension(i9, cls, (int) j9, false, 0, 0);
    }

    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T[]> createRepeatedMessageTyped(int i9, Class<T[]> cls, long j9) {
        return new Extension<>(i9, cls, (int) j9, true);
    }

    public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createRepeatedPrimitiveTyped(int i9, Class<T> cls, long j9, long j10, long j11) {
        return new PrimitiveExtension(i9, cls, (int) j9, true, (int) j10, (int) j11);
    }

    private T getRepeatedValueFrom(List<UnknownFieldData> list) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < list.size(); i9++) {
            UnknownFieldData unknownFieldData = list.get(i9);
            if (unknownFieldData.bytes.length != 0) {
                readDataInto(unknownFieldData, arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        Class<T> cls = this.clazz;
        T tCast = cls.cast(Array.newInstance(cls.getComponentType(), size));
        for (int i10 = 0; i10 < size; i10++) {
            Array.set(tCast, i10, arrayList.get(i10));
        }
        return tCast;
    }

    private T getSingularValueFrom(List<UnknownFieldData> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.clazz.cast(readData(CodedInputByteBufferNano.newInstance(list.get(list.size() - 1).bytes)));
    }

    public int computeRepeatedSerializedSize(Object obj) {
        int length = Array.getLength(obj);
        int iComputeSingularSerializedSize = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (Array.get(obj, i9) != null) {
                iComputeSingularSerializedSize += computeSingularSerializedSize(Array.get(obj, i9));
            }
        }
        return iComputeSingularSerializedSize;
    }

    public int computeSerializedSize(Object obj) {
        return this.repeated ? computeRepeatedSerializedSize(obj) : computeSingularSerializedSize(obj);
    }

    public int computeSingularSerializedSize(Object obj) {
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
        int i9 = this.type;
        if (i9 == 10) {
            return CodedOutputByteBufferNano.computeGroupSize(tagFieldNumber, (MessageNano) obj);
        }
        if (i9 == 11) {
            return CodedOutputByteBufferNano.computeMessageSize(tagFieldNumber, (MessageNano) obj);
        }
        int i10 = this.type;
        StringBuilder sb = new StringBuilder(24);
        sb.append("Unknown type ");
        sb.append(i10);
        throw new IllegalArgumentException(sb.toString());
    }

    public final T getValueFrom(List<UnknownFieldData> list) {
        if (list == null) {
            return null;
        }
        return this.repeated ? getRepeatedValueFrom(list) : getSingularValueFrom(list);
    }

    public Object readData(CodedInputByteBufferNano codedInputByteBufferNano) {
        Class componentType = this.repeated ? this.clazz.getComponentType() : this.clazz;
        try {
            int i9 = this.type;
            if (i9 == 10) {
                MessageNano messageNano = (MessageNano) componentType.newInstance();
                codedInputByteBufferNano.readGroup(messageNano, WireFormatNano.getTagFieldNumber(this.tag));
                return messageNano;
            }
            if (i9 == 11) {
                MessageNano messageNano2 = (MessageNano) componentType.newInstance();
                codedInputByteBufferNano.readMessage(messageNano2);
                return messageNano2;
            }
            int i10 = this.type;
            StringBuilder sb = new StringBuilder(24);
            sb.append("Unknown type ");
            sb.append(i10);
            throw new IllegalArgumentException(sb.toString());
        } catch (IOException e9) {
            throw new IllegalArgumentException("Error reading extension field", e9);
        } catch (IllegalAccessException e10) {
            String strValueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 33);
            sb2.append("Error creating instance of class ");
            sb2.append(strValueOf);
            throw new IllegalArgumentException(sb2.toString(), e10);
        } catch (InstantiationException e11) {
            String strValueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 33);
            sb3.append("Error creating instance of class ");
            sb3.append(strValueOf2);
            throw new IllegalArgumentException(sb3.toString(), e11);
        }
    }

    public void readDataInto(UnknownFieldData unknownFieldData, List<Object> list) {
        list.add(readData(CodedInputByteBufferNano.newInstance(unknownFieldData.bytes)));
    }

    public void writeRepeatedData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        int length = Array.getLength(obj);
        for (int i9 = 0; i9 < length; i9++) {
            Object obj2 = Array.get(obj, i9);
            if (obj2 != null) {
                writeSingularData(obj2, codedOutputByteBufferNano);
            }
        }
    }

    public void writeSingularData(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) {
        try {
            codedOutputByteBufferNano.writeRawVarint32(this.tag);
            int i9 = this.type;
            if (i9 == 10) {
                int tagFieldNumber = WireFormatNano.getTagFieldNumber(this.tag);
                codedOutputByteBufferNano.writeGroupNoTag((MessageNano) obj);
                codedOutputByteBufferNano.writeTag(tagFieldNumber, 4);
            } else {
                if (i9 == 11) {
                    codedOutputByteBufferNano.writeMessageNoTag((MessageNano) obj);
                    return;
                }
                int i10 = this.type;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i10);
                throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e9) {
            throw new IllegalStateException(e9);
        }
    }

    public void writeTo(Object obj, CodedOutputByteBufferNano codedOutputByteBufferNano) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (this.repeated) {
            writeRepeatedData(obj, codedOutputByteBufferNano);
        } else {
            writeSingularData(obj, codedOutputByteBufferNano);
        }
    }

    private Extension(int i9, Class<T> cls, int i10, boolean z8) {
        this.type = i9;
        this.clazz = cls;
        this.tag = i10;
        this.repeated = z8;
    }

    public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int i9, Class<T> cls, long j9) {
        return new Extension<>(i9, cls, (int) j9, false);
    }
}
