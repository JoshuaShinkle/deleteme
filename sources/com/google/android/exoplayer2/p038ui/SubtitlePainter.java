package com.google.android.exoplayer2.p038ui;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private final float cornerRadius;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final RectF lineBounds = new RectF();
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private float textSizePx;
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.lineSpacingExtra, R.attr.lineSpacingMultiplier}, 0, 0);
        this.spacingAdd = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        float fRound = Math.round((context.getResources().getDisplayMetrics().densityDpi * 2.0f) / 160.0f);
        this.cornerRadius = fRound;
        this.outlineWidth = fRound;
        this.shadowRadius = fRound;
        this.shadowOffset = fRound;
        TextPaint textPaint = new TextPaint();
        this.textPaint = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, (Paint) null);
    }

    private void drawLayout(Canvas canvas, boolean z8) {
        if (z8) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(this.textLeft, this.textTop);
        if (Color.alpha(this.windowColor) > 0) {
            this.paint.setColor(this.windowColor);
            canvas.drawRect(-this.textPaddingX, BitmapDescriptorFactory.HUE_RED, staticLayout.getWidth() + this.textPaddingX, staticLayout.getHeight(), this.paint);
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            this.paint.setColor(this.backgroundColor);
            float lineTop = staticLayout.getLineTop(0);
            int lineCount = staticLayout.getLineCount();
            int i9 = 0;
            while (i9 < lineCount) {
                this.lineBounds.left = staticLayout.getLineLeft(i9) - this.textPaddingX;
                this.lineBounds.right = staticLayout.getLineRight(i9) + this.textPaddingX;
                RectF rectF = this.lineBounds;
                rectF.top = lineTop;
                rectF.bottom = staticLayout.getLineBottom(i9);
                RectF rectF2 = this.lineBounds;
                float f9 = rectF2.bottom;
                float f10 = this.cornerRadius;
                canvas.drawRoundRect(rectF2, f10, f10, this.paint);
                i9++;
                lineTop = f9;
            }
        }
        int i10 = this.edgeType;
        if (i10 == 1) {
            this.textPaint.setStrokeJoin(Paint.Join.ROUND);
            this.textPaint.setStrokeWidth(this.outlineWidth);
            this.textPaint.setColor(this.edgeColor);
            this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            staticLayout.draw(canvas);
        } else if (i10 == 2) {
            TextPaint textPaint = this.textPaint;
            float f11 = this.shadowRadius;
            float f12 = this.shadowOffset;
            textPaint.setShadowLayer(f11, f12, f12, this.edgeColor);
        } else if (i10 == 3 || i10 == 4) {
            boolean z8 = i10 == 3;
            int i11 = z8 ? -1 : this.edgeColor;
            int i12 = z8 ? this.edgeColor : -1;
            float f13 = this.shadowRadius / 2.0f;
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            float f14 = -f13;
            this.textPaint.setShadowLayer(this.shadowRadius, f14, f14, i11);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(this.shadowRadius, f13, f13, i12);
        }
        this.textPaint.setColor(this.foregroundColor);
        this.textPaint.setStyle(Paint.Style.FILL);
        staticLayout.draw(canvas);
        this.textPaint.setShadowLayer(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        canvas.restoreToCount(iSave);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void setupBitmapLayout() {
        float f9;
        int i9;
        float f10;
        int i10 = this.parentRight;
        int i11 = this.parentLeft;
        int i12 = this.parentBottom;
        int i13 = this.parentTop;
        float f11 = i10 - i11;
        float f12 = i11 + (this.cuePosition * f11);
        float f13 = i12 - i13;
        float f14 = i13 + (this.cueLine * f13);
        int iRound = Math.round(f11 * this.cueSize);
        float f15 = this.cueBitmapHeight;
        int iRound2 = f15 != Float.MIN_VALUE ? Math.round(f13 * f15) : Math.round(iRound * (this.cueBitmap.getHeight() / this.cueBitmap.getWidth()));
        int i14 = this.cueLineAnchor;
        if (i14 != 2) {
            if (i14 == 1) {
                f9 = iRound / 2;
            }
            int iRound3 = Math.round(f12);
            i9 = this.cuePositionAnchor;
            if (i9 == 2) {
                if (i9 == 1) {
                    f10 = iRound2 / 2;
                }
                int iRound4 = Math.round(f14);
                this.bitmapRect = new Rect(iRound3, iRound4, iRound + iRound3, iRound2 + iRound4);
            }
            f10 = iRound2;
            f14 -= f10;
            int iRound42 = Math.round(f14);
            this.bitmapRect = new Rect(iRound3, iRound42, iRound + iRound3, iRound2 + iRound42);
        }
        f9 = iRound;
        f12 -= f9;
        int iRound32 = Math.round(f12);
        i9 = this.cuePositionAnchor;
        if (i9 == 2) {
        }
        f14 -= f10;
        int iRound422 = Math.round(f14);
        this.bitmapRect = new Rect(iRound32, iRound422, iRound + iRound32, iRound2 + iRound422);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v18, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v3, types: [android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v6 */
    private void setupTextLayout() {
        ?? spannableStringBuilder;
        int iMax;
        int iMin;
        int i9;
        int iRound;
        int i10;
        int i11 = this.parentRight - this.parentLeft;
        int i12 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.textSizePx);
        int i13 = (int) ((this.textSizePx * INNER_PADDING_RATIO) + 0.5f);
        int i14 = i13 * 2;
        int i15 = i11 - i14;
        float f9 = this.cueSize;
        if (f9 != Float.MIN_VALUE) {
            i15 = (int) (i15 * f9);
        }
        if (i15 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        if (this.applyEmbeddedFontSizes && this.applyEmbeddedStyles) {
            spannableStringBuilder = this.cueText;
        } else if (this.applyEmbeddedStyles) {
            spannableStringBuilder = new SpannableStringBuilder(this.cueText);
            int length = spannableStringBuilder.length();
            AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder.getSpans(0, length, AbsoluteSizeSpan.class);
            RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder.getSpans(0, length, RelativeSizeSpan.class);
            for (AbsoluteSizeSpan absoluteSizeSpan : absoluteSizeSpanArr) {
                spannableStringBuilder.removeSpan(absoluteSizeSpan);
            }
            for (RelativeSizeSpan relativeSizeSpan : relativeSizeSpanArr) {
                spannableStringBuilder.removeSpan(relativeSizeSpan);
            }
        } else {
            spannableStringBuilder = this.cueText.toString();
        }
        CharSequence charSequence = spannableStringBuilder;
        Layout.Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        }
        Layout.Alignment alignment2 = alignment;
        StaticLayout staticLayout = new StaticLayout(charSequence, this.textPaint, i15, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = staticLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int iMax2 = 0;
        for (int i16 = 0; i16 < lineCount; i16++) {
            iMax2 = Math.max((int) Math.ceil(this.textLayout.getLineWidth(i16)), iMax2);
        }
        if (this.cueSize == Float.MIN_VALUE || iMax2 >= i15) {
            i15 = iMax2;
        }
        int i17 = i15 + i14;
        float f10 = this.cuePosition;
        if (f10 != Float.MIN_VALUE) {
            int iRound2 = Math.round(i11 * f10);
            int i18 = this.parentLeft;
            int i19 = iRound2 + i18;
            int i20 = this.cuePositionAnchor;
            if (i20 == 2) {
                i19 -= i17;
            } else if (i20 == 1) {
                i19 = ((i19 * 2) - i17) / 2;
            }
            iMax = Math.max(i19, i18);
            iMin = Math.min(i17 + iMax, this.parentRight);
        } else {
            iMax = (i11 - i17) / 2;
            iMin = iMax + i17;
        }
        int i21 = iMin - iMax;
        if (i21 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        float f11 = this.cueLine;
        if (f11 != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                iRound = Math.round(i12 * f11);
                i10 = this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f12 = this.cueLine;
                if (f12 >= BitmapDescriptorFactory.HUE_RED) {
                    iRound = Math.round(f12 * lineBottom);
                    i10 = this.parentTop;
                } else {
                    iRound = Math.round((f12 + 1.0f) * lineBottom);
                    i10 = this.parentBottom;
                }
            }
            i9 = iRound + i10;
            int i22 = this.cueLineAnchor;
            if (i22 == 2) {
                i9 -= height;
            } else if (i22 == 1) {
                i9 = ((i9 * 2) - height) / 2;
            }
            int i23 = i9 + height;
            int i24 = this.parentBottom;
            if (i23 > i24) {
                i9 = i24 - height;
            } else {
                int i25 = this.parentTop;
                if (i9 < i25) {
                    i9 = i25;
                }
            }
        } else {
            i9 = (this.parentBottom - height) - ((int) (i12 * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(charSequence, this.textPaint, i21, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLeft = iMax;
        this.textTop = i9;
        this.textPaddingX = i13;
    }

    public void draw(Cue cue, boolean z8, boolean z9, CaptionStyleCompat captionStyleCompat, float f9, float f10, Canvas canvas, int i9, int i10, int i11, int i12) {
        int i13;
        boolean z10 = cue.bitmap == null;
        if (!z10) {
            i13 = -16777216;
        } else if (TextUtils.isEmpty(cue.text)) {
            return;
        } else {
            i13 = (cue.windowColorSet && z8) ? cue.windowColor : captionStyleCompat.windowColor;
        }
        if (areCharSequencesEqual(this.cueText, cue.text) && Util.areEqual(this.cueTextAlignment, cue.textAlignment) && this.cueBitmap == cue.bitmap && this.cueLine == cue.line && this.cueLineType == cue.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue.lineAnchor)) && this.cuePosition == cue.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue.positionAnchor)) && this.cueSize == cue.size && this.cueBitmapHeight == cue.bitmapHeight && this.applyEmbeddedStyles == z8 && this.applyEmbeddedFontSizes == z9 && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == i13 && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat.typeface) && this.textSizePx == f9 && this.bottomPaddingFraction == f10 && this.parentLeft == i9 && this.parentTop == i10 && this.parentRight == i11 && this.parentBottom == i12) {
            drawLayout(canvas, z10);
            return;
        }
        this.cueText = cue.text;
        this.cueTextAlignment = cue.textAlignment;
        this.cueBitmap = cue.bitmap;
        this.cueLine = cue.line;
        this.cueLineType = cue.lineType;
        this.cueLineAnchor = cue.lineAnchor;
        this.cuePosition = cue.position;
        this.cuePositionAnchor = cue.positionAnchor;
        this.cueSize = cue.size;
        this.cueBitmapHeight = cue.bitmapHeight;
        this.applyEmbeddedStyles = z8;
        this.applyEmbeddedFontSizes = z9;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = i13;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.textSizePx = f9;
        this.bottomPaddingFraction = f10;
        this.parentLeft = i9;
        this.parentTop = i10;
        this.parentRight = i11;
        this.parentBottom = i12;
        if (z10) {
            setupTextLayout();
        } else {
            setupBitmapLayout();
        }
        drawLayout(canvas, z10);
    }
}
