package p238x4;

import com.linkedin.urls.UrlPart;

/* renamed from: x4.b */
/* loaded from: classes2.dex */
public class C6574b {

    /* renamed from: a */
    public String f22109a;

    /* renamed from: b */
    public int f22110b = -1;

    /* renamed from: c */
    public int f22111c = -1;

    /* renamed from: d */
    public int f22112d = -1;

    /* renamed from: e */
    public int f22113e = -1;

    /* renamed from: f */
    public int f22114f = -1;

    /* renamed from: g */
    public int f22115g = -1;

    /* renamed from: h */
    public int f22116h = -1;

    /* renamed from: x4.b$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f22117a;

        static {
            int[] iArr = new int[UrlPart.values().length];
            f22117a = iArr;
            try {
                iArr[UrlPart.SCHEME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f22117a[UrlPart.USERNAME_PASSWORD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f22117a[UrlPart.HOST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f22117a[UrlPart.PORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f22117a[UrlPart.PATH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f22117a[UrlPart.QUERY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f22117a[UrlPart.FRAGMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* renamed from: a */
    public C6573a m25186a() {
        return new C6573a(this);
    }

    /* renamed from: b */
    public String m25187b() {
        return this.f22109a;
    }

    /* renamed from: c */
    public int m25188c(UrlPart urlPart) {
        switch (a.f22117a[urlPart.ordinal()]) {
            case 1:
                return this.f22110b;
            case 2:
                return this.f22111c;
            case 3:
                return this.f22112d;
            case 4:
                return this.f22113e;
            case 5:
                return this.f22114f;
            case 6:
                return this.f22115g;
            case 7:
                return this.f22116h;
            default:
                return -1;
        }
    }

    /* renamed from: d */
    public void m25189d(UrlPart urlPart, int i9) {
        switch (a.f22117a[urlPart.ordinal()]) {
            case 1:
                this.f22110b = i9;
                break;
            case 2:
                this.f22111c = i9;
                break;
            case 3:
                this.f22112d = i9;
                break;
            case 4:
                this.f22113e = i9;
                break;
            case 5:
                this.f22114f = i9;
                break;
            case 6:
                this.f22115g = i9;
                break;
            case 7:
                this.f22116h = i9;
                break;
        }
    }

    /* renamed from: e */
    public void m25190e(String str) {
        this.f22109a = str;
    }

    /* renamed from: f */
    public void m25191f(UrlPart urlPart) {
        m25189d(urlPart, -1);
    }
}
