package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzff extends zzcb implements zzdg {
    private static final zzff zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;

    static {
        zzff zzffVar = new zzff();
        zzb = zzffVar;
        zzcb.zzp(zzff.class, zzffVar);
    }

    private zzff() {
    }

    public static zzfe zzv() {
        return (zzfe) zzb.zzg();
    }

    public static /* synthetic */ void zzx(zzff zzffVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzffVar.zzf = zzfwVar;
        zzffVar.zze = 2;
    }

    public static /* synthetic */ void zzy(zzff zzffVar, int i9) {
        zzffVar.zzg = i9 - 1;
        zzffVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0002\u0001\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzfc.zza, zzfw.class});
        }
        if (i10 == 3) {
            return new zzff();
        }
        zzfd zzfdVar = null;
        if (i10 == 4) {
            return new zzfe(zzfdVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
