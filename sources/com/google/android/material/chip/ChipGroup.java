package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.google.android.material.C3476R;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes2.dex */
public class ChipGroup extends FlowLayout {
    private int checkedId;
    private final CheckedStateTracker checkedStateTracker;
    private int chipSpacingHorizontal;
    private int chipSpacingVertical;
    private OnCheckedChangeListener onCheckedChangeListener;
    private PassThroughHierarchyChangeListener passThroughListener;
    private boolean protectFromCheckedChange;
    private boolean singleSelection;

    public class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
            if (ChipGroup.this.protectFromCheckedChange) {
                return;
            }
            int id = compoundButton.getId();
            if (!z8) {
                if (ChipGroup.this.checkedId == id) {
                    ChipGroup.this.setCheckedId(-1);
                }
            } else {
                if (ChipGroup.this.checkedId != -1 && ChipGroup.this.checkedId != id && ChipGroup.this.singleSelection) {
                    ChipGroup chipGroup = ChipGroup.this;
                    chipGroup.setCheckedStateForView(chipGroup.checkedId, false);
                }
                ChipGroup.this.setCheckedId(id);
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(int i9, int i10) {
            super(i9, i10);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(ChipGroup chipGroup, int i9);
    }

    public class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    view2.setId(View.generateViewId());
                }
                ((Chip) view2).setOnCheckedChangeListenerInternal(ChipGroup.this.checkedStateTracker);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                ((Chip) view2).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i9) {
        this.checkedId = i9;
        OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
        if (onCheckedChangeListener == null || !this.singleSelection) {
            return;
        }
        onCheckedChangeListener.onCheckedChanged(this, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int i9, boolean z8) {
        View viewFindViewById = findViewById(i9);
        if (viewFindViewById instanceof Chip) {
            this.protectFromCheckedChange = true;
            ((Chip) viewFindViewById).setChecked(z8);
            this.protectFromCheckedChange = false;
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i9, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i10 = this.checkedId;
                if (i10 != -1 && this.singleSelection) {
                    setCheckedStateForView(i10, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i9, layoutParams);
    }

    public void check(int i9) {
        int i10 = this.checkedId;
        if (i9 == i10) {
            return;
        }
        if (i10 != -1 && this.singleSelection) {
            setCheckedStateForView(i10, false);
        }
        if (i9 != -1) {
            setCheckedStateForView(i9, true);
        }
        setCheckedId(i9);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public void clearCheck() {
        this.protectFromCheckedChange = true;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt instanceof Chip) {
                ((Chip) childAt).setChecked(false);
            }
        }
        this.protectFromCheckedChange = false;
        setCheckedId(-1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public int getChipSpacingHorizontal() {
        return this.chipSpacingHorizontal;
    }

    public int getChipSpacingVertical() {
        return this.chipSpacingVertical;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i9 = this.checkedId;
        if (i9 != -1) {
            setCheckedStateForView(i9, true);
            setCheckedId(this.checkedId);
        }
    }

    public void setChipSpacing(int i9) {
        setChipSpacingHorizontal(i9);
        setChipSpacingVertical(i9);
    }

    public void setChipSpacingHorizontal(int i9) {
        if (this.chipSpacingHorizontal != i9) {
            this.chipSpacingHorizontal = i9;
            setItemSpacing(i9);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i9) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i9));
    }

    public void setChipSpacingResource(int i9) {
        setChipSpacing(getResources().getDimensionPixelOffset(i9));
    }

    public void setChipSpacingVertical(int i9) {
        if (this.chipSpacingVertical != i9) {
            this.chipSpacingVertical = i9;
            setLineSpacing(i9);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i9) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i9));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i9) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.passThroughListener.onHierarchyChangeListener = onHierarchyChangeListener;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i9) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i9) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    public void setSingleLine(int i9) {
        setSingleLine(getResources().getBoolean(i9));
    }

    public void setSingleSelection(boolean z8) {
        if (this.singleSelection != z8) {
            this.singleSelection = z8;
            clearCheck();
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3476R.attr.chipGroupStyle);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ChipGroup(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.checkedStateTracker = new CheckedStateTracker();
        this.passThroughListener = new PassThroughHierarchyChangeListener();
        this.checkedId = -1;
        this.protectFromCheckedChange = false;
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.ChipGroup, i9, C3476R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayObtainStyledAttributes.getDimensionPixelOffset(C3476R.styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayObtainStyledAttributes.getBoolean(C3476R.styleable.ChipGroup_singleSelection, false));
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(C3476R.styleable.ChipGroup_checkedChip, -1);
        if (resourceId != -1) {
            this.checkedId = resourceId;
        }
        typedArrayObtainStyledAttributes.recycle();
        super.setOnHierarchyChangeListener(this.passThroughListener);
    }

    public void setSingleSelection(int i9) {
        setSingleSelection(getResources().getBoolean(i9));
    }
}
