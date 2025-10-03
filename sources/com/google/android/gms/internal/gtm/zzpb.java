package com.google.android.gms.internal.gtm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes2.dex */
final class zzpb implements zzpc {
    private HttpURLConnection zzatk;
    private InputStream zzatl = null;

    @Override // com.google.android.gms.internal.gtm.zzpc
    public final void close() throws IOException {
        HttpURLConnection httpURLConnection = this.zzatk;
        try {
            InputStream inputStream = this.zzatl;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e9) {
            String strValueOf = String.valueOf(e9.getMessage());
            com.google.android.gms.tagmanager.zzdi.zza(strValueOf.length() != 0 ? "HttpUrlConnectionNetworkClient: Error when closing http input stream: ".concat(strValueOf) : new String("HttpUrlConnectionNetworkClient: Error when closing http input stream: "), e9);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzpc
    public final InputStream zzcj(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        this.zzatk = httpURLConnection;
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            this.zzatl = inputStream;
            return inputStream;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Bad response: ");
        sb.append(responseCode);
        String string = sb.toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(string);
        }
        if (responseCode == 503) {
            throw new zzpe(string);
        }
        throw new IOException(string);
    }
}
