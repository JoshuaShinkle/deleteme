package de.measite.smack;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslServer;
import org.apache.harmony.javax.security.sasl.SaslServerFactory;

/* loaded from: classes2.dex */
public class Sasl {
    private static final String CLIENTFACTORYSRV = "SaslClientFactory";
    public static final String MAX_BUFFER = "javax.security.sasl.maxbuffer";
    public static final String POLICY_FORWARD_SECRECY = "javax.security.sasl.policy.forward";
    public static final String POLICY_NOACTIVE = "javax.security.sasl.policy.noactive";
    public static final String POLICY_NOANONYMOUS = "javax.security.sasl.policy.noanonymous";
    public static final String POLICY_NODICTIONARY = "javax.security.sasl.policy.nodictionary";
    public static final String POLICY_NOPLAINTEXT = "javax.security.sasl.policy.noplaintext";
    public static final String POLICY_PASS_CREDENTIALS = "javax.security.sasl.policy.credentials";
    public static final String QOP = "javax.security.sasl.qop";
    public static final String RAW_SEND_SIZE = "javax.security.sasl.rawsendsize";
    public static final String REUSE = "javax.security.sasl.reuse";
    private static final String SERVERFACTORYSRV = "SaslServerFactory";
    public static final String SERVER_AUTH = "javax.security.sasl.server.authentication";
    public static final String STRENGTH = "javax.security.sasl.strength";

    public static SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) {
        if (strArr == null) {
            throw new NullPointerException("auth.33");
        }
        SaslClientFactory saslClientFactoryNextElement = getSaslClientFactories().nextElement();
        String[] mechanismNames = saslClientFactoryNextElement.getMechanismNames(null);
        boolean z8 = false;
        if (mechanismNames != null) {
            boolean z9 = false;
            for (String str4 : mechanismNames) {
                int i9 = 0;
                while (true) {
                    if (i9 >= strArr.length) {
                        break;
                    }
                    if (str4.equals(strArr[i9])) {
                        z9 = true;
                        break;
                    }
                    i9++;
                }
            }
            z8 = z9;
        }
        if (z8) {
            return saslClientFactoryNextElement.createSaslClient(strArr, str, str2, str3, map, callbackHandler);
        }
        return null;
    }

    public static SaslServer createSaslServer(String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) {
        return org.apache.harmony.javax.security.sasl.Sasl.createSaslServer(str, str2, str3, map, callbackHandler);
    }

    public static Enumeration<SaslClientFactory> getSaslClientFactories() {
        Hashtable hashtable = new Hashtable();
        hashtable.put(new SaslClientFactory(), new Object());
        return hashtable.keys();
    }

    public static Enumeration<SaslServerFactory> getSaslServerFactories() {
        return org.apache.harmony.javax.security.sasl.Sasl.getSaslServerFactories();
    }
}
