package com.google.android.gms.internal.play_billing;

/* loaded from: classes2.dex */
public final class zzfm extends zzcb implements zzdg {
    private static final zzfm zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzfm zzfmVar = new zzfm();
        zzb = zzfmVar;
        zzcb.zzp(zzfm.class, zzfmVar);
    }

    private zzfm() {
    }

    public static zzfl zzv() {
        return (zzfl) zzb.zzg();
    }

    public static /* synthetic */ void zzx(zzfm zzfmVar, String str) {
        str.getClass();
        zzfmVar.zzd |= 1;
        zzfmVar.zze = str;
    }

    public static /* synthetic */ void zzy(zzfm zzfmVar, String str) {
        str.getClass();
        zzfmVar.zzd |= 2;
        zzfmVar.zzf = str;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzu(int i9, Object obj, Object obj2) {
        int i10 = i9 - 1;
        if (i10 == 0) {
            return (byte) 1;
        }
        if (i10 == 2) {
            return zzcb.zzm(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i10 == 3) {
            return new zzfm();
        }
        zzfk zzfkVar = null;
        if (i10 == 4) {
            return new zzfl(zzfkVar);
        }
        if (i10 != 5) {
            return null;
        }
        return zzb;
    }
}
