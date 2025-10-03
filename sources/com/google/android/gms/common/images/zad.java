package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import java.lang.ref.WeakReference;
import net.sqlcipher.database.SQLiteDatabase;

/* loaded from: classes2.dex */
public final class zad extends zab {
    private WeakReference<ImageView> zac;

    public zad(ImageView imageView, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zad)) {
            return false;
        }
        ImageView imageView = this.zac.get();
        ImageView imageView2 = ((zad) obj).zac.get();
        return (imageView2 == null || imageView == null || !Objects.equal(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // com.google.android.gms.common.images.zab
    public final void zaa(Drawable drawable, boolean z8, boolean z9, boolean z10) {
        ImageView imageView = this.zac.get();
        if (imageView != null) {
            boolean z11 = (z9 || z10) ? false : true;
            if (z11 && (imageView instanceof zak)) {
                int iZaa = zak.zaa();
                int i9 = this.zab;
                if (i9 != 0 && iZaa == i9) {
                    return;
                }
            }
            boolean zZaa = zaa(z8, z9);
            if (zZaa) {
                Drawable drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof com.google.android.gms.internal.base.zae) {
                    drawable2 = ((com.google.android.gms.internal.base.zae) drawable2).zaa();
                }
                drawable = new com.google.android.gms.internal.base.zae(drawable2, drawable);
            }
            imageView.setImageDrawable(drawable);
            if (imageView instanceof zak) {
                zak.zaa(z10 ? this.zaa.zaa : Uri.EMPTY);
                zak.zaa(z11 ? this.zab : 0);
            }
            if (drawable == null || !zZaa) {
                return;
            }
            ((com.google.android.gms.internal.base.zae) drawable).zaa(SQLiteDatabase.MAX_SQL_CACHE_SIZE);
        }
    }

    public zad(ImageView imageView, int i9) {
        super(Uri.EMPTY, i9);
        Asserts.checkNotNull(imageView);
        this.zac = new WeakReference<>(imageView);
    }
}
