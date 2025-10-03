package p247y4;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* renamed from: y4.a */
/* loaded from: classes2.dex */
public class C6789a {
    /* renamed from: a */
    public static boolean m25329a(char c9) {
        return (c9 >= 'a' && c9 <= 'z') || (c9 >= 'A' && c9 <= 'Z');
    }

    /* renamed from: b */
    public static boolean m25330b(char c9) {
        return m25329a(c9) || m25335g(c9);
    }

    /* renamed from: c */
    public static boolean m25331c(char c9) {
        return m25329a(c9) || m25335g(c9) || m25332d(c9);
    }

    /* renamed from: d */
    public static boolean m25332d(char c9) {
        return 19968 <= c9 && c9 < 40869;
    }

    /* renamed from: e */
    public static boolean m25333e(char c9) {
        return c9 == '.' || c9 == 12290 || c9 == 65294 || c9 == 65377;
    }

    /* renamed from: f */
    public static boolean m25334f(char c9) {
        return (c9 >= '0' && c9 <= '9') || (c9 >= 'a' && c9 <= 'f') || (c9 >= 'A' && c9 <= 'F');
    }

    /* renamed from: g */
    public static boolean m25335g(char c9) {
        return c9 >= '0' && c9 <= '9';
    }

    /* renamed from: h */
    public static boolean m25336h(char c9) {
        return m25330b(c9) || c9 == '-' || c9 == '.' || c9 == '_' || c9 == '~';
    }

    /* renamed from: i */
    public static boolean m25337i(char c9) {
        return c9 == '\n' || c9 == '\t' || c9 == '\r' || c9 == ' ';
    }

    /* renamed from: j */
    public static String[] m25338j(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(str)) {
            return new String[]{""};
        }
        C6790b c6790b = new C6790b(str);
        while (!c6790b.m25340b()) {
            char cM25345g = c6790b.m25345g();
            if (m25333e(cM25345g)) {
                arrayList.add(sb.toString());
                sb.setLength(0);
            } else if (cM25345g == '%' && c6790b.m25339a(2) && c6790b.m25343e(2).equalsIgnoreCase("2e")) {
                c6790b.m25345g();
                c6790b.m25345g();
                arrayList.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(cM25345g);
            }
        }
        arrayList.add(sb.toString());
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
