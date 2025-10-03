package p060e8;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.C5693j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import p070f8.C4793a;
import p090h8.C5033d;

/* renamed from: e8.a */
/* loaded from: classes.dex */
public final class C4769a {

    /* renamed from: a */
    public static final Pattern f16566a = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");

    /* renamed from: b */
    public static final char[] f16567b = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: e8.a$a */
    public static class a {

        /* renamed from: a */
        public final String f16568a;

        /* renamed from: b */
        public final boolean f16569b;

        public a(String str, boolean z8) {
            this.f16568a = str;
            this.f16569b = z8;
        }
    }

    /* renamed from: a */
    public static void m18904a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[32768];
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i9);
            }
        }
    }

    /* renamed from: b */
    public static a m18905b(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        byte[] bArr = new byte[4];
        if (byteBuffer.remaining() >= 4) {
            byteBuffer.get(bArr);
            byteBuffer.rewind();
        }
        byte b9 = bArr[0];
        if ((b9 == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) || (b9 == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0)) {
            return new a("UTF-32", false);
        }
        if ((b9 == -2 && bArr[1] == -1) || (b9 == -1 && bArr[1] == -2)) {
            return new a("UTF-16", false);
        }
        if (b9 == -17 && bArr[1] == -69 && bArr[2] == -65) {
            return new a("UTF-8", true);
        }
        return null;
    }

    /* renamed from: c */
    public static ByteBuffer m18906c() {
        return ByteBuffer.allocate(0);
    }

    /* renamed from: d */
    public static String m18907d(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f16566a.matcher(str);
        if (matcher.find()) {
            return m18911h(matcher.group(1).trim().replace("charset=", ""));
        }
        return null;
    }

    /* renamed from: e */
    public static String m18908e() {
        StringBuilder sb = new StringBuilder(32);
        Random random = new Random();
        for (int i9 = 0; i9 < 32; i9++) {
            char[] cArr = f16567b;
            sb.append(cArr[random.nextInt(cArr.length)]);
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static Document m18909f(InputStream inputStream, String str, String str2, C5033d c5033d) throws IOException {
        if (inputStream == null) {
            return new Document(str2);
        }
        C4793a c4793aM19026w = C4793a.m19026w(inputStream, 32768, 0);
        c4793aM19026w.mark(32768);
        ByteBuffer byteBufferM18910g = m18910g(c4793aM19026w, 5119);
        boolean z8 = c4793aM19026w.read() == -1;
        c4793aM19026w.reset();
        a aVarM18905b = m18905b(byteBufferM18910g);
        if (aVarM18905b != null) {
            str = aVarM18905b.f16568a;
        }
        Document documentM19648c = null;
        if (str == null) {
            Document documentM19649d = c5033d.m19649d(Charset.forName("UTF-8").decode(byteBufferM18910g).toString(), str2);
            Iterator<Element> it = documentM19649d.m22871s0("meta[http-equiv=content-type], meta[charset]").iterator();
            String strMo19255c = null;
            while (it.hasNext()) {
                Element next = it.next();
                if (next.mo19262q("http-equiv")) {
                    strMo19255c = m18907d(next.mo19255c(FirebaseAnalytics.Param.CONTENT));
                }
                if (strMo19255c == null && next.mo19262q("charset")) {
                    strMo19255c = next.mo19255c("charset");
                }
                if (strMo19255c != null) {
                    break;
                }
            }
            if (strMo19255c == null && documentM19649d.mo19259j() > 0 && (documentM19649d.m22951i(0) instanceof C5693j)) {
                C5693j c5693j = (C5693j) documentM19649d.m22951i(0);
                if (c5693j.m22962U().equals("xml")) {
                    strMo19255c = c5693j.mo19255c("encoding");
                }
            }
            String strM18911h = m18911h(strMo19255c);
            if (strM18911h != null && !strM18911h.equalsIgnoreCase("UTF-8")) {
                str = strM18911h.trim().replaceAll("[\"']", "");
            } else if (z8) {
                documentM19648c = documentM19649d;
            }
        } else {
            C4772d.m19003i(str, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
        }
        if (documentM19648c == null) {
            String str3 = str != null ? str : "UTF-8";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(c4793aM19026w, str3), 32768);
            if (aVarM18905b != null && aVarM18905b.f16569b) {
                bufferedReader.skip(1L);
            }
            try {
                documentM19648c = c5033d.m19648c(bufferedReader, str2);
                documentM19648c.m22830z0().m22832b(str3);
            } catch (UncheckedIOException e9) {
                throw e9.m22820a();
            }
        }
        c4793aM19026w.close();
        return documentM19648c;
    }

    /* renamed from: g */
    public static ByteBuffer m18910g(InputStream inputStream, int i9) {
        C4772d.m18999e(i9 >= 0, "maxSize must be 0 (unlimited) or larger");
        return C4793a.m19026w(inputStream, 32768, i9).m19028u(i9);
    }

    /* renamed from: h */
    public static String m18911h(String str) {
        if (str != null && str.length() != 0) {
            String strReplaceAll = str.trim().replaceAll("[\"']", "");
            try {
                if (Charset.isSupported(strReplaceAll)) {
                    return strReplaceAll;
                }
                String upperCase = strReplaceAll.toUpperCase(Locale.ENGLISH);
                if (Charset.isSupported(upperCase)) {
                    return upperCase;
                }
            } catch (IllegalCharsetNameException unused) {
            }
        }
        return null;
    }
}
