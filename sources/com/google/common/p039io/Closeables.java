package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Closeables {

    @VisibleForTesting
    static final Logger logger = Logger.getLogger(Closeables.class.getName());

    private Closeables() {
    }

    public static void close(Closeable closeable, boolean z8) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e9) {
            if (!z8) {
                throw e9;
            }
            logger.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e9);
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        try {
            close(inputStream, true);
        } catch (IOException e9) {
            throw new AssertionError(e9);
        }
    }

    public static void closeQuietly(Reader reader) {
        try {
            close(reader, true);
        } catch (IOException e9) {
            throw new AssertionError(e9);
        }
    }
}
