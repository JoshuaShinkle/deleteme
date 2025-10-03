package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes2.dex */
abstract class zzv {
    String zza;
    int zzb;
    Boolean zzc;
    Boolean zzd;
    Long zze;
    Long zzf;

    public zzv(String str, int i9) {
        this.zza = str;
        this.zzb = i9;
    }

    @VisibleForTesting
    public static Boolean zza(Boolean bool, boolean z8) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z8);
    }

    public abstract int zza();

    public abstract boolean zzb();

    public abstract boolean zzc();

    @VisibleForTesting
    public static Boolean zza(String str, zzbv.zzf zzfVar, zzex zzexVar) {
        List<String> list;
        Preconditions.checkNotNull(zzfVar);
        if (str == null || !zzfVar.zza() || zzfVar.zzb() == zzbv.zzf.zzb.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        zzbv.zzf.zzb zzbVarZzb = zzfVar.zzb();
        zzbv.zzf.zzb zzbVar = zzbv.zzf.zzb.IN_LIST;
        if (zzbVarZzb == zzbVar) {
            if (zzfVar.zzh() == 0) {
                return null;
            }
        } else if (!zzfVar.zzc()) {
            return null;
        }
        zzbv.zzf.zzb zzbVarZzb2 = zzfVar.zzb();
        boolean zZzf = zzfVar.zzf();
        String strZzd = (zZzf || zzbVarZzb2 == zzbv.zzf.zzb.REGEXP || zzbVarZzb2 == zzbVar) ? zzfVar.zzd() : zzfVar.zzd().toUpperCase(Locale.ENGLISH);
        if (zzfVar.zzh() == 0) {
            list = null;
        } else {
            List<String> listZzg = zzfVar.zzg();
            if (!zZzf) {
                ArrayList arrayList = new ArrayList(listZzg.size());
                Iterator<String> it = listZzg.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toUpperCase(Locale.ENGLISH));
                }
                listZzg = Collections.unmodifiableList(arrayList);
            }
            list = listZzg;
        }
        return zza(str, zzbVarZzb2, zZzf, strZzd, list, zzbVarZzb2 == zzbv.zzf.zzb.REGEXP ? strZzd : null, zzexVar);
    }

    private static Boolean zza(String str, zzbv.zzf.zzb zzbVar, boolean z8, String str2, List<String> list, String str3, zzex zzexVar) {
        if (str == null) {
            return null;
        }
        if (zzbVar == zzbv.zzf.zzb.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z8 && zzbVar != zzbv.zzf.zzb.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzr.zza[zzbVar.ordinal()]) {
            case 1:
                try {
                    break;
                } catch (PatternSyntaxException unused) {
                    if (zzexVar != null) {
                        zzexVar.zzh().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
        }
        return null;
    }

    public static Boolean zza(long j9, zzbv.zzd zzdVar) {
        try {
            return zza(new BigDecimal(j9), zzdVar, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zza(double d9, zzbv.zzd zzdVar) {
        try {
            return zza(new BigDecimal(d9), zzdVar, Math.ulp(d9));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Boolean zza(String str, zzbv.zzd zzdVar) {
        if (!zzkt.zza(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzdVar, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(BigDecimal bigDecimal, zzbv.zzd zzdVar, double d9) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzdVar);
        if (zzdVar.zza() && zzdVar.zzb() != zzbv.zzd.zza.UNKNOWN_COMPARISON_TYPE) {
            zzbv.zzd.zza zzaVarZzb = zzdVar.zzb();
            zzbv.zzd.zza zzaVar = zzbv.zzd.zza.BETWEEN;
            if (zzaVarZzb == zzaVar) {
                if (!zzdVar.zzg() || !zzdVar.zzi()) {
                    return null;
                }
            } else if (!zzdVar.zze()) {
                return null;
            }
            zzbv.zzd.zza zzaVarZzb2 = zzdVar.zzb();
            if (zzdVar.zzb() == zzaVar) {
                if (zzkt.zza(zzdVar.zzh()) && zzkt.zza(zzdVar.zzj())) {
                    try {
                        BigDecimal bigDecimal5 = new BigDecimal(zzdVar.zzh());
                        bigDecimal4 = new BigDecimal(zzdVar.zzj());
                        bigDecimal3 = bigDecimal5;
                        bigDecimal2 = null;
                    } catch (NumberFormatException unused) {
                    }
                }
                return null;
            }
            if (!zzkt.zza(zzdVar.zzf())) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(zzdVar.zzf());
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException unused2) {
            }
            if (zzaVarZzb2 == zzaVar) {
                if (bigDecimal3 == null) {
                    return null;
                }
            } else if (bigDecimal2 != null) {
            }
            int i9 = zzr.zzb[zzaVarZzb2.ordinal()];
            if (i9 == 1) {
                return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == -1);
            }
            if (i9 == 2) {
                return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == 1);
            }
            if (i9 == 3) {
                if (d9 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == 0);
                }
                if (bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d9).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d9).multiply(new BigDecimal(2)))) == -1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            if (i9 == 4) {
                if (bigDecimal.compareTo(bigDecimal3) != -1 && bigDecimal.compareTo(bigDecimal4) != 1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }
        return null;
    }
}
