package org.apache.commons.p159io.output;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class CountingOutputStream extends ProxyOutputStream {
    private long count;

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
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

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.count += bArr.length;
        super.write(bArr);
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        this.count += i10;
        super.write(bArr, i9, i10);
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i9) throws IOException {
        this.count++;
        super.write(i9);
    }
}
