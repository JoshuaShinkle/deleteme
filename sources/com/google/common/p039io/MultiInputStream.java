package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@GwtIncompatible
/* loaded from: classes2.dex */
final class MultiInputStream extends InputStream {

    /* renamed from: in */
    private InputStream f15458in;

    /* renamed from: it */
    private Iterator<? extends ByteSource> f15459it;

    public MultiInputStream(Iterator<? extends ByteSource> it) {
        this.f15459it = (Iterator) Preconditions.checkNotNull(it);
        advance();
    }

    private void advance() {
        close();
        if (this.f15459it.hasNext()) {
            this.f15458in = this.f15459it.next().openStream();
        }
    }

    @Override // java.io.InputStream
    public int available() {
        InputStream inputStream = this.f15458in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        InputStream inputStream = this.f15458in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.f15458in = null;
            }
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.f15458in;
        if (inputStream == null) {
            return -1;
        }
        int i9 = inputStream.read();
        if (i9 != -1) {
            return i9;
        }
        advance();
        return read();
    }

    @Override // java.io.InputStream
    public long skip(long j9) throws IOException {
        InputStream inputStream = this.f15458in;
        if (inputStream == null || j9 <= 0) {
            return 0L;
        }
        long jSkip = inputStream.skip(j9);
        if (jSkip != 0) {
            return jSkip;
        }
        if (read() == -1) {
            return 0L;
        }
        return this.f15458in.skip(j9 - 1) + 1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i9, int i10) throws IOException {
        InputStream inputStream = this.f15458in;
        if (inputStream == null) {
            return -1;
        }
        int i11 = inputStream.read(bArr, i9, i10);
        if (i11 != -1) {
            return i11;
        }
        advance();
        return read(bArr, i9, i10);
    }
}
