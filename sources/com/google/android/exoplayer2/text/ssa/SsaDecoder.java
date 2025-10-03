package com.google.android.exoplayer2.text.ssa;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue: ";
    private static final String FORMAT_LINE_PREFIX = "Format: ";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private static final String TAG = "SsaDecoder";
    private int formatEndIndex;
    private int formatKeyCount;
    private int formatStartIndex;
    private int formatTextIndex;
    private final boolean haveInitializationData;

    public SsaDecoder() {
        this(null);
    }

    private void parseDialogueLine(String str, List<Cue> list, LongArray longArray) {
        long timecodeUs;
        if (this.formatKeyCount == 0) {
            Log.w(TAG, "Skipping dialogue line before complete format: " + str);
            return;
        }
        String[] strArrSplit = str.substring(10).split(",", this.formatKeyCount);
        if (strArrSplit.length != this.formatKeyCount) {
            Log.w(TAG, "Skipping dialogue line with fewer columns than format: " + str);
            return;
        }
        long timecodeUs2 = parseTimecodeUs(strArrSplit[this.formatStartIndex]);
        if (timecodeUs2 == C3322C.TIME_UNSET) {
            Log.w(TAG, "Skipping invalid timing: " + str);
            return;
        }
        String str2 = strArrSplit[this.formatEndIndex];
        if (str2.trim().isEmpty()) {
            timecodeUs = -9223372036854775807L;
        } else {
            timecodeUs = parseTimecodeUs(str2);
            if (timecodeUs == C3322C.TIME_UNSET) {
                Log.w(TAG, "Skipping invalid timing: " + str);
                return;
            }
        }
        list.add(new Cue(strArrSplit[this.formatTextIndex].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        longArray.add(timecodeUs2);
        if (timecodeUs != C3322C.TIME_UNSET) {
            list.add(null);
            longArray.add(timecodeUs);
        }
    }

    private void parseEventBody(ParsableByteArray parsableByteArray, List<Cue> list, LongArray longArray) {
        while (true) {
            String line = parsableByteArray.readLine();
            if (line == null) {
                return;
            }
            if (!this.haveInitializationData && line.startsWith(FORMAT_LINE_PREFIX)) {
                parseFormatLine(line);
            } else if (line.startsWith(DIALOGUE_LINE_PREFIX)) {
                parseDialogueLine(line, list, longArray);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parseFormatLine(String str) {
        String[] strArrSplit = TextUtils.split(str.substring(8), ",");
        this.formatKeyCount = strArrSplit.length;
        this.formatStartIndex = -1;
        this.formatEndIndex = -1;
        this.formatTextIndex = -1;
        for (int i9 = 0; i9 < this.formatKeyCount; i9++) {
            String lowerInvariant = Util.toLowerInvariant(strArrSplit[i9].trim());
            lowerInvariant.hashCode();
            switch (lowerInvariant) {
                case "end":
                    this.formatEndIndex = i9;
                    break;
                case "text":
                    this.formatTextIndex = i9;
                    break;
                case "start":
                    this.formatStartIndex = i9;
                    break;
            }
        }
        if (this.formatStartIndex == -1 || this.formatEndIndex == -1 || this.formatTextIndex == -1) {
            this.formatKeyCount = 0;
        }
    }

    private void parseHeader(ParsableByteArray parsableByteArray) {
        String line;
        do {
            line = parsableByteArray.readLine();
            if (line == null) {
                return;
            }
        } while (!line.startsWith("[Events]"));
    }

    public static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str);
        return !matcher.matches() ? C3322C.TIME_UNSET : (Long.parseLong(matcher.group(1)) * 60 * 60 * C3322C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(2)) * 60 * C3322C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(3)) * C3322C.MICROS_PER_SECOND) + (Long.parseLong(matcher.group(4)) * 10000);
    }

    public SsaDecoder(List<byte[]> list) {
        super(TAG);
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            return;
        }
        this.haveInitializationData = true;
        String str = new String(list.get(0));
        Assertions.checkArgument(str.startsWith(FORMAT_LINE_PREFIX));
        parseFormatLine(str);
        parseHeader(new ParsableByteArray(list.get(1)));
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public SsaSubtitle decode(byte[] bArr, int i9, boolean z8) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i9);
        if (!this.haveInitializationData) {
            parseHeader(parsableByteArray);
        }
        parseEventBody(parsableByteArray, arrayList, longArray);
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SsaSubtitle(cueArr, longArray.toArray());
    }
}
