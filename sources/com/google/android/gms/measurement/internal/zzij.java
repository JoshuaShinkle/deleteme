package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzij implements Runnable {
    private final URL zza;
    private final byte[] zzb;
    private final zzig zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzih zzf;

    public zzij(zzih zzihVar, String str, URL url, byte[] bArr, Map<String, String> map, zzig zzigVar) {
        this.zzf = zzihVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzigVar);
        this.zza = url;
        this.zzb = null;
        this.zzc = zzigVar;
        this.zzd = str;
        this.zze = null;
    }

    private final void zzb(final int i9, final Exception exc, final byte[] bArr, final Map<String, List<String>> map) {
        this.zzf.zzp().zza(new Runnable(this, i9, exc, bArr, map) { // from class: com.google.android.gms.measurement.internal.zzii
            private final zzij zza;
            private final int zzb;
            private final Exception zzc;
            private final byte[] zzd;
            private final Map zze;

            {
                this.zza = this;
                this.zzb = i9;
                this.zzc = exc;
                this.zzd = bArr;
                this.zze = map;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        HttpURLConnection httpURLConnectionZza;
        Map<String, List<String>> headerFields;
        this.zzf.zzb();
        int responseCode = 0;
        try {
            httpURLConnectionZza = this.zzf.zza(this.zza);
            try {
                responseCode = httpURLConnectionZza.getResponseCode();
                headerFields = httpURLConnectionZza.getHeaderFields();
                try {
                    zzih zzihVar = this.zzf;
                    byte[] bArrZza = zzih.zza(httpURLConnectionZza);
                    httpURLConnectionZza.disconnect();
                    zzb(responseCode, null, bArrZza, headerFields);
                } catch (IOException e9) {
                    e = e9;
                    if (httpURLConnectionZza != null) {
                        httpURLConnectionZza.disconnect();
                    }
                    zzb(responseCode, e, null, headerFields);
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnectionZza != null) {
                        httpURLConnectionZza.disconnect();
                    }
                    zzb(responseCode, null, null, headerFields);
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                headerFields = null;
            } catch (Throwable th2) {
                th = th2;
                headerFields = null;
            }
        } catch (IOException e11) {
            e = e11;
            httpURLConnectionZza = null;
            headerFields = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnectionZza = null;
            headerFields = null;
        }
    }

    public final /* synthetic */ void zza(int i9, Exception exc, byte[] bArr, Map map) {
        this.zzc.zza(this.zzd, i9, exc, bArr, map);
    }
}
