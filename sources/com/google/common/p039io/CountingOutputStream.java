package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class CountingOutputStream extends FilterOutputStream {
    private long count;

    public CountingOutputStream(OutputStream outputStream) {
        super((OutputStream) Preconditions.checkNotNull(outputStream));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ((FilterOutputStream) this).out.close();
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) throws IOException {
        ((FilterOutputStream) this).out.write(bArr, i9, i10);
        this.count += i10;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i9) throws IOException {
        ((FilterOutputStream) this).out.write(i9);
        this.count++;
    }
}
