package com.google.android.exoplayer2.decoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public class SimpleOutputBuffer extends OutputBuffer {
    public ByteBuffer data;
    private final SimpleDecoder<?, SimpleOutputBuffer, ?> owner;

    public SimpleOutputBuffer(SimpleDecoder<?, SimpleOutputBuffer, ?> simpleDecoder) {
        this.owner = simpleDecoder;
    }

    @Override // com.google.android.exoplayer2.decoder.Buffer
    public void clear() {
        super.clear();
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    public ByteBuffer init(long j9, int i9) {
        this.timeUs = j9;
        ByteBuffer byteBuffer = this.data;
        if (byteBuffer == null || byteBuffer.capacity() < i9) {
            this.data = ByteBuffer.allocateDirect(i9).order(ByteOrder.nativeOrder());
        }
        this.data.position(0);
        this.data.limit(i9);
        return this.data;
    }

    @Override // com.google.android.exoplayer2.decoder.OutputBuffer
    public void release() {
        this.owner.releaseOutputBuffer(this);
    }
}
