package com.google.android.gms.internal.firebase_messaging;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
final class zzj extends FilterInputStream {
    private long zza;
    private long zzb;

    public zzj(InputStream inputStream, long j9) {
        super(inputStream);
        this.zzb = -1L;
        zze.zza(inputStream);
        this.zza = 1048577L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        return (int) Math.min(((FilterInputStream) this).in.available(), this.zza);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void mark(int i9) {
        ((FilterInputStream) this).in.mark(i9);
        this.zzb = this.zza;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.zza == 0) {
            return -1;
        }
        int i9 = ((FilterInputStream) this).in.read();
        if (i9 != -1) {
            this.zza--;
        }
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() {
        if (!((FilterInputStream) this).in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (this.zzb == -1) {
            throw new IOException("Mark not set");
        }
        ((FilterInputStream) this).in.reset();
        this.zza = this.zzb;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j9) throws IOException {
        long jSkip = ((FilterInputStream) this).in.skip(Math.min(j9, this.zza));
        this.zza -= jSkip;
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i9, int i10) throws IOException {
        long j9 = this.zza;
        if (j9 == 0) {
            return -1;
        }
        int i11 = ((FilterInputStream) this).in.read(bArr, i9, (int) Math.min(i10, j9));
        if (i11 != -1) {
            this.zza -= i11;
        }
        return i11;
    }
}
