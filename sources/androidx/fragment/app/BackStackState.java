package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.AbstractC0372k;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new C0350a();

    /* renamed from: b */
    public final int[] f1945b;

    /* renamed from: c */
    public final ArrayList<String> f1946c;

    /* renamed from: d */
    public final int[] f1947d;

    /* renamed from: e */
    public final int[] f1948e;

    /* renamed from: f */
    public final int f1949f;

    /* renamed from: g */
    public final int f1950g;

    /* renamed from: h */
    public final String f1951h;

    /* renamed from: i */
    public final int f1952i;

    /* renamed from: j */
    public final int f1953j;

    /* renamed from: k */
    public final CharSequence f1954k;

    /* renamed from: l */
    public final int f1955l;

    /* renamed from: m */
    public final CharSequence f1956m;

    /* renamed from: n */
    public final ArrayList<String> f1957n;

    /* renamed from: o */
    public final ArrayList<String> f1958o;

    /* renamed from: p */
    public final boolean f1959p;

    /* renamed from: androidx.fragment.app.BackStackState$a */
    public static class C0350a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i9) {
            return new BackStackState[i9];
        }
    }

    public BackStackState(C0362a c0362a) {
        int size = c0362a.f2088a.size();
        this.f1945b = new int[size * 5];
        if (!c0362a.f2095h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1946c = new ArrayList<>(size);
        this.f1947d = new int[size];
        this.f1948e = new int[size];
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            AbstractC0372k.a aVar = c0362a.f2088a.get(i9);
            int i11 = i10 + 1;
            this.f1945b[i10] = aVar.f2106a;
            ArrayList<String> arrayList = this.f1946c;
            Fragment fragment = aVar.f2107b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1945b;
            int i12 = i11 + 1;
            iArr[i11] = aVar.f2108c;
            int i13 = i12 + 1;
            iArr[i12] = aVar.f2109d;
            int i14 = i13 + 1;
            iArr[i13] = aVar.f2110e;
            iArr[i14] = aVar.f2111f;
            this.f1947d[i9] = aVar.f2112g.ordinal();
            this.f1948e[i9] = aVar.f2113h.ordinal();
            i9++;
            i10 = i14 + 1;
        }
        this.f1949f = c0362a.f2093f;
        this.f1950g = c0362a.f2094g;
        this.f1951h = c0362a.f2097j;
        this.f1952i = c0362a.f2005u;
        this.f1953j = c0362a.f2098k;
        this.f1954k = c0362a.f2099l;
        this.f1955l = c0362a.f2100m;
        this.f1956m = c0362a.f2101n;
        this.f1957n = c0362a.f2102o;
        this.f1958o = c0362a.f2103p;
        this.f1959p = c0362a.f2104q;
    }

    /* renamed from: a */
    public C0362a m1755a(LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h) {
        C0362a c0362a = new C0362a(layoutInflaterFactory2C0369h);
        int i9 = 0;
        int i10 = 0;
        while (i9 < this.f1945b.length) {
            AbstractC0372k.a aVar = new AbstractC0372k.a();
            int i11 = i9 + 1;
            aVar.f2106a = this.f1945b[i9];
            if (LayoutInflaterFactory2C0369h.f2016I) {
                Log.v("FragmentManager", "Instantiate " + c0362a + " op #" + i10 + " base fragment #" + this.f1945b[i11]);
            }
            String str = this.f1946c.get(i10);
            if (str != null) {
                aVar.f2107b = layoutInflaterFactory2C0369h.f2031h.get(str);
            } else {
                aVar.f2107b = null;
            }
            aVar.f2112g = Lifecycle.State.values()[this.f1947d[i10]];
            aVar.f2113h = Lifecycle.State.values()[this.f1948e[i10]];
            int[] iArr = this.f1945b;
            int i12 = i11 + 1;
            int i13 = iArr[i11];
            aVar.f2108c = i13;
            int i14 = i12 + 1;
            int i15 = iArr[i12];
            aVar.f2109d = i15;
            int i16 = i14 + 1;
            int i17 = iArr[i14];
            aVar.f2110e = i17;
            int i18 = iArr[i16];
            aVar.f2111f = i18;
            c0362a.f2089b = i13;
            c0362a.f2090c = i15;
            c0362a.f2091d = i17;
            c0362a.f2092e = i18;
            c0362a.m1983e(aVar);
            i10++;
            i9 = i16 + 1;
        }
        c0362a.f2093f = this.f1949f;
        c0362a.f2094g = this.f1950g;
        c0362a.f2097j = this.f1951h;
        c0362a.f2005u = this.f1952i;
        c0362a.f2095h = true;
        c0362a.f2098k = this.f1953j;
        c0362a.f2099l = this.f1954k;
        c0362a.f2100m = this.f1955l;
        c0362a.f2101n = this.f1956m;
        c0362a.f2102o = this.f1957n;
        c0362a.f2103p = this.f1958o;
        c0362a.f2104q = this.f1959p;
        c0362a.m1803s(1);
        return c0362a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        parcel.writeIntArray(this.f1945b);
        parcel.writeStringList(this.f1946c);
        parcel.writeIntArray(this.f1947d);
        parcel.writeIntArray(this.f1948e);
        parcel.writeInt(this.f1949f);
        parcel.writeInt(this.f1950g);
        parcel.writeString(this.f1951h);
        parcel.writeInt(this.f1952i);
        parcel.writeInt(this.f1953j);
        TextUtils.writeToParcel(this.f1954k, parcel, 0);
        parcel.writeInt(this.f1955l);
        TextUtils.writeToParcel(this.f1956m, parcel, 0);
        parcel.writeStringList(this.f1957n);
        parcel.writeStringList(this.f1958o);
        parcel.writeInt(this.f1959p ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1945b = parcel.createIntArray();
        this.f1946c = parcel.createStringArrayList();
        this.f1947d = parcel.createIntArray();
        this.f1948e = parcel.createIntArray();
        this.f1949f = parcel.readInt();
        this.f1950g = parcel.readInt();
        this.f1951h = parcel.readString();
        this.f1952i = parcel.readInt();
        this.f1953j = parcel.readInt();
        this.f1954k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1955l = parcel.readInt();
        this.f1956m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1957n = parcel.createStringArrayList();
        this.f1958o = parcel.createStringArrayList();
        this.f1959p = parcel.readInt() != 0;
    }
}
