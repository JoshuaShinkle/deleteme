package com.google.firebase.remoteconfig.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class ConfigPersistence {

    /* renamed from: com.google.firebase.remoteconfig.proto.ConfigPersistence$1 */
    public static /* synthetic */ class C42091 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15589xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15589xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15589xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class ConfigHolder extends GeneratedMessageLite<ConfigHolder, Builder> implements ConfigHolderOrBuilder {
        private static final ConfigHolder DEFAULT_INSTANCE;
        public static final int EXPERIMENT_PAYLOAD_FIELD_NUMBER = 3;
        public static final int NAMESPACE_KEY_VALUE_FIELD_NUMBER = 1;
        private static volatile Parser<ConfigHolder> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        private int bitField0_;
        private long timestamp_;
        private Internal.ProtobufList<NamespaceKeyValue> namespaceKeyValue_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<ByteString> experimentPayload_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<ConfigHolder, Builder> implements ConfigHolderOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addAllExperimentPayload(iterable);
                return this;
            }

            public Builder addAllNamespaceKeyValue(Iterable<? extends NamespaceKeyValue> iterable) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addAllNamespaceKeyValue(iterable);
                return this;
            }

            public Builder addExperimentPayload(ByteString byteString) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addExperimentPayload(byteString);
                return this;
            }

            public Builder addNamespaceKeyValue(NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(namespaceKeyValue);
                return this;
            }

            public Builder clearExperimentPayload() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearExperimentPayload();
                return this;
            }

            public Builder clearNamespaceKeyValue() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearNamespaceKeyValue();
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearTimestamp();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public ByteString getExperimentPayload(int i9) {
                return ((ConfigHolder) this.instance).getExperimentPayload(i9);
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public int getExperimentPayloadCount() {
                return ((ConfigHolder) this.instance).getExperimentPayloadCount();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public List<ByteString> getExperimentPayloadList() {
                return Collections.unmodifiableList(((ConfigHolder) this.instance).getExperimentPayloadList());
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public NamespaceKeyValue getNamespaceKeyValue(int i9) {
                return ((ConfigHolder) this.instance).getNamespaceKeyValue(i9);
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public int getNamespaceKeyValueCount() {
                return ((ConfigHolder) this.instance).getNamespaceKeyValueCount();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public List<NamespaceKeyValue> getNamespaceKeyValueList() {
                return Collections.unmodifiableList(((ConfigHolder) this.instance).getNamespaceKeyValueList());
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public long getTimestamp() {
                return ((ConfigHolder) this.instance).getTimestamp();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
            public boolean hasTimestamp() {
                return ((ConfigHolder) this.instance).hasTimestamp();
            }

            public Builder removeNamespaceKeyValue(int i9) {
                copyOnWrite();
                ((ConfigHolder) this.instance).removeNamespaceKeyValue(i9);
                return this;
            }

            public Builder setExperimentPayload(int i9, ByteString byteString) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setExperimentPayload(i9, byteString);
                return this;
            }

            public Builder setNamespaceKeyValue(int i9, NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setNamespaceKeyValue(i9, namespaceKeyValue);
                return this;
            }

            public Builder setTimestamp(long j9) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setTimestamp(j9);
                return this;
            }

            private Builder() {
                super(ConfigHolder.DEFAULT_INSTANCE);
            }

            public Builder addNamespaceKeyValue(int i9, NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(i9, namespaceKeyValue);
                return this;
            }

            public Builder setNamespaceKeyValue(int i9, NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setNamespaceKeyValue(i9, builder.build());
                return this;
            }

            public Builder addNamespaceKeyValue(NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(builder.build());
                return this;
            }

            public Builder addNamespaceKeyValue(int i9, NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(i9, builder.build());
                return this;
            }
        }

        static {
            ConfigHolder configHolder = new ConfigHolder();
            DEFAULT_INSTANCE = configHolder;
            GeneratedMessageLite.registerDefaultInstance(ConfigHolder.class, configHolder);
        }

        private ConfigHolder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
            ensureExperimentPayloadIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.experimentPayload_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllNamespaceKeyValue(Iterable<? extends NamespaceKeyValue> iterable) {
            ensureNamespaceKeyValueIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.namespaceKeyValue_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addExperimentPayload(ByteString byteString) {
            byteString.getClass();
            ensureExperimentPayloadIsMutable();
            this.experimentPayload_.add(byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceKeyValue(NamespaceKeyValue namespaceKeyValue) {
            namespaceKeyValue.getClass();
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.add(namespaceKeyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExperimentPayload() {
            this.experimentPayload_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespaceKeyValue() {
            this.namespaceKeyValue_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimestamp() {
            this.bitField0_ &= -2;
            this.timestamp_ = 0L;
        }

        private void ensureExperimentPayloadIsMutable() {
            if (this.experimentPayload_.isModifiable()) {
                return;
            }
            this.experimentPayload_ = GeneratedMessageLite.mutableCopy(this.experimentPayload_);
        }

        private void ensureNamespaceKeyValueIsMutable() {
            if (this.namespaceKeyValue_.isModifiable()) {
                return;
            }
            this.namespaceKeyValue_ = GeneratedMessageLite.mutableCopy(this.namespaceKeyValue_);
        }

        public static ConfigHolder getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConfigHolder parseDelimitedFrom(InputStream inputStream) {
            return (ConfigHolder) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigHolder parseFrom(ByteBuffer byteBuffer) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConfigHolder> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeNamespaceKeyValue(int i9) {
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExperimentPayload(int i9, ByteString byteString) {
            byteString.getClass();
            ensureExperimentPayloadIsMutable();
            this.experimentPayload_.set(i9, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespaceKeyValue(int i9, NamespaceKeyValue namespaceKeyValue) {
            namespaceKeyValue.getClass();
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.set(i9, namespaceKeyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTimestamp(long j9) {
            this.bitField0_ |= 1;
            this.timestamp_ = j9;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigHolder();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u0005\u0000\u0003\u001c", new Object[]{"bitField0_", "namespaceKeyValue_", NamespaceKeyValue.class, "timestamp_", "experimentPayload_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ConfigHolder> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ConfigHolder.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public ByteString getExperimentPayload(int i9) {
            return this.experimentPayload_.get(i9);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public int getExperimentPayloadCount() {
            return this.experimentPayload_.size();
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public List<ByteString> getExperimentPayloadList() {
            return this.experimentPayload_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public NamespaceKeyValue getNamespaceKeyValue(int i9) {
            return this.namespaceKeyValue_.get(i9);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public int getNamespaceKeyValueCount() {
            return this.namespaceKeyValue_.size();
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public List<NamespaceKeyValue> getNamespaceKeyValueList() {
            return this.namespaceKeyValue_;
        }

        public NamespaceKeyValueOrBuilder getNamespaceKeyValueOrBuilder(int i9) {
            return this.namespaceKeyValue_.get(i9);
        }

        public List<? extends NamespaceKeyValueOrBuilder> getNamespaceKeyValueOrBuilderList() {
            return this.namespaceKeyValue_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ConfigHolderOrBuilder
        public boolean hasTimestamp() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(ConfigHolder configHolder) {
            return DEFAULT_INSTANCE.createBuilder(configHolder);
        }

        public static ConfigHolder parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(ByteString byteString) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceKeyValue(int i9, NamespaceKeyValue namespaceKeyValue) {
            namespaceKeyValue.getClass();
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.add(i9, namespaceKeyValue);
        }

        public static ConfigHolder parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(byte[] bArr) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigHolder parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(InputStream inputStream) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigHolder parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(CodedInputStream codedInputStream) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigHolder parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ConfigHolderOrBuilder extends MessageLiteOrBuilder {
        ByteString getExperimentPayload(int i9);

        int getExperimentPayloadCount();

        List<ByteString> getExperimentPayloadList();

        NamespaceKeyValue getNamespaceKeyValue(int i9);

        int getNamespaceKeyValueCount();

        List<NamespaceKeyValue> getNamespaceKeyValueList();

        long getTimestamp();

        boolean hasTimestamp();
    }

    public static final class KeyValue extends GeneratedMessageLite<KeyValue, Builder> implements KeyValueOrBuilder {
        private static final KeyValue DEFAULT_INSTANCE;
        public static final int KEY_FIELD_NUMBER = 1;
        private static volatile Parser<KeyValue> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String key_ = "";
        private ByteString value_ = ByteString.EMPTY;

        public static final class Builder extends GeneratedMessageLite.Builder<KeyValue, Builder> implements KeyValueOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder clearKey() {
                copyOnWrite();
                ((KeyValue) this.instance).clearKey();
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((KeyValue) this.instance).clearValue();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
            public String getKey() {
                return ((KeyValue) this.instance).getKey();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
            public ByteString getKeyBytes() {
                return ((KeyValue) this.instance).getKeyBytes();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
            public ByteString getValue() {
                return ((KeyValue) this.instance).getValue();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
            public boolean hasKey() {
                return ((KeyValue) this.instance).hasKey();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
            public boolean hasValue() {
                return ((KeyValue) this.instance).hasValue();
            }

            public Builder setKey(String str) {
                copyOnWrite();
                ((KeyValue) this.instance).setKey(str);
                return this;
            }

            public Builder setKeyBytes(ByteString byteString) {
                copyOnWrite();
                ((KeyValue) this.instance).setKeyBytes(byteString);
                return this;
            }

            public Builder setValue(ByteString byteString) {
                copyOnWrite();
                ((KeyValue) this.instance).setValue(byteString);
                return this;
            }

            private Builder() {
                super(KeyValue.DEFAULT_INSTANCE);
            }
        }

        static {
            KeyValue keyValue = new KeyValue();
            DEFAULT_INSTANCE = keyValue;
            GeneratedMessageLite.registerDefaultInstance(KeyValue.class, keyValue);
        }

        private KeyValue() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKey() {
            this.bitField0_ &= -2;
            this.key_ = getDefaultInstance().getKey();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = getDefaultInstance().getValue();
        }

        public static KeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream) {
            return (KeyValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValue parseFrom(ByteBuffer byteBuffer) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<KeyValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKey(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.key_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyBytes(ByteString byteString) {
            this.key_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 2;
            this.value_ = byteString;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new KeyValue();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\n\u0001", new Object[]{"bitField0_", "key_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<KeyValue> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (KeyValue.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
        public String getKey() {
            return this.key_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
        public ByteString getKeyBytes() {
            return ByteString.copyFromUtf8(this.key_);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
        public ByteString getValue() {
            return this.value_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.KeyValueOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        public static Builder newBuilder(KeyValue keyValue) {
            return DEFAULT_INSTANCE.createBuilder(keyValue);
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static KeyValue parseFrom(ByteString byteString) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static KeyValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static KeyValue parseFrom(byte[] bArr) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static KeyValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static KeyValue parseFrom(InputStream inputStream) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface KeyValueOrBuilder extends MessageLiteOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        ByteString getValue();

        boolean hasKey();

        boolean hasValue();
    }

    public static final class Metadata extends GeneratedMessageLite<Metadata, Builder> implements MetadataOrBuilder {
        private static final Metadata DEFAULT_INSTANCE;
        public static final int DEVELOPER_MODE_ENABLED_FIELD_NUMBER = 2;
        public static final int LAST_FETCH_STATUS_FIELD_NUMBER = 1;
        public static final int LAST_KNOWN_EXPERIMENT_START_TIME_FIELD_NUMBER = 3;
        private static volatile Parser<Metadata> PARSER;
        private int bitField0_;
        private boolean developerModeEnabled_;
        private int lastFetchStatus_;
        private long lastKnownExperimentStartTime_;

        public static final class Builder extends GeneratedMessageLite.Builder<Metadata, Builder> implements MetadataOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder clearDeveloperModeEnabled() {
                copyOnWrite();
                ((Metadata) this.instance).clearDeveloperModeEnabled();
                return this;
            }

            public Builder clearLastFetchStatus() {
                copyOnWrite();
                ((Metadata) this.instance).clearLastFetchStatus();
                return this;
            }

            public Builder clearLastKnownExperimentStartTime() {
                copyOnWrite();
                ((Metadata) this.instance).clearLastKnownExperimentStartTime();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public boolean getDeveloperModeEnabled() {
                return ((Metadata) this.instance).getDeveloperModeEnabled();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public int getLastFetchStatus() {
                return ((Metadata) this.instance).getLastFetchStatus();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public long getLastKnownExperimentStartTime() {
                return ((Metadata) this.instance).getLastKnownExperimentStartTime();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public boolean hasDeveloperModeEnabled() {
                return ((Metadata) this.instance).hasDeveloperModeEnabled();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public boolean hasLastFetchStatus() {
                return ((Metadata) this.instance).hasLastFetchStatus();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
            public boolean hasLastKnownExperimentStartTime() {
                return ((Metadata) this.instance).hasLastKnownExperimentStartTime();
            }

            public Builder setDeveloperModeEnabled(boolean z8) {
                copyOnWrite();
                ((Metadata) this.instance).setDeveloperModeEnabled(z8);
                return this;
            }

            public Builder setLastFetchStatus(int i9) {
                copyOnWrite();
                ((Metadata) this.instance).setLastFetchStatus(i9);
                return this;
            }

            public Builder setLastKnownExperimentStartTime(long j9) {
                copyOnWrite();
                ((Metadata) this.instance).setLastKnownExperimentStartTime(j9);
                return this;
            }

            private Builder() {
                super(Metadata.DEFAULT_INSTANCE);
            }
        }

        static {
            Metadata metadata = new Metadata();
            DEFAULT_INSTANCE = metadata;
            GeneratedMessageLite.registerDefaultInstance(Metadata.class, metadata);
        }

        private Metadata() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeveloperModeEnabled() {
            this.bitField0_ &= -3;
            this.developerModeEnabled_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastFetchStatus() {
            this.bitField0_ &= -2;
            this.lastFetchStatus_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLastKnownExperimentStartTime() {
            this.bitField0_ &= -5;
            this.lastKnownExperimentStartTime_ = 0L;
        }

        public static Metadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream) {
            return (Metadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Metadata> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeveloperModeEnabled(boolean z8) {
            this.bitField0_ |= 2;
            this.developerModeEnabled_ = z8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastFetchStatus(int i9) {
            this.bitField0_ |= 1;
            this.lastFetchStatus_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastKnownExperimentStartTime(long j9) {
            this.bitField0_ |= 4;
            this.lastKnownExperimentStartTime_ = j9;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new Metadata();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0007\u0001\u0003\u0005\u0002", new Object[]{"bitField0_", "lastFetchStatus_", "developerModeEnabled_", "lastKnownExperimentStartTime_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Metadata> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Metadata.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public boolean getDeveloperModeEnabled() {
            return this.developerModeEnabled_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public int getLastFetchStatus() {
            return this.lastFetchStatus_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public long getLastKnownExperimentStartTime() {
            return this.lastKnownExperimentStartTime_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public boolean hasDeveloperModeEnabled() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public boolean hasLastFetchStatus() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.MetadataOrBuilder
        public boolean hasLastKnownExperimentStartTime() {
            return (this.bitField0_ & 4) != 0;
        }

        public static Builder newBuilder(Metadata metadata) {
            return DEFAULT_INSTANCE.createBuilder(metadata);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Metadata parseFrom(ByteString byteString) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Metadata parseFrom(byte[] bArr) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Metadata parseFrom(InputStream inputStream) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface MetadataOrBuilder extends MessageLiteOrBuilder {
        boolean getDeveloperModeEnabled();

        int getLastFetchStatus();

        long getLastKnownExperimentStartTime();

        boolean hasDeveloperModeEnabled();

        boolean hasLastFetchStatus();

        boolean hasLastKnownExperimentStartTime();
    }

    public static final class NamespaceKeyValue extends GeneratedMessageLite<NamespaceKeyValue, Builder> implements NamespaceKeyValueOrBuilder {
        private static final NamespaceKeyValue DEFAULT_INSTANCE;
        public static final int KEY_VALUE_FIELD_NUMBER = 2;
        public static final int NAMESPACE_FIELD_NUMBER = 1;
        private static volatile Parser<NamespaceKeyValue> PARSER;
        private int bitField0_;
        private String namespace_ = "";
        private Internal.ProtobufList<KeyValue> keyValue_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<NamespaceKeyValue, Builder> implements NamespaceKeyValueOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder addAllKeyValue(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addAllKeyValue(iterable);
                return this;
            }

            public Builder addKeyValue(KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(keyValue);
                return this;
            }

            public Builder clearKeyValue() {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).clearKeyValue();
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).clearNamespace();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public KeyValue getKeyValue(int i9) {
                return ((NamespaceKeyValue) this.instance).getKeyValue(i9);
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public int getKeyValueCount() {
                return ((NamespaceKeyValue) this.instance).getKeyValueCount();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public List<KeyValue> getKeyValueList() {
                return Collections.unmodifiableList(((NamespaceKeyValue) this.instance).getKeyValueList());
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public String getNamespace() {
                return ((NamespaceKeyValue) this.instance).getNamespace();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public ByteString getNamespaceBytes() {
                return ((NamespaceKeyValue) this.instance).getNamespaceBytes();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
            public boolean hasNamespace() {
                return ((NamespaceKeyValue) this.instance).hasNamespace();
            }

            public Builder removeKeyValue(int i9) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).removeKeyValue(i9);
                return this;
            }

            public Builder setKeyValue(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setKeyValue(i9, keyValue);
                return this;
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setNamespace(str);
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            private Builder() {
                super(NamespaceKeyValue.DEFAULT_INSTANCE);
            }

            public Builder addKeyValue(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(i9, keyValue);
                return this;
            }

            public Builder setKeyValue(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setKeyValue(i9, builder.build());
                return this;
            }

            public Builder addKeyValue(KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(builder.build());
                return this;
            }

            public Builder addKeyValue(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(i9, builder.build());
                return this;
            }
        }

        static {
            NamespaceKeyValue namespaceKeyValue = new NamespaceKeyValue();
            DEFAULT_INSTANCE = namespaceKeyValue;
            GeneratedMessageLite.registerDefaultInstance(NamespaceKeyValue.class, namespaceKeyValue);
        }

        private NamespaceKeyValue() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllKeyValue(Iterable<? extends KeyValue> iterable) {
            ensureKeyValueIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.keyValue_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addKeyValue(KeyValue keyValue) {
            keyValue.getClass();
            ensureKeyValueIsMutable();
            this.keyValue_.add(keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearKeyValue() {
            this.keyValue_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespace() {
            this.bitField0_ &= -2;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        private void ensureKeyValueIsMutable() {
            if (this.keyValue_.isModifiable()) {
                return;
            }
            this.keyValue_ = GeneratedMessageLite.mutableCopy(this.keyValue_);
        }

        public static NamespaceKeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NamespaceKeyValue parseDelimitedFrom(InputStream inputStream) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamespaceKeyValue parseFrom(ByteBuffer byteBuffer) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NamespaceKeyValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeKeyValue(int i9) {
            ensureKeyValueIsMutable();
            this.keyValue_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyValue(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureKeyValueIsMutable();
            this.keyValue_.set(i9, keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespace(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.namespace_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespaceBytes(ByteString byteString) {
            this.namespace_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new NamespaceKeyValue();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b", new Object[]{"bitField0_", "namespace_", "keyValue_", KeyValue.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NamespaceKeyValue> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (NamespaceKeyValue.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public KeyValue getKeyValue(int i9) {
            return this.keyValue_.get(i9);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public int getKeyValueCount() {
            return this.keyValue_.size();
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public List<KeyValue> getKeyValueList() {
            return this.keyValue_;
        }

        public KeyValueOrBuilder getKeyValueOrBuilder(int i9) {
            return this.keyValue_.get(i9);
        }

        public List<? extends KeyValueOrBuilder> getKeyValueOrBuilderList() {
            return this.keyValue_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public String getNamespace() {
            return this.namespace_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.NamespaceKeyValueOrBuilder
        public boolean hasNamespace() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(NamespaceKeyValue namespaceKeyValue) {
            return DEFAULT_INSTANCE.createBuilder(namespaceKeyValue);
        }

        public static NamespaceKeyValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(ByteString byteString) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addKeyValue(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureKeyValueIsMutable();
            this.keyValue_.add(i9, keyValue);
        }

        public static NamespaceKeyValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(byte[] bArr) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NamespaceKeyValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(InputStream inputStream) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamespaceKeyValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(CodedInputStream codedInputStream) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NamespaceKeyValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface NamespaceKeyValueOrBuilder extends MessageLiteOrBuilder {
        KeyValue getKeyValue(int i9);

        int getKeyValueCount();

        List<KeyValue> getKeyValueList();

        String getNamespace();

        ByteString getNamespaceBytes();

        boolean hasNamespace();
    }

    public static final class PersistedConfig extends GeneratedMessageLite<PersistedConfig, Builder> implements PersistedConfigOrBuilder {
        public static final int ACTIVE_CONFIG_HOLDER_FIELD_NUMBER = 2;
        public static final int APPLIED_RESOURCE_FIELD_NUMBER = 5;
        public static final int DEFAULTS_CONFIG_HOLDER_FIELD_NUMBER = 3;
        private static final PersistedConfig DEFAULT_INSTANCE;
        public static final int FETCHED_CONFIG_HOLDER_FIELD_NUMBER = 1;
        public static final int METADATA_FIELD_NUMBER = 4;
        private static volatile Parser<PersistedConfig> PARSER;
        private ConfigHolder activeConfigHolder_;
        private Internal.ProtobufList<Resource> appliedResource_ = GeneratedMessageLite.emptyProtobufList();
        private int bitField0_;
        private ConfigHolder defaultsConfigHolder_;
        private ConfigHolder fetchedConfigHolder_;
        private Metadata metadata_;

        public static final class Builder extends GeneratedMessageLite.Builder<PersistedConfig, Builder> implements PersistedConfigOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder addAllAppliedResource(Iterable<? extends Resource> iterable) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAllAppliedResource(iterable);
                return this;
            }

            public Builder addAppliedResource(Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(resource);
                return this;
            }

            public Builder clearActiveConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearActiveConfigHolder();
                return this;
            }

            public Builder clearAppliedResource() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearAppliedResource();
                return this;
            }

            public Builder clearDefaultsConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearDefaultsConfigHolder();
                return this;
            }

            public Builder clearFetchedConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearFetchedConfigHolder();
                return this;
            }

            public Builder clearMetadata() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearMetadata();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public ConfigHolder getActiveConfigHolder() {
                return ((PersistedConfig) this.instance).getActiveConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public Resource getAppliedResource(int i9) {
                return ((PersistedConfig) this.instance).getAppliedResource(i9);
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public int getAppliedResourceCount() {
                return ((PersistedConfig) this.instance).getAppliedResourceCount();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public List<Resource> getAppliedResourceList() {
                return Collections.unmodifiableList(((PersistedConfig) this.instance).getAppliedResourceList());
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public ConfigHolder getDefaultsConfigHolder() {
                return ((PersistedConfig) this.instance).getDefaultsConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public ConfigHolder getFetchedConfigHolder() {
                return ((PersistedConfig) this.instance).getFetchedConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public Metadata getMetadata() {
                return ((PersistedConfig) this.instance).getMetadata();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public boolean hasActiveConfigHolder() {
                return ((PersistedConfig) this.instance).hasActiveConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public boolean hasDefaultsConfigHolder() {
                return ((PersistedConfig) this.instance).hasDefaultsConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public boolean hasFetchedConfigHolder() {
                return ((PersistedConfig) this.instance).hasFetchedConfigHolder();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
            public boolean hasMetadata() {
                return ((PersistedConfig) this.instance).hasMetadata();
            }

            public Builder mergeActiveConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeActiveConfigHolder(configHolder);
                return this;
            }

            public Builder mergeDefaultsConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeDefaultsConfigHolder(configHolder);
                return this;
            }

            public Builder mergeFetchedConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeFetchedConfigHolder(configHolder);
                return this;
            }

            public Builder mergeMetadata(Metadata metadata) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeMetadata(metadata);
                return this;
            }

            public Builder removeAppliedResource(int i9) {
                copyOnWrite();
                ((PersistedConfig) this.instance).removeAppliedResource(i9);
                return this;
            }

            public Builder setActiveConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setActiveConfigHolder(configHolder);
                return this;
            }

            public Builder setAppliedResource(int i9, Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setAppliedResource(i9, resource);
                return this;
            }

            public Builder setDefaultsConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setDefaultsConfigHolder(configHolder);
                return this;
            }

            public Builder setFetchedConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setFetchedConfigHolder(configHolder);
                return this;
            }

            public Builder setMetadata(Metadata metadata) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setMetadata(metadata);
                return this;
            }

            private Builder() {
                super(PersistedConfig.DEFAULT_INSTANCE);
            }

            public Builder addAppliedResource(int i9, Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(i9, resource);
                return this;
            }

            public Builder setActiveConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setActiveConfigHolder(builder.build());
                return this;
            }

            public Builder setAppliedResource(int i9, Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setAppliedResource(i9, builder.build());
                return this;
            }

            public Builder setDefaultsConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setDefaultsConfigHolder(builder.build());
                return this;
            }

            public Builder setFetchedConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setFetchedConfigHolder(builder.build());
                return this;
            }

            public Builder setMetadata(Metadata.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setMetadata(builder.build());
                return this;
            }

            public Builder addAppliedResource(Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(builder.build());
                return this;
            }

            public Builder addAppliedResource(int i9, Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(i9, builder.build());
                return this;
            }
        }

        static {
            PersistedConfig persistedConfig = new PersistedConfig();
            DEFAULT_INSTANCE = persistedConfig;
            GeneratedMessageLite.registerDefaultInstance(PersistedConfig.class, persistedConfig);
        }

        private PersistedConfig() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAppliedResource(Iterable<? extends Resource> iterable) {
            ensureAppliedResourceIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.appliedResource_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAppliedResource(Resource resource) {
            resource.getClass();
            ensureAppliedResourceIsMutable();
            this.appliedResource_.add(resource);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActiveConfigHolder() {
            this.activeConfigHolder_ = null;
            this.bitField0_ &= -3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppliedResource() {
            this.appliedResource_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDefaultsConfigHolder() {
            this.defaultsConfigHolder_ = null;
            this.bitField0_ &= -5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFetchedConfigHolder() {
            this.fetchedConfigHolder_ = null;
            this.bitField0_ &= -2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMetadata() {
            this.metadata_ = null;
            this.bitField0_ &= -9;
        }

        private void ensureAppliedResourceIsMutable() {
            if (this.appliedResource_.isModifiable()) {
                return;
            }
            this.appliedResource_ = GeneratedMessageLite.mutableCopy(this.appliedResource_);
        }

        public static PersistedConfig getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeActiveConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            ConfigHolder configHolder2 = this.activeConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.activeConfigHolder_ = configHolder;
            } else {
                this.activeConfigHolder_ = ConfigHolder.newBuilder(this.activeConfigHolder_).mergeFrom((ConfigHolder.Builder) configHolder).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeDefaultsConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            ConfigHolder configHolder2 = this.defaultsConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.defaultsConfigHolder_ = configHolder;
            } else {
                this.defaultsConfigHolder_ = ConfigHolder.newBuilder(this.defaultsConfigHolder_).mergeFrom((ConfigHolder.Builder) configHolder).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFetchedConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            ConfigHolder configHolder2 = this.fetchedConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.fetchedConfigHolder_ = configHolder;
            } else {
                this.fetchedConfigHolder_ = ConfigHolder.newBuilder(this.fetchedConfigHolder_).mergeFrom((ConfigHolder.Builder) configHolder).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMetadata(Metadata metadata) {
            metadata.getClass();
            Metadata metadata2 = this.metadata_;
            if (metadata2 == null || metadata2 == Metadata.getDefaultInstance()) {
                this.metadata_ = metadata;
            } else {
                this.metadata_ = Metadata.newBuilder(this.metadata_).mergeFrom((Metadata.Builder) metadata).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PersistedConfig parseDelimitedFrom(InputStream inputStream) {
            return (PersistedConfig) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PersistedConfig parseFrom(ByteBuffer byteBuffer) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PersistedConfig> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAppliedResource(int i9) {
            ensureAppliedResourceIsMutable();
            this.appliedResource_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActiveConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            this.activeConfigHolder_ = configHolder;
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppliedResource(int i9, Resource resource) {
            resource.getClass();
            ensureAppliedResourceIsMutable();
            this.appliedResource_.set(i9, resource);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDefaultsConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            this.defaultsConfigHolder_ = configHolder;
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFetchedConfigHolder(ConfigHolder configHolder) {
            configHolder.getClass();
            this.fetchedConfigHolder_ = configHolder;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMetadata(Metadata metadata) {
            metadata.getClass();
            this.metadata_ = metadata;
            this.bitField0_ |= 8;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new PersistedConfig();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003\u0005\u001b", new Object[]{"bitField0_", "fetchedConfigHolder_", "activeConfigHolder_", "defaultsConfigHolder_", "metadata_", "appliedResource_", Resource.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PersistedConfig> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (PersistedConfig.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public ConfigHolder getActiveConfigHolder() {
            ConfigHolder configHolder = this.activeConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public Resource getAppliedResource(int i9) {
            return this.appliedResource_.get(i9);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public int getAppliedResourceCount() {
            return this.appliedResource_.size();
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public List<Resource> getAppliedResourceList() {
            return this.appliedResource_;
        }

        public ResourceOrBuilder getAppliedResourceOrBuilder(int i9) {
            return this.appliedResource_.get(i9);
        }

        public List<? extends ResourceOrBuilder> getAppliedResourceOrBuilderList() {
            return this.appliedResource_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public ConfigHolder getDefaultsConfigHolder() {
            ConfigHolder configHolder = this.defaultsConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public ConfigHolder getFetchedConfigHolder() {
            ConfigHolder configHolder = this.fetchedConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public Metadata getMetadata() {
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public boolean hasActiveConfigHolder() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public boolean hasDefaultsConfigHolder() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public boolean hasFetchedConfigHolder() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfigOrBuilder
        public boolean hasMetadata() {
            return (this.bitField0_ & 8) != 0;
        }

        public static Builder newBuilder(PersistedConfig persistedConfig) {
            return DEFAULT_INSTANCE.createBuilder(persistedConfig);
        }

        public static PersistedConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(ByteString byteString) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAppliedResource(int i9, Resource resource) {
            resource.getClass();
            ensureAppliedResourceIsMutable();
            this.appliedResource_.add(i9, resource);
        }

        public static PersistedConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(byte[] bArr) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PersistedConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(InputStream inputStream) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PersistedConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(CodedInputStream codedInputStream) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PersistedConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface PersistedConfigOrBuilder extends MessageLiteOrBuilder {
        ConfigHolder getActiveConfigHolder();

        Resource getAppliedResource(int i9);

        int getAppliedResourceCount();

        List<Resource> getAppliedResourceList();

        ConfigHolder getDefaultsConfigHolder();

        ConfigHolder getFetchedConfigHolder();

        Metadata getMetadata();

        boolean hasActiveConfigHolder();

        boolean hasDefaultsConfigHolder();

        boolean hasFetchedConfigHolder();

        boolean hasMetadata();
    }

    public static final class Resource extends GeneratedMessageLite<Resource, Builder> implements ResourceOrBuilder {
        public static final int APP_UPDATE_TIME_FIELD_NUMBER = 2;
        private static final Resource DEFAULT_INSTANCE;
        public static final int NAMESPACE_FIELD_NUMBER = 3;
        private static volatile Parser<Resource> PARSER = null;
        public static final int RESOURCE_ID_FIELD_NUMBER = 1;
        private long appUpdateTime_;
        private int bitField0_;
        private String namespace_ = "";
        private int resourceId_;

        public static final class Builder extends GeneratedMessageLite.Builder<Resource, Builder> implements ResourceOrBuilder {
            public /* synthetic */ Builder(C42091 c42091) {
                this();
            }

            public Builder clearAppUpdateTime() {
                copyOnWrite();
                ((Resource) this.instance).clearAppUpdateTime();
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((Resource) this.instance).clearNamespace();
                return this;
            }

            public Builder clearResourceId() {
                copyOnWrite();
                ((Resource) this.instance).clearResourceId();
                return this;
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public long getAppUpdateTime() {
                return ((Resource) this.instance).getAppUpdateTime();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public String getNamespace() {
                return ((Resource) this.instance).getNamespace();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public ByteString getNamespaceBytes() {
                return ((Resource) this.instance).getNamespaceBytes();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public int getResourceId() {
                return ((Resource) this.instance).getResourceId();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public boolean hasAppUpdateTime() {
                return ((Resource) this.instance).hasAppUpdateTime();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public boolean hasNamespace() {
                return ((Resource) this.instance).hasNamespace();
            }

            @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
            public boolean hasResourceId() {
                return ((Resource) this.instance).hasResourceId();
            }

            public Builder setAppUpdateTime(long j9) {
                copyOnWrite();
                ((Resource) this.instance).setAppUpdateTime(j9);
                return this;
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((Resource) this.instance).setNamespace(str);
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((Resource) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            public Builder setResourceId(int i9) {
                copyOnWrite();
                ((Resource) this.instance).setResourceId(i9);
                return this;
            }

            private Builder() {
                super(Resource.DEFAULT_INSTANCE);
            }
        }

        static {
            Resource resource = new Resource();
            DEFAULT_INSTANCE = resource;
            GeneratedMessageLite.registerDefaultInstance(Resource.class, resource);
        }

        private Resource() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppUpdateTime() {
            this.bitField0_ &= -3;
            this.appUpdateTime_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespace() {
            this.bitField0_ &= -5;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResourceId() {
            this.bitField0_ &= -2;
            this.resourceId_ = 0;
        }

        public static Resource getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Resource parseDelimitedFrom(InputStream inputStream) {
            return (Resource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseFrom(ByteBuffer byteBuffer) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<Resource> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppUpdateTime(long j9) {
            this.bitField0_ |= 2;
            this.appUpdateTime_ = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespace(String str) {
            str.getClass();
            this.bitField0_ |= 4;
            this.namespace_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespaceBytes(ByteString byteString) {
            this.namespace_ = byteString.toStringUtf8();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setResourceId(int i9) {
            this.bitField0_ |= 1;
            this.resourceId_ = i9;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C42091 c42091 = null;
            switch (C42091.f15589xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new Resource();
                case 2:
                    return new Builder(c42091);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0005\u0001\u0003\b\u0002", new Object[]{"bitField0_", "resourceId_", "appUpdateTime_", "namespace_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Resource> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (Resource.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                        }
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public long getAppUpdateTime() {
            return this.appUpdateTime_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public String getNamespace() {
            return this.namespace_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public int getResourceId() {
            return this.resourceId_;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public boolean hasAppUpdateTime() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public boolean hasNamespace() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.firebase.remoteconfig.proto.ConfigPersistence.ResourceOrBuilder
        public boolean hasResourceId() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(Resource resource) {
            return DEFAULT_INSTANCE.createBuilder(resource);
        }

        public static Resource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Resource parseFrom(ByteString byteString) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Resource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Resource parseFrom(byte[] bArr) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Resource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Resource parseFrom(InputStream inputStream) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ResourceOrBuilder extends MessageLiteOrBuilder {
        long getAppUpdateTime();

        String getNamespace();

        ByteString getNamespaceBytes();

        int getResourceId();

        boolean hasAppUpdateTime();

        boolean hasNamespace();

        boolean hasResourceId();
    }

    private ConfigPersistence() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
