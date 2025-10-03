package org.apache.qpid.management.common.sasl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

/* loaded from: classes.dex */
public class PlainSaslClient implements SaslClient {
    private static byte SEPARATOR;
    private String authenticationID;
    private String authorizationID;
    private CallbackHandler cbh;
    private boolean completed = false;
    private byte[] password;

    public PlainSaslClient(String str, CallbackHandler callbackHandler) throws SaslException, UnsupportedEncodingException {
        this.cbh = callbackHandler;
        Object[] userInfo = getUserInfo();
        this.authorizationID = str;
        String str2 = (String) userInfo[0];
        this.authenticationID = str2;
        byte[] bArr = (byte[]) userInfo[1];
        this.password = bArr;
        if (str2 == null || bArr == null) {
            throw new SaslException("PLAIN: authenticationID and password must be specified");
        }
    }

    private void clearPassword() {
        if (this.password == null) {
            return;
        }
        int i9 = 0;
        while (true) {
            byte[] bArr = this.password;
            if (i9 >= bArr.length) {
                this.password = null;
                return;
            } else {
                bArr[i9] = 0;
                i9++;
            }
        }
    }

    private Object[] getUserInfo() throws SaslException, UnsupportedEncodingException {
        byte[] bytes;
        try {
            NameCallback nameCallback = new NameCallback("PLAIN authentication id: ");
            PasswordCallback passwordCallback = new PasswordCallback("PLAIN password: ", false);
            this.cbh.handle(new Callback[]{nameCallback, passwordCallback});
            String name = nameCallback.getName();
            char[] password = passwordCallback.getPassword();
            if (password != null) {
                bytes = new String(password).getBytes("UTF8");
                passwordCallback.clearPassword();
            } else {
                bytes = null;
            }
            return new Object[]{name, bytes};
        } catch (IOException e9) {
            throw new SaslException("Cannot get password", e9);
        } catch (UnsupportedCallbackException e10) {
            throw new SaslException("Cannot get userid/password", e10);
        }
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public void dispose() {
        clearPassword();
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] evaluateChallenge(byte[] bArr) throws SaslException, UnsupportedEncodingException {
        int length;
        if (this.completed) {
            throw new IllegalStateException("PLAIN: authentication already completed");
        }
        this.completed = true;
        try {
            String str = this.authorizationID;
            byte[] bytes = str == null ? null : str.getBytes("UTF8");
            byte[] bytes2 = this.authenticationID.getBytes("UTF8");
            byte[] bArr2 = new byte[this.password.length + bytes2.length + 2 + (bytes != null ? bytes.length : 0)];
            if (bytes != null) {
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                length = bytes.length;
            } else {
                length = 0;
            }
            int i9 = length + 1;
            bArr2[length] = SEPARATOR;
            System.arraycopy(bytes2, 0, bArr2, i9, bytes2.length);
            int length2 = i9 + bytes2.length;
            bArr2[length2] = SEPARATOR;
            byte[] bArr3 = this.password;
            System.arraycopy(bArr3, 0, bArr2, length2 + 1, bArr3.length);
            clearPassword();
            return bArr2;
        } catch (UnsupportedEncodingException e9) {
            throw new SaslException("PLAIN: Cannot get UTF-8 encoding of ids", e9);
        }
    }

    public void finalize() throws Throwable {
        clearPassword();
        super.finalize();
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public String getMechanismName() {
        return Constants.MECH_PLAIN;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public Object getNegotiatedProperty(String str) {
        if (!this.completed) {
            throw new IllegalStateException("PLAIN: authentication not completed");
        }
        if (str.equals("javax.security.sasl.qop")) {
            return "auth";
        }
        return null;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public boolean hasInitialResponse() {
        return true;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public boolean isComplete() {
        return this.completed;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] unwrap(byte[] bArr, int i9, int i10) {
        if (this.completed) {
            throw new IllegalStateException("PLAIN: this mechanism supports neither integrity nor privacy");
        }
        throw new IllegalStateException("PLAIN: authentication not completed");
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClient
    public byte[] wrap(byte[] bArr, int i9, int i10) {
        if (this.completed) {
            throw new IllegalStateException("PLAIN: this mechanism supports neither integrity nor privacy");
        }
        throw new IllegalStateException("PLAIN: authentication not completed");
    }
}
