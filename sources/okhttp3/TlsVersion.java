package okhttp3;

import p007a6.C0040d;
import p007a6.C0042f;

/* loaded from: classes2.dex */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");


    /* renamed from: b */
    public static final C5481a f18478b = new C5481a(null);
    private final String javaName;

    /* renamed from: okhttp3.TlsVersion$a */
    public static final class C5481a {
        public C5481a() {
        }

        public /* synthetic */ C5481a(C0040d c0040d) {
            this();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* renamed from: a */
        public final TlsVersion m21216a(String str) {
            C0042f.m158e(str, "javaName");
            int iHashCode = str.hashCode();
            if (iHashCode != 79201641) {
                if (iHashCode != 79923350) {
                    switch (iHashCode) {
                        case -503070503:
                            if (str.equals("TLSv1.1")) {
                                return TlsVersion.TLS_1_1;
                            }
                            break;
                        case -503070502:
                            if (str.equals("TLSv1.2")) {
                                return TlsVersion.TLS_1_2;
                            }
                            break;
                        case -503070501:
                            if (str.equals("TLSv1.3")) {
                                return TlsVersion.TLS_1_3;
                            }
                            break;
                    }
                } else if (str.equals("TLSv1")) {
                    return TlsVersion.TLS_1_0;
                }
            } else if (str.equals("SSLv3")) {
                return TlsVersion.SSL_3_0;
            }
            throw new IllegalArgumentException("Unexpected TLS version: " + str);
        }
    }

    TlsVersion(String str) {
        this.javaName = str;
    }

    /* renamed from: b */
    public final String m21215b() {
        return this.javaName;
    }
}
