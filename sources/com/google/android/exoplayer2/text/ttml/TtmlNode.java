package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes.dex */
final class TtmlNode {
    public static final String ANONYMOUS_REGION_ID = "";
    public static final String ATTR_ID = "id";
    public static final String ATTR_TTS_BACKGROUND_COLOR = "backgroundColor";
    public static final String ATTR_TTS_COLOR = "color";
    public static final String ATTR_TTS_DISPLAY_ALIGN = "displayAlign";
    public static final String ATTR_TTS_EXTENT = "extent";
    public static final String ATTR_TTS_FONT_FAMILY = "fontFamily";
    public static final String ATTR_TTS_FONT_SIZE = "fontSize";
    public static final String ATTR_TTS_FONT_STYLE = "fontStyle";
    public static final String ATTR_TTS_FONT_WEIGHT = "fontWeight";
    public static final String ATTR_TTS_ORIGIN = "origin";
    public static final String ATTR_TTS_TEXT_ALIGN = "textAlign";
    public static final String ATTR_TTS_TEXT_DECORATION = "textDecoration";
    public static final String BOLD = "bold";
    public static final String CENTER = "center";
    public static final String END = "end";
    public static final String ITALIC = "italic";
    public static final String LEFT = "left";
    public static final String LINETHROUGH = "linethrough";
    public static final String NO_LINETHROUGH = "nolinethrough";
    public static final String NO_UNDERLINE = "nounderline";
    public static final String RIGHT = "right";
    public static final String START = "start";
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SMPTE_DATA = "smpte:data";
    public static final String TAG_SMPTE_IMAGE = "smpte:image";
    public static final String TAG_SMPTE_INFORMATION = "smpte:information";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";
    public static final String UNDERLINE = "underline";
    private List<TtmlNode> children;
    public final long endTimeUs;
    public final boolean isTextNode;
    private final HashMap<String, Integer> nodeEndsByRegion;
    private final HashMap<String, Integer> nodeStartsByRegion;
    public final String regionId;
    public final long startTimeUs;
    public final TtmlStyle style;
    private final String[] styleIds;
    public final String tag;
    public final String text;

    private TtmlNode(String str, String str2, long j9, long j10, TtmlStyle ttmlStyle, String[] strArr, String str3) {
        this.tag = str;
        this.text = str2;
        this.style = ttmlStyle;
        this.styleIds = strArr;
        this.isTextNode = str2 != null;
        this.startTimeUs = j9;
        this.endTimeUs = j10;
        this.regionId = (String) Assertions.checkNotNull(str3);
        this.nodeStartsByRegion = new HashMap<>();
        this.nodeEndsByRegion = new HashMap<>();
    }

    private void applyStyleToOutput(Map<String, TtmlStyle> map, SpannableStringBuilder spannableStringBuilder, int i9, int i10) {
        TtmlStyle ttmlStyleResolveStyle;
        if (i9 == i10 || (ttmlStyleResolveStyle = TtmlRenderUtil.resolveStyle(this.style, this.styleIds, map)) == null) {
            return;
        }
        TtmlRenderUtil.applyStylesToSpan(spannableStringBuilder, i9, i10, ttmlStyleResolveStyle);
    }

    public static TtmlNode buildNode(String str, long j9, long j10, TtmlStyle ttmlStyle, String[] strArr, String str2) {
        return new TtmlNode(str, null, j9, j10, ttmlStyle, strArr, str2);
    }

    public static TtmlNode buildTextNode(String str) {
        return new TtmlNode(null, TtmlRenderUtil.applyTextElementSpacePolicy(str), C3322C.TIME_UNSET, C3322C.TIME_UNSET, null, null, "");
    }

    private SpannableStringBuilder cleanUpText(SpannableStringBuilder spannableStringBuilder) {
        int i9;
        int i10;
        int length = spannableStringBuilder.length();
        int i11 = 0;
        for (int i12 = 0; i12 < length; i12++) {
            if (spannableStringBuilder.charAt(i12) == ' ') {
                int i13 = i12 + 1;
                int i14 = i13;
                while (i14 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i14) == ' ') {
                    i14++;
                }
                int i15 = i14 - i13;
                if (i15 > 0) {
                    spannableStringBuilder.delete(i12, i12 + i15);
                    length -= i15;
                }
            }
        }
        if (length > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
            length--;
        }
        int i16 = 0;
        while (true) {
            i9 = length - 1;
            if (i16 >= i9) {
                break;
            }
            if (spannableStringBuilder.charAt(i16) == '\n') {
                int i17 = i16 + 1;
                if (spannableStringBuilder.charAt(i17) == ' ') {
                    spannableStringBuilder.delete(i17, i16 + 2);
                    length--;
                }
            }
            i16++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i9) == ' ') {
            spannableStringBuilder.delete(i9, length);
            length--;
        }
        while (true) {
            i10 = length - 1;
            if (i11 >= i10) {
                break;
            }
            if (spannableStringBuilder.charAt(i11) == ' ') {
                int i18 = i11 + 1;
                if (spannableStringBuilder.charAt(i18) == '\n') {
                    spannableStringBuilder.delete(i11, i18);
                    length--;
                }
            }
            i11++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i10) == '\n') {
            spannableStringBuilder.delete(i10, length);
        }
        return spannableStringBuilder;
    }

    private void getEventTimes(TreeSet<Long> treeSet, boolean z8) {
        boolean zEquals = TAG_P.equals(this.tag);
        if (z8 || zEquals) {
            long j9 = this.startTimeUs;
            if (j9 != C3322C.TIME_UNSET) {
                treeSet.add(Long.valueOf(j9));
            }
            long j10 = this.endTimeUs;
            if (j10 != C3322C.TIME_UNSET) {
                treeSet.add(Long.valueOf(j10));
            }
        }
        if (this.children == null) {
            return;
        }
        for (int i9 = 0; i9 < this.children.size(); i9++) {
            this.children.get(i9).getEventTimes(treeSet, z8 || zEquals);
        }
    }

    private static SpannableStringBuilder getRegionOutput(String str, Map<String, SpannableStringBuilder> map) {
        if (!map.containsKey(str)) {
            map.put(str, new SpannableStringBuilder());
        }
        return map.get(str);
    }

    private void traverseForStyle(Map<String, TtmlStyle> map, Map<String, SpannableStringBuilder> map2) {
        for (Map.Entry<String, Integer> entry : this.nodeEndsByRegion.entrySet()) {
            String key = entry.getKey();
            applyStyleToOutput(map, map2.get(key), this.nodeStartsByRegion.containsKey(key) ? this.nodeStartsByRegion.get(key).intValue() : 0, entry.getValue().intValue());
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                getChild(i9).traverseForStyle(map, map2);
            }
        }
    }

    private void traverseForText(long j9, boolean z8, String str, Map<String, SpannableStringBuilder> map) {
        this.nodeStartsByRegion.clear();
        this.nodeEndsByRegion.clear();
        String str2 = this.regionId;
        if (!"".equals(str2)) {
            str = str2;
        }
        if (this.isTextNode && z8) {
            getRegionOutput(str, map).append((CharSequence) this.text);
            return;
        }
        if (TAG_BR.equals(this.tag) && z8) {
            getRegionOutput(str, map).append('\n');
            return;
        }
        if (!TAG_METADATA.equals(this.tag) && isActive(j9)) {
            boolean zEquals = TAG_P.equals(this.tag);
            for (Map.Entry<String, SpannableStringBuilder> entry : map.entrySet()) {
                this.nodeStartsByRegion.put(entry.getKey(), Integer.valueOf(entry.getValue().length()));
            }
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                getChild(i9).traverseForText(j9, z8 || zEquals, str, map);
            }
            if (zEquals) {
                TtmlRenderUtil.endParagraph(getRegionOutput(str, map));
            }
            for (Map.Entry<String, SpannableStringBuilder> entry2 : map.entrySet()) {
                this.nodeEndsByRegion.put(entry2.getKey(), Integer.valueOf(entry2.getValue().length()));
            }
        }
    }

    public void addChild(TtmlNode ttmlNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(ttmlNode);
    }

    public TtmlNode getChild(int i9) {
        List<TtmlNode> list = this.children;
        if (list != null) {
            return list.get(i9);
        }
        throw new IndexOutOfBoundsException();
    }

    public int getChildCount() {
        List<TtmlNode> list = this.children;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<Cue> getCues(long j9, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) {
        TreeMap treeMap = new TreeMap();
        traverseForText(j9, false, this.regionId, treeMap);
        traverseForStyle(map, treeMap);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : treeMap.entrySet()) {
            TtmlRegion ttmlRegion = map2.get(entry.getKey());
            arrayList.add(new Cue(cleanUpText((SpannableStringBuilder) entry.getValue()), null, ttmlRegion.line, ttmlRegion.lineType, ttmlRegion.lineAnchor, ttmlRegion.position, Integer.MIN_VALUE, ttmlRegion.width));
        }
        return arrayList;
    }

    public long[] getEventTimesUs() {
        TreeSet<Long> treeSet = new TreeSet<>();
        int i9 = 0;
        getEventTimes(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator<Long> it = treeSet.iterator();
        while (it.hasNext()) {
            jArr[i9] = it.next().longValue();
            i9++;
        }
        return jArr;
    }

    public String[] getStyleIds() {
        return this.styleIds;
    }

    public boolean isActive(long j9) {
        long j10 = this.startTimeUs;
        return (j10 == C3322C.TIME_UNSET && this.endTimeUs == C3322C.TIME_UNSET) || (j10 <= j9 && this.endTimeUs == C3322C.TIME_UNSET) || ((j10 == C3322C.TIME_UNSET && j9 < this.endTimeUs) || (j10 <= j9 && j9 < this.endTimeUs));
    }
}
