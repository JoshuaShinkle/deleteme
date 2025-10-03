package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class Track {
    public static final int TRANSFORMATION_CEA608_CDAT = 1;
    public static final int TRANSFORMATION_NONE = 0;
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final Format format;

    /* renamed from: id */
    public final int f15304id;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    private final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final int sampleTransformation;
    public final long timescale;
    public final int type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Transformation {
    }

    public Track(int i9, int i10, long j9, long j10, long j11, Format format, int i11, TrackEncryptionBox[] trackEncryptionBoxArr, int i12, long[] jArr, long[] jArr2) {
        this.f15304id = i9;
        this.type = i10;
        this.timescale = j9;
        this.movieTimescale = j10;
        this.durationUs = j11;
        this.format = format;
        this.sampleTransformation = i11;
        this.sampleDescriptionEncryptionBoxes = trackEncryptionBoxArr;
        this.nalUnitLengthFieldLength = i12;
        this.editListDurations = jArr;
        this.editListMediaTimes = jArr2;
    }

    public TrackEncryptionBox getSampleDescriptionEncryptionBox(int i9) {
        TrackEncryptionBox[] trackEncryptionBoxArr = this.sampleDescriptionEncryptionBoxes;
        if (trackEncryptionBoxArr == null) {
            return null;
        }
        return trackEncryptionBoxArr[i9];
    }
}
