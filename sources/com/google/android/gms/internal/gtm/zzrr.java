package com.google.android.gms.internal.gtm;

/* loaded from: classes2.dex */
public class zzrr {
    private static final zzqp zzavr = zzqp.zzoq();
    private zzps zzbcb;
    private volatile zzsk zzbcc;
    private volatile zzps zzbcd;

    private final zzsk zzh(zzsk zzskVar) {
        if (this.zzbcc == null) {
            synchronized (this) {
                if (this.zzbcc == null) {
                    try {
                        this.zzbcc = zzskVar;
                        this.zzbcd = zzps.zzavx;
                    } catch (zzrk unused) {
                        this.zzbcc = zzskVar;
                        this.zzbcd = zzps.zzavx;
                    }
                }
            }
        }
        return this.zzbcc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzrr)) {
            return false;
        }
        zzrr zzrrVar = (zzrr) obj;
        zzsk zzskVar = this.zzbcc;
        zzsk zzskVar2 = zzrrVar.zzbcc;
        return (zzskVar == null && zzskVar2 == null) ? zzmv().equals(zzrrVar.zzmv()) : (zzskVar == null || zzskVar2 == null) ? zzskVar != null ? zzskVar.equals(zzrrVar.zzh(zzskVar.zzpi())) : zzh(zzskVar2.zzpi()).equals(zzskVar2) : zzskVar.equals(zzskVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzsk zzi(zzsk zzskVar) {
        zzsk zzskVar2 = this.zzbcc;
        this.zzbcb = null;
        this.zzbcd = null;
        this.zzbcc = zzskVar;
        return zzskVar2;
    }

    public final zzps zzmv() {
        if (this.zzbcd != null) {
            return this.zzbcd;
        }
        synchronized (this) {
            if (this.zzbcd != null) {
                return this.zzbcd;
            }
            if (this.zzbcc == null) {
                this.zzbcd = zzps.zzavx;
            } else {
                this.zzbcd = this.zzbcc.zzmv();
            }
            return this.zzbcd;
        }
    }

    public final int zzpe() {
        if (this.zzbcd != null) {
            return this.zzbcd.size();
        }
        if (this.zzbcc != null) {
            return this.zzbcc.zzpe();
        }
        return 0;
    }
}
