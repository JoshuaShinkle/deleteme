package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
final class zzfu implements zzbe {
    private final String zzabp;
    private final zzfx zzalb;
    private final zzfw zzalc;
    private final Context zzrm;

    @VisibleForTesting
    private zzfu(zzfx zzfxVar, Context context, zzfw zzfwVar) {
        this.zzalb = zzfxVar;
        this.zzrm = context.getApplicationContext();
        this.zzalc = zzfwVar;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String string = null;
        if (locale != null && locale.getLanguage() != null && locale.getLanguage().length() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && locale.getCountry().length() != 0) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            string = sb.toString();
        }
        this.zzabp = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleTagManager", "4.00", str, string, Build.MODEL, Build.ID);
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final void zzd(List<zzbw> list) throws IOException {
        int iMin = Math.min(list.size(), 40);
        boolean z8 = true;
        for (int i9 = 0; i9 < iMin; i9++) {
            zzbw zzbwVar = list.get(i9);
            URL urlZzd = zzd(zzbwVar);
            if (urlZzd == null) {
                zzdi.zzac("No destination: discarding hit.");
                this.zzalc.zzb(zzbwVar);
            } else {
                try {
                    HttpURLConnection httpURLConnectionZzc = this.zzalb.zzc(urlZzd);
                    InputStream inputStream = null;
                    if (z8) {
                        try {
                            zzdn.zzn(this.zzrm);
                            z8 = false;
                        } catch (Throwable th) {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            httpURLConnectionZzc.disconnect();
                            throw th;
                        }
                    }
                    httpURLConnectionZzc.setRequestProperty(HttpHeaders.USER_AGENT, this.zzabp);
                    int responseCode = httpURLConnectionZzc.getResponseCode();
                    inputStream = httpURLConnectionZzc.getInputStream();
                    if (responseCode != 200) {
                        StringBuilder sb = new StringBuilder(25);
                        sb.append("Bad response: ");
                        sb.append(responseCode);
                        zzdi.zzac(sb.toString());
                        this.zzalc.zzc(zzbwVar);
                    } else {
                        this.zzalc.zza(zzbwVar);
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    httpURLConnectionZzc.disconnect();
                } catch (IOException e9) {
                    String simpleName = e9.getClass().getSimpleName();
                    zzdi.zzac(simpleName.length() != 0 ? "Exception sending hit: ".concat(simpleName) : new String("Exception sending hit: "));
                    zzdi.zzac(e9.getMessage());
                    this.zzalc.zzc(zzbwVar);
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final boolean zzhy() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzrm.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdi.zzab("...no network connectivity");
        return false;
    }

    @VisibleForTesting
    public zzfu(Context context, zzfw zzfwVar) {
        this(new zzfv(), context, zzfwVar);
    }

    @VisibleForTesting
    private static URL zzd(zzbw zzbwVar) {
        try {
            return new URL(zzbwVar.zzij());
        } catch (MalformedURLException unused) {
            zzdi.zzav("Error trying to parse the GTM url.");
            return null;
        }
    }
}
