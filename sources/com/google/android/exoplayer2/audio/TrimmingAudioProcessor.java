package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
final class TrimmingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private int channelCount;
    private byte[] endBuffer;
    private int endBufferSize;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer;
    private int pendingTrimStartBytes;
    private int sampleRateHz;
    private int trimEndSamples;
    private int trimStartSamples;

    public TrimmingAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        this.channelCount = -1;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i9, int i10, int i11) throws AudioProcessor.UnhandledFormatException {
        if (i11 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i9, i10, i11);
        }
        this.channelCount = i10;
        this.sampleRateHz = i9;
        int i12 = this.trimEndSamples;
        this.endBuffer = new byte[i12 * i10 * 2];
        this.endBufferSize = 0;
        int i13 = this.trimStartSamples;
        this.pendingTrimStartBytes = i10 * i13 * 2;
        boolean z8 = this.isActive;
        boolean z9 = (i13 == 0 && i12 == 0) ? false : true;
        this.isActive = z9;
        return z8 != z9;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
        this.pendingTrimStartBytes = 0;
        this.endBufferSize = 0;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        return this.channelCount;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return this.isActive;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == AudioProcessor.EMPTY_BUFFER;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i9 = iLimit - iPosition;
        int iMin = Math.min(i9, this.pendingTrimStartBytes);
        this.pendingTrimStartBytes -= iMin;
        byteBuffer.position(iPosition + iMin);
        if (this.pendingTrimStartBytes > 0) {
            return;
        }
        int i10 = i9 - iMin;
        int length = (this.endBufferSize + i10) - this.endBuffer.length;
        if (this.buffer.capacity() < length) {
            this.buffer = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        int iConstrainValue = Util.constrainValue(length, 0, this.endBufferSize);
        this.buffer.put(this.endBuffer, 0, iConstrainValue);
        int iConstrainValue2 = Util.constrainValue(length - iConstrainValue, 0, i10);
        byteBuffer.limit(byteBuffer.position() + iConstrainValue2);
        this.buffer.put(byteBuffer);
        byteBuffer.limit(iLimit);
        int i11 = i10 - iConstrainValue2;
        int i12 = this.endBufferSize - iConstrainValue;
        this.endBufferSize = i12;
        byte[] bArr = this.endBuffer;
        System.arraycopy(bArr, iConstrainValue, bArr, 0, i12);
        byteBuffer.get(this.endBuffer, this.endBufferSize, i11);
        this.endBufferSize += i11;
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        this.buffer = AudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = null;
    }

    public void setTrimSampleCount(int i9, int i10) {
        this.trimStartSamples = i9;
        this.trimEndSamples = i10;
    }
}
