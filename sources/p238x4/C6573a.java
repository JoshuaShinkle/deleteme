package p238x4;

import android.text.TextUtils;
import com.linkedin.urls.UrlPart;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: x4.a */
/* loaded from: classes2.dex */
public class C6573a {

    /* renamed from: k */
    public static final Map<String, Integer> f22098k;

    /* renamed from: a */
    public C6574b f22099a;

    /* renamed from: b */
    public String f22100b;

    /* renamed from: c */
    public String f22101c;

    /* renamed from: d */
    public String f22102d;

    /* renamed from: e */
    public String f22103e;

    /* renamed from: f */
    public int f22104f = 0;

    /* renamed from: g */
    public String f22105g;

    /* renamed from: h */
    public String f22106h;

    /* renamed from: i */
    public String f22107i;

    /* renamed from: j */
    public String f22108j;

    static {
        HashMap map = new HashMap();
        f22098k = map;
        map.put("http", 80);
        map.put("https", 443);
        map.put("ftp", 21);
    }

    public C6573a(C6574b c6574b) {
        this.f22099a = c6574b;
        this.f22108j = c6574b.m25187b();
    }

    /* renamed from: a */
    public final boolean m25171a(UrlPart urlPart) {
        return urlPart != null && this.f22099a.m25188c(urlPart) >= 0;
    }

    /* renamed from: b */
    public String m25172b() {
        if (this.f22107i == null) {
            this.f22107i = m25177g(UrlPart.FRAGMENT);
        }
        return StringUtils.defaultString(this.f22107i);
    }

    /* renamed from: c */
    public String m25173c() {
        return m25174d() + StringUtils.defaultString(m25172b());
    }

    /* renamed from: d */
    public String m25174d() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(m25182l())) {
            sb.append(m25182l());
            sb.append(":");
        }
        sb.append("//");
        if (!StringUtils.isEmpty(m25183m())) {
            sb.append(m25183m());
            if (!StringUtils.isEmpty(m25178h())) {
                sb.append(":");
                sb.append(m25178h());
            }
            sb.append("@");
        }
        sb.append(m25175e());
        if (m25180j() > 0 && m25180j() != f22098k.get(m25182l()).intValue()) {
            sb.append(":");
            sb.append(m25180j());
        }
        sb.append(m25179i());
        sb.append(m25181k());
        return sb.toString();
    }

    /* renamed from: e */
    public String m25175e() {
        if (this.f22103e == null) {
            this.f22103e = m25177g(UrlPart.HOST);
            if (m25171a(UrlPart.PORT)) {
                this.f22103e = this.f22103e.substring(0, r0.length() - 1);
            }
        }
        return this.f22103e;
    }

    /* renamed from: f */
    public String m25176f() {
        return this.f22108j;
    }

    /* renamed from: g */
    public final String m25177g(UrlPart urlPart) {
        if (!m25171a(urlPart)) {
            return null;
        }
        UrlPart urlPartM25184n = m25184n(urlPart);
        return urlPartM25184n == null ? this.f22108j.substring(this.f22099a.m25188c(urlPart)) : this.f22108j.substring(this.f22099a.m25188c(urlPart), this.f22099a.m25188c(urlPartM25184n));
    }

    /* renamed from: h */
    public String m25178h() {
        if (this.f22102d == null) {
            m25185o();
        }
        return StringUtils.defaultString(this.f22102d);
    }

    /* renamed from: i */
    public String m25179i() {
        if (this.f22105g == null) {
            UrlPart urlPart = UrlPart.PATH;
            this.f22105g = m25171a(urlPart) ? m25177g(urlPart) : "/";
        }
        return this.f22105g;
    }

    /* renamed from: j */
    public int m25180j() {
        if (this.f22104f == 0) {
            String strM25177g = m25177g(UrlPart.PORT);
            if (strM25177g == null || strM25177g.isEmpty()) {
                Map<String, Integer> map = f22098k;
                if (map.containsKey(m25182l())) {
                    this.f22104f = map.get(m25182l()).intValue();
                } else {
                    this.f22104f = -1;
                }
            } else {
                try {
                    this.f22104f = Integer.parseInt(strM25177g);
                } catch (NumberFormatException unused) {
                    this.f22104f = -1;
                }
            }
        }
        return this.f22104f;
    }

    /* renamed from: k */
    public String m25181k() {
        if (this.f22106h == null) {
            this.f22106h = m25177g(UrlPart.QUERY);
        }
        return StringUtils.defaultString(this.f22106h);
    }

    /* renamed from: l */
    public String m25182l() {
        int iIndexOf;
        if (this.f22100b == null) {
            UrlPart urlPart = UrlPart.SCHEME;
            if (m25171a(urlPart)) {
                String strM25177g = m25177g(urlPart);
                this.f22100b = strM25177g;
                if (strM25177g != null && (iIndexOf = strM25177g.indexOf(":")) != -1) {
                    this.f22100b = this.f22100b.substring(0, iIndexOf);
                }
            } else if (!this.f22108j.startsWith("//")) {
                this.f22100b = "http";
            }
        }
        return StringUtils.defaultString(this.f22100b);
    }

    /* renamed from: m */
    public String m25183m() {
        if (this.f22101c == null) {
            m25185o();
        }
        return StringUtils.defaultString(this.f22101c);
    }

    /* renamed from: n */
    public final UrlPart m25184n(UrlPart urlPart) {
        UrlPart urlPartM17846a = urlPart.m17846a();
        if (m25171a(urlPartM17846a)) {
            return urlPartM17846a;
        }
        if (urlPartM17846a == null) {
            return null;
        }
        return m25184n(urlPartM17846a);
    }

    /* renamed from: o */
    public final void m25185o() {
        UrlPart urlPart = UrlPart.USERNAME_PASSWORD;
        if (m25171a(urlPart)) {
            String strM25177g = m25177g(urlPart);
            if (TextUtils.isEmpty(strM25177g)) {
                return;
            }
            String[] strArrSplit = strM25177g.substring(0, strM25177g.length() - 1).split(":");
            if (strArrSplit.length == 1) {
                this.f22101c = strArrSplit[0];
            } else if (strArrSplit.length == 2) {
                this.f22101c = strArrSplit[0];
                this.f22102d = strArrSplit[1];
            }
        }
    }

    public String toString() {
        return m25173c();
    }
}
