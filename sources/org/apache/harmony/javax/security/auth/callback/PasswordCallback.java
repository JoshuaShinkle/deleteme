package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;
import java.util.Arrays;

/* loaded from: classes.dex */
public class PasswordCallback implements Callback, Serializable {
    private static final long serialVersionUID = 2267422647454909926L;
    boolean echoOn;
    private char[] inputPassword;
    private String prompt;

    public PasswordCallback(String str, boolean z8) {
        setPrompt(str);
        this.echoOn = z8;
    }

    private void setPrompt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        }
        this.prompt = str;
    }

    public void clearPassword() {
        char[] cArr = this.inputPassword;
        if (cArr != null) {
            Arrays.fill(cArr, (char) 0);
        }
    }

    public char[] getPassword() {
        char[] cArr = this.inputPassword;
        if (cArr == null) {
            return null;
        }
        int length = cArr.length;
        char[] cArr2 = new char[length];
        System.arraycopy(cArr, 0, cArr2, 0, length);
        return cArr2;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public boolean isEchoOn() {
        return this.echoOn;
    }

    public void setPassword(char[] cArr) {
        if (cArr == null) {
            this.inputPassword = cArr;
            return;
        }
        char[] cArr2 = new char[cArr.length];
        this.inputPassword = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr2.length);
    }
}
