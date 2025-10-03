package com.google.android.gms.internal.gtm;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes2.dex */
final class zzck extends zzan {
    private static final byte[] zzabr = "\n".getBytes();
    private final String zzabp;
    private final zzcv zzabq;

    public zzck(zzap zzapVar) {
        super(zzapVar);
        this.zzabp = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleAnalytics", zzao.VERSION, Build.VERSION.RELEASE, zzcz.zza(Locale.getDefault()), Build.MODEL, Build.ID);
        this.zzabq = new zzcv(zzapVar.zzcn());
    }

    private final int zza(URL url) {
        Preconditions.checkNotNull(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnectionZzb = null;
        try {
            try {
                httpURLConnectionZzb = zzb(url);
                httpURLConnectionZzb.connect();
                zza(httpURLConnectionZzb);
                int responseCode = httpURLConnectionZzb.getResponseCode();
                if (responseCode == 200) {
                    zzcs().zzcl();
                }
                zzb("GET status", Integer.valueOf(responseCode));
                httpURLConnectionZzb.disconnect();
                return responseCode;
            } catch (IOException e9) {
                zzd("Network GET connection error", e9);
                if (httpURLConnectionZzb == null) {
                    return 0;
                }
                httpURLConnectionZzb.disconnect();
                return 0;
            }
        } catch (Throwable th) {
            if (httpURLConnectionZzb != null) {
                httpURLConnectionZzb.disconnect();
            }
            throw th;
        }
    }

    private final URL zzd(zzcd zzcdVar) {
        String str;
        String strConcat;
        if (zzcdVar.zzfj()) {
            String strValueOf = String.valueOf(zzbq.zzet());
            String strValueOf2 = String.valueOf(zzbq.zzev());
            if (strValueOf2.length() != 0) {
                strConcat = strValueOf.concat(strValueOf2);
            } else {
                str = new String(strValueOf);
                strConcat = str;
            }
        } else {
            String strValueOf3 = String.valueOf(zzbq.zzeu());
            String strValueOf4 = String.valueOf(zzbq.zzev());
            if (strValueOf4.length() != 0) {
                strConcat = strValueOf3.concat(strValueOf4);
            } else {
                str = new String(strValueOf3);
                strConcat = str;
            }
        }
        try {
            return new URL(strConcat);
        } catch (MalformedURLException e9) {
            zze("Error trying to parse the hardcoded host url", e9);
            return null;
        }
    }

    private final URL zzfs() {
        String strValueOf = String.valueOf(zzbq.zzet());
        String strValueOf2 = String.valueOf(zzby.zzzs.get());
        try {
            return new URL(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        } catch (MalformedURLException e9) {
            zze("Error trying to parse the hardcoded host url", e9);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() {
        zza("Network initialized. User agent", this.zzabp);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01b3 A[EDGE_INSN: B:75:0x01b3->B:72:0x01b3 BREAK  A[LOOP:1: B:42:0x010f->B:78:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<Long> zzb(List<zzcd> list) {
        boolean z8;
        boolean z9;
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        Preconditions.checkNotNull(list);
        if (!zzcp().zzew().isEmpty() && this.zzabq.zzj(zzby.zzaab.get().intValue() * 1000)) {
            z8 = zzbg.zzz(zzby.zzzu.get()) != zzbg.NONE;
            boolean z10 = zzbm.zzaa(zzby.zzzv.get()) == zzbm.GZIP;
            if (!z8) {
                Preconditions.checkArgument(!list.isEmpty());
                zza("Uploading batched hits. compression, count", Boolean.valueOf(z10), Integer.valueOf(list.size()));
                zzcl zzclVar = new zzcl(this);
                ArrayList arrayList = new ArrayList();
                for (zzcd zzcdVar : list) {
                    if (!zzclVar.zze(zzcdVar)) {
                        break;
                    }
                    arrayList.add(Long.valueOf(zzcdVar.zzfg()));
                }
                if (zzclVar.zzfu() == 0) {
                    return arrayList;
                }
                URL urlZzfs = zzfs();
                if (urlZzfs == null) {
                    zzu("Failed to build batching endpoint url");
                } else {
                    int iZzb = z10 ? zzb(urlZzfs, zzclVar.getPayload()) : zza(urlZzfs, zzclVar.getPayload());
                    if (200 == iZzb) {
                        zza("Batched upload completed. Hits batched", Integer.valueOf(zzclVar.zzfu()));
                        return arrayList;
                    }
                    zza("Network error uploading hits. status code", Integer.valueOf(iZzb));
                    if (zzcp().zzew().contains(Integer.valueOf(iZzb))) {
                        zzt("Server instructed the client to stop batching");
                        this.zzabq.start();
                    }
                }
                return Collections.emptyList();
            }
            ArrayList arrayList2 = new ArrayList(list.size());
            for (zzcd zzcdVar2 : list) {
                Preconditions.checkNotNull(zzcdVar2);
                String strZza = zza(zzcdVar2, !zzcdVar2.zzfj());
                if (strZza != null) {
                    if (strZza.length() <= zzby.zzzt.get().intValue()) {
                        URL urlZzb = zzb(zzcdVar2, strZza);
                        if (urlZzb == null) {
                            zzu("Failed to build collect GET endpoint url");
                        } else if (zza(urlZzb) == 200) {
                        }
                        z9 = false;
                    } else {
                        String strZza2 = zza(zzcdVar2, false);
                        if (strZza2 == null) {
                            zzco().zza(zzcdVar2, "Error formatting hit for POST upload");
                        } else {
                            byte[] bytes = strZza2.getBytes();
                            if (bytes.length > zzby.zzzy.get().intValue()) {
                                zzco().zza(zzcdVar2, "Hit payload exceeds size limit");
                            } else {
                                URL urlZzd = zzd(zzcdVar2);
                                if (urlZzd == null) {
                                    zzu("Failed to build collect POST endpoint url");
                                } else if (zza(urlZzd, bytes) == 200) {
                                }
                                z9 = false;
                            }
                        }
                    }
                    if (z9) {
                        break;
                    }
                    arrayList2.add(Long.valueOf(zzcdVar2.zzfg()));
                    if (arrayList2.size() >= zzbq.zzer()) {
                        break;
                    }
                } else {
                    zzco().zza(zzcdVar2, "Error formatting hit for upload");
                }
                z9 = true;
                if (z9) {
                }
            }
            return arrayList2;
        }
        z8 = false;
        if (!z8) {
        }
    }

    public final boolean zzfr() {
        NetworkInfo activeNetworkInfo;
        com.google.android.gms.analytics.zzk.zzav();
        zzdb();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzq("No network connectivity");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zza(URL url, byte[] bArr) throws Throwable {
        HttpURLConnection httpURLConnectionZzb;
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(bArr);
        zzb("POST bytes, url", Integer.valueOf(bArr.length), url);
        if (zzam.zzda()) {
            zza("Post payload\n", new String(bArr));
        }
        OutputStream outputStream = null;
        try {
            getContext().getPackageName();
            httpURLConnectionZzb = zzb(url);
            try {
                try {
                    httpURLConnectionZzb.setDoOutput(true);
                    httpURLConnectionZzb.setFixedLengthStreamingMode(bArr.length);
                    httpURLConnectionZzb.connect();
                    outputStream = httpURLConnectionZzb.getOutputStream();
                    outputStream.write(bArr);
                    zza(httpURLConnectionZzb);
                    int responseCode = httpURLConnectionZzb.getResponseCode();
                    if (responseCode == 200) {
                        zzcs().zzcl();
                    }
                    zzb("POST status", Integer.valueOf(responseCode));
                    try {
                        outputStream.close();
                    } catch (IOException e9) {
                        zze("Error closing http post connection output stream", e9);
                    }
                    httpURLConnectionZzb.disconnect();
                    return responseCode;
                } catch (IOException e10) {
                    e = e10;
                    zzd("Network POST connection error", e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e11) {
                            zze("Error closing http post connection output stream", e11);
                        }
                    }
                    if (httpURLConnectionZzb == null) {
                        return 0;
                    }
                    httpURLConnectionZzb.disconnect();
                    return 0;
                }
            } catch (Throwable th) {
                th = th;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e12) {
                        zze("Error closing http post connection output stream", e12);
                    }
                }
                if (httpURLConnectionZzb == null) {
                    httpURLConnectionZzb.disconnect();
                    throw th;
                }
                throw th;
            }
        } catch (IOException e13) {
            e = e13;
            httpURLConnectionZzb = null;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnectionZzb = null;
            if (outputStream != null) {
            }
            if (httpURLConnectionZzb == null) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
    
        zze("Error closing http connection input stream", r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0018, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zza(HttpURLConnection httpURLConnection) throws Throwable {
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                do {
                } while (inputStream.read(new byte[UserMetadata.MAX_ATTRIBUTE_SIZE]) > 0);
                inputStream.close();
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e9) {
                        zze("Error closing http connection input stream", e9);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    @VisibleForTesting
    public final String zza(zzcd zzcdVar, boolean z8) {
        String strValueOf;
        Preconditions.checkNotNull(zzcdVar);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : zzcdVar.zzdm().entrySet()) {
                String key = entry.getKey();
                if (!"ht".equals(key) && !"qt".equals(key) && !"AppUID".equals(key) && !"z".equals(key) && !"_gmsv".equals(key)) {
                    zza(sb, key, entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzcdVar.zzfh()));
            zza(sb, "qt", String.valueOf(zzcn().currentTimeMillis() - zzcdVar.zzfh()));
            if (z8) {
                long jZzfk = zzcdVar.zzfk();
                if (jZzfk != 0) {
                    strValueOf = String.valueOf(jZzfk);
                } else {
                    strValueOf = String.valueOf(zzcdVar.zzfg());
                }
                zza(sb, "z", strValueOf);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e9) {
            zze("Failed to encode name or value", e9);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzb(URL url, byte[] bArr) throws Throwable {
        HttpURLConnection httpURLConnection;
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(bArr);
        OutputStream outputStream = null;
        try {
            getContext().getPackageName();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            zza("POST compressed size, ratio %, url", Integer.valueOf(byteArray.length), Long.valueOf((byteArray.length * 100) / bArr.length), url);
            if (byteArray.length > bArr.length) {
                zzc("Compressed payload is larger then uncompressed. compressed, uncompressed", Integer.valueOf(byteArray.length), Integer.valueOf(bArr.length));
            }
            if (zzam.zzda()) {
                String str = new String(bArr);
                zza("Post payload", str.length() != 0 ? "\n".concat(str) : new String("\n"));
            }
            HttpURLConnection httpURLConnectionZzb = zzb(url);
            try {
                httpURLConnectionZzb.setDoOutput(true);
                httpURLConnectionZzb.addRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
                httpURLConnectionZzb.setFixedLengthStreamingMode(byteArray.length);
                httpURLConnectionZzb.connect();
                OutputStream outputStream2 = httpURLConnectionZzb.getOutputStream();
                try {
                    outputStream2.write(byteArray);
                    outputStream2.close();
                    zza(httpURLConnectionZzb);
                    int responseCode = httpURLConnectionZzb.getResponseCode();
                    if (responseCode == 200) {
                        zzcs().zzcl();
                    }
                    zzb("POST status", Integer.valueOf(responseCode));
                    httpURLConnectionZzb.disconnect();
                    return responseCode;
                } catch (IOException e9) {
                    httpURLConnection = httpURLConnectionZzb;
                    e = e9;
                    outputStream = outputStream2;
                    try {
                        zzd("Network compressed POST connection error", e);
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e10) {
                                zze("Error closing http compressed post connection output stream", e10);
                            }
                        }
                        if (httpURLConnection == null) {
                            return 0;
                        }
                        httpURLConnection.disconnect();
                        return 0;
                    } catch (Throwable th) {
                        th = th;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e11) {
                                zze("Error closing http compressed post connection output stream", e11);
                            }
                        }
                        if (httpURLConnection == null) {
                            httpURLConnection.disconnect();
                            throw th;
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnectionZzb;
                    th = th2;
                    outputStream = outputStream2;
                    if (outputStream != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                }
            } catch (IOException e12) {
                httpURLConnection = httpURLConnectionZzb;
                e = e12;
            } catch (Throwable th3) {
                httpURLConnection = httpURLConnectionZzb;
                th = th3;
            }
        } catch (IOException e13) {
            e = e13;
            httpURLConnection = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
        }
    }

    private static void zza(StringBuilder sb, String str, String str2) {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    @VisibleForTesting
    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection uRLConnectionOpenConnection = url.openConnection();
        if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(zzby.zzaad.get().intValue());
            httpURLConnection.setReadTimeout(zzby.zzaae.get().intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, this.zzabp);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzb(zzcd zzcdVar, String str) {
        String string;
        if (zzcdVar.zzfj()) {
            String strZzet = zzbq.zzet();
            String strZzev = zzbq.zzev();
            StringBuilder sb = new StringBuilder(String.valueOf(strZzet).length() + 1 + String.valueOf(strZzev).length() + String.valueOf(str).length());
            sb.append(strZzet);
            sb.append(strZzev);
            sb.append("?");
            sb.append(str);
            string = sb.toString();
        } else {
            String strZzeu = zzbq.zzeu();
            String strZzev2 = zzbq.zzev();
            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzeu).length() + 1 + String.valueOf(strZzev2).length() + String.valueOf(str).length());
            sb2.append(strZzeu);
            sb2.append(strZzev2);
            sb2.append("?");
            sb2.append(str);
            string = sb2.toString();
        }
        try {
            return new URL(string);
        } catch (MalformedURLException e9) {
            zze("Error trying to parse the hardcoded host url", e9);
            return null;
        }
    }
}
