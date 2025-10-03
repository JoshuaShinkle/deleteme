package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.WireFormat;
import java.util.List;

/* loaded from: classes2.dex */
final class ArrayDecoders {

    /* renamed from: com.google.protobuf.ArrayDecoders$1 */
    public static /* synthetic */ class C43801 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public static int decodeBoolList(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int iDecodeVarint64 = decodeVarint64(bArr, i10, registers);
        booleanArrayList.addBoolean(registers.long1 != 0);
        while (iDecodeVarint64 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint64, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint64 = decodeVarint64(bArr, iDecodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        return iDecodeVarint64;
    }

    public static int decodeBytes(byte[] bArr, int i9, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1;
        if (i10 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i10 > bArr.length - iDecodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if (i10 == 0) {
            registers.object1 = ByteString.EMPTY;
            return iDecodeVarint32;
        }
        registers.object1 = ByteString.copyFrom(bArr, iDecodeVarint32, i10);
        return iDecodeVarint32 + i10;
    }

    public static int decodeBytesList(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
        int i12 = registers.int1;
        if (i12 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i12 > bArr.length - iDecodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if (i12 == 0) {
            protobufList.add(ByteString.EMPTY);
        } else {
            protobufList.add(ByteString.copyFrom(bArr, iDecodeVarint32, i12));
            iDecodeVarint32 += i12;
        }
        while (iDecodeVarint32 < i11) {
            int iDecodeVarint322 = decodeVarint32(bArr, iDecodeVarint32, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint322, registers);
            int i13 = registers.int1;
            if (i13 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i13 > bArr.length - iDecodeVarint32) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i13 == 0) {
                protobufList.add(ByteString.EMPTY);
            } else {
                protobufList.add(ByteString.copyFrom(bArr, iDecodeVarint32, i13));
                iDecodeVarint32 += i13;
            }
        }
        return iDecodeVarint32;
    }

    public static double decodeDouble(byte[] bArr, int i9) {
        return Double.longBitsToDouble(decodeFixed64(bArr, i9));
    }

    public static int decodeDoubleList(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        doubleArrayList.addDouble(decodeDouble(bArr, i10));
        int i12 = i10 + 8;
        while (i12 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, i12, registers);
            if (i9 != registers.int1) {
                break;
            }
            doubleArrayList.addDouble(decodeDouble(bArr, iDecodeVarint32));
            i12 = iDecodeVarint32 + 8;
        }
        return i12;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int decodeExtension(int i9, byte[] bArr, int i10, int i11, GeneratedMessageLite.ExtendableMessage<?, ?> extendableMessage, GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws InvalidProtocolBufferException {
        Object field;
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet = extendableMessage.extensions;
        int i12 = i9 >>> 3;
        if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
            switch (C43801.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    DoubleArrayList doubleArrayList = new DoubleArrayList();
                    int iDecodePackedDoubleList = decodePackedDoubleList(bArr, i10, doubleArrayList, registers);
                    fieldSet.setField(generatedExtension.descriptor, doubleArrayList);
                    return iDecodePackedDoubleList;
                case 2:
                    FloatArrayList floatArrayList = new FloatArrayList();
                    int iDecodePackedFloatList = decodePackedFloatList(bArr, i10, floatArrayList, registers);
                    fieldSet.setField(generatedExtension.descriptor, floatArrayList);
                    return iDecodePackedFloatList;
                case 3:
                case 4:
                    LongArrayList longArrayList = new LongArrayList();
                    int iDecodePackedVarint64List = decodePackedVarint64List(bArr, i10, longArrayList, registers);
                    fieldSet.setField(generatedExtension.descriptor, longArrayList);
                    return iDecodePackedVarint64List;
                case 5:
                case 6:
                    IntArrayList intArrayList = new IntArrayList();
                    int iDecodePackedVarint32List = decodePackedVarint32List(bArr, i10, intArrayList, registers);
                    fieldSet.setField(generatedExtension.descriptor, intArrayList);
                    return iDecodePackedVarint32List;
                case 7:
                case 8:
                    LongArrayList longArrayList2 = new LongArrayList();
                    int iDecodePackedFixed64List = decodePackedFixed64List(bArr, i10, longArrayList2, registers);
                    fieldSet.setField(generatedExtension.descriptor, longArrayList2);
                    return iDecodePackedFixed64List;
                case 9:
                case 10:
                    IntArrayList intArrayList2 = new IntArrayList();
                    int iDecodePackedFixed32List = decodePackedFixed32List(bArr, i10, intArrayList2, registers);
                    fieldSet.setField(generatedExtension.descriptor, intArrayList2);
                    return iDecodePackedFixed32List;
                case 11:
                    BooleanArrayList booleanArrayList = new BooleanArrayList();
                    int iDecodePackedBoolList = decodePackedBoolList(bArr, i10, booleanArrayList, registers);
                    fieldSet.setField(generatedExtension.descriptor, booleanArrayList);
                    return iDecodePackedBoolList;
                case 12:
                    IntArrayList intArrayList3 = new IntArrayList();
                    int iDecodePackedSInt32List = decodePackedSInt32List(bArr, i10, intArrayList3, registers);
                    fieldSet.setField(generatedExtension.descriptor, intArrayList3);
                    return iDecodePackedSInt32List;
                case 13:
                    LongArrayList longArrayList3 = new LongArrayList();
                    int iDecodePackedSInt64List = decodePackedSInt64List(bArr, i10, longArrayList3, registers);
                    fieldSet.setField(generatedExtension.descriptor, longArrayList3);
                    return iDecodePackedSInt64List;
                case 14:
                    IntArrayList intArrayList4 = new IntArrayList();
                    int iDecodePackedVarint32List2 = decodePackedVarint32List(bArr, i10, intArrayList4, registers);
                    UnknownFieldSetLite unknownFieldSetLite = extendableMessage.unknownFields;
                    UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(i12, (List<Integer>) intArrayList4, generatedExtension.descriptor.getEnumType(), unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance() ? unknownFieldSetLite : null, (UnknownFieldSchema<UT, Object>) unknownFieldSchema);
                    if (unknownFieldSetLite2 != null) {
                        extendableMessage.unknownFields = unknownFieldSetLite2;
                    }
                    fieldSet.setField(generatedExtension.descriptor, intArrayList4);
                    return iDecodePackedVarint32List2;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.descriptor.getLiteType());
            }
        }
        if (generatedExtension.getLiteType() != WireFormat.FieldType.ENUM) {
            switch (C43801.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    objValueOf = Double.valueOf(decodeDouble(bArr, i10));
                    i10 += 8;
                    break;
                case 2:
                    objValueOf = Float.valueOf(decodeFloat(bArr, i10));
                    i10 += 4;
                    break;
                case 3:
                case 4:
                    i10 = decodeVarint64(bArr, i10, registers);
                    objValueOf = Long.valueOf(registers.long1);
                    break;
                case 5:
                case 6:
                    i10 = decodeVarint32(bArr, i10, registers);
                    objValueOf = Integer.valueOf(registers.int1);
                    break;
                case 7:
                case 8:
                    objValueOf = Long.valueOf(decodeFixed64(bArr, i10));
                    i10 += 8;
                    break;
                case 9:
                case 10:
                    objValueOf = Integer.valueOf(decodeFixed32(bArr, i10));
                    i10 += 4;
                    break;
                case 11:
                    i10 = decodeVarint64(bArr, i10, registers);
                    objValueOf = Boolean.valueOf(registers.long1 != 0);
                    break;
                case 12:
                    i10 = decodeVarint32(bArr, i10, registers);
                    objValueOf = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                    break;
                case 13:
                    i10 = decodeVarint64(bArr, i10, registers);
                    objValueOf = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                    break;
                case 14:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 15:
                    i10 = decodeBytes(bArr, i10, registers);
                    objValueOf = registers.object1;
                    break;
                case 16:
                    i10 = decodeString(bArr, i10, registers);
                    objValueOf = registers.object1;
                    break;
                case 17:
                    i10 = decodeGroupField(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, i10, i11, (i12 << 3) | 4, registers);
                    objValueOf = registers.object1;
                    break;
                case 18:
                    i10 = decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, i10, i11, registers);
                    objValueOf = registers.object1;
                    break;
            }
        } else {
            i10 = decodeVarint32(bArr, i10, registers);
            if (generatedExtension.descriptor.getEnumType().findValueByNumber(registers.int1) == null) {
                UnknownFieldSetLite unknownFieldSetLiteNewInstance = extendableMessage.unknownFields;
                if (unknownFieldSetLiteNewInstance == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
                    extendableMessage.unknownFields = unknownFieldSetLiteNewInstance;
                }
                SchemaUtil.storeUnknownEnum(i12, registers.int1, unknownFieldSetLiteNewInstance, unknownFieldSchema);
                return i10;
            }
            objValueOf = Integer.valueOf(registers.int1);
        }
        if (generatedExtension.isRepeated()) {
            fieldSet.addRepeatedField(generatedExtension.descriptor, objValueOf);
        } else {
            int i13 = C43801.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()];
            if ((i13 == 17 || i13 == 18) && (field = fieldSet.getField(generatedExtension.descriptor)) != null) {
                objValueOf = Internal.mergeMessage(field, objValueOf);
            }
            fieldSet.setField(generatedExtension.descriptor, objValueOf);
        }
        return i10;
    }

    public static int decodeExtensionOrUnknownField(int i9, byte[] bArr, int i10, int i11, Object obj, MessageLite messageLite, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) {
        GeneratedMessageLite.GeneratedExtension generatedExtensionFindLiteExtensionByNumber = registers.extensionRegistry.findLiteExtensionByNumber(messageLite, i9 >>> 3);
        if (generatedExtensionFindLiteExtensionByNumber == null) {
            return decodeUnknownField(i9, bArr, i10, i11, MessageSchema.getMutableUnknownFields(obj), registers);
        }
        GeneratedMessageLite.ExtendableMessage extendableMessage = (GeneratedMessageLite.ExtendableMessage) obj;
        extendableMessage.ensureExtensionsAreMutable();
        return decodeExtension(i9, bArr, i10, i11, extendableMessage, generatedExtensionFindLiteExtensionByNumber, unknownFieldSchema, registers);
    }

    public static int decodeFixed32(byte[] bArr, int i9) {
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i9] & UnsignedBytes.MAX_VALUE) | ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    public static int decodeFixed32List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        intArrayList.addInt(decodeFixed32(bArr, i10));
        int i12 = i10 + 4;
        while (i12 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, i12, registers);
            if (i9 != registers.int1) {
                break;
            }
            intArrayList.addInt(decodeFixed32(bArr, iDecodeVarint32));
            i12 = iDecodeVarint32 + 4;
        }
        return i12;
    }

    public static long decodeFixed64(byte[] bArr, int i9) {
        return ((bArr[i9 + 7] & 255) << 56) | (bArr[i9] & 255) | ((bArr[i9 + 1] & 255) << 8) | ((bArr[i9 + 2] & 255) << 16) | ((bArr[i9 + 3] & 255) << 24) | ((bArr[i9 + 4] & 255) << 32) | ((bArr[i9 + 5] & 255) << 40) | ((bArr[i9 + 6] & 255) << 48);
    }

    public static int decodeFixed64List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        longArrayList.addLong(decodeFixed64(bArr, i10));
        int i12 = i10 + 8;
        while (i12 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, i12, registers);
            if (i9 != registers.int1) {
                break;
            }
            longArrayList.addLong(decodeFixed64(bArr, iDecodeVarint32));
            i12 = iDecodeVarint32 + 8;
        }
        return i12;
    }

    public static float decodeFloat(byte[] bArr, int i9) {
        return Float.intBitsToFloat(decodeFixed32(bArr, i9));
    }

    public static int decodeFloatList(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        floatArrayList.addFloat(decodeFloat(bArr, i10));
        int i12 = i10 + 4;
        while (i12 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, i12, registers);
            if (i9 != registers.int1) {
                break;
            }
            floatArrayList.addFloat(decodeFloat(bArr, iDecodeVarint32));
            i12 = iDecodeVarint32 + 4;
        }
        return i12;
    }

    public static int decodeGroupField(Schema schema, byte[] bArr, int i9, int i10, int i11, Registers registers) {
        MessageSchema messageSchema = (MessageSchema) schema;
        Object objNewInstance = messageSchema.newInstance();
        int proto2Message = messageSchema.parseProto2Message(objNewInstance, bArr, i9, i10, i11, registers);
        messageSchema.makeImmutable(objNewInstance);
        registers.object1 = objNewInstance;
        return proto2Message;
    }

    public static int decodeGroupList(Schema schema, int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        int i12 = (i9 & (-8)) | 4;
        int iDecodeGroupField = decodeGroupField(schema, bArr, i10, i11, i12, registers);
        protobufList.add(registers.object1);
        while (iDecodeGroupField < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, iDecodeGroupField, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeGroupField = decodeGroupField(schema, bArr, iDecodeVarint32, i11, i12, registers);
            protobufList.add(registers.object1);
        }
        return iDecodeGroupField;
    }

    public static int decodeMessageField(Schema schema, byte[] bArr, int i9, int i10, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = i9 + 1;
        int i11 = bArr[i9];
        if (i11 < 0) {
            iDecodeVarint32 = decodeVarint32(i11, bArr, iDecodeVarint32, registers);
            i11 = registers.int1;
        }
        int i12 = iDecodeVarint32;
        if (i11 < 0 || i11 > i10 - i12) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        Object objNewInstance = schema.newInstance();
        int i13 = i11 + i12;
        schema.mergeFrom(objNewInstance, bArr, i12, i13, registers);
        schema.makeImmutable(objNewInstance);
        registers.object1 = objNewInstance;
        return i13;
    }

    public static int decodeMessageList(Schema<?> schema, int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeMessageField = decodeMessageField(schema, bArr, i10, i11, registers);
        protobufList.add(registers.object1);
        while (iDecodeMessageField < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, iDecodeMessageField, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeMessageField = decodeMessageField(schema, bArr, iDecodeVarint32, i11, registers);
            protobufList.add(registers.object1);
        }
        return iDecodeMessageField;
    }

    public static int decodePackedBoolList(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            iDecodeVarint32 = decodeVarint64(bArr, iDecodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedDoubleList(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            doubleArrayList.addDouble(decodeDouble(bArr, iDecodeVarint32));
            iDecodeVarint32 += 8;
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed32List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            intArrayList.addInt(decodeFixed32(bArr, iDecodeVarint32));
            iDecodeVarint32 += 4;
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed64List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            longArrayList.addLong(decodeFixed64(bArr, iDecodeVarint32));
            iDecodeVarint32 += 8;
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFloatList(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            floatArrayList.addFloat(decodeFloat(bArr, iDecodeVarint32));
            iDecodeVarint32 += 4;
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt32List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint32, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt64List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            iDecodeVarint32 = decodeVarint64(bArr, iDecodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint32List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint32, registers);
            intArrayList.addInt(registers.int1);
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint64List(byte[] bArr, int i9, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1 + iDecodeVarint32;
        while (iDecodeVarint32 < i10) {
            iDecodeVarint32 = decodeVarint64(bArr, iDecodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        if (iDecodeVarint32 == i10) {
            return iDecodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodeSInt32List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
        intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        while (iDecodeVarint32 < i11) {
            int iDecodeVarint322 = decodeVarint32(bArr, iDecodeVarint32, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint322, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        return iDecodeVarint32;
    }

    public static int decodeSInt64List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int iDecodeVarint64 = decodeVarint64(bArr, i10, registers);
        longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        while (iDecodeVarint64 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint64, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint64 = decodeVarint64(bArr, iDecodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        return iDecodeVarint64;
    }

    public static int decodeString(byte[] bArr, int i9, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1;
        if (i10 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i10 == 0) {
            registers.object1 = "";
            return iDecodeVarint32;
        }
        registers.object1 = new String(bArr, iDecodeVarint32, i10, Internal.UTF_8);
        return iDecodeVarint32 + i10;
    }

    public static int decodeStringList(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
        int i12 = registers.int1;
        if (i12 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i12 == 0) {
            protobufList.add("");
        } else {
            protobufList.add(new String(bArr, iDecodeVarint32, i12, Internal.UTF_8));
            iDecodeVarint32 += i12;
        }
        while (iDecodeVarint32 < i11) {
            int iDecodeVarint322 = decodeVarint32(bArr, iDecodeVarint32, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint322, registers);
            int i13 = registers.int1;
            if (i13 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i13 == 0) {
                protobufList.add("");
            } else {
                protobufList.add(new String(bArr, iDecodeVarint32, i13, Internal.UTF_8));
                iDecodeVarint32 += i13;
            }
        }
        return iDecodeVarint32;
    }

    public static int decodeStringListRequireUtf8(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
        int i12 = registers.int1;
        if (i12 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i12 == 0) {
            protobufList.add("");
        } else {
            int i13 = iDecodeVarint32 + i12;
            if (!Utf8.isValidUtf8(bArr, iDecodeVarint32, i13)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            protobufList.add(new String(bArr, iDecodeVarint32, i12, Internal.UTF_8));
            iDecodeVarint32 = i13;
        }
        while (iDecodeVarint32 < i11) {
            int iDecodeVarint322 = decodeVarint32(bArr, iDecodeVarint32, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint322, registers);
            int i14 = registers.int1;
            if (i14 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i14 == 0) {
                protobufList.add("");
            } else {
                int i15 = iDecodeVarint32 + i14;
                if (!Utf8.isValidUtf8(bArr, iDecodeVarint32, i15)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
                protobufList.add(new String(bArr, iDecodeVarint32, i14, Internal.UTF_8));
                iDecodeVarint32 = i15;
            }
        }
        return iDecodeVarint32;
    }

    public static int decodeStringRequireUtf8(byte[] bArr, int i9, Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32 = decodeVarint32(bArr, i9, registers);
        int i10 = registers.int1;
        if (i10 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i10 == 0) {
            registers.object1 = "";
            return iDecodeVarint32;
        }
        registers.object1 = Utf8.decodeUtf8(bArr, iDecodeVarint32, i10);
        return iDecodeVarint32 + i10;
    }

    public static int decodeUnknownField(int i9, byte[] bArr, int i10, int i11, UnknownFieldSetLite unknownFieldSetLite, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i9) == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            int iDecodeVarint64 = decodeVarint64(bArr, i10, registers);
            unknownFieldSetLite.storeField(i9, Long.valueOf(registers.long1));
            return iDecodeVarint64;
        }
        if (tagWireType == 1) {
            unknownFieldSetLite.storeField(i9, Long.valueOf(decodeFixed64(bArr, i10)));
            return i10 + 8;
        }
        if (tagWireType == 2) {
            int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
            int i12 = registers.int1;
            if (i12 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i12 > bArr.length - iDecodeVarint32) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i12 == 0) {
                unknownFieldSetLite.storeField(i9, ByteString.EMPTY);
            } else {
                unknownFieldSetLite.storeField(i9, ByteString.copyFrom(bArr, iDecodeVarint32, i12));
            }
            return iDecodeVarint32 + i12;
        }
        if (tagWireType != 3) {
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidTag();
            }
            unknownFieldSetLite.storeField(i9, Integer.valueOf(decodeFixed32(bArr, i10)));
            return i10 + 4;
        }
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
        int i13 = (i9 & (-8)) | 4;
        int i14 = 0;
        while (true) {
            if (i10 >= i11) {
                break;
            }
            int iDecodeVarint322 = decodeVarint32(bArr, i10, registers);
            int i15 = registers.int1;
            if (i15 == i13) {
                i14 = i15;
                i10 = iDecodeVarint322;
                break;
            }
            i14 = i15;
            i10 = decodeUnknownField(i15, bArr, iDecodeVarint322, i11, unknownFieldSetLiteNewInstance, registers);
        }
        if (i10 > i11 || i14 != i13) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        unknownFieldSetLite.storeField(i9, unknownFieldSetLiteNewInstance);
        return i10;
    }

    public static int decodeVarint32(byte[] bArr, int i9, Registers registers) {
        int i10 = i9 + 1;
        byte b9 = bArr[i9];
        if (b9 < 0) {
            return decodeVarint32(b9, bArr, i10, registers);
        }
        registers.int1 = b9;
        return i10;
    }

    public static int decodeVarint32List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int iDecodeVarint32 = decodeVarint32(bArr, i10, registers);
        intArrayList.addInt(registers.int1);
        while (iDecodeVarint32 < i11) {
            int iDecodeVarint322 = decodeVarint32(bArr, iDecodeVarint32, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint322, registers);
            intArrayList.addInt(registers.int1);
        }
        return iDecodeVarint32;
    }

    public static int decodeVarint64(byte[] bArr, int i9, Registers registers) {
        int i10 = i9 + 1;
        long j9 = bArr[i9];
        if (j9 < 0) {
            return decodeVarint64(j9, bArr, i10, registers);
        }
        registers.long1 = j9;
        return i10;
    }

    public static int decodeVarint64List(int i9, byte[] bArr, int i10, int i11, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int iDecodeVarint64 = decodeVarint64(bArr, i10, registers);
        longArrayList.addLong(registers.long1);
        while (iDecodeVarint64 < i11) {
            int iDecodeVarint32 = decodeVarint32(bArr, iDecodeVarint64, registers);
            if (i9 != registers.int1) {
                break;
            }
            iDecodeVarint64 = decodeVarint64(bArr, iDecodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        return iDecodeVarint64;
    }

    public static int skipField(int i9, byte[] bArr, int i10, int i11, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i9) == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            return decodeVarint64(bArr, i10, registers);
        }
        if (tagWireType == 1) {
            return i10 + 8;
        }
        if (tagWireType == 2) {
            return decodeVarint32(bArr, i10, registers) + registers.int1;
        }
        if (tagWireType != 3) {
            if (tagWireType == 5) {
                return i10 + 4;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }
        int i12 = (i9 & (-8)) | 4;
        int i13 = 0;
        while (i10 < i11) {
            i10 = decodeVarint32(bArr, i10, registers);
            i13 = registers.int1;
            if (i13 == i12) {
                break;
            }
            i10 = skipField(i13, bArr, i10, i11, registers);
        }
        if (i10 > i11 || i13 != i12) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i10;
    }

    public static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        public Registers() {
            this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        }

        public Registers(ExtensionRegistryLite extensionRegistryLite) {
            extensionRegistryLite.getClass();
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    public static int decodeVarint32(int i9, byte[] bArr, int i10, Registers registers) {
        int i11 = i9 & 127;
        int i12 = i10 + 1;
        byte b9 = bArr[i10];
        if (b9 >= 0) {
            registers.int1 = i11 | (b9 << 7);
            return i12;
        }
        int i13 = i11 | ((b9 & Ascii.DEL) << 7);
        int i14 = i12 + 1;
        byte b10 = bArr[i12];
        if (b10 >= 0) {
            registers.int1 = i13 | (b10 << Ascii.f15390SO);
            return i14;
        }
        int i15 = i13 | ((b10 & Ascii.DEL) << 14);
        int i16 = i14 + 1;
        byte b11 = bArr[i14];
        if (b11 >= 0) {
            registers.int1 = i15 | (b11 << Ascii.NAK);
            return i16;
        }
        int i17 = i15 | ((b11 & Ascii.DEL) << 21);
        int i18 = i16 + 1;
        byte b12 = bArr[i16];
        if (b12 >= 0) {
            registers.int1 = i17 | (b12 << Ascii.f15383FS);
            return i18;
        }
        int i19 = i17 | ((b12 & Ascii.DEL) << 28);
        while (true) {
            int i20 = i18 + 1;
            if (bArr[i18] >= 0) {
                registers.int1 = i19;
                return i20;
            }
            i18 = i20;
        }
    }

    public static int decodeVarint64(long j9, byte[] bArr, int i9, Registers registers) {
        int i10 = i9 + 1;
        byte b9 = bArr[i9];
        long j10 = (j9 & 127) | ((b9 & Ascii.DEL) << 7);
        int i11 = 7;
        while (b9 < 0) {
            int i12 = i10 + 1;
            byte b10 = bArr[i10];
            i11 += 7;
            j10 |= (b10 & Ascii.DEL) << i11;
            i10 = i12;
            b9 = b10;
        }
        registers.long1 = j10;
        return i10;
    }
}
