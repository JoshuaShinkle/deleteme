package org.jivesoftware.smack;

import java.util.List;
import p028c7.C0751b;

/* loaded from: classes.dex */
public class SmackException extends Exception {
    private static final long serialVersionUID = 1844674365368214457L;

    public static class AlreadyLoggedInException extends SmackException {
        private static final long serialVersionUID = 5011416918049935231L;
    }

    public static class ConnectionException extends SmackException {
        private static final long serialVersionUID = 1686944201672697996L;
        private final List<C0751b> failedAddresses;

        public ConnectionException(List<C0751b> list) {
            this.failedAddresses = list;
        }

        /* renamed from: a */
        public List<C0751b> m21960a() {
            return this.failedAddresses;
        }
    }

    public static class FeatureNotSupportedException extends SmackException {
        private static final long serialVersionUID = 4713404802621452016L;
        private final String feature;
        private final String jid;
    }

    public static class IllegalStateChangeException extends SmackException {
        private static final long serialVersionUID = -1766023961577168927L;
    }

    public static class NoResponseException extends SmackException {
        private static final long serialVersionUID = -6523363748984543636L;
    }

    public static class NotConnectedException extends SmackException {
        private static final long serialVersionUID = 9197980400776001173L;
    }

    public static class NotLoggedInException extends SmackException {
        private static final long serialVersionUID = 3216216839100019278L;
    }

    public static class ResourceBindingNotOfferedException extends SmackException {
        private static final long serialVersionUID = 2346934138253437571L;
    }

    public static class SecurityRequiredException extends SmackException {
        private static final long serialVersionUID = 384291845029773545L;
    }

    public SmackException(Throwable th) {
        super(th);
    }

    public SmackException(String str) {
        super(str);
    }

    public SmackException(String str, Throwable th) {
        super(str, th);
    }

    public SmackException() {
    }
}
