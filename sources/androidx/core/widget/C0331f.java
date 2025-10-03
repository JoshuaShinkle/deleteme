package androidx.core.widget;

import android.widget.ListView;

/* renamed from: androidx.core.widget.f */
/* loaded from: classes.dex */
public class C0331f extends AbstractViewOnTouchListenerC0326a {

    /* renamed from: t */
    public final ListView f1851t;

    public C0331f(ListView listView) {
        super(listView);
        this.f1851t = listView;
    }

    @Override // androidx.core.widget.AbstractViewOnTouchListenerC0326a
    /* renamed from: a */
    public boolean mo1557a(int i9) {
        return false;
    }

    @Override // androidx.core.widget.AbstractViewOnTouchListenerC0326a
    /* renamed from: b */
    public boolean mo1558b(int i9) {
        ListView listView = this.f1851t;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i10 = firstVisiblePosition + childCount;
        if (i9 > 0) {
            if (i10 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i9 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.widget.AbstractViewOnTouchListenerC0326a
    /* renamed from: j */
    public void mo1564j(int i9, int i10) {
        C0332g.m1599b(this.f1851t, i10);
    }
}
