package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Track;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class DefaultSsChunkSource implements SsChunkSource {
    private int currentManifestChunkOffset;
    private final DataSource dataSource;
    private final ChunkExtractorWrapper[] extractorWrappers;
    private IOException fatalError;
    private SsManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int streamElementIndex;
    private final TrackSelection trackSelection;

    public static final class Factory implements SsChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource.Factory
        public SsChunkSource createChunkSource(LoaderErrorThrower loaderErrorThrower, SsManifest ssManifest, int i9, TrackSelection trackSelection, TrackEncryptionBox[] trackEncryptionBoxArr) {
            return new DefaultSsChunkSource(loaderErrorThrower, ssManifest, i9, trackSelection, this.dataSourceFactory.createDataSource(), trackEncryptionBoxArr);
        }
    }

    public DefaultSsChunkSource(LoaderErrorThrower loaderErrorThrower, SsManifest ssManifest, int i9, TrackSelection trackSelection, DataSource dataSource, TrackEncryptionBox[] trackEncryptionBoxArr) {
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = ssManifest;
        this.streamElementIndex = i9;
        this.trackSelection = trackSelection;
        this.dataSource = dataSource;
        SsManifest.StreamElement streamElement = ssManifest.streamElements[i9];
        this.extractorWrappers = new ChunkExtractorWrapper[trackSelection.length()];
        int i10 = 0;
        while (i10 < this.extractorWrappers.length) {
            int indexInTrackGroup = trackSelection.getIndexInTrackGroup(i10);
            Format format = streamElement.formats[indexInTrackGroup];
            int i11 = streamElement.type;
            int i12 = i10;
            this.extractorWrappers[i12] = new ChunkExtractorWrapper(new FragmentedMp4Extractor(3, null, new Track(indexInTrackGroup, i11, streamElement.timescale, C3322C.TIME_UNSET, ssManifest.durationUs, format, 0, trackEncryptionBoxArr, i11 == 2 ? 4 : 0, null, null), null), streamElement.type, format);
            i10 = i12 + 1;
        }
    }

    private static MediaChunk newMediaChunk(Format format, DataSource dataSource, Uri uri, String str, int i9, long j9, long j10, int i10, Object obj, ChunkExtractorWrapper chunkExtractorWrapper) {
        return new ContainerMediaChunk(dataSource, new DataSpec(uri, 0L, -1L, str), format, i10, obj, j9, j10, i9, 1, j9, chunkExtractorWrapper);
    }

    private long resolveTimeToLiveEdgeUs(long j9) {
        SsManifest ssManifest = this.manifest;
        if (!ssManifest.isLive) {
            return C3322C.TIME_UNSET;
        }
        SsManifest.StreamElement streamElement = ssManifest.streamElements[this.streamElementIndex];
        int i9 = streamElement.chunkCount - 1;
        return (streamElement.getStartTimeUs(i9) + streamElement.getChunkDurationUs(i9)) - j9;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        SsManifest.StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
        int chunkIndex = streamElement.getChunkIndex(j9);
        long startTimeUs = streamElement.getStartTimeUs(chunkIndex);
        return Util.resolveSeekPositionUs(j9, seekParameters, startTimeUs, (startTimeUs >= j9 || chunkIndex >= streamElement.chunkCount + (-1)) ? startTimeUs : streamElement.getStartTimeUs(chunkIndex + 1));
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public final void getNextChunk(MediaChunk mediaChunk, long j9, long j10, ChunkHolder chunkHolder) {
        int nextChunkIndex;
        if (this.fatalError != null) {
            return;
        }
        SsManifest.StreamElement streamElement = this.manifest.streamElements[this.streamElementIndex];
        if (streamElement.chunkCount == 0) {
            chunkHolder.endOfStream = !r1.isLive;
            return;
        }
        if (mediaChunk == null) {
            nextChunkIndex = streamElement.getChunkIndex(j10);
        } else {
            nextChunkIndex = (int) (mediaChunk.getNextChunkIndex() - this.currentManifestChunkOffset);
            if (nextChunkIndex < 0) {
                this.fatalError = new BehindLiveWindowException();
                return;
            }
        }
        int i9 = nextChunkIndex;
        if (i9 >= streamElement.chunkCount) {
            chunkHolder.endOfStream = !this.manifest.isLive;
            return;
        }
        this.trackSelection.updateSelectedTrack(j9, j10 - j9, resolveTimeToLiveEdgeUs(j9));
        long startTimeUs = streamElement.getStartTimeUs(i9);
        long chunkDurationUs = startTimeUs + streamElement.getChunkDurationUs(i9);
        int i10 = i9 + this.currentManifestChunkOffset;
        int selectedIndex = this.trackSelection.getSelectedIndex();
        chunkHolder.chunk = newMediaChunk(this.trackSelection.getSelectedFormat(), this.dataSource, streamElement.buildRequestUri(this.trackSelection.getIndexInTrackGroup(selectedIndex), i9), null, i10, startTimeUs, chunkDurationUs, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.extractorWrappers[selectedIndex]);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public int getPreferredQueueSize(long j9, List<? extends MediaChunk> list) {
        return (this.fatalError != null || this.trackSelection.length() < 2) ? list.size() : this.trackSelection.evaluateQueueSize(j9, list);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean z8, Exception exc) {
        if (z8) {
            TrackSelection trackSelection = this.trackSelection;
            if (ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection, trackSelection.indexOf(chunk.trackFormat), exc)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource
    public void updateManifest(SsManifest ssManifest) {
        SsManifest.StreamElement[] streamElementArr = this.manifest.streamElements;
        int i9 = this.streamElementIndex;
        SsManifest.StreamElement streamElement = streamElementArr[i9];
        int i10 = streamElement.chunkCount;
        SsManifest.StreamElement streamElement2 = ssManifest.streamElements[i9];
        if (i10 == 0 || streamElement2.chunkCount == 0) {
            this.currentManifestChunkOffset += i10;
        } else {
            int i11 = i10 - 1;
            long startTimeUs = streamElement.getStartTimeUs(i11) + streamElement.getChunkDurationUs(i11);
            long startTimeUs2 = streamElement2.getStartTimeUs(0);
            if (startTimeUs <= startTimeUs2) {
                this.currentManifestChunkOffset += i10;
            } else {
                this.currentManifestChunkOffset += streamElement.getChunkIndex(startTimeUs2);
            }
        }
        this.manifest = ssManifest;
    }
}
