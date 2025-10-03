package org.jivesoftware.smack.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.sasl.SaslException;
import org.jivesoftware.smack.C5585d;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.C5616j;
import org.xmlpull.v1.XmlPullParserException;
import p028c7.C0751b;
import p222v6.C6492e;
import p222v6.InterfaceC6489b;
import p222v6.InterfaceC6490c;
import p231w6.InterfaceC6547b;
import p258z6.AbstractC6835b;

/* loaded from: classes.dex */
public class XMPPTCPConnection extends XMPPConnection {

    /* renamed from: Q */
    public static final Logger f19393Q = Logger.getLogger(XMPPTCPConnection.class.getName());

    /* renamed from: B */
    public Socket f19394B;

    /* renamed from: C */
    public String f19395C;

    /* renamed from: D */
    public String f19396D;

    /* renamed from: E */
    public String f19397E;

    /* renamed from: F */
    public boolean f19398F;

    /* renamed from: G */
    public volatile boolean f19399G;

    /* renamed from: H */
    public boolean f19400H;

    /* renamed from: I */
    public boolean f19401I;

    /* renamed from: J */
    public boolean f19402J;

    /* renamed from: K */
    public AbstractC6835b f19403K;

    /* renamed from: L */
    public C5602b f19404L;

    /* renamed from: M */
    public C5601a f19405M;

    /* renamed from: N */
    public Collection<String> f19406N;

    /* renamed from: O */
    public boolean f19407O;

    /* renamed from: P */
    public final Object f19408P;

    public XMPPTCPConnection(ConnectionConfiguration connectionConfiguration) {
        super(connectionConfiguration);
        this.f19395C = null;
        this.f19396D = null;
        this.f19397E = null;
        this.f19398F = false;
        this.f19399G = false;
        this.f19400H = false;
        this.f19401I = false;
        this.f19402J = false;
        this.f19403K = C6492e.m24828d();
        this.f19407O = false;
        this.f19408P = new Object();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: A */
    public Reader mo21964A() {
        return super.mo21964A();
    }

    /* renamed from: A0 */
    public final boolean m22197A0() {
        if (this.f19180w) {
            throw new IllegalStateException("Compression should be negotiated before authentication.");
        }
        XMPPInputOutputStream xMPPInputOutputStreamM22210o0 = m22210o0();
        this.f19173p = xMPPInputOutputStreamM22210o0;
        if (xMPPInputOutputStreamM22210o0 == null) {
            return false;
        }
        synchronized (this.f19408P) {
            m22214s0(this.f19173p.m22026a());
            try {
                this.f19408P.wait(m22013y());
            } catch (InterruptedException unused) {
            }
        }
        return m22206k0();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: B */
    public C5585d mo21965B() {
        return super.mo21965B();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: D */
    public String mo21967D() {
        if (mo21970G()) {
            return this.f19397E;
        }
        return null;
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: E */
    public Writer mo21968E() {
        return super.mo21968E();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: G */
    public boolean mo21970G() {
        return this.f19180w;
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: H */
    public boolean mo21971H() {
        return this.f19398F;
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: I */
    public synchronized void mo21972I(String str, String str2, String str3) {
        InterfaceC6547b interfaceC6547b;
        if (!mo21971H()) {
            throw new SmackException.NotConnectedException();
        }
        if (this.f19180w) {
            throw new SmackException.AlreadyLoggedInException();
        }
        String strTrim = str.toLowerCase(Locale.US).trim();
        if (!this.f19167j.m22041h()) {
            throw new SaslException("No non-anonymous SASL authentication mechanism available");
        }
        if (str2 != null) {
            this.f19167j.m22034a(strTrim, str2, str3);
        } else {
            this.f19167j.m22035b(str3, this.f19169l.m21925a());
        }
        if (this.f19169l.m21940q()) {
            m22197A0();
        }
        String strM21995f = m21995f(str3);
        if (strM21995f != null) {
            this.f19397E = strM21995f;
            mo21988Y(C5616j.m22348m(strM21995f));
        } else {
            this.f19397E = strTrim + "@" + m21966C();
            if (str3 != null) {
                this.f19397E += "/" + str3;
            }
        }
        this.f19180w = true;
        this.f19400H = false;
        if (this.f19169l.m21944u()) {
            m21979P(new Presence(Presence.Type.available));
        }
        m21985V(strTrim, str2, str3);
        if (this.f19169l.m21941r() && (interfaceC6547b = this.f19164g) != null) {
            interfaceC6547b.userHasLogged(this.f19397E);
        }
        m21996g();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: K */
    public void mo21974K(AbstractC5594b abstractC5594b) {
        super.mo21974K(abstractC5594b);
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: Q */
    public void mo21980Q(AbstractC5594b abstractC5594b) throws SmackException.NotConnectedException {
        this.f19404L.m22233e(abstractC5594b);
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: R */
    public void mo21981R() {
        super.mo21981R();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: S */
    public void mo21982S() {
        super.mo21982S();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: T */
    public void mo21983T() {
        super.mo21983T();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: W */
    public void mo21986W() {
        super.mo21986W();
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: X */
    public void mo21987X(String str) {
        super.mo21987X(str);
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: Y */
    public void mo21988Y(String str) {
        super.mo21988Y(str);
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: a0 */
    public void mo21990a0() throws IOException {
        C5601a c5601a = this.f19405M;
        if (c5601a != null) {
            c5601a.m22227f();
        }
        C5602b c5602b = this.f19404L;
        if (c5602b != null) {
            c5602b.m22235g();
        }
        this.f19399G = true;
        try {
            this.f19394B.close();
        } catch (Exception e9) {
            f19393Q.log(Level.WARNING, "shutdown", (Throwable) e9);
        }
        m21989Z(this.f19180w);
        this.f19180w = false;
        this.f19398F = false;
        this.f19401I = false;
        Reader reader = this.f19165h;
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e10) {
                e10.printStackTrace();
            }
        }
        Writer writer = this.f19166i;
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e11) {
                e11.printStackTrace();
            }
        }
        this.f19165h = null;
        this.f19166i = null;
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: b0 */
    public void mo21991b0() throws SmackException.NoResponseException, IOException {
        super.mo21991b0();
    }

    /* renamed from: c0 */
    public final void m22198c0(ConnectionConfiguration connectionConfiguration) throws XmlPullParserException, SmackException, ClassNotFoundException, IOException {
        try {
            m21973J();
            Iterator<C0751b> it = connectionConfiguration.m21927c().iterator();
            LinkedList linkedList = new LinkedList();
            do {
                if (it.hasNext()) {
                    C0751b next = it.next();
                    String strM3629b = next.m3629b();
                    int iM3630c = next.m3630c();
                    try {
                        if (connectionConfiguration.m21936m() == null) {
                            this.f19394B = new Socket(strM3629b, iM3630c);
                        } else {
                            this.f19394B = connectionConfiguration.m21936m().createSocket(strM3629b, iM3630c);
                        }
                        if (ConnectionConfiguration.SecurityMode.legacy == connectionConfiguration.m21934k()) {
                            m22199d0(false);
                            this.f19402J = true;
                        }
                        e = null;
                    } catch (Exception e9) {
                        e = e9;
                    }
                    if (e == null) {
                        next.m3629b();
                        next.m3630c();
                    } else {
                        next.m3631d(e);
                        linkedList.add(next);
                    }
                }
                this.f19399G = false;
                m22201f0();
                return;
            } while (it.hasNext());
            throw new SmackException.ConnectionException(linkedList);
        } catch (Exception e10) {
            throw new SmackException(e10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ec A[Catch: NullPointerException -> 0x00ff, TRY_ENTER, TryCatch #2 {NullPointerException -> 0x00ff, blocks: (B:26:0x00ec, B:28:0x00fa, B:27:0x00f0), top: B:46:0x00ea }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f0 A[Catch: NullPointerException -> 0x00ff, TryCatch #2 {NullPointerException -> 0x00ff, blocks: (B:26:0x00ec, B:28:0x00fa, B:27:0x00f0), top: B:46:0x00ea }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: d0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m22199d0(boolean z8) throws NoSuchMethodException, NoSuchAlgorithmException, UnrecoverableKeyException, IOException, SecurityException, KeyStoreException, KeyManagementException, CertificateException, NoSuchProviderException {
        KeyManager[] keyManagers;
        KeyStore keyStore;
        PasswordCallback passwordCallback;
        FileInputStream fileInputStream;
        KeyManagerFactory keyManagerFactory;
        SSLContext sSLContextM21926b = this.f19169l.m21926b();
        if (this.f19169l.m21925a() == null || sSLContextM21926b != null) {
            keyManagers = null;
        } else if (this.f19169l.m21930g().equals("NONE")) {
            keyStore = null;
            passwordCallback = null;
            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            try {
                if (passwordCallback == null) {
                    keyManagerFactory.init(keyStore, null);
                } else {
                    keyManagerFactory.init(keyStore, passwordCallback.getPassword());
                    passwordCallback.clearPassword();
                }
                keyManagers = keyManagerFactory.getKeyManagers();
            } catch (NullPointerException unused) {
            }
        } else {
            if (this.f19169l.m21930g().equals("PKCS11")) {
                try {
                    Provider provider = (Provider) Class.forName("sun.security.pkcs11.SunPKCS11").getConstructor(InputStream.class).newInstance(new ByteArrayInputStream(("name = SmartCard\nlibrary = " + this.f19169l.m21931h()).getBytes()));
                    Security.addProvider(provider);
                    keyStore = KeyStore.getInstance("PKCS11", provider);
                    passwordCallback = new PasswordCallback("PKCS11 Password: ", false);
                    this.f19169l.m21925a().handle(new Callback[]{passwordCallback});
                    keyStore.load(null, passwordCallback.getPassword());
                } catch (Exception unused2) {
                }
            } else if (this.f19169l.m21930g().equals("Apple")) {
                keyStore = KeyStore.getInstance("KeychainStore", "Apple");
                keyStore.load(null, null);
                passwordCallback = null;
            } else {
                keyStore = KeyStore.getInstance(this.f19169l.m21930g());
                try {
                    passwordCallback = new PasswordCallback("Keystore Password: ", false);
                    this.f19169l.m21925a().handle(new Callback[]{passwordCallback});
                    fileInputStream = new FileInputStream(this.f19169l.m21929e());
                    try {
                        keyStore.load(fileInputStream, passwordCallback.getPassword());
                    } catch (Exception unused3) {
                        keyStore = null;
                        passwordCallback = null;
                        if (fileInputStream != null) {
                        }
                        keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
                        if (passwordCallback == null) {
                        }
                        keyManagers = keyManagerFactory.getKeyManagers();
                        if (sSLContextM21926b == null) {
                        }
                        Socket socket = this.f19394B;
                        this.f19394B = sSLContextM21926b.getSocketFactory().createSocket(socket, socket.getInetAddress().getHostAddress(), socket.getPort(), true);
                    }
                } catch (Exception unused4) {
                    fileInputStream = null;
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception unused5) {
                    }
                }
            }
            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            if (passwordCallback == null) {
            }
            keyManagers = keyManagerFactory.getKeyManagers();
        }
        if (sSLContextM21926b == null) {
            sSLContextM21926b = SSLContext.getInstance(z8 ? "TLS" : "SSL");
            sSLContextM21926b.init(keyManagers, null, new SecureRandom());
        }
        Socket socket2 = this.f19394B;
        this.f19394B = sSLContextM21926b.getSocketFactory().createSocket(socket2, socket2.getInetAddress().getHostAddress(), socket2.getPort(), true);
    }

    /* renamed from: e0 */
    public AbstractC6835b m22200e0() {
        return this.f19403K;
    }

    /* renamed from: f0 */
    public final void m22201f0() throws XmlPullParserException, SmackException, ClassNotFoundException, IOException {
        boolean z8 = this.f19405M == null || this.f19404L == null;
        this.f19173p = null;
        this.f19407O = false;
        m22202g0();
        try {
            if (z8) {
                this.f19404L = new C5602b(this);
                this.f19405M = new C5601a(this);
                if (this.f19169l.m21941r()) {
                    m21993d(this.f19164g.getReaderListener(), null);
                    if (this.f19164g.getWriterListener() != null) {
                        m21994e(this.f19164g.getWriterListener(), null);
                    }
                }
            } else {
                this.f19404L.m22230b();
                this.f19405M.m22223b();
            }
            this.f19404L.m22236h();
            this.f19405M.m22228g();
            this.f19398F = true;
            if (z8) {
                Iterator<InterfaceC6489b> it = XMPPConnection.m21963u().iterator();
                while (it.hasNext()) {
                    it.next().mo21958a(this);
                }
            }
        } catch (SmackException e9) {
            mo21990a0();
            throw e9;
        }
    }

    /* renamed from: g0 */
    public final void m22202g0() throws ClassNotFoundException {
        try {
            XMPPInputOutputStream xMPPInputOutputStream = this.f19173p;
            if (xMPPInputOutputStream == null) {
                this.f19165h = new BufferedReader(new InputStreamReader(this.f19394B.getInputStream(), "UTF-8"));
                this.f19166i = new BufferedWriter(new OutputStreamWriter(this.f19394B.getOutputStream(), "UTF-8"));
            } else {
                try {
                    this.f19166i = new BufferedWriter(new OutputStreamWriter(xMPPInputOutputStream.mo22028c(this.f19394B.getOutputStream()), "UTF-8"));
                    this.f19165h = new BufferedReader(new InputStreamReader(this.f19173p.mo22027b(this.f19394B.getInputStream()), "UTF-8"));
                } catch (Exception e9) {
                    f19393Q.log(Level.WARNING, "initReaderAndWriter()", (Throwable) e9);
                    this.f19173p = null;
                    this.f19165h = new BufferedReader(new InputStreamReader(this.f19394B.getInputStream(), "UTF-8"));
                    this.f19166i = new BufferedWriter(new OutputStreamWriter(this.f19394B.getOutputStream(), "UTF-8"));
                }
            }
            m21969F();
        } catch (UnsupportedEncodingException e10) {
            throw new IllegalStateException(e10);
        }
    }

    /* renamed from: h0 */
    public boolean m22203h0() {
        return this.f19400H;
    }

    /* renamed from: i0 */
    public boolean m22204i0() {
        return m22208m0() || m22207l0();
    }

    /* renamed from: j0 */
    public boolean m22205j0() {
        return this.f19399G;
    }

    /* renamed from: k0 */
    public boolean m22206k0() {
        return this.f19173p != null && this.f19407O;
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: l */
    public void mo22001l() throws XmlPullParserException, SmackException, ClassNotFoundException, IOException {
        m22198c0(this.f19169l);
        if (this.f19398F) {
            m21999j();
        }
        if (this.f19398F && this.f19181x) {
            if (m22203h0()) {
                m22209n0();
            } else if (this.f19395C == null) {
                mo21972I(this.f19169l.m21937n(), this.f19169l.m21932i(), this.f19169l.m21933j());
            } else {
                m22215t0(this.f19169l.m21937n(), this.f19169l.m21932i(), this.f19169l.m21933j());
            }
            m22212q0();
        }
    }

    /* renamed from: l0 */
    public boolean m22207l0() {
        return this.f19402J;
    }

    /* renamed from: m0 */
    public boolean m22208m0() {
        return this.f19401I;
    }

    /* renamed from: n0 */
    public synchronized void m22209n0() {
        InterfaceC6547b interfaceC6547b;
        if (!mo21971H()) {
            throw new SmackException.NotConnectedException();
        }
        if (this.f19180w) {
            throw new SmackException.AlreadyLoggedInException();
        }
        if (!this.f19167j.m22040g()) {
            throw new SaslException("No anonymous SASL authentication mechanism available");
        }
        this.f19167j.m22036c();
        String strM21995f = m21995f(null);
        this.f19397E = strM21995f;
        mo21988Y(C5616j.m22348m(strM21995f));
        if (this.f19169l.m21940q()) {
            m22197A0();
        }
        m21979P(new Presence(Presence.Type.available));
        this.f19180w = true;
        this.f19400H = true;
        if (this.f19169l.m21941r() && (interfaceC6547b = this.f19164g) != null) {
            interfaceC6547b.userHasLogged(this.f19397E);
        }
        m21996g();
    }

    /* renamed from: o0 */
    public final XMPPInputOutputStream m22210o0() {
        if (this.f19406N == null) {
            return null;
        }
        for (XMPPInputOutputStream xMPPInputOutputStream : C6492e.m24825a()) {
            if (this.f19406N.contains(xMPPInputOutputStream.m22026a())) {
                return xMPPInputOutputStream;
            }
        }
        return null;
    }

    /* renamed from: p0 */
    public synchronized void m22211p0(Exception exc) {
        C5602b c5602b;
        C5601a c5601a = this.f19405M;
        if ((c5601a != null && !c5601a.f19414e) || ((c5602b = this.f19404L) != null && !c5602b.f19421e)) {
            mo21990a0();
            m21998i(exc);
        }
    }

    /* renamed from: q0 */
    public final void m22212q0() {
        Iterator<InterfaceC6490c> it = m22010v().iterator();
        while (it.hasNext()) {
            try {
                it.next().reconnectionSuccessful();
            } catch (Exception e9) {
                f19393Q.log(Level.WARNING, "notifyReconnection()", (Throwable) e9);
            }
        }
    }

    /* renamed from: r0 */
    public void m22213r0() {
        m22199d0(true);
        m22202g0();
        SSLSocket sSLSocket = (SSLSocket) this.f19394B;
        try {
            sSLSocket.startHandshake();
            HostnameVerifier hostnameVerifierM21928d = mo22008s().m21928d();
            if (hostnameVerifierM21928d == null || hostnameVerifierM21928d.verify(m21966C(), sSLSocket.getSession())) {
                this.f19401I = true;
                this.f19404L.m22234f(this.f19166i);
                this.f19404L.m22232d();
            } else {
                throw new CertificateException("Hostname verification of certificate failed. Certificate does not authenticate " + m21966C());
            }
        } catch (IOException e9) {
            m21984U(e9);
            throw e9;
        }
    }

    @Override // org.jivesoftware.smack.XMPPConnection
    /* renamed from: s */
    public ConnectionConfiguration mo22008s() {
        return super.mo22008s();
    }

    /* renamed from: s0 */
    public final void m22214s0(String str) throws IOException {
        this.f19166i.write("<compress xmlns='http://jabber.org/protocol/compress'>");
        this.f19166i.write("<method>" + str + "</method></compress>");
        this.f19166i.flush();
    }

    /* renamed from: t0 */
    public synchronized void m22215t0(String str, String str2, String str3) {
        InterfaceC6547b interfaceC6547b;
        if (!mo21971H()) {
            throw new SmackException.NotConnectedException();
        }
        if (this.f19180w) {
            throw new SmackException.AlreadyLoggedInException();
        }
        m21979P(new AbstractC5594b() { // from class: org.jivesoftware.smack.tcp.XMPPTCPConnection.1
            @Override // org.jivesoftware.smack.packet.AbstractC5594b
            /* renamed from: u */
            public CharSequence mo22057u() {
                return "<clresume xmlns='urn:xmpp:custom:resume' sessionid='" + XMPPTCPConnection.this.f19395C + "'/>";
            }
        });
        this.f19397E = str;
        if (str3 != null) {
            this.f19397E += "/" + str3;
        }
        this.f19180w = true;
        this.f19400H = false;
        if (this.f19169l.m21944u()) {
            m21979P(new Presence(Presence.Type.available));
        }
        m21985V(str, str2, str3);
        if (this.f19169l.m21941r() && (interfaceC6547b = this.f19164g) != null) {
            interfaceC6547b.userHasLogged(this.f19397E);
        }
        m21996g();
    }

    /* renamed from: u0 */
    public void m22216u0(boolean z8) {
        this.f19180w = z8;
    }

    /* renamed from: v0 */
    public void m22217v0(Collection<String> collection) {
        this.f19406N = collection;
    }

    /* renamed from: w0 */
    public void m22218w0(String str) {
        this.f19395C = str;
    }

    /* renamed from: x0 */
    public void m22219x0() {
        this.f19407O = true;
        m22202g0();
        this.f19404L.m22234f(this.f19166i);
        this.f19404L.m22232d();
        m22221z0();
    }

    /* renamed from: y0 */
    public void m22220y0(boolean z8) {
        if (z8 && this.f19169l.m21934k() == ConnectionConfiguration.SecurityMode.disabled) {
            m22211p0(new IllegalStateException("TLS required by server but not allowed by connection configuration"));
            return;
        }
        if (z8 && this.f19402J) {
            m22211p0(new IllegalStateException("TLS required by server but legacy SSL already enabled"));
        } else {
            if (this.f19169l.m21934k() == ConnectionConfiguration.SecurityMode.disabled || this.f19169l.m21934k() == ConnectionConfiguration.SecurityMode.legacy) {
                return;
            }
            this.f19166i.write("<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>");
            this.f19166i.flush();
        }
    }

    /* renamed from: z0 */
    public void m22221z0() {
        synchronized (this.f19408P) {
            this.f19408P.notify();
        }
    }
}
