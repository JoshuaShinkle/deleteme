package de.measite.smack;

import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.qpid.management.common.sasl.Constants;
import org.apache.qpid.management.common.sasl.PlainSaslClient;
import p026c5.C0739b;
import p026c5.C0741d;

/* loaded from: classes2.dex */
public class SaslClientFactory implements org.apache.harmony.javax.security.sasl.SaslClientFactory {
    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) {
        for (String str4 : strArr) {
            if (Constants.MECH_PLAIN.equals(str4)) {
                return new PlainSaslClient(str, callbackHandler);
            }
            if ("DIGEST-MD5".equals(str4)) {
                return C0739b.m3589f(str, str2, str3, map, callbackHandler);
            }
            if ("EXTERNAL".equals(str4)) {
                return C0741d.m3602a(str, str2, str3, map, callbackHandler);
            }
        }
        return null;
    }

    @Override // org.apache.harmony.javax.security.sasl.SaslClientFactory
    public String[] getMechanismNames(Map<String, ?> map) {
        return new String[]{Constants.MECH_PLAIN, "DIGEST-MD5", "EXTERNAL"};
    }
}
