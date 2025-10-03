package com.google.protobuf;

import java.util.Collection;
import java.util.List;

/* loaded from: classes2.dex */
public interface LazyStringList extends ProtocolStringList {
    void add(ByteString byteString);

    void add(byte[] bArr);

    boolean addAllByteArray(Collection<byte[]> collection);

    boolean addAllByteString(Collection<? extends ByteString> collection);

    List<byte[]> asByteArrayList();

    byte[] getByteArray(int i9);

    ByteString getByteString(int i9);

    Object getRaw(int i9);

    List<?> getUnderlyingElements();

    LazyStringList getUnmodifiableView();

    void mergeFrom(LazyStringList lazyStringList);

    void set(int i9, ByteString byteString);

    void set(int i9, byte[] bArr);
}
