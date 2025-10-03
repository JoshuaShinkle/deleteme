package com.google.android.gms.internal.firebase_messaging;

import java.io.OutputStream;

/* loaded from: classes2.dex */
final class zzg extends OutputStream {
    public final String toString() {
        return "ByteStreams.nullOutputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i9) {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        zze.zza(bArr);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i9, int i10) {
        zze.zza(bArr);
    }
}
