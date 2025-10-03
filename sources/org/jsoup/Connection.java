package org.jsoup;

import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.nodes.Document;
import p090h8.C5033d;

/* loaded from: classes.dex */
public interface Connection {

    public enum Method {
        GET(false),
        POST(true),
        PUT(true),
        DELETE(false),
        PATCH(true),
        HEAD(false),
        OPTIONS(false),
        TRACE(false);

        private final boolean hasBody;

        Method(boolean z8) {
            this.hasBody = z8;
        }

        /* renamed from: a */
        public final boolean m22815a() {
            return this.hasBody;
        }
    }

    /* renamed from: org.jsoup.Connection$a */
    public interface InterfaceC5678a<T extends InterfaceC5678a> {
        /* renamed from: b */
        T mo18936b(String str, String str2);

        /* renamed from: c */
        T mo18937c(String str, String str2);

        /* renamed from: g */
        T mo18938g(Method method);

        /* renamed from: j */
        boolean mo18939j(String str);

        /* renamed from: l */
        URL mo18940l();

        /* renamed from: m */
        Method mo18941m();

        /* renamed from: q */
        Map<String, List<String>> mo18942q();

        /* renamed from: r */
        Map<String, String> mo18943r();

        /* renamed from: u */
        T mo18944u(String str);

        /* renamed from: z */
        T mo18945z(URL url);
    }

    /* renamed from: org.jsoup.Connection$b */
    public interface InterfaceC5679b {
        /* renamed from: c */
        InputStream m22816c();

        /* renamed from: d */
        String m22817d();

        /* renamed from: e */
        String m22818e();

        /* renamed from: f */
        boolean m22819f();

        String value();
    }

    /* renamed from: org.jsoup.Connection$c */
    public interface InterfaceC5680c extends InterfaceC5678a<InterfaceC5680c> {
        /* renamed from: a */
        int mo18949a();

        /* renamed from: d */
        InterfaceC5680c mo18950d(int i9);

        /* renamed from: e */
        boolean mo18951e();

        /* renamed from: f */
        String mo18952f();

        /* renamed from: h */
        InterfaceC5680c mo18953h(String str);

        /* renamed from: i */
        boolean mo18954i();

        /* renamed from: k */
        boolean mo18955k();

        /* renamed from: n */
        SSLSocketFactory mo18956n();

        /* renamed from: o */
        Proxy mo18957o();

        /* renamed from: p */
        Collection<InterfaceC5679b> mo18958p();

        /* renamed from: s */
        boolean mo18959s();

        /* renamed from: v */
        String mo18960v();

        /* renamed from: w */
        int mo18961w();

        /* renamed from: x */
        InterfaceC5680c mo18962x(C5033d c5033d);

        /* renamed from: y */
        C5033d mo18963y();
    }

    /* renamed from: org.jsoup.Connection$d */
    public interface InterfaceC5681d extends InterfaceC5678a<InterfaceC5681d> {
        /* renamed from: t */
        Document mo18978t();
    }

    /* renamed from: a */
    Connection mo18921a(String str);

    /* renamed from: b */
    Connection mo18922b(String str, String str2);

    /* renamed from: c */
    Connection mo18923c(String str, String str2);

    /* renamed from: d */
    Connection mo18924d(int i9);

    /* renamed from: e */
    Connection mo18925e(String str);

    Document get();
}
