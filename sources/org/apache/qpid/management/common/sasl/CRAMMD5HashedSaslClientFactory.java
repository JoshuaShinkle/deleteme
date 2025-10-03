package org.apache.qpid.management.common.sasl;

import de.measite.smack.Sasl;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslClientFactory;
import org.apache.harmony.javax.security.sasl.SaslException;

/* loaded from: classes.dex */
public class CRAMMD5HashedSaslClientFactory implements SaslClientFactory {
    public static final String MECHANISM = "CRAM-MD5-HASHED";

    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) throws SaslException {
        for (String str4 : strArr) {
            if (str4.equals(MECHANISM)) {
                if (callbackHandler != null) {
                    return Sasl.createSaslClient(new String[]{Constants.MECH_CRAMMD5}, str, str2, str3, map, callbackHandler);
                }
                throw new SaslException("CallbackHandler must not be null");
            }
        }
        return null;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public String[] getMechanismNames(Map map) {
        return new String[]{MECHANISM};
    }
}
