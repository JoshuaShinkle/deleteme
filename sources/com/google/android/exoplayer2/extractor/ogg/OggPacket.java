package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

/* loaded from: classes.dex */
final class OggPacket {
    private boolean populated;
    private int segmentCount;
    private final OggPageHeader pageHeader = new OggPageHeader();
    private final ParsableByteArray packetArray = new ParsableByteArray(new byte[OggPageHeader.MAX_PAGE_PAYLOAD], 0);
    private int currentSegmentIndex = -1;

    private int calculatePacketSize(int i9) {
        int i10;
        int i11 = 0;
        this.segmentCount = 0;
        do {
            int i12 = this.segmentCount;
            int i13 = i9 + i12;
            OggPageHeader oggPageHeader = this.pageHeader;
            if (i13 >= oggPageHeader.pageSegmentCount) {
                break;
            }
            int[] iArr = oggPageHeader.laces;
            this.segmentCount = i12 + 1;
            i10 = iArr[i12 + i9];
            i11 += i10;
        } while (i10 == 255);
        return i11;
    }

    public OggPageHeader getPageHeader() {
        return this.pageHeader;
    }

    public ParsableByteArray getPayload() {
        return this.packetArray;
    }

    public boolean populate(ExtractorInput extractorInput) {
        int i9;
        Assertions.checkState(extractorInput != null);
        if (this.populated) {
            this.populated = false;
            this.packetArray.reset();
        }
        while (!this.populated) {
            if (this.currentSegmentIndex < 0) {
                if (!this.pageHeader.populate(extractorInput, true)) {
                    return false;
                }
                OggPageHeader oggPageHeader = this.pageHeader;
                int iCalculatePacketSize = oggPageHeader.headerSize;
                if ((oggPageHeader.type & 1) == 1 && this.packetArray.limit() == 0) {
                    iCalculatePacketSize += calculatePacketSize(0);
                    i9 = this.segmentCount + 0;
                } else {
                    i9 = 0;
                }
                extractorInput.skipFully(iCalculatePacketSize);
                this.currentSegmentIndex = i9;
            }
            int iCalculatePacketSize2 = calculatePacketSize(this.currentSegmentIndex);
            int i10 = this.currentSegmentIndex + this.segmentCount;
            if (iCalculatePacketSize2 > 0) {
                if (this.packetArray.capacity() < this.packetArray.limit() + iCalculatePacketSize2) {
                    ParsableByteArray parsableByteArray = this.packetArray;
                    parsableByteArray.data = Arrays.copyOf(parsableByteArray.data, parsableByteArray.limit() + iCalculatePacketSize2);
                }
                ParsableByteArray parsableByteArray2 = this.packetArray;
                extractorInput.readFully(parsableByteArray2.data, parsableByteArray2.limit(), iCalculatePacketSize2);
                ParsableByteArray parsableByteArray3 = this.packetArray;
                parsableByteArray3.setLimit(parsableByteArray3.limit() + iCalculatePacketSize2);
                this.populated = this.pageHeader.laces[i10 + (-1)] != 255;
            }
            if (i10 == this.pageHeader.pageSegmentCount) {
                i10 = -1;
            }
            this.currentSegmentIndex = i10;
        }
        return true;
    }

    public void reset() {
        this.pageHeader.reset();
        this.packetArray.reset();
        this.currentSegmentIndex = -1;
        this.populated = false;
    }

    public void trimPayload() {
        ParsableByteArray parsableByteArray = this.packetArray;
        byte[] bArr = parsableByteArray.data;
        if (bArr.length == 65025) {
            return;
        }
        parsableByteArray.data = Arrays.copyOf(bArr, Math.max(OggPageHeader.MAX_PAGE_PAYLOAD, parsableByteArray.limit()));
    }
}
