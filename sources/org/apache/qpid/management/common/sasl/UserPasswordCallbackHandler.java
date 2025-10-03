package org.apache.qpid.management.common.sasl;

import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;

/* loaded from: classes.dex */
public class UserPasswordCallbackHandler implements CallbackHandler {
    private char[] pwchars;
    private String user;

    public UserPasswordCallbackHandler(String str, String str2) {
        this.user = str;
        this.pwchars = str2.toCharArray();
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
