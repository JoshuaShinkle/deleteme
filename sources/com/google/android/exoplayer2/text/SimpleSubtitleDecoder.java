package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class SimpleSubtitleDecoder extends SimpleDecoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> implements SubtitleDecoder {
    private final String name;

    public SimpleSubtitleDecoder(String str) {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        this.name = str;
        setInitialInputBufferSize(UserMetadata.MAX_ATTRIBUTE_SIZE);
    }

    public abstract Subtitle decode(byte[] bArr, int i9, boolean z8);

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public final String getName() {
        return this.name;
    }

    @Override // com.google.android.exoplayer2.text.SubtitleDecoder
    public void setPositionUs(long j9) {
    }

    @Override // com.google.android.exoplayer2.decoder.SimpleDecoder
    public final SubtitleInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    @Override // com.google.android.exoplayer2.decoder.SimpleDecoder
    public final SubtitleOutputBuffer createOutputBuffer() {
        return new SimpleSubtitleOutputBuffer(this);
    }

    @Override // com.google.android.exoplayer2.decoder.SimpleDecoder
    public final SubtitleDecoderException createUnexpectedDecodeException(Throwable th) {
        return new SubtitleDecoderException("Unexpected decode error", th);
    }

    @Override // com.google.android.exoplayer2.decoder.SimpleDecoder
    public final SubtitleDecoderException decode(SubtitleInputBuffer subtitleInputBuffer, SubtitleOutputBuffer subtitleOutputBuffer, boolean z8) {
        try {
            ByteBuffer byteBuffer = subtitleInputBuffer.data;
            subtitleOutputBuffer.setContent(subtitleInputBuffer.timeUs, decode(byteBuffer.array(), byteBuffer.limit(), z8), subtitleInputBuffer.subsampleOffsetUs);
            subtitleOutputBuffer.clearFlag(Integer.MIN_VALUE);
            return null;
        } catch (SubtitleDecoderException e9) {
            return e9;
        }
    }

    @Override // com.google.android.exoplayer2.decoder.SimpleDecoder
    public final void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        super.releaseOutputBuffer((SimpleSubtitleDecoder) subtitleOutputBuffer);
    }
}
