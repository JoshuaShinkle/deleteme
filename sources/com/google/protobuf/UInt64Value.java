package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class UInt64Value extends GeneratedMessageLite<UInt64Value, Builder> implements UInt64ValueOrBuilder {
    private static final UInt64Value DEFAULT_INSTANCE;
    private static volatile Parser<UInt64Value> PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private long value_;

    /* renamed from: com.google.protobuf.UInt64Value$1 */
    public static /* synthetic */ class C44351 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15646xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15646xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15646xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UInt64Value, Builder> implements UInt64ValueOrBuilder {
        public /* synthetic */ Builder(C44351 c44351) {
            this();
        }

        public Builder clearValue() {
            copyOnWrite();
            ((UInt64Value) this.instance).clearValue();
            return this;
        }

        @Override // com.google.protobuf.UInt64ValueOrBuilder
        public long getValue() {
            return ((UInt64Value) this.instance).getValue();
        }

        public Builder setValue(long j9) {
            copyOnWrite();
            ((UInt64Value) this.instance).setValue(j9);
            return this;
        }

        private Builder() {
            super(UInt64Value.DEFAULT_INSTANCE);
        }
    }

    static {
        UInt64Value uInt64Value = new UInt64Value();
        DEFAULT_INSTANCE = uInt64Value;
        GeneratedMessageLite.registerDefaultInstance(UInt64Value.class, uInt64Value);
    }

    private UInt64Value() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValue() {
        this.value_ = 0L;
    }

    public static UInt64Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    /* renamed from: of */
    public static UInt64Value m17845of(long j9) {
        return newBuilder().setValue(j9).build();
    }

    public static UInt64Value parseDelimitedFrom(InputStream inputStream) {
        return (UInt64Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UInt64Value parseFrom(ByteBuffer byteBuffer) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<UInt64Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValue(long j9) {
        this.value_ = j9;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C44351 c44351 = null;
        switch (C44351.f15646xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new UInt64Value();
            case 2:
                return new Builder(c44351);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0003", new Object[]{"value_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<UInt64Value> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (UInt64Value.class) {
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

    @Override // com.google.protobuf.UInt64ValueOrBuilder
    public long getValue() {
        return this.value_;
    }

    public static Builder newBuilder(UInt64Value uInt64Value) {
        return DEFAULT_INSTANCE.createBuilder(uInt64Value);
    }

    public static UInt64Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(ByteString byteString) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UInt64Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(byte[] bArr) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UInt64Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(InputStream inputStream) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UInt64Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UInt64Value parseFrom(CodedInputStream codedInputStream) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UInt64Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt64Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
