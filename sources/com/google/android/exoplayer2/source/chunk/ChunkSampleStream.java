package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private static final String TAG = "ChunkSampleStream";
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private final T chunkSource;
    long decodeOnlyUntilPositionUs;
    private final SampleQueue[] embeddedSampleQueues;
    private final Format[] embeddedTrackFormats;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private long lastSeekPositionUs;
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final ArrayList<BaseMediaChunk> mediaChunks;
    private final int minLoadableRetryCount;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    private ReleaseCallback<T> releaseCallback;
    private final Loader loader = new Loader("Loader:ChunkSampleStream");
    private final ChunkHolder nextChunkHolder = new ChunkHolder();

    public final class EmbeddedSampleStream implements SampleStream {
        private boolean formatNotificationSent;
        private final int index;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue, int i9) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue;
            this.index = i9;
        }

        private void maybeNotifyTrackFormatChanged() {
            if (this.formatNotificationSent) {
                return;
            }
            ChunkSampleStream.this.eventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, null, ChunkSampleStream.this.lastSeekPositionUs);
            this.formatNotificationSent = true;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            ChunkSampleStream chunkSampleStream = ChunkSampleStream.this;
            return chunkSampleStream.loadingFinished || (!chunkSampleStream.isPendingReset() && this.sampleQueue.hasNextSample());
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() {
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            SampleQueue sampleQueue = this.sampleQueue;
            ChunkSampleStream chunkSampleStream = ChunkSampleStream.this;
            int i9 = sampleQueue.read(formatHolder, decoderInputBuffer, z8, chunkSampleStream.loadingFinished, chunkSampleStream.decodeOnlyUntilPositionUs);
            if (i9 == -4) {
                maybeNotifyTrackFormatChanged();
            }
            return i9;
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int skipData(long j9) {
            int iAdvanceTo;
            if (!ChunkSampleStream.this.loadingFinished || j9 <= this.sampleQueue.getLargestQueuedTimestampUs()) {
                iAdvanceTo = this.sampleQueue.advanceTo(j9, true, true);
                if (iAdvanceTo == -1) {
                    iAdvanceTo = 0;
                }
            } else {
                iAdvanceTo = this.sampleQueue.advanceToEnd();
            }
            if (iAdvanceTo > 0) {
                maybeNotifyTrackFormatChanged();
            }
            return iAdvanceTo;
        }
    }

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    public ChunkSampleStream(int i9, int[] iArr, Format[] formatArr, T t8, SequenceableLoader.Callback<ChunkSampleStream<T>> callback, Allocator allocator, long j9, int i10, MediaSourceEventListener.EventDispatcher eventDispatcher) {
        this.primaryTrackType = i9;
        this.embeddedTrackTypes = iArr;
        this.embeddedTrackFormats = formatArr;
        this.chunkSource = t8;
        this.callback = callback;
        this.eventDispatcher = eventDispatcher;
        this.minLoadableRetryCount = i10;
        ArrayList<BaseMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        int i11 = 0;
        int length = iArr == null ? 0 : iArr.length;
        this.embeddedSampleQueues = new SampleQueue[length];
        this.embeddedTracksSelected = new boolean[length];
        int i12 = length + 1;
        int[] iArr2 = new int[i12];
        SampleQueue[] sampleQueueArr = new SampleQueue[i12];
        SampleQueue sampleQueue = new SampleQueue(allocator);
        this.primarySampleQueue = sampleQueue;
        iArr2[0] = i9;
        sampleQueueArr[0] = sampleQueue;
        while (i11 < length) {
            SampleQueue sampleQueue2 = new SampleQueue(allocator);
            this.embeddedSampleQueues[i11] = sampleQueue2;
            int i13 = i11 + 1;
            sampleQueueArr[i13] = sampleQueue2;
            iArr2[i13] = iArr[i11];
            i11 = i13;
        }
        this.mediaChunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j9;
        this.lastSeekPositionUs = j9;
    }

    private void discardDownstreamMediaChunks(int i9) {
        int iPrimaryStreamIndexToMediaChunkIndex = primaryStreamIndexToMediaChunkIndex(i9, 0);
        if (iPrimaryStreamIndexToMediaChunkIndex > 0) {
            Util.removeRange(this.mediaChunks, 0, iPrimaryStreamIndexToMediaChunkIndex);
        }
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int i9) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i9);
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i9, arrayList.size());
        int i10 = 0;
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i10 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            SampleQueue sampleQueue = sampleQueueArr[i10];
            i10++;
            sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i10));
        }
    }

    private BaseMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(r0.size() - 1);
    }

    private boolean haveReadFromMediaChunk(int i9) {
        int readIndex;
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i9);
        if (this.primarySampleQueue.getReadIndex() > baseMediaChunk.getFirstSampleIndex(0)) {
            return true;
        }
        int i10 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i10 >= sampleQueueArr.length) {
                return false;
            }
            readIndex = sampleQueueArr[i10].getReadIndex();
            i10++;
        } while (readIndex <= baseMediaChunk.getFirstSampleIndex(i10));
        return true;
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i9, int i10) {
        int iPrimaryStreamIndexToMediaChunkIndex = primaryStreamIndexToMediaChunkIndex(i9 - i10, 0);
        int iPrimaryStreamIndexToMediaChunkIndex2 = i10 == 1 ? iPrimaryStreamIndexToMediaChunkIndex : primaryStreamIndexToMediaChunkIndex(i9 - 1, iPrimaryStreamIndexToMediaChunkIndex);
        while (iPrimaryStreamIndexToMediaChunkIndex <= iPrimaryStreamIndexToMediaChunkIndex2) {
            maybeNotifyPrimaryTrackFormatChanged(iPrimaryStreamIndexToMediaChunkIndex);
            iPrimaryStreamIndexToMediaChunkIndex++;
        }
    }

    private int primaryStreamIndexToMediaChunkIndex(int i9, int i10) {
        do {
            i10++;
            if (i10 >= this.mediaChunks.size()) {
                return this.mediaChunks.size() - 1;
            }
        } while (this.mediaChunks.get(i10).getFirstSampleIndex(0) <= i9);
        return i10 - 1;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        BaseMediaChunk lastMediaChunk;
        long j10;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        boolean zIsPendingReset = isPendingReset();
        if (zIsPendingReset) {
            j10 = this.pendingResetPositionUs;
            lastMediaChunk = null;
        } else {
            lastMediaChunk = getLastMediaChunk();
            j10 = lastMediaChunk.endTimeUs;
        }
        this.chunkSource.getNextChunk(lastMediaChunk, j9, j10, this.nextChunkHolder);
        ChunkHolder chunkHolder = this.nextChunkHolder;
        boolean z8 = chunkHolder.endOfStream;
        Chunk chunk = chunkHolder.chunk;
        chunkHolder.clear();
        if (z8) {
            this.pendingResetPositionUs = C3322C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            return false;
        }
        if (isMediaChunk(chunk)) {
            BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
            if (zIsPendingReset) {
                long j11 = baseMediaChunk.startTimeUs;
                long j12 = this.pendingResetPositionUs;
                if (j11 == j12) {
                    j12 = Long.MIN_VALUE;
                }
                this.decodeOnlyUntilPositionUs = j12;
                this.pendingResetPositionUs = C3322C.TIME_UNSET;
            }
            baseMediaChunk.init(this.mediaChunkOutput);
            this.mediaChunks.add(baseMediaChunk);
        }
        this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
        return true;
    }

    public void discardBuffer(long j9, boolean z8) {
        int firstIndex = this.primarySampleQueue.getFirstIndex();
        this.primarySampleQueue.discardTo(j9, z8, true);
        int firstIndex2 = this.primarySampleQueue.getFirstIndex();
        if (firstIndex2 <= firstIndex) {
            return;
        }
        long firstTimestampUs = this.primarySampleQueue.getFirstTimestampUs();
        int i9 = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i9 >= sampleQueueArr.length) {
                discardDownstreamMediaChunks(firstIndex2);
                return;
            } else {
                sampleQueueArr[i9].discardTo(firstTimestampUs, z8, this.embeddedTracksSelected[i9]);
                i9++;
            }
        }
    }

    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j9, seekParameters);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long jMax = this.lastSeekPositionUs;
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            if (this.mediaChunks.size() > 1) {
                lastMediaChunk = this.mediaChunks.get(r2.size() - 2);
            } else {
                lastMediaChunk = null;
            }
        }
        if (lastMediaChunk != null) {
            jMax = Math.max(jMax, lastMediaChunk.endTimeUs);
        }
        return Math.max(jMax, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public T getChunkSource() {
        return this.chunkSource;
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

    public boolean isPendingReset() {
        return this.pendingResetPositionUs != C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return this.loadingFinished || (!isPendingReset() && this.primarySampleQueue.hasNextSample());
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        if (this.loader.isLoading()) {
            return;
        }
        this.chunkSource.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
        ReleaseCallback<T> releaseCallback = this.releaseCallback;
        if (releaseCallback != null) {
            releaseCallback.onSampleStreamReleased(this);
        }
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
        if (isPendingReset()) {
            return -3;
        }
        int i9 = this.primarySampleQueue.read(formatHolder, decoderInputBuffer, z8, this.loadingFinished, this.decodeOnlyUntilPositionUs);
        if (i9 == -4) {
            maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), 1);
        }
        return i9;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        int size;
        int preferredQueueSize;
        if (this.loader.isLoading() || isPendingReset() || (size = this.mediaChunks.size()) <= (preferredQueueSize = this.chunkSource.getPreferredQueueSize(j9, this.readOnlyMediaChunks))) {
            return;
        }
        while (true) {
            if (preferredQueueSize >= size) {
                preferredQueueSize = size;
                break;
            } else if (!haveReadFromMediaChunk(preferredQueueSize)) {
                break;
            } else {
                preferredQueueSize++;
            }
        }
        if (preferredQueueSize == size) {
            return;
        }
        long j10 = getLastMediaChunk().endTimeUs;
        BaseMediaChunk baseMediaChunkDiscardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(preferredQueueSize);
        if (this.mediaChunks.isEmpty()) {
            this.pendingResetPositionUs = this.lastSeekPositionUs;
        }
        this.loadingFinished = false;
        this.eventDispatcher.upstreamDiscarded(this.primaryTrackType, baseMediaChunkDiscardUpstreamMediaChunksFromIndex.startTimeUs, j10);
    }

    public void release() {
        release(null);
    }

    public void seekToUs(long j9) {
        BaseMediaChunk baseMediaChunk;
        boolean readPosition;
        this.lastSeekPositionUs = j9;
        this.primarySampleQueue.rewind();
        if (isPendingReset()) {
            readPosition = false;
        } else {
            for (int i9 = 0; i9 < this.mediaChunks.size(); i9++) {
                baseMediaChunk = this.mediaChunks.get(i9);
                long j10 = baseMediaChunk.startTimeUs;
                if (j10 == j9) {
                    break;
                } else {
                    if (j10 > j9) {
                        break;
                    }
                }
            }
            baseMediaChunk = null;
            if (baseMediaChunk != null) {
                readPosition = this.primarySampleQueue.setReadPosition(baseMediaChunk.getFirstSampleIndex(0));
                this.decodeOnlyUntilPositionUs = Long.MIN_VALUE;
            } else {
                readPosition = this.primarySampleQueue.advanceTo(j9, true, (j9 > getNextLoadPositionUs() ? 1 : (j9 == getNextLoadPositionUs() ? 0 : -1)) < 0) != -1;
                this.decodeOnlyUntilPositionUs = this.lastSeekPositionUs;
            }
        }
        if (readPosition) {
            for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
                sampleQueue.rewind();
                sampleQueue.advanceTo(j9, true, false);
            }
            return;
        }
        this.pendingResetPositionUs = j9;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return;
        }
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue2 : this.embeddedSampleQueues) {
            sampleQueue2.reset();
        }
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j9, int i9) {
        for (int i10 = 0; i10 < this.embeddedSampleQueues.length; i10++) {
            if (this.embeddedTrackTypes[i10] == i9) {
                Assertions.checkState(!this.embeddedTracksSelected[i10]);
                this.embeddedTracksSelected[i10] = true;
                this.embeddedSampleQueues[i10].rewind();
                this.embeddedSampleQueues[i10].advanceTo(j9, true, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i10], i10);
            }
        }
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int skipData(long j9) {
        int iAdvanceToEnd = 0;
        if (isPendingReset()) {
            return 0;
        }
        if (!this.loadingFinished || j9 <= this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            int iAdvanceTo = this.primarySampleQueue.advanceTo(j9, true, true);
            if (iAdvanceTo != -1) {
                iAdvanceToEnd = iAdvanceTo;
            }
        } else {
            iAdvanceToEnd = this.primarySampleQueue.advanceToEnd();
        }
        if (iAdvanceToEnd > 0) {
            maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), iAdvanceToEnd);
        }
        return iAdvanceToEnd;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j9, long j10, boolean z8) {
        this.eventDispatcher.loadCanceled(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, chunk.bytesLoaded());
        if (z8) {
            return;
        }
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j9, long j10) {
        this.chunkSource.onChunkLoadCompleted(chunk);
        this.eventDispatcher.loadCompleted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, chunk.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(Chunk chunk, long j9, long j10, IOException iOException) {
        boolean z8;
        long jBytesLoaded = chunk.bytesLoaded();
        boolean zIsMediaChunk = isMediaChunk(chunk);
        int size = this.mediaChunks.size() - 1;
        boolean z9 = (jBytesLoaded != 0 && zIsMediaChunk && haveReadFromMediaChunk(size)) ? false : true;
        if (!this.chunkSource.onChunkLoadError(chunk, z9, iOException)) {
            z8 = false;
        } else if (z9) {
            if (zIsMediaChunk) {
                Assertions.checkState(discardUpstreamMediaChunksFromIndex(size) == chunk);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            z8 = true;
        } else {
            Log.w(TAG, "Ignoring attempt to cancel non-cancelable load.");
            z8 = false;
        }
        this.eventDispatcher.loadError(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j9, j10, jBytesLoaded, iOException, z8);
        if (!z8) {
            return 0;
        }
        this.callback.onContinueLoadingRequested(this);
        return 2;
    }

    public void release(ReleaseCallback<T> releaseCallback) {
        this.releaseCallback = releaseCallback;
        this.primarySampleQueue.discardToEnd();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.discardToEnd();
        }
        this.loader.release(this);
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i9) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i9);
        Format format = baseMediaChunk.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, baseMediaChunk.trackSelectionReason, baseMediaChunk.trackSelectionData, baseMediaChunk.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }
}
