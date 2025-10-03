package org.jivesoftware.smackx.hoxt.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import p250y7.C6794b;

/* loaded from: classes.dex */
public abstract class AbstractHttpOverXmpp extends AbstractC5586IQ {

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$a */
    public static abstract class AbstractC5639a {

        /* renamed from: a */
        public C6794b f19700a;

        /* renamed from: b */
        public C5642d f19701b;

        /* renamed from: c */
        public String f19702c;

        /* renamed from: a */
        public abstract String mo22520a();

        /* renamed from: b */
        public abstract String mo22521b();

        /* renamed from: c */
        public void m22522c(C5642d c5642d) {
            this.f19701b = c5642d;
        }

        /* renamed from: d */
        public void m22523d(C6794b c6794b) {
            this.f19700a = c6794b;
        }

        /* renamed from: e */
        public void m22524e(String str) {
            this.f19702c = str;
        }

        /* renamed from: f */
        public String m22525f() {
            return mo22521b() + this.f19700a.mo190a() + this.f19701b.m22527a() + mo22520a();
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$b */
    public static class C5640b implements InterfaceC5643e {

        /* renamed from: a */
        public final String f19703a;

        public C5640b(String str) {
            this.f19703a = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.InterfaceC5643e
        /* renamed from: a */
        public String mo22526a() {
            StringBuilder sb = new StringBuilder();
            sb.append("<base64>");
            String str = this.f19703a;
            if (str != null) {
                sb.append(str);
            }
            sb.append("</base64>");
            return sb.toString();
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$c */
    public static class C5641c implements InterfaceC5643e {

        /* renamed from: a */
        public final String f19704a;

        public C5641c(String str) {
            this.f19704a = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.InterfaceC5643e
        /* renamed from: a */
        public String mo22526a() {
            return "<chunkedBase64 streamId='" + this.f19704a + "'/>";
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$d */
    public static class C5642d {

        /* renamed from: a */
        public final InterfaceC5643e f19705a;

        public C5642d(InterfaceC5643e interfaceC5643e) {
            this.f19705a = interfaceC5643e;
        }

        /* renamed from: a */
        public String m22527a() {
            return "<data>" + this.f19705a.mo22526a() + "</data>";
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$e */
    public interface InterfaceC5643e {
        /* renamed from: a */
        String mo22526a();
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$f */
    public static class C5644f implements InterfaceC5643e {

        /* renamed from: a */
        public final String f19706a;

        public C5644f(String str) {
            this.f19706a = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.InterfaceC5643e
        /* renamed from: a */
        public String mo22526a() {
            return "<ibb sid='" + this.f19706a + "'/>";
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$g */
    public static class C5645g implements InterfaceC5643e {

        /* renamed from: a */
        public final String f19707a;

        public C5645g(String str) {
            this.f19707a = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.InterfaceC5643e
        /* renamed from: a */
        public String mo22526a() {
            StringBuilder sb = new StringBuilder();
            sb.append("<text>");
            String str = this.f19707a;
            if (str != null) {
                sb.append(str);
            }
            sb.append("</text>");
            return sb.toString();
        }
    }

    /* renamed from: org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp$h */
    public static class C5646h implements InterfaceC5643e {

        /* renamed from: a */
        public final String f19708a;

        public C5646h(String str) {
            this.f19708a = str;
        }

        @Override // org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.InterfaceC5643e
        /* renamed from: a */
        public String mo22526a() {
            StringBuilder sb = new StringBuilder();
            sb.append("<xml>");
            String str = this.f19708a;
            if (str != null) {
                sb.append(str);
            }
            sb.append("</xml>");
            return sb.toString();
        }
    }
}
