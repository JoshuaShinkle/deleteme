package okhttp3;

import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.common.primitives.UnsignedBytes;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.C5227j;
import kotlin.text.C5255l;
import kotlin.text.C5257n;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.p159io.IOUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p027c6.C0745a;
import p027c6.C0749e;
import p098i6.C5054a;
import p098i6.C5057d;
import p204t6.C6322c;

/* renamed from: okhttp3.s */
/* loaded from: classes2.dex */
public final class C5518s {

    /* renamed from: k */
    public static final b f18941k = new b(null);

    /* renamed from: l */
    public static final char[] f18942l = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public final String f18943a;

    /* renamed from: b */
    public final String f18944b;

    /* renamed from: c */
    public final String f18945c;

    /* renamed from: d */
    public final String f18946d;

    /* renamed from: e */
    public final int f18947e;

    /* renamed from: f */
    public final List<String> f18948f;

    /* renamed from: g */
    public final List<String> f18949g;

    /* renamed from: h */
    public final String f18950h;

    /* renamed from: i */
    public final String f18951i;

    /* renamed from: j */
    public final boolean f18952j;

    /* renamed from: okhttp3.s$a */
    public static final class a {

        /* renamed from: i */
        public static final C6878a f18953i = new C6878a(null);

        /* renamed from: a */
        public String f18954a;

        /* renamed from: d */
        public String f18957d;

        /* renamed from: f */
        public final List<String> f18959f;

        /* renamed from: g */
        public List<String> f18960g;

        /* renamed from: h */
        public String f18961h;

        /* renamed from: b */
        public String f18955b = "";

        /* renamed from: c */
        public String f18956c = "";

        /* renamed from: e */
        public int f18958e = -1;

        /* renamed from: okhttp3.s$a$a, reason: collision with other inner class name */
        public static final class C6878a {
            public C6878a() {
            }

            public /* synthetic */ C6878a(C0040d c0040d) {
                this();
            }

            /* renamed from: e */
            public final int m21690e(String str, int i9, int i10) throws NumberFormatException {
                try {
                    int i11 = Integer.parseInt(b.m21694b(C5518s.f18941k, str, i9, i10, "", false, false, false, false, null, 248, null));
                    boolean z8 = false;
                    if (1 <= i11 && i11 < 65536) {
                        z8 = true;
                    }
                    if (z8) {
                        return i11;
                    }
                    return -1;
                } catch (NumberFormatException unused) {
                    return -1;
                }
            }

            /* renamed from: f */
            public final int m21691f(String str, int i9, int i10) {
                while (i9 < i10) {
                    char cCharAt = str.charAt(i9);
                    if (cCharAt == '[') {
                        do {
                            i9++;
                            if (i9 < i10) {
                            }
                        } while (str.charAt(i9) != ']');
                    } else if (cCharAt == ':') {
                        return i9;
                    }
                    i9++;
                }
                return i10;
            }

            /* renamed from: g */
            public final int m21692g(String str, int i9, int i10) {
                if (i10 - i9 < 2) {
                    return -1;
                }
                char cCharAt = str.charAt(i9);
                if ((C0042f.m159f(cCharAt, 97) >= 0 && C0042f.m159f(cCharAt, 122) <= 0) || (C0042f.m159f(cCharAt, 65) >= 0 && C0042f.m159f(cCharAt, 90) <= 0)) {
                    for (int i11 = i9 + 1; i11 < i10; i11++) {
                        char cCharAt2 = str.charAt(i11);
                        if (!(((((('a' <= cCharAt2 && cCharAt2 < '{') || ('A' <= cCharAt2 && cCharAt2 < '[')) || ('0' <= cCharAt2 && cCharAt2 < ':')) || cCharAt2 == '+') || cCharAt2 == '-') || cCharAt2 == '.')) {
                            if (cCharAt2 == ':') {
                                return i11;
                            }
                            return -1;
                        }
                    }
                }
                return -1;
            }

            /* renamed from: h */
            public final int m21693h(String str, int i9, int i10) {
                int i11 = 0;
                while (i9 < i10) {
                    char cCharAt = str.charAt(i9);
                    if (cCharAt != '\\' && cCharAt != '/') {
                        break;
                    }
                    i11++;
                    i9++;
                }
                return i11;
            }
        }

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f18959f = arrayList;
            arrayList.add("");
        }

        /* renamed from: a */
        public final C5518s m21664a() {
            ArrayList arrayList;
            String str = this.f18954a;
            if (str == null) {
                throw new IllegalStateException("scheme == null");
            }
            b bVar = C5518s.f18941k;
            String strM21695g = b.m21695g(bVar, this.f18955b, 0, 0, false, 7, null);
            String strM21695g2 = b.m21695g(bVar, this.f18956c, 0, 0, false, 7, null);
            String str2 = this.f18957d;
            if (str2 == null) {
                throw new IllegalStateException("host == null");
            }
            int iM21665b = m21665b();
            List<String> list = this.f18959f;
            ArrayList arrayList2 = new ArrayList(C5227j.m20408n(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(b.m21695g(C5518s.f18941k, (String) it.next(), 0, 0, false, 7, null));
            }
            List<String> list2 = this.f18960g;
            if (list2 != null) {
                List<String> list3 = list2;
                arrayList = new ArrayList(C5227j.m20408n(list3, 10));
                for (String str3 : list3) {
                    arrayList.add(str3 != null ? b.m21695g(C5518s.f18941k, str3, 0, 0, true, 3, null) : null);
                }
            } else {
                arrayList = null;
            }
            String str4 = this.f18961h;
            return new C5518s(str, strM21695g, strM21695g2, str2, iM21665b, arrayList2, arrayList, str4 != null ? b.m21695g(C5518s.f18941k, str4, 0, 0, false, 7, null) : null, toString());
        }

        /* renamed from: b */
        public final int m21665b() {
            int i9 = this.f18958e;
            if (i9 != -1) {
                return i9;
            }
            b bVar = C5518s.f18941k;
            String str = this.f18954a;
            C0042f.m155b(str);
            return bVar.m21697c(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x001d  */
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final a m21666c(String str) {
            List<String> listM21702i;
            if (str != null) {
                b bVar = C5518s.f18941k;
                String strM21694b = b.m21694b(bVar, str, 0, 0, " \"'<>#", true, false, true, false, null, 211, null);
                listM21702i = strM21694b != null ? bVar.m21702i(strM21694b) : null;
            }
            this.f18960g = listM21702i;
            return this;
        }

        /* renamed from: d */
        public final List<String> m21667d() {
            return this.f18959f;
        }

        /* renamed from: e */
        public final a m21668e(String str) {
            C0042f.m158e(str, "host");
            String strM19759e = C5054a.m19759e(b.m21695g(C5518s.f18941k, str, 0, 0, false, 7, null));
            if (strM19759e != null) {
                this.f18957d = strM19759e;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        /* renamed from: f */
        public final boolean m21669f(String str) {
            return C0042f.m154a(str, ".") || C5255l.m20513l(str, "%2e", true);
        }

        /* renamed from: g */
        public final boolean m21670g(String str) {
            return C0042f.m154a(str, "..") || C5255l.m20513l(str, "%2e.", true) || C5255l.m20513l(str, ".%2e", true) || C5255l.m20513l(str, "%2e%2e", true);
        }

        /* renamed from: h */
        public final a m21671h(C5518s c5518s, String str) throws NumberFormatException {
            int iM19803q;
            int i9;
            int i10;
            String str2;
            boolean z8;
            int i11;
            String str3;
            int i12;
            boolean z9;
            String str4 = str;
            C0042f.m158e(str4, "input");
            int iM19761A = C5057d.m19761A(str4, 0, 0, 3, null);
            int iM19763C = C5057d.m19763C(str4, iM19761A, 0, 2, null);
            C6878a c6878a = f18953i;
            int iM21692g = c6878a.m21692g(str4, iM19761A, iM19763C);
            String str5 = "this as java.lang.String…ing(startIndex, endIndex)";
            boolean z10 = true;
            char c9 = 65535;
            if (iM21692g != -1) {
                if (C5255l.m20522u(str4, "https:", iM19761A, true)) {
                    this.f18954a = "https";
                    iM19761A += 6;
                } else {
                    if (!C5255l.m20522u(str4, "http:", iM19761A, true)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected URL scheme 'http' or 'https' but was '");
                        String strSubstring = str4.substring(0, iM21692g);
                        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(strSubstring);
                        sb.append('\'');
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.f18954a = "http";
                    iM19761A += 5;
                }
            } else {
                if (c5518s == null) {
                    if (str.length() > 6) {
                        str4 = C5257n.m20526n0(str4, 6) + "...";
                    }
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no scheme was found for " + str4);
                }
                this.f18954a = c5518s.m21661p();
            }
            int iM21693h = c6878a.m21693h(str4, iM19761A, iM19763C);
            char c10 = '?';
            char c11 = '#';
            if (iM21693h >= 2 || c5518s == null || !C0042f.m154a(c5518s.m21661p(), this.f18954a)) {
                int i13 = iM19761A + iM21693h;
                boolean z11 = false;
                boolean z12 = false;
                while (true) {
                    iM19803q = C5057d.m19803q(str4, "@/\\?#", i13, iM19763C);
                    char cCharAt = iM19803q != iM19763C ? str4.charAt(iM19803q) : c9;
                    if (cCharAt == c9 || cCharAt == c11 || cCharAt == '/' || cCharAt == '\\' || cCharAt == c10) {
                        break;
                    }
                    if (cCharAt == '@') {
                        if (z11) {
                            z8 = z10;
                            i11 = iM19763C;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.f18956c);
                            sb2.append("%40");
                            str3 = str5;
                            i12 = iM19803q;
                            sb2.append(b.m21694b(C5518s.f18941k, str, i13, iM19803q, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, PsExtractor.VIDEO_STREAM_MASK, null));
                            this.f18956c = sb2.toString();
                        } else {
                            int iM19802p = C5057d.m19802p(str4, ':', i13, iM19803q);
                            b bVar = C5518s.f18941k;
                            z8 = z10;
                            i11 = iM19763C;
                            String str6 = str5;
                            String strM21694b = b.m21694b(bVar, str, i13, iM19802p, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, PsExtractor.VIDEO_STREAM_MASK, null);
                            if (z12) {
                                strM21694b = this.f18955b + "%40" + strM21694b;
                            }
                            this.f18955b = strM21694b;
                            if (iM19802p != iM19803q) {
                                this.f18956c = b.m21694b(bVar, str, iM19802p + 1, iM19803q, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, PsExtractor.VIDEO_STREAM_MASK, null);
                                z9 = z8;
                            } else {
                                z9 = z11;
                            }
                            z11 = z9;
                            str3 = str6;
                            z12 = z8;
                            i12 = iM19803q;
                        }
                        i13 = i12 + 1;
                        str5 = str3;
                        z10 = z8;
                        iM19763C = i11;
                        c11 = '#';
                        c10 = '?';
                        c9 = 65535;
                    }
                }
                boolean z13 = z10;
                String str7 = str5;
                i9 = iM19763C;
                C6878a c6878a2 = f18953i;
                int iM21691f = c6878a2.m21691f(str4, i13, iM19803q);
                int i14 = iM21691f + 1;
                if (i14 < iM19803q) {
                    i10 = i13;
                    this.f18957d = C5054a.m19759e(b.m21695g(C5518s.f18941k, str, i13, iM21691f, false, 4, null));
                    int iM21690e = c6878a2.m21690e(str4, i14, iM19803q);
                    this.f18958e = iM21690e;
                    if (!(iM21690e != -1 ? z13 : false)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Invalid URL port: \"");
                        String strSubstring2 = str4.substring(i14, iM19803q);
                        C0042f.m157d(strSubstring2, str7);
                        sb3.append(strSubstring2);
                        sb3.append('\"');
                        throw new IllegalArgumentException(sb3.toString().toString());
                    }
                    str2 = str7;
                } else {
                    i10 = i13;
                    str2 = str7;
                    b bVar2 = C5518s.f18941k;
                    this.f18957d = C5054a.m19759e(b.m21695g(bVar2, str, i10, iM21691f, false, 4, null));
                    String str8 = this.f18954a;
                    C0042f.m155b(str8);
                    this.f18958e = bVar2.m21697c(str8);
                }
                if (!(this.f18957d != null ? z13 : false)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Invalid URL host: \"");
                    String strSubstring3 = str4.substring(i10, iM21691f);
                    C0042f.m157d(strSubstring3, str2);
                    sb4.append(strSubstring3);
                    sb4.append('\"');
                    throw new IllegalArgumentException(sb4.toString().toString());
                }
                iM19761A = iM19803q;
            } else {
                this.f18955b = c5518s.m21652g();
                this.f18956c = c5518s.m21648c();
                this.f18957d = c5518s.m21653h();
                this.f18958e = c5518s.m21657l();
                this.f18959f.clear();
                this.f18959f.addAll(c5518s.m21650e());
                if (iM19761A == iM19763C || str4.charAt(iM19761A) == '#') {
                    m21666c(c5518s.m21651f());
                }
                i9 = iM19763C;
            }
            int i15 = i9;
            int iM19803q2 = C5057d.m19803q(str4, "?#", iM19761A, i15);
            m21677n(str4, iM19761A, iM19803q2);
            if (iM19803q2 < i15 && str4.charAt(iM19803q2) == '?') {
                int iM19802p2 = C5057d.m19802p(str4, '#', iM19803q2, i15);
                b bVar3 = C5518s.f18941k;
                this.f18960g = bVar3.m21702i(b.m21694b(bVar3, str, iM19803q2 + 1, iM19802p2, " \"'<>#", true, false, true, false, null, 208, null));
                iM19803q2 = iM19802p2;
            }
            if (iM19803q2 < i15 && str4.charAt(iM19803q2) == '#') {
                this.f18961h = b.m21694b(C5518s.f18941k, str, iM19803q2 + 1, i15, "", true, false, false, true, null, 176, null);
            }
            return this;
        }

        /* renamed from: i */
        public final a m21672i(String str) {
            C0042f.m158e(str, "password");
            this.f18956c = b.m21694b(C5518s.f18941k, str, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        /* renamed from: j */
        public final void m21673j() {
            List<String> list = this.f18959f;
            if (!(list.remove(list.size() - 1).length() == 0) || !(!this.f18959f.isEmpty())) {
                this.f18959f.add("");
            } else {
                List<String> list2 = this.f18959f;
                list2.set(list2.size() - 1, "");
            }
        }

        /* renamed from: k */
        public final a m21674k(int i9) {
            boolean z8 = false;
            if (1 <= i9 && i9 < 65536) {
                z8 = true;
            }
            if (z8) {
                this.f18958e = i9;
                return this;
            }
            throw new IllegalArgumentException(("unexpected port: " + i9).toString());
        }

        /* renamed from: l */
        public final void m21675l(String str, int i9, int i10, boolean z8, boolean z9) {
            String strM21694b = b.m21694b(C5518s.f18941k, str, i9, i10, " \"<>^`{}|/\\?#", z9, false, false, false, null, PsExtractor.VIDEO_STREAM_MASK, null);
            if (m21669f(strM21694b)) {
                return;
            }
            if (m21670g(strM21694b)) {
                m21673j();
                return;
            }
            List<String> list = this.f18959f;
            if (list.get(list.size() - 1).length() == 0) {
                List<String> list2 = this.f18959f;
                list2.set(list2.size() - 1, strM21694b);
            } else {
                this.f18959f.add(strM21694b);
            }
            if (z8) {
                this.f18959f.add("");
            }
        }

        /* renamed from: m */
        public final a m21676m() {
            String str = this.f18957d;
            this.f18957d = str != null ? new Regex("[\"<>^`{|}]").m20442b(str, "") : null;
            int size = this.f18959f.size();
            for (int i9 = 0; i9 < size; i9++) {
                List<String> list = this.f18959f;
                list.set(i9, b.m21694b(C5518s.f18941k, list.get(i9), 0, 0, "[]", true, true, false, false, null, 227, null));
            }
            List<String> list2 = this.f18960g;
            if (list2 != null) {
                int size2 = list2.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    String str2 = list2.get(i10);
                    list2.set(i10, str2 != null ? b.m21694b(C5518s.f18941k, str2, 0, 0, "\\^`{|}", true, true, true, false, null, 195, null) : null);
                }
            }
            String str3 = this.f18961h;
            this.f18961h = str3 != null ? b.m21694b(C5518s.f18941k, str3, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163, null) : null;
            return this;
        }

        /* renamed from: n */
        public final void m21677n(String str, int i9, int i10) {
            if (i9 == i10) {
                return;
            }
            char cCharAt = str.charAt(i9);
            if (cCharAt == '/' || cCharAt == '\\') {
                this.f18959f.clear();
                this.f18959f.add("");
                i9++;
            } else {
                List<String> list = this.f18959f;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i11 = i9;
                while (i11 < i10) {
                    i9 = C5057d.m19803q(str, "/\\", i11, i10);
                    boolean z8 = i9 < i10;
                    m21675l(str, i11, i9, z8, true);
                    if (z8) {
                        i11 = i9 + 1;
                    }
                }
                return;
            }
        }

        /* renamed from: o */
        public final a m21678o(String str) {
            C0042f.m158e(str, "scheme");
            if (C5255l.m20513l(str, "http", true)) {
                this.f18954a = "http";
            } else {
                if (!C5255l.m20513l(str, "https", true)) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f18954a = "https";
            }
            return this;
        }

        /* renamed from: p */
        public final void m21679p(String str) {
            this.f18961h = str;
        }

        /* renamed from: q */
        public final void m21680q(String str) {
            C0042f.m158e(str, "<set-?>");
            this.f18956c = str;
        }

        /* renamed from: r */
        public final void m21681r(String str) {
            C0042f.m158e(str, "<set-?>");
            this.f18955b = str;
        }

        /* renamed from: s */
        public final void m21682s(String str) {
            this.f18957d = str;
        }

        /* renamed from: t */
        public final void m21683t(int i9) {
            this.f18958e = i9;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0093  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = this.f18954a;
            if (str != null) {
                sb.append(str);
                sb.append("://");
            } else {
                sb.append("//");
            }
            if (this.f18955b.length() > 0) {
                sb.append(this.f18955b);
                if (this.f18956c.length() > 0) {
                    sb.append(':');
                    sb.append(this.f18956c);
                }
                sb.append('@');
            } else {
                if (this.f18956c.length() > 0) {
                }
            }
            String str2 = this.f18957d;
            if (str2 != null) {
                C0042f.m155b(str2);
                if (StringsKt__StringsKt.m20450B(str2, ':', false, 2, null)) {
                    sb.append('[');
                    sb.append(this.f18957d);
                    sb.append(']');
                } else {
                    sb.append(this.f18957d);
                }
            }
            if (this.f18958e != -1 || this.f18954a != null) {
                int iM21665b = m21665b();
                String str3 = this.f18954a;
                if (str3 != null) {
                    b bVar = C5518s.f18941k;
                    C0042f.m155b(str3);
                    if (iM21665b != bVar.m21697c(str3)) {
                        sb.append(':');
                        sb.append(iM21665b);
                    }
                }
            }
            b bVar2 = C5518s.f18941k;
            bVar2.m21701h(this.f18959f, sb);
            if (this.f18960g != null) {
                sb.append('?');
                List<String> list = this.f18960g;
                C0042f.m155b(list);
                bVar2.m21703j(list, sb);
            }
            if (this.f18961h != null) {
                sb.append('#');
                sb.append(this.f18961h);
            }
            String string = sb.toString();
            C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }

        /* renamed from: u */
        public final void m21684u(String str) {
            this.f18954a = str;
        }

        /* renamed from: v */
        public final a m21685v(String str) {
            C0042f.m158e(str, "username");
            this.f18955b = b.m21694b(C5518s.f18941k, str, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }
    }

    /* renamed from: okhttp3.s$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public static /* synthetic */ String m21694b(b bVar, String str, int i9, int i10, String str2, boolean z8, boolean z9, boolean z10, boolean z11, Charset charset, int i11, Object obj) {
            return bVar.m21696a(str, (i11 & 1) != 0 ? 0 : i9, (i11 & 2) != 0 ? str.length() : i10, str2, (i11 & 8) != 0 ? false : z8, (i11 & 16) != 0 ? false : z9, (i11 & 32) != 0 ? false : z10, (i11 & 64) != 0 ? false : z11, (i11 & 128) != 0 ? null : charset);
        }

        /* renamed from: g */
        public static /* synthetic */ String m21695g(b bVar, String str, int i9, int i10, boolean z8, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i9 = 0;
            }
            if ((i11 & 2) != 0) {
                i10 = str.length();
            }
            if ((i11 & 4) != 0) {
                z8 = false;
            }
            return bVar.m21700f(str, i9, i10, z8);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final String m21696a(String str, int i9, int i10, String str2, boolean z8, boolean z9, boolean z10, boolean z11, Charset charset) {
            C0042f.m158e(str, "<this>");
            C0042f.m158e(str2, "encodeSet");
            int iCharCount = i9;
            while (iCharCount < i10) {
                int iCodePointAt = str.codePointAt(iCharCount);
                if (iCodePointAt >= 32 && iCodePointAt != 127 && ((iCodePointAt < 128 || z11) && !StringsKt__StringsKt.m20450B(str2, (char) iCodePointAt, false, 2, null))) {
                    if (iCodePointAt != 37) {
                        if (iCodePointAt == 43 || !z10) {
                            iCharCount += Character.charCount(iCodePointAt);
                        }
                    } else if (z8) {
                        if (z9) {
                            if (m21699e(str, iCharCount, i10)) {
                            }
                        }
                        if (iCodePointAt == 43) {
                        }
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                    C6322c c6322c = new C6322c();
                    c6322c.m24213Z(str, i9, iCharCount);
                    m21704k(c6322c, str, iCharCount, i10, str2, z8, z9, z10, z11, charset);
                    return c6322c.m24197J();
                }
                C6322c c6322c2 = new C6322c();
                c6322c2.m24213Z(str, i9, iCharCount);
                m21704k(c6322c2, str, iCharCount, i10, str2, z8, z9, z10, z11, charset);
                return c6322c2.m24197J();
            }
            String strSubstring = str.substring(i9, i10);
            C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring;
        }

        /* renamed from: c */
        public final int m21697c(String str) {
            C0042f.m158e(str, "scheme");
            if (C0042f.m154a(str, "http")) {
                return 80;
            }
            return C0042f.m154a(str, "https") ? 443 : -1;
        }

        /* renamed from: d */
        public final C5518s m21698d(String str) {
            C0042f.m158e(str, "<this>");
            return new a().m21671h(null, str).m21664a();
        }

        /* renamed from: e */
        public final boolean m21699e(String str, int i9, int i10) {
            int i11 = i9 + 2;
            return i11 < i10 && str.charAt(i9) == '%' && C5057d.m19768H(str.charAt(i9 + 1)) != -1 && C5057d.m19768H(str.charAt(i11)) != -1;
        }

        /* renamed from: f */
        public final String m21700f(String str, int i9, int i10, boolean z8) {
            C0042f.m158e(str, "<this>");
            for (int i11 = i9; i11 < i10; i11++) {
                char cCharAt = str.charAt(i11);
                if (cCharAt == '%' || (cCharAt == '+' && z8)) {
                    C6322c c6322c = new C6322c();
                    c6322c.m24213Z(str, i9, i11);
                    m21705l(c6322c, str, i11, i10, z8);
                    return c6322c.m24197J();
                }
            }
            String strSubstring = str.substring(i9, i10);
            C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring;
        }

        /* renamed from: h */
        public final void m21701h(List<String> list, StringBuilder sb) {
            C0042f.m158e(list, "<this>");
            C0042f.m158e(sb, "out");
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                sb.append(list.get(i9));
            }
        }

        /* renamed from: i */
        public final List<String> m21702i(String str) {
            C0042f.m158e(str, "<this>");
            ArrayList arrayList = new ArrayList();
            int i9 = 0;
            while (i9 <= str.length()) {
                int iM20461M = StringsKt__StringsKt.m20461M(str, '&', i9, false, 4, null);
                if (iM20461M == -1) {
                    iM20461M = str.length();
                }
                int i10 = iM20461M;
                int iM20461M2 = StringsKt__StringsKt.m20461M(str, '=', i9, false, 4, null);
                if (iM20461M2 == -1 || iM20461M2 > i10) {
                    String strSubstring = str.substring(i9, i10);
                    C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring);
                    arrayList.add(null);
                } else {
                    String strSubstring2 = str.substring(i9, iM20461M2);
                    C0042f.m157d(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring2);
                    String strSubstring3 = str.substring(iM20461M2 + 1, i10);
                    C0042f.m157d(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring3);
                }
                i9 = i10 + 1;
            }
            return arrayList;
        }

        /* renamed from: j */
        public final void m21703j(List<String> list, StringBuilder sb) {
            C0042f.m158e(list, "<this>");
            C0042f.m158e(sb, "out");
            C0745a c0745aM3625f = C0749e.m3625f(C0749e.m3626g(0, list.size()), 2);
            int iM3610a = c0745aM3625f.m3610a();
            int iM3611b = c0745aM3625f.m3611b();
            int iM3612c = c0745aM3625f.m3612c();
            if ((iM3612c <= 0 || iM3610a > iM3611b) && (iM3612c >= 0 || iM3611b > iM3610a)) {
                return;
            }
            while (true) {
                String str = list.get(iM3610a);
                String str2 = list.get(iM3610a + 1);
                if (iM3610a > 0) {
                    sb.append('&');
                }
                sb.append(str);
                if (str2 != null) {
                    sb.append('=');
                    sb.append(str2);
                }
                if (iM3610a == iM3611b) {
                    return;
                } else {
                    iM3610a += iM3612c;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0090 A[LOOP:1: B:49:0x008a->B:51:0x0090, LOOP_END] */
        /* renamed from: k */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m21704k(C6322c c6322c, String str, int i9, int i10, String str2, boolean z8, boolean z9, boolean z10, boolean z11, Charset charset) {
            int iCharCount = i9;
            C6322c c6322c2 = null;
            while (iCharCount < i10) {
                int iCodePointAt = str.codePointAt(iCharCount);
                if (!z8 || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                    if (iCodePointAt == 43 && z10) {
                        c6322c.mo24221j(z8 ? "+" : "%2B");
                    } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && !z11) || StringsKt__StringsKt.m20450B(str2, (char) iCodePointAt, false, 2, null))) {
                        if (c6322c2 == null) {
                            c6322c2 = new C6322c();
                        }
                        if (charset != null || C0042f.m154a(charset, StandardCharsets.UTF_8)) {
                            c6322c2.m24214a0(iCodePointAt);
                        } else {
                            c6322c2.m24211X(str, iCharCount, Character.charCount(iCodePointAt) + iCharCount, charset);
                        }
                        while (!c6322c2.mo24218g()) {
                            int i11 = c6322c2.readByte() & UnsignedBytes.MAX_VALUE;
                            c6322c.writeByte(37);
                            c6322c.writeByte(C5518s.f18942l[(i11 >> 4) & 15]);
                            c6322c.writeByte(C5518s.f18942l[i11 & 15]);
                        }
                    } else if (iCodePointAt != 37) {
                        c6322c.m24214a0(iCodePointAt);
                    } else {
                        if (z8) {
                            if (z9) {
                                if (!m21699e(str, iCharCount, i10)) {
                                }
                            }
                            c6322c.m24214a0(iCodePointAt);
                        }
                        if (c6322c2 == null) {
                        }
                        if (charset != null) {
                            c6322c2.m24214a0(iCodePointAt);
                            while (!c6322c2.mo24218g()) {
                            }
                        }
                    }
                }
                iCharCount += Character.charCount(iCodePointAt);
            }
        }

        /* renamed from: l */
        public final void m21705l(C6322c c6322c, String str, int i9, int i10, boolean z8) {
            int i11;
            while (i9 < i10) {
                int iCodePointAt = str.codePointAt(i9);
                if (iCodePointAt == 37 && (i11 = i9 + 2) < i10) {
                    int iM19768H = C5057d.m19768H(str.charAt(i9 + 1));
                    int iM19768H2 = C5057d.m19768H(str.charAt(i11));
                    if (iM19768H == -1 || iM19768H2 == -1) {
                        c6322c.m24214a0(iCodePointAt);
                        i9 += Character.charCount(iCodePointAt);
                    } else {
                        c6322c.writeByte((iM19768H << 4) + iM19768H2);
                        i9 = Character.charCount(iCodePointAt) + i11;
                    }
                } else if (iCodePointAt == 43 && z8) {
                    c6322c.writeByte(32);
                    i9++;
                } else {
                    c6322c.m24214a0(iCodePointAt);
                    i9 += Character.charCount(iCodePointAt);
                }
            }
        }
    }

    public C5518s(String str, String str2, String str3, String str4, int i9, List<String> list, List<String> list2, String str5, String str6) {
        C0042f.m158e(str, "scheme");
        C0042f.m158e(str2, "username");
        C0042f.m158e(str3, "password");
        C0042f.m158e(str4, "host");
        C0042f.m158e(list, "pathSegments");
        C0042f.m158e(str6, "url");
        this.f18943a = str;
        this.f18944b = str2;
        this.f18945c = str3;
        this.f18946d = str4;
        this.f18947e = i9;
        this.f18948f = list;
        this.f18949g = list2;
        this.f18950h = str5;
        this.f18951i = str6;
        this.f18952j = C0042f.m154a(str, "https");
    }

    /* renamed from: b */
    public final String m21647b() {
        if (this.f18950h == null) {
            return null;
        }
        String strSubstring = this.f18951i.substring(StringsKt__StringsKt.m20461M(this.f18951i, '#', 0, false, 6, null) + 1);
        C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    /* renamed from: c */
    public final String m21648c() {
        if (this.f18945c.length() == 0) {
            return "";
        }
        String strSubstring = this.f18951i.substring(StringsKt__StringsKt.m20461M(this.f18951i, ':', this.f18943a.length() + 3, false, 4, null) + 1, StringsKt__StringsKt.m20461M(this.f18951i, '@', 0, false, 6, null));
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    /* renamed from: d */
    public final String m21649d() {
        int iM20461M = StringsKt__StringsKt.m20461M(this.f18951i, IOUtils.DIR_SEPARATOR_UNIX, this.f18943a.length() + 3, false, 4, null);
        String str = this.f18951i;
        String strSubstring = this.f18951i.substring(iM20461M, C5057d.m19803q(str, "?#", iM20461M, str.length()));
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    /* renamed from: e */
    public final List<String> m21650e() {
        int iM20461M = StringsKt__StringsKt.m20461M(this.f18951i, IOUtils.DIR_SEPARATOR_UNIX, this.f18943a.length() + 3, false, 4, null);
        String str = this.f18951i;
        int iM19803q = C5057d.m19803q(str, "?#", iM20461M, str.length());
        ArrayList arrayList = new ArrayList();
        while (iM20461M < iM19803q) {
            int i9 = iM20461M + 1;
            int iM19802p = C5057d.m19802p(this.f18951i, IOUtils.DIR_SEPARATOR_UNIX, i9, iM19803q);
            String strSubstring = this.f18951i.substring(i9, iM19802p);
            C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iM20461M = iM19802p;
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C5518s) && C0042f.m154a(((C5518s) obj).f18951i, this.f18951i);
    }

    /* renamed from: f */
    public final String m21651f() {
        if (this.f18949g == null) {
            return null;
        }
        int iM20461M = StringsKt__StringsKt.m20461M(this.f18951i, '?', 0, false, 6, null) + 1;
        String str = this.f18951i;
        String strSubstring = this.f18951i.substring(iM20461M, C5057d.m19802p(str, '#', iM20461M, str.length()));
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    /* renamed from: g */
    public final String m21652g() {
        if (this.f18944b.length() == 0) {
            return "";
        }
        int length = this.f18943a.length() + 3;
        String str = this.f18951i;
        String strSubstring = this.f18951i.substring(length, C5057d.m19803q(str, ":@", length, str.length()));
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    /* renamed from: h */
    public final String m21653h() {
        return this.f18946d;
    }

    public int hashCode() {
        return this.f18951i.hashCode();
    }

    /* renamed from: i */
    public final boolean m21654i() {
        return this.f18952j;
    }

    /* renamed from: j */
    public final a m21655j() {
        a aVar = new a();
        aVar.m21684u(this.f18943a);
        aVar.m21681r(m21652g());
        aVar.m21680q(m21648c());
        aVar.m21682s(this.f18946d);
        aVar.m21683t(this.f18947e != f18941k.m21697c(this.f18943a) ? this.f18947e : -1);
        aVar.m21667d().clear();
        aVar.m21667d().addAll(m21650e());
        aVar.m21666c(m21651f());
        aVar.m21679p(m21647b());
        return aVar;
    }

    /* renamed from: k */
    public final a m21656k(String str) {
        C0042f.m158e(str, "link");
        try {
            return new a().m21671h(this, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    /* renamed from: l */
    public final int m21657l() {
        return this.f18947e;
    }

    /* renamed from: m */
    public final String m21658m() {
        if (this.f18949g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        f18941k.m21703j(this.f18949g, sb);
        return sb.toString();
    }

    /* renamed from: n */
    public final String m21659n() {
        a aVarM21656k = m21656k("/...");
        C0042f.m155b(aVarM21656k);
        return aVarM21656k.m21685v("").m21672i("").m21664a().toString();
    }

    /* renamed from: o */
    public final C5518s m21660o(String str) {
        C0042f.m158e(str, "link");
        a aVarM21656k = m21656k(str);
        if (aVarM21656k != null) {
            return aVarM21656k.m21664a();
        }
        return null;
    }

    /* renamed from: p */
    public final String m21661p() {
        return this.f18943a;
    }

    /* renamed from: q */
    public final URI m21662q() {
        String string = m21655j().m21676m().toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e9) {
            try {
                URI uriCreate = URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").m20442b(string, ""));
                C0042f.m157d(uriCreate, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e9);
            }
        }
    }

    /* renamed from: r */
    public final URL m21663r() {
        try {
            return new URL(this.f18951i);
        } catch (MalformedURLException e9) {
            throw new RuntimeException(e9);
        }
    }

    public String toString() {
        return this.f18951i;
    }
}
