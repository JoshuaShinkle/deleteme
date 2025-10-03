package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "WebImageCreator")
/* loaded from: classes2.dex */
public final class WebImage extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();

    @SafeParcelable.VersionField(m17523id = 1)
    private final int zaa;

    @SafeParcelable.Field(getter = "getUrl", m17520id = 2)
    private final Uri zab;

    @SafeParcelable.Field(getter = "getWidth", m17520id = 3)
    private final int zac;

    @SafeParcelable.Field(getter = "getHeight", m17520id = 4)
    private final int zad;

    @SafeParcelable.Constructor
    public WebImage(@SafeParcelable.Param(m17521id = 1) int i9, @SafeParcelable.Param(m17521id = 2) Uri uri, @SafeParcelable.Param(m17521id = 3) int i10, @SafeParcelable.Param(m17521id = 4) int i11) {
        this.zaa = i9;
        this.zab = uri;
        this.zac = i10;
        this.zad = i11;
    }

    private static Uri zaa(JSONObject jSONObject) {
        Uri uri = Uri.EMPTY;
        if (!jSONObject.has("url")) {
            return uri;
        }
        try {
            return Uri.parse(jSONObject.getString("url"));
        } catch (JSONException unused) {
            return uri;
        }
    }

    @RecentlyNonNull
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            if (Objects.equal(this.zab, webImage.zab) && this.zac == webImage.zac && this.zad == webImage.zad) {
                return true;
            }
        }
        return false;
    }

    @RecentlyNonNull
    public final int getHeight() {
        return this.zad;
    }

    @RecentlyNonNull
    public final Uri getUrl() {
        return this.zab;
    }

    @RecentlyNonNull
    public final int getWidth() {
        return this.zac;
    }

    @RecentlyNonNull
    public final int hashCode() {
        return Objects.hashCode(this.zab, Integer.valueOf(this.zac), Integer.valueOf(this.zad));
    }

    @RecentlyNonNull
    @KeepForSdk
    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zab.toString());
            jSONObject.put("width", this.zac);
            jSONObject.put("height", this.zad);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @RecentlyNonNull
    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.zac), Integer.valueOf(this.zad), this.zab.toString());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@RecentlyNonNull Parcel parcel, @RecentlyNonNull int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i9, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public WebImage(@RecentlyNonNull Uri uri, @RecentlyNonNull int i9, @RecentlyNonNull int i10) {
        this(1, uri, i9, i10);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (i9 < 0 || i10 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(@RecentlyNonNull Uri uri) {
        this(uri, 0, 0);
    }

    @KeepForSdk
    public WebImage(@RecentlyNonNull JSONObject jSONObject) {
        this(zaa(jSONObject), jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }
}
