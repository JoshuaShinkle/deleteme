package com.google.android.gms.measurement.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes2.dex */
final class zzkz extends SSLSocket {
    private final SSLSocket zza;

    public zzkz(zzla zzlaVar, SSLSocket sSLSocket) {
        this.zza = sSLSocket;
    }

    @Override // javax.net.ssl.SSLSocket
    public final void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zza.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public final void bind(SocketAddress socketAddress) throws IOException {
        this.zza.bind(socketAddress);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.zza.close();
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress) throws IOException {
        this.zza.connect(socketAddress);
    }

    public final boolean equals(Object obj) {
        return this.zza.equals(obj);
    }

    @Override // java.net.Socket
    public final SocketChannel getChannel() {
        return this.zza.getChannel();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getEnableSessionCreation() {
        return this.zza.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledCipherSuites() {
        return this.zza.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledProtocols() {
        return this.zza.getEnabledProtocols();
    }

    @Override // java.net.Socket
    public final InetAddress getInetAddress() {
        return this.zza.getInetAddress();
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() {
        return this.zza.getInputStream();
    }

    @Override // java.net.Socket
    public final boolean getKeepAlive() {
        return this.zza.getKeepAlive();
    }

    @Override // java.net.Socket
    public final InetAddress getLocalAddress() {
        return this.zza.getLocalAddress();
    }

    @Override // java.net.Socket
    public final int getLocalPort() {
        return this.zza.getLocalPort();
    }

    @Override // java.net.Socket
    public final SocketAddress getLocalSocketAddress() {
        return this.zza.getLocalSocketAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getNeedClientAuth() {
        return this.zza.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() {
        return this.zza.getOOBInline();
    }

    @Override // java.net.Socket
    public final OutputStream getOutputStream() {
        return this.zza.getOutputStream();
    }

    @Override // java.net.Socket
    public final int getPort() {
        return this.zza.getPort();
    }

    @Override // java.net.Socket
    public final synchronized int getReceiveBufferSize() {
        return this.zza.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public final SocketAddress getRemoteSocketAddress() {
        return this.zza.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getReuseAddress() {
        return this.zza.getReuseAddress();
    }

    @Override // java.net.Socket
    public final synchronized int getSendBufferSize() {
        return this.zza.getSendBufferSize();
    }

    @Override // javax.net.ssl.SSLSocket
    public final SSLSession getSession() {
        return this.zza.getSession();
    }

    @Override // java.net.Socket
    public final int getSoLinger() {
        return this.zza.getSoLinger();
    }

    @Override // java.net.Socket
    public final synchronized int getSoTimeout() {
        return this.zza.getSoTimeout();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedCipherSuites() {
        return this.zza.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedProtocols() {
        return this.zza.getSupportedProtocols();
    }

    @Override // java.net.Socket
    public final boolean getTcpNoDelay() {
        return this.zza.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public final int getTrafficClass() {
        return this.zza.getTrafficClass();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getUseClientMode() {
        return this.zza.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getWantClientAuth() {
        return this.zza.getWantClientAuth();
    }

    @Override // java.net.Socket
    public final boolean isBound() {
        return this.zza.isBound();
    }

    @Override // java.net.Socket
    public final boolean isClosed() {
        return this.zza.isClosed();
    }

    @Override // java.net.Socket
    public final boolean isConnected() {
        return this.zza.isConnected();
    }

    @Override // java.net.Socket
    public final boolean isInputShutdown() {
        return this.zza.isInputShutdown();
    }

    @Override // java.net.Socket
    public final boolean isOutputShutdown() {
        return this.zza.isOutputShutdown();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zza.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public final void sendUrgentData(int i9) throws IOException {
        this.zza.sendUrgentData(i9);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnableSessionCreation(boolean z8) {
        this.zza.setEnableSessionCreation(z8);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledCipherSuites(String[] strArr) {
        this.zza.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledProtocols(String[] strArr) {
        if (strArr != null && Arrays.asList(strArr).contains("SSLv3")) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.zza.getEnabledProtocols()));
            if (arrayList.size() > 1) {
                arrayList.remove("SSLv3");
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.zza.setEnabledProtocols(strArr);
    }

    @Override // java.net.Socket
    public final void setKeepAlive(boolean z8) throws SocketException {
        this.zza.setKeepAlive(z8);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setNeedClientAuth(boolean z8) {
        this.zza.setNeedClientAuth(z8);
    }

    @Override // java.net.Socket
    public final void setOOBInline(boolean z8) throws SocketException {
        this.zza.setOOBInline(z8);
    }

    @Override // java.net.Socket
    public final void setPerformancePreferences(int i9, int i10, int i11) {
        this.zza.setPerformancePreferences(i9, i10, i11);
    }

    @Override // java.net.Socket
    public final synchronized void setReceiveBufferSize(int i9) {
        this.zza.setReceiveBufferSize(i9);
    }

    @Override // java.net.Socket
    public final void setReuseAddress(boolean z8) throws SocketException {
        this.zza.setReuseAddress(z8);
    }

    @Override // java.net.Socket
    public final synchronized void setSendBufferSize(int i9) {
        this.zza.setSendBufferSize(i9);
    }

    @Override // java.net.Socket
    public final void setSoLinger(boolean z8, int i9) throws SocketException {
        this.zza.setSoLinger(z8, i9);
    }

    @Override // java.net.Socket
    public final synchronized void setSoTimeout(int i9) {
        this.zza.setSoTimeout(i9);
    }

    @Override // java.net.Socket
    public final void setTcpNoDelay(boolean z8) throws SocketException {
        this.zza.setTcpNoDelay(z8);
    }

    @Override // java.net.Socket
    public final void setTrafficClass(int i9) throws SocketException {
        this.zza.setTrafficClass(i9);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setUseClientMode(boolean z8) {
        this.zza.setUseClientMode(z8);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setWantClientAuth(boolean z8) {
        this.zza.setWantClientAuth(z8);
    }

    @Override // java.net.Socket
    public final void shutdownInput() throws IOException {
        this.zza.shutdownInput();
    }

    @Override // java.net.Socket
    public final void shutdownOutput() throws IOException {
        this.zza.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() throws IOException {
        this.zza.startHandshake();
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public final String toString() {
        return this.zza.toString();
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress, int i9) throws IOException {
        this.zza.connect(socketAddress, i9);
    }
}
