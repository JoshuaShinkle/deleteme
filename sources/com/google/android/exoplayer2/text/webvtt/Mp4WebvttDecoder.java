package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.webvtt.WebvttCue;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int TYPE_payl = Util.getIntegerCodeForString("payl");
    private static final int TYPE_sttg = Util.getIntegerCodeForString("sttg");
    private static final int TYPE_vttc = Util.getIntegerCodeForString("vttc");
    private final WebvttCue.Builder builder;
    private final ParsableByteArray sampleData;

    public Mp4WebvttDecoder() {
        super("Mp4WebvttDecoder");
        this.sampleData = new ParsableByteArray();
        this.builder = new WebvttCue.Builder();
    }

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray, WebvttCue.Builder builder, int i9) throws SubtitleDecoderException {
        builder.reset();
        while (i9 > 0) {
            if (i9 < 8) {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
            int i10 = parsableByteArray.readInt();
            int i11 = parsableByteArray.readInt();
            int i12 = i10 - 8;
            String str = new String(parsableByteArray.data, parsableByteArray.getPosition(), i12);
            parsableByteArray.skipBytes(i12);
            i9 = (i9 - 8) - i12;
            if (i11 == TYPE_sttg) {
                WebvttCueParser.parseCueSettingsList(str, builder);
            } else if (i11 == TYPE_payl) {
                WebvttCueParser.parseCueText(null, str.trim(), builder, Collections.emptyList());
            }
        }
        return builder.build();
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public Mp4WebvttSubtitle decode(byte[] bArr, int i9, boolean z8) throws SubtitleDecoderException {
        this.sampleData.reset(bArr, i9);
        ArrayList arrayList = new ArrayList();
        while (this.sampleData.bytesLeft() > 0) {
            if (this.sampleData.bytesLeft() < 8) {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
            int i10 = this.sampleData.readInt();
            if (this.sampleData.readInt() == TYPE_vttc) {
                arrayList.add(parseVttCueBox(this.sampleData, this.builder, i10 - 8));
            } else {
                this.sampleData.skipBytes(i10 - 8);
            }
        }
        return new Mp4WebvttSubtitle(arrayList);
    }
}
