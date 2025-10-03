package com.google.protobuf;

import java.util.List;

/* loaded from: classes2.dex */
public interface ApiOrBuilder extends MessageLiteOrBuilder {
    Method getMethods(int i9);

    int getMethodsCount();

    List<Method> getMethodsList();

    Mixin getMixins(int i9);

    int getMixinsCount();

    List<Mixin> getMixinsList();

    String getName();

    ByteString getNameBytes();

    Option getOptions(int i9);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasSourceContext();
}
