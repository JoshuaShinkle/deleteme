package p195s6;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.collections.C5226i;
import kotlin.collections.C5234q;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0042f;
import p098i6.C5054a;
import p098i6.C5057d;
import p204t6.C6344y;

/* renamed from: s6.d */
/* loaded from: classes.dex */
public final class C6270d implements HostnameVerifier {

    /* renamed from: a */
    public static final C6270d f21152a = new C6270d();

    /* renamed from: a */
    public final List<String> m24012a(X509Certificate x509Certificate) throws CertificateParsingException {
        C0042f.m158e(x509Certificate, "certificate");
        return C5234q.m20412A(m24014c(x509Certificate, 7), m24014c(x509Certificate, 2));
    }

    /* renamed from: b */
    public final String m24013b(String str) {
        if (!m24015d(str)) {
            return str;
        }
        Locale locale = Locale.US;
        C0042f.m157d(locale, "US");
        String lowerCase = str.toLowerCase(locale);
        C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    /* renamed from: c */
    public final List<String> m24014c(X509Certificate x509Certificate, int i9) throws CertificateParsingException {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return C5226i.m20400f();
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && C0042f.m154a(list.get(0), Integer.valueOf(i9)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return C5226i.m20400f();
        }
    }

    /* renamed from: d */
    public final boolean m24015d(String str) {
        return str.length() == ((int) C6344y.m24297b(str, 0, 0, 3, null));
    }

    /* renamed from: e */
    public final boolean m24016e(String str, X509Certificate x509Certificate) {
        C0042f.m158e(str, "host");
        C0042f.m158e(x509Certificate, "certificate");
        return C5057d.m19795i(str) ? m24019h(str, x509Certificate) : m24018g(str, x509Certificate);
    }

    /* renamed from: f */
    public final boolean m24017f(String str, String str2) {
        String str3;
        String str4 = str;
        if ((str4 == null || str.length() == 0) || C5255l.m20525x(str4, ".", false, 2, null) || C5255l.m20512k(str4, "..", false, 2, null)) {
            return false;
        }
        if ((str2 == null || str2.length() == 0) || C5255l.m20525x(str2, ".", false, 2, null) || C5255l.m20512k(str2, "..", false, 2, null)) {
            return false;
        }
        if (!C5255l.m20512k(str4, ".", false, 2, null)) {
            str4 = str4 + ClassUtils.PACKAGE_SEPARATOR_CHAR;
        }
        String str5 = str4;
        if (C5255l.m20512k(str2, ".", false, 2, null)) {
            str3 = str2;
        } else {
            str3 = str2 + ClassUtils.PACKAGE_SEPARATOR_CHAR;
        }
        String strM24013b = m24013b(str3);
        if (!StringsKt__StringsKt.m20451C(strM24013b, "*", false, 2, null)) {
            return C0042f.m154a(str5, strM24013b);
        }
        if (!C5255l.m20525x(strM24013b, "*.", false, 2, null) || StringsKt__StringsKt.m20461M(strM24013b, '*', 1, false, 4, null) != -1 || str5.length() < strM24013b.length() || C0042f.m154a("*.", strM24013b)) {
            return false;
        }
        String strSubstring = strM24013b.substring(1);
        C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
        if (!C5255l.m20512k(str5, strSubstring, false, 2, null)) {
            return false;
        }
        int length = str5.length() - strSubstring.length();
        return length <= 0 || StringsKt__StringsKt.m20466R(str5, ClassUtils.PACKAGE_SEPARATOR_CHAR, length + (-1), false, 4, null) == -1;
    }

    /* renamed from: g */
    public final boolean m24018g(String str, X509Certificate x509Certificate) throws CertificateParsingException {
        String strM24013b = m24013b(str);
        List<String> listM24014c = m24014c(x509Certificate, 2);
        if ((listM24014c instanceof Collection) && listM24014c.isEmpty()) {
            return false;
        }
        Iterator<T> it = listM24014c.iterator();
        while (it.hasNext()) {
            if (f21152a.m24017f(strM24013b, (String) it.next())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: h */
    public final boolean m24019h(String str, X509Certificate x509Certificate) throws CertificateParsingException {
        String strM19759e = C5054a.m19759e(str);
        List<String> listM24014c = m24014c(x509Certificate, 7);
        if ((listM24014c instanceof Collection) && listM24014c.isEmpty()) {
            return false;
        }
        Iterator<T> it = listM24014c.iterator();
        while (it.hasNext()) {
            if (C0042f.m154a(strM19759e, C5054a.m19759e((String) it.next()))) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        C0042f.m158e(str, "host");
        C0042f.m158e(sSLSession, "session");
        if (!m24015d(str)) {
            return false;
        }
        try {
            Certificate certificate = sSLSession.getPeerCertificates()[0];
            C0042f.m156c(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            return m24016e(str, (X509Certificate) certificate);
        } catch (SSLException unused) {
            return false;
        }
    }
}
