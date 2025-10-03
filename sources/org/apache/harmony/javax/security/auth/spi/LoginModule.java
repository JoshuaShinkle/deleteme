package org.apache.harmony.javax.security.auth.spi;

import java.util.Map;
import org.apache.harmony.javax.security.auth.Subject;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;

/* loaded from: classes.dex */
public interface LoginModule {
    boolean abort();

    boolean commit();

    void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> map, Map<String, ?> map2);

    boolean login();

    boolean logout();
}
