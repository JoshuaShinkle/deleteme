package org.jivesoftware.smackx.hoxt.packet;

import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;

/* loaded from: classes.dex */
public class HttpOverXmppResp extends AbstractHttpOverXmpp {

    /* renamed from: p */
    public C5648a f19725p;

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.HttpOverXmppResp$a */
    public static class C5648a extends AbstractHttpOverXmpp.AbstractC5639a {

        /* renamed from: d */
        public int f19726d;

        /* renamed from: e */
        public String f19727e = null;

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.AbstractC5639a
        /* renamed from: a */
        public String mo22520a() {
            return "</resp>";
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.AbstractC5639a
        /* renamed from: b */
        public String mo22521b() {
            StringBuilder sb = new StringBuilder();
            sb.append("<resp");
            sb.append(StringUtils.SPACE);
            sb.append("xmlns='");
            sb.append("urn:xmpp:http");
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("version='");
            sb.append(C5616j.m22341f(this.f19702c));
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("statusCode='");
            sb.append(Integer.toString(this.f19726d));
            sb.append("'");
            if (this.f19727e != null) {
                sb.append(StringUtils.SPACE);
                sb.append("statusMessage='");
                sb.append(C5616j.m22341f(this.f19727e));
                sb.append("'");
            }
            sb.append(">");
            return sb.toString();
        }

        /* renamed from: g */
        public void m22536g(int i9) {
            this.f19726d = i9;
        }

        /* renamed from: h */
        public void m22537h(String str) {
            this.f19727e = str;
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return this.f19725p.m22525f();
    }

    /* renamed from: H */
    public void m22535H(C5648a c5648a) {
        this.f19725p = c5648a;
    }
}
