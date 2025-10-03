package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Internal;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LazyField;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageReflection;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.UnknownFieldSet;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
    protected static boolean alwaysUseFieldBuilders = false;
    private static final long serialVersionUID = 1;

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage$4 */
    public static /* synthetic */ class C43524 {

        /* renamed from: $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType */
        static final /* synthetic */ int[] f15619xdde82548;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.JavaType.values().length];
            f15619xdde82548 = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.JavaType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15619xdde82548[Descriptors.FieldDescriptor.JavaType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static abstract class Builder<BuilderType extends Builder> extends AbstractMessage.Builder<BuilderType> {
        private BuilderParent builderParent;
        private boolean isClean;
        private Builder<BuilderType>.BuilderParentImpl meAsParent;
        private UnknownFieldSet unknownFields;

        public class BuilderParentImpl implements BuilderParent {
            private BuilderParentImpl() {
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.BuilderParent
            public void markDirty() {
                Builder.this.onChanged();
            }
        }

        public Builder() {
            this(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
            TreeMap treeMap = new TreeMap();
            for (Descriptors.FieldDescriptor fieldDescriptor : internalGetFieldAccessorTable().descriptor.getFields()) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) getField(fieldDescriptor);
                    if (!list.isEmpty()) {
                        treeMap.put(fieldDescriptor, list);
                    }
                } else if (hasField(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                }
            }
            return treeMap;
        }

        public void dispose() {
            this.builderParent = null;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            return Collections.unmodifiableMap(getAllFieldsMutable());
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().descriptor;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            Object obj = internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
            return fieldDescriptor.isRepeated() ? Collections.unmodifiableList((List) obj) : obj;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getBuilder(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
        }

        public BuilderParent getParentForChildren() {
            if (this.meAsParent == null) {
                this.meAsParent = new BuilderParentImpl();
            }
            return this.meAsParent;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
        }

        public abstract FieldAccessorTable internalGetFieldAccessorTable();

        public boolean isClean() {
            return this.isClean;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                    return false;
                }
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    if (fieldDescriptor.isRepeated()) {
                        Iterator it = ((List) getField(fieldDescriptor)).iterator();
                        while (it.hasNext()) {
                            if (!((Message) it.next()).isInitialized()) {
                                return false;
                            }
                        }
                    } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void markClean() {
            this.isClean = true;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public Message.Builder newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).newBuilder();
        }

        public void onBuilt() {
            if (this.builderParent != null) {
                markClean();
            }
        }

        public final void onChanged() {
            BuilderParent builderParent;
            if (!this.isClean || (builderParent = this.builderParent) == null) {
                return;
            }
            builderParent.markDirty();
            this.isClean = false;
        }

        public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i9) {
            return builder.mergeFieldFrom(i9, codedInputStream);
        }

        public Builder(BuilderParent builderParent) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.builderParent = builderParent;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).addRepeated(this, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).clear(this);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).set(this, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).setRepeated(this, i9, obj);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public final BuilderType setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            onChanged();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            internalGetFieldAccessorTable().getOneof(oneofDescriptor).clear(this);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public final BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            onChanged();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clear() {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            onChanged();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo25567clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }
    }

    public interface BuilderParent {
        void markDirty();
    }

    public static abstract class CachedDescriptorRetriever implements ExtensionDescriptorRetriever {
        private volatile Descriptors.FieldDescriptor descriptor;

        private CachedDescriptorRetriever() {
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtensionDescriptorRetriever
        public Descriptors.FieldDescriptor getDescriptor() {
            if (this.descriptor == null) {
                synchronized (this) {
                    if (this.descriptor == null) {
                        this.descriptor = loadDescriptor();
                    }
                }
            }
            return this.descriptor;
        }

        public abstract Descriptors.FieldDescriptor loadDescriptor();
    }

    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageOrBuilder {
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        Message getDefaultInstanceForType();

        <Type> Type getExtension(Extension<MessageType, Type> extension);

        <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i9);

        <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension);

        <Type> boolean hasExtension(Extension<MessageType, Type> extension);
    }

    public interface ExtensionDescriptorRetriever {
        Descriptors.FieldDescriptor getDescriptor();
    }

    public static class GeneratedExtension<ContainingType extends Message, Type> extends Extension<ContainingType, Type> {
        private ExtensionDescriptorRetriever descriptorRetriever;
        private final Method enumGetValueDescriptor;
        private final Method enumValueOf;
        private final Extension.ExtensionType extensionType;
        private final Message messageDefaultInstance;
        private final Class singularType;

        public GeneratedExtension(ExtensionDescriptorRetriever extensionDescriptorRetriever, Class cls, Message message, Extension.ExtensionType extensionType) {
            if (Message.class.isAssignableFrom(cls) && !cls.isInstance(message)) {
                String name = cls.getName();
                throw new IllegalArgumentException(name.length() != 0 ? "Bad messageDefaultInstance for ".concat(name) : new String("Bad messageDefaultInstance for "));
            }
            this.descriptorRetriever = extensionDescriptorRetriever;
            this.singularType = cls;
            this.messageDefaultInstance = message;
            if (ProtocolMessageEnum.class.isAssignableFrom(cls)) {
                this.enumValueOf = GeneratedMessage.getMethodOrDie(cls, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(cls, "getValueDescriptor", new Class[0]);
            } else {
                this.enumValueOf = null;
                this.enumGetValueDescriptor = null;
            }
            this.extensionType = extensionType;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Object fromReflectionType(Object obj) {
            Descriptors.FieldDescriptor descriptor = getDescriptor();
            if (!descriptor.isRepeated()) {
                return singularFromReflectionType(obj);
            }
            if (descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE && descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(singularFromReflectionType(it.next()));
            }
            return arrayList;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Type getDefaultValue() {
            return isRepeated() ? (Type) Collections.emptyList() : getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? (Type) this.messageDefaultInstance : (Type) singularFromReflectionType(getDescriptor().getDefaultValue());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Descriptors.FieldDescriptor getDescriptor() {
            ExtensionDescriptorRetriever extensionDescriptorRetriever = this.descriptorRetriever;
            if (extensionDescriptorRetriever != null) {
                return extensionDescriptorRetriever.getDescriptor();
            }
            throw new IllegalStateException("getDescriptor() called before internalInit()");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Extension.ExtensionType getExtensionType() {
            return this.extensionType;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public WireFormat.FieldType getLiteType() {
            return getDescriptor().getLiteType();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public int getNumber() {
            return getDescriptor().getNumber();
        }

        public void internalInit(final Descriptors.FieldDescriptor fieldDescriptor) {
            if (this.descriptorRetriever != null) {
                throw new IllegalStateException("Already initialized.");
            }
            this.descriptorRetriever = new ExtensionDescriptorRetriever() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.GeneratedExtension.1
                @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtensionDescriptorRetriever
                public Descriptors.FieldDescriptor getDescriptor() {
                    return fieldDescriptor;
                }
            };
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public boolean isRepeated() {
            return getDescriptor().isRepeated();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Object singularFromReflectionType(Object obj) {
            int i9 = C43524.f15619xdde82548[getDescriptor().getJavaType().ordinal()];
            return i9 != 1 ? i9 != 2 ? obj : GeneratedMessage.invokeOrDie(this.enumValueOf, null, (Descriptors.EnumValueDescriptor) obj) : this.singularType.isInstance(obj) ? obj : this.messageDefaultInstance.newBuilderForType().mergeFrom((Message) obj).build();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Object singularToReflectionType(Object obj) {
            return C43524.f15619xdde82548[getDescriptor().getJavaType().ordinal()] != 2 ? obj : GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, obj, new Object[0]);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Object toReflectionType(Object obj) {
            Descriptors.FieldDescriptor descriptor = getDescriptor();
            if (!descriptor.isRepeated()) {
                return singularToReflectionType(obj);
            }
            if (descriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(singularToReflectionType(it.next()));
            }
            return arrayList;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension
        public Message getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }
    }

    public GeneratedMessage() {
    }

    public static void enableAlwaysUseFieldBuildersForTesting() {
        alwaysUseFieldBuilders = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
        TreeMap treeMap = new TreeMap();
        for (Descriptors.FieldDescriptor fieldDescriptor : internalGetFieldAccessorTable().descriptor.getFields()) {
            if (fieldDescriptor.isRepeated()) {
                List list = (List) getField(fieldDescriptor);
                if (!list.isEmpty()) {
                    treeMap.put(fieldDescriptor, list);
                }
            } else if (hasField(fieldDescriptor)) {
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
            }
        }
        return treeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
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

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class cls, Message message) {
        return new GeneratedExtension<>(null, cls, message, Extension.ExtensionType.IMMUTABLE);
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message message, final int i9, Class cls, Message message2) {
        return new GeneratedExtension<>(new CachedDescriptorRetriever() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.CachedDescriptorRetriever
            public Descriptors.FieldDescriptor loadDescriptor() {
                return message.getDescriptorForType().getExtensions().get(i9);
            }
        }, cls, message2, Extension.ExtensionType.IMMUTABLE);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        return Collections.unmodifiableMap(getAllFieldsMutable());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().descriptor;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message
    public Parser<? extends GeneratedMessage> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i9);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
    }

    public UnknownFieldSet getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
    }

    public abstract FieldAccessorTable internalGetFieldAccessorTable();

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        for (Descriptors.FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (fieldDescriptor.isRepeated()) {
                    Iterator it = ((List) getField(fieldDescriptor)).iterator();
                    while (it.hasNext()) {
                        if (!((Message) it.next()).isInitialized()) {
                            return false;
                        }
                    }
                } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeExtensionsImmutable() {
    }

    public abstract Message.Builder newBuilderForType(BuilderParent builderParent);

    public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i9) {
        return builder.mergeFieldFrom(i9, codedInputStream);
    }

    public Object writeReplace() {
        return new GeneratedMessageLite.SerializedForm(this);
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder> extends Builder<BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<Descriptors.FieldDescriptor> extensions;

        public ExtendableBuilder() {
            this.extensions = FieldSet.emptySet();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            return this.extensions;
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.m25568clone();
            }
        }

        private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() == getDescriptorForType()) {
                return;
            }
            String fullName = extension.getDescriptor().getContainingType().getFullName();
            String fullName2 = getDescriptorForType().getFullName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 62 + String.valueOf(fullName2).length());
            sb.append("Extension is for type \"");
            sb.append(fullName);
            sb.append("\" which does not match message type \"");
            sb.append(fullName2);
            sb.append("\".");
            throw new IllegalArgumentException(sb.toString());
        }

        public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> extension, Type type) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(extension.getDescriptor(), extension.singularToReflectionType(type));
            onChanged();
            return this;
        }

        public final <Type> BuilderType clearExtension(Extension<MessageType, ?> extension) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.clearField(extension.getDescriptor());
            onChanged();
            return this;
        }

        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable();
            allFieldsMutable.putAll(this.extensions.getAllFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            return field == null ? descriptor.isRepeated() ? (Type) Collections.emptyList() : descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? (Type) extension.getMessageDefaultInstance() : (Type) extension.fromReflectionType(descriptor.getDefaultValue()) : (Type) extension.fromReflectionType(field);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.getDescriptor());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedField(fieldDescriptor, i9);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        public void internalSetExtensionSet(FieldSet<Descriptors.FieldDescriptor> fieldSet) {
            this.extensions = fieldSet;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        public final void mergeExtensionFields(ExtendableMessage extendableMessage) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(extendableMessage.extensions);
            onChanged();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder
        public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i9) {
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new MessageReflection.BuilderAdapter(this), i9);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <Type> BuilderType setExtension(Extension<MessageType, Type> extension, Type type) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.setField(extension.getDescriptor(), extension.toReflectionType(type));
            onChanged();
            return this;
        }

        public ExtendableBuilder(BuilderParent builderParent) {
            super(builderParent);
            this.extensions = FieldSet.emptySet();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                ensureExtensionsIsMutable();
                this.extensions.addRepeatedField(fieldDescriptor, obj);
                onChanged();
                return this;
            }
            return (BuilderType) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                ensureExtensionsIsMutable();
                this.extensions.clearField(fieldDescriptor);
                onChanged();
                return this;
            }
            return (BuilderType) super.clearField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                ensureExtensionsIsMutable();
                this.extensions.setField(fieldDescriptor, obj);
                onChanged();
                return this;
            }
            return (BuilderType) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9, Object obj) {
            if (fieldDescriptor.isExtension()) {
                verifyContainingType(fieldDescriptor);
                ensureExtensionsIsMutable();
                this.extensions.setRepeatedField(fieldDescriptor, i9, obj);
                onChanged();
                return this;
            }
            return (BuilderType) super.setRepeatedField(fieldDescriptor, i9, obj);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType clear() {
            this.extensions = FieldSet.emptySet();
            return (BuilderType) super.clear();
        }

        public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> extension, int i9, Type type) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(extension.getDescriptor(), i9, extension.singularToReflectionType(type));
            onChanged();
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public BuilderType mo25567clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i9) {
            verifyExtensionContainingType(extension);
            return (Type) extension.singularFromReflectionType(this.extensions.getRepeatedField(extension.getDescriptor(), i9));
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage implements ExtendableMessageOrBuilder<MessageType> {
        private final FieldSet<Descriptors.FieldDescriptor> extensions;

        public class ExtensionWriter {
            private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Map.Entry<Descriptors.FieldDescriptor, Object> next;

            public void writeUntil(int i9, CodedOutputStream codedOutputStream) {
                while (true) {
                    Map.Entry<Descriptors.FieldDescriptor, Object> entry = this.next;
                    if (entry == null || entry.getKey().getNumber() >= i9) {
                        return;
                    }
                    Descriptors.FieldDescriptor key = this.next.getKey();
                    if (!this.messageSetWireFormat || key.getLiteJavaType() != WireFormat.JavaType.MESSAGE || key.isRepeated()) {
                        FieldSet.writeField(key, this.next.getValue(), codedOutputStream);
                    } else if (this.next instanceof LazyField.LazyEntry) {
                        codedOutputStream.writeRawMessageSetExtension(key.getNumber(), ((LazyField.LazyEntry) this.next).getField().toByteString());
                    } else {
                        codedOutputStream.writeMessageSetExtension(key.getNumber(), (Message) this.next.getValue());
                    }
                    if (this.iter.hasNext()) {
                        this.next = this.iter.next();
                    } else {
                        this.next = null;
                    }
                }
            }

            private ExtensionWriter(boolean z8) {
                Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> it = ExtendableMessage.this.extensions.iterator();
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

        private void verifyContainingType(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() == getDescriptorForType()) {
                return;
            }
            String fullName = extension.getDescriptor().getContainingType().getFullName();
            String fullName2 = getDescriptorForType().getFullName();
            StringBuilder sb = new StringBuilder(String.valueOf(fullName).length() + 62 + String.valueOf(fullName2).length());
            sb.append("Extension is for type \"");
            sb.append(fullName);
            sb.append("\" which does not match message type \"");
            sb.append(fullName2);
            sb.append("\".");
            throw new IllegalArgumentException(sb.toString());
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

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            Map allFieldsMutable = getAllFieldsMutable();
            allFieldsMutable.putAll(getExtensionFields());
            return Collections.unmodifiableMap(allFieldsMutable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            verifyExtensionContainingType(extension);
            Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            return field == null ? descriptor.isRepeated() ? (Type) Collections.emptyList() : descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? (Type) extension.getMessageDefaultInstance() : (Type) extension.fromReflectionType(descriptor.getDefaultValue()) : (Type) extension.fromReflectionType(field);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.getDescriptor());
        }

        public Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
            return this.extensions.getAllFields();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i9) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedField(fieldDescriptor, i9);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i9);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.AbstractMessage, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage
        public void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        public ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        public ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(true);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage
        public boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i9) {
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), i9);
        }

        public ExtendableMessage(ExtendableBuilder<MessageType, ?> extendableBuilder) {
            super(extendableBuilder);
            this.extensions = extendableBuilder.buildExtensions();
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder
        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i9) {
            verifyExtensionContainingType(extension);
            return (Type) extension.singularFromReflectionType(this.extensions.getRepeatedField(extension.getDescriptor(), i9));
        }
    }

    public static final class FieldAccessorTable {
        private String[] camelCaseNames;
        private final Descriptors.Descriptor descriptor;
        private final FieldAccessor[] fields;
        private volatile boolean initialized;
        private final OneofAccessor[] oneofs;

        public interface FieldAccessor {
            void addRepeated(Builder builder, Object obj);

            void clear(Builder builder);

            Object get(Builder builder);

            Object get(GeneratedMessage generatedMessage);

            Message.Builder getBuilder(Builder builder);

            Object getRepeated(Builder builder, int i9);

            Object getRepeated(GeneratedMessage generatedMessage, int i9);

            int getRepeatedCount(Builder builder);

            int getRepeatedCount(GeneratedMessage generatedMessage);

            boolean has(Builder builder);

            boolean has(GeneratedMessage generatedMessage);

            Message.Builder newBuilder();

            void set(Builder builder, Object obj);

            void setRepeated(Builder builder, int i9, Object obj);
        }

        public static class OneofAccessor {
            private final Method caseMethod;
            private final Method caseMethodBuilder;
            private final Method clearMethod;
            private final Descriptors.Descriptor descriptor;

            public OneofAccessor(Descriptors.Descriptor descriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                this.descriptor = descriptor;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
                sb.append("get");
                sb.append(str);
                sb.append("Case");
                this.caseMethod = GeneratedMessage.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 7);
                sb2.append("get");
                sb2.append(str);
                sb2.append("Case");
                this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                String strValueOf = String.valueOf(str);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf.length() != 0 ? "clear".concat(strValueOf) : new String("clear"), new Class[0]);
            }

            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public Descriptors.FieldDescriptor get(GeneratedMessage generatedMessage) {
                int number = ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }

            public boolean has(GeneratedMessage generatedMessage) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber() != 0;
            }

            public boolean has(Builder builder) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public Descriptors.FieldDescriptor get(Builder builder) {
                int number = ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }
        }

        public static class RepeatedFieldAccessor implements FieldAccessor {
            protected final Method addRepeatedMethod;
            protected final Method clearMethod;
            protected final Method getCountMethod;
            protected final Method getCountMethodBuilder;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method getRepeatedMethod;
            protected final Method getRepeatedMethodBuilder;
            protected final Method setRepeatedMethod;
            protected final Class type;

            public RepeatedFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
                sb.append("get");
                sb.append(str);
                sb.append("List");
                this.getMethod = GeneratedMessage.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 7);
                sb2.append("get");
                sb2.append(str);
                sb2.append("List");
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                String strValueOf = String.valueOf(str);
                String strConcat = strValueOf.length() != 0 ? "get".concat(strValueOf) : new String("get");
                Class cls3 = Integer.TYPE;
                Method methodOrDie = GeneratedMessage.getMethodOrDie(cls, strConcat, cls3);
                this.getRepeatedMethod = methodOrDie;
                String strValueOf2 = String.valueOf(str);
                this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, strValueOf2.length() != 0 ? "get".concat(strValueOf2) : new String("get"), cls3);
                Class<?> returnType = methodOrDie.getReturnType();
                this.type = returnType;
                String strValueOf3 = String.valueOf(str);
                this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf3.length() != 0 ? "set".concat(strValueOf3) : new String("set"), cls3, returnType);
                String strValueOf4 = String.valueOf(str);
                this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf4.length() != 0 ? ProductAction.ACTION_ADD.concat(strValueOf4) : new String(ProductAction.ACTION_ADD), returnType);
                StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 8);
                sb3.append("get");
                sb3.append(str);
                sb3.append("Count");
                this.getCountMethod = GeneratedMessage.getMethodOrDie(cls, sb3.toString(), new Class[0]);
                StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 8);
                sb4.append("get");
                sb4.append(str);
                sb4.append("Count");
                this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, sb4.toString(), new Class[0]);
                String strValueOf5 = String.valueOf(str);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf5.length() != 0 ? "clear".concat(strValueOf5) : new String("clear"), new Class[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, obj);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i9) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, generatedMessage, Integer.valueOf(i9));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethod, generatedMessage, new Object[0])).intValue();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                clear(builder);
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    addRepeated(builder, it.next());
                }
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i9, Object obj) {
                GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, Integer.valueOf(i9), obj);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(Builder builder, int i9) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, Integer.valueOf(i9));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(Builder builder) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }
        }

        public static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method newBuilderMethod;

            public RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).build();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, coerceType(obj));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i9, Object obj) {
                super.setRepeated(builder, i9, coerceType(obj));
            }
        }

        public static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private Method getValueDescriptorMethod;
            private Method valueOfMethod;

            public SingularEnumFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(generatedMessage), new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
            }
        }

        public static class SingularFieldAccessor implements FieldAccessor {
            protected final Method caseMethod;
            protected final Method caseMethodBuilder;
            protected final Method clearMethod;
            protected final Descriptors.FieldDescriptor field;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final boolean hasHasMethod;
            protected final Method hasMethod;
            protected final Method hasMethodBuilder;
            protected final boolean isOneofField;
            protected final Method setMethod;
            protected final Class<?> type;

            public SingularFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2, String str2) {
                Method methodOrDie;
                Method methodOrDie2;
                Method methodOrDie3;
                this.field = fieldDescriptor;
                boolean z8 = fieldDescriptor.getContainingOneof() != null;
                this.isOneofField = z8;
                boolean z9 = FieldAccessorTable.supportFieldPresence(fieldDescriptor.getFile()) || (!z8 && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE);
                this.hasHasMethod = z9;
                String strValueOf = String.valueOf(str);
                Method methodOrDie4 = GeneratedMessage.getMethodOrDie(cls, strValueOf.length() != 0 ? "get".concat(strValueOf) : new String("get"), new Class[0]);
                this.getMethod = methodOrDie4;
                String strValueOf2 = String.valueOf(str);
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, strValueOf2.length() != 0 ? "get".concat(strValueOf2) : new String("get"), new Class[0]);
                Class<?> returnType = methodOrDie4.getReturnType();
                this.type = returnType;
                String strValueOf3 = String.valueOf(str);
                this.setMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf3.length() != 0 ? "set".concat(strValueOf3) : new String("set"), returnType);
                Method methodOrDie5 = null;
                if (z9) {
                    String strValueOf4 = String.valueOf(str);
                    methodOrDie = GeneratedMessage.getMethodOrDie(cls, strValueOf4.length() != 0 ? "has".concat(strValueOf4) : new String("has"), new Class[0]);
                } else {
                    methodOrDie = null;
                }
                this.hasMethod = methodOrDie;
                if (z9) {
                    String strValueOf5 = String.valueOf(str);
                    methodOrDie2 = GeneratedMessage.getMethodOrDie(cls2, strValueOf5.length() != 0 ? "has".concat(strValueOf5) : new String("has"), new Class[0]);
                } else {
                    methodOrDie2 = null;
                }
                this.hasMethodBuilder = methodOrDie2;
                String strValueOf6 = String.valueOf(str);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, strValueOf6.length() != 0 ? "clear".concat(strValueOf6) : new String("clear"), new Class[0]);
                if (z8) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 7);
                    sb.append("get");
                    sb.append(str2);
                    sb.append("Case");
                    methodOrDie3 = GeneratedMessage.getMethodOrDie(cls, sb.toString(), new Class[0]);
                } else {
                    methodOrDie3 = null;
                }
                this.caseMethod = methodOrDie3;
                if (z8) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 7);
                    sb2.append("get");
                    sb2.append(str2);
                    sb2.append("Case");
                    methodOrDie5 = GeneratedMessage.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                }
                this.caseMethodBuilder = methodOrDie5;
            }

            private int getOneofFieldNumber(GeneratedMessage generatedMessage) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i9) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(GeneratedMessage generatedMessage) {
                return !this.hasHasMethod ? this.isOneofField ? getOneofFieldNumber(generatedMessage) == this.field.getNumber() : !get(generatedMessage).equals(this.field.getDefaultValue()) : ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethod, generatedMessage, new Object[0])).booleanValue();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.setMethod, builder, obj);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i9, Object obj) {
                throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
            }

            private int getOneofFieldNumber(Builder builder) {
                return ((Internal.EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(Builder builder, int i9) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public int getRepeatedCount(Builder builder) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public boolean has(Builder builder) {
                if (!this.hasHasMethod) {
                    if (this.isOneofField) {
                        return getOneofFieldNumber(builder) == this.field.getNumber();
                    }
                    return !get(builder).equals(this.field.getDefaultValue());
                }
                return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
            }
        }

        public static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod;

            public SingularMessageFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 10);
                sb.append("get");
                sb.append(str);
                sb.append("Builder");
                this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, sb.toString(), new Class[0]);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).buildPartial();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder getBuilder(Builder builder) {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Message.Builder newBuilder() {
                return (Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.SingularFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void set(Builder builder, Object obj) {
                super.set(builder, coerceType(obj));
            }
        }

        public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] strArr, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
            this(descriptor, strArr);
            ensureFieldAccessorsInitialized(cls, cls2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FieldAccessor getField(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
            if (fieldDescriptor.isExtension()) {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
            return this.fields[fieldDescriptor.getIndex()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public OneofAccessor getOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() == this.descriptor) {
                return this.oneofs[oneofDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean supportFieldPresence(Descriptors.FileDescriptor fileDescriptor) {
            return true;
        }

        public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
            if (this.initialized) {
                return this;
            }
            synchronized (this) {
                if (this.initialized) {
                    return this;
                }
                int length = this.fields.length;
                int i9 = 0;
                while (true) {
                    if (i9 >= length) {
                        break;
                    }
                    Descriptors.FieldDescriptor fieldDescriptor = this.descriptor.getFields().get(i9);
                    String str = fieldDescriptor.getContainingOneof() != null ? this.camelCaseNames[fieldDescriptor.getContainingOneof().getIndex() + length] : null;
                    if (fieldDescriptor.isRepeated()) {
                        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            this.fields[i9] = new RepeatedMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2);
                        } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                            this.fields[i9] = new RepeatedEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2);
                        } else {
                            this.fields[i9] = new RepeatedFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2);
                        }
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        this.fields[i9] = new SingularMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2, str);
                    } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                        this.fields[i9] = new SingularEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2, str);
                    } else {
                        this.fields[i9] = new SingularFieldAccessor(fieldDescriptor, this.camelCaseNames[i9], cls, cls2, str);
                    }
                    i9++;
                }
                int length2 = this.oneofs.length;
                for (int i10 = 0; i10 < length2; i10++) {
                    this.oneofs[i10] = new OneofAccessor(this.descriptor, this.camelCaseNames[i10 + length], cls, cls2);
                }
                this.initialized = true;
                this.camelCaseNames = null;
                return this;
            }
        }

        public static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private final Method getValueDescriptorMethod;
            private final Method valueOfMethod;

            public RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessage> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", Descriptors.EnumValueDescriptor.class);
                this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(GeneratedMessage generatedMessage) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((List) super.get(generatedMessage)).iterator();
                while (it.hasNext()) {
                    arrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, it.next(), new Object[0]));
                }
                return Collections.unmodifiableList(arrayList);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(GeneratedMessage generatedMessage, int i9) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(generatedMessage, i9), new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public void setRepeated(Builder builder, int i9, Object obj) {
                super.setRepeated(builder, i9, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object getRepeated(Builder builder, int i9) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, i9), new Object[0]);
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.FieldAccessorTable.FieldAccessor
            public Object get(Builder builder) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((List) super.get(builder)).iterator();
                while (it.hasNext()) {
                    arrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, it.next(), new Object[0]));
                }
                return Collections.unmodifiableList(arrayList);
            }
        }

        public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] strArr) {
            this.descriptor = descriptor;
            this.camelCaseNames = strArr;
            this.fields = new FieldAccessor[descriptor.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
            this.initialized = false;
        }
    }

    public GeneratedMessage(Builder<?> builder) {
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(final Class cls, Message message, final String str, final String str2) {
        return new GeneratedExtension<>(new CachedDescriptorRetriever() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.CachedDescriptorRetriever
            public Descriptors.FieldDescriptor loadDescriptor() {
                try {
                    return ((Descriptors.FileDescriptor) cls.getClassLoader().loadClass(str).getField("descriptor").get(null)).findExtensionByName(str2);
                } catch (Exception e9) {
                    String str3 = str;
                    StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 62);
                    sb.append("Cannot load descriptors: ");
                    sb.append(str3);
                    sb.append(" is not a valid descriptor class name");
                    throw new RuntimeException(sb.toString(), e9);
                }
            }
        }, cls, message, Extension.ExtensionType.MUTABLE);
    }

    public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message message, final String str, Class cls, Message message2) {
        return new GeneratedExtension<>(new CachedDescriptorRetriever() { // from class: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.GeneratedMessage.CachedDescriptorRetriever
            public Descriptors.FieldDescriptor loadDescriptor() {
                return message.getDescriptorForType().findFieldByName(str);
            }
        }, cls, message2, Extension.ExtensionType.MUTABLE);
    }
}
