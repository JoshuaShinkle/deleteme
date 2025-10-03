package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes.dex */
class FragmentTabHost$SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<FragmentTabHost$SavedState> CREATOR = new C0361a();

    /* renamed from: b */
    public String f2002b;

    /* renamed from: androidx.fragment.app.FragmentTabHost$SavedState$a */
    public static class C0361a implements Parcelable.Creator<FragmentTabHost$SavedState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentTabHost$SavedState createFromParcel(Parcel parcel) {
            return new FragmentTabHost$SavedState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentTabHost$SavedState[] newArray(int i9) {
            return new FragmentTabHost$SavedState[i9];
        }
    }

    public FragmentTabHost$SavedState(Parcel parcel) {
        super(parcel);
        this.f2002b = parcel.readString();
    }

    public String toString() {
        return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f2002b + "}";
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        super.writeToParcel(parcel, i9);
        parcel.writeString(this.f2002b);
    }
}
