package androidx.versionedparcelable;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import p180r0.C6181a;
import p180r0.InterfaceC6182b;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new C0546a();

    /* renamed from: b */
    public final InterfaceC6182b f2976b;

    /* renamed from: androidx.versionedparcelable.ParcelImpl$a */
    public static class C0546a implements Parcelable.Creator<ParcelImpl> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ParcelImpl createFromParcel(Parcel parcel) {
            return new ParcelImpl(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ParcelImpl[] newArray(int i9) {
            return new ParcelImpl[i9];
        }
    }

    public ParcelImpl(Parcel parcel) {
        this.f2976b = new C6181a(parcel).m3161u();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        new C6181a(parcel).m3138L(this.f2976b);
    }
}
