package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class AvcConfig {
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthAspectRatio;
    public final int width;

    private AvcConfig(List<byte[]> list, int i9, int i10, int i11, float f9) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i9;
        this.width = i10;
        this.height = i11;
        this.pixelWidthAspectRatio = f9;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray parsableByteArray) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(unsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.data, position, unsignedShort);
    }

    public static AvcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        int i9;
        int i10;
        float f9;
        try {
            parsableByteArray.skipBytes(4);
            int unsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
            if (unsignedByte == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList = new ArrayList();
            int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
            for (int i11 = 0; i11 < unsignedByte2; i11++) {
                arrayList.add(buildNalUnitForChild(parsableByteArray));
            }
            int unsignedByte3 = parsableByteArray.readUnsignedByte();
            for (int i12 = 0; i12 < unsignedByte3; i12++) {
                arrayList.add(buildNalUnitForChild(parsableByteArray));
            }
            if (unsignedByte2 > 0) {
                NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit((byte[]) arrayList.get(0), unsignedByte, ((byte[]) arrayList.get(0)).length);
                int i13 = spsNalUnit.width;
                int i14 = spsNalUnit.height;
                f9 = spsNalUnit.pixelWidthAspectRatio;
                i9 = i13;
                i10 = i14;
            } else {
                i9 = -1;
                i10 = -1;
                f9 = 1.0f;
            }
            return new AvcConfig(arrayList, unsignedByte, i9, i10, f9);
        } catch (ArrayIndexOutOfBoundsException e9) {
            throw new ParserException("Error parsing AVC config", e9);
        }
    }
}
