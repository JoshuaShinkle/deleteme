package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzor;
import com.google.android.gms.internal.gtm.zzpc;
import com.google.android.gms.internal.gtm.zzpd;
import com.google.android.gms.internal.gtm.zzpe;
import com.google.android.gms.internal.gtm.zzuw;
import com.google.android.gms.tagmanager.zzeh;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
final class zzer implements Runnable {
    private final String zzaec;
    private volatile String zzafd;
    private final zzpd zzajd;
    private final String zzaje;
    private zzdh<com.google.android.gms.internal.gtm.zzk> zzajf;
    private volatile zzal zzajg;
    private volatile String zzajh;
    private final Context zzrm;

    public zzer(Context context, String str, zzal zzalVar) {
        this(context, str, new zzpd(), zzalVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z8;
        InputStream inputStreamZzcj;
        zzdh<com.google.android.gms.internal.gtm.zzk> zzdhVar = this.zzajf;
        if (zzdhVar == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        zzdhVar.zzhj();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzrm.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzdi.zzab("...no network connectivity");
            z8 = false;
        } else {
            z8 = true;
        }
        if (!z8) {
            this.zzajf.zzs(zzcz.zzaht);
            return;
        }
        zzdi.zzab("Start loading resource from network ...");
        String strZzhq = this.zzajg.zzhq();
        String str = this.zzafd;
        StringBuilder sb = new StringBuilder(String.valueOf(strZzhq).length() + 12 + String.valueOf(str).length());
        sb.append(strZzhq);
        sb.append(str);
        sb.append("&v=a65833898");
        String string = sb.toString();
        if (this.zzajh != null && !this.zzajh.trim().equals("")) {
            String strValueOf = String.valueOf(string);
            String str2 = this.zzajh;
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 4 + String.valueOf(str2).length());
            sb2.append(strValueOf);
            sb2.append("&pv=");
            sb2.append(str2);
            string = sb2.toString();
        }
        if (zzeh.zziy().zziz().equals(zzeh.zza.CONTAINER_DEBUG)) {
            String strValueOf2 = String.valueOf(string);
            string = "&gtm_debug=x".length() != 0 ? strValueOf2.concat("&gtm_debug=x") : new String(strValueOf2);
        }
        zzpc zzpcVarZzmt = zzpd.zzmt();
        try {
            try {
                try {
                    try {
                        inputStreamZzcj = zzpcVarZzmt.zzcj(string);
                    } catch (zzpe unused) {
                        String strValueOf3 = String.valueOf(string);
                        zzdi.zzac(strValueOf3.length() != 0 ? "Error when loading resource for url: ".concat(strValueOf3) : new String("Error when loading resource for url: "));
                        this.zzajf.zzs(zzcz.zzahw);
                        inputStreamZzcj = null;
                    }
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        zzor.zza(inputStreamZzcj, byteArrayOutputStream);
                        com.google.android.gms.internal.gtm.zzk zzkVar = (com.google.android.gms.internal.gtm.zzk) zzuw.zza(new com.google.android.gms.internal.gtm.zzk(), byteArrayOutputStream.toByteArray());
                        String strValueOf4 = String.valueOf(zzkVar);
                        StringBuilder sb3 = new StringBuilder(strValueOf4.length() + 43);
                        sb3.append("Successfully loaded supplemented resource: ");
                        sb3.append(strValueOf4);
                        zzdi.zzab(sb3.toString());
                        if (zzkVar.zzqk == null && zzkVar.zzqj.length == 0) {
                            String strValueOf5 = String.valueOf(this.zzaec);
                            zzdi.zzab(strValueOf5.length() != 0 ? "No change for container: ".concat(strValueOf5) : new String("No change for container: "));
                        }
                        this.zzajf.zze(zzkVar);
                        zzpcVarZzmt.close();
                        zzdi.zzab("Load resource from network finished.");
                    } catch (IOException e9) {
                        String message = e9.getMessage();
                        StringBuilder sb4 = new StringBuilder(String.valueOf(string).length() + 51 + String.valueOf(message).length());
                        sb4.append("Error when parsing downloaded resources from url: ");
                        sb4.append(string);
                        sb4.append(StringUtils.SPACE);
                        sb4.append(message);
                        zzdi.zzb(sb4.toString(), e9);
                        this.zzajf.zzs(zzcz.zzahv);
                        zzpcVarZzmt.close();
                    }
                } catch (IOException e10) {
                    String message2 = e10.getMessage();
                    StringBuilder sb5 = new StringBuilder(String.valueOf(string).length() + 40 + String.valueOf(message2).length());
                    sb5.append("Error when loading resources from url: ");
                    sb5.append(string);
                    sb5.append(StringUtils.SPACE);
                    sb5.append(message2);
                    zzdi.zzb(sb5.toString(), e10);
                    this.zzajf.zzs(zzcz.zzahu);
                    zzpcVarZzmt.close();
                }
            } catch (FileNotFoundException unused2) {
                String str3 = this.zzaec;
                StringBuilder sb6 = new StringBuilder(String.valueOf(string).length() + 79 + String.valueOf(str3).length());
                sb6.append("No data is retrieved from the given url: ");
                sb6.append(string);
                sb6.append(". Make sure container_id: ");
                sb6.append(str3);
                sb6.append(" is correct.");
                zzdi.zzac(sb6.toString());
                this.zzajf.zzs(zzcz.zzahv);
                zzpcVarZzmt.close();
            }
        } catch (Throwable th) {
            zzpcVarZzmt.close();
            throw th;
        }
    }

    public final void zza(zzdh<com.google.android.gms.internal.gtm.zzk> zzdhVar) {
        this.zzajf = zzdhVar;
    }

    @VisibleForTesting
    public final void zzap(String str) {
        if (str == null) {
            this.zzafd = this.zzaje;
        } else {
            zzdi.zzax(str.length() != 0 ? "Setting CTFE URL path: ".concat(str) : new String("Setting CTFE URL path: "));
            this.zzafd = str;
        }
    }

    @VisibleForTesting
    public final void zzbi(String str) {
        String strValueOf = String.valueOf(str);
        zzdi.zzax(strValueOf.length() != 0 ? "Setting previous container version: ".concat(strValueOf) : new String("Setting previous container version: "));
        this.zzajh = str;
    }

    @VisibleForTesting
    private zzer(Context context, String str, zzpd zzpdVar, zzal zzalVar) {
        this.zzrm = context;
        this.zzajd = zzpdVar;
        this.zzaec = str;
        this.zzajg = zzalVar;
        String strValueOf = String.valueOf(str);
        String strConcat = strValueOf.length() != 0 ? "/r?id=".concat(strValueOf) : new String("/r?id=");
        this.zzaje = strConcat;
        this.zzafd = strConcat;
        this.zzajh = null;
    }
}
