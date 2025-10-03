package com.google.android.exoplayer2.decoder;

import java.lang.Exception;

/* loaded from: classes.dex */
public interface Decoder<I, O, E extends Exception> {
    I dequeueInputBuffer();

    O dequeueOutputBuffer();

    void flush();

    String getName();

    void queueInputBuffer(I i9);

    void release();
}
