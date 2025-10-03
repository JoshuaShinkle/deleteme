package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes.dex */
public final class FilteringSsManifestParser implements ParsingLoadable.Parser<SsManifest> {
    private final List<TrackKey> filter;
    private final SsManifestParser ssManifestParser = new SsManifestParser();

    public FilteringSsManifestParser(List<TrackKey> list) {
        this.filter = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public SsManifest parse(Uri uri, InputStream inputStream) {
        return this.ssManifestParser.parse(uri, inputStream).copy(this.filter);
    }
}
