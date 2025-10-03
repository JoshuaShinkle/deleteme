package com.google.android.gms.internal.firebase_messaging;

import com.google.android.gms.appinvite.PreviewActivity;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes2.dex */
public final class zzi {
    private static final Logger zza = Logger.getLogger(zzi.class.getName());

    private zzi() {
    }

    public static void zza(@NullableDecl InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e9) {
                try {
                    zza.logp(Level.WARNING, "com.google.common.io.Closeables", PreviewActivity.ON_CLICK_LISTENER_CLOSE, "IOException thrown while closing Closeable.", (Throwable) e9);
                } catch (IOException e10) {
                    throw new AssertionError(e10);
                }
            }
        }
    }
}
