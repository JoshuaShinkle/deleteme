package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.hls.HlsChunkSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
final class HlsSampleStreamWrapper implements Loader.Callback<Chunk>, Loader.ReleaseCallback, SequenceableLoader, ExtractorOutput, SampleQueue.UpstreamFormatChangedListener {
    private static final int PRIMARY_TYPE_AUDIO = 2;
    private static final int PRIMARY_TYPE_NONE = 0;
    private static final int PRIMARY_TYPE_TEXT = 1;
    private static final int PRIMARY_TYPE_VIDEO = 3;
    private static final String TAG = "HlsSampleStreamWrapper";
    private final Allocator allocator;
    private boolean audioSampleQueueMappingDone;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    private Format downstreamTrackFormat;
    private int enabledTrackGroupCount;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private boolean haveAudioVideoSampleQueues;
    private long lastSeekPositionUs;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private final Format muxedAudioFormat;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primaryTrackGroupIndex;
    private boolean released;
    private long sampleOffsetUs;
    private boolean sampleQueuesBuilt;
    private boolean seenFirstTrackSelection;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private boolean videoSampleQueueMappingDone;
    private final Loader loader = new Loader("Loader:HlsSampleStreamWrapper");
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
    private int[] sampleQueueTrackIds = new int[0];
    private int audioSampleQueueIndex = -1;
    private int videoSampleQueueIndex = -1;
    private SampleQueue[] sampleQueues = new SampleQueue[0];
    private boolean[] sampleQueueIsAudioVideoFlags = new boolean[0];
    private boolean[] sampleQueuesEnabledStates = new boolean[0];
    private final ArrayList<HlsMediaChunk> mediaChunks = new ArrayList<>();
    private final Runnable maybeFinishPrepareRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.1
        @Override // java.lang.Runnable
        public void run() {
            HlsSampleStreamWrapper.this.maybeFinishPrepare();
        }
    };
    private final Runnable onTracksEndedRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.2
        @Override // java.lang.Runnable
        public void run() {
            HlsSampleStreamWrapper.this.onTracksEnded();
        }
    };
    private final Handler handler = new Handler();

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(HlsMasterPlaylist.HlsUrl hlsUrl);

        void onPrepared();
    }

    public HlsSampleStreamWrapper(int i9, Callback callback, HlsChunkSource hlsChunkSource, Allocator allocator, long j9, Format format, int i10, MediaSourceEventListener.EventDispatcher eventDispatcher) {
        this.trackType = i9;
        this.callback = callback;
        this.chunkSource = hlsChunkSource;
        this.allocator = allocator;
        this.muxedAudioFormat = format;
        this.minLoadableRetryCount = i10;
        this.eventDispatcher = eventDispatcher;
        this.lastSeekPositionUs = j9;
        this.pendingResetPositionUs = j9;
    }

    private void buildTracks() {
        int length = this.sampleQueues.length;
        int i9 = 0;
        char c9 = 0;
        int i10 = -1;
        while (true) {
            if (i9 >= length) {
                break;
            }
            String str = this.sampleQueues[i9].getUpstreamFormat().sampleMimeType;
            char c10 = MimeTypes.isVideo(str) ? (char) 3 : MimeTypes.isAudio(str) ? (char) 2 : MimeTypes.isText(str) ? (char) 1 : (char) 0;
            if (c10 > c9) {
                i10 = i9;
                c9 = c10;
            } else if (c10 == c9 && i10 != -1) {
                i10 = -1;
            }
            i9++;
        }
        TrackGroup trackGroup = this.chunkSource.getTrackGroup();
        int i11 = trackGroup.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupToSampleQueueIndex = new int[length];
        for (int i12 = 0; i12 < length; i12++) {
            this.trackGroupToSampleQueueIndex[i12] = i12;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        for (int i13 = 0; i13 < length; i13++) {
            Format upstreamFormat = this.sampleQueues[i13].getUpstreamFormat();
            if (i13 == i10) {
                Format[] formatArr = new Format[i11];
                for (int i14 = 0; i14 < i11; i14++) {
                    formatArr[i14] = deriveFormat(trackGroup.getFormat(i14), upstreamFormat, true);
                }
                trackGroupArr[i13] = new TrackGroup(formatArr);
                this.primaryTrackGroupIndex = i13;
            } else {
                trackGroupArr[i13] = new TrackGroup(deriveFormat((c9 == 3 && MimeTypes.isAudio(upstreamFormat.sampleMimeType)) ? this.muxedAudioFormat : null, upstreamFormat, false));
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
    }

    private static DummyTrackOutput createDummyTrackOutput(int i9, int i10) {
        Log.w(TAG, "Unmapped track with id " + i9 + " of type " + i10);
        return new DummyTrackOutput();
    }

    private static Format deriveFormat(Format format, Format format2, boolean z8) {
        if (format == null) {
            return format2;
        }
        int i9 = z8 ? format.bitrate : -1;
        String codecsOfType = Util.getCodecsOfType(format.codecs, MimeTypes.getTrackType(format2.sampleMimeType));
        String mediaMimeType = MimeTypes.getMediaMimeType(codecsOfType);
        if (mediaMimeType == null) {
            mediaMimeType = format2.sampleMimeType;
        }
        return format2.copyWithContainerInfo(format.f15298id, mediaMimeType, codecsOfType, i9, format.width, format.height, format.selectionFlags, format.language);
    }

    private boolean finishedReadingChunk(HlsMediaChunk hlsMediaChunk) {
        int i9 = hlsMediaChunk.uid;
        int length = this.sampleQueues.length;
        for (int i10 = 0; i10 < length; i10++) {
            if (this.sampleQueuesEnabledStates[i10] && this.sampleQueues[i10].peekSourceId() == i9) {
                return false;
            }
        }
        return true;
    }

    private static boolean formatsMatch(Format format, Format format2) {
        String str = format.sampleMimeType;
        String str2 = format2.sampleMimeType;
        int trackType = MimeTypes.getTrackType(str);
        if (trackType != 3) {
            return trackType == MimeTypes.getTrackType(str2);
        }
        if (Util.areEqual(str, str2)) {
            return !(MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str)) || format.accessibilityChannel == format2.accessibilityChannel;
        }
        return false;
    }

    private HlsMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(r0.size() - 1);
    }

    private static boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C3322C.TIME_UNSET;
    }

    private void mapSampleQueuesToMatchTrackGroups() {
        int i9 = this.trackGroups.length;
        int[] iArr = new int[i9];
        this.trackGroupToSampleQueueIndex = iArr;
        Arrays.fill(iArr, -1);
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = 0;
            while (true) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                if (i11 >= sampleQueueArr.length) {
                    break;
                }
                if (formatsMatch(sampleQueueArr[i11].getUpstreamFormat(), this.trackGroups.get(i10).getFormat(0))) {
                    this.trackGroupToSampleQueueIndex[i10] = i11;
                    break;
                }
                i11++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (!this.released && this.trackGroupToSampleQueueIndex == null && this.sampleQueuesBuilt) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                if (sampleQueue.getUpstreamFormat() == null) {
                    return;
                }
            }
            if (this.trackGroups != null) {
                mapSampleQueuesToMatchTrackGroups();
                return;
            }
            buildTracks();
            this.prepared = true;
            this.callback.onPrepared();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTracksEnded() {
        this.sampleQueuesBuilt = true;
        maybeFinishPrepare();
    }

    private void resetSampleQueues() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset(this.pendingResetUpstreamFormats);
        }
        this.pendingResetUpstreamFormats = false;
    }

    private boolean seekInsideBufferUs(long j9) {
        int length = this.sampleQueues.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                return true;
            }
            SampleQueue sampleQueue = this.sampleQueues[i9];
            sampleQueue.rewind();
            if (!(sampleQueue.advanceTo(j9, true, false) != -1) && (this.sampleQueueIsAudioVideoFlags[i9] || !this.haveAudioVideoSampleQueues)) {
                break;
            }
            i9++;
        }
        return false;
    }

    public int bindSampleQueueToSampleStream(int i9) {
        int i10;
        if (!isMappingFinished() || (i10 = this.trackGroupToSampleQueueIndex[i9]) == -1) {
            return -1;
        }
        boolean[] zArr = this.sampleQueuesEnabledStates;
        if (zArr[i10]) {
            return -1;
        }
        zArr[i10] = true;
        return i10;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        HlsMediaChunk lastMediaChunk;
        long j10;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        if (isPendingReset()) {
            j10 = this.pendingResetPositionUs;
            lastMediaChunk = null;
        } else {
            lastMediaChunk = getLastMediaChunk();
            j10 = lastMediaChunk.endTimeUs;
        }
        this.chunkSource.getNextChunk(lastMediaChunk, j9, j10, this.nextChunkHolder);
        HlsChunkSource.HlsChunkHolder hlsChunkHolder = this.nextChunkHolder;
        boolean z8 = hlsChunkHolder.endOfStream;
        Chunk chunk = hlsChunkHolder.chunk;
        HlsMasterPlaylist.HlsUrl hlsUrl = hlsChunkHolder.playlist;
        hlsChunkHolder.clear();
        if (z8) {
            this.pendingResetPositionUs = C3322C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            if (hlsUrl != null) {
                this.callback.onPlaylistRefreshRequired(hlsUrl);
            }
            return false;
        }
        if (isMediaChunk(chunk)) {
            this.pendingResetPositionUs = C3322C.TIME_UNSET;
            HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) chunk;
            hlsMediaChunk.init(this);
            this.mediaChunks.add(hlsMediaChunk);
        }
        this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
        return true;
    }

    public void continuePreparing() {
        if (this.prepared) {
            return;
        }
        continueLoading(this.lastSeekPositionUs);
    }

    public void discardBuffer(long j9, boolean z8) {
        if (this.sampleQueuesBuilt) {
            int length = this.sampleQueues.length;
            for (int i9 = 0; i9 < length; i9++) {
                this.sampleQueues[i9].discardTo(j9, z8, this.sampleQueuesEnabledStates[i9]);
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        */
    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        /*
            r7 = this;
            boolean r0 = r7.loadingFinished
            if (r0 == 0) goto L7
            r0 = -9223372036854775808
            return r0
        L7:
            boolean r0 = r7.isPendingReset()
            if (r0 == 0) goto L10
            long r0 = r7.pendingResetPositionUs
            return r0
        L10:
            long r0 = r7.lastSeekPositionUs
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r2 = r7.getLastMediaChunk()
            boolean r3 = r2.isLoadCompleted()
            if (r3 == 0) goto L1d
            goto L36
        L1d:
            java.util.ArrayList<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r2 = r7.mediaChunks
            int r2 = r2.size()
            r3 = 1
            if (r2 <= r3) goto L35
            java.util.ArrayList<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r2 = r7.mediaChunks
            int r3 = r2.size()
            int r3 = r3 + (-2)
            java.lang.Object r2 = r2.get(r3)
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r2 = (com.google.android.exoplayer2.source.hls.HlsMediaChunk) r2
            goto L36
        L35:
            r2 = 0
        L36:
            if (r2 == 0) goto L3e
            long r2 = r2.endTimeUs
            long r0 = java.lang.Math.max(r0, r2)
        L3e:
            boolean r2 = r7.sampleQueuesBuilt
            if (r2 == 0) goto L55
            com.google.android.exoplayer2.source.SampleQueue[] r2 = r7.sampleQueues
            int r3 = r2.length
            r4 = 0
        L46:
            if (r4 >= r3) goto L55
            r5 = r2[r4]
            long r5 = r5.getLargestQueuedTimestampUs()
            long r0 = java.lang.Math.max(r0, r5)
            int r4 = r4 + 1
            goto L46
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.getBufferedPositionUs():long");
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public void init(int i9, boolean z8, boolean z9) {
        if (!z9) {
            this.audioSampleQueueMappingDone = false;
            this.videoSampleQueueMappingDone = false;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.sourceId(i9);
        }
        if (z8) {
            for (SampleQueue sampleQueue2 : this.sampleQueues) {
                sampleQueue2.splice();
            }
        }
    }

    public boolean isMappingFinished() {
        return this.trackGroupToSampleQueueIndex != null;
    }

    public boolean isReady(int i9) {
        return this.loadingFinished || (!isPendingReset() && this.sampleQueues[i9].hasNextSample());
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    public void maybeThrowPrepareError() {
        maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        resetSampleQueues();
    }

    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j9) {
        this.chunkSource.onPlaylistBlacklisted(hlsUrl, j9);
    }

    @Override // com.google.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepareWithMasterPlaylistInfo(TrackGroupArray trackGroupArray, int i9) {
        this.prepared = true;
        this.trackGroups = trackGroupArray;
        this.primaryTrackGroupIndex = i9;
        this.callback.onPrepared();
    }

    public int readData(int i9, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
        if (isPendingReset()) {
            return -3;
        }
        if (!this.mediaChunks.isEmpty()) {
            int i10 = 0;
            while (i10 < this.mediaChunks.size() - 1 && finishedReadingChunk(this.mediaChunks.get(i10))) {
                i10++;
            }
            if (i10 > 0) {
                Util.removeRange(this.mediaChunks, 0, i10);
            }
            HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(0);
            Format format = hlsMediaChunk.trackFormat;
            if (!format.equals(this.downstreamTrackFormat)) {
                this.eventDispatcher.downstreamFormatChanged(this.trackType, format, hlsMediaChunk.trackSelectionReason, hlsMediaChunk.trackSelectionData, hlsMediaChunk.startTimeUs);
            }
            this.downstreamTrackFormat = format;
        }
        return this.sampleQueues[i9].read(formatHolder, decoderInputBuffer, z8, this.loadingFinished, this.lastSeekPositionUs);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.discardToEnd();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
    }

    public boolean seekToUs(long j9, boolean z8) {
        this.lastSeekPositionUs = j9;
        if (this.sampleQueuesBuilt && !z8 && !isPendingReset() && seekInsideBufferUs(j9)) {
            return false;
        }
        this.pendingResetPositionUs = j9;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return true;
        }
        resetSampleQueues();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9, boolean z8) {
        boolean z9;
        TrackSelection trackSelection;
        Assertions.checkState(this.prepared);
        int i9 = this.enabledTrackGroupCount;
        int i10 = 0;
        for (int i11 = 0; i11 < trackSelectionArr.length; i11++) {
            SampleStream sampleStream = sampleStreamArr[i11];
            if (sampleStream != null && (trackSelectionArr[i11] == null || !zArr[i11])) {
                this.enabledTrackGroupCount--;
                ((HlsSampleStream) sampleStream).unbindSampleQueue();
                sampleStreamArr[i11] = null;
            }
        }
        boolean z10 = z8 || (!this.seenFirstTrackSelection ? j9 == this.lastSeekPositionUs : i9 != 0);
        TrackSelection trackSelection2 = this.chunkSource.getTrackSelection();
        boolean z11 = z10;
        TrackSelection trackSelection3 = trackSelection2;
        for (int i12 = 0; i12 < trackSelectionArr.length; i12++) {
            if (sampleStreamArr[i12] == null && (trackSelection = trackSelectionArr[i12]) != null) {
                this.enabledTrackGroupCount++;
                int iIndexOf = this.trackGroups.indexOf(trackSelection.getTrackGroup());
                if (iIndexOf == this.primaryTrackGroupIndex) {
                    this.chunkSource.selectTracks(trackSelection);
                    trackSelection3 = trackSelection;
                }
                sampleStreamArr[i12] = new HlsSampleStream(this, iIndexOf);
                zArr2[i12] = true;
                if (this.sampleQueuesBuilt && !z11) {
                    SampleQueue sampleQueue = this.sampleQueues[this.trackGroupToSampleQueueIndex[iIndexOf]];
                    sampleQueue.rewind();
                    z11 = sampleQueue.advanceTo(j9, true, true) == -1 && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackGroupCount == 0) {
            this.chunkSource.reset();
            this.downstreamTrackFormat = null;
            this.mediaChunks.clear();
            if (this.loader.isLoading()) {
                if (this.sampleQueuesBuilt) {
                    SampleQueue[] sampleQueueArr = this.sampleQueues;
                    int length = sampleQueueArr.length;
                    while (i10 < length) {
                        sampleQueueArr[i10].discardToEnd();
                        i10++;
                    }
                }
                this.loader.cancelLoading();
            } else {
                resetSampleQueues();
            }
        } else if (this.mediaChunks.isEmpty() || Util.areEqual(trackSelection3, trackSelection2)) {
            z9 = z8;
            if (z11) {
                seekToUs(j9, z9);
                while (i10 < sampleStreamArr.length) {
                    if (sampleStreamArr[i10] != null) {
                        zArr2[i10] = true;
                    }
                    i10++;
                }
            }
        } else if (!this.seenFirstTrackSelection) {
            trackSelection3.updateSelectedTrack(j9, j9 < 0 ? -j9 : 0L, C3322C.TIME_UNSET);
            boolean z12 = trackSelection3.getSelectedIndexInTrackGroup() != this.chunkSource.getTrackGroup().indexOf(getLastMediaChunk().trackFormat);
            if (z12) {
                this.pendingResetUpstreamFormats = true;
                z9 = true;
                z11 = true;
            }
            if (z11) {
            }
        }
        this.seenFirstTrackSelection = true;
        return z11;
    }

    public void setIsTimestampMaster(boolean z8) {
        this.chunkSource.setIsTimestampMaster(z8);
    }

    public void setSampleOffsetUs(long j9) {
        this.sampleOffsetUs = j9;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.setSampleOffsetUs(j9);
        }
    }

    public int skipData(int i9, long j9) {
        if (isPendingReset()) {
            return 0;
        }
        SampleQueue sampleQueue = this.sampleQueues[i9];
        if (this.loadingFinished && j9 > sampleQueue.getLargestQueuedTimestampUs()) {
            return sampleQueue.advanceToEnd();
        }
        int iAdvanceTo = sampleQueue.advanceTo(j9, true, true);
        if (iAdvanceTo == -1) {
            return 0;
        }
        return iAdvanceTo;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i9, int i10) {
        SampleQueue[] sampleQueueArr = this.sampleQueues;
        int length = sampleQueueArr.length;
        if (i10 == 1) {
            int i11 = this.audioSampleQueueIndex;
            if (i11 != -1) {
                if (this.audioSampleQueueMappingDone) {
                    return this.sampleQueueTrackIds[i11] == i9 ? sampleQueueArr[i11] : createDummyTrackOutput(i9, i10);
                }
                this.audioSampleQueueMappingDone = true;
                this.sampleQueueTrackIds[i11] = i9;
                return sampleQueueArr[i11];
            }
            if (this.tracksEnded) {
                return createDummyTrackOutput(i9, i10);
            }
        } else if (i10 == 2) {
            int i12 = this.videoSampleQueueIndex;
            if (i12 != -1) {
                if (this.videoSampleQueueMappingDone) {
                    return this.sampleQueueTrackIds[i12] == i9 ? sampleQueueArr[i12] : createDummyTrackOutput(i9, i10);
                }
                this.videoSampleQueueMappingDone = true;
                this.sampleQueueTrackIds[i12] = i9;
                return sampleQueueArr[i12];
            }
            if (this.tracksEnded) {
                return createDummyTrackOutput(i9, i10);
            }
        } else {
            for (int i13 = 0; i13 < length; i13++) {
                if (this.sampleQueueTrackIds[i13] == i9) {
                    return this.sampleQueues[i13];
                }
            }
            if (this.tracksEnded) {
                return createDummyTrackOutput(i9, i10);
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator);
        sampleQueue.setSampleOffsetUs(this.sampleOffsetUs);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i14 = length + 1;
        int[] iArrCopyOf = Arrays.copyOf(this.sampleQueueTrackIds, i14);
        this.sampleQueueTrackIds = iArrCopyOf;
        iArrCopyOf[length] = i9;
        SampleQueue[] sampleQueueArr2 = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i14);
        this.sampleQueues = sampleQueueArr2;
        sampleQueueArr2[length] = sampleQueue;
        boolean[] zArrCopyOf = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, i14);
        this.sampleQueueIsAudioVideoFlags = zArrCopyOf;
        boolean z8 = i10 == 1 || i10 == 2;
        zArrCopyOf[length] = z8;
        this.haveAudioVideoSampleQueues |= z8;
        if (i10 == 1) {
            this.audioSampleQueueMappingDone = true;
            this.audioSampleQueueIndex = length;
        } else if (i10 == 2) {
            this.videoSampleQueueMappingDone = true;
            this.videoSampleQueueIndex = length;
        }
        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, i14);
        return sampleQueue;
    }

    public void unbindSampleQueue(int i9) {
        int i10 = this.trackGroupToSampleQueueIndex[i9];
        Assertions.checkState(this.sampleQueuesEnabledStates[i10]);
        this.sampleQueuesEnabledStates[i10] = false;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j9, long j10, boolean z8) {
        this.eventDispatcher.loadCanceled(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, chunk.bytesLoaded());
        if (z8) {
            return;
        }
        resetSampleQueues();
        if (this.enabledTrackGroupCount > 0) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j9, long j10) {
        this.chunkSource.onChunkLoadCompleted(chunk);
        this.eventDispatcher.loadCompleted(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, chunk.bytesLoaded());
        if (this.prepared) {
            this.callback.onContinueLoadingRequested(this);
        } else {
            continueLoading(this.lastSeekPositionUs);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(Chunk chunk, long j9, long j10, IOException iOException) {
        boolean z8;
        long jBytesLoaded = chunk.bytesLoaded();
        boolean zIsMediaChunk = isMediaChunk(chunk);
        if (this.chunkSource.onChunkLoadError(chunk, !zIsMediaChunk || jBytesLoaded == 0, iOException)) {
            if (zIsMediaChunk) {
                ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
                Assertions.checkState(arrayList.remove(arrayList.size() - 1) == chunk);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            z8 = true;
        } else {
            z8 = false;
        }
        this.eventDispatcher.loadError(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, chunk.bytesLoaded(), iOException, z8);
        if (!z8) {
            return iOException instanceof ParserException ? 3 : 0;
        }
        if (this.prepared) {
            this.callback.onContinueLoadingRequested(this);
            return 2;
        }
        continueLoading(this.lastSeekPositionUs);
        return 2;
    }
}
