package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* loaded from: classes.dex */
public interface MediaSourceEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        private final MediaSourceEventListener listener;
        private final long mediaTimeOffsetMs;

        public EventDispatcher(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            this(handler, mediaSourceEventListener, 0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long adjustMediaTime(long j9) {
            long jUsToMs = C3322C.usToMs(j9);
            return jUsToMs == C3322C.TIME_UNSET ? C3322C.TIME_UNSET : this.mediaTimeOffsetMs + jUsToMs;
        }

        public EventDispatcher copyWithMediaTimeOffsetMs(long j9) {
            return new EventDispatcher(this.handler, this.listener, j9);
        }

        public void downstreamFormatChanged(final int i9, final Format format, final int i10, final Object obj, final long j9) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.6
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onDownstreamFormatChanged(i9, format, i10, obj, EventDispatcher.this.adjustMediaTime(j9));
                }
            });
        }

        public void loadCanceled(DataSpec dataSpec, int i9, long j9, long j10, long j11) {
            loadCanceled(dataSpec, i9, -1, null, 0, null, C3322C.TIME_UNSET, C3322C.TIME_UNSET, j9, j10, j11);
        }

        public void loadCompleted(DataSpec dataSpec, int i9, long j9, long j10, long j11) {
            loadCompleted(dataSpec, i9, -1, null, 0, null, C3322C.TIME_UNSET, C3322C.TIME_UNSET, j9, j10, j11);
        }

        public void loadError(DataSpec dataSpec, int i9, long j9, long j10, long j11, IOException iOException, boolean z8) {
            loadError(dataSpec, i9, -1, null, 0, null, C3322C.TIME_UNSET, C3322C.TIME_UNSET, j9, j10, j11, iOException, z8);
        }

        public void loadStarted(DataSpec dataSpec, int i9, long j9) {
            loadStarted(dataSpec, i9, -1, null, 0, null, C3322C.TIME_UNSET, C3322C.TIME_UNSET, j9);
        }

        public void upstreamDiscarded(final int i9, final long j9, final long j10) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.5
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onUpstreamDiscarded(i9, EventDispatcher.this.adjustMediaTime(j9), EventDispatcher.this.adjustMediaTime(j10));
                }
            });
        }

        public EventDispatcher(Handler handler, MediaSourceEventListener mediaSourceEventListener, long j9) {
            this.handler = mediaSourceEventListener != null ? (Handler) Assertions.checkNotNull(handler) : null;
            this.listener = mediaSourceEventListener;
            this.mediaTimeOffsetMs = j9;
        }

        public void loadCanceled(final DataSpec dataSpec, final int i9, final int i10, final Format format, final int i11, final Object obj, final long j9, final long j10, final long j11, final long j12, final long j13) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.3
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onLoadCanceled(dataSpec, i9, i10, format, i11, obj, EventDispatcher.this.adjustMediaTime(j9), EventDispatcher.this.adjustMediaTime(j10), j11, j12, j13);
                }
            });
        }

        public void loadCompleted(final DataSpec dataSpec, final int i9, final int i10, final Format format, final int i11, final Object obj, final long j9, final long j10, final long j11, final long j12, final long j13) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.2
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onLoadCompleted(dataSpec, i9, i10, format, i11, obj, EventDispatcher.this.adjustMediaTime(j9), EventDispatcher.this.adjustMediaTime(j10), j11, j12, j13);
                }
            });
        }

        public void loadError(final DataSpec dataSpec, final int i9, final int i10, final Format format, final int i11, final Object obj, final long j9, final long j10, final long j11, final long j12, final long j13, final IOException iOException, final boolean z8) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.4
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onLoadError(dataSpec, i9, i10, format, i11, obj, EventDispatcher.this.adjustMediaTime(j9), EventDispatcher.this.adjustMediaTime(j10), j11, j12, j13, iOException, z8);
                }
            });
        }

        public void loadStarted(final DataSpec dataSpec, final int i9, final int i10, final Format format, final int i11, final Object obj, final long j9, final long j10, final long j11) {
            Handler handler;
            if (this.listener == null || (handler = this.handler) == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    EventDispatcher.this.listener.onLoadStarted(dataSpec, i9, i10, format, i11, obj, EventDispatcher.this.adjustMediaTime(j9), EventDispatcher.this.adjustMediaTime(j10), j11);
                }
            });
        }
    }

    void onDownstreamFormatChanged(int i9, Format format, int i10, Object obj, long j9);

    void onLoadCanceled(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13);

    void onLoadCompleted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13);

    void onLoadError(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13, IOException iOException, boolean z8);

    void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11);

    void onUpstreamDiscarded(int i9, long j9, long j10);
}
