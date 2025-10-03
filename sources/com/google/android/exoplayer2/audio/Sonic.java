package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* loaded from: classes.dex */
final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputBufferSize;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequired;
    private int minDiff;
    private final int minPeriod;
    private final int numChannels;
    private int numInputSamples;
    private int numOutputSamples;
    private int numPitchSamples;
    private short[] outputBuffer;
    private int outputBufferSize;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchBufferSize;
    private int prevMinDiff;
    private final float rate;
    private int remainingInputToCopy;
    private final float speed;
    private int oldRatePosition = 0;
    private int newRatePosition = 0;
    private int prevPeriod = 0;

    public Sonic(int i9, int i10, float f9, float f10, int i11) {
        this.inputSampleRateHz = i9;
        this.numChannels = i10;
        this.minPeriod = i9 / MAXIMUM_PITCH;
        int i12 = i9 / 65;
        this.maxPeriod = i12;
        int i13 = i12 * 2;
        this.maxRequired = i13;
        this.downSampleBuffer = new short[i13];
        this.inputBufferSize = i13;
        this.inputBuffer = new short[i13 * i10];
        this.outputBufferSize = i13;
        this.outputBuffer = new short[i13 * i10];
        this.pitchBufferSize = i13;
        this.pitchBuffer = new short[i13 * i10];
        this.speed = f9;
        this.pitch = f10;
        this.rate = i9 / i11;
    }

    private void adjustRate(float f9, int i9) {
        int i10;
        int i11;
        if (this.numOutputSamples == i9) {
            return;
        }
        int i12 = this.inputSampleRateHz;
        int i13 = (int) (i12 / f9);
        while (true) {
            if (i13 <= 16384 && i12 <= 16384) {
                break;
            }
            i13 /= 2;
            i12 /= 2;
        }
        moveNewSamplesToPitchBuffer(i9);
        int i14 = 0;
        while (true) {
            int i15 = this.numPitchSamples;
            if (i14 >= i15 - 1) {
                removePitchSamples(i15 - 1);
                return;
            }
            while (true) {
                i10 = this.oldRatePosition;
                int i16 = (i10 + 1) * i13;
                i11 = this.newRatePosition;
                if (i16 <= i11 * i12) {
                    break;
                }
                enlargeOutputBufferIfNeeded(1);
                int i17 = 0;
                while (true) {
                    int i18 = this.numChannels;
                    if (i17 < i18) {
                        this.outputBuffer[(this.numOutputSamples * i18) + i17] = interpolate(this.pitchBuffer, (i18 * i14) + i17, i12, i13);
                        i17++;
                    }
                }
                this.newRatePosition++;
                this.numOutputSamples++;
            }
            int i19 = i10 + 1;
            this.oldRatePosition = i19;
            if (i19 == i12) {
                this.oldRatePosition = 0;
                Assertions.checkState(i11 == i13);
                this.newRatePosition = 0;
            }
            i14++;
        }
    }

    private void changeSpeed(float f9) {
        int iSkipPitchPeriod;
        int i9 = this.numInputSamples;
        if (i9 < this.maxRequired) {
            return;
        }
        int i10 = 0;
        do {
            if (this.remainingInputToCopy > 0) {
                iSkipPitchPeriod = copyInputToOutput(i10);
            } else {
                int iFindPitchPeriod = findPitchPeriod(this.inputBuffer, i10, true);
                iSkipPitchPeriod = ((double) f9) > 1.0d ? iFindPitchPeriod + skipPitchPeriod(this.inputBuffer, i10, f9, iFindPitchPeriod) : insertPitchPeriod(this.inputBuffer, i10, f9, iFindPitchPeriod);
            }
            i10 += iSkipPitchPeriod;
        } while (this.maxRequired + i10 <= i9);
        removeProcessedInputSamples(i10);
    }

    private int copyInputToOutput(int i9) {
        int iMin = Math.min(this.maxRequired, this.remainingInputToCopy);
        copyToOutput(this.inputBuffer, i9, iMin);
        this.remainingInputToCopy -= iMin;
        return iMin;
    }

    private void copyToOutput(short[] sArr, int i9, int i10) {
        enlargeOutputBufferIfNeeded(i10);
        int i11 = this.numChannels;
        System.arraycopy(sArr, i9 * i11, this.outputBuffer, this.numOutputSamples * i11, i11 * i10);
        this.numOutputSamples += i10;
    }

    private void downSampleInput(short[] sArr, int i9, int i10) {
        int i11 = this.maxRequired / i10;
        int i12 = this.numChannels;
        int i13 = i10 * i12;
        int i14 = i9 * i12;
        for (int i15 = 0; i15 < i11; i15++) {
            int i16 = 0;
            for (int i17 = 0; i17 < i13; i17++) {
                i16 += sArr[(i15 * i13) + i14 + i17];
            }
            this.downSampleBuffer[i15] = (short) (i16 / i13);
        }
    }

    private void enlargeInputBufferIfNeeded(int i9) {
        int i10 = this.numInputSamples + i9;
        int i11 = this.inputBufferSize;
        if (i10 > i11) {
            int i12 = i11 + (i11 / 2) + i9;
            this.inputBufferSize = i12;
            this.inputBuffer = Arrays.copyOf(this.inputBuffer, i12 * this.numChannels);
        }
    }

    private void enlargeOutputBufferIfNeeded(int i9) {
        int i10 = this.numOutputSamples + i9;
        int i11 = this.outputBufferSize;
        if (i10 > i11) {
            int i12 = i11 + (i11 / 2) + i9;
            this.outputBufferSize = i12;
            this.outputBuffer = Arrays.copyOf(this.outputBuffer, i12 * this.numChannels);
        }
    }

    private int findPitchPeriod(short[] sArr, int i9, boolean z8) {
        int iFindPitchPeriodInRange;
        int i10 = this.inputSampleRateHz;
        int i11 = i10 > AMDF_FREQUENCY ? i10 / AMDF_FREQUENCY : 1;
        if (this.numChannels == 1 && i11 == 1) {
            iFindPitchPeriodInRange = findPitchPeriodInRange(sArr, i9, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i9, i11);
            int iFindPitchPeriodInRange2 = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i11, this.maxPeriod / i11);
            if (i11 != 1) {
                int i12 = iFindPitchPeriodInRange2 * i11;
                int i13 = i11 * 4;
                int i14 = i12 - i13;
                int i15 = i12 + i13;
                int i16 = this.minPeriod;
                if (i14 < i16) {
                    i14 = i16;
                }
                int i17 = this.maxPeriod;
                if (i15 > i17) {
                    i15 = i17;
                }
                if (this.numChannels == 1) {
                    iFindPitchPeriodInRange = findPitchPeriodInRange(sArr, i9, i14, i15);
                } else {
                    downSampleInput(sArr, i9, 1);
                    iFindPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, i14, i15);
                }
            } else {
                iFindPitchPeriodInRange = iFindPitchPeriodInRange2;
            }
        }
        int i18 = previousPeriodBetter(this.minDiff, this.maxDiff, z8) ? this.prevPeriod : iFindPitchPeriodInRange;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = iFindPitchPeriodInRange;
        return i18;
    }

    private int findPitchPeriodInRange(short[] sArr, int i9, int i10, int i11) {
        int i12 = i9 * this.numChannels;
        int i13 = 255;
        int i14 = 1;
        int i15 = 0;
        int i16 = 0;
        while (i10 <= i11) {
            int iAbs = 0;
            for (int i17 = 0; i17 < i10; i17++) {
                iAbs += Math.abs(sArr[i12 + i17] - sArr[(i12 + i10) + i17]);
            }
            if (iAbs * i15 < i14 * i10) {
                i15 = i10;
                i14 = iAbs;
            }
            if (iAbs * i13 > i16 * i10) {
                i13 = i10;
                i16 = iAbs;
            }
            i10++;
        }
        this.minDiff = i14 / i15;
        this.maxDiff = i16 / i13;
        return i15;
    }

    private int insertPitchPeriod(short[] sArr, int i9, float f9, int i10) {
        int i11;
        if (f9 < 0.5f) {
            i11 = (int) ((i10 * f9) / (1.0f - f9));
        } else {
            this.remainingInputToCopy = (int) ((i10 * ((2.0f * f9) - 1.0f)) / (1.0f - f9));
            i11 = i10;
        }
        int i12 = i10 + i11;
        enlargeOutputBufferIfNeeded(i12);
        int i13 = this.numChannels;
        System.arraycopy(sArr, i9 * i13, this.outputBuffer, this.numOutputSamples * i13, i13 * i10);
        overlapAdd(i11, this.numChannels, this.outputBuffer, this.numOutputSamples + i10, sArr, i9 + i10, sArr, i9);
        this.numOutputSamples += i12;
        return i11;
    }

    private short interpolate(short[] sArr, int i9, int i10, int i11) {
        short s8 = sArr[i9];
        short s9 = sArr[i9 + this.numChannels];
        int i12 = this.newRatePosition * i10;
        int i13 = this.oldRatePosition;
        int i14 = i13 * i11;
        int i15 = (i13 + 1) * i11;
        int i16 = i15 - i12;
        int i17 = i15 - i14;
        return (short) (((s8 * i16) + ((i17 - i16) * s9)) / i17);
    }

    private void moveNewSamplesToPitchBuffer(int i9) {
        int i10 = this.numOutputSamples - i9;
        int i11 = this.numPitchSamples + i10;
        int i12 = this.pitchBufferSize;
        if (i11 > i12) {
            int i13 = i12 + (i12 / 2) + i10;
            this.pitchBufferSize = i13;
            this.pitchBuffer = Arrays.copyOf(this.pitchBuffer, i13 * this.numChannels);
        }
        short[] sArr = this.outputBuffer;
        int i14 = this.numChannels;
        System.arraycopy(sArr, i9 * i14, this.pitchBuffer, this.numPitchSamples * i14, i14 * i10);
        this.numOutputSamples = i9;
        this.numPitchSamples += i10;
    }

    private static void overlapAdd(int i9, int i10, short[] sArr, int i11, short[] sArr2, int i12, short[] sArr3, int i13) {
        for (int i14 = 0; i14 < i10; i14++) {
            int i15 = (i11 * i10) + i14;
            int i16 = (i13 * i10) + i14;
            int i17 = (i12 * i10) + i14;
            for (int i18 = 0; i18 < i9; i18++) {
                sArr[i15] = (short) (((sArr2[i17] * (i9 - i18)) + (sArr3[i16] * i18)) / i9);
                i15 += i10;
                i17 += i10;
                i16 += i10;
            }
        }
    }

    private boolean previousPeriodBetter(int i9, int i10, boolean z8) {
        if (i9 == 0 || this.prevPeriod == 0) {
            return false;
        }
        return z8 ? i10 <= i9 * 3 && i9 * 2 > this.prevMinDiff * 3 : i9 > this.prevMinDiff;
    }

    private void processStreamInput() {
        int i9 = this.numOutputSamples;
        float f9 = this.speed;
        float f10 = this.pitch;
        float f11 = f9 / f10;
        float f12 = this.rate * f10;
        double d9 = f11;
        if (d9 > 1.00001d || d9 < 0.99999d) {
            changeSpeed(f11);
        } else {
            copyToOutput(this.inputBuffer, 0, this.numInputSamples);
            this.numInputSamples = 0;
        }
        if (f12 != 1.0f) {
            adjustRate(f12, i9);
        }
    }

    private void removePitchSamples(int i9) {
        if (i9 == 0) {
            return;
        }
        short[] sArr = this.pitchBuffer;
        int i10 = this.numChannels;
        System.arraycopy(sArr, i9 * i10, sArr, 0, (this.numPitchSamples - i9) * i10);
        this.numPitchSamples -= i9;
    }

    private void removeProcessedInputSamples(int i9) {
        int i10 = this.numInputSamples - i9;
        short[] sArr = this.inputBuffer;
        int i11 = this.numChannels;
        System.arraycopy(sArr, i9 * i11, sArr, 0, i11 * i10);
        this.numInputSamples = i10;
    }

    private int skipPitchPeriod(short[] sArr, int i9, float f9, int i10) {
        int i11;
        if (f9 >= 2.0f) {
            i11 = (int) (i10 / (f9 - 1.0f));
        } else {
            this.remainingInputToCopy = (int) ((i10 * (2.0f - f9)) / (f9 - 1.0f));
            i11 = i10;
        }
        enlargeOutputBufferIfNeeded(i11);
        overlapAdd(i11, this.numChannels, this.outputBuffer, this.numOutputSamples, sArr, i9, sArr, i9 + i10);
        this.numOutputSamples += i11;
        return i11;
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int iMin = Math.min(shortBuffer.remaining() / this.numChannels, this.numOutputSamples);
        shortBuffer.put(this.outputBuffer, 0, this.numChannels * iMin);
        int i9 = this.numOutputSamples - iMin;
        this.numOutputSamples = i9;
        short[] sArr = this.outputBuffer;
        int i10 = this.numChannels;
        System.arraycopy(sArr, iMin * i10, sArr, 0, i9 * i10);
    }

    public int getSamplesAvailable() {
        return this.numOutputSamples;
    }

    public void queueEndOfStream() {
        int i9;
        int i10 = this.numInputSamples;
        float f9 = this.speed;
        float f10 = this.pitch;
        int i11 = this.numOutputSamples + ((int) ((((i10 / (f9 / f10)) + this.numPitchSamples) / (this.rate * f10)) + 0.5f));
        enlargeInputBufferIfNeeded((this.maxRequired * 2) + i10);
        int i12 = 0;
        while (true) {
            i9 = this.maxRequired;
            int i13 = this.numChannels;
            if (i12 >= i9 * 2 * i13) {
                break;
            }
            this.inputBuffer[(i13 * i10) + i12] = 0;
            i12++;
        }
        this.numInputSamples += i9 * 2;
        processStreamInput();
        if (this.numOutputSamples > i11) {
            this.numOutputSamples = i11;
        }
        this.numInputSamples = 0;
        this.remainingInputToCopy = 0;
        this.numPitchSamples = 0;
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int iRemaining = shortBuffer.remaining();
        int i9 = this.numChannels;
        int i10 = iRemaining / i9;
        enlargeInputBufferIfNeeded(i10);
        shortBuffer.get(this.inputBuffer, this.numInputSamples * this.numChannels, ((i9 * i10) * 2) / 2);
        this.numInputSamples += i10;
        processStreamInput();
    }
}
