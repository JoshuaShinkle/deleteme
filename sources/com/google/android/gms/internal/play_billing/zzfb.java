package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzfb extends zzcb implements zzdg {
    private static final zzfb zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private int zzg;
    private zzfj zzh;
    private zzfs zzi;

    static {
        zzfb zzfbVar = new zzfb();
        zzb = zzfbVar;
        zzcb.zzp(zzfb.class, zzfbVar);
    }

    private zzfb() {
    }

    public static /* synthetic */ void zzA(zzfb zzfbVar, int i9) {
        zzfbVar.zzg = i9 - 1;
        zzfbVar.zzd |= 1;
    }

    public static zzfa zzv() {
        return (zzfa) zzb.zzg();
    }

    public static zzfb zzx(byte[] bArr, zzbn zzbnVar) {
        return (zzfb) zzcb.zzj(zzb, bArr, zzbnVar);
    }

    public static /* synthetic */ void zzy(zzfb zzfbVar, zzfj zzfjVar) {
        zzfjVar.getClass();
        zzfbVar.zzh = zzfjVar;
        zzfbVar.zzd |= 2;
    }

    public static /* synthetic */ void zzz(zzfb zzfbVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzfbVar.zzf = zzfwVar;
        zzfbVar.zze = 4;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzfc.zza, "zzh", "zzi", zzfw.class});
        }
        if (i10 == 3) {
            return new zzfb();
        }
        zzez zzezVar = null;
        if (i10 == 4) {
            return new zzfa(zzezVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
