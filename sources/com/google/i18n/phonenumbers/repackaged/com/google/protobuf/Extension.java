package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Descriptors;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.WireFormat;

/* loaded from: classes2.dex */
public abstract class Extension<ContainingType extends MessageLite, Type> {

    public enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    public abstract Object fromReflectionType(Object obj);

    public abstract Type getDefaultValue();

    public abstract Descriptors.FieldDescriptor getDescriptor();

    public ExtensionType getExtensionType() {
        return ExtensionType.IMMUTABLE;
    }

    public abstract WireFormat.FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }

    public abstract int getNumber();

    public abstract boolean isRepeated();

    public abstract Object singularFromReflectionType(Object obj);

    public abstract Object singularToReflectionType(Object obj);

    public abstract Object toReflectionType(Object obj);
}
