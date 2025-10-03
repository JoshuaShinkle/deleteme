package org.jivesoftware.smack.proxy;

import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo;

/* renamed from: org.jivesoftware.smack.proxy.c */
/* loaded from: classes.dex */
public class C5599c extends SocketFactory {

    /* renamed from: a */
    public ProxyInfo f19367a;

    public C5599c(ProxyInfo proxyInfo) {
        this.f19367a = proxyInfo;
    }

    /* renamed from: a */
    public final void m22181a(InputStream inputStream, byte[] bArr, int i9) throws IOException {
        int i10 = 0;
        while (i10 < i9) {
            int i11 = inputStream.read(bArr, i10, i9 - i10);
            if (i11 <= 0) {
                throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "stream is closed");
            }
            i10 += i11;
        }
    }

    /* renamed from: b */
    public final Socket m22182b(String str, int i9) throws IOException {
        boolean z8;
        String strM22171b = this.f19367a.m22171b();
        int iM22173d = this.f19367a.m22173d();
        String strM22174e = this.f19367a.m22174e();
        String strM22172c = this.f19367a.m22172c();
        Socket socket = null;
        try {
            try {
                Socket socket2 = new Socket(strM22171b, iM22173d);
                try {
                    InputStream inputStream = socket2.getInputStream();
                    OutputStream outputStream = socket2.getOutputStream();
                    socket2.setTcpNoDelay(true);
                    byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
                    bArr[0] = 5;
                    bArr[1] = 2;
                    bArr[2] = 0;
                    bArr[3] = 2;
                    outputStream.write(bArr, 0, 4);
                    m22181a(inputStream, bArr, 2);
                    int i10 = bArr[1] & UnsignedBytes.MAX_VALUE;
                    if (i10 == 0) {
                        z8 = true;
                    } else {
                        if (i10 == 2 && strM22174e != null && strM22172c != null) {
                            bArr[0] = 1;
                            bArr[1] = (byte) strM22174e.length();
                            System.arraycopy(strM22174e.getBytes(), 0, bArr, 2, strM22174e.length());
                            int length = strM22174e.length() + 2;
                            int i11 = length + 1;
                            bArr[length] = (byte) strM22172c.length();
                            System.arraycopy(strM22172c.getBytes(), 0, bArr, i11, strM22172c.length());
                            outputStream.write(bArr, 0, i11 + strM22172c.length());
                            m22181a(inputStream, bArr, 2);
                            if (bArr[1] == 0) {
                                z8 = true;
                            }
                        }
                        z8 = false;
                    }
                    if (!z8) {
                        try {
                            socket2.close();
                        } catch (Exception unused) {
                        }
                        throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "fail in SOCKS5 proxy");
                    }
                    bArr[0] = 5;
                    bArr[1] = 1;
                    bArr[2] = 0;
                    byte[] bytes = str.getBytes();
                    int length2 = bytes.length;
                    bArr[3] = 3;
                    bArr[4] = (byte) length2;
                    System.arraycopy(bytes, 0, bArr, 5, length2);
                    int i12 = 5 + length2;
                    int i13 = i12 + 1;
                    bArr[i12] = (byte) (i9 >>> 8);
                    bArr[i13] = (byte) (i9 & 255);
                    outputStream.write(bArr, 0, i13 + 1);
                    m22181a(inputStream, bArr, 4);
                    if (bArr[1] != 0) {
                        try {
                            socket2.close();
                        } catch (Exception unused2) {
                        }
                        throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "server returns " + ((int) bArr[1]));
                    }
                    int i14 = bArr[3] & UnsignedBytes.MAX_VALUE;
                    if (i14 == 1) {
                        m22181a(inputStream, bArr, 6);
                    } else if (i14 == 3) {
                        m22181a(inputStream, bArr, 1);
                        m22181a(inputStream, bArr, (bArr[0] & UnsignedBytes.MAX_VALUE) + 2);
                    } else if (i14 == 4) {
                        m22181a(inputStream, bArr, 18);
                    }
                    return socket2;
                } catch (Exception e9) {
                    e = e9;
                    socket = socket2;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "ProxySOCKS5: " + e.toString(), e);
                }
            } catch (RuntimeException e10) {
                throw e10;
            }
        } catch (Exception e11) {
            e = e11;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9) {
        return m22182b(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9, InetAddress inetAddress, int i10) {
        return m22182b(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9) {
        return m22182b(inetAddress.getHostAddress(), i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9, InetAddress inetAddress2, int i10) {
        return m22182b(inetAddress.getHostAddress(), i9);
    }
}
