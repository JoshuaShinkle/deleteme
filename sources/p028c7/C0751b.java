package p028c7;

/* renamed from: c7.b */
/* loaded from: classes.dex */
public class C0751b {

    /* renamed from: b */
    public final String f3496b;

    /* renamed from: c */
    public final int f3497c;

    /* renamed from: d */
    public Exception f3498d;

    public C0751b(String str) {
        this(str, 5222);
    }

    /* renamed from: a */
    public String m3628a() {
        Exception exc = this.f3498d;
        return toString() + " Exception: " + (exc == null ? "No error logged" : exc.getMessage());
    }

    /* renamed from: b */
    public String m3629b() {
        return this.f3496b;
    }

    /* renamed from: c */
    public int m3630c() {
        return this.f3497c;
    }

    /* renamed from: d */
    public void m3631d(Exception exc) {
        this.f3498d = exc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0751b)) {
            return false;
        }
        C0751b c0751b = (C0751b) obj;
        return this.f3496b.equals(c0751b.f3496b) && this.f3497c == c0751b.f3497c;
    }

    public int hashCode() {
        return ((this.f3496b.hashCode() + 37) * 37) + this.f3497c;
    }

    public String toString() {
        return this.f3496b + ":" + this.f3497c;
    }

    public C0751b(String str, int i9) {
        if (str == null) {
            throw new IllegalArgumentException("FQDN is null");
        }
        if (i9 < 0 || i9 > 65535) {
            throw new IllegalArgumentException("Port must be a 16-bit unsiged integer (i.e. between 0-65535. Port was: " + i9);
        }
        if (str.charAt(str.length() - 1) == '.') {
            this.f3496b = str.substring(0, str.length() - 1);
        } else {
            this.f3496b = str;
        }
        this.f3497c = i9;
    }
}
