package p060e8;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.common.net.HttpHeaders;
import com.google.common.primitives.UnsignedBytes;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import p070f8.C4793a;
import p070f8.C4794b;
import p090h8.C5033d;
import p090h8.C5035f;

/* renamed from: e8.b */
/* loaded from: classes.dex */
public class C4770b implements Connection {

    /* renamed from: a */
    public Connection.InterfaceC5680c f16570a = new c();

    /* renamed from: b */
    public Connection.InterfaceC5681d f16571b = new d();

    /* renamed from: e8.b$b */
    public static abstract class b<T extends Connection.InterfaceC5678a> implements Connection.InterfaceC5678a<T> {

        /* renamed from: a */
        public URL f16572a;

        /* renamed from: b */
        public Connection.Method f16573b;

        /* renamed from: c */
        public Map<String, List<String>> f16574c;

        /* renamed from: d */
        public Map<String, String> f16575d;

        /* renamed from: B */
        public static String m18927B(String str) throws UnsupportedEncodingException {
            try {
                byte[] bytes = str.getBytes(CharEncoding.ISO_8859_1);
                return !m18928H(bytes) ? str : new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
        /* renamed from: H */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static boolean m18928H(byte[] bArr) {
            int i9;
            int i10 = 3;
            if (bArr.length < 3 || (bArr[0] & UnsignedBytes.MAX_VALUE) != 239) {
                i10 = 0;
            } else {
                if (!(((bArr[1] & UnsignedBytes.MAX_VALUE) == 187) & ((bArr[2] & UnsignedBytes.MAX_VALUE) == 191))) {
                }
            }
            int length = bArr.length;
            while (i10 < length) {
                byte b9 = bArr[i10];
                if ((b9 & UnsignedBytes.MAX_POWER_OF_TWO) != 0) {
                    if ((b9 & 224) == 192) {
                        i9 = i10 + 1;
                    } else if ((b9 & 240) == 224) {
                        i9 = i10 + 2;
                    } else {
                        if ((b9 & 248) != 240) {
                            return false;
                        }
                        i9 = i10 + 3;
                    }
                    while (i10 < i9) {
                        i10++;
                        if ((bArr[i10] & 192) != 128) {
                            return false;
                        }
                    }
                }
                i10++;
            }
            return true;
        }

        /* renamed from: A */
        public T m18929A(String str, String str2) {
            C4772d.m19002h(str);
            if (str2 == null) {
                str2 = "";
            }
            List<String> listMo18934G = mo18934G(str);
            if (listMo18934G.isEmpty()) {
                listMo18934G = new ArrayList<>();
                this.f16574c.put(str, listMo18934G);
            }
            listMo18934G.add(m18927B(str2));
            return this;
        }

        /* renamed from: C */
        public final List<String> m18930C(String str) {
            C4772d.m19004j(str);
            for (Map.Entry<String, List<String>> entry : this.f16574c.entrySet()) {
                if (str.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return Collections.emptyList();
        }

        /* renamed from: D */
        public boolean mo18931D(String str) {
            C4772d.m19003i(str, "Cookie name must not be empty");
            return this.f16575d.containsKey(str);
        }

        /* renamed from: E */
        public boolean mo18932E(String str, String str2) {
            C4772d.m19002h(str);
            C4772d.m19002h(str2);
            Iterator<String> it = mo18934G(str).iterator();
            while (it.hasNext()) {
                if (str2.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: F */
        public String mo18933F(String str) {
            C4772d.m19005k(str, "Header name must not be null");
            List<String> listM18930C = m18930C(str);
            if (listM18930C.size() > 0) {
                return C4771c.m18987i(listM18930C, ", ");
            }
            return null;
        }

        /* renamed from: G */
        public List<String> mo18934G(String str) {
            C4772d.m19002h(str);
            return m18930C(str);
        }

        /* renamed from: I */
        public final Map.Entry<String, List<String>> m18935I(String str) {
            String strM19030a = C4794b.m19030a(str);
            for (Map.Entry<String, List<String>> entry : this.f16574c.entrySet()) {
                if (C4794b.m19030a(entry.getKey()).equals(strM19030a)) {
                    return entry;
                }
            }
            return null;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: b */
        public T mo18936b(String str, String str2) {
            C4772d.m19003i(str, "Header name must not be empty");
            mo18944u(str);
            m18929A(str, str2);
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: c */
        public T mo18937c(String str, String str2) {
            C4772d.m19003i(str, "Cookie name must not be empty");
            C4772d.m19005k(str2, "Cookie value must not be null");
            this.f16575d.put(str, str2);
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: g */
        public T mo18938g(Connection.Method method) {
            C4772d.m19005k(method, "Method must not be null");
            this.f16573b = method;
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: j */
        public boolean mo18939j(String str) {
            C4772d.m19003i(str, "Header name must not be empty");
            return m18930C(str).size() != 0;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: l */
        public URL mo18940l() {
            return this.f16572a;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: m */
        public Connection.Method mo18941m() {
            return this.f16573b;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: q */
        public Map<String, List<String>> mo18942q() {
            return this.f16574c;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: r */
        public Map<String, String> mo18943r() {
            return this.f16575d;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: u */
        public T mo18944u(String str) {
            C4772d.m19003i(str, "Header name must not be empty");
            Map.Entry<String, List<String>> entryM18935I = m18935I(str);
            if (entryM18935I != null) {
                this.f16574c.remove(entryM18935I.getKey());
            }
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5678a
        /* renamed from: z */
        public T mo18945z(URL url) {
            C4772d.m19005k(url, "URL must not be null");
            this.f16572a = url;
            return this;
        }

        public b() {
            this.f16574c = new LinkedHashMap();
            this.f16575d = new LinkedHashMap();
        }
    }

    /* renamed from: e8.b$c */
    public static class c extends b<Connection.InterfaceC5680c> implements Connection.InterfaceC5680c {

        /* renamed from: e */
        public Proxy f16576e;

        /* renamed from: f */
        public int f16577f;

        /* renamed from: g */
        public int f16578g;

        /* renamed from: h */
        public boolean f16579h;

        /* renamed from: i */
        public Collection<Connection.InterfaceC5679b> f16580i;

        /* renamed from: j */
        public String f16581j;

        /* renamed from: k */
        public boolean f16582k;

        /* renamed from: l */
        public boolean f16583l;

        /* renamed from: m */
        public C5033d f16584m;

        /* renamed from: n */
        public boolean f16585n;

        /* renamed from: o */
        public boolean f16586o;

        /* renamed from: p */
        public String f16587p;

        /* renamed from: q */
        public SSLSocketFactory f16588q;

        public c() {
            super();
            this.f16581j = null;
            this.f16582k = false;
            this.f16583l = false;
            this.f16585n = false;
            this.f16586o = true;
            this.f16587p = "UTF-8";
            this.f16577f = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
            this.f16578g = ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
            this.f16579h = true;
            this.f16580i = new ArrayList();
            this.f16573b = Connection.Method.GET;
            m18929A(HttpHeaders.ACCEPT_ENCODING, "gzip");
            m18929A(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            this.f16584m = C5033d.m19645a();
        }

        @Override // p060e8.C4770b.b
        /* renamed from: G */
        public /* bridge */ /* synthetic */ List mo18934G(String str) {
            return super.mo18934G(str);
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: K, reason: merged with bridge method [inline-methods] */
        public c mo18962x(C5033d c5033d) {
            this.f16584m = c5033d;
            this.f16585n = true;
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: L, reason: merged with bridge method [inline-methods] */
        public c mo18950d(int i9) {
            C4772d.m18999e(i9 >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.f16577f = i9;
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: a */
        public int mo18949a() {
            return this.f16577f;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: e */
        public boolean mo18951e() {
            return this.f16582k;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: f */
        public String mo18952f() {
            return this.f16587p;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: h */
        public Connection.InterfaceC5680c mo18953h(String str) {
            this.f16581j = str;
            return this;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: i */
        public boolean mo18954i() {
            return this.f16586o;
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: j */
        public /* bridge */ /* synthetic */ boolean mo18939j(String str) {
            return super.mo18939j(str);
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: k */
        public boolean mo18955k() {
            return this.f16583l;
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: l */
        public /* bridge */ /* synthetic */ URL mo18940l() {
            return super.mo18940l();
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: m */
        public /* bridge */ /* synthetic */ Connection.Method mo18941m() {
            return super.mo18941m();
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: n */
        public SSLSocketFactory mo18956n() {
            return this.f16588q;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: o */
        public Proxy mo18957o() {
            return this.f16576e;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: p */
        public Collection<Connection.InterfaceC5679b> mo18958p() {
            return this.f16580i;
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: q */
        public /* bridge */ /* synthetic */ Map mo18942q() {
            return super.mo18942q();
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: r */
        public /* bridge */ /* synthetic */ Map mo18943r() {
            return super.mo18943r();
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: s */
        public boolean mo18959s() {
            return this.f16579h;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: v */
        public String mo18960v() {
            return this.f16581j;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: w */
        public int mo18961w() {
            return this.f16578g;
        }

        @Override // org.jsoup.Connection.InterfaceC5680c
        /* renamed from: y */
        public C5033d mo18963y() {
            return this.f16584m;
        }
    }

    /* renamed from: h */
    public static Connection m18916h(String str) {
        C4770b c4770b = new C4770b();
        c4770b.mo18925e(str);
        return c4770b;
    }

    /* renamed from: i */
    public static String m18917i(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\"", "%22");
    }

    /* renamed from: j */
    public static String m18918j(String str) {
        try {
            return m18919k(new URL(str)).toExternalForm();
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: k */
    public static URL m18919k(URL url) {
        try {
            return new URL(new URI(url.toExternalForm().replaceAll(StringUtils.SPACE, "%20")).toASCIIString());
        } catch (Exception unused) {
            return url;
        }
    }

    /* renamed from: m */
    public static boolean m18920m(Connection.InterfaceC5680c interfaceC5680c) {
        Iterator<Connection.InterfaceC5679b> it = interfaceC5680c.mo18958p().iterator();
        while (it.hasNext()) {
            if (it.next().m22819f()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jsoup.Connection
    /* renamed from: a */
    public Connection mo18921a(String str) {
        C4772d.m19005k(str, "User agent must not be null");
        this.f16570a.mo18936b(HttpHeaders.USER_AGENT, str);
        return this;
    }

    @Override // org.jsoup.Connection
    /* renamed from: b */
    public Connection mo18922b(String str, String str2) {
        this.f16570a.mo18936b(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    /* renamed from: c */
    public Connection mo18923c(String str, String str2) {
        this.f16570a.mo18937c(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    /* renamed from: d */
    public Connection mo18924d(int i9) {
        this.f16570a.mo18950d(i9);
        return this;
    }

    @Override // org.jsoup.Connection
    /* renamed from: e */
    public Connection mo18925e(String str) {
        C4772d.m19003i(str, "Must supply a valid URL");
        try {
            this.f16570a.mo18945z(new URL(m18918j(str)));
            return this;
        } catch (MalformedURLException e9) {
            throw new IllegalArgumentException("Malformed URL: " + str, e9);
        }
    }

    @Override // org.jsoup.Connection
    public Document get() {
        this.f16570a.mo18938g(Connection.Method.GET);
        m18926l();
        return this.f16571b.mo18978t();
    }

    /* renamed from: l */
    public Connection.InterfaceC5681d m18926l() {
        d dVarM18966M = d.m18966M(this.f16570a);
        this.f16571b = dVarM18966M;
        return dVarM18966M;
    }

    /* renamed from: e8.b$d */
    public static class d extends b<Connection.InterfaceC5681d> implements Connection.InterfaceC5681d {

        /* renamed from: o */
        public static SSLSocketFactory f16589o;

        /* renamed from: p */
        public static final Pattern f16590p = Pattern.compile("(application|text)/\\w*\\+?xml.*");

        /* renamed from: e */
        public int f16591e;

        /* renamed from: f */
        public String f16592f;

        /* renamed from: g */
        public ByteBuffer f16593g;

        /* renamed from: h */
        public InputStream f16594h;

        /* renamed from: i */
        public String f16595i;

        /* renamed from: j */
        public String f16596j;

        /* renamed from: k */
        public boolean f16597k;

        /* renamed from: l */
        public boolean f16598l;

        /* renamed from: m */
        public int f16599m;

        /* renamed from: n */
        public Connection.InterfaceC5680c f16600n;

        /* renamed from: e8.b$d$a */
        public class a implements HostnameVerifier {
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        }

        /* renamed from: e8.b$d$b */
        public class b implements X509TrustManager {
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        public d() {
            super();
            this.f16597k = false;
            this.f16598l = false;
            this.f16599m = 0;
        }

        /* renamed from: K */
        public static HttpURLConnection m18964K(Connection.InterfaceC5680c interfaceC5680c) throws ProtocolException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) (interfaceC5680c.mo18957o() == null ? interfaceC5680c.mo18940l().openConnection() : interfaceC5680c.mo18940l().openConnection(interfaceC5680c.mo18957o()));
            httpURLConnection.setRequestMethod(interfaceC5680c.mo18941m().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(interfaceC5680c.mo18949a());
            httpURLConnection.setReadTimeout(interfaceC5680c.mo18949a() / 2);
            if (httpURLConnection instanceof HttpsURLConnection) {
                SSLSocketFactory sSLSocketFactoryMo18956n = interfaceC5680c.mo18956n();
                if (sSLSocketFactoryMo18956n != null) {
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactoryMo18956n);
                } else if (!interfaceC5680c.mo18954i()) {
                    m18970Q();
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                    httpsURLConnection.setSSLSocketFactory(f16589o);
                    httpsURLConnection.setHostnameVerifier(m18968O());
                }
            }
            if (interfaceC5680c.mo18941m().m22815a()) {
                httpURLConnection.setDoOutput(true);
            }
            if (interfaceC5680c.mo18943r().size() > 0) {
                httpURLConnection.addRequestProperty(HttpHeaders.COOKIE, m18969P(interfaceC5680c));
            }
            for (Map.Entry<String, List<String>> entry : interfaceC5680c.mo18942q().entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    httpURLConnection.addRequestProperty(entry.getKey(), it.next());
                }
            }
            return httpURLConnection;
        }

        /* renamed from: L */
        public static LinkedHashMap<String, List<String>> m18965L(HttpURLConnection httpURLConnection) {
            LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
            int i9 = 0;
            while (true) {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i9);
                String headerField = httpURLConnection.getHeaderField(i9);
                if (headerFieldKey == null && headerField == null) {
                    return linkedHashMap;
                }
                i9++;
                if (headerFieldKey != null && headerField != null) {
                    if (linkedHashMap.containsKey(headerFieldKey)) {
                        linkedHashMap.get(headerFieldKey).add(headerField);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(headerField);
                        linkedHashMap.put(headerFieldKey, arrayList);
                    }
                }
            }
        }

        /* renamed from: M */
        public static d m18966M(Connection.InterfaceC5680c interfaceC5680c) {
            return m18967N(interfaceC5680c, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0082 A[Catch: IOException -> 0x01f4, TryCatch #0 {IOException -> 0x01f4, blocks: (B:24:0x0079, B:26:0x0082, B:27:0x0089, B:29:0x009d, B:33:0x00a7, B:34:0x00bb, B:36:0x00c1, B:38:0x00c9, B:40:0x00d2, B:41:0x00d6, B:42:0x00ef, B:44:0x00f5, B:45:0x010b, B:53:0x011e, B:55:0x0124, B:57:0x012a, B:59:0x0132, B:62:0x013f, B:63:0x014e, B:65:0x0151, B:67:0x015d, B:69:0x0161, B:71:0x016a, B:72:0x0171, B:74:0x017f, B:76:0x0187, B:78:0x018f, B:80:0x0198, B:82:0x01a2, B:86:0x01c2, B:83:0x01ac, B:85:0x01b4, B:79:0x0194, B:87:0x01db, B:51:0x0118, B:90:0x01e4, B:91:0x01f3), top: B:95:0x0079 }] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x01db A[Catch: IOException -> 0x01f4, TRY_LEAVE, TryCatch #0 {IOException -> 0x01f4, blocks: (B:24:0x0079, B:26:0x0082, B:27:0x0089, B:29:0x009d, B:33:0x00a7, B:34:0x00bb, B:36:0x00c1, B:38:0x00c9, B:40:0x00d2, B:41:0x00d6, B:42:0x00ef, B:44:0x00f5, B:45:0x010b, B:53:0x011e, B:55:0x0124, B:57:0x012a, B:59:0x0132, B:62:0x013f, B:63:0x014e, B:65:0x0151, B:67:0x015d, B:69:0x0161, B:71:0x016a, B:72:0x0171, B:74:0x017f, B:76:0x0187, B:78:0x018f, B:80:0x0198, B:82:0x01a2, B:86:0x01c2, B:83:0x01ac, B:85:0x01b4, B:79:0x0194, B:87:0x01db, B:51:0x0118, B:90:0x01e4, B:91:0x01f3), top: B:95:0x0079 }] */
        /* renamed from: N */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static d m18967N(Connection.InterfaceC5680c interfaceC5680c, d dVar) throws IOException {
            HttpURLConnection httpURLConnectionM18964K;
            int responseCode;
            d dVar2;
            String strM18974J;
            C4772d.m19005k(interfaceC5680c, "Request must not be null");
            String protocol = interfaceC5680c.mo18940l().getProtocol();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                throw new MalformedURLException("Only http & https protocols supported");
            }
            boolean zM22815a = interfaceC5680c.mo18941m().m22815a();
            boolean z8 = interfaceC5680c.mo18960v() != null;
            if (!zM22815a) {
                C4772d.m18997c(z8, "Cannot set a request body for HTTP method " + interfaceC5680c.mo18941m());
            }
            try {
                if (interfaceC5680c.mo18958p().size() <= 0 || (zM22815a && !z8)) {
                    String strM18972U = zM22815a ? m18972U(interfaceC5680c) : null;
                    long jNanoTime = System.nanoTime();
                    httpURLConnectionM18964K = m18964K(interfaceC5680c);
                    httpURLConnectionM18964K.connect();
                    if (httpURLConnectionM18964K.getDoOutput()) {
                        m18973W(interfaceC5680c, httpURLConnectionM18964K.getOutputStream(), strM18972U);
                    }
                    responseCode = httpURLConnectionM18964K.getResponseCode();
                    dVar2 = new d(dVar);
                    dVar2.m18977V(httpURLConnectionM18964K, dVar);
                    dVar2.f16600n = interfaceC5680c;
                    if (!dVar2.mo18939j(HttpHeaders.LOCATION) && interfaceC5680c.mo18959s()) {
                        if (responseCode != 307) {
                            interfaceC5680c.mo18938g(Connection.Method.GET);
                            interfaceC5680c.mo18958p().clear();
                            interfaceC5680c.mo18953h(null);
                            interfaceC5680c.mo18944u(HttpHeaders.CONTENT_TYPE);
                        }
                        String strMo18933F = dVar2.mo18933F(HttpHeaders.LOCATION);
                        if (strMo18933F != null && strMo18933F.startsWith("http:/") && strMo18933F.charAt(6) != '/') {
                            strMo18933F = strMo18933F.substring(6);
                        }
                        interfaceC5680c.mo18945z(C4770b.m18919k(C4771c.m18992n(interfaceC5680c.mo18940l(), strMo18933F)));
                        for (Map.Entry<String, String> entry : dVar2.f16575d.entrySet()) {
                            interfaceC5680c.mo18937c(entry.getKey(), entry.getValue());
                        }
                        return m18967N(interfaceC5680c, dVar2);
                    }
                    if ((responseCode >= 200 || responseCode >= 400) && !interfaceC5680c.mo18951e()) {
                        throw new HttpStatusException("HTTP error fetching URL", responseCode, interfaceC5680c.mo18940l().toString());
                    }
                    strM18974J = dVar2.m18974J();
                    if (strM18974J != null && !interfaceC5680c.mo18955k() && !strM18974J.startsWith("text/") && !f16590p.matcher(strM18974J).matches()) {
                        throw new UnsupportedMimeTypeException("Unhandled content type. Must be text/*, application/xml, or application/xhtml+xml", strM18974J, interfaceC5680c.mo18940l().toString());
                    }
                    if (strM18974J != null && f16590p.matcher(strM18974J).matches() && (interfaceC5680c instanceof c) && !((c) interfaceC5680c).f16585n) {
                        interfaceC5680c.mo18962x(C5033d.m19646e());
                    }
                    dVar2.f16595i = C4769a.m18907d(dVar2.f16596j);
                    if (httpURLConnectionM18964K.getContentLength() != 0 || interfaceC5680c.mo18941m() == Connection.Method.HEAD) {
                        dVar2.f16593g = C4769a.m18906c();
                    } else {
                        dVar2.f16594h = null;
                        dVar2.f16594h = httpURLConnectionM18964K.getErrorStream() != null ? httpURLConnectionM18964K.getErrorStream() : httpURLConnectionM18964K.getInputStream();
                        if (dVar2.mo18932E(HttpHeaders.CONTENT_ENCODING, "gzip")) {
                            dVar2.f16594h = new GZIPInputStream(dVar2.f16594h);
                        } else if (dVar2.mo18932E(HttpHeaders.CONTENT_ENCODING, "deflate")) {
                            dVar2.f16594h = new InflaterInputStream(dVar2.f16594h, new Inflater(true));
                        }
                        dVar2.f16594h = C4793a.m19026w(dVar2.f16594h, 32768, interfaceC5680c.mo18961w()).m19029v(jNanoTime, interfaceC5680c.mo18949a());
                    }
                    dVar2.f16597k = true;
                    return dVar2;
                }
                m18971T(interfaceC5680c);
                httpURLConnectionM18964K.connect();
                if (httpURLConnectionM18964K.getDoOutput()) {
                }
                responseCode = httpURLConnectionM18964K.getResponseCode();
                dVar2 = new d(dVar);
                dVar2.m18977V(httpURLConnectionM18964K, dVar);
                dVar2.f16600n = interfaceC5680c;
                if (!dVar2.mo18939j(HttpHeaders.LOCATION)) {
                }
                if (responseCode >= 200) {
                    throw new HttpStatusException("HTTP error fetching URL", responseCode, interfaceC5680c.mo18940l().toString());
                }
                throw new HttpStatusException("HTTP error fetching URL", responseCode, interfaceC5680c.mo18940l().toString());
                strM18974J = dVar2.m18974J();
                if (strM18974J != null) {
                    throw new UnsupportedMimeTypeException("Unhandled content type. Must be text/*, application/xml, or application/xhtml+xml", strM18974J, interfaceC5680c.mo18940l().toString());
                }
                if (strM18974J != null) {
                    interfaceC5680c.mo18962x(C5033d.m19646e());
                }
                dVar2.f16595i = C4769a.m18907d(dVar2.f16596j);
                if (httpURLConnectionM18964K.getContentLength() != 0) {
                    dVar2.f16593g = C4769a.m18906c();
                }
                dVar2.f16597k = true;
                return dVar2;
            } catch (IOException e9) {
                httpURLConnectionM18964K.disconnect();
                throw e9;
            }
            long jNanoTime2 = System.nanoTime();
            httpURLConnectionM18964K = m18964K(interfaceC5680c);
        }

        /* renamed from: O */
        public static HostnameVerifier m18968O() {
            return new a();
        }

        /* renamed from: P */
        public static String m18969P(Connection.InterfaceC5680c interfaceC5680c) {
            StringBuilder sbM18993o = C4771c.m18993o();
            boolean z8 = true;
            for (Map.Entry<String, String> entry : interfaceC5680c.mo18943r().entrySet()) {
                if (z8) {
                    z8 = false;
                } else {
                    sbM18993o.append("; ");
                }
                sbM18993o.append(entry.getKey());
                sbM18993o.append('=');
                sbM18993o.append(entry.getValue());
            }
            return sbM18993o.toString();
        }

        /* renamed from: Q */
        public static synchronized void m18970Q() {
            if (f16589o == null) {
                TrustManager[] trustManagerArr = {new b()};
                try {
                    SSLContext sSLContext = SSLContext.getInstance("SSL");
                    sSLContext.init(null, trustManagerArr, new SecureRandom());
                    f16589o = sSLContext.getSocketFactory();
                } catch (KeyManagementException | NoSuchAlgorithmException unused) {
                    throw new IOException("Can't create unsecure trust manager");
                }
            }
        }

        /* renamed from: T */
        public static void m18971T(Connection.InterfaceC5680c interfaceC5680c) {
            boolean z8;
            URL urlMo18940l = interfaceC5680c.mo18940l();
            StringBuilder sbM18993o = C4771c.m18993o();
            sbM18993o.append(urlMo18940l.getProtocol());
            sbM18993o.append("://");
            sbM18993o.append(urlMo18940l.getAuthority());
            sbM18993o.append(urlMo18940l.getPath());
            sbM18993o.append("?");
            if (urlMo18940l.getQuery() != null) {
                sbM18993o.append(urlMo18940l.getQuery());
                z8 = false;
            } else {
                z8 = true;
            }
            for (Connection.InterfaceC5679b interfaceC5679b : interfaceC5680c.mo18958p()) {
                C4772d.m18997c(interfaceC5679b.m22819f(), "InputStream data not supported in URL query string.");
                if (z8) {
                    z8 = false;
                } else {
                    sbM18993o.append('&');
                }
                sbM18993o.append(URLEncoder.encode(interfaceC5679b.m22818e(), "UTF-8"));
                sbM18993o.append('=');
                sbM18993o.append(URLEncoder.encode(interfaceC5679b.value(), "UTF-8"));
            }
            interfaceC5680c.mo18945z(new URL(sbM18993o.toString()));
            interfaceC5680c.mo18958p().clear();
        }

        /* renamed from: U */
        public static String m18972U(Connection.InterfaceC5680c interfaceC5680c) {
            if (!interfaceC5680c.mo18939j(HttpHeaders.CONTENT_TYPE)) {
                if (C4770b.m18920m(interfaceC5680c)) {
                    String strM18908e = C4769a.m18908e();
                    interfaceC5680c.mo18936b(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + strM18908e);
                    return strM18908e;
                }
                interfaceC5680c.mo18936b(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + interfaceC5680c.mo18952f());
            }
            return null;
        }

        /* renamed from: W */
        public static void m18973W(Connection.InterfaceC5680c interfaceC5680c, OutputStream outputStream, String str) throws IOException {
            Collection<Connection.InterfaceC5679b> collectionMo18958p = interfaceC5680c.mo18958p();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, interfaceC5680c.mo18952f()));
            if (str != null) {
                for (Connection.InterfaceC5679b interfaceC5679b : collectionMo18958p) {
                    bufferedWriter.write("--");
                    bufferedWriter.write(str);
                    bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                    bufferedWriter.write("Content-Disposition: form-data; name=\"");
                    bufferedWriter.write(C4770b.m18917i(interfaceC5679b.m22818e()));
                    bufferedWriter.write("\"");
                    if (interfaceC5679b.m22819f()) {
                        bufferedWriter.write("; filename=\"");
                        bufferedWriter.write(C4770b.m18917i(interfaceC5679b.value()));
                        bufferedWriter.write("\"\r\nContent-Type: ");
                        bufferedWriter.write(interfaceC5679b.m22817d() != null ? interfaceC5679b.m22817d() : "application/octet-stream");
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.flush();
                        C4769a.m18904a(interfaceC5679b.m22816c(), outputStream);
                        outputStream.flush();
                    } else {
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.write(interfaceC5679b.value());
                    }
                    bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                }
                bufferedWriter.write("--");
                bufferedWriter.write(str);
                bufferedWriter.write("--");
            } else if (interfaceC5680c.mo18960v() != null) {
                bufferedWriter.write(interfaceC5680c.mo18960v());
            } else {
                boolean z8 = true;
                for (Connection.InterfaceC5679b interfaceC5679b2 : collectionMo18958p) {
                    if (z8) {
                        z8 = false;
                    } else {
                        bufferedWriter.append('&');
                    }
                    bufferedWriter.write(URLEncoder.encode(interfaceC5679b2.m22818e(), interfaceC5680c.mo18952f()));
                    bufferedWriter.write(61);
                    bufferedWriter.write(URLEncoder.encode(interfaceC5679b2.value(), interfaceC5680c.mo18952f()));
                }
            }
            bufferedWriter.close();
        }

        @Override // p060e8.C4770b.b
        /* renamed from: D */
        public /* bridge */ /* synthetic */ boolean mo18931D(String str) {
            return super.mo18931D(str);
        }

        @Override // p060e8.C4770b.b
        /* renamed from: E */
        public /* bridge */ /* synthetic */ boolean mo18932E(String str, String str2) {
            return super.mo18932E(str, str2);
        }

        @Override // p060e8.C4770b.b
        /* renamed from: F */
        public /* bridge */ /* synthetic */ String mo18933F(String str) {
            return super.mo18933F(str);
        }

        @Override // p060e8.C4770b.b
        /* renamed from: G */
        public /* bridge */ /* synthetic */ List mo18934G(String str) {
            return super.mo18934G(str);
        }

        /* renamed from: J */
        public String m18974J() {
            return this.f16596j;
        }

        /* renamed from: R */
        public void m18975R(Map<String, List<String>> map) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null) {
                    List<String> value = entry.getValue();
                    if (key.equalsIgnoreCase(HttpHeaders.SET_COOKIE)) {
                        for (String str : value) {
                            if (str != null) {
                                C5035f c5035f = new C5035f(str);
                                String strTrim = c5035f.m19664b("=").trim();
                                String strTrim2 = c5035f.m19669g(";").trim();
                                if (strTrim.length() > 0) {
                                    mo18937c(strTrim, strTrim2);
                                }
                            }
                        }
                    }
                    Iterator<String> it = value.iterator();
                    while (it.hasNext()) {
                        m18929A(key, it.next());
                    }
                }
            }
        }

        /* renamed from: S */
        public final void m18976S() {
            InputStream inputStream = this.f16594h;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.f16594h = null;
                    throw th;
                }
                this.f16594h = null;
            }
        }

        /* renamed from: V */
        public final void m18977V(HttpURLConnection httpURLConnection, Connection.InterfaceC5681d interfaceC5681d) {
            this.f16573b = Connection.Method.valueOf(httpURLConnection.getRequestMethod());
            this.f16572a = httpURLConnection.getURL();
            this.f16591e = httpURLConnection.getResponseCode();
            this.f16592f = httpURLConnection.getResponseMessage();
            this.f16596j = httpURLConnection.getContentType();
            m18975R(m18965L(httpURLConnection));
            if (interfaceC5681d != null) {
                for (Map.Entry<String, String> entry : interfaceC5681d.mo18943r().entrySet()) {
                    if (!mo18931D(entry.getKey())) {
                        mo18937c(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: j */
        public /* bridge */ /* synthetic */ boolean mo18939j(String str) {
            return super.mo18939j(str);
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: l */
        public /* bridge */ /* synthetic */ URL mo18940l() {
            return super.mo18940l();
        }

        @Override // p060e8.C4770b.b, org.jsoup.Connection.InterfaceC5678a
        /* renamed from: r */
        public /* bridge */ /* synthetic */ Map mo18943r() {
            return super.mo18943r();
        }

        @Override // org.jsoup.Connection.InterfaceC5681d
        /* renamed from: t */
        public Document mo18978t() throws IOException {
            C4772d.m18999e(this.f16597k, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            if (this.f16593g != null) {
                this.f16594h = new ByteArrayInputStream(this.f16593g.array());
                this.f16598l = false;
            }
            C4772d.m18997c(this.f16598l, "Input stream already read and parsed, cannot re-read.");
            Document documentM18909f = C4769a.m18909f(this.f16594h, this.f16595i, this.f16572a.toExternalForm(), this.f16600n.mo18963y());
            this.f16595i = documentM18909f.m22830z0().m22831a().name();
            this.f16598l = true;
            m18976S();
            return documentM18909f;
        }

        public d(d dVar) throws IOException {
            super();
            this.f16597k = false;
            this.f16598l = false;
            this.f16599m = 0;
            if (dVar != null) {
                int i9 = dVar.f16599m + 1;
                this.f16599m = i9;
                if (i9 >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", dVar.mo18940l()));
                }
            }
        }
    }
}
