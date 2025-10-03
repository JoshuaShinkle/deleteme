package p201t3;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.apache.commons.lang3.CharEncoding;

/* renamed from: t3.y */
/* loaded from: classes.dex */
public class C6311y {
    /* renamed from: a */
    public static String m24131a(String str, String str2) {
        if (str2 == null) {
            str2 = CharEncoding.ISO_8859_1;
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e9) {
            throw new IllegalArgumentException(e9);
        }
    }

    /* renamed from: b */
    public static String m24132b(List<? extends C6301o> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (C6301o c6301o : list) {
            String strM24131a = m24131a(c6301o.m24110a(), str);
            String strM24111b = c6301o.m24111b();
            String strM24131a2 = strM24111b != null ? m24131a(strM24111b, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strM24131a);
            sb.append("=");
            sb.append(strM24131a2);
        }
        return sb.toString();
    }
}
