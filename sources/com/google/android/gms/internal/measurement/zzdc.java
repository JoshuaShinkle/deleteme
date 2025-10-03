package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.util.Log;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public abstract class zzdc<T> {
    private static volatile zzdk zzb = null;
    private static volatile boolean zzc = false;
    private final zzdl zzf;
    private final String zzg;
    private final T zzh;
    private volatile int zzj;
    private volatile T zzk;
    private final boolean zzl;
    private static final Object zza = new Object();
    private static final AtomicReference<Collection<zzdc<?>>> zzd = new AtomicReference<>();
    private static zzdp zze = new zzdp(zzde.zza);
    private static final AtomicInteger zzi = new AtomicInteger();

    private zzdc(zzdl zzdlVar, String str, T t8, boolean z8) {
        this.zzj = -1;
        if (zzdlVar.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzf = zzdlVar;
        this.zzg = str;
        this.zzh = t8;
        this.zzl = z8;
    }

    @Deprecated
    public static void zza(final Context context) {
        synchronized (zza) {
            zzdk zzdkVar = zzb;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzdkVar == null || zzdkVar.zza() != context) {
                zzco.zzc();
                zzdn.zza();
                zzcx.zza();
                zzb = new zzcp(context, zzea.zza(new zzeb(context) { // from class: com.google.android.gms.internal.measurement.zzdf
                    private final Context zza;

                    {
                        this.zza = context;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzeb
                    public final Object zza() {
                        return zzdc.zzb(this.zza);
                    }
                }));
                zzi.incrementAndGet();
            }
        }
    }

    public static final /* synthetic */ boolean zzd() {
        return true;
    }

    public abstract T zza(Object obj);

    public final String zzb() {
        return zza(this.zzf.zzc);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00be A[Catch: all -> 0x0110, TryCatch #0 {, blocks: (B:8:0x001c, B:10:0x0020, B:14:0x0029, B:16:0x0040, B:22:0x0051, B:24:0x0057, B:26:0x0065, B:30:0x0082, B:32:0x008c, B:50:0x00df, B:52:0x00ef, B:54:0x0103, B:55:0x0106, B:56:0x010a, B:43:0x00be, B:45:0x00d4, B:49:0x00dd, B:28:0x0078, B:33:0x0091, B:35:0x009a, B:37:0x00ac, B:39:0x00b7, B:38:0x00b1, B:57:0x010e), top: B:64:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ef A[Catch: all -> 0x0110, TryCatch #0 {, blocks: (B:8:0x001c, B:10:0x0020, B:14:0x0029, B:16:0x0040, B:22:0x0051, B:24:0x0057, B:26:0x0065, B:30:0x0082, B:32:0x008c, B:50:0x00df, B:52:0x00ef, B:54:0x0103, B:55:0x0106, B:56:0x010a, B:43:0x00be, B:45:0x00d4, B:49:0x00dd, B:28:0x0078, B:33:0x0091, B:35:0x009a, B:37:0x00ac, B:39:0x00b7, B:38:0x00b1, B:57:0x010e), top: B:64:0x001c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final T zzc() {
        T tZza;
        zzdx<zzcy> zzdxVarZza;
        Object objZza;
        if (!this.zzl) {
            zzdw.zzb(zze.zza(this.zzg), "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error.");
        }
        int i9 = zzi.get();
        if (this.zzj < i9) {
            synchronized (this) {
                if (this.zzj < i9) {
                    zzdk zzdkVar = zzb;
                    boolean z8 = true;
                    zzdw.zzb(zzdkVar != null, "Must call PhenotypeFlag.init() first");
                    String str = (String) zzcx.zza(zzdkVar.zza()).zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
                    if (str == null || !zzck.zzb.matcher(str).matches()) {
                        z8 = false;
                    }
                    if (!z8) {
                        zzcs zzcsVarZza = this.zzf.zza != null ? zzda.zza(zzdkVar.zza(), this.zzf.zza) ? zzco.zza(zzdkVar.zza().getContentResolver(), this.zzf.zza) : null : zzdn.zza(zzdkVar.zza(), (String) null);
                        if (zzcsVarZza != null && (objZza = zzcsVarZza.zza(zzb())) != null) {
                            tZza = zza(objZza);
                        }
                        if (tZza == null) {
                            Object objZza2 = zzcx.zza(zzdkVar.zza()).zza(zza(this.zzf.zzb));
                            tZza = objZza2 != null ? zza(objZza2) : null;
                            if (tZza == null) {
                                tZza = this.zzh;
                            }
                        }
                        zzdxVarZza = zzdkVar.zzb().zza();
                        if (zzdxVarZza.zza()) {
                            zzcy zzcyVarZzb = zzdxVarZza.zzb();
                            zzdl zzdlVar = this.zzf;
                            String strZza = zzcyVarZzb.zza(zzdlVar.zza, null, zzdlVar.zzc, this.zzg);
                            tZza = strZza == null ? this.zzh : zza((Object) strZza);
                        }
                        this.zzk = tZza;
                        this.zzj = i9;
                    } else if (Log.isLoggable("PhenotypeFlag", 3)) {
                        String strValueOf = String.valueOf(zzb());
                        Log.d("PhenotypeFlag", strValueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(strValueOf) : new String("Bypass reading Phenotype values for flag: "));
                    }
                    tZza = null;
                    if (tZza == null) {
                    }
                    zzdxVarZza = zzdkVar.zzb().zza();
                    if (zzdxVarZza.zza()) {
                    }
                    this.zzk = tZza;
                    this.zzj = i9;
                }
            }
        }
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzdc<Long> zzb(zzdl zzdlVar, String str, long j9, boolean z8) {
        return new zzdh(zzdlVar, str, Long.valueOf(j9), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzdc<Boolean> zzb(zzdl zzdlVar, String str, boolean z8, boolean z9) {
        return new zzdg(zzdlVar, str, Boolean.valueOf(z8), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzdc<Double> zzb(zzdl zzdlVar, String str, double d9, boolean z8) {
        return new zzdj(zzdlVar, str, Double.valueOf(-3.0d), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzdc<String> zzb(zzdl zzdlVar, String str, String str2, boolean z8) {
        return new zzdi(zzdlVar, str, str2, true);
    }

    public static final /* synthetic */ zzdx zzb(Context context) {
        new zzdb();
        return zzdb.zza(context);
    }

    public /* synthetic */ zzdc(zzdl zzdlVar, String str, Object obj, boolean z8, zzdh zzdhVar) {
        this(zzdlVar, str, obj, z8);
    }

    public static void zza() {
        zzi.incrementAndGet();
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf(this.zzg);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }
}
