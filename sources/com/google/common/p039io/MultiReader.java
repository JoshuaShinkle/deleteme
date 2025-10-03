package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

@GwtIncompatible
/* loaded from: classes2.dex */
class MultiReader extends Reader {
    private Reader current;

    /* renamed from: it */
    private final Iterator<? extends CharSource> f15460it;

    public MultiReader(Iterator<? extends CharSource> it) {
        this.f15460it = it;
        advance();
    }

    private void advance() {
        close();
        if (this.f15460it.hasNext()) {
            this.current = this.f15460it.next().openStream();
        }
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i9, int i10) throws IOException {
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int i11 = reader.read(cArr, i9, i10);
        if (i11 != -1) {
            return i11;
        }
        advance();
        return read(cArr, i9, i10);
    }

    @Override // java.io.Reader
    public boolean ready() {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader
    public long skip(long j9) throws IOException {
        Preconditions.checkArgument(j9 >= 0, "n is negative");
        if (j9 > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long jSkip = reader.skip(j9);
                if (jSkip > 0) {
                    return jSkip;
                }
                advance();
            }
        }
        return 0L;
    }
}
