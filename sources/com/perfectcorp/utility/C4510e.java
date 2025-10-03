package com.perfectcorp.utility;

import android.util.Pair;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.apache.commons.p159io.IOUtils;

/* renamed from: com.perfectcorp.utility.e */
/* loaded from: classes2.dex */
public class C4510e {

    /* renamed from: g */
    public static final Pattern f15948g = Pattern.compile("((aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(biz|b[abdefghijmnorstvwyz])|(cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(edu|e[cegrstu])|f[ijkmor]|(gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(info|int|i[delmnoqrst])|(jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(name|net|n[acefgilopruz])|(org|om)|(pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(δοκιμή|испытание|рф|срб|טעסט|آزمایشی|إختبار|الاردن|الجزائر|السعودية|المغرب|امارات|بھارت|تونس|سورية|فلسطين|قطر|مصر|परीक्षा|भारत|ভারত|ਭਾਰਤ|ભારત|இந்தியா|இலங்கை|சிங்கப்பூர்|பரிட்சை|భారత్|ලංකා|ไทย|テスト|中国|中國|台湾|台灣|新加坡|测试|測試|香港|테스트|한국|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw])");

    /* renamed from: h */
    public static final Pattern f15949h = Pattern.compile("((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?((?:(?:[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,64}\\.)+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:δοκιμή|испытание|рф|срб|טעסט|آزمایشی|إختبار|الاردن|الجزائر|السعودية|المغرب|امارات|بھارت|تونس|سورية|فلسطين|قطر|مصر|परीक्षा|भारत|ভারত|ਭਾਰਤ|ભારત|இந்தியா|இலங்கை|சிங்கப்பூர்|பரிட்சை|భారత్|ලංකා|ไทย|テスト|中国|中國|台湾|台灣|新加坡|测试|測試|香港|테스트|한국|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");

    /* renamed from: i */
    public static final Pattern f15950i = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");

    /* renamed from: j */
    public static b f15951j = new b();

    /* renamed from: d */
    public String f15955d;

    /* renamed from: e */
    public String f15956e;

    /* renamed from: a */
    public ArrayList<Pair<String, String>> f15952a = new ArrayList<>();

    /* renamed from: b */
    public ArrayList<Pair<String, String>> f15953b = new ArrayList<>();

    /* renamed from: c */
    public ArrayList<Pair<String, a>> f15954c = new ArrayList<>();

    /* renamed from: f */
    public String f15957f = "";

    /* renamed from: com.perfectcorp.utility.e$a */
    public class a {

        /* renamed from: a */
        public String f15958a;

        /* renamed from: b */
        public byte[] f15959b;

        /* renamed from: c */
        public String f15960c;
    }

    /* renamed from: com.perfectcorp.utility.e$b */
    public static class b {

        /* renamed from: a */
        public long f15961a = 0;

        /* renamed from: b */
        public long f15962b = 0;

        /* renamed from: c */
        public long f15963c = 0;

        /* renamed from: d */
        public TreeMap<String, Long> f15964d = new TreeMap<>();
    }

    public C4510e(String str) {
        this.f15955d = "";
        this.f15956e = "";
        Long l9 = f15951j.f15964d.get(str);
        f15951j.f15964d.put(str, l9 == null ? 1L : Long.valueOf(l9.longValue() + 1));
        try {
            if (str.length() == 0) {
                return;
            }
            String[] strArrSplit = str.split("\\?");
            if (strArrSplit.length == 0) {
                return;
            }
            this.f15955d = strArrSplit[0];
            if (strArrSplit.length > 1) {
                for (String str2 : strArrSplit[1].split("&")) {
                    String[] strArrSplit2 = str2.split("=");
                    this.f15953b.add(new Pair<>(strArrSplit2[0], strArrSplit2.length == 2 ? strArrSplit2[1] : ""));
                }
            }
            this.f15956e = new URL(strArrSplit[0]).getHost();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: e */
    public static String m18121e(String str) {
        return str.replaceAll("(\r\n|\n)", "<br />");
    }

    /* renamed from: a */
    public final void m18122a(PrintWriter printWriter, String str, a aVar, OutputStream outputStream, String str2) throws IOException {
        try {
            printWriter.append((CharSequence) ("--" + str2)).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append((CharSequence) ("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + aVar.f15960c + "\"")).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type: ");
            sb.append(aVar.f15958a);
            printWriter.append((CharSequence) sb.toString()).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.flush();
            try {
                outputStream.write(aVar.f15959b);
                outputStream.write(IOUtils.LINE_SEPARATOR_WINDOWS.getBytes());
                outputStream.flush();
            } catch (IOException e9) {
                e9.printStackTrace();
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: b */
    public final void m18123b(PrintWriter printWriter, String str, String str2, String str3) {
        try {
            printWriter.append("--").append((CharSequence) str3).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append("Content-Disposition: form-data; name=\"").append((CharSequence) str).append("\"").append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.append((CharSequence) str2).append(IOUtils.LINE_SEPARATOR_WINDOWS);
            printWriter.flush();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: c */
    public <T> void m18124c(String str, T t8) {
        if (t8 != null) {
            this.f15952a.add(new Pair<>(str, t8.toString()));
        }
    }

    /* renamed from: d */
    public <T> void m18125d(String str, T t8) {
        if (t8 != null) {
            this.f15953b.add(new Pair<>(str, t8.toString()));
        }
    }

    /* renamed from: f */
    public String m18126f() {
        return this.f15955d;
    }

    /* renamed from: g */
    public ArrayList<Pair<String, String>> m18127g() {
        return this.f15952a;
    }

    /* renamed from: h */
    public String m18128h() throws UnsupportedEncodingException {
        String str = "";
        if (this.f15953b.size() > 0) {
            Iterator<Pair<String, String>> it = this.f15953b.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                Pair<String, String> next = it.next();
                String str2 = (String) next.first;
                String strEncode = (String) next.second;
                if (!str2.isEmpty()) {
                    if (z8) {
                        str = str + "&";
                    } else {
                        z8 = true;
                    }
                    try {
                        strEncode = URLEncoder.encode(strEncode, "UTF-8");
                    } catch (UnsupportedEncodingException e9) {
                        e9.printStackTrace();
                    }
                    str = str + str2 + "=" + strEncode;
                }
            }
        }
        return str;
    }

    /* renamed from: i */
    public String m18129i() {
        String str;
        String str2 = "".equals(this.f15955d) ? "" : this.f15955d;
        if (this.f15953b.size() > 0) {
            Iterator<Pair<String, String>> it = this.f15953b.iterator();
            boolean z8 = false;
            while (it.hasNext()) {
                Pair<String, String> next = it.next();
                String str3 = (String) next.first;
                String strReplaceAll = (String) next.second;
                if (!str3.isEmpty()) {
                    if (z8) {
                        str = str2 + "&";
                    } else {
                        str = str2 + "?";
                        z8 = true;
                    }
                    try {
                        strReplaceAll = URLEncoder.encode(strReplaceAll, "UTF-8").replaceAll("\\+", "%20");
                    } catch (UnsupportedEncodingException e9) {
                        e9.printStackTrace();
                    }
                    str2 = str + str3 + "=" + strReplaceAll;
                }
            }
        }
        return str2;
    }

    /* renamed from: j */
    public boolean m18130j(PrintWriter printWriter, OutputStream outputStream, String str) throws IOException {
        if (this.f15953b.size() <= 0) {
            return true;
        }
        Iterator<Pair<String, String>> it = this.f15953b.iterator();
        while (it.hasNext()) {
            Pair<String, String> next = it.next();
            String str2 = (String) next.first;
            String str3 = (String) next.second;
            if (!str2.isEmpty()) {
                m18123b(printWriter, str2, str3, str);
            }
        }
        Iterator<Pair<String, a>> it2 = this.f15954c.iterator();
        while (it2.hasNext()) {
            Pair<String, a> next2 = it2.next();
            String str4 = (String) next2.first;
            a aVar = (a) next2.second;
            if (!str4.isEmpty() && aVar != null) {
                m18122a(printWriter, str4, aVar, outputStream, str);
            }
        }
        printWriter.append("--").append((CharSequence) str).append("--").append(IOUtils.LINE_SEPARATOR_WINDOWS).flush();
        printWriter.close();
        return true;
    }
}
