package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes.dex */
final class TtmlRenderUtil {
    private TtmlRenderUtil() {
    }

    public static void applyStylesToSpan(SpannableStringBuilder spannableStringBuilder, int i9, int i10, TtmlStyle ttmlStyle) {
        if (ttmlStyle.getStyle() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(ttmlStyle.getStyle()), i9, i10, 33);
        }
        if (ttmlStyle.isLinethrough()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i9, i10, 33);
        }
        if (ttmlStyle.isUnderline()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i9, i10, 33);
        }
        if (ttmlStyle.hasFontColor()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ttmlStyle.getFontColor()), i9, i10, 33);
        }
        if (ttmlStyle.hasBackgroundColor()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(ttmlStyle.getBackgroundColor()), i9, i10, 33);
        }
        if (ttmlStyle.getFontFamily() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(ttmlStyle.getFontFamily()), i9, i10, 33);
        }
        if (ttmlStyle.getTextAlign() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(ttmlStyle.getTextAlign()), i9, i10, 33);
        }
        int fontSizeUnit = ttmlStyle.getFontSizeUnit();
        if (fontSizeUnit == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) ttmlStyle.getFontSize(), true), i9, i10, 33);
        } else if (fontSizeUnit == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(ttmlStyle.getFontSize()), i9, i10, 33);
        } else {
            if (fontSizeUnit != 3) {
                return;
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(ttmlStyle.getFontSize() / 100.0f), i9, i10, 33);
        }
    }

    public static String applyTextElementSpacePolicy(String str) {
        return str.replaceAll(IOUtils.LINE_SEPARATOR_WINDOWS, "\n").replaceAll(" *\n *", "\n").replaceAll("\n", StringUtils.SPACE).replaceAll("[ \t\\x0B\f\r]+", StringUtils.SPACE);
    }

    public static void endParagraph(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length < 0 || spannableStringBuilder.charAt(length) == '\n') {
            return;
        }
        spannableStringBuilder.append('\n');
    }

    public static TtmlStyle resolveStyle(TtmlStyle ttmlStyle, String[] strArr, Map<String, TtmlStyle> map) {
        if (ttmlStyle == null && strArr == null) {
            return null;
        }
        int i9 = 0;
        if (ttmlStyle == null && strArr.length == 1) {
            return map.get(strArr[0]);
        }
        if (ttmlStyle == null && strArr.length > 1) {
            TtmlStyle ttmlStyle2 = new TtmlStyle();
            int length = strArr.length;
            while (i9 < length) {
                ttmlStyle2.chain(map.get(strArr[i9]));
                i9++;
            }
            return ttmlStyle2;
        }
        if (ttmlStyle != null && strArr != null && strArr.length == 1) {
            return ttmlStyle.chain(map.get(strArr[0]));
        }
        if (ttmlStyle != null && strArr != null && strArr.length > 1) {
            int length2 = strArr.length;
            while (i9 < length2) {
                ttmlStyle.chain(map.get(strArr[i9]));
                i9++;
            }
        }
        return ttmlStyle;
    }
}
