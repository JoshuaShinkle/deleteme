package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;

/* loaded from: classes2.dex */
interface Schema<T> {
    boolean equals(T t8, T t9);

    int getSerializedSize(T t8);

    int hashCode(T t8);

    boolean isInitialized(T t8);

    void makeImmutable(T t8);

    void mergeFrom(T t8, Reader reader, ExtensionRegistryLite extensionRegistryLite);

    void mergeFrom(T t8, T t9);

    void mergeFrom(T t8, byte[] bArr, int i9, int i10, ArrayDecoders.Registers registers);

    T newInstance();

    void writeTo(T t8, Writer writer);
}
