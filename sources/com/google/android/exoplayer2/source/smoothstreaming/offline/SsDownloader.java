package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.TrackKey;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class SsDownloader extends SegmentDownloader<SsManifest, TrackKey> {
    public SsDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(SsUtil.fixManifestUri(uri), downloaderConstructorHelper);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public TrackKey[] getAllRepresentationKeys() {
        ArrayList arrayList = new ArrayList();
        SsManifest manifest = getManifest();
        int i9 = 0;
        while (true) {
            SsManifest.StreamElement[] streamElementArr = manifest.streamElements;
            if (i9 >= streamElementArr.length) {
                return (TrackKey[]) arrayList.toArray(new TrackKey[arrayList.size()]);
            }
            SsManifest.StreamElement streamElement = streamElementArr[i9];
            for (int i10 = 0; i10 < streamElement.formats.length; i10++) {
                arrayList.add(new TrackKey(i9, i10));
            }
            i9++;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public SsManifest getManifest(DataSource dataSource, Uri uri) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, 4, new SsManifestParser());
        parsingLoadable.load();
        return (SsManifest) parsingLoadable.getResult();
    }

    @Override // com.google.android.exoplayer2.offline.SegmentDownloader
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, SsManifest ssManifest, TrackKey[] trackKeyArr, boolean z8) {
        ArrayList arrayList = new ArrayList();
        for (TrackKey trackKey : trackKeyArr) {
            SsManifest.StreamElement streamElement = ssManifest.streamElements[trackKey.streamElementIndex];
            for (int i9 = 0; i9 < streamElement.chunkCount; i9++) {
                arrayList.add(new SegmentDownloader.Segment(streamElement.getStartTimeUs(i9), new DataSpec(streamElement.buildRequestUri(trackKey.trackIndex, i9))));
            }
        }
        return arrayList;
    }
}
