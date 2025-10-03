package org.jivesoftware.smack.packet;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Date;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class StreamMgmt extends AbstractC5594b {

    /* renamed from: l */
    public String f19296l;

    /* renamed from: m */
    public String f19297m = "U";

    /* renamed from: n */
    public Date f19298n = new Date(0);

    /* renamed from: o */
    public String f19299o;

    public StreamMgmt() {
        AbstractC5594b.m22153p("U");
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public C5618l mo22057u() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("r");
        c5618l.m22372w(mo22056m());
        c5618l.m22371v(m22135v());
        c5618l.m22367r(TtmlNode.ATTR_ID, m22161k());
        Date date = this.f19298n;
        if (date != null && date != new Date(0L)) {
            c5618l.m22367r("ts", String.valueOf(this.f19298n.getTime()));
        }
        c5618l.m22358i();
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: m */
    public String mo22056m() {
        return this.f19297m;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public String toString() {
        return mo22057u().toString();
    }

    /* renamed from: v */
    public String m22135v() {
        return this.f19296l;
    }

    /* renamed from: w */
    public String m22136w() {
        return this.f19299o;
    }

    /* renamed from: x */
    public Date m22137x() {
        return this.f19298n;
    }

    /* renamed from: y */
    public void m22138y(String str) {
        this.f19299o = str;
    }

    /* renamed from: z */
    public void m22139z(Date date) {
        this.f19298n = date;
    }
}
