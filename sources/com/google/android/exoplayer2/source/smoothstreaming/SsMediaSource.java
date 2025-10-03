package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class SsMediaSource implements MediaSource, Loader.Callback<ParsingLoadable<SsManifest>> {
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_MS = 30000;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private static final int MINIMUM_MANIFEST_REFRESH_PERIOD_MS = 5000;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private final SsChunkSource.Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final long livePresentationDelayMs;
    private SsManifest manifest;
    private DataSource manifestDataSource;
    private final DataSource.Factory manifestDataSourceFactory;
    private long manifestLoadStartTimestamp;
    private Loader manifestLoader;
    private LoaderErrorThrower manifestLoaderErrorThrower;
    private final ParsingLoadable.Parser<? extends SsManifest> manifestParser;
    private Handler manifestRefreshHandler;
    private final Uri manifestUri;
    private final ArrayList<SsMediaPeriod> mediaPeriods;
    private final int minLoadableRetryCount;
    private final boolean sideloadedManifest;
    private MediaSource.Listener sourceListener;

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private final SsChunkSource.Factory chunkSourceFactory;
        private boolean isCreateCalled;
        private final DataSource.Factory manifestDataSourceFactory;
        private ParsingLoadable.Parser<? extends SsManifest> manifestParser;
        private int minLoadableRetryCount = 3;
        private long livePresentationDelayMs = SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();

        public Factory(SsChunkSource.Factory factory, DataSource.Factory factory2) {
            this.chunkSourceFactory = (SsChunkSource.Factory) Assertions.checkNotNull(factory);
            this.manifestDataSourceFactory = factory2;
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{1};
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory);
            return this;
        }

        public Factory setLivePresentationDelayMs(long j9) {
            Assertions.checkState(!this.isCreateCalled);
            this.livePresentationDelayMs = j9;
            return this;
        }

        public Factory setManifestParser(ParsingLoadable.Parser<? extends SsManifest> parser) {
            Assertions.checkState(!this.isCreateCalled);
            this.manifestParser = (ParsingLoadable.Parser) Assertions.checkNotNull(parser);
            return this;
        }

        public Factory setMinLoadableRetryCount(int i9) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i9;
            return this;
        }

        public SsMediaSource createMediaSource(SsManifest ssManifest, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkArgument(!ssManifest.isLive);
            this.isCreateCalled = true;
            return new SsMediaSource(ssManifest, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, handler, mediaSourceEventListener);
        }

        public SsMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, (Handler) null, (MediaSourceEventListener) null);
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public SsMediaSource createMediaSource(Uri uri, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.manifestParser == null) {
                this.manifestParser = new SsManifestParser();
            }
            return new SsMediaSource(null, (Uri) Assertions.checkNotNull(uri), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, handler, mediaSourceEventListener);
        }
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.smoothstreaming");
    }

    private void processManifest() {
        SinglePeriodTimeline singlePeriodTimeline;
        for (int i9 = 0; i9 < this.mediaPeriods.size(); i9++) {
            this.mediaPeriods.get(i9).updateManifest(this.manifest);
        }
        long jMax = Long.MIN_VALUE;
        long jMax2 = Long.MAX_VALUE;
        for (SsManifest.StreamElement streamElement : this.manifest.streamElements) {
            if (streamElement.chunkCount > 0) {
                jMax2 = Math.min(jMax2, streamElement.getStartTimeUs(0));
                jMax = Math.max(jMax, streamElement.getStartTimeUs(streamElement.chunkCount - 1) + streamElement.getChunkDurationUs(streamElement.chunkCount - 1));
            }
        }
        if (jMax2 == Long.MAX_VALUE) {
            singlePeriodTimeline = new SinglePeriodTimeline(this.manifest.isLive ? -9223372036854775807L : 0L, 0L, 0L, 0L, true, this.manifest.isLive);
        } else {
            SsManifest ssManifest = this.manifest;
            if (ssManifest.isLive) {
                long j9 = ssManifest.dvrWindowLengthUs;
                if (j9 != C3322C.TIME_UNSET && j9 > 0) {
                    jMax2 = Math.max(jMax2, jMax - j9);
                }
                long j10 = jMax2;
                long j11 = jMax - j10;
                long jMsToUs = j11 - C3322C.msToUs(this.livePresentationDelayMs);
                if (jMsToUs < MIN_LIVE_DEFAULT_START_POSITION_US) {
                    jMsToUs = Math.min(MIN_LIVE_DEFAULT_START_POSITION_US, j11 / 2);
                }
                singlePeriodTimeline = new SinglePeriodTimeline(C3322C.TIME_UNSET, j11, j10, jMsToUs, true, true);
            } else {
                long j12 = ssManifest.durationUs;
                long j13 = j12 != C3322C.TIME_UNSET ? j12 : jMax - jMax2;
                singlePeriodTimeline = new SinglePeriodTimeline(jMax2 + j13, j13, jMax2, 0L, true, false);
            }
        }
        this.sourceListener.onSourceInfoRefreshed(this, singlePeriodTimeline, this.manifest);
    }

    private void scheduleManifestRefresh() {
        if (this.manifest.isLive) {
            this.manifestRefreshHandler.postDelayed(new Runnable() { // from class: com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource.1
                @Override // java.lang.Runnable
                public void run() {
                    SsMediaSource.this.startLoadingManifest();
                }
            }, Math.max(0L, (this.manifestLoadStartTimestamp + DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) - SystemClock.elapsedRealtime()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoadingManifest() {
        ParsingLoadable parsingLoadable = new ParsingLoadable(this.manifestDataSource, this.manifestUri, 4, this.manifestParser);
        this.eventDispatcher.loadStarted(parsingLoadable.dataSpec, parsingLoadable.type, this.manifestLoader.startLoading(parsingLoadable, this, this.minLoadableRetryCount));
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        SsMediaPeriod ssMediaPeriod = new SsMediaPeriod(this.manifest, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.eventDispatcher, this.manifestLoaderErrorThrower, allocator);
        this.mediaPeriods.add(ssMediaPeriod);
        return ssMediaPeriod;
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        this.sourceListener = listener;
        if (this.sideloadedManifest) {
            this.manifestLoaderErrorThrower = new LoaderErrorThrower.Dummy();
            processManifest();
            return;
        }
        this.manifestDataSource = this.manifestDataSourceFactory.createDataSource();
        Loader loader = new Loader("Loader:Manifest");
        this.manifestLoader = loader;
        this.manifestLoaderErrorThrower = loader;
        this.manifestRefreshHandler = new Handler();
        startLoadingManifest();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SsMediaPeriod) mediaPeriod).release();
        this.mediaPeriods.remove(mediaPeriod);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        this.sourceListener = null;
        this.manifest = this.sideloadedManifest ? this.manifest : null;
        this.manifestDataSource = null;
        this.manifestLoadStartTimestamp = 0L;
        Loader loader = this.manifestLoader;
        if (loader != null) {
            loader.release();
            this.manifestLoader = null;
        }
        Handler handler = this.manifestRefreshHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.manifestRefreshHandler = null;
        }
    }

    @Deprecated
    public SsMediaSource(SsManifest ssManifest, SsChunkSource.Factory factory, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(ssManifest, factory, 3, handler, mediaSourceEventListener);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(ParsingLoadable<SsManifest> parsingLoadable, long j9, long j10, boolean z8) {
        this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, parsingLoadable.type, j9, j10, parsingLoadable.bytesLoaded());
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(ParsingLoadable<SsManifest> parsingLoadable, long j9, long j10) {
        this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, parsingLoadable.type, j9, j10, parsingLoadable.bytesLoaded());
        this.manifest = parsingLoadable.getResult();
        this.manifestLoadStartTimestamp = j9 - j10;
        processManifest();
        scheduleManifestRefresh();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(ParsingLoadable<SsManifest> parsingLoadable, long j9, long j10, IOException iOException) {
        boolean z8 = iOException instanceof ParserException;
        this.eventDispatcher.loadError(parsingLoadable.dataSpec, parsingLoadable.type, j9, j10, parsingLoadable.bytesLoaded(), iOException, z8);
        return z8 ? 3 : 0;
    }

    @Deprecated
    public SsMediaSource(SsManifest ssManifest, SsChunkSource.Factory factory, int i9, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(ssManifest, null, null, null, factory, new DefaultCompositeSequenceableLoaderFactory(), i9, DEFAULT_LIVE_PRESENTATION_DELAY_MS, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(Uri uri, DataSource.Factory factory, SsChunkSource.Factory factory2, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, factory2, 3, DEFAULT_LIVE_PRESENTATION_DELAY_MS, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(Uri uri, DataSource.Factory factory, SsChunkSource.Factory factory2, int i9, long j9, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, new SsManifestParser(), factory2, i9, j9, handler, mediaSourceEventListener);
    }

    @Deprecated
    public SsMediaSource(Uri uri, DataSource.Factory factory, ParsingLoadable.Parser<? extends SsManifest> parser, SsChunkSource.Factory factory2, int i9, long j9, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(null, uri, factory, parser, factory2, new DefaultCompositeSequenceableLoaderFactory(), i9, j9, handler, mediaSourceEventListener);
    }

    private SsMediaSource(SsManifest ssManifest, Uri uri, DataSource.Factory factory, ParsingLoadable.Parser<? extends SsManifest> parser, SsChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, int i9, long j9, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        Assertions.checkState(ssManifest == null || !ssManifest.isLive);
        this.manifest = ssManifest;
        this.manifestUri = uri == null ? null : SsUtil.fixManifestUri(uri);
        this.manifestDataSourceFactory = factory;
        this.manifestParser = parser;
        this.chunkSourceFactory = factory2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.minLoadableRetryCount = i9;
        this.livePresentationDelayMs = j9;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler, mediaSourceEventListener);
        this.sideloadedManifest = ssManifest != null;
        this.mediaPeriods = new ArrayList<>();
    }
}
