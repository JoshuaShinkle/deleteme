package com.perfectcorp.ycl.pages.live;

import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.GeobFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.metadata.id3.UrlLinkFrame;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/* renamed from: com.perfectcorp.ycl.pages.live.d */
/* loaded from: classes2.dex */
public final class C4584d implements Player.EventListener, MetadataOutput, AudioRendererEventListener, VideoRendererEventListener, AdaptiveMediaSourceEventListener, ExtractorMediaSource.EventListener, DefaultDrmSessionManager.EventListener {

    /* renamed from: f */
    public static final NumberFormat f16091f;

    /* renamed from: b */
    public final MappingTrackSelector f16092b;

    /* renamed from: c */
    public final Timeline.Window f16093c = new Timeline.Window();

    /* renamed from: d */
    public final Timeline.Period f16094d = new Timeline.Period();

    /* renamed from: e */
    public final long f16095e = SystemClock.elapsedRealtime();

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        f16091f = numberFormat;
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
    }

    public C4584d(MappingTrackSelector mappingTrackSelector) {
        this.f16092b = mappingTrackSelector;
    }

    /* renamed from: a */
    public static String m18210a(int i9, int i10) {
        return i9 < 2 ? "N/A" : i10 != 0 ? i10 != 8 ? i10 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    /* renamed from: b */
    public static String m18211b(int i9) {
        return i9 != 0 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "?" : "YES" : "NO_EXCEEDS_CAPABILITIES" : "NO_UNSUPPORTED_DRM" : "NO_UNSUPPORTED_TYPE" : "NO";
    }

    /* renamed from: d */
    public static String m18212d(long j9) {
        return j9 == C3322C.TIME_UNSET ? "?" : f16091f.format(j9 / 1000.0f);
    }

    /* renamed from: e */
    public static String m18213e(TrackSelection trackSelection, TrackGroup trackGroup, int i9) {
        return m18214f((trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i9) == -1) ? false : true);
    }

    /* renamed from: f */
    public static String m18214f(boolean z8) {
        return z8 ? "[X]" : "[ ]";
    }

    /* renamed from: c */
    public final String m18215c() {
        return m18212d(SystemClock.elapsedRealtime() - this.f16095e);
    }

    /* renamed from: g */
    public final void m18216g(String str, Exception exc) {
        Log.e("EventLogger", "internalError [" + m18215c() + ", " + str + "]", exc);
    }

    /* renamed from: h */
    public final void m18217h(Metadata metadata, String str) {
        for (int i9 = 0; i9 < metadata.length(); i9++) {
            Metadata.Entry entry = metadata.get(i9);
            if (entry instanceof TextInformationFrame) {
            } else if (entry instanceof UrlLinkFrame) {
            } else if (entry instanceof PrivFrame) {
            } else if (entry instanceof GeobFrame) {
            } else if (entry instanceof ApicFrame) {
            } else if (entry instanceof CommentFrame) {
            } else if (entry instanceof Id3Frame) {
            } else if (entry instanceof EventMessage) {
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioDecoderInitialized(String str, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioDisabled(DecoderCounters decoderCounters) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioEnabled(DecoderCounters decoderCounters) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioInputFormatChanged(Format format) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioSessionId(int i9) {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioSinkUnderrun(int i9, long j9, long j10) {
        m18216g("audioTrackUnderrun [" + i9 + ", " + j9 + ", " + j10 + "]", null);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int i9, Format format, int i10, Object obj, long j9) {
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysLoaded() {
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysRemoved() {
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysRestored() {
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmSessionManagerError(Exception exc) {
        m18216g("drmSessionManagerError", exc);
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onDroppedFrames(int i9, long j9) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCanceled(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCompleted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
    }

    @Override // com.google.android.exoplayer2.source.ExtractorMediaSource.EventListener
    public void onLoadError(IOException iOException) {
        m18216g("loadError", iOException);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z8) {
    }

    @Override // com.google.android.exoplayer2.metadata.MetadataOutput
    public void onMetadata(Metadata metadata) {
        m18217h(metadata, "  ");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Log.e("EventLogger", "playerFailed [" + m18215c() + "]", exoPlaybackException);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z8, int i9) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity(int i9) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onRenderedFirstFrame(Surface surface) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i9) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onSeekProcessed() {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onShuffleModeEnabledChanged(boolean z8) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj, int i9) {
        int periodCount = timeline.getPeriodCount();
        int windowCount = timeline.getWindowCount();
        for (int i10 = 0; i10 < Math.min(periodCount, 3); i10++) {
            timeline.getPeriod(i10, this.f16094d);
        }
        for (int i11 = 0; i11 < Math.min(windowCount, 3); i11++) {
            timeline.getWindow(i11, this.f16093c);
        }
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = this.f16092b.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo == null) {
            return;
        }
        for (int i9 = 0; i9 < currentMappedTrackInfo.length; i9++) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i9);
            TrackSelection trackSelection = trackSelectionArray.get(i9);
            if (trackGroups.length > 0) {
                for (int i10 = 0; i10 < trackGroups.length; i10++) {
                    TrackGroup trackGroup = trackGroups.get(i10);
                    m18210a(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i9, i10, false));
                    for (int i11 = 0; i11 < trackGroup.length; i11++) {
                        m18213e(trackSelection, trackGroup, i11);
                        m18211b(currentMappedTrackInfo.getTrackFormatSupport(i9, i10, i11));
                    }
                }
                if (trackSelection != null) {
                    int i12 = 0;
                    while (true) {
                        if (i12 >= trackSelection.length()) {
                            break;
                        }
                        Metadata metadata = trackSelection.getFormat(i12).metadata;
                        if (metadata != null) {
                            m18217h(metadata, "      ");
                            break;
                        }
                        i12++;
                    }
                }
            }
        }
        TrackGroupArray unassociatedTrackGroups = currentMappedTrackInfo.getUnassociatedTrackGroups();
        if (unassociatedTrackGroups.length > 0) {
            for (int i13 = 0; i13 < unassociatedTrackGroups.length; i13++) {
                TrackGroup trackGroup2 = unassociatedTrackGroups.get(i13);
                for (int i14 = 0; i14 < trackGroup2.length; i14++) {
                    m18214f(false);
                    m18211b(0);
                }
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int i9, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoDecoderInitialized(String str, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoDisabled(DecoderCounters decoderCounters) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoEnabled(DecoderCounters decoderCounters) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoInputFormatChanged(Format format) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoSizeChanged(int i9, int i10, int i11, float f9) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadError(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13, IOException iOException, boolean z8) {
        m18216g("loadError", iOException);
    }
}
