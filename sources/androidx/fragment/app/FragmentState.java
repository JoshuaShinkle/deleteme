package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new C0360a();

    /* renamed from: b */
    public final String f1988b;

    /* renamed from: c */
    public final String f1989c;

    /* renamed from: d */
    public final boolean f1990d;

    /* renamed from: e */
    public final int f1991e;

    /* renamed from: f */
    public final int f1992f;

    /* renamed from: g */
    public final String f1993g;

    /* renamed from: h */
    public final boolean f1994h;

    /* renamed from: i */
    public final boolean f1995i;

    /* renamed from: j */
    public final boolean f1996j;

    /* renamed from: k */
    public final Bundle f1997k;

    /* renamed from: l */
    public final boolean f1998l;

    /* renamed from: m */
    public final int f1999m;

    /* renamed from: n */
    public Bundle f2000n;

    /* renamed from: o */
    public Fragment f2001o;

    /* renamed from: androidx.fragment.app.FragmentState$a */
    public static class C0360a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i9) {
            return new FragmentState[i9];
        }
    }

    public FragmentState(Fragment fragment) {
        this.f1988b = fragment.getClass().getName();
        this.f1989c = fragment.mWho;
        this.f1990d = fragment.mFromLayout;
        this.f1991e = fragment.mFragmentId;
        this.f1992f = fragment.mContainerId;
        this.f1993g = fragment.mTag;
        this.f1994h = fragment.mRetainInstance;
        this.f1995i = fragment.mRemoving;
        this.f1996j = fragment.mDetached;
        this.f1997k = fragment.mArguments;
        this.f1998l = fragment.mHidden;
        this.f1999m = fragment.mMaxState.ordinal();
    }

    /* renamed from: a */
    public Fragment m1780a(ClassLoader classLoader, C0366e c0366e) {
        if (this.f2001o == null) {
            Bundle bundle = this.f1997k;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentMo1840a = c0366e.mo1840a(classLoader, this.f1988b);
            this.f2001o = fragmentMo1840a;
            fragmentMo1840a.setArguments(this.f1997k);
            Bundle bundle2 = this.f2000n;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.f2001o.mSavedFragmentState = this.f2000n;
            } else {
                this.f2001o.mSavedFragmentState = new Bundle();
            }
            Fragment fragment = this.f2001o;
            fragment.mWho = this.f1989c;
            fragment.mFromLayout = this.f1990d;
            fragment.mRestored = true;
            fragment.mFragmentId = this.f1991e;
            fragment.mContainerId = this.f1992f;
            fragment.mTag = this.f1993g;
            fragment.mRetainInstance = this.f1994h;
            fragment.mRemoving = this.f1995i;
            fragment.mDetached = this.f1996j;
            fragment.mHidden = this.f1998l;
            fragment.mMaxState = Lifecycle.State.values()[this.f1999m];
            if (LayoutInflaterFactory2C0369h.f2016I) {
                Log.v("FragmentManager", "Instantiated fragment " + this.f2001o);
            }
        }
        return this.f2001o;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1988b);
        sb.append(" (");
        sb.append(this.f1989c);
        sb.append(")}:");
        if (this.f1990d) {
            sb.append(" fromLayout");
        }
        if (this.f1992f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1992f));
        }
        String str = this.f1993g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1993g);
        }
        if (this.f1994h) {
            sb.append(" retainInstance");
        }
        if (this.f1995i) {
            sb.append(" removing");
        }
        if (this.f1996j) {
            sb.append(" detached");
        }
        if (this.f1998l) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeString(this.f1988b);
        parcel.writeString(this.f1989c);
        parcel.writeInt(this.f1990d ? 1 : 0);
        parcel.writeInt(this.f1991e);
        parcel.writeInt(this.f1992f);
        parcel.writeString(this.f1993g);
        parcel.writeInt(this.f1994h ? 1 : 0);
        parcel.writeInt(this.f1995i ? 1 : 0);
        parcel.writeInt(this.f1996j ? 1 : 0);
        parcel.writeBundle(this.f1997k);
        parcel.writeInt(this.f1998l ? 1 : 0);
        parcel.writeBundle(this.f2000n);
        parcel.writeInt(this.f1999m);
    }

    public FragmentState(Parcel parcel) {
        this.f1988b = parcel.readString();
        this.f1989c = parcel.readString();
        this.f1990d = parcel.readInt() != 0;
        this.f1991e = parcel.readInt();
        this.f1992f = parcel.readInt();
        this.f1993g = parcel.readString();
        this.f1994h = parcel.readInt() != 0;
        this.f1995i = parcel.readInt() != 0;
        this.f1996j = parcel.readInt() != 0;
        this.f1997k = parcel.readBundle();
        this.f1998l = parcel.readInt() != 0;
        this.f2000n = parcel.readBundle();
        this.f1999m = parcel.readInt();
    }
}
