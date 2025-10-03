package org.jivesoftware.smack.proxy;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo;

/* renamed from: org.jivesoftware.smack.proxy.b */
/* loaded from: classes.dex */
public class C5598b extends SocketFactory {

    /* renamed from: a */
    public ProxyInfo f19366a;

    public C5598b(ProxyInfo proxyInfo) {
        this.f19366a = proxyInfo;
    }

    /* renamed from: a */
    public final Socket m22180a(String str, int i9) throws IOException {
        Socket socket;
        String strM22171b = this.f19366a.m22171b();
        int iM22173d = this.f19366a.m22173d();
        String strM22174e = this.f19366a.m22174e();
        Socket socket2 = null;
        try {
            try {
                socket = new Socket(strM22171b, iM22173d);
            } catch (Exception e9) {
                e = e9;
            }
            try {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                socket.setTcpNoDelay(true);
                byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                int length = 4;
                bArr[0] = 4;
                bArr[1] = 1;
                bArr[2] = (byte) (i9 >>> 8);
                bArr[3] = (byte) (i9 & 255);
                try {
                    byte[] address = InetAddress.getByName(str).getAddress();
                    int i10 = 0;
                    while (i10 < address.length) {
                        int i11 = length + 1;
                        bArr[length] = address[i10];
                        i10++;
                        length = i11;
                    }
                    if (strM22174e != null) {
                        System.arraycopy(strM22174e.getBytes(), 0, bArr, length, strM22174e.length());
                        length += strM22174e.length();
                    }
                    bArr[length] = 0;
                    outputStream.write(bArr, 0, length + 1);
                    int i12 = 0;
                    while (i12 < 6) {
                        int i13 = inputStream.read(bArr, i12, 6 - i12);
                        if (i13 <= 0) {
                            throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, "stream is closed");
                        }
                        i12 += i13;
                    }
                    if (bArr[0] != 0) {
                        throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, "server returns VN " + ((int) bArr[0]));
                    }
                    if (bArr[1] == 90) {
                        inputStream.read(new byte[2], 0, 2);
                        return socket;
                    }
                    try {
                        socket.close();
                    } catch (Exception unused) {
                    }
                    throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, "ProxySOCKS4: server returns CD " + ((int) bArr[1]));
                } catch (UnknownHostException e10) {
                    throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, e10.toString(), e10);
                }
            } catch (Exception e11) {
                e = e11;
                socket2 = socket;
                if (socket2 != null) {
                    try {
                        socket2.close();
                    } catch (Exception unused2) {
                    }
                }
                throw new ProxyException(ProxyInfo.ProxyType.SOCKS4, e.toString());
            }
        } catch (RuntimeException e12) {
            throw e12;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9) {
        return m22180a(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9, InetAddress inetAddress, int i10) {
        return m22180a(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9) {
        return m22180a(inetAddress.getHostAddress(), i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9, InetAddress inetAddress2, int i10) {
        return m22180a(inetAddress.getHostAddress(), i9);
    }
}
