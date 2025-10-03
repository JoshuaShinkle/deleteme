package p018b7;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import javax.net.SocketFactory;

/* renamed from: b7.a */
/* loaded from: classes.dex */
public class C0692a extends SocketFactory {
    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9) throws IOException {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.setTcpNoDelay(true);
        socket.connect(new InetSocketAddress(str, i9), 10000);
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9, InetAddress inetAddress, int i10) throws SocketException {
        Socket socket = new Socket(str, i9, inetAddress, i10);
        socket.setTcpNoDelay(true);
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9) throws IOException {
        Socket socket = new Socket(Proxy.NO_PROXY);
        socket.setTcpNoDelay(true);
        socket.connect(new InetSocketAddress(inetAddress, i9));
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9, InetAddress inetAddress2, int i10) throws SocketException {
        Socket socket = new Socket(inetAddress, i9, inetAddress2, i10);
        socket.setTcpNoDelay(true);
        return socket;
    }
}
