package com.google.android.gms.common.images;

import androidx.annotation.RecentlyNonNull;

/* loaded from: classes2.dex */
public final class Size {
    private final int zaa;
    private final int zab;

    public Size(@RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        this.zaa = i9;
        this.zab = i10;
    }

    @RecentlyNonNull
    public static Size parseSize(@RecentlyNonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            throw zaa(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            throw zaa(str);
        }
    }

    private static NumberFormatException zaa(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 16);
        sb.append("Invalid Size: \"");
        sb.append(str);
        sb.append("\"");
        throw new NumberFormatException(sb.toString());
    }

    @RecentlyNonNull
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zaa == size.zaa && this.zab == size.zab) {
                return true;
            }
        }
        return false;
    }

    @RecentlyNonNull
    public final int getHeight() {
        return this.zab;
    }

    @RecentlyNonNull
    public final int getWidth() {
        return this.zaa;
    }

    @RecentlyNonNull
    public final int hashCode() {
        int i9 = this.zab;
        int i10 = this.zaa;
        return i9 ^ ((i10 >>> 16) | (i10 << 16));
    }

    @RecentlyNonNull
    public final String toString() {
        int i9 = this.zaa;
        int i10 = this.zab;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i9);
        sb.append("x");
        sb.append(i10);
        return sb.toString();
    }
}
