package com.google.android.gms.tagmanager;

/* loaded from: classes2.dex */
final class zzca implements Runnable {
    private final /* synthetic */ zzby zzahf;
    private final /* synthetic */ long zzahg;
    private final /* synthetic */ String zzahh;
    private final /* synthetic */ zzbz zzahi;

    public zzca(zzbz zzbzVar, zzby zzbyVar, long j9, String str) {
        this.zzahi = zzbzVar;
        this.zzahf = zzbyVar;
        this.zzahg = j9;
        this.zzahh = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzahi.zzahe == null) {
            zzfn zzfnVarZzjq = zzfn.zzjq();
            zzfnVarZzjq.zza(this.zzahi.zzrm, this.zzahf);
            this.zzahi.zzahe = zzfnVarZzjq.zzjr();
        }
        this.zzahi.zzahe.zzb(this.zzahg, this.zzahh);
    }
}
