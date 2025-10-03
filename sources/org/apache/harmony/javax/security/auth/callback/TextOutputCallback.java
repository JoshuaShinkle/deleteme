package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TextOutputCallback implements Callback, Serializable {
    public static final int ERROR = 2;
    public static final int INFORMATION = 0;
    public static final int WARNING = 1;
    private static final long serialVersionUID = 1689502495511663102L;
    private String message;
    private int messageType;

    public TextOutputCallback(int i9, String str) {
        if (i9 > 2 || i9 < 0) {
            throw new IllegalArgumentException("auth.16");
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.1F");
        }
        this.messageType = i9;
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public int getMessageType() {
        return this.messageType;
    }
}
