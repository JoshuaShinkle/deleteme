package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhv;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class zzhi {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzhi zzc;
    private static volatile zzhi zzd;
    private static final zzhi zze = new zzhi(true);
    private final Map<zza, zzhv.zzf<?, ?>> zzf;

    public static final class zza {
        private final Object zza;
        private final int zzb;

        public zza(Object obj, int i9) {
            this.zza = obj;
            this.zzb = i9;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.zza == zzaVar.zza && this.zzb == zzaVar.zzb;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }
    }

    public zzhi() {
        this.zzf = new HashMap();
    }

    public static zzhi zza() {
        zzhi zzhiVar = zzc;
        if (zzhiVar == null) {
            synchronized (zzhi.class) {
                zzhiVar = zzc;
                if (zzhiVar == null) {
                    zzhiVar = zze;
                    zzc = zzhiVar;
                }
            }
        }
        return zzhiVar;
    }

    public static zzhi zzb() {
        zzhi zzhiVar = zzd;
        if (zzhiVar != null) {
            return zzhiVar;
        }
        synchronized (zzhi.class) {
            zzhi zzhiVar2 = zzd;
            if (zzhiVar2 != null) {
                return zzhiVar2;
            }
            zzhi zzhiVarZza = zzht.zza(zzhi.class);
            zzd = zzhiVarZza;
            return zzhiVarZza;
        }
    }

    private zzhi(boolean z8) {
        this.zzf = Collections.emptyMap();
    }

    public final <ContainingType extends zzjg> zzhv.zzf<ContainingType, ?> zza(ContainingType containingtype, int i9) {
        return (zzhv.zzf) this.zzf.get(new zza(containingtype, i9));
    }
}
