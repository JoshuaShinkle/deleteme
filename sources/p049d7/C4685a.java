package p049d7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: d7.a */
/* loaded from: classes.dex */
public class C4685a implements InterfaceC5595c {

    /* renamed from: a */
    public List<b> f16397a = new ArrayList();

    /* renamed from: d7.a$b */
    public static class b {

        /* renamed from: a */
        public String f16398a;

        /* renamed from: b */
        public String f16399b;

        /* renamed from: c */
        public String f16400c;

        /* renamed from: d */
        public String f16401d;

        /* renamed from: e */
        public boolean f16402e;

        /* renamed from: f */
        public String f16403f;

        /* renamed from: g */
        public final void m18737g(boolean z8) {
            this.f16402e = z8;
        }

        /* renamed from: h */
        public final void m18738h(String str) {
            this.f16401d = str;
        }

        /* renamed from: i */
        public final void m18739i(String str) {
            this.f16399b = str;
        }

        /* renamed from: j */
        public final void m18740j(String str) {
            this.f16400c = str;
        }

        /* renamed from: k */
        public final void m18741k(String str) {
            this.f16403f = str;
        }

        /* renamed from: l */
        public final String m18742l() {
            StringBuilder sb = new StringBuilder();
            sb.append("<address type=\"");
            sb.append(this.f16398a);
            sb.append("\"");
            if (this.f16399b != null) {
                sb.append(" jid=\"");
                sb.append(this.f16399b);
                sb.append("\"");
            }
            if (this.f16400c != null) {
                sb.append(" node=\"");
                sb.append(this.f16400c);
                sb.append("\"");
            }
            String str = this.f16401d;
            if (str != null && str.trim().length() > 0) {
                sb.append(" desc=\"");
                sb.append(this.f16401d);
                sb.append("\"");
            }
            if (this.f16402e) {
                sb.append(" delivered=\"true\"");
            }
            if (this.f16403f != null) {
                sb.append(" uri=\"");
                sb.append(this.f16403f);
                sb.append("\"");
            }
            sb.append("/>");
            return sb.toString();
        }

        public b(String str) {
            this.f16398a = str;
        }
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "addresses";
    }

    /* renamed from: c */
    public void m18729c(String str, String str2, String str3, String str4, boolean z8, String str5) {
        b bVar = new b(str);
        bVar.m18739i(str2);
        bVar.m18740j(str3);
        bVar.m18738h(str4);
        bVar.m18737g(z8);
        bVar.m18741k(str5);
        this.f16397a.add(bVar);
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append("http://jabber.org/protocol/address");
        sb.append("\">");
        Iterator<b> it = this.f16397a.iterator();
        while (it.hasNext()) {
            sb.append(it.next().m18742l());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/address";
    }
}
