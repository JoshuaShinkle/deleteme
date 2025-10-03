package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParserException;
import p011b0.C0570a;
import p020c.C0694a;
import p206u.C6348b;
import p215v.C6427a;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class ChipDrawable extends Drawable implements Drawable.Callback {
    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = {R.attr.state_enabled};
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private int alpha;
    private boolean checkable;
    private Drawable checkedIcon;
    private boolean checkedIconVisible;
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    private Drawable chipIcon;
    private float chipIconSize;
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint;
    private float chipStartPadding;
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    private Drawable closeIcon;
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    private ColorFilter colorFilter;
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentCompatRippleColor;
    private int currentTextColor;
    private int currentTint;
    private final Paint debugPaint;
    private WeakReference<Delegate> delegate;
    private final C6348b.a fontCallback = new C6348b.a() { // from class: com.google.android.material.chip.ChipDrawable.1
        @Override // p206u.C6348b.a
        public void onFontRetrievalFailed(int i9) {
        }

        @Override // p206u.C6348b.a
        public void onFontRetrieved(Typeface typeface) {
            ChipDrawable.this.textWidthDirty = true;
            ChipDrawable.this.onSizeChange();
            ChipDrawable.this.invalidateSelf();
        }
    };
    private final Paint.FontMetrics fontMetrics;
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private int maxWidth;
    private final PointF pointF;
    private CharSequence rawText;
    private final RectF rectF;
    private ColorStateList rippleColor;
    private boolean shouldDrawText;
    private MotionSpec showMotionSpec;
    private TextAppearance textAppearance;
    private float textEndPadding;
    private final TextPaint textPaint;
    private float textStartPadding;
    private float textWidth;
    private boolean textWidthDirty;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private PorterDuff.Mode tintMode;
    private TextUtils.TruncateAt truncateAt;
    private CharSequence unicodeWrappedText;
    private boolean useCompatRipple;

    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    private ChipDrawable(Context context) {
        TextPaint textPaint = new TextPaint(1);
        this.textPaint = textPaint;
        this.chipPaint = new Paint(1);
        this.fontMetrics = new Paint.FontMetrics();
        this.rectF = new RectF();
        this.pointF = new PointF();
        this.alpha = 255;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.delegate = new WeakReference<>(null);
        this.textWidthDirty = true;
        this.context = context;
        this.rawText = "";
        textPaint.density = context.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        int[] iArr = DEFAULT_STATE;
        setState(iArr);
        setCloseIconState(iArr);
        this.shouldDrawText = true;
    }

    private void applyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(this);
            C6494a.m24844g(drawable, C6494a.m24839b(this));
            drawable.setLevel(getLevel());
            drawable.setVisible(isVisible(), false);
            if (drawable == this.closeIcon) {
                if (drawable.isStateful()) {
                    drawable.setState(getCloseIconState());
                }
                C6494a.m24846i(drawable, this.closeIconTint);
            } else if (drawable.isStateful()) {
                drawable.setState(getState());
            }
        }
    }

    private void calculateChipIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsChipIcon() || showsCheckedIcon()) {
            float f9 = this.chipStartPadding + this.iconStartPadding;
            if (C6494a.m24839b(this) == 0) {
                float f10 = rect.left + f9;
                rectF.left = f10;
                rectF.right = f10 + this.chipIconSize;
            } else {
                float f11 = rect.right - f9;
                rectF.right = f11;
                rectF.left = f11 - this.chipIconSize;
            }
            float fExactCenterY = rect.exactCenterY();
            float f12 = this.chipIconSize;
            float f13 = fExactCenterY - (f12 / 2.0f);
            rectF.top = f13;
            rectF.bottom = f13 + f12;
        }
    }

    private void calculateChipTouchBounds(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (showsCloseIcon()) {
            float f9 = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (C6494a.m24839b(this) == 0) {
                rectF.right = rect.right - f9;
            } else {
                rectF.left = rect.left + f9;
            }
        }
    }

    private void calculateCloseIconBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f9 = this.chipEndPadding + this.closeIconEndPadding;
            if (C6494a.m24839b(this) == 0) {
                float f10 = rect.right - f9;
                rectF.right = f10;
                rectF.left = f10 - this.closeIconSize;
            } else {
                float f11 = rect.left + f9;
                rectF.left = f11;
                rectF.right = f11 + this.closeIconSize;
            }
            float fExactCenterY = rect.exactCenterY();
            float f12 = this.closeIconSize;
            float f13 = fExactCenterY - (f12 / 2.0f);
            rectF.top = f13;
            rectF.bottom = f13 + f12;
        }
    }

    private void calculateCloseIconTouchBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (showsCloseIcon()) {
            float f9 = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if (C6494a.m24839b(this) == 0) {
                float f10 = rect.right;
                rectF.right = f10;
                rectF.left = f10 - f9;
            } else {
                int i9 = rect.left;
                rectF.left = i9;
                rectF.right = i9 + f9;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private float calculateCloseIconWidth() {
        return showsCloseIcon() ? this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding : BitmapDescriptorFactory.HUE_RED;
    }

    private void calculateTextBounds(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.unicodeWrappedText != null) {
            float fCalculateChipIconWidth = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            float fCalculateCloseIconWidth = this.chipEndPadding + calculateCloseIconWidth() + this.textEndPadding;
            if (C6494a.m24839b(this) == 0) {
                rectF.left = rect.left + fCalculateChipIconWidth;
                rectF.right = rect.right - fCalculateCloseIconWidth;
            } else {
                rectF.left = rect.left + fCalculateCloseIconWidth;
                rectF.right = rect.right - fCalculateChipIconWidth;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private float calculateTextCenterFromBaseline() {
        this.textPaint.getFontMetrics(this.fontMetrics);
        Paint.FontMetrics fontMetrics = this.fontMetrics;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private float calculateTextWidth(CharSequence charSequence) {
        return charSequence == null ? BitmapDescriptorFactory.HUE_RED : this.textPaint.measureText(charSequence, 0, charSequence.length());
    }

    private boolean canShowCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.checkable;
    }

    public static ChipDrawable createFromAttributes(Context context, AttributeSet attributeSet, int i9, int i10) {
        ChipDrawable chipDrawable = new ChipDrawable(context);
        chipDrawable.loadFromAttributes(attributeSet, i9, i10);
        return chipDrawable;
    }

    public static ChipDrawable createFromResource(Context context, int i9) throws XmlPullParserException, Resources.NotFoundException, IOException {
        int next;
        try {
            XmlResourceParser xml = context.getResources().getXml(i9);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if (!TextUtils.equals(xml.getName(), "chip")) {
                throw new XmlPullParserException("Must have a <chip> start tag");
            }
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            int styleAttribute = attributeSetAsAttributeSet.getStyleAttribute();
            if (styleAttribute == 0) {
                styleAttribute = C3476R.style.Widget_MaterialComponents_Chip_Entry;
            }
            return createFromAttributes(context, attributeSetAsAttributeSet, C3476R.attr.chipStandaloneStyle, styleAttribute);
        } catch (IOException | XmlPullParserException e9) {
            Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load chip resource ID #0x" + Integer.toHexString(i9));
            notFoundException.initCause(e9);
            throw notFoundException;
        }
    }

    private void drawCheckedIcon(Canvas canvas, Rect rect) {
        if (showsCheckedIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            RectF rectF = this.rectF;
            float f9 = rectF.left;
            float f10 = rectF.top;
            canvas.translate(f9, f10);
            this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.checkedIcon.draw(canvas);
            canvas.translate(-f9, -f10);
        }
    }

    private void drawChipBackground(Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentChipBackgroundColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.chipPaint.setColorFilter(getTintColorFilter());
        this.rectF.set(rect);
        RectF rectF = this.rectF;
        float f9 = this.chipCornerRadius;
        canvas.drawRoundRect(rectF, f9, f9, this.chipPaint);
    }

    private void drawChipIcon(Canvas canvas, Rect rect) {
        if (showsChipIcon()) {
            calculateChipIconBounds(rect, this.rectF);
            RectF rectF = this.rectF;
            float f9 = rectF.left;
            float f10 = rectF.top;
            canvas.translate(f9, f10);
            this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.chipIcon.draw(canvas);
            canvas.translate(-f9, -f10);
        }
    }

    private void drawChipStroke(Canvas canvas, Rect rect) {
        if (this.chipStrokeWidth > BitmapDescriptorFactory.HUE_RED) {
            this.chipPaint.setColor(this.currentChipStrokeColor);
            this.chipPaint.setStyle(Paint.Style.STROKE);
            this.chipPaint.setColorFilter(getTintColorFilter());
            RectF rectF = this.rectF;
            float f9 = rect.left;
            float f10 = this.chipStrokeWidth;
            rectF.set(f9 + (f10 / 2.0f), rect.top + (f10 / 2.0f), rect.right - (f10 / 2.0f), rect.bottom - (f10 / 2.0f));
            float f11 = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
            canvas.drawRoundRect(this.rectF, f11, f11, this.chipPaint);
        }
    }

    private void drawCloseIcon(Canvas canvas, Rect rect) {
        if (showsCloseIcon()) {
            calculateCloseIconBounds(rect, this.rectF);
            RectF rectF = this.rectF;
            float f9 = rectF.left;
            float f10 = rectF.top;
            canvas.translate(f9, f10);
            this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.closeIcon.draw(canvas);
            canvas.translate(-f9, -f10);
        }
    }

    private void drawCompatRipple(Canvas canvas, Rect rect) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(rect);
        RectF rectF = this.rectF;
        float f9 = this.chipCornerRadius;
        canvas.drawRoundRect(rectF, f9, f9, this.chipPaint);
    }

    private void drawDebug(Canvas canvas, Rect rect) {
        Paint paint = this.debugPaint;
        if (paint != null) {
            paint.setColor(C6427a.m24590d(-16777216, 127));
            canvas.drawRect(rect, this.debugPaint);
            if (showsChipIcon() || showsCheckedIcon()) {
                calculateChipIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            if (this.unicodeWrappedText != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.debugPaint);
            }
            if (showsCloseIcon()) {
                calculateCloseIconBounds(rect, this.rectF);
                canvas.drawRect(this.rectF, this.debugPaint);
            }
            this.debugPaint.setColor(C6427a.m24590d(-65536, 127));
            calculateChipTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
            this.debugPaint.setColor(C6427a.m24590d(-16711936, 127));
            calculateCloseIconTouchBounds(rect, this.rectF);
            canvas.drawRect(this.rectF, this.debugPaint);
        }
    }

    private void drawText(Canvas canvas, Rect rect) {
        if (this.unicodeWrappedText != null) {
            Paint.Align alignCalculateTextOriginAndAlignment = calculateTextOriginAndAlignment(rect, this.pointF);
            calculateTextBounds(rect, this.rectF);
            if (this.textAppearance != null) {
                this.textPaint.drawableState = getState();
                this.textAppearance.updateDrawState(this.context, this.textPaint, this.fontCallback);
            }
            this.textPaint.setTextAlign(alignCalculateTextOriginAndAlignment);
            int iSave = 0;
            boolean z8 = Math.round(getTextWidth()) > Math.round(this.rectF.width());
            if (z8) {
                iSave = canvas.save();
                canvas.clipRect(this.rectF);
            }
            CharSequence charSequenceEllipsize = this.unicodeWrappedText;
            if (z8 && this.truncateAt != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.textPaint, this.rectF.width(), this.truncateAt);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.pointF;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.textPaint);
            if (z8) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    private float getTextWidth() {
        if (!this.textWidthDirty) {
            return this.textWidth;
        }
        float fCalculateTextWidth = calculateTextWidth(this.unicodeWrappedText);
        this.textWidth = fCalculateTextWidth;
        this.textWidthDirty = false;
        return fCalculateTextWidth;
    }

    private ColorFilter getTintColorFilter() {
        ColorFilter colorFilter = this.colorFilter;
        return colorFilter != null ? colorFilter : this.tintFilter;
    }

    private static boolean hasState(int[] iArr, int i9) {
        if (iArr == null) {
            return false;
        }
        for (int i10 : iArr) {
            if (i10 == i9) {
                return true;
            }
        }
        return false;
    }

    private void loadFromAttributes(AttributeSet attributeSet, int i9, int i10) {
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(this.context, attributeSet, C3476R.styleable.Chip, i9, i10, new int[0]);
        setChipBackgroundColor(MaterialResources.getColorStateList(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_chipBackgroundColor));
        setChipMinHeight(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipMinHeight, BitmapDescriptorFactory.HUE_RED));
        setChipCornerRadius(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipCornerRadius, BitmapDescriptorFactory.HUE_RED));
        setChipStrokeColor(MaterialResources.getColorStateList(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_chipStrokeColor));
        setChipStrokeWidth(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipStrokeWidth, BitmapDescriptorFactory.HUE_RED));
        setRippleColor(MaterialResources.getColorStateList(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_rippleColor));
        setText(typedArrayObtainStyledAttributes.getText(C3476R.styleable.Chip_android_text));
        setTextAppearance(MaterialResources.getTextAppearance(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_android_textAppearance));
        int i11 = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.Chip_android_ellipsize, 0);
        if (i11 == 1) {
            setEllipsize(TextUtils.TruncateAt.START);
        } else if (i11 == 2) {
            setEllipsize(TextUtils.TruncateAt.MIDDLE);
        } else if (i11 == 3) {
            setEllipsize(TextUtils.TruncateAt.END);
        }
        setChipIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "chipIconVisible") == null) {
            setChipIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_chipIconEnabled, false));
        }
        setChipIcon(MaterialResources.getDrawable(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_chipIcon));
        setChipIconTint(MaterialResources.getColorStateList(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_chipIconTint));
        setChipIconSize(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipIconSize, BitmapDescriptorFactory.HUE_RED));
        setCloseIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "closeIconVisible") == null) {
            setCloseIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_closeIconEnabled, false));
        }
        setCloseIcon(MaterialResources.getDrawable(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_closeIcon));
        setCloseIconTint(MaterialResources.getColorStateList(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_closeIconTint));
        setCloseIconSize(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_closeIconSize, BitmapDescriptorFactory.HUE_RED));
        setCheckable(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_android_checkable, false));
        setCheckedIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconEnabled") != null && attributeSet.getAttributeValue(NAMESPACE_APP, "checkedIconVisible") == null) {
            setCheckedIconVisible(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.Chip_checkedIconEnabled, false));
        }
        setCheckedIcon(MaterialResources.getDrawable(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_checkedIcon));
        setShowMotionSpec(MotionSpec.createFromAttribute(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_showMotionSpec));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.context, typedArrayObtainStyledAttributes, C3476R.styleable.Chip_hideMotionSpec));
        setChipStartPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipStartPadding, BitmapDescriptorFactory.HUE_RED));
        setIconStartPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_iconStartPadding, BitmapDescriptorFactory.HUE_RED));
        setIconEndPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_iconEndPadding, BitmapDescriptorFactory.HUE_RED));
        setTextStartPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_textStartPadding, BitmapDescriptorFactory.HUE_RED));
        setTextEndPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_textEndPadding, BitmapDescriptorFactory.HUE_RED));
        setCloseIconStartPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_closeIconStartPadding, BitmapDescriptorFactory.HUE_RED));
        setCloseIconEndPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_closeIconEndPadding, BitmapDescriptorFactory.HUE_RED));
        setChipEndPadding(typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.Chip_chipEndPadding, BitmapDescriptorFactory.HUE_RED));
        setMaxWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(C3476R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        typedArrayObtainStyledAttributes.recycle();
    }

    private boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    private boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    private boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    private void unapplyChildDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void updateCompatRippleColor() {
        this.compatRippleColor = this.useCompatRipple ? RippleUtils.convertToRippleDrawableColor(this.rippleColor) : null;
    }

    public float calculateChipIconWidth() {
        return (showsChipIcon() || showsCheckedIcon()) ? this.iconStartPadding + this.chipIconSize + this.iconEndPadding : BitmapDescriptorFactory.HUE_RED;
    }

    public Paint.Align calculateTextOriginAndAlignment(Rect rect, PointF pointF) {
        pointF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        Paint.Align align = Paint.Align.LEFT;
        if (this.unicodeWrappedText != null) {
            float fCalculateChipIconWidth = this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding;
            if (C6494a.m24839b(this) == 0) {
                pointF.x = rect.left + fCalculateChipIconWidth;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - fCalculateChipIconWidth;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - calculateTextCenterFromBaseline();
        }
        return align;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i9 = this.alpha;
        int iSaveLayerAlpha = i9 < 255 ? CanvasCompat.saveLayerAlpha(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i9) : 0;
        drawChipBackground(canvas, bounds);
        drawChipStroke(canvas, bounds);
        drawCompatRipple(canvas, bounds);
        drawChipIcon(canvas, bounds);
        drawCheckedIcon(canvas, bounds);
        if (this.shouldDrawText) {
            drawText(canvas, bounds);
        }
        drawCloseIcon(canvas, bounds);
        drawDebug(canvas, bounds);
        if (this.alpha < 255) {
            canvas.restoreToCount(iSaveLayerAlpha);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    public float getChipCornerRadius() {
        return this.chipCornerRadius;
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public Drawable getChipIcon() {
        Drawable drawable = this.chipIcon;
        if (drawable != null) {
            return C6494a.m24848k(drawable);
        }
        return null;
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void getChipTouchBounds(RectF rectF) {
        calculateChipTouchBounds(getBounds(), rectF);
    }

    public Drawable getCloseIcon() {
        Drawable drawable = this.closeIcon;
        if (drawable != null) {
            return C6494a.m24848k(drawable);
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void getCloseIconTouchBounds(RectF rectF) {
        calculateCloseIconTouchBounds(getBounds(), rectF);
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.chipStartPadding + calculateChipIconWidth() + this.textStartPadding + getTextWidth() + this.textEndPadding + calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        } else {
            outline.setRoundRect(bounds, this.chipCornerRadius);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public CharSequence getText() {
        return this.rawText;
    }

    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconStateful() {
        return isStateful(this.closeIcon);
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return isStateful(this.chipBackgroundColor) || isStateful(this.chipStrokeColor) || (this.useCompatRipple && isStateful(this.compatRippleColor)) || isStateful(this.textAppearance) || canShowCheckedIcon() || isStateful(this.chipIcon) || isStateful(this.checkedIcon) || isStateful(this.tint);
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(23)
    public boolean onLayoutDirectionChanged(int i9) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i9);
        if (showsChipIcon()) {
            zOnLayoutDirectionChanged |= this.chipIcon.setLayoutDirection(i9);
        }
        if (showsCheckedIcon()) {
            zOnLayoutDirectionChanged |= this.checkedIcon.setLayoutDirection(i9);
        }
        if (showsCloseIcon()) {
            zOnLayoutDirectionChanged |= this.closeIcon.setLayoutDirection(i9);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i9) {
        boolean zOnLevelChange = super.onLevelChange(i9);
        if (showsChipIcon()) {
            zOnLevelChange |= this.chipIcon.setLevel(i9);
        }
        if (showsCheckedIcon()) {
            zOnLevelChange |= this.checkedIcon.setLevel(i9);
        }
        if (showsCloseIcon()) {
            zOnLevelChange |= this.closeIcon.setLevel(i9);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    public void onSizeChange() {
        Delegate delegate = this.delegate.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        return onStateChange(iArr, getCloseIconState());
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j9) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j9);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        if (this.alpha != i9) {
            this.alpha = i9;
            invalidateSelf();
        }
    }

    public void setCheckable(boolean z8) {
        if (this.checkable != z8) {
            this.checkable = z8;
            float fCalculateChipIconWidth = calculateChipIconWidth();
            if (!z8 && this.currentChecked) {
                this.currentChecked = false;
            }
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setCheckableResource(int i9) {
        setCheckable(this.context.getResources().getBoolean(i9));
    }

    public void setCheckedIcon(Drawable drawable) {
        if (this.checkedIcon != drawable) {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.checkedIcon = drawable;
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(this.checkedIcon);
            applyChildDrawable(this.checkedIcon);
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z8) {
        setCheckedIconVisible(z8);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i9) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i9));
    }

    public void setCheckedIconResource(int i9) {
        setCheckedIcon(C0694a.m3458b(this.context, i9));
    }

    public void setCheckedIconVisible(int i9) {
        setCheckedIconVisible(this.context.getResources().getBoolean(i9));
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        if (this.chipBackgroundColor != colorStateList) {
            this.chipBackgroundColor = colorStateList;
            onStateChange(getState());
        }
    }

    public void setChipBackgroundColorResource(int i9) {
        setChipBackgroundColor(C0694a.m3457a(this.context, i9));
    }

    public void setChipCornerRadius(float f9) {
        if (this.chipCornerRadius != f9) {
            this.chipCornerRadius = f9;
            invalidateSelf();
        }
    }

    public void setChipCornerRadiusResource(int i9) {
        setChipCornerRadius(this.context.getResources().getDimension(i9));
    }

    public void setChipEndPadding(float f9) {
        if (this.chipEndPadding != f9) {
            this.chipEndPadding = f9;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipEndPaddingResource(int i9) {
        setChipEndPadding(this.context.getResources().getDimension(i9));
    }

    public void setChipIcon(Drawable drawable) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon != drawable) {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.chipIcon = drawable != null ? C6494a.m24849l(drawable).mutate() : null;
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            unapplyChildDrawable(chipIcon);
            if (showsChipIcon()) {
                applyChildDrawable(this.chipIcon);
            }
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z8) {
        setChipIconVisible(z8);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i9) {
        setChipIconVisible(i9);
    }

    public void setChipIconResource(int i9) {
        setChipIcon(C0694a.m3458b(this.context, i9));
    }

    public void setChipIconSize(float f9) {
        if (this.chipIconSize != f9) {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.chipIconSize = f9;
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setChipIconSizeResource(int i9) {
        setChipIconSize(this.context.getResources().getDimension(i9));
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        if (this.chipIconTint != colorStateList) {
            this.chipIconTint = colorStateList;
            if (showsChipIcon()) {
                C6494a.m24846i(this.chipIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipIconTintResource(int i9) {
        setChipIconTint(C0694a.m3457a(this.context, i9));
    }

    public void setChipIconVisible(int i9) {
        setChipIconVisible(this.context.getResources().getBoolean(i9));
    }

    public void setChipMinHeight(float f9) {
        if (this.chipMinHeight != f9) {
            this.chipMinHeight = f9;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipMinHeightResource(int i9) {
        setChipMinHeight(this.context.getResources().getDimension(i9));
    }

    public void setChipStartPadding(float f9) {
        if (this.chipStartPadding != f9) {
            this.chipStartPadding = f9;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setChipStartPaddingResource(int i9) {
        setChipStartPadding(this.context.getResources().getDimension(i9));
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        if (this.chipStrokeColor != colorStateList) {
            this.chipStrokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    public void setChipStrokeColorResource(int i9) {
        setChipStrokeColor(C0694a.m3457a(this.context, i9));
    }

    public void setChipStrokeWidth(float f9) {
        if (this.chipStrokeWidth != f9) {
            this.chipStrokeWidth = f9;
            this.chipPaint.setStrokeWidth(f9);
            invalidateSelf();
        }
    }

    public void setChipStrokeWidthResource(int i9) {
        setChipStrokeWidth(this.context.getResources().getDimension(i9));
    }

    public void setCloseIcon(Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float fCalculateCloseIconWidth = calculateCloseIconWidth();
            this.closeIcon = drawable != null ? C6494a.m24849l(drawable).mutate() : null;
            float fCalculateCloseIconWidth2 = calculateCloseIconWidth();
            unapplyChildDrawable(closeIcon);
            if (showsCloseIcon()) {
                applyChildDrawable(this.closeIcon);
            }
            invalidateSelf();
            if (fCalculateCloseIconWidth != fCalculateCloseIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        if (this.closeIconContentDescription != charSequence) {
            this.closeIconContentDescription = C0570a.m3218c().m3223h(charSequence);
            invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z8) {
        setCloseIconVisible(z8);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i9) {
        setCloseIconVisible(i9);
    }

    public void setCloseIconEndPadding(float f9) {
        if (this.closeIconEndPadding != f9) {
            this.closeIconEndPadding = f9;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconEndPaddingResource(int i9) {
        setCloseIconEndPadding(this.context.getResources().getDimension(i9));
    }

    public void setCloseIconResource(int i9) {
        setCloseIcon(C0694a.m3458b(this.context, i9));
    }

    public void setCloseIconSize(float f9) {
        if (this.closeIconSize != f9) {
            this.closeIconSize = f9;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconSizeResource(int i9) {
        setCloseIconSize(this.context.getResources().getDimension(i9));
    }

    public void setCloseIconStartPadding(float f9) {
        if (this.closeIconStartPadding != f9) {
            this.closeIconStartPadding = f9;
            invalidateSelf();
            if (showsCloseIcon()) {
                onSizeChange();
            }
        }
    }

    public void setCloseIconStartPaddingResource(int i9) {
        setCloseIconStartPadding(this.context.getResources().getDimension(i9));
    }

    public boolean setCloseIconState(int[] iArr) {
        if (Arrays.equals(this.closeIconStateSet, iArr)) {
            return false;
        }
        this.closeIconStateSet = iArr;
        if (showsCloseIcon()) {
            return onStateChange(getState(), iArr);
        }
        return false;
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        if (this.closeIconTint != colorStateList) {
            this.closeIconTint = colorStateList;
            if (showsCloseIcon()) {
                C6494a.m24846i(this.closeIcon, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCloseIconTintResource(int i9) {
        setCloseIconTint(C0694a.m3457a(this.context, i9));
    }

    public void setCloseIconVisible(int i9) {
        setCloseIconVisible(this.context.getResources().getBoolean(i9));
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.colorFilter != colorFilter) {
            this.colorFilter = colorFilter;
            invalidateSelf();
        }
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = new WeakReference<>(delegate);
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        this.truncateAt = truncateAt;
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        this.hideMotionSpec = motionSpec;
    }

    public void setHideMotionSpecResource(int i9) {
        setHideMotionSpec(MotionSpec.createFromResource(this.context, i9));
    }

    public void setIconEndPadding(float f9) {
        if (this.iconEndPadding != f9) {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.iconEndPadding = f9;
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setIconEndPaddingResource(int i9) {
        setIconEndPadding(this.context.getResources().getDimension(i9));
    }

    public void setIconStartPadding(float f9) {
        if (this.iconStartPadding != f9) {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.iconStartPadding = f9;
            float fCalculateChipIconWidth2 = calculateChipIconWidth();
            invalidateSelf();
            if (fCalculateChipIconWidth != fCalculateChipIconWidth2) {
                onSizeChange();
            }
        }
    }

    public void setIconStartPaddingResource(int i9) {
        setIconStartPadding(this.context.getResources().getDimension(i9));
    }

    public void setMaxWidth(int i9) {
        this.maxWidth = i9;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.rippleColor != colorStateList) {
            this.rippleColor = colorStateList;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    public void setRippleColorResource(int i9) {
        setRippleColor(C0694a.m3457a(this.context, i9));
    }

    public void setShouldDrawText(boolean z8) {
        this.shouldDrawText = z8;
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        this.showMotionSpec = motionSpec;
    }

    public void setShowMotionSpecResource(int i9) {
        setShowMotionSpec(MotionSpec.createFromResource(this.context, i9));
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (this.rawText != charSequence) {
            this.rawText = charSequence;
            this.unicodeWrappedText = C0570a.m3218c().m3223h(charSequence);
            this.textWidthDirty = true;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextAppearance(TextAppearance textAppearance) {
        if (this.textAppearance != textAppearance) {
            this.textAppearance = textAppearance;
            if (textAppearance != null) {
                textAppearance.updateMeasureState(this.context, this.textPaint, this.fontCallback);
                this.textWidthDirty = true;
            }
            onStateChange(getState());
            onSizeChange();
        }
    }

    public void setTextAppearanceResource(int i9) {
        setTextAppearance(new TextAppearance(this.context, i9));
    }

    public void setTextEndPadding(float f9) {
        if (this.textEndPadding != f9) {
            this.textEndPadding = f9;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextEndPaddingResource(int i9) {
        setTextEndPadding(this.context.getResources().getDimension(i9));
    }

    public void setTextResource(int i9) {
        setText(this.context.getResources().getString(i9));
    }

    public void setTextStartPadding(float f9) {
        if (this.textStartPadding != f9) {
            this.textStartPadding = f9;
            invalidateSelf();
            onSizeChange();
        }
    }

    public void setTextStartPaddingResource(int i9) {
        setTextStartPadding(this.context.getResources().getDimension(i9));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.tint != colorStateList) {
            this.tint = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, mode);
            invalidateSelf();
        }
    }

    public void setUseCompatRipple(boolean z8) {
        if (this.useCompatRipple != z8) {
            this.useCompatRipple = z8;
            updateCompatRippleColor();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z8, boolean z9) {
        boolean visible = super.setVisible(z8, z9);
        if (showsChipIcon()) {
            visible |= this.chipIcon.setVisible(z8, z9);
        }
        if (showsCheckedIcon()) {
            visible |= this.checkedIcon.setVisible(z8, z9);
        }
        if (showsCloseIcon()) {
            visible |= this.closeIcon.setVisible(z8, z9);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    private boolean onStateChange(int[] iArr, int[] iArr2) {
        boolean z8;
        ColorStateList colorStateList;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.chipBackgroundColor;
        int colorForState = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.currentChipBackgroundColor) : 0;
        boolean state = true;
        if (this.currentChipBackgroundColor != colorForState) {
            this.currentChipBackgroundColor = colorForState;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.chipStrokeColor;
        int colorForState2 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.currentChipStrokeColor) : 0;
        if (this.currentChipStrokeColor != colorForState2) {
            this.currentChipStrokeColor = colorForState2;
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.compatRippleColor;
        int colorForState3 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.currentCompatRippleColor) : 0;
        if (this.currentCompatRippleColor != colorForState3) {
            this.currentCompatRippleColor = colorForState3;
            if (this.useCompatRipple) {
                zOnStateChange = true;
            }
        }
        TextAppearance textAppearance = this.textAppearance;
        int colorForState4 = (textAppearance == null || (colorStateList = textAppearance.textColor) == null) ? 0 : colorStateList.getColorForState(iArr, this.currentTextColor);
        if (this.currentTextColor != colorForState4) {
            this.currentTextColor = colorForState4;
            zOnStateChange = true;
        }
        boolean z9 = hasState(getState(), R.attr.state_checked) && this.checkable;
        if (this.currentChecked == z9 || this.checkedIcon == null) {
            z8 = false;
        } else {
            float fCalculateChipIconWidth = calculateChipIconWidth();
            this.currentChecked = z9;
            if (fCalculateChipIconWidth != calculateChipIconWidth()) {
                zOnStateChange = true;
                z8 = true;
            } else {
                z8 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.tint;
        int colorForState5 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.currentTint) : 0;
        if (this.currentTint != colorForState5) {
            this.currentTint = colorForState5;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
        } else {
            state = zOnStateChange;
        }
        if (isStateful(this.chipIcon)) {
            state |= this.chipIcon.setState(iArr);
        }
        if (isStateful(this.checkedIcon)) {
            state |= this.checkedIcon.setState(iArr);
        }
        if (isStateful(this.closeIcon)) {
            state |= this.closeIcon.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z8) {
            onSizeChange();
        }
        return state;
    }

    public void setCheckedIconVisible(boolean z8) {
        if (this.checkedIconVisible != z8) {
            boolean zShowsCheckedIcon = showsCheckedIcon();
            this.checkedIconVisible = z8;
            boolean zShowsCheckedIcon2 = showsCheckedIcon();
            if (zShowsCheckedIcon != zShowsCheckedIcon2) {
                if (zShowsCheckedIcon2) {
                    applyChildDrawable(this.checkedIcon);
                } else {
                    unapplyChildDrawable(this.checkedIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setChipIconVisible(boolean z8) {
        if (this.chipIconVisible != z8) {
            boolean zShowsChipIcon = showsChipIcon();
            this.chipIconVisible = z8;
            boolean zShowsChipIcon2 = showsChipIcon();
            if (zShowsChipIcon != zShowsChipIcon2) {
                if (zShowsChipIcon2) {
                    applyChildDrawable(this.chipIcon);
                } else {
                    unapplyChildDrawable(this.chipIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    public void setCloseIconVisible(boolean z8) {
        if (this.closeIconVisible != z8) {
            boolean zShowsCloseIcon = showsCloseIcon();
            this.closeIconVisible = z8;
            boolean zShowsCloseIcon2 = showsCloseIcon();
            if (zShowsCloseIcon != zShowsCloseIcon2) {
                if (zShowsCloseIcon2) {
                    applyChildDrawable(this.closeIcon);
                } else {
                    unapplyChildDrawable(this.closeIcon);
                }
                invalidateSelf();
                onSizeChange();
            }
        }
    }

    private static boolean isStateful(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean isStateful(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean isStateful(TextAppearance textAppearance) {
        ColorStateList colorStateList;
        return (textAppearance == null || (colorStateList = textAppearance.textColor) == null || !colorStateList.isStateful()) ? false : true;
    }
}
