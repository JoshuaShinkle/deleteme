package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.C5596d;
import org.jivesoftware.smack.packet.XMPPError;

/* loaded from: classes.dex */
public abstract class XMPPException extends Exception {
    private static final long serialVersionUID = 6881651633890968625L;

    public static class StreamErrorException extends XMPPException {
        private static final long serialVersionUID = 3400556867134848886L;
        private final C5596d streamError;

        public StreamErrorException(C5596d c5596d) {
            this.streamError = c5596d;
        }

        /* renamed from: a */
        public C5596d m22017a() {
            return this.streamError;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.streamError.toString();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return getMessage();
        }
    }

    public static class XMPPErrorException extends XMPPException {
        private static final long serialVersionUID = 212790389529249604L;
        private final XMPPError error;

        public XMPPErrorException(XMPPError xMPPError) {
            this.error = xMPPError;
        }

        /* renamed from: a */
        public XMPPError m22018a() {
            return this.error;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            String message = super.getMessage();
            return message != null ? message : this.error.toString();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return getMessage();
        }
    }

    public XMPPException() {
    }

    public XMPPException(String str) {
        super(str);
    }
}
