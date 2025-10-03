package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p147n5.C5366d;

/* loaded from: classes.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");

    public static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            String strTrim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return true;
            }
            do {
                String line = this.reader.readLine();
                this.next = line;
                if (line == null) {
                    return false;
                }
                strTrim = line.trim();
                this.next = strTrim;
            } while (strTrim.isEmpty());
            return true;
        }

        public String next() {
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int i9 = bufferedReader.read();
        if (i9 == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            i9 = bufferedReader.read();
        }
        int iSkipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, i9);
        for (int i10 = 0; i10 < 7; i10++) {
            if (iSkipIgnorableWhitespace != PLAYLIST_HEADER.charAt(i10)) {
                return false;
            }
            iSkipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, iSkipIgnorableWhitespace));
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(" + BOOLEAN_FALSE + "|" + BOOLEAN_TRUE + ")");
    }

    private static boolean parseBooleanAttribute(String str, Pattern pattern, boolean z8) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals(BOOLEAN_TRUE) : z8;
    }

    private static double parseDoubleAttr(String str, Pattern pattern) {
        return Double.parseDouble(parseStringAttr(str, pattern));
    }

    private static int parseIntAttr(String str, Pattern pattern) {
        return Integer.parseInt(parseStringAttr(str, pattern));
    }

    private static long parseLongAttr(String str, Pattern pattern) {
        return Long.parseLong(parseStringAttr(str, pattern));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator lineIterator, String str) throws ParserException, NumberFormatException {
        String str2;
        int selectionFlags;
        String optionalStringAttr;
        String stringAttr;
        String optionalStringAttr2;
        String optionalStringAttr3;
        int i9;
        String str3;
        int i10;
        int i11;
        int i12;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        boolean zContains = false;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith(TAG_PREFIX)) {
                arrayList5.add(next);
            }
            if (next.startsWith(TAG_MEDIA)) {
                arrayList4.add(next);
            } else if (next.startsWith(TAG_STREAM_INF)) {
                zContains |= next.contains(ATTR_CLOSED_CAPTIONS_NONE);
                int intAttr = parseIntAttr(next, REGEX_BANDWIDTH);
                String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_AVERAGE_BANDWIDTH);
                if (optionalStringAttr4 != null) {
                    intAttr = Integer.parseInt(optionalStringAttr4);
                }
                int i13 = intAttr;
                String optionalStringAttr5 = parseOptionalStringAttr(next, REGEX_CODECS);
                String optionalStringAttr6 = parseOptionalStringAttr(next, REGEX_RESOLUTION);
                if (optionalStringAttr6 != null) {
                    String[] strArrSplit = optionalStringAttr6.split("x");
                    int i14 = Integer.parseInt(strArrSplit[0]);
                    int i15 = Integer.parseInt(strArrSplit[1]);
                    if (i14 <= 0 || i15 <= 0) {
                        i12 = -1;
                        i15 = -1;
                    } else {
                        i12 = i14;
                    }
                    i10 = i12;
                    i11 = i15;
                } else {
                    i10 = -1;
                    i11 = -1;
                }
                String optionalStringAttr7 = parseOptionalStringAttr(next, REGEX_FRAME_RATE);
                float f9 = optionalStringAttr7 != null ? Float.parseFloat(optionalStringAttr7) : -1.0f;
                String optionalStringAttr8 = parseOptionalStringAttr(next, REGEX_AUDIO);
                if (optionalStringAttr8 != null && optionalStringAttr5 != null) {
                    map.put(optionalStringAttr8, Util.getCodecsOfType(optionalStringAttr5, 1));
                }
                String next2 = lineIterator.next();
                if (hashSet.add(next2)) {
                    arrayList.add(new HlsMasterPlaylist.HlsUrl(next2, Format.createVideoContainerFormat(Integer.toString(arrayList.size()), MimeTypes.APPLICATION_M3U8, null, optionalStringAttr5, i13, i10, i11, f9, null, 0)));
                }
            }
        }
        int i16 = 0;
        Format format = null;
        ArrayList arrayList6 = null;
        while (i16 < arrayList4.size()) {
            str2 = (String) arrayList4.get(i16);
            selectionFlags = parseSelectionFlags(str2);
            optionalStringAttr = parseOptionalStringAttr(str2, REGEX_URI);
            stringAttr = parseStringAttr(str2, REGEX_NAME);
            optionalStringAttr2 = parseOptionalStringAttr(str2, REGEX_LANGUAGE);
            optionalStringAttr3 = parseOptionalStringAttr(str2, REGEX_GROUP_ID);
            String stringAttr2 = parseStringAttr(str2, REGEX_TYPE);
            stringAttr2.hashCode();
            ArrayList arrayList7 = arrayList4;
            switch (stringAttr2) {
                case "SUBTITLES":
                    arrayList3.add(new HlsMasterPlaylist.HlsUrl(optionalStringAttr, Format.createTextContainerFormat(stringAttr, MimeTypes.APPLICATION_M3U8, MimeTypes.TEXT_VTT, null, -1, selectionFlags, optionalStringAttr2)));
                    break;
                case "CLOSED-CAPTIONS":
                    String stringAttr3 = parseStringAttr(str2, REGEX_INSTREAM_ID);
                    if (stringAttr3.startsWith("CC")) {
                        i9 = Integer.parseInt(stringAttr3.substring(2));
                        str3 = MimeTypes.APPLICATION_CEA608;
                    } else {
                        i9 = Integer.parseInt(stringAttr3.substring(7));
                        str3 = MimeTypes.APPLICATION_CEA708;
                    }
                    int i17 = i9;
                    String str4 = str3;
                    if (arrayList6 == null) {
                        arrayList6 = new ArrayList();
                    }
                    arrayList6.add(Format.createTextContainerFormat(stringAttr, null, str4, null, -1, selectionFlags, optionalStringAttr2, i17));
                    break;
                case "AUDIO":
                    String str5 = (String) map.get(optionalStringAttr3);
                    Format formatCreateAudioContainerFormat = Format.createAudioContainerFormat(stringAttr, MimeTypes.APPLICATION_M3U8, str5 != null ? MimeTypes.getMediaMimeType(str5) : null, str5, -1, -1, -1, null, selectionFlags, optionalStringAttr2);
                    if (optionalStringAttr != null) {
                        arrayList2.add(new HlsMasterPlaylist.HlsUrl(optionalStringAttr, formatCreateAudioContainerFormat));
                        break;
                    } else {
                        format = formatCreateAudioContainerFormat;
                        break;
                    }
            }
            i16++;
            arrayList4 = arrayList7;
        }
        return new HlsMasterPlaylist(str, arrayList5, arrayList, arrayList2, arrayList3, format, zContains ? Collections.emptyList() : arrayList6);
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator lineIterator, String str) throws ParserException, NumberFormatException {
        DrmInitData.SchemeData widevineSchemeData;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        char c9 = 0;
        boolean z8 = false;
        int i9 = 0;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        long doubleAttr = -9223372036854775807L;
        long intAttr = -9223372036854775807L;
        int intAttr2 = 1;
        long jMsToUs = 0;
        long j9 = 0;
        DrmInitData drmInitData = null;
        HlsMediaPlaylist.Segment segment = null;
        long j10 = 0;
        long longAttr = 0;
        long j11 = -1;
        long j12 = 0;
        String stringAttr = null;
        String str2 = null;
        long doubleAttr2 = 0;
        int i10 = 0;
        int i11 = 0;
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            if (next.startsWith(TAG_PREFIX)) {
                arrayList2.add(next);
            }
            if (next.startsWith(TAG_PLAYLIST_TYPE)) {
                String stringAttr2 = parseStringAttr(next, REGEX_PLAYLIST_TYPE);
                if (NetworkLive.STATE.VIDEO_ON_DEMAND.equals(stringAttr2)) {
                    i10 = 1;
                } else if (C5366d.type.equals(stringAttr2)) {
                    i10 = 2;
                }
            } else if (next.startsWith(TAG_START)) {
                doubleAttr = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
            } else if (next.startsWith(TAG_INIT_SEGMENT)) {
                String stringAttr3 = parseStringAttr(next, REGEX_URI);
                String optionalStringAttr = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE);
                if (optionalStringAttr != null) {
                    String[] strArrSplit = optionalStringAttr.split("@");
                    j11 = Long.parseLong(strArrSplit[c9]);
                    if (strArrSplit.length > 1) {
                        j10 = Long.parseLong(strArrSplit[1]);
                    }
                }
                segment = new HlsMediaPlaylist.Segment(stringAttr3, j10, j11);
                j10 = 0;
                j11 = -1;
            } else if (next.startsWith(TAG_TARGET_DURATION)) {
                intAttr = C3322C.MICROS_PER_SECOND * parseIntAttr(next, REGEX_TARGET_DURATION);
            } else if (next.startsWith(TAG_MEDIA_SEQUENCE)) {
                longAttr = parseLongAttr(next, REGEX_MEDIA_SEQUENCE);
                j9 = longAttr;
            } else if (next.startsWith(TAG_VERSION)) {
                intAttr2 = parseIntAttr(next, REGEX_VERSION);
            } else if (next.startsWith(TAG_MEDIA_DURATION)) {
                doubleAttr2 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
            } else if (next.startsWith(TAG_KEY)) {
                String optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_METHOD);
                String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT);
                if (METHOD_NONE.equals(optionalStringAttr2)) {
                    stringAttr = null;
                    str2 = null;
                } else {
                    String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_IV);
                    if (!KEYFORMAT_IDENTITY.equals(optionalStringAttr3) && optionalStringAttr3 != null) {
                        if (optionalStringAttr2 != null && (widevineSchemeData = parseWidevineSchemeData(next, optionalStringAttr3)) != null) {
                            String str3 = (METHOD_SAMPLE_AES_CENC.equals(optionalStringAttr2) || METHOD_SAMPLE_AES_CTR.equals(optionalStringAttr2)) ? C3322C.CENC_TYPE_cenc : C3322C.CENC_TYPE_cbcs;
                            DrmInitData.SchemeData[] schemeDataArr = new DrmInitData.SchemeData[1];
                            schemeDataArr[c9] = widevineSchemeData;
                            str2 = optionalStringAttr4;
                            drmInitData = new DrmInitData(str3, schemeDataArr);
                        }
                        stringAttr = null;
                    } else if (METHOD_AES_128.equals(optionalStringAttr2)) {
                        str2 = optionalStringAttr4;
                        stringAttr = parseStringAttr(next, REGEX_URI);
                    }
                    str2 = optionalStringAttr4;
                    stringAttr = null;
                }
            } else if (next.startsWith(TAG_BYTERANGE)) {
                String[] strArrSplit2 = parseStringAttr(next, REGEX_BYTERANGE).split("@");
                j11 = Long.parseLong(strArrSplit2[c9]);
                if (strArrSplit2.length > 1) {
                    j10 = Long.parseLong(strArrSplit2[1]);
                }
            } else if (next.startsWith(TAG_DISCONTINUITY_SEQUENCE)) {
                i9 = Integer.parseInt(next.substring(next.indexOf(58) + 1));
                z8 = true;
            } else if (next.equals(TAG_DISCONTINUITY)) {
                i11++;
            } else if (next.startsWith(TAG_PROGRAM_DATE_TIME)) {
                if (jMsToUs == 0) {
                    jMsToUs = C3322C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + 1))) - j12;
                } else {
                    c9 = 0;
                }
            } else if (next.equals(TAG_GAP)) {
                z11 = true;
            } else if (next.equals(TAG_INDEPENDENT_SEGMENTS)) {
                z9 = true;
            } else if (next.equals(TAG_ENDLIST)) {
                z10 = true;
            } else if (next.startsWith("#")) {
                c9 = 0;
            } else {
                String hexString = stringAttr == null ? null : str2 != null ? str2 : Long.toHexString(longAttr);
                long j13 = longAttr + 1;
                if (j11 == -1) {
                    j10 = 0;
                }
                arrayList.add(new HlsMediaPlaylist.Segment(next, doubleAttr2, i11, j12, stringAttr, hexString, j10, j11, z11));
                j12 += doubleAttr2;
                if (j11 != -1) {
                    j10 += j11;
                }
                longAttr = j13;
                j11 = -1;
                c9 = 0;
                z11 = false;
                doubleAttr2 = 0;
            }
        }
        return new HlsMediaPlaylist(i10, str, arrayList2, doubleAttr, jMsToUs, z8, i9, j9, intAttr2, intAttr, z9, z10, jMsToUs != 0, drmInitData, segment, arrayList);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static int parseSelectionFlags(String str) {
        return (parseBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0) | (parseBooleanAttribute(str, REGEX_FORCED, false) ? 2 : 0) | (parseBooleanAttribute(str, REGEX_AUTOSELECT, false) ? 4 : 0);
    }

    private static String parseStringAttr(String str, Pattern pattern) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static DrmInitData.SchemeData parseWidevineSchemeData(String str, String str2) throws ParserException {
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(str2)) {
            String stringAttr = parseStringAttr(str, REGEX_URI);
            return new DrmInitData.SchemeData(C3322C.WIDEVINE_UUID, MimeTypes.VIDEO_MP4, Base64.decode(stringAttr.substring(stringAttr.indexOf(44)), 0));
        }
        if (!KEYFORMAT_WIDEVINE_PSSH_JSON.equals(str2)) {
            return null;
        }
        try {
            return new DrmInitData.SchemeData(C3322C.WIDEVINE_UUID, "hls", str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e9) {
            throw new ParserException(e9);
        }
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z8, int i9) throws IOException {
        while (i9 != -1 && Character.isWhitespace(i9) && (z8 || !Util.isLinebreak(i9))) {
            i9 = bufferedReader.read();
        }
        return i9;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String strTrim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (!checkPlaylistHeader(bufferedReader)) {
                throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    Util.closeQuietly(bufferedReader);
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
                strTrim = line.trim();
                if (!strTrim.isEmpty()) {
                    if (!strTrim.startsWith(TAG_STREAM_INF)) {
                        if (strTrim.startsWith(TAG_TARGET_DURATION) || strTrim.startsWith(TAG_MEDIA_SEQUENCE) || strTrim.startsWith(TAG_MEDIA_DURATION) || strTrim.startsWith(TAG_KEY) || strTrim.startsWith(TAG_BYTERANGE) || strTrim.equals(TAG_DISCONTINUITY) || strTrim.equals(TAG_DISCONTINUITY_SEQUENCE) || strTrim.equals(TAG_ENDLIST)) {
                            break;
                        }
                        arrayDeque.add(strTrim);
                    } else {
                        arrayDeque.add(strTrim);
                        return parseMasterPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                    }
                }
            }
            arrayDeque.add(strTrim);
            return parseMediaPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
        } finally {
            Util.closeQuietly(bufferedReader);
        }
    }
}
