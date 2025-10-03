package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final int minLoadableRetryCount;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private TrackGroupArray trackGroups;
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private final Handler continueLoadingHandler = new Handler();
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, int i9, MediaSourceEventListener.EventDispatcher eventDispatcher, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, boolean z8) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.minLoadableRetryCount = i9;
        this.eventDispatcher = eventDispatcher;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.allowChunklessPreparation = z8;
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMasterPlaylist hlsMasterPlaylist, long j9) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(hlsMasterPlaylist.variants);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i9 = 0; i9 < arrayList2.size(); i9++) {
            HlsMasterPlaylist.HlsUrl hlsUrl = (HlsMasterPlaylist.HlsUrl) arrayList2.get(i9);
            Format format = hlsUrl.format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                arrayList3.add(hlsUrl);
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                arrayList4.add(hlsUrl);
            }
        }
        if (arrayList3.isEmpty()) {
            if (arrayList4.size() < arrayList2.size()) {
                arrayList2.removeAll(arrayList4);
            }
            arrayList = arrayList2;
        } else {
            arrayList = arrayList3;
        }
        Assertions.checkArgument(!arrayList.isEmpty());
        HlsMasterPlaylist.HlsUrl[] hlsUrlArr = (HlsMasterPlaylist.HlsUrl[]) arrayList.toArray(new HlsMasterPlaylist.HlsUrl[0]);
        String str = hlsUrlArr[0].format.codecs;
        HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(0, hlsUrlArr, hlsMasterPlaylist.muxedAudioFormat, hlsMasterPlaylist.muxedCaptionFormats, j9);
        this.sampleStreamWrappers[0] = hlsSampleStreamWrapperBuildSampleStreamWrapper;
        if (!this.allowChunklessPreparation || str == null) {
            hlsSampleStreamWrapperBuildSampleStreamWrapper.setIsTimestampMaster(true);
            hlsSampleStreamWrapperBuildSampleStreamWrapper.continuePreparing();
            return;
        }
        boolean z8 = Util.getCodecsOfType(str, 2) != null;
        boolean z9 = Util.getCodecsOfType(str, 1) != null;
        ArrayList arrayList5 = new ArrayList();
        if (z8) {
            int size = arrayList.size();
            Format[] formatArr = new Format[size];
            for (int i10 = 0; i10 < size; i10++) {
                formatArr[i10] = deriveVideoFormat(hlsUrlArr[i10].format);
            }
            arrayList5.add(new TrackGroup(formatArr));
            if (z9 && (hlsMasterPlaylist.muxedAudioFormat != null || hlsMasterPlaylist.audios.isEmpty())) {
                arrayList5.add(new TrackGroup(deriveMuxedAudioFormat(hlsUrlArr[0].format, hlsMasterPlaylist.muxedAudioFormat, -1)));
            }
            List<Format> list = hlsMasterPlaylist.muxedCaptionFormats;
            if (list != null) {
                for (int i11 = 0; i11 < list.size(); i11++) {
                    arrayList5.add(new TrackGroup(list.get(i11)));
                }
            }
        } else {
            if (!z9) {
                throw new IllegalArgumentException("Unexpected codecs attribute: " + str);
            }
            int size2 = arrayList.size();
            Format[] formatArr2 = new Format[size2];
            for (int i12 = 0; i12 < size2; i12++) {
                Format format2 = hlsUrlArr[i12].format;
                formatArr2[i12] = deriveMuxedAudioFormat(format2, hlsMasterPlaylist.muxedAudioFormat, format2.bitrate);
            }
            arrayList5.add(new TrackGroup(formatArr2));
        }
        hlsSampleStreamWrapperBuildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray((TrackGroup[]) arrayList5.toArray(new TrackGroup[0])), 0);
    }

    private void buildAndPrepareSampleStreamWrappers(long j9) {
        HlsMasterPlaylist masterPlaylist = this.playlistTracker.getMasterPlaylist();
        List<HlsMasterPlaylist.HlsUrl> list = masterPlaylist.audios;
        List<HlsMasterPlaylist.HlsUrl> list2 = masterPlaylist.subtitles;
        int size = list.size() + 1 + list2.size();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[size];
        this.pendingPrepareCount = size;
        buildAndPrepareMainSampleStreamWrapper(masterPlaylist, j9);
        char c9 = 0;
        int i9 = 1;
        int i10 = 0;
        while (i10 < list.size()) {
            HlsMasterPlaylist.HlsUrl hlsUrl = list.get(i10);
            HlsMasterPlaylist.HlsUrl[] hlsUrlArr = new HlsMasterPlaylist.HlsUrl[1];
            hlsUrlArr[c9] = hlsUrl;
            HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(1, hlsUrlArr, null, Collections.emptyList(), j9);
            int i11 = i9 + 1;
            this.sampleStreamWrappers[i9] = hlsSampleStreamWrapperBuildSampleStreamWrapper;
            Format format = hlsUrl.format;
            if (!this.allowChunklessPreparation || format.codecs == null) {
                hlsSampleStreamWrapperBuildSampleStreamWrapper.continuePreparing();
            } else {
                hlsSampleStreamWrapperBuildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup(hlsUrl.format)), 0);
            }
            i10++;
            i9 = i11;
            c9 = 0;
        }
        int i12 = 0;
        while (i12 < list2.size()) {
            HlsMasterPlaylist.HlsUrl hlsUrl2 = list2.get(i12);
            HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper2 = buildSampleStreamWrapper(3, new HlsMasterPlaylist.HlsUrl[]{hlsUrl2}, null, Collections.emptyList(), j9);
            this.sampleStreamWrappers[i9] = hlsSampleStreamWrapperBuildSampleStreamWrapper2;
            hlsSampleStreamWrapperBuildSampleStreamWrapper2.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup(hlsUrl2.format)), 0);
            i12++;
            i9++;
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int i9, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, Format format, List<Format> list, long j9) {
        return new HlsSampleStreamWrapper(i9, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, hlsUrlArr, this.dataSourceFactory, this.timestampAdjusterProvider, list), this.allocator, j9, format, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private void continuePreparingOrLoading() {
        if (this.trackGroups != null) {
            this.callback.onContinueLoadingRequested(this);
            return;
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
    }

    private static Format deriveMuxedAudioFormat(Format format, Format format2, int i9) {
        String codecsOfType;
        int i10;
        int i11;
        String str;
        if (format2 != null) {
            codecsOfType = format2.codecs;
            i10 = format2.channelCount;
            i11 = format2.selectionFlags;
            str = format2.language;
        } else {
            codecsOfType = Util.getCodecsOfType(format.codecs, 1);
            i10 = -1;
            i11 = 0;
            str = null;
        }
        String str2 = codecsOfType;
        int i12 = i10;
        int i13 = i11;
        String mediaMimeType = MimeTypes.getMediaMimeType(str2);
        return Format.createAudioSampleFormat(format.f15298id, mediaMimeType, str2, i9, -1, i12, -1, null, null, i13, str);
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return Format.createVideoSampleFormat(format.f15298id, MimeTypes.getMediaMimeType(codecsOfType), codecsOfType, format.bitrate, -1, format.width, format.height, format.frameRate, null, null);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j9) {
        return this.compositeSequenceableLoader.continueLoading(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j9, boolean z8) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            hlsSampleStreamWrapper.discardBuffer(j9, z8);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j9, SeekParameters seekParameters) {
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.maybeThrowPrepareError();
        }
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j9) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.onPlaylistBlacklisted(hlsUrl, j9);
        }
        continuePreparingOrLoading();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        continuePreparingOrLoading();
    }

    @Override // com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPlaylistRefreshRequired(HlsMasterPlaylist.HlsUrl hlsUrl) {
        this.playlistTracker.refreshPlaylist(hlsUrl);
    }

    @Override // com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPrepared() {
        int i9 = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i9;
        if (i9 > 0) {
            return;
        }
        int i10 = 0;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            i10 += hlsSampleStreamWrapper.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i10];
        int i11 = 0;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper2 : this.sampleStreamWrappers) {
            int i12 = hlsSampleStreamWrapper2.getTrackGroups().length;
            int i13 = 0;
            while (i13 < i12) {
                trackGroupArr[i11] = hlsSampleStreamWrapper2.getTrackGroups().get(i13);
                i13++;
                i11++;
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j9) {
        this.callback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j9);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C3322C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j9) {
        this.compositeSequenceableLoader.reevaluateBuffer(j9);
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        this.continueLoadingHandler.removeCallbacksAndMessages(null);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.release();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j9) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean zSeekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j9, false);
            int i9 = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i9 >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i9].seekToUs(j9, zSeekToUs);
                i9++;
            }
            if (zSeekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j9;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00eb  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j9) {
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[trackSelectionArr.length];
        int[] iArr2 = new int[trackSelectionArr.length];
        for (int i9 = 0; i9 < trackSelectionArr.length; i9++) {
            SampleStream sampleStream = sampleStreamArr2[i9];
            iArr[i9] = sampleStream == null ? -1 : this.streamWrapperIndices.get(sampleStream).intValue();
            iArr2[i9] = -1;
            TrackSelection trackSelection = trackSelectionArr[i9];
            if (trackSelection != null) {
                TrackGroup trackGroup = trackSelection.getTrackGroup();
                int i10 = 0;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (i10 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    }
                    if (hlsSampleStreamWrapperArr[i10].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i9] = i10;
                        break;
                    }
                    i10++;
                }
            }
        }
        this.streamWrapperIndices.clear();
        int length = trackSelectionArr.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[trackSelectionArr.length];
        TrackSelection[] trackSelectionArr2 = new TrackSelection[trackSelectionArr.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i11 = 0;
        int i12 = 0;
        boolean z8 = false;
        while (i12 < this.sampleStreamWrappers.length) {
            for (int i13 = 0; i13 < trackSelectionArr.length; i13++) {
                TrackSelection trackSelection2 = null;
                sampleStreamArr4[i13] = iArr[i13] == i12 ? sampleStreamArr2[i13] : null;
                if (iArr2[i13] == i12) {
                    trackSelection2 = trackSelectionArr[i13];
                }
                trackSelectionArr2[i13] = trackSelection2;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i12];
            int i14 = i11;
            int i15 = length;
            int i16 = i12;
            TrackSelection[] trackSelectionArr3 = trackSelectionArr2;
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr3 = hlsSampleStreamWrapperArr2;
            boolean zSelectTracks = hlsSampleStreamWrapper.selectTracks(trackSelectionArr2, zArr, sampleStreamArr4, zArr2, j9, z8);
            int i17 = 0;
            boolean z9 = false;
            while (true) {
                if (i17 >= trackSelectionArr.length) {
                    break;
                }
                if (iArr2[i17] == i16) {
                    Assertions.checkState(sampleStreamArr4[i17] != null);
                    sampleStreamArr3[i17] = sampleStreamArr4[i17];
                    this.streamWrapperIndices.put(sampleStreamArr4[i17], Integer.valueOf(i16));
                    z9 = true;
                } else if (iArr[i17] == i16) {
                    Assertions.checkState(sampleStreamArr4[i17] == null);
                }
                i17++;
            }
            if (z9) {
                hlsSampleStreamWrapperArr3[i14] = hlsSampleStreamWrapper;
                i11 = i14 + 1;
                if (i14 == 0) {
                    hlsSampleStreamWrapper.setIsTimestampMaster(true);
                    if (!zSelectTracks) {
                        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr4 = this.enabledSampleStreamWrappers;
                        if (hlsSampleStreamWrapperArr4.length != 0) {
                            if (hlsSampleStreamWrapper != hlsSampleStreamWrapperArr4[0]) {
                            }
                        }
                        this.timestampAdjusterProvider.reset();
                        z8 = true;
                    }
                } else {
                    hlsSampleStreamWrapper.setIsTimestampMaster(false);
                }
            } else {
                i11 = i14;
            }
            i12 = i16 + 1;
            sampleStreamArr2 = sampleStreamArr;
            hlsSampleStreamWrapperArr2 = hlsSampleStreamWrapperArr3;
            length = i15;
            trackSelectionArr2 = trackSelectionArr3;
        }
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr2, 0, length);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr5 = (HlsSampleStreamWrapper[]) Arrays.copyOf(hlsSampleStreamWrapperArr2, i11);
        this.enabledSampleStreamWrappers = hlsSampleStreamWrapperArr5;
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(hlsSampleStreamWrapperArr5);
        return j9;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        if (this.trackGroups == null) {
            return;
        }
        this.callback.onContinueLoadingRequested(this);
    }
}
