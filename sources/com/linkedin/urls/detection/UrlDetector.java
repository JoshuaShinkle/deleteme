package com.linkedin.urls.detection;

import com.linkedin.urls.UrlPart;
import com.linkedin.urls.detection.DomainNameReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p238x4.C6573a;
import p238x4.C6574b;
import p247y4.C6789a;
import p247y4.C6790b;

/* loaded from: classes2.dex */
public class UrlDetector {

    /* renamed from: k */
    public static final Set<String> f15689k = Collections.unmodifiableSet(new HashSet(Arrays.asList("http://", "https://", "ftp://", "ftps://", "http%3a//", "https%3a//", "ftp%3a//", "ftps%3a//")));

    /* renamed from: a */
    public final UrlDetectorOptions f15690a;

    /* renamed from: b */
    public final C6790b f15691b;

    /* renamed from: c */
    public StringBuilder f15692c = new StringBuilder();

    /* renamed from: d */
    public boolean f15693d = false;

    /* renamed from: e */
    public boolean f15694e = false;

    /* renamed from: f */
    public boolean f15695f = false;

    /* renamed from: g */
    public boolean f15696g = false;

    /* renamed from: h */
    public ArrayList<C6573a> f15697h = new ArrayList<>();

    /* renamed from: i */
    public HashMap<Character, Integer> f15698i = new HashMap<>();

    /* renamed from: j */
    public C6574b f15699j = new C6574b();

    public enum CharacterMatch {
        CharacterNotMatched,
        CharacterMatchStop,
        CharacterMatchStart
    }

    public enum ReadEndState {
        ValidUrl,
        InvalidUrl
    }

    /* renamed from: com.linkedin.urls.detection.UrlDetector$a */
    public class C4473a implements DomainNameReader.InterfaceC4472a {
        public C4473a() {
        }

        @Override // com.linkedin.urls.detection.DomainNameReader.InterfaceC4472a
        /* renamed from: a */
        public void mo17853a(char c9) {
            UrlDetector.this.m17855b(c9);
        }
    }

    /* renamed from: com.linkedin.urls.detection.UrlDetector$b */
    public static /* synthetic */ class C4474b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f15708a;

        static {
            int[] iArr = new int[DomainNameReader.ReaderNextState.values().length];
            f15708a = iArr;
            try {
                iArr[DomainNameReader.ReaderNextState.ValidDomainName.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15708a[DomainNameReader.ReaderNextState.ReadFragment.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15708a[DomainNameReader.ReaderNextState.ReadPath.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15708a[DomainNameReader.ReaderNextState.ReadPort.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15708a[DomainNameReader.ReaderNextState.ReadQueryString.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15708a[DomainNameReader.ReaderNextState.ReadUserPass.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public UrlDetector(String str, UrlDetectorOptions urlDetectorOptions) {
        this.f15691b = new C6790b(str);
        this.f15690a = urlDetectorOptions;
    }

    /* renamed from: b */
    public final CharacterMatch m17855b(char c9) {
        boolean z8;
        if ((c9 == '\"' && this.f15690a.m17869a(UrlDetectorOptions.QUOTE_MATCH)) || (c9 == '\'' && this.f15690a.m17869a(UrlDetectorOptions.SINGLE_QUOTE_MATCH))) {
            if (c9 == '\"') {
                z8 = this.f15694e;
                this.f15694e = true;
            } else {
                z8 = this.f15695f;
                this.f15695f = true;
            }
            Integer numValueOf = Integer.valueOf(m17857d(c9) + 1);
            this.f15698i.put(Character.valueOf(c9), numValueOf);
            return (z8 || numValueOf.intValue() % 2 == 0) ? CharacterMatch.CharacterMatchStop : CharacterMatch.CharacterMatchStart;
        }
        UrlDetectorOptions urlDetectorOptions = this.f15690a;
        UrlDetectorOptions urlDetectorOptions2 = UrlDetectorOptions.BRACKET_MATCH;
        if (urlDetectorOptions.m17869a(urlDetectorOptions2) && (c9 == '[' || c9 == '{' || c9 == '(')) {
            this.f15698i.put(Character.valueOf(c9), Integer.valueOf(m17857d(c9) + 1));
            return CharacterMatch.CharacterMatchStart;
        }
        UrlDetectorOptions urlDetectorOptions3 = this.f15690a;
        UrlDetectorOptions urlDetectorOptions4 = UrlDetectorOptions.XML;
        if (urlDetectorOptions3.m17869a(urlDetectorOptions4) && c9 == '<') {
            this.f15698i.put(Character.valueOf(c9), Integer.valueOf(m17857d(c9) + 1));
            return CharacterMatch.CharacterMatchStart;
        }
        if ((!this.f15690a.m17869a(urlDetectorOptions2) || (c9 != ']' && c9 != '}' && c9 != ')')) && (!this.f15690a.m17869a(urlDetectorOptions4) || c9 != '>')) {
            return CharacterMatch.CharacterNotMatched;
        }
        Integer numValueOf2 = Integer.valueOf(m17857d(c9) + 1);
        this.f15698i.put(Character.valueOf(c9), numValueOf2);
        return m17857d(c9 != ')' ? c9 != '>' ? c9 != ']' ? c9 != '}' ? (char) 0 : '{' : '[' : '<' : '(') > numValueOf2.intValue() ? CharacterMatch.CharacterMatchStop : CharacterMatch.CharacterMatchStart;
    }

    /* renamed from: c */
    public List<C6573a> m17856c() {
        m17859f();
        return this.f15697h;
    }

    /* renamed from: d */
    public final int m17857d(char c9) {
        Integer num = this.f15698i.get(Character.valueOf(c9));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* renamed from: e */
    public final int m17858e(int i9) {
        if (this.f15693d) {
            if (!m17868o(i9)) {
                this.f15691b.m25342d();
                if (this.f15692c.length() > 0) {
                    StringBuilder sb = this.f15692c;
                    sb.delete(sb.length() - 1, this.f15692c.length());
                } else {
                    i9 = 0;
                }
                int iM25341c = (this.f15691b.m25341c() - this.f15692c.length()) + i9;
                if (!m17860g(this.f15692c.substring(i9))) {
                    this.f15691b.m25346h(iM25341c);
                    m17861h(ReadEndState.InvalidUrl);
                }
            }
        } else {
            if (m17867n() && this.f15692c.length() > 0) {
                this.f15693d = true;
                return this.f15692c.length();
            }
            if (this.f15692c.length() > 0 && this.f15690a.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.f15691b.m25339a(1)) {
                this.f15691b.m25342d();
                StringBuilder sb2 = this.f15692c;
                sb2.delete(sb2.length() - 1, this.f15692c.length());
                if (m17860g(this.f15692c.toString())) {
                    return i9;
                }
                m17861h(ReadEndState.InvalidUrl);
                return i9;
            }
            m17861h(ReadEndState.InvalidUrl);
        }
        return 0;
    }

    /* renamed from: f */
    public final void m17859f() {
        int length;
        loop0: while (true) {
            length = 0;
            while (!this.f15691b.m25340b()) {
                char cM25345g = this.f15691b.m25345g();
                if (cM25345g == ' ') {
                    if (this.f15690a.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.f15692c.length() > 0 && this.f15693d) {
                        this.f15691b.m25342d();
                        if (!m17860g(this.f15692c.substring(length))) {
                            m17861h(ReadEndState.InvalidUrl);
                        }
                    }
                    this.f15692c.append(cM25345g);
                    m17861h(ReadEndState.InvalidUrl);
                } else if (cM25345g != '%') {
                    if (cM25345g == ':') {
                        this.f15692c.append(cM25345g);
                        length = m17858e(length);
                    } else if (cM25345g != '@') {
                        if (cM25345g == '[') {
                            if (this.f15696g && m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                                m17861h(ReadEndState.InvalidUrl);
                                length = 0;
                            }
                            int iM25341c = this.f15691b.m25341c();
                            if (!this.f15693d) {
                                StringBuilder sb = this.f15692c;
                                sb.delete(0, sb.length());
                            }
                            this.f15692c.append(cM25345g);
                            if (!m17860g(this.f15692c.substring(length))) {
                                m17861h(ReadEndState.InvalidUrl);
                                this.f15691b.m25346h(iM25341c);
                                this.f15696g = true;
                            }
                        } else if (cM25345g == 65294 || cM25345g == 65377 || cM25345g == '.') {
                            this.f15692c.append(cM25345g);
                            if (!m17860g(this.f15692c.substring(length))) {
                                m17861h(ReadEndState.InvalidUrl);
                            }
                        } else if (cM25345g != '/') {
                            if (m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                                m17861h(ReadEndState.InvalidUrl);
                            } else {
                                this.f15692c.append(cM25345g);
                            }
                        } else if (this.f15693d || (this.f15690a.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) && this.f15692c.length() > 1)) {
                            this.f15691b.m25342d();
                            if (!m17860g(this.f15692c.substring(length))) {
                                m17861h(ReadEndState.InvalidUrl);
                            }
                        } else {
                            m17861h(ReadEndState.InvalidUrl);
                            this.f15692c.append(cM25345g);
                            this.f15693d = m17863j();
                            length = this.f15692c.length();
                        }
                    } else if (this.f15692c.length() > 0) {
                        this.f15699j.m25189d(UrlPart.USERNAME_PASSWORD, length);
                        this.f15692c.append(cM25345g);
                        if (!m17860g(null)) {
                            m17861h(ReadEndState.InvalidUrl);
                        }
                    }
                } else if (!this.f15691b.m25339a(2)) {
                    continue;
                } else if (this.f15691b.m25343e(2).equalsIgnoreCase("3a")) {
                    this.f15692c.append(cM25345g);
                    this.f15692c.append(this.f15691b.m25345g());
                    this.f15692c.append(this.f15691b.m25345g());
                    length = m17858e(length);
                } else if (C6789a.m25334f(this.f15691b.m25344f(0)) && C6789a.m25334f(this.f15691b.m25344f(1))) {
                    this.f15692c.append(cM25345g);
                    this.f15692c.append(this.f15691b.m25345g());
                    this.f15692c.append(this.f15691b.m25345g());
                    if (!m17860g(this.f15692c.substring(length))) {
                        m17861h(ReadEndState.InvalidUrl);
                    }
                }
            }
            break loop0;
        }
        if (!this.f15690a.m17869a(UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN) || this.f15692c.length() <= 0 || !this.f15693d || m17860g(this.f15692c.substring(length))) {
            return;
        }
        m17861h(ReadEndState.InvalidUrl);
    }

    /* renamed from: g */
    public final boolean m17860g(String str) {
        int length = this.f15692c.length();
        if (str != null) {
            length -= str.length();
        }
        C6574b c6574b = this.f15699j;
        UrlPart urlPart = UrlPart.HOST;
        c6574b.m25189d(urlPart, length);
        switch (C4474b.f15708a[new DomainNameReader(this.f15691b, this.f15692c, str, this.f15690a, new C4473a()).m17852f().ordinal()]) {
            case 1:
                return m17861h(ReadEndState.ValidUrl);
            case 2:
                return m17862i();
            case 3:
                return m17864k();
            case 4:
                return m17865l();
            case 5:
                return m17866m();
            case 6:
                int iM25188c = this.f15699j.m25188c(urlPart);
                this.f15699j.m25191f(urlPart);
                return m17868o(iM25188c);
            default:
                return false;
        }
    }

    /* renamed from: h */
    public final boolean m17861h(ReadEndState readEndState) {
        ReadEndState readEndState2 = ReadEndState.ValidUrl;
        if (readEndState == readEndState2 && this.f15692c.length() > 0) {
            int length = this.f15692c.length();
            if (this.f15694e) {
                int i9 = length - 1;
                if (this.f15692c.charAt(i9) == '\"') {
                    this.f15692c.delete(i9, length);
                }
            }
            if (this.f15692c.length() > 0) {
                this.f15699j.m25190e(this.f15692c.toString());
                this.f15697h.add(this.f15699j.m25186a());
            }
        }
        StringBuilder sb = this.f15692c;
        sb.delete(0, sb.length());
        this.f15694e = false;
        this.f15693d = false;
        this.f15696g = false;
        this.f15699j = new C6574b();
        return readEndState == readEndState2;
    }

    /* renamed from: i */
    public final boolean m17862i() {
        this.f15699j.m25189d(UrlPart.FRAGMENT, this.f15692c.length() - 1);
        while (!this.f15691b.m25340b()) {
            char cM25345g = this.f15691b.m25345g();
            if (cM25345g == ' ' || m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                return m17861h(ReadEndState.ValidUrl);
            }
            this.f15692c.append(cM25345g);
        }
        return m17861h(ReadEndState.ValidUrl);
    }

    /* renamed from: j */
    public final boolean m17863j() {
        if (this.f15691b.m25340b()) {
            return false;
        }
        char cM25345g = this.f15691b.m25345g();
        if (cM25345g == '/') {
            this.f15692c.append(cM25345g);
            return true;
        }
        this.f15691b.m25342d();
        m17861h(ReadEndState.InvalidUrl);
        return false;
    }

    /* renamed from: k */
    public final boolean m17864k() {
        this.f15699j.m25189d(UrlPart.PATH, this.f15692c.length() - 1);
        while (!this.f15691b.m25340b()) {
            char cM25345g = this.f15691b.m25345g();
            if (cM25345g == ' ' || m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                return m17861h(ReadEndState.ValidUrl);
            }
            this.f15692c.append(cM25345g);
            if (cM25345g == '?') {
                return m17866m();
            }
            if (cM25345g == '#') {
                return m17862i();
            }
        }
        return m17861h(ReadEndState.ValidUrl);
    }

    /* renamed from: l */
    public final boolean m17865l() {
        this.f15699j.m25189d(UrlPart.PORT, this.f15692c.length());
        int i9 = 0;
        while (!this.f15691b.m25340b()) {
            char cM25345g = this.f15691b.m25345g();
            i9++;
            if (cM25345g == '/') {
                this.f15692c.append(cM25345g);
                return m17864k();
            }
            if (cM25345g == '?') {
                this.f15692c.append(cM25345g);
                return m17866m();
            }
            if (cM25345g == '#') {
                this.f15692c.append(cM25345g);
                return m17862i();
            }
            if (m17855b(cM25345g) == CharacterMatch.CharacterMatchStop || !C6789a.m25335g(cM25345g)) {
                this.f15691b.m25342d();
                if (i9 == 1) {
                    StringBuilder sb = this.f15692c;
                    sb.delete(sb.length() - 1, this.f15692c.length());
                }
                this.f15699j.m25191f(UrlPart.PORT);
                return m17861h(ReadEndState.ValidUrl);
            }
            this.f15692c.append(cM25345g);
        }
        return m17861h(ReadEndState.ValidUrl);
    }

    /* renamed from: m */
    public final boolean m17866m() {
        this.f15699j.m25189d(UrlPart.QUERY, this.f15692c.length() - 1);
        while (!this.f15691b.m25340b()) {
            char cM25345g = this.f15691b.m25345g();
            if (cM25345g == '#') {
                this.f15692c.append(cM25345g);
                return m17862i();
            }
            if (cM25345g == ' ' || m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                return m17861h(ReadEndState.ValidUrl);
            }
            this.f15692c.append(cM25345g);
        }
        return m17861h(ReadEndState.ValidUrl);
    }

    /* renamed from: n */
    public final boolean m17867n() {
        if (this.f15690a.m17869a(UrlDetectorOptions.HTML) && this.f15692c.length() >= 7) {
            StringBuilder sb = this.f15692c;
            if ("mailto:".equalsIgnoreCase(sb.substring(sb.length() - 7))) {
                return m17861h(ReadEndState.InvalidUrl);
            }
        }
        int length = this.f15692c.length();
        int i9 = 0;
        while (!this.f15691b.m25340b()) {
            char cM25345g = this.f15691b.m25345g();
            if (cM25345g == '/') {
                this.f15692c.append(cM25345g);
                if (i9 == 1) {
                    if (!f15689k.contains(this.f15692c.toString().toLowerCase())) {
                        return false;
                    }
                    this.f15699j.m25189d(UrlPart.SCHEME, 0);
                    return true;
                }
                i9++;
            } else {
                if (cM25345g == ' ' || m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                    this.f15692c.append(cM25345g);
                    break;
                }
                if (cM25345g == '[') {
                    this.f15691b.m25342d();
                    return false;
                }
                if (length > 0 || i9 > 0 || !C6789a.m25329a(cM25345g)) {
                    this.f15691b.m25342d();
                    return m17868o(0);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0060, code lost:
    
        if (r3 == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        r9 = r8.f15692c.length() - r0;
        r3 = r8.f15692c;
        r3.delete(r0, r3.length());
        r8.f15691b.m25346h(java.lang.Math.max((r8.f15691b.m25341c() - r9) - r2, 0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0083, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008a, code lost:
    
        return m17861h(com.linkedin.urls.detection.UrlDetector.ReadEndState.f15705c);
     */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m17868o(int i9) {
        int length = this.f15692c.length();
        int i10 = 0;
        loop0: while (true) {
            int i11 = i10;
            while (i10 == 0 && !this.f15691b.m25340b()) {
                char cM25345g = this.f15691b.m25345g();
                if (cM25345g == '@') {
                    this.f15692c.append(cM25345g);
                    this.f15699j.m25189d(UrlPart.USERNAME_PASSWORD, i9);
                    return m17860g("");
                }
                if (C6789a.m25333e(cM25345g) || cM25345g == '[') {
                    this.f15692c.append(cM25345g);
                    i11 = 1;
                } else {
                    if (cM25345g == '#' || cM25345g == ' ' || cM25345g == '/' || m17855b(cM25345g) != CharacterMatch.CharacterNotMatched) {
                        break;
                    }
                    this.f15692c.append(cM25345g);
                }
            }
            i10 = 1;
        }
    }
}
