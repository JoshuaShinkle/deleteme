package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class ExoPlaybackException extends Exception {
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    public final int rendererIndex;
    public final int type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private ExoPlaybackException(int i9, String str, Throwable th, int i10) {
        super(str, th);
        this.type = i9;
        this.rendererIndex = i10;
    }

    public static ExoPlaybackException createForRenderer(Exception exc, int i9) {
        return new ExoPlaybackException(1, null, exc, i9);
    }

    public static ExoPlaybackException createForSource(IOException iOException) {
        return new ExoPlaybackException(0, null, iOException, -1);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return new ExoPlaybackException(2, null, runtimeException, -1);
    }

    public Exception getRendererException() {
        Assertions.checkState(this.type == 1);
        return (Exception) getCause();
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        return (IOException) getCause();
    }

    public RuntimeException getUnexpectedException() {
        Assertions.checkState(this.type == 2);
        return (RuntimeException) getCause();
    }
}
