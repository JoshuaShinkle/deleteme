package p139m6;

import java.net.ProtocolException;
import kotlin.text.C5255l;
import okhttp3.Protocol;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: m6.k */
/* loaded from: classes.dex */
public final class C5338k {

    /* renamed from: d */
    public static final a f18184d = new a(null);

    /* renamed from: a */
    public final Protocol f18185a;

    /* renamed from: b */
    public final int f18186b;

    /* renamed from: c */
    public final String f18187c;

    /* renamed from: m6.k$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C5338k m20980a(String str) throws ProtocolException, NumberFormatException {
            Protocol protocol;
            int i9;
            String strSubstring;
            C0042f.m158e(str, "statusLine");
            if (C5255l.m20525x(str, "HTTP/1.", false, 2, null)) {
                i9 = 9;
                if (str.length() < 9 || str.charAt(8) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                int iCharAt = str.charAt(7) - '0';
                if (iCharAt == 0) {
                    protocol = Protocol.HTTP_1_0;
                } else {
                    if (iCharAt != 1) {
                        throw new ProtocolException("Unexpected status line: " + str);
                    }
                    protocol = Protocol.HTTP_1_1;
                }
            } else {
                if (!C5255l.m20525x(str, "ICY ", false, 2, null)) {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                protocol = Protocol.HTTP_1_0;
                i9 = 4;
            }
            int i10 = i9 + 3;
            if (str.length() < i10) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            try {
                String strSubstring2 = str.substring(i9, i10);
                C0042f.m157d(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                int i11 = Integer.parseInt(strSubstring2);
                if (str.length() <= i10) {
                    strSubstring = "";
                } else {
                    if (str.charAt(i10) != ' ') {
                        throw new ProtocolException("Unexpected status line: " + str);
                    }
                    strSubstring = str.substring(i9 + 4);
                    C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
                }
                return new C5338k(protocol, i11, strSubstring);
            } catch (NumberFormatException unused) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        }
    }

    public C5338k(Protocol protocol, int i9, String str) {
        C0042f.m158e(protocol, "protocol");
        C0042f.m158e(str, "message");
        this.f18185a = protocol;
        this.f18186b = i9;
        this.f18187c = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f18185a == Protocol.HTTP_1_0) {
            sb.append("HTTP/1.0");
        } else {
            sb.append("HTTP/1.1");
        }
        sb.append(' ');
        sb.append(this.f18186b);
        sb.append(' ');
        sb.append(this.f18187c);
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
