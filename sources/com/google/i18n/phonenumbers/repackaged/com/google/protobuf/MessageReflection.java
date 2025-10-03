package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ExtensionRegistry;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.UnknownFieldSet;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
class MessageReflection {

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection$1 */
    public static /* synthetic */ class C43571 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class BuilderAdapter implements MergeTarget {
        private final Message.Builder builder;

        public BuilderAdapter(Message.Builder builder) {
            this.builder = builder;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            this.builder.clearField(fieldDescriptor);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            this.builder.clearOneof(oneofDescriptor);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptors.Descriptor descriptor, int i9) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object finish() {
            return this.builder.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget.ContainerType getContainerType() {
            return MergeTarget.ContainerType.MESSAGE;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Descriptors.Descriptor getDescriptorForType() {
            return this.builder.getDescriptorForType();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            return this.builder.getField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            return this.builder.getOneofFieldDescriptor(oneofDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            return this.builder.hasField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return this.builder.hasOneof(oneofDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            return message != null ? new BuilderAdapter(message.newBuilderForType()) : new BuilderAdapter(this.builder.newBuilderForField(fieldDescriptor));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) throws InvalidProtocolBufferException {
            Message message2;
            Message.Builder builderNewBuilderForType = message != null ? message.newBuilderForType() : this.builder.newBuilderForField(fieldDescriptor);
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), builderNewBuilderForType, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) throws InvalidProtocolBufferException {
            Message message2;
            Message.Builder builderNewBuilderForType = message != null ? message.newBuilderForType() : this.builder.newBuilderForField(fieldDescriptor);
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            codedInputStream.readMessage(builderNewBuilderForType, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            Message message2;
            Message.Builder builderNewBuilderForType = message != null ? message.newBuilderForType() : this.builder.newBuilderForField(fieldDescriptor);
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            builderNewBuilderForType.mergeFrom(byteString, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object readPrimitiveField(CodedInputStream codedInputStream, WireFormat.FieldType fieldType, boolean z8) {
            return FieldSet.readPrimitiveField(codedInputStream, fieldType, z8);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.setField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj) {
            this.builder.setRepeatedField(fieldDescriptor, i9, obj);
            return this;
        }
    }

    public static class ExtensionAdapter implements MergeTarget {
        private final FieldSet<Descriptors.FieldDescriptor> extensions;

        public ExtensionAdapter(FieldSet<Descriptors.FieldDescriptor> fieldSet) {
            this.extensions = fieldSet;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            this.extensions.clearField(fieldDescriptor);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptors.Descriptor descriptor, int i9) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object finish() {
            throw new UnsupportedOperationException("finish() called on FieldSet object");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget.ContainerType getContainerType() {
            return MergeTarget.ContainerType.EXTENSION_SET;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Descriptors.Descriptor getDescriptorForType() {
            throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            return this.extensions.getField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            return null;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            return this.extensions.hasField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return false;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) throws InvalidProtocolBufferException {
            Message message2;
            Message.Builder builderNewBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), builderNewBuilderForType, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) throws InvalidProtocolBufferException {
            Message message2;
            Message.Builder builderNewBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            codedInputStream.readMessage(builderNewBuilderForType, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            Message message2;
            Message.Builder builderNewBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated() && (message2 = (Message) getField(fieldDescriptor)) != null) {
                builderNewBuilderForType.mergeFrom(message2);
            }
            builderNewBuilderForType.mergeFrom(byteString, extensionRegistryLite);
            return builderNewBuilderForType.buildPartial();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public Object readPrimitiveField(CodedInputStream codedInputStream, WireFormat.FieldType fieldType, boolean z8) {
            return FieldSet.readPrimitiveField(codedInputStream, fieldType, z8);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.setField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection.MergeTarget
        public MergeTarget setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj) {
            this.extensions.setRepeatedField(fieldDescriptor, i9, obj);
            return this;
        }
    }

    public interface MergeTarget {

        public enum ContainerType {
            MESSAGE,
            EXTENSION_SET
        }

        MergeTarget addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget clearField(Descriptors.FieldDescriptor fieldDescriptor);

        MergeTarget clearOneof(Descriptors.OneofDescriptor oneofDescriptor);

        ExtensionRegistry.ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str);

        ExtensionRegistry.ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptors.Descriptor descriptor, int i9);

        Object finish();

        ContainerType getContainerType();

        Descriptors.Descriptor getDescriptorForType();

        Object getField(Descriptors.FieldDescriptor fieldDescriptor);

        Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor);

        boolean hasField(Descriptors.FieldDescriptor fieldDescriptor);

        boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor);

        MergeTarget newMergeTargetForField(Descriptors.FieldDescriptor fieldDescriptor, Message message);

        Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message);

        Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message);

        Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, Descriptors.FieldDescriptor fieldDescriptor, Message message);

        Object readPrimitiveField(CodedInputStream codedInputStream, WireFormat.FieldType fieldType, boolean z8);

        MergeTarget setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj);
    }

    public static String delimitWithCommas(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private static void eagerlyMergeMessageSetExtension(CodedInputStream codedInputStream, ExtensionRegistry.ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) {
        Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
    }

    private static void findMissingFields(MessageOrBuilder messageOrBuilder, String str, List<String> list) {
        for (Descriptors.FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                String strValueOf = String.valueOf(str);
                String strValueOf2 = String.valueOf(fieldDescriptor.getName());
                list.add(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    Iterator it = ((List) value).iterator();
                    int i9 = 0;
                    while (it.hasNext()) {
                        findMissingFields((MessageOrBuilder) it.next(), subMessagePrefix(str, key, i9), list);
                        i9++;
                    }
                } else if (messageOrBuilder.hasField(key)) {
                    findMissingFields((MessageOrBuilder) value, subMessagePrefix(str, key, -1), list);
                }
            }
        }
    }

    public static int getSerializedSize(Message message) {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        int iComputeMessageSetExtensionSize = 0;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            iComputeMessageSetExtensionSize += (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) ? CodedOutputStream.computeMessageSetExtensionSize(key.getNumber(), (Message) value) : FieldSet.computeFieldSize(key, value);
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        return iComputeMessageSetExtensionSize + (messageSetWireFormat ? unknownFields.getSerializedSizeAsMessageSet() : unknownFields.getSerializedSize());
    }

    public static boolean isInitialized(MessageOrBuilder messageOrBuilder) {
        for (Descriptors.FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (key.isRepeated()) {
                    Iterator it = ((List) entry.getValue()).iterator();
                    while (it.hasNext()) {
                        if (!((Message) it.next()).isInitialized()) {
                            return false;
                        }
                    }
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean mergeFieldFrom(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptors.Descriptor descriptor, MergeTarget mergeTarget, int i9) throws InvalidProtocolBufferException {
        Message message;
        boolean z8;
        Object group;
        ExtensionRegistry.ExtensionInfo extensionInfoFindExtensionByNumber;
        if (descriptor.getOptions().getMessageSetWireFormat() && i9 == WireFormat.MESSAGE_SET_ITEM_TAG) {
            mergeMessageSetExtensionFromCodedStream(codedInputStream, builder, extensionRegistryLite, descriptor, mergeTarget);
            return true;
        }
        int tagWireType = WireFormat.getTagWireType(i9);
        int tagFieldNumber = WireFormat.getTagFieldNumber(i9);
        Descriptors.FieldDescriptor fieldDescriptorFindFieldByNumber = null;
        if (descriptor.isExtensionNumber(tagFieldNumber)) {
            if (!(extensionRegistryLite instanceof ExtensionRegistry) || (extensionInfoFindExtensionByNumber = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, tagFieldNumber)) == null) {
                message = null;
            } else {
                Descriptors.FieldDescriptor fieldDescriptor = extensionInfoFindExtensionByNumber.descriptor;
                Message message2 = extensionInfoFindExtensionByNumber.defaultInstance;
                if (message2 == null && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    String strValueOf = String.valueOf(fieldDescriptor.getFullName());
                    throw new IllegalStateException(strValueOf.length() != 0 ? "Message-typed extension lacked default instance: ".concat(strValueOf) : new String("Message-typed extension lacked default instance: "));
                }
                message = message2;
                fieldDescriptorFindFieldByNumber = fieldDescriptor;
            }
        } else if (mergeTarget.getContainerType() == MergeTarget.ContainerType.MESSAGE) {
            fieldDescriptorFindFieldByNumber = descriptor.findFieldByNumber(tagFieldNumber);
            message = null;
        }
        boolean z9 = false;
        if (fieldDescriptorFindFieldByNumber != null) {
            if (tagWireType == FieldSet.getWireFormatForFieldType(fieldDescriptorFindFieldByNumber.getLiteType(), false)) {
                z8 = false;
            } else if (fieldDescriptorFindFieldByNumber.isPackable() && tagWireType == FieldSet.getWireFormatForFieldType(fieldDescriptorFindFieldByNumber.getLiteType(), true)) {
                z8 = true;
            } else {
                z8 = false;
                z9 = true;
            }
        }
        if (z9) {
            return builder.mergeFieldFrom(i9, codedInputStream);
        }
        if (z8) {
            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
            if (fieldDescriptorFindFieldByNumber.getLiteType() == WireFormat.FieldType.ENUM) {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    Descriptors.EnumValueDescriptor enumValueDescriptorFindValueByNumber = fieldDescriptorFindFieldByNumber.getEnumType().findValueByNumber(codedInputStream.readEnum());
                    if (enumValueDescriptorFindValueByNumber == null) {
                        return true;
                    }
                    mergeTarget.addRepeatedField(fieldDescriptorFindFieldByNumber, enumValueDescriptorFindValueByNumber);
                }
            } else {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    mergeTarget.addRepeatedField(fieldDescriptorFindFieldByNumber, mergeTarget.readPrimitiveField(codedInputStream, fieldDescriptorFindFieldByNumber.getLiteType(), fieldDescriptorFindFieldByNumber.needsUtf8Check()));
                }
            }
            codedInputStream.popLimit(iPushLimit);
        } else {
            int i10 = C43571.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptorFindFieldByNumber.getType().ordinal()];
            if (i10 == 1) {
                group = mergeTarget.parseGroup(codedInputStream, extensionRegistryLite, fieldDescriptorFindFieldByNumber, message);
            } else if (i10 == 2) {
                group = mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptorFindFieldByNumber, message);
            } else if (i10 != 3) {
                group = mergeTarget.readPrimitiveField(codedInputStream, fieldDescriptorFindFieldByNumber.getLiteType(), fieldDescriptorFindFieldByNumber.needsUtf8Check());
            } else {
                int i11 = codedInputStream.readEnum();
                Descriptors.EnumValueDescriptor enumValueDescriptorFindValueByNumber2 = fieldDescriptorFindFieldByNumber.getEnumType().findValueByNumber(i11);
                if (enumValueDescriptorFindValueByNumber2 == null) {
                    builder.mergeVarintField(tagFieldNumber, i11);
                    return true;
                }
                group = enumValueDescriptorFindValueByNumber2;
            }
            if (fieldDescriptorFindFieldByNumber.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptorFindFieldByNumber, group);
            } else {
                mergeTarget.setField(fieldDescriptorFindFieldByNumber, group);
            }
        }
        return true;
    }

    private static void mergeMessageSetExtensionFromBytes(ByteString byteString, ExtensionRegistry.ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) {
        Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        if (mergeTarget.hasField(fieldDescriptor) || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessageFromBytes(byteString, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
        } else {
            mergeTarget.setField(fieldDescriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, byteString));
        }
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptors.Descriptor descriptor, MergeTarget mergeTarget) throws InvalidProtocolBufferException {
        int uInt32 = 0;
        ByteString bytes = null;
        ExtensionRegistry.ExtensionInfo extensionInfoFindExtensionByNumber = null;
        while (true) {
            int tag = codedInputStream.readTag();
            if (tag == 0) {
                break;
            }
            if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                uInt32 = codedInputStream.readUInt32();
                if (uInt32 != 0 && (extensionRegistryLite instanceof ExtensionRegistry)) {
                    extensionInfoFindExtensionByNumber = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, uInt32);
                }
            } else if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (uInt32 == 0 || extensionInfoFindExtensionByNumber == null || !ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                    bytes = codedInputStream.readBytes();
                } else {
                    eagerlyMergeMessageSetExtension(codedInputStream, extensionInfoFindExtensionByNumber, extensionRegistryLite, mergeTarget);
                    bytes = null;
                }
            } else if (!codedInputStream.skipField(tag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
        if (bytes == null || uInt32 == 0) {
            return;
        }
        if (extensionInfoFindExtensionByNumber != null) {
            mergeMessageSetExtensionFromBytes(bytes, extensionInfoFindExtensionByNumber, extensionRegistryLite, mergeTarget);
        } else {
            builder.mergeField(uInt32, UnknownFieldSet.Field.newBuilder().addLengthDelimited(bytes).build());
        }
    }

    private static String subMessagePrefix(String str, Descriptors.FieldDescriptor fieldDescriptor, int i9) {
        StringBuilder sb = new StringBuilder(str);
        if (fieldDescriptor.isExtension()) {
            sb.append('(');
            sb.append(fieldDescriptor.getFullName());
            sb.append(')');
        } else {
            sb.append(fieldDescriptor.getName());
        }
        if (i9 != -1) {
            sb.append('[');
            sb.append(i9);
            sb.append(']');
        }
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        return sb.toString();
    }

    public static void writeMessageTo(Message message, CodedOutputStream codedOutputStream, boolean z8) {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        Map<Descriptors.FieldDescriptor, Object> allFields = message.getAllFields();
        if (z8) {
            TreeMap treeMap = new TreeMap(allFields);
            for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !treeMap.containsKey(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, message.getField(fieldDescriptor));
                }
            }
            allFields = treeMap;
        }
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            if (messageSetWireFormat && key.isExtension() && key.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !key.isRepeated()) {
                codedOutputStream.writeMessageSetExtension(key.getNumber(), (Message) value);
            } else {
                FieldSet.writeField(key, value, codedOutputStream);
            }
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            unknownFields.writeTo(codedOutputStream);
        }
    }

    public static List<String> findMissingFields(MessageOrBuilder messageOrBuilder) {
        ArrayList arrayList = new ArrayList();
        findMissingFields(messageOrBuilder, "", arrayList);
        return arrayList;
    }
}
