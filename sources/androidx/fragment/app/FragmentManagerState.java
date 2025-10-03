package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new C0359a();

    /* renamed from: b */
    public ArrayList<FragmentState> f1983b;

    /* renamed from: c */
    public ArrayList<String> f1984c;

    /* renamed from: d */
    public BackStackState[] f1985d;

    /* renamed from: e */
    public String f1986e;

    /* renamed from: f */
    public int f1987f;

    /* renamed from: androidx.fragment.app.FragmentManagerState$a */
    public static class C0359a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState[] newArray(int i9) {
            return new FragmentManagerState[i9];
        }
    }

    public FragmentManagerState() {
        this.f1986e = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeTypedList(this.f1983b);
        parcel.writeStringList(this.f1984c);
        parcel.writeTypedArray(this.f1985d, i9);
        parcel.writeString(this.f1986e);
        parcel.writeInt(this.f1987f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f1986e = null;
        this.f1983b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1984c = parcel.createStringArrayList();
        this.f1985d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f1986e = parcel.readString();
        this.f1987f = parcel.readInt();
    }
}
