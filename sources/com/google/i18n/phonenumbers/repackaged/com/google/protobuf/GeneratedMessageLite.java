package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class GeneratedMessageLite extends AbstractMessageLite implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite$1 */
    public static /* synthetic */ class C43541 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$JavaType;

        static {
            int[] iArr = new int[WireFormat.JavaType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$JavaType = iArr;
            try {
                iArr[WireFormat.JavaType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> {
        private ByteString unknownFields = ByteString.EMPTY;

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public abstract MessageType getDefaultInstanceForType();

        public final ByteString getUnknownFields() {
            return this.unknownFields;
        }

        public abstract BuilderType mergeFrom(MessageType messagetype);

        public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i9) {
            return codedInputStream.skipField(i9, codedOutputStream);
        }

        public final BuilderType setUnknownFields(ByteString byteString) {
            this.unknownFields = byteString;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clear() {
            this.unknownFields = ByteString.EMPTY;
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo25567clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }
    }

    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageLiteOrBuilder {
        <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension);

        <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i9);

        <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension);

        <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension);
    }

    public static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
        final Internal.EnumLiteMap<?> enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final WireFormat.FieldType type;

        public ExtensionDescriptor(Internal.EnumLiteMap<?> enumLiteMap, int i9, WireFormat.FieldType fieldType, boolean z8, boolean z9) {
            this.enumTypeMap = enumLiteMap;
            this.number = i9;
            this.type = fieldType;
            this.isRepeated = z8;
            this.isPacked = z9;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public Internal.EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public WireFormat.FieldType getLiteType() {
            return this.type;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public int getNumber() {
            return this.number;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((Builder) messageLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.FieldSet.FieldDescriptorLite
        public boolean isRepeated() {
            return this.isRepeated;
        }

        @Override // java.lang.Comparable
        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }
    }

    public static class GeneratedExtension<ContainingType extends MessageLite, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final ExtensionDescriptor descriptor;
        final Method enumValueOf;
        final MessageLite messageDefaultInstance;
        final Class singularType;

        public GeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (extensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageLite == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.containingTypeDefaultInstance = containingtype;
            this.defaultValue = type;
            this.messageDefaultInstance = messageLite;
            this.descriptor = extensionDescriptor;
            this.singularType = cls;
            if (Internal.EnumLite.class.isAssignableFrom(cls)) {
                this.enumValueOf = GeneratedMessageLite.getMethodOrDie(cls, "valueOf", Integer.TYPE);
            } else {
                this.enumValueOf = null;
            }
        }

        public Object fromFieldSetType(Object obj) {
            if (!this.descriptor.isRepeated()) {
                return singularFromFieldSetType(obj);
            }
            if (this.descriptor.getLiteJavaType() != WireFormat.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(singularFromFieldSetType(it.next()));
            }
            return arrayList;
        }

        public ContainingType getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        public Object singularFromFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? GeneratedMessageLite.invokeOrDie(this.enumValueOf, null, (Integer) obj) : obj;
        }

        public Object singularToFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM ? Integer.valueOf(((Internal.EnumLite) obj).getNumber()) : obj;
        }

        public Object toFieldSetType(Object obj) {
            if (!this.descriptor.isRepeated()) {
                return singularToFieldSetType(obj);
            }
            if (this.descriptor.getLiteJavaType() != WireFormat.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(singularToFieldSetType(it.next()));
            }
            return arrayList;
        }
    }

    public static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private byte[] asBytes;
        private String messageClassName;

        public SerializedForm(MessageLite messageLite) {
            this.messageClassName = messageLite.getClass().getName();
            this.asBytes = messageLite.toByteArray();
        }

        public Object readResolve() {
            try {
                MessageLite.Builder builder = (MessageLite.Builder) Class.forName(this.messageClassName).getMethod("newBuilder", new Class[0]).invoke(null, new Object[0]);
                builder.mergeFrom(this.asBytes);
                return builder.buildPartial();
            } catch (InvalidProtocolBufferException e9) {
                throw new RuntimeException("Unable to understand proto buffer", e9);
            } catch (ClassNotFoundException e10) {
                throw new RuntimeException("Unable to find proto buffer class", e10);
            } catch (IllegalAccessException e11) {
                throw new RuntimeException("Unable to call newBuilder method", e11);
            } catch (NoSuchMethodException e12) {
                throw new RuntimeException("Unable to find newBuilder method", e12);
            } catch (InvocationTargetException e13) {
                throw new RuntimeException("Error calling newBuilder", e13.getCause());
            }
        }
    }

    public GeneratedMessageLite() {
    }

    static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e9) {
            String name = cls.getName();
            StringBuilder sb = new StringBuilder(name.length() + 45 + String.valueOf(str).length());
            sb.append("Generated message class \"");
            sb.append(name);
            sb.append("\" missing method \"");
            sb.append(str);
            sb.append("\".");
            throw new RuntimeException(sb.toString(), e9);
        }
    }

    static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e9) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e9);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i9, WireFormat.FieldType fieldType, boolean z8, Class cls) {
        return new GeneratedExtension<>(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i9, fieldType, true, z8), cls);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i9, WireFormat.FieldType fieldType, Class cls) {
        return new GeneratedExtension<>(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i9, fieldType, false, false), cls);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Parser<? extends MessageLite> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public void makeExtensionsImmutable() {
    }

    public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i9) {
        return codedInputStream.skipField(i9, codedOutputStream);
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<ExtensionDescriptor> extensions = FieldSet.emptySet();
        private boolean extensionsIsMutable;

        /* JADX INFO: Access modifiers changed from: private */
        public FieldSet<ExtensionDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            this.extensionsIsMutable = false;
            return this.extensions;
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensionsIsMutable) {
                return;
            }
            this.extensions = this.extensions.m25568clone();
            this.extensionsIsMutable = true;
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public final <Type> BuilderType addExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, Type type) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(type));
            return this;
        }

        public final <Type> BuilderType clearExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.clearField(generatedExtension.descriptor);
            return this;
        }

        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Object field = this.extensions.getField(generatedExtension.descriptor);
            return field == null ? generatedExtension.defaultValue : (Type) generatedExtension.fromFieldSetType(field);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.getRepeatedFieldCount(generatedExtension.descriptor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.hasField(generatedExtension.descriptor);
        }

        public void internalSetExtensionSet(FieldSet<ExtensionDescriptor> fieldSet) {
            this.extensions = fieldSet;
        }

        public final void mergeExtensionFields(MessageType messagetype) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(((ExtendableMessage) messagetype).extensions);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.Builder
        public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i9) {
            ensureExtensionsIsMutable();
            return GeneratedMessageLite.parseUnknownField(this.extensions, getDefaultInstanceForType(), codedInputStream, codedOutputStream, extensionRegistryLite, i9);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, Type> generatedExtension, Type type) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.setField(generatedExtension.descriptor, generatedExtension.toFieldSetType(type));
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clear() {
            this.extensions.clear();
            this.extensionsIsMutable = false;
            return (BuilderType) super.clear();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo25567clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i9) {
            verifyExtensionContainingType(generatedExtension);
            return (Type) generatedExtension.singularFromFieldSetType(this.extensions.getRepeatedField(generatedExtension.descriptor, i9));
        }

        public final <Type> BuilderType setExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i9, Type type) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(generatedExtension.descriptor, i9, generatedExtension.singularToFieldSetType(type));
            return this;
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>> extends GeneratedMessageLite implements ExtendableMessageOrBuilder<MessageType> {
        private final FieldSet<ExtensionDescriptor> extensions;

        public class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Map.Entry<ExtensionDescriptor, Object> next;

            public /* synthetic */ ExtensionWriter(ExtendableMessage extendableMessage, boolean z8, C43541 c43541) {
                this(z8);
            }

            public void writeUntil(int i9, CodedOutputStream codedOutputStream) throws IOException {
                while (true) {
                    Map.Entry<ExtensionDescriptor, Object> entry = this.next;
                    if (entry == null || entry.getKey().getNumber() >= i9) {
                        return;
                    }
                    ExtensionDescriptor key = this.next.getKey();
                    if (this.messageSetWireFormat && key.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !key.isRepeated()) {
                        codedOutputStream.writeMessageSetExtension(key.getNumber(), (MessageLite) this.next.getValue());
                    } else {
                        FieldSet.writeField(key, this.next.getValue(), codedOutputStream);
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                    } else {
                        this.next = null;
                    }
                }
            }

            private ExtensionWriter(boolean z8) {
                Iterator<Map.Entry<ExtensionDescriptor, Object>> it = ExtendableMessage.this.extensions.iterator();
                this.iter = it;
                if (it.hasNext()) {
                    this.next = it.next();
                }
                this.messageSetWireFormat = z8;
            }
        }

        public ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        public int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        public int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Object field = this.extensions.getField(generatedExtension.descriptor);
            return field == null ? generatedExtension.defaultValue : (Type) generatedExtension.fromFieldSetType(field);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.getRepeatedFieldCount(generatedExtension.descriptor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.hasField(generatedExtension.descriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite
        public void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        public ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(this, false, null);
        }

        public ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(this, true, null);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite
        public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i9) {
            return GeneratedMessageLite.parseUnknownField(this.extensions, getDefaultInstanceForType(), codedInputStream, codedOutputStream, extensionRegistryLite, i9);
        }

        public ExtendableMessage(ExtendableBuilder<MessageType, ?> extendableBuilder) {
            this.extensions = extendableBuilder.buildExtensions();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i9) {
            verifyExtensionContainingType(generatedExtension);
            return (Type) generatedExtension.singularFromFieldSetType(this.extensions.getRepeatedField(generatedExtension.descriptor, i9));
        }
    }

    public GeneratedMessageLite(Builder builder) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <MessageType extends MessageLite> boolean parseUnknownField(FieldSet<ExtensionDescriptor> fieldSet, MessageType messagetype, CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i9) throws InvalidProtocolBufferException {
        boolean z8;
        boolean z9;
        Object objBuild;
        MessageLite messageLite;
        int tagWireType = WireFormat.getTagWireType(i9);
        GeneratedExtension generatedExtensionFindLiteExtensionByNumber = extensionRegistryLite.findLiteExtensionByNumber(messagetype, WireFormat.getTagFieldNumber(i9));
        if (generatedExtensionFindLiteExtensionByNumber != null) {
            if (tagWireType == FieldSet.getWireFormatForFieldType(generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType(), false)) {
                z9 = false;
                z8 = false;
            } else {
                ExtensionDescriptor extensionDescriptor = generatedExtensionFindLiteExtensionByNumber.descriptor;
                if (extensionDescriptor.isRepeated && extensionDescriptor.type.isPackable() && tagWireType == FieldSet.getWireFormatForFieldType(generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType(), true)) {
                    z8 = true;
                    z9 = false;
                } else {
                    z9 = true;
                    z8 = false;
                }
            }
        }
        if (z9) {
            return codedInputStream.skipField(i9, codedOutputStream);
        }
        if (z8) {
            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
            if (generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    Internal.EnumLite enumLiteFindValueByNumber = generatedExtensionFindLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(codedInputStream.readEnum());
                    if (enumLiteFindValueByNumber == null) {
                        return true;
                    }
                    fieldSet.addRepeatedField(generatedExtensionFindLiteExtensionByNumber.descriptor, generatedExtensionFindLiteExtensionByNumber.singularToFieldSetType(enumLiteFindValueByNumber));
                }
            } else {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    fieldSet.addRepeatedField(generatedExtensionFindLiteExtensionByNumber.descriptor, FieldSet.readPrimitiveField(codedInputStream, generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType(), false));
                }
            }
            codedInputStream.popLimit(iPushLimit);
        } else {
            int i10 = C43541.$SwitchMap$com$google$protobuf$WireFormat$JavaType[generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteJavaType().ordinal()];
            if (i10 == 1) {
                MessageLite.Builder builder = (generatedExtensionFindLiteExtensionByNumber.descriptor.isRepeated() || (messageLite = (MessageLite) fieldSet.getField(generatedExtensionFindLiteExtensionByNumber.descriptor)) == null) ? null : messageLite.toBuilder();
                if (builder == null) {
                    builder = generatedExtensionFindLiteExtensionByNumber.getMessageDefaultInstance().newBuilderForType();
                }
                if (generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
                    codedInputStream.readGroup(generatedExtensionFindLiteExtensionByNumber.getNumber(), builder, extensionRegistryLite);
                } else {
                    codedInputStream.readMessage(builder, extensionRegistryLite);
                }
                objBuild = builder.build();
            } else if (i10 != 2) {
                objBuild = FieldSet.readPrimitiveField(codedInputStream, generatedExtensionFindLiteExtensionByNumber.descriptor.getLiteType(), false);
            } else {
                int i11 = codedInputStream.readEnum();
                Internal.EnumLite enumLiteFindValueByNumber2 = generatedExtensionFindLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(i11);
                if (enumLiteFindValueByNumber2 == null) {
                    codedOutputStream.writeRawVarint32(i9);
                    codedOutputStream.writeUInt32NoTag(i11);
                    return true;
                }
                objBuild = enumLiteFindValueByNumber2;
            }
            if (generatedExtensionFindLiteExtensionByNumber.descriptor.isRepeated()) {
                fieldSet.addRepeatedField(generatedExtensionFindLiteExtensionByNumber.descriptor, generatedExtensionFindLiteExtensionByNumber.singularToFieldSetType(objBuild));
            } else {
                fieldSet.setField(generatedExtensionFindLiteExtensionByNumber.descriptor, generatedExtensionFindLiteExtensionByNumber.singularToFieldSetType(objBuild));
            }
        }
        return true;
    }
}
