package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
final class OpusReader extends StreamReader {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int OPUS_CODE = Util.getIntegerCodeForString("Opus");
    private static final byte[] OPUS_SIGNATURE = {79, 112, 117, 115, 72, 101, 97, 100};
    private static final int SAMPLE_RATE = 48000;
    private boolean headerRead;

    private long getPacketDurationUs(byte[] bArr) {
        int i9;
        int i10 = bArr[0] & UnsignedBytes.MAX_VALUE;
        int i11 = i10 & 3;
        if (i11 != 0) {
            i9 = 2;
            if (i11 != 1 && i11 != 2) {
                i9 = bArr[1] & 63;
            }
        } else {
            i9 = 1;
        }
        int i12 = i10 >> 3;
        return i9 * (i12 >= 16 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS << r1 : i12 >= 12 ? 10000 << (r1 & 1) : (i12 & 3) == 3 ? 60000 : 10000 << r1);
    }

    private void putNativeOrderLong(List<byte[]> list, int i9) {
        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((i9 * C3322C.NANOS_PER_SECOND) / 48000).array());
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        int iBytesLeft = parsableByteArray.bytesLeft();
        byte[] bArr = OPUS_SIGNATURE;
        if (iBytesLeft < bArr.length) {
            return false;
        }
        byte[] bArr2 = new byte[bArr.length];
        parsableByteArray.readBytes(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(getPacketDurationUs(parsableByteArray.data));
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j9, StreamReader.SetupData setupData) {
        if (this.headerRead) {
            boolean z8 = parsableByteArray.readInt() == OPUS_CODE;
            parsableByteArray.setPosition(0);
            return z8;
        }
        byte[] bArrCopyOf = Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit());
        int i9 = bArrCopyOf[9] & UnsignedBytes.MAX_VALUE;
        int i10 = ((bArrCopyOf[11] & UnsignedBytes.MAX_VALUE) << 8) | (bArrCopyOf[10] & UnsignedBytes.MAX_VALUE);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArrCopyOf);
        putNativeOrderLong(arrayList, i10);
        putNativeOrderLong(arrayList, DEFAULT_SEEK_PRE_ROLL_SAMPLES);
        setupData.format = Format.createAudioSampleFormat(null, MimeTypes.AUDIO_OPUS, null, -1, -1, i9, SAMPLE_RATE, arrayList, null, 0, null);
        this.headerRead = true;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void reset(boolean z8) {
        super.reset(z8);
        if (z8) {
            this.headerRead = false;
        }
    }
}
