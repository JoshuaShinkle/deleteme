package okhttp3;

import java.io.IOException;
import p007a6.C0040d;
import p007a6.C0042f;

/* loaded from: classes2.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");


    /* renamed from: b */
    public static final C5480a f18470b = new C5480a(null);
    private final String protocol;

    /* renamed from: okhttp3.Protocol$a */
    public static final class C5480a {
        public C5480a() {
        }

        public /* synthetic */ C5480a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final Protocol m21213a(String str) throws IOException {
            C0042f.m158e(str, "protocol");
            Protocol protocol = Protocol.HTTP_1_0;
            if (!C0042f.m154a(str, protocol.protocol)) {
                protocol = Protocol.HTTP_1_1;
                if (!C0042f.m154a(str, protocol.protocol)) {
                    protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                    if (!C0042f.m154a(str, protocol.protocol)) {
                        protocol = Protocol.HTTP_2;
                        if (!C0042f.m154a(str, protocol.protocol)) {
                            protocol = Protocol.SPDY_3;
                            if (!C0042f.m154a(str, protocol.protocol)) {
                                protocol = Protocol.QUIC;
                                if (!C0042f.m154a(str, protocol.protocol)) {
                                    throw new IOException("Unexpected protocol: " + str);
                                }
                            }
                        }
                    }
                }
            }
            return protocol;
        }
    }

    Protocol(String str) {
        this.protocol = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
