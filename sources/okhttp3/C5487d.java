package okhttp3;

import com.google.common.net.HttpHeaders;
import java.util.concurrent.TimeUnit;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;

/* renamed from: okhttp3.d */
/* loaded from: classes2.dex */
public final class C5487d {

    /* renamed from: n */
    public static final b f18507n = new b(null);

    /* renamed from: o */
    public static final C5487d f18508o = new a().m21251d().m21248a();

    /* renamed from: p */
    public static final C5487d f18509p = new a().m21252e().m21250c(Integer.MAX_VALUE, TimeUnit.SECONDS).m21248a();

    /* renamed from: a */
    public final boolean f18510a;

    /* renamed from: b */
    public final boolean f18511b;

    /* renamed from: c */
    public final int f18512c;

    /* renamed from: d */
    public final int f18513d;

    /* renamed from: e */
    public final boolean f18514e;

    /* renamed from: f */
    public final boolean f18515f;

    /* renamed from: g */
    public final boolean f18516g;

    /* renamed from: h */
    public final int f18517h;

    /* renamed from: i */
    public final int f18518i;

    /* renamed from: j */
    public final boolean f18519j;

    /* renamed from: k */
    public final boolean f18520k;

    /* renamed from: l */
    public final boolean f18521l;

    /* renamed from: m */
    public String f18522m;

    /* renamed from: okhttp3.d$a */
    public static final class a {

        /* renamed from: a */
        public boolean f18523a;

        /* renamed from: b */
        public boolean f18524b;

        /* renamed from: c */
        public int f18525c = -1;

        /* renamed from: d */
        public int f18526d = -1;

        /* renamed from: e */
        public int f18527e = -1;

        /* renamed from: f */
        public boolean f18528f;

        /* renamed from: g */
        public boolean f18529g;

        /* renamed from: h */
        public boolean f18530h;

        /* renamed from: a */
        public final C5487d m21248a() {
            return new C5487d(this.f18523a, this.f18524b, this.f18525c, -1, false, false, false, this.f18526d, this.f18527e, this.f18528f, this.f18529g, this.f18530h, null, null);
        }

        /* renamed from: b */
        public final int m21249b(long j9) {
            if (j9 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j9;
        }

        /* renamed from: c */
        public final a m21250c(int i9, TimeUnit timeUnit) {
            C0042f.m158e(timeUnit, "timeUnit");
            if (i9 >= 0) {
                this.f18526d = m21249b(timeUnit.toSeconds(i9));
                return this;
            }
            throw new IllegalArgumentException(("maxStale < 0: " + i9).toString());
        }

        /* renamed from: d */
        public final a m21251d() {
            this.f18523a = true;
            return this;
        }

        /* renamed from: e */
        public final a m21252e() {
            this.f18528f = true;
            return this;
        }
    }

    /* renamed from: okhttp3.d$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final int m21253a(String str, String str2, int i9) {
            int length = str.length();
            while (i9 < length) {
                if (StringsKt__StringsKt.m20450B(str2, str.charAt(i9), false, 2, null)) {
                    return i9;
                }
                i9++;
            }
            return str.length();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00cf  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00d7  */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final C5487d m21254b(C5517r c5517r) {
            int i9;
            int i10;
            int iM21253a;
            String string;
            C5517r c5517r2 = c5517r;
            C0042f.m158e(c5517r2, "headers");
            int size = c5517r.size();
            boolean z8 = true;
            boolean z9 = true;
            int i11 = 0;
            String str = null;
            boolean z10 = false;
            boolean z11 = false;
            int iM19782V = -1;
            int iM19782V2 = -1;
            boolean z12 = false;
            boolean z13 = false;
            boolean z14 = false;
            int iM19782V3 = -1;
            int iM19782V4 = -1;
            boolean z15 = false;
            boolean z16 = false;
            boolean z17 = false;
            while (i11 < size) {
                String strM21627b = c5517r2.m21627b(i11);
                String strM21629d = c5517r2.m21629d(i11);
                if (C5255l.m20513l(strM21627b, HttpHeaders.CACHE_CONTROL, z8)) {
                    if (str == null) {
                        str = strM21629d;
                    }
                    i9 = 0;
                    while (i9 < strM21629d.length()) {
                        int iM21253a2 = m21253a(strM21629d, "=,;", i9);
                        String strSubstring = strM21629d.substring(i9, iM21253a2);
                        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                        String string2 = StringsKt__StringsKt.m20487m0(strSubstring).toString();
                        if (iM21253a2 != strM21629d.length()) {
                            i10 = size;
                            if (strM21629d.charAt(iM21253a2) != ',' && strM21629d.charAt(iM21253a2) != ';') {
                                int iM19764D = C5057d.m19764D(strM21629d, iM21253a2 + 1);
                                if (iM19764D >= strM21629d.length() || strM21629d.charAt(iM19764D) != '\"') {
                                    iM21253a = m21253a(strM21629d, ",;", iM19764D);
                                    String strSubstring2 = strM21629d.substring(iM19764D, iM21253a);
                                    C0042f.m157d(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                    string = StringsKt__StringsKt.m20487m0(strSubstring2).toString();
                                } else {
                                    int i12 = iM19764D + 1;
                                    int iM20461M = StringsKt__StringsKt.m20461M(strM21629d, '\"', i12, false, 4, null);
                                    string = strM21629d.substring(i12, iM20461M);
                                    C0042f.m157d(string, "this as java.lang.String…ing(startIndex, endIndex)");
                                    iM21253a = iM20461M + 1;
                                }
                            }
                            z8 = true;
                            if (!C5255l.m20513l("no-cache", string2, true)) {
                                i9 = iM21253a;
                                z10 = true;
                            } else if (C5255l.m20513l("no-store", string2, true)) {
                                i9 = iM21253a;
                                z11 = true;
                            } else {
                                if (C5255l.m20513l("max-age", string2, true)) {
                                    iM19782V = C5057d.m19782V(string, -1);
                                } else if (C5255l.m20513l("s-maxage", string2, true)) {
                                    iM19782V2 = C5057d.m19782V(string, -1);
                                } else if (C5255l.m20513l("private", string2, true)) {
                                    i9 = iM21253a;
                                    z12 = true;
                                } else if (C5255l.m20513l("public", string2, true)) {
                                    i9 = iM21253a;
                                    z13 = true;
                                } else if (C5255l.m20513l("must-revalidate", string2, true)) {
                                    i9 = iM21253a;
                                    z14 = true;
                                } else if (C5255l.m20513l("max-stale", string2, true)) {
                                    iM19782V3 = C5057d.m19782V(string, Integer.MAX_VALUE);
                                } else if (C5255l.m20513l("min-fresh", string2, true)) {
                                    iM19782V4 = C5057d.m19782V(string, -1);
                                } else if (C5255l.m20513l("only-if-cached", string2, true)) {
                                    i9 = iM21253a;
                                    z15 = true;
                                } else if (C5255l.m20513l("no-transform", string2, true)) {
                                    i9 = iM21253a;
                                    z16 = true;
                                } else if (C5255l.m20513l("immutable", string2, true)) {
                                    i9 = iM21253a;
                                    z17 = true;
                                }
                                i9 = iM21253a;
                            }
                            size = i10;
                        } else {
                            i10 = size;
                        }
                        iM21253a = iM21253a2 + 1;
                        string = null;
                        z8 = true;
                        if (!C5255l.m20513l("no-cache", string2, true)) {
                        }
                        size = i10;
                    }
                    i11++;
                    c5517r2 = c5517r;
                    size = size;
                } else if (!C5255l.m20513l(strM21627b, HttpHeaders.PRAGMA, z8)) {
                    i11++;
                    c5517r2 = c5517r;
                    size = size;
                }
                z9 = false;
                i9 = 0;
                while (i9 < strM21629d.length()) {
                }
                i11++;
                c5517r2 = c5517r;
                size = size;
            }
            return new C5487d(z10, z11, iM19782V, iM19782V2, z12, z13, z14, iM19782V3, iM19782V4, z15, z16, z17, !z9 ? null : str, null);
        }
    }

    public C5487d(boolean z8, boolean z9, int i9, int i10, boolean z10, boolean z11, boolean z12, int i11, int i12, boolean z13, boolean z14, boolean z15, String str) {
        this.f18510a = z8;
        this.f18511b = z9;
        this.f18512c = i9;
        this.f18513d = i10;
        this.f18514e = z10;
        this.f18515f = z11;
        this.f18516g = z12;
        this.f18517h = i11;
        this.f18518i = i12;
        this.f18519j = z13;
        this.f18520k = z14;
        this.f18521l = z15;
        this.f18522m = str;
    }

    public /* synthetic */ C5487d(boolean z8, boolean z9, int i9, int i10, boolean z10, boolean z11, boolean z12, int i11, int i12, boolean z13, boolean z14, boolean z15, String str, C0040d c0040d) {
        this(z8, z9, i9, i10, z10, z11, z12, i11, i12, z13, z14, z15, str);
    }

    /* renamed from: a */
    public final boolean m21239a() {
        return this.f18514e;
    }

    /* renamed from: b */
    public final boolean m21240b() {
        return this.f18515f;
    }

    /* renamed from: c */
    public final int m21241c() {
        return this.f18512c;
    }

    /* renamed from: d */
    public final int m21242d() {
        return this.f18517h;
    }

    /* renamed from: e */
    public final int m21243e() {
        return this.f18518i;
    }

    /* renamed from: f */
    public final boolean m21244f() {
        return this.f18516g;
    }

    /* renamed from: g */
    public final boolean m21245g() {
        return this.f18510a;
    }

    /* renamed from: h */
    public final boolean m21246h() {
        return this.f18511b;
    }

    /* renamed from: i */
    public final boolean m21247i() {
        return this.f18519j;
    }

    public String toString() {
        String str = this.f18522m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f18510a) {
            sb.append("no-cache, ");
        }
        if (this.f18511b) {
            sb.append("no-store, ");
        }
        if (this.f18512c != -1) {
            sb.append("max-age=");
            sb.append(this.f18512c);
            sb.append(", ");
        }
        if (this.f18513d != -1) {
            sb.append("s-maxage=");
            sb.append(this.f18513d);
            sb.append(", ");
        }
        if (this.f18514e) {
            sb.append("private, ");
        }
        if (this.f18515f) {
            sb.append("public, ");
        }
        if (this.f18516g) {
            sb.append("must-revalidate, ");
        }
        if (this.f18517h != -1) {
            sb.append("max-stale=");
            sb.append(this.f18517h);
            sb.append(", ");
        }
        if (this.f18518i != -1) {
            sb.append("min-fresh=");
            sb.append(this.f18518i);
            sb.append(", ");
        }
        if (this.f18519j) {
            sb.append("only-if-cached, ");
        }
        if (this.f18520k) {
            sb.append("no-transform, ");
        }
        if (this.f18521l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        this.f18522m = string;
        return string;
    }
}
