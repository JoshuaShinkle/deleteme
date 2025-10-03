package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes.dex */
public class SsManifestParser implements ParsingLoadable.Parser<SsManifest> {
    private final XmlPullParserFactory xmlParserFactory;

    public static abstract class ElementParser {
        private final String baseUri;
        private final List<Pair<String, Object>> normalizedAttributes = new LinkedList();
        private final ElementParser parent;
        private final String tag;

        public ElementParser(ElementParser elementParser, String str, String str2) {
            this.parent = elementParser;
            this.baseUri = str;
            this.tag = str2;
        }

        private ElementParser newChildParser(ElementParser elementParser, String str, String str2) {
            if (QualityLevelParser.TAG.equals(str)) {
                return new QualityLevelParser(elementParser, str2);
            }
            if (ProtectionParser.TAG.equals(str)) {
                return new ProtectionParser(elementParser, str2);
            }
            if (StreamIndexParser.TAG.equals(str)) {
                return new StreamIndexParser(elementParser, str2);
            }
            return null;
        }

        public void addChild(Object obj) {
        }

        public abstract Object build();

        public final Object getNormalizedAttribute(String str) {
            for (int i9 = 0; i9 < this.normalizedAttributes.size(); i9++) {
                Pair<String, Object> pair = this.normalizedAttributes.get(i9);
                if (((String) pair.first).equals(str)) {
                    return pair.second;
                }
            }
            ElementParser elementParser = this.parent;
            if (elementParser == null) {
                return null;
            }
            return elementParser.getNormalizedAttribute(str);
        }

        public boolean handleChildInline(String str) {
            return false;
        }

        public final Object parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            boolean z8 = false;
            int i9 = 0;
            while (true) {
                int eventType = xmlPullParser.getEventType();
                if (eventType == 1) {
                    return null;
                }
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (this.tag.equals(name)) {
                        parseStartTag(xmlPullParser);
                        z8 = true;
                    } else if (z8) {
                        if (i9 > 0) {
                            i9++;
                        } else if (handleChildInline(name)) {
                            parseStartTag(xmlPullParser);
                        } else {
                            ElementParser elementParserNewChildParser = newChildParser(this, name, this.baseUri);
                            if (elementParserNewChildParser == null) {
                                i9 = 1;
                            } else {
                                addChild(elementParserNewChildParser.parse(xmlPullParser));
                            }
                        }
                    }
                } else if (eventType != 3) {
                    if (eventType == 4 && z8 && i9 == 0) {
                        parseText(xmlPullParser);
                    }
                } else if (!z8) {
                    continue;
                } else if (i9 > 0) {
                    i9--;
                } else {
                    String name2 = xmlPullParser.getName();
                    parseEndTag(xmlPullParser);
                    if (!handleChildInline(name2)) {
                        return build();
                    }
                }
                xmlPullParser.next();
            }
        }

        public final boolean parseBoolean(XmlPullParser xmlPullParser, String str, boolean z8) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            return attributeValue != null ? Boolean.parseBoolean(attributeValue) : z8;
        }

        public void parseEndTag(XmlPullParser xmlPullParser) {
        }

        public final int parseInt(XmlPullParser xmlPullParser, String str, int i9) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return i9;
            }
            try {
                return Integer.parseInt(attributeValue);
            } catch (NumberFormatException e9) {
                throw new ParserException(e9);
            }
        }

        public final long parseLong(XmlPullParser xmlPullParser, String str, long j9) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return j9;
            }
            try {
                return Long.parseLong(attributeValue);
            } catch (NumberFormatException e9) {
                throw new ParserException(e9);
            }
        }

        public final int parseRequiredInt(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                throw new MissingFieldException(str);
            }
            try {
                return Integer.parseInt(attributeValue);
            } catch (NumberFormatException e9) {
                throw new ParserException(e9);
            }
        }

        public final long parseRequiredLong(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                throw new MissingFieldException(str);
            }
            try {
                return Long.parseLong(attributeValue);
            } catch (NumberFormatException e9) {
                throw new ParserException(e9);
            }
        }

        public final String parseRequiredString(XmlPullParser xmlPullParser, String str) throws MissingFieldException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                return attributeValue;
            }
            throw new MissingFieldException(str);
        }

        public void parseStartTag(XmlPullParser xmlPullParser) {
        }

        public void parseText(XmlPullParser xmlPullParser) {
        }

        public final void putNormalizedAttribute(String str, Object obj) {
            this.normalizedAttributes.add(Pair.create(str, obj));
        }
    }

    public static class MissingFieldException extends ParserException {
        public MissingFieldException(String str) {
            super("Missing required field: " + str);
        }
    }

    public static class ProtectionParser extends ElementParser {
        public static final String KEY_SYSTEM_ID = "SystemID";
        public static final String TAG = "Protection";
        public static final String TAG_PROTECTION_HEADER = "ProtectionHeader";
        private boolean inProtectionHeader;
        private byte[] initData;
        private UUID uuid;

        public ProtectionParser(ElementParser elementParser, String str) {
            super(elementParser, str, TAG);
        }

        private static String stripCurlyBraces(String str) {
            return (str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') ? str.substring(1, str.length() - 1) : str;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public Object build() {
            UUID uuid = this.uuid;
            return new SsManifest.ProtectionElement(uuid, PsshAtomUtil.buildPsshAtom(uuid, this.initData));
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public boolean handleChildInline(String str) {
            return TAG_PROTECTION_HEADER.equals(str);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseEndTag(XmlPullParser xmlPullParser) {
            if (TAG_PROTECTION_HEADER.equals(xmlPullParser.getName())) {
                this.inProtectionHeader = false;
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) {
            if (TAG_PROTECTION_HEADER.equals(xmlPullParser.getName())) {
                this.inProtectionHeader = true;
                this.uuid = UUID.fromString(stripCurlyBraces(xmlPullParser.getAttributeValue(null, KEY_SYSTEM_ID)));
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseText(XmlPullParser xmlPullParser) {
            if (this.inProtectionHeader) {
                this.initData = Base64.decode(xmlPullParser.getText(), 0);
            }
        }
    }

    public static class QualityLevelParser extends ElementParser {
        private static final String KEY_BITRATE = "Bitrate";
        private static final String KEY_CHANNELS = "Channels";
        private static final String KEY_CODEC_PRIVATE_DATA = "CodecPrivateData";
        private static final String KEY_FOUR_CC = "FourCC";
        private static final String KEY_INDEX = "Index";
        private static final String KEY_LANGUAGE = "Language";
        private static final String KEY_MAX_HEIGHT = "MaxHeight";
        private static final String KEY_MAX_WIDTH = "MaxWidth";
        private static final String KEY_SAMPLING_RATE = "SamplingRate";
        private static final String KEY_TYPE = "Type";
        public static final String TAG = "QualityLevel";
        private Format format;

        public QualityLevelParser(ElementParser elementParser, String str) {
            super(elementParser, str, TAG);
        }

        private static List<byte[]> buildCodecSpecificData(String str) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                byte[] bytesFromHexString = Util.getBytesFromHexString(str);
                byte[][] bArrSplitNalUnits = CodecSpecificDataUtil.splitNalUnits(bytesFromHexString);
                if (bArrSplitNalUnits == null) {
                    arrayList.add(bytesFromHexString);
                } else {
                    Collections.addAll(arrayList, bArrSplitNalUnits);
                }
            }
            return arrayList;
        }

        private static String fourCCToMimeType(String str) {
            if (str.equalsIgnoreCase("H264") || str.equalsIgnoreCase("X264") || str.equalsIgnoreCase("AVC1") || str.equalsIgnoreCase("DAVC")) {
                return MimeTypes.VIDEO_H264;
            }
            if (str.equalsIgnoreCase("AAC") || str.equalsIgnoreCase("AACL") || str.equalsIgnoreCase("AACH") || str.equalsIgnoreCase("AACP")) {
                return MimeTypes.AUDIO_AAC;
            }
            if (str.equalsIgnoreCase("TTML")) {
                return MimeTypes.APPLICATION_TTML;
            }
            if (str.equalsIgnoreCase("ac-3") || str.equalsIgnoreCase("dac3")) {
                return MimeTypes.AUDIO_AC3;
            }
            if (str.equalsIgnoreCase("ec-3") || str.equalsIgnoreCase("dec3")) {
                return MimeTypes.AUDIO_E_AC3;
            }
            if (str.equalsIgnoreCase("dtsc")) {
                return MimeTypes.AUDIO_DTS;
            }
            if (str.equalsIgnoreCase("dtsh") || str.equalsIgnoreCase("dtsl")) {
                return MimeTypes.AUDIO_DTS_HD;
            }
            if (str.equalsIgnoreCase("dtse")) {
                return MimeTypes.AUDIO_DTS_EXPRESS;
            }
            if (str.equalsIgnoreCase("opus")) {
                return MimeTypes.AUDIO_OPUS;
            }
            return null;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public Object build() {
            return this.format;
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int iIntValue = ((Integer) getNormalizedAttribute(KEY_TYPE)).intValue();
            String attributeValue = xmlPullParser.getAttributeValue(null, KEY_INDEX);
            int requiredInt = parseRequiredInt(xmlPullParser, KEY_BITRATE);
            String strFourCCToMimeType = fourCCToMimeType(parseRequiredString(xmlPullParser, KEY_FOUR_CC));
            if (iIntValue == 2) {
                this.format = Format.createVideoContainerFormat(attributeValue, MimeTypes.VIDEO_MP4, strFourCCToMimeType, null, requiredInt, parseRequiredInt(xmlPullParser, KEY_MAX_WIDTH), parseRequiredInt(xmlPullParser, KEY_MAX_HEIGHT), -1.0f, buildCodecSpecificData(xmlPullParser.getAttributeValue(null, KEY_CODEC_PRIVATE_DATA)), 0);
                return;
            }
            if (iIntValue != 1) {
                if (iIntValue == 3) {
                    this.format = Format.createTextContainerFormat(attributeValue, MimeTypes.APPLICATION_MP4, strFourCCToMimeType, null, requiredInt, 0, (String) getNormalizedAttribute(KEY_LANGUAGE));
                    return;
                } else {
                    this.format = Format.createContainerFormat(attributeValue, MimeTypes.APPLICATION_MP4, strFourCCToMimeType, null, requiredInt, 0, null);
                    return;
                }
            }
            if (strFourCCToMimeType == null) {
                strFourCCToMimeType = MimeTypes.AUDIO_AAC;
            }
            int requiredInt2 = parseRequiredInt(xmlPullParser, KEY_CHANNELS);
            int requiredInt3 = parseRequiredInt(xmlPullParser, KEY_SAMPLING_RATE);
            List<byte[]> listBuildCodecSpecificData = buildCodecSpecificData(xmlPullParser.getAttributeValue(null, KEY_CODEC_PRIVATE_DATA));
            if (listBuildCodecSpecificData.isEmpty() && MimeTypes.AUDIO_AAC.equals(strFourCCToMimeType)) {
                listBuildCodecSpecificData = Collections.singletonList(CodecSpecificDataUtil.buildAacLcAudioSpecificConfig(requiredInt3, requiredInt2));
            }
            this.format = Format.createAudioContainerFormat(attributeValue, MimeTypes.AUDIO_MP4, strFourCCToMimeType, null, requiredInt, requiredInt2, requiredInt3, listBuildCodecSpecificData, 0, (String) getNormalizedAttribute(KEY_LANGUAGE));
        }
    }

    public static class SmoothStreamingMediaParser extends ElementParser {
        private static final String KEY_DURATION = "Duration";
        private static final String KEY_DVR_WINDOW_LENGTH = "DVRWindowLength";
        private static final String KEY_IS_LIVE = "IsLive";
        private static final String KEY_LOOKAHEAD_COUNT = "LookaheadCount";
        private static final String KEY_MAJOR_VERSION = "MajorVersion";
        private static final String KEY_MINOR_VERSION = "MinorVersion";
        private static final String KEY_TIME_SCALE = "TimeScale";
        public static final String TAG = "SmoothStreamingMedia";
        private long duration;
        private long dvrWindowLength;
        private boolean isLive;
        private int lookAheadCount;
        private int majorVersion;
        private int minorVersion;
        private SsManifest.ProtectionElement protectionElement;
        private final List<SsManifest.StreamElement> streamElements;
        private long timescale;

        public SmoothStreamingMediaParser(ElementParser elementParser, String str) {
            super(elementParser, str, TAG);
            this.lookAheadCount = -1;
            this.protectionElement = null;
            this.streamElements = new LinkedList();
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void addChild(Object obj) {
            if (obj instanceof SsManifest.StreamElement) {
                this.streamElements.add((SsManifest.StreamElement) obj);
            } else if (obj instanceof SsManifest.ProtectionElement) {
                Assertions.checkState(this.protectionElement == null);
                this.protectionElement = (SsManifest.ProtectionElement) obj;
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public Object build() {
            int size = this.streamElements.size();
            SsManifest.StreamElement[] streamElementArr = new SsManifest.StreamElement[size];
            this.streamElements.toArray(streamElementArr);
            if (this.protectionElement != null) {
                SsManifest.ProtectionElement protectionElement = this.protectionElement;
                DrmInitData drmInitData = new DrmInitData(new DrmInitData.SchemeData(protectionElement.uuid, MimeTypes.VIDEO_MP4, protectionElement.data));
                for (int i9 = 0; i9 < size; i9++) {
                    SsManifest.StreamElement streamElement = streamElementArr[i9];
                    int i10 = 0;
                    while (true) {
                        Format[] formatArr = streamElement.formats;
                        if (i10 < formatArr.length) {
                            formatArr[i10] = formatArr[i10].copyWithDrmInitData(drmInitData);
                            i10++;
                        }
                    }
                }
            }
            return new SsManifest(this.majorVersion, this.minorVersion, this.timescale, this.duration, this.dvrWindowLength, this.lookAheadCount, this.isLive, this.protectionElement, streamElementArr);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) {
            this.majorVersion = parseRequiredInt(xmlPullParser, KEY_MAJOR_VERSION);
            this.minorVersion = parseRequiredInt(xmlPullParser, KEY_MINOR_VERSION);
            this.timescale = parseLong(xmlPullParser, KEY_TIME_SCALE, 10000000L);
            this.duration = parseRequiredLong(xmlPullParser, KEY_DURATION);
            this.dvrWindowLength = parseLong(xmlPullParser, KEY_DVR_WINDOW_LENGTH, 0L);
            this.lookAheadCount = parseInt(xmlPullParser, KEY_LOOKAHEAD_COUNT, -1);
            this.isLive = parseBoolean(xmlPullParser, KEY_IS_LIVE, false);
            putNormalizedAttribute(KEY_TIME_SCALE, Long.valueOf(this.timescale));
        }
    }

    public static class StreamIndexParser extends ElementParser {
        private static final String KEY_DISPLAY_HEIGHT = "DisplayHeight";
        private static final String KEY_DISPLAY_WIDTH = "DisplayWidth";
        private static final String KEY_FRAGMENT_DURATION = "d";
        private static final String KEY_FRAGMENT_REPEAT_COUNT = "r";
        private static final String KEY_FRAGMENT_START_TIME = "t";
        private static final String KEY_LANGUAGE = "Language";
        private static final String KEY_MAX_HEIGHT = "MaxHeight";
        private static final String KEY_MAX_WIDTH = "MaxWidth";
        private static final String KEY_NAME = "Name";
        private static final String KEY_SUB_TYPE = "Subtype";
        private static final String KEY_TIME_SCALE = "TimeScale";
        private static final String KEY_TYPE = "Type";
        private static final String KEY_TYPE_AUDIO = "audio";
        private static final String KEY_TYPE_TEXT = "text";
        private static final String KEY_TYPE_VIDEO = "video";
        private static final String KEY_URL = "Url";
        public static final String TAG = "StreamIndex";
        private static final String TAG_STREAM_FRAGMENT = "c";
        private final String baseUri;
        private int displayHeight;
        private int displayWidth;
        private final List<Format> formats;
        private String language;
        private long lastChunkDuration;
        private int maxHeight;
        private int maxWidth;
        private String name;
        private ArrayList<Long> startTimes;
        private String subType;
        private long timescale;
        private int type;
        private String url;

        public StreamIndexParser(ElementParser elementParser, String str) {
            super(elementParser, str, TAG);
            this.baseUri = str;
            this.formats = new LinkedList();
        }

        private void parseStreamElementStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int type = parseType(xmlPullParser);
            this.type = type;
            putNormalizedAttribute(KEY_TYPE, Integer.valueOf(type));
            if (this.type == 3) {
                this.subType = parseRequiredString(xmlPullParser, KEY_SUB_TYPE);
            } else {
                this.subType = xmlPullParser.getAttributeValue(null, KEY_SUB_TYPE);
            }
            this.name = xmlPullParser.getAttributeValue(null, KEY_NAME);
            this.url = parseRequiredString(xmlPullParser, KEY_URL);
            this.maxWidth = parseInt(xmlPullParser, KEY_MAX_WIDTH, -1);
            this.maxHeight = parseInt(xmlPullParser, KEY_MAX_HEIGHT, -1);
            this.displayWidth = parseInt(xmlPullParser, KEY_DISPLAY_WIDTH, -1);
            this.displayHeight = parseInt(xmlPullParser, KEY_DISPLAY_HEIGHT, -1);
            String attributeValue = xmlPullParser.getAttributeValue(null, KEY_LANGUAGE);
            this.language = attributeValue;
            putNormalizedAttribute(KEY_LANGUAGE, attributeValue);
            long j9 = parseInt(xmlPullParser, KEY_TIME_SCALE, -1);
            this.timescale = j9;
            if (j9 == -1) {
                this.timescale = ((Long) getNormalizedAttribute(KEY_TIME_SCALE)).longValue();
            }
            this.startTimes = new ArrayList<>();
        }

        private void parseStreamFragmentStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int size = this.startTimes.size();
            long jLongValue = parseLong(xmlPullParser, KEY_FRAGMENT_START_TIME, C3322C.TIME_UNSET);
            int i9 = 1;
            if (jLongValue == C3322C.TIME_UNSET) {
                if (size == 0) {
                    jLongValue = 0;
                } else {
                    if (this.lastChunkDuration == -1) {
                        throw new ParserException("Unable to infer start time");
                    }
                    jLongValue = this.lastChunkDuration + this.startTimes.get(size - 1).longValue();
                }
            }
            this.startTimes.add(Long.valueOf(jLongValue));
            this.lastChunkDuration = parseLong(xmlPullParser, KEY_FRAGMENT_DURATION, C3322C.TIME_UNSET);
            long j9 = parseLong(xmlPullParser, KEY_FRAGMENT_REPEAT_COUNT, 1L);
            if (j9 > 1 && this.lastChunkDuration == C3322C.TIME_UNSET) {
                throw new ParserException("Repeated chunk with unspecified duration");
            }
            while (true) {
                long j10 = i9;
                if (j10 >= j9) {
                    return;
                }
                this.startTimes.add(Long.valueOf((this.lastChunkDuration * j10) + jLongValue));
                i9++;
            }
        }

        private int parseType(XmlPullParser xmlPullParser) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, KEY_TYPE);
            if (attributeValue == null) {
                throw new MissingFieldException(KEY_TYPE);
            }
            if ("audio".equalsIgnoreCase(attributeValue)) {
                return 1;
            }
            if ("video".equalsIgnoreCase(attributeValue)) {
                return 2;
            }
            if ("text".equalsIgnoreCase(attributeValue)) {
                return 3;
            }
            throw new ParserException("Invalid key value[" + attributeValue + "]");
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void addChild(Object obj) {
            if (obj instanceof Format) {
                this.formats.add((Format) obj);
            }
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public Object build() {
            Format[] formatArr = new Format[this.formats.size()];
            this.formats.toArray(formatArr);
            return new SsManifest.StreamElement(this.baseUri, this.url, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, formatArr, this.startTimes, this.lastChunkDuration);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public boolean handleChildInline(String str) {
            return TAG_STREAM_FRAGMENT.equals(str);
        }

        @Override // com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser.ElementParser
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            if (TAG_STREAM_FRAGMENT.equals(xmlPullParser.getName())) {
                parseStreamFragmentStartTag(xmlPullParser);
            } else {
                parseStreamElementStartTag(xmlPullParser);
            }
        }
    }

    public SsManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e9) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e9);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public SsManifest parse(Uri uri, InputStream inputStream) throws XmlPullParserException, ParserException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            return (SsManifest) new SmoothStreamingMediaParser(null, uri.toString()).parse(xmlPullParserNewPullParser);
        } catch (XmlPullParserException e9) {
            throw new ParserException(e9);
        }
    }
}
