package org.apache.commons.p159io.input;

import java.io.InputStream;

/* loaded from: classes.dex */
public class CountingInputStream extends ProxyInputStream {
    private long count;

    public CountingInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public synchronized long getByteCount() {
        return this.count;
    }

    public synchronized int getCount() {
        long byteCount;
        byteCount = getByteCount();
        if (byteCount > 2147483647L) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The byte count ");
            stringBuffer.append(byteCount);
            stringBuffer.append(" is too large to be converted to an int");
            throw new ArithmeticException(stringBuffer.toString());
        }
        return (int) byteCount;
    }

    @Override // org.apache.commons.p159io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        int i9 = super.read(bArr);
        this.count += i9 >= 0 ? i9 : 0L;
        return i9;
    }

    public synchronized long resetByteCount() {
        long j9;
        j9 = this.count;
        this.count = 0L;
        return j9;
    }

    public synchronized int resetCount() {
        long jResetByteCount;
        jResetByteCount = resetByteCount();
        if (jResetByteCount > 2147483647L) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The byte count ");
            stringBuffer.append(jResetByteCount);
            stringBuffer.append(" is too large to be converted to an int");
            throw new ArithmeticException(stringBuffer.toString());
        }
        return (int) jResetByteCount;
    }

    @Override // org.apache.commons.p159io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j9) {
        long jSkip = super.skip(j9);
        this.count += jSkip;
        return jSkip;
    }

    @Override // org.apache.commons.p159io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) {
        int i11 = super.read(bArr, i9, i10);
        this.count += i11 >= 0 ? i11 : 0L;
        return i11;
    }

    @Override // org.apache.commons.p159io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int i9 = super.read();
        this.count += i9 >= 0 ? 1L : 0L;
        return i9;
    }
}
