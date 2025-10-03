package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class Duration extends GeneratedMessageLite<Duration, Builder> implements DurationOrBuilder {
    private static final Duration DEFAULT_INSTANCE;
    public static final int NANOS_FIELD_NUMBER = 2;
    private static volatile Parser<Duration> PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 1;
    private int nanos_;
    private long seconds_;

    /* renamed from: com.google.protobuf.Duration$1 */
    public static /* synthetic */ class C43931 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15625xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15625xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15625xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Duration, Builder> implements DurationOrBuilder {
        public /* synthetic */ Builder(C43931 c43931) {
            this();
        }

        public Builder clearNanos() {
            copyOnWrite();
            ((Duration) this.instance).clearNanos();
            return this;
        }

        public Builder clearSeconds() {
            copyOnWrite();
            ((Duration) this.instance).clearSeconds();
            return this;
        }

        @Override // com.google.protobuf.DurationOrBuilder
        public int getNanos() {
            return ((Duration) this.instance).getNanos();
        }

        @Override // com.google.protobuf.DurationOrBuilder
        public long getSeconds() {
            return ((Duration) this.instance).getSeconds();
        }

        public Builder setNanos(int i9) {
            copyOnWrite();
            ((Duration) this.instance).setNanos(i9);
            return this;
        }

        public Builder setSeconds(long j9) {
            copyOnWrite();
            ((Duration) this.instance).setSeconds(j9);
            return this;
        }

        private Builder() {
            super(Duration.DEFAULT_INSTANCE);
        }
    }

    static {
        Duration duration = new Duration();
        DEFAULT_INSTANCE = duration;
        GeneratedMessageLite.registerDefaultInstance(Duration.class, duration);
    }

    private Duration() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNanos() {
        this.nanos_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSeconds() {
        this.seconds_ = 0L;
    }

    public static Duration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Duration parseDelimitedFrom(InputStream inputStream) {
        return (Duration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Duration parseFrom(ByteBuffer byteBuffer) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Duration> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNanos(int i9) {
        this.nanos_ = i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeconds(long j9) {
        this.seconds_ = j9;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C43931 c43931 = null;
        switch (C43931.f15625xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Duration();
            case 2:
                return new Builder(c43931);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"seconds_", "nanos_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Duration> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Duration.class) {
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

    @Override // com.google.protobuf.DurationOrBuilder
    public int getNanos() {
        return this.nanos_;
    }

    @Override // com.google.protobuf.DurationOrBuilder
    public long getSeconds() {
        return this.seconds_;
    }

    public static Builder newBuilder(Duration duration) {
        return DEFAULT_INSTANCE.createBuilder(duration);
    }

    public static Duration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Duration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Duration parseFrom(ByteString byteString) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Duration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Duration parseFrom(byte[] bArr) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Duration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Duration parseFrom(InputStream inputStream) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Duration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Duration parseFrom(CodedInputStream codedInputStream) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Duration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Duration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
