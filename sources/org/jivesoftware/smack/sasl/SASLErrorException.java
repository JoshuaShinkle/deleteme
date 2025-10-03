package org.jivesoftware.smack.sasl;

import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;

/* loaded from: classes.dex */
public class SASLErrorException extends XMPPException {
    private static final long serialVersionUID = 6247573875760717257L;
    private final String mechanism;
    private final SASLMechanism.SASLFailure saslFailure;
    private final Map<String, String> texts;

    public SASLErrorException(String str, SASLMechanism.SASLFailure sASLFailure) {
        this(str, sASLFailure, new HashMap());
    }

    /* renamed from: a */
    public SASLMechanism.SASLFailure m22187a() {
        return this.saslFailure;
    }

    public SASLErrorException(String str, SASLMechanism.SASLFailure sASLFailure, Map<String, String> map) {
        super("SASLError using " + str + ": " + sASLFailure.m22194v());
        this.mechanism = str;
        this.saslFailure = sASLFailure;
        this.texts = map;
    }
}
