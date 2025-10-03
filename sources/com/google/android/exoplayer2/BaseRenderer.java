package com.google.android.exoplayer2;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;

/* loaded from: classes.dex */
public abstract class BaseRenderer implements Renderer, RendererCapabilities {
    private RendererConfiguration configuration;
    private int index;
    private boolean readEndOfStream = true;
    private int state;
    private SampleStream stream;
    private boolean streamIsFinal;
    private long streamOffsetUs;
    private final int trackType;

    public BaseRenderer(int i9) {
        this.trackType = i9;
    }

    public static boolean supportsFormatDrm(DrmSessionManager<?> drmSessionManager, DrmInitData drmInitData) {
        if (drmInitData == null) {
            return true;
        }
        if (drmSessionManager == null) {
            return false;
        }
        return drmSessionManager.canAcquireSession(drmInitData);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void disable() {
        Assertions.checkState(this.state == 1);
        this.state = 0;
        this.stream = null;
        this.streamIsFinal = false;
        onDisabled();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j9, boolean z8, long j10) {
        Assertions.checkState(this.state == 0);
        this.configuration = rendererConfiguration;
        this.state = 1;
        onEnabled(z8);
        replaceStream(formatArr, sampleStream, j10);
        onPositionReset(j9, z8);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final RendererCapabilities getCapabilities() {
        return this;
    }

    public final RendererConfiguration getConfiguration() {
        return this.configuration;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public MediaClock getMediaClock() {
        return null;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final SampleStream getStream() {
        return this.stream;
    }

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
    public final int getTrackType() {
        return this.trackType;
    }

    @Override // com.google.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i9, Object obj) {
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final boolean hasReadStreamToEnd() {
        return this.readEndOfStream;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final boolean isCurrentStreamFinal() {
        return this.streamIsFinal;
    }

    public final boolean isSourceReady() {
        return this.readEndOfStream ? this.streamIsFinal : this.stream.isReady();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void maybeThrowStreamError() {
        this.stream.maybeThrowError();
    }

    public void onDisabled() {
    }

    public void onEnabled(boolean z8) {
    }

    public void onPositionReset(long j9, boolean z8) {
    }

    public void onStarted() {
    }

    public void onStopped() {
    }

    public void onStreamChanged(Format[] formatArr, long j9) {
    }

    public final int readSource(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z8) {
        int data = this.stream.readData(formatHolder, decoderInputBuffer, z8);
        if (data == -4) {
            if (decoderInputBuffer.isEndOfStream()) {
                this.readEndOfStream = true;
                return this.streamIsFinal ? -4 : -3;
            }
            decoderInputBuffer.timeUs += this.streamOffsetUs;
        } else if (data == -5) {
            Format format = formatHolder.format;
            long j9 = format.subsampleOffsetUs;
            if (j9 != Long.MAX_VALUE) {
                formatHolder.format = format.copyWithSubsampleOffsetUs(j9 + this.streamOffsetUs);
            }
        }
        return data;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void replaceStream(Format[] formatArr, SampleStream sampleStream, long j9) {
        Assertions.checkState(!this.streamIsFinal);
        this.stream = sampleStream;
        this.readEndOfStream = false;
        this.streamOffsetUs = j9;
        onStreamChanged(formatArr, j9);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void resetPosition(long j9) {
        this.streamIsFinal = false;
        this.readEndOfStream = false;
        onPositionReset(j9, false);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void setCurrentStreamFinal() {
        this.streamIsFinal = true;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void setIndex(int i9) {
        this.index = i9;
    }

    public int skipSource(long j9) {
        return this.stream.skipData(j9 - this.streamOffsetUs);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void start() {
        Assertions.checkState(this.state == 1);
        this.state = 2;
        onStarted();
    }

    @Override // com.google.android.exoplayer2.Renderer
    public final void stop() {
        Assertions.checkState(this.state == 2);
        this.state = 1;
        onStopped();
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public int supportsMixedMimeTypeAdaptation() {
        return 0;
    }
}
