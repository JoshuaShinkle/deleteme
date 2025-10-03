package org.jivesoftware.smack.sasl;

import org.jivesoftware.smack.C5585d;
import org.jivesoftware.smack.sasl.SASLMechanism;

/* loaded from: classes.dex */
public class SASLAnonymous extends SASLMechanism {
    public SASLAnonymous(C5585d c5585d) {
        super(c5585d);
    }

    @Override // org.jivesoftware.smack.sasl.SASLMechanism
    /* renamed from: a */
    public void mo22183a() {
        m22190f().m22043k(new SASLMechanism.AuthMechanism(mo22185e(), null));
    }

    @Override // org.jivesoftware.smack.sasl.SASLMechanism
    /* renamed from: d */
    public void mo22184d(String str) {
        m22190f().m22043k(new SASLMechanism.Response());
    }

    @Override // org.jivesoftware.smack.sasl.SASLMechanism
    /* renamed from: e */
    public String mo22185e() {
        return "ANONYMOUS";
    }
}
