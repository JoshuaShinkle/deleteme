package p259z7;

import java.util.List;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import p009a8.C0055a;

/* renamed from: z7.a */
/* loaded from: classes.dex */
public class C6837a {

    /* renamed from: a */
    public C0055a f22722a;

    public C6837a(C0055a c0055a) {
        this.f22722a = c0055a;
    }

    /* renamed from: c */
    public static C6837a m25543c(AbstractC5594b abstractC5594b) {
        InterfaceC5595c interfaceC5595cM22157g = abstractC5594b.m22157g("x", "jabber:x:data");
        if (interfaceC5595cM22157g == null) {
            return null;
        }
        C0055a c0055a = (C0055a) interfaceC5595cM22157g;
        if (c0055a.m198i() == null) {
            return new C6837a(c0055a);
        }
        return null;
    }

    /* renamed from: a */
    public C0055a m25544a() {
        if (!m25547e()) {
            return this.f22722a;
        }
        C0055a c0055a = new C0055a(m25546d());
        for (C6838b c6838b : m25545b()) {
            if (!c6838b.m25554g().isEmpty()) {
                c0055a.m192c(c6838b);
            }
        }
        return c0055a;
    }

    /* renamed from: b */
    public List<C6838b> m25545b() {
        return this.f22722a.m195f();
    }

    /* renamed from: d */
    public String m25546d() {
        return this.f22722a.m200k();
    }

    /* renamed from: e */
    public final boolean m25547e() {
        return "submit".equals(this.f22722a.m200k());
    }
}
