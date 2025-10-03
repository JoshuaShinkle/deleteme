package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class SsManifest {
    public static final int UNSET_LOOKAHEAD = -1;
    public final long durationUs;
    public final long dvrWindowLengthUs;
    public final boolean isLive;
    public final int lookAheadCount;
    public final int majorVersion;
    public final int minorVersion;
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    public static class ProtectionElement {
        public final byte[] data;
        public final UUID uuid;

        public ProtectionElement(UUID uuid, byte[] bArr) {
            this.uuid = uuid;
            this.data = bArr;
        }
    }

    public SsManifest(int i9, int i10, long j9, long j10, long j11, int i11, boolean z8, ProtectionElement protectionElement, StreamElement[] streamElementArr) {
        this(i9, i10, j10 == 0 ? -9223372036854775807L : Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, j9), j11 != 0 ? Util.scaleLargeTimestamp(j11, C3322C.MICROS_PER_SECOND, j9) : C3322C.TIME_UNSET, i11, z8, protectionElement, streamElementArr);
    }

    public final SsManifest copy(List<TrackKey> list) {
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StreamElement streamElement = null;
        int i9 = 0;
        while (i9 < linkedList.size()) {
            TrackKey trackKey = (TrackKey) linkedList.get(i9);
            StreamElement streamElement2 = this.streamElements[trackKey.streamElementIndex];
            if (streamElement2 != streamElement && streamElement != null) {
                arrayList.add(streamElement.copy((Format[]) arrayList2.toArray(new Format[0])));
                arrayList2.clear();
            }
            arrayList2.add(streamElement2.formats[trackKey.trackIndex]);
            i9++;
            streamElement = streamElement2;
        }
        if (streamElement != null) {
            arrayList.add(streamElement.copy((Format[]) arrayList2.toArray(new Format[0])));
        }
        return new SsManifest(this.majorVersion, this.minorVersion, this.durationUs, this.dvrWindowLengthUs, this.lookAheadCount, this.isLive, this.protectionElement, (StreamElement[]) arrayList.toArray(new StreamElement[0]));
    }

    public static class StreamElement {
        private static final String URL_PLACEHOLDER_BITRATE_1 = "{bitrate}";
        private static final String URL_PLACEHOLDER_BITRATE_2 = "{Bitrate}";
        private static final String URL_PLACEHOLDER_START_TIME_1 = "{start time}";
        private static final String URL_PLACEHOLDER_START_TIME_2 = "{start_time}";
        private final String baseUri;
        public final int chunkCount;
        private final List<Long> chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        public final int displayHeight;
        public final int displayWidth;
        public final Format[] formats;
        public final String language;
        private final long lastChunkDurationUs;
        public final int maxHeight;
        public final int maxWidth;
        public final String name;
        public final String subType;
        public final long timescale;
        public final int type;

        public StreamElement(String str, String str2, int i9, String str3, long j9, String str4, int i10, int i11, int i12, int i13, String str5, Format[] formatArr, List<Long> list, long j10) {
            this(str, str2, i9, str3, j9, str4, i10, i11, i12, i13, str5, formatArr, list, Util.scaleLargeTimestamps(list, C3322C.MICROS_PER_SECOND, j9), Util.scaleLargeTimestamp(j10, C3322C.MICROS_PER_SECOND, j9));
        }

        public Uri buildRequestUri(int i9, int i10) {
            Assertions.checkState(this.formats != null);
            Assertions.checkState(this.chunkStartTimes != null);
            Assertions.checkState(i10 < this.chunkStartTimes.size());
            String string = Integer.toString(this.formats[i9].bitrate);
            String string2 = this.chunkStartTimes.get(i10).toString();
            return UriUtil.resolveToUri(this.baseUri, this.chunkTemplate.replace(URL_PLACEHOLDER_BITRATE_1, string).replace(URL_PLACEHOLDER_BITRATE_2, string).replace(URL_PLACEHOLDER_START_TIME_1, string2).replace(URL_PLACEHOLDER_START_TIME_2, string2));
        }

        public StreamElement copy(Format[] formatArr) {
            return new StreamElement(this.baseUri, this.chunkTemplate, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, formatArr, this.chunkStartTimes, this.chunkStartTimesUs, this.lastChunkDurationUs);
        }

        public long getChunkDurationUs(int i9) {
            if (i9 == this.chunkCount - 1) {
                return this.lastChunkDurationUs;
            }
            long[] jArr = this.chunkStartTimesUs;
            return jArr[i9 + 1] - jArr[i9];
        }

        public int getChunkIndex(long j9) {
            return Util.binarySearchFloor(this.chunkStartTimesUs, j9, true, true);
        }

        public long getStartTimeUs(int i9) {
            return this.chunkStartTimesUs[i9];
        }

        private StreamElement(String str, String str2, int i9, String str3, long j9, String str4, int i10, int i11, int i12, int i13, String str5, Format[] formatArr, List<Long> list, long[] jArr, long j10) {
            this.baseUri = str;
            this.chunkTemplate = str2;
            this.type = i9;
            this.subType = str3;
            this.timescale = j9;
            this.name = str4;
            this.maxWidth = i10;
            this.maxHeight = i11;
            this.displayWidth = i12;
            this.displayHeight = i13;
            this.language = str5;
            this.formats = formatArr;
            this.chunkStartTimes = list;
            this.chunkStartTimesUs = jArr;
            this.lastChunkDurationUs = j10;
            this.chunkCount = list.size();
        }
    }

    private SsManifest(int i9, int i10, long j9, long j10, int i11, boolean z8, ProtectionElement protectionElement, StreamElement[] streamElementArr) {
        this.majorVersion = i9;
        this.minorVersion = i10;
        this.durationUs = j9;
        this.dvrWindowLengthUs = j10;
        this.lookAheadCount = i11;
        this.isLive = z8;
        this.protectionElement = protectionElement;
        this.streamElements = streamElementArr;
    }
}
