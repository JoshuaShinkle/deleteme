package org.apache.commons.p159io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class ProxyWriter extends FilterWriter {
    public ProxyWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterWriter) this).out.close();
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        ((FilterWriter) this).out.flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i9) throws IOException {
        ((FilterWriter) this).out.write(i9);
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        ((FilterWriter) this).out.write(cArr);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i9, int i10) throws IOException {
        ((FilterWriter) this).out.write(cArr, i9, i10);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        ((FilterWriter) this).out.write(str);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i9, int i10) throws IOException {
        ((FilterWriter) this).out.write(str, i9, i10);
    }
}
