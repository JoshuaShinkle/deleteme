package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.extractor.ogg.VorbisUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class VorbisReader extends StreamReader {
    private VorbisUtil.CommentHeader commentHeader;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;

    public static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader, byte[] bArr, VorbisUtil.Mode[] modeArr, int i9) {
            this.idHeader = vorbisIdHeader;
            this.commentHeader = commentHeader;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i9;
        }
    }

    public static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j9) {
        parsableByteArray.setLimit(parsableByteArray.limit() + 4);
        parsableByteArray.data[parsableByteArray.limit() - 4] = (byte) (j9 & 255);
        parsableByteArray.data[parsableByteArray.limit() - 3] = (byte) ((j9 >>> 8) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 2] = (byte) ((j9 >>> 16) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 1] = (byte) ((j9 >>> 24) & 255);
    }

    private static int decodeBlockSize(byte b9, VorbisSetup vorbisSetup) {
        return !vorbisSetup.modes[readBits(b9, vorbisSetup.iLogModes, 1)].blockFlag ? vorbisSetup.idHeader.blockSize0 : vorbisSetup.idHeader.blockSize1;
    }

    public static int readBits(byte b9, int i9, int i10) {
        return (b9 >> i10) & (255 >>> (8 - i9));
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void onSeekEnd(long j9) {
        super.onSeekEnd(j9);
        this.seenFirstAudioPacket = j9 != 0;
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.vorbisIdHeader;
        this.previousPacketBlockSize = vorbisIdHeader != null ? vorbisIdHeader.blockSize0 : 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray parsableByteArray) {
        byte b9 = parsableByteArray.data[0];
        if ((b9 & 1) == 1) {
            return -1L;
        }
        int iDecodeBlockSize = decodeBlockSize(b9, this.vorbisSetup);
        long j9 = this.seenFirstAudioPacket ? (this.previousPacketBlockSize + iDecodeBlockSize) / 4 : 0;
        appendNumberOfSamples(parsableByteArray, j9);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = iDecodeBlockSize;
        return j9;
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j9, StreamReader.SetupData setupData) throws ParserException {
        if (this.vorbisSetup != null) {
            return false;
        }
        VorbisSetup setupHeaders = readSetupHeaders(parsableByteArray);
        this.vorbisSetup = setupHeaders;
        if (setupHeaders == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.vorbisSetup.idHeader.data);
        arrayList.add(this.vorbisSetup.setupHeaderData);
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.vorbisSetup.idHeader;
        setupData.format = Format.createAudioSampleFormat(null, MimeTypes.AUDIO_VORBIS, null, vorbisIdHeader.bitrateNominal, -1, vorbisIdHeader.channels, (int) vorbisIdHeader.sampleRate, arrayList, null, 0, null);
        return true;
    }

    public VorbisSetup readSetupHeaders(ParsableByteArray parsableByteArray) throws ParserException {
        if (this.vorbisIdHeader == null) {
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            return null;
        }
        if (this.commentHeader == null) {
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray);
            return null;
        }
        byte[] bArr = new byte[parsableByteArray.limit()];
        System.arraycopy(parsableByteArray.data, 0, bArr, 0, parsableByteArray.limit());
        return new VorbisSetup(this.vorbisIdHeader, this.commentHeader, bArr, VorbisUtil.readVorbisModes(parsableByteArray, this.vorbisIdHeader.channels), VorbisUtil.iLog(r5.length - 1));
    }

    @Override // com.google.android.exoplayer2.extractor.ogg.StreamReader
    public void reset(boolean z8) {
        super.reset(z8);
        if (z8) {
            this.vorbisSetup = null;
            this.vorbisIdHeader = null;
            this.commentHeader = null;
        }
        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }
}
