package com.google.android.exoplayer2.source.smoothstreaming;

import android.util.Base64;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class SsMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<SsChunkSource>> {
    private static final int INITIALIZATION_VECTOR_SIZE = 8;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final SsChunkSource.Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private SsManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private ChunkSampleStream<SsChunkSource>[] sampleStreams;
    private final TrackEncryptionBox[] trackEncryptionBoxes;
    private final TrackGroupArray trackGroups;

    public SsMediaPeriod(SsManifest ssManifest, SsChunkSource.Factory factory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, int i9, MediaSourceEventListener.EventDispatcher eventDispatcher, LoaderErrorThrower loaderErrorThrower, Allocator allocator) {
        this.chunkSourceFactory = factory;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.minLoadableRetryCount = i9;
        this.eventDispatcher = eventDispatcher;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.trackGroups = buildTrackGroups(ssManifest);
        SsManifest.ProtectionElement protectionElement = ssManifest.protectionElement;
        if (protectionElement != null) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[]{new TrackEncryptionBox(true, null, 8, getProtectionElementKeyId(protectionElement.data), 0, 0, null)};
        } else {
            this.trackEncryptionBoxes = null;
        }
        this.manifest = ssManifest;
        ChunkSampleStream<SsChunkSource>[] chunkSampleStreamArrNewSampleStreamArray = newSampleStreamArray(0);
        this.sampleStreams = chunkSampleStreamArrNewSampleStreamArray;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(chunkSampleStreamArrNewSampleStreamArray);
    }

    private ChunkSampleStream<SsChunkSource> buildSampleStream(TrackSelection trackSelection, long j9) {
        int iIndexOf = this.trackGroups.indexOf(trackSelection.getTrackGroup());
        return new ChunkSampleStream<>(this.manifest.streamElements[iIndexOf].type, null, null, this.chunkSourceFactory.createChunkSource(this.manifestLoaderErrorThrower, this.manifest, iIndexOf, trackSelection, this.trackEncryptionBoxes), this, this.allocator, j9, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private static TrackGroupArray buildTrackGroups(SsManifest ssManifest) {
        TrackGroup[] trackGroupArr = new TrackGroup[ssManifest.streamElements.length];
        int i9 = 0;
        while (true) {
            SsManifest.StreamElement[] streamElementArr = ssManifest.streamElements;
            if (i9 >= streamElementArr.length) {
                return new TrackGroupArray(trackGroupArr);
            }
            trackGroupArr[i9] = new TrackGroup(streamElementArr[i9].formats);
            i9++;
        }
    }

    private static byte[] getProtectionElementKeyId(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < bArr.length; i9 += 2) {
            sb.append((char) bArr[i9]);
        }
        String string = sb.toString();
        byte[] bArrDecode = Base64.decode(string.substring(string.indexOf("<KID>") + 5, string.indexOf("</KID>")), 0);
        swap(bArrDecode, 0, 3);
        swap(bArrDecode, 1, 2);
        swap(bArrDecode, 4, 5);
        swap(bArrDecode, 6, 7);
        return bArrDecode;
    }

    private static ChunkSampleStream<SsChunkSource>[] newSampleStreamArray(int i9) {
        return new ChunkSampleStream[i9];
    }

    private static void swap(byte[] bArr, int i9, int i10) {
        byte b9 = bArr[i9];
        bArr[i9] = bArr[i10];
        bArr[i10] = b9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        return this.compositeSequenceableLoader.continueLoading(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        for (ChunkSampleStream<SsChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.discardBuffer(j9, z8);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        for (ChunkSampleStream<SsChunkSource> chunkSampleStream : this.sampleStreams) {
            if (chunkSampleStream.primaryTrackType == 2) {
                return chunkSampleStream.getAdjustedSeekPositionUs(j9, seekParameters);
            }
        }
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        this.compositeSequenceableLoader.reevaluateBuffer(j9);
    }

    public void release() {
        for (ChunkSampleStream<SsChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.release();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        for (ChunkSampleStream<SsChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.seekToUs(j9);
        }
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        TrackSelection trackSelection;
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < trackSelectionArr.length; i9++) {
            SampleStream sampleStream = sampleStreamArr[i9];
            if (sampleStream != null) {
                ChunkSampleStream chunkSampleStream = (ChunkSampleStream) sampleStream;
                if (trackSelectionArr[i9] == null || !zArr[i9]) {
                    chunkSampleStream.release();
                    sampleStreamArr[i9] = null;
                } else {
                    arrayList.add(chunkSampleStream);
                }
            }
            if (sampleStreamArr[i9] == null && (trackSelection = trackSelectionArr[i9]) != null) {
                ChunkSampleStream<SsChunkSource> chunkSampleStreamBuildSampleStream = buildSampleStream(trackSelection, j9);
                arrayList.add(chunkSampleStreamBuildSampleStream);
                sampleStreamArr[i9] = chunkSampleStreamBuildSampleStream;
                zArr2[i9] = true;
            }
        }
        ChunkSampleStream<SsChunkSource>[] chunkSampleStreamArrNewSampleStreamArray = newSampleStreamArray(arrayList.size());
        this.sampleStreams = chunkSampleStreamArrNewSampleStreamArray;
        arrayList.toArray(chunkSampleStreamArrNewSampleStreamArray);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
        return j9;
    }

    public void updateManifest(SsManifest ssManifest) {
        this.manifest = ssManifest;
        for (ChunkSampleStream<SsChunkSource> chunkSampleStream : this.sampleStreams) {
            ((SsChunkSource) chunkSampleStream.getChunkSource()).updateManifest(ssManifest);
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(ChunkSampleStream<SsChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }
}
