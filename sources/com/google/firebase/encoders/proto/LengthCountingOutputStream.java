package com.google.firebase.encoders.proto;

import java.io.OutputStream;

/* loaded from: classes2.dex */
final class LengthCountingOutputStream extends OutputStream {
    private long length = 0;

    public long getLength() {
        return this.length;
    }

    @Override // java.io.OutputStream
    public void write(int i9) {
        this.length++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.length += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i9, int i10) {
        int i11;
        if (i9 >= 0 && i9 <= bArr.length && i10 >= 0 && (i11 = i9 + i10) <= bArr.length && i11 >= 0) {
            this.length += i10;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
