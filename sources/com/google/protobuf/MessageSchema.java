package com.google.protobuf;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.FieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    /* renamed from: com.google.protobuf.MessageSchema$1 */
    public static /* synthetic */ class C44151 {
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
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i9, int i10, MessageLite messageLite, boolean z8, boolean z9, int[] iArr2, int i11, int i12, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i9;
        this.maxFieldNumber = i10;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z8;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z9;
        this.intArray = iArr2;
        this.checkInitializedCount = i11;
        this.repeatedFieldOffsetStart = i12;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    private boolean arePresentForEquals(T t8, T t9, int i9) {
        return isFieldPresent(t8, i9) == isFieldPresent(t9, i9);
    }

    private static <T> boolean booleanAt(T t8, long j9) {
        return UnsafeUtil.getBoolean(t8, j9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    private <K, V> int decodeMapEntry(byte[] bArr, int i9, int i10, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32;
        int iDecodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
        int i11 = registers.int1;
        if (i11 < 0 || i11 > i10 - iDecodeVarint322) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i12 = iDecodeVarint322 + i11;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (iDecodeVarint322 < i12) {
            int i13 = iDecodeVarint322 + 1;
            byte b9 = bArr[iDecodeVarint322];
            if (b9 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(b9, bArr, i13, registers);
                b9 = registers.int1;
            } else {
                iDecodeVarint32 = i13;
            }
            int i14 = b9 >>> 3;
            int i15 = b9 & 7;
            if (i14 != 1) {
                if (i14 == 2 && i15 == metadata.valueType.getWireType()) {
                    iDecodeVarint322 = decodeMapEntryValue(bArr, iDecodeVarint32, i10, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                } else {
                    iDecodeVarint322 = ArrayDecoders.skipField(b9, bArr, iDecodeVarint32, i10, registers);
                }
            } else if (i15 == metadata.keyType.getWireType()) {
                iDecodeVarint322 = decodeMapEntryValue(bArr, iDecodeVarint32, i10, metadata.keyType, null, registers);
                obj = registers.object1;
            } else {
                iDecodeVarint322 = ArrayDecoders.skipField(b9, bArr, iDecodeVarint32, i10, registers);
            }
        }
        if (iDecodeVarint322 != i12) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return i12;
    }

    private int decodeMapEntryValue(byte[] bArr, int i9, int i10, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) {
        switch (C44151.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return iDecodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(bArr, i9, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i9));
                return i9 + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i9));
                return i9 + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i9));
                return i9 + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i9));
                return i9 + 4;
            case 9:
            case 10:
            case 11:
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return iDecodeVarint32;
            case 12:
            case 13:
                int iDecodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return iDecodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) cls), bArr, i9, i10, registers);
            case 15:
                int iDecodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return iDecodeVarint322;
            case 16:
                int iDecodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return iDecodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i9, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static <T> double doubleAt(T t8, long j9) {
        return UnsafeUtil.getDouble(t8, j9);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i9, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i9);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i9)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i9)) == null) ? ub : (UB) filterUnknownEnumMap(i9, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i9, int i10, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i9));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i10, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e9) {
                    throw new RuntimeException(e9);
                }
            }
        }
        return ub;
    }

    private static <T> float floatAt(T t8, long j9) {
        return UnsafeUtil.getFloat(t8, j9);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i9) {
        return (Internal.EnumVerifier) this.objects[((i9 / 3) * 2) + 1];
    }

    private Object getMapFieldDefaultEntry(int i9) {
        return this.objects[(i9 / 3) * 2];
    }

    private Schema getMessageFieldSchema(int i9) {
        int i10 = (i9 / 3) * 2;
        Schema schema = (Schema) this.objects[i10];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i10 + 1]);
        this.objects[i10] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = unknownFieldSetLiteNewInstance;
        return unknownFieldSetLiteNewInstance;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int getSerializedSizeProto2(T t8) {
        int i9;
        int i10;
        int iComputeDoubleSize;
        int iComputeBoolSize;
        int iComputeSFixed32Size;
        boolean z8;
        int iComputeSizeFixed32List;
        int iComputeSizeFixed64ListNoTag;
        int iComputeTagSize;
        int iComputeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i11 = -1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < this.buffer.length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i12);
            int iNumberAt = numberAt(i12);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                i9 = this.buffer[i12 + 2];
                int i15 = OFFSET_MASK & i9;
                int i16 = 1 << (i9 >>> 20);
                if (i15 != i11) {
                    i14 = unsafe.getInt(t8, i15);
                    i11 = i15;
                }
                i10 = i16;
            } else {
                i9 = (!this.useCachedSizeField || iType < FieldType.DOUBLE_LIST_PACKED.m17838id() || iType > FieldType.SINT64_LIST_PACKED.m17838id()) ? 0 : this.buffer[i12 + 2] & OFFSET_MASK;
                i10 = 0;
            }
            long jOffset = offset(iTypeAndOffsetAt);
            int i17 = i11;
            switch (iType) {
                case 0:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(iNumberAt, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 1:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(iNumberAt, BitmapDescriptorFactory.HUE_RED);
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 2:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(iNumberAt, unsafe.getLong(t8, jOffset));
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(iNumberAt, unsafe.getLong(t8, jOffset));
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(iNumberAt, unsafe.getInt(t8, jOffset));
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 5:
                    if ((i14 & i10) == 0) {
                        break;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i13 += iComputeDoubleSize;
                        break;
                    }
                case 6:
                    if ((i14 & i10) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i13 += iComputeDoubleSize;
                        break;
                    }
                    break;
                case 7:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 8:
                    if ((i14 & i10) != 0) {
                        Object object = unsafe.getObject(t8, jOffset);
                        iComputeBoolSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object) : CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 9:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 10:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 11:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeUInt32Size(iNumberAt, unsafe.getInt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 12:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeEnumSize(iNumberAt, unsafe.getInt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 13:
                    if ((i14 & i10) != 0) {
                        iComputeSFixed32Size = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 14:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 15:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSInt32Size(iNumberAt, unsafe.getInt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 16:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSInt64Size(iNumberAt, unsafe.getLong(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 17:
                    if ((i14 & i10) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 18:
                    iComputeBoolSize = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeBoolSize;
                    break;
                case 19:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 20:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeInt64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 21:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeUInt64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 22:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeInt32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 23:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 24:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 25:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeBoolList(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 26:
                    iComputeBoolSize = SchemaUtil.computeSizeStringList(iNumberAt, (List) unsafe.getObject(t8, jOffset));
                    i13 += iComputeBoolSize;
                    break;
                case 27:
                    iComputeBoolSize = SchemaUtil.computeSizeMessageList(iNumberAt, (List) unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                    i13 += iComputeBoolSize;
                    break;
                case 28:
                    iComputeBoolSize = SchemaUtil.computeSizeByteStringList(iNumberAt, (List) unsafe.getObject(t8, jOffset));
                    i13 += iComputeBoolSize;
                    break;
                case 29:
                    iComputeBoolSize = SchemaUtil.computeSizeUInt32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeBoolSize;
                    break;
                case 30:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeEnumList(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 31:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 32:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 33:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeSInt32List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 34:
                    z8 = false;
                    iComputeSizeFixed32List = SchemaUtil.computeSizeSInt64List(iNumberAt, (List) unsafe.getObject(t8, jOffset), false);
                    i13 += iComputeSizeFixed32List;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i9, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeSFixed32Size = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 49:
                    iComputeBoolSize = SchemaUtil.computeSizeGroupList(iNumberAt, (List) unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                    i13 += iComputeBoolSize;
                    break;
                case 50:
                    iComputeBoolSize = this.mapFieldSchema.getSerializedSize(iNumberAt, unsafe.getObject(t8, jOffset), getMapFieldDefaultEntry(i12));
                    i13 += iComputeBoolSize;
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeDoubleSize(iNumberAt, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeFloatSize(iNumberAt, BitmapDescriptorFactory.HUE_RED);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeUInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeSFixed32Size = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        Object object2 = unsafe.getObject(t8, jOffset);
                        iComputeBoolSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object2) : CodedOutputStream.computeStringSize(iNumberAt, (String) object2);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeUInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeEnumSize(iNumberAt, oneofIntAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeSFixed32Size = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i13 += iComputeSFixed32Size;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeSInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeSInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i13 += iComputeBoolSize;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t8, iNumberAt, i12)) {
                        iComputeBoolSize = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t8, jOffset), getMessageFieldSchema(i12));
                        i13 += iComputeBoolSize;
                    }
                    break;
            }
            i12 += 3;
            i11 = i17;
        }
        int unknownFieldsSerializedSize = i13 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t8);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t8).getSerializedSize() : unknownFieldsSerializedSize;
    }

    private int getSerializedSizeProto3(T t8) {
        int iComputeDoubleSize;
        int iComputeSizeFixed64ListNoTag;
        int iComputeTagSize;
        int iComputeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i9 = 0;
        for (int i10 = 0; i10 < this.buffer.length; i10 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i10);
            int iType = type(iTypeAndOffsetAt);
            int iNumberAt = numberAt(i10);
            long jOffset = offset(iTypeAndOffsetAt);
            int i11 = (iType < FieldType.DOUBLE_LIST_PACKED.m17838id() || iType > FieldType.SINT64_LIST_PACKED.m17838id()) ? 0 : this.buffer[i10 + 2] & OFFSET_MASK;
            switch (iType) {
                case 0:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(iNumberAt, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(iNumberAt, BitmapDescriptorFactory.HUE_RED);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(iNumberAt, UnsafeUtil.getLong(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(iNumberAt, UnsafeUtil.getLong(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(iNumberAt, UnsafeUtil.getInt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(t8, i10)) {
                        Object object = UnsafeUtil.getObject(t8, jOffset);
                        iComputeDoubleSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object) : CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(iNumberAt, UnsafeUtil.getObject(t8, jOffset), getMessageFieldSchema(i10));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt32Size(iNumberAt, UnsafeUtil.getInt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeEnumSize(iNumberAt, UnsafeUtil.getInt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt32Size(iNumberAt, UnsafeUtil.getInt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt64Size(iNumberAt, UnsafeUtil.getLong(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(t8, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) UnsafeUtil.getObject(t8, jOffset), getMessageFieldSchema(i10));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 19:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 20:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 21:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 22:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 23:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 24:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 25:
                    iComputeDoubleSize = SchemaUtil.computeSizeBoolList(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 26:
                    iComputeDoubleSize = SchemaUtil.computeSizeStringList(iNumberAt, listAt(t8, jOffset));
                    i9 += iComputeDoubleSize;
                    break;
                case 27:
                    iComputeDoubleSize = SchemaUtil.computeSizeMessageList(iNumberAt, listAt(t8, jOffset), getMessageFieldSchema(i10));
                    i9 += iComputeDoubleSize;
                    break;
                case 28:
                    iComputeDoubleSize = SchemaUtil.computeSizeByteStringList(iNumberAt, listAt(t8, jOffset));
                    i9 += iComputeDoubleSize;
                    break;
                case 29:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 30:
                    iComputeDoubleSize = SchemaUtil.computeSizeEnumList(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 31:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 32:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 33:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt32List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 34:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt64List(iNumberAt, listAt(t8, jOffset), false);
                    i9 += iComputeDoubleSize;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t8, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t8, i11, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        iComputeDoubleSize = iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                        i9 += iComputeDoubleSize;
                        break;
                    }
                case 49:
                    iComputeDoubleSize = SchemaUtil.computeSizeGroupList(iNumberAt, listAt(t8, jOffset), getMessageFieldSchema(i10));
                    i9 += iComputeDoubleSize;
                    break;
                case 50:
                    iComputeDoubleSize = this.mapFieldSchema.getSerializedSize(iNumberAt, UnsafeUtil.getObject(t8, jOffset), getMapFieldDefaultEntry(i10));
                    i9 += iComputeDoubleSize;
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(iNumberAt, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(iNumberAt, BitmapDescriptorFactory.HUE_RED);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        Object object2 = UnsafeUtil.getObject(t8, jOffset);
                        iComputeDoubleSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object2) : CodedOutputStream.computeStringSize(iNumberAt, (String) object2);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(iNumberAt, UnsafeUtil.getObject(t8, jOffset), getMessageFieldSchema(i10));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeEnumSize(iNumberAt, oneofIntAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt32Size(iNumberAt, oneofIntAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt64Size(iNumberAt, oneofLongAt(t8, jOffset));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t8, iNumberAt, i10)) {
                        iComputeDoubleSize = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) UnsafeUtil.getObject(t8, jOffset), getMessageFieldSchema(i10));
                        i9 += iComputeDoubleSize;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return i9 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t8);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t8) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t8));
    }

    private static <T> int intAt(T t8, long j9) {
        return UnsafeUtil.getInt(t8, j9);
    }

    private static boolean isEnforceUtf8(int i9) {
        return (i9 & ENFORCE_UTF8_MASK) != 0;
    }

    private boolean isFieldPresent(T t8, int i9, int i10, int i11) {
        return this.proto3 ? isFieldPresent(t8, i9) : (i10 & i11) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i9, int i10) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i9));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i10);
        for (int i11 = 0; i11 < list.size(); i11++) {
            if (!messageFieldSchema.isInitialized(list.get(i11))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.protobuf.Schema] */
    private boolean isMapInitialized(T t8, int i9, int i10) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t8, offset(i9)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i10)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            SchemaFor = SchemaFor;
            if (SchemaFor == 0) {
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOneofCaseEqual(T t8, T t9, int i9) {
        long jPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i9) & OFFSET_MASK;
        return UnsafeUtil.getInt(t8, jPresenceMaskAndOffsetAt) == UnsafeUtil.getInt(t9, jPresenceMaskAndOffsetAt);
    }

    private boolean isOneofPresent(T t8, int i9, int i10) {
        return UnsafeUtil.getInt(t8, (long) (presenceMaskAndOffsetAt(i10) & OFFSET_MASK)) == i9;
    }

    private static boolean isRequired(int i9) {
        return (i9 & 268435456) != 0;
    }

    private static List<?> listAt(Object obj, long j9) {
        return (List) UnsafeUtil.getObject(obj, j9);
    }

    private static <T> long longAt(T t8, long j9) {
        return UnsafeUtil.getLong(t8, j9);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
    
        r0 = r16.checkInitializedCount;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007b, code lost:
    
        if (r0 >= r16.repeatedFieldOffsetStart) goto L359;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
    
        r13 = filterMapUnknownEnumValues(r19, r16.intArray[r0], r13, r17);
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0088, code lost:
    
        if (r13 == null) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008a, code lost:
    
        r17.setBuilderToMessage(r19, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t8, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Object objFilterMapUnknownEnumValues = null;
        Object mutableExtensions = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int iPositionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (iPositionForFieldNumber >= 0) {
                    int iTypeAndOffsetAt = typeAndOffsetAt(iPositionForFieldNumber);
                    try {
                        switch (type(iTypeAndOffsetAt)) {
                            case 0:
                                UnsafeUtil.putDouble(t8, offset(iTypeAndOffsetAt), reader.readDouble());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 1:
                                UnsafeUtil.putFloat(t8, offset(iTypeAndOffsetAt), reader.readFloat());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 2:
                                UnsafeUtil.putLong(t8, offset(iTypeAndOffsetAt), reader.readInt64());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 3:
                                UnsafeUtil.putLong(t8, offset(iTypeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 4:
                                UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), reader.readInt32());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 5:
                                UnsafeUtil.putLong(t8, offset(iTypeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 6:
                                UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 7:
                                UnsafeUtil.putBoolean(t8, offset(iTypeAndOffsetAt), reader.readBool());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 8:
                                readString(t8, iTypeAndOffsetAt, reader);
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 9:
                                if (isFieldPresent(t8, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                    break;
                                } else {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t8, iPositionForFieldNumber);
                                    break;
                                }
                            case 10:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readBytes());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 11:
                                UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 12:
                                int i9 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(iPositionForFieldNumber);
                                if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(i9)) {
                                    objFilterMapUnknownEnumValues = SchemaUtil.storeUnknownEnum(fieldNumber, i9, objFilterMapUnknownEnumValues, unknownFieldSchema);
                                    break;
                                } else {
                                    UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), i9);
                                    setFieldPresent(t8, iPositionForFieldNumber);
                                    break;
                                }
                                break;
                            case 13:
                                UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 14:
                                UnsafeUtil.putLong(t8, offset(iTypeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 15:
                                UnsafeUtil.putInt(t8, offset(iTypeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 16:
                                UnsafeUtil.putLong(t8, offset(iTypeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(t8, iPositionForFieldNumber);
                                break;
                            case 17:
                                if (isFieldPresent(t8, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                    break;
                                } else {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t8, iPositionForFieldNumber);
                                    break;
                                }
                            case 18:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 19:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 20:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 21:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 22:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 23:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 24:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 25:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 26:
                                readStringList(t8, iTypeAndOffsetAt, reader);
                                break;
                            case 27:
                                readMessageList(t8, iTypeAndOffsetAt, reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 28:
                                reader.readBytesList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 29:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 30:
                                List<Integer> listMutableListAt = this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt));
                                reader.readEnumList(listMutableListAt);
                                objFilterMapUnknownEnumValues = SchemaUtil.filterUnknownEnumList(fieldNumber, listMutableListAt, getEnumFieldVerifier(iPositionForFieldNumber), objFilterMapUnknownEnumValues, unknownFieldSchema);
                                break;
                            case 31:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 32:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 33:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 34:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 35:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 36:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 37:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 38:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 39:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 40:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 41:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 42:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 43:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 44:
                                List<Integer> listMutableListAt2 = this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt));
                                reader.readEnumList(listMutableListAt2);
                                objFilterMapUnknownEnumValues = SchemaUtil.filterUnknownEnumList(fieldNumber, listMutableListAt2, getEnumFieldVerifier(iPositionForFieldNumber), objFilterMapUnknownEnumValues, unknownFieldSchema);
                                break;
                            case 45:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 46:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 47:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 48:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t8, offset(iTypeAndOffsetAt)));
                                break;
                            case 49:
                                readGroupList(t8, offset(iTypeAndOffsetAt), reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 50:
                                mergeMap(t8, iPositionForFieldNumber, getMapFieldDefaultEntry(iPositionForFieldNumber), extensionRegistryLite, reader);
                                break;
                            case 51:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 52:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 53:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 54:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 55:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 56:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 57:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 58:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 59:
                                readString(t8, iTypeAndOffsetAt, reader);
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 60:
                                if (isOneofPresent(t8, fieldNumber, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t8, iPositionForFieldNumber);
                                }
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 61:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readBytes());
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 62:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 63:
                                int i10 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(iPositionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(i10)) {
                                    objFilterMapUnknownEnumValues = SchemaUtil.storeUnknownEnum(fieldNumber, i10, objFilterMapUnknownEnumValues, unknownFieldSchema);
                                    break;
                                } else {
                                    UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(i10));
                                    setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                    break;
                                }
                                break;
                            case 64:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 65:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 66:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 67:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 68:
                                UnsafeUtil.putObject(t8, offset(iTypeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                setOneofPresent(t8, fieldNumber, iPositionForFieldNumber);
                                break;
                            default:
                                if (objFilterMapUnknownEnumValues == null) {
                                    objFilterMapUnknownEnumValues = unknownFieldSchema.newBuilder();
                                }
                                if (!unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                                    for (int i11 = this.checkInitializedCount; i11 < this.repeatedFieldOffsetStart; i11++) {
                                        objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t8, this.intArray[i11], objFilterMapUnknownEnumValues, unknownFieldSchema);
                                    }
                                    if (objFilterMapUnknownEnumValues != null) {
                                        unknownFieldSchema.setBuilderToMessage(t8, objFilterMapUnknownEnumValues);
                                        return;
                                    }
                                    return;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                        if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                            if (objFilterMapUnknownEnumValues == null) {
                                objFilterMapUnknownEnumValues = unknownFieldSchema.getBuilderFromMessage(t8);
                            }
                            if (!unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                                for (int i12 = this.checkInitializedCount; i12 < this.repeatedFieldOffsetStart; i12++) {
                                    objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t8, this.intArray[i12], objFilterMapUnknownEnumValues, unknownFieldSchema);
                                }
                                if (objFilterMapUnknownEnumValues != null) {
                                    unknownFieldSchema.setBuilderToMessage(t8, objFilterMapUnknownEnumValues);
                                    return;
                                }
                                return;
                            }
                        } else if (!reader.skipField()) {
                            for (int i13 = this.checkInitializedCount; i13 < this.repeatedFieldOffsetStart; i13++) {
                                objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t8, this.intArray[i13], objFilterMapUnknownEnumValues, unknownFieldSchema);
                            }
                            if (objFilterMapUnknownEnumValues != null) {
                                unknownFieldSchema.setBuilderToMessage(t8, objFilterMapUnknownEnumValues);
                                return;
                            }
                            return;
                        }
                    }
                } else {
                    if (fieldNumber == Integer.MAX_VALUE) {
                        for (int i14 = this.checkInitializedCount; i14 < this.repeatedFieldOffsetStart; i14++) {
                            objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t8, this.intArray[i14], objFilterMapUnknownEnumValues, unknownFieldSchema);
                        }
                        if (objFilterMapUnknownEnumValues != null) {
                            unknownFieldSchema.setBuilderToMessage(t8, objFilterMapUnknownEnumValues);
                            return;
                        }
                        return;
                    }
                    Object objFindExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, fieldNumber);
                    if (objFindExtensionByNumber != null) {
                        if (mutableExtensions == null) {
                            mutableExtensions = extensionSchema.getMutableExtensions(t8);
                        }
                        objFilterMapUnknownEnumValues = extensionSchema.parseExtension(reader, objFindExtensionByNumber, extensionRegistryLite, mutableExtensions, objFilterMapUnknownEnumValues, unknownFieldSchema);
                    } else if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                        if (objFilterMapUnknownEnumValues == null) {
                            objFilterMapUnknownEnumValues = unknownFieldSchema.getBuilderFromMessage(t8);
                        }
                        if (unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                        }
                    } else if (reader.skipField()) {
                    }
                }
            } catch (Throwable th) {
                for (int i15 = this.checkInitializedCount; i15 < this.repeatedFieldOffsetStart; i15++) {
                    objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t8, this.intArray[i15], objFilterMapUnknownEnumValues, unknownFieldSchema);
                }
                if (objFilterMapUnknownEnumValues != null) {
                    unknownFieldSchema.setBuilderToMessage(t8, objFilterMapUnknownEnumValues);
                }
                throw th;
            }
        }
    }

    private final <K, V> void mergeMap(Object obj, int i9, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long jOffset = offset(typeAndOffsetAt(i9));
        Object object = UnsafeUtil.getObject(obj, jOffset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, jOffset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            UnsafeUtil.putObject(obj, jOffset, objNewMapField);
            object = objNewMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private void mergeMessage(T t8, T t9, int i9) {
        long jOffset = offset(typeAndOffsetAt(i9));
        if (isFieldPresent(t9, i9)) {
            Object object = UnsafeUtil.getObject(t8, jOffset);
            Object object2 = UnsafeUtil.getObject(t9, jOffset);
            if (object != null && object2 != null) {
                UnsafeUtil.putObject(t8, jOffset, Internal.mergeMessage(object, object2));
                setFieldPresent(t8, i9);
            } else if (object2 != null) {
                UnsafeUtil.putObject(t8, jOffset, object2);
                setFieldPresent(t8, i9);
            }
        }
    }

    private void mergeOneofMessage(T t8, T t9, int i9) {
        int iTypeAndOffsetAt = typeAndOffsetAt(i9);
        int iNumberAt = numberAt(i9);
        long jOffset = offset(iTypeAndOffsetAt);
        if (isOneofPresent(t9, iNumberAt, i9)) {
            Object object = UnsafeUtil.getObject(t8, jOffset);
            Object object2 = UnsafeUtil.getObject(t9, jOffset);
            if (object != null && object2 != null) {
                UnsafeUtil.putObject(t8, jOffset, Internal.mergeMessage(object, object2));
                setOneofPresent(t8, iNumberAt, i9);
            } else if (object2 != null) {
                UnsafeUtil.putObject(t8, jOffset, object2);
                setOneofPresent(t8, iNumberAt, i9);
            }
        }
    }

    private void mergeSingleField(T t8, T t9, int i9) {
        int iTypeAndOffsetAt = typeAndOffsetAt(i9);
        long jOffset = offset(iTypeAndOffsetAt);
        int iNumberAt = numberAt(i9);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putDouble(t8, jOffset, UnsafeUtil.getDouble(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putFloat(t8, jOffset, UnsafeUtil.getFloat(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putLong(t8, jOffset, UnsafeUtil.getLong(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putLong(t8, jOffset, UnsafeUtil.getLong(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putLong(t8, jOffset, UnsafeUtil.getLong(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putBoolean(t8, jOffset, UnsafeUtil.getBoolean(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putObject(t8, jOffset, UnsafeUtil.getObject(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 9:
                mergeMessage(t8, t9, i9);
                break;
            case 10:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putObject(t8, jOffset, UnsafeUtil.getObject(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putLong(t8, jOffset, UnsafeUtil.getLong(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putInt(t8, jOffset, UnsafeUtil.getInt(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(t9, i9)) {
                    UnsafeUtil.putLong(t8, jOffset, UnsafeUtil.getLong(t9, jOffset));
                    setFieldPresent(t8, i9);
                    break;
                }
                break;
            case 17:
                mergeMessage(t8, t9, i9);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(t8, t9, jOffset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t8, t9, jOffset);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(t9, iNumberAt, i9)) {
                    UnsafeUtil.putObject(t8, jOffset, UnsafeUtil.getObject(t9, jOffset));
                    setOneofPresent(t8, iNumberAt, i9);
                    break;
                }
                break;
            case 60:
                mergeOneofMessage(t8, t9, i9);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(t9, iNumberAt, i9)) {
                    UnsafeUtil.putObject(t8, jOffset, UnsafeUtil.getObject(t9, jOffset));
                    setOneofPresent(t8, iNumberAt, i9);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(t8, t9, i9);
                break;
        }
    }

    public static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        return messageInfo instanceof RawMessageInfo ? newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema) : newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    public static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i9;
        boolean z8 = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i10 = 0;
        int i11 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i10++;
            } else if (fieldInfo.getType().m17838id() >= 18 && fieldInfo.getType().m17838id() <= 49) {
                i11++;
            }
        }
        int[] iArr2 = i10 > 0 ? new int[i10] : null;
        int[] iArr3 = i11 > 0 ? new int[i11] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i12 < fields.length) {
            FieldInfo fieldInfo2 = fields[i12];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i13, z8, objArr);
            if (i14 < checkInitialized.length && checkInitialized[i14] == fieldNumber3) {
                checkInitialized[i14] = i13;
                i14++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i15] = i13;
                i15++;
            } else {
                if (fieldInfo2.getType().m17838id() >= 18 && fieldInfo2.getType().m17838id() <= 49) {
                    i9 = i13;
                    iArr3[i16] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                    i16++;
                }
                i12++;
                i13 = i9 + 3;
            }
            i9 = i13;
            i12++;
            i13 = i9 + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z8, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x039e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i9;
        int iCharAt;
        int iCharAt2;
        int iCharAt3;
        int i10;
        int i11;
        int[] iArr;
        int i12;
        int i13;
        char cCharAt;
        int i14;
        char cCharAt2;
        int i15;
        char cCharAt3;
        int i16;
        char cCharAt4;
        int i17;
        char cCharAt5;
        int i18;
        char cCharAt6;
        int i19;
        char cCharAt7;
        int i20;
        char cCharAt8;
        int i21;
        int i22;
        boolean z8;
        int i23;
        int[] iArr2;
        int i24;
        int i25;
        String str;
        Class<?> cls;
        int i26;
        int i27;
        int i28;
        int iObjectFieldOffset;
        int i29;
        int i30;
        java.lang.reflect.Field fieldReflectField;
        int i31;
        char cCharAt9;
        int i32;
        int i33;
        Object obj;
        java.lang.reflect.Field fieldReflectField2;
        Object obj2;
        java.lang.reflect.Field fieldReflectField3;
        int i34;
        char cCharAt10;
        int i35;
        char cCharAt11;
        int i36;
        char cCharAt12;
        int i37;
        char cCharAt13;
        char cCharAt14;
        int i38 = 0;
        boolean z9 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
        int length = stringInfo.length();
        int iCharAt4 = stringInfo.charAt(0);
        if (iCharAt4 >= 55296) {
            int i39 = iCharAt4 & 8191;
            int i40 = 1;
            int i41 = 13;
            while (true) {
                i9 = i40 + 1;
                cCharAt14 = stringInfo.charAt(i40);
                if (cCharAt14 < 55296) {
                    break;
                }
                i39 |= (cCharAt14 & 8191) << i41;
                i41 += 13;
                i40 = i9;
            }
            iCharAt4 = i39 | (cCharAt14 << i41);
        } else {
            i9 = 1;
        }
        int i42 = i9 + 1;
        int iCharAt5 = stringInfo.charAt(i9);
        if (iCharAt5 >= 55296) {
            int i43 = iCharAt5 & 8191;
            int i44 = 13;
            while (true) {
                i37 = i42 + 1;
                cCharAt13 = stringInfo.charAt(i42);
                if (cCharAt13 < 55296) {
                    break;
                }
                i43 |= (cCharAt13 & 8191) << i44;
                i44 += 13;
                i42 = i37;
            }
            iCharAt5 = i43 | (cCharAt13 << i44);
            i42 = i37;
        }
        if (iCharAt5 == 0) {
            i12 = 0;
            iCharAt = 0;
            iCharAt2 = 0;
            i10 = 0;
            iCharAt3 = 0;
            iArr = EMPTY_INT_ARRAY;
            i11 = 0;
        } else {
            int i45 = i42 + 1;
            int iCharAt6 = stringInfo.charAt(i42);
            if (iCharAt6 >= 55296) {
                int i46 = iCharAt6 & 8191;
                int i47 = 13;
                while (true) {
                    i20 = i45 + 1;
                    cCharAt8 = stringInfo.charAt(i45);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt8 & 8191) << i47;
                    i47 += 13;
                    i45 = i20;
                }
                iCharAt6 = i46 | (cCharAt8 << i47);
                i45 = i20;
            }
            int i48 = i45 + 1;
            int iCharAt7 = stringInfo.charAt(i45);
            if (iCharAt7 >= 55296) {
                int i49 = iCharAt7 & 8191;
                int i50 = 13;
                while (true) {
                    i19 = i48 + 1;
                    cCharAt7 = stringInfo.charAt(i48);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt7 & 8191) << i50;
                    i50 += 13;
                    i48 = i19;
                }
                iCharAt7 = i49 | (cCharAt7 << i50);
                i48 = i19;
            }
            int i51 = i48 + 1;
            int iCharAt8 = stringInfo.charAt(i48);
            if (iCharAt8 >= 55296) {
                int i52 = iCharAt8 & 8191;
                int i53 = 13;
                while (true) {
                    i18 = i51 + 1;
                    cCharAt6 = stringInfo.charAt(i51);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt6 & 8191) << i53;
                    i53 += 13;
                    i51 = i18;
                }
                iCharAt8 = i52 | (cCharAt6 << i53);
                i51 = i18;
            }
            int i54 = i51 + 1;
            iCharAt = stringInfo.charAt(i51);
            if (iCharAt >= 55296) {
                int i55 = iCharAt & 8191;
                int i56 = 13;
                while (true) {
                    i17 = i54 + 1;
                    cCharAt5 = stringInfo.charAt(i54);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i55 |= (cCharAt5 & 8191) << i56;
                    i56 += 13;
                    i54 = i17;
                }
                iCharAt = i55 | (cCharAt5 << i56);
                i54 = i17;
            }
            int i57 = i54 + 1;
            iCharAt2 = stringInfo.charAt(i54);
            if (iCharAt2 >= 55296) {
                int i58 = iCharAt2 & 8191;
                int i59 = 13;
                while (true) {
                    i16 = i57 + 1;
                    cCharAt4 = stringInfo.charAt(i57);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i58 |= (cCharAt4 & 8191) << i59;
                    i59 += 13;
                    i57 = i16;
                }
                iCharAt2 = i58 | (cCharAt4 << i59);
                i57 = i16;
            }
            int i60 = i57 + 1;
            int iCharAt9 = stringInfo.charAt(i57);
            if (iCharAt9 >= 55296) {
                int i61 = iCharAt9 & 8191;
                int i62 = 13;
                while (true) {
                    i15 = i60 + 1;
                    cCharAt3 = stringInfo.charAt(i60);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i61 |= (cCharAt3 & 8191) << i62;
                    i62 += 13;
                    i60 = i15;
                }
                iCharAt9 = i61 | (cCharAt3 << i62);
                i60 = i15;
            }
            int i63 = i60 + 1;
            int iCharAt10 = stringInfo.charAt(i60);
            if (iCharAt10 >= 55296) {
                int i64 = iCharAt10 & 8191;
                int i65 = 13;
                while (true) {
                    i14 = i63 + 1;
                    cCharAt2 = stringInfo.charAt(i63);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i64 |= (cCharAt2 & 8191) << i65;
                    i65 += 13;
                    i63 = i14;
                }
                iCharAt10 = i64 | (cCharAt2 << i65);
                i63 = i14;
            }
            int i66 = i63 + 1;
            iCharAt3 = stringInfo.charAt(i63);
            if (iCharAt3 >= 55296) {
                int i67 = iCharAt3 & 8191;
                int i68 = i66;
                int i69 = 13;
                while (true) {
                    i13 = i68 + 1;
                    cCharAt = stringInfo.charAt(i68);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i67 |= (cCharAt & 8191) << i69;
                    i69 += 13;
                    i68 = i13;
                }
                iCharAt3 = i67 | (cCharAt << i69);
                i66 = i13;
            }
            int[] iArr3 = new int[iCharAt3 + iCharAt9 + iCharAt10];
            i10 = (iCharAt6 * 2) + iCharAt7;
            i11 = iCharAt6;
            i42 = i66;
            int i70 = iCharAt9;
            iArr = iArr3;
            i38 = iCharAt8;
            i12 = i70;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = rawMessageInfo.getObjects();
        Class<?> cls2 = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr4 = new int[iCharAt2 * 3];
        Object[] objArr = new Object[iCharAt2 * 2];
        int i71 = iCharAt3 + i12;
        int i72 = iCharAt3;
        int i73 = i71;
        int i74 = 0;
        int i75 = 0;
        while (i42 < length) {
            int i76 = i42 + 1;
            int iCharAt11 = stringInfo.charAt(i42);
            int i77 = length;
            if (iCharAt11 >= 55296) {
                int i78 = iCharAt11 & 8191;
                int i79 = i76;
                int i80 = 13;
                while (true) {
                    i36 = i79 + 1;
                    cCharAt12 = stringInfo.charAt(i79);
                    i21 = iCharAt3;
                    if (cCharAt12 < 55296) {
                        break;
                    }
                    i78 |= (cCharAt12 & 8191) << i80;
                    i80 += 13;
                    i79 = i36;
                    iCharAt3 = i21;
                }
                iCharAt11 = i78 | (cCharAt12 << i80);
                i22 = i36;
            } else {
                i21 = iCharAt3;
                i22 = i76;
            }
            int i81 = i22 + 1;
            int iCharAt12 = stringInfo.charAt(i22);
            if (iCharAt12 >= 55296) {
                int i82 = iCharAt12 & 8191;
                int i83 = i81;
                int i84 = 13;
                while (true) {
                    i35 = i83 + 1;
                    cCharAt11 = stringInfo.charAt(i83);
                    z8 = z9;
                    if (cCharAt11 < 55296) {
                        break;
                    }
                    i82 |= (cCharAt11 & 8191) << i84;
                    i84 += 13;
                    i83 = i35;
                    z9 = z8;
                }
                iCharAt12 = i82 | (cCharAt11 << i84);
                i23 = i35;
            } else {
                z8 = z9;
                i23 = i81;
            }
            int i85 = iCharAt12 & 255;
            int i86 = iCharAt;
            if ((iCharAt12 & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0) {
                iArr[i74] = i75;
                i74++;
            }
            int i87 = i74;
            if (i85 >= 51) {
                int i88 = i23 + 1;
                int iCharAt13 = stringInfo.charAt(i23);
                char c9 = 55296;
                if (iCharAt13 >= 55296) {
                    int i89 = iCharAt13 & 8191;
                    int i90 = 13;
                    while (true) {
                        i34 = i88 + 1;
                        cCharAt10 = stringInfo.charAt(i88);
                        if (cCharAt10 < c9) {
                            break;
                        }
                        i89 |= (cCharAt10 & 8191) << i90;
                        i90 += 13;
                        i88 = i34;
                        c9 = 55296;
                    }
                    iCharAt13 = i89 | (cCharAt10 << i90);
                    i88 = i34;
                }
                int i91 = i85 - 51;
                int i92 = i88;
                if (i91 == 9 || i91 == 17) {
                    i33 = i10 + 1;
                    objArr[((i75 / 3) * 2) + 1] = objects[i10];
                } else {
                    if (i91 == 12 && (iCharAt4 & 1) == 1) {
                        i33 = i10 + 1;
                        objArr[((i75 / 3) * 2) + 1] = objects[i10];
                    }
                    int i93 = iCharAt13 * 2;
                    obj = objects[i93];
                    if (obj instanceof java.lang.reflect.Field) {
                        fieldReflectField2 = reflectField(cls2, (String) obj);
                        objects[i93] = fieldReflectField2;
                    } else {
                        fieldReflectField2 = (java.lang.reflect.Field) obj;
                    }
                    iArr2 = iArr4;
                    i24 = iCharAt11;
                    int iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                    int i94 = i93 + 1;
                    obj2 = objects[i94];
                    if (obj2 instanceof java.lang.reflect.Field) {
                        fieldReflectField3 = reflectField(cls2, (String) obj2);
                        objects[i94] = fieldReflectField3;
                    } else {
                        fieldReflectField3 = (java.lang.reflect.Field) obj2;
                    }
                    str = stringInfo;
                    cls = cls2;
                    i26 = iCharAt4;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField3);
                    i30 = iObjectFieldOffset2;
                    i27 = i85;
                    i42 = i92;
                    i29 = 0;
                }
                i10 = i33;
                int i932 = iCharAt13 * 2;
                obj = objects[i932];
                if (obj instanceof java.lang.reflect.Field) {
                }
                iArr2 = iArr4;
                i24 = iCharAt11;
                int iObjectFieldOffset22 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                int i942 = i932 + 1;
                obj2 = objects[i942];
                if (obj2 instanceof java.lang.reflect.Field) {
                }
                str = stringInfo;
                cls = cls2;
                i26 = iCharAt4;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField3);
                i30 = iObjectFieldOffset22;
                i27 = i85;
                i42 = i92;
                i29 = 0;
            } else {
                iArr2 = iArr4;
                i24 = iCharAt11;
                int i95 = i10 + 1;
                java.lang.reflect.Field fieldReflectField4 = reflectField(cls2, (String) objects[i10]);
                if (i85 == 9 || i85 == 17) {
                    objArr[((i75 / 3) * 2) + 1] = fieldReflectField4.getType();
                } else {
                    if (i85 == 27 || i85 == 49) {
                        i32 = i95 + 1;
                        objArr[((i75 / 3) * 2) + 1] = objects[i95];
                    } else if (i85 == 12 || i85 == 30 || i85 == 44) {
                        if ((iCharAt4 & 1) == 1) {
                            i32 = i95 + 1;
                            objArr[((i75 / 3) * 2) + 1] = objects[i95];
                        }
                    } else if (i85 == 50) {
                        int i96 = i72 + 1;
                        iArr[i72] = i75;
                        int i97 = (i75 / 3) * 2;
                        int i98 = i95 + 1;
                        objArr[i97] = objects[i95];
                        if ((iCharAt12 & 2048) != 0) {
                            i95 = i98 + 1;
                            objArr[i97 + 1] = objects[i98];
                            i72 = i96;
                        } else {
                            i72 = i96;
                            i95 = i98;
                        }
                    }
                    i25 = i85;
                    i95 = i32;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldReflectField4);
                    if ((iCharAt4 & 1) != 1) {
                        i27 = i25;
                        if (i27 <= 17) {
                            i28 = i23 + 1;
                            int iCharAt14 = stringInfo.charAt(i23);
                            if (iCharAt14 >= 55296) {
                                int i99 = iCharAt14 & 8191;
                                int i100 = 13;
                                while (true) {
                                    i31 = i28 + 1;
                                    cCharAt9 = stringInfo.charAt(i28);
                                    if (cCharAt9 < 55296) {
                                        break;
                                    }
                                    i99 |= (cCharAt9 & 8191) << i100;
                                    i100 += 13;
                                    i28 = i31;
                                }
                                iCharAt14 = i99 | (cCharAt9 << i100);
                                i28 = i31;
                            }
                            int i101 = (i11 * 2) + (iCharAt14 / 32);
                            Object obj3 = objects[i101];
                            str = stringInfo;
                            if (obj3 instanceof java.lang.reflect.Field) {
                                fieldReflectField = (java.lang.reflect.Field) obj3;
                            } else {
                                fieldReflectField = reflectField(cls2, (String) obj3);
                                objects[i101] = fieldReflectField;
                            }
                            cls = cls2;
                            i26 = iCharAt4;
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField);
                            i29 = iCharAt14 % 32;
                            if (i27 >= 18 && i27 <= 49) {
                                iArr[i73] = iObjectFieldOffset3;
                                i73++;
                            }
                            int i102 = i28;
                            i10 = i95;
                            i30 = iObjectFieldOffset3;
                            i42 = i102;
                        } else {
                            str = stringInfo;
                            cls = cls2;
                            i26 = iCharAt4;
                        }
                    } else {
                        str = stringInfo;
                        cls = cls2;
                        i26 = iCharAt4;
                        i27 = i25;
                    }
                    i28 = i23;
                    iObjectFieldOffset = 0;
                    i29 = 0;
                    if (i27 >= 18) {
                        iArr[i73] = iObjectFieldOffset3;
                        i73++;
                    }
                    int i1022 = i28;
                    i10 = i95;
                    i30 = iObjectFieldOffset3;
                    i42 = i1022;
                }
                i25 = i85;
                int iObjectFieldOffset32 = (int) unsafe.objectFieldOffset(fieldReflectField4);
                if ((iCharAt4 & 1) != 1) {
                }
                i28 = i23;
                iObjectFieldOffset = 0;
                i29 = 0;
                if (i27 >= 18) {
                }
                int i10222 = i28;
                i10 = i95;
                i30 = iObjectFieldOffset32;
                i42 = i10222;
            }
            int i103 = i75 + 1;
            iArr2[i75] = i24;
            int i104 = i103 + 1;
            int i105 = i26;
            iArr2[i103] = ((iCharAt12 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | ((iCharAt12 & 256) != 0 ? 268435456 : 0) | (i27 << 20) | i30;
            int i106 = i104 + 1;
            iArr2[i104] = (i29 << 20) | iObjectFieldOffset;
            iArr4 = iArr2;
            cls2 = cls;
            iCharAt4 = i105;
            iCharAt = i86;
            length = i77;
            iCharAt3 = i21;
            z9 = z8;
            i74 = i87;
            i75 = i106;
            stringInfo = str;
        }
        return new MessageSchema<>(iArr4, objArr, i38, iCharAt, rawMessageInfo.getDefaultInstance(), z9, false, iArr, iCharAt3, i71, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private int numberAt(int i9) {
        return this.buffer[i9];
    }

    private static long offset(int i9) {
        return i9 & OFFSET_MASK;
    }

    private static <T> boolean oneofBooleanAt(T t8, long j9) {
        return ((Boolean) UnsafeUtil.getObject(t8, j9)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T t8, long j9) {
        return ((Double) UnsafeUtil.getObject(t8, j9)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t8, long j9) {
        return ((Float) UnsafeUtil.getObject(t8, j9)).floatValue();
    }

    private static <T> int oneofIntAt(T t8, long j9) {
        return ((Integer) UnsafeUtil.getObject(t8, j9)).intValue();
    }

    private static <T> long oneofLongAt(T t8, long j9) {
        return ((Long) UnsafeUtil.getObject(t8, j9)).longValue();
    }

    private <K, V> int parseMapField(T t8, byte[] bArr, int i9, int i10, int i11, long j9, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i11);
        Object object = unsafe.getObject(t8, j9);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            unsafe.putObject(t8, j9, objNewMapField);
            object = objNewMapField;
        }
        return decodeMapEntry(bArr, i9, i10, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t8, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j9, int i16, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        Unsafe unsafe = UNSAFE;
        long j10 = this.buffer[i16 + 2] & OFFSET_MASK;
        switch (i15) {
            case 51:
                if (i13 == 1) {
                    unsafe.putObject(t8, j9, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i9)));
                    int i17 = i9 + 8;
                    unsafe.putInt(t8, j10, i12);
                    return i17;
                }
                break;
            case 52:
                if (i13 == 5) {
                    unsafe.putObject(t8, j9, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i9)));
                    int i18 = i9 + 4;
                    unsafe.putInt(t8, j10, i12);
                    return i18;
                }
                break;
            case 53:
            case 54:
                if (i13 == 0) {
                    int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                    unsafe.putObject(t8, j9, Long.valueOf(registers.long1));
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint64;
                }
                break;
            case 55:
            case 62:
                if (i13 == 0) {
                    int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                    unsafe.putObject(t8, j9, Integer.valueOf(registers.int1));
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint32;
                }
                break;
            case 56:
            case 65:
                if (i13 == 1) {
                    unsafe.putObject(t8, j9, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i9)));
                    int i19 = i9 + 8;
                    unsafe.putInt(t8, j10, i12);
                    return i19;
                }
                break;
            case 57:
            case 64:
                if (i13 == 5) {
                    unsafe.putObject(t8, j9, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i9)));
                    int i20 = i9 + 4;
                    unsafe.putInt(t8, j10, i12);
                    return i20;
                }
                break;
            case 58:
                if (i13 == 0) {
                    int iDecodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                    unsafe.putObject(t8, j9, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint642;
                }
                break;
            case 59:
                if (i13 == 2) {
                    int iDecodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                    int i21 = registers.int1;
                    if (i21 == 0) {
                        unsafe.putObject(t8, j9, "");
                    } else {
                        if ((i14 & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(bArr, iDecodeVarint322, iDecodeVarint322 + i21)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        unsafe.putObject(t8, j9, new String(bArr, iDecodeVarint322, i21, Internal.UTF_8));
                        iDecodeVarint322 += i21;
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint322;
                }
                break;
            case 60:
                if (i13 == 2) {
                    int iDecodeMessageField = ArrayDecoders.decodeMessageField(getMessageFieldSchema(i16), bArr, i9, i10, registers);
                    Object object = unsafe.getInt(t8, j10) == i12 ? unsafe.getObject(t8, j9) : null;
                    if (object == null) {
                        unsafe.putObject(t8, j9, registers.object1);
                    } else {
                        unsafe.putObject(t8, j9, Internal.mergeMessage(object, registers.object1));
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeMessageField;
                }
                break;
            case 61:
                if (i13 == 2) {
                    int iDecodeBytes = ArrayDecoders.decodeBytes(bArr, i9, registers);
                    unsafe.putObject(t8, j9, registers.object1);
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeBytes;
                }
                break;
            case 63:
                if (i13 == 0) {
                    int iDecodeVarint323 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                    int i22 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i16);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i22)) {
                        unsafe.putObject(t8, j9, Integer.valueOf(i22));
                        unsafe.putInt(t8, j10, i12);
                    } else {
                        getMutableUnknownFields(t8).storeField(i11, Long.valueOf(i22));
                    }
                    return iDecodeVarint323;
                }
                break;
            case 66:
                if (i13 == 0) {
                    int iDecodeVarint324 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
                    unsafe.putObject(t8, j9, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint324;
                }
                break;
            case 67:
                if (i13 == 0) {
                    int iDecodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i9, registers);
                    unsafe.putObject(t8, j9, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeVarint643;
                }
                break;
            case 68:
                if (i13 == 3) {
                    int iDecodeGroupField = ArrayDecoders.decodeGroupField(getMessageFieldSchema(i16), bArr, i9, i10, (i11 & (-8)) | 4, registers);
                    Object object2 = unsafe.getInt(t8, j10) == i12 ? unsafe.getObject(t8, j9) : null;
                    if (object2 == null) {
                        unsafe.putObject(t8, j9, registers.object1);
                    } else {
                        unsafe.putObject(t8, j9, Internal.mergeMessage(object2, registers.object1));
                    }
                    unsafe.putInt(t8, j10, i12);
                    return iDecodeGroupField;
                }
                break;
        }
        return i9;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x022b, code lost:
    
        if (r0 != r15) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x022e, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01de, code lost:
    
        if (r0 != r15) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x020c, code lost:
    
        if (r0 != r15) goto L106;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x005c. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int parseProto3Message(T t8, byte[] bArr, int i9, int i10, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        byte b9;
        int iDecodeVarint32;
        int i11;
        int i12;
        Unsafe unsafe;
        int i13;
        int i14;
        int i15;
        int i16;
        int iDecodeVarint64;
        MessageSchema<T> messageSchema = this;
        T t9 = t8;
        byte[] bArr2 = bArr;
        int i17 = i10;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i18 = -1;
        int iDecodeUnknownField = i9;
        int i19 = -1;
        int i20 = 0;
        while (iDecodeUnknownField < i17) {
            int i21 = iDecodeUnknownField + 1;
            byte b10 = bArr2[iDecodeUnknownField];
            if (b10 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(b10, bArr2, i21, registers2);
                b9 = registers2.int1;
            } else {
                b9 = b10;
                iDecodeVarint32 = i21;
            }
            int i22 = b9 >>> 3;
            int i23 = b9 & 7;
            int iPositionForFieldNumber = i22 > i19 ? messageSchema.positionForFieldNumber(i22, i20 / 3) : messageSchema.positionForFieldNumber(i22);
            if (iPositionForFieldNumber == i18) {
                i11 = i22;
                i12 = iDecodeVarint32;
                unsafe = unsafe2;
                i13 = i18;
                i14 = 0;
            } else {
                int i24 = messageSchema.buffer[iPositionForFieldNumber + 1];
                int iType = type(i24);
                long jOffset = offset(i24);
                if (iType <= 17) {
                    switch (iType) {
                        case 0:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 1) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                UnsafeUtil.putDouble(t9, jOffset, ArrayDecoders.decodeDouble(bArr2, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 8;
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 1:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 5) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                UnsafeUtil.putFloat(t9, jOffset, ArrayDecoders.decodeFloat(bArr2, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 4;
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 2:
                        case 3:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 0) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putLong(t8, jOffset, registers2.long1);
                                iDecodeUnknownField = iDecodeVarint64;
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 4:
                        case 11:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 0) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putInt(t9, jOffset, registers2.int1);
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 5:
                        case 14:
                            if (i23 != 1) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                i16 = iPositionForFieldNumber;
                                unsafe2.putLong(t8, jOffset, ArrayDecoders.decodeFixed64(bArr2, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 8;
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 6:
                        case 13:
                            if (i23 != 5) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                unsafe2.putInt(t9, jOffset, ArrayDecoders.decodeFixed32(bArr2, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 4;
                                i20 = iPositionForFieldNumber;
                                i19 = i22;
                                i18 = -1;
                                break;
                            }
                        case 7:
                            if (i23 != 0) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                int iDecodeVarint642 = ArrayDecoders.decodeVarint64(bArr2, iDecodeVarint32, registers2);
                                UnsafeUtil.putBoolean(t9, jOffset, registers2.long1 != 0);
                                iDecodeUnknownField = iDecodeVarint642;
                                i20 = iPositionForFieldNumber;
                                i19 = i22;
                                i18 = -1;
                                break;
                            }
                        case 8:
                            if (i23 != 2) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = (ENFORCE_UTF8_MASK & i24) == 0 ? ArrayDecoders.decodeString(bArr2, iDecodeVarint32, registers2) : ArrayDecoders.decodeStringRequireUtf8(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putObject(t9, jOffset, registers2.object1);
                                i20 = iPositionForFieldNumber;
                                i19 = i22;
                                i18 = -1;
                                break;
                            }
                        case 9:
                            if (i23 != 2) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(iPositionForFieldNumber), bArr2, iDecodeVarint32, i17, registers2);
                                Object object = unsafe2.getObject(t9, jOffset);
                                if (object == null) {
                                    unsafe2.putObject(t9, jOffset, registers2.object1);
                                } else {
                                    unsafe2.putObject(t9, jOffset, Internal.mergeMessage(object, registers2.object1));
                                }
                                i20 = iPositionForFieldNumber;
                                i19 = i22;
                                i18 = -1;
                                break;
                            }
                        case 10:
                            if (i23 != 2) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeBytes(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putObject(t9, jOffset, registers2.object1);
                                i20 = iPositionForFieldNumber;
                                i19 = i22;
                                i18 = -1;
                                break;
                            }
                        case 12:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 0) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putInt(t9, jOffset, registers2.int1);
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 15:
                            i16 = iPositionForFieldNumber;
                            if (i23 != 0) {
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i14 = i16;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, iDecodeVarint32, registers2);
                                unsafe2.putInt(t9, jOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        case 16:
                            if (i23 != 0) {
                                i14 = iPositionForFieldNumber;
                                i11 = i22;
                                i15 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i13 = -1;
                                break;
                            } else {
                                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, iDecodeVarint32, registers2);
                                i16 = iPositionForFieldNumber;
                                unsafe2.putLong(t8, jOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                iDecodeUnknownField = iDecodeVarint64;
                                i19 = i22;
                                i20 = i16;
                                i18 = -1;
                                break;
                            }
                        default:
                            i14 = iPositionForFieldNumber;
                            i11 = i22;
                            i15 = iDecodeVarint32;
                            unsafe = unsafe2;
                            i13 = -1;
                            break;
                    }
                } else if (iType != 27) {
                    i14 = iPositionForFieldNumber;
                    if (iType <= 49) {
                        i11 = i22;
                        int i25 = iDecodeVarint32;
                        unsafe = unsafe2;
                        i13 = -1;
                        iDecodeUnknownField = parseRepeatedField(t8, bArr, iDecodeVarint32, i10, b9, i22, i23, i14, i24, iType, jOffset, registers);
                    } else {
                        i11 = i22;
                        i15 = iDecodeVarint32;
                        unsafe = unsafe2;
                        i13 = -1;
                        if (iType != 50) {
                            iDecodeUnknownField = parseOneofField(t8, bArr, i15, i10, b9, i11, i23, i24, iType, jOffset, i14, registers);
                        } else if (i23 == 2) {
                            iDecodeUnknownField = parseMapField(t8, bArr, i15, i10, i14, jOffset, registers);
                        }
                    }
                    messageSchema = this;
                    t9 = t8;
                    bArr2 = bArr;
                    i17 = i10;
                    registers2 = registers;
                    unsafe2 = unsafe;
                    i20 = i14;
                    i19 = i11;
                    i18 = i13;
                } else if (i23 == 2) {
                    Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe2.getObject(t9, jOffset);
                    if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                        int size = protobufListMutableCopyWithCapacity2.size();
                        protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                        unsafe2.putObject(t9, jOffset, protobufListMutableCopyWithCapacity2);
                    }
                    iDecodeUnknownField = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(iPositionForFieldNumber), b9, bArr, iDecodeVarint32, i10, protobufListMutableCopyWithCapacity2, registers);
                    i19 = i22;
                    i20 = iPositionForFieldNumber;
                    i18 = -1;
                } else {
                    i14 = iPositionForFieldNumber;
                    i11 = i22;
                    i15 = iDecodeVarint32;
                    unsafe = unsafe2;
                    i13 = -1;
                }
                i12 = i15;
            }
            iDecodeUnknownField = ArrayDecoders.decodeUnknownField(b9, bArr, i12, i10, getMutableUnknownFields(t8), registers);
            messageSchema = this;
            t9 = t8;
            bArr2 = bArr;
            i17 = i10;
            registers2 = registers;
            unsafe2 = unsafe;
            i20 = i14;
            i19 = i11;
            i18 = i13;
        }
        if (iDecodeUnknownField == i17) {
            return iDecodeUnknownField;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int parseRepeatedField(T t8, byte[] bArr, int i9, int i10, int i11, int i12, int i13, int i14, long j9, int i15, long j10, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe.getObject(t8, j10);
        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
            int size = protobufListMutableCopyWithCapacity2.size();
            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(t8, j10, protobufListMutableCopyWithCapacity2);
        }
        switch (i15) {
            case 18:
            case 35:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 1) {
                    return ArrayDecoders.decodeDoubleList(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 19:
            case 36:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 5) {
                    return ArrayDecoders.decodeFloatList(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 0) {
                    return ArrayDecoders.decodeVarint64List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 0) {
                    return ArrayDecoders.decodeVarint32List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 1) {
                    return ArrayDecoders.decodeFixed64List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 5) {
                    return ArrayDecoders.decodeFixed32List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 25:
            case 42:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 0) {
                    return ArrayDecoders.decodeBoolList(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 26:
                if (i13 == 2) {
                    return (j9 & 536870912) == 0 ? ArrayDecoders.decodeStringList(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers) : ArrayDecoders.decodeStringListRequireUtf8(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 27:
                if (i13 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i14), i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 28:
                if (i13 == 2) {
                    return ArrayDecoders.decodeBytesList(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 30:
            case 44:
                if (i13 == 2) {
                    iDecodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                } else if (i13 == 0) {
                    iDecodeVarint32List = ArrayDecoders.decodeVarint32List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t8;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(i12, (List<Integer>) protobufListMutableCopyWithCapacity2, getEnumFieldVerifier(i14), unknownFieldSetLite, (UnknownFieldSchema<UT, UnknownFieldSetLite>) this.unknownFieldSchema);
                if (unknownFieldSetLite2 != null) {
                    generatedMessageLite.unknownFields = unknownFieldSetLite2;
                }
                return iDecodeVarint32List;
            case 33:
            case 47:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 0) {
                    return ArrayDecoders.decodeSInt32List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 34:
            case 48:
                if (i13 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i9, protobufListMutableCopyWithCapacity2, registers);
                }
                if (i13 == 0) {
                    return ArrayDecoders.decodeSInt64List(i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
            case 49:
                if (i13 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i14), i11, bArr, i9, i10, protobufListMutableCopyWithCapacity2, registers);
                }
                break;
        }
        return i9;
    }

    private int positionForFieldNumber(int i9) {
        if (i9 < this.minFieldNumber || i9 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i9, 0);
    }

    private int presenceMaskAndOffsetAt(int i9) {
        return this.buffer[i9 + 2];
    }

    private <E> void readGroupList(Object obj, long j9, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j9), schema, extensionRegistryLite);
    }

    private <E> void readMessageList(Object obj, int i9, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i9)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i9, Reader reader) {
        if (isEnforceUtf8(i9)) {
            UnsafeUtil.putObject(obj, offset(i9), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i9), reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i9), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i9, Reader reader) {
        if (isEnforceUtf8(i9)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i9)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i9)));
        }
    }

    private static java.lang.reflect.Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private void setFieldPresent(T t8, int i9) {
        if (this.proto3) {
            return;
        }
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i9);
        long j9 = iPresenceMaskAndOffsetAt & OFFSET_MASK;
        UnsafeUtil.putInt(t8, j9, UnsafeUtil.getInt(t8, j9) | (1 << (iPresenceMaskAndOffsetAt >>> 20)));
    }

    private void setOneofPresent(T t8, int i9, int i10) {
        UnsafeUtil.putInt(t8, presenceMaskAndOffsetAt(i10) & OFFSET_MASK, i9);
    }

    private int slowPositionForFieldNumber(int i9, int i10) {
        int length = (this.buffer.length / 3) - 1;
        while (i10 <= length) {
            int i11 = (length + i10) >>> 1;
            int i12 = i11 * 3;
            int iNumberAt = numberAt(i12);
            if (i9 == iNumberAt) {
                return i12;
            }
            if (i9 < iNumberAt) {
                length = i11 - 1;
            } else {
                i10 = i11 + 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i9, boolean z8, Object[] objArr) {
        int iObjectFieldOffset;
        int iObjectFieldOffset2;
        int iM17838id;
        int iNumberOfTrailingZeros;
        int i10;
        int i11;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof == null) {
            FieldType type = fieldInfo.getType();
            iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            int iM17838id2 = type.m17838id();
            if (!z8 && !type.isList() && !type.isMap()) {
                int iObjectFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getPresenceField());
                iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
                iM17838id = iM17838id2;
                i10 = iObjectFieldOffset;
                i11 = iObjectFieldOffset3;
            } else if (fieldInfo.getCachedSizeField() == null) {
                iNumberOfTrailingZeros = 0;
                iM17838id = iM17838id2;
                i10 = iObjectFieldOffset;
                i11 = 0;
            } else {
                iObjectFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getCachedSizeField());
                iM17838id = iM17838id2;
            }
            iArr[i9] = fieldInfo.getFieldNumber();
            iArr[i9 + 1] = (iM17838id << 20) | (fieldInfo.isRequired() ? 268435456 : 0) | (!fieldInfo.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | i10;
            iArr[i9 + 2] = (iNumberOfTrailingZeros << 20) | i11;
            Class<?> messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objArr[((i9 / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fieldInfo.getEnumVerifier() != null) {
                        objArr[((i9 / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            int i12 = (i9 / 3) * 2;
            objArr[i12] = fieldInfo.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objArr[i12 + 1] = messageFieldClass;
                return;
            } else {
                if (fieldInfo.getEnumVerifier() != null) {
                    objArr[i12 + 1] = fieldInfo.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        iM17838id = fieldInfo.getType().m17838id() + 51;
        iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
        iObjectFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        i10 = iObjectFieldOffset;
        i11 = iObjectFieldOffset2;
        iNumberOfTrailingZeros = 0;
        iArr[i9] = fieldInfo.getFieldNumber();
        if (!fieldInfo.isEnforceUtf8()) {
        }
        iArr[i9 + 1] = (iM17838id << 20) | (fieldInfo.isRequired() ? 268435456 : 0) | (!fieldInfo.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | i10;
        iArr[i9 + 2] = (iNumberOfTrailingZeros << 20) | i11;
        Class<?> messageFieldClass2 = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
        }
    }

    private static int type(int i9) {
        return (i9 & FIELD_TYPE_MASK) >>> 20;
    }

    private int typeAndOffsetAt(int i9) {
        return this.buffer[i9 + 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto2(T t8, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        Map.Entry<?, ?> entry2;
        int i9;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t8);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        }
        int length = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int i10 = -1;
        int i11 = 0;
        int i12 = 0;
        while (i11 < length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i11);
            int iNumberAt = numberAt(i11);
            int iType = type(iTypeAndOffsetAt);
            if (this.proto3 || iType > 17) {
                entry2 = entry;
                i9 = 0;
            } else {
                int i13 = this.buffer[i11 + 2];
                int i14 = i13 & OFFSET_MASK;
                Map.Entry<?, ?> entry3 = entry;
                if (i14 != i10) {
                    i12 = unsafe.getInt(t8, i14);
                    i10 = i14;
                }
                i9 = 1 << (i13 >>> 20);
                entry2 = entry3;
            }
            while (entry2 != null && this.extensionSchema.extensionNumber(entry2) <= iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            Map.Entry<?, ?> entry4 = entry2;
            int i15 = length;
            long jOffset = offset(iTypeAndOffsetAt);
            switch (iType) {
                case 0:
                    if ((i9 & i12) != 0) {
                        writer.writeDouble(iNumberAt, doubleAt(t8, jOffset));
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 1:
                    if ((i9 & i12) != 0) {
                        writer.writeFloat(iNumberAt, floatAt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 2:
                    if ((i9 & i12) != 0) {
                        writer.writeInt64(iNumberAt, unsafe.getLong(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 3:
                    if ((i9 & i12) != 0) {
                        writer.writeUInt64(iNumberAt, unsafe.getLong(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 4:
                    if ((i9 & i12) != 0) {
                        writer.writeInt32(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 5:
                    if ((i9 & i12) != 0) {
                        writer.writeFixed64(iNumberAt, unsafe.getLong(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 6:
                    if ((i9 & i12) != 0) {
                        writer.writeFixed32(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 7:
                    if ((i9 & i12) != 0) {
                        writer.writeBool(iNumberAt, booleanAt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 8:
                    if ((i9 & i12) != 0) {
                        writeString(iNumberAt, unsafe.getObject(t8, jOffset), writer);
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 9:
                    if ((i9 & i12) != 0) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i11));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 10:
                    if ((i9 & i12) != 0) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 11:
                    if ((i9 & i12) != 0) {
                        writer.writeUInt32(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 12:
                    if ((i9 & i12) != 0) {
                        writer.writeEnum(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 13:
                    if ((i9 & i12) != 0) {
                        writer.writeSFixed32(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 14:
                    if ((i9 & i12) != 0) {
                        writer.writeSFixed64(iNumberAt, unsafe.getLong(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 15:
                    if ((i9 & i12) != 0) {
                        writer.writeSInt32(iNumberAt, unsafe.getInt(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 16:
                    if ((i9 & i12) != 0) {
                        writer.writeSInt64(iNumberAt, unsafe.getLong(t8, jOffset));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 17:
                    if ((i9 & i12) != 0) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i11));
                    } else {
                        continue;
                    }
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 26:
                    SchemaUtil.writeStringList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, getMessageFieldSchema(i11));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, false);
                    continue;
                    i11 += 3;
                    length = i15;
                    entry = entry4;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(i11), (List) unsafe.getObject(t8, jOffset), writer, getMessageFieldSchema(i11));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, unsafe.getObject(t8, jOffset), i11);
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(t8, jOffset));
                        break;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(t8, jOffset));
                        break;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(t8, jOffset));
                        break;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(t8, jOffset));
                        break;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(t8, jOffset));
                        break;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(t8, jOffset));
                        break;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writeString(iNumberAt, unsafe.getObject(t8, jOffset), writer);
                        break;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i11));
                        break;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(t8, jOffset));
                        break;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(t8, jOffset));
                        break;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(t8, jOffset));
                        break;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(t8, jOffset));
                        break;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(t8, jOffset), getMessageFieldSchema(i11));
                        break;
                    }
                    break;
            }
            i11 += 3;
            length = i15;
            entry = entry4;
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t8, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto3(T t8, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t8);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        }
        int length = this.buffer.length;
        for (int i9 = 0; i9 < length; i9 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i9);
            int iNumberAt = numberAt(i9);
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeDouble(iNumberAt, doubleAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeFloat(iNumberAt, floatAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeUInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeFixed64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeFixed32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeBool(iNumberAt, booleanAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(t8, i9)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i9));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeUInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeEnum(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeSFixed32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeSFixed64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeSInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeSInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(t8, i9)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i9));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i9));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(i9), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i9));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), i9);
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i9));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t8, iNumberAt, i9)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i9));
                        break;
                    } else {
                        break;
                    }
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t8, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(T t8, Writer writer) {
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry;
        writeUnknownInMessageTo(this.unknownFieldSchema, t8, writer);
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t8);
            if (extensions.isEmpty()) {
                itDescendingIterator = null;
                entry = null;
            } else {
                itDescendingIterator = extensions.descendingIterator();
                entry = (Map.Entry) itDescendingIterator.next();
            }
        }
        for (int length = this.buffer.length - 3; length >= 0; length -= 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(length);
            int iNumberAt = numberAt(length);
            while (entry != null && this.extensionSchema.extensionNumber(entry) > iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t8, length)) {
                        writer.writeDouble(iNumberAt, doubleAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(t8, length)) {
                        writer.writeFloat(iNumberAt, floatAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(t8, length)) {
                        writer.writeInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(t8, length)) {
                        writer.writeUInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(t8, length)) {
                        writer.writeInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(t8, length)) {
                        writer.writeFixed64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(t8, length)) {
                        writer.writeFixed32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(t8, length)) {
                        writer.writeBool(iNumberAt, booleanAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(t8, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(t8, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(t8, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(t8, length)) {
                        writer.writeUInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(t8, length)) {
                        writer.writeEnum(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(t8, length)) {
                        writer.writeSFixed32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(t8, length)) {
                        writer.writeSFixed64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(t8, length)) {
                        writer.writeSInt32(iNumberAt, intAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(t8, length)) {
                        writer.writeSInt64(iNumberAt, longAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(t8, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), length);
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), writer);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(t8, offset(iTypeAndOffsetAt)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t8, iNumberAt, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t8, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                        break;
                    } else {
                        break;
                    }
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int i9, Object obj, int i10) {
        if (obj != null) {
            writer.writeMap(i9, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i10)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i9, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i9, (String) obj);
        } else {
            writer.writeBytes(i9, (ByteString) obj);
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t8, Writer writer) {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t8), writer);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t8, T t9) {
        int length = this.buffer.length;
        for (int i9 = 0; i9 < length; i9 += 3) {
            if (!equals(t8, t9, i9)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t8).equals(this.unknownFieldSchema.getFromMessage(t9))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t8).equals(this.extensionSchema.getExtensions(t9));
        }
        return true;
    }

    public int getSchemaSize() {
        return this.buffer.length * 3;
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t8) {
        return this.proto3 ? getSerializedSizeProto3(t8) : getSerializedSizeProto2(t8);
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T t8) {
        int i9;
        int iHashLong;
        int length = this.buffer.length;
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i11);
            int iNumberAt = numberAt(i11);
            long jOffset = offset(iTypeAndOffsetAt);
            int iHashCode = 37;
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(t8, jOffset)));
                    i10 = i9 + iHashLong;
                    break;
                case 1:
                    i9 = i10 * 53;
                    iHashLong = Float.floatToIntBits(UnsafeUtil.getFloat(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 2:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 3:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 4:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 5:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 6:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 7:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 8:
                    i9 = i10 * 53;
                    iHashLong = ((String) UnsafeUtil.getObject(t8, jOffset)).hashCode();
                    i10 = i9 + iHashLong;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(t8, jOffset);
                    if (object != null) {
                        iHashCode = object.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 10:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                    i10 = i9 + iHashLong;
                    break;
                case 11:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 12:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 13:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 14:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 15:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getInt(t8, jOffset);
                    i10 = i9 + iHashLong;
                    break;
                case 16:
                    i9 = i10 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t8, jOffset));
                    i10 = i9 + iHashLong;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(t8, jOffset);
                    if (object2 != null) {
                        iHashCode = object2.hashCode();
                    }
                    i10 = (i10 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                    i10 = i9 + iHashLong;
                    break;
                case 50:
                    i9 = i10 * 53;
                    iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                    i10 = i9 + iHashLong;
                    break;
                case 51:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(t8, jOffset)));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Float.floatToIntBits(oneofFloatAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashBoolean(oneofBooleanAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = ((String) UnsafeUtil.getObject(t8, jOffset)).hashCode();
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = oneofIntAt(t8, jOffset);
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t8, jOffset));
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t8, iNumberAt, i11)) {
                        i9 = i10 * 53;
                        iHashLong = UnsafeUtil.getObject(t8, jOffset).hashCode();
                        i10 = i9 + iHashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i10 * 53) + this.unknownFieldSchema.getFromMessage(t8).hashCode();
        return this.hasExtensions ? (iHashCode2 * 53) + this.extensionSchema.getExtensions(t8).hashCode() : iHashCode2;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0078  */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isInitialized(T t8) {
        int i9;
        int i10 = -1;
        int i11 = 0;
        for (int i12 = 0; i12 < this.checkInitializedCount; i12++) {
            int i13 = this.intArray[i12];
            int iNumberAt = numberAt(i13);
            int iTypeAndOffsetAt = typeAndOffsetAt(i13);
            if (this.proto3) {
                i9 = 0;
            } else {
                int i14 = this.buffer[i13 + 2];
                int i15 = OFFSET_MASK & i14;
                i9 = 1 << (i14 >>> 20);
                if (i15 != i10) {
                    i11 = UNSAFE.getInt(t8, i15);
                    i10 = i15;
                }
            }
            if (isRequired(iTypeAndOffsetAt) && !isFieldPresent(t8, i13, i11, i9)) {
                return false;
            }
            int iType = type(iTypeAndOffsetAt);
            if (iType == 9 || iType == 17) {
                if (isFieldPresent(t8, i13, i11, i9) && !isInitialized(t8, iTypeAndOffsetAt, getMessageFieldSchema(i13))) {
                    return false;
                }
            } else if (iType == 27) {
                if (!isListInitialized(t8, iTypeAndOffsetAt, i13)) {
                    return false;
                }
            } else if (iType == 60 || iType == 68) {
                if (isOneofPresent(t8, iNumberAt, i13) && !isInitialized(t8, iTypeAndOffsetAt, getMessageFieldSchema(i13))) {
                    return false;
                }
            } else if (iType != 49) {
                if (iType == 50 && !isMapInitialized(t8, iTypeAndOffsetAt, i13)) {
                    return false;
                }
            }
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t8).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t8) {
        int i9;
        int i10 = this.checkInitializedCount;
        while (true) {
            i9 = this.repeatedFieldOffsetStart;
            if (i10 >= i9) {
                break;
            }
            long jOffset = offset(typeAndOffsetAt(this.intArray[i10]));
            Object object = UnsafeUtil.getObject(t8, jOffset);
            if (object != null) {
                UnsafeUtil.putObject(t8, jOffset, this.mapFieldSchema.toImmutable(object));
            }
            i10++;
        }
        int length = this.intArray.length;
        while (i9 < length) {
            this.listFieldSchema.makeImmutableListAt(t8, this.intArray[i9]);
            i9++;
        }
        this.unknownFieldSchema.makeImmutable(t8);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(t8);
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t8, T t9) {
        t9.getClass();
        for (int i9 = 0; i9 < this.buffer.length; i9 += 3) {
            mergeSingleField(t8, t9, i9);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t8, t9);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t8, t9);
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0359, code lost:
    
        if (r0 != r11) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x035b, code lost:
    
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r9 = r36;
        r1 = r17;
        r3 = r18;
        r7 = r19;
        r2 = r20;
        r6 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x03a2, code lost:
    
        if (r0 != r15) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03c5, code lost:
    
        if (r0 != r15) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03c8, code lost:
    
        r2 = r0;
        r8 = r18;
        r0 = r35;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x008b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int parseProto2Message(T t8, byte[] bArr, int i9, int i10, int i11, ArrayDecoders.Registers registers) {
        Unsafe unsafe;
        int i12;
        MessageSchema<T> messageSchema;
        T t9;
        int i13;
        int iDecodeVarint32;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        char c9;
        int i24;
        int i25;
        int i26;
        int iDecodeVarint64;
        int i27;
        MessageSchema<T> messageSchema2 = this;
        T t10 = t8;
        byte[] bArr2 = bArr;
        int i28 = i10;
        int i29 = i11;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int iDecodeUnknownField = i9;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        int i33 = -1;
        int i34 = -1;
        while (true) {
            if (iDecodeUnknownField < i28) {
                int i35 = iDecodeUnknownField + 1;
                byte b9 = bArr2[iDecodeUnknownField];
                if (b9 < 0) {
                    iDecodeVarint32 = ArrayDecoders.decodeVarint32(b9, bArr2, i35, registers2);
                    i13 = registers2.int1;
                } else {
                    i13 = b9;
                    iDecodeVarint32 = i35;
                }
                int i36 = i13 >>> 3;
                int i37 = i13 & 7;
                int iPositionForFieldNumber = i36 > i33 ? messageSchema2.positionForFieldNumber(i36, i30 / 3) : messageSchema2.positionForFieldNumber(i36);
                if (iPositionForFieldNumber == -1) {
                    i14 = i36;
                    i15 = iDecodeVarint32;
                    i16 = i13;
                    i17 = i32;
                    i18 = i34;
                    unsafe = unsafe2;
                    i19 = i29;
                    i20 = 0;
                } else {
                    int i38 = messageSchema2.buffer[iPositionForFieldNumber + 1];
                    int iType = type(i38);
                    long jOffset = offset(i38);
                    int i39 = i13;
                    if (iType <= 17) {
                        int i40 = messageSchema2.buffer[iPositionForFieldNumber + 2];
                        int i41 = 1 << (i40 >>> 20);
                        int i42 = i40 & OFFSET_MASK;
                        if (i42 != i34) {
                            c9 = 65535;
                            i23 = iPositionForFieldNumber;
                            if (i34 != -1) {
                                unsafe2.putInt(t10, i34, i32);
                            }
                            i32 = unsafe2.getInt(t10, i42);
                            i34 = i42;
                        } else {
                            i23 = iPositionForFieldNumber;
                            c9 = 65535;
                        }
                        switch (iType) {
                            case 0:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i25 = iDecodeVarint32;
                                i26 = i39;
                                if (i37 == 1) {
                                    UnsafeUtil.putDouble(t10, jOffset, ArrayDecoders.decodeDouble(bArr2, i25));
                                    iDecodeUnknownField = i25 + 8;
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 1:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i25 = iDecodeVarint32;
                                i26 = i39;
                                if (i37 == 5) {
                                    UnsafeUtil.putFloat(t10, jOffset, ArrayDecoders.decodeFloat(bArr2, i25));
                                    iDecodeUnknownField = i25 + 4;
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 2:
                            case 3:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i25 = iDecodeVarint32;
                                i26 = i39;
                                if (i37 == 0) {
                                    iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i25, registers2);
                                    unsafe2.putLong(t8, jOffset, registers2.long1);
                                    i32 |= i41;
                                    i30 = i24;
                                    iDecodeUnknownField = iDecodeVarint64;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    i29 = i11;
                                    break;
                                } else {
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 4:
                            case 11:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i25 = iDecodeVarint32;
                                i26 = i39;
                                if (i37 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i25, registers2);
                                    unsafe2.putInt(t10, jOffset, registers2.int1);
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 5:
                            case 14:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i26 = i39;
                                if (i37 == 1) {
                                    i25 = iDecodeVarint32;
                                    unsafe2.putLong(t8, jOffset, ArrayDecoders.decodeFixed64(bArr2, iDecodeVarint32));
                                    iDecodeUnknownField = i25 + 8;
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 6:
                            case 13:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i27 = i10;
                                i26 = i39;
                                if (i37 == 5) {
                                    unsafe2.putInt(t10, jOffset, ArrayDecoders.decodeFixed32(bArr2, iDecodeVarint32));
                                    iDecodeUnknownField = iDecodeVarint32 + 4;
                                    i32 |= i41;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i27;
                                    i29 = i11;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 7:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i27 = i10;
                                i26 = i39;
                                if (i37 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint64(bArr2, iDecodeVarint32, registers2);
                                    UnsafeUtil.putBoolean(t10, jOffset, registers2.long1 != 0);
                                    i32 |= i41;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i27;
                                    i29 = i11;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 8:
                                i24 = i23;
                                i14 = i36;
                                bArr2 = bArr;
                                i27 = i10;
                                i26 = i39;
                                if (i37 == 2) {
                                    iDecodeUnknownField = (i38 & ENFORCE_UTF8_MASK) == 0 ? ArrayDecoders.decodeString(bArr2, iDecodeVarint32, registers2) : ArrayDecoders.decodeStringRequireUtf8(bArr2, iDecodeVarint32, registers2);
                                    unsafe2.putObject(t10, jOffset, registers2.object1);
                                    i32 |= i41;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i27;
                                    i29 = i11;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 9:
                                i24 = i23;
                                i26 = i39;
                                i14 = i36;
                                bArr2 = bArr;
                                if (i37 == 2) {
                                    i27 = i10;
                                    iDecodeUnknownField = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(i24), bArr2, iDecodeVarint32, i27, registers2);
                                    if ((i32 & i41) == 0) {
                                        unsafe2.putObject(t10, jOffset, registers2.object1);
                                    } else {
                                        unsafe2.putObject(t10, jOffset, Internal.mergeMessage(unsafe2.getObject(t10, jOffset), registers2.object1));
                                    }
                                    i32 |= i41;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i27;
                                    i29 = i11;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 10:
                                i24 = i23;
                                i26 = i39;
                                i14 = i36;
                                bArr2 = bArr;
                                if (i37 == 2) {
                                    iDecodeUnknownField = ArrayDecoders.decodeBytes(bArr2, iDecodeVarint32, registers2);
                                    unsafe2.putObject(t10, jOffset, registers2.object1);
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 12:
                                i24 = i23;
                                i26 = i39;
                                i14 = i36;
                                bArr2 = bArr;
                                if (i37 != 0) {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                } else {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, iDecodeVarint32, registers2);
                                    int i43 = registers2.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i24);
                                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i43)) {
                                        unsafe2.putInt(t10, jOffset, i43);
                                        i32 |= i41;
                                        i29 = i11;
                                        i30 = i24;
                                        i31 = i26;
                                        i33 = i14;
                                        i28 = i10;
                                        break;
                                    } else {
                                        getMutableUnknownFields(t8).storeField(i26, Long.valueOf(i43));
                                        i29 = i11;
                                        i30 = i24;
                                        i31 = i26;
                                        i33 = i14;
                                        i28 = i10;
                                    }
                                }
                                break;
                            case 15:
                                i24 = i23;
                                i26 = i39;
                                i14 = i36;
                                bArr2 = bArr;
                                if (i37 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, iDecodeVarint32, registers2);
                                    unsafe2.putInt(t10, jOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                    i32 |= i41;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 16:
                                i24 = i23;
                                i26 = i39;
                                i14 = i36;
                                if (i37 == 0) {
                                    bArr2 = bArr;
                                    iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, iDecodeVarint32, registers2);
                                    unsafe2.putLong(t8, jOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                    i32 |= i41;
                                    i30 = i24;
                                    iDecodeUnknownField = iDecodeVarint64;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    i29 = i11;
                                    break;
                                } else {
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            case 17:
                                if (i37 == 3) {
                                    int i44 = i23;
                                    i24 = i44;
                                    i14 = i36;
                                    i26 = i39;
                                    iDecodeUnknownField = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(i44), bArr, iDecodeVarint32, i10, (i36 << 3) | 4, registers);
                                    if ((i32 & i41) == 0) {
                                        unsafe2.putObject(t10, jOffset, registers2.object1);
                                    } else {
                                        unsafe2.putObject(t10, jOffset, Internal.mergeMessage(unsafe2.getObject(t10, jOffset), registers2.object1));
                                    }
                                    i32 |= i41;
                                    bArr2 = bArr;
                                    i29 = i11;
                                    i30 = i24;
                                    i31 = i26;
                                    i33 = i14;
                                    i28 = i10;
                                    break;
                                } else {
                                    i24 = i23;
                                    i26 = i39;
                                    i14 = i36;
                                    i25 = iDecodeVarint32;
                                    i19 = i11;
                                    i17 = i32;
                                    i18 = i34;
                                    i20 = i24;
                                    unsafe = unsafe2;
                                    i15 = i25;
                                    i16 = i26;
                                    break;
                                }
                            default:
                                i25 = iDecodeVarint32;
                                i24 = i23;
                                i14 = i36;
                                i26 = i39;
                                i19 = i11;
                                i17 = i32;
                                i18 = i34;
                                i20 = i24;
                                unsafe = unsafe2;
                                i15 = i25;
                                i16 = i26;
                                break;
                        }
                    } else {
                        i14 = i36;
                        bArr2 = bArr;
                        int i45 = iDecodeVarint32;
                        if (iType != 27) {
                            i20 = iPositionForFieldNumber;
                            i17 = i32;
                            if (iType <= 49) {
                                i18 = i34;
                                unsafe = unsafe2;
                                i22 = i39;
                                iDecodeUnknownField = parseRepeatedField(t8, bArr, i45, i10, i39, i14, i37, i20, i38, iType, jOffset, registers);
                            } else {
                                unsafe = unsafe2;
                                i21 = i45;
                                i22 = i39;
                                i18 = i34;
                                if (iType != 50) {
                                    iDecodeUnknownField = parseOneofField(t8, bArr, i21, i10, i22, i14, i37, i38, iType, jOffset, i20, registers);
                                } else if (i37 == 2) {
                                    iDecodeUnknownField = parseMapField(t8, bArr, i21, i10, i20, jOffset, registers);
                                }
                            }
                        } else if (i37 == 2) {
                            Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe2.getObject(t10, jOffset);
                            if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                                int size = protobufListMutableCopyWithCapacity2.size();
                                protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(t10, jOffset, protobufListMutableCopyWithCapacity2);
                            }
                            iDecodeUnknownField = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(iPositionForFieldNumber), i39, bArr, i45, i10, protobufListMutableCopyWithCapacity2, registers);
                            i29 = i11;
                            i31 = i39;
                            i33 = i14;
                            i30 = iPositionForFieldNumber;
                            i32 = i32;
                            i28 = i10;
                        } else {
                            i20 = iPositionForFieldNumber;
                            i17 = i32;
                            i18 = i34;
                            unsafe = unsafe2;
                            i21 = i45;
                            i22 = i39;
                        }
                        i19 = i11;
                        i15 = i21;
                        i16 = i22;
                    }
                }
                if (i16 != i19 || i19 == 0) {
                    int i46 = i19;
                    iDecodeUnknownField = (!this.hasExtensions || registers.extensionRegistry == ExtensionRegistryLite.getEmptyRegistry()) ? ArrayDecoders.decodeUnknownField(i16, bArr, i15, i10, getMutableUnknownFields(t8), registers) : ArrayDecoders.decodeExtensionOrUnknownField(i16, bArr, i15, i10, t8, this.defaultInstance, this.unknownFieldSchema, registers);
                    t10 = t8;
                    bArr2 = bArr;
                    i28 = i10;
                    i31 = i16;
                    messageSchema2 = this;
                    registers2 = registers;
                    i33 = i14;
                    i34 = i18;
                    i30 = i20;
                    i32 = i17;
                    i29 = i46;
                    unsafe2 = unsafe;
                } else {
                    messageSchema = this;
                    i12 = i19;
                    iDecodeUnknownField = i15;
                    i31 = i16;
                    i34 = i18;
                    i32 = i17;
                }
            } else {
                unsafe = unsafe2;
                i12 = i29;
                messageSchema = messageSchema2;
            }
        }
        if (i34 != -1) {
            t9 = t8;
            unsafe.putInt(t9, i34, i32);
        } else {
            t9 = t8;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i47 = messageSchema.checkInitializedCount; i47 < messageSchema.repeatedFieldOffsetStart; i47++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t9, messageSchema.intArray[i47], unknownFieldSetLite, messageSchema.unknownFieldSchema);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t9, unknownFieldSetLite);
        }
        if (i12 == 0) {
            if (iDecodeUnknownField != i10) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (iDecodeUnknownField > i10 || i31 != i12) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return iDecodeUnknownField;
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t8, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t8, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t8, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t8, writer);
        }
    }

    private boolean isFieldPresent(T t8, int i9) {
        if (this.proto3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i9);
            long jOffset = offset(iTypeAndOffsetAt);
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    return UnsafeUtil.getDouble(t8, jOffset) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return UnsafeUtil.getFloat(t8, jOffset) != BitmapDescriptorFactory.HUE_RED;
                case 2:
                    return UnsafeUtil.getLong(t8, jOffset) != 0;
                case 3:
                    return UnsafeUtil.getLong(t8, jOffset) != 0;
                case 4:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 5:
                    return UnsafeUtil.getLong(t8, jOffset) != 0;
                case 6:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(t8, jOffset);
                case 8:
                    Object object = UnsafeUtil.getObject(t8, jOffset);
                    if (object instanceof String) {
                        return !((String) object).isEmpty();
                    }
                    if (object instanceof ByteString) {
                        return !ByteString.EMPTY.equals(object);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(t8, jOffset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(t8, jOffset));
                case 11:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 12:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 13:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 14:
                    return UnsafeUtil.getLong(t8, jOffset) != 0;
                case 15:
                    return UnsafeUtil.getInt(t8, jOffset) != 0;
                case 16:
                    return UnsafeUtil.getLong(t8, jOffset) != 0;
                case 17:
                    return UnsafeUtil.getObject(t8, jOffset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i9);
        return (UnsafeUtil.getInt(t8, (long) (iPresenceMaskAndOffsetAt & OFFSET_MASK)) & (1 << (iPresenceMaskAndOffsetAt >>> 20))) != 0;
    }

    private int positionForFieldNumber(int i9, int i10) {
        if (i9 < this.minFieldNumber || i9 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i9, i10);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t8, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.getClass();
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t8, reader, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t8, byte[] bArr, int i9, int i10, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        if (this.proto3) {
            parseProto3Message(t8, bArr, i9, i10, registers);
        } else {
            parseProto2Message(t8, bArr, i9, i10, 0, registers);
        }
    }

    private boolean equals(T t8, T t9, int i9) {
        int iTypeAndOffsetAt = typeAndOffsetAt(i9);
        long jOffset = offset(iTypeAndOffsetAt);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                if (arePresentForEquals(t8, t9, i9) && Double.doubleToLongBits(UnsafeUtil.getDouble(t8, jOffset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(t9, jOffset))) {
                    break;
                }
                break;
            case 1:
                if (arePresentForEquals(t8, t9, i9) && Float.floatToIntBits(UnsafeUtil.getFloat(t8, jOffset)) == Float.floatToIntBits(UnsafeUtil.getFloat(t9, jOffset))) {
                    break;
                }
                break;
            case 2:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getLong(t8, jOffset) == UnsafeUtil.getLong(t9, jOffset)) {
                    break;
                }
                break;
            case 3:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getLong(t8, jOffset) == UnsafeUtil.getLong(t9, jOffset)) {
                    break;
                }
                break;
            case 4:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 5:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getLong(t8, jOffset) == UnsafeUtil.getLong(t9, jOffset)) {
                    break;
                }
                break;
            case 6:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 7:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getBoolean(t8, jOffset) == UnsafeUtil.getBoolean(t9, jOffset)) {
                    break;
                }
                break;
            case 8:
                if (arePresentForEquals(t8, t9, i9) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t8, jOffset), UnsafeUtil.getObject(t9, jOffset))) {
                    break;
                }
                break;
            case 9:
                if (arePresentForEquals(t8, t9, i9) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t8, jOffset), UnsafeUtil.getObject(t9, jOffset))) {
                    break;
                }
                break;
            case 10:
                if (arePresentForEquals(t8, t9, i9) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t8, jOffset), UnsafeUtil.getObject(t9, jOffset))) {
                    break;
                }
                break;
            case 11:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 12:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 13:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 14:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getLong(t8, jOffset) == UnsafeUtil.getLong(t9, jOffset)) {
                    break;
                }
                break;
            case 15:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getInt(t8, jOffset) == UnsafeUtil.getInt(t9, jOffset)) {
                    break;
                }
                break;
            case 16:
                if (arePresentForEquals(t8, t9, i9) && UnsafeUtil.getLong(t8, jOffset) == UnsafeUtil.getLong(t9, jOffset)) {
                    break;
                }
                break;
            case 17:
                if (arePresentForEquals(t8, t9, i9) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t8, jOffset), UnsafeUtil.getObject(t9, jOffset))) {
                    break;
                }
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                if (isOneofCaseEqual(t8, t9, i9) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t8, jOffset), UnsafeUtil.getObject(t9, jOffset))) {
                    break;
                }
                break;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object obj, int i9, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i9)));
    }
}
