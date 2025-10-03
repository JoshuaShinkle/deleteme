package com.google.android.exoplayer2.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    private static final int SEARCH_LENGTH = 4096;

    private Sniffer() {
    }

    private static boolean isCompatibleBrand(int i9) {
        if ((i9 >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i10 : COMPATIBLE_BRANDS) {
            if (i10 == i9) {
                return true;
            }
        }
        return false;
    }

    public static boolean sniffFragmented(ExtractorInput extractorInput) {
        return sniffInternal(extractorInput, true);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z8) {
        boolean z9;
        boolean z10;
        int i9;
        long length = extractorInput.getLength();
        long j9 = -1;
        if (length == -1 || length > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            length = 4096;
        }
        int i10 = (int) length;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i11 = 0;
        boolean z11 = false;
        while (i11 < i10) {
            parsableByteArray.reset(8);
            extractorInput.peekFully(parsableByteArray.data, 0, 8);
            long unsignedInt = parsableByteArray.readUnsignedInt();
            int i12 = parsableByteArray.readInt();
            if (unsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                i9 = 16;
                unsignedInt = parsableByteArray.readUnsignedLongToLong();
            } else {
                if (unsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j9) {
                        unsignedInt = 8 + (length2 - extractorInput.getPosition());
                    }
                }
                i9 = 8;
            }
            long j10 = i9;
            if (unsignedInt < j10) {
                return false;
            }
            i11 += i9;
            if (i12 != Atom.TYPE_moov) {
                if (i12 == Atom.TYPE_moof || i12 == Atom.TYPE_mvex) {
                    z9 = true;
                    z10 = true;
                    break;
                }
                if ((i11 + unsignedInt) - j10 >= i10) {
                    break;
                }
                int i13 = (int) (unsignedInt - j10);
                i11 += i13;
                if (i12 == Atom.TYPE_ftyp) {
                    if (i13 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(i13);
                    extractorInput.peekFully(parsableByteArray.data, 0, i13);
                    int i14 = i13 / 4;
                    int i15 = 0;
                    while (true) {
                        if (i15 >= i14) {
                            break;
                        }
                        if (i15 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                            z11 = true;
                            break;
                        }
                        i15++;
                    }
                    if (!z11) {
                        return false;
                    }
                } else if (i13 != 0) {
                    extractorInput.advancePeekPosition(i13);
                }
                j9 = -1;
            }
        }
        z9 = true;
        z10 = false;
        if (z11 && z8 == z10) {
            return z9;
        }
        return false;
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) {
        return sniffInternal(extractorInput, false);
    }
}
