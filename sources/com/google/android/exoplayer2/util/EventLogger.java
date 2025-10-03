package com.google.android.exoplayer2.util;

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
import com.google.android.exoplayer2.metadata.scte35.SpliceCommand;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/* loaded from: classes.dex */
public class EventLogger implements Player.EventListener, MetadataOutput, AudioRendererEventListener, VideoRendererEventListener, MediaSourceEventListener, AdsMediaSource.EventListener, DefaultDrmSessionManager.EventListener {
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final String TAG = "EventLogger";
    private static final NumberFormat TIME_FORMAT;
    private final MappingTrackSelector trackSelector;
    private final Timeline.Window window = new Timeline.Window();
    private final Timeline.Period period = new Timeline.Period();
    private final long startTimeMs = android.os.SystemClock.elapsedRealtime();

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = numberFormat;
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
    }

    public EventLogger(MappingTrackSelector mappingTrackSelector) {
        this.trackSelector = mappingTrackSelector;
    }

    private static String getAdaptiveSupportString(int i9, int i10) {
        return i9 < 2 ? "N/A" : i10 != 0 ? i10 != 8 ? i10 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    private static String getDiscontinuityReasonString(int i9) {
        return i9 != 0 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "?" : "INTERNAL" : "AD_INSERTION" : "SEEK_ADJUSTMENT" : "SEEK" : "PERIOD_TRANSITION";
    }

    private static String getFormatSupportString(int i9) {
        return i9 != 0 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "?" : "YES" : "NO_EXCEEDS_CAPABILITIES" : "NO_UNSUPPORTED_DRM" : "NO_UNSUPPORTED_TYPE" : "NO";
    }

    private static String getRepeatModeString(int i9) {
        return i9 != 0 ? i9 != 1 ? i9 != 2 ? "?" : "ALL" : "ONE" : "OFF";
    }

    private String getSessionTimeString() {
        return getTimeString(android.os.SystemClock.elapsedRealtime() - this.startTimeMs);
    }

    private static String getStateString(int i9) {
        return i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "?" : "E" : "R" : "B" : "I";
    }

    private static String getTimeString(long j9) {
        return j9 == C3322C.TIME_UNSET ? "?" : TIME_FORMAT.format(j9 / 1000.0f);
    }

    private static String getTimelineChangeReasonString(int i9) {
        return i9 != 0 ? i9 != 1 ? i9 != 2 ? "?" : "DYNAMIC" : "RESET" : "PREPARED";
    }

    private static String getTrackStatusString(TrackSelection trackSelection, TrackGroup trackGroup, int i9) {
        return getTrackStatusString((trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i9) == -1) ? false : true);
    }

    private static String getTrackStatusString(boolean z8) {
        return z8 ? "[X]" : "[ ]";
    }

    private void printInternalError(String str, Exception exc) {
        Log.e(TAG, "internalError [" + getSessionTimeString() + ", " + str + "]", exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i9 = 0; i9 < metadata.length(); i9++) {
            Metadata.Entry entry = metadata.get(i9);
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                Log.d(TAG, str + String.format("%s: value=%s", textInformationFrame.f15312id, textInformationFrame.value));
            } else if (entry instanceof UrlLinkFrame) {
                UrlLinkFrame urlLinkFrame = (UrlLinkFrame) entry;
                Log.d(TAG, str + String.format("%s: url=%s", urlLinkFrame.f15312id, urlLinkFrame.url));
            } else if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                Log.d(TAG, str + String.format("%s: owner=%s", privFrame.f15312id, privFrame.owner));
            } else if (entry instanceof GeobFrame) {
                GeobFrame geobFrame = (GeobFrame) entry;
                Log.d(TAG, str + String.format("%s: mimeType=%s, filename=%s, description=%s", geobFrame.f15312id, geobFrame.mimeType, geobFrame.filename, geobFrame.description));
            } else if (entry instanceof ApicFrame) {
                ApicFrame apicFrame = (ApicFrame) entry;
                Log.d(TAG, str + String.format("%s: mimeType=%s, description=%s", apicFrame.f15312id, apicFrame.mimeType, apicFrame.description));
            } else if (entry instanceof CommentFrame) {
                CommentFrame commentFrame = (CommentFrame) entry;
                Log.d(TAG, str + String.format("%s: language=%s, description=%s", commentFrame.f15312id, commentFrame.language, commentFrame.description));
            } else if (entry instanceof Id3Frame) {
                Log.d(TAG, str + String.format("%s", ((Id3Frame) entry).f15312id));
            } else if (entry instanceof EventMessage) {
                EventMessage eventMessage = (EventMessage) entry;
                Log.d(TAG, str + String.format("EMSG: scheme=%s, id=%d, value=%s", eventMessage.schemeIdUri, Long.valueOf(eventMessage.f15306id), eventMessage.value));
            } else if (entry instanceof SpliceCommand) {
                Log.d(TAG, str + String.format("SCTE-35 splice command: type=%s.", entry.getClass().getSimpleName()));
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.EventListener
    public void onAdClicked() {
    }

    @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.EventListener
    public void onAdLoadError(IOException iOException) {
        printInternalError("adLoadError", iOException);
    }

    @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.EventListener
    public void onAdTapped() {
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioDecoderInitialized(String str, long j9, long j10) {
        Log.d(TAG, "audioDecoderInitialized [" + getSessionTimeString() + ", " + str + "]");
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioDisabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "audioDisabled [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioEnabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "audioEnabled [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioInputFormatChanged(Format format) {
        Log.d(TAG, "audioFormatChanged [" + getSessionTimeString() + ", " + Format.toLogString(format) + "]");
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioSessionId(int i9) {
        Log.d(TAG, "audioSessionId [" + i9 + "]");
    }

    @Override // com.google.android.exoplayer2.audio.AudioRendererEventListener
    public void onAudioSinkUnderrun(int i9, long j9, long j10) {
        printInternalError("audioTrackUnderrun [" + i9 + ", " + j9 + ", " + j10 + "]", null);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int i9, Format format, int i10, Object obj, long j9) {
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysLoaded() {
        Log.d(TAG, "drmKeysLoaded [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysRemoved() {
        Log.d(TAG, "drmKeysRemoved [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmKeysRestored() {
        Log.d(TAG, "drmKeysRestored [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener
    public void onDrmSessionManagerError(Exception exc) {
        printInternalError("drmSessionManagerError", exc);
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onDroppedFrames(int i9, long j9) {
        Log.d(TAG, "droppedFrames [" + getSessionTimeString() + ", " + i9 + "]");
    }

    @Override // com.google.android.exoplayer2.source.ads.AdsMediaSource.EventListener
    public void onInternalAdLoadError(RuntimeException runtimeException) {
        printInternalError("internalAdLoadError", runtimeException);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCanceled(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCompleted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadError(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11, long j12, long j13, IOException iOException, boolean z8) {
        printInternalError("loadError", iOException);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadStarted(DataSpec dataSpec, int i9, int i10, Format format, int i11, Object obj, long j9, long j10, long j11) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z8) {
        Log.d(TAG, "loading [" + z8 + "]");
    }

    @Override // com.google.android.exoplayer2.metadata.MetadataOutput
    public void onMetadata(Metadata metadata) {
        Log.d(TAG, "onMetadata [");
        printMetadata(metadata, "  ");
        Log.d(TAG, "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Log.d(TAG, "playbackParameters " + String.format("[speed=%.2f, pitch=%.2f]", Float.valueOf(playbackParameters.speed), Float.valueOf(playbackParameters.pitch)));
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Log.e(TAG, "playerFailed [" + getSessionTimeString() + "]", exoPlaybackException);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z8, int i9) {
        Log.d(TAG, "state [" + getSessionTimeString() + ", " + z8 + ", " + getStateString(i9) + "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity(int i9) {
        Log.d(TAG, "positionDiscontinuity [" + getDiscontinuityReasonString(i9) + "]");
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onRenderedFirstFrame(Surface surface) {
        Log.d(TAG, "renderedFirstFrame [" + surface + "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i9) {
        Log.d(TAG, "repeatMode [" + getRepeatModeString(i9) + "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onSeekProcessed() {
        Log.d(TAG, "seekProcessed");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onShuffleModeEnabledChanged(boolean z8) {
        Log.d(TAG, "shuffleModeEnabled [" + z8 + "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj, int i9) {
        int periodCount = timeline.getPeriodCount();
        int windowCount = timeline.getWindowCount();
        Log.d(TAG, "timelineChanged [periodCount=" + periodCount + ", windowCount=" + windowCount + ", reason=" + getTimelineChangeReasonString(i9));
        for (int i10 = 0; i10 < Math.min(periodCount, 3); i10++) {
            timeline.getPeriod(i10, this.period);
            Log.d(TAG, "  period [" + getTimeString(this.period.getDurationMs()) + "]");
        }
        if (periodCount > 3) {
            Log.d(TAG, "  ...");
        }
        for (int i11 = 0; i11 < Math.min(windowCount, 3); i11++) {
            timeline.getWindow(i11, this.window);
            Log.d(TAG, "  window [" + getTimeString(this.window.getDurationMs()) + ", " + this.window.isSeekable + ", " + this.window.isDynamic + "]");
        }
        if (windowCount > 3) {
            Log.d(TAG, "  ...");
        }
        Log.d(TAG, "]");
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        EventLogger eventLogger;
        EventLogger eventLogger2 = this;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = eventLogger2.trackSelector.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo == null) {
            Log.d(TAG, "Tracks []");
            return;
        }
        Log.d(TAG, "Tracks [");
        int i9 = 0;
        while (true) {
            String str = "  ]";
            String str2 = " [";
            if (i9 >= currentMappedTrackInfo.length) {
                break;
            }
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i9);
            TrackSelection trackSelection = trackSelectionArray.get(i9);
            if (trackGroups.length > 0) {
                Log.d(TAG, "  Renderer:" + i9 + " [");
                int i10 = 0;
                while (i10 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i10);
                    TrackGroupArray trackGroupArray2 = trackGroups;
                    String str3 = str;
                    Log.d(TAG, "    Group:" + i10 + ", adaptive_supported=" + getAdaptiveSupportString(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i9, i10, false)) + str2);
                    int i11 = 0;
                    while (i11 < trackGroup.length) {
                        Log.d(TAG, "      " + getTrackStatusString(trackSelection, trackGroup, i11) + " Track:" + i11 + ", " + Format.toLogString(trackGroup.getFormat(i11)) + ", supported=" + getFormatSupportString(currentMappedTrackInfo.getTrackFormatSupport(i9, i10, i11)));
                        i11++;
                        str2 = str2;
                    }
                    Log.d(TAG, "    ]");
                    i10++;
                    trackGroups = trackGroupArray2;
                    str = str3;
                }
                String str4 = str;
                if (trackSelection != null) {
                    for (int i12 = 0; i12 < trackSelection.length(); i12++) {
                        Metadata metadata = trackSelection.getFormat(i12).metadata;
                        if (metadata != null) {
                            Log.d(TAG, "    Metadata [");
                            eventLogger = this;
                            eventLogger.printMetadata(metadata, "      ");
                            Log.d(TAG, "    ]");
                            break;
                        }
                    }
                    eventLogger = this;
                    Log.d(TAG, str4);
                } else {
                    eventLogger = this;
                    Log.d(TAG, str4);
                }
            } else {
                eventLogger = eventLogger2;
            }
            i9++;
            eventLogger2 = eventLogger;
        }
        String str5 = " [";
        TrackGroupArray unassociatedTrackGroups = currentMappedTrackInfo.getUnassociatedTrackGroups();
        if (unassociatedTrackGroups.length > 0) {
            Log.d(TAG, "  Renderer:None [");
            int i13 = 0;
            while (i13 < unassociatedTrackGroups.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("    Group:");
                sb.append(i13);
                String str6 = str5;
                sb.append(str6);
                Log.d(TAG, sb.toString());
                TrackGroup trackGroup2 = unassociatedTrackGroups.get(i13);
                int i14 = 0;
                while (i14 < trackGroup2.length) {
                    TrackGroupArray trackGroupArray3 = unassociatedTrackGroups;
                    Log.d(TAG, "      " + getTrackStatusString(false) + " Track:" + i14 + ", " + Format.toLogString(trackGroup2.getFormat(i14)) + ", supported=" + getFormatSupportString(0));
                    i14++;
                    unassociatedTrackGroups = trackGroupArray3;
                }
                Log.d(TAG, "    ]");
                i13++;
                str5 = str6;
            }
            Log.d(TAG, "  ]");
        }
        Log.d(TAG, "]");
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int i9, long j9, long j10) {
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoDecoderInitialized(String str, long j9, long j10) {
        Log.d(TAG, "videoDecoderInitialized [" + getSessionTimeString() + ", " + str + "]");
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoDisabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "videoDisabled [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoEnabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "videoEnabled [" + getSessionTimeString() + "]");
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoInputFormatChanged(Format format) {
        Log.d(TAG, "videoFormatChanged [" + getSessionTimeString() + ", " + Format.toLogString(format) + "]");
    }

    @Override // com.google.android.exoplayer2.video.VideoRendererEventListener
    public void onVideoSizeChanged(int i9, int i10, int i11, float f9) {
        Log.d(TAG, "videoSizeChanged [" + i9 + ", " + i10 + "]");
    }
}
