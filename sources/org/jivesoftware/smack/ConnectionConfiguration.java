package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.proxy.ProxyInfo;
import org.jivesoftware.smack.util.C5610d;
import org.jivesoftware.smack.util.C5616j;
import p028c7.C0751b;
import p222v6.C6492e;

/* loaded from: classes.dex */
public class ConnectionConfiguration implements Cloneable {

    /* renamed from: b */
    public String f19122b;

    /* renamed from: c */
    public List<C0751b> f19123c;

    /* renamed from: d */
    public String f19124d;

    /* renamed from: e */
    public String f19125e;

    /* renamed from: f */
    public String f19126f;

    /* renamed from: g */
    public SSLContext f19127g;

    /* renamed from: i */
    public CallbackHandler f19129i;

    /* renamed from: l */
    public SocketFactory f19132l;

    /* renamed from: m */
    public String f19133m;

    /* renamed from: n */
    public String f19134n;

    /* renamed from: o */
    public String f19135o;

    /* renamed from: u */
    public HostnameVerifier f19141u;

    /* renamed from: v */
    public ProxyInfo f19142v;

    /* renamed from: h */
    public boolean f19128h = false;

    /* renamed from: j */
    public boolean f19130j = C6492e.f21821h;

    /* renamed from: k */
    public boolean f19131k = true;

    /* renamed from: p */
    public boolean f19136p = true;

    /* renamed from: q */
    public boolean f19137q = true;

    /* renamed from: r */
    public boolean f19138r = false;

    /* renamed from: s */
    public boolean f19139s = true;

    /* renamed from: t */
    public SecurityMode f19140t = SecurityMode.enabled;

    public enum SecurityMode {
        required,
        enabled,
        disabled,
        legacy
    }

    public ConnectionConfiguration(String str, int i9, String str2, ProxyInfo proxyInfo) {
        m21939p(str, i9);
        m21938o(str2, proxyInfo);
    }

    /* renamed from: A */
    public void m21922A(SecurityMode securityMode) {
        this.f19140t = securityMode;
    }

    /* renamed from: B */
    public void m21923B(boolean z8) {
        this.f19136p = z8;
    }

    /* renamed from: C */
    public void m21924C(String str) {
        this.f19122b = str;
    }

    /* renamed from: a */
    public CallbackHandler m21925a() {
        return this.f19129i;
    }

    /* renamed from: b */
    public SSLContext m21926b() {
        return this.f19127g;
    }

    /* renamed from: c */
    public List<C0751b> m21927c() {
        return Collections.unmodifiableList(this.f19123c);
    }

    /* renamed from: d */
    public HostnameVerifier m21928d() {
        HostnameVerifier hostnameVerifier = this.f19141u;
        return hostnameVerifier != null ? hostnameVerifier : C6492e.m24826b();
    }

    /* renamed from: e */
    public String m21929e() {
        return this.f19124d;
    }

    /* renamed from: g */
    public String m21930g() {
        return this.f19125e;
    }

    /* renamed from: h */
    public String m21931h() {
        return this.f19126f;
    }

    /* renamed from: i */
    public String m21932i() {
        return this.f19134n;
    }

    /* renamed from: j */
    public String m21933j() {
        return this.f19135o;
    }

    /* renamed from: k */
    public SecurityMode m21934k() {
        return this.f19140t;
    }

    /* renamed from: l */
    public String m21935l() {
        return this.f19122b;
    }

    /* renamed from: m */
    public SocketFactory m21936m() {
        return this.f19132l;
    }

    /* renamed from: n */
    public String m21937n() {
        return this.f19133m;
    }

    /* renamed from: o */
    public void m21938o(String str, ProxyInfo proxyInfo) {
        if (C5616j.m22342g(str)) {
            throw new IllegalArgumentException("serviceName must not be the empty String");
        }
        this.f19122b = str;
        this.f19142v = proxyInfo;
        this.f19124d = System.getProperty("javax.net.ssl.keyStore");
        this.f19125e = "jks";
        this.f19126f = "pkcs11.config";
        this.f19132l = proxyInfo.m22175f();
    }

    /* renamed from: p */
    public final void m21939p(String str, int i9) {
        if (C5616j.m22342g(str)) {
            throw new IllegalArgumentException("host must not be the empty String");
        }
        this.f19123c = new ArrayList(1);
        this.f19123c.add(new C0751b(str, i9));
        this.f19139s = false;
    }

    /* renamed from: q */
    public boolean m21940q() {
        return this.f19128h;
    }

    /* renamed from: r */
    public boolean m21941r() {
        return this.f19130j;
    }

    /* renamed from: s */
    public boolean m21942s() {
        return this.f19138r;
    }

    /* renamed from: t */
    public boolean m21943t() {
        return this.f19131k;
    }

    /* renamed from: u */
    public boolean m21944u() {
        return this.f19136p;
    }

    /* renamed from: v */
    public void m21945v() {
        if (this.f19139s) {
            this.f19123c = C5610d.m22320d(this.f19122b);
        }
    }

    /* renamed from: w */
    public void m21946w(SSLContext sSLContext) {
        this.f19127g = sSLContext;
    }

    /* renamed from: x */
    public void m21947x(boolean z8) {
        this.f19130j = z8;
    }

    /* renamed from: y */
    public void m21948y(String str, String str2, String str3) {
        this.f19133m = str;
        this.f19134n = str2;
        this.f19135o = str3;
    }

    /* renamed from: z */
    public void m21949z(boolean z8) {
        this.f19131k = z8;
    }
}
