package org.xbill.DNS;

import com.google.common.primitives.UnsignedBytes;

/* renamed from: org.xbill.DNS.a */
/* loaded from: classes3.dex */
public final class C5855a {
    /* renamed from: a */
    public static String m23229a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bArr[0] & UnsignedBytes.MAX_VALUE);
        stringBuffer.append(".");
        stringBuffer.append(bArr[1] & UnsignedBytes.MAX_VALUE);
        stringBuffer.append(".");
        stringBuffer.append(bArr[2] & UnsignedBytes.MAX_VALUE);
        stringBuffer.append(".");
        stringBuffer.append(bArr[3] & UnsignedBytes.MAX_VALUE);
        return stringBuffer.toString();
    }
}
