package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes.dex */
public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_MAX_BUFFER_MS = 30000;
    public static final int DEFAULT_MIN_BUFFER_MS = 15000;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = true;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    private final DefaultAllocator allocator;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private boolean isBuffering;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final PriorityTaskManager priorityTaskManager;
    private final int targetBufferBytesOverwrite;
    private int targetBufferSize;

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE));
    }

    private void reset(boolean z8) {
        this.targetBufferSize = 0;
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null && this.isBuffering) {
            priorityTaskManager.remove(0);
        }
        this.isBuffering = false;
        if (z8) {
            this.allocator.reset();
        }
    }

    public int calculateTargetBufferSize(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int defaultBufferSize = 0;
        for (int i9 = 0; i9 < rendererArr.length; i9++) {
            if (trackSelectionArray.get(i9) != null) {
                defaultBufferSize += Util.getDefaultBufferSize(rendererArr[i9].getTrackType());
            }
        }
        return defaultBufferSize;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public Allocator getAllocator() {
        return this.allocator;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public long getBackBufferDurationUs() {
        return 0L;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onPrepared() {
        reset(false);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onReleased() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onStopped() {
        reset(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        int iCalculateTargetBufferSize = this.targetBufferBytesOverwrite;
        if (iCalculateTargetBufferSize == -1) {
            iCalculateTargetBufferSize = calculateTargetBufferSize(rendererArr, trackSelectionArray);
        }
        this.targetBufferSize = iCalculateTargetBufferSize;
        this.allocator.setTargetBufferSize(iCalculateTargetBufferSize);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldContinueLoading(long j9, float f9) {
        boolean z8;
        boolean z9 = true;
        boolean z10 = this.allocator.getTotalBytesAllocated() >= this.targetBufferSize;
        boolean z11 = this.isBuffering;
        if (this.prioritizeTimeOverSizeThresholds) {
            if (j9 >= this.minBufferUs && (j9 > this.maxBufferUs || !z11 || z10)) {
                z9 = false;
            }
            this.isBuffering = z9;
        } else {
            if (z10 || (j9 >= this.minBufferUs && (j9 > this.maxBufferUs || !z11))) {
                z9 = false;
            }
            this.isBuffering = z9;
        }
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null && (z8 = this.isBuffering) != z11) {
            if (z8) {
                priorityTaskManager.add(0);
            } else {
                priorityTaskManager.remove(0);
            }
        }
        return this.isBuffering;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldStartPlayback(long j9, float f9, boolean z8) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(j9, f9);
        long j10 = z8 ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        return j10 <= 0 || playoutDurationForMediaDuration >= j10 || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= this.targetBufferSize);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, 15000, DEFAULT_MAX_BUFFER_MS, DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000, -1, true);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i9, int i10, int i11, int i12, int i13, boolean z8) {
        this(defaultAllocator, i9, i10, i11, i12, i13, z8, null);
    }

    public DefaultLoadControl(DefaultAllocator defaultAllocator, int i9, int i10, int i11, int i12, int i13, boolean z8, PriorityTaskManager priorityTaskManager) {
        this.allocator = defaultAllocator;
        this.minBufferUs = i9 * 1000;
        this.maxBufferUs = i10 * 1000;
        this.bufferForPlaybackUs = i11 * 1000;
        this.bufferForPlaybackAfterRebufferUs = i12 * 1000;
        this.targetBufferBytesOverwrite = i13;
        this.prioritizeTimeOverSizeThresholds = z8;
        this.priorityTaskManager = priorityTaskManager;
    }
}
