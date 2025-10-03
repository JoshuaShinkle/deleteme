package com.google.protobuf;

import java.util.List;

/* loaded from: classes2.dex */
public interface TypeOrBuilder extends MessageLiteOrBuilder {
    Field getFields(int i9);

    int getFieldsCount();

    List<Field> getFieldsList();

    String getName();

    ByteString getNameBytes();

    String getOneofs(int i9);

    ByteString getOneofsBytes(int i9);

    int getOneofsCount();

    List<String> getOneofsList();

    Option getOptions(int i9);

    int getOptionsCount();

    List<Option> getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
