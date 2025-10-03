package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class CLResumed extends AbstractC5594b {

    /* renamed from: l */
    public String f19221l;

    /* renamed from: m */
    public String f19222m = "urn:xmpp:custom:resume";

    /* renamed from: n */
    public String f19223n;

    /* renamed from: o */
    public String f19224o;

    /* renamed from: p */
    public long f19225p;

    public CLResumed() {
        AbstractC5594b.m22153p("urn:xmpp:custom:resume");
    }

    /* renamed from: A */
    public void m22053A(String str) {
        this.f19224o = str;
    }

    /* renamed from: B */
    public void m22054B(String str) {
        this.f19223n = str;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: C, reason: merged with bridge method [inline-methods] */
    public C5618l mo22057u() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("clresumed");
        c5618l.m22372w(mo22056m());
        c5618l.m22371v(m22059w());
        c5618l.m22367r("status", m22061y());
        c5618l.m22367r("sessionid", m22060x());
        c5618l.m22367r("expiration", String.valueOf(m22058v()));
        c5618l.m22358i();
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: m */
    public String mo22056m() {
        return this.f19222m;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public String toString() {
        return mo22057u().toString();
    }

    /* renamed from: v */
    public long m22058v() {
        return this.f19225p;
    }

    /* renamed from: w */
    public String m22059w() {
        return this.f19221l;
    }

    /* renamed from: x */
    public String m22060x() {
        return this.f19224o;
    }

    /* renamed from: y */
    public String m22061y() {
        return this.f19223n;
    }

    /* renamed from: z */
    public void m22062z(long j9) {
        this.f19225p = j9;
    }
}
