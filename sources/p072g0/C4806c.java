package p072g0;

import android.content.Context;
import android.net.Uri;

/* renamed from: g0.c */
/* loaded from: classes.dex */
public class C4806c extends AbstractC4804a {

    /* renamed from: b */
    public Context f16724b;

    /* renamed from: c */
    public Uri f16725c;

    public C4806c(AbstractC4804a abstractC4804a, Context context, Uri uri) {
        super(abstractC4804a);
        this.f16724b = context;
        this.f16725c = uri;
    }

    @Override // p072g0.AbstractC4804a
    /* renamed from: b */
    public long mo19073b() {
        return C4805b.m19075b(this.f16724b, this.f16725c);
    }
}
