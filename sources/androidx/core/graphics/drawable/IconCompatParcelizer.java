package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1782a = versionedParcel.m3156p(iconCompat.f1782a, 1);
        iconCompat.f1784c = versionedParcel.m3150j(iconCompat.f1784c, 2);
        iconCompat.f1785d = versionedParcel.m3158r(iconCompat.f1785d, 3);
        iconCompat.f1786e = versionedParcel.m3156p(iconCompat.f1786e, 4);
        iconCompat.f1787f = versionedParcel.m3156p(iconCompat.f1787f, 5);
        iconCompat.f1788g = (ColorStateList) versionedParcel.m3158r(iconCompat.f1788g, 6);
        iconCompat.f1790i = versionedParcel.m3160t(iconCompat.f1790i, 7);
        iconCompat.m1512k();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        versionedParcel.m3164x(true, true);
        iconCompat.m1513l(versionedParcel.m3146f());
        int i9 = iconCompat.f1782a;
        if (-1 != i9) {
            versionedParcel.m3132F(i9, 1);
        }
        byte[] bArr = iconCompat.f1784c;
        if (bArr != null) {
            versionedParcel.m3128B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1785d;
        if (parcelable != null) {
            versionedParcel.m3134H(parcelable, 3);
        }
        int i10 = iconCompat.f1786e;
        if (i10 != 0) {
            versionedParcel.m3132F(i10, 4);
        }
        int i11 = iconCompat.f1787f;
        if (i11 != 0) {
            versionedParcel.m3132F(i11, 5);
        }
        ColorStateList colorStateList = iconCompat.f1788g;
        if (colorStateList != null) {
            versionedParcel.m3134H(colorStateList, 6);
        }
        String str = iconCompat.f1790i;
        if (str != null) {
            versionedParcel.m3136J(str, 7);
        }
    }
}
