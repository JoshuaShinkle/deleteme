package org.jivesoftware.smackx.disco.packet;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class DiscoverItems extends AbstractC5586IQ {

    /* renamed from: p */
    public final List<C5638a> f19694p = new LinkedList();

    /* renamed from: q */
    public String f19695q;

    /* renamed from: org.jivesoftware.smackx.disco.packet.DiscoverItems$a */
    public static class C5638a {

        /* renamed from: a */
        public String f19696a;

        /* renamed from: b */
        public String f19697b;

        /* renamed from: c */
        public String f19698c;

        /* renamed from: d */
        public String f19699d;

        public C5638a(String str) {
            this.f19696a = str;
        }

        /* renamed from: a */
        public void m22515a(String str) {
            this.f19699d = str;
        }

        /* renamed from: b */
        public void m22516b(String str) {
            this.f19697b = str;
        }

        /* renamed from: c */
        public void m22517c(String str) {
            this.f19698c = str;
        }

        /* renamed from: d */
        public C5618l m22518d() {
            C5618l c5618l = new C5618l();
            c5618l.m22364o("item");
            c5618l.m22355f("jid", this.f19696a);
            c5618l.m22367r(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f19697b);
            c5618l.m22367r("node", this.f19698c);
            c5618l.m22367r("action", this.f19699d);
            c5618l.m22358i();
            return c5618l;
        }
    }

    /* renamed from: G */
    public void m22510G(C5638a c5638a) {
        this.f19694p.add(c5638a);
    }

    /* renamed from: H */
    public void m22511H(Collection<C5638a> collection) {
        if (collection == null) {
            return;
        }
        Iterator<C5638a> it = collection.iterator();
        while (it.hasNext()) {
            m22510G(it.next());
        }
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: I, reason: merged with bridge method [inline-methods] */
    public C5618l mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(SearchIntents.EXTRA_QUERY);
        c5618l.m22372w("http://jabber.org/protocol/disco#items");
        c5618l.m22367r("node", m22513J());
        c5618l.m22370u();
        Iterator<C5638a> it = this.f19694p.iterator();
        while (it.hasNext()) {
            c5618l.m22353d(it.next().m22518d());
        }
        c5618l.m22356g(SearchIntents.EXTRA_QUERY);
        return c5618l;
    }

    /* renamed from: J */
    public String m22513J() {
        return this.f19695q;
    }

    /* renamed from: K */
    public void m22514K(String str) {
        this.f19695q = str;
    }
}
