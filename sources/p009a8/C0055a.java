package p009a8;

import com.google.android.gms.plus.PlusShare;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5618l;
import p259z7.C6838b;

/* renamed from: a8.a */
/* loaded from: classes.dex */
public class C0055a implements InterfaceC5595c {

    /* renamed from: a */
    public String f148a;

    /* renamed from: b */
    public String f149b;

    /* renamed from: d */
    public b f151d;

    /* renamed from: c */
    public List<String> f150c = new ArrayList();

    /* renamed from: e */
    public final List<a> f152e = new ArrayList();

    /* renamed from: f */
    public final List<C6838b> f153f = new ArrayList();

    /* renamed from: a8.a$a */
    public static class a {

        /* renamed from: a */
        public List<C6838b> f154a;

        public a(List<C6838b> list) {
            new ArrayList();
            this.f154a = list;
        }

        /* renamed from: a */
        public List<C6838b> m204a() {
            return Collections.unmodifiableList(new ArrayList(this.f154a));
        }

        /* renamed from: b */
        public CharSequence m205b() {
            C5618l c5618l = new C5618l();
            c5618l.m22365p("item");
            Iterator<C6838b> it = m204a().iterator();
            while (it.hasNext()) {
                c5618l.m22353d(it.next().m25561n());
            }
            c5618l.m22356g("item");
            return c5618l;
        }
    }

    /* renamed from: a8.a$b */
    public static class b {

        /* renamed from: a */
        public List<C6838b> f155a;

        public b(List<C6838b> list) {
            new ArrayList();
            this.f155a = list;
        }

        /* renamed from: a */
        public List<C6838b> m206a() {
            return Collections.unmodifiableList(new ArrayList(this.f155a));
        }

        /* renamed from: b */
        public CharSequence m207b() {
            C5618l c5618l = new C5618l();
            c5618l.m22365p("reported");
            Iterator<C6838b> it = m206a().iterator();
            while (it.hasNext()) {
                c5618l.m22353d(it.next().m25561n());
            }
            c5618l.m22356g("reported");
            return c5618l;
        }
    }

    public C0055a(String str) {
        this.f148a = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "x";
    }

    /* renamed from: c */
    public void m192c(C6838b c6838b) {
        synchronized (this.f153f) {
            this.f153f.add(c6838b);
        }
    }

    /* renamed from: d */
    public void m193d(String str) {
        synchronized (this.f150c) {
            this.f150c.add(str);
        }
    }

    /* renamed from: e */
    public void m194e(a aVar) {
        synchronized (this.f152e) {
            this.f152e.add(aVar);
        }
    }

    /* renamed from: f */
    public List<C6838b> m195f() {
        List<C6838b> listUnmodifiableList;
        synchronized (this.f153f) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f153f));
        }
        return listUnmodifiableList;
    }

    /* renamed from: g */
    public List<String> m196g() {
        List<String> listUnmodifiableList;
        synchronized (this.f150c) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f150c));
        }
        return listUnmodifiableList;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "jabber:x:data";
    }

    /* renamed from: h */
    public List<a> m197h() {
        List<a> listUnmodifiableList;
        synchronized (this.f152e) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f152e));
        }
        return listUnmodifiableList;
    }

    /* renamed from: i */
    public b m198i() {
        return this.f151d;
    }

    /* renamed from: j */
    public String m199j() {
        return this.f149b;
    }

    /* renamed from: k */
    public String m200k() {
        return this.f148a;
    }

    /* renamed from: l */
    public void m201l(b bVar) {
        this.f151d = bVar;
    }

    /* renamed from: m */
    public void m202m(String str) {
        this.f149b = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public C5618l mo190a() {
        C5618l c5618l = new C5618l(this);
        c5618l.m22355f("type", m200k());
        c5618l.m22370u();
        c5618l.m22368s(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, m199j());
        Iterator<String> it = m196g().iterator();
        while (it.hasNext()) {
            c5618l.m22361l("instructions", it.next());
        }
        if (m198i() != null) {
            c5618l.append(m198i().m207b());
        }
        Iterator<a> it2 = m197h().iterator();
        while (it2.hasNext()) {
            c5618l.append(it2.next().m205b());
        }
        Iterator<C6838b> it3 = m195f().iterator();
        while (it3.hasNext()) {
            c5618l.m22353d(it3.next().m25561n());
        }
        c5618l.m22357h(this);
        return c5618l;
    }
}
