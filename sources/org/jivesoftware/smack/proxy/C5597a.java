package org.jivesoftware.smack.proxy;

import android.text.TextUtils;
import com.google.common.primitives.UnsignedBytes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo;
import org.jivesoftware.smack.util.C5616j;

/* renamed from: org.jivesoftware.smack.proxy.a */
/* loaded from: classes.dex */
public class C5597a extends SocketFactory {

    /* renamed from: b */
    public static final Pattern f19363b = Pattern.compile("HTTP/\\S+\\s(\\d+)\\s(.*)\\s*");

    /* renamed from: c */
    public static final Pattern f19364c = Pattern.compile("[\\s\\S]*Proxy-Authenticate:\\S*.Digest.(.*)\\s*");

    /* renamed from: a */
    public ProxyInfo f19365a;

    public C5597a(ProxyInfo proxyInfo) {
        this.f19365a = proxyInfo;
    }

    /* renamed from: a */
    public static String m22176a() {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < 8; i9++) {
            sb.append(Integer.toHexString(new Random().nextInt(16)));
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static String m22177d(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b9 : bArrDigest) {
                String hexString = Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE);
                while (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public final int m22178b(String str) {
        if (str == null) {
            return -1;
        }
        Matcher matcher = f19363b.matcher(str);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    /* renamed from: c */
    public final Socket m22179c(String str, int i9) throws NoSuchAlgorithmException, IOException {
        String string;
        String strM22177d;
        int i10;
        String strM22171b = this.f19365a.m22171b();
        Socket socket = new Socket(strM22171b, this.f19365a.m22173d());
        String str2 = "CONNECT " + str + ":" + i9;
        String strM22174e = this.f19365a.m22174e();
        String str3 = "";
        if (strM22174e == null) {
            string = "";
        } else {
            String strM22172c = this.f19365a.m22172c();
            StringBuilder sb = new StringBuilder();
            sb.append("\r\nProxy-Authorization: Basic ");
            sb.append(C5616j.m22337b(strM22174e + ":" + strM22172c));
            string = sb.toString();
        }
        socket.getOutputStream().write((str2 + " HTTP/1.1\r\nHost: " + str2 + string + "\r\nProxy-Connection: Keep-Alive\r\nPragma: no-cache\r\n\r\n").getBytes("UTF-8"));
        InputStream inputStream = socket.getInputStream();
        StringBuilder sb2 = new StringBuilder(100);
        int i11 = 0;
        while (true) {
            char c9 = (char) inputStream.read();
            sb2.append(c9);
            String str4 = str3;
            String str5 = "Recieved header of >1024 characters from ";
            String str6 = strM22171b;
            if (sb2.length() > 1024) {
                throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Recieved header of >1024 characters from " + str6 + ", cancelling connection");
            }
            i11 = (((i11 == 0 || i11 == 2) && c9 == '\r') || ((i11 == 1 || i11 == 3) && c9 == '\n')) ? i11 + 1 : 0;
            if (i11 == 4) {
                while (inputStream.available() != 0) {
                    inputStream.read();
                }
                BufferedReader bufferedReader = new BufferedReader(new StringReader(sb2.toString()));
                int iM22178b = m22178b(bufferedReader.readLine());
                if (iM22178b != 200) {
                    if (iM22178b != 407) {
                        throw new ProxyException(ProxyInfo.ProxyType.HTTP);
                    }
                    ArrayList arrayList = new ArrayList();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        Matcher matcher = f19364c.matcher(line);
                        if (matcher.matches()) {
                            Matcher matcher2 = Pattern.compile("[\"A-Za-z ]+=\"([^\"]+)\"").matcher(matcher.group(1));
                            while (matcher2.find()) {
                                arrayList.add(matcher2.group(0).trim());
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        throw new ProxyException(ProxyInfo.ProxyType.HTTP, "Authenticate does not support");
                    }
                    Iterator it = arrayList.iterator();
                    String str7 = str4;
                    String str8 = str7;
                    String str9 = str8;
                    while (it.hasNext()) {
                        String str10 = (String) it.next();
                        Iterator it2 = it;
                        String str11 = str5;
                        String strSubstring = str10.substring(str10.indexOf("\"") + 1, str10.lastIndexOf("\""));
                        if (str10.startsWith("realm")) {
                            str7 = strSubstring;
                        } else if (str10.startsWith("nonce")) {
                            str8 = strSubstring;
                        } else if (str10.startsWith("qop")) {
                            str9 = strSubstring;
                        }
                        it = it2;
                        str5 = str11;
                    }
                    String str12 = str5;
                    String strM22176a = m22176a();
                    String strM22177d2 = m22177d(strM22174e + ":" + str7 + ":" + this.f19365a.m22172c());
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("CONNECT:");
                    sb3.append(str);
                    sb3.append(":");
                    sb3.append(i9);
                    String strM22177d3 = m22177d(sb3.toString());
                    if (TextUtils.isEmpty(strM22177d2) || TextUtils.isEmpty(strM22177d3)) {
                        strM22177d = null;
                    } else {
                        strM22177d = m22177d(strM22177d2 + ":" + str8 + ":00000001:" + strM22176a + ":" + str9 + ":" + strM22177d3);
                    }
                    if (strM22177d != null) {
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write((str2 + " HTTP/1.1\r\nHost: " + str2 + ("\r\nProxy-Authorization: Digest username=\"" + strM22174e + "\", realm=\"" + str7 + "\", nonce=\"" + str8 + "\", uri=\"" + str + ":" + i9 + "\", response=\"" + strM22177d + "\", qop=\"" + str9 + "\", nc=00000001, cnonce=\"" + strM22176a + "\" ") + "\r\nProxy-Connection: Keep-Alive\r\nPragma: no-cache \r\n\r\n").getBytes("UTF-8"));
                        InputStream inputStream2 = socket.getInputStream();
                        sb2.setLength(0);
                        int i12 = 0;
                        do {
                            char c10 = (char) inputStream2.read();
                            sb2.append(c10);
                            if (sb2.length() > 1024) {
                                throw new ProxyException(ProxyInfo.ProxyType.HTTP, str12 + str6 + ", cancelling connection");
                            }
                            if ((i12 == 0 || i12 == 2) && c10 == '\r') {
                                i12++;
                                i10 = 4;
                            } else {
                                i12 = ((i12 == 1 || i12 == 3) && c10 == '\n') ? i12 + 1 : 0;
                                i10 = 4;
                            }
                        } while (i12 != i10);
                        if (m22178b(new BufferedReader(new StringReader(sb2.toString())).readLine()) != 200) {
                            throw new ProxyException(ProxyInfo.ProxyType.HTTP);
                        }
                    }
                }
                return socket;
            }
            str3 = str4;
            strM22171b = str6;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9) {
        return m22179c(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i9, InetAddress inetAddress, int i10) {
        return m22179c(str, i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9) {
        return m22179c(inetAddress.getHostAddress(), i9);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i9, InetAddress inetAddress2, int i10) {
        return m22179c(inetAddress.getHostAddress(), i9);
    }
}
