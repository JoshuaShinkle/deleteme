package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Extension;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class ExtensionRegistry extends ExtensionRegistryLite {
    private static final ExtensionRegistry EMPTY = new ExtensionRegistry(true);
    private final Map<String, ExtensionInfo> immutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> immutableExtensionsByNumber;
    private final Map<String, ExtensionInfo> mutableExtensionsByName;
    private final Map<DescriptorIntPair, ExtensionInfo> mutableExtensionsByNumber;

    /* renamed from: com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ExtensionRegistry$1 */
    public static /* synthetic */ class C43471 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Extension$ExtensionType;

        static {
            int[] iArr = new int[Extension.ExtensionType.values().length];
            $SwitchMap$com$google$protobuf$Extension$ExtensionType = iArr;
            try {
                iArr[Extension.ExtensionType.IMMUTABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Extension$ExtensionType[Extension.ExtensionType.MUTABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static final class DescriptorIntPair {
        private final Descriptors.Descriptor descriptor;
        private final int number;

        public DescriptorIntPair(Descriptors.Descriptor descriptor, int i9) {
            this.descriptor = descriptor;
            this.number = i9;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DescriptorIntPair)) {
                return false;
            }
            DescriptorIntPair descriptorIntPair = (DescriptorIntPair) obj;
            return this.descriptor == descriptorIntPair.descriptor && this.number == descriptorIntPair.number;
        }

        public int hashCode() {
            return (this.descriptor.hashCode() * 65535) + this.number;
        }
    }

    public static final class ExtensionInfo {
        public final Message defaultInstance;
        public final Descriptors.FieldDescriptor descriptor;

        public /* synthetic */ ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor, Message message, C43471 c43471) {
            this(fieldDescriptor, message);
        }

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor) {
            this.descriptor = fieldDescriptor;
            this.defaultInstance = null;
        }

        private ExtensionInfo(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
            this.descriptor = fieldDescriptor;
            this.defaultInstance = message;
        }
    }

    private ExtensionRegistry() {
        this.immutableExtensionsByName = new HashMap();
        this.mutableExtensionsByName = new HashMap();
        this.immutableExtensionsByNumber = new HashMap();
        this.mutableExtensionsByNumber = new HashMap();
    }

    public static ExtensionRegistry getEmptyRegistry() {
        return EMPTY;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ExtensionInfo newExtensionInfo(Extension<?, ?> extension) {
        C43471 c43471 = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (extension.getDescriptor().getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            return new ExtensionInfo(extension.getDescriptor(), objArr2 == true ? 1 : 0, objArr == true ? 1 : 0);
        }
        if (extension.getMessageDefaultInstance() != null) {
            return new ExtensionInfo(extension.getDescriptor(), (Message) extension.getMessageDefaultInstance(), c43471);
        }
        String strValueOf = String.valueOf(extension.getDescriptor().getFullName());
        throw new IllegalStateException(strValueOf.length() != 0 ? "Registered message-type extension had null default instance: ".concat(strValueOf) : new String("Registered message-type extension had null default instance: "));
    }

    public static ExtensionRegistry newInstance() {
        return new ExtensionRegistry();
    }

    public void add(Extension<?, ?> extension) {
        if (extension.getExtensionType() == Extension.ExtensionType.IMMUTABLE || extension.getExtensionType() == Extension.ExtensionType.MUTABLE) {
            add(newExtensionInfo(extension), extension.getExtensionType());
        }
    }

    public ExtensionInfo findExtensionByName(String str) {
        return findImmutableExtensionByName(str);
    }

    public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor descriptor, int i9) {
        return findImmutableExtensionByNumber(descriptor, i9);
    }

    public ExtensionInfo findImmutableExtensionByName(String str) {
        return this.immutableExtensionsByName.get(str);
    }

    public ExtensionInfo findImmutableExtensionByNumber(Descriptors.Descriptor descriptor, int i9) {
        return this.immutableExtensionsByNumber.get(new DescriptorIntPair(descriptor, i9));
    }

    public ExtensionInfo findMutableExtensionByName(String str) {
        return this.mutableExtensionsByName.get(str);
    }

    public ExtensionInfo findMutableExtensionByNumber(Descriptors.Descriptor descriptor, int i9) {
        return this.mutableExtensionsByNumber.get(new DescriptorIntPair(descriptor, i9));
    }

    public Set<ExtensionInfo> getAllImmutableExtensionsByExtendedType(String str) {
        HashSet hashSet = new HashSet();
        for (DescriptorIntPair descriptorIntPair : this.immutableExtensionsByNumber.keySet()) {
            if (descriptorIntPair.descriptor.getFullName().equals(str)) {
                hashSet.add(this.immutableExtensionsByNumber.get(descriptorIntPair));
            }
        }
        return hashSet;
    }

    public Set<ExtensionInfo> getAllMutableExtensionsByExtendedType(String str) {
        HashSet hashSet = new HashSet();
        for (DescriptorIntPair descriptorIntPair : this.mutableExtensionsByNumber.keySet()) {
            if (descriptorIntPair.descriptor.getFullName().equals(str)) {
                hashSet.add(this.mutableExtensionsByNumber.get(descriptorIntPair));
            }
        }
        return hashSet;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ExtensionRegistryLite
    public ExtensionRegistry getUnmodifiable() {
        return new ExtensionRegistry(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void add(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            ExtensionInfo extensionInfo = new ExtensionInfo(fieldDescriptor, null, 0 == true ? 1 : 0);
            add(extensionInfo, Extension.ExtensionType.IMMUTABLE);
            add(extensionInfo, Extension.ExtensionType.MUTABLE);
            return;
        }
        throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
    }

    private ExtensionRegistry(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
        this.immutableExtensionsByName = Collections.unmodifiableMap(extensionRegistry.immutableExtensionsByName);
        this.mutableExtensionsByName = Collections.unmodifiableMap(extensionRegistry.mutableExtensionsByName);
        this.immutableExtensionsByNumber = Collections.unmodifiableMap(extensionRegistry.immutableExtensionsByNumber);
        this.mutableExtensionsByNumber = Collections.unmodifiableMap(extensionRegistry.mutableExtensionsByNumber);
    }

    public void add(Descriptors.FieldDescriptor fieldDescriptor, Message message) {
        if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            add(new ExtensionInfo(fieldDescriptor, message, null), Extension.ExtensionType.IMMUTABLE);
            return;
        }
        throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
    }

    private void add(ExtensionInfo extensionInfo, Extension.ExtensionType extensionType) {
        Map<String, ExtensionInfo> map;
        Map<DescriptorIntPair, ExtensionInfo> map2;
        if (extensionInfo.descriptor.isExtension()) {
            int i9 = C43471.$SwitchMap$com$google$protobuf$Extension$ExtensionType[extensionType.ordinal()];
            if (i9 == 1) {
                map = this.immutableExtensionsByName;
                map2 = this.immutableExtensionsByNumber;
            } else {
                if (i9 != 2) {
                    return;
                }
                map = this.mutableExtensionsByName;
                map2 = this.mutableExtensionsByNumber;
            }
            map.put(extensionInfo.descriptor.getFullName(), extensionInfo);
            map2.put(new DescriptorIntPair(extensionInfo.descriptor.getContainingType(), extensionInfo.descriptor.getNumber()), extensionInfo);
            Descriptors.FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
            if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                map.put(fieldDescriptor.getMessageType().getFullName(), extensionInfo);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
    }

    public ExtensionRegistry(boolean z8) {
        super(ExtensionRegistryLite.getEmptyRegistry());
        this.immutableExtensionsByName = Collections.emptyMap();
        this.mutableExtensionsByName = Collections.emptyMap();
        this.immutableExtensionsByNumber = Collections.emptyMap();
        this.mutableExtensionsByNumber = Collections.emptyMap();
    }
}
