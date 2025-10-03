package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.Set;

@CheckReturnValue
@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms/common/testing/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes2.dex */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zza;
    private static volatile Set zzb;
    private final Context zzc;
    private volatile String zzd;

    public GoogleSignatureVerifier(Context context) {
        this.zzc = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zza == null) {
                zzn.zze(context);
                zza = new GoogleSignatureVerifier(context);
            }
        }
        return zza;
    }

    public static final zzj zza(PackageInfo packageInfo, zzj... zzjVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzk zzkVar = new zzk(packageInfo.signatures[0].toByteArray());
        for (int i9 = 0; i9 < zzjVarArr.length; i9++) {
            if (zzjVarArr[i9].equals(zzkVar)) {
                return zzjVarArr[i9];
            }
        }
        return null;
    }

    public static final boolean zzb(PackageInfo packageInfo, boolean z8) {
        if (z8 && packageInfo != null && ("com.android.vending".equals(packageInfo.packageName) || "com.google.android.gms".equals(packageInfo.packageName))) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            z8 = (applicationInfo == null || (applicationInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) == 0) ? false : true;
        }
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z8 ? zza(packageInfo, zzm.zza) : zza(packageInfo, zzm.zza[0])) != null) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private final zzx zzc(String str, boolean z8, boolean z9) throws PackageManager.NameNotFoundException {
        zzx zzxVarZzc;
        ApplicationInfo applicationInfo;
        if (str == null) {
            return zzx.zzc("null pkg");
        }
        if (str.equals(this.zzd)) {
            return zzx.zzb();
        }
        if (zzn.zzg()) {
            zzxVarZzc = zzn.zzb(str, GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc), false, false);
        } else {
            try {
                PackageInfo packageInfo = this.zzc.getPackageManager().getPackageInfo(str, 64);
                boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc);
                if (packageInfo == null) {
                    zzxVarZzc = zzx.zzc("null pkg");
                } else {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (signatureArr == null || signatureArr.length != 1) {
                        zzxVarZzc = zzx.zzc("single cert required");
                    } else {
                        zzk zzkVar = new zzk(packageInfo.signatures[0].toByteArray());
                        String str2 = packageInfo.packageName;
                        zzx zzxVarZza = zzn.zza(str2, zzkVar, zHonorsDebugCertificates, false);
                        zzxVarZzc = (!zzxVarZza.zza || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || !zzn.zza(str2, zzkVar, false, true).zza) ? zzxVarZza : zzx.zzc("debuggable release cert app rejected");
                    }
                }
            } catch (PackageManager.NameNotFoundException e9) {
                return zzx.zzd("no pkg ".concat(str), e9);
            }
        }
        if (zzxVarZzc.zza) {
            this.zzd = str;
        }
        return zzxVarZzc;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zzb(packageInfo, false)) {
            return true;
        }
        if (zzb(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzc)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) throws PackageManager.NameNotFoundException {
        zzx zzxVarZzc = zzc(str, false, false);
        zzxVarZzc.zze();
        return zzxVarZzc.zza;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i9) throws PackageManager.NameNotFoundException {
        zzx zzxVarZzc;
        int length;
        String[] packagesForUid = this.zzc.getPackageManager().getPackagesForUid(i9);
        if (packagesForUid != null && (length = packagesForUid.length) != 0) {
            zzxVarZzc = null;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    Preconditions.checkNotNull(zzxVarZzc);
                    break;
                }
                zzxVarZzc = zzc(packagesForUid[i10], false, false);
                if (zzxVarZzc.zza) {
                    break;
                }
                i10++;
            }
        } else {
            zzxVarZzc = zzx.zzc("no pkgs");
        }
        zzxVarZzc.zze();
        return zzxVarZzc.zza;
    }
}
