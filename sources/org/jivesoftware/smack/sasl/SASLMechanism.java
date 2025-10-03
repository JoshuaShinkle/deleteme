package org.jivesoftware.smack.sasl;

import de.measite.smack.Sasl;
import java.util.HashMap;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.RealmCallback;
import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.jivesoftware.smack.C5585d;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.C5616j;

/* loaded from: classes.dex */
public abstract class SASLMechanism implements CallbackHandler {

    /* renamed from: a */
    public C5585d f19381a;

    /* renamed from: b */
    public SaslClient f19382b;

    /* renamed from: c */
    public String f19383c;

    /* renamed from: d */
    public String f19384d;

    /* renamed from: e */
    public String f19385e;

    public static class AuthMechanism extends AbstractC5594b {

        /* renamed from: l */
        public final String f19386l;

        /* renamed from: m */
        public final String f19387m;

        public AuthMechanism(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("SASL mechanism name shouldn't be null.");
            }
            this.f19386l = str;
            this.f19387m = str2;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5594b
        /* renamed from: v, reason: merged with bridge method [inline-methods] */
        public String mo22057u() {
            StringBuilder sb = new StringBuilder();
            sb.append("<auth mechanism=\"");
            sb.append(this.f19386l);
            sb.append("\" xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.f19387m;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.f19387m);
            }
            sb.append("</auth>");
            return sb.toString();
        }
    }

    public static class Challenge extends AbstractC5594b {

        /* renamed from: l */
        public final String f19388l;

        public Challenge(String str) {
            this.f19388l = str;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5594b
        /* renamed from: v, reason: merged with bridge method [inline-methods] */
        public String mo22057u() {
            StringBuilder sb = new StringBuilder();
            sb.append("<challenge xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.f19388l;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.f19388l);
            }
            sb.append("</challenge>");
            return sb.toString();
        }
    }

    public static class SASLFailure extends AbstractC5594b {

        /* renamed from: l */
        public final SASLError f19390l;

        /* renamed from: m */
        public final String f19391m;

        public SASLFailure(String str) {
            SASLError sASLErrorM22186a = SASLError.m22186a(str);
            if (sASLErrorM22186a == null) {
                this.f19390l = SASLError.not_authorized;
            } else {
                this.f19390l = sASLErrorM22186a;
            }
            this.f19391m = str;
        }

        /* renamed from: v */
        public String m22194v() {
            return this.f19391m;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5594b
        /* renamed from: w, reason: merged with bridge method [inline-methods] */
        public String mo22057u() {
            return "<failure xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\"><" + this.f19391m + "/></failure>";
        }
    }

    public static class Success extends AbstractC5594b {

        /* renamed from: l */
        public final String f19392l;

        public Success(String str) {
            this.f19392l = str;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5594b
        /* renamed from: v, reason: merged with bridge method [inline-methods] */
        public String mo22057u() {
            StringBuilder sb = new StringBuilder();
            sb.append("<success xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.f19392l;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.f19392l);
            }
            sb.append("</success>");
            return sb.toString();
        }
    }

    public SASLMechanism(C5585d c5585d) {
        this.f19381a = c5585d;
    }

    /* renamed from: a */
    public void mo22183a() {
        m22190f().m22043k(new AuthMechanism(mo22185e(), this.f19382b.hasInitialResponse() ? C5616j.m22340e(this.f19382b.evaluateChallenge(new byte[0]), false) : null));
    }

    /* renamed from: b */
    public void m22188b(String str, String str2, String str3, String str4) {
        this.f19383c = str;
        this.f19384d = str4;
        this.f19385e = str2;
        this.f19382b = Sasl.createSaslClient(new String[]{mo22185e()}, null, "xmpp", str3, new HashMap(), this);
        mo22183a();
    }

    /* renamed from: c */
    public void m22189c(String str, CallbackHandler callbackHandler) {
        this.f19382b = Sasl.createSaslClient(new String[]{mo22185e()}, null, "xmpp", str, new HashMap(), callbackHandler);
        mo22183a();
    }

    /* renamed from: d */
    public void mo22184d(String str) {
        byte[] bArrEvaluateChallenge = str != null ? this.f19382b.evaluateChallenge(C5616j.m22336a(str)) : this.f19382b.evaluateChallenge(new byte[0]);
        m22190f().m22043k(bArrEvaluateChallenge == null ? new Response() : new Response(C5616j.m22340e(bArrEvaluateChallenge, false)));
    }

    /* renamed from: e */
    public abstract String mo22185e();

    /* renamed from: f */
    public C5585d m22190f() {
        return this.f19381a;
    }

    @Override // org.apache.harmony.javax.security.auth.callback.CallbackHandler
    public void handle(Callback[] callbackArr) throws UnsupportedCallbackException {
        for (int i9 = 0; i9 < callbackArr.length; i9++) {
            Callback callback = callbackArr[i9];
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(this.f19383c);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(this.f19384d.toCharArray());
            } else if (callback instanceof RealmCallback) {
                RealmCallback realmCallback = (RealmCallback) callback;
                realmCallback.setText(realmCallback.getDefaultText());
            } else if (!(callback instanceof RealmChoiceCallback)) {
                throw new UnsupportedCallbackException(callbackArr[i9]);
            }
        }
    }

    public static class Response extends AbstractC5594b {

        /* renamed from: l */
        public final String f19389l;

        public Response() {
            this.f19389l = null;
        }

        @Override // org.jivesoftware.smack.packet.AbstractC5594b
        /* renamed from: v, reason: merged with bridge method [inline-methods] */
        public String mo22057u() {
            StringBuilder sb = new StringBuilder();
            sb.append("<response xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.f19389l;
            if (str != null) {
                sb.append(str);
            }
            sb.append("</response>");
            return sb.toString();
        }

        public Response(String str) {
            if (str != null && str.trim().length() != 0) {
                this.f19389l = str;
            } else {
                this.f19389l = null;
            }
        }
    }
}
