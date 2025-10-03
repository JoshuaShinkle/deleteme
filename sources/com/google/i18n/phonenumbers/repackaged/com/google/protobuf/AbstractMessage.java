package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.UnknownFieldSet;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    private int memoizedSize = -1;

    public static abstract class Builder<BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> implements Message.Builder {
        public static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(MessageReflection.findMissingFields(message));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public abstract BuilderType mo25567clone();

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public List<String> findInitializationErrors() {
            return MessageReflection.findMissingFields(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public String getInitializationErrorString() {
            return MessageReflection.delimitWithCommas(findInitializationErrors());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return super.mergeDelimitedFrom(inputStream);
        }

        public String toString() {
            return TextFormat.printToString(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("clearOneof() is not implemented.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clear() {
            Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> it = getAllFields().entrySet().iterator();
            while (it.hasNext()) {
                clearField(it.next().getKey());
            }
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(Message message) {
            if (message.getDescriptorForType() == getDescriptorForType()) {
                for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
                    Descriptors.FieldDescriptor key = entry.getKey();
                    if (key.isRepeated()) {
                        Iterator it = ((List) entry.getValue()).iterator();
                        while (it.hasNext()) {
                            addRepeatedField(key, it.next());
                        }
                    } else if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        Message message2 = (Message) getField(key);
                        if (message2 == message2.getDefaultInstanceForType()) {
                            setField(key, entry.getValue());
                        } else {
                            setField(key, message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build());
                        }
                    } else {
                        setField(key, entry.getValue());
                    }
                }
                mergeUnknownFields(message.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) {
            return (BuilderType) mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int tag;
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
            do {
                tag = codedInputStream.readTag();
                if (tag == 0) {
                    break;
                }
            } while (MessageReflection.mergeFieldFrom(codedInputStream, builderNewBuilder, extensionRegistryLite, getDescriptorForType(), new MessageReflection.BuilderAdapter(this), tag));
            setUnknownFields(builderNewBuilder.build());
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString) {
            return (BuilderType) super.mergeFrom(byteString);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr) {
            return (BuilderType) super.mergeFrom(bArr);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i9, int i10) {
            return (BuilderType) super.mergeFrom(bArr, i9, i10);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(bArr, extensionRegistryLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i9, int i10, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(bArr, i9, i10, extensionRegistryLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream) {
            return (BuilderType) super.mergeFrom(inputStream);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) super.mergeFrom(inputStream, extensionRegistryLite);
        }
    }

    private static boolean compareBytes(Object obj, Object obj2) {
        return ((obj instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) obj, (byte[]) obj2) : toByteString(obj).equals(toByteString(obj2));
    }

    public static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> map, Map<Descriptors.FieldDescriptor, Object> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (Descriptors.FieldDescriptor fieldDescriptor : map.keySet()) {
            if (!map2.containsKey(fieldDescriptor)) {
                return false;
            }
            Object obj = map.get(fieldDescriptor);
            Object obj2 = map2.get(fieldDescriptor);
            if (fieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                if (!obj.equals(obj2)) {
                    return false;
                }
            } else if (fieldDescriptor.isRepeated()) {
                List list = (List) obj;
                List list2 = (List) obj2;
                if (list.size() != list2.size()) {
                    return false;
                }
                for (int i9 = 0; i9 < list.size(); i9++) {
                    if (!compareBytes(list.get(i9), list2.get(i9))) {
                        return false;
                    }
                }
            } else if (!compareBytes(obj, obj2)) {
                return false;
            }
        }
        return true;
    }

    public static int hashFields(int i9, Map<Descriptors.FieldDescriptor, Object> map) {
        int i10;
        int iHashEnum;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            int number = (i9 * 37) + key.getNumber();
            if (key.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
                i10 = number * 53;
                iHashEnum = value.hashCode();
            } else if (key.isRepeated()) {
                i10 = number * 53;
                iHashEnum = Internal.hashEnumList((List) value);
            } else {
                i10 = number * 53;
                iHashEnum = Internal.hashEnum((Internal.EnumLite) value);
            }
            i9 = i10 + iHashEnum;
        }
        return i9;
    }

    private static ByteString toByteString(Object obj) {
        return obj instanceof byte[] ? ByteString.copyFrom((byte[]) obj) : (ByteString) obj;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if (getDescriptorForType() != message.getDescriptorForType()) {
            return false;
        }
        return compareFields(getAllFields(), message.getAllFields()) && getUnknownFields().equals(message.getUnknownFields());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public List<String> findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i9 = this.memoizedSize;
        if (i9 != -1) {
            return i9;
        }
        int serializedSize = MessageReflection.getSerializedSize(this);
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public int hashCode() {
        int i9 = this.memoizedHashCode;
        if (i9 != 0) {
            return i9;
        }
        int iHashFields = (hashFields(779 + getDescriptorForType().hashCode(), getAllFields()) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = iHashFields;
        return iHashFields;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return MessageReflection.isInitialized(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite
    public UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException((Message) this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public final String toString() {
        return TextFormat.printToString(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        MessageReflection.writeMessageTo(this, codedOutputStream, false);
    }
}
