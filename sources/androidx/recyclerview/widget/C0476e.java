package androidx.recyclerview.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.e */
/* loaded from: classes.dex */
public class C0476e extends RecyclerView.AbstractC0453n {

    /* renamed from: d */
    public static final int[] f2646d = {R.attr.listDivider};

    /* renamed from: a */
    public Drawable f2647a;

    /* renamed from: b */
    public int f2648b;

    /* renamed from: c */
    public final Rect f2649c = new Rect();

    public C0476e(Context context, int i9) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f2646d);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f2647a = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArrayObtainStyledAttributes.recycle();
        m2810l(i9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0453n
    /* renamed from: e */
    public void mo2421e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.C0465z c0465z) {
        Drawable drawable = this.f2647a;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f2648b == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0453n
    /* renamed from: g */
    public void mo2423g(Canvas canvas, RecyclerView recyclerView, RecyclerView.C0465z c0465z) {
        if (recyclerView.getLayoutManager() == null || this.f2647a == null) {
            return;
        }
        if (this.f2648b == 1) {
            m2809k(canvas, recyclerView);
        } else {
            m2808j(canvas, recyclerView);
        }
    }

    /* renamed from: j */
    public final void m2808j(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int paddingTop;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = recyclerView.getChildAt(i9);
            recyclerView.getLayoutManager().m2457P(childAt, this.f2649c);
            int iRound = this.f2649c.right + Math.round(childAt.getTranslationX());
            this.f2647a.setBounds(iRound - this.f2647a.getIntrinsicWidth(), paddingTop, iRound, height);
            this.f2647a.draw(canvas);
        }
        canvas.restore();
    }

    /* renamed from: k */
    public final void m2809k(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int paddingLeft;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingLeft = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = recyclerView.getChildAt(i9);
            recyclerView.getDecoratedBoundsWithMargins(childAt, this.f2649c);
            int iRound = this.f2649c.bottom + Math.round(childAt.getTranslationY());
            this.f2647a.setBounds(paddingLeft, iRound - this.f2647a.getIntrinsicHeight(), width, iRound);
            this.f2647a.draw(canvas);
        }
        canvas.restore();
    }

    /* renamed from: l */
    public void m2810l(int i9) {
        if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.f2648b = i9;
    }
}
