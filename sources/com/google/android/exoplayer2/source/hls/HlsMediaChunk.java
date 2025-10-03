package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
final class HlsMediaChunk extends MediaChunk {
    private static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    private int bytesLoaded;
    public final int discontinuitySequenceNumber;
    private final Extractor extractor;
    private final boolean hasGapTag;
    public final HlsMasterPlaylist.HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private boolean id3TimestampPeeked;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private final boolean isPackedAudioExtractor;
    private volatile boolean loadCanceled;
    private volatile boolean loadCompleted;
    private HlsSampleStreamWrapper output;
    private final boolean reusingExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsMasterPlaylist.HlsUrl hlsUrl, List<Format> list, int i9, Object obj, long j9, long j10, long j11, int i10, boolean z8, boolean z9, TimestampAdjuster timestampAdjuster, HlsMediaChunk hlsMediaChunk, DrmInitData drmInitData, byte[] bArr, byte[] bArr2) {
        DataSpec dataSpec3;
        Extractor extractor;
        ParsableByteArray parsableByteArray;
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, hlsUrl.format, i9, obj, j9, j10, j11);
        this.discontinuitySequenceNumber = i10;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl;
        this.isMasterTimestampSource = z9;
        this.timestampAdjuster = timestampAdjuster;
        this.isEncrypted = this.dataSource instanceof Aes128DataSource;
        this.hasGapTag = z8;
        if (hlsMediaChunk != null) {
            boolean z10 = hlsMediaChunk.hlsUrl != hlsUrl;
            this.shouldSpliceIn = z10;
            extractor = (hlsMediaChunk.discontinuitySequenceNumber != i10 || z10) ? null : hlsMediaChunk.extractor;
            dataSpec3 = dataSpec;
        } else {
            this.shouldSpliceIn = false;
            dataSpec3 = dataSpec;
            extractor = null;
        }
        Pair<Extractor, Boolean> pairCreateExtractor = hlsExtractorFactory.createExtractor(extractor, dataSpec3.uri, this.trackFormat, list, drmInitData, timestampAdjuster);
        Extractor extractor2 = (Extractor) pairCreateExtractor.first;
        this.extractor = extractor2;
        boolean zBooleanValue = ((Boolean) pairCreateExtractor.second).booleanValue();
        this.isPackedAudioExtractor = zBooleanValue;
        boolean z11 = extractor2 == extractor;
        this.reusingExtractor = z11;
        this.initLoadCompleted = z11 && dataSpec2 != null;
        if (!zBooleanValue) {
            this.id3Decoder = null;
            this.id3Data = null;
        } else if (hlsMediaChunk == null || (parsableByteArray = hlsMediaChunk.id3Data) == null) {
            this.id3Decoder = new Id3Decoder();
            this.id3Data = new ParsableByteArray(10);
        } else {
            this.id3Decoder = hlsMediaChunk.id3Decoder;
            this.id3Data = parsableByteArray;
        }
        this.initDataSource = dataSource;
        this.uid = uidSource.getAndIncrement();
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f A[Catch: all -> 0x00a0, TryCatch #1 {all -> 0x00a0, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x004e, B:21:0x005f, B:23:0x0068, B:22:0x0066, B:25:0x006d, B:34:0x008e, B:32:0x0081, B:33:0x008d, B:27:0x0074, B:29:0x0078), top: B:42:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0066 A[Catch: all -> 0x00a0, TryCatch #1 {all -> 0x00a0, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x004e, B:21:0x005f, B:23:0x0068, B:22:0x0066, B:25:0x006d, B:34:0x008e, B:32:0x0081, B:33:0x008d, B:27:0x0074, B:29:0x0078), top: B:42:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d A[Catch: all -> 0x00a0, TRY_LEAVE, TryCatch #1 {all -> 0x00a0, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x004e, B:21:0x005f, B:23:0x0068, B:22:0x0066, B:25:0x006d, B:34:0x008e, B:32:0x0081, B:33:0x008d, B:27:0x0074, B:29:0x0078), top: B:42:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void loadMedia() {
        DataSpec dataSpecSubrange;
        boolean z8;
        DefaultExtractorInput defaultExtractorInput;
        int i9 = 0;
        try {
            if (this.isEncrypted) {
                dataSpecSubrange = this.dataSpec;
                if (this.bytesLoaded != 0) {
                    z8 = true;
                }
                if (this.isMasterTimestampSource) {
                    this.timestampAdjuster.waitUntilInitialized();
                } else if (this.timestampAdjuster.getFirstSampleTimestampUs() == Long.MAX_VALUE) {
                    this.timestampAdjuster.setFirstSampleTimestampUs(this.startTimeUs);
                }
                DataSource dataSource = this.dataSource;
                defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpecSubrange.absoluteStreamPosition, dataSource.open(dataSpecSubrange));
                if (this.isPackedAudioExtractor && !this.id3TimestampPeeked) {
                    long jPeekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
                    this.id3TimestampPeeked = true;
                    this.output.setSampleOffsetUs(jPeekId3PrivTimestamp == C3322C.TIME_UNSET ? this.timestampAdjuster.adjustTsTimestamp(jPeekId3PrivTimestamp) : this.startTimeUs);
                }
                if (z8) {
                    defaultExtractorInput.skipFully(this.bytesLoaded);
                }
                while (i9 == 0) {
                    try {
                        if (this.loadCanceled) {
                            break;
                        } else {
                            i9 = this.extractor.read(defaultExtractorInput, null);
                        }
                    } finally {
                        this.bytesLoaded = (int) (defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition);
                    }
                }
                return;
            }
            dataSpecSubrange = this.dataSpec.subrange(this.bytesLoaded);
            DataSource dataSource2 = this.dataSource;
            defaultExtractorInput = new DefaultExtractorInput(dataSource2, dataSpecSubrange.absoluteStreamPosition, dataSource2.open(dataSpecSubrange));
            if (this.isPackedAudioExtractor) {
                long jPeekId3PrivTimestamp2 = peekId3PrivTimestamp(defaultExtractorInput);
                this.id3TimestampPeeked = true;
                this.output.setSampleOffsetUs(jPeekId3PrivTimestamp2 == C3322C.TIME_UNSET ? this.timestampAdjuster.adjustTsTimestamp(jPeekId3PrivTimestamp2) : this.startTimeUs);
            }
            if (z8) {
            }
            while (i9 == 0) {
            }
            return;
        } finally {
            Util.closeQuietly(this.dataSource);
        }
        z8 = false;
        if (this.isMasterTimestampSource) {
        }
    }

    private void maybeLoadInitData() {
        DataSpec dataSpec;
        if (this.initLoadCompleted || (dataSpec = this.initDataSpec) == null) {
            return;
        }
        DataSpec dataSpecSubrange = dataSpec.subrange(this.initSegmentBytesLoaded);
        try {
            DataSource dataSource = this.initDataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpecSubrange.absoluteStreamPosition, dataSource.open(dataSpecSubrange));
            int i9 = 0;
            while (i9 == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    } else {
                        i9 = this.extractor.read(defaultExtractorInput, null);
                    }
                } finally {
                    this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
                }
            }
            Util.closeQuietly(this.dataSource);
            this.initLoadCompleted = true;
        } catch (Throwable th) {
            Util.closeQuietly(this.dataSource);
            throw th;
        }
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) {
        Metadata metadataDecode;
        extractorInput.resetPeekPosition();
        if (!extractorInput.peekFully(this.id3Data.data, 0, 10, true)) {
            return C3322C.TIME_UNSET;
        }
        this.id3Data.reset(10);
        if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
            return C3322C.TIME_UNSET;
        }
        this.id3Data.skipBytes(3);
        int synchSafeInt = this.id3Data.readSynchSafeInt();
        int i9 = synchSafeInt + 10;
        if (i9 > this.id3Data.capacity()) {
            ParsableByteArray parsableByteArray = this.id3Data;
            byte[] bArr = parsableByteArray.data;
            parsableByteArray.reset(i9);
            System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
        }
        if (!extractorInput.peekFully(this.id3Data.data, 10, synchSafeInt, true) || (metadataDecode = this.id3Decoder.decode(this.id3Data.data, synchSafeInt)) == null) {
            return C3322C.TIME_UNSET;
        }
        int length = metadataDecode.length();
        for (int i10 = 0; i10 < length; i10++) {
            Metadata.Entry entry = metadataDecode.get(i10);
            if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                    System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                    this.id3Data.reset(8);
                    return this.id3Data.readLong() & 8589934591L;
                }
            }
        }
        return C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
        hlsSampleStreamWrapper.init(this.uid, this.shouldSpliceIn, this.reusingExtractor);
        if (this.reusingExtractor) {
            return;
        }
        this.extractor.init(hlsSampleStreamWrapper);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() {
        maybeLoadInitData();
        if (this.loadCanceled) {
            return;
        }
        if (!this.hasGapTag) {
            loadMedia();
        }
        this.loadCompleted = true;
    }
}
