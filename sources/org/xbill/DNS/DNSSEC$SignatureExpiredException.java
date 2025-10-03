package org.xbill.DNS;

import java.util.Date;

/* loaded from: classes3.dex */
public class DNSSEC$SignatureExpiredException extends DNSSEC$DNSSECException {
    private Date now;
    private Date when;
}
