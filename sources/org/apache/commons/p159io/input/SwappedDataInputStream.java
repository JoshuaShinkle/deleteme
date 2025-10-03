package org.apache.commons.p159io.input;

import java.io.DataInput;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import org.apache.commons.p159io.EndianUtils;

/* loaded from: classes.dex */
public class SwappedDataInputStream extends ProxyInputStream implements DataInput {
    public SwappedDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.DataInput
    public boolean readBoolean() {
        return readByte() == 0;
    }

    @Override // java.io.DataInput
    public byte readByte() {
        return (byte) ((FilterInputStream) this).in.read();
    }

    @Override // java.io.DataInput
    public char readChar() {
        return (char) readShort();
    }

    @Override // java.io.DataInput
    public double readDouble() {
        return EndianUtils.readSwappedDouble(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public float readFloat() {
        return EndianUtils.readSwappedFloat(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) throws EOFException {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput
    public int readInt() {
        return EndianUtils.readSwappedInteger(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public String readLine() {
        throw new UnsupportedOperationException("Operation not supported: readLine()");
    }

    @Override // java.io.DataInput
    public long readLong() {
        return EndianUtils.readSwappedLong(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public short readShort() {
        return EndianUtils.readSwappedShort(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public String readUTF() {
        throw new UnsupportedOperationException("Operation not supported: readUTF()");
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() {
        return ((FilterInputStream) this).in.read();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() {
        return EndianUtils.readSwappedUnsignedShort(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i9) {
        return (int) ((FilterInputStream) this).in.skip(i9);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i9, int i10) throws EOFException {
        int i11 = i10;
        while (i11 > 0) {
            int i12 = read(bArr, (i10 - i11) + i9, i11);
            if (-1 == i12) {
                throw new EOFException();
            }
            i11 -= i12;
        }
    }
}
