package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public abstract class zzqe {
    int zzawf;
    int zzawg;
    private int zzawh;
    zzqh zzawi;
    private boolean zzawj;

    private zzqe() {
        this.zzawg = 100;
        this.zzawh = Integer.MAX_VALUE;
        this.zzawj = false;
    }

    public static zzqe zza(byte[] bArr, int i9, int i10, boolean z8) {
        zzqg zzqgVar = new zzqg(bArr, i9, i10, false);
        try {
            zzqgVar.zzaq(i10);
            return zzqgVar;
        } catch (zzrk e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    public static zzqe zzd(byte[] bArr, int i9, int i10) {
        return zza(bArr, i9, i10, false);
    }

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    public abstract <T extends zzsk> T zza(zzsu<T> zzsuVar, zzqp zzqpVar);

    public abstract void zzan(int i9);

    public abstract boolean zzao(int i9);

    public final int zzap(int i9) {
        if (i9 >= 0) {
            int i10 = this.zzawg;
            this.zzawg = i9;
            return i10;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append("Recursion limit cannot be negative: ");
        sb.append(i9);
        throw new IllegalArgumentException(sb.toString());
    }

    public abstract int zzaq(int i9);

    public abstract void zzar(int i9);

    public abstract void zzas(int i9);

    public abstract int zzni();

    public abstract long zznj();

    public abstract long zznk();

    public abstract int zznl();

    public abstract long zznm();

    public abstract int zznn();

    public abstract boolean zzno();

    public abstract String zznp();

    public abstract zzps zznq();

    public abstract int zznr();

    public abstract int zzns();

    public abstract int zznt();

    public abstract long zznu();

    public abstract int zznv();

    public abstract long zznw();

    public abstract long zznx();

    public abstract boolean zzny();

    public abstract int zznz();
}
