package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzgd extends zzcb implements zzdg {
    private static final zzgd zzb;
    private int zzd;
    private int zze;

    static {
        zzgd zzgdVar = new zzgd();
        zzb = zzgdVar;
        zzcb.zzp(zzgd.class, zzgdVar);
    }

    private zzgd() {
    }

    public static zzgd zzw() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzgc.zza});
        }
        if (i10 == 3) {
            return new zzgd();
        }
        zzga zzgaVar = null;
        if (i10 == 4) {
            return new zzgb(zzgaVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
