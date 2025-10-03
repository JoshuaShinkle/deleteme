package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.LazyField;
import com.google.protobuf.WireFormat;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(messageLite);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t8) {
        return unknownFieldSchema.getSerializedSizeAsMessageSet(unknownFieldSchema.getFromMessage(t8));
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t8, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        UB builderFromMessage = unknownFieldSchema.getBuilderFromMessage(t8);
        FieldSet<ET> mutableExtensions = extensionSchema.getMutableExtensions(t8);
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            try {
                if (!parseMessageSetItemOrUnknownField(reader, extensionRegistryLite, extensionSchema, mutableExtensions, unknownFieldSchema, builderFromMessage)) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(t8, builderFromMessage);
            }
        }
    }

    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, messageLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistryLite, ExtensionSchema<ET> extensionSchema, FieldSet<ET> fieldSet, UnknownFieldSchema<UT, UB> unknownFieldSchema, UB ub) throws InvalidProtocolBufferException {
        int tag = reader.getTag();
        if (tag != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if (WireFormat.getTagWireType(tag) != 2) {
                return reader.skipField();
            }
            Object objFindExtensionByNumber = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, WireFormat.getTagFieldNumber(tag));
            if (objFindExtensionByNumber == null) {
                return unknownFieldSchema.mergeOneFieldFrom(ub, reader);
            }
            extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber, extensionRegistryLite, fieldSet);
            return true;
        }
        Object objFindExtensionByNumber2 = null;
        int uInt32 = 0;
        ByteString bytes = null;
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            int tag2 = reader.getTag();
            if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                uInt32 = reader.readUInt32();
                objFindExtensionByNumber2 = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, uInt32);
            } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (objFindExtensionByNumber2 != null) {
                    extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
                } else {
                    bytes = reader.readBytes();
                }
            } else if (!reader.skipField()) {
                break;
            }
        }
        if (reader.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if (bytes != null) {
            if (objFindExtensionByNumber2 != null) {
                extensionSchema.parseMessageSetItem(bytes, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
            } else {
                unknownFieldSchema.addLengthDelimited(ub, uInt32, bytes);
            }
        }
        return true;
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t8, Writer writer) {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(t8), writer);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t8, T t9) {
        if (!this.unknownFieldSchema.getFromMessage(t8).equals(this.unknownFieldSchema.getFromMessage(t9))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t8).equals(this.extensionSchema.getExtensions(t9));
        }
        return true;
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t8) {
        int unknownFieldsSerializedSize = getUnknownFieldsSerializedSize(this.unknownFieldSchema, t8) + 0;
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t8).getMessageSetSerializedSize() : unknownFieldsSerializedSize;
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T t8) {
        int iHashCode = this.unknownFieldSchema.getFromMessage(t8).hashCode();
        return this.hasExtensions ? (iHashCode * 53) + this.extensionSchema.getExtensions(t8).hashCode() : iHashCode;
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T t8) {
        return this.extensionSchema.getExtensions(t8).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t8) {
        this.unknownFieldSchema.makeImmutable(t8);
        this.extensionSchema.makeImmutable(t8);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t8, T t9) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t8, t9);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t8, t9);
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.defaultInstance.newBuilderForType().buildPartial();
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t8, Writer writer) {
        Iterator it = this.extensionSchema.getExtensions(t8).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) entry.getKey();
            if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof LazyField.LazyEntry) {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), ((LazyField.LazyEntry) entry).getField().toByteString());
            } else {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), entry.getValue());
            }
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, t8, writer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cb A[EDGE_INSN: B:58:0x00cb->B:34:0x00cb BREAK  A[LOOP:1: B:18:0x006d->B:61:0x006d], SYNTHETIC] */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mergeFrom(T t8, byte[] bArr, int i9, int i10, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t8;
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = generatedMessageLite.unknownFields;
        if (unknownFieldSetLiteNewInstance == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
            generatedMessageLite.unknownFields = unknownFieldSetLiteNewInstance;
        }
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSetEnsureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) t8).ensureExtensionsAreMutable();
        GeneratedMessageLite.GeneratedExtension generatedExtension = null;
        while (i9 < i10) {
            int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i9, registers);
            int i11 = registers.int1;
            if (i11 == WireFormat.MESSAGE_SET_ITEM_TAG) {
                int i12 = 0;
                ByteString byteString = null;
                while (iDecodeVarint32 < i10) {
                    iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                    int i13 = registers.int1;
                    int tagFieldNumber = WireFormat.getTagFieldNumber(i13);
                    int tagWireType = WireFormat.getTagWireType(i13);
                    if (tagFieldNumber != 2) {
                        if (tagFieldNumber == 3) {
                            if (generatedExtension != null) {
                                iDecodeVarint32 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i10, registers);
                                fieldSetEnsureExtensionsAreMutable.setField(generatedExtension.descriptor, registers.object1);
                            } else if (tagWireType == 2) {
                                iDecodeVarint32 = ArrayDecoders.decodeBytes(bArr, iDecodeVarint32, registers);
                                byteString = (ByteString) registers.object1;
                            }
                        }
                        if (i13 != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                        } else {
                            iDecodeVarint32 = ArrayDecoders.skipField(i13, bArr, iDecodeVarint32, i10, registers);
                        }
                    } else if (tagWireType == 0) {
                        iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                        i12 = registers.int1;
                        generatedExtension = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, i12);
                    } else if (i13 != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                    }
                }
                if (byteString != null) {
                    unknownFieldSetLiteNewInstance.storeField(WireFormat.makeTag(i12, 2), byteString);
                }
                i9 = iDecodeVarint32;
            } else if (WireFormat.getTagWireType(i11) == 2) {
                GeneratedMessageLite.GeneratedExtension generatedExtension2 = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, WireFormat.getTagFieldNumber(i11));
                if (generatedExtension2 != null) {
                    i9 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension2.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i10, registers);
                    fieldSetEnsureExtensionsAreMutable.setField(generatedExtension2.descriptor, registers.object1);
                } else {
                    i9 = ArrayDecoders.decodeUnknownField(i11, bArr, iDecodeVarint32, i10, unknownFieldSetLiteNewInstance, registers);
                }
                generatedExtension = generatedExtension2;
            } else {
                i9 = ArrayDecoders.skipField(i11, bArr, iDecodeVarint32, i10, registers);
            }
        }
        if (i9 != i10) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t8, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t8, reader, extensionRegistryLite);
    }
}
