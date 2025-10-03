package org.apache.qpid.management.common.sasl;

import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslClientFactory;

/* loaded from: classes.dex */
public class ClientSaslFactory implements SaslClientFactory {
    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        for (String str4 : strArr) {
            if (str4.equals(Constants.MECH_PLAIN)) {
                return new PlainSaslClient(str, callbackHandler);
            }
        }
        return null;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public String[] getMechanismNames(Map map) {
        return new String[]{Constants.MECH_PLAIN};
    }
}
