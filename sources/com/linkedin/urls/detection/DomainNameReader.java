package com.linkedin.urls.detection;

import java.util.regex.Pattern;
import p247y4.C6789a;
import p247y4.C6790b;

/* loaded from: classes2.dex */
public class DomainNameReader {

    /* renamed from: a */
    public StringBuilder f15668a;

    /* renamed from: b */
    public String f15669b;

    /* renamed from: c */
    public UrlDetectorOptions f15670c;

    /* renamed from: d */
    public int f15671d = 0;

    /* renamed from: e */
    public int f15672e = 0;

    /* renamed from: f */
    public int f15673f = 0;

    /* renamed from: g */
    public int f15674g = 0;

    /* renamed from: h */
    public boolean f15675h = false;

    /* renamed from: i */
    public boolean f15676i = false;

    /* renamed from: j */
    public boolean f15677j = false;

    /* renamed from: k */
    public boolean f15678k = false;

    /* renamed from: l */
    public final C6790b f15679l;

    /* renamed from: m */
    public final InterfaceC4472a f15680m;

    public enum ReaderNextState {
        InvalidDomainName,
        ValidDomainName,
        ReadFragment,
        ReadPath,
        ReadPort,
        ReadQueryString,
        ReadUserPass
    }

    /* renamed from: com.linkedin.urls.detection.DomainNameReader$a */
    public interface InterfaceC4472a {
        /* renamed from: a */
        void mo17853a(char c9);
    }

    public DomainNameReader(C6790b c6790b, StringBuilder sb, String str, UrlDetectorOptions urlDetectorOptions, InterfaceC4472a interfaceC4472a) {
        this.f15668a = sb;
        this.f15669b = str;
        this.f15679l = c6790b;
        this.f15670c = urlDetectorOptions;
        this.f15680m = interfaceC4472a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ReaderNextState m17847a(ReaderNextState readerNextState, Character ch) {
        int i9;
        int i10;
        boolean z8 = true;
        if (this.f15668a.length() > 3) {
            StringBuilder sb = this.f15668a;
            i9 = sb.substring(sb.length() - 3).equalsIgnoreCase("%2e") ? 3 : 1;
        }
        int length = this.f15668a.length();
        int i11 = this.f15674g;
        int i12 = length - i11;
        int i13 = this.f15672e;
        boolean zM17848b = false;
        if (i13 <= 0) {
            i9 = 0;
        }
        int i14 = i12 + i9;
        int i15 = this.f15671d;
        int i16 = (i13 > 0 ? 1 : 0) + i15;
        if (i14 < 255 && i16 <= 127) {
            if (this.f15675h) {
                zM17848b = m17849c(this.f15668a.substring(i11).toLowerCase());
            } else if (this.f15676i) {
                zM17848b = m17850d(this.f15668a.substring(i11).toLowerCase());
            } else if ((i13 > 0 && i15 >= 1) || ((i15 >= 2 && i13 == 0) || (this.f15670c.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.f15671d == 0))) {
                int length2 = this.f15668a.length() - this.f15673f;
                if (this.f15672e == 0) {
                    length2--;
                }
                int iMax = Math.max(length2, 0);
                StringBuilder sb2 = this.f15668a;
                if (!sb2.substring(iMax, Math.min(4, sb2.length() - iMax) + iMax).equalsIgnoreCase("xn--") && ((i10 = this.f15673f) < 2 || i10 > 22)) {
                    z8 = false;
                }
                zM17848b = z8 ? m17848b(this.f15668a.substring(iMax).toLowerCase()) : z8;
            }
        }
        if (!zM17848b) {
            this.f15679l.m25342d();
            return ReaderNextState.InvalidDomainName;
        }
        if (ch != null) {
            this.f15668a.append(ch);
        }
        return readerNextState;
    }

    /* renamed from: b */
    public final boolean m17848b(String str) {
        return Pattern.compile("(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:δοκιμή|испытание|рф|срб|טעסט|آزمایشی|إختبار|الاردن|الجزائر|السعودية|المغرب|امارات|بھارت|تونس|سورية|فلسطين|قطر|مصر|परीक्षा|भारत|ভারত|ਭਾਰਤ|ભારત|இந்தியா|இலங்கை|சிங்கப்பூர்|பரிட்சை|భారత్|ලංකා|ไทย|テスト|中国|中國|台湾|台灣|新加坡|测试|測試|香港|테스트|한국|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw])").matcher(str).matches();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m17849c(String str) {
        int i9;
        Integer numValueOf;
        if (str.length() <= 0) {
            return false;
        }
        int i10 = this.f15671d;
        if (i10 == 0) {
            try {
                long j9 = (str.length() > 2 && str.charAt(0) == '0' && str.charAt(1) == 'x') ? Long.parseLong(str.substring(2), 16) : str.charAt(0) == '0' ? Long.parseLong(str.substring(1), 8) : Long.parseLong(str);
                return j9 <= 4294967295L && j9 >= 16843008;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        if (i10 != 3) {
            return false;
        }
        String[] strArrM25338j = C6789a.m25338j(str);
        boolean z8 = true;
        for (int i11 = 0; i11 < strArrM25338j.length && z8; i11++) {
            String strSubstring = strArrM25338j[i11];
            if (strSubstring.length() <= 0) {
                z8 = false;
            } else {
                if (strSubstring.length() > 2 && strSubstring.charAt(0) == '0' && strSubstring.charAt(1) == 'x') {
                    strSubstring = strSubstring.substring(2);
                    i9 = 16;
                } else if (strSubstring.charAt(0) == '0') {
                    strSubstring = strSubstring.substring(1);
                    i9 = 8;
                } else {
                    i9 = 10;
                }
                if (strSubstring.length() == 0) {
                    numValueOf = 0;
                } else {
                    try {
                        numValueOf = Integer.valueOf(Integer.parseInt(strSubstring, i9));
                    } catch (NumberFormatException unused2) {
                        return false;
                    }
                }
                if (numValueOf.intValue() < 0 || numValueOf.intValue() > 255) {
                }
            }
        }
        return z8;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0065 A[PHI: r12 r14
      0x0065: PHI (r12v4 boolean) = (r12v2 boolean), (r12v2 boolean), (r12v1 boolean), (r12v1 boolean), (r12v1 boolean) binds: [B:50:0x00ac, B:53:0x00b2, B:19:0x003d, B:29:0x005b, B:23:0x0047] A[DONT_GENERATE, DONT_INLINE]
      0x0065: PHI (r14v2 int) = (r14v1 int), (r14v1 int), (r14v1 int), (r14v5 int), (r14v1 int) binds: [B:50:0x00ac, B:53:0x00b2, B:19:0x003d, B:29:0x005b, B:23:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0085  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m17850d(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length >= 3) {
            char c9 = ']';
            if (charArray[charArray.length - 1] == ']' && charArray[0] == '[' && (charArray[1] != ':' || charArray[2] == ':')) {
                StringBuilder sb = new StringBuilder();
                Boolean bool = Boolean.TRUE;
                int i9 = 1;
                int i10 = 0;
                char c10 = 0;
                boolean z8 = false;
                boolean z9 = false;
                int i11 = 0;
                while (i10 < charArray.length) {
                    char c11 = charArray[i10];
                    if (c11 == '%') {
                        if (c11 == '%') {
                            if (charArray.length - i10 >= 2 && charArray[i10 + 1] == '2') {
                                int i12 = i10 + 2;
                                if (charArray[i12] == 'e') {
                                    sb.append("%2e");
                                    bool = Boolean.FALSE;
                                    i10 = i12;
                                }
                            }
                            z8 = true;
                            if (bool.booleanValue()) {
                            }
                        } else if (bool.booleanValue() && (!z8 || charArray[i10] == '%')) {
                            if (!m17849c(sb.toString())) {
                                return false;
                            }
                            i9++;
                        }
                    } else if (c11 == ':') {
                        if (c10 == ':') {
                            if (z9) {
                                return false;
                            }
                            z9 = true;
                        }
                        if (!bool.booleanValue()) {
                            return false;
                        }
                        Boolean bool2 = Boolean.TRUE;
                        i9++;
                        sb.delete(0, sb.length());
                        bool = bool2;
                        i11 = 0;
                    } else if (c11 != '[') {
                        if (c11 != c9) {
                            if (!z8) {
                                sb.append(c11);
                                if (bool.booleanValue() && C6789a.m25334f(charArray[i10])) {
                                    i11++;
                                } else {
                                    bool = Boolean.FALSE;
                                }
                            } else if (!C6789a.m25336h(c11)) {
                                return false;
                            }
                        }
                    }
                    if (i11 > 4 || i9 > 8) {
                        return false;
                    }
                    c10 = charArray[i10];
                    i10++;
                    c9 = ']';
                }
                if (i9 != 1) {
                    return i9 >= 8 || z9;
                }
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c1  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ReaderNextState m17851e() {
        int i9;
        char c9;
        String str = this.f15669b;
        if (str == null) {
            this.f15674g = this.f15668a.length();
        } else {
            if ((str.length() == 1 && C6789a.m25333e(this.f15669b.charAt(0))) || (this.f15669b.length() == 3 && this.f15669b.equalsIgnoreCase("%2e"))) {
                return ReaderNextState.InvalidDomainName;
            }
            this.f15674g = this.f15668a.length() - this.f15669b.length();
            this.f15675h = true;
            char[] charArray = this.f15669b.toCharArray();
            int length = charArray.length;
            boolean z8 = length > 2 && charArray[0] == '0' && ((c9 = charArray[1]) == 'x' || c9 == 'X');
            int i10 = z8 ? 2 : 0;
            boolean z9 = false;
            int i11 = 0;
            while (i10 < length && !z9) {
                char c10 = charArray[i10];
                int i12 = this.f15672e + 1;
                this.f15672e = i12;
                this.f15673f = i12;
                if (i12 > 64) {
                    return ReaderNextState.InvalidDomainName;
                }
                if (C6789a.m25333e(c10)) {
                    this.f15671d++;
                    this.f15672e = 0;
                } else if (c10 == '[') {
                    this.f15676i = true;
                    this.f15675h = false;
                } else if (c10 == '%' && (i9 = i10 + 2) < length) {
                    int i13 = i10 + 1;
                    if (C6789a.m25334f(charArray[i13]) && C6789a.m25334f(charArray[i9])) {
                        if (charArray[i13] == '2' && charArray[i9] == 'e') {
                            this.f15671d++;
                            this.f15672e = 0;
                        } else {
                            this.f15675h = false;
                        }
                        i10 = i9;
                    }
                } else if (z8) {
                    if (!C6789a.m25334f(c10)) {
                        this.f15675h = false;
                        i10--;
                        z8 = false;
                    }
                } else if (C6789a.m25329a(c10) || c10 == '-' || c10 >= 192) {
                    this.f15675h = false;
                } else if (!C6789a.m25335g(c10) && !this.f15670c.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN)) {
                    i11 = i10 + 1;
                    this.f15672e = 0;
                    this.f15673f = 0;
                    this.f15675h = true;
                    this.f15671d = 0;
                    z9 = true;
                }
                i10++;
            }
            if (i11 > 0) {
                if (i11 < this.f15669b.length()) {
                    StringBuilder sb = this.f15668a;
                    sb.replace(0, sb.length(), this.f15669b.substring(i11));
                    this.f15674g = 0;
                }
                if (i11 >= this.f15669b.length() || this.f15668a.toString().equals(".")) {
                    return ReaderNextState.InvalidDomainName;
                }
            }
        }
        return ReaderNextState.ValidDomainName;
    }

    /* renamed from: f */
    public ReaderNextState m17852f() {
        ReaderNextState readerNextStateM17851e = m17851e();
        ReaderNextState readerNextState = ReaderNextState.InvalidDomainName;
        if (readerNextStateM17851e == readerNextState) {
            return readerNextState;
        }
        boolean z8 = false;
        while (!z8 && !this.f15679l.m25340b()) {
            char cM25345g = this.f15679l.m25345g();
            if (cM25345g == '/') {
                return m17847a(ReaderNextState.ReadPath, Character.valueOf(cM25345g));
            }
            if (cM25345g == ':' && (!this.f15676i || this.f15677j)) {
                return m17847a(ReaderNextState.ReadPort, Character.valueOf(cM25345g));
            }
            if (cM25345g == '?') {
                return m17847a(ReaderNextState.ReadQueryString, Character.valueOf(cM25345g));
            }
            if (cM25345g == '#') {
                return m17847a(ReaderNextState.ReadFragment, Character.valueOf(cM25345g));
            }
            if (cM25345g == '@') {
                this.f15679l.m25342d();
                return ReaderNextState.ReadUserPass;
            }
            if (C6789a.m25333e(cM25345g) || (cM25345g == '%' && this.f15679l.m25339a(2) && this.f15679l.m25343e(2).equalsIgnoreCase("2e"))) {
                if (this.f15672e < 1) {
                    z8 = true;
                } else {
                    this.f15668a.append(cM25345g);
                    if (!C6789a.m25333e(cM25345g)) {
                        this.f15668a.append(this.f15679l.m25345g());
                        this.f15668a.append(this.f15679l.m25345g());
                    }
                    if (!this.f15678k) {
                        this.f15671d++;
                        this.f15672e = 0;
                    }
                    if (this.f15672e >= 64) {
                        return ReaderNextState.InvalidDomainName;
                    }
                }
            } else if (this.f15676i && ((C6789a.m25334f(cM25345g) || cM25345g == ':' || cM25345g == '[' || cM25345g == ']' || cM25345g == '%') && !this.f15677j)) {
                if (cM25345g == '%') {
                    this.f15678k = true;
                } else if (cM25345g == ':') {
                    this.f15672e = 0;
                } else {
                    if (cM25345g == '[') {
                        this.f15679l.m25342d();
                        return ReaderNextState.InvalidDomainName;
                    }
                    if (cM25345g != ']') {
                        this.f15672e++;
                    } else {
                        this.f15677j = true;
                        this.f15678k = false;
                    }
                }
                this.f15675h = false;
                this.f15668a.append(cM25345g);
            } else if (C6789a.m25331c(cM25345g) || cM25345g == '-') {
                if (this.f15677j) {
                    this.f15679l.m25342d();
                    z8 = true;
                } else {
                    if (cM25345g != 'x' && cM25345g != 'X' && !C6789a.m25335g(cM25345g)) {
                        this.f15675h = false;
                    }
                    this.f15668a.append(cM25345g);
                    int i9 = this.f15672e + 1;
                    this.f15672e = i9;
                    this.f15673f = i9;
                }
            } else if (cM25345g != '[' || this.f15676i) {
                if (cM25345g == '[' && this.f15677j) {
                    this.f15679l.m25342d();
                } else if (cM25345g == '%' && this.f15679l.m25339a(2) && C6789a.m25334f(this.f15679l.m25344f(0)) && C6789a.m25334f(this.f15679l.m25344f(1))) {
                    this.f15668a.append(cM25345g);
                    this.f15668a.append(this.f15679l.m25345g());
                    this.f15668a.append(this.f15679l.m25345g());
                    int i10 = this.f15672e + 3;
                    this.f15672e = i10;
                    this.f15673f = i10;
                } else {
                    this.f15680m.mo17853a(cM25345g);
                }
                z8 = true;
            } else {
                this.f15676i = true;
                this.f15675h = false;
                this.f15668a.append(cM25345g);
            }
        }
        return m17847a(ReaderNextState.ValidDomainName, null);
    }
}
