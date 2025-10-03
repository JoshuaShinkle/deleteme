package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.drm.DrmInitData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HlsMediaPlaylist extends HlsPlaylist {
    public static final int PLAYLIST_TYPE_EVENT = 2;
    public static final int PLAYLIST_TYPE_UNKNOWN = 0;
    public static final int PLAYLIST_TYPE_VOD = 1;
    public final int discontinuitySequence;
    public final DrmInitData drmInitData;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasIndependentSegmentsTag;
    public final boolean hasProgramDateTime;
    public final Segment initializationSegment;
    public final long mediaSequence;
    public final int playlistType;
    public final List<Segment> segments;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final int version;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaylistType {
    }

    public static final class Segment implements Comparable<Long> {
        public final long byterangeLength;
        public final long byterangeOffset;
        public final long durationUs;
        public final String encryptionIV;
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String url;

        public Segment(String str, long j9, long j10) {
            this(str, 0L, -1, C3322C.TIME_UNSET, null, null, j9, j10, false);
        }

        public Segment(String str, long j9, int i9, long j10, String str2, String str3, long j11, long j12, boolean z8) {
            this.url = str;
            this.durationUs = j9;
            this.relativeDiscontinuitySequence = i9;
            this.relativeStartTimeUs = j10;
            this.fullSegmentEncryptionKeyUri = str2;
            this.encryptionIV = str3;
            this.byterangeOffset = j11;
            this.byterangeLength = j12;
            this.hasGapTag = z8;
        }

        @Override // java.lang.Comparable
        public int compareTo(Long l9) {
            if (this.relativeStartTimeUs > l9.longValue()) {
                return 1;
            }
            return this.relativeStartTimeUs < l9.longValue() ? -1 : 0;
        }
    }

    public HlsMediaPlaylist(int i9, String str, List<String> list, long j9, long j10, boolean z8, int i10, long j11, int i11, long j12, boolean z9, boolean z10, boolean z11, DrmInitData drmInitData, Segment segment, List<Segment> list2) {
        super(str, list);
        this.playlistType = i9;
        this.startTimeUs = j10;
        this.hasDiscontinuitySequence = z8;
        this.discontinuitySequence = i10;
        this.mediaSequence = j11;
        this.version = i11;
        this.targetDurationUs = j12;
        this.hasIndependentSegmentsTag = z9;
        this.hasEndTag = z10;
        this.hasProgramDateTime = z11;
        this.drmInitData = drmInitData;
        this.initializationSegment = segment;
        this.segments = Collections.unmodifiableList(list2);
        if (list2.isEmpty()) {
            this.durationUs = 0L;
        } else {
            Segment segment2 = list2.get(list2.size() - 1);
            this.durationUs = segment2.relativeStartTimeUs + segment2.durationUs;
        }
        this.startOffsetUs = j9 == C3322C.TIME_UNSET ? -9223372036854775807L : j9 >= 0 ? j9 : this.durationUs + j9;
    }

    public HlsMediaPlaylist copyWith(long j9, int i9) {
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, j9, true, i9, this.mediaSequence, this.version, this.targetDurationUs, this.hasIndependentSegmentsTag, this.hasEndTag, this.hasProgramDateTime, this.drmInitData, this.initializationSegment, this.segments);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        return this.hasEndTag ? this : new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.startTimeUs, this.hasDiscontinuitySequence, this.discontinuitySequence, this.mediaSequence, this.version, this.targetDurationUs, this.hasIndependentSegmentsTag, true, this.hasProgramDateTime, this.drmInitData, this.initializationSegment, this.segments);
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public boolean isNewerThan(HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsMediaPlaylist == null) {
            return true;
        }
        long j9 = this.mediaSequence;
        long j10 = hlsMediaPlaylist.mediaSequence;
        if (j9 > j10) {
            return true;
        }
        if (j9 < j10) {
            return false;
        }
        int size = this.segments.size();
        int size2 = hlsMediaPlaylist.segments.size();
        if (size <= size2) {
            return size == size2 && this.hasEndTag && !hlsMediaPlaylist.hasEndTag;
        }
        return true;
    }
}
