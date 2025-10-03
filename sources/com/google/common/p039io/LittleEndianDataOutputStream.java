package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class LittleEndianDataOutputStream extends FilterOutputStream implements DataOutput {
    public LittleEndianDataOutputStream(OutputStream outputStream) {
        super(new DataOutputStream((OutputStream) Preconditions.checkNotNull(outputStream)));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.DataOutput
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        ((FilterOutputStream) this).out.write(bArr, i9, i10);
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean z8) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeBoolean(z8);
    }

    @Override // java.io.DataOutput
    public void writeByte(int i9) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeByte(i9);
    }

    @Override // java.io.DataOutput
    @Deprecated
    public void writeBytes(String str) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeBytes(str);
    }

    @Override // java.io.DataOutput
    public void writeChar(int i9) throws IOException {
        writeShort(i9);
    }

    @Override // java.io.DataOutput
    public void writeChars(String str) throws IOException {
        for (int i9 = 0; i9 < str.length(); i9++) {
            writeChar(str.charAt(i9));
        }
    }

    @Override // java.io.DataOutput
    public void writeDouble(double d9) throws IOException {
        writeLong(Double.doubleToLongBits(d9));
    }

    @Override // java.io.DataOutput
    public void writeFloat(float f9) throws IOException {
        writeInt(Float.floatToIntBits(f9));
    }

    @Override // java.io.DataOutput
    public void writeInt(int i9) throws IOException {
        ((FilterOutputStream) this).out.write(i9 & 255);
        ((FilterOutputStream) this).out.write((i9 >> 8) & 255);
        ((FilterOutputStream) this).out.write((i9 >> 16) & 255);
        ((FilterOutputStream) this).out.write((i9 >> 24) & 255);
    }

    @Override // java.io.DataOutput
    public void writeLong(long j9) throws IOException {
        byte[] byteArray = Longs.toByteArray(Long.reverseBytes(j9));
        write(byteArray, 0, byteArray.length);
    }

    @Override // java.io.DataOutput
    public void writeShort(int i9) throws IOException {
        ((FilterOutputStream) this).out.write(i9 & 255);
        ((FilterOutputStream) this).out.write((i9 >> 8) & 255);
    }

    @Override // java.io.DataOutput
    public void writeUTF(String str) throws IOException {
        ((DataOutputStream) ((FilterOutputStream) this).out).writeUTF(str);
    }
}
