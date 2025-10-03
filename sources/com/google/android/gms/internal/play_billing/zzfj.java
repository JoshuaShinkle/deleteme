package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzfj extends zzcb implements zzdg {
    private static final zzfj zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private String zzf = "";
    private String zzh = "";

    static {
        zzfj zzfjVar = new zzfj();
        zzb = zzfjVar;
        zzcb.zzp(zzfj.class, zzfjVar);
    }

    private zzfj() {
    }

    public static zzfh zzv() {
        return (zzfh) zzb.zzg();
    }

    public static /* synthetic */ void zzx(zzfj zzfjVar, int i9) {
        zzfjVar.zzd |= 1;
        zzfjVar.zze = i9;
    }

    public static /* synthetic */ void zzy(zzfj zzfjVar, String str) {
        str.getClass();
        zzfjVar.zzd |= 2;
        zzfjVar.zzf = str;
    }

    public static /* synthetic */ void zzz(zzfj zzfjVar, int i9) {
        zzfjVar.zzg = i9 - 1;
        zzfjVar.zzd |= 4;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004᠌\u0002\u0005ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzfi.zza, "zzh"});
        }
        if (i10 == 3) {
            return new zzfj();
        }
        zzfg zzfgVar = null;
        if (i10 == 4) {
            return new zzfh(zzfgVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
