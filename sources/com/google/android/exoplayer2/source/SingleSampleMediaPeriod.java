package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback<SourceLoadable> {
    private static final int INITIAL_SAMPLE_SIZE = 1024;
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private int errorCount;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    final Format format;
    boolean loadingFinished;
    boolean loadingSucceeded;
    private final int minLoadableRetryCount;
    byte[] sampleData;
    int sampleSize;
    private final TrackGroupArray tracks;
    final boolean treatLoadErrorsAsEndOfStream;
    private final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    final Loader loader = new Loader("Loader:SingleSampleMediaPeriod");

    public final class SampleStreamImpl implements SampleStream {
        private static final int STREAM_STATE_END_OF_STREAM = 2;
        private static final int STREAM_STATE_SEND_FORMAT = 0;
        private static final int STREAM_STATE_SEND_SAMPLE = 1;
        private boolean formatSent;
        private int streamState;

        private SampleStreamImpl() {
        }

        private void sendFormat() {
            if (this.formatSent) {
                return;
            }
            SingleSampleMediaPeriod.this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(SingleSampleMediaPeriod.this.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, null, 0L);
            this.formatSent = true;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return SingleSampleMediaPeriod.this.loadingFinished;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            if (singleSampleMediaPeriod.treatLoadErrorsAsEndOfStream) {
                return;
            }
            singleSampleMediaPeriod.loader.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
            int i9 = this.streamState;
            if (i9 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            if (z8 || i9 == 0) {
                formatHolder.format = SingleSampleMediaPeriod.this.format;
                this.streamState = 1;
                return -5;
            }
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            if (!singleSampleMediaPeriod.loadingFinished) {
                return -3;
            }
            if (singleSampleMediaPeriod.loadingSucceeded) {
                decoderInputBuffer.timeUs = 0L;
                decoderInputBuffer.addFlag(1);
                decoderInputBuffer.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                ByteBuffer byteBuffer = decoderInputBuffer.data;
                SingleSampleMediaPeriod singleSampleMediaPeriod2 = SingleSampleMediaPeriod.this;
                byteBuffer.put(singleSampleMediaPeriod2.sampleData, 0, singleSampleMediaPeriod2.sampleSize);
                sendFormat();
            } else {
                decoderInputBuffer.addFlag(4);
            }
            this.streamState = 2;
            return -4;
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int skipData(long j9) {
            if (j9 <= 0 || this.streamState == 2) {
                return 0;
            }
            this.streamState = 2;
            sendFormat();
            return 1;
        }
    }

    public static final class SourceLoadable implements Loader.Loadable {
        private final DataSource dataSource;
        public final DataSpec dataSpec;
        private byte[] sampleData;
        private int sampleSize;

        public SourceLoadable(DataSpec dataSpec, DataSource dataSource) {
            this.dataSpec = dataSpec;
            this.dataSource = dataSource;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return false;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void load() {
            int i9 = 0;
            this.sampleSize = 0;
            try {
                this.dataSource.open(this.dataSpec);
                while (i9 != -1) {
                    int i10 = this.sampleSize + i9;
                    this.sampleSize = i10;
                    byte[] bArr = this.sampleData;
                    if (bArr == null) {
                        this.sampleData = new byte[1024];
                    } else if (i10 == bArr.length) {
                        this.sampleData = Arrays.copyOf(bArr, bArr.length * 2);
                    }
                    DataSource dataSource = this.dataSource;
                    byte[] bArr2 = this.sampleData;
                    int i11 = this.sampleSize;
                    i9 = dataSource.read(bArr2, i11, bArr2.length - i11);
                }
            } finally {
                Util.closeQuietly(this.dataSource);
            }
        }
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec, DataSource.Factory factory, Format format, long j9, int i9, MediaSourceEventListener.EventDispatcher eventDispatcher, boolean z8) {
        this.dataSpec = dataSpec;
        this.dataSourceFactory = factory;
        this.format = format;
        this.durationUs = j9;
        this.minLoadableRetryCount = i9;
        this.eventDispatcher = eventDispatcher;
        this.treatLoadErrorsAsEndOfStream = z8;
        this.tracks = new TrackGroupArray(new TrackGroup(format));
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        this.eventDispatcher.loadStarted(this.dataSpec, 1, -1, this.format, 0, null, 0L, this.durationUs, this.loader.startLoading(new SourceLoadable(this.dataSpec, this.dataSourceFactory.createDataSource()), this, this.minLoadableRetryCount));
        return true;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.loadingFinished ? Long.MIN_VALUE : 0L;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return (this.loadingFinished || this.loader.isLoading()) ? Long.MIN_VALUE : 0L;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
    }

    public void release() {
        this.loader.release();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        for (int i9 = 0; i9 < this.sampleStreams.size(); i9++) {
            this.sampleStreams.get(i9).reset();
        }
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        for (int i9 = 0; i9 < trackSelectionArr.length; i9++) {
            SampleStream sampleStream = sampleStreamArr[i9];
            if (sampleStream != null && (trackSelectionArr[i9] == null || !zArr[i9])) {
                this.sampleStreams.remove(sampleStream);
                sampleStreamArr[i9] = null;
            }
            if (sampleStreamArr[i9] == null && trackSelectionArr[i9] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i9] = sampleStreamImpl;
                zArr2[i9] = true;
            }
        }
        return j9;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(SourceLoadable sourceLoadable, long j9, long j10, boolean z8) {
        this.eventDispatcher.loadCanceled(sourceLoadable.dataSpec, 1, -1, null, 0, null, 0L, this.durationUs, j9, j10, sourceLoadable.sampleSize);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(SourceLoadable sourceLoadable, long j9, long j10) {
        this.eventDispatcher.loadCompleted(sourceLoadable.dataSpec, 1, -1, this.format, 0, null, 0L, this.durationUs, j9, j10, sourceLoadable.sampleSize);
        this.sampleSize = sourceLoadable.sampleSize;
        this.sampleData = sourceLoadable.sampleData;
        this.loadingFinished = true;
        this.loadingSucceeded = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(SourceLoadable sourceLoadable, long j9, long j10, IOException iOException) {
        int i9 = this.errorCount + 1;
        this.errorCount = i9;
        boolean z8 = this.treatLoadErrorsAsEndOfStream && i9 >= this.minLoadableRetryCount;
        this.eventDispatcher.loadError(sourceLoadable.dataSpec, 1, -1, this.format, 0, null, 0L, this.durationUs, j9, j10, sourceLoadable.sampleSize, iOException, z8);
        if (!z8) {
            return 0;
        }
        this.loadingFinished = true;
        return 2;
    }
}
