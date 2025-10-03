package org.jivesoftware.smack.packet;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.C5618l;

/* loaded from: classes.dex */
public class RosterPacket extends AbstractC5586IQ {

    /* renamed from: p */
    public final List<C5590a> f19278p = new ArrayList();

    /* renamed from: q */
    public String f19279q;

    public enum ItemStatus {
        subscribe,
        unsubscribe;


        /* renamed from: d */
        public static final ItemStatus f19282d;

        /* renamed from: e */
        public static final ItemStatus f19283e;

        static {
            ItemStatus itemStatus = subscribe;
            ItemStatus itemStatus2 = unsubscribe;
            f19282d = itemStatus;
            f19283e = itemStatus2;
        }

        /* renamed from: a */
        public static ItemStatus m22129a(String str) {
            if (str == null) {
                return null;
            }
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    public enum ItemType {
        none,
        to,
        from,
        both,
        remove
    }

    /* renamed from: org.jivesoftware.smack.packet.RosterPacket$a */
    public static class C5590a {

        /* renamed from: a */
        public String f19291a;

        /* renamed from: b */
        public String f19292b;

        /* renamed from: c */
        public ItemType f19293c = null;

        /* renamed from: d */
        public ItemStatus f19294d = null;

        /* renamed from: e */
        public final Set<String> f19295e = new CopyOnWriteArraySet();

        public C5590a(String str, String str2) {
            this.f19291a = str.toLowerCase(Locale.US);
            this.f19292b = str2;
        }

        /* renamed from: a */
        public void m22130a(String str) {
            this.f19295e.add(str);
        }

        /* renamed from: b */
        public void m22131b(ItemStatus itemStatus) {
            this.f19294d = itemStatus;
        }

        /* renamed from: c */
        public void m22132c(ItemType itemType) {
            this.f19293c = itemType;
        }

        /* renamed from: d */
        public String m22133d() {
            StringBuilder sb = new StringBuilder();
            sb.append("<item jid=\"");
            sb.append(C5616j.m22341f(this.f19291a));
            sb.append("\"");
            if (this.f19292b != null) {
                sb.append(" name=\"");
                sb.append(C5616j.m22341f(this.f19292b));
                sb.append("\"");
            }
            if (this.f19293c != null) {
                sb.append(" subscription=\"");
                sb.append(this.f19293c);
                sb.append("\"");
            }
            if (this.f19294d != null) {
                sb.append(" ask=\"");
                sb.append(this.f19294d);
                sb.append("\"");
            }
            sb.append(">");
            for (String str : this.f19295e) {
                sb.append("<group>");
                sb.append(C5616j.m22341f(str));
                sb.append("</group>");
            }
            sb.append("</item>");
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C5590a c5590a = (C5590a) obj;
            Set<String> set = this.f19295e;
            if (set == null) {
                if (c5590a.f19295e != null) {
                    return false;
                }
            } else if (!set.equals(c5590a.f19295e)) {
                return false;
            }
            if (this.f19294d != c5590a.f19294d || this.f19293c != c5590a.f19293c) {
                return false;
            }
            String str = this.f19292b;
            if (str == null) {
                if (c5590a.f19292b != null) {
                    return false;
                }
            } else if (!str.equals(c5590a.f19292b)) {
                return false;
            }
            String str2 = this.f19291a;
            if (str2 == null) {
                if (c5590a.f19291a != null) {
                    return false;
                }
            } else if (!str2.equals(c5590a.f19291a)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            Set<String> set = this.f19295e;
            int iHashCode = ((set == null ? 0 : set.hashCode()) + 31) * 31;
            ItemStatus itemStatus = this.f19294d;
            int iHashCode2 = (iHashCode + (itemStatus == null ? 0 : itemStatus.hashCode())) * 31;
            ItemType itemType = this.f19293c;
            int iHashCode3 = (iHashCode2 + (itemType == null ? 0 : itemType.hashCode())) * 31;
            String str = this.f19292b;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.f19291a;
            return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
        }
    }

    /* renamed from: G */
    public void m22127G(C5590a c5590a) {
        synchronized (this.f19278p) {
            this.f19278p.add(c5590a);
        }
    }

    /* renamed from: H */
    public void m22128H(String str) {
        this.f19279q = str;
    }

    @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
    /* renamed from: y */
    public CharSequence mo9675y() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o(SearchIntents.EXTRA_QUERY);
        c5618l.m22372w("jabber:iq:roster");
        c5618l.m22367r("ver", this.f19279q);
        c5618l.m22370u();
        synchronized (this.f19278p) {
            Iterator<C5590a> it = this.f19278p.iterator();
            while (it.hasNext()) {
                c5618l.append(it.next().m22133d());
            }
        }
        c5618l.m22356g(SearchIntents.EXTRA_QUERY);
        return c5618l;
    }
}
