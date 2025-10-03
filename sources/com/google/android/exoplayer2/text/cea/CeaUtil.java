package com.google.android.exoplayer2.text.cea;

import android.util.Log;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public final class CeaUtil {
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE_ATSC = 49;
    private static final int PROVIDER_CODE_DIRECTV = 47;
    private static final String TAG = "CeaUtil";
    private static final int USER_DATA_TYPE_CODE = 3;
    private static final int USER_ID_GA94 = Util.getIntegerCodeForString("GA94");
    private static final int USER_ID_DTG1 = Util.getIntegerCodeForString("DTG1");

    private CeaUtil() {
    }

    public static void consume(long j9, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        while (parsableByteArray.bytesLeft() > 1) {
            int non255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
            int non255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
            int position = parsableByteArray.getPosition() + non255TerminatedValue2;
            if (non255TerminatedValue2 == -1 || non255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                Log.w(TAG, "Skipping remainder of malformed SEI NAL unit.");
                position = parsableByteArray.limit();
            } else if (non255TerminatedValue == 4 && non255TerminatedValue2 >= 8) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedShort = parsableByteArray.readUnsignedShort();
                int i9 = unsignedShort == 49 ? parsableByteArray.readInt() : 0;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                if (unsignedShort == 47) {
                    parsableByteArray.skipBytes(1);
                }
                boolean z8 = unsignedByte == COUNTRY_CODE && (unsignedShort == 49 || unsignedShort == 47) && unsignedByte2 == 3;
                if (unsignedShort == 49) {
                    z8 &= i9 == USER_ID_GA94 || i9 == USER_ID_DTG1;
                }
                if (z8) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte() & 31;
                    parsableByteArray.skipBytes(1);
                    int i10 = unsignedByte3 * 3;
                    int position2 = parsableByteArray.getPosition();
                    for (TrackOutput trackOutput : trackOutputArr) {
                        parsableByteArray.setPosition(position2);
                        trackOutput.sampleData(parsableByteArray, i10);
                        trackOutput.sampleMetadata(j9, 1, i10, 0, null);
                    }
                }
            }
            parsableByteArray.setPosition(position);
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i9 = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            i9 += unsignedByte;
            if (unsignedByte != 255) {
                return i9;
            }
        }
        return -1;
    }
}
