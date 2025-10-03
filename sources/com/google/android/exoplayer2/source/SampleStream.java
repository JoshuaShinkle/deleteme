package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

/* loaded from: classes.dex */
public interface SampleStream {
    boolean isReady();

    void maybeThrowError();

    int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8);

    int skipData(long j9);
}
