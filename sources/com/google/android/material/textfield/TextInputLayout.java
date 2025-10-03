package com.google.android.material.textfield;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.C0227f;
import androidx.appcompat.widget.C0250q0;
import androidx.appcompat.widget.C0262x;
import androidx.core.widget.C0337l;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p020c.C0694a;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;
import p197t.C6273a;
import p224w.C6494a;

/* loaded from: classes2.dex */
public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator animator;
    private GradientDrawable boxBackground;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxBottomOffsetPx;
    private final int boxCollapsedPaddingTopPx;
    private float boxCornerRadiusBottomEnd;
    private float boxCornerRadiusBottomStart;
    private float boxCornerRadiusTopEnd;
    private float boxCornerRadiusTopStart;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private final int counterOverflowTextAppearance;
    private boolean counterOverflowed;
    private final int counterTextAppearance;
    private TextView counterView;
    private ColorStateList defaultHintTextColor;
    private final int defaultStrokeColor;
    private final int disabledColor;
    EditText editText;
    private Drawable editTextOriginalDrawable;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasPasswordToggleTintList;
    private boolean hasPasswordToggleTintMode;
    private boolean hasReconstructedEditTextBackground;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private CharSequence passwordToggleContentDesc;
    private Drawable passwordToggleDrawable;
    private Drawable passwordToggleDummyDrawable;
    private boolean passwordToggleEnabled;
    private ColorStateList passwordToggleTintList;
    private PorterDuff.Mode passwordToggleTintMode;
    private CheckableImageButton passwordToggleView;
    private boolean passwordToggledVisible;
    private boolean restoringSavedState;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    public static class AccessibilityDelegate extends C4613a {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            EditText editText = this.layout.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.layout.getHint();
            CharSequence error = this.layout.getError();
            CharSequence counterOverflowDescription = this.layout.getCounterOverflowDescription();
            boolean z8 = !TextUtils.isEmpty(text);
            boolean z9 = !TextUtils.isEmpty(hint);
            boolean z10 = !TextUtils.isEmpty(error);
            boolean z11 = false;
            boolean z12 = z10 || !TextUtils.isEmpty(counterOverflowDescription);
            if (z8) {
                c4704m.m18824t0(text);
            } else if (z9) {
                c4704m.m18824t0(hint);
            }
            if (z9) {
                c4704m.m18800g0(hint);
                if (!z8 && z9) {
                    z11 = true;
                }
                c4704m.m18818q0(z11);
            }
            if (z12) {
                if (!z10) {
                    error = counterOverflowDescription;
                }
                c4704m.m18792c0(error);
                c4704m.m18786Z(true);
            }
        }

        @Override // p042d0.C4613a
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            EditText editText = this.layout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            if (TextUtils.isEmpty(text)) {
                text = this.layout.getHint();
            }
            if (TextUtils.isEmpty(text)) {
                return;
            }
            accessibilityEvent.getText().add(text);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.textfield.TextInputLayout.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        CharSequence error;
        boolean isPasswordToggledVisible;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.error) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            TextUtils.writeToParcel(this.error, parcel, i9);
            parcel.writeInt(this.isPasswordToggledVisible ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isPasswordToggledVisible = parcel.readInt() == 1;
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private void applyBoxAttributes() {
        int i9;
        Drawable drawable;
        if (this.boxBackground == null) {
            return;
        }
        setBoxAttributes();
        EditText editText = this.editText;
        if (editText != null && this.boxBackgroundMode == 2) {
            if (editText.getBackground() != null) {
                this.editTextOriginalDrawable = this.editText.getBackground();
            }
            C4647u.m18534b0(this.editText, null);
        }
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackgroundMode == 1 && (drawable = this.editTextOriginalDrawable) != null) {
            C4647u.m18534b0(editText2, drawable);
        }
        int i10 = this.boxStrokeWidthPx;
        if (i10 > -1 && (i9 = this.boxStrokeColor) != 0) {
            this.boxBackground.setStroke(i10, i9);
        }
        this.boxBackground.setCornerRadii(getCornerRadiiAsArray());
        this.boxBackground.setColor(this.boxBackgroundColor);
        invalidate();
    }

    private void applyCutoutPadding(RectF rectF) {
        float f9 = rectF.left;
        int i9 = this.boxLabelCutoutPaddingPx;
        rectF.left = f9 - i9;
        rectF.top -= i9;
        rectF.right += i9;
        rectF.bottom += i9;
    }

    private void applyPasswordToggleTint() {
        Drawable drawable = this.passwordToggleDrawable;
        if (drawable != null) {
            if (this.hasPasswordToggleTintList || this.hasPasswordToggleTintMode) {
                Drawable drawableMutate = C6494a.m24849l(drawable).mutate();
                this.passwordToggleDrawable = drawableMutate;
                if (this.hasPasswordToggleTintList) {
                    C6494a.m24846i(drawableMutate, this.passwordToggleTintList);
                }
                if (this.hasPasswordToggleTintMode) {
                    C6494a.m24847j(this.passwordToggleDrawable, this.passwordToggleTintMode);
                }
                CheckableImageButton checkableImageButton = this.passwordToggleView;
                if (checkableImageButton != null) {
                    Drawable drawable2 = checkableImageButton.getDrawable();
                    Drawable drawable3 = this.passwordToggleDrawable;
                    if (drawable2 != drawable3) {
                        this.passwordToggleView.setImageDrawable(drawable3);
                    }
                }
            }
        }
    }

    private void assignBoxBackgroundByMode() {
        int i9 = this.boxBackgroundMode;
        if (i9 == 0) {
            this.boxBackground = null;
            return;
        }
        if (i9 == 2 && this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
            this.boxBackground = new CutoutDrawable();
        } else {
            if (this.boxBackground instanceof GradientDrawable) {
                return;
            }
            this.boxBackground = new GradientDrawable();
        }
    }

    private int calculateBoxBackgroundTop() {
        EditText editText = this.editText;
        if (editText == null) {
            return 0;
        }
        int i9 = this.boxBackgroundMode;
        if (i9 == 1) {
            return editText.getTop();
        }
        if (i9 != 2) {
            return 0;
        }
        return editText.getTop() + calculateLabelMarginTop();
    }

    private int calculateCollapsedTextTopBounds() {
        int i9 = this.boxBackgroundMode;
        return i9 != 1 ? i9 != 2 ? getPaddingTop() : getBoxBackground().getBounds().top - calculateLabelMarginTop() : getBoxBackground().getBounds().top + this.boxCollapsedPaddingTopPx;
    }

    private int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (!this.hintEnabled) {
            return 0;
        }
        int i9 = this.boxBackgroundMode;
        if (i9 == 0 || i9 == 1) {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
        } else {
            if (i9 != 2) {
                return 0;
            }
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
        }
        return (int) collapsedTextHeight;
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void collapseHint(boolean z8) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z8 && this.hintAnimationEnabled) {
            animateToExpansionFraction(1.0f);
        } else {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void ensureBackgroundDrawableStateWorkaround() {
    }

    private void expandHint(boolean z8) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z8 && this.hintAnimationEnabled) {
            animateToExpansionFraction(BitmapDescriptorFactory.HUE_RED);
        } else {
            this.collapsingTextHelper.setExpansionFraction(BitmapDescriptorFactory.HUE_RED);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
    }

    private Drawable getBoxBackground() {
        int i9 = this.boxBackgroundMode;
        if (i9 == 1 || i9 == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    private float[] getCornerRadiiAsArray() {
        if (ViewUtils.isLayoutRtl(this)) {
            float f9 = this.boxCornerRadiusTopEnd;
            float f10 = this.boxCornerRadiusTopStart;
            float f11 = this.boxCornerRadiusBottomStart;
            float f12 = this.boxCornerRadiusBottomEnd;
            return new float[]{f9, f9, f10, f10, f11, f11, f12, f12};
        }
        float f13 = this.boxCornerRadiusTopStart;
        float f14 = this.boxCornerRadiusTopEnd;
        float f15 = this.boxCornerRadiusBottomEnd;
        float f16 = this.boxCornerRadiusBottomStart;
        return new float[]{f13, f13, f14, f14, f15, f15, f16, f16};
    }

    private boolean hasPasswordTransformation() {
        EditText editText = this.editText;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF rectF = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(rectF);
            applyCutoutPadding(rectF);
            ((CutoutDrawable) this.boxBackground).setCutout(rectF);
        }
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z8) {
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = viewGroup.getChildAt(i9);
            childAt.setEnabled(z8);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z8);
            }
        }
    }

    private void setBoxAttributes() {
        int i9 = this.boxBackgroundMode;
        if (i9 == 1) {
            this.boxStrokeWidthPx = 0;
        } else if (i9 == 2 && this.focusedStrokeColor == 0) {
            this.focusedStrokeColor = this.focusedTextColor.getColorForState(getDrawableState(), this.focusedTextColor.getDefaultColor());
        }
    }

    private void setEditText(EditText editText) {
        if (this.editText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(editText instanceof TextInputEditText)) {
            Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.editText = editText;
        onApplyBoxBackgroundMode();
        setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        if (!hasPasswordTransformation()) {
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
        }
        this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
        int gravity = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity((gravity & (-113)) | 48);
        this.collapsingTextHelper.setExpandedTextGravity(gravity);
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.google.android.material.textfield.TextInputLayout.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                TextInputLayout.this.updateLabelState(!r0.restoringSavedState);
                TextInputLayout textInputLayout = TextInputLayout.this;
                if (textInputLayout.counterEnabled) {
                    textInputLayout.updateCounter(editable.length());
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            }
        });
        if (this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
        }
        if (this.hintEnabled) {
            if (TextUtils.isEmpty(this.hint)) {
                CharSequence hint = this.editText.getHint();
                this.originalHint = hint;
                setHint(hint);
                this.editText.setHint((CharSequence) null);
            }
            this.isProvidingHint = true;
        }
        if (this.counterView != null) {
            updateCounter(this.editText.getText().length());
        }
        this.indicatorViewController.adjustIndicatorPadding();
        updatePasswordToggleView();
        updateLabelState(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.hint)) {
            return;
        }
        this.hint = charSequence;
        this.collapsingTextHelper.setText(charSequence);
        if (this.hintExpanded) {
            return;
        }
        openCutout();
    }

    private boolean shouldShowPasswordIcon() {
        return this.passwordToggleEnabled && (hasPasswordTransformation() || this.passwordToggledVisible);
    }

    private void updateEditTextBackgroundBounds() {
        Drawable background;
        EditText editText = this.editText;
        if (editText == null || (background = editText.getBackground()) == null) {
            return;
        }
        if (C0262x.m1073a(background)) {
            background = background.mutate();
        }
        DescendantOffsetUtils.getDescendantRect(this, this.editText, new Rect());
        Rect bounds = background.getBounds();
        if (bounds.left != bounds.right) {
            Rect rect = new Rect();
            background.getPadding(rect);
            background.setBounds(bounds.left - rect.left, bounds.top, bounds.right + (rect.right * 2), this.editText.getBottom());
        }
    }

    private void updateInputLayoutMargins() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
        int iCalculateLabelMarginTop = calculateLabelMarginTop();
        if (iCalculateLabelMarginTop != layoutParams.topMargin) {
            layoutParams.topMargin = iCalculateLabelMarginTop;
            this.inputFrame.requestLayout();
        }
    }

    private void updatePasswordToggleView() {
        if (this.editText == null) {
            return;
        }
        if (!shouldShowPasswordIcon()) {
            CheckableImageButton checkableImageButton = this.passwordToggleView;
            if (checkableImageButton != null && checkableImageButton.getVisibility() == 0) {
                this.passwordToggleView.setVisibility(8);
            }
            if (this.passwordToggleDummyDrawable != null) {
                Drawable[] drawableArrM1606a = C0337l.m1606a(this.editText);
                if (drawableArrM1606a[2] == this.passwordToggleDummyDrawable) {
                    C0337l.m1615j(this.editText, drawableArrM1606a[0], drawableArrM1606a[1], this.originalEditTextEndDrawable, drawableArrM1606a[3]);
                    this.passwordToggleDummyDrawable = null;
                    return;
                }
                return;
            }
            return;
        }
        if (this.passwordToggleView == null) {
            CheckableImageButton checkableImageButton2 = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(C3476R.layout.design_text_input_password_icon, (ViewGroup) this.inputFrame, false);
            this.passwordToggleView = checkableImageButton2;
            checkableImageButton2.setImageDrawable(this.passwordToggleDrawable);
            this.passwordToggleView.setContentDescription(this.passwordToggleContentDesc);
            this.inputFrame.addView(this.passwordToggleView);
            this.passwordToggleView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.textfield.TextInputLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TextInputLayout.this.passwordVisibilityToggleRequested(false);
                }
            });
        }
        EditText editText = this.editText;
        if (editText != null && C4647u.m18568t(editText) <= 0) {
            this.editText.setMinimumHeight(C4647u.m18568t(this.passwordToggleView));
        }
        this.passwordToggleView.setVisibility(0);
        this.passwordToggleView.setChecked(this.passwordToggledVisible);
        if (this.passwordToggleDummyDrawable == null) {
            this.passwordToggleDummyDrawable = new ColorDrawable();
        }
        this.passwordToggleDummyDrawable.setBounds(0, 0, this.passwordToggleView.getMeasuredWidth(), 1);
        Drawable[] drawableArrM1606a2 = C0337l.m1606a(this.editText);
        Drawable drawable = drawableArrM1606a2[2];
        Drawable drawable2 = this.passwordToggleDummyDrawable;
        if (drawable != drawable2) {
            this.originalEditTextEndDrawable = drawable;
        }
        C0337l.m1615j(this.editText, drawableArrM1606a2[0], drawableArrM1606a2[1], drawable2, drawableArrM1606a2[3]);
        this.passwordToggleView.setPadding(this.editText.getPaddingLeft(), this.editText.getPaddingTop(), this.editText.getPaddingRight(), this.editText.getPaddingBottom());
    }

    private void updateTextInputBoxBounds() {
        if (this.boxBackgroundMode == 0 || this.boxBackground == null || this.editText == null || getRight() == 0) {
            return;
        }
        int left = this.editText.getLeft();
        int iCalculateBoxBackgroundTop = calculateBoxBackgroundTop();
        int right = this.editText.getRight();
        int bottom = this.editText.getBottom() + this.boxBottomOffsetPx;
        if (this.boxBackgroundMode == 2) {
            int i9 = this.boxStrokeWidthFocusedPx;
            left += i9 / 2;
            iCalculateBoxBackgroundTop -= i9 / 2;
            right -= i9 / 2;
            bottom += i9 / 2;
        }
        this.boxBackground.setBounds(left, iCalculateBoxBackgroundTop, right, bottom);
        applyBoxAttributes();
        updateEditTextBackgroundBounds();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i9, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.inputFrame.addView(view, layoutParams2);
        this.inputFrame.setLayoutParams(layoutParams);
        updateInputLayoutMargins();
        setEditText((EditText) view);
    }

    public void animateToExpansionFraction(float f9) {
        if (this.collapsingTextHelper.getExpansionFraction() == f9) {
            return;
        }
        if (this.animator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.animator = valueAnimator;
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.animator.setDuration(167L);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.TextInputLayout.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
        }
        this.animator.setFloatValues(this.collapsingTextHelper.getExpansionFraction(), f9);
        this.animator.start();
    }

    public boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i9) {
        EditText editText;
        if (this.originalHint == null || (editText = this.editText) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i9);
            return;
        }
        boolean z8 = this.isProvidingHint;
        this.isProvidingHint = false;
        CharSequence hint = editText.getHint();
        this.editText.setHint(this.originalHint);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i9);
        } finally {
            this.editText.setHint(hint);
            this.isProvidingHint = z8;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        GradientDrawable gradientDrawable = this.boxBackground;
        if (gradientDrawable != null) {
            gradientDrawable.draw(canvas);
        }
        super.draw(canvas);
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.inDrawableStateChanged) {
            return;
        }
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        updateLabelState(C4647u.m18513I(this) && isEnabled());
        updateEditTextBackground();
        updateTextInputBoxBounds();
        updateTextInputBoxState();
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null ? collapsingTextHelper.setState(drawableState) | false : false) {
            invalidate();
        }
        this.inDrawableStateChanged = false;
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.boxCornerRadiusBottomEnd;
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.boxCornerRadiusBottomStart;
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.boxCornerRadiusTopEnd;
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxCornerRadiusTopStart;
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.counterEnabled && this.counterOverflowed && (textView = this.counterView) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled()) {
            return this.indicatorViewController.getErrorText();
        }
        return null;
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public CharSequence getHelperText() {
        if (this.indicatorViewController.isHelperTextEnabled()) {
            return this.indicatorViewController.getHelperText();
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.passwordToggleContentDesc;
    }

    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.passwordToggleDrawable;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        return this.passwordToggleEnabled;
    }

    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        EditText editText;
        super.onLayout(z8, i9, i10, i11, i12);
        if (this.boxBackground != null) {
            updateTextInputBoxBounds();
        }
        if (!this.hintEnabled || (editText = this.editText) == null) {
            return;
        }
        Rect rect = this.tmpRect;
        DescendantOffsetUtils.getDescendantRect(this, editText, rect);
        int compoundPaddingLeft = rect.left + this.editText.getCompoundPaddingLeft();
        int compoundPaddingRight = rect.right - this.editText.getCompoundPaddingRight();
        int iCalculateCollapsedTextTopBounds = calculateCollapsedTextTopBounds();
        this.collapsingTextHelper.setExpandedBounds(compoundPaddingLeft, rect.top + this.editText.getCompoundPaddingTop(), compoundPaddingRight, rect.bottom - this.editText.getCompoundPaddingBottom());
        this.collapsingTextHelper.setCollapsedBounds(compoundPaddingLeft, iCalculateCollapsedTextTopBounds, compoundPaddingRight, (i12 - i10) - getPaddingBottom());
        this.collapsingTextHelper.recalculate();
        if (!cutoutEnabled() || this.hintExpanded) {
            return;
        }
        openCutout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        updatePasswordToggleView();
        super.onMeasure(i9, i10);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setError(savedState.error);
        if (savedState.isPasswordToggledVisible) {
            passwordVisibilityToggleRequested(true);
        }
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            savedState.error = getError();
        }
        savedState.isPasswordToggledVisible = this.passwordToggledVisible;
        return savedState;
    }

    public void passwordVisibilityToggleRequested(boolean z8) {
        if (this.passwordToggleEnabled) {
            int selectionEnd = this.editText.getSelectionEnd();
            if (hasPasswordTransformation()) {
                this.editText.setTransformationMethod(null);
                this.passwordToggledVisible = true;
            } else {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.passwordToggledVisible = false;
            }
            this.passwordToggleView.setChecked(this.passwordToggledVisible);
            if (z8) {
                this.passwordToggleView.jumpDrawablesToCurrentState();
            }
            this.editText.setSelection(selectionEnd);
        }
    }

    public void setBoxBackgroundColor(int i9) {
        if (this.boxBackgroundColor != i9) {
            this.boxBackgroundColor = i9;
            applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorResource(int i9) {
        setBoxBackgroundColor(C6273a.m24024c(getContext(), i9));
    }

    public void setBoxBackgroundMode(int i9) {
        if (i9 == this.boxBackgroundMode) {
            return;
        }
        this.boxBackgroundMode = i9;
        onApplyBoxBackgroundMode();
    }

    public void setBoxCornerRadii(float f9, float f10, float f11, float f12) {
        if (this.boxCornerRadiusTopStart == f9 && this.boxCornerRadiusTopEnd == f10 && this.boxCornerRadiusBottomEnd == f12 && this.boxCornerRadiusBottomStart == f11) {
            return;
        }
        this.boxCornerRadiusTopStart = f9;
        this.boxCornerRadiusTopEnd = f10;
        this.boxCornerRadiusBottomEnd = f12;
        this.boxCornerRadiusBottomStart = f11;
        applyBoxAttributes();
    }

    public void setBoxCornerRadiiResources(int i9, int i10, int i11, int i12) {
        setBoxCornerRadii(getContext().getResources().getDimension(i9), getContext().getResources().getDimension(i10), getContext().getResources().getDimension(i11), getContext().getResources().getDimension(i12));
    }

    public void setBoxStrokeColor(int i9) {
        if (this.focusedStrokeColor != i9) {
            this.focusedStrokeColor = i9;
            updateTextInputBoxState();
        }
    }

    public void setCounterEnabled(boolean z8) {
        if (this.counterEnabled != z8) {
            if (z8) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.counterView = appCompatTextView;
                appCompatTextView.setId(C3476R.id.textinput_counter);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.counterView.setTypeface(typeface);
                }
                this.counterView.setMaxLines(1);
                setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterTextAppearance);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                EditText editText = this.editText;
                if (editText == null) {
                    updateCounter(0);
                } else {
                    updateCounter(editText.getText().length());
                }
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z8;
        }
    }

    public void setCounterMaxLength(int i9) {
        if (this.counterMaxLength != i9) {
            if (i9 > 0) {
                this.counterMaxLength = i9;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                EditText editText = this.editText;
                updateCounter(editText == null ? 0 : editText.getText().length());
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.defaultHintTextColor = colorStateList;
        this.focusedTextColor = colorStateList;
        if (this.editText != null) {
            updateLabelState(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z8) {
        recursiveSetEnabled(this, z8);
        super.setEnabled(z8);
    }

    public void setError(CharSequence charSequence) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.indicatorViewController.hideError();
        } else {
            this.indicatorViewController.showError(charSequence);
        }
    }

    public void setErrorEnabled(boolean z8) {
        this.indicatorViewController.setErrorEnabled(z8);
    }

    public void setErrorTextAppearance(int i9) {
        this.indicatorViewController.setErrorTextAppearance(i9);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.indicatorViewController.setErrorViewTextColor(colorStateList);
    }

    public void setHelperText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (isHelperTextEnabled()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(charSequence);
        }
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.indicatorViewController.setHelperTextViewTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z8) {
        this.indicatorViewController.setHelperTextEnabled(z8);
    }

    public void setHelperTextTextAppearance(int i9) {
        this.indicatorViewController.setHelperTextAppearance(i9);
    }

    public void setHint(CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z8) {
        this.hintAnimationEnabled = z8;
    }

    public void setHintEnabled(boolean z8) {
        if (z8 != this.hintEnabled) {
            this.hintEnabled = z8;
            if (z8) {
                CharSequence hint = this.editText.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(hint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            } else {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public void setHintTextAppearance(int i9) {
        this.collapsingTextHelper.setCollapsedTextAppearance(i9);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setPasswordVisibilityToggleContentDescription(int i9) {
        setPasswordVisibilityToggleContentDescription(i9 != 0 ? getResources().getText(i9) : null);
    }

    public void setPasswordVisibilityToggleDrawable(int i9) {
        setPasswordVisibilityToggleDrawable(i9 != 0 ? C0694a.m3458b(getContext(), i9) : null);
    }

    public void setPasswordVisibilityToggleEnabled(boolean z8) {
        EditText editText;
        if (this.passwordToggleEnabled != z8) {
            this.passwordToggleEnabled = z8;
            if (!z8 && this.passwordToggledVisible && (editText = this.editText) != null) {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            this.passwordToggledVisible = false;
            updatePasswordToggleView();
        }
    }

    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        this.passwordToggleTintList = colorStateList;
        this.hasPasswordToggleTintList = true;
        applyPasswordToggleTint();
    }

    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.passwordToggleTintMode = mode;
        this.hasPasswordToggleTintMode = true;
        applyPasswordToggleTint();
    }

    public void setTextAppearanceCompatWithErrorFallback(TextView textView, int i9) {
        boolean z8 = true;
        try {
            C0337l.m1620o(textView, i9);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                z8 = false;
            }
        } catch (Exception unused) {
        }
        if (z8) {
            C0337l.m1620o(textView, C3476R.style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(C6273a.m24024c(getContext(), C3476R.color.design_error));
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        EditText editText = this.editText;
        if (editText != null) {
            C4647u.m18530Z(editText, accessibilityDelegate);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            this.collapsingTextHelper.setTypefaces(typeface);
            this.indicatorViewController.setTypefaces(typeface);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public void updateCounter(int i9) {
        boolean z8 = this.counterOverflowed;
        if (this.counterMaxLength == -1) {
            this.counterView.setText(String.valueOf(i9));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            if (C4647u.m18547i(this.counterView) == 1) {
                C4647u.m18532a0(this.counterView, 0);
            }
            boolean z9 = i9 > this.counterMaxLength;
            this.counterOverflowed = z9;
            if (z8 != z9) {
                setTextAppearanceCompatWithErrorFallback(this.counterView, z9 ? this.counterOverflowTextAppearance : this.counterTextAppearance);
                if (this.counterOverflowed) {
                    C4647u.m18532a0(this.counterView, 1);
                }
            }
            this.counterView.setText(getContext().getString(C3476R.string.character_counter_pattern, Integer.valueOf(i9), Integer.valueOf(this.counterMaxLength)));
            this.counterView.setContentDescription(getContext().getString(C3476R.string.character_counter_content_description, Integer.valueOf(i9), Integer.valueOf(this.counterMaxLength)));
        }
        if (this.editText == null || z8 == this.counterOverflowed) {
            return;
        }
        updateLabelState(false);
        updateTextInputBoxState();
        updateEditTextBackground();
    }

    public void updateEditTextBackground() {
        Drawable background;
        TextView textView;
        EditText editText = this.editText;
        if (editText == null || (background = editText.getBackground()) == null) {
            return;
        }
        ensureBackgroundDrawableStateWorkaround();
        if (C0262x.m1073a(background)) {
            background = background.mutate();
        }
        if (this.indicatorViewController.errorShouldBeShown()) {
            background.setColorFilter(C0227f.m820e(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            background.setColorFilter(C0227f.m820e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            C6494a.m24838a(background);
            this.editText.refreshDrawableState();
        }
    }

    public void updateLabelState(boolean z8) {
        updateLabelState(z8, false);
    }

    public void updateTextInputBoxState() {
        TextView textView;
        if (this.boxBackground == null || this.boxBackgroundMode == 0) {
            return;
        }
        EditText editText = this.editText;
        boolean z8 = editText != null && editText.hasFocus();
        EditText editText2 = this.editText;
        boolean z9 = editText2 != null && editText2.isHovered();
        if (this.boxBackgroundMode == 2) {
            if (!isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            } else if (this.indicatorViewController.errorShouldBeShown()) {
                this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
            } else if (this.counterOverflowed && (textView = this.counterView) != null) {
                this.boxStrokeColor = textView.getCurrentTextColor();
            } else if (z8) {
                this.boxStrokeColor = this.focusedStrokeColor;
            } else if (z9) {
                this.boxStrokeColor = this.hoveredStrokeColor;
            } else {
                this.boxStrokeColor = this.defaultStrokeColor;
            }
            if ((z9 || z8) && isEnabled()) {
                this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
            } else {
                this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
            }
            applyBoxAttributes();
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.textInputStyle);
    }

    private void updateLabelState(boolean z8, boolean z9) {
        ColorStateList colorStateList;
        TextView textView;
        boolean zIsEnabled = isEnabled();
        EditText editText = this.editText;
        boolean z10 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.editText;
        boolean z11 = editText2 != null && editText2.hasFocus();
        boolean zErrorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!zIsEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
        } else if (zErrorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
        } else if (z11 && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (z10 || (isEnabled() && (z11 || zErrorShouldBeShown))) {
            if (z9 || this.hintExpanded) {
                collapseHint(z8);
                return;
            }
            return;
        }
        if (z9 || !this.hintExpanded) {
            expandHint(z8);
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i9) throws Resources.NotFoundException {
        super(context, attributeSet, i9);
        this.indicatorViewController = new IndicatorViewController(this);
        this.tmpRect = new Rect();
        this.tmpRectF = new RectF();
        CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper;
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout frameLayout = new FrameLayout(context);
        this.inputFrame = frameLayout;
        frameLayout.setAddStatesFromChildren(true);
        addView(frameLayout);
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        collapsingTextHelper.setTextSizeInterpolator(timeInterpolator);
        collapsingTextHelper.setPositionInterpolator(timeInterpolator);
        collapsingTextHelper.setCollapsedTextGravity(8388659);
        C0250q0 c0250q0ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeSet, C3476R.styleable.TextInputLayout, i9, C3476R.style.Widget_Design_TextInputLayout, new int[0]);
        this.hintEnabled = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_hintEnabled, true);
        setHint(c0250q0ObtainTintedStyledAttributes.m1020p(C3476R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.boxBottomOffsetPx = context.getResources().getDimensionPixelOffset(C3476R.dimen.mtrl_textinput_box_bottom_offset);
        this.boxLabelCutoutPaddingPx = context.getResources().getDimensionPixelOffset(C3476R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = c0250q0ObtainTintedStyledAttributes.m1009e(C3476R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.boxCornerRadiusTopStart = c0250q0ObtainTintedStyledAttributes.m1008d(C3476R.styleable.TextInputLayout_boxCornerRadiusTopStart, BitmapDescriptorFactory.HUE_RED);
        this.boxCornerRadiusTopEnd = c0250q0ObtainTintedStyledAttributes.m1008d(C3476R.styleable.TextInputLayout_boxCornerRadiusTopEnd, BitmapDescriptorFactory.HUE_RED);
        this.boxCornerRadiusBottomEnd = c0250q0ObtainTintedStyledAttributes.m1008d(C3476R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, BitmapDescriptorFactory.HUE_RED);
        this.boxCornerRadiusBottomStart = c0250q0ObtainTintedStyledAttributes.m1008d(C3476R.styleable.TextInputLayout_boxCornerRadiusBottomStart, BitmapDescriptorFactory.HUE_RED);
        this.boxBackgroundColor = c0250q0ObtainTintedStyledAttributes.m1006b(C3476R.styleable.TextInputLayout_boxBackgroundColor, 0);
        this.focusedStrokeColor = c0250q0ObtainTintedStyledAttributes.m1006b(C3476R.styleable.TextInputLayout_boxStrokeColor, 0);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C3476R.dimen.mtrl_textinput_box_stroke_width_default);
        this.boxStrokeWidthDefaultPx = dimensionPixelSize;
        this.boxStrokeWidthFocusedPx = context.getResources().getDimensionPixelSize(C3476R.dimen.mtrl_textinput_box_stroke_width_focused);
        this.boxStrokeWidthPx = dimensionPixelSize;
        setBoxBackgroundMode(c0250q0ObtainTintedStyledAttributes.m1015k(C3476R.styleable.TextInputLayout_boxBackgroundMode, 0));
        int i10 = C3476R.styleable.TextInputLayout_android_textColorHint;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i10)) {
            ColorStateList colorStateListM1007c = c0250q0ObtainTintedStyledAttributes.m1007c(i10);
            this.focusedTextColor = colorStateListM1007c;
            this.defaultHintTextColor = colorStateListM1007c;
        }
        this.defaultStrokeColor = C6273a.m24024c(context, C3476R.color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = C6273a.m24024c(context, C3476R.color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = C6273a.m24024c(context, C3476R.color.mtrl_textinput_hovered_box_stroke_color);
        int i11 = C3476R.styleable.TextInputLayout_hintTextAppearance;
        if (c0250q0ObtainTintedStyledAttributes.m1018n(i11, -1) != -1) {
            setHintTextAppearance(c0250q0ObtainTintedStyledAttributes.m1018n(i11, 0));
        }
        int iM1018n = c0250q0ObtainTintedStyledAttributes.m1018n(C3476R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean zM1005a = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_errorEnabled, false);
        int iM1018n2 = c0250q0ObtainTintedStyledAttributes.m1018n(C3476R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean zM1005a2 = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequenceM1020p = c0250q0ObtainTintedStyledAttributes.m1020p(C3476R.styleable.TextInputLayout_helperText);
        boolean zM1005a3 = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(c0250q0ObtainTintedStyledAttributes.m1015k(C3476R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = c0250q0ObtainTintedStyledAttributes.m1018n(C3476R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = c0250q0ObtainTintedStyledAttributes.m1018n(C3476R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.passwordToggleEnabled = c0250q0ObtainTintedStyledAttributes.m1005a(C3476R.styleable.TextInputLayout_passwordToggleEnabled, false);
        this.passwordToggleDrawable = c0250q0ObtainTintedStyledAttributes.m1011g(C3476R.styleable.TextInputLayout_passwordToggleDrawable);
        this.passwordToggleContentDesc = c0250q0ObtainTintedStyledAttributes.m1020p(C3476R.styleable.TextInputLayout_passwordToggleContentDescription);
        int i12 = C3476R.styleable.TextInputLayout_passwordToggleTint;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i12)) {
            this.hasPasswordToggleTintList = true;
            this.passwordToggleTintList = c0250q0ObtainTintedStyledAttributes.m1007c(i12);
        }
        int i13 = C3476R.styleable.TextInputLayout_passwordToggleTintMode;
        if (c0250q0ObtainTintedStyledAttributes.m1023s(i13)) {
            this.hasPasswordToggleTintMode = true;
            this.passwordToggleTintMode = ViewUtils.parseTintMode(c0250q0ObtainTintedStyledAttributes.m1015k(i13, -1), null);
        }
        c0250q0ObtainTintedStyledAttributes.m1024w();
        setHelperTextEnabled(zM1005a2);
        setHelperText(charSequenceM1020p);
        setHelperTextTextAppearance(iM1018n2);
        setErrorEnabled(zM1005a);
        setErrorTextAppearance(iM1018n);
        setCounterEnabled(zM1005a3);
        applyPasswordToggleTint();
        C4647u.m18548i0(this, 2);
    }

    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.passwordToggleContentDesc = charSequence;
        CheckableImageButton checkableImageButton = this.passwordToggleView;
        if (checkableImageButton != null) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.passwordToggleDrawable = drawable;
        CheckableImageButton checkableImageButton = this.passwordToggleView;
        if (checkableImageButton != null) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }
}
