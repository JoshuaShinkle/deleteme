package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;

/* loaded from: classes.dex */
public interface MediaSource {
    public static final String MEDIA_SOURCE_REUSED_ERROR_MESSAGE = "MediaSource instances are not allowed to be reused.";

    public interface Listener {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj);
    }

    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int periodIndex;
        public final long windowSequenceNumber;

        public MediaPeriodId(int i9) {
            this(i9, -1L);
        }

        public MediaPeriodId copyWithPeriodIndex(int i9) {
            return this.periodIndex == i9 ? this : new MediaPeriodId(i9, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || MediaPeriodId.class != obj.getClass()) {
                return false;
            }
            MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
            return this.periodIndex == mediaPeriodId.periodIndex && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber;
        }

        public int hashCode() {
            return ((((((527 + this.periodIndex) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber);
        }

        public boolean isAd() {
            return this.adGroupIndex != -1;
        }

        public MediaPeriodId(int i9, long j9) {
            this(i9, -1, -1, j9);
        }

        public MediaPeriodId(int i9, int i10, int i11, long j9) {
            this.periodIndex = i9;
            this.adGroupIndex = i10;
            this.adIndexInAdGroup = i11;
            this.windowSequenceNumber = j9;
        }
    }

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator);

    void maybeThrowSourceInfoRefreshError();

    void prepareSource(ExoPlayer exoPlayer, boolean z8, Listener listener);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource();
}
