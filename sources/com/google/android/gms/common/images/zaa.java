package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes2.dex */
final class zaa {
    public final Uri zaa;

    public zaa(Uri uri) {
        this.zaa = uri;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zaa) {
            return Objects.equal(((zaa) obj).zaa, this.zaa);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zaa);
    }
}
