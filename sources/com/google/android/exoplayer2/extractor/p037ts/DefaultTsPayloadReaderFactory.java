package com.google.android.exoplayer2.extractor.p037ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.p037ts.TsPayloadReader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo esInfo) {
        int i9;
        String str;
        if (isSet(32)) {
            return new SeiReader(this.closedCaptionFormats);
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> arrayList = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (unsignedByte == 134) {
                arrayList = new ArrayList<>();
                int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i10 = 0; i10 < unsignedByte2; i10++) {
                    String string = parsableByteArray.readString(3);
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    if ((unsignedByte3 & 128) != 0) {
                        i9 = unsignedByte3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        i9 = 1;
                        str = MimeTypes.APPLICATION_CEA608;
                    }
                    arrayList.add(Format.createTextSampleFormat((String) null, str, (String) null, -1, 0, string, i9, (DrmInitData) null));
                    parsableByteArray.skipBytes(2);
                }
            }
            parsableByteArray.setPosition(position);
        }
        return new SeiReader(arrayList);
    }

    private boolean isSet(int i9) {
        return (i9 & this.flags) != 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    @Override // com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory
    public TsPayloadReader createPayloadReader(int i9, TsPayloadReader.EsInfo esInfo) {
        if (i9 == 2) {
            return new PesReader(new H262Reader());
        }
        if (i9 == 3 || i9 == 4) {
            return new PesReader(new MpegAudioReader(esInfo.language));
        }
        if (i9 == 15) {
            if (isSet(2)) {
                return null;
            }
            return new PesReader(new AdtsReader(false, esInfo.language));
        }
        if (i9 == 17) {
            if (isSet(2)) {
                return null;
            }
            return new PesReader(new LatmReader(esInfo.language));
        }
        if (i9 == 21) {
            return new PesReader(new Id3Reader());
        }
        if (i9 == 27) {
            if (isSet(4)) {
                return null;
            }
            return new PesReader(new H264Reader(buildSeiReader(esInfo), isSet(1), isSet(8)));
        }
        if (i9 == 36) {
            return new PesReader(new H265Reader(buildSeiReader(esInfo)));
        }
        if (i9 == 89) {
            return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
        }
        if (i9 != 138) {
            if (i9 != 129) {
                if (i9 != 130) {
                    if (i9 == 134) {
                        if (isSet(16)) {
                            return null;
                        }
                        return new SectionReader(new SpliceInfoSectionReader());
                    }
                    if (i9 != 135) {
                        return null;
                    }
                }
            }
            return new PesReader(new Ac3Reader(esInfo.language));
        }
        return new PesReader(new DtsReader(esInfo.language));
    }

    public DefaultTsPayloadReaderFactory(int i9) {
        this(i9, Collections.emptyList());
    }

    public DefaultTsPayloadReaderFactory(int i9, List<Format> list) {
        this.flags = i9;
        if (!isSet(32) && list.isEmpty()) {
            list = Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null));
        }
        this.closedCaptionFormats = list;
    }
}
