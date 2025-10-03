package com.mobeta.android.dslv;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.mobeta.android.dslv.DragSortListView;
import java.util.ArrayList;
import p062f0.AbstractC4774a;

/* renamed from: com.mobeta.android.dslv.a */
/* loaded from: classes2.dex */
public abstract class AbstractC4499a extends AbstractC4774a implements DragSortListView.InterfaceC4490i {

    /* renamed from: k */
    public SparseIntArray f15915k;

    /* renamed from: l */
    public ArrayList<Integer> f15916l;

    public AbstractC4499a(Context context, Cursor cursor, int i9) {
        super(context, cursor, i9);
        this.f15915k = new SparseIntArray();
        this.f15916l = new ArrayList<>();
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4487f
    /* renamed from: a */
    public void mo18068a(int i9, int i10) {
    }

    @Override // p062f0.AbstractC4774a, p062f0.C4775b.a
    /* renamed from: b */
    public void mo916b(Cursor cursor) {
        super.mo916b(cursor);
        m18093o();
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4493l
    /* renamed from: c */
    public void mo18081c(int i9, int i10) {
        if (i9 != i10) {
            int i11 = this.f15915k.get(i9, i9);
            if (i9 > i10) {
                while (i9 > i10) {
                    SparseIntArray sparseIntArray = this.f15915k;
                    int i12 = i9 - 1;
                    sparseIntArray.put(i9, sparseIntArray.get(i12, i12));
                    i9--;
                }
            } else {
                while (i9 < i10) {
                    SparseIntArray sparseIntArray2 = this.f15915k;
                    int i13 = i9 + 1;
                    sparseIntArray2.put(i9, sparseIntArray2.get(i13, i13));
                    i9 = i13;
                }
            }
            this.f15915k.put(i10, i11);
            m18090l();
            notifyDataSetChanged();
        }
    }

    @Override // p062f0.AbstractC4774a, android.widget.Adapter
    public int getCount() {
        return super.getCount() - this.f15916l.size();
    }

    @Override // p062f0.AbstractC4774a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i9, View view, ViewGroup viewGroup) {
        return super.getDropDownView(this.f15915k.get(i9, i9), view, viewGroup);
    }

    @Override // p062f0.AbstractC4774a, android.widget.Adapter
    public Object getItem(int i9) {
        return super.getItem(this.f15915k.get(i9, i9));
    }

    @Override // p062f0.AbstractC4774a, android.widget.Adapter
    public long getItemId(int i9) {
        return super.getItemId(this.f15915k.get(i9, i9));
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: k */
    public Cursor mo3441k(Cursor cursor) {
        Cursor cursorMo3441k = super.mo3441k(cursor);
        m18093o();
        return cursorMo3441k;
    }

    /* renamed from: l */
    public final void m18090l() {
        ArrayList arrayList = new ArrayList();
        int size = this.f15915k.size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.f15915k.keyAt(i9) == this.f15915k.valueAt(i9)) {
                arrayList.add(Integer.valueOf(this.f15915k.keyAt(i9)));
            }
        }
        int size2 = arrayList.size();
        for (int i10 = 0; i10 < size2; i10++) {
            this.f15915k.delete(((Integer) arrayList.get(i10)).intValue());
        }
    }

    /* renamed from: m */
    public int m18091m(int i9) {
        return this.f15915k.get(i9, i9);
    }

    /* renamed from: n */
    public ArrayList<Integer> m18092n() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i9 = 0; i9 < getCount(); i9++) {
            arrayList.add(Integer.valueOf(this.f15915k.get(i9, i9)));
        }
        return arrayList;
    }

    /* renamed from: o */
    public final void m18093o() {
        this.f15915k.clear();
        this.f15916l.clear();
    }

    @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4497p
    public void remove(int i9) {
        int i10 = this.f15915k.get(i9, i9);
        if (!this.f15916l.contains(Integer.valueOf(i10))) {
            this.f15916l.add(Integer.valueOf(i10));
        }
        int count = getCount();
        while (i9 < count) {
            SparseIntArray sparseIntArray = this.f15915k;
            int i11 = i9 + 1;
            sparseIntArray.put(i9, sparseIntArray.get(i11, i11));
            i9 = i11;
        }
        this.f15915k.delete(count);
        m18090l();
        notifyDataSetChanged();
    }
}
