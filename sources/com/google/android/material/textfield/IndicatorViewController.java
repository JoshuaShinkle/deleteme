package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.C0337l;
import androidx.legacy.widget.Space;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.C3476R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4647u;

/* loaded from: classes2.dex */
final class IndicatorViewController {
    private static final int CAPTION_OPACITY_FADE_ANIMATION_DURATION = 167;
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    private static final int CAPTION_TRANSLATE_Y_ANIMATION_DURATION = 217;
    static final int COUNTER_INDEX = 2;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private int captionToShow;
    private final float captionTranslationYPx;
    private int captionViewsAdded;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    private TextView errorView;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        this.context = textInputLayout.getContext();
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = r0.getResources().getDimensionPixelSize(C3476R.dimen.design_textinput_caption_translate_y);
    }

    private boolean canAdjustIndicatorPadding() {
        return (this.indicatorArea == null || this.textInputView.getEditText() == null) ? false : true;
    }

    private void createCaptionAnimators(List<Animator> list, boolean z8, TextView textView, int i9, int i10, int i11) {
        if (textView == null || !z8) {
            return;
        }
        if (i9 == i11 || i9 == i10) {
            list.add(createCaptionOpacityAnimator(textView, i11 == i9));
            if (i11 == i9) {
                list.add(createCaptionTranslationYAnimator(textView));
            }
        }
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView textView, boolean z8) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z8 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.captionTranslationYPx, BitmapDescriptorFactory.HUE_RED);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    private TextView getCaptionViewFromDisplayState(int i9) {
        if (i9 == 1) {
            return this.errorView;
        }
        if (i9 != 2) {
            return null;
        }
        return this.helperTextView;
    }

    private boolean isCaptionStateError(int i9) {
        return (i9 != 1 || this.errorView == null || TextUtils.isEmpty(this.errorText)) ? false : true;
    }

    private boolean isCaptionStateHelperText(int i9) {
        return (i9 != 2 || this.helperTextView == null || TextUtils.isEmpty(this.helperText)) ? false : true;
    }

    private void setCaptionViewVisibilities(int i9, int i10) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i9 == i10) {
            return;
        }
        if (i10 != 0 && (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i10)) != null) {
            captionViewFromDisplayState2.setVisibility(0);
            captionViewFromDisplayState2.setAlpha(1.0f);
        }
        if (i9 != 0 && (captionViewFromDisplayState = getCaptionViewFromDisplayState(i9)) != null) {
            captionViewFromDisplayState.setVisibility(4);
            if (i9 == 1) {
                captionViewFromDisplayState.setText((CharSequence) null);
            }
        }
        this.captionDisplayed = i10;
    }

    private void setTextViewTypeface(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void setViewGroupGoneIfEmpty(ViewGroup viewGroup, int i9) {
        if (i9 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean shouldAnimateCaptionView(TextView textView, CharSequence charSequence) {
        return C4647u.m18513I(this.textInputView) && this.textInputView.isEnabled() && !(this.captionToShow == this.captionDisplayed && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void updateCaptionViewsVisibility(final int i9, final int i10, boolean z8) {
        if (z8) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.captionAnimator = animatorSet;
            ArrayList arrayList = new ArrayList();
            createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, i9, i10);
            createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, i9, i10);
            AnimatorSetCompat.playTogether(animatorSet, arrayList);
            final TextView captionViewFromDisplayState = getCaptionViewFromDisplayState(i9);
            final TextView captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i10);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.IndicatorViewController.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    IndicatorViewController.this.captionDisplayed = i10;
                    IndicatorViewController.this.captionAnimator = null;
                    TextView textView = captionViewFromDisplayState;
                    if (textView != null) {
                        textView.setVisibility(4);
                        if (i9 != 1 || IndicatorViewController.this.errorView == null) {
                            return;
                        }
                        IndicatorViewController.this.errorView.setText((CharSequence) null);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    TextView textView = captionViewFromDisplayState2;
                    if (textView != null) {
                        textView.setVisibility(0);
                    }
                }
            });
            animatorSet.start();
        } else {
            setCaptionViewVisibilities(i9, i10);
        }
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateLabelState(z8);
        this.textInputView.updateTextInputBoxState();
    }

    public void addIndicator(TextView textView, int i9) {
        if (this.indicatorArea == null && this.captionArea == null) {
            LinearLayout linearLayout = new LinearLayout(this.context);
            this.indicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            FrameLayout frameLayout = new FrameLayout(this.context);
            this.captionArea = frameLayout;
            this.indicatorArea.addView(frameLayout, -1, new FrameLayout.LayoutParams(-2, -2));
            this.indicatorArea.addView(new Space(this.context), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.textInputView.getEditText() != null) {
                adjustIndicatorPadding();
            }
        }
        if (isCaptionView(i9)) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView);
            this.captionViewsAdded++;
        } else {
            this.indicatorArea.addView(textView, i9);
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    public void adjustIndicatorPadding() {
        if (canAdjustIndicatorPadding()) {
            C4647u.m18556m0(this.indicatorArea, C4647u.m18571w(this.textInputView.getEditText()), 0, C4647u.m18570v(this.textInputView.getEditText()), 0);
        }
    }

    public void cancelCaptionAnimator() {
        Animator animator = this.captionAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    public boolean errorIsDisplayed() {
        return isCaptionStateError(this.captionDisplayed);
    }

    public boolean errorShouldBeShown() {
        return isCaptionStateError(this.captionToShow);
    }

    public CharSequence getErrorText() {
        return this.errorText;
    }

    public int getErrorViewCurrentTextColor() {
        TextView textView = this.errorView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList getErrorViewTextColors() {
        TextView textView = this.errorView;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence getHelperText() {
        return this.helperText;
    }

    public ColorStateList getHelperTextViewColors() {
        TextView textView = this.helperTextView;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public int getHelperTextViewCurrentTextColor() {
        TextView textView = this.helperTextView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public boolean helperTextIsDisplayed() {
        return isCaptionStateHelperText(this.captionDisplayed);
    }

    public boolean helperTextShouldBeShown() {
        return isCaptionStateHelperText(this.captionToShow);
    }

    public void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (!this.helperTextEnabled || TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 0;
            } else {
                this.captionToShow = 2;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, null));
    }

    public void hideHelperText() {
        cancelCaptionAnimator();
        int i9 = this.captionDisplayed;
        if (i9 == 2) {
            this.captionToShow = 0;
        }
        updateCaptionViewsVisibility(i9, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, null));
    }

    public boolean isCaptionView(int i9) {
        return i9 == 0 || i9 == 1;
    }

    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    public boolean isHelperTextEnabled() {
        return this.helperTextEnabled;
    }

    public void removeIndicator(TextView textView, int i9) {
        FrameLayout frameLayout;
        if (this.indicatorArea == null) {
            return;
        }
        if (!isCaptionView(i9) || (frameLayout = this.captionArea) == null) {
            this.indicatorArea.removeView(textView);
        } else {
            int i10 = this.captionViewsAdded - 1;
            this.captionViewsAdded = i10;
            setViewGroupGoneIfEmpty(frameLayout, i10);
            this.captionArea.removeView(textView);
        }
        int i11 = this.indicatorsAdded - 1;
        this.indicatorsAdded = i11;
        setViewGroupGoneIfEmpty(this.indicatorArea, i11);
    }

    public void setErrorEnabled(boolean z8) {
        if (this.errorEnabled == z8) {
            return;
        }
        cancelCaptionAnimator();
        if (z8) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
            this.errorView = appCompatTextView;
            appCompatTextView.setId(C3476R.id.textinput_error);
            Typeface typeface = this.typeface;
            if (typeface != null) {
                this.errorView.setTypeface(typeface);
            }
            setErrorTextAppearance(this.errorTextAppearance);
            this.errorView.setVisibility(4);
            C4647u.m18532a0(this.errorView, 1);
            addIndicator(this.errorView, 0);
        } else {
            hideError();
            removeIndicator(this.errorView, 0);
            this.errorView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.errorEnabled = z8;
    }

    public void setErrorTextAppearance(int i9) {
        this.errorTextAppearance = i9;
        TextView textView = this.errorView;
        if (textView != null) {
            this.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i9);
        }
    }

    public void setErrorViewTextColor(ColorStateList colorStateList) {
        TextView textView = this.errorView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setHelperTextAppearance(int i9) {
        this.helperTextTextAppearance = i9;
        TextView textView = this.helperTextView;
        if (textView != null) {
            C0337l.m1620o(textView, i9);
        }
    }

    public void setHelperTextEnabled(boolean z8) {
        if (this.helperTextEnabled == z8) {
            return;
        }
        cancelCaptionAnimator();
        if (z8) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
            this.helperTextView = appCompatTextView;
            appCompatTextView.setId(C3476R.id.textinput_helper_text);
            Typeface typeface = this.typeface;
            if (typeface != null) {
                this.helperTextView.setTypeface(typeface);
            }
            this.helperTextView.setVisibility(4);
            C4647u.m18532a0(this.helperTextView, 1);
            setHelperTextAppearance(this.helperTextTextAppearance);
            addIndicator(this.helperTextView, 1);
        } else {
            hideHelperText();
            removeIndicator(this.helperTextView, 1);
            this.helperTextView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.helperTextEnabled = z8;
    }

    public void setHelperTextViewTextColor(ColorStateList colorStateList) {
        TextView textView = this.helperTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTypefaces(Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            setTextViewTypeface(this.errorView, typeface);
            setTextViewTypeface(this.helperTextView, typeface);
        }
    }

    public void showError(CharSequence charSequence) {
        cancelCaptionAnimator();
        this.errorText = charSequence;
        this.errorView.setText(charSequence);
        int i9 = this.captionDisplayed;
        if (i9 != 1) {
            this.captionToShow = 1;
        }
        updateCaptionViewsVisibility(i9, this.captionToShow, shouldAnimateCaptionView(this.errorView, charSequence));
    }

    public void showHelper(CharSequence charSequence) {
        cancelCaptionAnimator();
        this.helperText = charSequence;
        this.helperTextView.setText(charSequence);
        int i9 = this.captionDisplayed;
        if (i9 != 2) {
            this.captionToShow = 2;
        }
        updateCaptionViewsVisibility(i9, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, charSequence));
    }
}
