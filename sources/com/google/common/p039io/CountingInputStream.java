package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class CountingInputStream extends FilterInputStream {
    private long count;
    private long mark;

    public CountingInputStream(InputStream inputStream) {
        super((InputStream) Preconditions.checkNotNull(inputStream));
        this.mark = -1L;
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i9) {
        ((FilterInputStream) this).in.mark(i9);
        this.mark = this.count;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i9 = ((FilterInputStream) this).in.read();
        if (i9 != -1) {
            this.count++;
        }
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        if (!((FilterInputStream) this).in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (this.mark == -1) {
            throw new IOException("Mark not set");
        }
        ((FilterInputStream) this).in.reset();
        this.count = this.mark;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j9) throws IOException {
        long jSkip = ((FilterInputStream) this).in.skip(j9);
        this.count += jSkip;
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        int i11 = ((FilterInputStream) this).in.read(bArr, i9, i10);
        if (i11 != -1) {
            this.count += i11;
        }
        return i11;
    }
}
