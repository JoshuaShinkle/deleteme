package okhttp3;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.C5226i;
import kotlin.collections.C5239v;
import kotlin.text.C5255l;
import okhttp3.C5517r;
import p007a6.C0042f;
import p098i6.C5057d;
import p139m6.C5333f;

/* renamed from: okhttp3.x */
/* loaded from: classes2.dex */
public final class C5523x {

    /* renamed from: a */
    public final C5518s f19050a;

    /* renamed from: b */
    public final String f19051b;

    /* renamed from: c */
    public final C5517r f19052c;

    /* renamed from: d */
    public final AbstractC5524y f19053d;

    /* renamed from: e */
    public final Map<Class<?>, Object> f19054e;

    /* renamed from: f */
    public C5487d f19055f;

    public C5523x(C5518s c5518s, String str, C5517r c5517r, AbstractC5524y abstractC5524y, Map<Class<?>, ? extends Object> map) {
        C0042f.m158e(c5518s, "url");
        C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
        C0042f.m158e(c5517r, "headers");
        C0042f.m158e(map, "tags");
        this.f19050a = c5518s;
        this.f19051b = str;
        this.f19052c = c5517r;
        this.f19053d = abstractC5524y;
        this.f19054e = map;
    }

    /* renamed from: a */
    public final AbstractC5524y m21803a() {
        return this.f19053d;
    }

    /* renamed from: b */
    public final C5487d m21804b() {
        C5487d c5487d = this.f19055f;
        if (c5487d != null) {
            return c5487d;
        }
        C5487d c5487dM21254b = C5487d.f18507n.m21254b(this.f19052c);
        this.f19055f = c5487dM21254b;
        return c5487dM21254b;
    }

    /* renamed from: c */
    public final Map<Class<?>, Object> m21805c() {
        return this.f19054e;
    }

    /* renamed from: d */
    public final String m21806d(String str) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        return this.f19052c.m21626a(str);
    }

    /* renamed from: e */
    public final C5517r m21807e() {
        return this.f19052c;
    }

    /* renamed from: f */
    public final boolean m21808f() {
        return this.f19050a.m21654i();
    }

    /* renamed from: g */
    public final String m21809g() {
        return this.f19051b;
    }

    /* renamed from: h */
    public final a m21810h() {
        return new a(this);
    }

    /* renamed from: i */
    public final C5518s m21811i() {
        return this.f19050a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f19051b);
        sb.append(", url=");
        sb.append(this.f19050a);
        if (this.f19052c.size() != 0) {
            sb.append(", headers=[");
            int i9 = 0;
            for (Pair<? extends String, ? extends String> pair : this.f19052c) {
                int i10 = i9 + 1;
                if (i9 < 0) {
                    C5226i.m20407m();
                }
                Pair<? extends String, ? extends String> pair2 = pair;
                String strM20346a = pair2.m20346a();
                String strM20347b = pair2.m20347b();
                if (i9 > 0) {
                    sb.append(", ");
                }
                sb.append(strM20346a);
                sb.append(':');
                sb.append(strM20347b);
                i9 = i10;
            }
            sb.append(']');
        }
        if (!this.f19054e.isEmpty()) {
            sb.append(", tags=");
            sb.append(this.f19054e);
        }
        sb.append('}');
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* renamed from: okhttp3.x$a */
    public static class a {

        /* renamed from: a */
        public C5518s f19056a;

        /* renamed from: b */
        public String f19057b;

        /* renamed from: c */
        public C5517r.a f19058c;

        /* renamed from: d */
        public AbstractC5524y f19059d;

        /* renamed from: e */
        public Map<Class<?>, Object> f19060e;

        public a() {
            this.f19060e = new LinkedHashMap();
            this.f19057b = "GET";
            this.f19058c = new C5517r.a();
        }

        /* renamed from: a */
        public a m21812a(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            this.f19058c.m21631a(str, str2);
            return this;
        }

        /* renamed from: b */
        public C5523x m21813b() {
            C5518s c5518s = this.f19056a;
            if (c5518s != null) {
                return new C5523x(c5518s, this.f19057b, this.f19058c.m21635e(), this.f19059d, C5057d.m19780T(this.f19060e));
            }
            throw new IllegalStateException("url == null".toString());
        }

        /* renamed from: c */
        public a m21814c(C5487d c5487d) {
            C0042f.m158e(c5487d, "cacheControl");
            String string = c5487d.toString();
            return string.length() == 0 ? m21819h(HttpHeaders.CACHE_CONTROL) : m21815d(HttpHeaders.CACHE_CONTROL, string);
        }

        /* renamed from: d */
        public a m21815d(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            this.f19058c.m21638h(str, str2);
            return this;
        }

        /* renamed from: e */
        public a m21816e(C5517r c5517r) {
            C0042f.m158e(c5517r, "headers");
            this.f19058c = c5517r.m21628c();
            return this;
        }

        /* renamed from: f */
        public a m21817f(String str, AbstractC5524y abstractC5524y) {
            C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
            if (!(str.length() > 0)) {
                throw new IllegalArgumentException("method.isEmpty() == true".toString());
            }
            if (abstractC5524y == null) {
                if (!(true ^ C5333f.m20954d(str))) {
                    throw new IllegalArgumentException(("method " + str + " must have a request body.").toString());
                }
            } else if (!C5333f.m20953a(str)) {
                throw new IllegalArgumentException(("method " + str + " must not have a request body.").toString());
            }
            this.f19057b = str;
            this.f19059d = abstractC5524y;
            return this;
        }

        /* renamed from: g */
        public a m21818g(AbstractC5524y abstractC5524y) {
            C0042f.m158e(abstractC5524y, TtmlNode.TAG_BODY);
            return m21817f("POST", abstractC5524y);
        }

        /* renamed from: h */
        public a m21819h(String str) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            this.f19058c.m21637g(str);
            return this;
        }

        /* renamed from: i */
        public a m21820i(String str) {
            C0042f.m158e(str, "url");
            if (C5255l.m20523v(str, "ws:", true)) {
                StringBuilder sb = new StringBuilder();
                sb.append("http:");
                String strSubstring = str.substring(3);
                C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
                sb.append(strSubstring);
                str = sb.toString();
            } else if (C5255l.m20523v(str, "wss:", true)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https:");
                String strSubstring2 = str.substring(4);
                C0042f.m157d(strSubstring2, "this as java.lang.String).substring(startIndex)");
                sb2.append(strSubstring2);
                str = sb2.toString();
            }
            return m21821j(C5518s.f18941k.m21698d(str));
        }

        /* renamed from: j */
        public a m21821j(C5518s c5518s) {
            C0042f.m158e(c5518s, "url");
            this.f19056a = c5518s;
            return this;
        }

        public a(C5523x c5523x) {
            Map<Class<?>, Object> mapM20437e;
            C0042f.m158e(c5523x, "request");
            this.f19060e = new LinkedHashMap();
            this.f19056a = c5523x.m21811i();
            this.f19057b = c5523x.m21809g();
            this.f19059d = c5523x.m21803a();
            if (c5523x.m21805c().isEmpty()) {
                mapM20437e = new LinkedHashMap<>();
            } else {
                mapM20437e = C5239v.m20437e(c5523x.m21805c());
            }
            this.f19060e = mapM20437e;
            this.f19058c = c5523x.m21807e().m21628c();
        }
    }
}
