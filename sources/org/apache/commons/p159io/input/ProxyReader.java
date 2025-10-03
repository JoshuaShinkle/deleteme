package org.apache.commons.p159io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/* loaded from: classes.dex */
public abstract class ProxyReader extends FilterReader {
    public ProxyReader(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterReader) this).in.close();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public synchronized void mark(int i9) {
        ((FilterReader) this).in.mark(i9);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean markSupported() {
        return ((FilterReader) this).in.markSupported();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() {
        return ((FilterReader) this).in.read();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean ready() {
        return ((FilterReader) this).in.ready();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public synchronized void reset() {
        ((FilterReader) this).in.reset();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public long skip(long j9) {
        return ((FilterReader) this).in.skip(j9);
    }

    @Override // java.io.Reader
    public int read(char[] cArr) {
        return ((FilterReader) this).in.read(cArr);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i9, int i10) {
        return ((FilterReader) this).in.read(cArr, i9, i10);
    }
}
