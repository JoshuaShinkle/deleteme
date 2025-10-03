package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import p206u.C6348b;

/* loaded from: classes2.dex */
public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    private Typeface font;
    public final String fontFamily;
    private final int fontFamilyResourceId;
    private boolean fontResolved = false;
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    public final ColorStateList textColor;
    public final ColorStateList textColorHint;
    public final ColorStateList textColorLink;
    public final float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context, int i9) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i9, C3476R.styleable.TextAppearance);
        this.textSize = typedArrayObtainStyledAttributes.getDimension(C3476R.styleable.TextAppearance_android_textSize, BitmapDescriptorFactory.HUE_RED);
        this.textColor = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TextAppearance_android_textColor);
        this.textColorHint = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TextAppearance_android_textColorLink);
        this.textStyle = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TextAppearance_android_textStyle, 0);
        this.typeface = typedArrayObtainStyledAttributes.getInt(C3476R.styleable.TextAppearance_android_typeface, 1);
        int indexWithValue = MaterialResources.getIndexWithValue(typedArrayObtainStyledAttributes, C3476R.styleable.TextAppearance_fontFamily, C3476R.styleable.TextAppearance_android_fontFamily);
        this.fontFamilyResourceId = typedArrayObtainStyledAttributes.getResourceId(indexWithValue, 0);
        this.fontFamily = typedArrayObtainStyledAttributes.getString(indexWithValue);
        this.textAllCaps = typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, C3476R.styleable.TextAppearance_android_shadowColor);
        this.shadowDx = typedArrayObtainStyledAttributes.getFloat(C3476R.styleable.TextAppearance_android_shadowDx, BitmapDescriptorFactory.HUE_RED);
        this.shadowDy = typedArrayObtainStyledAttributes.getFloat(C3476R.styleable.TextAppearance_android_shadowDy, BitmapDescriptorFactory.HUE_RED);
        this.shadowRadius = typedArrayObtainStyledAttributes.getFloat(C3476R.styleable.TextAppearance_android_shadowRadius, BitmapDescriptorFactory.HUE_RED);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFallbackTypeface() {
        if (this.font == null) {
            this.font = Typeface.create(this.fontFamily, this.textStyle);
        }
        if (this.font == null) {
            int i9 = this.typeface;
            if (i9 == 1) {
                this.font = Typeface.SANS_SERIF;
            } else if (i9 == 2) {
                this.font = Typeface.SERIF;
            } else if (i9 != 3) {
                this.font = Typeface.DEFAULT;
            } else {
                this.font = Typeface.MONOSPACE;
            }
            Typeface typeface = this.font;
            if (typeface != null) {
                this.font = Typeface.create(typeface, this.textStyle);
            }
        }
    }

    public Typeface getFont(Context context) {
        if (this.fontResolved) {
            return this.font;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceM24364b = C6348b.m24364b(context, this.fontFamilyResourceId);
                this.font = typefaceM24364b;
                if (typefaceM24364b != null) {
                    this.font = Typeface.create(typefaceM24364b, this.textStyle);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e9) {
                Log.d(TAG, "Error loading font " + this.fontFamily, e9);
            }
        }
        createFallbackTypeface();
        this.fontResolved = true;
        return this.font;
    }

    public void getFontAsync(Context context, final TextPaint textPaint, final C6348b.a aVar) {
        if (this.fontResolved) {
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        createFallbackTypeface();
        if (context.isRestricted()) {
            this.fontResolved = true;
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        try {
            C6348b.m24366d(context, this.fontFamilyResourceId, new C6348b.a() { // from class: com.google.android.material.resources.TextAppearance.1
                @Override // p206u.C6348b.a
                public void onFontRetrievalFailed(int i9) {
                    TextAppearance.this.createFallbackTypeface();
                    TextAppearance.this.fontResolved = true;
                    aVar.onFontRetrievalFailed(i9);
                }

                @Override // p206u.C6348b.a
                public void onFontRetrieved(Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                    TextAppearance.this.updateTextPaintMeasureState(textPaint, typeface);
                    TextAppearance.this.fontResolved = true;
                    aVar.onFontRetrieved(typeface);
                }
            }, null);
        } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
        } catch (Exception e9) {
            Log.d(TAG, "Error loading font " + this.fontFamily, e9);
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, C6348b.a aVar) {
        updateMeasureState(context, textPaint, aVar);
        ColorStateList colorStateList = this.textColor;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f9 = this.shadowRadius;
        float f10 = this.shadowDx;
        float f11 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        textPaint.setShadowLayer(f9, f10, f11, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void updateMeasureState(Context context, TextPaint textPaint, C6348b.a aVar) {
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            updateTextPaintMeasureState(textPaint, getFont(context));
            return;
        }
        getFontAsync(context, textPaint, aVar);
        if (this.fontResolved) {
            return;
        }
        updateTextPaintMeasureState(textPaint, this.font);
    }

    public void updateTextPaintMeasureState(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i9 = (~typeface.getStyle()) & this.textStyle;
        textPaint.setFakeBoldText((i9 & 1) != 0);
        textPaint.setTextSkewX((i9 & 2) != 0 ? -0.25f : BitmapDescriptorFactory.HUE_RED);
        textPaint.setTextSize(this.textSize);
    }
}
