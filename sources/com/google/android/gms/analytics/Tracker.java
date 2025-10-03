package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzan;
import com.google.android.gms.internal.gtm.zzap;
import com.google.android.gms.internal.gtm.zzcg;
import com.google.android.gms.internal.gtm.zzcy;
import com.google.android.gms.internal.gtm.zzcz;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@VisibleForTesting
/* loaded from: classes2.dex */
public class Tracker extends zzan {
    private boolean zztb;
    private final Map<String, String> zztc;
    private final Map<String, String> zztd;
    private final zzcg zzte;
    private final zza zztf;
    private ExceptionReporter zztg;
    private zzcy zzth;

    public class zza extends zzan implements GoogleAnalytics.zza {
        private boolean zztq;
        private int zztr;
        private long zzts;
        private boolean zztt;
        private long zztu;

        public zza(zzap zzapVar) {
            super(zzapVar);
            this.zzts = -1L;
        }

        private final void zzay() {
            if (this.zzts >= 0 || this.zztq) {
                zzcr().zza(Tracker.this.zztf);
            } else {
                zzcr().zzb(Tracker.this.zztf);
            }
        }

        public final void enableAutoActivityTracking(boolean z8) {
            this.zztq = z8;
            zzay();
        }

        public final void setSessionTimeout(long j9) {
            this.zzts = j9;
            zzay();
        }

        @Override // com.google.android.gms.internal.gtm.zzan
        public final void zzaw() {
        }

        public final synchronized boolean zzax() {
            boolean z8;
            z8 = this.zztt;
            this.zztt = false;
            return z8;
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzc(Activity activity) {
            String canonicalName;
            if (this.zztr == 0) {
                if (zzcn().elapsedRealtime() >= this.zztu + Math.max(1000L, this.zzts)) {
                    this.zztt = true;
                }
            }
            this.zztr++;
            if (this.zztq) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap map = new HashMap();
                map.put("&t", "screenview");
                Tracker tracker = Tracker.this;
                if (tracker.zzth != null) {
                    zzcy zzcyVar = Tracker.this.zzth;
                    canonicalName = activity.getClass().getCanonicalName();
                    String str = zzcyVar.zzacs.get(canonicalName);
                    if (str != null) {
                        canonicalName = str;
                    }
                } else {
                    canonicalName = activity.getClass().getCanonicalName();
                }
                tracker.set("&cd", canonicalName);
                if (TextUtils.isEmpty((CharSequence) map.get("&dr"))) {
                    Preconditions.checkNotNull(activity);
                    Intent intent2 = activity.getIntent();
                    String str2 = null;
                    if (intent2 != null) {
                        String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                        if (!TextUtils.isEmpty(stringExtra)) {
                            str2 = stringExtra;
                        }
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        map.put("&dr", str2);
                    }
                }
                Tracker.this.send(map);
            }
        }

        @Override // com.google.android.gms.analytics.GoogleAnalytics.zza
        public final void zzd(Activity activity) {
            int i9 = this.zztr - 1;
            this.zztr = i9;
            int iMax = Math.max(0, i9);
            this.zztr = iMax;
            if (iMax == 0) {
                this.zztu = zzcn().elapsedRealtime();
            }
        }
    }

    public Tracker(zzap zzapVar, String str, zzcg zzcgVar) {
        super(zzapVar);
        HashMap map = new HashMap();
        this.zztc = map;
        this.zztd = new HashMap();
        if (str != null) {
            map.put("&tid", str);
        }
        map.put("useSecure", "1");
        map.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.zzte = new zzcg("tracking", zzcn());
        this.zztf = new zza(zzapVar);
    }

    public void enableAdvertisingIdCollection(boolean z8) {
        this.zztb = z8;
    }

    public void enableAutoActivityTracking(boolean z8) {
        this.zztf.enableAutoActivityTracking(z8);
    }

    public void enableExceptionReporting(boolean z8) {
        synchronized (this) {
            ExceptionReporter exceptionReporter = this.zztg;
            if ((exceptionReporter != null) == z8) {
                return;
            }
            if (z8) {
                ExceptionReporter exceptionReporter2 = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), getContext());
                this.zztg = exceptionReporter2;
                Thread.setDefaultUncaughtExceptionHandler(exceptionReporter2);
                zzq("Uncaught exceptions will be reported to Google Analytics");
            } else {
                Thread.setDefaultUncaughtExceptionHandler(exceptionReporter.zzaf());
                zzq("Uncaught exceptions will not be reported to Google Analytics");
            }
        }
    }

    public String get(String str) {
        zzdb();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zztc.containsKey(str)) {
            return this.zztc.get(str);
        }
        if (str.equals("&ul")) {
            return zzcz.zza(Locale.getDefault());
        }
        if (str.equals("&cid")) {
            return zzcw().zzeh();
        }
        if (str.equals("&sr")) {
            return zzcz().zzfb();
        }
        if (str.equals("&aid")) {
            return zzcy().zzdv().zzbb();
        }
        if (str.equals("&an")) {
            return zzcy().zzdv().zzaz();
        }
        if (str.equals("&av")) {
            return zzcy().zzdv().zzba();
        }
        if (str.equals("&aiid")) {
            return zzcy().zzdv().zzbc();
        }
        return null;
    }

    public void send(Map<String, String> map) {
        long jCurrentTimeMillis = zzcn().currentTimeMillis();
        if (zzcr().getAppOptOut()) {
            zzr("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean zIsDryRunEnabled = zzcr().isDryRunEnabled();
        HashMap map2 = new HashMap();
        zza(this.zztc, map2);
        zza(map, map2);
        int i9 = 1;
        boolean zZzb = zzcz.zzb(this.zztc.get("useSecure"), true);
        Map<String, String> map3 = this.zztd;
        Preconditions.checkNotNull(map2);
        if (map3 != null) {
            for (Map.Entry<String, String> entry : map3.entrySet()) {
                String strZza = zza(entry);
                if (strZza != null && !map2.containsKey(strZza)) {
                    map2.put(strZza, entry.getValue());
                }
            }
        }
        this.zztd.clear();
        String str = map2.get("t");
        if (TextUtils.isEmpty(str)) {
            zzco().zza(map2, "Missing hit type parameter");
            return;
        }
        String str2 = map2.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzco().zza(map2, "Missing tracking id parameter");
            return;
        }
        boolean z8 = this.zztb;
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int i10 = Integer.parseInt(this.zztc.get("&a")) + 1;
                if (i10 < Integer.MAX_VALUE) {
                    i9 = i10;
                }
                this.zztc.put("&a", Integer.toString(i9));
            }
        }
        zzcq().zza(new zzp(this, map2, z8, str, jCurrentTimeMillis, zIsDryRunEnabled, zZzb, str2));
    }

    public void set(String str, String str2) {
        Preconditions.checkNotNull(str, "Key should be non-null");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zztc.put(str, str2);
    }

    public void setAnonymizeIp(boolean z8) {
        set("&aip", zzcz.zzc(z8));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri == null || uri.isOpaque()) {
            return;
        }
        String queryParameter = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        String strValueOf = String.valueOf(queryParameter);
        Uri uri2 = Uri.parse(strValueOf.length() != 0 ? "http://hostname/?".concat(strValueOf) : new String("http://hostname/?"));
        String queryParameter2 = uri2.getQueryParameter("utm_id");
        if (queryParameter2 != null) {
            this.zztd.put("&ci", queryParameter2);
        }
        String queryParameter3 = uri2.getQueryParameter("anid");
        if (queryParameter3 != null) {
            this.zztd.put("&anid", queryParameter3);
        }
        String queryParameter4 = uri2.getQueryParameter("utm_campaign");
        if (queryParameter4 != null) {
            this.zztd.put("&cn", queryParameter4);
        }
        String queryParameter5 = uri2.getQueryParameter("utm_content");
        if (queryParameter5 != null) {
            this.zztd.put("&cc", queryParameter5);
        }
        String queryParameter6 = uri2.getQueryParameter("utm_medium");
        if (queryParameter6 != null) {
            this.zztd.put("&cm", queryParameter6);
        }
        String queryParameter7 = uri2.getQueryParameter("utm_source");
        if (queryParameter7 != null) {
            this.zztd.put("&cs", queryParameter7);
        }
        String queryParameter8 = uri2.getQueryParameter("utm_term");
        if (queryParameter8 != null) {
            this.zztd.put("&ck", queryParameter8);
        }
        String queryParameter9 = uri2.getQueryParameter("dclid");
        if (queryParameter9 != null) {
            this.zztd.put("&dclid", queryParameter9);
        }
        String queryParameter10 = uri2.getQueryParameter("gclid");
        if (queryParameter10 != null) {
            this.zztd.put("&gclid", queryParameter10);
        }
        String queryParameter11 = uri2.getQueryParameter(FirebaseAnalytics.Param.ACLID);
        if (queryParameter11 != null) {
            this.zztd.put("&aclid", queryParameter11);
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d9) {
        set("&sf", Double.toString(d9));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i9, int i10) {
        if (i9 < 0 && i10 < 0) {
            zzt("Invalid width or height. The values should be non-negative.");
            return;
        }
        StringBuilder sb = new StringBuilder(23);
        sb.append(i9);
        sb.append("x");
        sb.append(i10);
        set("&sr", sb.toString());
    }

    public void setSessionTimeout(long j9) {
        this.zztf.setSessionTimeout(j9 * 1000);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z8) {
        set("useSecure", zzcz.zzc(z8));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    public final void zza(zzcy zzcyVar) {
        zzq("Loading Tracker config values");
        this.zzth = zzcyVar;
        String str = zzcyVar.zzacm;
        if (str != null) {
            set("&tid", str);
            zza("trackingId loaded", str);
        }
        double d9 = this.zzth.zzacn;
        if (d9 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            String string = Double.toString(d9);
            set("&sf", string);
            zza("Sample frequency loaded", string);
        }
        int i9 = this.zzth.zzaco;
        if (i9 >= 0) {
            setSessionTimeout(i9);
            zza("Session timeout loaded", Integer.valueOf(i9));
        }
        int i10 = this.zzth.zzacp;
        if (i10 != -1) {
            boolean z8 = i10 == 1;
            enableAutoActivityTracking(z8);
            zza("Auto activity tracking loaded", Boolean.valueOf(z8));
        }
        int i11 = this.zzth.zzacq;
        if (i11 != -1) {
            boolean z9 = i11 == 1;
            if (z9) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(z9));
        }
        enableExceptionReporting(this.zzth.zzacr == 1);
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
        this.zztf.zzag();
        String strZzaz = zzcu().zzaz();
        if (strZzaz != null) {
            set("&an", strZzaz);
        }
        String strZzba = zzcu().zzba();
        if (strZzba != null) {
            set("&av", strZzba);
        }
    }

    private static String zza(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (key.startsWith("&") && key.length() >= 2) {
            return entry.getKey().substring(1);
        }
        return null;
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        Preconditions.checkNotNull(map2);
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strZza = zza(entry);
            if (strZza != null) {
                map2.put(strZza, entry.getValue());
            }
        }
    }
}
