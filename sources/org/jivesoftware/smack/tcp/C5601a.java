package org.jivesoftware.smack.tcp;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import p258z6.AbstractC6835b;
import p258z6.C6836c;

/* renamed from: org.jivesoftware.smack.tcp.a */
/* loaded from: classes.dex */
public class C5601a {

    /* renamed from: a */
    public Thread f19410a;

    /* renamed from: b */
    public XMPPTCPConnection f19411b;

    /* renamed from: c */
    public XmlPullParser f19412c;

    /* renamed from: d */
    public volatile boolean f19413d;

    /* renamed from: e */
    public volatile boolean f19414e;

    /* renamed from: org.jivesoftware.smack.tcp.a$a */
    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Exception {
            C5601a.this.m22225d(this);
        }
    }

    public C5601a(XMPPTCPConnection xMPPTCPConnection) throws XmlPullParserException, SmackException {
        this.f19411b = xMPPTCPConnection;
        m22223b();
    }

    /* renamed from: b */
    public void m22223b() throws XmlPullParserException, SmackException {
        this.f19414e = false;
        this.f19413d = false;
        a aVar = new a();
        this.f19410a = aVar;
        aVar.setName("Smack Packet Reader (" + this.f19411b.m22009t() + ")");
        this.f19410a.setDaemon(true);
        m22226e();
    }

    /* renamed from: c */
    public final void m22224c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, SmackException.SecurityRequiredException {
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        while (!z8) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("starttls")) {
                    z9 = true;
                } else if (xmlPullParser.getName().equals("mechanisms")) {
                    this.f19411b.mo21965B().m22044l(PacketParserUtils.m22250m(xmlPullParser));
                } else if (xmlPullParser.getName().equals("bind")) {
                    this.f19411b.mo21981R();
                } else if (xmlPullParser.getName().equals("c")) {
                    String attributeValue = xmlPullParser.getAttributeValue(null, "node");
                    String attributeValue2 = xmlPullParser.getAttributeValue(null, "ver");
                    if (attributeValue2 != null && attributeValue != null) {
                        this.f19411b.mo21987X(attributeValue + "#" + attributeValue2);
                    }
                } else if (xmlPullParser.getName().equals("session")) {
                    this.f19411b.mo21983T();
                } else if (xmlPullParser.getName().equals("ver")) {
                    if (xmlPullParser.getNamespace().equals("urn:xmpp:features:rosterver")) {
                        this.f19411b.mo21986W();
                    }
                } else if (xmlPullParser.getName().equals("compression")) {
                    this.f19411b.m22217v0(PacketParserUtils.m22243f(xmlPullParser));
                } else if (xmlPullParser.getName().equals("register")) {
                    this.f19411b.mo21982S();
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("starttls")) {
                    this.f19411b.m22220y0(z10);
                } else if (xmlPullParser.getName().equals("required") && z9) {
                    z10 = true;
                } else if (xmlPullParser.getName().equals("features")) {
                    z8 = true;
                }
            }
        }
        if (!this.f19411b.m22204i0() && !z9 && this.f19411b.mo22008s().m21934k() == ConnectionConfiguration.SecurityMode.required) {
            throw new SmackException.SecurityRequiredException();
        }
        if (!z9 || this.f19411b.mo22008s().m21934k() == ConnectionConfiguration.SecurityMode.disabled) {
            this.f19413d = true;
            synchronized (this) {
                notify();
            }
        }
    }

    /* renamed from: d */
    public final void m22225d(Thread thread) throws Exception {
        try {
            int eventType = this.f19412c.getEventType();
            do {
                if (eventType == 2) {
                    int depth = this.f19412c.getDepth();
                    AbstractC6835b abstractC6835bM22200e0 = this.f19411b.m22200e0();
                    if (this.f19412c.getName().equals("message")) {
                        try {
                            this.f19411b.mo21974K(PacketParserUtils.m22251n(this.f19412c));
                        } catch (Exception e9) {
                            C6836c c6836c = new C6836c(PacketParserUtils.m22245h(this.f19412c, depth), e9);
                            if (abstractC6835bM22200e0 != null) {
                                abstractC6835bM22200e0.mo25541a(c6836c);
                            }
                        }
                    } else if (this.f19412c.getName().equals("iq")) {
                        try {
                            this.f19411b.mo21974K(PacketParserUtils.m22249l(this.f19412c, this.f19411b));
                        } catch (Exception e10) {
                            C6836c c6836c2 = new C6836c(PacketParserUtils.m22245h(this.f19412c, depth), e10);
                            if (abstractC6835bM22200e0 != null) {
                                abstractC6835bM22200e0.mo25541a(c6836c2);
                            }
                        }
                    } else if (this.f19412c.getName().equals("presence")) {
                        try {
                            this.f19411b.mo21974K(PacketParserUtils.m22253p(this.f19412c));
                        } catch (Exception e11) {
                            C6836c c6836c3 = new C6836c(PacketParserUtils.m22245h(this.f19412c, depth), e11);
                            if (abstractC6835bM22200e0 != null) {
                                abstractC6835bM22200e0.mo25541a(c6836c3);
                            }
                        }
                    } else if (this.f19412c.getName().equals("r")) {
                        try {
                            this.f19411b.mo21974K(PacketParserUtils.m22259v(this.f19412c));
                        } catch (Exception e12) {
                            C6836c c6836c4 = new C6836c(PacketParserUtils.m22245h(this.f19412c, depth), e12);
                            if (abstractC6835bM22200e0 != null) {
                                abstractC6835bM22200e0.mo25541a(c6836c4);
                            }
                        }
                    } else if (!this.f19412c.getName().equals("stream")) {
                        if (this.f19412c.getName().equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                            throw new XMPPException.StreamErrorException(PacketParserUtils.m22258u(this.f19412c));
                        }
                        if (this.f19412c.getName().equals("features")) {
                            m22224c(this.f19412c);
                        } else if (this.f19412c.getName().equals("proceed")) {
                            this.f19411b.m22213r0();
                            m22226e();
                        } else if (this.f19412c.getName().equals("failure")) {
                            String namespace = this.f19412c.getNamespace(null);
                            if ("urn:ietf:params:xml:ns:xmpp-tls".equals(namespace)) {
                                throw new Exception("TLS negotiation has failed");
                            }
                            if ("http://jabber.org/protocol/compress".equals(namespace)) {
                                this.f19411b.m22221z0();
                            } else {
                                SASLMechanism.SASLFailure sASLFailureM22257t = PacketParserUtils.m22257t(this.f19412c);
                                this.f19411b.mo21974K(sASLFailureM22257t);
                                this.f19411b.mo21965B().m22038e(sASLFailureM22257t);
                            }
                        } else if (this.f19412c.getName().equals("challenge")) {
                            String strNextText = this.f19412c.nextText();
                            this.f19411b.mo21974K(new SASLMechanism.Challenge(strNextText));
                            this.f19411b.mo21965B().m22039f(strNextText);
                        } else if (this.f19412c.getName().equals(FirebaseAnalytics.Param.SUCCESS)) {
                            this.f19411b.mo21974K(new SASLMechanism.Success(this.f19412c.nextText()));
                            this.f19411b.f19404L.m22232d();
                            m22226e();
                            this.f19411b.mo21965B().m22037d();
                        } else if (this.f19412c.getName().equals("compressed")) {
                            this.f19411b.m22219x0();
                            m22226e();
                        } else if (this.f19412c.getName().equals("clresumed")) {
                            try {
                                this.f19411b.mo21974K(PacketParserUtils.m22242e(this.f19412c));
                            } catch (Exception e13) {
                                C6836c c6836c5 = new C6836c(PacketParserUtils.m22245h(this.f19412c, depth), e13);
                                if (abstractC6835bM22200e0 != null) {
                                    abstractC6835bM22200e0.mo25541a(c6836c5);
                                }
                            }
                        }
                    } else if ("jabber:client".equals(this.f19412c.getNamespace(null))) {
                        for (int i9 = 0; i9 < this.f19412c.getAttributeCount(); i9++) {
                            if (this.f19412c.getAttributeName(i9).equals(TtmlNode.ATTR_ID)) {
                                this.f19411b.f19396D = this.f19412c.getAttributeValue(i9);
                            } else if (this.f19412c.getAttributeName(i9).equals(Constants.MessagePayloadKeys.FROM)) {
                                this.f19411b.mo21988Y(this.f19412c.getAttributeValue(i9));
                            }
                        }
                    }
                    if (!this.f19414e || eventType == 1) {
                        break;
                    }
                } else if (eventType == 3 && this.f19412c.getName().equals("stream")) {
                    this.f19411b.m22004o();
                }
                eventType = this.f19412c.next();
                if (!this.f19414e) {
                    break;
                    break;
                }
                break;
            } while (thread == this.f19410a);
            if (eventType == 1) {
                throw new SmackException.NoResponseException();
            }
        } catch (Exception e14) {
            if (this.f19414e || this.f19411b.m22205j0()) {
                return;
            }
            synchronized (this) {
                notify();
                this.f19411b.m22211p0(e14);
            }
        }
    }

    /* renamed from: e */
    public final void m22226e() throws XmlPullParserException, SmackException {
        try {
            XmlPullParser xmlPullParserM22241d = PacketParserUtils.m22241d();
            this.f19412c = xmlPullParserM22241d;
            xmlPullParserM22241d.setInput(this.f19411b.mo21964A());
        } catch (XmlPullParserException e9) {
            throw new SmackException(e9);
        }
    }

    /* renamed from: f */
    public void m22227f() {
        this.f19414e = true;
    }

    /* renamed from: g */
    public synchronized void m22228g() {
        this.f19410a.start();
        try {
            wait(this.f19411b.m22013y());
        } catch (InterruptedException unused) {
        }
        if (!this.f19413d) {
            this.f19411b.mo21991b0();
        }
    }
}
