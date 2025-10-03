package org.apache.commons.p159io.output;

import java.io.Writer;

/* loaded from: classes.dex */
public class NullWriter extends Writer {
    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Writer
    public void write(int i9) {
    }

    @Override // java.io.Writer
    public void write(String str) {
    }

    @Override // java.io.Writer
    public void write(String str, int i9, int i10) {
    }

    @Override // java.io.Writer
    public void write(char[] cArr) {
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i9, int i10) {
    }
}
