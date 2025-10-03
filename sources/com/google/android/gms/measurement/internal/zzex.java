package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
public final class zzex extends zzgx {
    private char zza;
    private long zzb;
    private String zzc;
    private final zzez zzd;
    private final zzez zze;
    private final zzez zzf;
    private final zzez zzg;
    private final zzez zzh;
    private final zzez zzi;
    private final zzez zzj;
    private final zzez zzk;
    private final zzez zzl;

    public zzex(zzgb zzgbVar) {
        super(zzgbVar);
        this.zza = (char) 0;
        this.zzb = -1L;
        this.zzd = new zzez(this, 6, false, false);
        this.zze = new zzez(this, 6, true, false);
        this.zzf = new zzez(this, 6, false, true);
        this.zzg = new zzez(this, 5, false, false);
        this.zzh = new zzez(this, 5, true, false);
        this.zzi = new zzez(this, 5, false, true);
        this.zzj = new zzez(this, 4, false, false);
        this.zzk = new zzez(this, 3, false, false);
        this.zzl = new zzez(this, 2, false, false);
    }

    public static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzey(str);
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    @VisibleForTesting
    private final String zzy() {
        String str;
        synchronized (this) {
            if (this.zzc == null) {
                this.zzc = this.zzy.zzr() != null ? this.zzy.zzr() : "FA";
            }
            str = this.zzc;
        }
        return str;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    public final boolean zzd() {
        return false;
    }

    public final zzez zze() {
        return this.zzd;
    }

    public final zzez zzf() {
        return this.zze;
    }

    public final zzez zzg() {
        return this.zzf;
    }

    public final zzez zzh() {
        return this.zzg;
    }

    public final zzez zzi() {
        return this.zzh;
    }

    public final zzez zzj() {
        return this.zzi;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzev zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzfu zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzex zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfj zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu, com.google.android.gms.measurement.internal.zzgw
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    public final zzez zzu() {
        return this.zzj;
    }

    public final zzez zzv() {
        return this.zzk;
    }

    public final zzez zzw() {
        return this.zzl;
    }

    public final String zzx() {
        Pair<String, Long> pairZza = zzr().zzb.zza();
        if (pairZza == null || pairZza == zzfj.zza) {
            return null;
        }
        String strValueOf = String.valueOf(pairZza.second);
        String str = (String) pairZza.first;
        StringBuilder sb = new StringBuilder(strValueOf.length() + 1 + String.valueOf(str).length());
        sb.append(strValueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    public final void zza(int i9, boolean z8, boolean z9, String str, Object obj, Object obj2, Object obj3) {
        if (!z8 && zza(i9)) {
            zza(i9, zza(false, str, obj, obj2, obj3));
        }
        if (z9 || i9 < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzfu zzfuVarZzf = this.zzy.zzf();
        if (zzfuVarZzf == null) {
            zza(6, "Scheduler not set. Not logging error/warn");
            return;
        }
        if (!zzfuVarZzf.zzz()) {
            zza(6, "Scheduler not initialized. Not logging error/warn");
            return;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 >= 9) {
            i9 = 8;
        }
        zzfuVarZzf.zza(new zzew(this, i9, str, obj, obj2, obj3));
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @VisibleForTesting
    public final boolean zza(int i9) {
        return Log.isLoggable(zzy(), i9);
    }

    @VisibleForTesting
    public final void zza(int i9, String str) {
        Log.println(i9, zzy(), str);
    }

    public static String zza(boolean z8, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String strZza = zza(z8, obj);
        String strZza2 = zza(z8, obj2);
        String strZza3 = zza(z8, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(strZza)) {
            sb.append(str2);
            sb.append(strZza);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(strZza2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(strZza2);
        }
        if (!TextUtils.isEmpty(strZza3)) {
            sb.append(str3);
            sb.append(strZza3);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z8, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i9 = 0;
        if (obj instanceof Long) {
            if (!z8) {
                return String.valueOf(obj);
            }
            Long l9 = (Long) obj;
            if (Math.abs(l9.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
            String strValueOf = String.valueOf(Math.abs(l9.longValue()));
            long jRound = Math.round(Math.pow(10.0d, strValueOf.length() - 1));
            long jRound2 = Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(str.length() + 43 + str.length());
            sb.append(str);
            sb.append(jRound);
            sb.append("...");
            sb.append(str);
            sb.append(jRound2);
            return sb.toString();
        }
        if (obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        if (obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            StringBuilder sb2 = new StringBuilder(z8 ? th.getClass().getName() : th.toString());
            String strZzb = zzb(zzgb.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i9 >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i9];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(strZzb)) {
                    sb2.append(": ");
                    sb2.append(stackTraceElement);
                    break;
                }
                i9++;
            }
            return sb2.toString();
        }
        if (obj instanceof zzey) {
            return ((zzey) obj).zza;
        }
        return z8 ? "-" : String.valueOf(obj);
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
