package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Int32Value extends GeneratedMessageLite<Int32Value, Builder> implements Int32ValueOrBuilder {
    private static final Int32Value DEFAULT_INSTANCE;
    private static volatile Parser<Int32Value> PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    private int value_;

    /* renamed from: com.google.protobuf.Int32Value$1 */
    public static /* synthetic */ class C44071 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15633xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15633xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15633xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Int32Value, Builder> implements Int32ValueOrBuilder {
        public /* synthetic */ Builder(C44071 c44071) {
            this();
        }

        public Builder clearValue() {
            copyOnWrite();
            ((Int32Value) this.instance).clearValue();
            return this;
        }

        @Override // com.google.protobuf.Int32ValueOrBuilder
        public int getValue() {
            return ((Int32Value) this.instance).getValue();
        }

        public Builder setValue(int i9) {
            copyOnWrite();
            ((Int32Value) this.instance).setValue(i9);
            return this;
        }

        private Builder() {
            super(Int32Value.DEFAULT_INSTANCE);
        }
    }

    static {
        Int32Value int32Value = new Int32Value();
        DEFAULT_INSTANCE = int32Value;
        GeneratedMessageLite.registerDefaultInstance(Int32Value.class, int32Value);
    }

    private Int32Value() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValue() {
        this.value_ = 0;
    }

    public static Int32Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    /* renamed from: of */
    public static Int32Value m17841of(int i9) {
        return newBuilder().setValue(i9).build();
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream) {
        return (Int32Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Int32Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValue(int i9) {
        this.value_ = i9;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C44071 c44071 = null;
        switch (C44071.f15633xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Int32Value();
            case 2:
                return new Builder(c44071);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"value_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Int32Value> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Int32Value.class) {
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

    @Override // com.google.protobuf.Int32ValueOrBuilder
    public int getValue() {
        return this.value_;
    }

    public static Builder newBuilder(Int32Value int32Value) {
        return DEFAULT_INSTANCE.createBuilder(int32Value);
    }

    public static Int32Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Int32Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Int32Value parseFrom(ByteString byteString) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Int32Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Int32Value parseFrom(byte[] bArr) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Int32Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Int32Value parseFrom(InputStream inputStream) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Int32Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Int32Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Int32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
