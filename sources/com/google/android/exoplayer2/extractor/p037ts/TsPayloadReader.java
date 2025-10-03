package com.google.android.exoplayer2.extractor.p037ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public interface TsPayloadReader {

    public static final class DvbSubtitleInfo {
        public final byte[] initializationData;
        public final String language;
        public final int type;

        public DvbSubtitleInfo(String str, int i9, byte[] bArr) {
            this.language = str;
            this.type = i9;
            this.initializationData = bArr;
        }
    }

    public static final class EsInfo {
        public final byte[] descriptorBytes;
        public final List<DvbSubtitleInfo> dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        public EsInfo(int i9, String str, List<DvbSubtitleInfo> list, byte[] bArr) {
            this.streamType = i9;
            this.language = str;
            this.dvbSubtitleInfos = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
            this.descriptorBytes = bArr;
        }
    }

    public interface Factory {
        SparseArray<TsPayloadReader> createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int i9, EsInfo esInfo);
    }

    public static final class TrackIdGenerator {
        private static final int ID_UNSET = Integer.MIN_VALUE;
        private final int firstTrackId;
        private String formatId;
        private final String formatIdPrefix;
        private int trackId;
        private final int trackIdIncrement;

        public TrackIdGenerator(int i9, int i10) {
            this(Integer.MIN_VALUE, i9, i10);
        }

        private void maybeThrowUninitializedError() {
            if (this.trackId == Integer.MIN_VALUE) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }

        public void generateNewId() {
            int i9 = this.trackId;
            this.trackId = i9 == Integer.MIN_VALUE ? this.firstTrackId : i9 + this.trackIdIncrement;
            this.formatId = this.formatIdPrefix + this.trackId;
        }

        public String getFormatId() {
            maybeThrowUninitializedError();
            return this.formatId;
        }

        public int getTrackId() {
            maybeThrowUninitializedError();
            return this.trackId;
        }

        public TrackIdGenerator(int i9, int i10, int i11) {
            String str;
            if (i9 != Integer.MIN_VALUE) {
                str = i9 + "/";
            } else {
                str = "";
            }
            this.formatIdPrefix = str;
            this.firstTrackId = i10;
            this.trackIdIncrement = i11;
            this.trackId = Integer.MIN_VALUE;
        }
    }

    void consume(ParsableByteArray parsableByteArray, boolean z8);

    void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator);

    void seek();
}
