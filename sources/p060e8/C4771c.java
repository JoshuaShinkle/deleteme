package p060e8;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/* renamed from: e8.c */
/* loaded from: classes.dex */
public final class C4771c {

    /* renamed from: a */
    public static final String[] f16601a = {"", StringUtils.SPACE, "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};

    /* renamed from: b */
    public static final ThreadLocal<StringBuilder> f16602b = new a();

    /* renamed from: e8.c$a */
    public class a extends ThreadLocal<StringBuilder> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StringBuilder initialValue() {
            return new StringBuilder(UserMetadata.MAX_INTERNAL_KEY_SIZE);
        }
    }

    /* renamed from: a */
    public static void m18979a(StringBuilder sb, String str, boolean z8) {
        int length = str.length();
        int iCharCount = 0;
        boolean z9 = false;
        boolean z10 = false;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (m18982d(iCodePointAt)) {
                if ((!z8 || z9) && !z10) {
                    sb.append(' ');
                    z10 = true;
                }
            } else if (!m18984f(iCodePointAt)) {
                sb.appendCodePoint(iCodePointAt);
                z10 = false;
                z9 = true;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
    }

    /* renamed from: b */
    public static boolean m18980b(String str, String... strArr) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m18981c(String str, String[] strArr) {
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    /* renamed from: d */
    public static boolean m18982d(int i9) {
        return i9 == 32 || i9 == 9 || i9 == 10 || i9 == 12 || i9 == 13 || i9 == 160;
    }

    /* renamed from: e */
    public static boolean m18983e(String str) {
        if (str != null && str.length() != 0) {
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                if (!m18986h(str.codePointAt(i9))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: f */
    public static boolean m18984f(int i9) {
        return Character.getType(i9) == 16 && (i9 == 8203 || i9 == 8204 || i9 == 8205 || i9 == 173);
    }

    /* renamed from: g */
    public static boolean m18985g(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isDigit(str.codePointAt(i9))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: h */
    public static boolean m18986h(int i9) {
        return i9 == 32 || i9 == 9 || i9 == 10 || i9 == 12 || i9 == 13;
    }

    /* renamed from: i */
    public static String m18987i(Collection collection, String str) {
        return m18988j(collection.iterator(), str);
    }

    /* renamed from: j */
    public static String m18988j(Iterator it, String str) {
        if (!it.hasNext()) {
            return "";
        }
        String string = it.next().toString();
        if (!it.hasNext()) {
            return string;
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append(string);
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
        }
        return sb.toString();
    }

    /* renamed from: k */
    public static String m18989k(String str) {
        StringBuilder sbM18993o = m18993o();
        m18979a(sbM18993o, str, false);
        return sbM18993o.toString();
    }

    /* renamed from: l */
    public static String m18990l(int i9) {
        if (i9 < 0) {
            throw new IllegalArgumentException("width must be > 0");
        }
        String[] strArr = f16601a;
        if (i9 < strArr.length) {
            return strArr[i9];
        }
        char[] cArr = new char[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            cArr[i10] = ' ';
        }
        return String.valueOf(cArr);
    }

    /* renamed from: m */
    public static String m18991m(String str, String str2) {
        try {
            try {
                return m18992n(new URL(str), str2).toExternalForm();
            } catch (MalformedURLException unused) {
                return new URL(str2).toExternalForm();
            }
        } catch (MalformedURLException unused2) {
            return "";
        }
    }

    /* renamed from: n */
    public static URL m18992n(URL url, String str) {
        if (str.startsWith("?")) {
            str = url.getPath() + str;
        }
        if (str.indexOf(46) == 0 && url.getFile().indexOf(47) != 0) {
            url = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/" + url.getFile());
        }
        return new URL(url, str);
    }

    /* renamed from: o */
    public static StringBuilder m18993o() {
        ThreadLocal<StringBuilder> threadLocal = f16602b;
        StringBuilder sb = threadLocal.get();
        if (sb.length() <= 8192) {
            sb.delete(0, sb.length());
            return sb;
        }
        StringBuilder sb2 = new StringBuilder(UserMetadata.MAX_INTERNAL_KEY_SIZE);
        threadLocal.set(sb2);
        return sb2;
    }
}
