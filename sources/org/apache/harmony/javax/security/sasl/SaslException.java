package org.apache.harmony.javax.security.sasl;

import java.io.IOException;

/* loaded from: classes.dex */
public class SaslException extends IOException {
    private static final long serialVersionUID = 4579784287983423626L;
    private Throwable _exception;

    public SaslException() {
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._exception;
    }

    @Override // java.lang.Throwable
    public Throwable initCause(Throwable th) {
        super.initCause(th);
        this._exception = th;
        return this;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this._exception == null) {
            return super.toString();
        }
        return super.toString() + ", caused by: " + this._exception.toString();
    }

    public SaslException(String str) {
        super(str);
    }

    public SaslException(String str, Throwable th) {
        super(str);
        if (th != null) {
            super.initCause(th);
            this._exception = th;
        }
    }
}
