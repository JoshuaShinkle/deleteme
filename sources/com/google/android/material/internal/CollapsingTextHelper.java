package com.google.android.material.internal;

import android.R;
import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.widget.C0250q0;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.animation.AnimationUtils;
import p010b.C0569j;
import p011b0.C0584o;
import p042d0.C4621e;
import p042d0.C4647u;
import p242y.C6587a;

/* loaded from: classes2.dex */
public final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private boolean boundsChanged;
    private final Rect collapsedBounds;
    private float collapsedDrawX;
    private float collapsedDrawY;
    private int collapsedShadowColor;
    private float collapsedShadowDx;
    private float collapsedShadowDy;
    private float collapsedShadowRadius;
    private ColorStateList collapsedTextColor;
    private Typeface collapsedTypeface;
    private final RectF currentBounds;
    private float currentDrawX;
    private float currentDrawY;
    private float currentTextSize;
    private Typeface currentTypeface;
    private boolean drawTitle;
    private final Rect expandedBounds;
    private float expandedDrawX;
    private float expandedDrawY;
    private float expandedFraction;
    private int expandedShadowColor;
    private float expandedShadowDx;
    private float expandedShadowDy;
    private float expandedShadowRadius;
    private ColorStateList expandedTextColor;
    private Bitmap expandedTitleTexture;
    private Typeface expandedTypeface;
    private boolean isRtl;
    private TimeInterpolator positionInterpolator;
    private float scale;
    private int[] state;
    private CharSequence text;
    private final TextPaint textPaint;
    private TimeInterpolator textSizeInterpolator;
    private CharSequence textToDraw;
    private float textureAscent;
    private float textureDescent;
    private Paint texturePaint;
    private final TextPaint tmpPaint;
    private boolean useTexture;
    private final View view;
    private static final boolean USE_SCALING_TEXTURE = false;
    private static final Paint DEBUG_DRAW_PAINT = null;
    private int expandedTextGravity = 16;
    private int collapsedTextGravity = 16;
    private float expandedTextSize = 15.0f;
    private float collapsedTextSize = 15.0f;

    public CollapsingTextHelper(View view) {
        this.view = view;
        TextPaint textPaint = new TextPaint(TsExtractor.TS_STREAM_TYPE_AC3);
        this.textPaint = textPaint;
        this.tmpPaint = new TextPaint(textPaint);
        this.collapsedBounds = new Rect();
        this.expandedBounds = new Rect();
        this.currentBounds = new RectF();
    }

    private static int blendColors(int i9, int i10, float f9) {
        float f10 = 1.0f - f9;
        return Color.argb((int) ((Color.alpha(i9) * f10) + (Color.alpha(i10) * f9)), (int) ((Color.red(i9) * f10) + (Color.red(i10) * f9)), (int) ((Color.green(i9) * f10) + (Color.green(i10) * f9)), (int) ((Color.blue(i9) * f10) + (Color.blue(i10) * f9)));
    }

    private void calculateBaseOffsets() {
        float f9 = this.currentTextSize;
        calculateUsingTextSize(this.collapsedTextSize);
        CharSequence charSequence = this.textToDraw;
        float fMeasureText = BitmapDescriptorFactory.HUE_RED;
        float fMeasureText2 = charSequence != null ? this.textPaint.measureText(charSequence, 0, charSequence.length()) : 0.0f;
        int iM18420b = C4621e.m18420b(this.collapsedTextGravity, this.isRtl ? 1 : 0);
        int i9 = iM18420b & 112;
        if (i9 == 48) {
            this.collapsedDrawY = this.collapsedBounds.top - this.textPaint.ascent();
        } else if (i9 != 80) {
            this.collapsedDrawY = this.collapsedBounds.centerY() + (((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f) - this.textPaint.descent());
        } else {
            this.collapsedDrawY = this.collapsedBounds.bottom;
        }
        int i10 = iM18420b & 8388615;
        if (i10 == 1) {
            this.collapsedDrawX = this.collapsedBounds.centerX() - (fMeasureText2 / 2.0f);
        } else if (i10 != 5) {
            this.collapsedDrawX = this.collapsedBounds.left;
        } else {
            this.collapsedDrawX = this.collapsedBounds.right - fMeasureText2;
        }
        calculateUsingTextSize(this.expandedTextSize);
        CharSequence charSequence2 = this.textToDraw;
        if (charSequence2 != null) {
            fMeasureText = this.textPaint.measureText(charSequence2, 0, charSequence2.length());
        }
        int iM18420b2 = C4621e.m18420b(this.expandedTextGravity, this.isRtl ? 1 : 0);
        int i11 = iM18420b2 & 112;
        if (i11 == 48) {
            this.expandedDrawY = this.expandedBounds.top - this.textPaint.ascent();
        } else if (i11 != 80) {
            this.expandedDrawY = this.expandedBounds.centerY() + (((this.textPaint.descent() - this.textPaint.ascent()) / 2.0f) - this.textPaint.descent());
        } else {
            this.expandedDrawY = this.expandedBounds.bottom;
        }
        int i12 = iM18420b2 & 8388615;
        if (i12 == 1) {
            this.expandedDrawX = this.expandedBounds.centerX() - (fMeasureText / 2.0f);
        } else if (i12 != 5) {
            this.expandedDrawX = this.expandedBounds.left;
        } else {
            this.expandedDrawX = this.expandedBounds.right - fMeasureText;
        }
        clearTexture();
        setInterpolatedTextSize(f9);
    }

    private void calculateCurrentOffsets() {
        calculateOffsets(this.expandedFraction);
    }

    private boolean calculateIsRtl(CharSequence charSequence) {
        return (C4647u.m18567s(this.view) == 1 ? C0584o.f3092d : C0584o.f3091c).isRtl(charSequence, 0, charSequence.length());
    }

    private void calculateOffsets(float f9) {
        interpolateBounds(f9);
        this.currentDrawX = lerp(this.expandedDrawX, this.collapsedDrawX, f9, this.positionInterpolator);
        this.currentDrawY = lerp(this.expandedDrawY, this.collapsedDrawY, f9, this.positionInterpolator);
        setInterpolatedTextSize(lerp(this.expandedTextSize, this.collapsedTextSize, f9, this.textSizeInterpolator));
        if (this.collapsedTextColor != this.expandedTextColor) {
            this.textPaint.setColor(blendColors(getCurrentExpandedTextColor(), getCurrentCollapsedTextColor(), f9));
        } else {
            this.textPaint.setColor(getCurrentCollapsedTextColor());
        }
        this.textPaint.setShadowLayer(lerp(this.expandedShadowRadius, this.collapsedShadowRadius, f9, null), lerp(this.expandedShadowDx, this.collapsedShadowDx, f9, null), lerp(this.expandedShadowDy, this.collapsedShadowDy, f9, null), blendColors(this.expandedShadowColor, this.collapsedShadowColor, f9));
        C4647u.m18524T(this.view);
    }

    private void calculateUsingTextSize(float f9) {
        boolean z8;
        float f10;
        boolean z9;
        if (this.text == null) {
            return;
        }
        float fWidth = this.collapsedBounds.width();
        float fWidth2 = this.expandedBounds.width();
        if (isClose(f9, this.collapsedTextSize)) {
            f10 = this.collapsedTextSize;
            this.scale = 1.0f;
            Typeface typeface = this.currentTypeface;
            Typeface typeface2 = this.collapsedTypeface;
            if (typeface != typeface2) {
                this.currentTypeface = typeface2;
                z9 = true;
            } else {
                z9 = false;
            }
        } else {
            float f11 = this.expandedTextSize;
            Typeface typeface3 = this.currentTypeface;
            Typeface typeface4 = this.expandedTypeface;
            if (typeface3 != typeface4) {
                this.currentTypeface = typeface4;
                z8 = true;
            } else {
                z8 = false;
            }
            if (isClose(f9, f11)) {
                this.scale = 1.0f;
            } else {
                this.scale = f9 / this.expandedTextSize;
            }
            float f12 = this.collapsedTextSize / this.expandedTextSize;
            fWidth = fWidth2 * f12 > fWidth ? Math.min(fWidth / f12, fWidth2) : fWidth2;
            f10 = f11;
            z9 = z8;
        }
        if (fWidth > BitmapDescriptorFactory.HUE_RED) {
            z9 = this.currentTextSize != f10 || this.boundsChanged || z9;
            this.currentTextSize = f10;
            this.boundsChanged = false;
        }
        if (this.textToDraw == null || z9) {
            this.textPaint.setTextSize(this.currentTextSize);
            this.textPaint.setTypeface(this.currentTypeface);
            this.textPaint.setLinearText(this.scale != 1.0f);
            CharSequence charSequenceEllipsize = TextUtils.ellipsize(this.text, this.textPaint, fWidth, TextUtils.TruncateAt.END);
            if (TextUtils.equals(charSequenceEllipsize, this.textToDraw)) {
                return;
            }
            this.textToDraw = charSequenceEllipsize;
            this.isRtl = calculateIsRtl(charSequenceEllipsize);
        }
    }

    private void clearTexture() {
        Bitmap bitmap = this.expandedTitleTexture;
        if (bitmap != null) {
            bitmap.recycle();
            this.expandedTitleTexture = null;
        }
    }

    private void ensureExpandedTexture() {
        if (this.expandedTitleTexture != null || this.expandedBounds.isEmpty() || TextUtils.isEmpty(this.textToDraw)) {
            return;
        }
        calculateOffsets(BitmapDescriptorFactory.HUE_RED);
        this.textureAscent = this.textPaint.ascent();
        this.textureDescent = this.textPaint.descent();
        TextPaint textPaint = this.textPaint;
        CharSequence charSequence = this.textToDraw;
        int iRound = Math.round(textPaint.measureText(charSequence, 0, charSequence.length()));
        int iRound2 = Math.round(this.textureDescent - this.textureAscent);
        if (iRound <= 0 || iRound2 <= 0) {
            return;
        }
        this.expandedTitleTexture = Bitmap.createBitmap(iRound, iRound2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.expandedTitleTexture);
        CharSequence charSequence2 = this.textToDraw;
        canvas.drawText(charSequence2, 0, charSequence2.length(), BitmapDescriptorFactory.HUE_RED, iRound2 - this.textPaint.descent(), this.textPaint);
        if (this.texturePaint == null) {
            this.texturePaint = new Paint(3);
        }
    }

    private int getCurrentExpandedTextColor() {
        int[] iArr = this.state;
        return iArr != null ? this.expandedTextColor.getColorForState(iArr, 0) : this.expandedTextColor.getDefaultColor();
    }

    private void getTextPaintCollapsed(TextPaint textPaint) {
        textPaint.setTextSize(this.collapsedTextSize);
        textPaint.setTypeface(this.collapsedTypeface);
    }

    private void interpolateBounds(float f9) {
        this.currentBounds.left = lerp(this.expandedBounds.left, this.collapsedBounds.left, f9, this.positionInterpolator);
        this.currentBounds.top = lerp(this.expandedDrawY, this.collapsedDrawY, f9, this.positionInterpolator);
        this.currentBounds.right = lerp(this.expandedBounds.right, this.collapsedBounds.right, f9, this.positionInterpolator);
        this.currentBounds.bottom = lerp(this.expandedBounds.bottom, this.collapsedBounds.bottom, f9, this.positionInterpolator);
    }

    private static boolean isClose(float f9, float f10) {
        return Math.abs(f9 - f10) < 0.001f;
    }

    private static float lerp(float f9, float f10, float f11, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f11 = timeInterpolator.getInterpolation(f11);
        }
        return AnimationUtils.lerp(f9, f10, f11);
    }

    private Typeface readFontFamilyTypeface(int i9) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = this.view.getContext().obtainStyledAttributes(i9, new int[]{R.attr.fontFamily});
        try {
            String string = typedArrayObtainStyledAttributes.getString(0);
            if (string != null) {
                return Typeface.create(string, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
            return null;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private static boolean rectEquals(Rect rect, int i9, int i10, int i11, int i12) {
        return rect.left == i9 && rect.top == i10 && rect.right == i11 && rect.bottom == i12;
    }

    private void setInterpolatedTextSize(float f9) {
        calculateUsingTextSize(f9);
        boolean z8 = USE_SCALING_TEXTURE && this.scale != 1.0f;
        this.useTexture = z8;
        if (z8) {
            ensureExpandedTexture();
        }
        C4647u.m18524T(this.view);
    }

    public float calculateCollapsedTextWidth() {
        if (this.text == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        getTextPaintCollapsed(this.tmpPaint);
        TextPaint textPaint = this.tmpPaint;
        CharSequence charSequence = this.text;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void draw(Canvas canvas) {
        float fAscent;
        int iSave = canvas.save();
        if (this.textToDraw != null && this.drawTitle) {
            float f9 = this.currentDrawX;
            float f10 = this.currentDrawY;
            boolean z8 = this.useTexture && this.expandedTitleTexture != null;
            if (z8) {
                fAscent = this.textureAscent * this.scale;
            } else {
                fAscent = this.textPaint.ascent() * this.scale;
                this.textPaint.descent();
            }
            if (z8) {
                f10 += fAscent;
            }
            float f11 = f10;
            float f12 = this.scale;
            if (f12 != 1.0f) {
                canvas.scale(f12, f12, f9, f11);
            }
            if (z8) {
                canvas.drawBitmap(this.expandedTitleTexture, f9, f11, this.texturePaint);
            } else {
                CharSequence charSequence = this.textToDraw;
                canvas.drawText(charSequence, 0, charSequence.length(), f9, f11, this.textPaint);
            }
        }
        canvas.restoreToCount(iSave);
    }

    public void getCollapsedTextActualBounds(RectF rectF) {
        boolean zCalculateIsRtl = calculateIsRtl(this.text);
        Rect rect = this.collapsedBounds;
        float fCalculateCollapsedTextWidth = !zCalculateIsRtl ? rect.left : rect.right - calculateCollapsedTextWidth();
        rectF.left = fCalculateCollapsedTextWidth;
        Rect rect2 = this.collapsedBounds;
        rectF.top = rect2.top;
        rectF.right = !zCalculateIsRtl ? fCalculateCollapsedTextWidth + calculateCollapsedTextWidth() : rect2.right;
        rectF.bottom = this.collapsedBounds.top + getCollapsedTextHeight();
    }

    public ColorStateList getCollapsedTextColor() {
        return this.collapsedTextColor;
    }

    public int getCollapsedTextGravity() {
        return this.collapsedTextGravity;
    }

    public float getCollapsedTextHeight() {
        getTextPaintCollapsed(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    public float getCollapsedTextSize() {
        return this.collapsedTextSize;
    }

    public Typeface getCollapsedTypeface() {
        Typeface typeface = this.collapsedTypeface;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int getCurrentCollapsedTextColor() {
        int[] iArr = this.state;
        return iArr != null ? this.collapsedTextColor.getColorForState(iArr, 0) : this.collapsedTextColor.getDefaultColor();
    }

    public ColorStateList getExpandedTextColor() {
        return this.expandedTextColor;
    }

    public int getExpandedTextGravity() {
        return this.expandedTextGravity;
    }

    public float getExpandedTextSize() {
        return this.expandedTextSize;
    }

    public Typeface getExpandedTypeface() {
        Typeface typeface = this.expandedTypeface;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float getExpansionFraction() {
        return this.expandedFraction;
    }

    public CharSequence getText() {
        return this.text;
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.collapsedTextColor;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.expandedTextColor) != null && colorStateList.isStateful());
    }

    public void onBoundsChanged() {
        this.drawTitle = this.collapsedBounds.width() > 0 && this.collapsedBounds.height() > 0 && this.expandedBounds.width() > 0 && this.expandedBounds.height() > 0;
    }

    public void recalculate() {
        if (this.view.getHeight() <= 0 || this.view.getWidth() <= 0) {
            return;
        }
        calculateBaseOffsets();
        calculateCurrentOffsets();
    }

    public void setCollapsedBounds(int i9, int i10, int i11, int i12) {
        if (rectEquals(this.collapsedBounds, i9, i10, i11, i12)) {
            return;
        }
        this.collapsedBounds.set(i9, i10, i11, i12);
        this.boundsChanged = true;
        onBoundsChanged();
    }

    public void setCollapsedTextAppearance(int i9) {
        C0250q0 c0250q0M1002t = C0250q0.m1002t(this.view.getContext(), i9, C0569j.TextAppearance);
        int i10 = C0569j.TextAppearance_android_textColor;
        if (c0250q0M1002t.m1023s(i10)) {
            this.collapsedTextColor = c0250q0M1002t.m1007c(i10);
        }
        if (c0250q0M1002t.m1023s(C0569j.TextAppearance_android_textSize)) {
            this.collapsedTextSize = c0250q0M1002t.m1010f(r1, (int) this.collapsedTextSize);
        }
        this.collapsedShadowColor = c0250q0M1002t.m1015k(C0569j.TextAppearance_android_shadowColor, 0);
        this.collapsedShadowDx = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowDx, BitmapDescriptorFactory.HUE_RED);
        this.collapsedShadowDy = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowDy, BitmapDescriptorFactory.HUE_RED);
        this.collapsedShadowRadius = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowRadius, BitmapDescriptorFactory.HUE_RED);
        c0250q0M1002t.m1024w();
        this.collapsedTypeface = readFontFamilyTypeface(i9);
        recalculate();
    }

    public void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.collapsedTextColor != colorStateList) {
            this.collapsedTextColor = colorStateList;
            recalculate();
        }
    }

    public void setCollapsedTextGravity(int i9) {
        if (this.collapsedTextGravity != i9) {
            this.collapsedTextGravity = i9;
            recalculate();
        }
    }

    public void setCollapsedTextSize(float f9) {
        if (this.collapsedTextSize != f9) {
            this.collapsedTextSize = f9;
            recalculate();
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        if (this.collapsedTypeface != typeface) {
            this.collapsedTypeface = typeface;
            recalculate();
        }
    }

    public void setExpandedBounds(int i9, int i10, int i11, int i12) {
        if (rectEquals(this.expandedBounds, i9, i10, i11, i12)) {
            return;
        }
        this.expandedBounds.set(i9, i10, i11, i12);
        this.boundsChanged = true;
        onBoundsChanged();
    }

    public void setExpandedTextAppearance(int i9) {
        C0250q0 c0250q0M1002t = C0250q0.m1002t(this.view.getContext(), i9, C0569j.TextAppearance);
        int i10 = C0569j.TextAppearance_android_textColor;
        if (c0250q0M1002t.m1023s(i10)) {
            this.expandedTextColor = c0250q0M1002t.m1007c(i10);
        }
        if (c0250q0M1002t.m1023s(C0569j.TextAppearance_android_textSize)) {
            this.expandedTextSize = c0250q0M1002t.m1010f(r1, (int) this.expandedTextSize);
        }
        this.expandedShadowColor = c0250q0M1002t.m1015k(C0569j.TextAppearance_android_shadowColor, 0);
        this.expandedShadowDx = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowDx, BitmapDescriptorFactory.HUE_RED);
        this.expandedShadowDy = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowDy, BitmapDescriptorFactory.HUE_RED);
        this.expandedShadowRadius = c0250q0M1002t.m1013i(C0569j.TextAppearance_android_shadowRadius, BitmapDescriptorFactory.HUE_RED);
        c0250q0M1002t.m1024w();
        this.expandedTypeface = readFontFamilyTypeface(i9);
        recalculate();
    }

    public void setExpandedTextColor(ColorStateList colorStateList) {
        if (this.expandedTextColor != colorStateList) {
            this.expandedTextColor = colorStateList;
            recalculate();
        }
    }

    public void setExpandedTextGravity(int i9) {
        if (this.expandedTextGravity != i9) {
            this.expandedTextGravity = i9;
            recalculate();
        }
    }

    public void setExpandedTextSize(float f9) {
        if (this.expandedTextSize != f9) {
            this.expandedTextSize = f9;
            recalculate();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        if (this.expandedTypeface != typeface) {
            this.expandedTypeface = typeface;
            recalculate();
        }
    }

    public void setExpansionFraction(float f9) {
        float fM25199a = C6587a.m25199a(f9, BitmapDescriptorFactory.HUE_RED, 1.0f);
        if (fM25199a != this.expandedFraction) {
            this.expandedFraction = fM25199a;
            calculateCurrentOffsets();
        }
    }

    public void setPositionInterpolator(TimeInterpolator timeInterpolator) {
        this.positionInterpolator = timeInterpolator;
        recalculate();
    }

    public final boolean setState(int[] iArr) {
        this.state = iArr;
        if (!isStateful()) {
            return false;
        }
        recalculate();
        return true;
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.text)) {
            this.text = charSequence;
            this.textToDraw = null;
            clearTexture();
            recalculate();
        }
    }

    public void setTextSizeInterpolator(TimeInterpolator timeInterpolator) {
        this.textSizeInterpolator = timeInterpolator;
        recalculate();
    }

    public void setTypefaces(Typeface typeface) {
        this.expandedTypeface = typeface;
        this.collapsedTypeface = typeface;
        recalculate();
    }
}
