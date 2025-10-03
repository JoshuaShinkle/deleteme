package org.jivesoftware.smack.sasl;

import org.apache.qpid.management.common.sasl.Constants;
import org.jivesoftware.smack.C5585d;

/* loaded from: classes.dex */
public class SASLCramMD5Mechanism extends SASLMechanism {
    public SASLCramMD5Mechanism(C5585d c5585d) {
        super(c5585d);
    }

    @Override // org.jivesoftware.smack.sasl.SASLMechanism
    /* renamed from: e */
    public String mo22185e() {
        return Constants.MECH_CRAMMD5;
    }
}
