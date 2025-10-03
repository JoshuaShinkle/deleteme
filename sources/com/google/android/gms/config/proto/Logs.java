package com.google.android.gms.config.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Logs {

    /* renamed from: com.google.android.gms.config.proto.Logs$1 */
    public static /* synthetic */ class C34631 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15327xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15327xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15327xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class AndroidConfigFetchProto extends GeneratedMessageLite<AndroidConfigFetchProto, Builder> implements AndroidConfigFetchProtoOrBuilder {
        private static final AndroidConfigFetchProto DEFAULT_INSTANCE;
        private static volatile Parser<AndroidConfigFetchProto> PARSER = null;
        public static final int REASON_FIELD_NUMBER = 1;
        private int bitField0_;
        private ConfigFetchReason reason_;

        public static final class Builder extends GeneratedMessageLite.Builder<AndroidConfigFetchProto, Builder> implements AndroidConfigFetchProtoOrBuilder {
            public /* synthetic */ Builder(C34631 c34631) {
                this();
            }

            public Builder clearReason() {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).clearReason();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Logs.AndroidConfigFetchProtoOrBuilder
            public ConfigFetchReason getReason() {
                return ((AndroidConfigFetchProto) this.instance).getReason();
            }

            @Override // com.google.android.gms.config.proto.Logs.AndroidConfigFetchProtoOrBuilder
            public boolean hasReason() {
                return ((AndroidConfigFetchProto) this.instance).hasReason();
            }

            public Builder mergeReason(ConfigFetchReason configFetchReason) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).mergeReason(configFetchReason);
                return this;
            }

            public Builder setReason(ConfigFetchReason configFetchReason) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).setReason(configFetchReason);
                return this;
            }

            private Builder() {
                super(AndroidConfigFetchProto.DEFAULT_INSTANCE);
            }

            public Builder setReason(ConfigFetchReason.Builder builder) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).setReason(builder.build());
                return this;
            }
        }

        static {
            AndroidConfigFetchProto androidConfigFetchProto = new AndroidConfigFetchProto();
            DEFAULT_INSTANCE = androidConfigFetchProto;
            GeneratedMessageLite.registerDefaultInstance(AndroidConfigFetchProto.class, androidConfigFetchProto);
        }

        private AndroidConfigFetchProto() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReason() {
            this.reason_ = null;
            this.bitField0_ &= -2;
        }

        public static AndroidConfigFetchProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeReason(ConfigFetchReason configFetchReason) {
            configFetchReason.getClass();
            ConfigFetchReason configFetchReason2 = this.reason_;
            if (configFetchReason2 == null || configFetchReason2 == ConfigFetchReason.getDefaultInstance()) {
                this.reason_ = configFetchReason;
            } else {
                this.reason_ = ConfigFetchReason.newBuilder(this.reason_).mergeFrom((ConfigFetchReason.Builder) configFetchReason).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static AndroidConfigFetchProto parseDelimitedFrom(InputStream inputStream) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AndroidConfigFetchProto parseFrom(ByteBuffer byteBuffer) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<AndroidConfigFetchProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReason(ConfigFetchReason configFetchReason) {
            configFetchReason.getClass();
            this.reason_ = configFetchReason;
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34631 c34631 = null;
            switch (C34631.f15327xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new AndroidConfigFetchProto();
                case 2:
                    return new Builder(c34631);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t\u0000", new Object[]{"bitField0_", "reason_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<AndroidConfigFetchProto> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (AndroidConfigFetchProto.class) {
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

        @Override // com.google.android.gms.config.proto.Logs.AndroidConfigFetchProtoOrBuilder
        public ConfigFetchReason getReason() {
            ConfigFetchReason configFetchReason = this.reason_;
            return configFetchReason == null ? ConfigFetchReason.getDefaultInstance() : configFetchReason;
        }

        @Override // com.google.android.gms.config.proto.Logs.AndroidConfigFetchProtoOrBuilder
        public boolean hasReason() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(AndroidConfigFetchProto androidConfigFetchProto) {
            return DEFAULT_INSTANCE.createBuilder(androidConfigFetchProto);
        }

        public static AndroidConfigFetchProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(ByteString byteString) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AndroidConfigFetchProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(byte[] bArr) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AndroidConfigFetchProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(InputStream inputStream) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AndroidConfigFetchProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(CodedInputStream codedInputStream) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AndroidConfigFetchProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface AndroidConfigFetchProtoOrBuilder extends MessageLiteOrBuilder {
        ConfigFetchReason getReason();

        boolean hasReason();
    }

    public static final class ConfigFetchReason extends GeneratedMessageLite<ConfigFetchReason, Builder> implements ConfigFetchReasonOrBuilder {
        private static final ConfigFetchReason DEFAULT_INSTANCE;
        private static volatile Parser<ConfigFetchReason> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private int type_;

        public enum AndroidConfigFetchType implements Internal.EnumLite {
            UNKNOWN(0),
            SCHEDULED(1),
            BOOT_COMPLETED(2),
            PACKAGE_ADDED(3),
            PACKAGE_REMOVED(4),
            GMS_CORE_UPDATED(5),
            SECRET_CODE(6);

            public static final int BOOT_COMPLETED_VALUE = 2;
            public static final int GMS_CORE_UPDATED_VALUE = 5;
            public static final int PACKAGE_ADDED_VALUE = 3;
            public static final int PACKAGE_REMOVED_VALUE = 4;
            public static final int SCHEDULED_VALUE = 1;
            public static final int SECRET_CODE_VALUE = 6;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<AndroidConfigFetchType> internalValueMap = new Internal.EnumLiteMap<AndroidConfigFetchType>() { // from class: com.google.android.gms.config.proto.Logs.ConfigFetchReason.AndroidConfigFetchType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public AndroidConfigFetchType findValueByNumber(int i9) {
                    return AndroidConfigFetchType.forNumber(i9);
                }
            };
            private final int value;

            public static final class AndroidConfigFetchTypeVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new AndroidConfigFetchTypeVerifier();

                private AndroidConfigFetchTypeVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i9) {
                    return AndroidConfigFetchType.forNumber(i9) != null;
                }
            }

            AndroidConfigFetchType(int i9) {
                this.value = i9;
            }

            public static AndroidConfigFetchType forNumber(int i9) {
                switch (i9) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return SCHEDULED;
                    case 2:
                        return BOOT_COMPLETED;
                    case 3:
                        return PACKAGE_ADDED;
                    case 4:
                        return PACKAGE_REMOVED;
                    case 5:
                        return GMS_CORE_UPDATED;
                    case 6:
                        return SECRET_CODE;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<AndroidConfigFetchType> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return AndroidConfigFetchTypeVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static AndroidConfigFetchType valueOf(int i9) {
                return forNumber(i9);
            }
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchReason, Builder> implements ConfigFetchReasonOrBuilder {
            public /* synthetic */ Builder(C34631 c34631) {
                this();
            }

            public Builder clearType() {
                copyOnWrite();
                ((ConfigFetchReason) this.instance).clearType();
                return this;
            }

            @Override // com.google.android.gms.config.proto.Logs.ConfigFetchReasonOrBuilder
            public AndroidConfigFetchType getType() {
                return ((ConfigFetchReason) this.instance).getType();
            }

            @Override // com.google.android.gms.config.proto.Logs.ConfigFetchReasonOrBuilder
            public boolean hasType() {
                return ((ConfigFetchReason) this.instance).hasType();
            }

            public Builder setType(AndroidConfigFetchType androidConfigFetchType) {
                copyOnWrite();
                ((ConfigFetchReason) this.instance).setType(androidConfigFetchType);
                return this;
            }

            private Builder() {
                super(ConfigFetchReason.DEFAULT_INSTANCE);
            }
        }

        static {
            ConfigFetchReason configFetchReason = new ConfigFetchReason();
            DEFAULT_INSTANCE = configFetchReason;
            GeneratedMessageLite.registerDefaultInstance(ConfigFetchReason.class, configFetchReason);
        }

        private ConfigFetchReason() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.bitField0_ &= -2;
            this.type_ = 0;
        }

        public static ConfigFetchReason getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ConfigFetchReason parseDelimitedFrom(InputStream inputStream) {
            return (ConfigFetchReason) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchReason parseFrom(ByteBuffer byteBuffer) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ConfigFetchReason> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(AndroidConfigFetchType androidConfigFetchType) {
            this.type_ = androidConfigFetchType.getNumber();
            this.bitField0_ |= 1;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            C34631 c34631 = null;
            switch (C34631.f15327xa1df5c61[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchReason();
                case 2:
                    return new Builder(c34631);
                case 3:
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"bitField0_", "type_", AndroidConfigFetchType.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ConfigFetchReason> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ConfigFetchReason.class) {
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

        @Override // com.google.android.gms.config.proto.Logs.ConfigFetchReasonOrBuilder
        public AndroidConfigFetchType getType() {
            AndroidConfigFetchType androidConfigFetchTypeForNumber = AndroidConfigFetchType.forNumber(this.type_);
            return androidConfigFetchTypeForNumber == null ? AndroidConfigFetchType.UNKNOWN : androidConfigFetchTypeForNumber;
        }

        @Override // com.google.android.gms.config.proto.Logs.ConfigFetchReasonOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 1) != 0;
        }

        public static Builder newBuilder(ConfigFetchReason configFetchReason) {
            return DEFAULT_INSTANCE.createBuilder(configFetchReason);
        }

        public static ConfigFetchReason parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(ByteString byteString) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConfigFetchReason parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(byte[] bArr) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchReason parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(InputStream inputStream) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchReason parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(CodedInputStream codedInputStream) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchReason parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ConfigFetchReasonOrBuilder extends MessageLiteOrBuilder {
        ConfigFetchReason.AndroidConfigFetchType getType();

        boolean hasType();
    }

    private Logs() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
