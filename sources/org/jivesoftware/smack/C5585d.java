package org.jivesoftware.smack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslException;
import org.apache.qpid.management.common.sasl.Constants;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.sasl.SASLAnonymous;
import org.jivesoftware.smack.sasl.SASLDigestMD5Mechanism;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.SASLExternalMechanism;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.sasl.SASLPlainMechanism;

/* renamed from: org.jivesoftware.smack.d */
/* loaded from: classes.dex */
public class C5585d {

    /* renamed from: f */
    public static Map<String, Class<? extends SASLMechanism>> f19211f = new HashMap();

    /* renamed from: g */
    public static List<String> f19212g = new ArrayList();

    /* renamed from: a */
    public XMPPConnection f19213a;

    /* renamed from: b */
    public Collection<String> f19214b = new ArrayList();

    /* renamed from: c */
    public SASLMechanism f19215c = null;

    /* renamed from: d */
    public boolean f19216d;

    /* renamed from: e */
    public SASLMechanism.SASLFailure f19217e;

    static {
        m22032j("EXTERNAL", SASLExternalMechanism.class);
        m22032j("DIGEST-MD5", SASLDigestMD5Mechanism.class);
        m22032j(Constants.MECH_PLAIN, SASLPlainMechanism.class);
        m22032j("ANONYMOUS", SASLAnonymous.class);
        m22033m("DIGEST-MD5", 0);
        m22033m(Constants.MECH_PLAIN, 1);
        m22033m("ANONYMOUS", 2);
    }

    public C5585d(XMPPConnection xMPPConnection) {
        this.f19213a = xMPPConnection;
        m22042i();
    }

    /* renamed from: j */
    public static void m22032j(String str, Class<? extends SASLMechanism> cls) {
        f19211f.put(str, cls);
    }

    /* renamed from: m */
    public static void m22033m(String str, int i9) {
        f19212g.add(i9, str);
    }

    /* renamed from: a */
    public void m22034a(String str, String str2, String str3) throws SmackException.NoResponseException, SaslException, SASLErrorException {
        String next;
        Iterator<String> it = f19212g.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (f19211f.containsKey(next) && this.f19214b.contains(next)) {
                break;
            }
        }
        if (next == null) {
            throw new SaslException("SASL Authentication failed. No known authentication mechanisims.");
        }
        try {
            this.f19215c = f19211f.get(next).getConstructor(C5585d.class).newInstance(this);
            synchronized (this) {
                this.f19215c.m22188b(str, this.f19213a.m22011w(), this.f19213a.m21966C(), str2);
                try {
                    wait(this.f19213a.m22013y());
                } catch (InterruptedException unused) {
                }
            }
            if (this.f19217e != null) {
                throw new SASLErrorException(next, this.f19217e);
            }
            if (!this.f19216d) {
                throw new SmackException.NoResponseException();
            }
        } catch (Exception e9) {
            throw new SaslException("Exception when creating the SASLAuthentication instance", e9);
        }
    }

    /* renamed from: b */
    public void m22035b(String str, CallbackHandler callbackHandler) throws SmackException.NoResponseException, SaslException, SASLErrorException {
        String next;
        Iterator<String> it = f19212g.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (f19211f.containsKey(next) && this.f19214b.contains(next)) {
                break;
            }
        }
        if (next == null) {
            throw new SaslException("SASL Authentication failed. No known authentication mechanisims.");
        }
        try {
            this.f19215c = f19211f.get(next).getConstructor(C5585d.class).newInstance(this);
            synchronized (this) {
                this.f19215c.m22189c(this.f19213a.m22011w(), callbackHandler);
                try {
                    wait(this.f19213a.m22013y());
                } catch (InterruptedException unused) {
                }
            }
            if (this.f19217e != null) {
                throw new SASLErrorException(next, this.f19217e);
            }
            if (!this.f19216d) {
                throw new SmackException.NoResponseException();
            }
        } catch (Exception e9) {
            throw new SaslException("Exception when creating the SASLAuthentication instance", e9);
        }
    }

    /* renamed from: c */
    public void m22036c() throws SmackException.NoResponseException, SASLErrorException {
        this.f19215c = new SASLAnonymous(this);
        synchronized (this) {
            this.f19215c.m22188b(null, null, null, "");
            try {
                wait(this.f19213a.m22013y());
            } catch (InterruptedException unused) {
            }
        }
        if (this.f19217e != null) {
            throw new SASLErrorException(this.f19215c.toString(), this.f19217e);
        }
        if (!this.f19216d) {
            throw new SmackException.NoResponseException();
        }
    }

    /* renamed from: d */
    public void m22037d() {
        this.f19216d = true;
        synchronized (this) {
            notify();
        }
    }

    /* renamed from: e */
    public void m22038e(SASLMechanism.SASLFailure sASLFailure) {
        this.f19217e = sASLFailure;
        synchronized (this) {
            notify();
        }
    }

    /* renamed from: f */
    public void m22039f(String str) {
        this.f19215c.mo22184d(str);
    }

    /* renamed from: g */
    public boolean m22040g() {
        return this.f19214b.contains("ANONYMOUS");
    }

    /* renamed from: h */
    public boolean m22041h() {
        return (this.f19214b.isEmpty() || (this.f19214b.size() == 1 && m22040g())) ? false : true;
    }

    /* renamed from: i */
    public void m22042i() {
        this.f19216d = false;
        this.f19217e = null;
    }

    /* renamed from: k */
    public void m22043k(AbstractC5594b abstractC5594b) {
        this.f19213a.m21979P(abstractC5594b);
    }

    /* renamed from: l */
    public void m22044l(Collection<String> collection) {
        this.f19214b = collection;
    }
}
