package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class WebvttCssStyle {
    public static final int FONT_SIZE_UNIT_EM = 2;
    public static final int FONT_SIZE_UNIT_PERCENT = 3;
    public static final int FONT_SIZE_UNIT_PIXEL = 1;
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f15324ON = 1;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_BOLD_ITALIC = 3;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_NORMAL = 0;
    public static final int UNSPECIFIED = -1;
    private int backgroundColor;
    private int bold;
    private int fontColor;
    private String fontFamily;
    private float fontSize;
    private int fontSizeUnit;
    private boolean hasBackgroundColor;
    private boolean hasFontColor;
    private int italic;
    private int linethrough;
    private List<String> targetClasses;
    private String targetId;
    private String targetTag;
    private String targetVoice;
    private Layout.Alignment textAlign;
    private int underline;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FontSizeUnit {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StyleFlags {
    }

    public WebvttCssStyle() {
        reset();
    }

    private static int updateScoreForMatch(int i9, String str, String str2, int i10) {
        if (str.isEmpty() || i9 == -1) {
            return i9;
        }
        if (str.equals(str2)) {
            return i9 + i10;
        }
        return -1;
    }

    public void cascadeFrom(WebvttCssStyle webvttCssStyle) {
        if (webvttCssStyle.hasFontColor) {
            setFontColor(webvttCssStyle.fontColor);
        }
        int i9 = webvttCssStyle.bold;
        if (i9 != -1) {
            this.bold = i9;
        }
        int i10 = webvttCssStyle.italic;
        if (i10 != -1) {
            this.italic = i10;
        }
        String str = webvttCssStyle.fontFamily;
        if (str != null) {
            this.fontFamily = str;
        }
        if (this.linethrough == -1) {
            this.linethrough = webvttCssStyle.linethrough;
        }
        if (this.underline == -1) {
            this.underline = webvttCssStyle.underline;
        }
        if (this.textAlign == null) {
            this.textAlign = webvttCssStyle.textAlign;
        }
        if (this.fontSizeUnit == -1) {
            this.fontSizeUnit = webvttCssStyle.fontSizeUnit;
            this.fontSize = webvttCssStyle.fontSize;
        }
        if (webvttCssStyle.hasBackgroundColor) {
            setBackgroundColor(webvttCssStyle.backgroundColor);
        }
    }

    public int getBackgroundColor() {
        if (this.hasBackgroundColor) {
            return this.backgroundColor;
        }
        throw new IllegalStateException("Background color not defined.");
    }

    public int getFontColor() {
        if (this.hasFontColor) {
            return this.fontColor;
        }
        throw new IllegalStateException("Font color not defined");
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public int getFontSizeUnit() {
        return this.fontSizeUnit;
    }

    public int getSpecificityScore(String str, String str2, String[] strArr, String str3) {
        if (this.targetId.isEmpty() && this.targetTag.isEmpty() && this.targetClasses.isEmpty() && this.targetVoice.isEmpty()) {
            return str2.isEmpty() ? 1 : 0;
        }
        int iUpdateScoreForMatch = updateScoreForMatch(updateScoreForMatch(updateScoreForMatch(0, this.targetId, str, 1073741824), this.targetTag, str2, 2), this.targetVoice, str3, 4);
        if (iUpdateScoreForMatch == -1 || !Arrays.asList(strArr).containsAll(this.targetClasses)) {
            return 0;
        }
        return iUpdateScoreForMatch + (this.targetClasses.size() * 4);
    }

    public int getStyle() {
        int i9 = this.bold;
        if (i9 == -1 && this.italic == -1) {
            return -1;
        }
        return (i9 == 1 ? 1 : 0) | (this.italic == 1 ? 2 : 0);
    }

    public Layout.Alignment getTextAlign() {
        return this.textAlign;
    }

    public boolean hasBackgroundColor() {
        return this.hasBackgroundColor;
    }

    public boolean hasFontColor() {
        return this.hasFontColor;
    }

    public boolean isLinethrough() {
        return this.linethrough == 1;
    }

    public boolean isUnderline() {
        return this.underline == 1;
    }

    public void reset() {
        this.targetId = "";
        this.targetTag = "";
        this.targetClasses = Collections.emptyList();
        this.targetVoice = "";
        this.fontFamily = null;
        this.hasFontColor = false;
        this.hasBackgroundColor = false;
        this.linethrough = -1;
        this.underline = -1;
        this.bold = -1;
        this.italic = -1;
        this.fontSizeUnit = -1;
        this.textAlign = null;
    }

    public WebvttCssStyle setBackgroundColor(int i9) {
        this.backgroundColor = i9;
        this.hasBackgroundColor = true;
        return this;
    }

    public WebvttCssStyle setBold(boolean z8) {
        this.bold = z8 ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setFontColor(int i9) {
        this.fontColor = i9;
        this.hasFontColor = true;
        return this;
    }

    public WebvttCssStyle setFontFamily(String str) {
        this.fontFamily = Util.toLowerInvariant(str);
        return this;
    }

    public WebvttCssStyle setFontSize(float f9) {
        this.fontSize = f9;
        return this;
    }

    public WebvttCssStyle setFontSizeUnit(short s8) {
        this.fontSizeUnit = s8;
        return this;
    }

    public WebvttCssStyle setItalic(boolean z8) {
        this.italic = z8 ? 1 : 0;
        return this;
    }

    public WebvttCssStyle setLinethrough(boolean z8) {
        this.linethrough = z8 ? 1 : 0;
        return this;
    }

    public void setTargetClasses(String[] strArr) {
        this.targetClasses = Arrays.asList(strArr);
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public void setTargetTagName(String str) {
        this.targetTag = str;
    }

    public void setTargetVoice(String str) {
        this.targetVoice = str;
    }

    public WebvttCssStyle setTextAlign(Layout.Alignment alignment) {
        this.textAlign = alignment;
        return this;
    }

    public WebvttCssStyle setUnderline(boolean z8) {
        this.underline = z8 ? 1 : 0;
        return this;
    }
}
