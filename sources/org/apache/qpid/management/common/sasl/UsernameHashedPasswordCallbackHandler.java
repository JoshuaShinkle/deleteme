package org.apache.qpid.management.common.sasl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;

/* loaded from: classes.dex */
public class UsernameHashedPasswordCallbackHandler implements CallbackHandler {
    private char[] pwchars;
    private String user;

    public UsernameHashedPasswordCallbackHandler(String str, String str2) {
        this.user = str;
        this.pwchars = getHash(str2);
    }

    private void clearPassword() {
        if (this.pwchars == null) {
            return;
        }
        int i9 = 0;
        while (true) {
            char[] cArr = this.pwchars;
            if (i9 >= cArr.length) {
                this.pwchars = null;
                return;
            } else {
                cArr[i9] = 0;
                i9++;
            }
        }
    }

    public static char[] getHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes = str.getBytes("utf-8");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int i9 = 0;
        for (byte b9 : bytes) {
            messageDigest.update(b9);
        }
        byte[] bArrDigest = messageDigest.digest();
        char[] cArr = new char[bArrDigest.length];
        int length = bArrDigest.length;
        int i10 = 0;
        while (i9 < length) {
            cArr[i10] = (char) bArrDigest[i9];
            i9++;
            i10++;
        }
        return cArr;
    }

    public void finalize() throws Throwable {
        clearPassword();
        super.finalize();
    }

    @Override // org.apache.harmony.javax.security.auth.callback.CallbackHandler
    public void handle(Callback[] callbackArr) throws UnsupportedCallbackException {
        for (int i9 = 0; i9 < callbackArr.length; i9++) {
            Callback callback = callbackArr[i9];
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(this.user);
            } else {
                if (!(callback instanceof PasswordCallback)) {
                    throw new UnsupportedCallbackException(callbackArr[i9]);
                }
                ((PasswordCallback) callback).setPassword(this.pwchars);
            }
        }
    }
}
