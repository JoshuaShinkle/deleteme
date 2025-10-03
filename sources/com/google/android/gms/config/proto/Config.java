package com.google.android.gms.config.proto;

import com.google.android.gms.config.proto.Logs;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
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
public final class Config {

    /* renamed from: com.google.android.gms.config.proto.Config$1 */
    public static /* synthetic */ class C34601 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15326xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15326xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15326xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class AppConfigTable extends GeneratedMessageLite<AppConfigTable, Builder> implements AppConfigTableOrBuilder {
        public static final int APP_NAME_FIELD_NUMBER = 1;
        private static final AppConfigTable DEFAULT_INSTANCE;
        public static final int EXPERIMENT_PAYLOAD_FIELD_NUMBER = 3;
        public static final int NAMESPACE_CONFIG_FIELD_NUMBER = 2;
        private static volatile Parser<AppConfigTable> PARSER;
        private int bitField0_;
        private String appName_ = "";
        private Internal.ProtobufList<AppNamespaceConfigTable> namespaceConfig_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<ByteString> experimentPayload_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<AppConfigTable, Builder> implements AppConfigTableOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addAllExperimentPayload(iterable);
                return this;
            }

            public Builder addAllNamespaceConfig(Iterable<? extends AppNamespaceConfigTable> iterable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addAllNamespaceConfig(iterable);
                return this;
            }

            public Builder addExperimentPayload(ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addExperimentPayload(byteString);
                return this;
            }

            public Builder addNamespaceConfig(AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(appNamespaceConfigTable);
                return this;
            }

            public Builder clearAppName() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearAppName();
                return this;
            }

            public Builder clearExperimentPayload() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearExperimentPayload();
                return this;
            }

            public Builder clearNamespaceConfig() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearNamespaceConfig();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public String getAppName() {
                return ((AppConfigTable) this.instance).getAppName();
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public ByteString getAppNameBytes() {
                return ((AppConfigTable) this.instance).getAppNameBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public ByteString getExperimentPayload(int i9) {
                return ((AppConfigTable) this.instance).getExperimentPayload(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public int getExperimentPayloadCount() {
                return ((AppConfigTable) this.instance).getExperimentPayloadCount();
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public List<ByteString> getExperimentPayloadList() {
                return Collections.unmodifiableList(((AppConfigTable) this.instance).getExperimentPayloadList());
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public AppNamespaceConfigTable getNamespaceConfig(int i9) {
                return ((AppConfigTable) this.instance).getNamespaceConfig(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public int getNamespaceConfigCount() {
                return ((AppConfigTable) this.instance).getNamespaceConfigCount();
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public List<AppNamespaceConfigTable> getNamespaceConfigList() {
                return Collections.unmodifiableList(((AppConfigTable) this.instance).getNamespaceConfigList());
            }

            @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
            public boolean hasAppName() {
                return ((AppConfigTable) this.instance).hasAppName();
            }

            public Builder removeNamespaceConfig(int i9) {
                copyOnWrite();
                ((AppConfigTable) this.instance).removeNamespaceConfig(i9);
                return this;
            }

            public Builder setAppName(String str) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setAppName(str);
                return this;
            }

            public Builder setAppNameBytes(ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setAppNameBytes(byteString);
                return this;
            }

            public Builder setExperimentPayload(int i9, ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setExperimentPayload(i9, byteString);
                return this;
            }

            public Builder setNamespaceConfig(int i9, AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setNamespaceConfig(i9, appNamespaceConfigTable);
                return this;
            }

            private Builder() {
                super(AppConfigTable.DEFAULT_INSTANCE);
            }

            public Builder addNamespaceConfig(int i9, AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(i9, appNamespaceConfigTable);
                return this;
            }

            public Builder setNamespaceConfig(int i9, AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setNamespaceConfig(i9, builder.build());
                return this;
            }

            public Builder addNamespaceConfig(AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(builder.build());
                return this;
            }

            public Builder addNamespaceConfig(int i9, AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(i9, builder.build());
                return this;
            }
        }

        static {
            AppConfigTable appConfigTable = new AppConfigTable();
            DEFAULT_INSTANCE = appConfigTable;
            GeneratedMessageLite.registerDefaultInstance(AppConfigTable.class, appConfigTable);
        }

        private AppConfigTable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
            ensureExperimentPayloadIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.experimentPayload_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllNamespaceConfig(Iterable<? extends AppNamespaceConfigTable> iterable) {
            ensureNamespaceConfigIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.namespaceConfig_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addExperimentPayload(ByteString byteString) {
            byteString.getClass();
            ensureExperimentPayloadIsMutable();
            this.experimentPayload_.add(byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceConfig(AppNamespaceConfigTable appNamespaceConfigTable) {
            appNamespaceConfigTable.getClass();
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.add(appNamespaceConfigTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppName() {
            this.bitField0_ &= -2;
            this.appName_ = getDefaultInstance().getAppName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExperimentPayload() {
            this.experimentPayload_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespaceConfig() {
            this.namespaceConfig_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureExperimentPayloadIsMutable() {
            if (this.experimentPayload_.isModifiable()) {
                return;
            }
            this.experimentPayload_ = GeneratedMessageLite.mutableCopy(this.experimentPayload_);
        }

        private void ensureNamespaceConfigIsMutable() {
            if (this.namespaceConfig_.isModifiable()) {
                return;
            }
            this.namespaceConfig_ = GeneratedMessageLite.mutableCopy(this.namespaceConfig_);
        }

        public static AppConfigTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AppConfigTable parseDelimitedFrom(InputStream inputStream) {
            return (AppConfigTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppConfigTable parseFrom(ByteBuffer byteBuffer) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AppConfigTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeNamespaceConfig(int i9) {
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppName(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.appName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppNameBytes(ByteString byteString) {
            this.appName_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExperimentPayload(int i9, ByteString byteString) {
            byteString.getClass();
            ensureExperimentPayloadIsMutable();
            this.experimentPayload_.set(i9, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespaceConfig(int i9, AppNamespaceConfigTable appNamespaceConfigTable) {
            appNamespaceConfigTable.getClass();
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.set(i9, appNamespaceConfigTable);
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new AppConfigTable();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\b\u0000\u0002\u001b\u0003\u001c", new Object[]{"bitField0_", "appName_", "namespaceConfig_", AppNamespaceConfigTable.class, "experimentPayload_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AppConfigTable> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (AppConfigTable.class) {
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

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public String getAppName() {
            return this.appName_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public ByteString getAppNameBytes() {
            return ByteString.copyFromUtf8(this.appName_);
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public ByteString getExperimentPayload(int i9) {
            return this.experimentPayload_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public int getExperimentPayloadCount() {
            return this.experimentPayload_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public List<ByteString> getExperimentPayloadList() {
            return this.experimentPayload_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public AppNamespaceConfigTable getNamespaceConfig(int i9) {
            return this.namespaceConfig_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public int getNamespaceConfigCount() {
            return this.namespaceConfig_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public List<AppNamespaceConfigTable> getNamespaceConfigList() {
            return this.namespaceConfig_;
        }

        public AppNamespaceConfigTableOrBuilder getNamespaceConfigOrBuilder(int i9) {
            return this.namespaceConfig_.get(i9);
        }

        public List<? extends AppNamespaceConfigTableOrBuilder> getNamespaceConfigOrBuilderList() {
            return this.namespaceConfig_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppConfigTableOrBuilder
        public boolean hasAppName() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(AppConfigTable appConfigTable) {
            return DEFAULT_INSTANCE.createBuilder(appConfigTable);
        }

        public static AppConfigTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(ByteString byteString) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceConfig(int i9, AppNamespaceConfigTable appNamespaceConfigTable) {
            appNamespaceConfigTable.getClass();
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.add(i9, appNamespaceConfigTable);
        }

        public static AppConfigTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(byte[] bArr) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AppConfigTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(InputStream inputStream) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppConfigTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(CodedInputStream codedInputStream) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AppConfigTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface AppConfigTableOrBuilder extends MessageLiteOrBuilder {
        String getAppName();

        ByteString getAppNameBytes();

        ByteString getExperimentPayload(int i9);

        int getExperimentPayloadCount();

        List<ByteString> getExperimentPayloadList();

        AppNamespaceConfigTable getNamespaceConfig(int i9);

        int getNamespaceConfigCount();

        List<AppNamespaceConfigTable> getNamespaceConfigList();

        boolean hasAppName();
    }

    public static final class AppNamespaceConfigTable extends GeneratedMessageLite<AppNamespaceConfigTable, Builder> implements AppNamespaceConfigTableOrBuilder {
        private static final AppNamespaceConfigTable DEFAULT_INSTANCE;
        public static final int DIGEST_FIELD_NUMBER = 2;
        public static final int ENTRY_FIELD_NUMBER = 3;
        public static final int NAMESPACE_FIELD_NUMBER = 1;
        private static volatile Parser<AppNamespaceConfigTable> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 4;
        private int bitField0_;
        private int status_;
        private String namespace_ = "";
        private String digest_ = "";
        private Internal.ProtobufList<KeyValue> entry_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<AppNamespaceConfigTable, Builder> implements AppNamespaceConfigTableOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllEntry(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addAllEntry(iterable);
                return this;
            }

            public Builder addEntry(KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(keyValue);
                return this;
            }

            public Builder clearDigest() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearDigest();
                return this;
            }

            public Builder clearEntry() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearEntry();
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearNamespace();
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearStatus();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public String getDigest() {
                return ((AppNamespaceConfigTable) this.instance).getDigest();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public ByteString getDigestBytes() {
                return ((AppNamespaceConfigTable) this.instance).getDigestBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public KeyValue getEntry(int i9) {
                return ((AppNamespaceConfigTable) this.instance).getEntry(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public int getEntryCount() {
                return ((AppNamespaceConfigTable) this.instance).getEntryCount();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public List<KeyValue> getEntryList() {
                return Collections.unmodifiableList(((AppNamespaceConfigTable) this.instance).getEntryList());
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public String getNamespace() {
                return ((AppNamespaceConfigTable) this.instance).getNamespace();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public ByteString getNamespaceBytes() {
                return ((AppNamespaceConfigTable) this.instance).getNamespaceBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public NamespaceStatus getStatus() {
                return ((AppNamespaceConfigTable) this.instance).getStatus();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public boolean hasDigest() {
                return ((AppNamespaceConfigTable) this.instance).hasDigest();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public boolean hasNamespace() {
                return ((AppNamespaceConfigTable) this.instance).hasNamespace();
            }

            @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
            public boolean hasStatus() {
                return ((AppNamespaceConfigTable) this.instance).hasStatus();
            }

            public Builder removeEntry(int i9) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).removeEntry(i9);
                return this;
            }

            public Builder setDigest(String str) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setDigest(str);
                return this;
            }

            public Builder setDigestBytes(ByteString byteString) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setDigestBytes(byteString);
                return this;
            }

            public Builder setEntry(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setEntry(i9, keyValue);
                return this;
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setNamespace(str);
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            public Builder setStatus(NamespaceStatus namespaceStatus) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setStatus(namespaceStatus);
                return this;
            }

            private Builder() {
                super(AppNamespaceConfigTable.DEFAULT_INSTANCE);
            }

            public Builder addEntry(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(i9, keyValue);
                return this;
            }

            public Builder setEntry(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setEntry(i9, builder.build());
                return this;
            }

            public Builder addEntry(KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(builder.build());
                return this;
            }

            public Builder addEntry(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(i9, builder.build());
                return this;
            }
        }

        public enum NamespaceStatus implements Internal.EnumLite {
            UPDATE(0),
            NO_TEMPLATE(1),
            NO_CHANGE(2),
            EMPTY_CONFIG(3),
            NOT_AUTHORIZED(4);

            public static final int EMPTY_CONFIG_VALUE = 3;
            public static final int NOT_AUTHORIZED_VALUE = 4;
            public static final int NO_CHANGE_VALUE = 2;
            public static final int NO_TEMPLATE_VALUE = 1;
            public static final int UPDATE_VALUE = 0;
            private static final Internal.EnumLiteMap<NamespaceStatus> internalValueMap = new Internal.EnumLiteMap<NamespaceStatus>() { // from class: com.google.android.gms.config.proto.Config.AppNamespaceConfigTable.NamespaceStatus.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public NamespaceStatus findValueByNumber(int i9) {
                    return NamespaceStatus.forNumber(i9);
                }
            };
            private final int value;

            public static final class NamespaceStatusVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new NamespaceStatusVerifier();

                private NamespaceStatusVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i9) {
                    return NamespaceStatus.forNumber(i9) != null;
                }
            }

            NamespaceStatus(int i9) {
                this.value = i9;
            }

            public static NamespaceStatus forNumber(int i9) {
                if (i9 == 0) {
                    return UPDATE;
                }
                if (i9 == 1) {
                    return NO_TEMPLATE;
                }
                if (i9 == 2) {
                    return NO_CHANGE;
                }
                if (i9 == 3) {
                    return EMPTY_CONFIG;
                }
                if (i9 != 4) {
                    return null;
                }
                return NOT_AUTHORIZED;
            }

            public static Internal.EnumLiteMap<NamespaceStatus> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return NamespaceStatusVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static NamespaceStatus valueOf(int i9) {
                return forNumber(i9);
            }
        }

        static {
            AppNamespaceConfigTable appNamespaceConfigTable = new AppNamespaceConfigTable();
            DEFAULT_INSTANCE = appNamespaceConfigTable;
            GeneratedMessageLite.registerDefaultInstance(AppNamespaceConfigTable.class, appNamespaceConfigTable);
        }

        private AppNamespaceConfigTable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllEntry(Iterable<? extends KeyValue> iterable) {
            ensureEntryIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.entry_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEntry(KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.add(keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDigest() {
            this.bitField0_ &= -3;
            this.digest_ = getDefaultInstance().getDigest();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEntry() {
            this.entry_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespace() {
            this.bitField0_ &= -2;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -5;
            this.status_ = 0;
        }

        private void ensureEntryIsMutable() {
            if (this.entry_.isModifiable()) {
                return;
            }
            this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
        }

        public static AppNamespaceConfigTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AppNamespaceConfigTable parseDelimitedFrom(InputStream inputStream) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppNamespaceConfigTable parseFrom(ByteBuffer byteBuffer) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AppNamespaceConfigTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeEntry(int i9) {
            ensureEntryIsMutable();
            this.entry_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDigest(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.digest_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDigestBytes(ByteString byteString) {
            this.digest_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEntry(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.set(i9, keyValue);
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

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(NamespaceStatus namespaceStatus) {
            this.status_ = namespaceStatus.getNumber();
            this.bitField0_ |= 4;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new AppNamespaceConfigTable();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001b\u0004\f\u0002", new Object[]{"bitField0_", "namespace_", "digest_", "entry_", KeyValue.class, "status_", NamespaceStatus.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AppNamespaceConfigTable> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (AppNamespaceConfigTable.class) {
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

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public String getDigest() {
            return this.digest_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public ByteString getDigestBytes() {
            return ByteString.copyFromUtf8(this.digest_);
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public KeyValue getEntry(int i9) {
            return this.entry_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public int getEntryCount() {
            return this.entry_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public List<KeyValue> getEntryList() {
            return this.entry_;
        }

        public KeyValueOrBuilder getEntryOrBuilder(int i9) {
            return this.entry_.get(i9);
        }

        public List<? extends KeyValueOrBuilder> getEntryOrBuilderList() {
            return this.entry_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public String getNamespace() {
            return this.namespace_;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public NamespaceStatus getStatus() {
            NamespaceStatus namespaceStatusForNumber = NamespaceStatus.forNumber(this.status_);
            return namespaceStatusForNumber == null ? NamespaceStatus.UPDATE : namespaceStatusForNumber;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public boolean hasDigest() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public boolean hasNamespace() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.AppNamespaceConfigTableOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 4) != 0;
        }

        public static Builder newBuilder(AppNamespaceConfigTable appNamespaceConfigTable) {
            return DEFAULT_INSTANCE.createBuilder(appNamespaceConfigTable);
        }

        public static AppNamespaceConfigTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(ByteString byteString) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEntry(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.add(i9, keyValue);
        }

        public static AppNamespaceConfigTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(byte[] bArr) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AppNamespaceConfigTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(InputStream inputStream) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppNamespaceConfigTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(CodedInputStream codedInputStream) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AppNamespaceConfigTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface AppNamespaceConfigTableOrBuilder extends MessageLiteOrBuilder {
        String getDigest();

        ByteString getDigestBytes();

        KeyValue getEntry(int i9);

        int getEntryCount();

        List<KeyValue> getEntryList();

        String getNamespace();

        ByteString getNamespaceBytes();

        AppNamespaceConfigTable.NamespaceStatus getStatus();

        boolean hasDigest();

        boolean hasNamespace();

        boolean hasStatus();
    }

    public static final class ConfigFetchRequest extends GeneratedMessageLite<ConfigFetchRequest, Builder> implements ConfigFetchRequestOrBuilder {
        public static final int ANDROID_ID_FIELD_NUMBER = 1;
        public static final int API_LEVEL_FIELD_NUMBER = 8;
        public static final int CLIENT_VERSION_FIELD_NUMBER = 6;
        public static final int CONFIG_FIELD_NUMBER = 5;
        private static final ConfigFetchRequest DEFAULT_INSTANCE;
        public static final int DEVICE_COUNTRY_FIELD_NUMBER = 9;
        public static final int DEVICE_DATA_VERSION_INFO_FIELD_NUMBER = 3;
        public static final int DEVICE_LOCALE_FIELD_NUMBER = 10;
        public static final int DEVICE_SUBTYPE_FIELD_NUMBER = 12;
        public static final int DEVICE_TIMEZONE_ID_FIELD_NUMBER = 14;
        public static final int DEVICE_TYPE_FIELD_NUMBER = 11;
        public static final int GMS_CORE_VERSION_FIELD_NUMBER = 7;
        public static final int OS_VERSION_FIELD_NUMBER = 13;
        public static final int PACKAGE_DATA_FIELD_NUMBER = 2;
        private static volatile Parser<ConfigFetchRequest> PARSER = null;
        public static final int SECURITY_TOKEN_FIELD_NUMBER = 4;
        private long androidId_;
        private int apiLevel_;
        private int bitField0_;
        private int clientVersion_;
        private Logs.AndroidConfigFetchProto config_;
        private int deviceSubtype_;
        private int deviceType_;
        private int gmsCoreVersion_;
        private long securityToken_;
        private Internal.ProtobufList<PackageData> packageData_ = GeneratedMessageLite.emptyProtobufList();
        private String deviceDataVersionInfo_ = "";
        private String deviceCountry_ = "";
        private String deviceLocale_ = "";
        private String osVersion_ = "";
        private String deviceTimezoneId_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchRequest, Builder> implements ConfigFetchRequestOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllPackageData(Iterable<? extends PackageData> iterable) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addAllPackageData(iterable);
                return this;
            }

            public Builder addPackageData(PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(packageData);
                return this;
            }

            public Builder clearAndroidId() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearAndroidId();
                return this;
            }

            public Builder clearApiLevel() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearApiLevel();
                return this;
            }

            public Builder clearClientVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearClientVersion();
                return this;
            }

            public Builder clearConfig() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearConfig();
                return this;
            }

            public Builder clearDeviceCountry() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceCountry();
                return this;
            }

            public Builder clearDeviceDataVersionInfo() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceDataVersionInfo();
                return this;
            }

            public Builder clearDeviceLocale() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceLocale();
                return this;
            }

            public Builder clearDeviceSubtype() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceSubtype();
                return this;
            }

            public Builder clearDeviceTimezoneId() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceTimezoneId();
                return this;
            }

            public Builder clearDeviceType() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceType();
                return this;
            }

            public Builder clearGmsCoreVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearGmsCoreVersion();
                return this;
            }

            public Builder clearOsVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearOsVersion();
                return this;
            }

            public Builder clearPackageData() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearPackageData();
                return this;
            }

            public Builder clearSecurityToken() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearSecurityToken();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public long getAndroidId() {
                return ((ConfigFetchRequest) this.instance).getAndroidId();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getApiLevel() {
                return ((ConfigFetchRequest) this.instance).getApiLevel();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getClientVersion() {
                return ((ConfigFetchRequest) this.instance).getClientVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public Logs.AndroidConfigFetchProto getConfig() {
                return ((ConfigFetchRequest) this.instance).getConfig();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public String getDeviceCountry() {
                return ((ConfigFetchRequest) this.instance).getDeviceCountry();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public ByteString getDeviceCountryBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceCountryBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public String getDeviceDataVersionInfo() {
                return ((ConfigFetchRequest) this.instance).getDeviceDataVersionInfo();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public ByteString getDeviceDataVersionInfoBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceDataVersionInfoBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public String getDeviceLocale() {
                return ((ConfigFetchRequest) this.instance).getDeviceLocale();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public ByteString getDeviceLocaleBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceLocaleBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getDeviceSubtype() {
                return ((ConfigFetchRequest) this.instance).getDeviceSubtype();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public String getDeviceTimezoneId() {
                return ((ConfigFetchRequest) this.instance).getDeviceTimezoneId();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public ByteString getDeviceTimezoneIdBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceTimezoneIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getDeviceType() {
                return ((ConfigFetchRequest) this.instance).getDeviceType();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getGmsCoreVersion() {
                return ((ConfigFetchRequest) this.instance).getGmsCoreVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public String getOsVersion() {
                return ((ConfigFetchRequest) this.instance).getOsVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public ByteString getOsVersionBytes() {
                return ((ConfigFetchRequest) this.instance).getOsVersionBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public PackageData getPackageData(int i9) {
                return ((ConfigFetchRequest) this.instance).getPackageData(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public int getPackageDataCount() {
                return ((ConfigFetchRequest) this.instance).getPackageDataCount();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public List<PackageData> getPackageDataList() {
                return Collections.unmodifiableList(((ConfigFetchRequest) this.instance).getPackageDataList());
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public long getSecurityToken() {
                return ((ConfigFetchRequest) this.instance).getSecurityToken();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasAndroidId() {
                return ((ConfigFetchRequest) this.instance).hasAndroidId();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasApiLevel() {
                return ((ConfigFetchRequest) this.instance).hasApiLevel();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasClientVersion() {
                return ((ConfigFetchRequest) this.instance).hasClientVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasConfig() {
                return ((ConfigFetchRequest) this.instance).hasConfig();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceCountry() {
                return ((ConfigFetchRequest) this.instance).hasDeviceCountry();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceDataVersionInfo() {
                return ((ConfigFetchRequest) this.instance).hasDeviceDataVersionInfo();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceLocale() {
                return ((ConfigFetchRequest) this.instance).hasDeviceLocale();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceSubtype() {
                return ((ConfigFetchRequest) this.instance).hasDeviceSubtype();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceTimezoneId() {
                return ((ConfigFetchRequest) this.instance).hasDeviceTimezoneId();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasDeviceType() {
                return ((ConfigFetchRequest) this.instance).hasDeviceType();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasGmsCoreVersion() {
                return ((ConfigFetchRequest) this.instance).hasGmsCoreVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasOsVersion() {
                return ((ConfigFetchRequest) this.instance).hasOsVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
            public boolean hasSecurityToken() {
                return ((ConfigFetchRequest) this.instance).hasSecurityToken();
            }

            public Builder mergeConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).mergeConfig(androidConfigFetchProto);
                return this;
            }

            public Builder removePackageData(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).removePackageData(i9);
                return this;
            }

            public Builder setAndroidId(long j9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setAndroidId(j9);
                return this;
            }

            public Builder setApiLevel(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setApiLevel(i9);
                return this;
            }

            public Builder setClientVersion(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setClientVersion(i9);
                return this;
            }

            public Builder setConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setConfig(androidConfigFetchProto);
                return this;
            }

            public Builder setDeviceCountry(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceCountry(str);
                return this;
            }

            public Builder setDeviceCountryBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceCountryBytes(byteString);
                return this;
            }

            public Builder setDeviceDataVersionInfo(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceDataVersionInfo(str);
                return this;
            }

            public Builder setDeviceDataVersionInfoBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceDataVersionInfoBytes(byteString);
                return this;
            }

            public Builder setDeviceLocale(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceLocale(str);
                return this;
            }

            public Builder setDeviceLocaleBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceLocaleBytes(byteString);
                return this;
            }

            public Builder setDeviceSubtype(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceSubtype(i9);
                return this;
            }

            public Builder setDeviceTimezoneId(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceTimezoneId(str);
                return this;
            }

            public Builder setDeviceTimezoneIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceTimezoneIdBytes(byteString);
                return this;
            }

            public Builder setDeviceType(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceType(i9);
                return this;
            }

            public Builder setGmsCoreVersion(int i9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setGmsCoreVersion(i9);
                return this;
            }

            public Builder setOsVersion(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setOsVersion(str);
                return this;
            }

            public Builder setOsVersionBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setOsVersionBytes(byteString);
                return this;
            }

            public Builder setPackageData(int i9, PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setPackageData(i9, packageData);
                return this;
            }

            public Builder setSecurityToken(long j9) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setSecurityToken(j9);
                return this;
            }

            private Builder() {
                super(ConfigFetchRequest.DEFAULT_INSTANCE);
            }

            public Builder addPackageData(int i9, PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(i9, packageData);
                return this;
            }

            public Builder setConfig(Logs.AndroidConfigFetchProto.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setConfig(builder.build());
                return this;
            }

            public Builder setPackageData(int i9, PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setPackageData(i9, builder.build());
                return this;
            }

            public Builder addPackageData(PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(builder.build());
                return this;
            }

            public Builder addPackageData(int i9, PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(i9, builder.build());
                return this;
            }
        }

        static {
            ConfigFetchRequest configFetchRequest = new ConfigFetchRequest();
            DEFAULT_INSTANCE = configFetchRequest;
            GeneratedMessageLite.registerDefaultInstance(ConfigFetchRequest.class, configFetchRequest);
        }

        private ConfigFetchRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPackageData(Iterable<? extends PackageData> iterable) {
            ensurePackageDataIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.packageData_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageData(PackageData packageData) {
            packageData.getClass();
            ensurePackageDataIsMutable();
            this.packageData_.add(packageData);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAndroidId() {
            this.bitField0_ &= -3;
            this.androidId_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearApiLevel() {
            this.bitField0_ &= -65;
            this.apiLevel_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearClientVersion() {
            this.bitField0_ &= -17;
            this.clientVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfig() {
            this.config_ = null;
            this.bitField0_ &= -2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceCountry() {
            this.bitField0_ &= -129;
            this.deviceCountry_ = getDefaultInstance().getDeviceCountry();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceDataVersionInfo() {
            this.bitField0_ &= -5;
            this.deviceDataVersionInfo_ = getDefaultInstance().getDeviceDataVersionInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceLocale() {
            this.bitField0_ &= -257;
            this.deviceLocale_ = getDefaultInstance().getDeviceLocale();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceSubtype() {
            this.bitField0_ &= -1025;
            this.deviceSubtype_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceTimezoneId() {
            this.bitField0_ &= -4097;
            this.deviceTimezoneId_ = getDefaultInstance().getDeviceTimezoneId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceType() {
            this.bitField0_ &= -513;
            this.deviceType_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGmsCoreVersion() {
            this.bitField0_ &= -33;
            this.gmsCoreVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOsVersion() {
            this.bitField0_ &= -2049;
            this.osVersion_ = getDefaultInstance().getOsVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageData() {
            this.packageData_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSecurityToken() {
            this.bitField0_ &= -9;
            this.securityToken_ = 0L;
        }

        private void ensurePackageDataIsMutable() {
            if (this.packageData_.isModifiable()) {
                return;
            }
            this.packageData_ = GeneratedMessageLite.mutableCopy(this.packageData_);
        }

        public static ConfigFetchRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
            androidConfigFetchProto.getClass();
            Logs.AndroidConfigFetchProto androidConfigFetchProto2 = this.config_;
            if (androidConfigFetchProto2 == null || androidConfigFetchProto2 == Logs.AndroidConfigFetchProto.getDefaultInstance()) {
                this.config_ = androidConfigFetchProto;
            } else {
                this.config_ = Logs.AndroidConfigFetchProto.newBuilder(this.config_).mergeFrom((Logs.AndroidConfigFetchProto.Builder) androidConfigFetchProto).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConfigFetchRequest parseDelimitedFrom(InputStream inputStream) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchRequest parseFrom(ByteBuffer byteBuffer) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConfigFetchRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePackageData(int i9) {
            ensurePackageDataIsMutable();
            this.packageData_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAndroidId(long j9) {
            this.bitField0_ |= 2;
            this.androidId_ = j9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setApiLevel(int i9) {
            this.bitField0_ |= 64;
            this.apiLevel_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClientVersion(int i9) {
            this.bitField0_ |= 16;
            this.clientVersion_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
            androidConfigFetchProto.getClass();
            this.config_ = androidConfigFetchProto;
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceCountry(String str) {
            str.getClass();
            this.bitField0_ |= 128;
            this.deviceCountry_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceCountryBytes(ByteString byteString) {
            this.deviceCountry_ = byteString.toStringUtf8();
            this.bitField0_ |= 128;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceDataVersionInfo(String str) {
            str.getClass();
            this.bitField0_ |= 4;
            this.deviceDataVersionInfo_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceDataVersionInfoBytes(ByteString byteString) {
            this.deviceDataVersionInfo_ = byteString.toStringUtf8();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceLocale(String str) {
            str.getClass();
            this.bitField0_ |= 256;
            this.deviceLocale_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceLocaleBytes(ByteString byteString) {
            this.deviceLocale_ = byteString.toStringUtf8();
            this.bitField0_ |= 256;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceSubtype(int i9) {
            this.bitField0_ |= UserMetadata.MAX_ATTRIBUTE_SIZE;
            this.deviceSubtype_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceTimezoneId(String str) {
            str.getClass();
            this.bitField0_ |= 4096;
            this.deviceTimezoneId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceTimezoneIdBytes(ByteString byteString) {
            this.deviceTimezoneId_ = byteString.toStringUtf8();
            this.bitField0_ |= 4096;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceType(int i9) {
            this.bitField0_ |= 512;
            this.deviceType_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGmsCoreVersion(int i9) {
            this.bitField0_ |= 32;
            this.gmsCoreVersion_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOsVersion(String str) {
            str.getClass();
            this.bitField0_ |= 2048;
            this.osVersion_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOsVersionBytes(ByteString byteString) {
            this.osVersion_ = byteString.toStringUtf8();
            this.bitField0_ |= 2048;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageData(int i9, PackageData packageData) {
            packageData.getClass();
            ensurePackageDataIsMutable();
            this.packageData_.set(i9, packageData);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSecurityToken(long j9) {
            this.bitField0_ |= 8;
            this.securityToken_ = j9;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchRequest();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0001\u0000\u0001\u0005\u0001\u0002\u001b\u0003\b\u0002\u0004\u0005\u0003\u0005\t\u0000\u0006\u0004\u0004\u0007\u0004\u0005\b\u0004\u0006\t\b\u0007\n\b\b\u000b\u0004\t\f\u0004\n\r\b\u000b\u000e\b\f", new Object[]{"bitField0_", "androidId_", "packageData_", PackageData.class, "deviceDataVersionInfo_", "securityToken_", "config_", "clientVersion_", "gmsCoreVersion_", "apiLevel_", "deviceCountry_", "deviceLocale_", "deviceType_", "deviceSubtype_", "osVersion_", "deviceTimezoneId_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ConfigFetchRequest> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ConfigFetchRequest.class) {
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

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public long getAndroidId() {
            return this.androidId_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getApiLevel() {
            return this.apiLevel_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getClientVersion() {
            return this.clientVersion_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public Logs.AndroidConfigFetchProto getConfig() {
            Logs.AndroidConfigFetchProto androidConfigFetchProto = this.config_;
            return androidConfigFetchProto == null ? Logs.AndroidConfigFetchProto.getDefaultInstance() : androidConfigFetchProto;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public String getDeviceCountry() {
            return this.deviceCountry_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public ByteString getDeviceCountryBytes() {
            return ByteString.copyFromUtf8(this.deviceCountry_);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public String getDeviceDataVersionInfo() {
            return this.deviceDataVersionInfo_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public ByteString getDeviceDataVersionInfoBytes() {
            return ByteString.copyFromUtf8(this.deviceDataVersionInfo_);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public String getDeviceLocale() {
            return this.deviceLocale_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public ByteString getDeviceLocaleBytes() {
            return ByteString.copyFromUtf8(this.deviceLocale_);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getDeviceSubtype() {
            return this.deviceSubtype_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public String getDeviceTimezoneId() {
            return this.deviceTimezoneId_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public ByteString getDeviceTimezoneIdBytes() {
            return ByteString.copyFromUtf8(this.deviceTimezoneId_);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getDeviceType() {
            return this.deviceType_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getGmsCoreVersion() {
            return this.gmsCoreVersion_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public String getOsVersion() {
            return this.osVersion_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public ByteString getOsVersionBytes() {
            return ByteString.copyFromUtf8(this.osVersion_);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public PackageData getPackageData(int i9) {
            return this.packageData_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public int getPackageDataCount() {
            return this.packageData_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public List<PackageData> getPackageDataList() {
            return this.packageData_;
        }

        public PackageDataOrBuilder getPackageDataOrBuilder(int i9) {
            return this.packageData_.get(i9);
        }

        public List<? extends PackageDataOrBuilder> getPackageDataOrBuilderList() {
            return this.packageData_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public long getSecurityToken() {
            return this.securityToken_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasAndroidId() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasApiLevel() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasClientVersion() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasConfig() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceCountry() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceDataVersionInfo() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceLocale() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceSubtype() {
            return (this.bitField0_ & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceTimezoneId() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasDeviceType() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasGmsCoreVersion() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasOsVersion() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchRequestOrBuilder
        public boolean hasSecurityToken() {
            return (this.bitField0_ & 8) != 0;
        }

        public static Builder newBuilder(ConfigFetchRequest configFetchRequest) {
            return DEFAULT_INSTANCE.createBuilder(configFetchRequest);
        }

        public static ConfigFetchRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(ByteString byteString) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageData(int i9, PackageData packageData) {
            packageData.getClass();
            ensurePackageDataIsMutable();
            this.packageData_.add(i9, packageData);
        }

        public static ConfigFetchRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(byte[] bArr) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(InputStream inputStream) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(CodedInputStream codedInputStream) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ConfigFetchRequestOrBuilder extends MessageLiteOrBuilder {
        long getAndroidId();

        int getApiLevel();

        int getClientVersion();

        Logs.AndroidConfigFetchProto getConfig();

        String getDeviceCountry();

        ByteString getDeviceCountryBytes();

        String getDeviceDataVersionInfo();

        ByteString getDeviceDataVersionInfoBytes();

        String getDeviceLocale();

        ByteString getDeviceLocaleBytes();

        int getDeviceSubtype();

        String getDeviceTimezoneId();

        ByteString getDeviceTimezoneIdBytes();

        int getDeviceType();

        int getGmsCoreVersion();

        String getOsVersion();

        ByteString getOsVersionBytes();

        PackageData getPackageData(int i9);

        int getPackageDataCount();

        List<PackageData> getPackageDataList();

        long getSecurityToken();

        boolean hasAndroidId();

        boolean hasApiLevel();

        boolean hasClientVersion();

        boolean hasConfig();

        boolean hasDeviceCountry();

        boolean hasDeviceDataVersionInfo();

        boolean hasDeviceLocale();

        boolean hasDeviceSubtype();

        boolean hasDeviceTimezoneId();

        boolean hasDeviceType();

        boolean hasGmsCoreVersion();

        boolean hasOsVersion();

        boolean hasSecurityToken();
    }

    public static final class ConfigFetchResponse extends GeneratedMessageLite<ConfigFetchResponse, Builder> implements ConfigFetchResponseOrBuilder {
        public static final int APP_CONFIG_FIELD_NUMBER = 4;
        private static final ConfigFetchResponse DEFAULT_INSTANCE;
        public static final int INTERNAL_METADATA_FIELD_NUMBER = 3;
        public static final int PACKAGE_TABLE_FIELD_NUMBER = 1;
        private static volatile Parser<ConfigFetchResponse> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int status_;
        private Internal.ProtobufList<PackageTable> packageTable_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<KeyValue> internalMetadata_ = GeneratedMessageLite.emptyProtobufList();
        private Internal.ProtobufList<AppConfigTable> appConfig_ = GeneratedMessageLite.emptyProtobufList();

        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchResponse, Builder> implements ConfigFetchResponseOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllAppConfig(Iterable<? extends AppConfigTable> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllAppConfig(iterable);
                return this;
            }

            public Builder addAllInternalMetadata(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllInternalMetadata(iterable);
                return this;
            }

            public Builder addAllPackageTable(Iterable<? extends PackageTable> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllPackageTable(iterable);
                return this;
            }

            public Builder addAppConfig(AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(appConfigTable);
                return this;
            }

            public Builder addInternalMetadata(KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(keyValue);
                return this;
            }

            public Builder addPackageTable(PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(packageTable);
                return this;
            }

            public Builder clearAppConfig() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearAppConfig();
                return this;
            }

            public Builder clearInternalMetadata() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearInternalMetadata();
                return this;
            }

            public Builder clearPackageTable() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearPackageTable();
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearStatus();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public AppConfigTable getAppConfig(int i9) {
                return ((ConfigFetchResponse) this.instance).getAppConfig(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public int getAppConfigCount() {
                return ((ConfigFetchResponse) this.instance).getAppConfigCount();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public List<AppConfigTable> getAppConfigList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getAppConfigList());
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public KeyValue getInternalMetadata(int i9) {
                return ((ConfigFetchResponse) this.instance).getInternalMetadata(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public int getInternalMetadataCount() {
                return ((ConfigFetchResponse) this.instance).getInternalMetadataCount();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public List<KeyValue> getInternalMetadataList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getInternalMetadataList());
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public PackageTable getPackageTable(int i9) {
                return ((ConfigFetchResponse) this.instance).getPackageTable(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public int getPackageTableCount() {
                return ((ConfigFetchResponse) this.instance).getPackageTableCount();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public List<PackageTable> getPackageTableList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getPackageTableList());
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public ResponseStatus getStatus() {
                return ((ConfigFetchResponse) this.instance).getStatus();
            }

            @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
            public boolean hasStatus() {
                return ((ConfigFetchResponse) this.instance).hasStatus();
            }

            public Builder removeAppConfig(int i9) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removeAppConfig(i9);
                return this;
            }

            public Builder removeInternalMetadata(int i9) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removeInternalMetadata(i9);
                return this;
            }

            public Builder removePackageTable(int i9) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removePackageTable(i9);
                return this;
            }

            public Builder setAppConfig(int i9, AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setAppConfig(i9, appConfigTable);
                return this;
            }

            public Builder setInternalMetadata(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setInternalMetadata(i9, keyValue);
                return this;
            }

            public Builder setPackageTable(int i9, PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setPackageTable(i9, packageTable);
                return this;
            }

            public Builder setStatus(ResponseStatus responseStatus) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setStatus(responseStatus);
                return this;
            }

            private Builder() {
                super(ConfigFetchResponse.DEFAULT_INSTANCE);
            }

            public Builder addAppConfig(int i9, AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(i9, appConfigTable);
                return this;
            }

            public Builder addInternalMetadata(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(i9, keyValue);
                return this;
            }

            public Builder addPackageTable(int i9, PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(i9, packageTable);
                return this;
            }

            public Builder setAppConfig(int i9, AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setAppConfig(i9, builder.build());
                return this;
            }

            public Builder setInternalMetadata(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setInternalMetadata(i9, builder.build());
                return this;
            }

            public Builder setPackageTable(int i9, PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setPackageTable(i9, builder.build());
                return this;
            }

            public Builder addAppConfig(AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(builder.build());
                return this;
            }

            public Builder addInternalMetadata(KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(builder.build());
                return this;
            }

            public Builder addPackageTable(PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(builder.build());
                return this;
            }

            public Builder addAppConfig(int i9, AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(i9, builder.build());
                return this;
            }

            public Builder addInternalMetadata(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(i9, builder.build());
                return this;
            }

            public Builder addPackageTable(int i9, PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(i9, builder.build());
                return this;
            }
        }

        public enum ResponseStatus implements Internal.EnumLite {
            SUCCESS(0),
            NO_PACKAGES_IN_REQUEST(1);

            public static final int NO_PACKAGES_IN_REQUEST_VALUE = 1;
            public static final int SUCCESS_VALUE = 0;
            private static final Internal.EnumLiteMap<ResponseStatus> internalValueMap = new Internal.EnumLiteMap<ResponseStatus>() { // from class: com.google.android.gms.config.proto.Config.ConfigFetchResponse.ResponseStatus.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ResponseStatus findValueByNumber(int i9) {
                    return ResponseStatus.forNumber(i9);
                }
            };
            private final int value;

            public static final class ResponseStatusVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ResponseStatusVerifier();

                private ResponseStatusVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i9) {
                    return ResponseStatus.forNumber(i9) != null;
                }
            }

            ResponseStatus(int i9) {
                this.value = i9;
            }

            public static ResponseStatus forNumber(int i9) {
                if (i9 == 0) {
                    return SUCCESS;
                }
                if (i9 != 1) {
                    return null;
                }
                return NO_PACKAGES_IN_REQUEST;
            }

            public static Internal.EnumLiteMap<ResponseStatus> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ResponseStatusVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ResponseStatus valueOf(int i9) {
                return forNumber(i9);
            }
        }

        static {
            ConfigFetchResponse configFetchResponse = new ConfigFetchResponse();
            DEFAULT_INSTANCE = configFetchResponse;
            GeneratedMessageLite.registerDefaultInstance(ConfigFetchResponse.class, configFetchResponse);
        }

        private ConfigFetchResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAppConfig(Iterable<? extends AppConfigTable> iterable) {
            ensureAppConfigIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.appConfig_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllInternalMetadata(Iterable<? extends KeyValue> iterable) {
            ensureInternalMetadataIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.internalMetadata_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllPackageTable(Iterable<? extends PackageTable> iterable) {
            ensurePackageTableIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.packageTable_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAppConfig(AppConfigTable appConfigTable) {
            appConfigTable.getClass();
            ensureAppConfigIsMutable();
            this.appConfig_.add(appConfigTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addInternalMetadata(KeyValue keyValue) {
            keyValue.getClass();
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.add(keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageTable(PackageTable packageTable) {
            packageTable.getClass();
            ensurePackageTableIsMutable();
            this.packageTable_.add(packageTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppConfig() {
            this.appConfig_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInternalMetadata() {
            this.internalMetadata_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageTable() {
            this.packageTable_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -2;
            this.status_ = 0;
        }

        private void ensureAppConfigIsMutable() {
            if (this.appConfig_.isModifiable()) {
                return;
            }
            this.appConfig_ = GeneratedMessageLite.mutableCopy(this.appConfig_);
        }

        private void ensureInternalMetadataIsMutable() {
            if (this.internalMetadata_.isModifiable()) {
                return;
            }
            this.internalMetadata_ = GeneratedMessageLite.mutableCopy(this.internalMetadata_);
        }

        private void ensurePackageTableIsMutable() {
            if (this.packageTable_.isModifiable()) {
                return;
            }
            this.packageTable_ = GeneratedMessageLite.mutableCopy(this.packageTable_);
        }

        public static ConfigFetchResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConfigFetchResponse parseDelimitedFrom(InputStream inputStream) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchResponse parseFrom(ByteBuffer byteBuffer) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConfigFetchResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAppConfig(int i9) {
            ensureAppConfigIsMutable();
            this.appConfig_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeInternalMetadata(int i9) {
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removePackageTable(int i9) {
            ensurePackageTableIsMutable();
            this.packageTable_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppConfig(int i9, AppConfigTable appConfigTable) {
            appConfigTable.getClass();
            ensureAppConfigIsMutable();
            this.appConfig_.set(i9, appConfigTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInternalMetadata(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.set(i9, keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageTable(int i9, PackageTable packageTable) {
            packageTable.getClass();
            ensurePackageTableIsMutable();
            this.packageTable_.set(i9, packageTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStatus(ResponseStatus responseStatus) {
            this.status_ = responseStatus.getNumber();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchResponse();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0003\u0000\u0001\u001b\u0002\f\u0000\u0003\u001b\u0004\u001b", new Object[]{"bitField0_", "packageTable_", PackageTable.class, "status_", ResponseStatus.internalGetVerifier(), "internalMetadata_", KeyValue.class, "appConfig_", AppConfigTable.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ConfigFetchResponse> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ConfigFetchResponse.class) {
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

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public AppConfigTable getAppConfig(int i9) {
            return this.appConfig_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public int getAppConfigCount() {
            return this.appConfig_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public List<AppConfigTable> getAppConfigList() {
            return this.appConfig_;
        }

        public AppConfigTableOrBuilder getAppConfigOrBuilder(int i9) {
            return this.appConfig_.get(i9);
        }

        public List<? extends AppConfigTableOrBuilder> getAppConfigOrBuilderList() {
            return this.appConfig_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public KeyValue getInternalMetadata(int i9) {
            return this.internalMetadata_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public int getInternalMetadataCount() {
            return this.internalMetadata_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public List<KeyValue> getInternalMetadataList() {
            return this.internalMetadata_;
        }

        public KeyValueOrBuilder getInternalMetadataOrBuilder(int i9) {
            return this.internalMetadata_.get(i9);
        }

        public List<? extends KeyValueOrBuilder> getInternalMetadataOrBuilderList() {
            return this.internalMetadata_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public PackageTable getPackageTable(int i9) {
            return this.packageTable_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public int getPackageTableCount() {
            return this.packageTable_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public List<PackageTable> getPackageTableList() {
            return this.packageTable_;
        }

        public PackageTableOrBuilder getPackageTableOrBuilder(int i9) {
            return this.packageTable_.get(i9);
        }

        public List<? extends PackageTableOrBuilder> getPackageTableOrBuilderList() {
            return this.packageTable_;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public ResponseStatus getStatus() {
            ResponseStatus responseStatusForNumber = ResponseStatus.forNumber(this.status_);
            return responseStatusForNumber == null ? ResponseStatus.SUCCESS : responseStatusForNumber;
        }

        @Override // com.google.android.gms.config.proto.Config.ConfigFetchResponseOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(ConfigFetchResponse configFetchResponse) {
            return DEFAULT_INSTANCE.createBuilder(configFetchResponse);
        }

        public static ConfigFetchResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(ByteString byteString) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAppConfig(int i9, AppConfigTable appConfigTable) {
            appConfigTable.getClass();
            ensureAppConfigIsMutable();
            this.appConfig_.add(i9, appConfigTable);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addInternalMetadata(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.add(i9, keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addPackageTable(int i9, PackageTable packageTable) {
            packageTable.getClass();
            ensurePackageTableIsMutable();
            this.packageTable_.add(i9, packageTable);
        }

        public static ConfigFetchResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(byte[] bArr) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(InputStream inputStream) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(CodedInputStream codedInputStream) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ConfigFetchResponseOrBuilder extends MessageLiteOrBuilder {
        AppConfigTable getAppConfig(int i9);

        int getAppConfigCount();

        List<AppConfigTable> getAppConfigList();

        KeyValue getInternalMetadata(int i9);

        int getInternalMetadataCount();

        List<KeyValue> getInternalMetadataList();

        PackageTable getPackageTable(int i9);

        int getPackageTableCount();

        List<PackageTable> getPackageTableList();

        ConfigFetchResponse.ResponseStatus getStatus();

        boolean hasStatus();
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
            public /* synthetic */ Builder(C34601 c34601) {
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

            @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
            public String getKey() {
                return ((KeyValue) this.instance).getKey();
            }

            @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
            public ByteString getKeyBytes() {
                return ((KeyValue) this.instance).getKeyBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
            public ByteString getValue() {
                return ((KeyValue) this.instance).getValue();
            }

            @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
            public boolean hasKey() {
                return ((KeyValue) this.instance).hasKey();
            }

            @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
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
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new KeyValue();
                case 2:
                    return new Builder(c34601);
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

        @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
        public String getKey() {
            return this.key_;
        }

        @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
        public ByteString getKeyBytes() {
            return ByteString.copyFromUtf8(this.key_);
        }

        @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
        public ByteString getValue() {
            return this.value_;
        }

        @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.KeyValueOrBuilder
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

    public static final class NamedValue extends GeneratedMessageLite<NamedValue, Builder> implements NamedValueOrBuilder {
        private static final NamedValue DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<NamedValue> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private String value_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<NamedValue, Builder> implements NamedValueOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder clearName() {
                copyOnWrite();
                ((NamedValue) this.instance).clearName();
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((NamedValue) this.instance).clearValue();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public String getName() {
                return ((NamedValue) this.instance).getName();
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public ByteString getNameBytes() {
                return ((NamedValue) this.instance).getNameBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public String getValue() {
                return ((NamedValue) this.instance).getValue();
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public ByteString getValueBytes() {
                return ((NamedValue) this.instance).getValueBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public boolean hasName() {
                return ((NamedValue) this.instance).hasName();
            }

            @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
            public boolean hasValue() {
                return ((NamedValue) this.instance).hasValue();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((NamedValue) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((NamedValue) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setValue(String str) {
                copyOnWrite();
                ((NamedValue) this.instance).setValue(str);
                return this;
            }

            public Builder setValueBytes(ByteString byteString) {
                copyOnWrite();
                ((NamedValue) this.instance).setValueBytes(byteString);
                return this;
            }

            private Builder() {
                super(NamedValue.DEFAULT_INSTANCE);
            }
        }

        static {
            NamedValue namedValue = new NamedValue();
            DEFAULT_INSTANCE = namedValue;
            GeneratedMessageLite.registerDefaultInstance(NamedValue.class, namedValue);
        }

        private NamedValue() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = getDefaultInstance().getValue();
        }

        public static NamedValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static NamedValue parseDelimitedFrom(InputStream inputStream) {
            return (NamedValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamedValue parseFrom(ByteBuffer byteBuffer) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<NamedValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            this.name_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.value_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValueBytes(ByteString byteString) {
            this.value_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new NamedValue();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"bitField0_", "name_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<NamedValue> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (NamedValue.class) {
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

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public String getValue() {
            return this.value_;
        }

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public ByteString getValueBytes() {
            return ByteString.copyFromUtf8(this.value_);
        }

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.NamedValueOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 2) != 0;
        }

        public static Builder newBuilder(NamedValue namedValue) {
            return DEFAULT_INSTANCE.createBuilder(namedValue);
        }

        public static NamedValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamedValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static NamedValue parseFrom(ByteString byteString) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NamedValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NamedValue parseFrom(byte[] bArr) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NamedValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NamedValue parseFrom(InputStream inputStream) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamedValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamedValue parseFrom(CodedInputStream codedInputStream) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NamedValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface NamedValueOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getValue();

        ByteString getValueBytes();

        boolean hasName();

        boolean hasValue();
    }

    public static final class PackageData extends GeneratedMessageLite<PackageData, Builder> implements PackageDataOrBuilder {
        public static final int ACTIVE_CONFIG_AGE_SECONDS_FIELD_NUMBER = 20;
        public static final int ANALYTICS_USER_PROPERTY_FIELD_NUMBER = 17;
        public static final int APP_CERT_HASH_FIELD_NUMBER = 10;
        public static final int APP_INSTANCE_ID_FIELD_NUMBER = 12;
        public static final int APP_INSTANCE_ID_TOKEN_FIELD_NUMBER = 14;
        public static final int APP_VERSION_CODE_FIELD_NUMBER = 11;
        public static final int APP_VERSION_FIELD_NUMBER = 13;
        public static final int CERT_HASH_FIELD_NUMBER = 4;
        public static final int CONFIG_ID_FIELD_NUMBER = 5;
        public static final int CUSTOM_VARIABLE_FIELD_NUMBER = 9;
        private static final PackageData DEFAULT_INSTANCE;
        public static final int DIGEST_FIELD_NUMBER = 3;
        public static final int FETCHED_CONFIG_AGE_SECONDS_FIELD_NUMBER = 19;
        public static final int GAMES_PROJECT_ID_FIELD_NUMBER = 7;
        public static final int GMP_PROJECT_ID_FIELD_NUMBER = 6;
        public static final int NAMESPACE_DIGEST_FIELD_NUMBER = 8;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PackageData> PARSER = null;
        public static final int REQUESTED_CACHE_EXPIRATION_SECONDS_FIELD_NUMBER = 18;
        public static final int REQUESTED_HIDDEN_NAMESPACE_FIELD_NUMBER = 15;
        public static final int SDK_VERSION_FIELD_NUMBER = 16;
        public static final int VERSION_CODE_FIELD_NUMBER = 2;
        private int activeConfigAgeSeconds_;
        private Internal.ProtobufList<NamedValue> analyticsUserProperty_;
        private ByteString appCertHash_;
        private String appInstanceIdToken_;
        private String appInstanceId_;
        private int appVersionCode_;
        private String appVersion_;
        private int bitField0_;
        private ByteString certHash_;
        private String configId_;
        private Internal.ProtobufList<NamedValue> customVariable_;
        private ByteString digest_;
        private int fetchedConfigAgeSeconds_;
        private String gamesProjectId_;
        private String gmpProjectId_;
        private Internal.ProtobufList<NamedValue> namespaceDigest_;
        private String packageName_;
        private int requestedCacheExpirationSeconds_;
        private Internal.ProtobufList<String> requestedHiddenNamespace_;
        private int sdkVersion_;
        private int versionCode_;

        public static final class Builder extends GeneratedMessageLite.Builder<PackageData, Builder> implements PackageDataOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllAnalyticsUserProperty(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllAnalyticsUserProperty(iterable);
                return this;
            }

            public Builder addAllCustomVariable(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllCustomVariable(iterable);
                return this;
            }

            public Builder addAllNamespaceDigest(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllNamespaceDigest(iterable);
                return this;
            }

            public Builder addAllRequestedHiddenNamespace(Iterable<String> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllRequestedHiddenNamespace(iterable);
                return this;
            }

            public Builder addAnalyticsUserProperty(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(namedValue);
                return this;
            }

            public Builder addCustomVariable(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(namedValue);
                return this;
            }

            public Builder addNamespaceDigest(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(namedValue);
                return this;
            }

            public Builder addRequestedHiddenNamespace(String str) {
                copyOnWrite();
                ((PackageData) this.instance).addRequestedHiddenNamespace(str);
                return this;
            }

            public Builder addRequestedHiddenNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).addRequestedHiddenNamespaceBytes(byteString);
                return this;
            }

            public Builder clearActiveConfigAgeSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearActiveConfigAgeSeconds();
                return this;
            }

            public Builder clearAnalyticsUserProperty() {
                copyOnWrite();
                ((PackageData) this.instance).clearAnalyticsUserProperty();
                return this;
            }

            public Builder clearAppCertHash() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppCertHash();
                return this;
            }

            public Builder clearAppInstanceId() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppInstanceId();
                return this;
            }

            public Builder clearAppInstanceIdToken() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppInstanceIdToken();
                return this;
            }

            public Builder clearAppVersion() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppVersion();
                return this;
            }

            public Builder clearAppVersionCode() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppVersionCode();
                return this;
            }

            public Builder clearCertHash() {
                copyOnWrite();
                ((PackageData) this.instance).clearCertHash();
                return this;
            }

            public Builder clearConfigId() {
                copyOnWrite();
                ((PackageData) this.instance).clearConfigId();
                return this;
            }

            public Builder clearCustomVariable() {
                copyOnWrite();
                ((PackageData) this.instance).clearCustomVariable();
                return this;
            }

            public Builder clearDigest() {
                copyOnWrite();
                ((PackageData) this.instance).clearDigest();
                return this;
            }

            public Builder clearFetchedConfigAgeSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearFetchedConfigAgeSeconds();
                return this;
            }

            public Builder clearGamesProjectId() {
                copyOnWrite();
                ((PackageData) this.instance).clearGamesProjectId();
                return this;
            }

            public Builder clearGmpProjectId() {
                copyOnWrite();
                ((PackageData) this.instance).clearGmpProjectId();
                return this;
            }

            public Builder clearNamespaceDigest() {
                copyOnWrite();
                ((PackageData) this.instance).clearNamespaceDigest();
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageData) this.instance).clearPackageName();
                return this;
            }

            public Builder clearRequestedCacheExpirationSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearRequestedCacheExpirationSeconds();
                return this;
            }

            public Builder clearRequestedHiddenNamespace() {
                copyOnWrite();
                ((PackageData) this.instance).clearRequestedHiddenNamespace();
                return this;
            }

            public Builder clearSdkVersion() {
                copyOnWrite();
                ((PackageData) this.instance).clearSdkVersion();
                return this;
            }

            public Builder clearVersionCode() {
                copyOnWrite();
                ((PackageData) this.instance).clearVersionCode();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getActiveConfigAgeSeconds() {
                return ((PackageData) this.instance).getActiveConfigAgeSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public NamedValue getAnalyticsUserProperty(int i9) {
                return ((PackageData) this.instance).getAnalyticsUserProperty(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getAnalyticsUserPropertyCount() {
                return ((PackageData) this.instance).getAnalyticsUserPropertyCount();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public List<NamedValue> getAnalyticsUserPropertyList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getAnalyticsUserPropertyList());
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getAppCertHash() {
                return ((PackageData) this.instance).getAppCertHash();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getAppInstanceId() {
                return ((PackageData) this.instance).getAppInstanceId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getAppInstanceIdBytes() {
                return ((PackageData) this.instance).getAppInstanceIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getAppInstanceIdToken() {
                return ((PackageData) this.instance).getAppInstanceIdToken();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getAppInstanceIdTokenBytes() {
                return ((PackageData) this.instance).getAppInstanceIdTokenBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getAppVersion() {
                return ((PackageData) this.instance).getAppVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getAppVersionBytes() {
                return ((PackageData) this.instance).getAppVersionBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getAppVersionCode() {
                return ((PackageData) this.instance).getAppVersionCode();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getCertHash() {
                return ((PackageData) this.instance).getCertHash();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getConfigId() {
                return ((PackageData) this.instance).getConfigId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getConfigIdBytes() {
                return ((PackageData) this.instance).getConfigIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public NamedValue getCustomVariable(int i9) {
                return ((PackageData) this.instance).getCustomVariable(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getCustomVariableCount() {
                return ((PackageData) this.instance).getCustomVariableCount();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public List<NamedValue> getCustomVariableList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getCustomVariableList());
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getDigest() {
                return ((PackageData) this.instance).getDigest();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getFetchedConfigAgeSeconds() {
                return ((PackageData) this.instance).getFetchedConfigAgeSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getGamesProjectId() {
                return ((PackageData) this.instance).getGamesProjectId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getGamesProjectIdBytes() {
                return ((PackageData) this.instance).getGamesProjectIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getGmpProjectId() {
                return ((PackageData) this.instance).getGmpProjectId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getGmpProjectIdBytes() {
                return ((PackageData) this.instance).getGmpProjectIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public NamedValue getNamespaceDigest(int i9) {
                return ((PackageData) this.instance).getNamespaceDigest(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getNamespaceDigestCount() {
                return ((PackageData) this.instance).getNamespaceDigestCount();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public List<NamedValue> getNamespaceDigestList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getNamespaceDigestList());
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getPackageName() {
                return ((PackageData) this.instance).getPackageName();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getPackageNameBytes() {
                return ((PackageData) this.instance).getPackageNameBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getRequestedCacheExpirationSeconds() {
                return ((PackageData) this.instance).getRequestedCacheExpirationSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public String getRequestedHiddenNamespace(int i9) {
                return ((PackageData) this.instance).getRequestedHiddenNamespace(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public ByteString getRequestedHiddenNamespaceBytes(int i9) {
                return ((PackageData) this.instance).getRequestedHiddenNamespaceBytes(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getRequestedHiddenNamespaceCount() {
                return ((PackageData) this.instance).getRequestedHiddenNamespaceCount();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public List<String> getRequestedHiddenNamespaceList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getRequestedHiddenNamespaceList());
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getSdkVersion() {
                return ((PackageData) this.instance).getSdkVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public int getVersionCode() {
                return ((PackageData) this.instance).getVersionCode();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasActiveConfigAgeSeconds() {
                return ((PackageData) this.instance).hasActiveConfigAgeSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasAppCertHash() {
                return ((PackageData) this.instance).hasAppCertHash();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasAppInstanceId() {
                return ((PackageData) this.instance).hasAppInstanceId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasAppInstanceIdToken() {
                return ((PackageData) this.instance).hasAppInstanceIdToken();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasAppVersion() {
                return ((PackageData) this.instance).hasAppVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasAppVersionCode() {
                return ((PackageData) this.instance).hasAppVersionCode();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasCertHash() {
                return ((PackageData) this.instance).hasCertHash();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasConfigId() {
                return ((PackageData) this.instance).hasConfigId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasDigest() {
                return ((PackageData) this.instance).hasDigest();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasFetchedConfigAgeSeconds() {
                return ((PackageData) this.instance).hasFetchedConfigAgeSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasGamesProjectId() {
                return ((PackageData) this.instance).hasGamesProjectId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasGmpProjectId() {
                return ((PackageData) this.instance).hasGmpProjectId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasPackageName() {
                return ((PackageData) this.instance).hasPackageName();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasRequestedCacheExpirationSeconds() {
                return ((PackageData) this.instance).hasRequestedCacheExpirationSeconds();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasSdkVersion() {
                return ((PackageData) this.instance).hasSdkVersion();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
            public boolean hasVersionCode() {
                return ((PackageData) this.instance).hasVersionCode();
            }

            public Builder removeAnalyticsUserProperty(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).removeAnalyticsUserProperty(i9);
                return this;
            }

            public Builder removeCustomVariable(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).removeCustomVariable(i9);
                return this;
            }

            public Builder removeNamespaceDigest(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).removeNamespaceDigest(i9);
                return this;
            }

            public Builder setActiveConfigAgeSeconds(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setActiveConfigAgeSeconds(i9);
                return this;
            }

            public Builder setAnalyticsUserProperty(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setAnalyticsUserProperty(i9, namedValue);
                return this;
            }

            public Builder setAppCertHash(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppCertHash(byteString);
                return this;
            }

            public Builder setAppInstanceId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceId(str);
                return this;
            }

            public Builder setAppInstanceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdBytes(byteString);
                return this;
            }

            public Builder setAppInstanceIdToken(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdToken(str);
                return this;
            }

            public Builder setAppInstanceIdTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdTokenBytes(byteString);
                return this;
            }

            public Builder setAppVersion(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersion(str);
                return this;
            }

            public Builder setAppVersionBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersionBytes(byteString);
                return this;
            }

            public Builder setAppVersionCode(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersionCode(i9);
                return this;
            }

            public Builder setCertHash(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setCertHash(byteString);
                return this;
            }

            public Builder setConfigId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setConfigId(str);
                return this;
            }

            public Builder setConfigIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setConfigIdBytes(byteString);
                return this;
            }

            public Builder setCustomVariable(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setCustomVariable(i9, namedValue);
                return this;
            }

            public Builder setDigest(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setDigest(byteString);
                return this;
            }

            public Builder setFetchedConfigAgeSeconds(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setFetchedConfigAgeSeconds(i9);
                return this;
            }

            public Builder setGamesProjectId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setGamesProjectId(str);
                return this;
            }

            public Builder setGamesProjectIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setGamesProjectIdBytes(byteString);
                return this;
            }

            public Builder setGmpProjectId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setGmpProjectId(str);
                return this;
            }

            public Builder setGmpProjectIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setGmpProjectIdBytes(byteString);
                return this;
            }

            public Builder setNamespaceDigest(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setNamespaceDigest(i9, namedValue);
                return this;
            }

            public Builder setPackageName(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setPackageName(str);
                return this;
            }

            public Builder setPackageNameBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setPackageNameBytes(byteString);
                return this;
            }

            public Builder setRequestedCacheExpirationSeconds(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setRequestedCacheExpirationSeconds(i9);
                return this;
            }

            public Builder setRequestedHiddenNamespace(int i9, String str) {
                copyOnWrite();
                ((PackageData) this.instance).setRequestedHiddenNamespace(i9, str);
                return this;
            }

            public Builder setSdkVersion(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setSdkVersion(i9);
                return this;
            }

            public Builder setVersionCode(int i9) {
                copyOnWrite();
                ((PackageData) this.instance).setVersionCode(i9);
                return this;
            }

            private Builder() {
                super(PackageData.DEFAULT_INSTANCE);
            }

            public Builder addAnalyticsUserProperty(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(i9, namedValue);
                return this;
            }

            public Builder addCustomVariable(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(i9, namedValue);
                return this;
            }

            public Builder addNamespaceDigest(int i9, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(i9, namedValue);
                return this;
            }

            public Builder setAnalyticsUserProperty(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setAnalyticsUserProperty(i9, builder.build());
                return this;
            }

            public Builder setCustomVariable(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setCustomVariable(i9, builder.build());
                return this;
            }

            public Builder setNamespaceDigest(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setNamespaceDigest(i9, builder.build());
                return this;
            }

            public Builder addAnalyticsUserProperty(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(builder.build());
                return this;
            }

            public Builder addCustomVariable(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(builder.build());
                return this;
            }

            public Builder addNamespaceDigest(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(builder.build());
                return this;
            }

            public Builder addAnalyticsUserProperty(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(i9, builder.build());
                return this;
            }

            public Builder addCustomVariable(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(i9, builder.build());
                return this;
            }

            public Builder addNamespaceDigest(int i9, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(i9, builder.build());
                return this;
            }
        }

        static {
            PackageData packageData = new PackageData();
            DEFAULT_INSTANCE = packageData;
            GeneratedMessageLite.registerDefaultInstance(PackageData.class, packageData);
        }

        private PackageData() {
            ByteString byteString = ByteString.EMPTY;
            this.digest_ = byteString;
            this.certHash_ = byteString;
            this.configId_ = "";
            this.packageName_ = "";
            this.gmpProjectId_ = "";
            this.gamesProjectId_ = "";
            this.namespaceDigest_ = GeneratedMessageLite.emptyProtobufList();
            this.customVariable_ = GeneratedMessageLite.emptyProtobufList();
            this.appCertHash_ = byteString;
            this.appVersion_ = "";
            this.appInstanceId_ = "";
            this.appInstanceIdToken_ = "";
            this.requestedHiddenNamespace_ = GeneratedMessageLite.emptyProtobufList();
            this.analyticsUserProperty_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllAnalyticsUserProperty(Iterable<? extends NamedValue> iterable) {
            ensureAnalyticsUserPropertyIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.analyticsUserProperty_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllCustomVariable(Iterable<? extends NamedValue> iterable) {
            ensureCustomVariableIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.customVariable_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllNamespaceDigest(Iterable<? extends NamedValue> iterable) {
            ensureNamespaceDigestIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.namespaceDigest_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllRequestedHiddenNamespace(Iterable<String> iterable) {
            ensureRequestedHiddenNamespaceIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.requestedHiddenNamespace_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAnalyticsUserProperty(NamedValue namedValue) {
            namedValue.getClass();
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.add(namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCustomVariable(NamedValue namedValue) {
            namedValue.getClass();
            ensureCustomVariableIsMutable();
            this.customVariable_.add(namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceDigest(NamedValue namedValue) {
            namedValue.getClass();
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.add(namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRequestedHiddenNamespace(String str) {
            str.getClass();
            ensureRequestedHiddenNamespaceIsMutable();
            this.requestedHiddenNamespace_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addRequestedHiddenNamespaceBytes(ByteString byteString) {
            ensureRequestedHiddenNamespaceIsMutable();
            this.requestedHiddenNamespace_.add(byteString.toStringUtf8());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearActiveConfigAgeSeconds() {
            this.bitField0_ &= -32769;
            this.activeConfigAgeSeconds_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAnalyticsUserProperty() {
            this.analyticsUserProperty_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppCertHash() {
            this.bitField0_ &= -129;
            this.appCertHash_ = getDefaultInstance().getAppCertHash();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppInstanceId() {
            this.bitField0_ &= -1025;
            this.appInstanceId_ = getDefaultInstance().getAppInstanceId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppInstanceIdToken() {
            this.bitField0_ &= -2049;
            this.appInstanceIdToken_ = getDefaultInstance().getAppInstanceIdToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppVersion() {
            this.bitField0_ &= -513;
            this.appVersion_ = getDefaultInstance().getAppVersion();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppVersionCode() {
            this.bitField0_ &= -257;
            this.appVersionCode_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCertHash() {
            this.bitField0_ &= -5;
            this.certHash_ = getDefaultInstance().getCertHash();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigId() {
            this.bitField0_ &= -9;
            this.configId_ = getDefaultInstance().getConfigId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCustomVariable() {
            this.customVariable_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDigest() {
            this.bitField0_ &= -3;
            this.digest_ = getDefaultInstance().getDigest();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFetchedConfigAgeSeconds() {
            this.bitField0_ &= -16385;
            this.fetchedConfigAgeSeconds_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGamesProjectId() {
            this.bitField0_ &= -65;
            this.gamesProjectId_ = getDefaultInstance().getGamesProjectId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGmpProjectId() {
            this.bitField0_ &= -33;
            this.gmpProjectId_ = getDefaultInstance().getGmpProjectId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNamespaceDigest() {
            this.namespaceDigest_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -17;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRequestedCacheExpirationSeconds() {
            this.bitField0_ &= -8193;
            this.requestedCacheExpirationSeconds_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRequestedHiddenNamespace() {
            this.requestedHiddenNamespace_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSdkVersion() {
            this.bitField0_ &= -4097;
            this.sdkVersion_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersionCode() {
            this.bitField0_ &= -2;
            this.versionCode_ = 0;
        }

        private void ensureAnalyticsUserPropertyIsMutable() {
            if (this.analyticsUserProperty_.isModifiable()) {
                return;
            }
            this.analyticsUserProperty_ = GeneratedMessageLite.mutableCopy(this.analyticsUserProperty_);
        }

        private void ensureCustomVariableIsMutable() {
            if (this.customVariable_.isModifiable()) {
                return;
            }
            this.customVariable_ = GeneratedMessageLite.mutableCopy(this.customVariable_);
        }

        private void ensureNamespaceDigestIsMutable() {
            if (this.namespaceDigest_.isModifiable()) {
                return;
            }
            this.namespaceDigest_ = GeneratedMessageLite.mutableCopy(this.namespaceDigest_);
        }

        private void ensureRequestedHiddenNamespaceIsMutable() {
            if (this.requestedHiddenNamespace_.isModifiable()) {
                return;
            }
            this.requestedHiddenNamespace_ = GeneratedMessageLite.mutableCopy(this.requestedHiddenNamespace_);
        }

        public static PackageData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PackageData parseDelimitedFrom(InputStream inputStream) {
            return (PackageData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageData parseFrom(ByteBuffer byteBuffer) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PackageData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeAnalyticsUserProperty(int i9) {
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeCustomVariable(int i9) {
            ensureCustomVariableIsMutable();
            this.customVariable_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeNamespaceDigest(int i9) {
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setActiveConfigAgeSeconds(int i9) {
            this.bitField0_ |= 32768;
            this.activeConfigAgeSeconds_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAnalyticsUserProperty(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.set(i9, namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppCertHash(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 128;
            this.appCertHash_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppInstanceId(String str) {
            str.getClass();
            this.bitField0_ |= UserMetadata.MAX_ATTRIBUTE_SIZE;
            this.appInstanceId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppInstanceIdBytes(ByteString byteString) {
            this.appInstanceId_ = byteString.toStringUtf8();
            this.bitField0_ |= UserMetadata.MAX_ATTRIBUTE_SIZE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppInstanceIdToken(String str) {
            str.getClass();
            this.bitField0_ |= 2048;
            this.appInstanceIdToken_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppInstanceIdTokenBytes(ByteString byteString) {
            this.appInstanceIdToken_ = byteString.toStringUtf8();
            this.bitField0_ |= 2048;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppVersion(String str) {
            str.getClass();
            this.bitField0_ |= 512;
            this.appVersion_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppVersionBytes(ByteString byteString) {
            this.appVersion_ = byteString.toStringUtf8();
            this.bitField0_ |= 512;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppVersionCode(int i9) {
            this.bitField0_ |= 256;
            this.appVersionCode_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCertHash(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 4;
            this.certHash_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigId(String str) {
            str.getClass();
            this.bitField0_ |= 8;
            this.configId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigIdBytes(ByteString byteString) {
            this.configId_ = byteString.toStringUtf8();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCustomVariable(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureCustomVariableIsMutable();
            this.customVariable_.set(i9, namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDigest(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 2;
            this.digest_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFetchedConfigAgeSeconds(int i9) {
            this.bitField0_ |= 16384;
            this.fetchedConfigAgeSeconds_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGamesProjectId(String str) {
            str.getClass();
            this.bitField0_ |= 64;
            this.gamesProjectId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGamesProjectIdBytes(ByteString byteString) {
            this.gamesProjectId_ = byteString.toStringUtf8();
            this.bitField0_ |= 64;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGmpProjectId(String str) {
            str.getClass();
            this.bitField0_ |= 32;
            this.gmpProjectId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGmpProjectIdBytes(ByteString byteString) {
            this.gmpProjectId_ = byteString.toStringUtf8();
            this.bitField0_ |= 32;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNamespaceDigest(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.set(i9, namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageName(String str) {
            str.getClass();
            this.bitField0_ |= 16;
            this.packageName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageNameBytes(ByteString byteString) {
            this.packageName_ = byteString.toStringUtf8();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestedCacheExpirationSeconds(int i9) {
            this.bitField0_ |= UserMetadata.MAX_INTERNAL_KEY_SIZE;
            this.requestedCacheExpirationSeconds_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRequestedHiddenNamespace(int i9, String str) {
            str.getClass();
            ensureRequestedHiddenNamespaceIsMutable();
            this.requestedHiddenNamespace_.set(i9, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSdkVersion(int i9) {
            this.bitField0_ |= 4096;
            this.sdkVersion_ = i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersionCode(int i9) {
            this.bitField0_ |= 1;
            this.versionCode_ = i9;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new PackageData();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0014\u0000\u0001\u0001\u0014\u0014\u0000\u0004\u0000\u0001\b\u0004\u0002\u0004\u0000\u0003\n\u0001\u0004\n\u0002\u0005\b\u0003\u0006\b\u0005\u0007\b\u0006\b\u001b\t\u001b\n\n\u0007\u000b\u0004\b\f\b\n\r\b\t\u000e\b\u000b\u000f\u001a\u0010\u0004\f\u0011\u001b\u0012\u0004\r\u0013\u0004\u000e\u0014\u0004\u000f", new Object[]{"bitField0_", "packageName_", "versionCode_", "digest_", "certHash_", "configId_", "gmpProjectId_", "gamesProjectId_", "namespaceDigest_", NamedValue.class, "customVariable_", NamedValue.class, "appCertHash_", "appVersionCode_", "appInstanceId_", "appVersion_", "appInstanceIdToken_", "requestedHiddenNamespace_", "sdkVersion_", "analyticsUserProperty_", NamedValue.class, "requestedCacheExpirationSeconds_", "fetchedConfigAgeSeconds_", "activeConfigAgeSeconds_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PackageData> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (PackageData.class) {
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

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getActiveConfigAgeSeconds() {
            return this.activeConfigAgeSeconds_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public NamedValue getAnalyticsUserProperty(int i9) {
            return this.analyticsUserProperty_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getAnalyticsUserPropertyCount() {
            return this.analyticsUserProperty_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public List<NamedValue> getAnalyticsUserPropertyList() {
            return this.analyticsUserProperty_;
        }

        public NamedValueOrBuilder getAnalyticsUserPropertyOrBuilder(int i9) {
            return this.analyticsUserProperty_.get(i9);
        }

        public List<? extends NamedValueOrBuilder> getAnalyticsUserPropertyOrBuilderList() {
            return this.analyticsUserProperty_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getAppCertHash() {
            return this.appCertHash_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getAppInstanceId() {
            return this.appInstanceId_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getAppInstanceIdBytes() {
            return ByteString.copyFromUtf8(this.appInstanceId_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getAppInstanceIdToken() {
            return this.appInstanceIdToken_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getAppInstanceIdTokenBytes() {
            return ByteString.copyFromUtf8(this.appInstanceIdToken_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getAppVersion() {
            return this.appVersion_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getAppVersionBytes() {
            return ByteString.copyFromUtf8(this.appVersion_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getAppVersionCode() {
            return this.appVersionCode_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getCertHash() {
            return this.certHash_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getConfigId() {
            return this.configId_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getConfigIdBytes() {
            return ByteString.copyFromUtf8(this.configId_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public NamedValue getCustomVariable(int i9) {
            return this.customVariable_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getCustomVariableCount() {
            return this.customVariable_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public List<NamedValue> getCustomVariableList() {
            return this.customVariable_;
        }

        public NamedValueOrBuilder getCustomVariableOrBuilder(int i9) {
            return this.customVariable_.get(i9);
        }

        public List<? extends NamedValueOrBuilder> getCustomVariableOrBuilderList() {
            return this.customVariable_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getDigest() {
            return this.digest_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getFetchedConfigAgeSeconds() {
            return this.fetchedConfigAgeSeconds_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getGamesProjectId() {
            return this.gamesProjectId_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getGamesProjectIdBytes() {
            return ByteString.copyFromUtf8(this.gamesProjectId_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getGmpProjectId() {
            return this.gmpProjectId_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getGmpProjectIdBytes() {
            return ByteString.copyFromUtf8(this.gmpProjectId_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public NamedValue getNamespaceDigest(int i9) {
            return this.namespaceDigest_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getNamespaceDigestCount() {
            return this.namespaceDigest_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public List<NamedValue> getNamespaceDigestList() {
            return this.namespaceDigest_;
        }

        public NamedValueOrBuilder getNamespaceDigestOrBuilder(int i9) {
            return this.namespaceDigest_.get(i9);
        }

        public List<? extends NamedValueOrBuilder> getNamespaceDigestOrBuilderList() {
            return this.namespaceDigest_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getRequestedCacheExpirationSeconds() {
            return this.requestedCacheExpirationSeconds_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public String getRequestedHiddenNamespace(int i9) {
            return this.requestedHiddenNamespace_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public ByteString getRequestedHiddenNamespaceBytes(int i9) {
            return ByteString.copyFromUtf8(this.requestedHiddenNamespace_.get(i9));
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getRequestedHiddenNamespaceCount() {
            return this.requestedHiddenNamespace_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public List<String> getRequestedHiddenNamespaceList() {
            return this.requestedHiddenNamespace_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getSdkVersion() {
            return this.sdkVersion_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public int getVersionCode() {
            return this.versionCode_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasActiveConfigAgeSeconds() {
            return (this.bitField0_ & 32768) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasAppCertHash() {
            return (this.bitField0_ & 128) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasAppInstanceId() {
            return (this.bitField0_ & UserMetadata.MAX_ATTRIBUTE_SIZE) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasAppInstanceIdToken() {
            return (this.bitField0_ & 2048) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasAppVersion() {
            return (this.bitField0_ & 512) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasAppVersionCode() {
            return (this.bitField0_ & 256) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasCertHash() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasConfigId() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasDigest() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasFetchedConfigAgeSeconds() {
            return (this.bitField0_ & 16384) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasGamesProjectId() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasGmpProjectId() {
            return (this.bitField0_ & 32) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasRequestedCacheExpirationSeconds() {
            return (this.bitField0_ & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasSdkVersion() {
            return (this.bitField0_ & 4096) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageDataOrBuilder
        public boolean hasVersionCode() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(PackageData packageData) {
            return DEFAULT_INSTANCE.createBuilder(packageData);
        }

        public static PackageData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageData parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PackageData parseFrom(ByteString byteString) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAnalyticsUserProperty(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.add(i9, namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCustomVariable(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureCustomVariableIsMutable();
            this.customVariable_.add(i9, namedValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addNamespaceDigest(int i9, NamedValue namedValue) {
            namedValue.getClass();
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.add(i9, namedValue);
        }

        public static PackageData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PackageData parseFrom(byte[] bArr) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PackageData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PackageData parseFrom(InputStream inputStream) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageData parseFrom(CodedInputStream codedInputStream) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PackageData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface PackageDataOrBuilder extends MessageLiteOrBuilder {
        int getActiveConfigAgeSeconds();

        NamedValue getAnalyticsUserProperty(int i9);

        int getAnalyticsUserPropertyCount();

        List<NamedValue> getAnalyticsUserPropertyList();

        ByteString getAppCertHash();

        String getAppInstanceId();

        ByteString getAppInstanceIdBytes();

        String getAppInstanceIdToken();

        ByteString getAppInstanceIdTokenBytes();

        String getAppVersion();

        ByteString getAppVersionBytes();

        int getAppVersionCode();

        ByteString getCertHash();

        String getConfigId();

        ByteString getConfigIdBytes();

        NamedValue getCustomVariable(int i9);

        int getCustomVariableCount();

        List<NamedValue> getCustomVariableList();

        ByteString getDigest();

        int getFetchedConfigAgeSeconds();

        String getGamesProjectId();

        ByteString getGamesProjectIdBytes();

        String getGmpProjectId();

        ByteString getGmpProjectIdBytes();

        NamedValue getNamespaceDigest(int i9);

        int getNamespaceDigestCount();

        List<NamedValue> getNamespaceDigestList();

        String getPackageName();

        ByteString getPackageNameBytes();

        int getRequestedCacheExpirationSeconds();

        String getRequestedHiddenNamespace(int i9);

        ByteString getRequestedHiddenNamespaceBytes(int i9);

        int getRequestedHiddenNamespaceCount();

        List<String> getRequestedHiddenNamespaceList();

        int getSdkVersion();

        int getVersionCode();

        boolean hasActiveConfigAgeSeconds();

        boolean hasAppCertHash();

        boolean hasAppInstanceId();

        boolean hasAppInstanceIdToken();

        boolean hasAppVersion();

        boolean hasAppVersionCode();

        boolean hasCertHash();

        boolean hasConfigId();

        boolean hasDigest();

        boolean hasFetchedConfigAgeSeconds();

        boolean hasGamesProjectId();

        boolean hasGmpProjectId();

        boolean hasPackageName();

        boolean hasRequestedCacheExpirationSeconds();

        boolean hasSdkVersion();

        boolean hasVersionCode();
    }

    public static final class PackageTable extends GeneratedMessageLite<PackageTable, Builder> implements PackageTableOrBuilder {
        public static final int CONFIG_ID_FIELD_NUMBER = 3;
        private static final PackageTable DEFAULT_INSTANCE;
        public static final int ENTRY_FIELD_NUMBER = 2;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PackageTable> PARSER;
        private int bitField0_;
        private String packageName_ = "";
        private Internal.ProtobufList<KeyValue> entry_ = GeneratedMessageLite.emptyProtobufList();
        private String configId_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<PackageTable, Builder> implements PackageTableOrBuilder {
            public /* synthetic */ Builder(C34601 c34601) {
                this();
            }

            public Builder addAllEntry(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((PackageTable) this.instance).addAllEntry(iterable);
                return this;
            }

            public Builder addEntry(KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(keyValue);
                return this;
            }

            public Builder clearConfigId() {
                copyOnWrite();
                ((PackageTable) this.instance).clearConfigId();
                return this;
            }

            public Builder clearEntry() {
                copyOnWrite();
                ((PackageTable) this.instance).clearEntry();
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageTable) this.instance).clearPackageName();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public String getConfigId() {
                return ((PackageTable) this.instance).getConfigId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public ByteString getConfigIdBytes() {
                return ((PackageTable) this.instance).getConfigIdBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public KeyValue getEntry(int i9) {
                return ((PackageTable) this.instance).getEntry(i9);
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public int getEntryCount() {
                return ((PackageTable) this.instance).getEntryCount();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public List<KeyValue> getEntryList() {
                return Collections.unmodifiableList(((PackageTable) this.instance).getEntryList());
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public String getPackageName() {
                return ((PackageTable) this.instance).getPackageName();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public ByteString getPackageNameBytes() {
                return ((PackageTable) this.instance).getPackageNameBytes();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public boolean hasConfigId() {
                return ((PackageTable) this.instance).hasConfigId();
            }

            @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
            public boolean hasPackageName() {
                return ((PackageTable) this.instance).hasPackageName();
            }

            public Builder removeEntry(int i9) {
                copyOnWrite();
                ((PackageTable) this.instance).removeEntry(i9);
                return this;
            }

            public Builder setConfigId(String str) {
                copyOnWrite();
                ((PackageTable) this.instance).setConfigId(str);
                return this;
            }

            public Builder setConfigIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageTable) this.instance).setConfigIdBytes(byteString);
                return this;
            }

            public Builder setEntry(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).setEntry(i9, keyValue);
                return this;
            }

            public Builder setPackageName(String str) {
                copyOnWrite();
                ((PackageTable) this.instance).setPackageName(str);
                return this;
            }

            public Builder setPackageNameBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageTable) this.instance).setPackageNameBytes(byteString);
                return this;
            }

            private Builder() {
                super(PackageTable.DEFAULT_INSTANCE);
            }

            public Builder addEntry(int i9, KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(i9, keyValue);
                return this;
            }

            public Builder setEntry(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).setEntry(i9, builder.build());
                return this;
            }

            public Builder addEntry(KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(builder.build());
                return this;
            }

            public Builder addEntry(int i9, KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(i9, builder.build());
                return this;
            }
        }

        static {
            PackageTable packageTable = new PackageTable();
            DEFAULT_INSTANCE = packageTable;
            GeneratedMessageLite.registerDefaultInstance(PackageTable.class, packageTable);
        }

        private PackageTable() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllEntry(Iterable<? extends KeyValue> iterable) {
            ensureEntryIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.entry_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEntry(KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.add(keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConfigId() {
            this.bitField0_ &= -3;
            this.configId_ = getDefaultInstance().getConfigId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEntry() {
            this.entry_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -2;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        private void ensureEntryIsMutable() {
            if (this.entry_.isModifiable()) {
                return;
            }
            this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
        }

        public static PackageTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static PackageTable parseDelimitedFrom(InputStream inputStream) {
            return (PackageTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageTable parseFrom(ByteBuffer byteBuffer) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<PackageTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeEntry(int i9) {
            ensureEntryIsMutable();
            this.entry_.remove(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigId(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.configId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setConfigIdBytes(ByteString byteString) {
            this.configId_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEntry(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.set(i9, keyValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageName(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.packageName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPackageNameBytes(ByteString byteString) {
            this.packageName_ = byteString.toStringUtf8();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34601 c34601 = null;
            switch (C34601.f15326xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new PackageTable();
                case 2:
                    return new Builder(c34601);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b\u0003\b\u0001", new Object[]{"bitField0_", "packageName_", "entry_", KeyValue.class, "configId_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<PackageTable> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (PackageTable.class) {
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

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public String getConfigId() {
            return this.configId_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public ByteString getConfigIdBytes() {
            return ByteString.copyFromUtf8(this.configId_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public KeyValue getEntry(int i9) {
            return this.entry_.get(i9);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public int getEntryCount() {
            return this.entry_.size();
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public List<KeyValue> getEntryList() {
            return this.entry_;
        }

        public KeyValueOrBuilder getEntryOrBuilder(int i9) {
            return this.entry_.get(i9);
        }

        public List<? extends KeyValueOrBuilder> getEntryOrBuilderList() {
            return this.entry_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public String getPackageName() {
            return this.packageName_;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public boolean hasConfigId() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.gms.config.proto.Config.PackageTableOrBuilder
        public boolean hasPackageName() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(PackageTable packageTable) {
            return DEFAULT_INSTANCE.createBuilder(packageTable);
        }

        public static PackageTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageTable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static PackageTable parseFrom(ByteString byteString) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addEntry(int i9, KeyValue keyValue) {
            keyValue.getClass();
            ensureEntryIsMutable();
            this.entry_.add(i9, keyValue);
        }

        public static PackageTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PackageTable parseFrom(byte[] bArr) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PackageTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PackageTable parseFrom(InputStream inputStream) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageTable parseFrom(CodedInputStream codedInputStream) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PackageTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface PackageTableOrBuilder extends MessageLiteOrBuilder {
        String getConfigId();

        ByteString getConfigIdBytes();

        KeyValue getEntry(int i9);

        int getEntryCount();

        List<KeyValue> getEntryList();

        String getPackageName();

        ByteString getPackageNameBytes();

        boolean hasConfigId();

        boolean hasPackageName();
    }

    private Config() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
