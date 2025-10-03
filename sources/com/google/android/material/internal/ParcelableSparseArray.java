package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* loaded from: classes2.dex */
public class ParcelableSparseArray extends SparseArray<Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR = new Parcelable.ClassLoaderCreator<ParcelableSparseArray>() { // from class: com.google.android.material.internal.ParcelableSparseArray.1
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray[] newArray(int i9) {
            return new ParcelableSparseArray[i9];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ParcelableSparseArray createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ParcelableSparseArray(parcel, classLoader);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray createFromParcel(Parcel parcel) {
            return new ParcelableSparseArray(parcel, null);
        }
    };

    public ParcelableSparseArray() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int size = size();
        int[] iArr = new int[size];
        Parcelable[] parcelableArr = new Parcelable[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = keyAt(i10);
            parcelableArr[i10] = valueAt(i10);
        }
        parcel.writeInt(size);
        parcel.writeIntArray(iArr);
        parcel.writeParcelableArray(parcelableArr, i9);
    }

    public ParcelableSparseArray(Parcel parcel, ClassLoader classLoader) {
        int i9 = parcel.readInt();
        int[] iArr = new int[i9];
        parcel.readIntArray(iArr);
        Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        for (int i10 = 0; i10 < i9; i10++) {
            put(iArr[i10], parcelableArray[i10]);
        }
    }
}
