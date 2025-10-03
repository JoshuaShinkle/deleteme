package com.google.android.exoplayer2.p038ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class SubtitleView extends View implements TextOutput {
    private static final int ABSOLUTE = 2;
    public static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    public static final float DEFAULT_TEXT_SIZE_FRACTION = 0.0533f;
    private static final int FRACTIONAL = 0;
    private static final int FRACTIONAL_IGNORE_PADDING = 1;
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private final List<SubtitlePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public SubtitleView(Context context) {
        this(context, null);
    }

    @TargetApi(19)
    private float getUserCaptionFontScaleV19() {
        return ((CaptioningManager) getContext().getSystemService("captioning")).getFontScale();
    }

    @TargetApi(19)
    private CaptionStyleCompat getUserCaptionStyleV19() {
        return CaptionStyleCompat.createFromCaptionStyle(((CaptioningManager) getContext().getSystemService("captioning")).getUserStyle());
    }

    private void setTextSize(int i9, float f9) {
        if (this.textSizeType == i9 && this.textSize == f9) {
            return;
        }
        this.textSizeType = i9;
        this.textSize = f9;
        invalidate();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
        float f9;
        List<Cue> list = this.cues;
        int i9 = 0;
        int size = list == null ? 0 : list.size();
        int top = getTop();
        int bottom = getBottom();
        int left = getLeft() + getPaddingLeft();
        int paddingTop = getPaddingTop() + top;
        int right = getRight() + getPaddingRight();
        int paddingBottom = bottom - getPaddingBottom();
        if (paddingBottom <= paddingTop || right <= left) {
            return;
        }
        int i10 = this.textSizeType;
        if (i10 == 2) {
            f9 = this.textSize;
        } else {
            f9 = (i10 == 0 ? paddingBottom - paddingTop : bottom - top) * this.textSize;
        }
        if (f9 <= BitmapDescriptorFactory.HUE_RED) {
            return;
        }
        while (i9 < size) {
            int i11 = paddingBottom;
            int i12 = right;
            this.painters.get(i9).draw(this.cues.get(i9), this.applyEmbeddedStyles, this.applyEmbeddedFontSizes, this.style, f9, this.bottomPaddingFraction, canvas, left, paddingTop, i12, i11);
            i9++;
            paddingBottom = i11;
            right = i12;
        }
    }

    @Override // com.google.android.exoplayer2.text.TextOutput
    public void onCues(List<Cue> list) {
        setCues(list);
    }

    public void setApplyEmbeddedFontSizes(boolean z8) {
        if (this.applyEmbeddedFontSizes == z8) {
            return;
        }
        this.applyEmbeddedFontSizes = z8;
        invalidate();
    }

    public void setApplyEmbeddedStyles(boolean z8) {
        if (this.applyEmbeddedStyles == z8 && this.applyEmbeddedFontSizes == z8) {
            return;
        }
        this.applyEmbeddedStyles = z8;
        this.applyEmbeddedFontSizes = z8;
        invalidate();
    }

    public void setBottomPaddingFraction(float f9) {
        if (this.bottomPaddingFraction == f9) {
            return;
        }
        this.bottomPaddingFraction = f9;
        invalidate();
    }

    public void setCues(List<Cue> list) {
        if (this.cues == list) {
            return;
        }
        this.cues = list;
        int size = list == null ? 0 : list.size();
        while (this.painters.size() < size) {
            this.painters.add(new SubtitlePainter(getContext()));
        }
        invalidate();
    }

    public void setFixedTextSize(int i9, float f9) {
        Context context = getContext();
        setTextSize(2, TypedValue.applyDimension(i9, f9, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    public void setFractionalTextSize(float f9) {
        setFractionalTextSize(f9, false);
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style == captionStyleCompat) {
            return;
        }
        this.style = captionStyleCompat;
        invalidate();
    }

    public void setUserDefaultStyle() {
        setStyle((Util.SDK_INT < 19 || isInEditMode()) ? CaptionStyleCompat.DEFAULT : getUserCaptionStyleV19());
    }

    public void setUserDefaultTextSize() {
        setFractionalTextSize(((Util.SDK_INT < 19 || isInEditMode()) ? 1.0f : getUserCaptionFontScaleV19()) * 0.0533f);
    }

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.applyEmbeddedStyles = true;
        this.applyEmbeddedFontSizes = true;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    public void setFractionalTextSize(float f9, boolean z8) {
        setTextSize(z8 ? 1 : 0, f9);
    }
}
