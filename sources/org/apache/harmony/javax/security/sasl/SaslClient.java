package org.apache.harmony.javax.security.sasl;

/* loaded from: classes.dex */
public interface SaslClient {
    void dispose();

    byte[] evaluateChallenge(byte[] bArr);

    String getMechanismName();

    Object getNegotiatedProperty(String str);

    boolean hasInitialResponse();

    boolean isComplete();

    byte[] unwrap(byte[] bArr, int i9, int i10);

    byte[] wrap(byte[] bArr, int i9, int i10);
}
