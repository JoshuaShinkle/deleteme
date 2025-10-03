package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzfw extends zzcb implements zzdg {
    private static final zzfw zzb;
    private int zzd;
    private int zze;

    static {
        zzfw zzfwVar = new zzfw();
        zzb = zzfwVar;
        zzcb.zzp(zzfw.class, zzfwVar);
    }

    private zzfw() {
    }

    public static zzfu zzv() {
        return (zzfu) zzb.zzg();
    }

    public static /* synthetic */ void zzx(zzfw zzfwVar, int i9) {
        zzfwVar.zze = i9 - 1;
        zzfwVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzfv.zza});
        }
        if (i10 == 3) {
            return new zzfw();
        }
        zzft zzftVar = null;
        if (i10 == 4) {
            return new zzfu(zzftVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
