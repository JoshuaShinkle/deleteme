package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public final class UnknownFieldSet implements MessageLite {
    private Map<Integer, Field> fields;
    private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(Collections.emptyMap());
    private static final Parser PARSER = new Parser();

    public static final class Builder implements MessageLite.Builder {
        private Map<Integer, Field> fields;
        private Field.Builder lastField;
        private int lastFieldNumber;

        private Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            Builder builder = new Builder();
            builder.reinitialize();
            return builder;
        }

        private Field.Builder getFieldBuilder(int i9) {
            Field.Builder builder = this.lastField;
            if (builder != null) {
                int i10 = this.lastFieldNumber;
                if (i9 == i10) {
                    return builder;
                }
                addField(i10, builder.build());
            }
            if (i9 == 0) {
                return null;
            }
            Field field = this.fields.get(Integer.valueOf(i9));
            this.lastFieldNumber = i9;
            Field.Builder builderNewBuilder = Field.newBuilder();
            this.lastField = builderNewBuilder;
            if (field != null) {
                builderNewBuilder.mergeFrom(field);
            }
            return this.lastField;
        }

        private void reinitialize() {
            this.fields = Collections.emptyMap();
            this.lastFieldNumber = 0;
            this.lastField = null;
        }

        public Builder addField(int i9, Field field) {
            if (i9 == 0) {
                throw new IllegalArgumentException("Zero is not a valid field number.");
            }
            if (this.lastField != null && this.lastFieldNumber == i9) {
                this.lastField = null;
                this.lastFieldNumber = 0;
            }
            if (this.fields.isEmpty()) {
                this.fields = new TreeMap();
            }
            this.fields.put(Integer.valueOf(i9), field);
            return this;
        }

        public Map<Integer, Field> asMap() {
            getFieldBuilder(0);
            return Collections.unmodifiableMap(this.fields);
        }

        public Builder clearField(int i9) {
            if (i9 == 0) {
                throw new IllegalArgumentException("Zero is not a valid field number.");
            }
            if (this.lastField != null && this.lastFieldNumber == i9) {
                this.lastField = null;
                this.lastFieldNumber = 0;
            }
            if (this.fields.containsKey(Integer.valueOf(i9))) {
                this.fields.remove(Integer.valueOf(i9));
            }
            return this;
        }

        public boolean hasField(int i9) {
            if (i9 != 0) {
                return i9 == this.lastFieldNumber || this.fields.containsKey(Integer.valueOf(i9));
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return true;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
            int i9 = inputStream.read();
            if (i9 == -1) {
                return false;
            }
            mergeFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(i9, inputStream)));
            return true;
        }

        public Builder mergeField(int i9, Field field) {
            if (i9 == 0) {
                throw new IllegalArgumentException("Zero is not a valid field number.");
            }
            if (hasField(i9)) {
                getFieldBuilder(i9).mergeFrom(field);
            } else {
                addField(i9, field);
            }
            return this;
        }

        public boolean mergeFieldFrom(int i9, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            int tagFieldNumber = WireFormat.getTagFieldNumber(i9);
            int tagWireType = WireFormat.getTagWireType(i9);
            if (tagWireType == 0) {
                getFieldBuilder(tagFieldNumber).addVarint(codedInputStream.readInt64());
                return true;
            }
            if (tagWireType == 1) {
                getFieldBuilder(tagFieldNumber).addFixed64(codedInputStream.readFixed64());
                return true;
            }
            if (tagWireType == 2) {
                getFieldBuilder(tagFieldNumber).addLengthDelimited(codedInputStream.readBytes());
                return true;
            }
            if (tagWireType == 3) {
                Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                codedInputStream.readGroup(tagFieldNumber, builderNewBuilder, ExtensionRegistry.getEmptyRegistry());
                getFieldBuilder(tagFieldNumber).addGroup(builderNewBuilder.build());
                return true;
            }
            if (tagWireType == 4) {
                return false;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            getFieldBuilder(tagFieldNumber).addFixed32(codedInputStream.readFixed32());
            return true;
        }

        public Builder mergeVarintField(int i9, int i10) {
            if (i9 == 0) {
                throw new IllegalArgumentException("Zero is not a valid field number.");
            }
            getFieldBuilder(i9).addVarint(i10);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public UnknownFieldSet build() {
            getFieldBuilder(0);
            UnknownFieldSet defaultInstance = this.fields.isEmpty() ? UnknownFieldSet.getDefaultInstance() : new UnknownFieldSet(Collections.unmodifiableMap(this.fields));
            this.fields = null;
            return defaultInstance;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public UnknownFieldSet buildPartial() {
            return build();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder clear() {
            reinitialize();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getDefaultInstanceForType() {
            return UnknownFieldSet.getDefaultInstance();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Builder m25569clone() {
            getFieldBuilder(0);
            return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields));
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeDelimitedFrom(inputStream);
        }

        public Builder mergeFrom(UnknownFieldSet unknownFieldSet) {
            if (unknownFieldSet != UnknownFieldSet.getDefaultInstance()) {
                for (Map.Entry entry : unknownFieldSet.fields.entrySet()) {
                    mergeField(((Integer) entry.getKey()).intValue(), (Field) entry.getValue());
                }
            }
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = codedInputStream.readTag();
                if (tag == 0) {
                    break;
                }
            } while (mergeFieldFrom(tag, codedInputStream));
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr);
                mergeFrom(codedInputStreamNewInstance);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(codedInputStreamNewInstance);
            codedInputStreamNewInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(codedInputStream);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(byteString);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i9, i10);
                mergeFrom(codedInputStreamNewInstance);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(byte[] bArr, int i9, int i10, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr, i9, i10);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(inputStream);
        }
    }

    public static final class Field {
        private static final Field fieldDefaultInstance = newBuilder().build();
        private List<Integer> fixed32;
        private List<Long> fixed64;
        private List<UnknownFieldSet> group;
        private List<ByteString> lengthDelimited;
        private List<Long> varint;

        public static final class Builder {
            private Field result;

            private Builder() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                Builder builder = new Builder();
                builder.result = new Field();
                return builder;
            }

            public Builder addFixed32(int i9) {
                if (this.result.fixed32 == null) {
                    this.result.fixed32 = new ArrayList();
                }
                this.result.fixed32.add(Integer.valueOf(i9));
                return this;
            }

            public Builder addFixed64(long j9) {
                if (this.result.fixed64 == null) {
                    this.result.fixed64 = new ArrayList();
                }
                this.result.fixed64.add(Long.valueOf(j9));
                return this;
            }

            public Builder addGroup(UnknownFieldSet unknownFieldSet) {
                if (this.result.group == null) {
                    this.result.group = new ArrayList();
                }
                this.result.group.add(unknownFieldSet);
                return this;
            }

            public Builder addLengthDelimited(ByteString byteString) {
                if (this.result.lengthDelimited == null) {
                    this.result.lengthDelimited = new ArrayList();
                }
                this.result.lengthDelimited.add(byteString);
                return this;
            }

            public Builder addVarint(long j9) {
                if (this.result.varint == null) {
                    this.result.varint = new ArrayList();
                }
                this.result.varint.add(Long.valueOf(j9));
                return this;
            }

            public Field build() {
                if (this.result.varint == null) {
                    this.result.varint = Collections.emptyList();
                } else {
                    Field field = this.result;
                    field.varint = Collections.unmodifiableList(field.varint);
                }
                if (this.result.fixed32 == null) {
                    this.result.fixed32 = Collections.emptyList();
                } else {
                    Field field2 = this.result;
                    field2.fixed32 = Collections.unmodifiableList(field2.fixed32);
                }
                if (this.result.fixed64 == null) {
                    this.result.fixed64 = Collections.emptyList();
                } else {
                    Field field3 = this.result;
                    field3.fixed64 = Collections.unmodifiableList(field3.fixed64);
                }
                if (this.result.lengthDelimited == null) {
                    this.result.lengthDelimited = Collections.emptyList();
                } else {
                    Field field4 = this.result;
                    field4.lengthDelimited = Collections.unmodifiableList(field4.lengthDelimited);
                }
                if (this.result.group == null) {
                    this.result.group = Collections.emptyList();
                } else {
                    Field field5 = this.result;
                    field5.group = Collections.unmodifiableList(field5.group);
                }
                Field field6 = this.result;
                this.result = null;
                return field6;
            }

            public Builder clear() {
                this.result = new Field();
                return this;
            }

            public Builder mergeFrom(Field field) {
                if (!field.varint.isEmpty()) {
                    if (this.result.varint == null) {
                        this.result.varint = new ArrayList();
                    }
                    this.result.varint.addAll(field.varint);
                }
                if (!field.fixed32.isEmpty()) {
                    if (this.result.fixed32 == null) {
                        this.result.fixed32 = new ArrayList();
                    }
                    this.result.fixed32.addAll(field.fixed32);
                }
                if (!field.fixed64.isEmpty()) {
                    if (this.result.fixed64 == null) {
                        this.result.fixed64 = new ArrayList();
                    }
                    this.result.fixed64.addAll(field.fixed64);
                }
                if (!field.lengthDelimited.isEmpty()) {
                    if (this.result.lengthDelimited == null) {
                        this.result.lengthDelimited = new ArrayList();
                    }
                    this.result.lengthDelimited.addAll(field.lengthDelimited);
                }
                if (!field.group.isEmpty()) {
                    if (this.result.group == null) {
                        this.result.group = new ArrayList();
                    }
                    this.result.group.addAll(field.group);
                }
                return this;
            }
        }

        private Field() {
        }

        public static Field getDefaultInstance() {
            return fieldDefaultInstance;
        }

        private Object[] getIdentityArray() {
            return new Object[]{this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group};
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Field) {
                return Arrays.equals(getIdentityArray(), ((Field) obj).getIdentityArray());
            }
            return false;
        }

        public List<Integer> getFixed32List() {
            return this.fixed32;
        }

        public List<Long> getFixed64List() {
            return this.fixed64;
        }

        public List<UnknownFieldSet> getGroupList() {
            return this.group;
        }

        public List<ByteString> getLengthDelimitedList() {
            return this.lengthDelimited;
        }

        public int getSerializedSize(int i9) {
            Iterator<Long> it = this.varint.iterator();
            int iComputeGroupSize = 0;
            while (it.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeUInt64Size(i9, it.next().longValue());
            }
            Iterator<Integer> it2 = this.fixed32.iterator();
            while (it2.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeFixed32Size(i9, it2.next().intValue());
            }
            Iterator<Long> it3 = this.fixed64.iterator();
            while (it3.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeFixed64Size(i9, it3.next().longValue());
            }
            Iterator<ByteString> it4 = this.lengthDelimited.iterator();
            while (it4.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeBytesSize(i9, it4.next());
            }
            Iterator<UnknownFieldSet> it5 = this.group.iterator();
            while (it5.hasNext()) {
                iComputeGroupSize += CodedOutputStream.computeGroupSize(i9, it5.next());
            }
            return iComputeGroupSize;
        }

        public int getSerializedSizeAsMessageSetExtension(int i9) {
            Iterator<ByteString> it = this.lengthDelimited.iterator();
            int iComputeRawMessageSetExtensionSize = 0;
            while (it.hasNext()) {
                iComputeRawMessageSetExtensionSize += CodedOutputStream.computeRawMessageSetExtensionSize(i9, it.next());
            }
            return iComputeRawMessageSetExtensionSize;
        }

        public List<Long> getVarintList() {
            return this.varint;
        }

        public int hashCode() {
            return Arrays.hashCode(getIdentityArray());
        }

        public void writeAsMessageSetExtensionTo(int i9, CodedOutputStream codedOutputStream) {
            Iterator<ByteString> it = this.lengthDelimited.iterator();
            while (it.hasNext()) {
                codedOutputStream.writeRawMessageSetExtension(i9, it.next());
            }
        }

        public void writeTo(int i9, CodedOutputStream codedOutputStream) {
            Iterator<Long> it = this.varint.iterator();
            while (it.hasNext()) {
                codedOutputStream.writeUInt64(i9, it.next().longValue());
            }
            Iterator<Integer> it2 = this.fixed32.iterator();
            while (it2.hasNext()) {
                codedOutputStream.writeFixed32(i9, it2.next().intValue());
            }
            Iterator<Long> it3 = this.fixed64.iterator();
            while (it3.hasNext()) {
                codedOutputStream.writeFixed64(i9, it3.next().longValue());
            }
            Iterator<ByteString> it4 = this.lengthDelimited.iterator();
            while (it4.hasNext()) {
                codedOutputStream.writeBytes(i9, it4.next());
            }
            Iterator<UnknownFieldSet> it5 = this.group.iterator();
            while (it5.hasNext()) {
                codedOutputStream.writeGroup(i9, it5.next());
            }
        }

        public static Builder newBuilder(Field field) {
            return newBuilder().mergeFrom(field);
        }
    }

    public static final class Parser extends AbstractParser<UnknownFieldSet> {
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Parser
        public UnknownFieldSet parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            try {
                builderNewBuilder.mergeFrom(codedInputStream);
                return builderNewBuilder.buildPartial();
            } catch (InvalidProtocolBufferException e9) {
                throw e9.setUnfinishedMessage(builderNewBuilder.buildPartial());
            } catch (IOException e10) {
                throw new InvalidProtocolBufferException(e10.getMessage()).setUnfinishedMessage(builderNewBuilder.buildPartial());
            }
        }
    }

    private UnknownFieldSet() {
    }

    public static UnknownFieldSet getDefaultInstance() {
        return defaultInstance;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static UnknownFieldSet parseFrom(CodedInputStream codedInputStream) {
        return newBuilder().mergeFrom(codedInputStream).build();
    }

    public Map<Integer, Field> asMap() {
        return this.fields;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UnknownFieldSet) && this.fields.equals(((UnknownFieldSet) obj).fields);
    }

    public Field getField(int i9) {
        Field field = this.fields.get(Integer.valueOf(i9));
        return field == null ? Field.getDefaultInstance() : field;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int serializedSize = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            serializedSize += entry.getValue().getSerializedSize(entry.getKey().intValue());
        }
        return serializedSize;
    }

    public int getSerializedSizeAsMessageSet() {
        int serializedSizeAsMessageSetExtension = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            serializedSizeAsMessageSetExtension += entry.getValue().getSerializedSizeAsMessageSetExtension(entry.getKey().intValue());
        }
        return serializedSizeAsMessageSetExtension;
    }

    public boolean hasField(int i9) {
        return this.fields.containsKey(Integer.valueOf(i9));
    }

    public int hashCode() {
        return this.fields.hashCode();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return true;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(bArr);
            writeTo(codedOutputStreamNewInstance);
            codedOutputStreamNewInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e9);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(codedBuilderNewCodedBuilder.getCodedOutput());
            return codedBuilderNewCodedBuilder.build();
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e9);
        }
    }

    public String toString() {
        return TextFormat.printToString(this);
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeAsMessageSetExtensionTo(entry.getKey().intValue(), codedOutputStream);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) throws IOException {
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream);
        codedOutputStreamNewInstance.writeRawVarint32(getSerializedSize());
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeTo(entry.getKey().intValue(), codedOutputStream);
        }
    }

    private UnknownFieldSet(Map<Integer, Field> map) {
        this.fields = map;
    }

    public static Builder newBuilder(UnknownFieldSet unknownFieldSet) {
        return newBuilder().mergeFrom(unknownFieldSet);
    }

    public static UnknownFieldSet parseFrom(ByteString byteString) {
        return newBuilder().mergeFrom(byteString).build();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getDefaultInstanceForType() {
        return defaultInstance;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public final Parser getParserForType() {
        return PARSER;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Builder toBuilder() {
        return newBuilder().mergeFrom(this);
    }

    public static UnknownFieldSet parseFrom(byte[] bArr) {
        return newBuilder().mergeFrom(bArr).build();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) throws IOException {
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream);
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    public static UnknownFieldSet parseFrom(InputStream inputStream) {
        return newBuilder().mergeFrom(inputStream).build();
    }
}
