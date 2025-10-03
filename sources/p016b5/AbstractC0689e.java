package p016b5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobeta.android.dslv.AbstractC4499a;

/* renamed from: b5.e */
/* loaded from: classes2.dex */
public abstract class AbstractC0689e extends AbstractC4499a {

    /* renamed from: m */
    public int f3335m;

    /* renamed from: n */
    public int f3336n;

    /* renamed from: o */
    public LayoutInflater f3337o;

    public AbstractC0689e(Context context, int i9, Cursor cursor, int i10) {
        super(context, cursor, i10);
        this.f3336n = i9;
        this.f3335m = i9;
        this.f3337o = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: h */
    public View mo3440h(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f3337o.inflate(this.f3336n, viewGroup, false);
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: i */
    public View mo919i(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f3337o.inflate(this.f3335m, viewGroup, false);
    }
}
