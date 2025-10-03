package org.apache.harmony.javax.security.auth.login;

import java.security.GeneralSecurityException;

/* loaded from: classes.dex */
public class LoginException extends GeneralSecurityException {
    private static final long serialVersionUID = -4679091624035232488L;

    public LoginException() {
    }

    public LoginException(String str) {
        super(str);
    }
}
