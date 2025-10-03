package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsMasterPlaylist.HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeTimeUs = C3322C.TIME_UNSET;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsMasterPlaylist.HlsUrl[] variants;

    public static final class EncryptionKeyChunk extends DataChunk {

        /* renamed from: iv */
        public final String f15317iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i9, Object obj, byte[] bArr, String str) {
            super(dataSource, dataSpec, 3, format, i9, obj, bArr);
            this.f15317iv = str;
        }

        @Override // com.google.android.exoplayer2.source.chunk.DataChunk
        public void consume(byte[] bArr, int i9) {
            this.result = Arrays.copyOf(bArr, i9);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsMasterPlaylist.HlsUrl playlist;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    public static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection
        public int getSelectedIndex() {
            return this.selectedIndex;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection
        public int getSelectionReason() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelection
        public void updateSelectedTrack(long j9, long j10, long j11) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, jElapsedRealtime)) {
                for (int i9 = this.length - 1; i9 >= 0; i9--) {
                    if (!isBlacklisted(i9, jElapsedRealtime)) {
                        this.selectedIndex = i9;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, HlsDataSourceFactory hlsDataSourceFactory, TimestampAdjusterProvider timestampAdjusterProvider, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.variants = hlsUrlArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider;
        this.muxedCaptionFormats = list;
        Format[] formatArr = new Format[hlsUrlArr.length];
        int[] iArr = new int[hlsUrlArr.length];
        for (int i9 = 0; i9 < hlsUrlArr.length; i9++) {
            formatArr[i9] = hlsUrlArr[i9].format;
            iArr[i9] = i9;
        }
        this.mediaDataSource = hlsDataSourceFactory.createDataSource(1);
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        TrackGroup trackGroup = new TrackGroup(formatArr);
        this.trackGroup = trackGroup;
        this.trackSelection = new InitializationTrackSelection(trackGroup, iArr);
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i9, int i10, Object obj) {
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(uri, 0L, -1L, null, 1), this.variants[i9].format, i10, obj, this.scratchSpace, str);
    }

    private long resolveTimeToLiveEdgeUs(long j9) {
        long j10 = this.liveEdgeTimeUs;
        return (j10 > C3322C.TIME_UNSET ? 1 : (j10 == C3322C.TIME_UNSET ? 0 : -1)) != 0 ? j10 - j9 : C3322C.TIME_UNSET;
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(Util.toLowerInvariant(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (16 - byteArray.length) + length, byteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeTimeUs = hlsMediaPlaylist.hasEndTag ? C3322C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs();
    }

    public void getNextChunk(HlsMediaChunk hlsMediaChunk, long j9, long j10, HlsChunkHolder hlsChunkHolder) {
        long nextChunkIndex;
        int iIndexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        this.expectedPlaylistUrl = null;
        long jMax = j10 - j9;
        long jResolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j9);
        if (hlsMediaChunk != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            jMax = Math.max(0L, jMax - durationUs);
            if (jResolveTimeToLiveEdgeUs != C3322C.TIME_UNSET) {
                jResolveTimeToLiveEdgeUs = Math.max(0L, jResolveTimeToLiveEdgeUs - durationUs);
            }
        }
        this.trackSelection.updateSelectedTrack(j9, jMax, jResolveTimeToLiveEdgeUs);
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z8 = iIndexOf != selectedIndexInTrackGroup;
        HlsMasterPlaylist.HlsUrl hlsUrl = this.variants[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(hlsUrl)) {
            hlsChunkHolder.playlist = hlsUrl;
            this.expectedPlaylistUrl = hlsUrl;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl);
        this.independentSegments = playlistSnapshot.hasIndependentSegmentsTag;
        updateLiveEdgeTimeUs(playlistSnapshot);
        if (hlsMediaChunk == null || z8) {
            long j11 = (hlsMediaChunk == null || this.independentSegments) ? j10 : hlsMediaChunk.startTimeUs;
            if (playlistSnapshot.hasEndTag || j11 < playlistSnapshot.getEndTimeUs()) {
                long jBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) playlistSnapshot.segments, Long.valueOf(j11), true, !this.playlistTracker.isLive() || hlsMediaChunk == null);
                long j12 = playlistSnapshot.mediaSequence;
                long j13 = jBinarySearchFloor + j12;
                if (j13 >= j12 || hlsMediaChunk == null) {
                    nextChunkIndex = j13;
                } else {
                    hlsUrl = this.variants[iIndexOf];
                    playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl);
                    selectedIndexInTrackGroup = iIndexOf;
                    nextChunkIndex = hlsMediaChunk.getNextChunkIndex();
                }
            } else {
                nextChunkIndex = playlistSnapshot.mediaSequence + playlistSnapshot.segments.size();
            }
        } else {
            nextChunkIndex = hlsMediaChunk.getNextChunkIndex();
        }
        long j14 = nextChunkIndex;
        HlsMasterPlaylist.HlsUrl hlsUrl2 = hlsUrl;
        int i9 = selectedIndexInTrackGroup;
        HlsMediaPlaylist hlsMediaPlaylist = playlistSnapshot;
        long j15 = hlsMediaPlaylist.mediaSequence;
        if (j14 < j15) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        int i10 = (int) (j14 - j15);
        if (i10 >= hlsMediaPlaylist.segments.size()) {
            if (hlsMediaPlaylist.hasEndTag) {
                hlsChunkHolder.endOfStream = true;
                return;
            } else {
                hlsChunkHolder.playlist = hlsUrl2;
                this.expectedPlaylistUrl = hlsUrl2;
                return;
            }
        }
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i10);
        String str = segment.fullSegmentEncryptionKeyUri;
        if (str != null) {
            Uri uriResolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, str);
            if (!uriResolveToUri.equals(this.encryptionKeyUri)) {
                hlsChunkHolder.chunk = newEncryptionKeyChunk(uriResolveToUri, segment.encryptionIV, i9, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData());
                return;
            } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                setEncryptionData(uriResolveToUri, segment.encryptionIV, this.encryptionKey);
            }
        } else {
            clearEncryptionData();
        }
        HlsMediaPlaylist.Segment segment2 = hlsMediaPlaylist.initializationSegment;
        DataSpec dataSpec = segment2 != null ? new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, null) : null;
        long initialStartTimeUs = (hlsMediaPlaylist.startTimeUs - this.playlistTracker.getInitialStartTimeUs()) + segment.relativeStartTimeUs;
        int i11 = hlsMediaPlaylist.discontinuitySequence + segment.relativeDiscontinuitySequence;
        hlsChunkHolder.chunk = new HlsMediaChunk(this.extractorFactory, this.mediaDataSource, new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null), dataSpec, hlsUrl2, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initialStartTimeUs, initialStartTimeUs + segment.durationUs, j14, i11, segment.hasGapTag, this.isTimestampMaster, this.timestampAdjusterProvider.getAdjuster(i11), hlsMediaChunk, hlsMediaPlaylist.drmInitData, this.encryptionKey, this.encryptionIv);
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        HlsMasterPlaylist.HlsUrl hlsUrl = this.expectedPlaylistUrl;
        if (hlsUrl != null) {
            this.playlistTracker.maybeThrowPlaylistRefreshError(hlsUrl);
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.f15317iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean z8, IOException iOException) {
        if (z8) {
            TrackSelection trackSelection = this.trackSelection;
            if (ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection, trackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), iOException)) {
                return true;
            }
        }
        return false;
    }

    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j9) {
        int iIndexOf;
        int iIndexOf2 = this.trackGroup.indexOf(hlsUrl.format);
        if (iIndexOf2 == -1 || (iIndexOf = this.trackSelection.indexOf(iIndexOf2)) == -1) {
            return;
        }
        this.trackSelection.blacklist(iIndexOf, j9);
    }

    public void reset() {
        this.fatalError = null;
    }

    public void selectTracks(TrackSelection trackSelection) {
        this.trackSelection = trackSelection;
    }

    public void setIsTimestampMaster(boolean z8) {
        this.isTimestampMaster = z8;
    }
}
