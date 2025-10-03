package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
final class ResamplingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int sampleRateHz = -1;
    private int channelCount = -1;
    private int encoding = 0;

    public ResamplingAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i9, int i10, int i11) throws AudioProcessor.UnhandledFormatException {
        if (i11 != 3 && i11 != 2 && i11 != Integer.MIN_VALUE && i11 != 1073741824) {
            throw new AudioProcessor.UnhandledFormatException(i9, i10, i11);
        }
        if (this.sampleRateHz == i9 && this.channelCount == i10 && this.encoding == i11) {
            return false;
        }
        this.sampleRateHz = i9;
        this.channelCount = i10;
        this.encoding = i11;
        if (i11 != 2) {
            return true;
        }
        this.buffer = AudioProcessor.EMPTY_BUFFER;
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
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
        int i9 = this.encoding;
        return (i9 == 0 || i9 == 2) ? false : true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == AudioProcessor.EMPTY_BUFFER;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0081 A[ADDED_TO_REGION, LOOP:2: B:25:0x0081->B:26:0x0083, LOOP_START, PHI: r0
      0x0081: PHI (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:16:0x0041, B:26:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void queueInput(ByteBuffer byteBuffer) {
        int i9;
        int i10;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i11 = iLimit - iPosition;
        int i12 = this.encoding;
        if (i12 != Integer.MIN_VALUE) {
            if (i12 != 3) {
                if (i12 != 1073741824) {
                    throw new IllegalStateException();
                }
                i9 = i11 / 2;
            }
            if (this.buffer.capacity() >= i9) {
                this.buffer = ByteBuffer.allocateDirect(i9).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            i10 = this.encoding;
            if (i10 != Integer.MIN_VALUE) {
                while (iPosition < iLimit) {
                    this.buffer.put(byteBuffer.get(iPosition + 1));
                    this.buffer.put(byteBuffer.get(iPosition + 2));
                    iPosition += 3;
                }
            } else if (i10 == 3) {
                while (iPosition < iLimit) {
                    this.buffer.put((byte) 0);
                    this.buffer.put((byte) ((byteBuffer.get(iPosition) & UnsignedBytes.MAX_VALUE) - 128));
                    iPosition++;
                }
            } else {
                if (i10 != 1073741824) {
                    throw new IllegalStateException();
                }
                while (iPosition < iLimit) {
                    this.buffer.put(byteBuffer.get(iPosition + 2));
                    this.buffer.put(byteBuffer.get(iPosition + 3));
                    iPosition += 4;
                }
            }
            byteBuffer.position(byteBuffer.limit());
            this.buffer.flip();
            this.outputBuffer = this.buffer;
        }
        i11 /= 3;
        i9 = i11 * 2;
        if (this.buffer.capacity() >= i9) {
        }
        i10 = this.encoding;
        if (i10 != Integer.MIN_VALUE) {
        }
        byteBuffer.position(byteBuffer.limit());
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        this.buffer = AudioProcessor.EMPTY_BUFFER;
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = 0;
    }
}
