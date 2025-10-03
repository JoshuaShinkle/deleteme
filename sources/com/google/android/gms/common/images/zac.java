package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class zac extends zab {
    private WeakReference<ImageManager.OnImageLoadedListener> zac;

    public zac(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
        super(uri, 0);
        Asserts.checkNotNull(onImageLoadedListener);
        this.zac = new WeakReference<>(onImageLoadedListener);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zac)) {
            return false;
        }
        zac zacVar = (zac) obj;
        ImageManager.OnImageLoadedListener onImageLoadedListener = this.zac.get();
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = zacVar.zac.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && Objects.equal(onImageLoadedListener2, onImageLoadedListener) && Objects.equal(zacVar.zaa, this.zaa);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zaa);
    }

    @Override // com.google.android.gms.common.images.zab
    public final void zaa(Drawable drawable, boolean z8, boolean z9, boolean z10) {
        ImageManager.OnImageLoadedListener onImageLoadedListener;
        if (z9 || (onImageLoadedListener = this.zac.get()) == null) {
            return;
        }
        onImageLoadedListener.onImageLoaded(this.zaa.zaa, drawable, z10);
    }
}
