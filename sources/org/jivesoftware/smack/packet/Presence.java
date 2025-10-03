package org.jivesoftware.smack.packet;

import com.google.firebase.messaging.Constants;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class Presence extends AbstractC5594b {

    /* renamed from: l */
    public Type f19257l = Type.available;

    /* renamed from: m */
    public String f19258m = null;

    /* renamed from: n */
    public int f19259n = Integer.MIN_VALUE;

    /* renamed from: o */
    public Mode f19260o = null;

    /* renamed from: p */
    public String f19261p;

    public enum Mode {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum Type {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error
    }

    public Presence(Type type) {
        m22117D(type);
    }

    /* renamed from: A */
    public void m22114A(Mode mode) {
        this.f19260o = mode;
    }

    /* renamed from: B */
    public void m22115B(int i9) {
        if (i9 >= -128 && i9 <= 128) {
            this.f19259n = i9;
            return;
        }
        throw new IllegalArgumentException("Priority value " + i9 + " is not valid. Valid range is -128 through 128.");
    }

    /* renamed from: C */
    public void m22116C(String str) {
        this.f19258m = str;
    }

    /* renamed from: D */
    public void m22117D(Type type) {
        if (type == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.f19257l = type;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public C5618l mo22057u() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("presence");
        c5618l.m22372w(mo22056m());
        c5618l.m22371v(m22119v());
        mo14052a(c5618l);
        Type type = this.f19257l;
        if (type != Type.available) {
            c5618l.m22354e("type", type);
        }
        c5618l.m22370u();
        c5618l.m22368s("status", this.f19258m);
        int i9 = this.f19259n;
        if (i9 != Integer.MIN_VALUE) {
            c5618l.m22361l(Constants.FirelogAnalytics.PARAM_PRIORITY, Integer.toString(i9));
        }
        Mode mode = this.f19260o;
        if (mode != null && mode != Mode.available) {
            c5618l.m22360k("show", mode);
        }
        c5618l.append(m22159i());
        XMPPError xMPPErrorM22156e = m22156e();
        if (xMPPErrorM22156e != null) {
            c5618l.append(xMPPErrorM22156e.m22144e());
        }
        c5618l.m22356g("presence");
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5594b
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f19257l);
        if (this.f19260o != null) {
            sb.append(": ");
            sb.append(this.f19260o);
        }
        if (m22121x() != null) {
            sb.append(" (");
            sb.append(m22121x());
            sb.append(")");
        }
        return sb.toString();
    }

    /* renamed from: v */
    public String m22119v() {
        return this.f19261p;
    }

    /* renamed from: w */
    public Mode m22120w() {
        return this.f19260o;
    }

    /* renamed from: x */
    public String m22121x() {
        return this.f19258m;
    }

    /* renamed from: y */
    public Type m22122y() {
        return this.f19257l;
    }

    /* renamed from: z */
    public void m22123z(String str) {
        this.f19261p = str;
    }
}
