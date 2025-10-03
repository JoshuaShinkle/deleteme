package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
final class FloatResamplingAudioProcessor implements AudioProcessor {
    private static final int FLOAT_NAN_AS_INT = Float.floatToIntBits(Float.NaN);
    private static final double PCM_32_BIT_INT_TO_PCM_32_BIT_FLOAT_FACTOR = 4.656612875245797E-10d;
    private ByteBuffer buffer;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int sampleRateHz = -1;
    private int channelCount = -1;
    private int sourceEncoding = 0;

    public FloatResamplingAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
    }

    private static void writePcm32BitFloat(int i9, ByteBuffer byteBuffer) {
        int iFloatToIntBits = Float.floatToIntBits((float) (i9 * PCM_32_BIT_INT_TO_PCM_32_BIT_FLOAT_FACTOR));
        if (iFloatToIntBits == FLOAT_NAN_AS_INT) {
            iFloatToIntBits = Float.floatToIntBits(BitmapDescriptorFactory.HUE_RED);
        }
        byteBuffer.putInt(iFloatToIntBits);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i9, int i10, int i11) throws AudioProcessor.UnhandledFormatException {
        if (!Util.isEncodingHighResolutionIntegerPcm(i11)) {
            throw new AudioProcessor.UnhandledFormatException(i9, i10, i11);
        }
        if (this.sampleRateHz == i9 && this.channelCount == i10 && this.sourceEncoding == i11) {
            return false;
        }
        this.sampleRateHz = i9;
        this.channelCount = i10;
        this.sourceEncoding = i11;
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
        return 4;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return Util.isEncodingHighResolutionIntegerPcm(this.sourceEncoding);
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
        Assertions.checkState(isActive());
        boolean z8 = this.sourceEncoding == 1073741824;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i9 = iLimit - iPosition;
        if (!z8) {
            i9 = (i9 / 3) * 4;
        }
        if (this.buffer.capacity() < i9) {
            this.buffer = ByteBuffer.allocateDirect(i9).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        if (z8) {
            while (iPosition < iLimit) {
                writePcm32BitFloat((byteBuffer.get(iPosition) & UnsignedBytes.MAX_VALUE) | ((byteBuffer.get(iPosition + 1) & UnsignedBytes.MAX_VALUE) << 8) | ((byteBuffer.get(iPosition + 2) & UnsignedBytes.MAX_VALUE) << 16) | ((byteBuffer.get(iPosition + 3) & UnsignedBytes.MAX_VALUE) << 24), this.buffer);
                iPosition += 4;
            }
        } else {
            while (iPosition < iLimit) {
                writePcm32BitFloat(((byteBuffer.get(iPosition) & UnsignedBytes.MAX_VALUE) << 8) | ((byteBuffer.get(iPosition + 1) & UnsignedBytes.MAX_VALUE) << 16) | ((byteBuffer.get(iPosition + 2) & UnsignedBytes.MAX_VALUE) << 24), this.buffer);
                iPosition += 3;
            }
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
        this.sourceEncoding = 0;
    }
}
