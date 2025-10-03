package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* loaded from: classes.dex */
public final class SonicAudioProcessor implements AudioProcessor {
    private static final float CLOSE_THRESHOLD = 0.01f;
    public static final float MAXIMUM_PITCH = 8.0f;
    public static final float MAXIMUM_SPEED = 8.0f;
    public static final float MINIMUM_PITCH = 0.1f;
    public static final float MINIMUM_SPEED = 0.1f;
    private static final int MIN_BYTES_FOR_SPEEDUP_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    private ByteBuffer buffer;
    private long inputBytes;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private long outputBytes;
    private int pendingOutputSampleRateHz;
    private ShortBuffer shortBuffer;
    private Sonic sonic;
    private float speed = 1.0f;
    private float pitch = 1.0f;
    private int channelCount = -1;
    private int sampleRateHz = -1;
    private int outputSampleRateHz = -1;

    public SonicAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.pendingOutputSampleRateHz = -1;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i9, int i10, int i11) throws AudioProcessor.UnhandledFormatException {
        if (i11 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i9, i10, i11);
        }
        int i12 = this.pendingOutputSampleRateHz;
        if (i12 == -1) {
            i12 = i9;
        }
        if (this.sampleRateHz == i9 && this.channelCount == i10 && this.outputSampleRateHz == i12) {
            return false;
        }
        this.sampleRateHz = i9;
        this.channelCount = i10;
        this.outputSampleRateHz = i12;
        return true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.sonic = new Sonic(this.sampleRateHz, this.channelCount, this.speed, this.pitch, this.outputSampleRateHz);
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
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
        return this.outputSampleRateHz;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return Math.abs(this.speed - 1.0f) >= CLOSE_THRESHOLD || Math.abs(this.pitch - 1.0f) >= CLOSE_THRESHOLD || this.outputSampleRateHz != this.sampleRateHz;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        Sonic sonic;
        return this.inputEnded && ((sonic = this.sonic) == null || sonic.getSamplesAvailable() == 0);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.sonic.queueEndOfStream();
        this.inputEnded = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
            int iRemaining = byteBuffer.remaining();
            this.inputBytes += iRemaining;
            this.sonic.queueInput(shortBufferAsShortBuffer);
            byteBuffer.position(byteBuffer.position() + iRemaining);
        }
        int samplesAvailable = this.sonic.getSamplesAvailable() * this.channelCount * 2;
        if (samplesAvailable > 0) {
            if (this.buffer.capacity() < samplesAvailable) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(samplesAvailable).order(ByteOrder.nativeOrder());
                this.buffer = byteBufferOrder;
                this.shortBuffer = byteBufferOrder.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            this.sonic.getOutput(this.shortBuffer);
            this.outputBytes += samplesAvailable;
            this.buffer.limit(samplesAvailable);
            this.outputBuffer = this.buffer;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        this.sonic = null;
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.shortBuffer = byteBuffer.asShortBuffer();
        this.outputBuffer = byteBuffer;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.inputBytes = 0L;
        this.outputBytes = 0L;
        this.inputEnded = false;
        this.pendingOutputSampleRateHz = -1;
    }

    public long scaleDurationForSpeedup(long j9) {
        long j10 = this.outputBytes;
        if (j10 < 1024) {
            return (long) (this.speed * j9);
        }
        int i9 = this.outputSampleRateHz;
        int i10 = this.sampleRateHz;
        return i9 == i10 ? Util.scaleLargeTimestamp(j9, this.inputBytes, j10) : Util.scaleLargeTimestamp(j9, this.inputBytes * i9, j10 * i10);
    }

    public void setOutputSampleRateHz(int i9) {
        this.pendingOutputSampleRateHz = i9;
    }

    public float setPitch(float f9) {
        this.pitch = Util.constrainValue(f9, 0.1f, 8.0f);
        return f9;
    }

    public float setSpeed(float f9) {
        float fConstrainValue = Util.constrainValue(f9, 0.1f, 8.0f);
        this.speed = fConstrainValue;
        return fConstrainValue;
    }
}
