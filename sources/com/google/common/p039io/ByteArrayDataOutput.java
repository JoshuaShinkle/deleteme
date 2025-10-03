package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import java.io.DataOutput;

@GwtIncompatible
/* loaded from: classes2.dex */
public interface ByteArrayDataOutput extends DataOutput {
    byte[] toByteArray();

    @Override // java.io.DataOutput
    void write(int i9);

    @Override // java.io.DataOutput
    void write(byte[] bArr);

    @Override // java.io.DataOutput
    void write(byte[] bArr, int i9, int i10);

    @Override // java.io.DataOutput
    void writeBoolean(boolean z8);

    @Override // java.io.DataOutput
    void writeByte(int i9);

    @Override // java.io.DataOutput
    @Deprecated
    void writeBytes(String str);

    @Override // java.io.DataOutput
    void writeChar(int i9);

    @Override // java.io.DataOutput
    void writeChars(String str);

    @Override // java.io.DataOutput
    void writeDouble(double d9);

    @Override // java.io.DataOutput
    void writeFloat(float f9);

    @Override // java.io.DataOutput
    void writeInt(int i9);

    @Override // java.io.DataOutput
    void writeLong(long j9);

    @Override // java.io.DataOutput
    void writeShort(int i9);

    @Override // java.io.DataOutput
    void writeUTF(String str);
}
