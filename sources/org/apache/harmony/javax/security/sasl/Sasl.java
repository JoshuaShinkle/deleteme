package org.apache.harmony.javax.security.sasl;

import java.security.Provider;
import java.security.Security;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;

/* loaded from: classes.dex */
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

    private Sasl() {
    }

    public static SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) {
        SaslClient saslClientCreateSaslClient;
        if (strArr == null) {
            throw new NullPointerException("auth.33");
        }
        Collection<?> collectionFindFactories = findFactories(CLIENTFACTORYSRV);
        if (collectionFindFactories.isEmpty()) {
            return null;
        }
        Iterator<?> it = collectionFindFactories.iterator();
        while (it.hasNext()) {
            SaslClientFactory saslClientFactory = (SaslClientFactory) it.next();
            String[] mechanismNames = saslClientFactory.getMechanismNames(null);
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
            if (z8 && (saslClientCreateSaslClient = saslClientFactory.createSaslClient(strArr, str, str2, str3, map, callbackHandler)) != null) {
                return saslClientCreateSaslClient;
            }
        }
        return null;
    }

    public static SaslServer createSaslServer(String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) {
        SaslServer saslServerCreateSaslServer;
        if (str == null) {
            throw new NullPointerException("auth.32");
        }
        Collection<?> collectionFindFactories = findFactories(SERVERFACTORYSRV);
        if (collectionFindFactories.isEmpty()) {
            return null;
        }
        Iterator<?> it = collectionFindFactories.iterator();
        while (it.hasNext()) {
            SaslServerFactory saslServerFactory = (SaslServerFactory) it.next();
            String[] mechanismNames = saslServerFactory.getMechanismNames(null);
            boolean z8 = false;
            if (mechanismNames != null) {
                int i9 = 0;
                while (true) {
                    if (i9 >= mechanismNames.length) {
                        break;
                    }
                    if (mechanismNames[i9].equals(str)) {
                        z8 = true;
                        break;
                    }
                    i9++;
                }
            }
            if (z8 && (saslServerCreateSaslServer = saslServerFactory.createSaslServer(str, str2, str3, map, callbackHandler)) != null) {
                return saslServerCreateSaslServer;
            }
        }
        return null;
    }

    private static Collection<?> findFactories(String str) {
        HashSet hashSet = new HashSet();
        Provider[] providers = Security.getProviders();
        if (providers != null && providers.length != 0) {
            HashSet hashSet2 = new HashSet();
            for (int i9 = 0; i9 < providers.length; i9++) {
                String name = providers[i9].getName();
                Enumeration<Object> enumerationKeys = providers[i9].keys();
                while (enumerationKeys.hasMoreElements()) {
                    String str2 = (String) enumerationKeys.nextElement();
                    if (str2.startsWith(str)) {
                        String property = providers[i9].getProperty(str2);
                        try {
                            if (hashSet2.add(name.concat(property))) {
                                hashSet.add(newInstance(property, providers[i9]));
                            }
                        } catch (SaslException e9) {
                            e9.printStackTrace();
                        }
                    }
                }
            }
        }
        return hashSet;
    }

    public static Enumeration<SaslClientFactory> getSaslClientFactories() {
        return Collections.enumeration(findFactories(CLIENTFACTORYSRV));
    }

    public static Enumeration<SaslServerFactory> getSaslServerFactories() {
        return Collections.enumeration(findFactories(SERVERFACTORYSRV));
    }

    private static Object newInstance(String str, Provider provider) throws SaslException {
        ClassLoader classLoader = provider.getClass().getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            return Class.forName(str, true, classLoader).newInstance();
        } catch (ClassNotFoundException e9) {
            throw new SaslException("auth.31" + str, e9);
        } catch (IllegalAccessException e10) {
            throw new SaslException("auth.31" + str, e10);
        } catch (InstantiationException e11) {
            throw new SaslException("auth.31" + str, e11);
        }
    }
}
