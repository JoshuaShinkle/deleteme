package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HlsMasterPlaylist extends HlsPlaylist {
    public final List<HlsUrl> audios;
    public final Format muxedAudioFormat;
    public final List<Format> muxedCaptionFormats;
    public final List<HlsUrl> subtitles;
    public final List<HlsUrl> variants;

    public static final class HlsUrl {
        public final Format format;
        public final String url;

        public HlsUrl(String str, Format format) {
            this.url = str;
            this.format = format;
        }

        public static HlsUrl createMediaPlaylistHlsUrl(String str) {
            return new HlsUrl(str, Format.createContainerFormat("0", MimeTypes.APPLICATION_M3U8, null, null, -1, 0, null));
        }
    }

    public HlsMasterPlaylist(String str, List<String> list, List<HlsUrl> list2, List<HlsUrl> list3, List<HlsUrl> list4, Format format, List<Format> list5) {
        super(str, list);
        this.variants = Collections.unmodifiableList(list2);
        this.audios = Collections.unmodifiableList(list3);
        this.subtitles = Collections.unmodifiableList(list4);
        this.muxedAudioFormat = format;
        this.muxedCaptionFormats = list5 != null ? Collections.unmodifiableList(list5) : null;
    }

    private static List<HlsUrl> copyRenditionsList(List<HlsUrl> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i9 = 0; i9 < list.size(); i9++) {
            HlsUrl hlsUrl = list.get(i9);
            if (list2.contains(hlsUrl.url)) {
                arrayList.add(hlsUrl);
            }
        }
        return arrayList;
    }

    public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String str) {
        List listSingletonList = Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(str));
        List listEmptyList = Collections.emptyList();
        return new HlsMasterPlaylist(null, Collections.emptyList(), listSingletonList, listEmptyList, listEmptyList, null, null);
    }

    public HlsMasterPlaylist copy(List<String> list) {
        return new HlsMasterPlaylist(this.baseUri, this.tags, copyRenditionsList(this.variants, list), copyRenditionsList(this.audios, list), copyRenditionsList(this.subtitles, list), this.muxedAudioFormat, this.muxedCaptionFormats);
    }
}
