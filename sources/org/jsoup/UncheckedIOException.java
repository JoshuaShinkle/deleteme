package org.jsoup;

import java.io.IOException;

/* loaded from: classes.dex */
public class UncheckedIOException extends RuntimeException {
    public UncheckedIOException(IOException iOException) {
        super(iOException);
    }

    /* renamed from: a */
    public IOException m22820a() {
        return (IOException) getCause();
    }
}
