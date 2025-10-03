package com.mobeta.android.dslv;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import com.mobeta.android.dslv.DragSortListView;

/* renamed from: com.mobeta.android.dslv.b */
/* loaded from: classes2.dex */
public class C4500b implements DragSortListView.InterfaceC4494m {

    /* renamed from: b */
    public Bitmap f15917b;

    /* renamed from: c */
    public ImageView f15918c;

    /* renamed from: d */
    public int f15919d = -16777216;

    /* renamed from: e */
    public ListView f15920e;

    public C4500b(ListView listView) {
        this.f15920e = listView;
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4494m
    /* renamed from: a */
    public void mo18082a(View view) {
        ((ImageView) view).setImageDrawable(null);
        this.f15917b.recycle();
        this.f15917b = null;
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4494m
    /* renamed from: b */
    public View mo18083b(int i9) {
        ListView listView = this.f15920e;
        View childAt = listView.getChildAt((i9 + listView.getHeaderViewsCount()) - this.f15920e.getFirstVisiblePosition());
        if (childAt == null) {
            return null;
        }
        childAt.setPressed(false);
        childAt.setDrawingCacheEnabled(true);
        this.f15917b = Bitmap.createBitmap(childAt.getDrawingCache());
        childAt.setDrawingCacheEnabled(false);
        if (this.f15918c == null) {
            this.f15918c = new ImageView(this.f15920e.getContext());
        }
        this.f15918c.setBackgroundColor(this.f15919d);
        this.f15918c.setPadding(0, 0, 0, 0);
        this.f15918c.setImageBitmap(this.f15917b);
        this.f15918c.setLayoutParams(new ViewGroup.LayoutParams(childAt.getWidth(), childAt.getHeight()));
        return this.f15918c;
    }

    /* renamed from: d */
    public void m18094d(int i9) {
        this.f15919d = i9;
    }
}
