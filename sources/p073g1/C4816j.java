package p073g1;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: g1.j */
/* loaded from: classes.dex */
public final class C4816j implements InterfaceC4814h {

    /* renamed from: c */
    public final Map<String, List<InterfaceC4815i>> f16757c;

    /* renamed from: d */
    public volatile Map<String, String> f16758d;

    /* renamed from: g1.j$a */
    public static final class a {

        /* renamed from: d */
        public static final String f16759d;

        /* renamed from: e */
        public static final Map<String, List<InterfaceC4815i>> f16760e;

        /* renamed from: a */
        public boolean f16761a = true;

        /* renamed from: b */
        public Map<String, List<InterfaceC4815i>> f16762b = f16760e;

        /* renamed from: c */
        public boolean f16763c = true;

        static {
            String strM19115b = m19115b();
            f16759d = strM19115b;
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(strM19115b)) {
                map.put(HttpHeaders.USER_AGENT, Collections.singletonList(new b(strM19115b)));
            }
            f16760e = Collections.unmodifiableMap(map);
        }

        /* renamed from: b */
        public static String m19115b() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = property.charAt(i9);
                if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                    sb.append(cCharAt);
                } else {
                    sb.append('?');
                }
            }
            return sb.toString();
        }

        /* renamed from: a */
        public C4816j m19116a() {
            this.f16761a = true;
            return new C4816j(this.f16762b);
        }
    }

    /* renamed from: g1.j$b */
    public static final class b implements InterfaceC4815i {

        /* renamed from: a */
        public final String f16764a;

        public b(String str) {
            this.f16764a = str;
        }

        @Override // p073g1.InterfaceC4815i
        /* renamed from: a */
        public String mo19112a() {
            return this.f16764a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.f16764a.equals(((b) obj).f16764a);
            }
            return false;
        }

        public int hashCode() {
            return this.f16764a.hashCode();
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f16764a + "'}";
        }
    }

    public C4816j(Map<String, List<InterfaceC4815i>> map) {
        this.f16757c = Collections.unmodifiableMap(map);
    }

    @Override // p073g1.InterfaceC4814h
    /* renamed from: a */
    public Map<String, String> mo19111a() {
        if (this.f16758d == null) {
            synchronized (this) {
                if (this.f16758d == null) {
                    this.f16758d = Collections.unmodifiableMap(m19114c());
                }
            }
        }
        return this.f16758d;
    }

    /* renamed from: b */
    public final String m19113b(List<InterfaceC4815i> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            String strMo19112a = list.get(i9).mo19112a();
            if (!TextUtils.isEmpty(strMo19112a)) {
                sb.append(strMo19112a);
                if (i9 != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    public final Map<String, String> m19114c() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<InterfaceC4815i>> entry : this.f16757c.entrySet()) {
            String strM19113b = m19113b(entry.getValue());
            if (!TextUtils.isEmpty(strM19113b)) {
                map.put(entry.getKey(), strM19113b);
            }
        }
        return map;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C4816j) {
            return this.f16757c.equals(((C4816j) obj).f16757c);
        }
        return false;
    }

    public int hashCode() {
        return this.f16757c.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.f16757c + '}';
    }
}
