package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public final class HlsDownloader extends SegmentDownloader<HlsMasterPlaylist, String> {
    public HlsDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(uri, downloaderConstructorHelper);
    }

    private static void addSegment(ArrayList<SegmentDownloader.Segment> arrayList, HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.Segment segment, HashSet<Uri> hashSet) {
        long j9 = hlsMediaPlaylist.startTimeUs + segment.relativeStartTimeUs;
        String str = segment.fullSegmentEncryptionKeyUri;
        if (str != null) {
            Uri uriResolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, str);
            if (hashSet.add(uriResolveToUri)) {
                arrayList.add(new SegmentDownloader.Segment(j9, new DataSpec(uriResolveToUri)));
            }
        }
        arrayList.add(new SegmentDownloader.Segment(j9, new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null)));
    }

    private static void extractUrls(List<HlsMasterPlaylist.HlsUrl> list, ArrayList<String> arrayList) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            arrayList.add(list.get(i9).url);
        }
    }

    private static HlsPlaylist loadManifest(DataSource dataSource, Uri uri) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, 4, new HlsPlaylistParser());
        parsingLoadable.load();
        return (HlsPlaylist) parsingLoadable.getResult();
    }

    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public String[] getAllRepresentationKeys() {
        ArrayList arrayList = new ArrayList();
        HlsMasterPlaylist manifest = getManifest();
        extractUrls(manifest.variants, arrayList);
        extractUrls(manifest.audios, arrayList);
        extractUrls(manifest.subtitles, arrayList);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public HlsMasterPlaylist getManifest(DataSource dataSource, Uri uri) throws IOException {
        HlsPlaylist hlsPlaylistLoadManifest = loadManifest(dataSource, uri);
        return hlsPlaylistLoadManifest instanceof HlsMasterPlaylist ? (HlsMasterPlaylist) hlsPlaylistLoadManifest : HlsMasterPlaylist.createSingleVariantMasterPlaylist(hlsPlaylistLoadManifest.baseUri);
    }

    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, HlsMasterPlaylist hlsMasterPlaylist, String[] strArr, boolean z8) throws IOException {
        HlsMediaPlaylist hlsMediaPlaylist;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Uri uriResolveToUri = UriUtil.resolveToUri(hlsMasterPlaylist.baseUri, str);
            try {
                hlsMediaPlaylist = (HlsMediaPlaylist) loadManifest(dataSource, uriResolveToUri);
            } catch (IOException e9) {
                if (!z8) {
                    throw e9;
                }
                hlsMediaPlaylist = null;
            }
            arrayList.add(new SegmentDownloader.Segment(hlsMediaPlaylist != null ? hlsMediaPlaylist.startTimeUs : Long.MIN_VALUE, new DataSpec(uriResolveToUri)));
            if (hlsMediaPlaylist != null) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.initializationSegment;
                if (segment != null) {
                    addSegment(arrayList, hlsMediaPlaylist, segment, hashSet);
                }
                List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
                for (int i9 = 0; i9 < list.size(); i9++) {
                    addSegment(arrayList, hlsMediaPlaylist, list.get(i9), hashSet);
                }
            }
        }
        return arrayList;
    }
}
