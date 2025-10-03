package org.jivesoftware.smack.sasl;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public enum SASLError {
    aborted,
    account_disabled,
    credentials_expired,
    encryption_required,
    incorrect_encoding,
    invalid_authzid,
    invalid_mechanism,
    malformed_request,
    mechanism_too_weak,
    not_authorized,
    temporary_auth_failure;


    /* renamed from: m */
    public static final Logger f19379m = Logger.getLogger(SASLError.class.getName());

    /* renamed from: a */
    public static SASLError m22186a(String str) {
        String strReplace = str.replace('-', '_');
        try {
            return valueOf(strReplace);
        } catch (Exception e9) {
            f19379m.log(Level.WARNING, "Could not transform string '" + strReplace + "' to SASLError", (Throwable) e9);
            return null;
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return name().replace('_', '-');
    }
}
