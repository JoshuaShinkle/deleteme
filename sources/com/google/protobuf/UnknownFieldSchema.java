package com.google.protobuf;

/* loaded from: classes2.dex */
abstract class UnknownFieldSchema<T, B> {
    public abstract void addFixed32(B b9, int i9, int i10);

    public abstract void addFixed64(B b9, int i9, long j9);

    public abstract void addGroup(B b9, int i9, T t8);

    public abstract void addLengthDelimited(B b9, int i9, ByteString byteString);

    public abstract void addVarint(B b9, int i9, long j9);

    public abstract B getBuilderFromMessage(Object obj);

    public abstract T getFromMessage(Object obj);

    public abstract int getSerializedSize(T t8);

    public abstract int getSerializedSizeAsMessageSet(T t8);

    public abstract void makeImmutable(Object obj);

    public abstract T merge(T t8, T t9);

    public final void mergeFrom(B b9, Reader reader) {
        while (reader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(b9, reader)) {
        }
    }

    public final boolean mergeOneFieldFrom(B b9, Reader reader) throws InvalidProtocolBufferException {
        int tag = reader.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            addVarint(b9, tagFieldNumber, reader.readInt64());
            return true;
        }
        if (tagWireType == 1) {
            addFixed64(b9, tagFieldNumber, reader.readFixed64());
            return true;
        }
        if (tagWireType == 2) {
            addLengthDelimited(b9, tagFieldNumber, reader.readBytes());
            return true;
        }
        if (tagWireType != 3) {
            if (tagWireType == 4) {
                return false;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            addFixed32(b9, tagFieldNumber, reader.readFixed32());
            return true;
        }
        B bNewBuilder = newBuilder();
        int iMakeTag = WireFormat.makeTag(tagFieldNumber, 4);
        mergeFrom(bNewBuilder, reader);
        if (iMakeTag != reader.getTag()) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        addGroup(b9, tagFieldNumber, toImmutable(bNewBuilder));
        return true;
    }

    public abstract B newBuilder();

    public abstract void setBuilderToMessage(Object obj, B b9);

    public abstract void setToMessage(Object obj, T t8);

    public abstract boolean shouldDiscardUnknownFields(Reader reader);

    public abstract T toImmutable(B b9);

    public abstract void writeAsMessageSetTo(T t8, Writer writer);

    public abstract void writeTo(T t8, Writer writer);
}
