package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* loaded from: classes.dex */
public final class ExtractorMediaSource implements MediaSource, ExtractorMediaPeriod.Listener {
    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_LIVE = 6;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_ON_DEMAND = 3;
    public static final int MIN_RETRY_COUNT_DEFAULT_FOR_MEDIA = -1;
    private final int continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource.Factory dataSourceFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final ExtractorsFactory extractorsFactory;
    private final int minLoadableRetryCount;
    private MediaSource.Listener sourceListener;
    private long timelineDurationUs;
    private boolean timelineIsSeekable;
    private final Uri uri;

    @Deprecated
    public interface EventListener {
        void onLoadError(IOException iOException);
    }

    public static final class EventListenerWrapper implements MediaSourceEventListener {
        private final EventListener eventListener;

        public EventListenerWrapper(EventListener eventListener) {
            this.eventListener = (EventListener) Assertions.checkNotNull(eventListener);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onDownstreamFormatChanged(int i9, Format format, int i10, Object obj, long j9) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCanceled(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCompleted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadError(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13, IOException iOException, boolean z8) {
            this.eventListener.onLoadError(iOException);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int i9, long j9, long j10) {
        }
    }

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private String customCacheKey;
        private final DataSource.Factory dataSourceFactory;
        private ExtractorsFactory extractorsFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount = -1;
        private int continueLoadingCheckIntervalBytes = ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{3};
        }

        public Factory setContinueLoadingCheckIntervalBytes(int i9) {
            Assertions.checkState(!this.isCreateCalled);
            this.continueLoadingCheckIntervalBytes = i9;
            return this;
        }

        public Factory setCustomCacheKey(String str) {
            Assertions.checkState(!this.isCreateCalled);
            this.customCacheKey = str;
            return this;
        }

        public Factory setExtractorsFactory(ExtractorsFactory extractorsFactory) {
            Assertions.checkState(!this.isCreateCalled);
            this.extractorsFactory = extractorsFactory;
            return this;
        }

        public Factory setMinLoadableRetryCount(int i9) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i9;
            return this;
        }

        public ExtractorMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, (Handler) null, (MediaSourceEventListener) null);
        }

        @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public ExtractorMediaSource createMediaSource(Uri uri, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.extractorsFactory == null) {
                this.extractorsFactory = new DefaultExtractorsFactory();
            }
            return new ExtractorMediaSource(uri, this.dataSourceFactory, this.extractorsFactory, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
        }
    }

    private void notifySourceInfoRefreshed(long j9, boolean z8) {
        this.timelineDurationUs = j9;
        this.timelineIsSeekable = z8;
        this.sourceListener.onSourceInfoRefreshed(this, new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false), null);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new ExtractorMediaPeriod(this.uri, this.dataSourceFactory.createDataSource(), this.extractorsFactory.createExtractors(), this.minLoadableRetryCount, this.eventDispatcher, this, allocator, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // com.google.android.exoplayer2.source.ExtractorMediaPeriod.Listener
    public void onSourceInfoRefreshed(long j9, boolean z8) {
        if (j9 == C3322C.TIME_UNSET) {
            j9 = this.timelineDurationUs;
        }
        if (this.timelineDurationUs == j9 && this.timelineIsSeekable == z8) {
            return;
        }
        notifySourceInfoRefreshed(j9, z8);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        this.sourceListener = listener;
        notifySourceInfoRefreshed(C3322C.TIME_UNSET, false);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExtractorMediaPeriod) mediaPeriod).release();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        this.sourceListener = null;
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, Handler handler, EventListener eventListener) {
        this(uri, factory, extractorsFactory, handler, eventListener, null);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, Handler handler, EventListener eventListener, String str) {
        this(uri, factory, extractorsFactory, -1, handler, eventListener, str, DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, int i9, Handler handler, EventListener eventListener, String str, int i10) {
        this(uri, factory, extractorsFactory, i9, handler, eventListener == null ? null : new EventListenerWrapper(eventListener), str, i10);
    }

    private ExtractorMediaSource(Uri uri, DataSource.Factory factory, ExtractorsFactory extractorsFactory, int i9, Handler handler, MediaSourceEventListener mediaSourceEventListener, String str, int i10) {
        this.uri = uri;
        this.dataSourceFactory = factory;
        this.extractorsFactory = extractorsFactory;
        this.minLoadableRetryCount = i9;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler, mediaSourceEventListener);
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i10;
    }
}
