package org.jivesoftware.smack.proxy;

import java.io.IOException;
import org.jivesoftware.smack.proxy.ProxyInfo;

/* loaded from: classes.dex */
public class ProxyException extends IOException {
    private static final long serialVersionUID = 1;

    public ProxyException(ProxyInfo.ProxyType proxyType, String str, Throwable th) {
        super("Proxy Exception " + proxyType.toString() + " : " + str + ", " + th);
    }

    public ProxyException(ProxyInfo.ProxyType proxyType, String str) {
        super("Proxy Exception " + proxyType.toString() + " : " + str);
    }

    public ProxyException(ProxyInfo.ProxyType proxyType) {
        super("Proxy Exception " + proxyType.toString() + " : Unknown Error");
    }
}
