package p259z7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: z7.b */
/* loaded from: classes.dex */
public class C6838b {

    /* renamed from: a */
    public String f22723a;

    /* renamed from: b */
    public boolean f22724b;

    /* renamed from: c */
    public String f22725c;

    /* renamed from: d */
    public String f22726d;

    /* renamed from: e */
    public String f22727e;

    /* renamed from: f */
    public final List<a> f22728f;

    /* renamed from: g */
    public final List<String> f22729g;

    /* renamed from: z7.b$a */
    public static class a {

        /* renamed from: a */
        public final String f22730a;

        /* renamed from: b */
        public String f22731b;

        public a(String str, String str2) {
            this.f22731b = str;
            this.f22730a = str2;
        }

        /* renamed from: a */
        public String m25562a() {
            return this.f22731b;
        }

        /* renamed from: b */
        public String m25563b() {
            return this.f22730a;
        }

        /* renamed from: c */
        public C5618l m25564c() {
            C5618l c5618l = new C5618l();
            c5618l.m22364o("option");
            c5618l.m22367r("label", m25562a());
            c5618l.m22370u();
            c5618l.m22361l("value", m25563b());
            c5618l.m22356g("option");
            return c5618l;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (!this.f22730a.equals(aVar.f22730a)) {
                return false;
            }
            String str = this.f22731b;
            if (str == null) {
                str = "";
            }
            String str2 = aVar.f22731b;
            return str.equals(str2 != null ? str2 : "");
        }

        public int hashCode() {
            int iHashCode = (this.f22730a.hashCode() + 37) * 37;
            String str = this.f22731b;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return m25562a();
        }
    }

    public C6838b(String str) {
        this.f22724b = false;
        this.f22728f = new ArrayList();
        this.f22729g = new ArrayList();
        this.f22726d = str;
    }

    /* renamed from: a */
    public void m25548a(a aVar) {
        synchronized (this.f22728f) {
            this.f22728f.add(aVar);
        }
    }

    /* renamed from: b */
    public void m25549b(String str) {
        synchronized (this.f22729g) {
            this.f22729g.add(str);
        }
    }

    /* renamed from: c */
    public String m25550c() {
        return this.f22723a;
    }

    /* renamed from: d */
    public String m25551d() {
        return this.f22725c;
    }

    /* renamed from: e */
    public List<a> m25552e() {
        List<a> listUnmodifiableList;
        synchronized (this.f22728f) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f22728f));
        }
        return listUnmodifiableList;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof C6838b) {
            return m25561n().equals(((C6838b) obj).m25561n());
        }
        return false;
    }

    /* renamed from: f */
    public String m25553f() {
        return this.f22727e;
    }

    /* renamed from: g */
    public List<String> m25554g() {
        List<String> listUnmodifiableList;
        synchronized (this.f22729g) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f22729g));
        }
        return listUnmodifiableList;
    }

    /* renamed from: h */
    public String m25555h() {
        return this.f22726d;
    }

    public int hashCode() {
        return m25561n().hashCode();
    }

    /* renamed from: i */
    public boolean m25556i() {
        return this.f22724b;
    }

    /* renamed from: j */
    public void m25557j(String str) {
        this.f22723a = str;
    }

    /* renamed from: k */
    public void m25558k(String str) {
        this.f22725c = str;
    }

    /* renamed from: l */
    public void m25559l(boolean z8) {
        this.f22724b = z8;
    }

    /* renamed from: m */
    public void m25560m(String str) {
        this.f22727e = str;
    }

    /* renamed from: n */
    public C5618l m25561n() {
        C5618l c5618l = new C5618l();
        c5618l.m22364o("field");
        c5618l.m22367r("label", m25551d());
        c5618l.m22367r("var", m25555h());
        c5618l.m22367r("type", m25553f());
        c5618l.m22370u();
        c5618l.m22368s("desc", m25550c());
        c5618l.m22359j(m25556i(), "required");
        Iterator<String> it = m25554g().iterator();
        while (it.hasNext()) {
            c5618l.m22361l("value", it.next());
        }
        Iterator<a> it2 = m25552e().iterator();
        while (it2.hasNext()) {
            c5618l.m22353d(it2.next().m25564c());
        }
        c5618l.m22356g("field");
        return c5618l;
    }

    public C6838b() {
        this.f22724b = false;
        this.f22728f = new ArrayList();
        this.f22729g = new ArrayList();
        this.f22727e = "fixed";
    }
}
