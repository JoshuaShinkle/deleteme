package com.google.android.gms.measurement.internal;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
final class zzla extends SSLSocketFactory {
    private final SSLSocketFactory zza;

    public zzla() {
        this(HttpsURLConnection.getDefaultSSLSocketFactory());
    }

    private final SSLSocket zza(SSLSocket sSLSocket) {
        return new zzkz(this, sSLSocket);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i9, boolean z8) {
        return zza((SSLSocket) this.zza.createSocket(socket, str, i9, z8));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.zza.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.zza.getSupportedCipherSuites();
    }

    private zzla(SSLSocketFactory sSLSocketFactory) {
        this.zza = sSLSocketFactory;
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i9) {
        return zza((SSLSocket) this.zza.createSocket(str, i9));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i9) {
        return zza((SSLSocket) this.zza.createSocket(inetAddress, i9));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i9, InetAddress inetAddress, int i10) {
        return zza((SSLSocket) this.zza.createSocket(str, i9, inetAddress, i10));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i9, InetAddress inetAddress2, int i10) {
        return zza((SSLSocket) this.zza.createSocket(inetAddress, i9, inetAddress2, i10));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket() {
        return zza((SSLSocket) this.zza.createSocket());
    }
}
