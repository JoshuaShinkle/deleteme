package com.google.android.gms.internal.gtm;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class zzps implements Serializable, Iterable<Byte> {
    public static final zzps zzavx = new zzqc(zzre.zzbbh);
    private static final zzpy zzavy;
    private static final Comparator<zzps> zzavz;
    private int zzavn = 0;

    static {
        zzpt zzptVar = null;
        zzavy = zzpp.zzna() ? new zzqd(zzptVar) : new zzpw(zzptVar);
        zzavz = new zzpu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zza(byte b9) {
        return b9 & UnsignedBytes.MAX_VALUE;
    }

    public static zzqa zzam(int i9) {
        return new zzqa(i9, null);
    }

    public static zzps zzb(byte[] bArr, int i9, int i10) {
        zzb(i9, i9 + i10, bArr.length);
        return new zzqc(zzavy.zzc(bArr, i9, i10));
    }

    public static zzps zzcy(String str) {
        return new zzqc(str.getBytes(zzre.UTF_8));
    }

    public static zzps zzf(byte[] bArr) {
        return new zzqc(bArr);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZza = this.zzavn;
        if (iZza == 0) {
            int size = size();
            iZza = zza(size, 0, size);
            if (iZza == 0) {
                iZza = 1;
            }
            this.zzavn = iZza;
        }
        return iZza;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzpt(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    public abstract int zza(int i9, int i10, int i11);

    public abstract String zza(Charset charset);

    public abstract void zza(zzpr zzprVar);

    public abstract byte zzak(int i9);

    public abstract byte zzal(int i9);

    public abstract zzps zzc(int i9, int i10);

    public final String zznc() {
        return size() == 0 ? "" : zza(zzre.UTF_8);
    }

    public abstract boolean zznd();

    public final int zzne() {
        return this.zzavn;
    }

    public static int zzb(int i9, int i10, int i11) {
        int i12 = i10 - i9;
        if ((i9 | i10 | i12 | (i11 - i10)) >= 0) {
            return i12;
        }
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i9);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 < i9) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i9);
            sb2.append(", ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("End index: ");
        sb3.append(i10);
        sb3.append(" >= ");
        sb3.append(i11);
        throw new IndexOutOfBoundsException(sb3.toString());
    }
}
