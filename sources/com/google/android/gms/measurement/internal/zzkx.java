package com.google.android.gms.measurement.internal;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.android.gms.internal.measurement.zznq;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes2.dex */
public final class zzkx extends zzgx {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private static final String[] zzb = {"_err"};
    private SecureRandom zzc;
    private final AtomicLong zzd;
    private int zze;
    private Integer zzf;

    public zzkx(zzgb zzgbVar) {
        super(zzgbVar);
        this.zzf = null;
        this.zzd = new AtomicLong(0L);
    }

    public static boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    public static boolean zzd(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    private final int zzh(String str) {
        if (!zzb("event param", str)) {
            return 3;
        }
        if (zza("event param", (String[]) null, str)) {
            return !zza("event param", 40, str) ? 3 : 0;
        }
        return 14;
    }

    @VisibleForTesting
    private static boolean zzi(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private final int zzj(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if ("_id".equals(str)) {
            return 256;
        }
        return (zzs().zza(zzat.zzbh) && "_lgclid".equals(str)) ? 100 : 36;
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    /* renamed from: g_ */
    public final void mo17541g_() {
        zzc();
        SecureRandom secureRandom = new SecureRandom();
        long jNextLong = secureRandom.nextLong();
        if (jNextLong == 0) {
            jNextLong = secureRandom.nextLong();
            if (jNextLong == 0) {
                zzq().zzh().zza("Utils falling back to Random for random id");
            }
        }
        this.zzd.set(jNextLong);
    }

    public final boolean zzb(String str, String str2) {
        if (str2 == null) {
            zzq().zzg().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzq().zzg().zza("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt) && iCodePointAt != 95) {
            zzq().zzg().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzq().zzg().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    public final Object zzc(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzj(str), obj, true, false) : zza(zzj(str), obj, false, false);
    }

    @Override // com.google.android.gms.measurement.internal.zzgx
    public final boolean zzd() {
        return true;
    }

    public final boolean zze(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return zzs().zzu().equals(str);
    }

    public final long zzf() {
        long andIncrement;
        long j9;
        if (this.zzd.get() != 0) {
            synchronized (this.zzd) {
                this.zzd.compareAndSet(-1L, 1L);
                andIncrement = this.zzd.getAndIncrement();
            }
            return andIncrement;
        }
        synchronized (this.zzd) {
            long jNextLong = new Random(System.nanoTime() ^ zzl().currentTimeMillis()).nextLong();
            int i9 = this.zze + 1;
            this.zze = i9;
            j9 = jNextLong + i9;
        }
        return j9;
    }

    public final SecureRandom zzg() {
        zzc();
        if (this.zzc == null) {
            this.zzc = new SecureRandom();
        }
        return this.zzc;
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

    public final Bundle zza(Uri uri) {
        String queryParameter;
        String queryParameter2;
        String queryParameter3;
        String queryParameter4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                queryParameter = uri.getQueryParameter("utm_campaign");
                queryParameter2 = uri.getQueryParameter("utm_source");
                queryParameter3 = uri.getQueryParameter("utm_medium");
                queryParameter4 = uri.getQueryParameter("gclid");
            } else {
                queryParameter = null;
                queryParameter2 = null;
                queryParameter3 = null;
                queryParameter4 = null;
            }
            if (TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("campaign", queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString("source", queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString("medium", queryParameter3);
            }
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("gclid", queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter5);
            }
            String queryParameter6 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter6)) {
                bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter6);
            }
            String queryParameter7 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter7)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter7);
            }
            String queryParameter8 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter8)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter8);
            }
            String queryParameter9 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter9)) {
                bundle.putString("anid", queryParameter9);
            }
            return bundle;
        } catch (UnsupportedOperationException e9) {
            zzq().zzh().zza("Install referrer url isn't a hierarchical URI", e9);
            return null;
        }
    }

    public final int zzi() {
        if (this.zzf == null) {
            this.zzf = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(zzm()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        }
        return this.zzf.intValue();
    }

    public static MessageDigest zzh() throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        for (int i9 = 0; i9 < 2; i9++) {
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }

    public final boolean zzc(String str) {
        zzc();
        if (Wrappers.packageManager(zzm()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzq().zzv().zza("Permission not granted", str);
        return false;
    }

    private final int zzg(String str) {
        if (!zza("event param", str)) {
            return 3;
        }
        if (zza("event param", (String[]) null, str)) {
            return !zza("event param", 40, str) ? 3 : 0;
        }
        return 14;
    }

    public final boolean zzj() throws ClassNotFoundException {
        try {
            zzm().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean zzc(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    @VisibleForTesting
    private final boolean zzc(Context context, String str) {
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (PackageManager.NameNotFoundException e9) {
            zzq().zze().zza("Package name not found", e9);
            return true;
        } catch (CertificateException e10) {
            zzq().zze().zza("Error obtaining certificate", e10);
            return true;
        }
    }

    public static boolean zzf(String str) {
        for (String str2 : zzb) {
            if (str2.equals(str)) {
                return false;
            }
        }
        return true;
    }

    public final int zzb(String str) {
        if (!zzb("user property", str)) {
            return 6;
        }
        if (zza("user property", zzha.zza, str)) {
            return !zza("user property", 24, str) ? 6 : 0;
        }
        return 15;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    private final boolean zzb(String str, String str2, int i9, Object obj) {
        int size;
        if (obj instanceof Parcelable[]) {
            size = ((Parcelable[]) obj).length;
        } else {
            if (obj instanceof ArrayList) {
                size = ((ArrayList) obj).size();
            }
            return true;
        }
        if (size > i9) {
            zzq().zzj().zza("Parameter array is too long; discarded. Value kind, name, array length", str, str2, Integer.valueOf(size));
            return false;
        }
        return true;
    }

    public static Bundle[] zzb(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        }
        if (!(obj instanceof ArrayList)) {
            return null;
        }
        ArrayList arrayList = (ArrayList) obj;
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private static boolean zzb(Bundle bundle, int i9) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", i9);
        return true;
    }

    public static boolean zza(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    public final int zzb(String str, Object obj) {
        boolean zZza;
        if ("_ldl".equals(str)) {
            zZza = zza("user property referrer", str, zzj(str), obj);
        } else {
            zZza = zza("user property", str, zzj(str), obj);
        }
        return zZza ? 0 : 7;
    }

    public final boolean zza(String str, String str2) {
        if (str2 == null) {
            zzq().zzg().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.length() == 0) {
            zzq().zzg().zza("Name is required and can't be empty. Type", str);
            return false;
        }
        int iCodePointAt = str2.codePointAt(0);
        if (!Character.isLetter(iCodePointAt)) {
            zzq().zzg().zza("Name must start with a letter. Type, name", str, str2);
            return false;
        }
        int length = str2.length();
        int iCharCount = Character.charCount(iCodePointAt);
        while (iCharCount < length) {
            int iCodePointAt2 = str2.codePointAt(iCharCount);
            if (iCodePointAt2 != 95 && !Character.isLetterOrDigit(iCodePointAt2)) {
                zzq().zzg().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                return false;
            }
            iCharCount += Character.charCount(iCodePointAt2);
        }
        return true;
    }

    private static boolean zzb(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) != null) {
                if (serviceInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static Bundle zzb(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else {
                int i9 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i9 < parcelableArr.length) {
                        if (parcelableArr[i9] instanceof Bundle) {
                            parcelableArr[i9] = new Bundle((Bundle) parcelableArr[i9]);
                        }
                        i9++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i9 < list.size()) {
                        Object obj2 = list.get(i9);
                        if (obj2 instanceof Bundle) {
                            list.set(i9, new Bundle((Bundle) obj2));
                        }
                        i9++;
                    }
                }
            }
        }
        return bundle2;
    }

    public final boolean zza(String str, String[] strArr, String[] strArr2, String str2) {
        boolean z8;
        if (str2 == null) {
            zzq().zzg().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr3 = zza;
        int length = strArr3.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                z8 = false;
                break;
            }
            if (str2.startsWith(strArr3[i9])) {
                z8 = true;
                break;
            }
            i9++;
        }
        if (z8) {
            zzq().zzg().zza("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr == null || !zza(str2, strArr) || (strArr2 != null && zza(str2, strArr2))) {
            return true;
        }
        zzq().zzg().zza("Name is reserved. Type, name", str, str2);
        return false;
    }

    public static ArrayList<Bundle> zzb(List<zzw> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzw zzwVar : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzwVar.zza);
            bundle.putString("origin", zzwVar.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, zzwVar.zzd);
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzwVar.zzc.zza);
            zzgz.zza(bundle, zzwVar.zzc.zza());
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, zzwVar.zze);
            String str = zzwVar.zzf;
            if (str != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str);
            }
            zzar zzarVar = zzwVar.zzg;
            if (zzarVar != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzarVar.zza);
                zzam zzamVar = zzwVar.zzg.zzb;
                if (zzamVar != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzamVar.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, zzwVar.zzh);
            zzar zzarVar2 = zzwVar.zzi;
            if (zzarVar2 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzarVar2.zza);
                zzam zzamVar2 = zzwVar.zzi.zzb;
                if (zzamVar2 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzamVar2.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, zzwVar.zzc.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, zzwVar.zzj);
            zzar zzarVar3 = zzwVar.zzk;
            if (zzarVar3 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, zzarVar3.zza);
                zzam zzamVar3 = zzwVar.zzk.zzb;
                if (zzamVar3 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzamVar3.zzb());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public final boolean zza(String str, String[] strArr, String str2) {
        return zza(str, strArr, (String[]) null, str2);
    }

    public final boolean zza(String str, int i9, String str2) {
        if (str2 == null) {
            zzq().zzg().zza("Name is required and can't be null. Type", str);
            return false;
        }
        if (str2.codePointCount(0, str2.length()) <= i9) {
            return true;
        }
        zzq().zzg().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i9), str2);
        return false;
    }

    public final int zza(String str, boolean z8) {
        if (!zzb("event", str)) {
            return 2;
        }
        if (z8) {
            if (!zza("event", zzgy.zza, zzgy.zzb, str)) {
                return 13;
            }
        } else if (!zza("event", zzgy.zza, str)) {
            return 13;
        }
        return !zza("event", 40, str) ? 2 : 0;
    }

    public static boolean zza(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    public final boolean zza(String str, String str2, int i9, Object obj) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String strValueOf = String.valueOf(obj);
            if (strValueOf.codePointCount(0, strValueOf.length()) > i9) {
                zzq().zzj().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(strValueOf.length()));
                return false;
            }
        }
        return true;
    }

    public static boolean zza(Bundle bundle, int i9) {
        int i10 = 0;
        if (bundle.size() <= i9) {
            return false;
        }
        for (String str : new TreeSet(bundle.keySet())) {
            i10++;
            if (i10 > i9) {
                bundle.remove(str);
            }
        }
        return true;
    }

    private final void zza(String str, String str2, String str3, Bundle bundle, List<String> list, boolean z8) {
        int iZzg;
        String str4;
        int iZza;
        String string;
        if (bundle == null) {
            return;
        }
        boolean zZza = zzs().zza(zzat.zzcb);
        int iZzd = zZza ? 0 : zzs().zzd();
        int i9 = 0;
        for (String str5 : new TreeSet(bundle.keySet())) {
            if (list == null || !list.contains(str5)) {
                iZzg = z8 ? zzg(str5) : 0;
                if (iZzg == 0) {
                    iZzg = zzh(str5);
                }
            } else {
                iZzg = 0;
            }
            if (iZzg != 0) {
                zza(bundle, iZzg, str5, str5, iZzg == 3 ? str5 : null);
                bundle.remove(str5);
            } else {
                if (zza(bundle.get(str5))) {
                    zzq().zzj().zza("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str2, str3, str5);
                    iZza = 22;
                    str4 = str5;
                } else {
                    str4 = str5;
                    iZza = zza(str, str2, str5, bundle.get(str5), bundle, list, z8, false);
                }
                if (iZza != 0 && !"_ev".equals(str4)) {
                    zza(bundle, iZza, str4, str4, bundle.get(str4));
                    bundle.remove(str4);
                } else if (zza(str4) && (!zZza || !zza(str4, zzhb.zzd))) {
                    int i10 = i9 + 1;
                    if (i10 > iZzd) {
                        if (zZza) {
                            string = "Item cannot contain custom parameters";
                        } else {
                            StringBuilder sb = new StringBuilder(63);
                            sb.append("Child bundles can't contain more than ");
                            sb.append(iZzd);
                            sb.append(" custom params");
                            string = sb.toString();
                        }
                        zzq().zzg().zza(string, zzn().zza(str2), zzn().zza(bundle));
                        zzb(bundle, zZza ? 23 : 5);
                        bundle.remove(str4);
                    }
                    i9 = i10;
                }
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final boolean zza(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (zzi(str)) {
                return true;
            }
            if (this.zzy.zzk()) {
                zzq().zzg().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzex.zza(str));
            }
            return false;
        }
        if (zznq.zzb() && zzs().zza(zzat.zzbj) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (zzi(str2)) {
                return true;
            }
            zzq().zzg().zza("Invalid admob_app_id. Analytics disabled.", zzex.zza(str2));
            return false;
        }
        if (this.zzy.zzk()) {
            zzq().zzg().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
        }
        return false;
    }

    public static boolean zza(String str, String str2, String str3, String str4) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        if (!zIsEmpty && !zIsEmpty2) {
            return !str.equals(str2);
        }
        if (zIsEmpty && zIsEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        }
        if (zIsEmpty || !zIsEmpty2) {
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
        if (TextUtils.isEmpty(str4)) {
            return false;
        }
        return TextUtils.isEmpty(str3) || !str3.equals(str4);
    }

    private final Object zza(int i9, Object obj, boolean z8, boolean z9) {
        Bundle bundleZza;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf(((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf(((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            if (!zzmh.zzb() || !zzs().zza(zzat.zzca) || !zzs().zza(zzat.zzbz) || !z9 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if ((parcelable instanceof Bundle) && (bundleZza = zza((Bundle) parcelable)) != null && !bundleZza.isEmpty()) {
                    arrayList.add(bundleZza);
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
        return zza(String.valueOf(obj), i9, z8);
    }

    public static String zza(String str, int i9, boolean z8) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i9) {
            return str;
        }
        if (z8) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i9))).concat("...");
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0165 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zza(String str, String str2, String str3, Object obj, Bundle bundle, List<String> list, boolean z8, boolean z9) {
        int i9;
        int i10;
        zzc();
        boolean z10 = false;
        if (zzmh.zzb() && zzs().zza(zzat.zzcb)) {
            if (zza(obj)) {
                if (!z9) {
                    return 21;
                }
                if (!zza(str3, zzhb.zzc)) {
                    return 20;
                }
                if (!this.zzy.zzv().zzah()) {
                    return 25;
                }
                if (!zzb("param", str3, 200, obj)) {
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        if (parcelableArr.length > 200) {
                            bundle.putParcelableArray(str3, (Parcelable[]) Arrays.copyOf(parcelableArr, 200));
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        if (arrayList.size() > 200) {
                            bundle.putParcelableArrayList(str3, new ArrayList<>(arrayList.subList(0, 200)));
                        }
                    }
                    i9 = 17;
                }
            }
            if (!zza("param", str3, ((zzs().zze(str, zzat.zzaq) || !zzd(str2)) && !zzd(str3)) ? 100 : 256, obj)) {
                return i9;
            }
            if (!z9) {
                return 4;
            }
            boolean z11 = zzmh.zzb() && zzs().zza(zzat.zzca);
            if (!(obj instanceof Bundle)) {
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr2 = (Parcelable[]) obj;
                    int length = parcelableArr2.length;
                    int i11 = 0;
                    while (i11 < length) {
                        Parcelable parcelable = parcelableArr2[i11];
                        if (!(parcelable instanceof Bundle)) {
                            zzq().zzj().zza("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str3);
                            break;
                        }
                        if (z11) {
                            i10 = i11;
                            zza(str, str2, str3, (Bundle) parcelable, list, z8);
                        } else {
                            i10 = i11;
                        }
                        i11 = i10 + 1;
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList2 = (ArrayList) obj;
                    int size = arrayList2.size();
                    int i12 = 0;
                    while (i12 < size) {
                        Object obj2 = arrayList2.get(i12);
                        int i13 = i12 + 1;
                        if (!(obj2 instanceof Bundle)) {
                            zzq().zzj().zza("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str3);
                            break;
                        }
                        if (z11) {
                            zza(str, str2, str3, (Bundle) obj2, list, z8);
                        }
                        i12 = i13;
                    }
                }
                if (z10) {
                    return 4;
                }
                return i9;
            }
            if (z11) {
                zza(str, str2, str3, (Bundle) obj, list, z8);
            }
            z10 = true;
            if (z10) {
            }
        } else if (z9 && !zzb("param", str3, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, obj)) {
            return 17;
        }
        i9 = 0;
        if (!zza("param", str3, ((zzs().zze(str, zzat.zzaq) || !zzd(str2)) && !zzd(str3)) ? 100 : 256, obj)) {
        }
    }

    public final Object zza(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(256, obj, true, true);
        }
        return zza(zzd(str) ? 256 : 100, obj, false, true);
    }

    public final Bundle zza(String str, String str2, Bundle bundle, List<String> list, boolean z8, boolean z9) {
        Set<String> setKeySet;
        int iZzg;
        int i9;
        Bundle bundle2;
        zzkx zzkxVar = this;
        List<String> list2 = list;
        boolean z10 = zzmh.zzb() && zzs().zza(zzat.zzcb);
        boolean zZza = z10 ? zza(str2, zzgy.zzd) : z9;
        if (bundle == null) {
            return null;
        }
        Bundle bundle3 = new Bundle(bundle);
        int iZzd = zzs().zzd();
        if (zzs().zze(str, zzat.zzaz)) {
            setKeySet = new TreeSet<>(bundle.keySet());
        } else {
            setKeySet = bundle.keySet();
        }
        int i10 = 0;
        for (String str3 : setKeySet) {
            if (list2 == null || !list2.contains(str3)) {
                iZzg = z8 ? zzkxVar.zzg(str3) : 0;
                if (iZzg == 0) {
                    iZzg = zzkxVar.zzh(str3);
                }
            } else {
                iZzg = 0;
            }
            if (iZzg != 0) {
                zza(bundle3, iZzg, str3, str3, iZzg == 3 ? str3 : null);
                bundle3.remove(str3);
                i9 = iZzd;
                bundle2 = bundle3;
            } else {
                i9 = iZzd;
                bundle2 = bundle3;
                int iZza = zza(str, str2, str3, bundle.get(str3), bundle3, list, z8, zZza);
                if (z10 && iZza == 17) {
                    zza(bundle2, iZza, str3, str3, Boolean.FALSE);
                } else if (iZza != 0 && !"_ev".equals(str3)) {
                    zza(bundle2, iZza, iZza == 21 ? str2 : str3, str3, bundle.get(str3));
                    bundle2.remove(str3);
                }
                if (zza(str3)) {
                    int i11 = i10 + 1;
                    if (i11 > i9) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Event can't contain more than ");
                        sb.append(i9);
                        sb.append(" params");
                        zzq().zzg().zza(sb.toString(), zzn().zza(str2), zzn().zza(bundle));
                        zzb(bundle2, 5);
                        bundle2.remove(str3);
                        i10 = i11;
                    } else {
                        i10 = i11;
                    }
                }
                iZzd = i9;
                bundle3 = bundle2;
                zzkxVar = this;
                list2 = list;
            }
            iZzd = i9;
            bundle3 = bundle2;
            zzkxVar = this;
            list2 = list;
        }
        return bundle3;
    }

    public final void zza(zzfb zzfbVar, int i9) {
        int i10 = 0;
        for (String str : new TreeSet(zzfbVar.zzb.keySet())) {
            if (zza(str) && (i10 = i10 + 1) > i9) {
                StringBuilder sb = new StringBuilder(48);
                sb.append("Event can't contain more than ");
                sb.append(i9);
                sb.append(" params");
                zzq().zzg().zza(sb.toString(), zzn().zza(zzfbVar.zza), zzn().zza(zzfbVar.zzb));
                zzb(zzfbVar.zzb, 5);
                zzfbVar.zzb.remove(str);
            }
        }
    }

    public final void zza(Bundle bundle, Bundle bundle2) {
        if (bundle2 == null) {
            return;
        }
        for (String str : bundle2.keySet()) {
            if (!bundle.containsKey(str)) {
                zzo().zza(bundle, str, bundle2.get(str));
            }
        }
    }

    private static void zza(Bundle bundle, int i9, String str, String str2, Object obj) {
        if (zzb(bundle, i9)) {
            bundle.putString("_ev", zza(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", String.valueOf(obj).length());
                }
            }
        }
    }

    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle == null) {
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof String) {
            bundle.putString(str, String.valueOf(obj));
            return;
        }
        if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
            return;
        }
        if (zzmh.zzb() && zzs().zza(zzat.zzca) && zzs().zza(zzat.zzbz) && (obj instanceof Bundle[])) {
            bundle.putParcelableArray(str, (Bundle[]) obj);
        } else if (str != null) {
            zzq().zzj().zza("Not putting event parameter. Invalid value type. name, type", zzn().zzb(str), obj != null ? obj.getClass().getSimpleName() : null);
        }
    }

    public final void zza(int i9, String str, String str2, int i10) {
        zza((String) null, i9, str, str2, i10);
    }

    public final void zza(String str, int i9, String str2, String str3, int i10) {
        Bundle bundle = new Bundle();
        zzb(bundle, i9);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i9 == 6 || i9 == 7 || i9 == 2) {
            bundle.putLong("_el", i10);
        }
        this.zzy.zzg().zza("auto", "_err", bundle);
    }

    @VisibleForTesting
    public static long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i9 = 0;
        Preconditions.checkState(bArr.length > 0);
        long j9 = 0;
        for (int length = bArr.length - 1; length >= 0 && length >= bArr.length - 8; length--) {
            j9 += (bArr[length] & 255) << i9;
            i9 += 8;
        }
        return j9;
    }

    public static boolean zza(Context context, boolean z8) {
        Preconditions.checkNotNull(context);
        return zzb(context, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    public static boolean zza(Boolean bool, Boolean bool2) {
        if (bool == null && bool2 == null) {
            return true;
        }
        if (bool == null) {
            return false;
        }
        return bool.equals(bool2);
    }

    public static boolean zza(List<String> list, List<String> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list2);
    }

    public final Bundle zza(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object objZza = zza(str, bundle.get(str));
                if (objZza == null) {
                    zzq().zzj().zza("Param value can't be null", zzn().zzb(str));
                } else {
                    zza(bundle2, str, objZza);
                }
            }
        }
        return bundle2;
    }

    public final zzar zza(String str, String str2, Bundle bundle, String str3, long j9, boolean z8, boolean z9, boolean z10) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zza(str2, z10) == 0) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            bundle2.putString("_o", str3);
            return new zzar(str2, new zzam(zza(zza(str, str2, bundle2, CollectionUtils.listOf("_o"), false, false))), str3, j9);
        }
        zzq().zze().zza("Invalid conditional property event name", zzn().zzc(str2));
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0072  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0065 -> B:18:0x0072). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final long zza(Context context, String str) throws NoSuchAlgorithmException {
        zzc();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest messageDigestZzh = zzh();
        long jZza = -1;
        if (messageDigestZzh == null) {
            zzq().zze().zza("Could not get MD5 instance");
        } else if (packageManager != null) {
            try {
            } catch (PackageManager.NameNotFoundException e9) {
                zzq().zze().zza("Package name not found", e9);
            }
            if (zzc(context, str)) {
                jZza = 0;
            } else {
                Signature[] signatureArr = Wrappers.packageManager(context).getPackageInfo(zzm().getPackageName(), 64).signatures;
                if (signatureArr != null && signatureArr.length > 0) {
                    jZza = zza(messageDigestZzh.digest(signatureArr[0].toByteArray()));
                } else {
                    zzq().zzh().zza("Could not get signatures");
                }
            }
        }
        return jZza;
    }

    public static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            return parcelObtain.marshall();
        } finally {
            parcelObtain.recycle();
        }
    }

    private static boolean zza(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String str2 : strArr) {
            if (zzc(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public final int zza(int i9) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzm(), 12451000);
    }

    public static long zza(long j9, long j10) {
        return (j9 + (j10 * 60000)) / DateUtils.MILLIS_PER_DAY;
    }

    public final void zza(Bundle bundle, long j9) {
        long j10 = bundle.getLong("_et");
        if (j10 != 0) {
            zzq().zzh().zza("Params already contained engagement", Long.valueOf(j10));
        }
        bundle.putLong("_et", j9 + j10);
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning string value to wrapper", e9);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, long j9) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j9);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning long value to wrapper", e9);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, int i9) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i9);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning int value to wrapper", e9);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning byte array to wrapper", e9);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, boolean z8) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z8);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning boolean value to wrapper", e9);
        }
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, Bundle bundle) {
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning bundle value to wrapper", e9);
        }
    }

    public static Bundle zza(List<zzkw> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzkw zzkwVar : list) {
            String str = zzkwVar.zzd;
            if (str != null) {
                bundle.putString(zzkwVar.zza, str);
            } else {
                Long l9 = zzkwVar.zzc;
                if (l9 != null) {
                    bundle.putLong(zzkwVar.zza, l9.longValue());
                } else {
                    Double d9 = zzkwVar.zzf;
                    if (d9 != null) {
                        bundle.putDouble(zzkwVar.zza, d9.doubleValue());
                    }
                }
            }
        }
        return bundle;
    }

    public final void zza(com.google.android.gms.internal.measurement.zzw zzwVar, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzwVar.zza(bundle);
        } catch (RemoteException e9) {
            this.zzy.zzq().zzh().zza("Error returning bundle list to wrapper", e9);
        }
    }

    public final URL zza(long j9, String str, String str2, long j10) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String strConcat = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", Long.valueOf(j9), Integer.valueOf(zzi())), str2, str, Long.valueOf(j10));
            if (str.equals(zzs().zzv())) {
                strConcat = strConcat.concat("&ddl_test=1");
            }
            return new URL(strConcat);
        } catch (IllegalArgumentException | MalformedURLException e9) {
            zzq().zze().zza("Failed to create BOW URL for Deferred Deep Link. exception", e9.getMessage());
            return null;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    public final boolean zza(String str, double d9) {
        try {
            SharedPreferences.Editor editorEdit = zzm().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            editorEdit.putString("deeplink", str);
            editorEdit.putLong("timestamp", Double.doubleToRawLongBits(d9));
            return editorEdit.commit();
        } catch (Exception e9) {
            zzq().zze().zza("Failed to persist Deferred Deep Link. exception", e9);
            return false;
        }
    }

    public static long zza(zzam zzamVar) {
        long length = 0;
        if (zzamVar == null) {
            return 0L;
        }
        Iterator<String> it = zzamVar.iterator();
        while (it.hasNext()) {
            if (zzamVar.zza(it.next()) instanceof Parcelable[]) {
                length += ((Parcelable[]) r3).length;
            }
        }
        return length;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }
}
