package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import p007a6.C0042f;

/* renamed from: okhttp3.g */
/* loaded from: classes2.dex */
public final class C5490g {

    /* renamed from: a */
    public final String f18531a;

    /* renamed from: b */
    public final Map<String, String> f18532b;

    public C5490g(String str, Map<String, String> map) {
        String lowerCase;
        C0042f.m158e(str, "scheme");
        C0042f.m158e(map, "authParams");
        this.f18531a = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null) {
                Locale locale = Locale.US;
                C0042f.m157d(locale, "US");
                lowerCase = key.toLowerCase(locale);
                C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            linkedHashMap.put(lowerCase, value);
        }
        Map<String, String> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        C0042f.m157d(mapUnmodifiableMap, "unmodifiableMap<String?, String>(newAuthParams)");
        this.f18532b = mapUnmodifiableMap;
    }

    /* renamed from: a */
    public final Charset m21257a() {
        String str = this.f18532b.get("charset");
        if (str != null) {
            try {
                Charset charsetForName = Charset.forName(str);
                C0042f.m157d(charsetForName, "forName(charset)");
                return charsetForName;
            } catch (Exception unused) {
            }
        }
        Charset charset = StandardCharsets.ISO_8859_1;
        C0042f.m157d(charset, "ISO_8859_1");
        return charset;
    }

    /* renamed from: b */
    public final String m21258b() {
        return this.f18532b.get("realm");
    }

    /* renamed from: c */
    public final String m21259c() {
        return this.f18531a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5490g) {
            C5490g c5490g = (C5490g) obj;
            if (C0042f.m154a(c5490g.f18531a, this.f18531a) && C0042f.m154a(c5490g.f18532b, this.f18532b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((899 + this.f18531a.hashCode()) * 31) + this.f18532b.hashCode();
    }

    public String toString() {
        return this.f18531a + " authParams=" + this.f18532b;
    }
}
