package org.jivesoftware.smackx.hoxt.packet;

import org.apache.commons.lang3.StringUtils;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;

/* loaded from: classes.dex */
public class HttpOverXmppReq extends AbstractHttpOverXmpp {

    /* renamed from: p */
    public C5647a f19718p;

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.HttpOverXmppReq$a */
    public static class C5647a extends AbstractHttpOverXmpp.AbstractC5639a {

        /* renamed from: d */
        public HttpMethod f19719d;

        /* renamed from: e */
        public String f19720e;

        /* renamed from: f */
        public int f19721f = 0;

        /* renamed from: g */
        public boolean f19722g = true;

        /* renamed from: h */
        public boolean f19723h = true;

        /* renamed from: i */
        public boolean f19724i = true;

        public C5647a(HttpMethod httpMethod, String str) {
            this.f19719d = httpMethod;
            this.f19720e = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.AbstractC5639a
        /* renamed from: a */
        public String mo22520a() {
            return "</req>";
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.AbstractC5639a
        /* renamed from: b */
        public String mo22521b() {
            StringBuilder sb = new StringBuilder();
            sb.append("<req");
            sb.append(StringUtils.SPACE);
            sb.append("xmlns='");
            sb.append("urn:xmpp:http");
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("method='");
            sb.append(this.f19719d.toString());
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("resource='");
            sb.append(C5616j.m22341f(this.f19720e));
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("version='");
            sb.append(C5616j.m22341f(this.f19702c));
            sb.append("'");
            if (this.f19721f != 0) {
                sb.append(StringUtils.SPACE);
                sb.append("maxChunkSize='");
                sb.append(Integer.toString(this.f19721f));
                sb.append("'");
            }
            sb.append(StringUtils.SPACE);
            sb.append("sipub='");
            sb.append(Boolean.toString(this.f19722g));
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("ibb='");
            sb.append(Boolean.toString(this.f19723h));
            sb.append("'");
            sb.append(StringUtils.SPACE);
            sb.append("jingle='");
            sb.append(Boolean.toString(this.f19724i));
            sb.append("'");
            sb.append(">");
            return sb.toString();
        }

        /* renamed from: g */
        public void m22530g(boolean z8) {
            this.f19723h = z8;
        }

        /* renamed from: h */
        public void m22531h(boolean z8) {
            this.f19724i = z8;
        }

        /* renamed from: i */
        public void m22532i(int i9) {
            this.f19721f = i9;
        }

        /* renamed from: j */
        public void m22533j(boolean z8) {
            this.f19722g = z8;
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        return this.f19718p.m22525f();
    }

    /* renamed from: H */
    public void m22529H(C5647a c5647a) {
        this.f19718p = c5647a;
    }
}
