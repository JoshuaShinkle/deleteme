package com.perfectcorp.ycl.pages.live;

import android.os.SystemClock;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import com.perfectcorp.utility.C4507b;

/* renamed from: com.perfectcorp.ycl.pages.live.s */
/* loaded from: classes2.dex */
public final class C4599s implements LoadControl {

    /* renamed from: a */
    public final DefaultAllocator f16172a;

    /* renamed from: b */
    public final long f16173b;

    /* renamed from: c */
    public final long f16174c;

    /* renamed from: d */
    public final long f16175d;

    /* renamed from: e */
    public final long f16176e;

    /* renamed from: f */
    public final PriorityTaskManager f16177f;

    /* renamed from: g */
    public int f16178g;

    /* renamed from: h */
    public boolean f16179h;

    /* renamed from: i */
    public int f16180i;

    /* renamed from: j */
    public long f16181j;

    public C4599s(long j9) {
        this(new DefaultAllocator(true, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE), j9);
    }

    /* renamed from: a */
    public final int m18336a(long j9) {
        if (j9 > this.f16174c) {
            return 0;
        }
        return j9 < this.f16173b ? 2 : 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0045  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m18337b(boolean z8) {
        boolean z9;
        if (!z8) {
            return false;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f16180i == 0 || jElapsedRealtime - this.f16181j < SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS) {
            C4507b.m18108i("rebuffer twice within 30 seconds");
            this.f16180i++;
            C4507b.m18108i("rebuffer counts: " + this.f16180i);
            z9 = this.f16180i == 6;
        }
        C4507b.m18108i("rebuffer at: " + jElapsedRealtime);
        this.f16181j = jElapsedRealtime;
        return z9;
    }

    /* renamed from: c */
    public final void m18338c(boolean z8) {
        this.f16178g = 0;
        PriorityTaskManager priorityTaskManager = this.f16177f;
        if (priorityTaskManager != null && this.f16179h) {
            priorityTaskManager.remove(0);
        }
        this.f16179h = false;
        if (z8) {
            this.f16172a.reset();
        }
        this.f16180i = 0;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public Allocator getAllocator() {
        return this.f16172a;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public long getBackBufferDurationUs() {
        return 0L;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onPrepared() {
        m18338c(false);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onReleased() {
        m18338c(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onStopped() {
        m18338c(true);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.f16178g = 0;
        for (int i9 = 0; i9 < rendererArr.length; i9++) {
            if (trackSelectionArray.get(i9) != null) {
                this.f16178g += Util.getDefaultBufferSize(rendererArr[i9].getTrackType());
            }
        }
        this.f16172a.setTargetBufferSize(this.f16178g);
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean retainBackBufferFromKeyframe() {
        return false;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldContinueLoading(long j9, float f9) {
        int iM18336a = m18336a(j9);
        boolean z8 = this.f16179h;
        boolean z9 = iM18336a == 2;
        this.f16179h = z9;
        PriorityTaskManager priorityTaskManager = this.f16177f;
        if (priorityTaskManager != null && z9 != z8) {
            if (z9) {
                priorityTaskManager.add(0);
            } else {
                priorityTaskManager.remove(0);
            }
        }
        return this.f16179h;
    }

    @Override // com.google.android.exoplayer2.LoadControl
    public boolean shouldStartPlayback(long j9, float f9, boolean z8) {
        long j10 = this.f16175d + (z8 ? this.f16176e * (this.f16180i / 2) : 0L);
        return j10 <= 0 || j9 >= j10;
    }

    public C4599s(DefaultAllocator defaultAllocator, long j9) {
        this(defaultAllocator, 15000, DefaultLoadControl.DEFAULT_MAX_BUFFER_MS, j9, 1000L);
    }

    public C4599s(DefaultAllocator defaultAllocator, int i9, int i10, long j9, long j10) {
        this(defaultAllocator, i9, i10, j9, j10, null);
    }

    public C4599s(DefaultAllocator defaultAllocator, int i9, int i10, long j9, long j10, PriorityTaskManager priorityTaskManager) {
        this.f16180i = 0;
        this.f16181j = 0L;
        this.f16172a = defaultAllocator;
        this.f16173b = i9 * 1000;
        this.f16174c = i10 * 1000;
        this.f16175d = j9 * 1000;
        this.f16176e = j10 * 1000;
        this.f16177f = priorityTaskManager;
    }
}
