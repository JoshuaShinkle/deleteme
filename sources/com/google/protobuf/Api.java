package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Method;
import com.google.protobuf.Mixin;
import com.google.protobuf.Option;
import com.google.protobuf.SourceContext;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class Api extends GeneratedMessageLite<Api, Builder> implements ApiOrBuilder {
    private static final Api DEFAULT_INSTANCE;
    public static final int METHODS_FIELD_NUMBER = 2;
    public static final int MIXINS_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static volatile Parser<Api> PARSER = null;
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 7;
    public static final int VERSION_FIELD_NUMBER = 4;
    private SourceContext sourceContext_;
    private int syntax_;
    private String name_ = "";
    private Internal.ProtobufList<Method> methods_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<Option> options_ = GeneratedMessageLite.emptyProtobufList();
    private String version_ = "";
    private Internal.ProtobufList<Mixin> mixins_ = GeneratedMessageLite.emptyProtobufList();

    /* renamed from: com.google.protobuf.Api$1 */
    public static /* synthetic */ class C43791 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f15621xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f15621xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15621xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Api, Builder> implements ApiOrBuilder {
        public /* synthetic */ Builder(C43791 c43791) {
            this();
        }

        public Builder addAllMethods(Iterable<? extends Method> iterable) {
            copyOnWrite();
            ((Api) this.instance).addAllMethods(iterable);
            return this;
        }

        public Builder addAllMixins(Iterable<? extends Mixin> iterable) {
            copyOnWrite();
            ((Api) this.instance).addAllMixins(iterable);
            return this;
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            copyOnWrite();
            ((Api) this.instance).addAllOptions(iterable);
            return this;
        }

        public Builder addMethods(Method method) {
            copyOnWrite();
            ((Api) this.instance).addMethods(method);
            return this;
        }

        public Builder addMixins(Mixin mixin) {
            copyOnWrite();
            ((Api) this.instance).addMixins(mixin);
            return this;
        }

        public Builder addOptions(Option option) {
            copyOnWrite();
            ((Api) this.instance).addOptions(option);
            return this;
        }

        public Builder clearMethods() {
            copyOnWrite();
            ((Api) this.instance).clearMethods();
            return this;
        }

        public Builder clearMixins() {
            copyOnWrite();
            ((Api) this.instance).clearMixins();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Api) this.instance).clearName();
            return this;
        }

        public Builder clearOptions() {
            copyOnWrite();
            ((Api) this.instance).clearOptions();
            return this;
        }

        public Builder clearSourceContext() {
            copyOnWrite();
            ((Api) this.instance).clearSourceContext();
            return this;
        }

        public Builder clearSyntax() {
            copyOnWrite();
            ((Api) this.instance).clearSyntax();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((Api) this.instance).clearVersion();
            return this;
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public Method getMethods(int i9) {
            return ((Api) this.instance).getMethods(i9);
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public int getMethodsCount() {
            return ((Api) this.instance).getMethodsCount();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public List<Method> getMethodsList() {
            return Collections.unmodifiableList(((Api) this.instance).getMethodsList());
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public Mixin getMixins(int i9) {
            return ((Api) this.instance).getMixins(i9);
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public int getMixinsCount() {
            return ((Api) this.instance).getMixinsCount();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public List<Mixin> getMixinsList() {
            return Collections.unmodifiableList(((Api) this.instance).getMixinsList());
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public String getName() {
            return ((Api) this.instance).getName();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public ByteString getNameBytes() {
            return ((Api) this.instance).getNameBytes();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public Option getOptions(int i9) {
            return ((Api) this.instance).getOptions(i9);
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public int getOptionsCount() {
            return ((Api) this.instance).getOptionsCount();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public List<Option> getOptionsList() {
            return Collections.unmodifiableList(((Api) this.instance).getOptionsList());
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public SourceContext getSourceContext() {
            return ((Api) this.instance).getSourceContext();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public Syntax getSyntax() {
            return ((Api) this.instance).getSyntax();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public int getSyntaxValue() {
            return ((Api) this.instance).getSyntaxValue();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public String getVersion() {
            return ((Api) this.instance).getVersion();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public ByteString getVersionBytes() {
            return ((Api) this.instance).getVersionBytes();
        }

        @Override // com.google.protobuf.ApiOrBuilder
        public boolean hasSourceContext() {
            return ((Api) this.instance).hasSourceContext();
        }

        public Builder mergeSourceContext(SourceContext sourceContext) {
            copyOnWrite();
            ((Api) this.instance).mergeSourceContext(sourceContext);
            return this;
        }

        public Builder removeMethods(int i9) {
            copyOnWrite();
            ((Api) this.instance).removeMethods(i9);
            return this;
        }

        public Builder removeMixins(int i9) {
            copyOnWrite();
            ((Api) this.instance).removeMixins(i9);
            return this;
        }

        public Builder removeOptions(int i9) {
            copyOnWrite();
            ((Api) this.instance).removeOptions(i9);
            return this;
        }

        public Builder setMethods(int i9, Method method) {
            copyOnWrite();
            ((Api) this.instance).setMethods(i9, method);
            return this;
        }

        public Builder setMixins(int i9, Mixin mixin) {
            copyOnWrite();
            ((Api) this.instance).setMixins(i9, mixin);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Api) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Api) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setOptions(int i9, Option option) {
            copyOnWrite();
            ((Api) this.instance).setOptions(i9, option);
            return this;
        }

        public Builder setSourceContext(SourceContext sourceContext) {
            copyOnWrite();
            ((Api) this.instance).setSourceContext(sourceContext);
            return this;
        }

        public Builder setSyntax(Syntax syntax) {
            copyOnWrite();
            ((Api) this.instance).setSyntax(syntax);
            return this;
        }

        public Builder setSyntaxValue(int i9) {
            copyOnWrite();
            ((Api) this.instance).setSyntaxValue(i9);
            return this;
        }

        public Builder setVersion(String str) {
            copyOnWrite();
            ((Api) this.instance).setVersion(str);
            return this;
        }

        public Builder setVersionBytes(ByteString byteString) {
            copyOnWrite();
            ((Api) this.instance).setVersionBytes(byteString);
            return this;
        }

        private Builder() {
            super(Api.DEFAULT_INSTANCE);
        }

        public Builder addMethods(int i9, Method method) {
            copyOnWrite();
            ((Api) this.instance).addMethods(i9, method);
            return this;
        }

        public Builder addMixins(int i9, Mixin mixin) {
            copyOnWrite();
            ((Api) this.instance).addMixins(i9, mixin);
            return this;
        }

        public Builder addOptions(int i9, Option option) {
            copyOnWrite();
            ((Api) this.instance).addOptions(i9, option);
            return this;
        }

        public Builder setMethods(int i9, Method.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).setMethods(i9, builder.build());
            return this;
        }

        public Builder setMixins(int i9, Mixin.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).setMixins(i9, builder.build());
            return this;
        }

        public Builder setOptions(int i9, Option.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).setOptions(i9, builder.build());
            return this;
        }

        public Builder setSourceContext(SourceContext.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).setSourceContext(builder.build());
            return this;
        }

        public Builder addMethods(Method.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addMethods(builder.build());
            return this;
        }

        public Builder addMixins(Mixin.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addMixins(builder.build());
            return this;
        }

        public Builder addOptions(Option.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addOptions(builder.build());
            return this;
        }

        public Builder addMethods(int i9, Method.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addMethods(i9, builder.build());
            return this;
        }

        public Builder addMixins(int i9, Mixin.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addMixins(i9, builder.build());
            return this;
        }

        public Builder addOptions(int i9, Option.Builder builder) {
            copyOnWrite();
            ((Api) this.instance).addOptions(i9, builder.build());
            return this;
        }
    }

    static {
        Api api = new Api();
        DEFAULT_INSTANCE = api;
        GeneratedMessageLite.registerDefaultInstance(Api.class, api);
    }

    private Api() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllMethods(Iterable<? extends Method> iterable) {
        ensureMethodsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.methods_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllMixins(Iterable<? extends Mixin> iterable) {
        ensureMixinsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.mixins_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllOptions(Iterable<? extends Option> iterable) {
        ensureOptionsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.options_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMethods(Method method) {
        method.getClass();
        ensureMethodsIsMutable();
        this.methods_.add(method);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMixins(Mixin mixin) {
        mixin.getClass();
        ensureMixinsIsMutable();
        this.mixins_.add(mixin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOptions(Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.add(option);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMethods() {
        this.methods_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMixins() {
        this.mixins_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOptions() {
        this.options_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSourceContext() {
        this.sourceContext_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSyntax() {
        this.syntax_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = getDefaultInstance().getVersion();
    }

    private void ensureMethodsIsMutable() {
        if (this.methods_.isModifiable()) {
            return;
        }
        this.methods_ = GeneratedMessageLite.mutableCopy(this.methods_);
    }

    private void ensureMixinsIsMutable() {
        if (this.mixins_.isModifiable()) {
            return;
        }
        this.mixins_ = GeneratedMessageLite.mutableCopy(this.mixins_);
    }

    private void ensureOptionsIsMutable() {
        if (this.options_.isModifiable()) {
            return;
        }
        this.options_ = GeneratedMessageLite.mutableCopy(this.options_);
    }

    public static Api getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSourceContext(SourceContext sourceContext) {
        sourceContext.getClass();
        SourceContext sourceContext2 = this.sourceContext_;
        if (sourceContext2 == null || sourceContext2 == SourceContext.getDefaultInstance()) {
            this.sourceContext_ = sourceContext;
        } else {
            this.sourceContext_ = SourceContext.newBuilder(this.sourceContext_).mergeFrom((SourceContext.Builder) sourceContext).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Api parseDelimitedFrom(InputStream inputStream) {
        return (Api) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Api parseFrom(ByteBuffer byteBuffer) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Api> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMethods(int i9) {
        ensureMethodsIsMutable();
        this.methods_.remove(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMixins(int i9) {
        ensureMixinsIsMutable();
        this.mixins_.remove(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOptions(int i9) {
        ensureOptionsIsMutable();
        this.options_.remove(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMethods(int i9, Method method) {
        method.getClass();
        ensureMethodsIsMutable();
        this.methods_.set(i9, method);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMixins(int i9, Mixin mixin) {
        mixin.getClass();
        ensureMixinsIsMutable();
        this.mixins_.set(i9, mixin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOptions(int i9, Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.set(i9, option);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceContext(SourceContext sourceContext) {
        sourceContext.getClass();
        this.sourceContext_ = sourceContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyntax(Syntax syntax) {
        this.syntax_ = syntax.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyntaxValue(int i9) {
        this.syntax_ = i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersion(String str) {
        str.getClass();
        this.version_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.version_ = byteString.toStringUtf8();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        C43791 c43791 = null;
        switch (C43791.f15621xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new Api();
            case 2:
                return new Builder(c43791);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0003\u0000\u0001Ȉ\u0002\u001b\u0003\u001b\u0004Ȉ\u0005\t\u0006\u001b\u0007\f", new Object[]{"name_", "methods_", Method.class, "options_", Option.class, "version_", "sourceContext_", "mixins_", Mixin.class, "syntax_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Api> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Api.class) {
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

    @Override // com.google.protobuf.ApiOrBuilder
    public Method getMethods(int i9) {
        return this.methods_.get(i9);
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public int getMethodsCount() {
        return this.methods_.size();
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public List<Method> getMethodsList() {
        return this.methods_;
    }

    public MethodOrBuilder getMethodsOrBuilder(int i9) {
        return this.methods_.get(i9);
    }

    public List<? extends MethodOrBuilder> getMethodsOrBuilderList() {
        return this.methods_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public Mixin getMixins(int i9) {
        return this.mixins_.get(i9);
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public int getMixinsCount() {
        return this.mixins_.size();
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public List<Mixin> getMixinsList() {
        return this.mixins_;
    }

    public MixinOrBuilder getMixinsOrBuilder(int i9) {
        return this.mixins_.get(i9);
    }

    public List<? extends MixinOrBuilder> getMixinsOrBuilderList() {
        return this.mixins_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public Option getOptions(int i9) {
        return this.options_.get(i9);
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public List<Option> getOptionsList() {
        return this.options_;
    }

    public OptionOrBuilder getOptionsOrBuilder(int i9) {
        return this.options_.get(i9);
    }

    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public SourceContext getSourceContext() {
        SourceContext sourceContext = this.sourceContext_;
        return sourceContext == null ? SourceContext.getDefaultInstance() : sourceContext;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public Syntax getSyntax() {
        Syntax syntaxForNumber = Syntax.forNumber(this.syntax_);
        return syntaxForNumber == null ? Syntax.UNRECOGNIZED : syntaxForNumber;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public String getVersion() {
        return this.version_;
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    @Override // com.google.protobuf.ApiOrBuilder
    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    public static Builder newBuilder(Api api) {
        return DEFAULT_INSTANCE.createBuilder(api);
    }

    public static Api parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Api parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Api parseFrom(ByteString byteString) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMethods(int i9, Method method) {
        method.getClass();
        ensureMethodsIsMutable();
        this.methods_.add(i9, method);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMixins(int i9, Mixin mixin) {
        mixin.getClass();
        ensureMixinsIsMutable();
        this.mixins_.add(i9, mixin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOptions(int i9, Option option) {
        option.getClass();
        ensureOptionsIsMutable();
        this.options_.add(i9, option);
    }

    public static Api parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Api parseFrom(byte[] bArr) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Api parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Api parseFrom(InputStream inputStream) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Api parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Api parseFrom(CodedInputStream codedInputStream) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Api parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Api) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
