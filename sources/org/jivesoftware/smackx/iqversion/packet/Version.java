package org.jivesoftware.smackx.iqversion.packet;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5616j;

/* loaded from: classes.dex */
public class Version extends AbstractC5586IQ {

    /* renamed from: p */
    public String f19745p;

    /* renamed from: q */
    public String f19746q;

    /* renamed from: r */
    public String f19747r;

    public Version(String str, String str2, String str3) {
        m22070F(AbstractC5586IQ.a.f19233d);
        this.f19745p = str;
        this.f19746q = str2;
        this.f19747r = str3;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: G, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<query xmlns=\"");
        sb.append("jabber:iq:version");
        sb.append("\">");
        if (this.f19745p != null) {
            sb.append("<name>");
            sb.append(C5616j.m22341f(this.f19745p));
            sb.append("</name>");
        }
        if (this.f19746q != null) {
            sb.append("<version>");
            sb.append(C5616j.m22341f(this.f19746q));
            sb.append("</version>");
        }
        if (this.f19747r != null) {
            sb.append("<os>");
            sb.append(C5616j.m22341f(this.f19747r));
            sb.append("</os>");
        }
        sb.append("</query>");
        return sb.toString();
    }
}
