package com.google.android.exoplayer2.text.pgs;

import android.graphics.Bitmap;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes.dex */
public final class PgsDecoder extends SimpleSubtitleDecoder {
    private static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    private static final int SECTION_TYPE_END = 128;
    private static final int SECTION_TYPE_IDENTIFIER = 22;
    private static final int SECTION_TYPE_PALETTE = 20;
    private final ParsableByteArray buffer;
    private final CueBuilder cueBuilder;

    public static final class CueBuilder {
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;
        private final ParsableByteArray bitmapData = new ParsableByteArray();
        private final int[] colors = new int[256];

        /* JADX INFO: Access modifiers changed from: private */
        public void parseBitmapSection(ParsableByteArray parsableByteArray, int i9) {
            int unsignedInt24;
            if (i9 < 4) {
                return;
            }
            parsableByteArray.skipBytes(3);
            int i10 = i9 - 4;
            if ((parsableByteArray.readUnsignedByte() & PgsDecoder.SECTION_TYPE_END) != 0) {
                if (i10 < 7 || (unsignedInt24 = parsableByteArray.readUnsignedInt24()) < 4) {
                    return;
                }
                this.bitmapWidth = parsableByteArray.readUnsignedShort();
                this.bitmapHeight = parsableByteArray.readUnsignedShort();
                this.bitmapData.reset(unsignedInt24 - 4);
                i10 -= 7;
            }
            int position = this.bitmapData.getPosition();
            int iLimit = this.bitmapData.limit();
            if (position >= iLimit || i10 <= 0) {
                return;
            }
            int iMin = Math.min(i10, iLimit - position);
            parsableByteArray.readBytes(this.bitmapData.data, position, iMin);
            this.bitmapData.setPosition(position + iMin);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void parseIdentifierSection(ParsableByteArray parsableByteArray, int i9) {
            if (i9 < 19) {
                return;
            }
            this.planeWidth = parsableByteArray.readUnsignedShort();
            this.planeHeight = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(11);
            this.bitmapX = parsableByteArray.readUnsignedShort();
            this.bitmapY = parsableByteArray.readUnsignedShort();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void parsePaletteSection(ParsableByteArray parsableByteArray, int i9) {
            if (i9 % 5 != 2) {
                return;
            }
            parsableByteArray.skipBytes(2);
            Arrays.fill(this.colors, 0);
            int i10 = i9 / 5;
            for (int i11 = 0; i11 < i10; i11++) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                int unsignedByte4 = parsableByteArray.readUnsignedByte();
                double d9 = unsignedByte2;
                double d10 = unsignedByte3 - 128;
                double d11 = unsignedByte4 - 128;
                this.colors[unsignedByte] = (Util.constrainValue((int) ((d9 - (0.34414d * d11)) - (d10 * 0.71414d)), 0, 255) << 8) | (parsableByteArray.readUnsignedByte() << 24) | (Util.constrainValue((int) ((1.402d * d10) + d9), 0, 255) << 16) | Util.constrainValue((int) (d9 + (d11 * 1.772d)), 0, 255);
            }
            this.colorsSet = true;
        }

        public Cue build() {
            int unsignedByte;
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || this.bitmapData.limit() == 0 || this.bitmapData.getPosition() != this.bitmapData.limit() || !this.colorsSet) {
                return null;
            }
            this.bitmapData.setPosition(0);
            int i9 = this.bitmapWidth * this.bitmapHeight;
            int[] iArr = new int[i9];
            int i10 = 0;
            while (i10 < i9) {
                int unsignedByte2 = this.bitmapData.readUnsignedByte();
                if (unsignedByte2 != 0) {
                    unsignedByte = i10 + 1;
                    iArr[i10] = this.colors[unsignedByte2];
                } else {
                    int unsignedByte3 = this.bitmapData.readUnsignedByte();
                    if (unsignedByte3 != 0) {
                        unsignedByte = ((unsignedByte3 & 64) == 0 ? unsignedByte3 & 63 : ((unsignedByte3 & 63) << 8) | this.bitmapData.readUnsignedByte()) + i10;
                        Arrays.fill(iArr, i10, unsignedByte, (unsignedByte3 & PgsDecoder.SECTION_TYPE_END) == 0 ? 0 : this.colors[this.bitmapData.readUnsignedByte()]);
                    }
                }
                i10 = unsignedByte;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888);
            float f9 = this.bitmapX;
            int i11 = this.planeWidth;
            float f10 = f9 / i11;
            float f11 = this.bitmapY;
            int i12 = this.planeHeight;
            return new Cue(bitmapCreateBitmap, f10, 0, f11 / i12, 0, this.bitmapWidth / i11, this.bitmapHeight / i12);
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    public PgsDecoder() {
        super("PgsDecoder");
        this.buffer = new ParsableByteArray();
        this.cueBuilder = new CueBuilder();
    }

    private static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder) {
        int iLimit = parsableByteArray.limit();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition() + unsignedShort;
        Cue cueBuild = null;
        if (position > iLimit) {
            parsableByteArray.setPosition(iLimit);
            return null;
        }
        if (unsignedByte != SECTION_TYPE_END) {
            switch (unsignedByte) {
                case 20:
                    cueBuilder.parsePaletteSection(parsableByteArray, unsignedShort);
                    break;
                case 21:
                    cueBuilder.parseBitmapSection(parsableByteArray, unsignedShort);
                    break;
                case 22:
                    cueBuilder.parseIdentifierSection(parsableByteArray, unsignedShort);
                    break;
            }
        } else {
            cueBuild = cueBuilder.build();
            cueBuilder.reset();
        }
        parsableByteArray.setPosition(position);
        return cueBuild;
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Subtitle decode(byte[] bArr, int i9, boolean z8) {
        this.buffer.reset(bArr, i9);
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue nextSection = readNextSection(this.buffer, this.cueBuilder);
            if (nextSection != null) {
                arrayList.add(nextSection);
            }
        }
        return new PgsSubtitle(Collections.unmodifiableList(arrayList));
    }
}
