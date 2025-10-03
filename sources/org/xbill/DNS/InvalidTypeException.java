package org.xbill.DNS;

/* loaded from: classes3.dex */
public class InvalidTypeException extends IllegalArgumentException {
    public InvalidTypeException(int i9) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Invalid DNS type: ");
        stringBuffer.append(i9);
        super(stringBuffer.toString());
    }
}
