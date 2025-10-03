package org.jivesoftware.smack.packet;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: org.jivesoftware.smack.packet.b */
/* loaded from: classes.dex */
public abstract class AbstractC5594b {

    /* renamed from: h */
    public static final String f19341h = Locale.getDefault().getLanguage().toLowerCase(Locale.US);

    /* renamed from: i */
    public static String f19342i = null;

    /* renamed from: j */
    public static String f19343j = C5616j.m22349n(5) + "-";

    /* renamed from: k */
    public static long f19344k = 0;

    /* renamed from: b */
    public String f19345b;

    /* renamed from: c */
    public String f19346c;

    /* renamed from: d */
    public String f19347d;

    /* renamed from: e */
    public String f19348e;

    /* renamed from: f */
    public final List<InterfaceC5595c> f19349f;

    /* renamed from: g */
    public XMPPError f19350g;

    public AbstractC5594b() {
        this.f19345b = f19342i;
        this.f19346c = null;
        this.f19347d = null;
        this.f19348e = null;
        this.f19349f = new CopyOnWriteArrayList();
        this.f19350g = null;
    }

    /* renamed from: d */
    public static String m22151d() {
        return f19341h;
    }

    /* renamed from: n */
    public static synchronized String m22152n() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append(f19343j);
        long j9 = f19344k;
        f19344k = 1 + j9;
        sb.append(Long.toString(j9));
        return sb.toString();
    }

    /* renamed from: p */
    public static void m22153p(String str) {
        f19342i = str;
    }

    /* renamed from: a */
    public void mo14052a(C5618l c5618l) {
        c5618l.m22367r(TtmlNode.ATTR_ID, m22161k());
        c5618l.m22367r("to", m22162l());
        c5618l.m22367r(Constants.MessagePayloadKeys.FROM, m22160j());
    }

    /* renamed from: b */
    public void m22154b(InterfaceC5595c interfaceC5595c) {
        if (interfaceC5595c == null) {
            return;
        }
        this.f19349f.add(interfaceC5595c);
    }

    /* renamed from: c */
    public void m22155c(Collection<InterfaceC5595c> collection) {
        if (collection == null) {
            return;
        }
        this.f19349f.addAll(collection);
    }

    /* renamed from: e */
    public XMPPError m22156e() {
        return this.f19350g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractC5594b abstractC5594b = (AbstractC5594b) obj;
        XMPPError xMPPError = this.f19350g;
        if (xMPPError == null ? abstractC5594b.f19350g != null : !xMPPError.equals(abstractC5594b.f19350g)) {
            return false;
        }
        String str = this.f19348e;
        if (str == null ? abstractC5594b.f19348e != null : !str.equals(abstractC5594b.f19348e)) {
            return false;
        }
        if (!this.f19349f.equals(abstractC5594b.f19349f)) {
            return false;
        }
        String str2 = this.f19346c;
        if (str2 == null ? abstractC5594b.f19346c != null : !str2.equals(abstractC5594b.f19346c)) {
            return false;
        }
        String str3 = this.f19347d;
        if (str3 == null ? abstractC5594b.f19347d != null : !str3.equals(abstractC5594b.f19347d)) {
            return false;
        }
        String str4 = this.f19345b;
        String str5 = abstractC5594b.f19345b;
        if (str4 != null) {
            if (str4.equals(str5)) {
                return true;
            }
        } else if (str5 == null) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    public <PE extends InterfaceC5595c> PE m22157g(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        Iterator<InterfaceC5595c> it = this.f19349f.iterator();
        while (it.hasNext()) {
            PE pe = (PE) it.next();
            if (str == null || str.equals(pe.mo191b())) {
                if (str2.equals(pe.getNamespace())) {
                    return pe;
                }
            }
        }
        return null;
    }

    /* renamed from: h */
    public synchronized Collection<InterfaceC5595c> m22158h() {
        return Collections.unmodifiableList(new ArrayList(this.f19349f));
    }

    public int hashCode() {
        String str = this.f19345b;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f19346c;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f19347d;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.f19348e;
        int iHashCode4 = (((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.f19349f.hashCode()) * 31;
        XMPPError xMPPError = this.f19350g;
        return iHashCode4 + (xMPPError != null ? xMPPError.hashCode() : 0);
    }

    /* renamed from: i */
    public synchronized CharSequence m22159i() {
        C5618l c5618l;
        c5618l = new C5618l();
        Iterator<InterfaceC5595c> it = m22158h().iterator();
        while (it.hasNext()) {
            c5618l.append(it.next().mo190a());
        }
        return c5618l;
    }

    /* renamed from: j */
    public String m22160j() {
        return this.f19348e;
    }

    /* renamed from: k */
    public String m22161k() {
        if ("ID_NOT_AVAILABLE".equals(this.f19346c)) {
            return null;
        }
        if (this.f19346c == null) {
            this.f19346c = m22152n();
        }
        return this.f19346c;
    }

    /* renamed from: l */
    public String m22162l() {
        return this.f19347d;
    }

    /* renamed from: m */
    public String mo22056m() {
        return this.f19345b;
    }

    /* renamed from: o */
    public void m22163o(InterfaceC5595c interfaceC5595c) {
        this.f19349f.remove(interfaceC5595c);
    }

    /* renamed from: q */
    public void m22164q(XMPPError xMPPError) {
        this.f19350g = xMPPError;
    }

    /* renamed from: r */
    public void m22165r(String str) {
        this.f19348e = str;
    }

    /* renamed from: s */
    public void m22166s(String str) {
        this.f19346c = str;
    }

    /* renamed from: t */
    public void m22167t(String str) {
        this.f19347d = str;
    }

    public String toString() {
        return mo22057u().toString();
    }

    /* renamed from: u */
    public abstract CharSequence mo22057u();

    public AbstractC5594b(AbstractC5594b abstractC5594b) {
        this.f19345b = f19342i;
        this.f19346c = null;
        this.f19347d = null;
        this.f19348e = null;
        this.f19349f = new CopyOnWriteArrayList();
        this.f19350g = null;
        this.f19346c = abstractC5594b.m22161k();
        this.f19347d = abstractC5594b.m22162l();
        this.f19348e = abstractC5594b.m22160j();
        this.f19345b = abstractC5594b.f19345b;
        this.f19350g = abstractC5594b.f19350g;
        Iterator<InterfaceC5595c> it = abstractC5594b.m22158h().iterator();
        while (it.hasNext()) {
            m22154b(it.next());
        }
    }
}
