package org.jivesoftware.smack.packet;

import com.google.firebase.messaging.Constants;
import java.util.Locale;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: org.jivesoftware.smack.packet.IQ */
/* loaded from: classes.dex */
public abstract class AbstractC5586IQ extends AbstractC5594b {

    /* renamed from: l */
    public a f19226l;

    /* renamed from: m */
    public String f19227m;

    /* renamed from: n */
    public String f19228n;

    /* renamed from: o */
    public String f19229o;

    /* renamed from: org.jivesoftware.smack.packet.IQ$a */
    public static class a {

        /* renamed from: b */
        public static final a f19231b = new a("get");

        /* renamed from: c */
        public static final a f19232c = new a("set");

        /* renamed from: d */
        public static final a f19233d = new a("result");

        /* renamed from: e */
        public static final a f19234e = new a(Constants.IPC_BUNDLE_KEY_SEND_ERROR);

        /* renamed from: a */
        public String f19235a;

        public a(String str) {
            this.f19235a = str;
        }

        /* renamed from: a */
        public static a m22074a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase(Locale.US);
            a aVar = f19231b;
            if (aVar.toString().equals(lowerCase)) {
                return aVar;
            }
            a aVar2 = f19232c;
            if (aVar2.toString().equals(lowerCase)) {
                return aVar2;
            }
            a aVar3 = f19234e;
            if (aVar3.toString().equals(lowerCase)) {
                return aVar3;
            }
            a aVar4 = f19233d;
            if (aVar4.toString().equals(lowerCase)) {
                return aVar4;
            }
            return null;
        }

        public String toString() {
            return this.f19235a;
        }
    }

    public AbstractC5586IQ() {
        this.f19226l = a.f19231b;
        this.f19227m = null;
        this.f19228n = null;
        this.f19229o = null;
    }

    /* renamed from: v */
    public static AbstractC5586IQ m22063v(AbstractC5586IQ abstractC5586IQ, XMPPError xMPPError) {
        if (abstractC5586IQ.m22066B() != a.f19231b && abstractC5586IQ.m22066B() != a.f19232c) {
            throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + ((Object) abstractC5586IQ.mo22057u()));
        }
        AbstractC5586IQ abstractC5586IQ2 = new AbstractC5586IQ() { // from class: org.jivesoftware.smack.packet.IQ.2
            @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
            /* renamed from: y */
            public CharSequence mo9675y() {
                return AbstractC5586IQ.this.mo9675y();
            }
        };
        abstractC5586IQ2.m22070F(a.f19234e);
        abstractC5586IQ2.m22166s(abstractC5586IQ.m22161k());
        abstractC5586IQ2.m22165r(abstractC5586IQ.m22162l());
        abstractC5586IQ2.m22167t(abstractC5586IQ.m22160j());
        abstractC5586IQ2.m22164q(xMPPError);
        return abstractC5586IQ2;
    }

    /* renamed from: w */
    public static AbstractC5586IQ m22064w(AbstractC5586IQ abstractC5586IQ) {
        if (abstractC5586IQ.m22066B() != a.f19231b && abstractC5586IQ.m22066B() != a.f19232c) {
            throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + ((Object) abstractC5586IQ.mo22057u()));
        }
        AbstractC5586IQ abstractC5586IQ2 = new AbstractC5586IQ() { // from class: org.jivesoftware.smack.packet.IQ.1
            @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
            /* renamed from: G, reason: merged with bridge method [inline-methods] */
            public String mo9675y() {
                return null;
            }
        };
        abstractC5586IQ2.m22070F(a.f19233d);
        abstractC5586IQ2.m22166s(abstractC5586IQ.m22161k());
        abstractC5586IQ2.m22165r(abstractC5586IQ.m22162l());
        abstractC5586IQ2.m22167t(abstractC5586IQ.m22160j());
        return abstractC5586IQ2;
    }

    /* renamed from: A */
    public String m22065A() {
        return this.f19229o;
    }

    /* renamed from: B */
    public a m22066B() {
        return this.f19226l;
    }

    /* renamed from: C */
    public void m22067C(String str) {
        this.f19227m = str;
    }

    /* renamed from: D */
    public void m22068D(String str) {
        this.f19228n = str;
    }

    /* renamed from: E */
    public void m22069E(String str) {
        this.f19229o = str;
    }

    /* renamed from: F */
    public void m22070F(a aVar) {
        if (aVar == null) {
            this.f19226l = a.f19231b;
        } else {
            this.f19226l = aVar;
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: u */
    public CharSequence mo22057u() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("iq");
        mo14052a(c5618l);
        a aVar = this.f19226l;
        if (aVar == null) {
            c5618l.m22355f("type", "get");
        } else {
            c5618l.m22355f("type", aVar.toString());
        }
        c5618l.m22370u();
        c5618l.m22366q(mo9675y());
        XMPPError xMPPErrorM22156e = m22156e();
        if (xMPPErrorM22156e != null) {
            c5618l.append(xMPPErrorM22156e.m22144e());
        }
        c5618l.m22356g("iq");
        return c5618l;
    }

    /* renamed from: x */
    public String m22071x() {
        return this.f19227m;
    }

    /* renamed from: y */
    public abstract CharSequence mo9675y();

    /* renamed from: z */
    public String m22072z() {
        return this.f19228n;
    }

    public AbstractC5586IQ(AbstractC5586IQ abstractC5586IQ) {
        super(abstractC5586IQ);
        this.f19226l = a.f19231b;
        this.f19227m = null;
        this.f19228n = null;
        this.f19229o = null;
        this.f19226l = abstractC5586IQ.m22066B();
    }
}
