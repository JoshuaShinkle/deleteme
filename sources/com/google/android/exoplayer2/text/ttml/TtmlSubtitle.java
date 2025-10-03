package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class TtmlSubtitle implements Subtitle {
    private final long[] eventTimesUs;
    private final Map<String, TtmlStyle> globalStyles;
    private final Map<String, TtmlRegion> regionMap;
    private final TtmlNode root;

    public TtmlSubtitle(TtmlNode ttmlNode, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) {
        this.root = ttmlNode;
        this.regionMap = map2;
        this.globalStyles = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.eventTimesUs = ttmlNode.getEventTimesUs();
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public List<Cue> getCues(long j9) {
        return this.root.getCues(j9, this.globalStyles, this.regionMap);
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public long getEventTime(int i9) {
        return this.eventTimesUs[i9];
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getEventTimeCount() {
        return this.eventTimesUs.length;
    }

    public Map<String, TtmlStyle> getGlobalStyles() {
        return this.globalStyles;
    }

    @Override // com.google.android.exoplayer2.text.Subtitle
    public int getNextEventTimeIndex(long j9) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j9, false, false);
        if (iBinarySearchCeil < this.eventTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    public TtmlNode getRoot() {
        return this.root;
    }
}
