package com.google.android.material.chip;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.customview.widget.AbstractC0340a;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import p011b0.C0570a;
import p042d0.C4647u;
import p052e0.C4704m;
import p206u.C6348b;

/* loaded from: classes2.dex */
public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate {
    private static final int CLOSE_ICON_VIRTUAL_ID = 0;
    private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    private static final String TAG = "Chip";
    private ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private int focusedVirtualView;
    private final C6348b.a fontCallback;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
    private View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;
    private static final Rect EMPTY_BOUNDS = new Rect();
    private static final int[] SELECTED_STATE = {R.attr.state_selected};

    public class ChipTouchHelper extends AbstractC0340a {
        public ChipTouchHelper(Chip chip) {
            super(chip);
        }

        @Override // androidx.customview.widget.AbstractC0340a
        public int getVirtualViewAt(float f9, float f10) {
            return (Chip.this.hasCloseIcon() && Chip.this.getCloseIconTouchBounds().contains(f9, f10)) ? 0 : -1;
        }

        @Override // androidx.customview.widget.AbstractC0340a
        public void getVisibleVirtualViews(List<Integer> list) {
            if (Chip.this.hasCloseIcon()) {
                list.add(0);
            }
        }

        @Override // androidx.customview.widget.AbstractC0340a
        public boolean onPerformActionForVirtualView(int i9, int i10, Bundle bundle) {
            if (i10 == 16 && i9 == 0) {
                return Chip.this.performCloseIconClick();
            }
            return false;
        }

        @Override // androidx.customview.widget.AbstractC0340a
        public void onPopulateNodeForHost(C4704m c4704m) {
            c4704m.m18779S(Chip.this.chipDrawable != null && Chip.this.chipDrawable.isCheckable());
            c4704m.m18781U(Chip.class.getName());
            c4704m.m18824t0(Chip.this.getText());
        }

        @Override // androidx.customview.widget.AbstractC0340a
        public void onPopulateNodeForVirtualView(int i9, C4704m c4704m) {
            if (!Chip.this.hasCloseIcon()) {
                c4704m.m18785Y("");
                c4704m.m18776P(Chip.EMPTY_BOUNDS);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                c4704m.m18785Y(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i10 = C3476R.string.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                c4704m.m18785Y(context.getString(i10, objArr).trim());
            }
            c4704m.m18776P(Chip.this.getCloseIconTouchBoundsInt());
            c4704m.m18789b(C4704m.a.f16429h);
            c4704m.m18790b0(Chip.this.isEnabled());
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    private void applyChipDrawable(ChipDrawable chipDrawable) {
        chipDrawable.setDelegate(this);
    }

    private float calculateTextOffsetFromStart(ChipDrawable chipDrawable) {
        float chipStartPadding = getChipStartPadding() + chipDrawable.calculateChipIconWidth() + getTextStartPadding();
        return C4647u.m18567s(this) == 0 ? chipStartPadding : -chipStartPadding;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int[] createCloseIconDrawableState() {
        ?? IsEnabled = isEnabled();
        int i9 = IsEnabled;
        if (this.closeIconFocused) {
            i9 = IsEnabled + 1;
        }
        int i10 = i9;
        if (this.closeIconHovered) {
            i10 = i9 + 1;
        }
        int i11 = i10;
        if (this.closeIconPressed) {
            i11 = i10 + 1;
        }
        int i12 = i11;
        if (isChecked()) {
            i12 = i11 + 1;
        }
        int[] iArr = new int[i12];
        int i13 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i13 = 1;
        }
        if (this.closeIconFocused) {
            iArr[i13] = 16842908;
            i13++;
        }
        if (this.closeIconHovered) {
            iArr[i13] = 16843623;
            i13++;
        }
        if (this.closeIconPressed) {
            iArr[i13] = 16842919;
            i13++;
        }
        if (isChecked()) {
            iArr[i13] = 16842913;
        }
        return iArr;
    }

    private void ensureFocus() {
        if (this.focusedVirtualView == Integer.MIN_VALUE) {
            setFocusedVirtualView(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if (hasCloseIcon()) {
            this.chipDrawable.getCloseIconTouchBounds(this.rectF);
        }
        return this.rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.rect.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.rect;
    }

    private TextAppearance getTextAppearance() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getTextAppearance();
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private boolean handleAccessibilityExit(MotionEvent motionEvent) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = AbstractC0340a.class.getDeclaredField("mHoveredVirtualViewId");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.touchHelper)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = AbstractC0340a.class.getDeclaredMethod("updateHoveredVirtualView", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.touchHelper, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e9) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e9);
            } catch (NoSuchFieldException e10) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e10);
            } catch (NoSuchMethodException e11) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e11);
            } catch (InvocationTargetException e12) {
                Log.e(TAG, "Unable to send Accessibility Exit event", e12);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasCloseIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return (chipDrawable == null || chipDrawable.getCloseIcon() == null) ? false : true;
    }

    private void initOutlineProvider() {
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.google.android.material.chip.Chip.2
            @Override // android.view.ViewOutlineProvider
            @TargetApi(21)
            public void getOutline(View view, Outline outline) {
                if (Chip.this.chipDrawable != null) {
                    Chip.this.chipDrawable.getOutline(outline);
                } else {
                    outline.setAlpha(BitmapDescriptorFactory.HUE_RED);
                }
            }
        });
    }

    private boolean moveFocus(boolean z8) {
        ensureFocus();
        if (z8) {
            if (this.focusedVirtualView == -1) {
                setFocusedVirtualView(0);
                return true;
            }
        } else if (this.focusedVirtualView == 0) {
            setFocusedVirtualView(-1);
            return true;
        }
        return false;
    }

    private void setCloseIconFocused(boolean z8) {
        if (this.closeIconFocused != z8) {
            this.closeIconFocused = z8;
            refreshDrawableState();
        }
    }

    private void setCloseIconHovered(boolean z8) {
        if (this.closeIconHovered != z8) {
            this.closeIconHovered = z8;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z8) {
        if (this.closeIconPressed != z8) {
            this.closeIconPressed = z8;
            refreshDrawableState();
        }
    }

    private void setFocusedVirtualView(int i9) {
        int i10 = this.focusedVirtualView;
        if (i10 != i9) {
            if (i10 == 0) {
                setCloseIconFocused(false);
            }
            this.focusedVirtualView = i9;
            if (i9 == 0) {
                setCloseIconFocused(true);
            }
        }
    }

    private void unapplyChipDrawable(ChipDrawable chipDrawable) {
        if (chipDrawable != null) {
            chipDrawable.setDelegate(null);
        }
    }

    private void updatePaddingInternal() {
        ChipDrawable chipDrawable;
        if (TextUtils.isEmpty(getText()) || (chipDrawable = this.chipDrawable) == null) {
            return;
        }
        float chipStartPadding = chipDrawable.getChipStartPadding() + this.chipDrawable.getChipEndPadding() + this.chipDrawable.getTextStartPadding() + this.chipDrawable.getTextEndPadding();
        if ((this.chipDrawable.isChipIconVisible() && this.chipDrawable.getChipIcon() != null) || (this.chipDrawable.getCheckedIcon() != null && this.chipDrawable.isCheckedIconVisible() && isChecked())) {
            chipStartPadding += this.chipDrawable.getIconStartPadding() + this.chipDrawable.getIconEndPadding() + this.chipDrawable.getChipIconSize();
        }
        if (this.chipDrawable.isCloseIconVisible() && this.chipDrawable.getCloseIcon() != null) {
            chipStartPadding += this.chipDrawable.getCloseIconStartPadding() + this.chipDrawable.getCloseIconEndPadding() + this.chipDrawable.getCloseIconSize();
        }
        if (C4647u.m18570v(this) != chipStartPadding) {
            C4647u.m18556m0(this, C4647u.m18571w(this), getPaddingTop(), (int) chipStartPadding, getPaddingBottom());
        }
    }

    private void updateTextPaintDrawState(TextAppearance textAppearance) {
        TextPaint paint = getPaint();
        paint.drawableState = this.chipDrawable.getState();
        textAppearance.updateDrawState(getContext(), paint, this.fontCallback);
    }

    private void validateAttributes(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "background") != null) {
            throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
        }
        if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableLeft") != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableStart") != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableEnd") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (attributeSet.getAttributeValue(NAMESPACE_ANDROID, "drawableRight") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (!attributeSet.getAttributeBooleanValue(NAMESPACE_ANDROID, "singleLine", true) || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "lines", 1) != 1 || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "minLines", 1) != 1 || attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "maxLines", 1) != 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        if (attributeSet.getAttributeIntValue(NAMESPACE_ANDROID, "gravity", 8388627) != 8388627) {
            Log.w(TAG, "Chip text must be vertically center and start aligned");
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return handleAccessibilityExit(motionEvent) || this.touchHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.touchHelper.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ChipDrawable chipDrawable = this.chipDrawable;
        if ((chipDrawable == null || !chipDrawable.isCloseIconStateful()) ? false : this.chipDrawable.setCloseIconState(createCloseIconDrawableState())) {
            invalidate();
        }
    }

    public Drawable getCheckedIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getCheckedIcon();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getChipBackgroundColor();
        }
        return null;
    }

    public float getChipCornerRadius() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipCornerRadius() : BitmapDescriptorFactory.HUE_RED;
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public float getChipEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipEndPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public Drawable getChipIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getChipIcon();
        }
        return null;
    }

    public float getChipIconSize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipIconSize() : BitmapDescriptorFactory.HUE_RED;
    }

    public ColorStateList getChipIconTint() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getChipIconTint();
        }
        return null;
    }

    public float getChipMinHeight() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipMinHeight() : BitmapDescriptorFactory.HUE_RED;
    }

    public float getChipStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipStartPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public ColorStateList getChipStrokeColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getChipStrokeColor();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getChipStrokeWidth() : BitmapDescriptorFactory.HUE_RED;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getCloseIcon();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getCloseIconContentDescription();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getCloseIconEndPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public float getCloseIconSize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getCloseIconSize() : BitmapDescriptorFactory.HUE_RED;
    }

    public float getCloseIconStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getCloseIconStartPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public ColorStateList getCloseIconTint() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getCloseIconTint();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getEllipsize();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.focusedVirtualView == 0) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public MotionSpec getHideMotionSpec() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getHideMotionSpec();
        }
        return null;
    }

    public float getIconEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getIconEndPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public float getIconStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getIconStartPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public ColorStateList getRippleColor() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getRippleColor();
        }
        return null;
    }

    public MotionSpec getShowMotionSpec() {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            return chipDrawable.getShowMotionSpec();
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getText() : "";
    }

    public float getTextEndPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getTextEndPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public float getTextStartPadding() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null ? chipDrawable.getTextStartPadding() : BitmapDescriptorFactory.HUE_RED;
    }

    public boolean isCheckable() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null && chipDrawable.isCheckable();
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null && chipDrawable.isCheckedIconVisible();
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null && chipDrawable.isChipIconVisible();
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconVisible() {
        ChipDrawable chipDrawable = this.chipDrawable;
        return chipDrawable != null && chipDrawable.isCloseIconVisible();
    }

    @Override // com.google.android.material.chip.ChipDrawable.Delegate
    public void onChipDrawableSizeChange() {
        updatePaddingInternal();
        requestLayout();
        invalidateOutline();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i9) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i9 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, SELECTED_STATE);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        ChipDrawable chipDrawable;
        if (TextUtils.isEmpty(getText()) || (chipDrawable = this.chipDrawable) == null || chipDrawable.shouldDrawText()) {
            super.onDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.translate(calculateTextOffsetFromStart(this.chipDrawable), BitmapDescriptorFactory.HUE_RED);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z8, int i9, Rect rect) {
        if (z8) {
            setFocusedVirtualView(-1);
        } else {
            setFocusedVirtualView(Integer.MIN_VALUE);
        }
        invalidate();
        super.onFocusChanged(z8, i9, rect);
        this.touchHelper.onFocusChanged(z8, i9, rect);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean zMoveFocus = false;
        if (keyCode == 61) {
            int i10 = keyEvent.hasNoModifiers() ? 2 : keyEvent.hasModifiers(1) ? 1 : 0;
            if (i10 != 0) {
                ViewParent parent = getParent();
                View viewFocusSearch = this;
                do {
                    viewFocusSearch = viewFocusSearch.focusSearch(i10);
                    if (viewFocusSearch == null || viewFocusSearch == this) {
                        break;
                    }
                } while (viewFocusSearch.getParent() == parent);
                if (viewFocusSearch != null) {
                    viewFocusSearch.requestFocus();
                    return true;
                }
            }
        } else if (keyCode != 66) {
            switch (keyCode) {
                case 21:
                    if (keyEvent.hasNoModifiers()) {
                        zMoveFocus = moveFocus(ViewUtils.isLayoutRtl(this));
                        break;
                    }
                    break;
                case 22:
                    if (keyEvent.hasNoModifiers()) {
                        zMoveFocus = moveFocus(!ViewUtils.isLayoutRtl(this));
                        break;
                    }
                    break;
                case 23:
                    int i11 = this.focusedVirtualView;
                    if (i11 == -1) {
                        performClick();
                        return true;
                    }
                    if (i11 == 0) {
                        performCloseIconClick();
                        return true;
                    }
                    break;
            }
        }
        if (!zMoveFocus) {
            return super.onKeyDown(i9, keyEvent);
        }
        invalidate();
        return true;
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i9) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L22;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        int actionMasked = motionEvent.getActionMasked();
        boolean zContains = getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY());
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (this.closeIconPressed) {
                        if (!zContains) {
                            setCloseIconPressed(false);
                        }
                        z8 = true;
                    }
                }
                z8 = false;
            } else {
                if (this.closeIconPressed) {
                    performCloseIconClick();
                    z8 = true;
                }
                setCloseIconPressed(false);
            }
            z8 = false;
            setCloseIconPressed(false);
        } else {
            if (zContains) {
                setCloseIconPressed(true);
                z8 = true;
            }
            z8 = false;
        }
        return z8 || super.onTouchEvent(motionEvent);
    }

    public boolean performCloseIconClick() {
        boolean z8;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.onCloseIconClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z8 = true;
        } else {
            z8 = false;
        }
        this.touchHelper.sendEventForVirtualView(0, 1);
        return z8;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable != this.chipDrawable && drawable != this.ripple) {
            throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
        }
        super.setBackground(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i9) {
        throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != this.chipDrawable && drawable != this.ripple) {
            throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i9) {
        throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z8) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckable(z8);
        }
    }

    public void setCheckableResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckableResource(i9);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z8) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable == null) {
            this.deferredCheckedValue = z8;
            return;
        }
        if (chipDrawable.isCheckable()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z8);
            if (zIsChecked == z8 || (onCheckedChangeListener = this.onCheckedChangeListenerInternal) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z8);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIcon(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z8) {
        setCheckedIconVisible(z8);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i9) {
        setCheckedIconVisible(i9);
    }

    public void setCheckedIconResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconResource(i9);
        }
    }

    public void setCheckedIconVisible(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconVisible(i9);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipBackgroundColor(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipBackgroundColorResource(i9);
        }
    }

    public void setChipCornerRadius(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipCornerRadius(f9);
        }
    }

    public void setChipCornerRadiusResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipCornerRadiusResource(i9);
        }
    }

    public void setChipDrawable(ChipDrawable chipDrawable) {
        ChipDrawable chipDrawable2 = this.chipDrawable;
        if (chipDrawable2 != chipDrawable) {
            unapplyChipDrawable(chipDrawable2);
            this.chipDrawable = chipDrawable;
            applyChipDrawable(chipDrawable);
            if (!RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.chipDrawable.setUseCompatRipple(true);
                C4647u.m18534b0(this, this.chipDrawable);
            } else {
                this.ripple = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.chipDrawable.getRippleColor()), this.chipDrawable, null);
                this.chipDrawable.setUseCompatRipple(false);
                C4647u.m18534b0(this, this.ripple);
            }
        }
    }

    public void setChipEndPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipEndPadding(f9);
        }
    }

    public void setChipEndPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipEndPaddingResource(i9);
        }
    }

    public void setChipIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIcon(drawable);
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
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconResource(i9);
        }
    }

    public void setChipIconSize(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconSize(f9);
        }
    }

    public void setChipIconSizeResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconSizeResource(i9);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconTint(colorStateList);
        }
    }

    public void setChipIconTintResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconTintResource(i9);
        }
    }

    public void setChipIconVisible(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconVisible(i9);
        }
    }

    public void setChipMinHeight(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipMinHeight(f9);
        }
    }

    public void setChipMinHeightResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipMinHeightResource(i9);
        }
    }

    public void setChipStartPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStartPadding(f9);
        }
    }

    public void setChipStartPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStartPaddingResource(i9);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeColor(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeColorResource(i9);
        }
    }

    public void setChipStrokeWidth(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeWidth(f9);
        }
    }

    public void setChipStrokeWidthResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipStrokeWidthResource(i9);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i9) {
        setText(getResources().getString(i9));
    }

    public void setCloseIcon(Drawable drawable) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIcon(drawable);
        }
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconContentDescription(charSequence);
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
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconEndPadding(f9);
        }
    }

    public void setCloseIconEndPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconEndPaddingResource(i9);
        }
    }

    public void setCloseIconResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconResource(i9);
        }
    }

    public void setCloseIconSize(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconSize(f9);
        }
    }

    public void setCloseIconSizeResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconSizeResource(i9);
        }
    }

    public void setCloseIconStartPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconStartPadding(f9);
        }
    }

    public void setCloseIconStartPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconStartPaddingResource(i9);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconTint(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconTintResource(i9);
        }
    }

    public void setCloseIconVisible(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconVisible(i9);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i9, int i10, int i11, int i12) {
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i11 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i9, i10, i11, i12);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i9, int i10, int i11, int i12) {
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i11 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i9, i10, i11, i12);
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.chipDrawable == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setEllipsize(truncateAt);
        }
    }

    @Override // android.widget.TextView
    public void setGravity(int i9) {
        if (i9 != 8388627) {
            Log.w(TAG, "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i9);
        }
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setHideMotionSpec(motionSpec);
        }
    }

    public void setHideMotionSpecResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setHideMotionSpecResource(i9);
        }
    }

    public void setIconEndPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconEndPadding(f9);
        }
    }

    public void setIconEndPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconEndPaddingResource(i9);
        }
    }

    public void setIconStartPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconStartPadding(f9);
        }
    }

    public void setIconStartPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setIconStartPaddingResource(i9);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i9) {
        if (i9 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i9);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i9) {
        if (i9 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i9);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i9) {
        super.setMaxWidth(i9);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setMaxWidth(i9);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i9) {
        if (i9 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i9);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListenerInternal = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.onCloseIconClickListener = onClickListener;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setRippleColor(colorStateList);
        }
    }

    public void setRippleColorResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setRippleColorResource(i9);
        }
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setShowMotionSpec(motionSpec);
        }
    }

    public void setShowMotionSpecResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setShowMotionSpecResource(i9);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z8) {
        if (!z8) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z8);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (this.chipDrawable == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        CharSequence charSequenceM3223h = C0570a.m3218c().m3223h(charSequence);
        if (this.chipDrawable.shouldDrawText()) {
            charSequenceM3223h = null;
        }
        super.setText(charSequenceM3223h, bufferType);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setText(charSequence);
        }
    }

    public void setTextAppearance(TextAppearance textAppearance) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextAppearance(textAppearance);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(textAppearance);
        }
    }

    public void setTextAppearanceResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextAppearanceResource(i9);
        }
        setTextAppearance(getContext(), i9);
    }

    public void setTextEndPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextEndPadding(f9);
        }
    }

    public void setTextEndPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextEndPaddingResource(i9);
        }
    }

    public void setTextStartPadding(float f9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextStartPadding(f9);
        }
    }

    public void setTextStartPaddingResource(int i9) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextStartPaddingResource(i9);
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.chipStyle);
    }

    public Chip(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.focusedVirtualView = Integer.MIN_VALUE;
        this.rect = new Rect();
        this.rectF = new RectF();
        this.fontCallback = new C6348b.a() { // from class: com.google.android.material.chip.Chip.1
            @Override // p206u.C6348b.a
            public void onFontRetrievalFailed(int i10) {
            }

            @Override // p206u.C6348b.a
            public void onFontRetrieved(Typeface typeface) {
                Chip chip = Chip.this;
                chip.setText(chip.getText());
                Chip.this.requestLayout();
                Chip.this.invalidate();
            }
        };
        validateAttributes(attributeSet);
        ChipDrawable chipDrawableCreateFromAttributes = ChipDrawable.createFromAttributes(context, attributeSet, i9, C3476R.style.Widget_MaterialComponents_Chip_Action);
        setChipDrawable(chipDrawableCreateFromAttributes);
        ChipTouchHelper chipTouchHelper = new ChipTouchHelper(this);
        this.touchHelper = chipTouchHelper;
        C4647u.m18530Z(this, chipTouchHelper);
        initOutlineProvider();
        setChecked(this.deferredCheckedValue);
        chipDrawableCreateFromAttributes.setShouldDrawText(false);
        setText(chipDrawableCreateFromAttributes.getText());
        setEllipsize(chipDrawableCreateFromAttributes.getEllipsize());
        setIncludeFontPadding(false);
        if (getTextAppearance() != null) {
            updateTextPaintDrawState(getTextAppearance());
        }
        setSingleLine();
        setGravity(8388627);
        updatePaddingInternal();
    }

    public void setCheckedIconVisible(boolean z8) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCheckedIconVisible(z8);
        }
    }

    public void setChipIconVisible(boolean z8) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setChipIconVisible(z8);
        }
    }

    public void setCloseIconVisible(boolean z8) {
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setCloseIconVisible(z8);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextAppearanceResource(i9);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(context, getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i9) {
        super.setTextAppearance(i9);
        ChipDrawable chipDrawable = this.chipDrawable;
        if (chipDrawable != null) {
            chipDrawable.setTextAppearanceResource(i9);
        }
        if (getTextAppearance() != null) {
            getTextAppearance().updateMeasureState(getContext(), getPaint(), this.fontCallback);
            updateTextPaintDrawState(getTextAppearance());
        }
    }
}
