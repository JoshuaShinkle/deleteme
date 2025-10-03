package org.jivesoftware.smackx.time.packet;

import java.util.Calendar;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.util.XmppDateTime;

/* loaded from: classes.dex */
public class Time extends AbstractC5586IQ {

    /* renamed from: r */
    public static final Logger f19913r = Logger.getLogger(Time.class.getName());

    /* renamed from: p */
    public String f19914p;

    /* renamed from: q */
    public String f19915q;

    public Time() {
        m22070F(AbstractC5586IQ.a.f19231b);
    }

    /* renamed from: G */
    public static Time m22737G(AbstractC5594b abstractC5594b) {
        Time time = new Time(Calendar.getInstance());
        time.m22070F(AbstractC5586IQ.a.f19233d);
        time.m22167t(abstractC5594b.m22160j());
        return time;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public String mo9675y() {
        StringBuilder sb = new StringBuilder();
        sb.append("<time xmlns='urn:xmpp:time'>");
        if (this.f19914p != null) {
            sb.append("<utc>");
            sb.append(this.f19914p);
            sb.append("</utc>");
            sb.append("<tzo>");
            sb.append(this.f19915q);
            sb.append("</tzo>");
        }
        sb.append("</time>");
        return sb.toString();
    }

    public Time(Calendar calendar) {
        this.f19915q = XmppDateTime.m22264a(calendar.getTimeZone());
        this.f19914p = XmppDateTime.m22269f(calendar.getTime());
    }
}
