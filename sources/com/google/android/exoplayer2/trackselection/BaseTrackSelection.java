package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseTrackSelection implements TrackSelection {
    private final long[] blacklistUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    protected final int[] tracks;

    public static final class DecreasingBandwidthComparator implements Comparator<Format> {
        private DecreasingBandwidthComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Format format, Format format2) {
            return format2.bitrate - format.bitrate;
        }
    }

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        int i9 = 0;
        Assertions.checkState(iArr.length > 0);
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        int length = iArr.length;
        this.length = length;
        this.formats = new Format[length];
        for (int i10 = 0; i10 < iArr.length; i10++) {
            this.formats[i10] = trackGroup.getFormat(iArr[i10]);
        }
        Arrays.sort(this.formats, new DecreasingBandwidthComparator());
        this.tracks = new int[this.length];
        while (true) {
            int i11 = this.length;
            if (i9 >= i11) {
                this.blacklistUntilTimes = new long[i11];
                return;
            } else {
                this.tracks[i9] = trackGroup.indexOf(this.formats[i9]);
                i9++;
            }
        }
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final boolean blacklist(int i9, long j9) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zIsBlacklisted = isBlacklisted(i9, jElapsedRealtime);
        int i10 = 0;
        while (i10 < this.length && !zIsBlacklisted) {
            zIsBlacklisted = (i10 == i9 || isBlacklisted(i10, jElapsedRealtime)) ? false : true;
            i10++;
        }
        if (!zIsBlacklisted) {
            return false;
        }
        long[] jArr = this.blacklistUntilTimes;
        jArr[i9] = Math.max(jArr[i9], jElapsedRealtime + j9);
        return true;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void disable() {
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void enable() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
        return this.group == baseTrackSelection.group && Arrays.equals(this.tracks, baseTrackSelection.tracks);
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public int evaluateQueueSize(long j9, List<? extends MediaChunk> list) {
        return list.size();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final Format getFormat(int i9) {
        return this.formats[i9];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int getIndexInTrackGroup(int i9) {
        return this.tracks[i9];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (System.identityHashCode(this.group) * 31) + Arrays.hashCode(this.tracks);
        }
        return this.hashCode;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int indexOf(Format format) {
        for (int i9 = 0; i9 < this.length; i9++) {
            if (this.formats[i9] == format) {
                return i9;
            }
        }
        return -1;
    }

    public final boolean isBlacklisted(int i9, long j9) {
        return this.blacklistUntilTimes[i9] > j9;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int length() {
        return this.tracks.length;
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public void onPlaybackSpeed(float f9) {
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelection
    public final int indexOf(int i9) {
        for (int i10 = 0; i10 < this.length; i10++) {
            if (this.tracks[i10] == i9) {
                return i10;
            }
        }
        return -1;
    }
}
