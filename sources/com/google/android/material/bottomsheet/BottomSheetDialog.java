package com.google.android.material.bottomsheet;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.DialogC0123e;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.material.C3476R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;

/* loaded from: classes2.dex */
public class BottomSheetDialog extends DialogC0123e {
    private BottomSheetBehavior<FrameLayout> behavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    boolean cancelable;
    private boolean canceledOnTouchOutside;
    private boolean canceledOnTouchOutsideSet;

    public BottomSheetDialog(Context context) {
        this(context, 0);
    }

    private static int getThemeResId(Context context, int i9) {
        if (i9 != 0) {
            return i9;
        }
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(C3476R.attr.bottomSheetDialogTheme, typedValue, true) ? typedValue.resourceId : C3476R.style.Theme_Design_Light_BottomSheetDialog;
    }

    private View wrapInBottomSheet(int i9, View view, ViewGroup.LayoutParams layoutParams) {
        FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), C3476R.layout.design_bottom_sheet_dialog, null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) frameLayout.findViewById(C3476R.id.coordinator);
        if (i9 != 0 && view == null) {
            view = getLayoutInflater().inflate(i9, (ViewGroup) coordinatorLayout, false);
        }
        FrameLayout frameLayout2 = (FrameLayout) coordinatorLayout.findViewById(C3476R.id.design_bottom_sheet);
        BottomSheetBehavior<FrameLayout> bottomSheetBehaviorFrom = BottomSheetBehavior.from(frameLayout2);
        this.behavior = bottomSheetBehaviorFrom;
        bottomSheetBehaviorFrom.setBottomSheetCallback(this.bottomSheetCallback);
        this.behavior.setHideable(this.cancelable);
        if (layoutParams == null) {
            frameLayout2.addView(view);
        } else {
            frameLayout2.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(C3476R.id.touch_outside).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                if (bottomSheetDialog.cancelable && bottomSheetDialog.isShowing() && BottomSheetDialog.this.shouldWindowCloseOnTouchOutside()) {
                    BottomSheetDialog.this.cancel();
                }
            }
        });
        C4647u.m18530Z(frameLayout2, new C4613a() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.2
            @Override // p042d0.C4613a
            public void onInitializeAccessibilityNodeInfo(View view2, C4704m c4704m) {
                super.onInitializeAccessibilityNodeInfo(view2, c4704m);
                if (!BottomSheetDialog.this.cancelable) {
                    c4704m.m18788a0(false);
                } else {
                    c4704m.m18787a(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES);
                    c4704m.m18788a0(true);
                }
            }

            @Override // p042d0.C4613a
            public boolean performAccessibilityAction(View view2, int i10, Bundle bundle) {
                if (i10 == 1048576) {
                    BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                    if (bottomSheetDialog.cancelable) {
                        bottomSheetDialog.cancel();
                        return true;
                    }
                }
                return super.performAccessibilityAction(view2, i10, bundle);
            }
        });
        frameLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                return true;
            }
        });
        return frameLayout;
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() != 5) {
            return;
        }
        this.behavior.setState(4);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z8) {
        super.setCancelable(z8);
        if (this.cancelable != z8) {
            this.cancelable = z8;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.behavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z8);
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z8) {
        super.setCanceledOnTouchOutside(z8);
        if (z8 && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z8;
        this.canceledOnTouchOutsideSet = true;
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void setContentView(int i9) {
        super.setContentView(wrapInBottomSheet(i9, null, null));
    }

    public boolean shouldWindowCloseOnTouchOutside() {
        if (!this.canceledOnTouchOutsideSet) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R.attr.windowCloseOnTouchOutside});
            this.canceledOnTouchOutside = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    public BottomSheetDialog(Context context, int i9) {
        super(context, getThemeResId(context, i9));
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.4
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View view, float f9) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View view, int i10) {
                if (i10 == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        supportRequestWindowFeature(1);
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    @Override // androidx.appcompat.app.DialogC0123e, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }

    public BottomSheetDialog(Context context, boolean z8, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z8, onCancelListener);
        this.cancelable = true;
        this.canceledOnTouchOutside = true;
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.4
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View view, float f9) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View view, int i10) {
                if (i10 == 5) {
                    BottomSheetDialog.this.cancel();
                }
            }
        };
        supportRequestWindowFeature(1);
        this.cancelable = z8;
    }
}
