package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* loaded from: classes.dex */
public final class SingleSampleMediaSource implements MediaSource {
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final Format format;
    private final int minLoadableRetryCount;
    private final Timeline timeline;
    private final boolean treatLoadErrorsAsEndOfStream;

    @Deprecated
    public interface EventListener {
        void onLoadError(int i9, IOException iOException);
    }

    public static final class EventListenerWrapper implements MediaSourceEventListener {
        private final EventListener eventListener;
        private final int eventSourceId;

        public EventListenerWrapper(EventListener eventListener, int i9) {
            this.eventListener = (EventListener) Assertions.checkNotNull(eventListener);
            this.eventSourceId = i9;
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
            this.eventListener.onLoadError(this.eventSourceId, iOException);
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11) {
        }

        @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int i9, long j9, long j10) {
        }
    }

    public static final class Factory {
        private final DataSource.Factory dataSourceFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount = 3;
        private boolean treatLoadErrorsAsEndOfStream;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(factory);
        }

        public SingleSampleMediaSource createMediaSource(Uri uri, Format format, long j9) {
            return createMediaSource(uri, format, j9, null, null);
        }

        public Factory setMinLoadableRetryCount(int i9) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i9;
            return this;
        }

        public Factory setTreatLoadErrorsAsEndOfStream(boolean z8) {
            Assertions.checkState(!this.isCreateCalled);
            this.treatLoadErrorsAsEndOfStream = z8;
            return this;
        }

        public SingleSampleMediaSource createMediaSource(Uri uri, Format format, long j9, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            return new SingleSampleMediaSource(uri, this.dataSourceFactory, format, j9, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.treatLoadErrorsAsEndOfStream);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new SingleSampleMediaPeriod(this.dataSpec, this.dataSourceFactory, this.format, this.durationUs, this.minLoadableRetryCount, this.eventDispatcher, this.treatLoadErrorsAsEndOfStream);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z8, MediaSource.Listener listener) {
        listener.onSourceInfoRefreshed(this, this.timeline, null);
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((SingleSampleMediaPeriod) mediaPeriod).release();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource
    public void releaseSource() {
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, DataSource.Factory factory, Format format, long j9) {
        this(uri, factory, format, j9, 3);
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, DataSource.Factory factory, Format format, long j9, int i9) {
        this(uri, factory, format, j9, i9, null, null, false);
    }

    @Deprecated
    public SingleSampleMediaSource(Uri uri, DataSource.Factory factory, Format format, long j9, int i9, Handler handler, EventListener eventListener, int i10, boolean z8) {
        this(uri, factory, format, j9, i9, handler, eventListener == null ? null : new EventListenerWrapper(eventListener, i10), z8);
    }

    private SingleSampleMediaSource(Uri uri, DataSource.Factory factory, Format format, long j9, int i9, Handler handler, MediaSourceEventListener mediaSourceEventListener, boolean z8) {
        this.dataSourceFactory = factory;
        this.format = format;
        this.durationUs = j9;
        this.minLoadableRetryCount = i9;
        this.treatLoadErrorsAsEndOfStream = z8;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler, mediaSourceEventListener);
        this.dataSpec = new DataSpec(uri);
        this.timeline = new SinglePeriodTimeline(j9, true, false);
    }
}
