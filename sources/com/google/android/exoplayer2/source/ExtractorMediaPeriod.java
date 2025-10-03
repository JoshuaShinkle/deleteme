package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
final class ExtractorMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private int actualMinLoadableRetryCount;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource dataSource;
    private int enabledTrackCount;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private final Listener listener;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private boolean notifyDiscontinuity;
    private boolean pendingDeferredRetry;
    private boolean prepared;
    private boolean released;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private boolean[] trackEnabledStates;
    private boolean[] trackFormatNotificationSent;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private final Uri uri;
    private final Loader loader = new Loader("Loader:ExtractorMediaPeriod");
    private final ConditionVariable loadCondition = new ConditionVariable();
    private final Runnable maybeFinishPrepareRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.ExtractorMediaPeriod.1
        @Override // java.lang.Runnable
        public void run() {
            ExtractorMediaPeriod.this.maybeFinishPrepare();
        }
    };
    private final Runnable onContinueLoadingRequestedRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.ExtractorMediaPeriod.2
        @Override // java.lang.Runnable
        public void run() {
            if (ExtractorMediaPeriod.this.released) {
                return;
            }
            ExtractorMediaPeriod.this.callback.onContinueLoadingRequested(ExtractorMediaPeriod.this);
        }
    };
    private final Handler handler = new Handler();
    private int[] sampleQueueTrackIds = new int[0];
    private SampleQueue[] sampleQueues = new SampleQueue[0];
    private long pendingResetPositionUs = C3322C.TIME_UNSET;
    private long length = -1;
    private long durationUs = C3322C.TIME_UNSET;

    public final class ExtractingLoadable implements Loader.Loadable {
        private long bytesLoaded;
        private final DataSource dataSource;
        private DataSpec dataSpec;
        private final ExtractorHolder extractorHolder;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private long seekTimeUs;
        private final Uri uri;
        private final PositionHolder positionHolder = new PositionHolder();
        private boolean pendingExtractorSeek = true;
        private long length = -1;

        public ExtractingLoadable(Uri uri, DataSource dataSource, ExtractorHolder extractorHolder, ConditionVariable conditionVariable) {
            this.uri = (Uri) Assertions.checkNotNull(uri);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder);
            this.loadCondition = conditionVariable;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void load() throws Throwable {
            int i9 = 0;
            while (i9 == 0 && !this.loadCanceled) {
                DefaultExtractorInput defaultExtractorInput = null;
                try {
                    long position = this.positionHolder.position;
                    DataSpec dataSpec = new DataSpec(this.uri, position, -1L, ExtractorMediaPeriod.this.customCacheKey);
                    this.dataSpec = dataSpec;
                    long jOpen = this.dataSource.open(dataSpec);
                    this.length = jOpen;
                    if (jOpen != -1) {
                        this.length = jOpen + position;
                    }
                    DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(this.dataSource, position, this.length);
                    try {
                        Extractor extractorSelectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput2, this.dataSource.getUri());
                        if (this.pendingExtractorSeek) {
                            extractorSelectExtractor.seek(position, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i9 == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            i9 = extractorSelectExtractor.read(defaultExtractorInput2, this.positionHolder);
                            if (defaultExtractorInput2.getPosition() > ExtractorMediaPeriod.this.continueLoadingCheckIntervalBytes + position) {
                                position = defaultExtractorInput2.getPosition();
                                this.loadCondition.close();
                                ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            }
                        }
                        if (i9 == 1) {
                            i9 = 0;
                        } else {
                            this.positionHolder.position = defaultExtractorInput2.getPosition();
                            this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th) {
                        th = th;
                        defaultExtractorInput = defaultExtractorInput2;
                        if (i9 != 1 && defaultExtractorInput != null) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                            this.bytesLoaded = this.positionHolder.position - this.dataSpec.absoluteStreamPosition;
                        }
                        Util.closeQuietly(this.dataSource);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public void setLoadPosition(long j9, long j10) {
            this.positionHolder.position = j9;
            this.seekTimeUs = j10;
            this.pendingExtractorSeek = true;
        }
    }

    public static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr, ExtractorOutput extractorOutput) {
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput;
        }

        public void release() {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                extractor.release();
                this.extractor = null;
            }
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, Uri uri) throws UnrecognizedInputFormatException {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                return extractor;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i9 = 0;
            while (true) {
                if (i9 >= length) {
                    break;
                }
                Extractor extractor2 = extractorArr[i9];
                try {
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    extractorInput.resetPeekPosition();
                    throw th;
                }
                if (extractor2.sniff(extractorInput)) {
                    this.extractor = extractor2;
                    extractorInput.resetPeekPosition();
                    break;
                }
                continue;
                extractorInput.resetPeekPosition();
                i9++;
            }
            Extractor extractor3 = this.extractor;
            if (extractor3 != null) {
                extractor3.init(this.extractorOutput);
                return this.extractor;
            }
            throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
        }
    }

    public interface Listener {
        void onSourceInfoRefreshed(long j9, boolean z8);
    }

    public final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(int i9) {
            this.track = i9;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
            return ExtractorMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z8);
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int skipData(long j9) {
            return ExtractorMediaPeriod.this.skipData(this.track, j9);
        }
    }

    public ExtractorMediaPeriod(Uri uri, DataSource dataSource, Extractor[] extractorArr, int i9, MediaSourceEventListener.EventDispatcher eventDispatcher, Listener listener, Allocator allocator, String str, int i10) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.minLoadableRetryCount = i9;
        this.eventDispatcher = eventDispatcher;
        this.listener = listener;
        this.allocator = allocator;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i10;
        this.extractorHolder = new ExtractorHolder(extractorArr, this);
        this.actualMinLoadableRetryCount = i9 == -1 ? 3 : i9;
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i9) {
        SeekMap seekMap;
        if (this.length != -1 || ((seekMap = this.seekMap) != null && seekMap.getDurationUs() != C3322C.TIME_UNSET)) {
            this.extractedSamplesCountAtStartOfLoad = i9;
            return true;
        }
        if (this.prepared && !suppressRead()) {
            this.pendingDeferredRetry = true;
            return false;
        }
        this.notifyDiscontinuity = this.prepared;
        this.lastSeekPositionUs = 0L;
        this.extractedSamplesCountAtStartOfLoad = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        extractingLoadable.setLoadPosition(0L, 0L);
        return true;
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private int getExtractedSamplesCount() {
        int writeIndex = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            writeIndex += sampleQueue.getWriteIndex();
        }
        return writeIndex;
    }

    private long getLargestQueuedTimestampUs() {
        long jMax = Long.MIN_VALUE;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            jMax = Math.max(jMax, sampleQueue.getLargestQueuedTimestampUs());
        }
        return jMax;
    }

    private static boolean isLoadableExceptionFatal(IOException iOException) {
        return iOException instanceof UnrecognizedInputFormatException;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C3322C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (this.released || this.prepared || this.seekMap == null || !this.sampleQueuesBuilt) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue.getUpstreamFormat() == null) {
                return;
            }
        }
        this.loadCondition.close();
        int length = this.sampleQueues.length;
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        this.trackIsAudioVideoFlags = new boolean[length];
        this.trackEnabledStates = new boolean[length];
        this.trackFormatNotificationSent = new boolean[length];
        this.durationUs = this.seekMap.getDurationUs();
        int i9 = 0;
        while (true) {
            boolean z8 = true;
            if (i9 >= length) {
                break;
            }
            Format upstreamFormat = this.sampleQueues[i9].getUpstreamFormat();
            trackGroupArr[i9] = new TrackGroup(upstreamFormat);
            String str = upstreamFormat.sampleMimeType;
            if (!MimeTypes.isVideo(str) && !MimeTypes.isAudio(str)) {
                z8 = false;
            }
            this.trackIsAudioVideoFlags[i9] = z8;
            this.haveAudioVideoTracks = z8 | this.haveAudioVideoTracks;
            i9++;
        }
        this.tracks = new TrackGroupArray(trackGroupArr);
        if (this.minLoadableRetryCount == -1 && this.length == -1 && this.seekMap.getDurationUs() == C3322C.TIME_UNSET) {
            this.actualMinLoadableRetryCount = 6;
        }
        this.prepared = true;
        this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
        this.callback.onPrepared(this);
    }

    private void maybeNotifyTrackFormat(int i9) {
        if (this.trackFormatNotificationSent[i9]) {
            return;
        }
        Format format = this.tracks.get(i9).getFormat(0);
        this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, null, this.lastSeekPositionUs);
        this.trackFormatNotificationSent[i9] = true;
    }

    private void maybeStartDeferredRetry(int i9) {
        if (this.pendingDeferredRetry && this.trackIsAudioVideoFlags[i9] && !this.sampleQueues[i9].hasNextSample()) {
            this.pendingResetPositionUs = 0L;
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = true;
            this.lastSeekPositionUs = 0L;
            this.extractedSamplesCountAtStartOfLoad = 0;
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
            this.callback.onContinueLoadingRequested(this);
        }
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
            if (!(sampleQueue.advanceTo(j9, true, false) != -1) && (this.trackIsAudioVideoFlags[i9] || !this.haveAudioVideoTracks)) {
                break;
            }
            i9++;
        }
        return false;
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j9 = this.durationUs;
            if (j9 != C3322C.TIME_UNSET && this.pendingResetPositionUs >= j9) {
                this.loadingFinished = true;
                this.pendingResetPositionUs = C3322C.TIME_UNSET;
                return;
            } else {
                extractingLoadable.setLoadPosition(this.seekMap.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                this.pendingResetPositionUs = C3322C.TIME_UNSET;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.eventDispatcher.loadStarted(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, this.loader.startLoading(extractingLoadable, this, this.actualMinLoadableRetryCount));
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        if (this.loadingFinished || this.pendingDeferredRetry) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean zOpen = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return zOpen;
        }
        startLoading();
        return true;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        int length = this.sampleQueues.length;
        for (int i9 = 0; i9 < length; i9++) {
            this.sampleQueues[i9].discardTo(j9, z8, this.trackEnabledStates[i9]);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        if (!this.seekMap.isSeekable()) {
            return 0L;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j9);
        return Util.resolveSeekPositionUs(j9, seekParameters, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long largestQueuedTimestampUs;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length = this.sampleQueues.length;
            largestQueuedTimestampUs = Long.MAX_VALUE;
            for (int i9 = 0; i9 < length; i9++) {
                if (this.trackIsAudioVideoFlags[i9]) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, this.sampleQueues[i9].getLargestQueuedTimestampUs());
                }
            }
        } else {
            largestQueuedTimestampUs = getLargestQueuedTimestampUs();
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.lastSeekPositionUs : largestQueuedTimestampUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public boolean isReady(int i9) {
        return !suppressRead() && (this.loadingFinished || this.sampleQueues[i9].hasNextSample());
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.actualMinLoadableRetryCount);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        this.extractorHolder.release();
    }

    @Override // com.google.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        this.loadCondition.open();
        startLoading();
    }

    public int readData(int i9, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
        if (suppressRead()) {
            return -3;
        }
        int i10 = this.sampleQueues[i9].read(formatHolder, decoderInputBuffer, z8, this.loadingFinished, this.lastSeekPositionUs);
        if (i10 == -4) {
            maybeNotifyTrackFormat(i9);
        } else if (i10 == -3) {
            maybeStartDeferredRetry(i9);
        }
        return i10;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity) {
            return C3322C.TIME_UNSET;
        }
        if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
            return C3322C.TIME_UNSET;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
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
        this.seekMap = seekMap;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        if (!this.seekMap.isSeekable()) {
            j9 = 0;
        }
        this.lastSeekPositionUs = j9;
        this.notifyDiscontinuity = false;
        if (!isPendingReset() && seekInsideBufferUs(j9)) {
            return j9;
        }
        this.pendingDeferredRetry = false;
        this.pendingResetPositionUs = j9;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
        }
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        TrackSelection trackSelection;
        Assertions.checkState(this.prepared);
        int i9 = this.enabledTrackCount;
        int i10 = 0;
        for (int i11 = 0; i11 < trackSelectionArr.length; i11++) {
            SampleStream sampleStream = sampleStreamArr[i11];
            if (sampleStream != null && (trackSelectionArr[i11] == null || !zArr[i11])) {
                int i12 = ((SampleStreamImpl) sampleStream).track;
                Assertions.checkState(this.trackEnabledStates[i12]);
                this.enabledTrackCount--;
                this.trackEnabledStates[i12] = false;
                sampleStreamArr[i11] = null;
            }
        }
        boolean z8 = !this.seenFirstTrackSelection ? j9 == 0 : i9 != 0;
        for (int i13 = 0; i13 < trackSelectionArr.length; i13++) {
            if (sampleStreamArr[i13] == null && (trackSelection = trackSelectionArr[i13]) != null) {
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int iIndexOf = this.tracks.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!this.trackEnabledStates[iIndexOf]);
                this.enabledTrackCount++;
                this.trackEnabledStates[iIndexOf] = true;
                sampleStreamArr[i13] = new SampleStreamImpl(iIndexOf);
                zArr2[i13] = true;
                if (!z8) {
                    SampleQueue sampleQueue = this.sampleQueues[iIndexOf];
                    sampleQueue.rewind();
                    z8 = sampleQueue.advanceTo(j9, true, true) == -1 && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i10 < length) {
                    sampleQueueArr[i10].discardToEnd();
                    i10++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length2 = sampleQueueArr2.length;
                while (i10 < length2) {
                    sampleQueueArr2[i10].reset();
                    i10++;
                }
            }
        } else if (z8) {
            j9 = seekToUs(j9);
            while (i10 < sampleStreamArr.length) {
                if (sampleStreamArr[i10] != null) {
                    zArr2[i10] = true;
                }
                i10++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j9;
    }

    public int skipData(int i9, long j9) {
        int iAdvanceToEnd = 0;
        if (suppressRead()) {
            return 0;
        }
        SampleQueue sampleQueue = this.sampleQueues[i9];
        if (!this.loadingFinished || j9 <= sampleQueue.getLargestQueuedTimestampUs()) {
            int iAdvanceTo = sampleQueue.advanceTo(j9, true, true);
            if (iAdvanceTo != -1) {
                iAdvanceToEnd = iAdvanceTo;
            }
        } else {
            iAdvanceToEnd = sampleQueue.advanceToEnd();
        }
        if (iAdvanceToEnd > 0) {
            maybeNotifyTrackFormat(i9);
        } else {
            maybeStartDeferredRetry(i9);
        }
        return iAdvanceToEnd;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i9, int i10) {
        int length = this.sampleQueues.length;
        for (int i11 = 0; i11 < length; i11++) {
            if (this.sampleQueueTrackIds[i11] == i9) {
                return this.sampleQueues[i11];
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i12 = length + 1;
        int[] iArrCopyOf = Arrays.copyOf(this.sampleQueueTrackIds, i12);
        this.sampleQueueTrackIds = iArrCopyOf;
        iArrCopyOf[length] = i9;
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i12);
        this.sampleQueues = sampleQueueArr;
        sampleQueueArr[length] = sampleQueue;
        return sampleQueue;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j9, long j10, boolean z8) {
        this.eventDispatcher.loadCanceled(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j9, j10, extractingLoadable.bytesLoaded);
        if (z8) {
            return;
        }
        copyLengthFromLoader(extractingLoadable);
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        if (this.enabledTrackCount > 0) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j9, long j10) {
        if (this.durationUs == C3322C.TIME_UNSET) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            long j11 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0L : largestQueuedTimestampUs + DEFAULT_LAST_SAMPLE_DURATION_US;
            this.durationUs = j11;
            this.listener.onSourceInfoRefreshed(j11, this.seekMap.isSeekable());
        }
        this.eventDispatcher.loadCompleted(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j9, j10, extractingLoadable.bytesLoaded);
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(ExtractingLoadable extractingLoadable, long j9, long j10, IOException iOException) {
        ExtractingLoadable extractingLoadable2;
        boolean z8;
        boolean zIsLoadableExceptionFatal = isLoadableExceptionFatal(iOException);
        this.eventDispatcher.loadError(extractingLoadable.dataSpec, 1, -1, null, 0, null, extractingLoadable.seekTimeUs, this.durationUs, j9, j10, extractingLoadable.bytesLoaded, iOException, zIsLoadableExceptionFatal);
        copyLengthFromLoader(extractingLoadable);
        if (zIsLoadableExceptionFatal) {
            return 3;
        }
        int extractedSamplesCount = getExtractedSamplesCount();
        if (extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad) {
            extractingLoadable2 = extractingLoadable;
            z8 = true;
        } else {
            extractingLoadable2 = extractingLoadable;
            z8 = false;
        }
        if (configureRetry(extractingLoadable2, extractedSamplesCount)) {
            return z8 ? 1 : 0;
        }
        return 2;
    }
}
