package com.google.android.gms.internal.measurement;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class zzgm implements Serializable, Iterable<Byte> {
    public static final zzgm zza = new zzgw(zzhx.zzb);
    private static final zzgs zzb;
    private static final Comparator<zzgm> zzd;
    private int zzc = 0;

    static {
        zzgp zzgpVar = null;
        zzb = zzgj.zza() ? new zzgz(zzgpVar) : new zzgq(zzgpVar);
        zzd = new zzgo();
    }

    public static zzgm zza(byte[] bArr, int i9, int i10) {
        zzb(i9, i9 + i10, bArr.length);
        return new zzgw(zzb.zza(bArr, i9, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b9) {
        return b9 & UnsignedBytes.MAX_VALUE;
    }

    public static zzgu zzc(int i9) {
        return new zzgu(i9, null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZza = this.zzc;
        if (iZza == 0) {
            int iZza2 = zza();
            iZza = zza(iZza2, 0, iZza2);
            if (iZza == 0) {
                iZza = 1;
            }
            this.zzc = iZza;
        }
        return iZza;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzgp(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zza());
        objArr[2] = zza() <= 50 ? zzkj.zza(this) : String.valueOf(zzkj.zza(zza(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i9);

    public abstract int zza();

    public abstract int zza(int i9, int i10, int i11);

    public abstract zzgm zza(int i9, int i10);

    public abstract String zza(Charset charset);

    public abstract void zza(zzgn zzgnVar);

    public abstract byte zzb(int i9);

    public final String zzb() {
        return zza() == 0 ? "" : zza(zzhx.zza);
    }

    public abstract boolean zzc();

    public final int zzd() {
        return this.zzc;
    }

    public static zzgm zza(byte[] bArr) {
        return new zzgw(bArr);
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

    public static zzgm zza(String str) {
        return new zzgw(str.getBytes(zzhx.zza));
    }
}
