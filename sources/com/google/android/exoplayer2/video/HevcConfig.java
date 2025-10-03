package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HevcConfig {
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;

    private HevcConfig(List<byte[]> list, int i9) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i9;
    }

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        try {
            parsableByteArray.skipBytes(21);
            int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i9 = 0;
            for (int i10 = 0; i10 < unsignedByte2; i10++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                for (int i11 = 0; i11 < unsignedShort; i11++) {
                    int unsignedShort2 = parsableByteArray.readUnsignedShort();
                    i9 += unsignedShort2 + 4;
                    parsableByteArray.skipBytes(unsignedShort2);
                }
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i9];
            int i12 = 0;
            for (int i13 = 0; i13 < unsignedByte2; i13++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                for (int i14 = 0; i14 < unsignedShort3; i14++) {
                    int unsignedShort4 = parsableByteArray.readUnsignedShort();
                    byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
                    System.arraycopy(bArr2, 0, bArr, i12, bArr2.length);
                    int length = i12 + bArr2.length;
                    System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                    i12 = length + unsignedShort4;
                    parsableByteArray.skipBytes(unsignedShort4);
                }
            }
            return new HevcConfig(i9 == 0 ? null : Collections.singletonList(bArr), unsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e9) {
            throw new ParserException("Error parsing HEVC config", e9);
        }
    }
}
