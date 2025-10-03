package org.apache.harmony.javax.security.sasl;

/* loaded from: classes.dex */
public interface SaslServer {
    void dispose();

    byte[] evaluateResponse(byte[] bArr);

    String getAuthorizationID();

    String getMechanismName();

    Object getNegotiatedProperty(String str);

    boolean isComplete();

    byte[] unwrap(byte[] bArr, int i9, int i10);

    byte[] wrap(byte[] bArr, int i9, int i10);
}
