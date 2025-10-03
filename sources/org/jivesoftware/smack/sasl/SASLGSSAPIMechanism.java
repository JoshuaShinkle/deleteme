package org.jivesoftware.smack.sasl;

import org.jivesoftware.smack.C5585d;

/* loaded from: classes.dex */
public class SASLGSSAPIMechanism extends SASLMechanism {
    public SASLGSSAPIMechanism(C5585d c5585d) {
        super(c5585d);
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty("java.security.auth.login.config", "gss.conf");
    }

    @Override // org.jivesoftware.smack.sasl.SASLMechanism
    /* renamed from: e */
    public String mo22185e() {
        return "GSSAPI";
    }
}
