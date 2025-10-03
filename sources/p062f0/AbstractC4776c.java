package p062f0;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: f0.c */
/* loaded from: classes.dex */
public abstract class AbstractC4776c extends AbstractC4774a {

    /* renamed from: k */
    public int f16616k;

    /* renamed from: l */
    public int f16617l;

    /* renamed from: m */
    public LayoutInflater f16618m;

    @Deprecated
    public AbstractC4776c(Context context, int i9, Cursor cursor, boolean z8) {
        super(context, cursor, z8);
        this.f16617l = i9;
        this.f16616k = i9;
        this.f16618m = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: h */
    public View mo3440h(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f16618m.inflate(this.f16617l, viewGroup, false);
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: i */
    public View mo919i(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f16618m.inflate(this.f16616k, viewGroup, false);
    }
}
