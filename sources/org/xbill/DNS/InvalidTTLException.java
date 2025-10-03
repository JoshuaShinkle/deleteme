package org.xbill.DNS;

/* loaded from: classes3.dex */
public class InvalidTTLException extends IllegalArgumentException {
    public InvalidTTLException(long j9) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Invalid DNS TTL: ");
        stringBuffer.append(j9);
        super(stringBuffer.toString());
    }
}
