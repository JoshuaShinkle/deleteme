package p026c5;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.RealmCallback;
import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.b */
/* loaded from: classes2.dex */
public class C0739b implements SaslClient {

    /* renamed from: a */
    public String f3453a;

    /* renamed from: b */
    public String f3454b;

    /* renamed from: c */
    public String f3455c;

    /* renamed from: d */
    public Map f3456d;

    /* renamed from: e */
    public CallbackHandler f3457e;

    /* renamed from: i */
    public String f3461i;

    /* renamed from: j */
    public C0738a f3462j;

    /* renamed from: g */
    public String f3459g = "";

    /* renamed from: h */
    public char[] f3460h = null;

    /* renamed from: k */
    public String f3463k = "";

    /* renamed from: l */
    public String f3464l = "";

    /* renamed from: m */
    public String f3465m = "";

    /* renamed from: f */
    public int f3458f = 0;

    public C0739b(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        this.f3453a = str;
        this.f3454b = str2;
        this.f3455c = str3;
        this.f3456d = map;
        this.f3457e = callbackHandler;
    }

    /* renamed from: f */
    public static SaslClient m3589f(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        String str4 = (String) map.get("javax.security.sasl.qop");
        String str5 = (String) map.get("javax.security.sasl.server.authentication");
        if (str4 != null && !"auth".equals(str4)) {
            return null;
        }
        if ((str5 == null || "false".equals(str5)) && callbackHandler != null) {
            return new C0739b(str, str2, str3, map, callbackHandler);
        }
        return null;
    }

    /* renamed from: h */
    public static char m3590h(byte b9) {
        switch (b9) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            default:
                return 'Z';
        }
    }

    /* renamed from: a */
    public char[] m3591a(String str, String str2, String str3, String str4, String str5, String str6) throws SaslException, NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str2.getBytes("UTF-8"));
            messageDigest.update(":".getBytes("UTF-8"));
            messageDigest.update(str3.getBytes("UTF-8"));
            messageDigest.update(":".getBytes("UTF-8"));
            messageDigest.update(str4.getBytes("UTF-8"));
            byte[] bArrDigest = messageDigest.digest();
            if ("md5-sess".equals(str)) {
                messageDigest.update(bArrDigest);
                messageDigest.update(":".getBytes("UTF-8"));
                messageDigest.update(str5.getBytes("UTF-8"));
                messageDigest.update(":".getBytes("UTF-8"));
                messageDigest.update(str6.getBytes("UTF-8"));
                bArrDigest = messageDigest.digest();
            }
            return m3594d(bArrDigest);
        } catch (UnsupportedEncodingException e9) {
            throw new SaslException("UTF-8 encoding not supported by platform.", e9);
        } catch (NoSuchAlgorithmException e10) {
            throw new SaslException("No provider found for MD5 hash", e10);
        }
    }

    /* renamed from: b */
    public char[] m3592b(char[] cArr, String str, String str2, String str3, String str4, String str5, String str6, boolean z8) throws SaslException, NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (z8) {
                messageDigest.update(str5.getBytes("UTF-8"));
            }
            messageDigest.update(":".getBytes("UTF-8"));
            messageDigest.update(str6.getBytes("UTF-8"));
            if ("auth-int".equals(str4)) {
                messageDigest.update(":".getBytes("UTF-8"));
                messageDigest.update("00000000000000000000000000000000".getBytes("UTF-8"));
            }
            char[] cArrM3594d = m3594d(messageDigest.digest());
            messageDigest.update(new String(cArr).getBytes("UTF-8"));
            messageDigest.update(":".getBytes("UTF-8"));
            messageDigest.update(str.getBytes("UTF-8"));
            messageDigest.update(":".getBytes("UTF-8"));
            if (str4.length() > 0) {
                messageDigest.update(str2.getBytes("UTF-8"));
                messageDigest.update(":".getBytes("UTF-8"));
                messageDigest.update(str3.getBytes("UTF-8"));
                messageDigest.update(":".getBytes("UTF-8"));
                messageDigest.update(str4.getBytes("UTF-8"));
                messageDigest.update(":".getBytes("UTF-8"));
            }
            messageDigest.update(new String(cArrM3594d).getBytes("UTF-8"));
            return m3594d(messageDigest.digest());
        } catch (UnsupportedEncodingException e9) {
            throw new SaslException("UTF-8 encoding not supported by platform.", e9);
        } catch (NoSuchAlgorithmException e10) {
            throw new SaslException("No provider found for MD5 hash", e10);
        }
    }

    /* renamed from: c */
    public boolean m3593c(byte[] bArr) {
        return new String(m3592b(this.f3460h, this.f3462j.m3578c(), "00000001", this.f3463k, this.f3459g, "AUTHENTICATE", this.f3461i, false)).equals(new C0743f(bArr).m3606b());
    }

    /* renamed from: d */
    public char[] m3594d(byte[] bArr) {
        char[] cArr = new char[32];
        for (int i9 = 0; i9 < 16; i9++) {
            int i10 = i9 * 2;
            cArr[i10] = m3590h((byte) ((bArr[i9] & 240) >> 4));
            cArr[i10 + 1] = m3590h((byte) (bArr[i9] & Ascii.f15389SI));
        }
        return cArr;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public void dispose() {
        if (this.f3458f != 4) {
            this.f3458f = 4;
        }
    }

    /* renamed from: e */
    public final String m3595e(byte[] bArr) throws SaslException, NoSuchAlgorithmException {
        StringBuffer stringBuffer = new StringBuffer(512);
        this.f3462j = new C0738a(bArr);
        this.f3461i = this.f3454b + "/" + this.f3455c;
        if ((this.f3462j.m3579d() & 1) != 1) {
            throw new SaslException("Client only supports qop of 'auth'");
        }
        this.f3459g = "auth";
        Callback[] callbackArr = new Callback[3];
        ArrayList arrayListM3580e = this.f3462j.m3580e();
        int size = arrayListM3580e.size();
        if (size == 0) {
            callbackArr[0] = new RealmCallback("Realm");
        } else if (size == 1) {
            callbackArr[0] = new RealmCallback("Realm", (String) arrayListM3580e.get(0));
        } else {
            callbackArr[0] = new RealmChoiceCallback("Realm", (String[]) arrayListM3580e.toArray(new String[size]), 0, false);
        }
        callbackArr[1] = new PasswordCallback("Password", false);
        String str = this.f3453a;
        if (str == null || str.length() == 0) {
            callbackArr[2] = new NameCallback("Name");
        } else {
            callbackArr[2] = new NameCallback("Name", this.f3453a);
        }
        try {
            this.f3457e.handle(callbackArr);
            if (size > 1) {
                int[] selectedIndexes = ((RealmChoiceCallback) callbackArr[0]).getSelectedIndexes();
                if (selectedIndexes.length > 0) {
                    this.f3464l = ((RealmChoiceCallback) callbackArr[0]).getChoices()[selectedIndexes[0]];
                } else {
                    this.f3464l = ((RealmChoiceCallback) callbackArr[0]).getChoices()[0];
                }
            } else {
                this.f3464l = ((RealmCallback) callbackArr[0]).getText();
            }
            if (this.f3464l == null) {
                this.f3464l = "";
            }
            this.f3463k = m3596g();
            String name = ((NameCallback) callbackArr[2]).getName();
            this.f3465m = name;
            if (name == null) {
                this.f3465m = ((NameCallback) callbackArr[2]).getDefaultName();
            }
            if (this.f3465m == null) {
                throw new SaslException("No user name was specified.");
            }
            char[] cArrM3591a = m3591a(this.f3462j.m3577b(), this.f3465m, this.f3464l, new String(((PasswordCallback) callbackArr[1]).getPassword()), this.f3462j.m3578c(), this.f3463k);
            this.f3460h = cArrM3591a;
            char[] cArrM3592b = m3592b(cArrM3591a, this.f3462j.m3578c(), "00000001", this.f3463k, this.f3459g, "AUTHENTICATE", this.f3461i, true);
            stringBuffer.append("username=\"");
            stringBuffer.append(this.f3465m);
            if (this.f3464l.length() != 0) {
                stringBuffer.append("\",realm=\"");
                stringBuffer.append(this.f3464l);
            }
            stringBuffer.append("\",cnonce=\"");
            stringBuffer.append(this.f3463k);
            stringBuffer.append("\",nc=");
            stringBuffer.append("00000001");
            stringBuffer.append(",qop=");
            stringBuffer.append(this.f3459g);
            stringBuffer.append(",digest-uri=\"");
            stringBuffer.append(this.f3461i);
            stringBuffer.append("\",response=");
            stringBuffer.append(cArrM3592b);
            stringBuffer.append(",charset=utf-8,nonce=\"");
            stringBuffer.append(this.f3462j.m3578c());
            String str2 = this.f3453a;
            if (str2 != null && str2.length() > 0) {
                stringBuffer.append("\",authzid=\"");
                stringBuffer.append(this.f3453a);
            }
            stringBuffer.append("\"");
            return stringBuffer.toString();
        } catch (IOException e9) {
            throw new SaslException("IO exception in CallbackHandler.", e9);
        } catch (UnsupportedCallbackException e10) {
            throw new SaslException("Handler does not support necessary callbacks", e10);
        }
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] evaluateChallenge(byte[] bArr) throws SaslException, UnsupportedEncodingException {
        int i9 = this.f3458f;
        if (i9 == 0) {
            if (bArr.length == 0) {
                throw new SaslException("response = byte[0]");
            }
            try {
                byte[] bytes = m3595e(bArr).getBytes("UTF-8");
                this.f3458f = 1;
                return bytes;
            } catch (UnsupportedEncodingException e9) {
                throw new SaslException("UTF-8 encoding not suppported by platform", e9);
            }
        }
        if (i9 == 1) {
            if (m3593c(bArr)) {
                this.f3458f = 2;
                return null;
            }
            this.f3458f = 3;
            throw new SaslException("Could not validate response-auth value from server");
        }
        if (i9 == 2 || i9 == 3) {
            throw new SaslException("Authentication sequence is complete");
        }
        if (i9 != 4) {
            throw new SaslException("Unknown client state.");
        }
        throw new SaslException("Client has been disposed");
    }

    /* renamed from: g */
    public String m3596g() throws SaslException {
        byte[] bArr = new byte[32];
        char[] cArr = new char[64];
        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            for (int i9 = 0; i9 < 32; i9++) {
                int i10 = i9 * 2;
                cArr[i10] = m3590h((byte) (bArr[i9] & Ascii.f15389SI));
                cArr[i10 + 1] = m3590h((byte) ((bArr[i9] & 240) >> 4));
            }
            return new String(cArr);
        } catch (NoSuchAlgorithmException e9) {
            throw new SaslException("No random number generator available", e9);
        }
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public String getMechanismName() {
        return "DIGEST-MD5";
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public Object getNegotiatedProperty(String str) {
        if (this.f3458f != 2) {
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
        int i9 = this.f3458f;
        return i9 == 2 || i9 == 3 || i9 == 4;
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
