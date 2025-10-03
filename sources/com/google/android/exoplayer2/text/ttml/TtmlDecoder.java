package com.google.android.exoplayer2.text.ttml;

import android.text.Layout;
import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes.dex */
public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);

    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        public FrameAndTickRate(float f9, int i9, int i10) {
            this.effectiveFrameRate = f9;
            this.subFrameRate = i9;
            this.tickRate = i10;
        }
    }

    public TtmlDecoder() throws XmlPullParserException {
        super(TAG);
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e9) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e9);
        }
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals(TtmlNode.TAG_BODY) || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals(TtmlNode.TAG_SMPTE_IMAGE) || str.equals(TtmlNode.TAG_SMPTE_DATA) || str.equals(TtmlNode.TAG_SMPTE_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String strGroup;
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else {
            if (strArrSplit.length != 2) {
                throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + strArrSplit.length + ".");
            }
            matcher = FONT_SIZE.matcher(strArrSplit[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        }
        if (!matcher.matches()) {
            throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
        }
        strGroup = matcher.group(3);
        strGroup.hashCode();
        switch (strGroup) {
            case "%":
                ttmlStyle.setFontSizeUnit(3);
                break;
            case "em":
                ttmlStyle.setFontSizeUnit(2);
                break;
            case "px":
                ttmlStyle.setFontSizeUnit(1);
                break;
            default:
                throw new SubtitleDecoderException("Invalid unit for fontSize: '" + strGroup + "'.");
        }
        ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException, NumberFormatException {
        float f9;
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int i9 = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            if (attributeValue2.split(StringUtils.SPACE).length != 2) {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
            f9 = Integer.parseInt(r2[0]) / Integer.parseInt(r2[1]);
        } else {
            f9 = 1.0f;
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i10 = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i10 = Integer.parseInt(attributeValue3);
        }
        int i11 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i11 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(i9 * f9, i10, i11);
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, Map<String, TtmlRegion> map2) throws XmlPullParserException, IOException {
        TtmlRegion regionAttributes;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        styleAttributes.chain(map.get(str));
                    }
                }
                if (styleAttributes.getId() != null) {
                    map.put(styleAttributes.getId(), styleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region") && (regionAttributes = parseRegionAttributes(xmlPullParser)) != null) {
                map2.put(regionAttributes.f15321id, regionAttributes);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException, NumberFormatException {
        long j9;
        String attributeValue;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, null);
        String[] strArr = null;
        long timeExpression = -9223372036854775807L;
        long timeExpression2 = -9223372036854775807L;
        long timeExpression3 = -9223372036854775807L;
        String str = "";
        for (int i9 = 0; i9 < attributeCount; i9++) {
            String attributeName = xmlPullParser.getAttributeName(i9);
            attributeValue = xmlPullParser.getAttributeValue(i9);
            attributeName.hashCode();
            switch (attributeName) {
                case "region":
                    if (map.containsKey(attributeValue)) {
                        str = attributeValue;
                        continue;
                    }
                case "dur":
                    timeExpression3 = parseTimeExpression(attributeValue, frameAndTickRate);
                    break;
                case "end":
                    timeExpression2 = parseTimeExpression(attributeValue, frameAndTickRate);
                    break;
                case "begin":
                    timeExpression = parseTimeExpression(attributeValue, frameAndTickRate);
                    break;
                case "style":
                    String[] styleIds = parseStyleIds(attributeValue);
                    if (styleIds.length > 0) {
                        strArr = styleIds;
                        break;
                    }
                    break;
            }
        }
        if (ttmlNode != null) {
            long j10 = ttmlNode.startTimeUs;
            if (j10 != C3322C.TIME_UNSET) {
                if (timeExpression != C3322C.TIME_UNSET) {
                    timeExpression += j10;
                }
                if (timeExpression2 != C3322C.TIME_UNSET) {
                    timeExpression2 += j10;
                }
            }
        }
        if (timeExpression2 != C3322C.TIME_UNSET) {
            j9 = timeExpression2;
        } else if (timeExpression3 != C3322C.TIME_UNSET) {
            j9 = timeExpression + timeExpression3;
        } else if (ttmlNode != null) {
            long j11 = ttmlNode.endTimeUs;
            if (j11 != C3322C.TIME_UNSET) {
                j9 = j11;
            }
        }
        return TtmlNode.buildNode(xmlPullParser.getName(), timeExpression, j9, styleAttributes, strArr, str);
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser) {
        float f9;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_ID);
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, "origin");
        if (attributeValue2 == null) {
            Log.w(TAG, "Ignoring region without an origin");
            return null;
        }
        Pattern pattern = PERCENTAGE_COORDINATES;
        Matcher matcher = pattern.matcher(attributeValue2);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring region with unsupported origin: " + attributeValue2);
            return null;
        }
        int i9 = 1;
        try {
            float f10 = Float.parseFloat(matcher.group(1)) / 100.0f;
            float f11 = Float.parseFloat(matcher.group(2)) / 100.0f;
            String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
            if (attributeValue3 == null) {
                Log.w(TAG, "Ignoring region without an extent");
                return null;
            }
            Matcher matcher2 = pattern.matcher(attributeValue3);
            if (!matcher2.matches()) {
                Log.w(TAG, "Ignoring region with unsupported extent: " + attributeValue2);
                return null;
            }
            try {
                float f12 = Float.parseFloat(matcher2.group(1)) / 100.0f;
                float f13 = Float.parseFloat(matcher2.group(2)) / 100.0f;
                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                if (attributeValue4 != null) {
                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                    lowerInvariant.hashCode();
                    if (lowerInvariant.equals(TtmlNode.CENTER)) {
                        f11 += f13 / 2.0f;
                        f9 = f11;
                    } else {
                        if (lowerInvariant.equals("after")) {
                            f9 = f11 + f13;
                            i9 = 2;
                        }
                        i9 = 0;
                        f9 = f11;
                    }
                } else {
                    i9 = 0;
                    f9 = f11;
                }
                return new TtmlRegion(attributeValue, f10, f9, 0, i9, f12);
            } catch (NumberFormatException unused) {
                Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                return null;
            }
        } catch (NumberFormatException unused2) {
            Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x018c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        String attributeValue;
        char c9;
        char c10;
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i9 = 0; i9 < attributeCount; i9++) {
            attributeValue = xmlPullParser.getAttributeValue(i9);
            String attributeName = xmlPullParser.getAttributeName(i9);
            attributeName.hashCode();
            c9 = 4;
            c10 = 3;
            switch (attributeName) {
                case "fontStyle":
                    ttmlStyle = createIfNull(ttmlStyle).setItalic(TtmlNode.ITALIC.equalsIgnoreCase(attributeValue));
                    break;
                case "fontFamily":
                    ttmlStyle = createIfNull(ttmlStyle).setFontFamily(attributeValue);
                    break;
                case "textAlign":
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    lowerInvariant.hashCode();
                    switch (lowerInvariant.hashCode()) {
                        case -1364013995:
                            if (lowerInvariant.equals(TtmlNode.CENTER)) {
                                c9 = 0;
                                break;
                            } else {
                                c9 = 65535;
                                break;
                            }
                        case 100571:
                            if (lowerInvariant.equals("end")) {
                                c9 = 1;
                                break;
                            }
                            break;
                        case 3317767:
                            if (lowerInvariant.equals(TtmlNode.LEFT)) {
                                c9 = 2;
                                break;
                            }
                            break;
                        case 108511772:
                            if (lowerInvariant.equals(TtmlNode.RIGHT)) {
                                c9 = 3;
                                break;
                            }
                            break;
                        case 109757538:
                            if (!lowerInvariant.equals(TtmlNode.START)) {
                            }
                            break;
                    }
                    switch (c9) {
                        case 0:
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_CENTER);
                            break;
                        case 1:
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                            break;
                        case 2:
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                            break;
                        case 3:
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                            break;
                        case 4:
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                            break;
                    }
                case "textDecoration":
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    lowerInvariant2.hashCode();
                    switch (lowerInvariant2.hashCode()) {
                        case -1461280213:
                            if (lowerInvariant2.equals(TtmlNode.NO_UNDERLINE)) {
                                c10 = 0;
                                break;
                            } else {
                                c10 = 65535;
                                break;
                            }
                        case -1026963764:
                            if (lowerInvariant2.equals(TtmlNode.UNDERLINE)) {
                                c10 = 1;
                                break;
                            }
                            break;
                        case 913457136:
                            if (lowerInvariant2.equals(TtmlNode.NO_LINETHROUGH)) {
                                c10 = 2;
                                break;
                            }
                            break;
                        case 1679736913:
                            if (!lowerInvariant2.equals(TtmlNode.LINETHROUGH)) {
                            }
                            break;
                    }
                    switch (c10) {
                        case 0:
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(false);
                            break;
                        case 1:
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(true);
                            break;
                        case 2:
                            ttmlStyle = createIfNull(ttmlStyle).setLinethrough(false);
                            break;
                        case 3:
                            ttmlStyle = createIfNull(ttmlStyle).setLinethrough(true);
                            break;
                    }
                case "fontWeight":
                    ttmlStyle = createIfNull(ttmlStyle).setBold(TtmlNode.BOLD.equalsIgnoreCase(attributeValue));
                    break;
                case "id":
                    if ("style".equals(xmlPullParser.getName())) {
                        ttmlStyle = createIfNull(ttmlStyle).setId(attributeValue);
                        break;
                    } else {
                        break;
                    }
                case "color":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setFontColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        Log.w(TAG, "Failed parsing color value: " + attributeValue);
                        break;
                    }
                case "fontSize":
                    try {
                        ttmlStyle = createIfNull(ttmlStyle);
                        parseFontSize(attributeValue, ttmlStyle);
                        break;
                    } catch (SubtitleDecoderException unused2) {
                        Log.w(TAG, "Failed parsing fontSize value: " + attributeValue);
                        break;
                    }
                case "backgroundColor":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setBackgroundColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused3) {
                        Log.w(TAG, "Failed parsing background value: " + attributeValue);
                        break;
                    }
            }
        }
        return ttmlStyle;
    }

    private String[] parseStyleIds(String str) {
        return str.split("\\s+");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException, NumberFormatException {
        double d9;
        double d10;
        Matcher matcher = CLOCK_TIME.matcher(str);
        char c9 = 4;
        if (matcher.matches()) {
            double d11 = (Long.parseLong(matcher.group(1)) * 3600) + (Long.parseLong(matcher.group(2)) * 60) + Long.parseLong(matcher.group(3));
            String strGroup = matcher.group(4);
            double d12 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            double d13 = d11 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d) + (matcher.group(5) != null ? Long.parseLong(r13) / frameAndTickRate.effectiveFrameRate : 0.0d);
            if (matcher.group(6) != null) {
                d12 = (Long.parseLong(r13) / frameAndTickRate.subFrameRate) / frameAndTickRate.effectiveFrameRate;
            }
            return (long) ((d13 + d12) * 1000000.0d);
        }
        Matcher matcher2 = OFFSET_TIME.matcher(str);
        if (!matcher2.matches()) {
            throw new SubtitleDecoderException("Malformed time expression: " + str);
        }
        double d14 = Double.parseDouble(matcher2.group(1));
        String strGroup2 = matcher2.group(2);
        strGroup2.hashCode();
        switch (strGroup2.hashCode()) {
            case 102:
                if (!strGroup2.equals("f")) {
                    c9 = 65535;
                    break;
                } else {
                    c9 = 0;
                    break;
                }
            case 104:
                if (strGroup2.equals("h")) {
                    c9 = 1;
                    break;
                }
                break;
            case 109:
                if (strGroup2.equals("m")) {
                    c9 = 2;
                    break;
                }
                break;
            case 116:
                if (strGroup2.equals("t")) {
                    c9 = 3;
                    break;
                }
                break;
            case 3494:
                if (!strGroup2.equals("ms")) {
                }
                break;
        }
        switch (c9) {
            case 0:
                d9 = frameAndTickRate.effectiveFrameRate;
                d14 /= d9;
                return (long) (d14 * 1000000.0d);
            case 1:
                d10 = 3600.0d;
                break;
            case 2:
                d10 = 60.0d;
                break;
            case 3:
                d9 = frameAndTickRate.tickRate;
                d14 /= d9;
                return (long) (d14 * 1000000.0d);
            case 4:
                d9 = 1000.0d;
                d14 /= d9;
                return (long) (d14 * 1000000.0d);
            default:
                return (long) (d14 * 1000000.0d);
        }
        d14 *= d10;
        return (long) (d14 * 1000000.0d);
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public TtmlSubtitle decode(byte[] bArr, int i9, boolean z8) throws XmlPullParserException, SubtitleDecoderException, NumberFormatException, IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            TtmlSubtitle ttmlSubtitle = null;
            map2.put("", new TtmlRegion(null));
            int i10 = 0;
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(bArr, 0, i9), null);
            LinkedList linkedList = new LinkedList();
            FrameAndTickRate frameAndTickRates = DEFAULT_FRAME_AND_TICK_RATE;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) linkedList.peekLast();
                if (i10 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRates = parseFrameAndTickRates(xmlPullParserNewPullParser);
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            parseHeader(xmlPullParserNewPullParser, map, map2);
                        } else {
                            try {
                                TtmlNode node = parseNode(xmlPullParserNewPullParser, ttmlNode, map2, frameAndTickRates);
                                linkedList.addLast(node);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(node);
                                }
                            } catch (SubtitleDecoderException e9) {
                                Log.w(TAG, "Suppressing parser error", e9);
                            }
                        }
                        i10++;
                    } else if (eventType == 4) {
                        ttmlNode.addChild(TtmlNode.buildTextNode(xmlPullParserNewPullParser.getText()));
                    } else if (eventType == 3) {
                        if (xmlPullParserNewPullParser.getName().equals(TtmlNode.TAG_TT)) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) linkedList.getLast(), map, map2);
                        }
                        linkedList.removeLast();
                    }
                } else if (eventType == 2) {
                    i10++;
                } else if (eventType == 3) {
                    i10--;
                }
                xmlPullParserNewPullParser.next();
            }
            return ttmlSubtitle;
        } catch (IOException e10) {
            throw new IllegalStateException("Unexpected error when reading input.", e10);
        } catch (XmlPullParserException e11) {
            throw new SubtitleDecoderException("Unable to decode source", e11);
        }
    }
}
