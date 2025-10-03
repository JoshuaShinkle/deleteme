package p169p7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: p7.a */
/* loaded from: classes.dex */
public class C6114a implements InterfaceC6115b {

    /* renamed from: a */
    public String f20748a;

    /* renamed from: b */
    public String f20749b;

    /* renamed from: c */
    public Map<String, String> f20750c;

    public C6114a(String str, String str2) {
        this.f20748a = str;
        this.f20749b = str2;
    }

    /* renamed from: b */
    public synchronized Set<String> m23453b() {
        Map<String, String> map = this.f20750c;
        if (map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }

    /* renamed from: c */
    public synchronized String m23454c(String str) {
        Map<String, String> map = this.f20750c;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: d */
    public synchronized void m23455d(String str, String str2) {
        if (this.f20750c == null) {
            this.f20750c = new HashMap();
        }
        this.f20750c.put(str, str2);
    }

    @Override // p169p7.InterfaceC6115b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo23452a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.f20748a);
        sb.append(" xmlns=\"");
        sb.append(this.f20749b);
        sb.append("\">");
        for (String str : m23453b()) {
            String strM23454c = m23454c(str);
            sb.append("<");
            sb.append(str);
            sb.append(">");
            sb.append(strM23454c);
            sb.append("</");
            sb.append(str);
            sb.append(">");
        }
        sb.append("</");
        sb.append(this.f20748a);
        sb.append(">");
        return sb.toString();
    }
}
