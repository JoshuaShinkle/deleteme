package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(String str) {
        super(str);
    }

    public VerifyException(Throwable th) {
        super(th);
    }

    public VerifyException(String str, Throwable th) {
        super(str, th);
    }
}
