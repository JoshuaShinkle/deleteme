package com.google.protobuf;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public abstract class ByteOutput {
    public abstract void write(byte b9);

    public abstract void write(ByteBuffer byteBuffer);

    public abstract void write(byte[] bArr, int i9, int i10);

    public abstract void writeLazy(ByteBuffer byteBuffer);

    public abstract void writeLazy(byte[] bArr, int i9, int i10);
}
