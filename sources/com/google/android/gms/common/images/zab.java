package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zaj;

/* loaded from: classes2.dex */
public abstract class zab {
    final zaa zaa;
    protected int zab;
    private int zac = 0;
    private boolean zad = false;
    private boolean zae = true;
    private boolean zaf = false;
    private boolean zag = true;

    public zab(Uri uri, int i9) {
        this.zab = 0;
        this.zaa = new zaa(uri);
        this.zab = i9;
    }

    public final void zaa(Context context, Bitmap bitmap, boolean z8) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z8, false, true);
    }

    public abstract void zaa(Drawable drawable, boolean z8, boolean z9, boolean z10);

    public final void zaa(Context context, zaj zajVar) {
        if (this.zag) {
            zaa(null, false, true, false);
        }
    }

    public final void zaa(Context context, zaj zajVar, boolean z8) {
        int i9 = this.zab;
        zaa(i9 != 0 ? context.getResources().getDrawable(i9) : null, z8, false, false);
    }

    public final boolean zaa(boolean z8, boolean z9) {
        return (!this.zae || z9 || z8) ? false : true;
    }
}
