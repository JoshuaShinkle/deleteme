package org.apache.commons.p159io.output;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class TeeOutputStream extends ProxyOutputStream {
    protected OutputStream branch;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        super(outputStream);
        this.branch = outputStream2;
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.branch.close();
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        super.flush();
        this.branch.flush();
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr) {
        super.write(bArr);
        this.branch.write(bArr);
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i9, int i10) {
        super.write(bArr, i9, i10);
        this.branch.write(bArr, i9, i10);
    }

    @Override // org.apache.commons.p159io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int i9) {
        super.write(i9);
        this.branch.write(i9);
    }
}
