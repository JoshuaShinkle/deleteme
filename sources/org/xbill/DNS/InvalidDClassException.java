package org.xbill.DNS;

/* loaded from: classes3.dex */
public class InvalidDClassException extends IllegalArgumentException {
    public InvalidDClassException(int i9) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Invalid DNS class: ");
        stringBuffer.append(i9);
        super(stringBuffer.toString());
    }
}
