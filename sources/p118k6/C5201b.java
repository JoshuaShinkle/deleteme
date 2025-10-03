package p118k6;

import com.google.common.net.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.text.C5255l;
import okhttp3.C5487d;
import okhttp3.C5517r;
import okhttp3.C5523x;
import okhttp3.C5525z;
import org.apache.commons.lang3.time.DateUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p139m6.C5330c;

/* renamed from: k6.b */
/* loaded from: classes.dex */
public final class C5201b {

    /* renamed from: c */
    public static final a f17810c = new a(null);

    /* renamed from: a */
    public final C5523x f17811a;

    /* renamed from: b */
    public final C5525z f17812b;

    /* renamed from: k6.b$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean m20332a(C5525z c5525z, C5523x c5523x) {
            C0042f.m158e(c5525z, "response");
            C0042f.m158e(c5523x, "request");
            int iM21853x = c5525z.m21853x();
            if (iM21853x != 200 && iM21853x != 410 && iM21853x != 414 && iM21853x != 501 && iM21853x != 203 && iM21853x != 204) {
                if (iM21853x == 307) {
                    if (C5525z.m21837B(c5525z, HttpHeaders.EXPIRES, null, 2, null) == null && c5525z.m21850u().m21241c() == -1 && !c5525z.m21850u().m21240b() && !c5525z.m21850u().m21239a()) {
                        return false;
                    }
                } else if (iM21853x != 308 && iM21853x != 404 && iM21853x != 405) {
                    switch (iM21853x) {
                        case 300:
                        case 301:
                            break;
                        case 302:
                            break;
                        default:
                            return false;
                    }
                }
            }
            return (c5525z.m21850u().m21246h() || c5523x.m21804b().m21246h()) ? false : true;
        }
    }

    /* renamed from: k6.b$b */
    public static final class b {

        /* renamed from: a */
        public final long f17813a;

        /* renamed from: b */
        public final C5523x f17814b;

        /* renamed from: c */
        public final C5525z f17815c;

        /* renamed from: d */
        public Date f17816d;

        /* renamed from: e */
        public String f17817e;

        /* renamed from: f */
        public Date f17818f;

        /* renamed from: g */
        public String f17819g;

        /* renamed from: h */
        public Date f17820h;

        /* renamed from: i */
        public long f17821i;

        /* renamed from: j */
        public long f17822j;

        /* renamed from: k */
        public String f17823k;

        /* renamed from: l */
        public int f17824l;

        public b(long j9, C5523x c5523x, C5525z c5525z) {
            C0042f.m158e(c5523x, "request");
            this.f17813a = j9;
            this.f17814b = c5523x;
            this.f17815c = c5525z;
            this.f17824l = -1;
            if (c5525z != null) {
                this.f17821i = c5525z.m21848L();
                this.f17822j = c5525z.m21846J();
                C5517r c5517rM21839C = c5525z.m21839C();
                int size = c5517rM21839C.size();
                for (int i9 = 0; i9 < size; i9++) {
                    String strM21627b = c5517rM21839C.m21627b(i9);
                    String strM21629d = c5517rM21839C.m21629d(i9);
                    if (C5255l.m20513l(strM21627b, HttpHeaders.DATE, true)) {
                        this.f17816d = C5330c.m20934a(strM21629d);
                        this.f17817e = strM21629d;
                    } else if (C5255l.m20513l(strM21627b, HttpHeaders.EXPIRES, true)) {
                        this.f17820h = C5330c.m20934a(strM21629d);
                    } else if (C5255l.m20513l(strM21627b, HttpHeaders.LAST_MODIFIED, true)) {
                        this.f17818f = C5330c.m20934a(strM21629d);
                        this.f17819g = strM21629d;
                    } else if (C5255l.m20513l(strM21627b, HttpHeaders.ETAG, true)) {
                        this.f17823k = strM21629d;
                    } else if (C5255l.m20513l(strM21627b, HttpHeaders.AGE, true)) {
                        this.f17824l = C5057d.m19782V(strM21629d, -1);
                    }
                }
            }
        }

        /* renamed from: a */
        public final long m20333a() {
            Date date = this.f17816d;
            long jMax = date != null ? Math.max(0L, this.f17822j - date.getTime()) : 0L;
            int i9 = this.f17824l;
            if (i9 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i9));
            }
            long j9 = this.f17822j;
            return jMax + (j9 - this.f17821i) + (this.f17813a - j9);
        }

        /* renamed from: b */
        public final C5201b m20334b() {
            C5201b c5201bM20335c = m20335c();
            return (c5201bM20335c.m20331b() == null || !this.f17814b.m21804b().m21247i()) ? c5201bM20335c : new C5201b(null, null);
        }

        /* renamed from: c */
        public final C5201b m20335c() {
            String str;
            if (this.f17815c == null) {
                return new C5201b(this.f17814b, null);
            }
            if ((!this.f17814b.m21808f() || this.f17815c.m21855z() != null) && C5201b.f17810c.m20332a(this.f17815c, this.f17814b)) {
                C5487d c5487dM21804b = this.f17814b.m21804b();
                if (c5487dM21804b.m21245g() || m20337e(this.f17814b)) {
                    return new C5201b(this.f17814b, null);
                }
                C5487d c5487dM21850u = this.f17815c.m21850u();
                long jM20333a = m20333a();
                long jM20336d = m20336d();
                if (c5487dM21804b.m21241c() != -1) {
                    jM20336d = Math.min(jM20336d, TimeUnit.SECONDS.toMillis(c5487dM21804b.m21241c()));
                }
                long millis = 0;
                long millis2 = c5487dM21804b.m21243e() != -1 ? TimeUnit.SECONDS.toMillis(c5487dM21804b.m21243e()) : 0L;
                if (!c5487dM21850u.m21244f() && c5487dM21804b.m21242d() != -1) {
                    millis = TimeUnit.SECONDS.toMillis(c5487dM21804b.m21242d());
                }
                if (!c5487dM21850u.m21245g()) {
                    long j9 = millis2 + jM20333a;
                    if (j9 < millis + jM20336d) {
                        C5525z.a aVarM21843G = this.f17815c.m21843G();
                        if (j9 >= jM20336d) {
                            aVarM21843G.m21856a(HttpHeaders.WARNING, "110 HttpURLConnection \"Response is stale\"");
                        }
                        if (jM20333a > DateUtils.MILLIS_PER_DAY && m20338f()) {
                            aVarM21843G.m21856a(HttpHeaders.WARNING, "113 HttpURLConnection \"Heuristic expiration\"");
                        }
                        return new C5201b(null, aVarM21843G.m21858c());
                    }
                }
                String str2 = this.f17823k;
                if (str2 != null) {
                    str = HttpHeaders.IF_NONE_MATCH;
                } else {
                    if (this.f17818f != null) {
                        str2 = this.f17819g;
                    } else {
                        if (this.f17816d == null) {
                            return new C5201b(this.f17814b, null);
                        }
                        str2 = this.f17817e;
                    }
                    str = HttpHeaders.IF_MODIFIED_SINCE;
                }
                C5517r.a aVarM21628c = this.f17814b.m21807e().m21628c();
                C0042f.m155b(str2);
                aVarM21628c.m21633c(str, str2);
                return new C5201b(this.f17814b.m21810h().m21816e(aVarM21628c.m21635e()).m21813b(), this.f17815c);
            }
            return new C5201b(this.f17814b, null);
        }

        /* renamed from: d */
        public final long m20336d() {
            C5525z c5525z = this.f17815c;
            C0042f.m155b(c5525z);
            if (c5525z.m21850u().m21241c() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.m21241c());
            }
            Date date = this.f17820h;
            if (date != null) {
                Date date2 = this.f17816d;
                long time = date.getTime() - (date2 != null ? date2.getTime() : this.f17822j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f17818f == null || this.f17815c.m21847K().m21811i().m21658m() != null) {
                return 0L;
            }
            Date date3 = this.f17816d;
            long time2 = date3 != null ? date3.getTime() : this.f17821i;
            Date date4 = this.f17818f;
            C0042f.m155b(date4);
            long time3 = time2 - date4.getTime();
            if (time3 > 0) {
                return time3 / 10;
            }
            return 0L;
        }

        /* renamed from: e */
        public final boolean m20337e(C5523x c5523x) {
            return (c5523x.m21806d(HttpHeaders.IF_MODIFIED_SINCE) == null && c5523x.m21806d(HttpHeaders.IF_NONE_MATCH) == null) ? false : true;
        }

        /* renamed from: f */
        public final boolean m20338f() {
            C5525z c5525z = this.f17815c;
            C0042f.m155b(c5525z);
            return c5525z.m21850u().m21241c() == -1 && this.f17820h == null;
        }
    }

    public C5201b(C5523x c5523x, C5525z c5525z) {
        this.f17811a = c5523x;
        this.f17812b = c5525z;
    }

    /* renamed from: a */
    public final C5525z m20330a() {
        return this.f17812b;
    }

    /* renamed from: b */
    public final C5523x m20331b() {
        return this.f17811a;
    }
}
