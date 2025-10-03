package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.webvtt.WebvttCue;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public final class WebvttCueParser {
    private static final char CHAR_AMPERSAND = '&';
    private static final char CHAR_GREATER_THAN = '>';
    private static final char CHAR_LESS_THAN = '<';
    private static final char CHAR_SEMI_COLON = ';';
    private static final char CHAR_SLASH = '/';
    private static final char CHAR_SPACE = ' ';
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    private static final String ENTITY_AMPERSAND = "amp";
    private static final String ENTITY_GREATER_THAN = "gt";
    private static final String ENTITY_LESS_THAN = "lt";
    private static final String ENTITY_NON_BREAK_SPACE = "nbsp";
    private static final int STYLE_BOLD = 1;
    private static final int STYLE_ITALIC = 2;
    private static final String TAG = "WebvttCueParser";
    private static final String TAG_BOLD = "b";
    private static final String TAG_CLASS = "c";
    private static final String TAG_ITALIC = "i";
    private static final String TAG_LANG = "lang";
    private static final String TAG_UNDERLINE = "u";
    private static final String TAG_VOICE = "v";
    private final StringBuilder textBuilder = new StringBuilder();

    public static final class StartTag {
        private static final String[] NO_CLASSES = new String[0];
        public final String[] classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String str, int i9, String str2, String[] strArr) {
            this.position = i9;
            this.name = str;
            this.voice = str2;
            this.classes = strArr;
        }

        public static StartTag buildStartTag(String str, int i9) {
            String str2;
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            int iIndexOf = strTrim.indexOf(StringUtils.SPACE);
            if (iIndexOf == -1) {
                str2 = "";
            } else {
                String strTrim2 = strTrim.substring(iIndexOf).trim();
                strTrim = strTrim.substring(0, iIndexOf);
                str2 = strTrim2;
            }
            String[] strArrSplit = strTrim.split("\\.");
            return new StartTag(strArrSplit[0], i9, str2, strArrSplit.length > 1 ? (String[]) Arrays.copyOfRange(strArrSplit, 1, strArrSplit.length) : NO_CLASSES);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", new String[0]);
        }
    }

    public static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i9, WebvttCssStyle webvttCssStyle) {
            this.score = i9;
            this.style = webvttCssStyle;
        }

        @Override // java.lang.Comparable
        public int compareTo(StyleMatch styleMatch) {
            return this.score - styleMatch.score;
        }
    }

    private static void applyEntity(String str, SpannableStringBuilder spannableStringBuilder) {
        str.hashCode();
        switch (str) {
            case "gt":
                spannableStringBuilder.append(CHAR_GREATER_THAN);
                break;
            case "lt":
                spannableStringBuilder.append(CHAR_LESS_THAN);
                break;
            case "amp":
                spannableStringBuilder.append(CHAR_AMPERSAND);
                break;
            case "nbsp":
                spannableStringBuilder.append(CHAR_SPACE);
                break;
            default:
                Log.w(TAG, "ignoring unsupported entity: '&" + str + ";'");
                break;
        }
    }

    private static void applySpansForTag(String str, StartTag startTag, SpannableStringBuilder spannableStringBuilder, List<WebvttCssStyle> list, List<StyleMatch> list2) {
        int i9;
        int length;
        i9 = startTag.position;
        length = spannableStringBuilder.length();
        String str2 = startTag.name;
        str2.hashCode();
        switch (str2) {
            case "":
            case "c":
            case "v":
            case "lang":
                break;
            case "b":
                spannableStringBuilder.setSpan(new StyleSpan(1), i9, length, 33);
                break;
            case "i":
                spannableStringBuilder.setSpan(new StyleSpan(2), i9, length, 33);
                break;
            case "u":
                spannableStringBuilder.setSpan(new UnderlineSpan(), i9, length, 33);
                break;
            default:
                return;
        }
        list2.clear();
        getApplicableStyles(list, str, startTag, list2);
        int size = list2.size();
        for (int i10 = 0; i10 < size; i10++) {
            applyStyleToText(spannableStringBuilder, list2.get(i10).style, i9, length);
        }
    }

    private static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i9, int i10) {
        if (webvttCssStyle == null) {
            return;
        }
        if (webvttCssStyle.getStyle() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(webvttCssStyle.getStyle()), i9, i10, 33);
        }
        if (webvttCssStyle.isLinethrough()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i9, i10, 33);
        }
        if (webvttCssStyle.isUnderline()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i9, i10, 33);
        }
        if (webvttCssStyle.hasFontColor()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(webvttCssStyle.getFontColor()), i9, i10, 33);
        }
        if (webvttCssStyle.hasBackgroundColor()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i9, i10, 33);
        }
        if (webvttCssStyle.getFontFamily() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(webvttCssStyle.getFontFamily()), i9, i10, 33);
        }
        if (webvttCssStyle.getTextAlign() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(webvttCssStyle.getTextAlign()), i9, i10, 33);
        }
        int fontSizeUnit = webvttCssStyle.getFontSizeUnit();
        if (fontSizeUnit == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) webvttCssStyle.getFontSize(), true), i9, i10, 33);
        } else if (fontSizeUnit == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize()), i9, i10, 33);
        } else {
            if (fontSizeUnit != 3) {
                return;
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(webvttCssStyle.getFontSize() / 100.0f), i9, i10, 33);
        }
    }

    private static int findEndOfTag(String str, int i9) {
        int iIndexOf = str.indexOf(62, i9);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    private static void getApplicableStyles(List<WebvttCssStyle> list, String str, StartTag startTag, List<StyleMatch> list2) {
        int size = list.size();
        for (int i9 = 0; i9 < size; i9++) {
            WebvttCssStyle webvttCssStyle = list.get(i9);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                list2.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(list2);
    }

    private static String getTagName(String str) {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return strTrim.split("[ \\.]")[0];
    }

    private static boolean isSupportedTag(String str) {
        str.hashCode();
        switch (str) {
            case "b":
            case "c":
            case "i":
            case "u":
            case "v":
            case "lang":
                return true;
            default:
                return false;
        }
    }

    public static void parseCueSettingsList(String str, WebvttCue.Builder builder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            try {
                if ("line".equals(strGroup)) {
                    parseLineAttribute(strGroup2, builder);
                } else if ("align".equals(strGroup)) {
                    builder.setTextAlignment(parseTextAlignment(strGroup2));
                } else if ("position".equals(strGroup)) {
                    parsePositionAttribute(strGroup2, builder);
                } else if ("size".equals(strGroup)) {
                    builder.setWidth(WebvttParserUtil.parsePercentage(strGroup2));
                } else {
                    Log.w(TAG, "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w(TAG, "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    public static void parseCueText(String str, String str2, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Stack stack = new Stack();
        ArrayList arrayList = new ArrayList();
        int i9 = 0;
        while (i9 < str2.length()) {
            char cCharAt = str2.charAt(i9);
            if (cCharAt == '&') {
                i9++;
                int iIndexOf = str2.indexOf(59, i9);
                int iIndexOf2 = str2.indexOf(32, i9);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    applyEntity(str2.substring(i9, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append(StringUtils.SPACE);
                    }
                    i9 = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt != '<') {
                spannableStringBuilder.append(cCharAt);
                i9++;
            } else {
                int iFindEndOfTag = i9 + 1;
                if (iFindEndOfTag < str2.length()) {
                    boolean z8 = str2.charAt(iFindEndOfTag) == '/';
                    iFindEndOfTag = findEndOfTag(str2, iFindEndOfTag);
                    int i10 = iFindEndOfTag - 2;
                    boolean z9 = str2.charAt(i10) == '/';
                    int i11 = i9 + (z8 ? 2 : 1);
                    if (!z9) {
                        i10 = iFindEndOfTag - 1;
                    }
                    String strSubstring = str2.substring(i11, i10);
                    String tagName = getTagName(strSubstring);
                    if (tagName != null && isSupportedTag(tagName)) {
                        if (z8) {
                            while (!stack.isEmpty()) {
                                StartTag startTag = (StartTag) stack.pop();
                                applySpansForTag(str, startTag, spannableStringBuilder, list, arrayList);
                                if (startTag.name.equals(tagName)) {
                                    break;
                                }
                            }
                        } else if (!z9) {
                            stack.push(StartTag.buildStartTag(strSubstring, spannableStringBuilder.length()));
                        }
                    }
                }
                i9 = iFindEndOfTag;
            }
        }
        while (!stack.isEmpty()) {
            applySpansForTag(str, (StartTag) stack.pop(), spannableStringBuilder, list, arrayList);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), spannableStringBuilder, list, arrayList);
        builder.setText(spannableStringBuilder);
    }

    private static void parseLineAttribute(String str, WebvttCue.Builder builder) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setLineAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setLineAnchor(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            builder.setLine(WebvttParserUtil.parsePercentage(str)).setLineType(0);
            return;
        }
        int i9 = Integer.parseInt(str);
        if (i9 < 0) {
            i9--;
        }
        builder.setLine(i9).setLineType(1);
    }

    private static int parsePositionAnchor(String str) {
        str.hashCode();
        switch (str) {
            case "center":
            case "middle":
                return 1;
            case "end":
                return 2;
            case "start":
                return 0;
            default:
                Log.w(TAG, "Invalid anchor value: " + str);
                return Integer.MIN_VALUE;
        }
    }

    private static void parsePositionAttribute(String str, WebvttCue.Builder builder) {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            builder.setPositionAnchor(parsePositionAnchor(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            builder.setPositionAnchor(Integer.MIN_VALUE);
        }
        builder.setPosition(WebvttParserUtil.parsePercentage(str));
    }

    private static Layout.Alignment parseTextAlignment(String str) {
        str.hashCode();
        switch (str) {
            case "center":
            case "middle":
                return Layout.Alignment.ALIGN_CENTER;
            case "end":
            case "right":
                return Layout.Alignment.ALIGN_OPPOSITE;
            case "left":
            case "start":
                return Layout.Alignment.ALIGN_NORMAL;
            default:
                Log.w(TAG, "Invalid alignment value: " + str);
                return null;
        }
    }

    public boolean parseCue(ParsableByteArray parsableByteArray, WebvttCue.Builder builder, List<WebvttCssStyle> list) {
        String line = parsableByteArray.readLine();
        if (line == null) {
            return false;
        }
        Pattern pattern = CUE_HEADER_PATTERN;
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return parseCue(null, matcher, parsableByteArray, builder, this.textBuilder, list);
        }
        String line2 = parsableByteArray.readLine();
        if (line2 == null) {
            return false;
        }
        Matcher matcher2 = pattern.matcher(line2);
        if (matcher2.matches()) {
            return parseCue(line.trim(), matcher2, parsableByteArray, builder, this.textBuilder, list);
        }
        return false;
    }

    private static boolean parseCue(String str, Matcher matcher, ParsableByteArray parsableByteArray, WebvttCue.Builder builder, StringBuilder sb, List<WebvttCssStyle> list) {
        try {
            builder.setStartTime(WebvttParserUtil.parseTimestampUs(matcher.group(1))).setEndTime(WebvttParserUtil.parseTimestampUs(matcher.group(2)));
            parseCueSettingsList(matcher.group(3), builder);
            sb.setLength(0);
            while (true) {
                String line = parsableByteArray.readLine();
                if (!TextUtils.isEmpty(line)) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line.trim());
                } else {
                    parseCueText(str, sb.toString(), builder, list);
                    return true;
                }
            }
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }
}
