package p026c5;

import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.d */
/* loaded from: classes2.dex */
public class C0741d implements SaslClient {

    /* renamed from: a */
    public String f3472a;

    /* renamed from: b */
    public String f3473b;

    /* renamed from: c */
    public String f3474c;

    /* renamed from: d */
    public Map f3475d;

    /* renamed from: e */
    public CallbackHandler f3476e;

    /* renamed from: f */
    public int f3477f = 0;

    public C0741d(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        this.f3472a = str;
        this.f3473b = str2;
        this.f3474c = str3;
        this.f3475d = map;
        this.f3476e = callbackHandler;
    }

    /* renamed from: a */
    public static SaslClient m3602a(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        return new C0741d(str, str2, str3, map, callbackHandler);
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public void dispose() {
        if (this.f3477f != 3) {
            this.f3477f = 3;
        }
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] evaluateChallenge(byte[] bArr) throws SaslException {
        int i9 = this.f3477f;
        if (i9 != 0) {
            if (i9 == 2) {
                throw new SaslException("Authentication sequence is complete");
            }
            if (i9 != 3) {
                throw new SaslException("Unknown client state.");
            }
            throw new SaslException("Client has been disposed");
        }
        if (bArr.length == 0) {
            this.f3477f = 1;
            return null;
        }
        this.f3477f = 2;
        throw new SaslException("Unexpected non-zero length response.");
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public String getMechanismName() {
        return "EXTERNAL";
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public Object getNegotiatedProperty(String str) {
        if (this.f3477f != 1) {
            throw new IllegalStateException("getNegotiatedProperty: authentication exchange not complete.");
        }
        if ("javax.security.sasl.qop".equals(str)) {
            return "auth";
        }
        return null;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public boolean hasInitialResponse() {
        return false;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public boolean isComplete() {
        int i9 = this.f3477f;
        return i9 == 1 || i9 == 2 || i9 == 3;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] unwrap(byte[] bArr, int i9, int i10) {
        throw new IllegalStateException("unwrap: QOP has neither integrity nor privacy>");
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] wrap(byte[] bArr, int i9, int i10) {
        throw new IllegalStateException("wrap: QOP has neither integrity nor privacy>");
    }
}
