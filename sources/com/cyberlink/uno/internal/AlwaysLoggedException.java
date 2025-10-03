package com.cyberlink.uno.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public final class AlwaysLoggedException extends RuntimeException {
    private final Throwable mCause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return null;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.mCause.getLocalizedMessage();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.mCause.getMessage();
    }

    @Override // java.lang.Throwable
    public StackTraceElement[] getStackTrace() {
        return this.mCause.getStackTrace();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        this.mCause.printStackTrace();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return this.mCause.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        this.mCause.printStackTrace(printStream);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        this.mCause.printStackTrace(printWriter);
    }
}
