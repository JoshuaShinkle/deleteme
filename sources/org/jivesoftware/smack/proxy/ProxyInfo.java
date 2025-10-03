package org.jivesoftware.smack.proxy;

import javax.net.SocketFactory;
import p018b7.C0692a;

/* loaded from: classes.dex */
public class ProxyInfo {

    /* renamed from: a */
    public String f19353a;

    /* renamed from: b */
    public int f19354b;

    /* renamed from: c */
    public String f19355c;

    /* renamed from: d */
    public String f19356d;

    /* renamed from: e */
    public ProxyType f19357e;

    public enum ProxyType {
        NONE,
        HTTP,
        SOCKS4,
        SOCKS5
    }

    public ProxyInfo(ProxyType proxyType, String str, int i9, String str2, String str3) {
        this.f19357e = proxyType;
        this.f19353a = str;
        this.f19354b = i9;
        this.f19355c = str2;
        this.f19356d = str3;
    }

    /* renamed from: a */
    public static ProxyInfo m22170a() {
        return new ProxyInfo(ProxyType.NONE, null, 0, null, null);
    }

    /* renamed from: b */
    public String m22171b() {
        return this.f19353a;
    }

    /* renamed from: c */
    public String m22172c() {
        return this.f19356d;
    }

    /* renamed from: d */
    public int m22173d() {
        return this.f19354b;
    }

    /* renamed from: e */
    public String m22174e() {
        return this.f19355c;
    }

    /* renamed from: f */
    public SocketFactory m22175f() {
        ProxyType proxyType = this.f19357e;
        if (proxyType == ProxyType.NONE) {
            return new C0692a();
        }
        if (proxyType == ProxyType.HTTP) {
            return new C5597a(this);
        }
        if (proxyType == ProxyType.SOCKS4) {
            return new C5598b(this);
        }
        if (proxyType == ProxyType.SOCKS5) {
            return new C5599c(this);
        }
        return null;
    }
}
