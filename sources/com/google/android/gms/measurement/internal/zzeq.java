package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.internal.measurement.zzmb;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.internal.measurement.zzpa;
import com.google.android.gms.internal.measurement.zzpg;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzeq extends zzg {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private long zzg;
    private List<String> zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;

    public zzeq(zzgb zzgbVar, long j9) {
        super(zzgbVar);
        this.zzg = j9;
    }

    @VisibleForTesting
    private final String zzah() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (zzpg.zzb() && zzs().zza(zzat.zzbm)) {
            zzq().zzw().zza("Disabled IID for tests.");
            return null;
        }
        try {
            Class<?> clsLoadClass = zzm().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (clsLoadClass == null) {
                return null;
            }
            try {
                Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, zzm());
                if (objInvoke == null) {
                    return null;
                }
                try {
                    return (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                } catch (Exception unused) {
                    zzq().zzj().zza("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                zzq().zzi().zza("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
        }
    }

    public final zzn zza(String str) {
        zzc();
        String strZzaa = zzaa();
        String strZzab = zzab();
        zzv();
        String str2 = this.zzb;
        long jZzae = zzae();
        zzv();
        String str3 = this.zzd;
        zzv();
        zzc();
        if (this.zzf == 0) {
            this.zzf = this.zzy.zzh().zza(zzm(), zzm().getPackageName());
        }
        long j9 = this.zzf;
        boolean zZzaa = this.zzy.zzaa();
        boolean z8 = !zzr().zzq;
        zzc();
        String strZzah = !this.zzy.zzaa() ? null : zzah();
        zzgb zzgbVar = this.zzy;
        Long lValueOf = Long.valueOf(zzgbVar.zzb().zzh.zza());
        long jMin = lValueOf.longValue() == 0 ? zzgbVar.zza : Math.min(zzgbVar.zza, lValueOf.longValue());
        int iZzaf = zzaf();
        boolean zBooleanValue = zzs().zzg().booleanValue();
        Boolean boolZzf = zzs().zzf("google_analytics_ssaid_collection_enabled");
        boolean zBooleanValue2 = Boolean.valueOf(boolZzf == null || boolZzf.booleanValue()).booleanValue();
        zzfj zzfjVarZzr = zzr();
        zzfjVarZzr.zzc();
        return new zzn(strZzaa, strZzab, str2, jZzae, str3, 31049L, j9, str, zZzaa, z8, strZzah, 0L, jMin, iZzaf, zBooleanValue, zBooleanValue2, zzfjVarZzr.zzf().getBoolean("deferred_analytics_collection", false), zzac(), zzs().zzf("google_analytics_default_allow_ad_personalization_signals") == null ? null : Boolean.valueOf(!r1.booleanValue()), this.zzg, this.zzh, (zznq.zzb() && zzs().zza(zzat.zzbj)) ? zzad() : null, (zzmb.zzb() && zzs().zza(zzat.zzco)) ? zzr().zzw().zza() : "");
    }

    public final String zzaa() {
        zzv();
        return this.zza;
    }

    public final String zzab() {
        zzv();
        return this.zzj;
    }

    public final String zzac() {
        zzv();
        return this.zzk;
    }

    public final String zzad() {
        zzv();
        return this.zzl;
    }

    public final int zzae() {
        zzv();
        return this.zzc;
    }

    public final int zzaf() {
        zzv();
        return this.zzi;
    }

    public final List<String> zzag() {
        return this.zzh;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhe zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziv zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzim zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzet zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzkb zzj() {
        return super.zzj();
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

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zzy() {
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:0|2|(1:4)(28:119|6|(1:10)(2:11|(1:13))|117|14|(4:16|(1:18)(1:20)|121|21)|26|(1:31)(1:30)|32|(1:37)(1:36)|38|(1:(1:41)(1:42))|(3:44|45|(1:57)(1:58))(0)|59|(1:61)|123|62|(1:67)(1:66)|68|(1:70)(1:71)|72|73|(2:86|(1:88))(4:77|(1:79)(1:80)|81|(1:85))|(3:90|(1:92)(1:93)|94)|98|(3:100|(1:102)(3:104|(3:107|(1:126)(1:127)|105)|125)|103)|(1:111)|(2:113|114)(2:115|116))|5|26|(2:28|31)(0)|32|(2:34|37)(0)|38|(0)|(0)(0)|59|(0)|123|62|(7:64|67|68|(0)(0)|72|73|(4:75|86|(0)|(0))(0))(0)|98|(0)|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0253, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0254, code lost:
    
        zzq().zze().zza("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzex.zza(r0), r2);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b9 A[Catch: IllegalStateException -> 0x0253, TryCatch #3 {IllegalStateException -> 0x0253, blocks: (B:62:0x01b3, B:64:0x01b9, B:66:0x01c5, B:68:0x01d4, B:72:0x01dd, B:75:0x01e7, B:77:0x01f3, B:81:0x020a, B:83:0x0212, B:90:0x0236, B:92:0x024a, B:94:0x024f, B:93:0x024d, B:85:0x0218, B:86:0x021f, B:88:0x0225, B:67:0x01d0), top: B:123:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d0 A[Catch: IllegalStateException -> 0x0253, TryCatch #3 {IllegalStateException -> 0x0253, blocks: (B:62:0x01b3, B:64:0x01b9, B:66:0x01c5, B:68:0x01d4, B:72:0x01dd, B:75:0x01e7, B:77:0x01f3, B:81:0x020a, B:83:0x0212, B:90:0x0236, B:92:0x024a, B:94:0x024f, B:93:0x024d, B:85:0x0218, B:86:0x021f, B:88:0x0225, B:67:0x01d0), top: B:123:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x021f A[Catch: IllegalStateException -> 0x0253, TryCatch #3 {IllegalStateException -> 0x0253, blocks: (B:62:0x01b3, B:64:0x01b9, B:66:0x01c5, B:68:0x01d4, B:72:0x01dd, B:75:0x01e7, B:77:0x01f3, B:81:0x020a, B:83:0x0212, B:90:0x0236, B:92:0x024a, B:94:0x024f, B:93:0x024d, B:85:0x0218, B:86:0x021f, B:88:0x0225, B:67:0x01d0), top: B:123:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0225 A[Catch: IllegalStateException -> 0x0253, TryCatch #3 {IllegalStateException -> 0x0253, blocks: (B:62:0x01b3, B:64:0x01b9, B:66:0x01c5, B:68:0x01d4, B:72:0x01dd, B:75:0x01e7, B:77:0x01f3, B:81:0x020a, B:83:0x0212, B:90:0x0236, B:92:0x024a, B:94:0x024f, B:93:0x024d, B:85:0x0218, B:86:0x021f, B:88:0x0225, B:67:0x01d0), top: B:123:0x01b3 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0236 A[Catch: IllegalStateException -> 0x0253, TryCatch #3 {IllegalStateException -> 0x0253, blocks: (B:62:0x01b3, B:64:0x01b9, B:66:0x01c5, B:68:0x01d4, B:72:0x01dd, B:75:0x01e7, B:77:0x01f3, B:81:0x020a, B:83:0x0212, B:90:0x0236, B:92:0x024a, B:94:0x024f, B:93:0x024d, B:85:0x0218, B:86:0x021f, B:88:0x0225, B:67:0x01d0), top: B:123:0x01b3 }] */
    @Override // com.google.android.gms.measurement.internal.zzg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzz() throws Resources.NotFoundException, PackageManager.NameNotFoundException {
        String str;
        String string;
        boolean z8;
        char c9;
        boolean z9;
        Object[] objArr;
        List<String> listZzg;
        String strZza;
        String packageName = zzm().getPackageName();
        PackageManager packageManager = zzm().getPackageManager();
        String str2 = "";
        String installerPackageName = "unknown";
        String str3 = "Unknown";
        int i9 = Integer.MIN_VALUE;
        if (packageManager != null) {
            try {
                installerPackageName = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException unused) {
                zzq().zze().zza("Error retrieving app installer package name. appId", zzex.zza(packageName));
            }
            if (installerPackageName == null) {
                installerPackageName = "manual_install";
            } else if ("com.android.vending".equals(installerPackageName)) {
                installerPackageName = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(zzm().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    string = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                    try {
                        str3 = packageInfo.versionName;
                        i9 = packageInfo.versionCode;
                    } catch (PackageManager.NameNotFoundException unused2) {
                        str = str3;
                        str3 = string;
                        zzq().zze().zza("Error retrieving package info. appId, appName", zzex.zza(packageName), str3);
                        string = str3;
                        str3 = str;
                        this.zza = packageName;
                        this.zzd = installerPackageName;
                        this.zzb = str3;
                        this.zzc = i9;
                        this.zze = string;
                        this.zzf = 0L;
                        Status statusInitialize = GoogleServices.initialize(zzm());
                        z8 = true;
                        if (statusInitialize != null) {
                        }
                        if (TextUtils.isEmpty(this.zzy.zzn())) {
                        }
                        z9 = c | c9;
                        if (!z9) {
                        }
                        if (z9) {
                        }
                        this.zzj = "";
                        this.zzk = "";
                        this.zzl = "";
                        if (c9 != false) {
                        }
                        if (zzpa.zzb()) {
                        }
                        this.zzh = null;
                        listZzg = zzs().zzg("analytics.safelisted_events");
                        if (listZzg != null) {
                        }
                        if (z8) {
                        }
                        if (packageManager != null) {
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException unused3) {
                str = "Unknown";
            }
            this.zza = packageName;
            this.zzd = installerPackageName;
            this.zzb = str3;
            this.zzc = i9;
            this.zze = string;
            this.zzf = 0L;
            Status statusInitialize2 = GoogleServices.initialize(zzm());
            z8 = true;
            char c10 = statusInitialize2 != null && statusInitialize2.isSuccess();
            c9 = !TextUtils.isEmpty(this.zzy.zzn()) && "am".equals(this.zzy.zzo());
            z9 = c10 | c9;
            if (!z9) {
                if (statusInitialize2 == null) {
                    zzq().zzf().zza("GoogleService failed to initialize (no status)");
                } else {
                    zzq().zzf().zza("GoogleService failed to initialize, status", Integer.valueOf(statusInitialize2.getStatusCode()), statusInitialize2.getStatusMessage());
                }
            }
            if (z9) {
                int iZzab = this.zzy.zzab();
                switch (iZzab) {
                    case 0:
                        zzq().zzw().zza("App measurement collection enabled");
                        break;
                    case 1:
                        zzq().zzu().zza("App measurement deactivated via the manifest");
                        break;
                    case 2:
                        zzq().zzw().zza("App measurement deactivated via the init parameters");
                        break;
                    case 3:
                        zzq().zzu().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                        break;
                    case 4:
                        zzq().zzu().zza("App measurement disabled via the manifest");
                        break;
                    case 5:
                        zzq().zzw().zza("App measurement disabled via the init parameters");
                        break;
                    case 6:
                        zzq().zzj().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                        break;
                    case 7:
                        zzq().zzu().zza("App measurement disabled via the global data collection setting");
                        break;
                    case 8:
                        zzq().zzu().zza("App measurement disabled due to denied storage consent");
                        break;
                    default:
                        zzq().zzu().zza("App measurement disabled");
                        zzq().zzf().zza("Invalid scion state in identity");
                        break;
                }
                objArr = iZzab == 0;
            }
            this.zzj = "";
            this.zzk = "";
            this.zzl = "";
            if (c9 != false) {
                this.zzk = this.zzy.zzn();
            }
            strZza = (zzpa.zzb() && zzs().zza(zzat.zzcj)) ? zzik.zza(zzm(), "google_app_id") : GoogleServices.getGoogleAppId();
            this.zzj = !TextUtils.isEmpty(strZza) ? "" : strZza;
            if (!zznq.zzb() && zzs().zza(zzat.zzbj)) {
                StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(zzm());
                String string2 = stringResourceValueReader.getString("ga_app_id");
                if (!TextUtils.isEmpty(string2)) {
                    str2 = string2;
                }
                this.zzl = str2;
                if (!TextUtils.isEmpty(strZza) || !TextUtils.isEmpty(string2)) {
                    this.zzk = stringResourceValueReader.getString("admob_app_id");
                }
            } else if (!TextUtils.isEmpty(strZza)) {
                this.zzk = new StringResourceValueReader(zzm()).getString("admob_app_id");
            }
            if (objArr != false) {
                zzq().zzw().zza("App measurement enabled for app package, google app id", this.zza, TextUtils.isEmpty(this.zzj) ? this.zzk : this.zzj);
            }
            this.zzh = null;
            listZzg = zzs().zzg("analytics.safelisted_events");
            if (listZzg != null) {
                if (listZzg.size() == 0) {
                    zzq().zzj().zza("Safelisted event list is empty. Ignoring");
                } else {
                    Iterator<String> it = listZzg.iterator();
                    while (it.hasNext()) {
                        if (!zzo().zzb("safelisted event", it.next())) {
                        }
                    }
                }
                z8 = false;
            }
            if (z8) {
                this.zzh = listZzg;
            }
            if (packageManager != null) {
                this.zzi = InstantApps.isInstantApp(zzm()) ? 1 : 0;
                return;
            } else {
                this.zzi = 0;
                return;
            }
        }
        zzq().zze().zza("PackageManager is null, app identity information might be inaccurate. appId", zzex.zza(packageName));
        string = "Unknown";
        this.zza = packageName;
        this.zzd = installerPackageName;
        this.zzb = str3;
        this.zzc = i9;
        this.zze = string;
        this.zzf = 0L;
        Status statusInitialize22 = GoogleServices.initialize(zzm());
        z8 = true;
        if (statusInitialize22 != null) {
        }
        if (TextUtils.isEmpty(this.zzy.zzn())) {
        }
        z9 = c10 | c9;
        if (!z9) {
        }
        if (z9) {
        }
        this.zzj = "";
        this.zzk = "";
        this.zzl = "";
        if (c9 != false) {
        }
        if (zzpa.zzb()) {
            this.zzj = !TextUtils.isEmpty(strZza) ? "" : strZza;
            if (!zznq.zzb()) {
                if (!TextUtils.isEmpty(strZza)) {
                }
                if (objArr != false) {
                }
            }
        }
        this.zzh = null;
        listZzg = zzs().zzg("analytics.safelisted_events");
        if (listZzg != null) {
        }
        if (z8) {
        }
        if (packageManager != null) {
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
