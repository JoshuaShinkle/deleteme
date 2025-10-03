package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class DynamicMessage extends AbstractMessage {
    private final FieldSet<Descriptors.FieldDescriptor> fields;
    private int memoizedSize = -1;
    private final Descriptors.FieldDescriptor[] oneofCases;
    private final Descriptors.Descriptor type;
    private final UnknownFieldSet unknownFields;

    public static final class Builder extends AbstractMessage.Builder<Builder> {
        private FieldSet<Descriptors.FieldDescriptor> fields;
        private final Descriptors.FieldDescriptor[] oneofCases;
        private final Descriptors.Descriptor type;
        private UnknownFieldSet unknownFields;

        /* JADX INFO: Access modifiers changed from: private */
        public DynamicMessage buildParsed() throws InvalidProtocolBufferException {
            if (isInitialized()) {
                return buildPartial();
            }
            Descriptors.Descriptor descriptor = this.type;
            FieldSet<Descriptors.FieldDescriptor> fieldSet = this.fields;
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) new DynamicMessage(descriptor, fieldSet, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields)).asInvalidProtocolBufferException();
        }

        private void ensureEnumValueDescriptor(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (!fieldDescriptor.isRepeated()) {
                ensureSingularEnumValueDescriptor(fieldDescriptor, obj);
                return;
            }
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                ensureSingularEnumValueDescriptor(fieldDescriptor, it.next());
            }
        }

        private void ensureIsMutable() {
            if (this.fields.isImmutable()) {
                this.fields = this.fields.m25568clone();
            }
        }

        private void ensureSingularEnumValueDescriptor(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            obj.getClass();
            if (!(obj instanceof Descriptors.EnumValueDescriptor)) {
                throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
            }
            if (fieldDescriptor.getEnumType() != ((Descriptors.EnumValueDescriptor) obj).getType()) {
                throw new IllegalArgumentException("EnumValueDescriptor doesn't much Enum Field.");
            }
        }

        private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyOneofContainingType(Descriptors.OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return this.fields.getAllFields();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return this.type;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            Object field = this.fields.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.isRepeated() ? Collections.emptyList() : fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()];
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedField(fieldDescriptor, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedFieldCount(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.hasField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()] != null;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return DynamicMessage.isInitialized(this.type, this.fields);
        }

        private Builder(Descriptors.Descriptor descriptor) {
            this.type = descriptor;
            this.fields = FieldSet.newFieldSet();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new Descriptors.FieldDescriptor[descriptor.toProto().getOneofDeclCount()];
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            this.fields.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                if (fieldDescriptorArr[index] == fieldDescriptor) {
                    fieldDescriptorArr[index] = null;
                }
            }
            this.fields.clearField(fieldDescriptor);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return new Builder(fieldDescriptor.getMessageType());
            }
            throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                ensureEnumValueDescriptor(fieldDescriptor, obj);
            }
            Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                Descriptors.FieldDescriptor fieldDescriptor2 = this.oneofCases[index];
                if (fieldDescriptor2 != null && fieldDescriptor2 != fieldDescriptor) {
                    this.fields.clearField(fieldDescriptor2);
                }
                this.oneofCases[index] = fieldDescriptor;
            }
            this.fields.setField(fieldDescriptor, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            this.fields.setRepeatedField(fieldDescriptor, i9, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public DynamicMessage build() {
            if (isInitialized()) {
                return buildPartial();
            }
            Descriptors.Descriptor descriptor = this.type;
            FieldSet<Descriptors.FieldDescriptor> fieldSet = this.fields;
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) new DynamicMessage(descriptor, fieldSet, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public DynamicMessage buildPartial() {
            this.fields.makeImmutable();
            Descriptors.Descriptor descriptor = this.type;
            FieldSet<Descriptors.FieldDescriptor> fieldSet = this.fields;
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            return new DynamicMessage(descriptor, fieldSet, (Descriptors.FieldDescriptor[]) Arrays.copyOf(fieldDescriptorArr, fieldDescriptorArr.length), this.unknownFields);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            Descriptors.FieldDescriptor fieldDescriptor = this.oneofCases[oneofDescriptor.getIndex()];
            if (fieldDescriptor != null) {
                clearField(fieldDescriptor);
            }
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof DynamicMessage) {
                DynamicMessage dynamicMessage = (DynamicMessage) message;
                if (dynamicMessage.type == this.type) {
                    ensureIsMutable();
                    this.fields.mergeFrom(dynamicMessage.fields);
                    mergeUnknownFields(dynamicMessage.unknownFields);
                    int i9 = 0;
                    while (true) {
                        Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
                        if (i9 >= fieldDescriptorArr.length) {
                            return this;
                        }
                        if (fieldDescriptorArr[i9] == null) {
                            fieldDescriptorArr[i9] = dynamicMessage.oneofCases[i9];
                        } else if (dynamicMessage.oneofCases[i9] != null && this.oneofCases[i9] != dynamicMessage.oneofCases[i9]) {
                            this.fields.clearField(this.oneofCases[i9]);
                            this.oneofCases[i9] = dynamicMessage.oneofCases[i9];
                        }
                        i9++;
                    }
                } else {
                    throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
                }
            } else {
                return (Builder) super.mergeFrom(message);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder clear() {
            if (this.fields.isImmutable()) {
                this.fields = FieldSet.newFieldSet();
            } else {
                this.fields.clear();
            }
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo25567clone() {
            Builder builder = new Builder(this.type);
            builder.fields.mergeFrom(this.fields);
            builder.mergeUnknownFields(this.unknownFields);
            Descriptors.FieldDescriptor[] fieldDescriptorArr = this.oneofCases;
            System.arraycopy(fieldDescriptorArr, 0, builder.oneofCases, 0, fieldDescriptorArr.length);
            return builder;
        }
    }

    public DynamicMessage(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet, Descriptors.FieldDescriptor[] fieldDescriptorArr, UnknownFieldSet unknownFieldSet) {
        this.type = descriptor;
        this.fields = fieldSet;
        this.oneofCases = fieldDescriptorArr;
        this.unknownFields = unknownFieldSet;
    }

    public static DynamicMessage getDefaultInstance(Descriptors.Descriptor descriptor) {
        return new DynamicMessage(descriptor, FieldSet.emptySet(), new Descriptors.FieldDescriptor[descriptor.toProto().getOneofDeclCount()], UnknownFieldSet.getDefaultInstance());
    }

    public static boolean isInitialized(Descriptors.Descriptor descriptor, FieldSet<Descriptors.FieldDescriptor> fieldSet) {
        for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            if (fieldDescriptor.isRequired() && !fieldSet.hasField(fieldDescriptor)) {
                return false;
            }
        }
        return fieldSet.isInitialized();
    }

    public static Builder newBuilder(Descriptors.Descriptor descriptor) {
        return new Builder(descriptor);
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream) {
        return newBuilder(descriptor).mergeFrom(codedInputStream).buildParsed();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(Descriptors.OneofDescriptor oneofDescriptor) {
        if (oneofDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return this.fields.getAllFields();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return this.type;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        Object field = this.fields.getField(fieldDescriptor);
        return field == null ? fieldDescriptor.isRepeated() ? Collections.emptyList() : fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()];
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Parser<DynamicMessage> getParserForType() {
        return new AbstractParser<DynamicMessage>() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.DynamicMessage.1
            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Parser
            public DynamicMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Builder builderNewBuilder = DynamicMessage.newBuilder(DynamicMessage.this.type);
                try {
                    builderNewBuilder.mergeFrom(codedInputStream, extensionRegistryLite);
                    return builderNewBuilder.buildPartial();
                } catch (InvalidProtocolBufferException e9) {
                    throw e9.setUnfinishedMessage(builderNewBuilder.buildPartial());
                } catch (IOException e10) {
                    throw new InvalidProtocolBufferException(e10.getMessage()).setUnfinishedMessage(builderNewBuilder.buildPartial());
                }
            }
        };
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedField(fieldDescriptor, i9);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedFieldCount(fieldDescriptor);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int serializedSize;
        int serializedSize2;
        int i9 = this.memoizedSize;
        if (i9 != -1) {
            return i9;
        }
        if (this.type.getOptions().getMessageSetWireFormat()) {
            serializedSize = this.fields.getMessageSetSerializedSize();
            serializedSize2 = this.unknownFields.getSerializedSizeAsMessageSet();
        } else {
            serializedSize = this.fields.getSerializedSize();
            serializedSize2 = this.unknownFields.getSerializedSize();
        }
        int i10 = serializedSize + serializedSize2;
        this.memoizedSize = i10;
        return i10;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.hasField(fieldDescriptor);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()] != null;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(codedOutputStream);
            this.unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            this.fields.writeTo(codedOutputStream);
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public static Builder newBuilder(Message message) {
        return new Builder(message.getDescriptorForType()).mergeFrom(message);
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, CodedInputStream codedInputStream, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(codedInputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString) {
        return newBuilder(descriptor).mergeFrom(byteString).buildParsed();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public DynamicMessage getDefaultInstanceForType() {
        return getDefaultInstance(this.type);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilderForType().mergeFrom((Message) this);
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, ByteString byteString, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(byteString, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr) {
        return newBuilder(descriptor).mergeFrom(bArr).buildParsed();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return isInitialized(this.type, this.fields);
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, byte[] bArr, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(bArr, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream) {
        return newBuilder(descriptor).mergeFrom(inputStream).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptors.Descriptor descriptor, InputStream inputStream, ExtensionRegistry extensionRegistry) {
        return newBuilder(descriptor).mergeFrom(inputStream, (ExtensionRegistryLite) extensionRegistry).buildParsed();
    }
}
